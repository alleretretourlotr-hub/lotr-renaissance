package fr.alleretretour.lotr.client;

import fr.alleretretour.lotr.entity.npc.LOTREntityNPC;
import net.minecraft.client.Minecraft;

/**
 * Pont vers le code client uniquement (jamais charge sur serveur dedie).
 * Appele exclusivement via DistExecutor.
 */
public final class LOTRClientHooks {

    private LOTRClientHooks() {
    }

    public static void openBannerScreen(
            fr.alleretretour.lotr.entity.item.LOTREntityBannerPlaced banner) {
        Minecraft.getInstance().setScreen(
                new fr.alleretretour.lotr.client.gui.LOTRScreenBannerEdit(banner));
    }

    public static void openHiredScreen(LOTREntityNPC npc) {
        Minecraft.getInstance().setScreen(
                new fr.alleretretour.lotr.client.gui.LOTRScreenHiredUnit(npc));
    }
}
