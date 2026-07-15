package fr.alleretretour.lotr.item;

import net.minecraft.entity.player.PlayerEntity;

/**
 * Pont vers le systeme d'alignement (Phase 3).
 * TODO Phase 3 : brancher sur la capability LOTRPlayerData (alignement par faction).
 * En attendant, toutes les tables sont accessibles.
 */
public final class LOTRAlignmentHelper {

    private LOTRAlignmentHelper() {
    }

    public static boolean hasAlignment(PlayerEntity player, String faction, float required) {
        return true; // Phase 3 : LOTRPlayerData.get(player).getAlignment(faction) >= required
    }
}
