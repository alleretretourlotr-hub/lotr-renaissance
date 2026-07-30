package fr.alleretretour.lotr.client.render;

import fr.alleretretour.lotr.client.model.LOTRModelBoar;
import fr.alleretretour.lotr.entity.animal.LOTREntityWildBoar;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

/** PORT de LOTRRenderWildBoar : texture officielle mob/boar/boar.png. */
public class LOTRRenderWildBoar
        extends MobRenderer<LOTREntityWildBoar, LOTRModelBoar<LOTREntityWildBoar>> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation("lotr", "textures/entity/boar/0.png");

    public LOTRRenderWildBoar(EntityRendererManager manager) {
        super(manager, new LOTRModelBoar<>(), 0.7f);
        addLayer(new LOTRLayerMountArmor<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(LOTREntityWildBoar entity) {
        return TEXTURE;
    }
}
