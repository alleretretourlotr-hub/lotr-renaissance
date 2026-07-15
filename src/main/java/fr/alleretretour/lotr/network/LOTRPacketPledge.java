package fr.alleretretour.lotr.network;

import fr.alleretretour.lotr.fac.LOTRFaction;
import fr.alleretretour.lotr.fac.LOTRPlayerData;
import fr.alleretretour.lotr.fac.LOTRPlayerDataProvider;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * CLIENT -> SERVEUR : demande de serment (ou de rupture si deja engage).
 * Le serveur valide (rang requis atteint), applique, et resynchronise.
 */
public class LOTRPacketPledge {

    private final LOTRFaction faction;

    public LOTRPacketPledge(LOTRFaction faction) {
        this.faction = faction;
    }

    public static void encode(LOTRPacketPledge msg, PacketBuffer buf) {
        buf.writeEnum(msg.faction);
    }

    public static LOTRPacketPledge decode(PacketBuffer buf) {
        return new LOTRPacketPledge(buf.readEnum(LOTRFaction.class));
    }

    public static void handle(LOTRPacketPledge msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity player = ctx.get().getSender();
            if (player == null) {
                return;
            }
            LOTRPlayerData data = LOTRPlayerDataProvider.get(player);
            if (data.isPledgedTo(msg.faction)) {
                // rupture volontaire
                data.setPledgeFaction(null);
                player.displayClientMessage(new TranslationTextComponent(
                        "lotr.pledge.broken", new TranslationTextComponent(
                                msg.faction.getTranslationKey())), false);
            } else if (data.getPledgeFaction() == null && data.canPledgeTo(msg.faction)) {
                data.setPledgeFaction(msg.faction);
                player.displayClientMessage(new TranslationTextComponent(
                        "lotr.pledge.made", new TranslationTextComponent(
                                msg.faction.getTranslationKey())), false);
            }
            LOTRPacketHandler.syncPledge(player);
        });
        ctx.get().setPacketHandled(true);
    }
}
