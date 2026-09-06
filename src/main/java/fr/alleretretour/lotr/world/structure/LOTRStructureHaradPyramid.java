package fr.alleretretour.lotr.world.structure;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

/**
 * PORT de LOTRWorldGenHaradPyramid : la pyramide du Proche-Harad.
 *
 * Le trace vient du scan "harad_pyramid" ; seuls les ALIAS sont declares ici,
 * avec les ponderations exactes du Legacy (addBlockAliasOption) :
 *
 *   BRICK        3 lisse / 1 fissuree
 *   BRICK_MAYBE  4 air / 3 lisse / 1 fissuree  -> les breches du temps
 *   BRICK2       3 rouge / 1 rouge fissuree
 *   TUNNEL       5 sable / 5 air               -> galeries a demi ensablees
 *   ROOF         4 sable / 4 gres rouge / 2+2 briques rouges
 *
 * Ce tirage a chaque pose est ce qui donne son aspect erode et jamais
 * identique d'une pyramide a l'autre.
 *
 * Terrain : rayon 27, sur surface naturelle, pierre ou gres ; la pyramide
 * s'enfonce ou se souleve de -2 a +4 blocs.
 *
 * Les coffres a butin (placePyramidChest) et les pieges attendent le portage
 * des TileEntity du Legacy : la pyramide est batie, ses tresors viendront.
 */
public class LOTRStructureHaradPyramid extends LOTRStructureScanned {

    private static final int RADIUS = 27;

    public LOTRStructureHaradPyramid(Codec<NoFeatureConfig> codec) {
        super(codec, "harad_pyramid");

        aliasOption("BRICK", 3, "lotr:near_harad_brick", Blocks.SANDSTONE);
        aliasOption("BRICK", 1, "lotr:near_harad_cracked_brick", Blocks.SANDSTONE);

        aliasOption("BRICK_MAYBE", 4, Blocks.AIR.defaultBlockState());
        aliasOption("BRICK_MAYBE", 3, "lotr:near_harad_brick", Blocks.SANDSTONE);
        aliasOption("BRICK_MAYBE", 1, "lotr:near_harad_cracked_brick", Blocks.SANDSTONE);

        // la dalle lisse du Proche-Harad n'est pas portee : le Legacy utilise ici
        // slabSingle4 meta 0 ; on garde la variante fissuree, seule disponible
        aliasOption("BRICK_SLAB", 3, "lotr:near_harad_cracked_brick_slab", Blocks.SANDSTONE_SLAB);
        aliasOption("BRICK_SLAB", 1, "lotr:near_harad_cracked_brick_slab", Blocks.SANDSTONE_SLAB);
        aliasOption("BRICK_STAIR", 3, "lotr:near_harad_brick_stairs", Blocks.SANDSTONE_STAIRS);
        aliasOption("BRICK_STAIR", 1, "lotr:near_harad_cracked_brick_stairs",
                Blocks.SANDSTONE_STAIRS);
        aliasOption("BRICK_WALL", 3, "lotr:near_harad_brick_wall", Blocks.SANDSTONE_WALL);
        aliasOption("BRICK_WALL", 1, "lotr:near_harad_cracked_brick_wall", Blocks.SANDSTONE_WALL);

        aliasOption("PILLAR", 4, "lotr:near_harad_pillar", Blocks.SANDSTONE);
        aliasOption("PILLAR_SLAB", 4, "lotr:near_harad_pillar_slab", Blocks.SANDSTONE_SLAB);

        aliasOption("BRICK2", 3, "lotr:near_harad_red_brick", Blocks.RED_SANDSTONE);
        aliasOption("BRICK2", 1, "lotr:near_harad_red_cracked_brick", Blocks.RED_SANDSTONE);
        aliasOption("BRICK2_SLAB", 3, "lotr:near_harad_red_brick_slab", Blocks.RED_SANDSTONE_SLAB);
        aliasOption("BRICK2_SLAB", 1, "lotr:near_harad_red_cracked_brick_slab",
                Blocks.RED_SANDSTONE_SLAB);
        aliasOption("BRICK2_STAIR", 3, "lotr:near_harad_red_brick_stairs",
                Blocks.RED_SANDSTONE_STAIRS);
        aliasOption("BRICK2_STAIR", 1, "lotr:near_harad_red_cracked_brick_stairs",
                Blocks.RED_SANDSTONE_STAIRS);

        // galeries a demi ensablees
        aliasOption("TUNNEL", 5, Blocks.SAND.defaultBlockState());
        aliasOption("TUNNEL", 5, Blocks.AIR.defaultBlockState());

        aliasOption("ROOF", 4, Blocks.SAND.defaultBlockState());
        aliasOption("ROOF", 4, Blocks.RED_SANDSTONE.defaultBlockState());
        aliasOption("ROOF", 2, "lotr:near_harad_red_brick", Blocks.RED_SANDSTONE);
        aliasOption("ROOF", 2, "lotr:near_harad_red_cracked_brick", Blocks.RED_SANDSTONE);
    }

    @Override
    protected boolean canPlace(ISeedReader world, Random random, BlockPos base, int rotation) {
        for (int i = -RADIUS; i <= RADIUS; i++) {
            for (int k = -RADIUS; k <= RADIUS; k++) {
                int y = groundAt(world, base, rotation, i, k) - 1 - base.getY();
                if (isSurfaceLocal(world, base, rotation, i, y, k)) {
                    continue;
                }
                Block b = world.getBlockState(rotate(base, rotation, i, y, k)).getBlock();
                if (b == Blocks.STONE || b == Blocks.SANDSTONE || b == Blocks.RED_SANDSTONE) {
                    continue;
                }
                return false;
            }
        }
        yOffset = MathHelper.nextInt(random, -2, 4);   // PORT
        return true;
    }
}
