package fr.alleretretour.lotr.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
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
 * PORT de LOTRWorldGenCedar : le cedre.
 *
 * Algorithme d'origine conserve tel quel :
 *   - hauteur 10-16 ;
 *   - cime : 3 couches au sommet, rayon 2 a la base puis decroissant, avec du
 *     BOIS pose en croix sur la couche basse (les charpentieres) ;
 *   - branches : depuis height - 1 en descendant par pas de 1 a 3 niveaux
 *     jusqu'a la mi-hauteur, 1 a 3 branches par palier, angle aleatoire,
 *     longueur 4-7, montant d'un bloc tous les deux pas (j = niveau - 3 + l/2) ;
 *     si le bouquet terminal est assez fourni, une croix de bois est ajoutee ;
 *     chaque branche finit par 1-2 couches de feuilles en disque ;
 *   - racines : quatre cotes, descendant jusqu'a l'obstacle.
 * C'est cette structure en plateaux etages qui donne au cedre sa silhouette.
 */
public class LOTRFeatureCedar extends Feature<NoFeatureConfig> {

    private final int minHeight;
    private final int maxHeight;

    /** PORT de setMinMaxHeight : CEDAR = 10-16, CEDAR_LARGE = 15-30. */
    public LOTRFeatureCedar(Codec<NoFeatureConfig> codec, int minHeight, int maxHeight) {
        super(codec);
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator generator, Random random,
                         BlockPos pos, NoFeatureConfig config) {
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        int height = MathHelper.nextInt(random, minHeight, maxHeight);
        if (j < 1 || j + height + 1 > world.getHeight()) {
            return false;
        }
        for (int j1 = j; j1 <= j + height + 1; j1++) {
            int range = 1;
            if (j1 == j) {
                range = 0;
            }
            if (j1 >= j + height - 1) {
                range = 2;
            }
            for (int i1 = i - range; i1 <= i + range; i1++) {
                for (int k1 = k - range; k1 <= k + range; k1++) {
                    if (!replaceable(world, new BlockPos(i1, j1, k1))) {
                        return false;
                    }
                }
            }
        }
        BlockPos soil = new BlockPos(i, j - 1, k);
        if (!world.getBlockState(soil).canSustainPlant(world, soil, Direction.UP,
                (net.minecraftforge.common.IPlantable) Blocks.OAK_SAPLING)) {
            return false;
        }

        BlockState log = log();
        BlockState leaf = leaves();

        // cime : 3 couches, croix de bois sur la couche basse
        int canopyMin = j + height - 2;
        for (int j1 = canopyMin; j1 <= j + height; j1++) {
            int leafRange = 2 - (j1 - (j + height));
            spawnLeaves(world, i, j1, k, leafRange, leaf);
            if (j1 != canopyMin) {
                continue;
            }
            for (int i1 = i - 1; i1 <= i + 1; i1++) {
                for (int k1 = k - 1; k1 <= k + 1; k1++) {
                    if (i1 != i && k1 != k) {
                        continue;   // PORT : croix, pas les diagonales
                    }
                    BlockPos p = new BlockPos(i1, j1, k1);
                    if (!replaceable(world, p)) {
                        continue;
                    }
                    setBlock(world, p, log);
                }
            }
        }

        // branches par paliers
        for (int j1 = j + height - 1; j1 > j + height / 2; j1 -= 1 + random.nextInt(3)) {
            int branches = 1 + random.nextInt(3);        // PORT
            for (int l = 0; l < branches; l++) {
                float angle = random.nextFloat() * (float) Math.PI * 2.0f;
                int length = MathHelper.nextInt(random, 4, 7);
                int leafMin = 1 + random.nextInt(2);
                int i1 = i;
                int k1 = k;
                int j2 = j1;
                boolean blocked = false;
                for (int l1 = 0; l1 < length; l1++) {
                    i1 = i + (int) (0.5f + MathHelper.cos(angle) * (l1 + 1));
                    j2 = j1 - 3 + l1 / 2;
                    k1 = k + (int) (0.5f + MathHelper.sin(angle) * (l1 + 1));
                    BlockPos p = new BlockPos(i1, j2, k1);
                    BlockState state = world.getBlockState(p);
                    if (!state.getMaterial().isReplaceable() && !isLeaves(state)
                            && state.getBlock() != log.getBlock()) {
                        blocked = true;
                        break;
                    }
                    setBlock(world, p, log);
                    if (l1 != length - 1 || leafMin < 2) {
                        continue;
                    }
                    // croix de bois sous le bout de branche
                    for (int i2 = i1 - 1; i2 <= i1 + 1; i2++) {
                        for (int k2 = k1 - 1; k2 <= k1 + 1; k2++) {
                            if (i2 != i1 && k2 != k1) {
                                continue;
                            }
                            BlockPos q = new BlockPos(i2, j2 - 1, k2);
                            if (!replaceable(world, q)) {
                                continue;
                            }
                            setBlock(world, q, log);
                        }
                    }
                }
                if (blocked) {
                    continue;
                }
                for (int j3 = j2 - leafMin; j3 <= j2; j3++) {
                    spawnLeaves(world, i1, j3, k1, 1 - (j3 - j2), leaf);
                }
            }
        }

        for (int j1 = 0; j1 < height; j1++) {
            setBlock(world, new BlockPos(i, j + j1, k), log);
        }

        // racines
        for (int i1 = i - 1; i1 <= i + 1; i1++) {
            for (int k1 = k - 1; k1 <= k + 1; k1++) {
                if (Math.abs(i1 - i) == Math.abs(k1 - k)) {
                    continue;
                }
                int rootY = j + random.nextInt(2);
                while (rootY > 0 && replaceable(world, new BlockPos(i1, rootY, k1))) {
                    setBlock(world, new BlockPos(i1, rootY, k1), log);
                    rootY--;
                }
            }
        }
        return true;
    }

    /** PORT de spawnLeaves : disque de feuilles. */
    private void spawnLeaves(ISeedReader world, int i, int j, int k, int leafRange,
                             BlockState leaf) {
        int leafRangeSq = leafRange * leafRange;
        for (int i1 = i - leafRange; i1 <= i + leafRange; i1++) {
            for (int k1 = k - leafRange; k1 <= k + leafRange; k1++) {
                int i2 = i1 - i;
                int k2 = k1 - k;
                BlockPos p = new BlockPos(i1, j, k1);
                if (i2 * i2 + k2 * k2 > leafRangeSq || !replaceable(world, p)) {
                    continue;
                }
                setBlock(world, p, leaf);
            }
        }
    }

    private static boolean isLeaves(BlockState state) {
        return state.getBlock() instanceof net.minecraft.block.LeavesBlock;
    }

    private static boolean replaceable(ISeedReader world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        return state.isAir(world, pos) || state.getMaterial().isReplaceable()
                || state.getBlock() instanceof net.minecraft.block.LeavesBlock;
    }

    private static BlockState log() {
        return state("lotr:cedar_log", Blocks.SPRUCE_LOG);
    }

    private static BlockState leaves() {
        return state("lotr:cedar_leaves", Blocks.SPRUCE_LEAVES);
    }

    private static BlockState state(String id, Block fallback) {
        Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(id));
        return (block == null || block == Blocks.AIR ? fallback : block).defaultBlockState();
    }
}
