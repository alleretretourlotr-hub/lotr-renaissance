package fr.alleretretour.lotr.world.feature;

import com.mojang.serialization.Codec;
import fr.alleretretour.lotr.world.map.LOTRRoadGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

/**
 * Feature de pose des routes : appelee une fois par chunk, elle delegue le
 * trace a LOTRRoadGenerator (PORT de LOTRRoadGenerator du Legacy).
 */
public class LOTRFeatureRoads extends Feature<NoFeatureConfig> {

    public LOTRFeatureRoads(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator generator, Random rand,
                         BlockPos pos, NoFeatureConfig config) {
        BlockPos origin = new BlockPos((pos.getX() >> 4) << 4, 0, (pos.getZ() >> 4) << 4);
        LOTRRoadGenerator.generate(world, rand, origin);
        return true;
    }
}
