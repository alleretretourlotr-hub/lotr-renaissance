package fr.alleretretour.lotr.client.render;

import fr.alleretretour.lotr.client.model.LOTRModelElk;
import fr.alleretretour.lotr.entity.animal.LOTREntityElk;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

/** PORT de LOTRRenderElk : modele converti, texture officielle mob/elk/. */
public class LOTRRenderElk extends MobRenderer<LOTREntityElk, LOTRModelElk<LOTREntityElk>> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation("lotr", "textures/entity/elk/0.png");

    public LOTRRenderElk(EntityRendererManager manager) {
        super(manager, new LOTRModelElk<>(), 0.9f);
    }

    @Override
    public ResourceLocation getTextureLocation(LOTREntityElk entity) {
        return TEXTURE;
    }
}
