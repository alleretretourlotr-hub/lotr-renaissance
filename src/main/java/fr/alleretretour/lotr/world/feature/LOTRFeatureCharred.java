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
 * PORT de LOTRWorldGenCharredTrees : les arbres calcines du Mordor.
 *
 * Algorithme d'origine conserve tel quel :
 *   - pousse sur les sols du Mordor, la pierre, le sable, le gravier ou tout
 *     sol a pousse (le seul arbre qui accepte la roche nue) ;
 *   - tronc nu de 2 a 6 blocs, sans la moindre feuille ;
 *   - si la hauteur atteint 4, quatre branches (une par direction cardinale) :
 *     longueur 2-5, avancant horizontalement UNE FOIS SUR QUATRE et montant
 *     DEUX FOIS SUR TROIS - d'ou ces moignons tordus qui pointent vers le ciel.
 *     Les branches utilisent le bois "en travers" (meta 15 du Legacy -> axe
 *     horizontal en 1.16.5).
 */
public class LOTRFeatureCharred extends Feature<NoFeatureConfig> {

    public LOTRFeatureCharred(Codec<NoFeatureConfig> codec) {
        super(codec);
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
        boolean ok = belowBlock == Blocks.STONE || belowBlock == Blocks.SAND
                || belowBlock == Blocks.GRAVEL
                || belowBlock == blockOrNull("lotr:mordor_dirt")
                || belowBlock == blockOrNull("lotr:mordor_gravel")
                || belowBlock == blockOrNull("lotr:mordor_rock")
                || belowBlock == blockOrNull("lotr:gondor_rock")
                || belowBlock == blockOrNull("lotr:rohan_rock")
                || belowBlock == blockOrNull("lotr:blue_rock")
                || belowBlock == blockOrNull("lotr:red_rock")
                || belowBlock == blockOrNull("lotr:chalk_rock")
                || below.canSustainPlant(world, soil, Direction.UP,
                        (net.minecraftforge.common.IPlantable) Blocks.OAK_SAPLING);
        if (!ok) {
            return false;
        }
        int height = 2 + random.nextInt(5);          // PORT
        if (j < 1 || j + height + 6 > world.getHeight()) {
            return false;
        }
        BlockState log = log();
        for (int j1 = j; j1 < j + height; j1++) {
            setBlock(world, new BlockPos(i, j1, k), log);
        }
        if (height < 4) {
            return true;
        }
        for (int branch = 0; branch < 4; branch++) {
            int branchLength = 2 + random.nextInt(4);            // PORT
            int horizontal = 0;
            int vertical = j + height - random.nextInt(2);
            for (int l = 0; l < branchLength; l++) {
                if (random.nextInt(4) == 0) {                    // PORT : 1 fois sur 4
                    horizontal++;
                }
                if (random.nextInt(3) != 0) {                    // PORT : 2 fois sur 3
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
                setBlock(world, p, branchLog(log, axis));
            }
        }
        return true;
    }

    /** PORT du meta 15 : bois pose en travers. */
    private static BlockState branchLog(BlockState log, Direction.Axis axis) {
        return log.hasProperty(BlockStateProperties.AXIS)
                ? log.setValue(BlockStateProperties.AXIS, axis) : log;
    }

    private static Block blockOrNull(String id) {
        return ForgeRegistries.BLOCKS.getValue(new ResourceLocation(id));
    }

    private static BlockState log() {
        Block block = blockOrNull("lotr:charred_log");
        return (block == null || block == Blocks.AIR ? Blocks.DARK_OAK_LOG : block)
                .defaultBlockState();
    }
}
