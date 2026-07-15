package fr.alleretretour.lotr.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import fr.alleretretour.lotr.inventory.LOTRContainerTrade;
import fr.alleretretour.lotr.trade.LOTRTradeTables;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

/**
 * PORT de LOTRGuiTrade : la texture officielle trade.png (512x512),
 * prix affiches sous chaque slot d'achat/vente, bourse en pieces.
 */
public class LOTRScreenTrade extends ContainerScreen<LOTRContainerTrade> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation("lotr", "textures/gui/trade.png");

    public LOTRScreenTrade(LOTRContainerTrade container, PlayerInventory inv, ITextComponent title) {
        super(container, inv, title);
        imageWidth = 176;
        imageHeight = 270;
    }

    @Override
    public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
        renderBackground(ms);
        super.render(ms, mouseX, mouseY, partialTicks);
        renderTooltip(ms, mouseX, mouseY);
    }

    @Override
    protected void renderBg(MatrixStack ms, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        minecraft.getTextureManager().bind(TEXTURE);
        // la texture est 512x512, on blit la zone 176x270
        blit(ms, leftPos, topPos, 0, 0, imageWidth, imageHeight, 512, 512);
    }

    @Override
    protected void renderLabels(MatrixStack ms, int mouseX, int mouseY) {
        font.draw(ms, title, 8, 6, 0x404040);
        // prix sous les slots d'achat (rangee y=40 -> texte a y~59)
        for (int i = 0; i < menu.buyCount(); i++) {
            drawPrice(ms, menu.buyCost(i), 8 + i * 18);
        }
        for (int i = 0; i < menu.sellCount(); i++) {
            drawPriceAt(ms, menu.sellCost(i), 8 + i * 18, 111);
        }
        String purse = new TranslationTextComponent("lotr.gui.trade.purse").getString()
                + " " + menu.getPurse();
        font.draw(ms, purse, 8, 168, 0xFFD700);
    }

    private void drawPrice(MatrixStack ms, int cost, int x) {
        drawPriceAt(ms, cost, x, 59);
    }

    private void drawPriceAt(MatrixStack ms, int cost, int x, int y) {
        String s = String.valueOf(cost);
        font.draw(ms, s, x + 9 - font.width(s) / 2.0f, y, 0xFFD700);
    }
}
