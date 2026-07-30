package fr.alleretretour.lotr.client.render;

import fr.alleretretour.lotr.client.model.LOTRModelTroll;
import fr.alleretretour.lotr.entity.npc.LOTREntityTroll;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

/** PORT de LOTRRenderTroll : modele de troll converti, texture officielle. */
public class LOTRRenderTroll
        extends MobRenderer<LOTREntityTroll, LOTRModelTroll<LOTREntityTroll>> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation("lotr", "textures/entity/troll/troll/0.png");

    public LOTRRenderTroll(EntityRendererManager manager) {
        super(manager, new LOTRModelTroll<>(), 1.0f);
    }

    @Override
    public ResourceLocation getTextureLocation(LOTREntityTroll entity) {
        return TEXTURE;
    }
}
