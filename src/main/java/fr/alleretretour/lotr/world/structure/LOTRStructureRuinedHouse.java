package fr.alleretretour.lotr.world.structure;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.Half;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

/**
 * PORT de LOTRWorldGenRuinedHouse : la maison abandonnee.
 *
 * La plus repandue des structures du Legacy : avec ses deux variantes
 * (pourrie et brulee) elle apparait dans 45 biomes.
 *
 * Rien n'y est fixe, tout est tire au sort - c'est ce qui fait qu'aucune
 * ruine ne ressemble a une autre :
 *   - largeur 4 a 6, terrain herbe/terre/pierre, denivele maximal 5 ;
 *   - le SOL est refait bloc par bloc : terre battue, gravier, pierre ou sa
 *     variante moussue, a parts egales, en descendant jusqu'au sol dur ;
 *   - les MURS : une pose sur douze manque (les breches), et chaque bloc est
 *     tire entre barriere, planche, bois couche et escalier ;
 *   - la facade nord s'interrompt des qu'un tirage sur quatre echoue : les
 *     murs s'arretent en plein milieu ;
 *   - quatre PILIERS de bois aux angles, avec des traverses aleatoires ;
 *   - un four adosse a un mur, d'un cote ou de l'autre, et un coffre.
 */
public class LOTRStructureRuinedHouse extends LOTRStructureBase {

    protected BlockState wood = Blocks.OAK_LOG.defaultBlockState();
    protected BlockState plank = Blocks.OAK_PLANKS.defaultBlockState();
    protected BlockState fence = Blocks.OAK_FENCE.defaultBlockState();
    protected BlockState stairs = Blocks.OAK_STAIRS.defaultBlockState();
    protected BlockState stone = Blocks.COBBLESTONE.defaultBlockState();
    protected BlockState stoneVariant = Blocks.MOSSY_COBBLESTONE.defaultBlockState();
    /** table de butin du coffre (PORT de LOTRChestContents.RUINED_HOUSE) */
    protected String loot = "ruined_house";

    public LOTRStructureRuinedHouse(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    protected boolean generateWithRotation(ISeedReader world, Random random, BlockPos origin,
                                           int rotation) {
        int width = 4 + random.nextInt(3);   // PORT : largeur 4 a 6
        BlockPos base = origin;

        // terrain : herbe, terre ou pierre, denivele maximal 5
        int min = 1;
        int max = 1;
        for (int i = -width; i <= width; i++) {
            for (int k = -width; k <= width; k++) {
                BlockPos p = rotate(base, rotation, i, 0, k);
                int y = world.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, p.getX(), p.getZ());
                Block b = world.getBlockState(new BlockPos(p.getX(), y - 1, p.getZ())).getBlock();
                if (b != Blocks.GRASS_BLOCK && b != Blocks.DIRT && b != Blocks.STONE) {
                    return false;
                }
                int rel = y - base.getY();
                min = Math.min(min, rel);
                max = Math.max(max, rel);
            }
        }
        if (Math.abs(max - min) > 5) {
            return false;
        }

        // degagement et refection du sol
        BlockState air = Blocks.AIR.defaultBlockState();
        for (int i = -width; i <= width; i++) {
            for (int k = -width; k <= width; k++) {
                for (int j = 0; j <= 5; j++) {
                    setBlockRotated(world, base, rotation, i, j, k, air);
                }
                int j = 0;
                while (!isOpaqueLocal(world, base, rotation, i, j, k) && base.getY() + j >= 0) {
                    setBlockRotated(world, base, rotation, i, j, k, groundBlock(random));
                    setGrassToDirt(world, rotate(base, rotation, i, j - 1, k));
                    j--;
                }
            }
        }

        // murs est et ouest
        for (int k = -width; k <= width; k++) {
            wallOrStone(world, base, rotation, random, -width, 1, -k);
            wallOrStone(world, base, rotation, random, width, 1, k);
            wall(world, base, rotation, random, -width, 2, k, true);
            wall(world, base, rotation, random, width, 2, k, true);
            wall(world, base, rotation, random, -width, 3, k, true);
            wall(world, base, rotation, random, width, 3, k, true);
        }
        // mur sud
        for (int i = -width; i <= width; i++) {
            wallOrStone(world, base, rotation, random, i, 1, width);
            if (random.nextInt(3) == 0) {
                wallOrStone(world, base, rotation, random, i, 2, width - 1);
            }
            wall(world, base, rotation, random, i, 2, width, false);
            wall(world, base, rotation, random, i, 3, width, false);
        }
        // facade nord : elle s'interrompt des qu'un tirage sur quatre echoue
        for (int i = -width + 1; i <= -1 && random.nextInt(4) != 0; i++) {
            wallOrStone(world, base, rotation, random, i, 1, -width);
        }
        for (int i = width - 1; i >= 1 && random.nextInt(4) != 0; i--) {
            wallOrStone(world, base, rotation, random, i, 1, -width);
        }
        setBlockRotated(world, base, rotation, -width + 1, 2, -width, fence);
        setBlockRotated(world, base, rotation, width - 1, 2, -width, fence);

        // piliers d'angle
        pillar(world, base, rotation, random, -width, 1, -width);
        pillar(world, base, rotation, random, width, 1, -width);
        pillar(world, base, rotation, random, -width, 1, width);
        pillar(world, base, rotation, random, width, 1, width);

        // four adosse, d'un cote ou de l'autre
        int side = random.nextBoolean() ? width - 1 : -width + 1;
        setBlockRotated(world, base, rotation, side, 1, -width + 1, stone);
        setBlockRotated(world, base, rotation, side, 1, -width + 2,
                Blocks.FURNACE.defaultBlockState());

        // coffre
        int chestX = random.nextBoolean() ? width - 1 : -width + 1;
        BlockPos chest = rotate(base, rotation, chestX, 1, width - 2);
        world.setBlock(chest, Blocks.CHEST.defaultBlockState(), 2);
        setLootTable(world, chest, loot, random);
        return true;
    }

    /** PORT de placeRandomGroundBlock : quatre sols a parts egales. */
    private BlockState groundBlock(Random random) {
        switch (random.nextInt(4)) {
            case 0: return Blocks.COARSE_DIRT.defaultBlockState();
            case 1: return Blocks.GRAVEL.defaultBlockState();
            case 2: return stone;
            default: return stoneVariant;
        }
    }

    /** PORT de placeRandomWall : une pose sur douze manque. */
    private void wall(ISeedReader world, BlockPos base, int rotation, Random random,
                      int i, int j, int k, boolean northToSouth) {
        if (random.nextInt(12) == 0 || !isOpaqueLocal(world, base, rotation, i, j - 1, k)) {
            return;
        }
        BlockState state;
        switch (random.nextInt(4)) {
            case 0:
                state = fence;
                break;
            case 1:
                state = plank;
                break;
            case 2:
                state = wood.hasProperty(RotatedPillarBlock.AXIS)
                        ? wood.setValue(RotatedPillarBlock.AXIS,
                                northToSouth ? Direction.Axis.Z : Direction.Axis.X)
                        : wood;
                break;
            default:
                state = stairs;
                if (state.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
                    Direction d = Direction.from2DDataValue(random.nextInt(4));
                    for (int r = 0; r < rotation; r++) {
                        d = d.getClockWise();
                    }
                    state = state.setValue(BlockStateProperties.HORIZONTAL_FACING, d);
                }
                if (random.nextBoolean() && state.hasProperty(BlockStateProperties.HALF)) {
                    state = state.setValue(BlockStateProperties.HALF, Half.TOP);
                }
                break;
        }
        setBlockRotated(world, base, rotation, i, j, k, state);
    }

    /** PORT de placeRandomWallOrStone : idem, avec la pierre en plus. */
    private void wallOrStone(ISeedReader world, BlockPos base, int rotation, Random random,
                             int i, int j, int k) {
        if (random.nextInt(12) == 0 || !isOpaqueLocal(world, base, rotation, i, j - 1, k)) {
            return;
        }
        BlockState state;
        switch (random.nextInt(4)) {
            case 0: state = fence; break;
            case 1: state = plank; break;
            case 2: state = stone; break;
            default: state = stoneVariant; break;
        }
        setBlockRotated(world, base, rotation, i, j, k, state);
    }

    /** PORT de placeWoodPillar : pilier de bois avec traverses aleatoires. */
    private void pillar(ISeedReader world, BlockPos base, int rotation, Random random,
                        int i, int j, int k) {
        for (int j1 = j; j1 <= j + 3; j1++) {
            setBlockRotated(world, base, rotation, i, j1, k, wood);
            if (random.nextInt(4) == 0 && j1 >= j + 2) {
                break;   // PORT : le pilier peut s'arreter plus bas
            }
        }
    }
}
