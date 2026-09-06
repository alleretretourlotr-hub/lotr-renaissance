package fr.alleretretour.lotr.world.structure;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

/**
 * PORT de LOTRWorldGenGondorObelisk : l'obelisque du Gondor.
 *
 * Structure d'origine reproduite bloc par bloc :
 *   - l'aire 7x7 doit etre entierement en surface naturelle ;
 *   - fondation : chaque colonne descend de la hauteur 3 jusqu'au sol dur,
 *     en briques (l'herbe dessous devient de la terre) ;
 *   - socle 5x5 de roche du Gondor entre 4 et 8, borde d'une couronne
 *     d'escaliers a la hauteur 4 ;
 *   - fut 3x3 de briques entre 9 et 14, seconde couronne d'escaliers a 9 ;
 *   - fleche en croix entre 15 et 18, couronnee de quatre escaliers ;
 *   - au sommet, le FEU D'ALARME du Gondor.
 *
 * Briques et escaliers : une pose sur QUATRE est moussue ou fissuree
 * (tirage a pile ou face entre les deux), le reste est lisse.
 */
public class LOTRStructureGondorObelisk extends LOTRStructureBase {

    public LOTRStructureGondorObelisk(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    protected boolean generateWithRotation(ISeedReader world, Random random, BlockPos origin,
                                           int rotation) {
        BlockPos base = origin.below();

        // terrain : 7x7 en surface naturelle
        for (int i = -3; i <= 3; i++) {
            for (int k = -3; k <= 3; k++) {
                int top = getTopBlockLocal(world, base, rotation, i, k) - 1;
                if (!isSurfaceLocal(world, base, rotation, i, top, k)) {
                    return false;
                }
            }
        }
        // fondation jusqu'au sol dur
        for (int i = -3; i <= 3; i++) {
            for (int k = -3; k <= 3; k++) {
                for (int j = 3; j >= 0 || !isOpaqueLocal(world, base, rotation, i, j, k); j--) {
                    if (base.getY() + j < 0) {
                        break;
                    }
                    brick(world, base, rotation, random, i, j, k);
                    setGrassToDirt(world, rotate(base, rotation, i, j - 1, k));
                }
            }
        }
        // socle 5x5 en roche du Gondor
        BlockState rock = modBlock("lotr:gondor_rock", Blocks.STONE);
        for (int i = -2; i <= 2; i++) {
            for (int k = -2; k <= 2; k++) {
                for (int j = 4; j <= 8; j++) {
                    setBlockRotated(world, base, rotation, i, j, k, rock);
                }
            }
        }
        for (int i = -3; i <= 3; i++) {
            stairs(world, base, rotation, random, i, 4, -3, 2);
            stairs(world, base, rotation, random, i, 4, 3, 3);
        }
        for (int k = -2; k <= 2; k++) {
            stairs(world, base, rotation, random, -3, 4, k, 1);
            stairs(world, base, rotation, random, 3, 4, k, 0);
        }
        // fut 3x3
        for (int i = -1; i <= 1; i++) {
            for (int k = -1; k <= 1; k++) {
                for (int j = 9; j <= 14; j++) {
                    brick(world, base, rotation, random, i, j, k);
                }
            }
        }
        for (int i = -2; i <= 2; i++) {
            stairs(world, base, rotation, random, i, 9, -2, 2);
            stairs(world, base, rotation, random, i, 9, 2, 3);
        }
        for (int k = -1; k <= 1; k++) {
            stairs(world, base, rotation, random, -2, 9, k, 1);
            stairs(world, base, rotation, random, 2, 9, k, 0);
        }
        // fleche en croix
        for (int j = 15; j <= 18; j++) {
            brick(world, base, rotation, random, 0, j, 0);
            brick(world, base, rotation, random, -1, j, 0);
            brick(world, base, rotation, random, 1, j, 0);
            brick(world, base, rotation, random, 0, j, -1);
            brick(world, base, rotation, random, 0, j, 1);
        }
        stairs(world, base, rotation, random, -1, 19, 0, 1);
        stairs(world, base, rotation, random, 1, 19, 0, 0);
        stairs(world, base, rotation, random, 0, 19, -1, 2);
        stairs(world, base, rotation, random, 0, 19, 1, 3);
        brick(world, base, rotation, random, 0, 19, 0);
        setBlockRotated(world, base, rotation, 0, 20, 0,
                modBlock("lotr:beacon", Blocks.CAMPFIRE));
        return true;
    }

    /** PORT : une brique sur quatre est moussue ou fissuree. */
    private void brick(ISeedReader world, BlockPos base, int rotation, Random random,
                       int i, int j, int k) {
        BlockState state;
        if (random.nextInt(4) == 0) {
            state = random.nextBoolean()
                    ? modBlock("lotr:gondor_mossy_brick", Blocks.MOSSY_STONE_BRICKS)
                    : modBlock("lotr:gondor_cracked_brick", Blocks.CRACKED_STONE_BRICKS);
        } else {
            state = modBlock("lotr:gondor_brick", Blocks.STONE_BRICKS);
        }
        setBlockRotated(world, base, rotation, i, j, k, state);
    }

    /** PORT : idem pour les escaliers, le meta donnant l'orientation. */
    private void stairs(ISeedReader world, BlockPos base, int rotation, Random random,
                        int i, int j, int k, int meta) {
        BlockState state;
        if (random.nextInt(4) == 0) {
            state = random.nextBoolean()
                    ? modBlock("lotr:gondor_mossy_brick_stairs", Blocks.MOSSY_STONE_BRICK_STAIRS)
                    : modBlock("lotr:gondor_cracked_brick_stairs", Blocks.STONE_BRICK_STAIRS);
        } else {
            state = modBlock("lotr:gondor_brick_stairs", Blocks.STONE_BRICK_STAIRS);
        }
        setBlockRotated(world, base, rotation, i, j, k, withMeta(state, meta, rotation));
    }
}
