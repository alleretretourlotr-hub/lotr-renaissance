package fr.alleretretour.lotr.network;

import fr.alleretretour.lotr.fac.LOTRFaction;
import fr.alleretretour.lotr.fac.LOTRPlayerDataProvider;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/** CLIENT -> SERVEUR : choix du titre affiche dans le chat (valide serveur). */
public class LOTRPacketSelectTitle {

    private final LOTRFaction faction;
    private final String rankName;

    public LOTRPacketSelectTitle(LOTRFaction faction, String rankName) {
        this.faction = faction;
        this.rankName = rankName;
    }

    public static void encode(LOTRPacketSelectTitle msg, PacketBuffer buf) {
        buf.writeBoolean(msg.faction != null);
        if (msg.faction != null) {
            buf.writeEnum(msg.faction);
            buf.writeUtf(msg.rankName, 64);
        }
    }

    public static LOTRPacketSelectTitle decode(PacketBuffer buf) {
        if (buf.readBoolean()) {
            return new LOTRPacketSelectTitle(buf.readEnum(LOTRFaction.class), buf.readUtf(64));
        }
        return new LOTRPacketSelectTitle(null, "");
    }

    public static void handle(LOTRPacketSelectTitle msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity player = ctx.get().getSender();
            if (player != null) {
                LOTRPlayerDataProvider.get(player).setTitle(msg.faction, msg.rankName);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
