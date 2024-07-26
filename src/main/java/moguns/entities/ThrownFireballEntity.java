package moguns.entities;

import com.mrcrayfish.guns.Config;
import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.item.GunItem;

import moguns.init.ParticleInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class ThrownFireballEntity extends ThrowableProjectile {
	
	private float damage;
	private int maxLife;

	public ThrownFireballEntity(EntityType<? extends ThrowableProjectile> pEntityType, Level worldIn) {
		super(pEntityType, worldIn);
	}
	
	public ThrownFireballEntity(EntityType<? extends ThrowableProjectile> entityType, Level worldIn, LivingEntity shooter, ItemStack stack, GunItem item, Gun modifiedGun) {
		this(entityType, worldIn);
		this.setOwner(shooter);
		this.maxLife = modifiedGun.getProjectile().getLife();
		this.damage = modifiedGun.getProjectile().getDamage();
	}
	
	@Override
	public void tick() {
		if(this.tickCount >= this.maxLife) {
			this.remove(RemovalReason.DISCARDED);
		}
		super.tick();
		if(this.level.isClientSide) {
			for(int i = 0; i < 5; i++) {
                this.level.addParticle(ParticleInit.FIREBALL_PARTICLES.get(), true, this.getX() - (this.getDeltaMovement().x() / i), this.getY() - (this.getDeltaMovement().y() / i), this.getZ() - (this.getDeltaMovement().z() / i), 0, 0, 0);
			}
		}
		if(this.getDeltaMovement().length() > 3) {
			Vec3 vec = this.getDeltaMovement().normalize();
			this.setDeltaMovement(vec.x * 3, vec.y * 3, vec.z * 3);
		}
	}
	
	@Override
	protected void onHitBlock(BlockHitResult result) {
		Vec3 hitVec = result.getLocation();
        BlockPos pos = result.getBlockPos();
        if(Config.COMMON.gameplay.griefing.setFireToBlocks.get()) {
            BlockPos offsetPos = pos.relative(result.getDirection());
            if(BaseFireBlock.canBePlacedAt(this.level, offsetPos, result.getDirection())) {
                BlockState fireState = BaseFireBlock.getState(this.level, offsetPos);
                this.level.setBlock(offsetPos, fireState, 11);
                ((ServerLevel) this.level).sendParticles(ParticleTypes.LAVA, hitVec.x - 1.0 + this.random.nextDouble() * 2.0, hitVec.y, hitVec.z - 1.0 + this.random.nextDouble() * 2.0, 4, 0, 0, 0, 0);
            }
        }
	}
	
	@Override
	protected void onHitEntity(EntityHitResult result) {
		super.onHitEntity(result);
		Entity entity = result.getEntity();
		entity.hurt(DamageSource.mobAttack((LivingEntity)this.getOwner()), this.damage);
		entity.setSecondsOnFire(10);
	}

	@Override
	protected void defineSynchedData() {}

}