package fr.alleretretour.lotr.item;

import fr.alleretretour.lotr.entity.item.LOTREntityBannerPlaced;
import fr.alleretretour.lotr.init.LOTREntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * PORT de lotr.common.item.LOTRItemBanner (placement) : la banniere se plante
 * au-dessus d'un bloc plein, orientee face au joueur. Les fonctions de
 * protection de l'original viendront avec leur lot dedie.
 */
public class LOTRItemBanner extends Item {

    public LOTRItemBanner(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        World world = context.getLevel();
        BlockPos clicked = context.getClickedPos();
        BlockPos pos = world.getBlockState(clicked).getMaterial().isReplaceable()
                ? clicked
                : clicked.relative(context.getClickedFace());
        if (context.getClickedFace() == Direction.DOWN
                || !world.getBlockState(pos.below()).isSolidRender(world, pos.below())
                || !world.getBlockState(pos).getMaterial().isReplaceable()) {
            return ActionResultType.FAIL;
        }
        PlayerEntity player = context.getPlayer();
        if (player != null && !player.mayUseItemAt(pos, context.getClickedFace(),
                context.getItemInHand())) {
            return ActionResultType.FAIL;
        }
        if (!world.isClientSide) {
            LOTREntityBannerPlaced banner =
                    LOTREntities.BANNER_PLACED.get().create(world);
            if (banner != null) {
                banner.setBannerItem(getRegistryName().toString());
                float yaw = player != null ? player.yRot : 0.0f;
                banner.moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, yaw, 0.0f);
                if (player != null) {
                    banner.setPlacingPlayer(player);
                }
                world.addFreshEntity(banner);
                world.playSound(null, pos, SoundEvents.WOOD_PLACE,
                        SoundCategory.BLOCKS, 1.0f, 1.0f);
            }
            context.getItemInHand().shrink(1);
        }
        return ActionResultType.sidedSuccess(world.isClientSide);
    }
}
