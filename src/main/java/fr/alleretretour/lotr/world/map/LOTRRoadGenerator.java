package fr.alleretretour.lotr.world.map;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * PORT de LOTRRoadGenerator / LOTRRoads.isRoadAt : pose les routes de la Terre
 * du Milieu au sol.
 *
 * Fidele au Legacy :
 *   - largeur 4 (isRoadAt = isRoadNear(x, z, 4)) ;
 *   - segments droits entre points de trace, echantillonnes bloc par bloc ;
 *   - blocs du type PATH : dessus 50% chemin de terre, 30% terre grossiere,
 *     20% gravier ; en dessous, chemin de terre.
 *
 * Les types de route de faction (PAVED_PATH, COBBLESTONE, GALADHRIM...) et les
 * ponts au-dessus de l'eau viendront quand les blocs correspondants du mod
 * seront portes ; ici seul le type PATH est pose, avec repli vanilla.
 */
public final class LOTRRoadGenerator {

    /** PORT : LOTRRoads.isRoadAt utilise une largeur de 4 blocs. */
    public static final int ROAD_WIDTH = 4;

    /** Points de trace convertis en coordonnees monde, une fois pour toutes. */
    private static List<double[]> segments;

    private LOTRRoadGenerator() {
    }

    private static synchronized void buildSegments() {
        if (segments != null) {
            return;
        }
        List<double[]> list = new ArrayList<>();
        for (LOTRRoads.Road road : LOTRRoads.ROADS) {
            for (int i = 0; i < road.points.size() - 1; i++) {
                LOTRRoads.Point a = road.points.get(i);
                LOTRRoads.Point b = road.points.get(i + 1);
                list.add(new double[]{
                        toWorldX(a.x), toWorldZ(a.z), toWorldX(b.x), toWorldZ(b.z)});
            }
        }
        segments = list;
    }

    private static double toWorldX(double mapX) {
        return (mapX - fr.alleretretour.lotr.world.biome.LOTRBiomeSource.ORIGIN_X)
                * fr.alleretretour.lotr.world.biome.LOTRBiomeSource.SCALE;
    }

    private static double toWorldZ(double mapZ) {
        return (mapZ - fr.alleretretour.lotr.world.biome.LOTRBiomeSource.ORIGIN_Z)
                * fr.alleretretour.lotr.world.biome.LOTRBiomeSource.SCALE;
    }

    /** true si une route passe a moins de ROAD_WIDTH blocs (PORT de isRoadAt). */
    public static boolean isRoadAt(int x, int z) {
        buildSegments();
        double widthSq = ROAD_WIDTH * ROAD_WIDTH;
        for (double[] s : segments) {
            if (distanceSqToSegment(x, z, s[0], s[1], s[2], s[3]) < widthSq) {
                return true;
            }
        }
        return false;
    }

    private static double distanceSqToSegment(double px, double pz,
                                              double x1, double z1, double x2, double z2) {
        double dx = x2 - x1;
        double dz = z2 - z1;
        double lenSq = dx * dx + dz * dz;
        if (lenSq == 0.0) {
            double ex = px - x1;
            double ez = pz - z1;
            return ex * ex + ez * ez;
        }
        double t = MathHelper.clamp(((px - x1) * dx + (pz - z1) * dz) / lenSq, 0.0, 1.0);
        double cx = x1 + t * dx;
        double cz = z1 + t * dz;
        return (px - cx) * (px - cx) + (pz - cz) * (pz - cz);
    }

    /** Pose la route sur un chunk (appele par LOTRFeatureRoads). */
    public static void generate(ISeedReader world, Random rand, BlockPos origin) {
        for (int dx = 0; dx < 16; dx++) {
            for (int dz = 0; dz < 16; dz++) {
                int x = origin.getX() + dx;
                int z = origin.getZ() + dz;
                if (!isRoadAt(x, z)) {
                    continue;
                }
                int y = world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, x, z) - 1;
                if (y <= 0) {
                    continue;
                }
                BlockPos pos = new BlockPos(x, y, z);
                BlockState existing = world.getBlockState(pos);
                if (existing.getMaterial().isLiquid()) {
                    continue;  // pas de pont pour l'instant (voir javadoc)
                }
                world.setBlock(pos, topBlock(rand), 2);
                world.setBlock(pos.below(), PATH_UNDER.get(), 2);
                // degage la vegetation posee au-dessus du chemin
                BlockPos above = pos.above();
                if (!world.getBlockState(above).isAir()
                        && !world.getBlockState(above).getMaterial().isLiquid()) {
                    world.setBlock(above, Blocks.AIR.defaultBlockState(), 2);
                }
            }
        }
    }

    /** PORT de LOTRRoadType.PATH.getBlock(top = true). */
    private static BlockState topBlock(Random rand) {
        float f = rand.nextFloat();
        if (f < 0.5f) {
            return PATH_TOP.get();
        }
        if (f < 0.8f) {
            return Blocks.COARSE_DIRT.defaultBlockState();
        }
        return Blocks.GRAVEL.defaultBlockState();
    }

    /** lotr:dirt_path si le bloc du mod est porte, sinon terre grossiere. */
    private static final java.util.function.Supplier<BlockState> PATH_TOP =
            memoize("lotr:dirt_path", Blocks.COARSE_DIRT);
    private static final java.util.function.Supplier<BlockState> PATH_UNDER =
            memoize("lotr:dirt_path", Blocks.DIRT);

    private static java.util.function.Supplier<BlockState> memoize(String id, Block fallback) {
        return new java.util.function.Supplier<BlockState>() {
            private BlockState cached;

            @Override
            public BlockState get() {
                if (cached == null) {
                    Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(id));
                    cached = (block == null || block == Blocks.AIR ? fallback : block)
                            .defaultBlockState();
                }
                return cached;
            }
        };
    }

    /** Utilitaire : distance du joueur a la route la plus proche (debug). */
    public static double nearestRoadDistance(Vector3i pos) {
        buildSegments();
        double best = Double.MAX_VALUE;
        for (double[] s : segments) {
            best = Math.min(best, distanceSqToSegment(pos.getX(), pos.getZ(),
                    s[0], s[1], s[2], s[3]));
        }
        return Math.sqrt(best);
    }
}
