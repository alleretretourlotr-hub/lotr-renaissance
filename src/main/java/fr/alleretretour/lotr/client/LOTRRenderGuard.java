package fr.alleretretour.lotr.client;

import fr.alleretretour.lotr.LOTRMod;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * GARDE-FOU DE RENDU (diagnostic + protection) :
 * quand une entite apparait cote client, verifie qu'elle a un renderer.
 * Si non : loggue son id exact ("LOTR GARDE") et annule son apparition
 * cote client au lieu de laisser le jeu crasher dans shouldRender.
 */
@Mod.EventBusSubscriber(modid = LOTRMod.MOD_ID, value = Dist.CLIENT)
public class LOTRRenderGuard {

    @SubscribeEvent
    public static void onEntityJoin(EntityJoinWorldEvent event) {
        if (!event.getWorld().isClientSide()) {
            return;
        }
        Entity entity = event.getEntity();
        try {
            Object renderer = Minecraft.getInstance()
                    .getEntityRenderDispatcher().getRenderer(entity);
            if (renderer == null) {
                LOTRMod.LOGGER.error("LOTR GARDE : entite SANS RENDERER : {} ({}) - apparition annulee cote client",
                        entity.getType().getRegistryName(), entity.getClass().getName());
                event.setCanceled(true);
            }
        } catch (Throwable t) {
            LOTRMod.LOGGER.error("LOTR GARDE : erreur en verifiant {} : {}",
                    entity.getType().getRegistryName(), t.toString());
            event.setCanceled(true);
        }
    }
}
