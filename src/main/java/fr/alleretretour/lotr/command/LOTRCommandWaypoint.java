package fr.alleretretour.lotr.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import fr.alleretretour.lotr.world.LOTRDimensions;
import fr.alleretretour.lotr.world.map.LOTRWaypoint;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

import java.util.Locale;

/**
 * /lieu liste [region] | /lieu <nom>
 * Voyage vers un lieu de la Terre du Milieu (coordonnees du Legacy).
 * Preambule au systeme de points de passage complet (deverrouillage,
 * carte, cout en temps) qui viendra avec la carte.
 */
public class LOTRCommandWaypoint {

    private static final SuggestionProvider<CommandSource> NAMES = (ctx, builder) ->
            ISuggestionProvider.suggest(java.util.Arrays.stream(LOTRWaypoint.values())
                    .filter(w -> !w.hidden)
                    .map(w -> w.name().toLowerCase(Locale.ROOT)), builder);

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("lieu")
                .requires(src -> src.hasPermission(2))
                .then(Commands.literal("liste")
                        .executes(ctx -> list(ctx.getSource(), null))
                        .then(Commands.argument("region", StringArgumentType.word())
                                .executes(ctx -> list(ctx.getSource(),
                                        StringArgumentType.getString(ctx, "region")))))
                .then(Commands.argument("nom", StringArgumentType.word())
                        .suggests(NAMES)
                        .executes(ctx -> travel(ctx.getSource(),
                                StringArgumentType.getString(ctx, "nom")))));
    }

    private static int list(CommandSource src, String region) {
        StringBuilder sb = new StringBuilder();
        int n = 0;
        for (LOTRWaypoint w : LOTRWaypoint.values()) {
            if (w.hidden) {
                continue;
            }
            if (region != null && !w.region.name().equalsIgnoreCase(region)) {
                continue;
            }
            if (n > 0) {
                sb.append(", ");
            }
            sb.append(w.name().toLowerCase(Locale.ROOT));
            n++;
        }
        src.sendSuccess(new StringTextComponent(n + " lieu(x) : " + sb), false);
        return n;
    }

    private static int travel(CommandSource src, String name) {
        LOTRWaypoint wp;
        try {
            wp = LOTRWaypoint.valueOf(name.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            src.sendFailure(new StringTextComponent("Lieu inconnu : " + name));
            return 0;
        }
        ServerPlayerEntity player;
        try {
            player = src.getPlayerOrException();
        } catch (Exception e) {
            src.sendFailure(new StringTextComponent("Commande reservee aux joueurs."));
            return 0;
        }
        ServerWorld world = src.getServer().getLevel(LOTRDimensions.MIDDLE_EARTH);
        if (world == null) {
            src.sendFailure(new StringTextComponent("Dimension de la Terre du Milieu introuvable."));
            return 0;
        }
        int y = world.getHeight(net.minecraft.world.gen.Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                wp.x, wp.z);
        player.teleportTo(world, wp.x + 0.5, y + 1.0, wp.z + 0.5, player.yRot, player.xRot);
        src.sendSuccess(new StringTextComponent(
                wp.displayName() + " (" + wp.region.name().toLowerCase(Locale.ROOT) + ")"), false);
        return 1;
    }
}
