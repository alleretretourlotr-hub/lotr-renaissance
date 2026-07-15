package fr.alleretretour.lotr.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import fr.alleretretour.lotr.entity.projectile.LOTREntityThrownWeapon;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

/**
 * Rendu des armes de jet : l'item plat, oriente selon la trajectoire,
 * incline a 45 degres comme le rendu du mod original (lance plantee en biais).
 */
public class LOTRThrownWeaponRenderer extends EntityRenderer<LOTREntityThrownWeapon> {

    private final ItemRenderer itemRenderer;

    public LOTRThrownWeaponRenderer(EntityRendererManager manager) {
        super(manager);
        this.itemRenderer = Minecraft.getInstance().getItemRenderer();
    }

    @Override
    public void render(LOTREntityThrownWeapon entity, float yaw, float partialTicks,
                       MatrixStack ms, IRenderTypeBuffer buffer, int light) {
        ItemStack stack = entity.getWeapon();
        if (!stack.isEmpty()) {
            ms.pushPose();
            ms.mulPose(Vector3f.YP.rotationDegrees(
                    MathHelper.lerp(partialTicks, entity.yRotO, entity.yRot) - 90.0f));
            ms.mulPose(Vector3f.ZP.rotationDegrees(
                    MathHelper.lerp(partialTicks, entity.xRotO, entity.xRot) + 45.0f));
            itemRenderer.renderStatic(stack, ItemCameraTransforms.TransformType.GROUND,
                    light, net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY, ms, buffer);
            ms.popPose();
        }
        super.render(entity, yaw, partialTicks, ms, buffer, light);
    }

    @Override
    public ResourceLocation getTextureLocation(LOTREntityThrownWeapon entity) {
        return AtlasTexture.LOCATION_BLOCKS;
    }
}
