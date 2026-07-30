package fr.alleretretour.lotr.client.render;

import fr.alleretretour.lotr.client.model.LOTRModelRhino;
import fr.alleretretour.lotr.entity.animal.LOTREntityRhino;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

/** PORT de LOTRRenderRhino : texture officielle mob/rhino/rhino.png. */
public class LOTRRenderRhino
        extends MobRenderer<LOTREntityRhino, LOTRModelRhino<LOTREntityRhino>> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation("lotr", "textures/entity/rhino/rhino.png");

    public LOTRRenderRhino(EntityRendererManager manager) {
        super(manager, new LOTRModelRhino<>(), 0.9f);
        addLayer(new LOTRLayerMountArmor<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(LOTREntityRhino entity) {
        return TEXTURE;
    }
}
