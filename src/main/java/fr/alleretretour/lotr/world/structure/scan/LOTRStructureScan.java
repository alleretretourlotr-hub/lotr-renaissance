package fr.alleretretour.lotr.world.structure.scan;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PORT de lotr.common.world.structure2.scan.LOTRStructureScan.
 *
 * Le Legacy stocke 74 structures dans des fichiers .strscan lisibles plutot
 * que dans du code. Format, repris tel quel :
 *
 *   ~ALIAS~            declare un alias de BLOC (a associer au moment de la pose)
 *   #ALIAS#            declare un alias de BLOC + METADONNEE
 *   x.y.z.~ALIAS~      pose l'alias aux coordonnees LOCALES x, y, z
 *   x.y.z."minecraft:stone"   pose un bloc nomme directement
 *   x.yv.z.~ALIAS~     le suffixe 'v' remplit VERS LE BAS jusqu'au sol dur
 *   x.y_.z.~ALIAS~     le suffixe '_' descend jusqu'au premier bloc solide
 *
 * Les alias sont resolus par la structure appelante (associate), ce qui permet
 * a une meme forme de servir a plusieurs factions - c'est ainsi que le Legacy
 * decline la maison de Bree, du Harad ou de Dun a partir d'un seul trace.
 */
public class LOTRStructureScan {

    private static final Map<String, LOTRStructureScan> LOADED = new HashMap<>();

    /** Une pose : coordonnees locales, alias ou bloc, et mode de remplissage. */
    public static class Step {
        public final int x;
        public final int y;
        public final int z;
        /** nom d'alias (sans les delimiteurs) ou null si bloc direct */
        public final String alias;
        /** bloc nomme directement dans le fichier, ou null */
        public final BlockState direct;
        /** 'v' du Legacy : remplit vers le bas jusqu'au sol dur */
        public final boolean fillDown;
        /** '_' du Legacy : descend jusqu'au premier bloc solide */
        public final boolean findLowest;

        Step(int x, int y, int z, String alias, BlockState direct,
             boolean fillDown, boolean findLowest) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.alias = alias;
            this.direct = direct;
            this.fillDown = fillDown;
            this.findLowest = findLowest;
        }
    }

    public final String name;
    public final List<Step> steps = new ArrayList<>();
    public final List<String> aliases = new ArrayList<>();

    private LOTRStructureScan(String name) {
        this.name = name;
    }

    /**
     * Charge un scan depuis assets/lotr/strscan/&lt;nom&gt;.strscan.
     *
     * La lecture se fait par le chargeur de classes plutot que par le
     * gestionnaire de ressources : les structures sont generees cote SERVEUR,
     * ou les ressources d'assets ne sont pas montees.
     */
    public static LOTRStructureScan get(String name) {
        LOTRStructureScan cached = LOADED.get(name);
        if (cached != null) {
            return cached;
        }
        LOTRStructureScan scan = new LOTRStructureScan(name);
        String path = "/assets/lotr/strscan/" + name + ".strscan";
        try (InputStream in = LOTRStructureScan.class.getResourceAsStream(path);
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(in, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                parse(scan, line.trim());
            }
        } catch (Exception e) {
            fr.alleretretour.lotr.LOTRMod.LOGGER.error(
                    "LOTR : scan de structure introuvable ou illisible : " + name, e);
            return null;
        }
        LOADED.put(name, scan);
        return scan;
    }

    /** PORT du parsing ligne a ligne. */
    private static void parse(LOTRStructureScan scan, String line) {
        if (line.isEmpty()) {
            return;
        }
        char c0 = line.charAt(0);
        // declaration d'alias : ~NOM~ (bloc) ou #NOM# (bloc + metadonnee)
        if (c0 == '~' || c0 == '#') {
            scan.aliases.add(line.substring(1, line.length() - 1));
            return;
        }
        try {
            int i = 0;
            int j = line.indexOf('.');
            int x = Integer.parseInt(line.substring(i, j));

            i = j + 1;
            j = line.indexOf('.', i);
            String sy = line.substring(i, j);
            boolean fillDown = false;
            boolean findLowest = false;
            if (!sy.isEmpty() && sy.charAt(sy.length() - 1) == 'v') {
                fillDown = true;
                sy = sy.substring(0, sy.length() - 1);
            } else if (!sy.isEmpty() && sy.charAt(sy.length() - 1) == '_') {
                findLowest = true;
                sy = sy.substring(0, sy.length() - 1);
            }
            int y = Integer.parseInt(sy);

            i = j + 1;
            j = line.indexOf('.', i);
            int z = Integer.parseInt(line.substring(i, j));

            String rest = line.substring(j + 1);
            if (rest.isEmpty()) {
                return;
            }
            if (rest.charAt(0) == '"') {
                // bloc nomme directement
                String id = rest.substring(1, rest.indexOf('"', 1));
                Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(id));
                if (block == null) {
                    block = ForgeRegistries.BLOCKS.getValue(
                            new ResourceLocation("lotr", id.replace("lotr:", "")));
                }
                scan.steps.add(new Step(x, y, z, null,
                        (block == null ? Blocks.STONE : block).defaultBlockState(),
                        fillDown, findLowest));
                return;
            }
            // alias : ~NOM~ ou #NOM#
            char delim = rest.charAt(0);
            int end = rest.indexOf(delim, 1);
            if (end < 0) {
                return;
            }
            scan.steps.add(new Step(x, y, z, rest.substring(1, end), null,
                    fillDown, findLowest));
        } catch (RuntimeException e) {
            // ligne malformee : ignoree, comme le Legacy
        }
    }

    /** Vide le cache (rechargement des ressources). */
    public static void clearCache() {
        LOADED.clear();
    }
}
