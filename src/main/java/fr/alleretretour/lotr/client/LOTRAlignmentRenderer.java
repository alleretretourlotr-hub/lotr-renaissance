package fr.alleretretour.lotr.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import fr.alleretretour.lotr.fac.LOTRFaction;
import fr.alleretretour.lotr.fac.LOTRFactionRanks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

/**
 * PORT FIDELE de LOTRTickHandlerClient.renderAlignmentBar : la barre d'alignement
 * avec ses bornes de RANG (et repli par puissances de 10 au-dela du dernier rang).
 * Utilisee par le HUD et par l'ecran des factions.
 */
public class LOTRAlignmentRenderer {

    public static final ResourceLocation TEXTURE =
            new ResourceLocation("lotr", "textures/gui/alignment.png");
    public static final int BAR_W = 232, BAR_H = 14, ACTIVE_W = 220, RING = 16;
    private static final float FIRST_RANK_FALLBACK = 10.0f;

    /** Dessine la barre en coordonnees locales (echelle geree par l'appelant). */
    public static void renderBar(MatrixStack ms, LOTRFaction faction, float alignment,
                                 float x, float y, int flashFrame, boolean renderLimits) {
        boolean pledged = fr.alleretretour.lotr.network.LOTRClientAlignments.getPledge() == faction;
        Minecraft mc = Minecraft.getInstance();

        // bornes selon les rangs, logique originale
        LOTRFactionRanks.Rank rank = LOTRFactionRanks.getRank(faction, alignment);
        float alignMin;
        float alignMax;
        String nameMin;
        String nameMax;
        if (rank == null) {
            LOTRFactionRanks.Rank first = LOTRFactionRanks.getFirstRank(faction);
            float firstAlign = first != null ? first.alignment : FIRST_RANK_FALLBACK;
            if (Math.abs(alignment) < firstAlign) {
                alignMin = -firstAlign;
                alignMax = firstAlign;
                nameMin = key("lotr.faction.rank.enemy");
                nameMax = first != null ? key(first.getTranslationKey())
                        : key("lotr.faction.rank.neutral");
            } else {
                alignMax = -firstAlign;
                alignMin = alignMax * 10.0f;
                while (alignment <= alignMin) {
                    alignMax *= 10.0f;
                    alignMin = alignMax * 10.0f;
                }
                nameMin = nameMax = key("lotr.faction.rank.enemy");
            }
        } else {
            alignMin = rank.alignment;
            nameMin = key(rank.getTranslationKey());
            LOTRFactionRanks.Rank next = LOTRFactionRanks.getRankAbove(faction, alignment);
            if (next != null) {
                alignMax = next.alignment;
                nameMax = key(next.getTranslationKey());
            } else {
                alignMax = rank.alignment * 10.0f;
                while (alignment >= alignMax) {
                    alignMin = alignMax;
                    alignMax = alignMin * 10.0f;
                }
                nameMax = nameMin;
            }
        }
        float ringProgress = (alignment - alignMin) / (alignMax - alignMin);

        float r = ((faction.color >> 16) & 0xFF) / 255.0f;
        float g = ((faction.color >> 8) & 0xFF) / 255.0f;
        float b = (faction.color & 0xFF) / 255.0f;

        mc.getTextureManager().bind(TEXTURE);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.color4f(r, g, b, 1.0f);
        AbstractGui.blit(ms, Math.round(x - BAR_W / 2.0f), Math.round(y), 0, 14, BAR_W, BAR_H, 256, 256);
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        AbstractGui.blit(ms, Math.round(x - BAR_W / 2.0f), Math.round(y), 0, 0, BAR_W, BAR_H, 256, 256);

        float ringProgressAdj = (ringProgress - 0.5f) * 2.0f;
        int ringX = Math.round(x - RING / 2.0f + ringProgressAdj * ACTIVE_W / 2.0f);
        int ringY = Math.round(y + BAR_H / 2.0f - RING / 2.0f);
        AbstractGui.blit(ms, ringX, ringY, 16 * flashFrame, pledged ? 212 : 36, RING, RING, 256, 256);
        RenderSystem.disableBlend();

        if (renderLimits) {
            // noms des rangs aux extremites, comme renderLimits de l'original
            int ty = Math.round(y + BAR_H + 3);
            mc.font.drawShadow(ms, nameMin,
                    x - BAR_W / 2.0f - mc.font.width(nameMin) / 2.0f + 8, ty, 0xBBBBBB);
            mc.font.drawShadow(ms, nameMax,
                    x + BAR_W / 2.0f - mc.font.width(nameMax) / 2.0f - 8, ty, 0xBBBBBB);
        }
    }

    /** Rang courant traduit ("Étranger" si aucun, "Ennemi" si negatif profond). */
    public static String currentRankName(LOTRFaction faction, float alignment) {
        LOTRFactionRanks.Rank rank = LOTRFactionRanks.getRank(faction, alignment);
        if (rank != null) {
            return key(rank.getTranslationKey());
        }
        return alignment < 0 ? key("lotr.faction.rank.enemy") : key("lotr.faction.rank.neutral");
    }

    private static String key(String k) {
        return new TranslationTextComponent(k).getString();
    }
}
