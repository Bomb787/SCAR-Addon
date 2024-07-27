package moguns.client.render.gun.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mrcrayfish.guns.client.render.gun.IOverrideModel;
import com.mrcrayfish.guns.client.util.RenderUtil;
import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.init.ModItems;
import com.mrcrayfish.guns.item.attachment.IAttachment;
import moguns.client.SpecialModels;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Nullable;

public class M107CustomModel implements IOverrideModel {

    @Override
    public void render(float v, ItemTransforms.TransformType transformType, ItemStack itemStack, ItemStack parent, @Nullable LivingEntity entity, PoseStack stack, MultiBufferSource buffer, int light, int overlay) {
        RenderUtil.renderModel(SpecialModels.M107_MAIN.getModel(), itemStack, stack, buffer, light, overlay);
        if(Gun.getAttachment(IAttachment.Type.SCOPE, itemStack).getItem() == Items.AIR) {
            RenderUtil.renderModel(SpecialModels.M107_SIGHTS.getModel(), itemStack, stack, buffer, light, overlay);
        }
        if(entity.equals(Minecraft.getInstance().player)) {
            stack.pushPose();
            stack.translate(0, -5.8 * 0.0625, 0);
            ItemCooldowns tracker = Minecraft.getInstance().player.getCooldowns();
            float cooldown = tracker.getCooldownPercent(itemStack.getItem(), Minecraft.getInstance().getFrameTime());
            float barrelCooldown = (float) easeBarrel(cooldown);
            cooldown = (float) ease(cooldown);
            stack.translate(0, 0, barrelCooldown/7.5);
            stack.translate(0, 5.8 * 0.0625, 0);
            RenderUtil.renderModel(SpecialModels.M107_BARREL.getModel(), itemStack, stack, buffer, light, overlay);
            stack.popPose();

            stack.pushPose();
            stack.translate(0, -5.8 * 0.0625, 0);
            stack.translate(0, 0, cooldown/5.0);
            stack.translate(0, 5.8 * 0.0625, 0);
            RenderUtil.renderModel(SpecialModels.M107_BOLT.getModel(), itemStack, stack, buffer, light, overlay);
            stack.popPose();
        }
    }

    private double easeBarrel(double x) {
        if(x >= 0.5) {
            double i = 1 - (4 * (x - 0.5));
            return 1 - (i * i * i * i);
        }
        return 0;
    }

    private double ease(double x) {
        double i = 1 - (2 * x);
        return 1 - (i*i*i*i);
    }

}