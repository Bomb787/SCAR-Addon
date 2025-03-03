package moguns.entities;

import com.mrcrayfish.guns.Config;
import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.entity.ProjectileEntity;
import com.mrcrayfish.guns.event.GunProjectileHitEvent;
import com.mrcrayfish.guns.item.GunItem;
import moguns.init.ParticleInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.MinecraftForge;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Projectile entity for the Taki item, referenced from Mr. Pineapple and borrows most of its code from {@link ProjectileEntity}
 */
public class FlareProjectileEntity extends ProjectileEntity {

	private static final Predicate<BlockState> IGNORE_LEAVES = input -> input != null && Config.COMMON.gameplay.ignoreLeaves.get() && input.getBlock() instanceof LeavesBlock;

	public FlareProjectileEntity(EntityType<? extends ProjectileEntity> entityType, Level worldIn) {
		super(entityType, worldIn);
	}

	public FlareProjectileEntity(EntityType<? extends ProjectileEntity> entityType, Level worldIn, LivingEntity shooter, ItemStack stack, GunItem item, Gun modifiedGun) {
		super(entityType, worldIn, shooter, stack, item, modifiedGun);
	}
	
	@Override
	protected void onProjectileTick() {
		if(this.level.isClientSide) {
			for(int i = 0; i < 2; i++) {
                this.level.addParticle(ParticleInit.FLARE_SMOKE.get(), true, this.getX() - (this.getDeltaMovement().x() / i), this.getY() - (this.getDeltaMovement().y() / i), this.getZ() - (this.getDeltaMovement().z() / i), 0, 0, 0);
                this.level.addParticle(ParticleTypes.LAVA, true, this.getX() - (this.getDeltaMovement().x() / i), this.getY() - (this.getDeltaMovement().y() / i), this.getZ() - (this.getDeltaMovement().z() / i), 0, 0, 0);
                this.level.addParticle(ParticleTypes.LAVA, true, this.getX() - (this.getDeltaMovement().x() / i), this.getY() - (this.getDeltaMovement().y() / i), this.getZ() - (this.getDeltaMovement().z() / i), 0, 0, 0);
                this.level.addParticle(ParticleTypes.LAVA, true, this.getX() - (this.getDeltaMovement().x() / i), this.getY() - (this.getDeltaMovement().y() / i), this.getZ() - (this.getDeltaMovement().z() / i), 0, 0, 0);
            }
		}
	}
	
	@Override
	public void tick() {
		super.tick();
		if(!this.level.isClientSide) {
			Vec3 startVec = this.position();
            Vec3 endVec = startVec.add(this.getDeltaMovement());
            HitResult result = rayTraceBlocks(this.level, new ClipContext(startVec, endVec, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this), IGNORE_LEAVES);
            this.onHit(result, startVec, endVec);
		}
	}
	
	@Override
	protected void onHitEntity(Entity entity, Vec3 hitVec, Vec3 startVec, Vec3 endVec, boolean headshot) {
		super.onHitEntity(entity, hitVec, startVec, endVec, headshot);
		entity.setSecondsOnFire(20);
	}
	
	/**
	 * Sets blocks on fire
	 */
	private void onHit(HitResult result, Vec3 startVec, Vec3 endVec) {
		if(MinecraftForge.EVENT_BUS.post(new GunProjectileHitEvent(result, this))) {
            return;
        }
        if(result instanceof BlockHitResult) {
            BlockHitResult blockRayTraceResult = (BlockHitResult) result;
            if(blockRayTraceResult.getType() == HitResult.Type.MISS) {
                return;
            }
            Vec3 hitVec = result.getLocation();
            BlockPos pos = blockRayTraceResult.getBlockPos();
            if(Config.COMMON.gameplay.griefing.setFireToBlocks.get()) {
                BlockPos offsetPos = pos.relative(blockRayTraceResult.getDirection());
                if(BaseFireBlock.canBePlacedAt(this.level, offsetPos, blockRayTraceResult.getDirection())) {
                    BlockState fireState = BaseFireBlock.getState(this.level, offsetPos);
                    this.level.setBlock(offsetPos, fireState, 11);
                    ((ServerLevel) this.level).sendParticles(ParticleTypes.LAVA, hitVec.x - 1.0 + this.random.nextDouble() * 2.0, hitVec.y, hitVec.z - 1.0 + this.random.nextDouble() * 2.0, 4, 0, 0, 0, 0);
                }
            }
            return;
        }
	}

	/**
     * A custom implementation of {@link //net.minecraft.world.IWorldReader#clip(RayTraceContext)}
     * that allows you to pass a predicate to ignore certain blocks when checking for collisions.
     *
     * @author: Mr. Crayfish
     *
     * @param world     the world to perform the ray trace
     * @param context   the ray trace context
     * @param ignorePredicate the block state predicate
     * @return a result of the raytrace
     */
    private static BlockHitResult rayTraceBlocks(Level world, ClipContext context, Predicate<BlockState> ignorePredicate) {
        return performRayTrace(context, (rayTraceContext, blockPos) -> {
            BlockState blockState = world.getBlockState(blockPos);
            if(ignorePredicate.test(blockState)) return null;
            FluidState fluidState = world.getFluidState(blockPos);
            Vec3 startVec = rayTraceContext.getFrom();
            Vec3 endVec = rayTraceContext.getTo();
            VoxelShape blockShape = rayTraceContext.getBlockShape(blockState, world, blockPos);
            BlockHitResult blockResult = world.clipWithInteractionOverride(startVec, endVec, blockPos, blockShape, blockState);
            VoxelShape fluidShape = rayTraceContext.getFluidShape(fluidState, world, blockPos);
            BlockHitResult fluidResult = fluidShape.clip(startVec, endVec, blockPos);
            double blockDistance = blockResult == null ? Double.MAX_VALUE : rayTraceContext.getFrom().distanceToSqr(blockResult.getLocation());
            double fluidDistance = fluidResult == null ? Double.MAX_VALUE : rayTraceContext.getFrom().distanceToSqr(fluidResult.getLocation());
            return blockDistance <= fluidDistance ? blockResult : fluidResult;
        }, (rayTraceContext) -> {
            Vec3 Vector3d = rayTraceContext.getFrom().subtract(rayTraceContext.getTo());
            return BlockHitResult.miss(rayTraceContext.getTo(), Direction.getNearest(Vector3d.x, Vector3d.y, Vector3d.z), new BlockPos(rayTraceContext.getTo()));
        });
    }
    
    /*
     * Also by Mr. Crayfish
     */
    private static <T> T performRayTrace(ClipContext context, BiFunction<ClipContext, BlockPos, T> hitFunction, Function<ClipContext, T> p_217300_2_) {
        Vec3 startVec = context.getFrom();
        Vec3 endVec = context.getTo();
        if(startVec.equals(endVec)) {
            return p_217300_2_.apply(context);
        } else {
            double startX = Mth.lerp(-0.0000001, endVec.x, startVec.x);
            double startY = Mth.lerp(-0.0000001, endVec.y, startVec.y);
            double startZ = Mth.lerp(-0.0000001, endVec.z, startVec.z);
            double endX = Mth.lerp(-0.0000001, startVec.x, endVec.x);
            double endY = Mth.lerp(-0.0000001, startVec.y, endVec.y);
            double endZ = Mth.lerp(-0.0000001, startVec.z, endVec.z);
            int blockX = Mth.floor(endX);
            int blockY = Mth.floor(endY);
            int blockZ = Mth.floor(endZ);
            BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos(blockX, blockY, blockZ);
            T t = hitFunction.apply(context, mutablePos);
            if(t != null) {
                return t;
            }

            double deltaX = startX - endX;
            double deltaY = startY - endY;
            double deltaZ = startZ - endZ;
            int signX = Mth.sign(deltaX);
            int signY = Mth.sign(deltaY);
            int signZ = Mth.sign(deltaZ);
            double d9 = signX == 0 ? Double.MAX_VALUE : (double) signX / deltaX;
            double d10 = signY == 0 ? Double.MAX_VALUE : (double) signY / deltaY;
            double d11 = signZ == 0 ? Double.MAX_VALUE : (double) signZ / deltaZ;
            double d12 = d9 * (signX > 0 ? 1.0D - Mth.frac(endX) : Mth.frac(endX));
            double d13 = d10 * (signY > 0 ? 1.0D - Mth.frac(endY) : Mth.frac(endY));
            double d14 = d11 * (signZ > 0 ? 1.0D - Mth.frac(endZ) : Mth.frac(endZ));

            while(d12 <= 1.0D || d13 <= 1.0D || d14 <= 1.0D) {
                if(d12 < d13) {
                    if(d12 < d14) {
                        blockX += signX;
                        d12 += d9;
                    } else {
                        blockZ += signZ;
                        d14 += d11;
                    }
                } else if(d13 < d14) {
                    blockY += signY;
                    d13 += d10;
                } else {
                    blockZ += signZ;
                    d14 += d11;
                }

                T t1 = hitFunction.apply(context, mutablePos.set(blockX, blockY, blockZ));
                if(t1 != null) {
                    return t1;
                }
            }
            return p_217300_2_.apply(context);
        }
    }
    
}