package fr.alleretretour.lotr.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import fr.alleretretour.lotr.entity.npc.LOTREntityNPC;
import fr.alleretretour.lotr.network.LOTRPacketHandler;
import fr.alleretretour.lotr.network.LOTRPacketHiredCommand;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

/**
 * PORT de LOTRGuiHiredNPC/LOTRGuiHiredInteract : interface de gestion d'une
 * unite engagee. Texture officielle hired.png (200x220), nom + type + PV,
 * boutons Parler / Suivre-Halte / Congedier. Se ferme si l'unite meurt,
 * s'eloigne, ou n'est plus a nous.
 */
public class LOTRScreenHiredUnit extends Screen {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation("lotr", "textures/gui/npc/hired.png");
    private static final int X_SIZE = 200;
    private static final int Y_SIZE = 220;

    private final LOTREntityNPC npc;
    private int guiLeft;
    private int guiTop;
    private Button haltButton;

    public LOTRScreenHiredUnit(LOTREntityNPC npc) {
        super(npc.getName());
        this.npc = npc;
    }

    @Override
    protected void init() {
        guiLeft = (width - X_SIZE) / 2;
        guiTop = (height - Y_SIZE) / 2;
        addButton(new Button(guiLeft + 10, guiTop + 130, 85, 20,
                new StringTextComponent("Parler"), b -> send(LOTRPacketHiredCommand.ACTION_TALK)));
        haltButton = addButton(new Button(guiLeft + 105, guiTop + 130, 85, 20,
                haltLabel(), b -> {
                    send(LOTRPacketHiredCommand.ACTION_TOGGLE_HALT);
                }));
        addButton(new Button(guiLeft + 10, guiTop + 155, 180, 20,
                new StringTextComponent("\u00a7cCongedier l'unite"), b -> {
                    send(LOTRPacketHiredCommand.ACTION_DISMISS);
                    onClose();
                }));
    }

    private StringTextComponent haltLabel() {
        return new StringTextComponent(npc.isHalted() ? "Suivre" : "Halte");
    }

    private void send(int action) {
        LOTRPacketHandler.CHANNEL.sendToServer(
                new LOTRPacketHiredCommand(npc.getId(), action));
    }

    @Override
    public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
        renderBackground(ms);
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        minecraft.getTextureManager().bind(TEXTURE);
        blit(ms, guiLeft, guiTop, 0, 0, X_SIZE, Y_SIZE, 256, 256);

        drawCenteredNoShadow(ms, npc.getName().getString(), guiTop + 11);
        drawCenteredNoShadow(ms, npc.getType().getDescription().getString(), guiTop + 26);
        drawCenteredNoShadow(ms, String.format("Points de vie : %.0f / %.0f",
                npc.getHealth(), npc.getMaxHealth()), guiTop + 44);
        drawCenteredNoShadow(ms, npc.isHalted()
                ? "Ordre actuel : tenir la position"
                : "Ordre actuel : vous suivre", guiTop + 58);

        haltButton.setMessage(haltLabel());
        super.render(ms, mouseX, mouseY, partialTicks);
    }

    private void drawCenteredNoShadow(MatrixStack ms, String s, int y) {
        font.draw(ms, s, guiLeft + X_SIZE / 2.0f - font.width(s) / 2.0f, y, 0x373737);
    }

    @Override
    public void tick() {
        super.tick();
        if (!npc.isAlive() || !npc.isHiredByClient(minecraft.player)
                || npc.distanceToSqr(minecraft.player) > 64.0) {
            onClose();
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
