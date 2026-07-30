package fr.alleretretour.lotr.client.render;

import fr.alleretretour.lotr.client.model.LOTRModelWarg;
import fr.alleretretour.lotr.entity.npc.LOTREntityWargBombardier;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

/** Rendu des wargs bombardiers : modele de warg, pelage selon le type. */
public class LOTRRenderWargBombardier extends
        MobRenderer<LOTREntityWargBombardier, LOTRModelWarg<LOTREntityWargBombardier>> {

    public LOTRRenderWargBombardier(EntityRendererManager manager) {
        super(manager, new LOTRModelWarg<>(), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(LOTREntityWargBombardier entity) {
        return new ResourceLocation("lotr",
                "textures/entity/warg/" + entity.getWargType().textureName() + ".png");
    }
}
