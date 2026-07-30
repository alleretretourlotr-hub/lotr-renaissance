package fr.alleretretour.lotr.client.render;

import fr.alleretretour.lotr.entity.animal.LOTREntityZebra;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.HorseModel;
import net.minecraft.util.ResourceLocation;

/** PORT de LOTRRenderZebra : modele de cheval, texture officielle mob/zebra.png. */
public class LOTRRenderZebra
        extends MobRenderer<LOTREntityZebra, HorseModel<LOTREntityZebra>> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation("lotr", "textures/entity/zebra.png");

    public LOTRRenderZebra(EntityRendererManager manager) {
        super(manager, new HorseModel<>(0.0f), 0.75f);
    }

    @Override
    public ResourceLocation getTextureLocation(LOTREntityZebra entity) {
        return TEXTURE;
    }
}
