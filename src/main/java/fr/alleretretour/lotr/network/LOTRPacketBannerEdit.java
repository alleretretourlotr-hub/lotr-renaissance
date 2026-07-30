package fr.alleretretour.lotr.network;

import fr.alleretretour.lotr.entity.item.LOTREntityBannerPlaced;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/** C2S : edition d'une banniere par son poseur. 0=seuil, 1=ajout joueur, 2=retrait. */
public class LOTRPacketBannerEdit {

    public static final int SET_ALIGNMENT = 0;
    public static final int ADD_PLAYER = 1;
    public static final int REMOVE_PLAYER = 2;

    private final int entityId;
    private final int action;
    private final float value;
    private final String name;

    public LOTRPacketBannerEdit(int entityId, int action, float value, String name) {
        this.entityId = entityId;
        this.action = action;
        this.value = value;
        this.name = name;
    }

    public static void encode(LOTRPacketBannerEdit msg, PacketBuffer buf) {
        buf.writeVarInt(msg.entityId);
        buf.writeVarInt(msg.action);
        buf.writeFloat(msg.value);
        buf.writeUtf(msg.name, 16);
    }

    public static LOTRPacketBannerEdit decode(PacketBuffer buf) {
        return new LOTRPacketBannerEdit(buf.readVarInt(), buf.readVarInt(),
                buf.readFloat(), buf.readUtf(16));
    }

    public static void handle(LOTRPacketBannerEdit msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity player = ctx.get().getSender();
            if (player == null) {
                return;
            }
            Entity entity = player.level.getEntity(msg.entityId);
            if (!(entity instanceof LOTREntityBannerPlaced)) {
                return;
            }
            LOTREntityBannerPlaced banner = (LOTREntityBannerPlaced) entity;
            if (!banner.isPlacer(player) || banner.distanceToSqr(player) > 64.0) {
                return;
            }
            switch (msg.action) {
                case SET_ALIGNMENT:
                    banner.setAlignmentProtection(msg.value);
                    break;
                case ADD_PLAYER:
                    banner.addToWhitelistByName(player.getServer(), msg.name);
                    break;
                case REMOVE_PLAYER:
                    banner.removeFromWhitelistByName(msg.name);
                    break;
                default:
                    break;
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
