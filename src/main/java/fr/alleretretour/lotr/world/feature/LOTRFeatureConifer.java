package fr.alleretretour.lotr.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

/**
 * PORT des generateurs d'arbres specifiques du Legacy.
 *
 * Chaque forme reproduit l'algorithme d'origine ligne a ligne :
 *   - SAPIN (LOTRWorldGenFir) : tronc 6-13, cime en 3 couches de feuilles
 *     posees de haut en bas, rayon = niveau / 2, filtre en losange
 *     (|dx| + |dz| <= rayon), sommet a hauteur + 2.
 *   - PIN DU COMTE (LOTRWorldGenShirePine) : tronc 10-20, houppier en
 *     couronne au sommet.
 * Les blocs sont ceux du mod (bloc_log / bloc_leaves), avec repli vanilla.
 */
public class LOTRFeatureConifer extends Feature<NoFeatureConfig> {

    /** Especes portees par ce generateur. */
    public enum Kind {
        /** PORT de LOTRWorldGenFir : hauteur 6-13, 3 couches de cime. */
        FIR("fir", 6, 13, 3),
        /** PORT de LOTRWorldGenShirePine : hauteur 10-20, couronne au sommet. */
        SHIRE_PINE("shire_pine", 10, 20, 4);

        public final String species;
        public final int minHeight;
        public final int maxHeight;
        public final int leafLayers;

        Kind(String species, int minHeight, int maxHeight, int leafLayers) {
            this.species = species;
            this.minHeight = minHeight;
            this.maxHeight = maxHeight;
            this.leafLayers = leafLayers;
        }
    }

    private final Kind kind;

    public LOTRFeatureConifer(Codec<NoFeatureConfig> codec, Kind kind) {
        super(codec);
        this.kind = kind;
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator generator, Random random,
                         BlockPos pos, NoFeatureConfig config) {
        int height = MathHelper.nextInt(random, kind.minHeight, kind.maxHeight);
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        if (y < 1 || y + height + 2 > world.getHeight()) {
            return false;
        }
        // le sol doit accueillir une pousse (PORT du test canSustainPlant)
        BlockState below = world.getBlockState(new BlockPos(x, y - 1, z));
        if (!below.canSustainPlant(world, new BlockPos(x, y - 1, z),
                net.minecraft.util.Direction.UP,
                (net.minecraftforge.common.IPlantable) Blocks.OAK_SAPLING)) {
            return false;
        }
        // place a-t-elle assez d'air ? (PORT de la boucle de verification)
        for (int j1 = y; j1 <= y + height + 2; j1++) {
            int range = j1 == y ? 0 : (j1 >= y + height - 1 ? 2 : 1);
            for (int i1 = x - range; i1 <= x + range; i1++) {
                for (int k1 = z - range; k1 <= z + range; k1++) {
                    BlockPos p = new BlockPos(i1, j1, k1);
                    if (!world.isEmptyBlock(p) && !world.getBlockState(p).getMaterial()
                            .isReplaceable() && !isLeaves(world.getBlockState(p))) {
                        return false;
                    }
                }
            }
        }

        BlockState leaf = leaves();
        // PORT : cime posee de haut en bas, rayon = couche / 2, filtre losange
        int leafLevel = y + height + 2;
        for (int l = 0; l <= kind.leafLayers * 2; l++) {
            int leafRange = l / 2;
            for (int i1 = x - leafRange; i1 <= x + leafRange; i1++) {
                for (int k1 = z - leafRange; k1 <= z + leafRange; k1++) {
                    if (Math.abs(i1 - x) + Math.abs(k1 - z) > leafRange) {
                        continue;
                    }
                    BlockPos p = new BlockPos(i1, leafLevel, k1);
                    BlockState state = world.getBlockState(p);
                    if (state.getMaterial().isReplaceable() || isLeaves(state)) {
                        setBlock(world, p, leaf);
                    }
                }
            }
            leafLevel--;
        }

        BlockState log = log();
        for (int j1 = 0; j1 < height; j1++) {
            setBlock(world, new BlockPos(x, y + j1, z), log);
        }
        return true;
    }

    private static boolean isLeaves(BlockState state) {
        return state.getBlock() instanceof net.minecraft.block.LeavesBlock;
    }

    private BlockState log() {
        return state("lotr:" + kind.species + "_log", Blocks.SPRUCE_LOG);
    }

    private BlockState leaves() {
        return state("lotr:" + kind.species + "_leaves", Blocks.SPRUCE_LEAVES);
    }

    private static BlockState state(String id, Block fallback) {
        Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(id));
        return (block == null || block == Blocks.AIR ? fallback : block).defaultBlockState();
    }
}
