package fr.alleretretour.lotr.world.structure;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

/**
 * PORT de LOTRWorldGenMoredainMercCamp : le camp des mercenaires moredain.
 *
 * Camp classique, auquel le Legacy ajoute 1 a 3 MANNEQUINS D'ENTRAINEMENT
 * (LOTRWorldGenMoredainMercDummy) disposes a 8-15 blocs du centre, a angle
 * libre - les mercenaires s'exercent entre deux contrats.
 *
 * Le mannequin du Legacy est un porte-armure sur un poteau ; le porte-armure
 * du mod n'etant pas porte, on pose ici le poteau et sa traverse.
 */
public class LOTRStructureMoredainMercCamp extends LOTRStructureCampBase {

    public LOTRStructureMoredainMercCamp(Codec<NoFeatureConfig> codec) {
        super(codec);
        tentWool = new BlockState[]{
                Blocks.BROWN_WOOL.defaultBlockState(),
                Blocks.YELLOW_WOOL.defaultBlockState(),
                Blocks.LIGHT_GRAY_WOOL.defaultBlockState()};
    }

    @Override
    protected void generateCentrepiece(ISeedReader world, Random random, BlockPos origin,
                                       int rotation) {
        int dummies = 1 + random.nextInt(3);   // PORT
        for (int l = 0; l < dummies; l++) {
            for (int attempt = 0; attempt < 8; attempt++) {
                int r = MathHelper.nextInt(random, 8, 15);
                float angle = random.nextFloat() * (float) Math.PI * 2.0f;
                int x = (int) (r * MathHelper.cos(angle));
                int z = (int) (r * MathHelper.sin(angle));
                BlockPos p = rotate(origin, rotation, x, 0, z);
                int y = world.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, p.getX(), p.getZ());
                BlockPos foot = new BlockPos(p.getX(), y, p.getZ());
                if (!world.getBlockState(foot.below()).isSolidRender(world, foot.below())
                        || !world.isEmptyBlock(foot) || !world.isEmptyBlock(foot.above())) {
                    continue;
                }
                BlockState post = modBlock("lotr:baobab_fence", Blocks.OAK_FENCE);
                world.setBlock(foot, post, 2);
                world.setBlock(foot.above(), post, 2);
                world.setBlock(foot.above(2), Blocks.HAY_BLOCK.defaultBlockState(), 2);
                setGrassToDirt(world, foot.below());
                break;
            }
        }
    }
}
