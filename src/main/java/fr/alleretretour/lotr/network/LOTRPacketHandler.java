package fr.alleretretour.lotr.network;

import fr.alleretretour.lotr.LOTRMod;
import fr.alleretretour.lotr.fac.LOTRFaction;
import fr.alleretretour.lotr.fac.LOTRPlayerData;
import fr.alleretretour.lotr.fac.LOTRPlayerDataProvider;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;

/**
 * Canal reseau du mod (SimpleChannel). CLIENT UNIQUEMENT pour la reception :
 * pas de classes client referencees ici, compatible Mohist.
 */
public class LOTRPacketHandler {

    private static final String PROTOCOL = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(LOTRMod.MOD_ID, "main"),
            () -> PROTOCOL, PROTOCOL::equals, PROTOCOL::equals);

    private static int id = 0;

    /** A appeler dans FMLCommonSetupEvent. */
    public static void register() {
        CHANNEL.registerMessage(id++, LOTRPacketAlignment.class,
                LOTRPacketAlignment::encode, LOTRPacketAlignment::decode,
                LOTRPacketAlignment::handle);
        CHANNEL.registerMessage(id++, LOTRPacketPledge.class,
                LOTRPacketPledge::encode, LOTRPacketPledge::decode,
                LOTRPacketPledge::handle);
        CHANNEL.registerMessage(id++, LOTRPacketPledgeSync.class,
                LOTRPacketPledgeSync::encode, LOTRPacketPledgeSync::decode,
                LOTRPacketPledgeSync::handle);
        CHANNEL.registerMessage(id++, LOTRPacketSelectTitle.class,
                LOTRPacketSelectTitle::encode, LOTRPacketSelectTitle::decode,
                LOTRPacketSelectTitle::handle);
        CHANNEL.registerMessage(id++, LOTRPacketHiredCommand.class,
                LOTRPacketHiredCommand::encode, LOTRPacketHiredCommand::decode,
                LOTRPacketHiredCommand::handle);
        CHANNEL.registerMessage(id++, LOTRPacketBannerEdit.class,
                LOTRPacketBannerEdit::encode, LOTRPacketBannerEdit::decode,
                LOTRPacketBannerEdit::handle);
    }

    /** Envoie tous les alignements non nuls du joueur a son client. */
    public static void syncAlignments(ServerPlayerEntity player) {
        checkPledge(player);
        LOTRPlayerData data = LOTRPlayerDataProvider.get(player);
        for (LOTRFaction f : LOTRFaction.values()) {
            float v = data.getAlignment(f);
            if (v != 0.0f) {
                CHANNEL.send(PacketDistributor.PLAYER.with(() -> player),
                        new LOTRPacketAlignment(f, v));
            }
        }
    }

    public static void syncPledge(ServerPlayerEntity player) {
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> player),
                new LOTRPacketPledgeSync(
                        LOTRPlayerDataProvider.get(player).getPledgeFaction()));
    }

    /** Verifie la rupture de serment (alignement retombe) et notifie. */
    public static void checkPledge(ServerPlayerEntity player) {
        LOTRPlayerData data = LOTRPlayerDataProvider.get(player);
        if (data.checkPledgeBroken()) {
            player.displayClientMessage(new net.minecraft.util.text.TranslationTextComponent(
                    "lotr.pledge.autobroken"), false);
            syncPledge(player);
        }
    }

    public static void syncAlignment(ServerPlayerEntity player, LOTRFaction faction) {
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> player),
                new LOTRPacketAlignment(faction,
                        LOTRPlayerDataProvider.get(player).getAlignment(faction)));
    }
}
