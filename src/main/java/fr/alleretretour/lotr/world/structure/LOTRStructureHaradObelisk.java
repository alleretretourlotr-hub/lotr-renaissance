package fr.alleretretour.lotr.world.structure;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.Heightmap;

import java.util.Random;

/**
 * PORT de LOTRWorldGenHaradObelisk : l'obelisque du Harad.
 *
 * Structure d'origine reproduite bloc par bloc :
 *   - ne s'implante que sur sable, terre ou herbe, et exige un terrain
 *     entierement en sable / terre / pierre / herbe sur 15x15 ;
 *   - la plateforme est comblee jusqu'au sol dur en gres ;
 *   - gradins : bordure a 7, marches a 5, 3 et 1 case du centre, en gres puis
 *     gres cisele ; briques du Harad (une sur trois en variante) aux niveaux
 *     superieurs ;
 *   - quatre colonnes d'angle montant en escalier (briques + murets) ;
 *   - fut central de 4 blocs de gres cisele, couronne par un foyer allume.
 */
public class LOTRStructureHaradObelisk extends LOTRStructureBase {

    public LOTRStructureHaradObelisk(Codec<net.minecraft.world.gen.feature.NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    protected boolean generateWithRotation(ISeedReader world, Random random, BlockPos origin,
                                           int rotation) {
        int i = origin.getX();
        int j = origin.getY();
        int k = origin.getZ();
        BlockState below = world.getBlockState(new BlockPos(i, j - 1, k));
        if (below.getBlock() != Blocks.SAND && below.getBlock() != Blocks.DIRT
                && below.getBlock() != Blocks.GRASS_BLOCK) {
            return false;
        }
        j--;
        // PORT : decalage de 8 selon la rotation
        switch (rotation) {
            case 0: k += 8; break;
            case 1: i -= 8; break;
            case 2: k -= 8; break;
            default: i += 8; break;
        }
        // terrain homogene sur 15x15
        for (int i1 = i - 7; i1 <= i + 7; i1++) {
            for (int k1 = k - 7; k1 <= k + 7; k1++) {
                int j1 = world.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, i1, k1);
                net.minecraft.block.Block b = world.getBlockState(new BlockPos(i1, j1 - 1, k1))
                        .getBlock();
                if (b != Blocks.SAND && b != Blocks.DIRT && b != Blocks.STONE
                        && b != Blocks.GRASS_BLOCK) {
                    return false;
                }
            }
        }
        BlockState sandstone = Blocks.SANDSTONE.defaultBlockState();
        BlockState chiseled = Blocks.CHISELED_SANDSTONE.defaultBlockState();

        // comblement jusqu'au sol dur
        for (int i1 = i - 7; i1 <= i + 7; i1++) {
            for (int k1 = k - 7; k1 <= k + 7; k1++) {
                for (int j1 = j; j1 >= 0; j1--) {
                    BlockPos p = new BlockPos(i1, j1, k1);
                    if (j1 != j && world.getBlockState(p).isSolidRender(world, p)) {
                        break;
                    }
                    world.setBlock(p, sandstone, 2);
                    setGrassToDirt(world, p.below());
                }
            }
        }
        // gradins
        for (int i1 = i - 7; i1 <= i + 7; i1++) {
            for (int k1 = k - 7; k1 <= k + 7; k1++) {
                int i2 = Math.abs(i1 - i);
                int k2 = Math.abs(k1 - k);
                if (i2 == 7 || k2 == 7) {
                    world.setBlock(new BlockPos(i1, j + 1, k1), sandstone, 2);
                }
                if (i2 == 5 && k2 <= 5 || k2 == 5 && i2 <= 5) {
                    world.setBlock(new BlockPos(i1, j + 1, k1), sandstone, 2);
                    world.setBlock(new BlockPos(i1, j + 2, k1), chiseled, 2);
                }
                if (i2 == 3 && k2 <= 3 || k2 == 3 && i2 <= 3) {
                    world.setBlock(new BlockPos(i1, j + 1, k1), sandstone, 2);
                    world.setBlock(new BlockPos(i1, j + 2, k1), chiseled, 2);
                    placeHaradBrick(world, random, new BlockPos(i1, j + 3, k1));
                }
                if (i2 <= 1 && k2 <= 1) {
                    world.setBlock(new BlockPos(i1, j + 1, k1), sandstone, 2);
                    world.setBlock(new BlockPos(i1, j + 2, k1), chiseled, 2);
                    placeHaradBrick(world, random, new BlockPos(i1, j + 3, k1));
                    world.setBlock(new BlockPos(i1, j + 4, k1), chiseled, 2);
                    placeHaradBrick(world, random, new BlockPos(i1, j + 5, k1));
                }
                // colonnes d'angle en escalier
                for (int l = 0; l <= 2; l++) {
                    int l1 = 8 - (l * 2 + 1);
                    if (i2 != l1 || k2 != l1) {
                        continue;
                    }
                    placeHaradBrick(world, random, new BlockPos(i1, j + l + 2, k1));
                    placeHaradWall(world, random, new BlockPos(i1, j + l + 3, k1));
                    placeHaradWall(world, random, new BlockPos(i1, j + l + 4, k1));
                }
            }
        }
        placeHaradBrick(world, random, new BlockPos(i - 1, j + 6, k));
        placeHaradBrick(world, random, new BlockPos(i + 1, j + 6, k));
        placeHaradBrick(world, random, new BlockPos(i, j + 6, k - 1));
        placeHaradBrick(world, random, new BlockPos(i, j + 6, k + 1));
        for (int j1 = j + 6; j1 <= j + 9; j1++) {
            world.setBlock(new BlockPos(i, j1, k), chiseled, 2);
        }
        world.setBlock(new BlockPos(i - 1, j + 10, k), chiseled, 2);
        world.setBlock(new BlockPos(i + 1, j + 10, k), chiseled, 2);
        world.setBlock(new BlockPos(i, j + 10, k - 1), chiseled, 2);
        world.setBlock(new BlockPos(i, j + 10, k + 1), chiseled, 2);
        world.setBlock(new BlockPos(i, j + 10, k),
                modBlock("lotr:hearth", Blocks.NETHERRACK), 2);
        world.setBlock(new BlockPos(i, j + 11, k), Blocks.FIRE.defaultBlockState(), 2);
        return true;
    }

    /** PORT : une brique sur trois est de la variante rouge du Harad. */
    private void placeHaradBrick(ISeedReader world, Random random, BlockPos pos) {
        world.setBlock(pos, random.nextInt(3) == 0
                ? modBlock("lotr:near_harad_cracked_brick", Blocks.RED_SANDSTONE)
                : modBlock("lotr:near_harad_brick", Blocks.SMOOTH_SANDSTONE), 2);
    }

    private void placeHaradWall(ISeedReader world, Random random, BlockPos pos) {
        world.setBlock(pos, random.nextInt(3) == 0
                ? modBlock("lotr:near_harad_cracked_brick_wall", Blocks.RED_SANDSTONE_WALL)
                : modBlock("lotr:near_harad_brick_wall", Blocks.SANDSTONE_WALL), 2);
    }
}
