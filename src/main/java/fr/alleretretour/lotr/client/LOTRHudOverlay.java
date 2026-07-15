package fr.alleretretour.lotr.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import fr.alleretretour.lotr.fac.LOTRFaction;
import fr.alleretretour.lotr.network.LOTRClientAlignments;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

/**
 * HUD d'alignement fidele : nom de faction colore, barre ornee (renderer partage),
 * valeur et RANG courant ("Garde du Gondor"...), anneau anime sur gain.
 */
public class LOTRHudOverlay {

    private static int flashTick;

    public static void triggerFlash() {
        flashTick = 12;
    }

    public static void onRenderOverlay(RenderGameOverlayEvent.Post event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.HOTBAR) {
            return;
        }
        Minecraft mc = Minecraft.getInstance();
        if (mc.options.hideGui || mc.player == null) {
            return;
        }
        if (flashTick > 0) {
            flashTick--;
        }
        MatrixStack ms = event.getMatrixStack();
        LOTRFaction faction = LOTRClientAlignments.getCurrent();
        float alignment = LOTRClientAlignments.get(faction);

        ms.pushPose();
        ms.scale(0.5f, 0.5f, 1.0f);
        float x = event.getWindow().getGuiScaledWidth();
        float y = 14.0f;
        int frame = flashTick > 0 ? Math.round(flashTick / 3.0f) % 4 : 0;

        LOTRAlignmentRenderer.renderBar(ms, faction, alignment, x, y, frame, false);

        String name = new TranslationTextComponent(faction.getTranslationKey()).getString();
        mc.font.drawShadow(ms, name, x - mc.font.width(name) / 2.0f, y - 11,
                0xFF000000 | faction.color);
        String value = (alignment > 0 ? "+" : "") + trim(alignment);
        String rankName = LOTRAlignmentRenderer.currentRankName(faction, alignment);
        String line = value + "  -  " + rankName;
        mc.font.drawShadow(ms, line, x - mc.font.width(line) / 2.0f,
                y + LOTRAlignmentRenderer.BAR_H + 3, 0xFFFFFF);
        ms.popPose();
    }

    private static String trim(float v) {
        String s = String.format("%.1f", v);
        return s.endsWith(".0") || s.endsWith(",0") ? s.substring(0, s.length() - 2) : s;
    }
}
