package fr.alleretretour.lotr.world.structure;

import com.mojang.serialization.Codec;
import fr.alleretretour.lotr.world.structure.scan.LOTRStructureScan;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Socle des structures decrites par un fichier .strscan (PORT du couple
 * loadStrScan / generateStrScan du Legacy).
 *
 * Une sous-classe se contente de nommer son scan et d'associer ses alias :
 *
 *     public LOTRStructureMumakSkeleton(Codec<NoFeatureConfig> codec) {
 *         super(codec, "mumak_skeleton");
 *         alias("BONE", "lotr:bone_block", Blocks.BONE_BLOCK);
 *     }
 *
 * Les suffixes du format sont respectes :
 *   'v' -> la colonne est remplie vers le bas jusqu'au sol dur ;
 *   '_' -> le bloc descend jusqu'au premier bloc solide.
 */
public abstract class LOTRStructureScanned extends LOTRStructureBase {

    private final String scanName;
    private final Map<String, BlockState> aliases = new HashMap<>();
    /** alias a PLUSIEURS blocs possibles, tires au poids (PORT de addBlockAliasOption) */
    private final Map<String, List<WeightedState>> weighted = new HashMap<>();

    /** Un choix pondere pour un alias. */
    private static final class WeightedState {
        final int weight;
        final BlockState state;

        WeightedState(int weight, BlockState state) {
            this.weight = weight;
            this.state = state;
        }
    }
    /** decalage vertical applique avant la pose (PORT de originY). */
    protected int yOffset;

    protected LOTRStructureScanned(Codec<NoFeatureConfig> codec, String scanName) {
        super(codec);
        this.scanName = scanName;
    }

    /** Associe un alias du scan a un bloc du mod, avec repli vanilla. */
    protected void alias(String name, String modId, net.minecraft.block.Block fallback) {
        aliases.put(name, modBlock(modId, fallback));
    }

    /** Associe un alias directement a un etat de bloc. */
    protected void alias(String name, BlockState state) {
        aliases.put(name, state);
    }

    /**
     * PORT de addBlockAliasOption : ajoute un choix pondere pour un alias.
     * Le Legacy tire au sort parmi les options a chaque pose, ce qui donne
     * les briques melangees des pyramides ou les toitures irregulieres.
     */
    protected void aliasOption(String name, int weight, String modId,
                               net.minecraft.block.Block fallback) {
        weighted.computeIfAbsent(name, n -> new ArrayList<>())
                .add(new WeightedState(weight, modBlock(modId, fallback)));
    }

    /** Idem, pour un etat de bloc direct (l'air est une option valide). */
    protected void aliasOption(String name, int weight, BlockState state) {
        weighted.computeIfAbsent(name, n -> new ArrayList<>())
                .add(new WeightedState(weight, state));
    }

    /** Tire un bloc parmi les options d'un alias pondere. */
    private BlockState pick(String name, Random random) {
        List<WeightedState> options = weighted.get(name);
        if (options == null || options.isEmpty()) {
            return null;
        }
        int total = 0;
        for (WeightedState w : options) {
            total += w.weight;
        }
        int roll = random.nextInt(Math.max(1, total));
        for (WeightedState w : options) {
            roll -= w.weight;
            if (roll < 0) {
                return w.state;
            }
        }
        return options.get(0).state;
    }

    /**
     * Verification de terrain propre a la structure ; par defaut aucune.
     * Renvoyer false annule la generation.
     */
    protected boolean canPlace(ISeedReader world, Random random, BlockPos base, int rotation) {
        return true;
    }

    @Override
    protected boolean generateWithRotation(ISeedReader world, Random random, BlockPos origin,
                                           int rotation) {
        BlockPos base = origin.offset(0, yOffset, 0);
        if (!canPlace(world, random, base, rotation)) {
            return false;
        }
        LOTRStructureScan scan = LOTRStructureScan.get(scanName);
        if (scan == null) {
            return false;
        }
        for (LOTRStructureScan.Step step : scan.steps) {
            BlockState state = step.direct != null
                    ? step.direct
                    : aliases.getOrDefault(step.alias, pick(step.alias, random));
            if (state == null) {
                continue;   // alias non associe : rien n'est pose (jamais devine)
            }
            if (step.findLowest) {
                // PORT du suffixe '_' : descendre jusqu'au premier bloc solide
                BlockPos p = rotate(base, rotation, step.x, step.y, step.z);
                int y = p.getY();
                while (y > 1 && !world.getBlockState(new BlockPos(p.getX(), y - 1, p.getZ()))
                        .isSolidRender(world, new BlockPos(p.getX(), y - 1, p.getZ()))) {
                    y--;
                }
                world.setBlock(new BlockPos(p.getX(), y, p.getZ()), state, 2);
                continue;
            }
            setBlockRotated(world, base, rotation, step.x, step.y, step.z, state);
            if (!step.fillDown) {
                continue;
            }
            // PORT du suffixe 'v' : remplir vers le bas jusqu'au sol dur
            for (int y = step.y - 1; y > -base.getY(); y--) {
                BlockPos p = rotate(base, rotation, step.x, y, step.z);
                if (world.getBlockState(p).isSolidRender(world, p)) {
                    break;
                }
                world.setBlock(p, state, 2);
                setGrassToDirt(world, p.below());
            }
        }
        return true;
    }

    /** Hauteur du sol a l'emplacement, utilitaire des verifications. */
    protected int groundAt(ISeedReader world, BlockPos base, int rotation, int x, int z) {
        BlockPos p = rotate(base, rotation, x, 0, z);
        return world.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, p.getX(), p.getZ());
    }
}
