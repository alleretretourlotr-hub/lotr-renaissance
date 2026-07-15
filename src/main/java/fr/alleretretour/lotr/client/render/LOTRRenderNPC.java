package fr.alleretretour.lotr.client.render;

import fr.alleretretour.lotr.entity.npc.LOTREntityNPC;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.util.ResourceLocation;

/**
 * Renderer generique des PNJ humanoides.
 * PlayerModel (64x64, avec les couches de vetements des skins modernes) :
 * les peaux du Legacy sont au format 64x64 avec surcouches (tuniques, capuches).
 * Variante de peau stable par entite + armures (nos modeles 3D inclus).
 */
public class LOTRRenderNPC<T extends LOTREntityNPC> extends BipedRenderer<T, PlayerModel<T>> {

    private final ResourceLocation[] textures;

    public LOTRRenderNPC(EntityRendererManager manager, String skinFolder, int variants) {
        super(manager, new PlayerModel<>(0.0f, false), 0.5f);
        this.textures = new ResourceLocation[variants];
        for (int i = 0; i < variants; i++) {
            textures[i] = new ResourceLocation("lotr",
                    "textures/entity/" + skinFolder + "/" + i + ".png");
        }
        addLayer(new BipedArmorLayer<>(this,
                new BipedModel<>(0.5f), new BipedModel<>(1.0f)));
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        int index = Math.floorMod(entity.getUUID().hashCode(), textures.length);
        return textures[index];
    }
}
