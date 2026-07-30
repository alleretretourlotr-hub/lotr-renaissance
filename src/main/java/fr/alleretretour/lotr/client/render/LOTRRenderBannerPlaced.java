package fr.alleretretour.lotr.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import fr.alleretretour.lotr.client.model.LOTRModelBanner;
import fr.alleretretour.lotr.entity.item.LOTREntityBannerPlaced;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

import java.util.HashMap;
import java.util.Map;

/**
 * PORT de LOTRRenderBanner : socle sur stand.png, hampe + etoffe sur la
 * texture de faction (le meme png que l'item, comme dans le Legacy).
 * Transformations d'origine : translate +1.5, scale(-1,-1,1), rotate 180-yaw.
 */
public class LOTRRenderBannerPlaced extends EntityRenderer<LOTREntityBannerPlaced> {

    private static final ResourceLocation STAND =
            new ResourceLocation("lotr", "textures/item/banner_stand.png");
    private static final Map<String, ResourceLocation> CACHE = new HashMap<>();
    private final LOTRModelBanner model = new LOTRModelBanner();

    public LOTRRenderBannerPlaced(EntityRendererManager manager) {
        super(manager);
    }

    @Override
    public ResourceLocation getTextureLocation(LOTREntityBannerPlaced entity) {
        return CACHE.computeIfAbsent(entity.getBannerItem(), id ->
                new ResourceLocation("lotr", "textures/item/"
                        + id.substring(id.indexOf(':') + 1) + ".png"));
    }

    @Override
    public void render(LOTREntityBannerPlaced entity, float yaw, float partialTicks,
                       MatrixStack ms, IRenderTypeBuffer buffers, int light) {
        ms.pushPose();
        ms.translate(0.0, 1.5, 0.0);
        ms.scale(-1.0f, -1.0f, 1.0f);
        ms.mulPose(Vector3f.YP.rotationDegrees(180.0f - entity.yRot));
        ms.translate(0.0, 0.01, 0.0);

        IVertexBuilder banner = buffers.getBuffer(
                RenderType.entityCutoutNoCull(getTextureLocation(entity)));
        model.renderToBuffer(ms, banner, light,
                net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY,
                1.0f, 1.0f, 1.0f, 1.0f);
        IVertexBuilder stand = buffers.getBuffer(RenderType.entityCutoutNoCull(STAND));
        model.renderStand(ms, stand, light,
                net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY);

        ms.popPose();
        super.render(entity, yaw, partialTicks, ms, buffers, light);
    }
}
