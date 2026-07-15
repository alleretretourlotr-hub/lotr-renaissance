package fr.alleretretour.lotr.init;

import fr.alleretretour.lotr.LOTRMod;
import fr.alleretretour.lotr.inventory.LOTRContainerFactionCrafting;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Registre differe des containers.
 */
public class LOTRContainers {

    public static final DeferredRegister<ContainerType<?>> CONTAINERS =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, LOTRMod.MOD_ID);

    public static final RegistryObject<ContainerType<LOTRContainerFactionCrafting>> FACTION_CRAFTING =
            CONTAINERS.register("faction_crafting",
                    () -> new ContainerType<>((id, inv) -> new LOTRContainerFactionCrafting(id, inv)));

    public static final RegistryObject<ContainerType<fr.alleretretour.lotr.inventory.LOTRContainerBarrel>> BARREL =
            CONTAINERS.register("barrel",
                    () -> new ContainerType<>((id, inv) -> new fr.alleretretour.lotr.inventory.LOTRContainerBarrel(id, inv)));

    public static final RegistryObject<ContainerType<fr.alleretretour.lotr.inventory.LOTRContainerTrade>> TRADE =
            CONTAINERS.register("trade",
                    () -> new ContainerType<>((id, inv) -> new fr.alleretretour.lotr.inventory.LOTRContainerTrade(id, inv)));
}
