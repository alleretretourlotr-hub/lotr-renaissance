package fr.alleretretour.lotr.init;

import fr.alleretretour.lotr.LOTRMod;
import fr.alleretretour.lotr.tileentity.LOTRTileEntityBarrel;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/** Registre differe des TileEntities. */
public class LOTRTileEntities {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES =
            DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, LOTRMod.MOD_ID);

    @SuppressWarnings("ConstantConditions")
    public static final RegistryObject<TileEntityType<LOTRTileEntityBarrel>> BARREL =
            TILE_ENTITIES.register("barrel", () -> TileEntityType.Builder.of(
                    LOTRTileEntityBarrel::new, LOTRBlocksBarrel.BARREL.get()).build(null));
}
