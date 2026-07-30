package fr.alleretretour.lotr.client.render;

import fr.alleretretour.lotr.client.model.LOTRModelTroll;
import fr.alleretretour.lotr.entity.npc.LOTREntityMountainTroll;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

/** PORT de LOTRRenderMountainTroll : modele de troll converti, texture officielle. */
public class LOTRRenderMountainTroll
        extends MobRenderer<LOTREntityMountainTroll, LOTRModelTroll<LOTREntityMountainTroll>> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation("lotr", "textures/entity/troll/mountain_troll/0.png");

    public LOTRRenderMountainTroll(EntityRendererManager manager) {
        super(manager, new LOTRModelTroll<>(), 1.0f);
    }

    @Override
    public ResourceLocation getTextureLocation(LOTREntityMountainTroll entity) {
        return TEXTURE;
    }
}
