package fr.alleretretour.lotr.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import fr.alleretretour.lotr.fac.LOTRFaction;
import fr.alleretretour.lotr.fac.LOTRPlayerData;
import fr.alleretretour.lotr.fac.LOTRPlayerDataProvider;
import fr.alleretretour.lotr.network.LOTRPacketHandler;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

/**
 * /alignement get|set|add <joueur> <faction> [valeur]
 * Commande d'administration et de test (permission niveau 2).
 */
public class LOTRCommandAlignment {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("alignement")
                .requires(src -> src.hasPermission(2))
                .then(Commands.literal("get")
                        .then(Commands.argument("joueur", EntityArgument.player())
                                .then(Commands.argument("faction", StringArgumentType.word())
                                        .executes(ctx -> get(ctx.getSource(),
                                                EntityArgument.getPlayer(ctx, "joueur"),
                                                StringArgumentType.getString(ctx, "faction"))))))
                .then(Commands.literal("set")
                        .then(Commands.argument("joueur", EntityArgument.player())
                                .then(Commands.argument("faction", StringArgumentType.word())
                                        .then(Commands.argument("valeur", FloatArgumentType.floatArg())
                                                .executes(ctx -> set(ctx.getSource(),
                                                        EntityArgument.getPlayer(ctx, "joueur"),
                                                        StringArgumentType.getString(ctx, "faction"),
                                                        FloatArgumentType.getFloat(ctx, "valeur"), false))))))
                .then(Commands.literal("add")
                        .then(Commands.argument("joueur", EntityArgument.player())
                                .then(Commands.argument("faction", StringArgumentType.word())
                                        .then(Commands.argument("valeur", FloatArgumentType.floatArg())
                                                .executes(ctx -> set(ctx.getSource(),
                                                        EntityArgument.getPlayer(ctx, "joueur"),
                                                        StringArgumentType.getString(ctx, "faction"),
                                                        FloatArgumentType.getFloat(ctx, "valeur"), true)))))));
    }

    private static int get(CommandSource src, ServerPlayerEntity player, String factionName) {
        LOTRFaction faction = LOTRFaction.byName(factionName);
        if (faction == null) {
            src.sendFailure(new StringTextComponent("Faction inconnue : " + factionName));
            return 0;
        }
        float v = LOTRPlayerDataProvider.get(player).getAlignment(faction);
        src.sendSuccess(new StringTextComponent(player.getName().getString() + " - ")
                .append(new TranslationTextComponent(faction.getTranslationKey()))
                .append(" : " + String.format("%.1f", v)), false);
        return 1;
    }

    private static int set(CommandSource src, ServerPlayerEntity player, String factionName,
                           float value, boolean add) {
        LOTRFaction faction = LOTRFaction.byName(factionName);
        if (faction == null) {
            src.sendFailure(new StringTextComponent("Faction inconnue : " + factionName));
            return 0;
        }
        LOTRPlayerData data = LOTRPlayerDataProvider.get(player);
        if (add) {
            data.addAlignment(faction, value);
            LOTRPacketHandler.syncAlignments(player);
        } else {
            data.setAlignment(faction, value);
            LOTRPacketHandler.syncAlignment(player, faction);
        }
        float now = data.getAlignment(faction);
        src.sendSuccess(new StringTextComponent(player.getName().getString() + " - ")
                .append(new TranslationTextComponent(faction.getTranslationKey()))
                .append(" : " + String.format("%.1f", now)), true);
        return 1;
    }
}
