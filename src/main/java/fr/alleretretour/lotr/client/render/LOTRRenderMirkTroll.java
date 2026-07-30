package fr.alleretretour.lotr.client.render;

import fr.alleretretour.lotr.client.model.LOTRModelTroll;
import fr.alleretretour.lotr.entity.npc.LOTREntityMirkTroll;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

/** PORT de LOTRRenderTroll : modele converti, texture mob/troll/mirkTroll. */
public class LOTRRenderMirkTroll
        extends MobRenderer<LOTREntityMirkTroll, LOTRModelTroll<LOTREntityMirkTroll>> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation("lotr", "textures/entity/troll/mirk_troll.png");

    public LOTRRenderMirkTroll(EntityRendererManager manager) {
        super(manager, new LOTRModelTroll<>(), 1.0f);
    }

    @Override
    public ResourceLocation getTextureLocation(LOTREntityMirkTroll entity) {
        return TEXTURE;
    }
}
