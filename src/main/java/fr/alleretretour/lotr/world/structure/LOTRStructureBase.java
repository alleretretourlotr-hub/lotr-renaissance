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
}
