package fr.alleretretour.lotr.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import fr.alleretretour.lotr.inventory.LOTRContainerFactionCrafting;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

/**
 * Ecran des tables de faction : reutilise la texture de l'etabli vanilla,
 * le titre porte le nom de la table (« Table de craft du Gondor »...).
 */
public class LOTRScreenFactionCrafting extends ContainerScreen<LOTRContainerFactionCrafting> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation("textures/gui/container/crafting_table.png");

    public LOTRScreenFactionCrafting(LOTRContainerFactionCrafting container, PlayerInventory inv, ITextComponent title) {
        super(container, inv, title);
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
        blit(ms, leftPos, topPos, 0, 0, imageWidth, imageHeight);
    }
}
