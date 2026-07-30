package fr.alleretretour.lotr.world.structure;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

/**
 * PORT de LOTRWorldGenDunlendingCampfire : le campement dunlending.
 *
 * Structure d'origine reproduite telle quelle :
 *   - ne s'implante que sur l'herbe, sur une aire 11x11 plate (denivele max 2)
 *     et degagee sur 2 blocs de hauteur ;
 *   - le sol est aplani sur 3 couches (herbe en surface, terre dessous) ;
 *   - foyer central de gravier 3x3, avec l'atre allume au milieu ;
 *   - quatre piliers a crane aux angles (muret + crane) ;
 *   - jusqu'a quatre barrieres de rondins (une par cote, chacune tiree a pile
 *     ou face) posees en travers ;
 *   - une chance sur deux d'un panier a butin sur un des cotes.
 */
public class LOTRStructureDunlendingCampfire extends LOTRStructureBase {

    public LOTRStructureDunlendingCampfire(Codec<NoFeatureConfig> codec) {
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
            case 0: k += 5; break;
            case 1: i -= 5; break;
            case 2: k -= 5; break;
            default: i += 5; break;
        }
        // terrain plat, herbeux et degage
        for (int i1 = i - 5; i1 <= i + 5; i1++) {
            for (int k1 = k - 5; k1 <= k + 5; k1++) {
                for (int j1 = j + 1; j1 <= j + 2; j1++) {
                    BlockPos p = new BlockPos(i1, j1, k1);
                    if (world.getBlockState(p).isSolidRender(world, p)) {
                        return false;
                    }
                }
                int top = world.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, i1, k1) - 1;
                if (Math.abs(top - j) > 2) {
                    return false;
                }
                if (world.getBlockState(new BlockPos(i1, top, k1)).getBlock()
                        != Blocks.GRASS_BLOCK) {
                    return false;
                }
            }
        }
        // aplanissement
        for (int i1 = i - 5; i1 <= i + 5; i1++) {
            for (int k1 = k - 5; k1 <= k + 5; k1++) {
                for (int j1 = j; j1 >= j - 2; j1--) {
                    BlockPos above = new BlockPos(i1, j1 + 1, k1);
                    BlockPos p = new BlockPos(i1, j1, k1);
                    world.setBlock(p, world.getBlockState(above).isSolidRender(world, above)
                            ? Blocks.DIRT.defaultBlockState()
                            : Blocks.GRASS_BLOCK.defaultBlockState(), 2);
                    setGrassToDirt(world, p.below());
                }
            }
        }
        // foyer
        for (int i1 = i - 1; i1 <= i + 1; i1++) {
            for (int k1 = k - 1; k1 <= k + 1; k1++) {
                world.setBlock(new BlockPos(i1, j, k1), Blocks.GRAVEL.defaultBlockState(), 2);
            }
        }
        world.setBlock(new BlockPos(i, j, k), modBlock("lotr:hearth", Blocks.NETHERRACK), 2);
        world.setBlock(new BlockPos(i, j + 1, k), Blocks.FIRE.defaultBlockState(), 2);

        placeSkullPillar(world, random, new BlockPos(i - 2, j + 1, k - 2));
        placeSkullPillar(world, random, new BlockPos(i + 2, j + 1, k - 2));
        placeSkullPillar(world, random, new BlockPos(i - 2, j + 1, k + 2));
        placeSkullPillar(world, random, new BlockPos(i + 2, j + 1, k + 2));

        BlockState logX = Blocks.OAK_LOG.defaultBlockState()
                .setValue(RotatedPillarBlock.AXIS, Direction.Axis.X);
        BlockState logZ = Blocks.OAK_LOG.defaultBlockState()
                .setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z);
        // barrieres de rondins (PORT : un tirage par cote)
        if (random.nextBoolean()) {
            for (int i1 = i - 2; i1 <= i + 2; i1++) {
                world.setBlock(new BlockPos(i1, j + 1, k + 4), logX, 2);
                setGrassToDirt(world, new BlockPos(i1, j, k + 4));
            }
        }
        if (random.nextBoolean()) {
            for (int k1 = k - 2; k1 <= k + 2; k1++) {
                world.setBlock(new BlockPos(i - 4, j + 1, k1), logZ, 2);
                setGrassToDirt(world, new BlockPos(i - 4, j, k1));
            }
        }
        if (random.nextBoolean()) {
            for (int i1 = i - 2; i1 <= i + 2; i1++) {
                world.setBlock(new BlockPos(i1, j + 1, k - 4), logX, 2);
                setGrassToDirt(world, new BlockPos(i1, j, k - 4));
            }
        }
        if (random.nextBoolean()) {
            for (int k1 = k - 2; k1 <= k + 2; k1++) {
                world.setBlock(new BlockPos(i + 4, j + 1, k1), logZ, 2);
                setGrassToDirt(world, new BlockPos(i + 4, j, k1));
            }
        }
        // panier a butin
        if (random.nextBoolean()) {
            int chestX = i;
            int chestZ = k;
            Direction facing = Direction.NORTH;
            switch (random.nextInt(4)) {
                case 0:
                    chestX = i - 3 + random.nextInt(6);
                    chestZ = k + 3;
                    facing = Direction.SOUTH;
                    break;
                case 1:
                    chestX = i - 3;
                    chestZ = k - 3 + random.nextInt(6);
                    facing = Direction.WEST;
                    break;
                case 2:
                    chestX = i - 3 + random.nextInt(6);
                    chestZ = k - 3;
                    facing = Direction.NORTH;
                    break;
                default:
                    chestX = i + 3;
                    chestZ = k - 3 + random.nextInt(6);
                    facing = Direction.EAST;
                    break;
            }
            BlockState basket = modBlock("lotr:chest_basket", Blocks.CHEST);
            if (basket.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
                basket = basket.setValue(BlockStateProperties.HORIZONTAL_FACING, facing);
            }
            BlockPos chestPos = new BlockPos(chestX, j + 1, chestZ);
            world.setBlock(chestPos, basket, 2);
            // PORT de LOTRChestContents.DUNLENDING_CAMPFIRE
            setLootTable(world, chestPos, "dunlending_campfire", random);
        }
        return true;
    }

    /** PORT de placeSkullPillar : muret surmonte d'un crane. */
    private void placeSkullPillar(ISeedReader world, Random random, BlockPos pos) {
        world.setBlock(pos, Blocks.COBBLESTONE_WALL.defaultBlockState(), 2);
        world.setBlock(pos.above(), Blocks.SKELETON_SKULL.defaultBlockState(), 2);
    }
}
