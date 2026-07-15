package fr.alleretretour.lotr.fac;

import fr.alleretretour.lotr.LOTRMod;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Capability Forge portant LOTRPlayerData sur chaque joueur.
 * Gere : attachement, persistance NBT, copie a la mort, resync aux (re)connexions.
 */
@Mod.EventBusSubscriber(modid = LOTRMod.MOD_ID)
public class LOTRPlayerDataProvider implements ICapabilitySerializable<CompoundNBT> {

    @CapabilityInject(LOTRPlayerData.class)
    public static Capability<LOTRPlayerData> CAPABILITY = null;

    public static final ResourceLocation KEY = new ResourceLocation(LOTRMod.MOD_ID, "player_data");

    private final LOTRPlayerData data = new LOTRPlayerData();
    private final LazyOptional<LOTRPlayerData> optional = LazyOptional.of(() -> data);

    /** A appeler une fois dans FMLCommonSetupEvent. */
    public static void register() {
        CapabilityManager.INSTANCE.register(LOTRPlayerData.class,
                new Capability.IStorage<LOTRPlayerData>() {
                    @Override
                    public net.minecraft.nbt.INBT writeNBT(Capability<LOTRPlayerData> cap,
                                                           LOTRPlayerData instance, Direction side) {
                        return instance.save();
                    }

                    @Override
                    public void readNBT(Capability<LOTRPlayerData> cap, LOTRPlayerData instance,
                                        Direction side, net.minecraft.nbt.INBT nbt) {
                        instance.load((CompoundNBT) nbt);
                    }
                }, LOTRPlayerData::new);
    }

    private static final LOTRPlayerData EMPTY = new LOTRPlayerData();

    /**
     * Recupere les donnees du joueur. Si la capability n'est pas encore attachee
     * (ex. au chargement du monde, avant que les events aient tourne), retourne
     * une instance vide plutot que de crasher - les PNJ qui evaluent leur cible
     * pendant ce laps de temps ne font ainsi pas planter le jeu.
     */
    public static LOTRPlayerData get(PlayerEntity player) {
        return player.getCapability(CAPABILITY).orElse(EMPTY);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == CAPABILITY ? optional.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundNBT serializeNBT() {
        return data.save();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        data.load(nbt);
    }

    // --- evenements ---

    @SubscribeEvent
    public static void onAttach(AttachCapabilitiesEvent<net.minecraft.entity.Entity> event) {
        if (event.getObject() instanceof PlayerEntity) {
            event.addCapability(KEY, new LOTRPlayerDataProvider());
        }
    }

    @SubscribeEvent
    public static void onClone(PlayerEvent.Clone event) {
        // survit a la mort : revive() (public) ranime les capabilities de
        // l'ancien joueur (reviveCaps est protected en Forge 36), puis copie
        event.getOriginal().revive();
        LOTRPlayerData oldData = get(event.getOriginal());
        get(event.getPlayer()).copyFrom(oldData);
    }

    @SubscribeEvent
    public static void onLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getPlayer() instanceof ServerPlayerEntity) {
            fr.alleretretour.lotr.network.LOTRPacketHandler.syncAlignments(
                    (ServerPlayerEntity) event.getPlayer());
        }
    }

    @SubscribeEvent
    public static void onRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (event.getPlayer() instanceof ServerPlayerEntity) {
            fr.alleretretour.lotr.network.LOTRPacketHandler.syncAlignments(
                    (ServerPlayerEntity) event.getPlayer());
        }
    }
}
