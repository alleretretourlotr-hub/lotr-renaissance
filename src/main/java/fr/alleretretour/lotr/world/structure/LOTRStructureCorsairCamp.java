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
 * PORT de LOTRWorldGenCorsairCamp (et de son socle LOTRWorldGenCampBase) :
 * le campement corsaire.
 *
 * Un camp n'est pas une batisse mais un ASSEMBLAGE, reproduit ici comme dans
 * le Legacy :
 *   - le sol est PIETINE dans un rayon de 12 : quatre cases d'herbe sur cinq
 *     deviennent de la terre ;
 *   - le centre vient du scan "corsair_camp_centre" ;
 *   - QUATRE TENTES sont dressees aux quatre points cardinaux, chacune a une
 *     distance tiree entre 6 et 12 et un decalage lateral de -3 a 3 : elles
 *     ne sont donc jamais alignees ;
 *   - une a deux PILES DE COFFRES, posees sur un billot, a une distance de
 *     8 a 20 et un angle libre - le butin des pillards ;
 *   - la toile des tentes est tiree entre rouge et gris clair a chaque bloc.
 *
 * Le capitaine corsaire, les cages a prisonniers et le contenu des coffres
 * attendent le portage des TileEntity et des entites.
 */
public class LOTRStructureCorsairCamp extends LOTRStructureScanned {

    private static final int GROUND_RANGE = 12;

    public LOTRStructureCorsairCamp(Codec<NoFeatureConfig> codec) {
        super(codec, "corsair_camp_centre");
        alias("SAND", Blocks.SAND.defaultBlockState());
        alias("PLANK", "lotr:charred_planks", Blocks.DARK_OAK_PLANKS);
        alias("WOOD", "lotr:charred_log", Blocks.DARK_OAK_LOG);
        alias("FENCE", "lotr:charred_fence", Blocks.DARK_OAK_FENCE);
    }

    @Override
    protected boolean canPlace(ISeedReader world, Random random, BlockPos base, int rotation) {
        // PORT : sol naturel et non liquide au centre
        return isSurfaceLocal(world, base, rotation, 0, -1, 0)
                && !world.getBlockState(rotate(base, rotation, 0, 0, 0))
                        .getMaterial().isLiquid();
    }

    @Override
    protected boolean generateWithRotation(ISeedReader world, Random random, BlockPos origin,
                                           int rotation) {
        // sol pietine : 4 cases d'herbe sur 5 deviennent de la terre
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
        // centre du camp (scan)
        if (!super.generateWithRotation(world, random, origin, rotation)) {
            return false;
        }
        // quatre tentes, une par point cardinal
        for (int side = 0; side < 4; side++) {
            int tentX = MathHelper.nextInt(random, -3, 3);
            int tentZ = MathHelper.nextInt(random, 6, 12);
            int x;
            int z;
            switch (side) {
                case 0: x = tentX; z = tentZ; break;
                case 1: x = tentZ; z = -tentX; break;
                case 2: x = -tentX; z = -tentZ; break;
                default: x = -tentZ; z = tentX; break;
            }
            placeTent(world, random, origin, rotation, x, z);
        }
        // une a deux piles de coffres
        int piles = 1 + random.nextInt(2);
        for (int l = 0; l < piles; l++) {
            for (int attempt = 0; attempt < 16; attempt++) {
                int r = MathHelper.nextInt(random, 8, 20);
                float angle = random.nextFloat() * (float) Math.PI * 2.0f;
                int x = (int) (r * MathHelper.cos(angle));
                int z = (int) (r * MathHelper.sin(angle));
                BlockPos p = rotate(origin, rotation, x, 0, z);
                int y = world.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, p.getX(), p.getZ());
                BlockPos log = new BlockPos(p.getX(), y, p.getZ());
                if (!world.getBlockState(log.below()).isSolidRender(world, log.below())
                        || !world.isEmptyBlock(log) || !world.isEmptyBlock(log.above())) {
                    continue;
                }
                world.setBlock(log, modBlock("lotr:charred_log", Blocks.DARK_OAK_LOG), 2);
                setGrassToDirt(world, log.below());
                world.setBlock(log.above(),
                        modBlock("lotr:chest_basket", Blocks.CHEST), 2);
                setLootTable(world, log.above(), "corsair", random);
                break;
            }
        }
        return true;
    }

    /** PORT de LOTRWorldGenCorsairTent : la toile est rouge ou gris clair. */
    private void placeTent(ISeedReader world, Random random, BlockPos origin, int rotation,
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
        // toile : deux pans montant vers un faite
        for (int dz = -2; dz <= 2; dz++) {
            for (int dx = -2; dx <= 2; dx++) {
                int height = 3 - Math.abs(dx);
                if (height < 1) {
                    continue;
                }
                world.setBlock(foot.offset(dx, height, dz), tentWool(random), 2);
            }
        }
        for (int dz = -2; dz <= 2; dz++) {
            world.setBlock(foot.offset(0, 4, dz), tentWool(random), 2);
        }
    }

    private BlockState tentWool(Random random) {
        return random.nextBoolean()
                ? Blocks.RED_WOOL.defaultBlockState()
                : Blocks.LIGHT_GRAY_WOOL.defaultBlockState();
    }
}
