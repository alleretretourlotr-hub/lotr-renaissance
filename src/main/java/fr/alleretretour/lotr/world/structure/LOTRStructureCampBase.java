package fr.alleretretour.lotr.world.structure;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

/**
 * PORT de LOTRWorldGenCampBase : le socle des campements.
 *
 * Tous les camps du Legacy partagent la meme disposition :
 *   - le sol est PIETINE dans un rayon de 12 : quatre cases d'herbe sur cinq
 *     deviennent de la terre battue ;
 *   - au centre, un FOYER de 3x3 : la colonne descend jusqu'au sol dur en
 *     pierre, et la surface est en dalles ;
 *   - QUATRE TENTES aux points cardinaux, chacune a une distance tiree entre
 *     6 et 12 avec un decalage lateral de -3 a 3 : jamais alignees.
 *
 * Une sous-classe se contente de choisir la couleur de sa toile et,
 * eventuellement, ses materiaux de foyer.
 *
 * Le capitaine de camp et les entites attendent le portage des respawners.
 */
public abstract class LOTRStructureCampBase extends LOTRStructureBase {

    private static final int GROUND_RANGE = 12;

    /** teintes possibles de la toile (PORT du tirage de chaque tente) */
    protected BlockState[] tentWool = {
            Blocks.WHITE_WOOL.defaultBlockState(),
            Blocks.BROWN_WOOL.defaultBlockState(),
            Blocks.LIGHT_GRAY_WOOL.defaultBlockState()};

    protected BlockState hearthBlock = Blocks.COBBLESTONE.defaultBlockState();
    protected BlockState hearthSlab = Blocks.COBBLESTONE_SLAB.defaultBlockState();

    protected LOTRStructureCampBase(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    protected boolean generateWithRotation(ISeedReader world, Random random, BlockPos origin,
                                           int rotation) {
        if (!isSurfaceLocal(world, origin, rotation, 0, -1, 0)
                || world.getBlockState(origin).getMaterial().isLiquid()) {
            return false;
        }
        // sol pietine
        for (int i = -GROUND_RANGE; i <= GROUND_RANGE; i++) {
            for (int k = -GROUND_RANGE; k <= GROUND_RANGE; k++) {
                if (i * i + k * k >= GROUND_RANGE * GROUND_RANGE || random.nextInt(5) == 0) {
                    continue;
                }
                BlockPos p = rotate(origin, rotation, i, 0, k);
                int y = world.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, p.getX(), p.getZ()) - 1;
                BlockPos ground = new BlockPos(p.getX(), y, p.getZ());
                if (world.getBlockState(ground).getBlock() != Blocks.GRASS_BLOCK) {
                    continue;
                }
                world.setBlock(ground, Blocks.COARSE_DIRT.defaultBlockState(), 2);
            }
        }
        // foyer central 3x3
        for (int i = -1; i <= 1; i++) {
            for (int k = -1; k <= 1; k++) {
                int j = -1;
                while (!isOpaqueLocal(world, origin, rotation, i, j, k)
                        && origin.getY() + j >= 0) {
                    setBlockRotated(world, origin, rotation, i, j, k, hearthBlock);
                    setGrassToDirt(world, rotate(origin, rotation, i, j - 1, k));
                    j--;
                }
                setBlockRotated(world, origin, rotation, i, 0, k, hearthSlab);
                setGrassToDirt(world, rotate(origin, rotation, i, -1, k));
            }
        }
        generateCentrepiece(world, random, origin, rotation);
        // quatre tentes
        for (int side = 0; side < 4; side++) {
            int tx = MathHelper.nextInt(random, -3, 3);
            int tz = MathHelper.nextInt(random, 6, 12);
            int x;
            int z;
            switch (side) {
                case 0: x = tx; z = tz; break;
                case 1: x = tz; z = -tx; break;
                case 2: x = -tx; z = -tz; break;
                default: x = -tz; z = tx; break;
            }
            placeTent(world, random, origin, rotation, x, z);
        }
        return true;
    }

    /** Element central propre a la faction ; par defaut, rien de plus. */
    protected void generateCentrepiece(ISeedReader world, Random random, BlockPos origin,
                                       int rotation) {
    }

    /** PORT des tentes : deux pans montant vers un faite, toile tiree. */
    protected void placeTent(ISeedReader world, Random random, BlockPos origin, int rotation,
                             int x, int z) {
        BlockPos p = rotate(origin, rotation, x, 0, z);
        int y = world.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, p.getX(), p.getZ());
        BlockPos foot = new BlockPos(p.getX(), y, p.getZ());
        if (!world.getBlockState(foot.below()).isSolidRender(world, foot.below())) {
            return;
        }
        for (int dx = -2; dx <= 2; dx++) {
            for (int dz = -2; dz <= 2; dz++) {
                BlockPos floor = foot.offset(dx, -1, dz);
                if (world.getBlockState(floor).getBlock() == Blocks.GRASS_BLOCK) {
                    world.setBlock(floor, Blocks.COARSE_DIRT.defaultBlockState(), 2);
                }
            }
        }
        for (int dz = -2; dz <= 2; dz++) {
            for (int dx = -2; dx <= 2; dx++) {
                int height = 3 - Math.abs(dx);
                if (height < 1) {
                    continue;
                }
                world.setBlock(foot.offset(dx, height, dz), wool(random), 2);
            }
            world.setBlock(foot.offset(0, 4, dz), wool(random), 2);
        }
    }

    protected BlockState wool(Random random) {
        return tentWool[random.nextInt(tentWool.length)];
    }
}
