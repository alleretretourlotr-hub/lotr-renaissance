package fr.alleretretour.lotr.event;

import fr.alleretretour.lotr.fac.LOTRBannerProtection;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Application de la protection des bannieres (PORT des points d'ancrage
 * essentiels du Legacy) : casse, pose, explosions. Buckets, feu et pistons
 * suivront dans la v2 du lot.
 */
@Mod.EventBusSubscriber(modid = "lotr")
public final class LOTRBannerProtectionEvents {

    private LOTRBannerProtectionEvents() {
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (event.getWorld() instanceof World
                && LOTRBannerProtection.isProtected((World) event.getWorld(),
                        event.getPos(), event.getPlayer(), true)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onBlockPlace(BlockEvent.EntityPlaceEvent event) {
        PlayerEntity player = event.getEntity() instanceof PlayerEntity
                ? (PlayerEntity) event.getEntity() : null;
        if (event.getWorld() instanceof World
                && LOTRBannerProtection.isProtected((World) event.getWorld(),
                        event.getPos(), player, true)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onBucket(net.minecraftforge.event.entity.player.FillBucketEvent event) {
        if (event.getTarget() == null || event.getWorld().isClientSide) {
            return;
        }
        BlockPos pos = new BlockPos(event.getTarget().getLocation());
        if (LOTRBannerProtection.isProtected(event.getWorld(), pos, event.getPlayer(), true)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onPiston(net.minecraftforge.event.world.PistonEvent.Pre event) {
        if (!(event.getWorld() instanceof World)) {
            return;
        }
        World world = (World) event.getWorld();
        // un piston exterieur ne peut pas pousser/tirer des blocs en zone protegee
        if (LOTRBannerProtection.isProtected(world, event.getFaceOffsetPos(), null, false)
                && !LOTRBannerProtection.isProtected(world, event.getPos(), null, false)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onExplosion(ExplosionEvent.Detonate event) {
        event.getAffectedBlocks().removeIf((BlockPos pos) ->
                LOTRBannerProtection.isProtected(event.getWorld(), pos, null, false));
    }
}
