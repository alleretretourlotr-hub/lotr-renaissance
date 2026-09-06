package fr.alleretretour.lotr.world.structure;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

/**
 * PORT de LOTRWorldGenRuinedGondorTower : la tour du Gondor abandonnee.
 *
 * Ecrite ici en coordonnees LOCALES (le socle applique la rotation), la ou le
 * Legacy repete quatre fois le meme trace.
 *
 * Structure d'origine :
 *   - emprise 7x7 sur l'herbe ; les murs descendent jusqu'au sol dur et
 *     montent sur 8 blocs, l'interieur etant evide ;
 *   - UNE BRIQUE SUR SEIZE MANQUE, et une sur quatre est fissuree ou moussue :
 *     c'est ce qui creuse les breches de la tour ;
 *   - un plancher de planches au niveau 5, troue une fois sur vingt ;
 *   - au rez-de-chaussee, un coffre de lebethron (une chance sur trois d'etre
 *     garni) et une table gondorienne ;
 *   - a l'etage, deux fois sur trois un lit de paille, une fois sur deux une
 *     enclume, et un comptoir de dalles ou trainent chope, assiette et
 *     tonneau - le logis abandonne d'une garnison ;
 *   - couronnement 9x9 au niveau 9, avec des creneaux poses un bloc sur deux ;
 *   - une echelle monte du sol au sommet, cote entree ;
 *   - de part et d'autre du seuil, un chemin de murets s'eloigne sur quatre
 *     blocs, chaque muret ayant une chance sur quatre de manquer.
 */
public class LOTRStructureRuinedGondorTower extends LOTRStructureBase {

    public LOTRStructureRuinedGondorTower(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    protected boolean generateWithRotation(ISeedReader world, Random random, BlockPos origin,
                                           int rotation) {
        if (world.getBlockState(origin.below()).getBlock() != Blocks.GRASS_BLOCK) {
            return false;
        }
        BlockPos base = origin.below();
        // PORT : la tour est decalee de 4 vers l'avant
        base = rotate(base, rotation, 0, 0, 4);

        // le sol de la facade doit etre herbeux (ou sous les arbres)
        for (int i = -3; i <= 3; i++) {
            BlockPos p = rotate(base, rotation, i, 0, 3);
            int y = world.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, p.getX(), p.getZ());
            Block b = world.getBlockState(new BlockPos(p.getX(), y - 1, p.getZ())).getBlock();
            if (b != Blocks.GRASS_BLOCK && !(b instanceof net.minecraft.block.LeavesBlock)
                    && !(b instanceof net.minecraft.block.RotatedPillarBlock)) {
                return false;
            }
        }

        BlockState air = Blocks.AIR.defaultBlockState();
        // murs et interieur
        for (int i = -3; i <= 3; i++) {
            for (int k = -3; k <= 3; k++) {
                boolean wall = Math.abs(i) == 3 || Math.abs(k) == 3;
                if (wall) {
                    for (int j = 8; (j >= 0 || !isOpaqueLocal(world, base, rotation, i, j, k))
                            && base.getY() + j >= 0; j--) {
                        brick(world, base, rotation, random, i, j, k);
                        setGrassToDirt(world, rotate(base, rotation, i, j - 1, k));
                    }
                } else {
                    for (int j = 0; !isOpaqueLocal(world, base, rotation, i, j, k)
                            && base.getY() + j >= 0; j--) {
                        brick(world, base, rotation, random, i, j, k);
                        setGrassToDirt(world, rotate(base, rotation, i, j - 1, k));
                    }
                    for (int j = 1; j <= 8; j++) {
                        setBlockRotated(world, base, rotation, i, j, k, air);
                    }
                }
                // plancher de l'etage, troue une fois sur vingt
                if (Math.abs(i) >= 3 || Math.abs(k) >= 3 || random.nextInt(20) == 0) {
                    continue;
                }
                // le Legacy pose ici des planches de chene
                setBlockRotated(world, base, rotation, i, 5, k,
                        Blocks.OAK_PLANKS.defaultBlockState());
            }
        }

        // rez-de-chaussee : coffre et table
        BlockPos chest = rotate(base, rotation, -2, 1, -2);
        world.setBlock(chest, Blocks.CHEST.defaultBlockState(), 2);
        if (random.nextInt(3) == 0) {   // PORT : une chance sur trois d'etre garni
            setLootTable(world, chest, "gondor_fortress_supplies", random);
        }
        // TODO : la table gondorienne n'est pas portee (les autres factions le sont)
        setBlockRotated(world, base, rotation, 2, 1, -2,
                Blocks.CRAFTING_TABLE.defaultBlockState());

        // etage : le logis
        // TODO : le lit de paille du mod n'est pas porte
        if (random.nextInt(3) != 0) {
            setBlockRotated(world, base, rotation, 2, 6, -2,
                    Blocks.RED_BED.defaultBlockState());
        }
        if (random.nextBoolean()) {
            setBlockRotated(world, base, rotation, 2, 6, 2, Blocks.ANVIL.defaultBlockState());
        }
        for (int k = 0; k <= 2; k++) {
            setBlockRotated(world, base, rotation, -2, 6, k,
                    modBlock("lotr:gondor_brick_slab", Blocks.STONE_BRICK_SLAB));
        }
        // TODO : chope et assiette du mod non portees ; seul le tonneau l'est
        if (random.nextBoolean()) {
            setBlockRotated(world, base, rotation, -2, 7, 2,
                    modBlock("lotr:barrel", Blocks.BARREL));
        }

        // couronnement 9x9 et creneaux
        for (int i = -4; i <= 4; i++) {
            for (int k = -4; k <= 4; k++) {
                brick(world, base, rotation, random, i, 9, k);
                boolean merlon = (Math.abs(i) == 4 && Math.abs(k) % 2 == 0)
                        || (Math.abs(k) == 4 && Math.abs(i) % 2 == 0);
                if (!merlon || !isOpaqueLocal(world, base, rotation, i, 9, k)) {
                    continue;
                }
                brick(world, base, rotation, random, i, 10, k);
            }
        }

        // echelle cote entree
        BlockState ladder = Blocks.LADDER.defaultBlockState();
        if (ladder.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
            Direction d = Direction.NORTH;
            for (int r = 0; r < rotation; r++) {
                d = d.getClockWise();
            }
            ladder = ladder.setValue(BlockStateProperties.HORIZONTAL_FACING, d);
        }
        for (int j = 1; j <= 9; j++) {
            if (!isOpaqueLocal(world, base, rotation, 0, j, 3)) {
                break;
            }
            setBlockRotated(world, base, rotation, 0, j, 2, ladder);
        }

        // seuil et chemin de murets
        BlockState brick = modBlock("lotr:gondor_brick", Blocks.STONE_BRICKS);
        for (int j = 1; j <= 2; j++) {
            setBlockRotated(world, base, rotation, -1, j, 3, brick);
            setBlockRotated(world, base, rotation, 0, j, 3, air);
            setBlockRotated(world, base, rotation, 1, j, 3, brick);
        }
        for (int k = 4; k <= 7; k++) {
            for (int i = -2; i <= 2; i += 4) {
                BlockPos p = rotate(base, rotation, i, 0, k);
                int y = world.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, p.getX(), p.getZ());
                if (world.getBlockState(new BlockPos(p.getX(), y - 1, p.getZ())).getBlock()
                        != Blocks.GRASS_BLOCK || random.nextInt(4) == 0) {
                    continue;   // PORT : un muret sur quatre manque
                }
                world.setBlock(new BlockPos(p.getX(), y, p.getZ()),
                        modBlock("lotr:gondor_brick_wall", Blocks.STONE_BRICK_WALL), 2);
            }
        }
        return true;
    }

    /**
     * PORT de placeRandomBrick : une brique sur SEIZE manque (les breches),
     * une sur quatre est fissuree ou moussue.
     */
    private void brick(ISeedReader world, BlockPos base, int rotation, Random random,
                       int i, int j, int k) {
        if (random.nextInt(16) == 0) {
            return;
        }
        BlockState state;
        if (random.nextInt(4) == 0) {
            state = random.nextBoolean()
                    ? modBlock("lotr:gondor_cracked_brick", Blocks.CRACKED_STONE_BRICKS)
                    : modBlock("lotr:gondor_mossy_brick", Blocks.MOSSY_STONE_BRICKS);
        } else {
            state = modBlock("lotr:gondor_brick", Blocks.STONE_BRICKS);
        }
        setBlockRotated(world, base, rotation, i, j, k, state);
    }
}
