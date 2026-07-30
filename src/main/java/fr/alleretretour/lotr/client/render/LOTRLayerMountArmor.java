package fr.alleretretour.lotr.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import fr.alleretretour.lotr.entity.animal.LOTRMountArmored;
import fr.alleretretour.lotr.item.LOTRItemMountArmor;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

/**
 * PORT du rendu d'armure de monture : redessine le modele de la monture avec
 * la texture d'armure, comme le renderer du Legacy.
 */
public class LOTRLayerMountArmor<T extends LivingEntity & LOTRMountArmored, M extends EntityModel<T>>
        extends LayerRenderer<T, M> {

    public LOTRLayerMountArmor(IEntityRenderer<T, M> parent) {
        super(parent);
    }

    @Override
    public void render(MatrixStack ms, IRenderTypeBuffer buffers, int light, T entity,
                       float limbSwing, float limbSwingAmount, float partialTicks,
                       float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack stack = entity.getMountArmor();
        if (!(stack.getItem() instanceof LOTRItemMountArmor)) {
            return;
        }
        IVertexBuilder buf = buffers.getBuffer(RenderType.entityCutoutNoCull(
                ((LOTRItemMountArmor) stack.getItem()).getArmorTexture()));
        getParentModel().renderToBuffer(ms, buf, light, OverlayTexture.NO_OVERLAY,
                1.0f, 1.0f, 1.0f, 1.0f);
    }
}
