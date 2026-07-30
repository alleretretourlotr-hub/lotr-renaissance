package fr.alleretretour.lotr.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import fr.alleretretour.lotr.inventory.LOTRContainerHire;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

/**
 * PORT de l'ecran d'embauche (unit trade) : texture officielle unit_trade.png,
 * liste TEXTUELLE des unites - nom, cout en pieces, alignement requis -
 * doree si accessible, grisee/rouge sinon. Clic sur une ligne = embauche
 * (clickMenuButton vanilla), l'unite rejoint le joueur.
 */
public class LOTRScreenHire extends ContainerScreen<LOTRContainerHire> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation("lotr", "textures/gui/npc/unit_trade.png");
    private static final int ROW_H = 15;
    private static final int LIST_X = 12;
    private static final int LIST_Y = 40;

    public LOTRScreenHire(LOTRContainerHire container, PlayerInventory inv, ITextComponent title) {
        super(container, inv, title);
        imageWidth = 200;
        imageHeight = 220;
    }

    @Override
    public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
        renderBackground(ms);
        super.render(ms, mouseX, mouseY, partialTicks);
        int row = hoveredRow(mouseX, mouseY);
        if (row >= 0) {
            int cost = menu.cost(row);
            int req = menu.minAlignment(row);
            java.util.List<ITextComponent> lines = new java.util.ArrayList<>();
            lines.add(new StringTextComponent(rowLabel(row)));
            lines.add(new StringTextComponent("\u00a76" + cost + " pieces d'argent"
                    + (menu.getPurse() >= cost ? "" : " \u00a7c(bourse insuffisante)")));
            lines.add(new StringTextComponent((menu.getAlignment() >= req ? "\u00a7a" : "\u00a7c")
                    + "Alignement requis : " + req));
            String pledgeReq = menu.pledgeType(row).requirementText();
            if (pledgeReq != null) {
                lines.add(new StringTextComponent(
                        (menu.pledgeOk(row) ? "\u00a7a" : "\u00a7c") + pledgeReq));
            }
            lines.add(new StringTextComponent("\u00a77Clic pour engager - l'unite vous rejoint."));
            renderComponentTooltip(ms, lines, mouseX, mouseY);
        }
    }

    /** Nom affiche : suffixe "(monte)" pour les entrees a monture. */
    private String rowLabel(int row) {
        return unitName(row) + (menu.isMounted(row) ? " (monte)" : "");
    }

    private String unitName(int row) {
        int id = menu.typeId(row);
        if (id < 0) {
            return "?";
        }
        EntityType<?> type = Registry.ENTITY_TYPE.byId(id);
        return type.getDescription().getString();
    }

    private int hoveredRow(int mouseX, int mouseY) {
        int x = mouseX - leftPos;
        int y = mouseY - topPos;
        if (x >= LIST_X && x < imageWidth - LIST_X && y >= LIST_Y) {
            int row = (y - LIST_Y) / ROW_H;
            if (row < menu.rowCount()) {
                return row;
            }
        }
        return -1;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int row = hoveredRow((int) mouseX, (int) mouseY);
        if (row >= 0 && button == 0) {
            minecraft.gameMode.handleInventoryButtonClick(menu.containerId, row);
            minecraft.getSoundManager().play(net.minecraft.client.audio.SimpleSound.forUI(
                    net.minecraft.util.SoundEvents.UI_BUTTON_CLICK, 1.0f));
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    protected void renderBg(MatrixStack ms, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        minecraft.getTextureManager().bind(TEXTURE);
        blit(ms, leftPos, topPos, 0, 0, imageWidth, imageHeight, 256, 256);
    }

    @Override
    protected void renderLabels(MatrixStack ms, int mouseX, int mouseY) {
        String s = title.getString();
        font.draw(ms, s, imageWidth / 2.0f - font.width(s) / 2.0f, 11, 0x373737);
        font.draw(ms, "Unites disponibles :", LIST_X, 28, 0x373737);

        for (int i = 0; i < menu.rowCount(); i++) {
            int y = LIST_Y + i * ROW_H;
            boolean okAlign = menu.getAlignment() >= menu.minAlignment(i) && menu.pledgeOk(i);
            boolean okCoins = menu.getPurse() >= menu.cost(i);
            int nameColor = okAlign ? 0x373737 : 0x992222;
            font.draw(ms, rowLabel(i), LIST_X, y, nameColor);
            String price = menu.cost(i) + " pa";
            font.draw(ms, price, imageWidth - LIST_X - font.width(price), y,
                    okCoins && okAlign ? 0xB8860B : 0x992222);
        }

        font.draw(ms, "Bourse : " + menu.getPurse() + " pieces", LIST_X, imageHeight - 40, 0xB8860B);
        font.draw(ms, "Alignement : " + menu.getAlignment(), LIST_X, imageHeight - 28, 0x2E7D32);
    }
}
