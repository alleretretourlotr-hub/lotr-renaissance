package fr.alleretretour.lotr.network;

import fr.alleretretour.lotr.fac.LOTRFaction;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/** SERVEUR -> CLIENT : la faction de serment actuelle (ou aucune). */
public class LOTRPacketPledgeSync {

    private final LOTRFaction faction;

    public LOTRPacketPledgeSync(LOTRFaction faction) {
        this.faction = faction;
    }

    public static void encode(LOTRPacketPledgeSync msg, PacketBuffer buf) {
        buf.writeBoolean(msg.faction != null);
        if (msg.faction != null) {
            buf.writeEnum(msg.faction);
        }
    }

    public static LOTRPacketPledgeSync decode(PacketBuffer buf) {
        return new LOTRPacketPledgeSync(buf.readBoolean() ? buf.readEnum(LOTRFaction.class) : null);
    }

    public static void handle(LOTRPacketPledgeSync msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> LOTRClientAlignments.setPledge(msg.faction));
        ctx.get().setPacketHandled(true);
    }
}
