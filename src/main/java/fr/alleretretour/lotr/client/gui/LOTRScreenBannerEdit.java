package fr.alleretretour.lotr.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import fr.alleretretour.lotr.entity.item.LOTREntityBannerPlaced;
import fr.alleretretour.lotr.network.LOTRPacketBannerEdit;
import fr.alleretretour.lotr.network.LOTRPacketHandler;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

/**
 * PORT de LOTRGuiBanner (essentiel) : texture officielle banner_edit.png,
 * seuil d'alignement editable, liste blanche (ajout par pseudo, retrait au
 * clic). Reserve au poseur de la banniere.
 */
public class LOTRScreenBannerEdit extends Screen {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation("lotr", "textures/gui/banner_edit.png");
    private static final int X_SIZE = 200;
    private static final int Y_SIZE = 220;

    private final LOTREntityBannerPlaced banner;
    private int guiLeft;
    private int guiTop;
    private TextFieldWidget alignField;
    private TextFieldWidget nameField;

    public LOTRScreenBannerEdit(LOTREntityBannerPlaced banner) {
        super(new StringTextComponent("Banniere de protection"));
        this.banner = banner;
    }

    @Override
    protected void init() {
        guiLeft = (width - X_SIZE) / 2;
        guiTop = (height - Y_SIZE) / 2;

        alignField = new TextFieldWidget(font, guiLeft + 12, guiTop + 58, 80, 16,
                new StringTextComponent("alignement"));
        alignField.setValue(String.valueOf((int) banner.getAlignmentProtection()));
        alignField.setFilter(s -> s.isEmpty() || s.matches("\\d{1,5}"));
        children.add(alignField);
        addButton(new Button(guiLeft + 98, guiTop + 56, 90, 20,
                new StringTextComponent("Appliquer seuil"), b -> {
                    if (!alignField.getValue().isEmpty()) {
                        send(LOTRPacketBannerEdit.SET_ALIGNMENT,
                                Float.parseFloat(alignField.getValue()), "");
                    }
                }));

        nameField = new TextFieldWidget(font, guiLeft + 12, guiTop + 98, 110, 16,
                new StringTextComponent("pseudo"));
        nameField.setMaxLength(16);
        children.add(nameField);
        addButton(new Button(guiLeft + 128, guiTop + 96, 60, 20,
                new StringTextComponent("Ajouter"), b -> {
                    if (!nameField.getValue().isEmpty()) {
                        send(LOTRPacketBannerEdit.ADD_PLAYER, 0.0f, nameField.getValue());
                        nameField.setValue("");
                    }
                }));
    }

    private void send(int action, float value, String name) {
        LOTRPacketHandler.CHANNEL.sendToServer(
                new LOTRPacketBannerEdit(banner.getId(), action, value, name));
    }

    private String[] names() {
        String joined = banner.getWhitelistNames();
        return joined.isEmpty() ? new String[0] : joined.split(";");
    }

    @Override
    public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
        renderBackground(ms);
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        minecraft.getTextureManager().bind(TEXTURE);
        blit(ms, guiLeft, guiTop, 0, 0, X_SIZE, Y_SIZE, 256, 256);

        center(ms, title.getString(), guiTop + 8);
        center(ms, "Faction : " + banner.getFactionName()
                + "   Portee : " + banner.getProtectionRange(), guiTop + 24);
        font.draw(ms, "Seuil d'alignement autorise :", guiLeft + 12, guiTop + 44, 0x373737);
        font.draw(ms, "Liste blanche (clic = retirer) :", guiLeft + 12, guiTop + 84, 0x373737);
        String[] names = names();
        for (int i = 0; i < names.length; i++) {
            int x = guiLeft + 12 + (i % 2) * 92;
            int y = guiTop + 122 + (i / 2) * 12;
            boolean hover = mouseX >= x && mouseX < x + 88 && mouseY >= y && mouseY < y + 10;
            font.draw(ms, "- " + names[i], x, y, hover ? 0xAA2222 : 0x373737);
        }
        alignField.render(ms, mouseX, mouseY, partialTicks);
        nameField.render(ms, mouseX, mouseY, partialTicks);
        super.render(ms, mouseX, mouseY, partialTicks);
    }

    private void center(MatrixStack ms, String s, int y) {
        font.draw(ms, s, guiLeft + X_SIZE / 2.0f - font.width(s) / 2.0f, y, 0x373737);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        String[] names = names();
        for (int i = 0; i < names.length; i++) {
            int x = guiLeft + 12 + (i % 2) * 92;
            int y = guiTop + 122 + (i / 2) * 12;
            if (mouseX >= x && mouseX < x + 88 && mouseY >= y && mouseY < y + 10) {
                send(LOTRPacketBannerEdit.REMOVE_PLAYER, 0.0f, names[i]);
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void tick() {
        alignField.tick();
        nameField.tick();
        if (!banner.isAlive() || banner.distanceToSqr(minecraft.player) > 64.0) {
            onClose();
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
