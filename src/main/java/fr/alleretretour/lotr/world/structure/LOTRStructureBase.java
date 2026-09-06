package fr.alleretretour.lotr.world.structure;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

/**
 * Socle commun des structures du mod (PORT de LOTRWorldGenStructureBase).
 *
 * Fournit ce dont chaque structure a besoin :
 *   - resolution des blocs du mod PAR NOM, avec repli vanilla (les blocs non
 *     encore portes n'empechent pas la structure d'apparaitre) ;
 *   - rotation sur les quatre orientations, comme le Legacy ;
 *   - pose de bloc en coordonnees LOCALES (i, j, k) tournees autour de
 *     l'origine, ce qui permet d'ecrire les structures exactement comme dans
 *     le mod d'origine ;
 *   - herbe convertie en terre sous les fondations (setGrassToDirt).
 */
public abstract class LOTRStructureBase extends Feature<NoFeatureConfig> {

    protected LOTRStructureBase(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(ISeedReader world, net.minecraft.world.gen.ChunkGenerator generator,
                         Random random, BlockPos pos, NoFeatureConfig config) {
        int rotation = random.nextInt(4);   // PORT : rotation aleatoire sur 4
        int x = pos.getX();
        int z = pos.getZ();
        int y = world.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, x, z);
        return generateWithRotation(world, random, new BlockPos(x, y, z), rotation);
    }

    /** A implementer par chaque structure, en coordonnees locales. */
    protected abstract boolean generateWithRotation(ISeedReader world, Random random,
                                                    BlockPos origin, int rotation);

    /** Pose un bloc en coordonnees locales, tournees selon la rotation. */
    protected void setBlockRotated(ISeedReader world, BlockPos origin, int rotation,
                                   int i, int j, int k, BlockState state) {
        world.setBlock(rotate(origin, rotation, i, j, k), state, 2);
    }

    /** PORT de la rotation du Legacy (0 = nord, sens horaire). */
    protected BlockPos rotate(BlockPos origin, int rotation, int i, int j, int k) {
        switch (rotation) {
            case 1:
                return origin.offset(-k, j, i);
            case 2:
                return origin.offset(-i, j, -k);
            case 3:
                return origin.offset(k, j, -i);
            default:
                return origin.offset(i, j, k);
        }
    }

    /** PORT de setGrassToDirt. */
    protected void setGrassToDirt(ISeedReader world, BlockPos pos) {
        if (world.getBlockState(pos).getBlock() == Blocks.GRASS_BLOCK) {
            world.setBlock(pos, Blocks.DIRT.defaultBlockState(), 2);
        }
    }

    /**
     * Remplit un coffre depuis une table de butin du mod
     * (PORT de LOTRChestContents.fillChest).
     */
    protected void setLootTable(ISeedReader world, BlockPos pos, String table, Random random) {
        net.minecraft.tileentity.TileEntity te = world.getBlockEntity(pos);
        if (te instanceof net.minecraft.tileentity.LockableLootTileEntity) {
            ((net.minecraft.tileentity.LockableLootTileEntity) te).setLootTable(
                    new ResourceLocation("lotr", "chests/" + table), random.nextLong());
        }
    }

    /** Bloc du mod resolu par nom, avec repli vanilla. */
    protected static BlockState modBlock(String id, Block fallback) {
        Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(id));
        return (block == null || block == Blocks.AIR ? fallback : block).defaultBlockState();
    }

    // ============ helpers des structures traduites automatiquement ============
    // (setLootTable, modBlock, rotate, setBlockRotated et setGrassToDirt sont
    //  definis plus haut : ne PAS les redeclarer ici.)

    /** Comme modBlock, mais renvoie le Block (pour les comparaisons). */
    protected static Block modBlockOnly(String id, Block fallback) {
        Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(id));
        return block == null || block == Blocks.AIR ? fallback : block;
    }

    /** Surcharge : pose un Block (les variables du Legacy sont des Block). */
    protected void setBlockRotated(ISeedReader world, BlockPos origin, int rotation,
                                   int i, int j, int k, Block block) {
        setBlockRotated(world, origin, rotation, i, j, k, block.defaultBlockState());
    }

    protected boolean isOpaqueLocal(ISeedReader world, BlockPos base, int rotation,
                                    int x, int y, int z) {
        BlockPos p = rotate(base, rotation, x, y, z);
        return world.getBlockState(p).isSolidRender(world, p);
    }

    /** true si le bloc est une surface naturelle (herbe, terre, pierre, sable). */
    protected boolean isSurfaceLocal(ISeedReader world, BlockPos base, int rotation,
                                     int x, int y, int z) {
        Block b = world.getBlockState(rotate(base, rotation, x, y, z)).getBlock();
        return b == Blocks.GRASS_BLOCK || b == Blocks.DIRT || b == Blocks.STONE
                || b == Blocks.SAND || b == Blocks.GRAVEL || b == Blocks.COARSE_DIRT;
    }

    /** Hauteur du sol, en coordonnees locales (PORT de getTopBlock). */
    protected int getTopBlockLocal(ISeedReader world, BlockPos base, int rotation,
                                   int x, int z) {
        BlockPos p = rotate(base, rotation, x, 0, z);
        return world.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, p.getX(), p.getZ()) - base.getY();
    }

    /** Brique de faction : une sur cinq fissuree ou moussue (PORT). */
    protected void placeRandomBrick(ISeedReader world, BlockPos base, int rotation,
                                    Random random, int x, int y, int z) {
        BlockState state;
        if (random.nextInt(5) == 0) {
            state = random.nextBoolean()
                    ? modBlock("lotr:gondor_cracked_brick", Blocks.CRACKED_STONE_BRICKS)
                    : modBlock("lotr:gondor_mossy_brick", Blocks.MOSSY_STONE_BRICKS);
        } else {
            state = modBlock("lotr:gondor_brick", Blocks.STONE_BRICKS);
        }
        setBlockRotated(world, base, rotation, x, y, z, state);
    }

    /** Escalier de faction oriente (le meta du Legacy donne la direction). */
    protected void placeRandomStairs(ISeedReader world, BlockPos base, int rotation,
                                     Random random, int x, int y, int z, int meta) {
        BlockState state = random.nextInt(5) == 0
                ? modBlock("lotr:gondor_mossy_brick_stairs", Blocks.MOSSY_STONE_BRICK_STAIRS)
                : modBlock("lotr:gondor_brick_stairs", Blocks.STONE_BRICK_STAIRS);
        setBlockRotated(world, base, rotation, x, y, z, withMeta(state, meta, rotation));
    }

    /** PORT des metadonnees d'escalier 1.7.10 : 0=est, 1=ouest, 2=sud, 3=nord. */
    protected static net.minecraft.util.Direction stairFacing(int meta) {
        switch (meta & 3) {
            case 0: return net.minecraft.util.Direction.EAST;
            case 1: return net.minecraft.util.Direction.WEST;
            case 2: return net.minecraft.util.Direction.SOUTH;
            default: return net.minecraft.util.Direction.NORTH;
        }
    }

    /**
     * PORT des metadonnees 1.7.10 : orientation des escaliers (0-3 + bit 4
     * "renverse"), axe des piliers, moitie des dalles.
     */
    protected BlockState withMeta(BlockState state, int meta, int rotation) {
        if (state.hasProperty(net.minecraft.state.properties.BlockStateProperties.HORIZONTAL_FACING)) {
            net.minecraft.util.Direction d = stairFacing(meta);
            for (int r = 0; r < rotation; r++) {
                d = d.getClockWise();
            }
            state = state.setValue(
                    net.minecraft.state.properties.BlockStateProperties.HORIZONTAL_FACING, d);
            if ((meta & 4) != 0
                    && state.hasProperty(net.minecraft.state.properties.BlockStateProperties.HALF)) {
                state = state.setValue(net.minecraft.state.properties.BlockStateProperties.HALF,
                        net.minecraft.state.properties.Half.TOP);
            }
            return state;
        }
        if (state.hasProperty(net.minecraft.block.RotatedPillarBlock.AXIS)) {
            net.minecraft.util.Direction.Axis axis;
            switch ((meta >> 2) & 3) {
                case 1: axis = net.minecraft.util.Direction.Axis.X; break;
                case 2: axis = net.minecraft.util.Direction.Axis.Z; break;
                default: axis = net.minecraft.util.Direction.Axis.Y; break;
            }
            return state.setValue(net.minecraft.block.RotatedPillarBlock.AXIS, axis);
        }
        if (state.hasProperty(net.minecraft.state.properties.BlockStateProperties.SLAB_TYPE)
                && (meta & 8) != 0) {
            return state.setValue(net.minecraft.state.properties.BlockStateProperties.SLAB_TYPE,
                    net.minecraft.state.properties.SlabType.TOP);
        }
        return state;
    }

    /** Surcharge : metadonnee appliquee a un Block. */
    protected BlockState withMeta(Block block, int meta, int rotation) {
        return withMeta(block.defaultBlockState(), meta, rotation);
    }
}
