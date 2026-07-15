package fr.alleretretour.lotr.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import fr.alleretretour.lotr.fac.LOTRFaction;
import fr.alleretretour.lotr.fac.LOTRFactionRanks;
import fr.alleretretour.lotr.network.LOTRClientAlignments;
import fr.alleretretour.lotr.network.LOTRPacketHandler;
import fr.alleretretour.lotr.network.LOTRPacketSelectTitle;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Ecran de selection du titre : liste tous les rangs ATTEINTS
 * (toutes factions confondues), colores par faction. Clic = titre choisi.
 */
public class LOTRScreenTitles extends Screen {

    private int scroll;
    private final List<LOTRFactionRanks.Rank> unlocked = new ArrayList<>();

    public LOTRScreenTitles() {
        super(new TranslationTextComponent("lotr.gui.titles.title"));
    }

    @Override
    protected void init() {
        unlocked.clear();
        for (LOTRFaction f : LOTRFaction.values()) {
            float v = LOTRClientAlignments.get(f);
            for (LOTRFactionRanks.Rank r : LOTRFactionRanks.ranksOf(f)) {
                if (v >= r.alignment) {
                    unlocked.add(r);
                }
            }
        }
        addButton(new Button(width / 2 - 60, height - 26, 120, 20,
                new TranslationTextComponent("lotr.gui.titles.none"), b -> {
            LOTRPacketHandler.CHANNEL.sendToServer(new LOTRPacketSelectTitle(null, ""));
            onClose();
        }));
    }

    @Override
    public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
        renderBackground(ms);
        drawCenteredString(ms, font, title, width / 2, 12, 0xFFFFFF);
        if (unlocked.isEmpty()) {
            drawCenteredString(ms, font,
                    new TranslationTextComponent("lotr.gui.titles.empty").getString(),
                    width / 2, height / 2, 0xAAAAAA);
        }
        int y0 = 34 - scroll;
        for (LOTRFactionRanks.Rank r : unlocked) {
            if (y0 > 24 && y0 < height - 30) {
                String label = new TranslationTextComponent(r.getTranslationKey()).getString()
                        + " (" + new TranslationTextComponent(
                                r.faction.getTranslationKey()).getString() + ")";
                drawCenteredString(ms, font, label, width / 2, y0, 0xFF000000 | r.faction.color);
            }
            y0 += 14;
        }
        super.render(ms, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (super.mouseClicked(mouseX, mouseY, button)) {
            return true;
        }
        int index = (int) ((mouseY - 34 + scroll) / 14);
        if (index >= 0 && index < unlocked.size() && Math.abs(mouseX - width / 2.0) < 140) {
            LOTRFactionRanks.Rank r = unlocked.get(index);
            LOTRPacketHandler.CHANNEL.sendToServer(
                    new LOTRPacketSelectTitle(r.faction, r.name));
            onClose();
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        int maxScroll = Math.max(0, unlocked.size() * 14 + 60 - height);
        scroll = Math.max(0, Math.min(maxScroll, scroll - (int) (delta * 20)));
        return true;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
