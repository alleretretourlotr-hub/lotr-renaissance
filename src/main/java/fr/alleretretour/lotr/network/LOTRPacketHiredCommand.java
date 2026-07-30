package fr.alleretretour.lotr.network;

import fr.alleretretour.lotr.entity.npc.LOTREntityNPC;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * C2S : commande d'une unite engagee depuis l'interface de gestion.
 * Actions : 0 = parler, 1 = suivre/halte, 2 = congedier.
 */
public class LOTRPacketHiredCommand {

    public static final int ACTION_TALK = 0;
    public static final int ACTION_TOGGLE_HALT = 1;
    public static final int ACTION_DISMISS = 2;

    private final int entityId;
    private final int action;

    public LOTRPacketHiredCommand(int entityId, int action) {
        this.entityId = entityId;
        this.action = action;
    }

    public static void encode(LOTRPacketHiredCommand msg, PacketBuffer buf) {
        buf.writeVarInt(msg.entityId);
        buf.writeVarInt(msg.action);
    }

    public static LOTRPacketHiredCommand decode(PacketBuffer buf) {
        return new LOTRPacketHiredCommand(buf.readVarInt(), buf.readVarInt());
    }

    public static void handle(LOTRPacketHiredCommand msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity player = ctx.get().getSender();
            if (player == null) {
                return;
            }
            Entity entity = player.level.getEntity(msg.entityId);
            if (!(entity instanceof LOTREntityNPC)) {
                return;
            }
            LOTREntityNPC npc = (LOTREntityNPC) entity;
            if (!npc.isHiredBy(player) || npc.distanceToSqr(player) > 64.0) {
                return;
            }
            switch (msg.action) {
                case ACTION_TALK:
                    npc.speakHiredTo(player);
                    break;
                case ACTION_TOGGLE_HALT:
                    npc.setHalted(!npc.isHalted());
                    player.displayClientMessage(
                            new net.minecraft.util.text.StringTextComponent(npc.isHalted()
                                    ? npc.getName().getString() + " tient la position."
                                    : npc.getName().getString() + " vous suit."), true);
                    break;
                case ACTION_DISMISS:
                    npc.dismiss();
                    player.displayClientMessage(
                            new net.minecraft.util.text.StringTextComponent(
                                    npc.getName().getString() + " a ete congedie."), true);
                    break;
                default:
                    break;
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
