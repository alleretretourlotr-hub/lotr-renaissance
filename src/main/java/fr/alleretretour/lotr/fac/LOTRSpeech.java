package fr.alleretretour.lotr.fac;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * PORT de LOTRSpeech : les banques de repliques des PNJ.
 * Fichiers assets/lotr/speech/<banque>.txt (une replique par ligne, # = nom du
 * joueur), charges depuis le JAR (fonctionne cote serveur Mohist), caches.
 */
public class LOTRSpeech {

    private static final Map<String, List<String>> CACHE = new HashMap<>();
    private static final Random RANDOM = new Random();

    private static List<String> getBank(String path) {
        return CACHE.computeIfAbsent(path, p -> {
            List<String> lines = new ArrayList<>();
            String resource = "/assets/lotr/speech/" + p + ".txt";
            try (InputStream in = LOTRSpeech.class.getResourceAsStream(resource)) {
                if (in != null) {
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(in, StandardCharsets.UTF_8));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        line = line.trim();
                        if (!line.isEmpty()) {
                            lines.add(line);
                        }
                    }
                }
            } catch (Exception ignored) {
            }
            return lines;
        });
    }

    /** Fait parler le PNJ : replique aleatoire de la banque, nom colore. */
    public static void speak(net.minecraft.entity.LivingEntity npc, String bank,
                             PlayerEntity player, int nameColor) {
        if (!(player instanceof ServerPlayerEntity)) {
            return;
        }
        List<String> lines = getBank(bank);
        if (lines.isEmpty() && bank.endsWith("/neutral")) {
            lines = getBank(bank.replace("/neutral", "/friendly"));
        }
        if (lines.isEmpty()) {
            return;
        }
        String line = lines.get(RANDOM.nextInt(lines.size()))
                .replace("#", player.getName().getString());
        StringTextComponent msg = new StringTextComponent("");
        msg.append(new StringTextComponent(npc.getDisplayName().getString() + " : ")
                .withStyle(Style.EMPTY.withColor(Color.fromRgb(nameColor))));
        msg.append(new StringTextComponent(line));
        player.sendMessage(msg, net.minecraft.util.Util.NIL_UUID);
    }
}
