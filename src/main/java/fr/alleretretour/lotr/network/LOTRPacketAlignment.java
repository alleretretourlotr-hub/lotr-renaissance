package fr.alleretretour.lotr.network;

import fr.alleretretour.lotr.fac.LOTRFaction;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Serveur -> client : la valeur d'alignement d'une faction.
 * Cote client, stocke dans LOTRClientAlignments (classe sans dependance monde,
 * lisible par le futur GUI et l'overlay).
 */
public class LOTRPacketAlignment {

    private final LOTRFaction faction;
    private final float value;

    public LOTRPacketAlignment(LOTRFaction faction, float value) {
        this.faction = faction;
        this.value = value;
    }

    public static void encode(LOTRPacketAlignment msg, PacketBuffer buf) {
        buf.writeEnum(msg.faction);
        buf.writeFloat(msg.value);
    }

    public static LOTRPacketAlignment decode(PacketBuffer buf) {
        return new LOTRPacketAlignment(buf.readEnum(LOTRFaction.class), buf.readFloat());
    }

    public static void handle(LOTRPacketAlignment msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            float before = LOTRClientAlignments.get(msg.faction);
            LOTRClientAlignments.set(msg.faction, msg.value);
            if (msg.value != before && net.minecraftforge.fml.loading.FMLEnvironment.dist
                    == net.minecraftforge.api.distmarker.Dist.CLIENT) {
                fr.alleretretour.lotr.client.LOTRHudOverlay.triggerFlash();
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
