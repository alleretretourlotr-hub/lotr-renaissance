package fr.alleretretour.lotr.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import fr.alleretretour.lotr.client.model.LOTRModelBanner;
import fr.alleretretour.lotr.entity.npc.LOTRBannerBearer;
import fr.alleretretour.lotr.entity.npc.LOTREntityNPC;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

/**
 * Couche de rendu des porte-bannieres : dessine la hampe + l'etoffe (modele
 * LOTRModelBanner, sans le socle) dressees contre le flanc gauche du porteur,
 * comme le rendu d'objet equipe du Legacy - sans passer par l'item en main.
 */
public class LOTRLayerBanner<T extends LOTREntityNPC, M extends BipedModel<T>>
        extends LayerRenderer<T, M> {

    private static final LOTRModelBanner MODEL = new LOTRModelBanner();
    private static final Map<String, ResourceLocation> CACHE = new HashMap<>();

    public LOTRLayerBanner(IEntityRenderer<T, M> parent) {
        super(parent);
    }

    @Override
    public void render(MatrixStack ms, IRenderTypeBuffer buffers, int light, T entity,
                       float limbSwing, float limbSwingAmount, float partialTicks,
                       float ageInTicks, float netHeadYaw, float headPitch) {
        if (!(entity instanceof LOTRBannerBearer)) {
            return;
        }
        String id = ((LOTRBannerBearer) entity).getBannerItemId();
        ResourceLocation texture = CACHE.computeIfAbsent(id, key ->
                new ResourceLocation("lotr", "textures/item/"
                        + key.substring(key.indexOf(':') + 1) + ".png"));
        ms.pushPose();
        // suit le buste (penche avec le sprint / l'accroupissement)
        getParentModel().body.translateAndRotate(ms);
        // flanc gauche, hampe verticale, legerement en retrait
        ms.translate(0.45, 0.0, 0.12);
        ms.scale(0.85f, 0.85f, 0.85f);
        // le modele place sa base a y=24/16 (les pieds) : la hampe depasse la tete
        ms.translate(0.0, -1.5 + 1.5 * (1.0 / 0.85 - 1.0), 0.0);
        IVertexBuilder buf = buffers.getBuffer(RenderType.entityCutoutNoCull(texture));
        MODEL.renderToBuffer(ms, buf, light, OverlayTexture.NO_OVERLAY,
                1.0f, 1.0f, 1.0f, 1.0f);
        ms.popPose();
    }
}
