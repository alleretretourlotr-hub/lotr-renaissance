package fr.alleretretour.lotr.world.structure;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

/**
 * PORT de LOTRWorldGenAngmarShrine : le sanctuaire d'Angmar.
 *
 * Structure d'origine reproduite telle quelle :
 *   - s'implante sur l'herbe ; le terrain 7x7 doit etre en herbe, terre ou
 *     pierre, avec un denivele maximal de 3 ;
 *   - fondation comblee jusqu'au sol dur en briques d'Angmar, dont UNE SUR
 *     QUATRE est fissuree (placeRandomBrick) ;
 *   - pyramide a trois degres (7x7, 5x5, 3x3), chaque degre borde de quatre
 *     escaliers orientes vers l'exterieur (fissures une fois sur quatre) ;
 *   - au sommet, la table d'Angmar ; quatre torches de Morgul aux angles du
 *     dernier degre ;
 *   - autour, 4 a 8 piliers de 2 a 4 blocs, poses a 4-7 cases du centre,
 *     couronnes d'une brique de gulduril.
 */
public class LOTRStructureAngmarShrine extends LOTRStructureBase {

    public LOTRStructureAngmarShrine(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    protected boolean generateWithRotation(ISeedReader world, Random random, BlockPos origin,
                                           int rotation) {
        int i = origin.getX();
        int j = origin.getY();
        int k = origin.getZ();
        if (world.getBlockState(new BlockPos(i, j - 1, k)).getBlock() != Blocks.GRASS_BLOCK) {
            return false;
        }
        j--;
        switch (rotation) {
            case 0: k += 4; break;
            case 1: i -= 4; break;
            case 2: k -= 4; break;
            default: i += 4; break;
        }
        // terrain : herbe / terre / pierre, denivele max 3
        int minHeight = j;
        int maxHeight = j;
        for (int i1 = i - 3; i1 <= i + 3; i1++) {
            for (int k1 = k - 3; k1 <= k + 3; k1++) {
                int j1 = world.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, i1, k1) - 1;
                Block b = world.getBlockState(new BlockPos(i1, j1, k1)).getBlock();
                if (b != Blocks.GRASS_BLOCK && b != Blocks.DIRT && b != Blocks.STONE) {
                    return false;
                }
                minHeight = Math.min(minHeight, j1);
                maxHeight = Math.max(maxHeight, j1);
            }
        }
        if (maxHeight - minHeight > 3) {
            return false;
        }
        // fondation
        for (int i1 = i - 3; i1 <= i + 3; i1++) {
            for (int k1 = k - 3; k1 <= k + 3; k1++) {
                for (int j1 = j; j1 >= 0; j1--) {
                    BlockPos p = new BlockPos(i1, j1, k1);
                    if (world.getBlockState(p).isSolidRender(world, p) && j1 != j) {
                        break;
                    }
                    placeRandomBrick(world, random, p);
                    setGrassToDirt(world, p.below());
                }
            }
        }
        // pyramide a trois degres
        for (int l = 0; l <= 2; l++) {
            for (int i1 = i - 3 + l; i1 <= i + 3 - l; i1++) {
                for (int k1 = k - 3 + l; k1 <= k + 3 - l; k1++) {
                    placeRandomBrick(world, random, new BlockPos(i1, j + 1 + l, k1));
                }
            }
            placeRandomStairs(world, random, new BlockPos(i - 3 + l, j + 1 + l, k), Direction.EAST);
            placeRandomStairs(world, random, new BlockPos(i + 3 - l, j + 1 + l, k), Direction.WEST);
            placeRandomStairs(world, random, new BlockPos(i, j + 1 + l, k - 3 + l), Direction.SOUTH);
            placeRandomStairs(world, random, new BlockPos(i, j + 1 + l, k + 3 - l), Direction.NORTH);
        }
        world.setBlock(new BlockPos(i, j + 4, k),
                modBlock("lotr:angmar_table", Blocks.CRAFTING_TABLE), 2);
        BlockState torch = modBlock("lotr:morgul_torch", Blocks.TORCH);
        world.setBlock(new BlockPos(i - 2, j + 3, k - 2), torch, 2);
        world.setBlock(new BlockPos(i - 2, j + 3, k + 2), torch, 2);
        world.setBlock(new BlockPos(i + 2, j + 3, k - 2), torch, 2);
        world.setBlock(new BlockPos(i + 2, j + 3, k + 2), torch, 2);

        // piliers alentour
        int pillars = 4 + random.nextInt(5);
        for (int p = 0; p < pillars; p++) {
            int i1 = 4 + random.nextInt(4);
            int k1 = 4 + random.nextInt(4);
            if (random.nextBoolean()) {
                i1 *= -1;
            }
            if (random.nextBoolean()) {
                k1 *= -1;
            }
            int height = 2 + random.nextInt(3);
            i1 += i;
            k1 += k;
            int j1 = world.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, i1, k1) - 1;
            Block b = world.getBlockState(new BlockPos(i1, j1, k1)).getBlock();
            if (b != Blocks.GRASS_BLOCK && b != Blocks.DIRT && b != Blocks.STONE) {
                continue;
            }
            setGrassToDirt(world, new BlockPos(i1, j1, k1));
            for (int j2 = j1; j2 < j1 + height; j2++) {
                placeRandomBrick(world, random, new BlockPos(i1, j2, k1));
            }
            world.setBlock(new BlockPos(i1, j1 + height, k1),
                    modBlock("lotr:gulduril_brick", Blocks.PRISMARINE_BRICKS), 2);
        }
        return true;
    }

    /** PORT : une brique sur quatre est fissuree. */
    private void placeRandomBrick(ISeedReader world, Random random, BlockPos pos) {
        world.setBlock(pos, random.nextInt(4) == 0
                ? modBlock("lotr:angmar_cracked_brick", Blocks.CRACKED_STONE_BRICKS)
                : modBlock("lotr:angmar_brick", Blocks.STONE_BRICKS), 2);
    }

    /** PORT : escalier oriente, fissure une fois sur quatre. */
    private void placeRandomStairs(ISeedReader world, Random random, BlockPos pos,
                                   Direction facing) {
        BlockState stairs = random.nextInt(4) == 0
                ? modBlock("lotr:angmar_cracked_brick_stairs", Blocks.STONE_BRICK_STAIRS)
                : modBlock("lotr:angmar_brick_stairs", Blocks.STONE_BRICK_STAIRS);
        if (stairs.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
            stairs = stairs.setValue(BlockStateProperties.HORIZONTAL_FACING, facing);
        }
        world.setBlock(pos, stairs, 2);
    }
}
