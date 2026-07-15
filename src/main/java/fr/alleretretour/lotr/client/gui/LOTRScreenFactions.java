package fr.alleretretour.lotr.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import fr.alleretretour.lotr.client.LOTRAlignmentRenderer;
import fr.alleretretour.lotr.fac.LOTRFaction;
import fr.alleretretour.lotr.fac.LOTRFactionRanks;
import fr.alleretretour.lotr.network.LOTRClientAlignments;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;

/**
 * Ecran des factions fidele a LOTRGuiFactions : une REGION a la fois
 * (Terres de l'Ouest / Rhun / Harad), fleches < > pour changer de region,
 * chaque faction affichee avec SA barre d'alignement complete et ses rangs.
 * Clic sur une barre : elle devient la faction suivie dans le HUD.
 */
public class LOTRScreenFactions extends Screen {

    private static LOTRFactionRanks.Region region = LOTRFactionRanks.Region.WEST;
    private int scroll;

    public LOTRScreenFactions() {
        super(new TranslationTextComponent("lotr.gui.factions.title"));
    }

    private Button pledgeButton;

    @Override
    protected void init() {
        pledgeButton = addButton(new Button(width / 2 - 60, height - 26, 120, 20,
                new TranslationTextComponent("lotr.gui.pledge.button"), b -> {
            fr.alleretretour.lotr.network.LOTRPacketHandler.CHANNEL.sendToServer(
                    new fr.alleretretour.lotr.network.LOTRPacketPledge(
                            LOTRClientAlignments.getCurrent()));
            onClose();
        }));
        addButton(new Button(width / 2 + 70, height - 26, 60, 20,
                new TranslationTextComponent("lotr.gui.titles.button"),
                b -> minecraft.setScreen(new LOTRScreenTitles())));
        addButton(new Button(width / 2 - 120, 8, 20, 20, new StringTextComponent("<"), b -> {
            region = LOTRFactionRanks.Region.values()[
                    (region.ordinal() + LOTRFactionRanks.Region.values().length - 1)
                            % LOTRFactionRanks.Region.values().length];
            scroll = 0;
        }));
        addButton(new Button(width / 2 + 100, 8, 20, 20, new StringTextComponent(">"), b -> {
            region = LOTRFactionRanks.Region.values()[
                    (region.ordinal() + 1) % LOTRFactionRanks.Region.values().length];
            scroll = 0;
        }));
    }

    @Override
    public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
        renderBackground(ms);
        String regionName = new TranslationTextComponent(
                "lotr.gui.factions.region." + region.name()).getString();
        drawCenteredString(ms, font, regionName, width / 2, 14, 0xFFFFFF);
        LOTRFaction cur = LOTRClientAlignments.getCurrent();
        boolean pledged = LOTRClientAlignments.getPledge() == cur;
        fr.alleretretour.lotr.fac.LOTRFactionRanks.Rank req =
                fr.alleretretour.lotr.fac.LOTRFactionRanks.getPledgeRank(cur);
        pledgeButton.setMessage(new TranslationTextComponent(
                pledged ? "lotr.gui.pledge.break" : "lotr.gui.pledge.button"));
        pledgeButton.active = pledged || (LOTRClientAlignments.getPledge() == null
                && req != null && LOTRClientAlignments.get(cur) >= req.alignment);
        super.render(ms, mouseX, mouseY, partialTicks);

        List<LOTRFaction> factions = LOTRFactionRanks.factionsOf(region);
        int rowH = 34;
        int y0 = 40 - scroll;
        for (LOTRFaction f : factions) {
            if (y0 > 28 && y0 < height - rowH) {
                float v = LOTRClientAlignments.get(f);
                boolean current = f == LOTRClientAlignments.getCurrent();
                String name = new TranslationTextComponent(f.getTranslationKey()).getString()
                        + (current ? " \u25C0" : "");
                drawCenteredString(ms, font, name, width / 2, y0, 0xFF000000 | f.color);
                ms.pushPose();
                ms.scale(0.5f, 0.5f, 1.0f);
                LOTRAlignmentRenderer.renderBar(ms, f, v, width, (y0 + 10) * 2, 0, true);
                ms.popPose();
            }
            y0 += rowH;
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (super.mouseClicked(mouseX, mouseY, button)) {
            return true;
        }
        List<LOTRFaction> factions = LOTRFactionRanks.factionsOf(region);
        int rowH = 34;
        int y0 = 40 - scroll;
        for (LOTRFaction f : factions) {
            if (mouseY >= y0 - 2 && mouseY < y0 + rowH - 4
                    && Math.abs(mouseX - width / 2.0) < LOTRAlignmentRenderer.BAR_W / 4.0 + 20) {
                LOTRClientAlignments.setCurrent(f);
                onClose();
                return true;
            }
            y0 += rowH;
        }
        return false;
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        int maxScroll = Math.max(0,
                LOTRFactionRanks.factionsOf(region).size() * 34 + 50 - height);
        scroll = Math.max(0, Math.min(maxScroll, scroll - (int) (delta * 20)));
        return true;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
