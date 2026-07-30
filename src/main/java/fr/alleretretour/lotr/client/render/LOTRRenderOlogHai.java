package fr.alleretretour.lotr.client.render;

import fr.alleretretour.lotr.client.model.LOTRModelTroll;
import fr.alleretretour.lotr.entity.npc.LOTREntityOlogHai;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

/** PORT de LOTRRenderOlogHai : modele de troll converti, texture officielle. */
public class LOTRRenderOlogHai
        extends MobRenderer<LOTREntityOlogHai, LOTRModelTroll<LOTREntityOlogHai>> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation("lotr", "textures/entity/troll/olog_hai/0.png");

    public LOTRRenderOlogHai(EntityRendererManager manager) {
        super(manager, new LOTRModelTroll<>(), 1.0f);
    }

    @Override
    public ResourceLocation getTextureLocation(LOTREntityOlogHai entity) {
        return TEXTURE;
    }
}
