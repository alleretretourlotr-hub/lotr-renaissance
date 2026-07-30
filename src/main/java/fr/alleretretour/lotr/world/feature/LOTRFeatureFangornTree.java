package fr.alleretretour.lotr.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.VineBlock;
import net.minecraft.state.properties.BlockStateProperties;
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
 * PORT de LOTRWorldGenFangornTrees : les arbres colossaux de Fangorn.
 *
 * Algorithme d'origine conserve tel quel :
 *   - un facteur f (0,5 a 1,0) commande TOUT l'arbre : hauteur = f x 40, rayon
 *     de tronc au sommet = f x 5, a la base = ce rayon + 4 ;
 *   - le tronc est CIRCULAIRE et s'affine en montant ; il s'incline d'une case
 *     tous les xSlope (4-10) niveaux en X, idem en Z, sens tire au hasard ;
 *   - a la base, des racines descendent sous chaque colonne jusqu'a 6-10 blocs
 *     de profondeur ou jusqu'a rencontrer un bloc opaque ;
 *   - RAMURES : tour complet par pas de 10 a 29 degres ; longueur 12-21,
 *     epaisseur (longueur / 25 x 1,5) diminuant de moitie vers la pointe,
 *     depart a 90-100 % de la hauteur, montee de 3 a 6 ;
 *   - de chaque segment de ramure part une branche (angle libre), longue de
 *     7-12, epaisse de 2 blocs en hauteur, terminee par une boule de feuilles
 *     de rayon 3 au bord effrite ;
 *   - LIANES : une chance sur 40 par face libre de chaque bloc de feuilles,
 *     retombant de 4 a 15 blocs.
 *
 * La variante MORTE (generateLeaves = false) ne pose aucune feuille.
 */
public class LOTRFeatureFangornTree extends Feature<NoFeatureConfig> {

    private final String species;
    private final boolean generateLeaves;
    private final Block logFallback;
    private final Block leafFallback;

    public LOTRFeatureFangornTree(Codec<NoFeatureConfig> codec, String species,
                                  boolean generateLeaves, Block logFallback,
                                  Block leafFallback) {
        super(codec);
        this.species = species;
        this.generateLeaves = generateLeaves;
        this.logFallback = logFallback;
        this.leafFallback = leafFallback;
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator generator, Random random,
                         BlockPos pos, NoFeatureConfig config) {
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        BlockPos soil = new BlockPos(i, j - 1, k);
        if (!world.getBlockState(soil).canSustainPlant(world, soil, Direction.UP,
                (net.minecraftforge.common.IPlantable) Blocks.OAK_SAPLING)) {
            return false;
        }
        float f = 0.5f + random.nextFloat() * 0.5f;          // PORT
        int height = (int) (f * 40.0f);
        int trunkRadiusMin = (int) (f * 5.0f);
        int trunkRadiusMax = trunkRadiusMin + 4;
        int xSlope = 4 + random.nextInt(7);
        if (random.nextBoolean()) {
            xSlope *= -1;
        }
        int zSlope = 4 + random.nextInt(7);
        if (random.nextBoolean()) {
            zSlope *= -1;
        }
        if (j < 1 || j + height + 5 > world.getHeight()) {
            return false;
        }
        for (int i1 = i - 1; i1 <= i + 1; i1++) {
            for (int k1 = k - 1; k1 <= k + 1; k1++) {
                for (int j1 = j; j1 <= j + height; j1++) {
                    for (int i2 = i1 - trunkRadiusMax; i2 <= i1 + trunkRadiusMax; i2++) {
                        for (int k2 = k1 - trunkRadiusMax; k2 <= k1 + trunkRadiusMax; k2++) {
                            if (!replaceable(world, new BlockPos(i2, j1, k2))) {
                                return false;
                            }
                        }
                    }
                }
            }
        }

        BlockState log = log();
        BlockState leaf = leaves();
        int centerX = i;
        int centerZ = k;

        for (int j1 = 0; j1 < height; j1++) {
            int width = trunkRadiusMax
                    - (int) ((float) j1 / height * (trunkRadiusMax - trunkRadiusMin));
            for (int i1 = centerX - width; i1 <= centerX + width; i1++) {
                for (int k1 = centerZ - width; k1 <= centerZ + width; k1++) {
                    int i2 = i1 - centerX;
                    int k2 = k1 - centerZ;
                    if (i2 * i2 + k2 * k2 >= width * width) {
                        continue;
                    }
                    BlockPos p = new BlockPos(i1, j + j1, k1);
                    BlockState state = world.getBlockState(p);
                    if (state.isAir(world, p) || isLeaves(state)) {
                        setBlock(world, p, log);
                    }
                    if (j1 != 0) {
                        continue;
                    }
                    // racines descendantes sous chaque colonne
                    int maxDepth = 6 + random.nextInt(5);
                    for (int j2 = j - 1; j2 >= 0 && Math.abs(j2 - j) <= maxDepth; j2--) {
                        BlockPos rp = new BlockPos(i1, j2, k1);
                        if (world.getBlockState(rp).isSolidRender(world, rp)) {
                            break;
                        }
                        setBlock(world, rp, log);
                    }
                }
            }
            if (j1 % xSlope == 0) {
                centerX += xSlope > 0 ? 1 : -1;
            }
            if (j1 % zSlope == 0) {
                centerZ += zSlope > 0 ? 1 : -1;
            }
        }

        int angle = 0;
        while (angle < 360) {
            angle += 10 + random.nextInt(20);
            float angleR = (float) Math.toRadians(angle);
            float sin = MathHelper.sin(angleR);
            float cos = MathHelper.cos(angleR);
            int boughLength = 12 + random.nextInt(10);
            int boughThickness = Math.round(boughLength / 25.0f * 1.5f);
            int boughBaseHeight = j + MathHelper.floor(height * (0.9f + random.nextFloat() * 0.1f));
            int boughHeight = 3 + random.nextInt(4);
            for (int l = 0; l < boughLength; l++) {
                int i1 = centerX + Math.round(sin * l);
                int k1 = centerZ + Math.round(cos * l);
                int j1 = boughBaseHeight + Math.round((float) l / boughLength * boughHeight);
                int range = boughThickness
                        - Math.round((float) l / boughLength * boughThickness * 0.5f);
                for (int i2 = i1 - range; i2 <= i1 + range; i2++) {
                    for (int j2 = j1 - range; j2 <= j1 + range; j2++) {
                        for (int k2 = k1 - range; k2 <= k1 + range; k2++) {
                            BlockPos p = new BlockPos(i2, j2, k2);
                            if (replaceable(world, p)) {
                                setBlock(world, p, sideways(log));
                            }
                        }
                    }
                }
                // branche partant de ce segment
                float branchAngleR = (float) Math.toRadians(angle + random.nextInt(360));
                float bSin = MathHelper.sin(branchAngleR);
                float bCos = MathHelper.cos(branchAngleR);
                int branchLength = 7 + random.nextInt(6);
                int branchHeight = random.nextInt(6);
                int leafRange = 3;
                for (int l1 = 0; l1 < branchLength; l1++) {
                    int i2 = i1 + Math.round(bSin * l1);
                    int k2 = k1 + Math.round(bCos * l1);
                    int j2 = j1 + Math.round((float) l1 / branchLength * branchHeight);
                    for (int j3 = j2; j3 >= j2 - 1; j3--) {
                        BlockPos p = new BlockPos(i2, j3, k2);
                        if (replaceable(world, p)) {
                            setBlock(world, p, sideways(log));
                        }
                    }
                    if (!generateLeaves || l1 != branchLength - 1) {
                        continue;
                    }
                    spawnLeafCluster(world, random, i2, j2, k2, leafRange, leaf);
                }
            }
        }
        return true;
    }

    /** PORT : boule de feuilles au bord effrite, avec lianes. */
    private void spawnLeafCluster(ISeedReader world, Random random, int i, int j, int k,
                                  int leafRange, BlockState leaf) {
        for (int i3 = i - leafRange; i3 <= i + leafRange; i3++) {
            for (int j3 = j - leafRange; j3 <= j + leafRange; j3++) {
                for (int k3 = k - leafRange; k3 <= k + leafRange; k3++) {
                    int i4 = i3 - i;
                    int j4 = j3 - j;
                    int k4 = k3 - k;
                    int dist = i4 * i4 + j4 * j4 + k4 * k4;
                    BlockPos p = new BlockPos(i3, j3, k3);
                    BlockState state = world.getBlockState(p);
                    if (dist >= (leafRange - 1) * (leafRange - 1)
                            && (dist >= leafRange * leafRange || random.nextInt(3) == 0)
                            || !state.isAir(world, p) && !isLeaves(state)) {
                        continue;
                    }
                    setBlock(world, p, leaf);
                    tryVines(world, random, new BlockPos(i3 - 1, j3, k3), Direction.EAST);
                    tryVines(world, random, new BlockPos(i3 + 1, j3, k3), Direction.WEST);
                    tryVines(world, random, new BlockPos(i3, j3, k3 - 1), Direction.SOUTH);
                    tryVines(world, random, new BlockPos(i3, j3, k3 + 1), Direction.NORTH);
                }
            }
        }
    }

    /** PORT de growVines : liane longue de 4 a 15 blocs. */
    private void tryVines(ISeedReader world, Random random, BlockPos pos, Direction face) {
        if (random.nextInt(40) != 0 || !world.isEmptyBlock(pos)) {
            return;
        }
        BlockState vine = Blocks.VINE.defaultBlockState()
                .setValue(VineBlock.getPropertyForFace(face), Boolean.TRUE);
        setBlock(world, pos, vine);
        int length = 4 + random.nextInt(12);
        BlockPos below = pos.below();
        while (length > 0 && world.isEmptyBlock(below)) {
            setBlock(world, below, vine);
            below = below.below();
            length--;
        }
    }

    /** PORT du meta | 0xC : bois pose en travers. */
    private static BlockState sideways(BlockState log) {
        return log.hasProperty(BlockStateProperties.AXIS)
                ? log.setValue(BlockStateProperties.AXIS, Direction.Axis.X) : log;
    }

    private static boolean isLeaves(BlockState state) {
        return state.getBlock() instanceof net.minecraft.block.LeavesBlock;
    }

    private static boolean replaceable(ISeedReader world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        return state.isAir(world, pos) || state.getMaterial().isReplaceable()
                || state.getBlock() instanceof net.minecraft.block.LeavesBlock;
    }

    private BlockState log() {
        return state("lotr:" + species + "_log", logFallback);
    }

    private BlockState leaves() {
        return state("lotr:" + species + "_leaves", leafFallback);
    }

    private static BlockState state(String id, Block fallback) {
        Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(id));
        return (block == null || block == Blocks.AIR ? fallback : block).defaultBlockState();
    }
}
