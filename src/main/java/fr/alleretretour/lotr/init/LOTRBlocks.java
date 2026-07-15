package fr.alleretretour.lotr.init;

import fr.alleretretour.lotr.LOTRMod;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Registre differe des blocs.
 * Phase 2 : les ~311 blocs du mod original seront declares ici.
 */
public class LOTRBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, LOTRMod.MOD_ID);

    // Exemple de declaration (Phase 2) :
    // public static final RegistryObject<Block> MALLORN_PLANKS = BLOCKS.register("mallorn_planks",
    //         () -> new Block(AbstractBlock.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
}
