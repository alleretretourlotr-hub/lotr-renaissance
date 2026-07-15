package fr.alleretretour.lotr.fac;

import fr.alleretretour.lotr.LOTRMod;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Decore le chat avec le titre de faction : [Garde du Gondor] Ando : message
 * Le titre est colore aux couleurs de sa faction, comme l'original.
 * Note Mohist : si un plugin de chat Bukkit reformate les messages,
 * ce prefixe Forge peut etre ignore - dans ce cas, brancher le titre
 * via le plugin (l'API LOTRPlayerDataProvider.get(player).getTitle...()).
 */
@Mod.EventBusSubscriber(modid = LOTRMod.MOD_ID)
public class LOTRChatTitles {

    @SubscribeEvent
    public static void onServerChat(ServerChatEvent event) {
        ServerPlayerEntity player = event.getPlayer();
        LOTRPlayerData data = LOTRPlayerDataProvider.get(player);
        LOTRFaction faction = data.getTitleFaction();
        if (faction == null || data.getTitleRank().isEmpty()) {
            return;
        }
        String key = "lotr.faction." + faction.name() + ".rank." + data.getTitleRank();
        StringTextComponent decorated = new StringTextComponent("");
        decorated.append(new StringTextComponent("[")
                .append(new TranslationTextComponent(key))
                .append("] ")
                .withStyle(Style.EMPTY.withColor(Color.fromRgb(faction.color))));
        decorated.append(event.getComponent());
        event.setComponent(decorated);
    }
}
