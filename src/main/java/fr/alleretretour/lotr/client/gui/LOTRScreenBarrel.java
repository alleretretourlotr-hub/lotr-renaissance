package fr.alleretretour.lotr.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import fr.alleretretour.lotr.inventory.LOTRContainerBarrel;
import fr.alleretretour.lotr.tileentity.LOTRTileEntityBarrel;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

/**
 * Ecran du tonneau (dispo du Legacy) : titre + ligne d'etat, grille 3x3,
 * slot de service, cuve graduee qui se remplit, bouton "Brasser".
 */
public class LOTRScreenBarrel extends ContainerScreen<LOTRContainerBarrel> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation("lotr", "textures/gui/barrel.png");
    private static final ResourceLocation BREWING =
            new ResourceLocation("lotr", "textures/gui/barrel_brewing.png");
    private static final String[] STRENGTH_KEYS = {"weak", "light", "moderate", "strong", "potent"};
    private static final int TANK_X0 = 148, TANK_X1 = 196, TANK_Y0 = 34, TANK_Y1 = 130;
    private static final int LIQUID_COLOR = 0xFFC0701B;

    private Button brewButton;

    public LOTRScreenBarrel(LOTRContainerBarrel container, PlayerInventory inv, ITextComponent title) {
        super(container, inv, title);
        imageWidth = 210;
        imageHeight = 221;
        inventoryLabelX = 25;
        inventoryLabelY = 127;
    }

    @Override
    protected void init() {
        super.init();
        brewButton = addButton(new Button(leftPos + 14, topPos + 96, 122, 20,
                new TranslationTextComponent("lotr.gui.barrel.brew"),
                b -> minecraft.gameMode.handleInventoryButtonClick(menu.containerId, 0)));
    }

    @Override
    public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
        renderBackground(ms);
        brewButton.active = menu.getServings() == 0 && menu.getBrewingTime() == 0;
        super.render(ms, mouseX, mouseY, partialTicks);
        renderTooltip(ms, mouseX, mouseY);
    }

    @Override
    protected void renderBg(MatrixStack ms, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        minecraft.getTextureManager().bind(TEXTURE);
        blit(ms, leftPos, topPos, 0, 0, imageWidth, imageHeight, 256, 256);

        int brewing = menu.getBrewingTime();
        int servings = menu.getServings();
        int tankHeight = TANK_Y1 - TANK_Y0;
        int level = 0;
        if (brewing > 0) {
            level = brewing * tankHeight / LOTRTileEntityBarrel.BREW_TICKS;
        } else if (servings > 0) {
            level = servings * tankHeight / LOTRTileEntityBarrel.CAPACITY;
        }
        if (level > 0) {
            int y0 = topPos + TANK_Y1 - level;
            fill(ms, leftPos + TANK_X0, y0, leftPos + TANK_X1, topPos + TANK_Y1, LIQUID_COLOR);
            if (brewing > 0) {
                minecraft.getTextureManager().bind(BREWING);
                RenderSystem.enableBlend();
                blit(ms, leftPos + TANK_X0, y0, 51, 0, TANK_X1 - TANK_X0, level, 256, 256);
                RenderSystem.disableBlend();
            }
        }
    }

    @Override
    protected void renderLabels(MatrixStack ms, int mouseX, int mouseY) {
        font.draw(ms, title, (imageWidth - font.width(title)) / 2.0f, 6, 4210752);
        font.draw(ms, inventory.getDisplayName(), inventoryLabelX, inventoryLabelY, 4210752);

        ITextComponent status;
        if (menu.getBrewingTime() > 0) {
            status = new TranslationTextComponent("lotr.gui.barrel.brewing");
        } else if (menu.getServings() > 0) {
            Item drink = Item.byId(menu.getDrinkItemId());
            String strength = new TranslationTextComponent("lotr.drink." + STRENGTH_KEYS[
                    Math.min(Math.max(menu.getStrength(), 0), 4)]).getString();
            status = drink.getDescription().plainCopy()
                    .append(" (" + strength + ") - " + menu.getServings() + "/"
                            + LOTRTileEntityBarrel.CAPACITY);
        } else {
            status = new TranslationTextComponent("lotr.gui.barrel.empty");
        }
        font.draw(ms, status, (imageWidth - font.width(status)) / 2.0f, 17, 4210752);
    }
}
