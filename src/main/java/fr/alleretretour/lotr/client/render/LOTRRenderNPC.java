package fr.alleretretour.lotr.client.render;

import fr.alleretretour.lotr.entity.npc.LOTREntityNPC;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import fr.alleretretour.lotr.client.model.LOTRModelNPC;
import net.minecraft.util.ResourceLocation;

/**
 * Renderer generique des PNJ humanoides.
 * LOTRModelNPC : modele du Legacy (cheveux en UV 0,32, membres gauches
 * en miroir - les peaux du Legacy n'ont pas de membres gauches).
 *
 * BLINDAGE : new ResourceLocation(...) leve une ResourceLocationException au
 * moindre caractere invalide (majuscule, espace...). Comme les renderers sont
 * construits par Forge dans un contexte ou toute exception est avalee en
 * silence (cf. saga "entite SANS RENDERER"), on ne laisse plus JAMAIS ce
 * constructeur lever : chemin invalide -> log "LOTR ERREUR" + peau de repli.
 */
public class LOTRRenderNPC<T extends LOTREntityNPC> extends BipedRenderer<T, LOTRModelNPC<T>> {

    /** Peau de repli si un chemin est invalide (visible mais jamais crashant). */
    private static final ResourceLocation FALLBACK =
            new ResourceLocation("minecraft", "textures/entity/steve.png");

    private final ResourceLocation[] textures;
    private final float renderScale;

    public LOTRRenderNPC(EntityRendererManager manager, String skinFolder, int variants) {
        this(manager, skinFolder, variants, 1.0f, LOTRModelNPC.Hair.LONG, false);
    }

    /** @param renderScale echelle visuelle du modele (0.75 nains, 0.62 hobbits, 1.2 semi-trolls) */
    public LOTRRenderNPC(EntityRendererManager manager, String skinFolder, int variants,
                         float renderScale) {
        this(manager, skinFolder, variants, renderScale, LOTRModelNPC.Hair.LONG, false);
    }

    /**
     * @param hair        chevelure du Legacy : LONG (humains, elfes),
     *                    SHORT (nains, hobbits), NONE (orques, trolls)
     * @param pointedEars oreilles pointues des elfes
     */
    public LOTRRenderNPC(EntityRendererManager manager, String skinFolder, int variants,
                         float renderScale, LOTRModelNPC.Hair hair, boolean pointedEars) {
        super(manager, new LOTRModelNPC<>(0.0f, hair, pointedEars), 0.5f * renderScale);
        this.textures = buildTextures(skinFolder, variants);
        this.renderScale = renderScale;
        addLayer(new BipedArmorLayer<>(this,
                new BipedModel<>(0.5f), new BipedModel<>(1.0f)));
        addLayer(new LOTRLayerBanner<>(this));
    }

    @Override
    protected void scale(T entity, com.mojang.blaze3d.matrix.MatrixStack ms, float partialTicks) {
        if (renderScale != 1.0f) {
            ms.scale(renderScale, renderScale, renderScale);
        }
    }

    /** Construit les chemins de peaux sans jamais lever d'exception. */
    static ResourceLocation[] buildTextures(String skinFolder, int variants) {
        ResourceLocation[] result = new ResourceLocation[Math.max(1, variants)];
        for (int i = 0; i < result.length; i++) {
            try {
                result[i] = new ResourceLocation("lotr",
                        "textures/entity/" + skinFolder + "/" + i + ".png");
            } catch (Throwable t) {
                fr.alleretretour.lotr.LOTRMod.LOGGER.error(
                        "LOTR ERREUR peau invalide [" + skinFolder + "/" + i
                        + ".png] (chemin en minuscules uniquement !) : " + t);
                result[i] = FALLBACK;
            }
        }
        return result;
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        int index = Math.floorMod(entity.getUUID().hashCode(), textures.length);
        return textures[index];
    }
}
