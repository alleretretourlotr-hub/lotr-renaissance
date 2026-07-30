package fr.alleretretour.lotr.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

/**
 * PORT de LOTRWorldGenLarch : le meleze.
 *
 * Algorithme d'origine, conserve tel quel :
 *   - hauteur = 8 + rand(9) ;
 *   - base de tronc nu = 2 + rand(2), largeur maximale de cime = 2 + rand(2) ;
 *   - le feuillage descend depuis le sommet ; le rayon part de rand(2) puis
 *     grandit par paliers (maxLeafRange +1 a chaque retour a minLeafRange,
 *     plafonne a leafWidth), les 4 coins de chaque couche etant omis ;
 *   - le tronc s'arrete rand(3) blocs sous le sommet.
 */
public class LOTRFeatureLarch extends Feature<NoFeatureConfig> {

    public LOTRFeatureLarch(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator generator, Random random,
                         BlockPos pos, NoFeatureConfig config) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        int height = random.nextInt(9) + 8;               // PORT
        int trunkBaseHeight = 2 + random.nextInt(2);      // PORT
        int leafStart = height - trunkBaseHeight;
        int leafWidth = 2 + random.nextInt(2);            // PORT

        if (y < 1 || y + height + 1 > world.getHeight()) {
            return false;
        }
        for (int j1 = y; j1 <= y + 1 + height; j1++) {
            int range = (j1 - y < trunkBaseHeight) ? 0 : leafWidth;
            for (int i1 = x - range; i1 <= x + range; i1++) {
                for (int k1 = z - range; k1 <= z + range; k1++) {
                    BlockPos p = new BlockPos(i1, j1, k1);
                    BlockState state = world.getBlockState(p);
                    if (!state.isAir(world, p) && !isLeaves(state)
                            && !state.getMaterial().isReplaceable()) {
                        return false;
                    }
                }
            }
        }
        BlockPos soilPos = new BlockPos(x, y - 1, z);
        if (!world.getBlockState(soilPos).canSustainPlant(world, soilPos,
                net.minecraft.util.Direction.UP,
                (net.minecraftforge.common.IPlantable) Blocks.SPRUCE_SAPLING)) {
            return false;
        }

        BlockState leaf = leaves();
        int leafRange = random.nextInt(2);   // PORT
        int maxLeafRange = 1;
        int minLeafRange = 0;
        for (int leafLayer = 0; leafLayer <= leafStart; leafLayer++) {
            int j1 = y + height - leafLayer;
            for (int i1 = x - leafRange; i1 <= x + leafRange; i1++) {
                int i2 = i1 - x;
                for (int k1 = z - leafRange; k1 <= z + leafRange; k1++) {
                    int k2 = k1 - z;
                    // PORT : les quatre coins de la couche sont omis
                    if (Math.abs(i2) == leafRange && Math.abs(k2) == leafRange && leafRange > 0) {
                        continue;
                    }
                    BlockPos p = new BlockPos(i1, j1, k1);
                    BlockState state = world.getBlockState(p);
                    if (!state.getMaterial().isReplaceable() && !isLeaves(state)) {
                        continue;
                    }
                    setBlock(world, p, leaf);
                }
            }
            // PORT exact de la progression du rayon
            if (leafRange >= maxLeafRange) {
                leafRange = minLeafRange;
                minLeafRange = 1;
                maxLeafRange++;
                if (maxLeafRange > leafWidth) {
                    maxLeafRange = leafWidth;
                }
            } else {
                leafRange++;
            }
        }

        BlockState log = log();
        int trunkTop = random.nextInt(3);    // PORT
        for (int j1 = 0; j1 < height - trunkTop; j1++) {
            BlockPos p = new BlockPos(x, y + j1, z);
            BlockState state = world.getBlockState(p);
            if (!state.isAir(world, p) && !isLeaves(state)
                    && !state.getMaterial().isReplaceable()) {
                continue;
            }
            setBlock(world, p, log);
        }
        return true;
    }

    private static boolean isLeaves(BlockState state) {
        return state.getBlock() instanceof net.minecraft.block.LeavesBlock;
    }

    private static BlockState log() {
        return state("lotr:larch_log", Blocks.SPRUCE_LOG);
    }

    private static BlockState leaves() {
        return state("lotr:larch_leaves", Blocks.SPRUCE_LEAVES);
    }

    private static BlockState state(String id, Block fallback) {
        Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(id));
        return (block == null || block == Blocks.AIR ? fallback : block).defaultBlockState();
    }
}
