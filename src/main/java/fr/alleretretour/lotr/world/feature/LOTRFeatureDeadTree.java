package fr.alleretretour.lotr.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

/**
 * PORT de LOTRWorldGenDeadTrees : les arbres morts (variantes _DEAD).
 *
 * Algorithme d'origine conserve tel quel :
 *   - pousse sur tout sol a pousse, ainsi que sur la pierre, le sable ou le
 *     gravier ;
 *   - tronc nu de 3 a 6 blocs, aucune feuille ;
 *   - quatre branches, une par direction cardinale, longues de 3 a 7, partant
 *     du sommet moins 0 ou 1 : elles avancent horizontalement UNE FOIS SUR
 *     QUATRE et montent DEUX FOIS SUR TROIS, en bois pose en travers
 *     (meta | 0xC du Legacy -> axe horizontal en 1.16.5).
 *
 * L'essence est parametrable : chene, hetre, bouleau, epicea, chene noir...
 */
public class LOTRFeatureDeadTree extends Feature<NoFeatureConfig> {

    private final String species;
    private final Block vanillaFallback;

    public LOTRFeatureDeadTree(Codec<NoFeatureConfig> codec, String species,
                               Block vanillaFallback) {
        super(codec);
        this.species = species;
        this.vanillaFallback = vanillaFallback;
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator generator, Random random,
                         BlockPos pos, NoFeatureConfig config) {
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        BlockPos soil = new BlockPos(i, j - 1, k);
        BlockState below = world.getBlockState(soil);
        Block belowBlock = below.getBlock();
        boolean ok = below.canSustainPlant(world, soil, Direction.UP,
                (net.minecraftforge.common.IPlantable) Blocks.OAK_SAPLING)
                || belowBlock == Blocks.STONE || belowBlock == Blocks.SAND
                || belowBlock == Blocks.GRAVEL;
        if (!ok) {
            return false;
        }
        int height = 3 + random.nextInt(4);          // PORT
        if (j < 1 || j + height + 6 > world.getHeight()) {
            return false;
        }
        BlockState log = log();
        for (int j1 = j; j1 < j + height; j1++) {
            setBlock(world, new BlockPos(i, j1, k), log);
        }
        for (int branch = 0; branch < 4; branch++) {
            int branchLength = 3 + random.nextInt(5);            // PORT
            int horizontal = 0;
            int vertical = j + height - 1 - random.nextInt(2);
            for (int l = 0; l < branchLength; l++) {
                if (random.nextInt(4) == 0) {                    // PORT
                    horizontal++;
                }
                if (random.nextInt(3) != 0) {                    // PORT
                    vertical++;
                }
                BlockPos p;
                Direction.Axis axis;
                switch (branch) {
                    case 0:
                        p = new BlockPos(i - horizontal, vertical, k);
                        axis = Direction.Axis.X;
                        break;
                    case 1:
                        p = new BlockPos(i, vertical, k + horizontal);
                        axis = Direction.Axis.Z;
                        break;
                    case 2:
                        p = new BlockPos(i + horizontal, vertical, k);
                        axis = Direction.Axis.X;
                        break;
                    default:
                        p = new BlockPos(i, vertical, k - horizontal);
                        axis = Direction.Axis.Z;
                        break;
                }
                setBlock(world, p, log.hasProperty(BlockStateProperties.AXIS)
                        ? log.setValue(BlockStateProperties.AXIS, axis) : log);
            }
        }
        return true;
    }

    private BlockState log() {
        Block block = ForgeRegistries.BLOCKS.getValue(
                new ResourceLocation("lotr:" + species + "_log"));
        if (block == null || block == Blocks.AIR) {
            block = vanillaFallback;
        }
        return block.defaultBlockState();
    }
}
