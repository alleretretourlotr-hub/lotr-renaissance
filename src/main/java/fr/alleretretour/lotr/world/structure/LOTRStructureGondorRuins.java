package fr.alleretretour.lotr.world.structure;

import com.mojang.serialization.Codec;
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
 * PORT de LOTRWorldGenGondorRuins : les ruines gondoriennes et leur crypte.
 *
 * Deux parties, comme dans le Legacy :
 *
 * EN SURFACE, des vestiges epars tires au hasard dans un rayon de 5 :
 *   - 3 a 6 dalles posees a meme le sol ;
 *   - 3 a 6 moignons de mur de 1 a 3 blocs ;
 *   - 3 a 7 pans de mur de 4 a 10 blocs, qui se dressent encore.
 *   Une brique sur quatre est fissuree ou moussue.
 *
 * EN DESSOUS, une CRYPTE de 10x7x3 creusee entre -7 et -9, aux murs de
 * brique et au sol de roche, avec :
 *   - une echelle descendant depuis la surface ;
 *   - un coffre au tresor GARDE (le spectre des ruines dans le Legacy) et un
 *     second coffre d'ossements.
 * La crypte n'est creusee que si la roche est pleine a cet endroit : sinon
 * seuls les vestiges de surface apparaissent.
 *
 * Les spectres gardiens attendent le portage des coffres a apparition.
 */
public class LOTRStructureGondorRuins extends LOTRStructureBase {

    public LOTRStructureGondorRuins(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    protected boolean generateWithRotation(ISeedReader world, Random random, BlockPos origin,
                                           int rotation) {
        if (world.getBlockState(origin.below()).getBlock() != Blocks.GRASS_BLOCK) {
            return false;
        }
        int i = origin.getX();
        int j = origin.getY();
        int k = origin.getZ();

        // dalles eparses
        int slabs = 3 + random.nextInt(4);
        for (int l = 0; l < slabs; l++) {
            int x = i - 5 + random.nextInt(10);
            int z = k - 5 + random.nextInt(10);
            if (x == i && z == k) {
                continue;
            }
            int y = world.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, x, z);
            BlockPos p = new BlockPos(x, y, z);
            if (world.getBlockState(p.below()).isSolidRender(world, p.below())) {
                world.setBlock(p, randomSlab(random), 2);
            }
            setGrassToDirt(world, p.below());
        }
        // moignons de mur
        int small = 3 + random.nextInt(4);
        for (int l = 0; l < small; l++) {
            int x = i - 5 + random.nextInt(10);
            int z = k - 5 + random.nextInt(10);
            if (x == i && z == k) {
                continue;
            }
            int y = world.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, x, z);
            if (world.getBlockState(new BlockPos(x, y - 1, z))
                    .isSolidRender(world, new BlockPos(x, y - 1, z))) {
                int height = 1 + random.nextInt(3);
                for (int dy = 0; dy < height; dy++) {
                    world.setBlock(new BlockPos(x, y + dy, z), randomBrick(random), 2);
                }
            }
            setGrassToDirt(world, new BlockPos(x, y - 1, z));
        }
        // pans de mur encore debout
        int large = 3 + random.nextInt(5);
        for (int l = 0; l < large; l++) {
            int x = i - 5 + random.nextInt(10);
            int z = k - 5 + random.nextInt(10);
            if (x == i && z == k) {
                continue;
            }
            int y = world.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, x, z);
            if (world.getBlockState(new BlockPos(x, y - 1, z))
                    .isSolidRender(world, new BlockPos(x, y - 1, z))) {
                int height = 4 + random.nextInt(7);
                for (int dy = 0; dy < height; dy++) {
                    world.setBlock(new BlockPos(x, y + dy, z), randomBrick(random), 2);
                }
            }
            setGrassToDirt(world, new BlockPos(x, y - 1, z));
        }

        // la crypte exige de la roche pleine : sinon on s'en tient a la surface
        for (int x = i - 1; x <= i + 8; x++) {
            for (int y = j - 6; y >= j - 11; y--) {
                for (int z = k - 3; z <= k + 3; z++) {
                    if (!world.getBlockState(new BlockPos(x, y, z))
                            .isSolidRender(world, new BlockPos(x, y, z))) {
                        return true;
                    }
                }
            }
        }
        BlockState brick = modBlock("lotr:gondor_brick", Blocks.STONE_BRICKS);
        BlockState rock = modBlock("lotr:gondor_rock", Blocks.STONE);
        BlockState air = Blocks.AIR.defaultBlockState();
        BlockState slab = modBlock("lotr:gondor_brick_slab", Blocks.STONE_BRICK_SLAB);

        // coque de la crypte
        for (int x = i - 1; x <= i + 8; x++) {
            for (int y = j - 6; y >= j - 11; y--) {
                for (int z = k - 3; z <= k + 3; z++) {
                    world.setBlock(new BlockPos(x, y, z),
                            (y == j - 6 || y < j - 9) ? rock : brick, 2);
                }
            }
        }
        // salle creusee
        for (int x = i; x <= i + 7; x++) {
            for (int y = j - 7; y >= j - 9; y--) {
                for (int z = k - 2; z <= k + 2; z++) {
                    world.setBlock(new BlockPos(x, y, z), air, 2);
                }
            }
        }
        for (int z = k - 2; z <= k + 2; z++) {
            world.setBlock(new BlockPos(i + 7, j - 9, z), brick, 2);
            world.setBlock(new BlockPos(i + 7, j - 7, z), slab, 2);
            world.setBlock(new BlockPos(i, j - 7, z), slab, 2);
        }
        for (int x = i + 1; x <= i + 5; x++) {
            for (int z = k - 1; z <= k + 1; z++) {
                world.setBlock(new BlockPos(x, j - 10, z), rock, 2);
            }
        }
        for (int x = i + 2; x <= i + 4; x++) {
            world.setBlock(new BlockPos(x, j - 9, k), slab, 2);
        }

        // les deux coffres
        BlockPos treasure = new BlockPos(i + 4, j - 10, k);
        world.setBlock(treasure, Blocks.CHEST.defaultBlockState(), 2);
        setLootTable(world, treasure, "gondor_ruins_treasure", random);
        BlockPos bones = new BlockPos(i + 2, j - 10, k);
        world.setBlock(bones, Blocks.CHEST.defaultBlockState(), 2);
        setLootTable(world, bones, "gondor_ruins_bones", random);

        // echelle depuis la surface
        BlockState ladder = Blocks.LADDER.defaultBlockState();
        if (ladder.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
            ladder = ladder.setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST);
        }
        for (int y = j - 2; y >= j - 9; y--) {
            world.setBlock(new BlockPos(i, y, k), ladder, 2);
        }
        world.setBlock(new BlockPos(i, j - 1, k), brick, 2);
        return true;
    }

    /** PORT : une brique sur quatre est fissuree ou moussue. */
    private BlockState randomBrick(Random random) {
        if (random.nextInt(4) == 0) {
            return random.nextBoolean()
                    ? modBlock("lotr:gondor_cracked_brick", Blocks.CRACKED_STONE_BRICKS)
                    : modBlock("lotr:gondor_mossy_brick", Blocks.MOSSY_STONE_BRICKS);
        }
        return modBlock("lotr:gondor_brick", Blocks.STONE_BRICKS);
    }

    /** PORT : idem pour les dalles. */
    private BlockState randomSlab(Random random) {
        if (random.nextInt(4) == 0) {
            return random.nextBoolean()
                    ? modBlock("lotr:gondor_cracked_brick_slab", Blocks.STONE_BRICK_SLAB)
                    : modBlock("lotr:gondor_mossy_brick_slab", Blocks.MOSSY_STONE_BRICK_SLAB);
        }
        return modBlock("lotr:gondor_brick_slab", Blocks.STONE_BRICK_SLAB);
    }
}
