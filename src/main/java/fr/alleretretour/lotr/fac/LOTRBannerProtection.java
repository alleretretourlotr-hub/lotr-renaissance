package fr.alleretretour.lotr.fac;

import fr.alleretretour.lotr.entity.item.LOTREntityBannerPlaced;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * PORT de lotr.common.LOTRBannerProtection : portees par bloc-socle
 * (bronze 8, argent 16, or 32, MAX 64), cube de protection, avertissement
 * anti-spam (5 s). Les evenements Forge appliquent la protection.
 */
public final class LOTRBannerProtection {

    public static final int MAX_RANGE = 64;
    private static final Map<UUID, Long> LAST_WARNING = new HashMap<>();

    private LOTRBannerProtection() {
    }

    /** PORT exact de protectionBlocks (Legacy : blockOreStorage 2/3, gold_block). */
    public static int getProtectionRange(Block block) {
        if (block == fr.alleretretour.lotr.init.LOTRBlocksStorage.BRONZE_BLOCK.get()) {
            return 8;
        }
        if (block == fr.alleretretour.lotr.init.LOTRBlocksStorage.SILVER_BLOCK.get()) {
            return 16;
        }
        if (block == Blocks.GOLD_BLOCK) {
            return 32;
        }
        return 0;
    }

    /** true si la position est protegee contre ce joueur (message si demande). */
    public static boolean isProtected(World world, BlockPos pos,
                                      PlayerEntity player, boolean sendMessage) {
        if (player != null && player.isCreative()) {
            return false;
        }
        AxisAlignedBB search = new AxisAlignedBB(pos).inflate(MAX_RANGE);
        List<LOTREntityBannerPlaced> banners =
                world.getEntitiesOfClass(LOTREntityBannerPlaced.class, search);
        for (LOTREntityBannerPlaced banner : banners) {
            int range = banner.getProtectionRange();
            if (range <= 0 || !banner.protectionCube(range).contains(
                    pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5)) {
                continue;
            }
            if (player != null && banner.isPlayerAllowed(player)) {
                continue;
            }
            if (sendMessage && player instanceof ServerPlayerEntity) {
                warn((ServerPlayerEntity) player, banner);
            }
            return true;
        }
        return false;
    }

    private static void warn(ServerPlayerEntity player, LOTREntityBannerPlaced banner) {
        long now = player.level.getGameTime();
        Long last = LAST_WARNING.get(player.getUUID());
        if (last != null && now - last < 100) {
            return;  // anti-spam 5 s, comme lastWarningTimes du Legacy
        }
        LAST_WARNING.put(player.getUUID(), now);
        player.displayClientMessage(new StringTextComponent(
                "Ce territoire est protege par une banniere ("
                        + banner.getFactionName() + ")."), true);
    }
}
