package fr.alleretretour.lotr.world.structure;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

/**
 * PORT de LOTRWorldGenHarnedorTower : la tour du Harnennor.
 *
 * Le trace vient du scan "harnedor_tower". Comme toutes les batisses du
 * Harnennor, elle est construite en CEDRE (PORT de setupRandomBlocks), avec
 * un toit de laine dont la couleur est tiree au sort entre trois teintes.
 *
 * Terrain : 7x7 en surface naturelle, denivele maximal de 8 - la tour peut
 * donc s'implanter a flanc de colline. L'espace au-dessus (niveaux 6 a 16)
 * est degage avant la pose.
 *
 * Les cranes fiches sur les murs, le panier a butin et les guerriers en
 * garnison attendent le portage des TileEntity et du respawner : la tour est
 * batie, sa garnison viendra.
 */
public class LOTRStructureHarnedorTower extends LOTRStructureScanned {

    public LOTRStructureHarnedorTower(Codec<NoFeatureConfig> codec) {
        super(codec, "harnedor_tower");

        alias("PLANK", "lotr:cedar_planks", Blocks.OAK_PLANKS);
        alias("PLANK_SLAB", "lotr:cedar_slab", Blocks.OAK_SLAB);
        alias("PLANK_STAIR", "lotr:cedar_stairs", Blocks.OAK_STAIRS);
        alias("FENCE", "lotr:cedar_fence", Blocks.OAK_FENCE);
        alias("TRAPDOOR", "lotr:cedar_trapdoor", Blocks.OAK_TRAPDOOR);
        alias("WOOD", "lotr:cedar_log", Blocks.OAK_LOG);
        // TODO : les portes du mod ne sont pas encore portees (seules les
        // trappes le sont) ; porte de chene en attendant.
        alias("DOOR", Blocks.OAK_DOOR.defaultBlockState());
        alias("FENCE_GATE", "lotr:cedar_fence_gate", Blocks.OAK_FENCE_GATE);
        // PORT : le toit est tire parmi trois laines
        aliasOption("ROOF", 1, Blocks.ORANGE_WOOL.defaultBlockState());
        aliasOption("ROOF", 1, Blocks.YELLOW_WOOL.defaultBlockState());
        aliasOption("ROOF", 1, Blocks.RED_WOOL.defaultBlockState());
    }

    @Override
    protected boolean canPlace(ISeedReader world, Random random, BlockPos base, int rotation) {
        int min = 0;
        int max = 0;
        for (int i = -3; i <= 3; i++) {
            for (int k = -3; k <= 3; k++) {
                int y = groundAt(world, base, rotation, i, k) - 1 - base.getY();
                if (!isSurfaceLocal(world, base, rotation, i, y, k)) {
                    return false;
                }
                min = Math.min(min, y);
                max = Math.max(max, y);
                if (max - min > 8) {
                    return false;   // PORT : denivele maximal de 8
                }
            }
        }
        return true;
    }

    @Override
    protected boolean generateWithRotation(ISeedReader world, Random random, BlockPos origin,
                                           int rotation) {
        // PORT : degagement de l'espace avant la pose
        BlockState air = Blocks.AIR.defaultBlockState();
        for (int i = -3; i <= 3; i++) {
            for (int k = -3; k <= 3; k++) {
                for (int j = 6; j <= 16; j++) {
                    setBlockRotated(world, origin, rotation, i, j, k, air);
                }
            }
        }
        return super.generateWithRotation(world, random, origin, rotation);
    }
}
