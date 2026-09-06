package fr.alleretretour.lotr.world.structure;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

/**
 * PORT de LOTRWorldGenDunlendingHouse : la maison du Dun.
 *
 * Le trace vient du scan "dunland_house". Les Dunlendings ne batissent pas
 * deux maisons identiques : le Legacy tire les materiaux a chaque
 * construction (setupRandomBlocks de LOTRWorldGenDunlandStructure), ce que
 * reproduisent ici les alias ponderes :
 *
 *   SOL   : pierre taillee, terre cuite, ou terre cuite teintee (gris,
 *           marron ou noir) - une chance sur cinq chacun ;
 *   BOIS  : une fois sur deux de l'epicea, sinon chene ou pin a parts egales ;
 *   TOIT  : toujours du CHAUME, le bloc caracteristique du Dun ;
 *   BARREAUX : fer ou bronze.
 */
public class LOTRStructureDunlendingHouse extends LOTRStructureScanned {

    public LOTRStructureDunlendingHouse(Codec<NoFeatureConfig> codec) {
        this(codec, "dunland_house");
    }

    protected LOTRStructureDunlendingHouse(Codec<NoFeatureConfig> codec, String scan) {
        super(codec, scan);

        // sol : cinq possibilites equiprobables (PORT)
        aliasOption("FLOOR", 1, Blocks.COBBLESTONE.defaultBlockState());
        aliasOption("FLOOR", 1, Blocks.TERRACOTTA.defaultBlockState());
        aliasOption("FLOOR", 1, Blocks.GRAY_TERRACOTTA.defaultBlockState());
        aliasOption("FLOOR", 1, Blocks.BROWN_TERRACOTTA.defaultBlockState());
        aliasOption("FLOOR", 1, Blocks.BLACK_TERRACOTTA.defaultBlockState());

        // bois : epicea une fois sur deux, sinon chene ou pin
        aliasOption("WOOD", 4, Blocks.SPRUCE_LOG.defaultBlockState());
        aliasOption("WOOD", 2, Blocks.OAK_LOG.defaultBlockState());
        aliasOption("WOOD", 2, Blocks.SPRUCE_LOG.defaultBlockState());
        aliasOption("WOOD|8", 4, Blocks.SPRUCE_WOOD.defaultBlockState());
        aliasOption("WOOD|8", 2, Blocks.OAK_WOOD.defaultBlockState());
        aliasOption("WOOD|8", 2, Blocks.SPRUCE_WOOD.defaultBlockState());

        aliasOption("PLANK", 4, Blocks.SPRUCE_PLANKS.defaultBlockState());
        aliasOption("PLANK", 2, Blocks.OAK_PLANKS.defaultBlockState());
        aliasOption("PLANK", 2, "lotr:pine_planks", Blocks.SPRUCE_PLANKS);
        aliasOption("PLANK_SLAB", 4, Blocks.SPRUCE_SLAB.defaultBlockState());
        aliasOption("PLANK_SLAB", 2, Blocks.OAK_SLAB.defaultBlockState());
        aliasOption("PLANK_SLAB", 2, "lotr:pine_slab", Blocks.SPRUCE_SLAB);
        aliasOption("PLANK_SLAB_INV", 4, Blocks.SPRUCE_SLAB.defaultBlockState()
                .setValue(net.minecraft.block.SlabBlock.TYPE,
                        net.minecraft.state.properties.SlabType.TOP));
        aliasOption("PLANK_SLAB_INV", 4, Blocks.OAK_SLAB.defaultBlockState()
                .setValue(net.minecraft.block.SlabBlock.TYPE,
                        net.minecraft.state.properties.SlabType.TOP));
        aliasOption("PLANK_STAIR", 4, Blocks.SPRUCE_STAIRS.defaultBlockState());
        aliasOption("PLANK_STAIR", 2, Blocks.OAK_STAIRS.defaultBlockState());
        aliasOption("PLANK_STAIR", 2, "lotr:pine_stairs", Blocks.SPRUCE_STAIRS);
        aliasOption("FENCE", 4, Blocks.SPRUCE_FENCE.defaultBlockState());
        aliasOption("FENCE", 2, Blocks.OAK_FENCE.defaultBlockState());
        aliasOption("FENCE", 2, "lotr:pine_fence", Blocks.SPRUCE_FENCE);
        // TODO : les portes du mod ne sont pas encore portees
        alias("DOOR", Blocks.SPRUCE_DOOR.defaultBlockState());

        // toit de chaume, la signature du Dun
        alias("ROOF", "lotr:thatch", Blocks.HAY_BLOCK);
        // TODO : la dalle et l'escalier de chaume ne sont pas portes ;
        // le bloc plein tient lieu de toiture en attendant.
        alias("ROOF_SLAB", "lotr:thatch", Blocks.HAY_BLOCK);
        alias("ROOF_SLAB_INV", "lotr:thatch", Blocks.HAY_BLOCK);
        alias("ROOF_STAIR", "lotr:thatch", Blocks.HAY_BLOCK);

        aliasOption("BARS", 1, Blocks.IRON_BARS.defaultBlockState());
        aliasOption("BARS", 1, "lotr:bronze_bars", Blocks.IRON_BARS);
    }

    @Override
    protected boolean canPlace(ISeedReader world, Random random, BlockPos base, int rotation) {
        // terrain plat et naturel sur l'emprise de la maison
        int min = 0;
        int max = 0;
        for (int i = -5; i <= 5; i++) {
            for (int k = -5; k <= 5; k++) {
                int y = groundAt(world, base, rotation, i, k) - 1 - base.getY();
                if (!isSurfaceLocal(world, base, rotation, i, y, k)) {
                    return false;
                }
                min = Math.min(min, y);
                max = Math.max(max, y);
                if (max - min > 4) {
                    return false;
                }
            }
        }
        return true;
    }
}
