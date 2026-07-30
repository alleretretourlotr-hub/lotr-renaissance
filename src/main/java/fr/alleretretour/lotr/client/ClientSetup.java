package fr.alleretretour.lotr.client;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Setup client BLINDE + AUTO-REPARATION du dispatcher de rendu.
 *
 * Historique du bug "entite SANS RENDERER" :
 * 1) v1 : renderers enregistres au constructeur -> RegistryObject.get() avant
 *    peuplement des registres -> 26 echecs avales par safe(). Corrige.
 * 2) v2 : enregistres au FMLClientSetupEvent (canonique), bilan "OK"... mais le
 *    dispatcher reste vide au join. Cause : dans Forge 1.16.5, l'application
 *    reelle (RenderingRegistry.loadEntityRenderers, appelee par postSidedRunnable)
 *    s'execute dans un CompletableFuture ORPHELIN (ModLoadingStage.buildTransition
 *    fait postSyncTask.apply() sans jamais join) : toute exception y est avalee
 *    en silence, et validateRendererExistence() peut lever pour un type manquant.
 *    Aucun crash, aucun log, dispatcher partiellement ou pas du tout rempli.
 *
 * PARADE (ce fichier) : les definitions type->factory sont centralisees dans DEFS.
 * a) Chemin normal : enregistrement via RenderingRegistry au client setup.
 * b) Filet de securite : au FMLLoadCompleteEvent (strictement apres le pipeline),
 *    on inspecte la map interne du dispatcher par reflexion, on loggue le
 *    diagnostic (presents/manquants), et on RE-APPLIQUE directement tout
 *    renderer manquant. Idempotent, isole, loggue.
 */
public class ClientSetup {

    public static net.minecraft.client.settings.KeyBinding ALIGNMENT_KEY;

    /** Echecs collectes pendant le setup, pour le bilan final. */
    private static final List<String> FAILURES = new ArrayList<>();

    /** Definitions centralisees : nom -> (type, factory). Remplies une seule fois. */
    private static final Map<String, Def> DEFS = new LinkedHashMap<>();

    private static final class Def {
        final RegistryObject<? extends EntityType<?>> type;
        final IRenderFactory<?> factory;
        Def(RegistryObject<? extends EntityType<?>> type, IRenderFactory<?> factory) {
            this.type = type;
            this.factory = factory;
        }
    }

    /**
     * Generique en T : indispensable pour que les lambdas s'inferent correctement
     * (une interface fonctionnelle ciblee avec un wildcard ne compile pas).
     */
    private static <T extends Entity> void def(String name, RegistryObject<EntityType<T>> type,
                                               IRenderFactory<? super T> factory) {
        DEFS.put(name, new Def(type, factory));
    }

    public static void init(IEventBus modBus) {
        // Ne rien enregistrer ici : les RegistryObject ne sont pas encore valides.
        modBus.addListener(ClientSetup::onClientSetup);
        modBus.addListener(ClientSetup::onItemColors);
        modBus.addListener(ClientSetup::onLoadComplete);
    }

    // ==================================================================
    // DEFINITIONS DES RENDERERS (source unique pour les deux chemins)
    // ==================================================================
    private static void defineRenderers() {
        if (!DEFS.isEmpty()) {
            return;
        }
        // --- Projectiles ---
        def("thrown_weapon", fr.alleretretour.lotr.init.LOTREntities.THROWN_WEAPON,
            fr.alleretretour.lotr.client.render.LOTRThrownWeaponRenderer::new);
        def("crossbow_bolt", fr.alleretretour.lotr.init.LOTREntities.CROSSBOW_BOLT,
            manager -> new net.minecraft.client.renderer.entity.ArrowRenderer<
                    fr.alleretretour.lotr.entity.projectile.LOTREntityCrossbowBolt>(manager) {
                @Override
                public net.minecraft.util.ResourceLocation getTextureLocation(
                        fr.alleretretour.lotr.entity.projectile.LOTREntityCrossbowBolt e) {
                    return new net.minecraft.util.ResourceLocation(
                            "minecraft", "textures/entity/projectiles/arrow.png");
                }
            });
        def("dart", fr.alleretretour.lotr.init.LOTREntities.DART,
            manager -> new net.minecraft.client.renderer.entity.ArrowRenderer<
                    fr.alleretretour.lotr.entity.projectile.LOTREntityDart>(manager) {
                @Override
                public net.minecraft.util.ResourceLocation getTextureLocation(
                        fr.alleretretour.lotr.entity.projectile.LOTREntityDart e) {
                    return new net.minecraft.util.ResourceLocation(
                            "minecraft", "textures/entity/projectiles/arrow.png");
                }
            });

        // --- Gondor ---
        def("gondor_soldier", fr.alleretretour.lotr.init.LOTREntities.GONDOR_SOLDIER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "gondor_male", 10));
        def("gondor_archer", fr.alleretretour.lotr.init.LOTREntities.GONDOR_ARCHER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "gondor_male", 10));
        def("gondor_tower_guard", fr.alleretretour.lotr.init.LOTREntities.GONDOR_TOWER_GUARD,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "gondor_male", 10));
        def("gondorian_captain", fr.alleretretour.lotr.init.LOTREntities.GONDORIAN_CAPTAIN,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "gondor_male", 10));
        def("gondor_levyman", fr.alleretretour.lotr.init.LOTREntities.GONDOR_LEVYMAN,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "gondor_male", 10));
        def("gondor_blacksmith", fr.alleretretour.lotr.init.LOTREntities.GONDOR_BLACKSMITH,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "gondor_male", 10));

        // --- Mordor / Isengard ---
        def("mordor_orc", fr.alleretretour.lotr.init.LOTREntities.MORDOR_ORC,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "orc", 8));
        def("mordor_orc_archer", fr.alleretretour.lotr.init.LOTREntities.MORDOR_ORC_ARCHER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "orc", 8));
        def("uruk_hai", fr.alleretretour.lotr.init.LOTREntities.URUK_HAI,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "uruk_hai", 3));

        // --- Rohan ---
        def("rohirrim_warrior", fr.alleretretour.lotr.init.LOTREntities.ROHIRRIM_WARRIOR,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "rohan_male", 6));
        def("rohirrim_archer", fr.alleretretour.lotr.init.LOTREntities.ROHIRRIM_ARCHER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "rohan_male", 6));
        def("rohirrim_marshal", fr.alleretretour.lotr.init.LOTREntities.ROHIRRIM_MARSHAL,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "rohan_male", 6));
        def("rohan_shieldmaiden", fr.alleretretour.lotr.init.LOTREntities.ROHAN_SHIELDMAIDEN,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "shieldmaiden", 3));

        // --- Elfes ---
        def("galadhrim_warrior", fr.alleretretour.lotr.init.LOTREntities.GALADHRIM_WARRIOR,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "galadhrim_male", 4, 1.0f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.LONG, true));
        def("galadhrim_warden", fr.alleretretour.lotr.init.LOTREntities.GALADHRIM_WARDEN,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "galadhrim_male", 4, 1.0f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.LONG, true));
        def("galadhrim_lord", fr.alleretretour.lotr.init.LOTREntities.GALADHRIM_LORD,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "galadhrim_male", 4, 1.0f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.LONG, true));
        def("high_elf_warrior", fr.alleretretour.lotr.init.LOTREntities.HIGH_ELF_WARRIOR,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "high_elf_male", 18, 1.0f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.LONG, true));
        def("high_elf_lord", fr.alleretretour.lotr.init.LOTREntities.HIGH_ELF_LORD,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "high_elf_male", 18, 1.0f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.LONG, true));
        def("wood_elf_warrior", fr.alleretretour.lotr.init.LOTREntities.WOOD_ELF_WARRIOR,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "wood_elf_male", 4, 1.0f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.LONG, true));
        def("wood_elf_scout", fr.alleretretour.lotr.init.LOTREntities.WOOD_ELF_SCOUT,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "wood_elf_male", 4, 1.0f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.LONG, true));
        def("wood_elf_captain", fr.alleretretour.lotr.init.LOTREntities.WOOD_ELF_CAPTAIN,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "wood_elf_male", 4, 1.0f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.LONG, true));
        def("rivendell_warrior", fr.alleretretour.lotr.init.LOTREntities.RIVENDELL_WARRIOR,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "high_elf_male", 18, 1.0f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.LONG, true));
        def("rivendell_lord", fr.alleretretour.lotr.init.LOTREntities.RIVENDELL_LORD,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "high_elf_male", 18, 1.0f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.LONG, true));

        // --- MEGA-LOT Phase 4 ---
        def("dwarf", fr.alleretretour.lotr.init.LOTREntities.DWARF,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "dwarf_male", 3, 0.75f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.SHORT, false));
        def("dwarf_warrior", fr.alleretretour.lotr.init.LOTREntities.DWARF_WARRIOR,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "dwarf_male", 3, 0.75f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.SHORT, false));
        def("dwarf_commander", fr.alleretretour.lotr.init.LOTREntities.DWARF_COMMANDER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "dwarf_male", 3, 0.75f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.SHORT, false));
        def("blue_dwarf_warrior", fr.alleretretour.lotr.init.LOTREntities.BLUE_DWARF_WARRIOR,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "blue_mountains_male", 3, 0.75f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.SHORT, false));
        def("hobbit", fr.alleretretour.lotr.init.LOTREntities.HOBBIT,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "hobbit_male", 13, 0.62f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.SHORT, false));
        def("hobbit_bounder", fr.alleretretour.lotr.init.LOTREntities.HOBBIT_BOUNDER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "hobbit_male", 13, 0.62f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.SHORT, false));
        def("bree_man", fr.alleretretour.lotr.init.LOTREntities.BREE_MAN,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "bree_male", 30));
        def("bree_guard", fr.alleretretour.lotr.init.LOTREntities.BREE_GUARD,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "bree_male", 30));
        def("ranger_north", fr.alleretretour.lotr.init.LOTREntities.RANGER_NORTH,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "ranger_male", 5));
        def("ranger_north_captain", fr.alleretretour.lotr.init.LOTREntities.RANGER_NORTH_CAPTAIN,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "ranger_male", 5));
        def("dale_soldier", fr.alleretretour.lotr.init.LOTREntities.DALE_SOLDIER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "dale_soldier", 3));
        def("dale_archer", fr.alleretretour.lotr.init.LOTREntities.DALE_ARCHER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "dale_soldier", 3));
        def("dale_levyman", fr.alleretretour.lotr.init.LOTREntities.DALE_LEVYMAN,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "dale_male", 3));
        def("dorwinion_guard", fr.alleretretour.lotr.init.LOTREntities.DORWINION_GUARD,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "dorwinion_male", 4, 1.0f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.LONG, true));
        def("dorwinion_elf_warrior", fr.alleretretour.lotr.init.LOTREntities.DORWINION_ELF_WARRIOR,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "dorwinion_male", 4, 1.0f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.LONG, true));
        def("dorwinion_elf_archer", fr.alleretretour.lotr.init.LOTREntities.DORWINION_ELF_ARCHER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "dorwinion_male", 4, 1.0f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.LONG, true));
        def("angmar_orc", fr.alleretretour.lotr.init.LOTREntities.ANGMAR_ORC,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "orc", 8));
        def("angmar_orc_archer", fr.alleretretour.lotr.init.LOTREntities.ANGMAR_ORC_ARCHER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "orc", 8));
        def("angmar_hillman_warrior", fr.alleretretour.lotr.init.LOTREntities.ANGMAR_HILLMAN_WARRIOR,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "hillman_male", 3));
        def("gundabad_orc", fr.alleretretour.lotr.init.LOTREntities.GUNDABAD_ORC,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "orc", 8));
        def("gundabad_orc_archer", fr.alleretretour.lotr.init.LOTREntities.GUNDABAD_ORC_ARCHER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "orc", 8));
        def("gundabad_uruk", fr.alleretretour.lotr.init.LOTREntities.GUNDABAD_URUK,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "uruk_hai", 3));
        def("dol_guldur_orc", fr.alleretretour.lotr.init.LOTREntities.DOL_GULDUR_ORC,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "orc", 8));
        def("dol_guldur_orc_archer", fr.alleretretour.lotr.init.LOTREntities.DOL_GULDUR_ORC_ARCHER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "orc", 8));
        def("dunlending", fr.alleretretour.lotr.init.LOTREntities.DUNLENDING,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "dunlending_male", 4));
        def("dunlending_warrior", fr.alleretretour.lotr.init.LOTREntities.DUNLENDING_WARRIOR,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "dunlending_male", 4));
        def("dunlending_berserker", fr.alleretretour.lotr.init.LOTREntities.DUNLENDING_BERSERKER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "dunland_berserker", 1));
        def("near_haradrim_warrior", fr.alleretretour.lotr.init.LOTREntities.NEAR_HARADRIM_WARRIOR,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "near_harad_warrior", 1));
        def("near_haradrim_archer", fr.alleretretour.lotr.init.LOTREntities.NEAR_HARADRIM_ARCHER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "near_harad_warrior", 1));
        def("corsair", fr.alleretretour.lotr.init.LOTREntities.CORSAIR,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "haradrim_male", 5));
        def("umbar_warrior", fr.alleretretour.lotr.init.LOTREntities.UMBAR_WARRIOR,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "haradrim_male", 5));
        def("umbar_archer", fr.alleretretour.lotr.init.LOTREntities.UMBAR_ARCHER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "haradrim_male", 5));
        def("easterling_warrior", fr.alleretretour.lotr.init.LOTREntities.EASTERLING_WARRIOR,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "easterling_male", 5));
        def("easterling_archer", fr.alleretretour.lotr.init.LOTREntities.EASTERLING_ARCHER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "easterling_male", 5));
        def("easterling_gold_warrior", fr.alleretretour.lotr.init.LOTREntities.EASTERLING_GOLD_WARRIOR,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "easterling_male", 5));
        def("moredain_warrior", fr.alleretretour.lotr.init.LOTREntities.MOREDAIN_WARRIOR,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "moredain_male", 5));
        def("moredain_huntsman", fr.alleretretour.lotr.init.LOTREntities.MOREDAIN_HUNTSMAN,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "moredain_male", 5));
        def("tauredain_warrior", fr.alleretretour.lotr.init.LOTREntities.TAUREDAIN_WARRIOR,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "tauredain_male", 4));
        def("tauredain_blowgunner", fr.alleretretour.lotr.init.LOTREntities.TAUREDAIN_BLOWGUNNER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "tauredain_male", 4));
        def("half_troll", fr.alleretretour.lotr.init.LOTREntities.HALF_TROLL,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "half_troll", 3, 1.2f));
        def("half_troll_warrior", fr.alleretretour.lotr.init.LOTREntities.HALF_TROLL_WARRIOR,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "half_troll", 3, 1.2f));

        // --- LOT ARMES EXOTIQUES ---
        def("dwarf_axe_thrower", fr.alleretretour.lotr.init.LOTREntities.DWARF_AXE_THROWER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "dwarf_male", 3, 0.75f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.SHORT, false));
        def("blue_dwarf_axe_thrower", fr.alleretretour.lotr.init.LOTREntities.BLUE_DWARF_AXE_THROWER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "blue_mountains_male", 3, 0.75f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.SHORT, false));
        def("dorwinion_crossbower", fr.alleretretour.lotr.init.LOTREntities.DORWINION_CROSSBOWER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "dorwinion_male", 4, 1.0f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.LONG, true));
        def("uruk_hai_crossbower", fr.alleretretour.lotr.init.LOTREntities.URUK_HAI_CROSSBOWER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "uruk_hai", 3));

        // --- LOT MARCHANDS ---
        def("dwarf_smith", fr.alleretretour.lotr.init.LOTREntities.DWARF_SMITH,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "dwarf_male", 3, 0.75f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.SHORT, false));
        def("blue_mountains_smith", fr.alleretretour.lotr.init.LOTREntities.BLUE_MOUNTAINS_SMITH,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "blue_mountains_male", 3, 0.75f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.SHORT, false));
        def("hobbit_bartender", fr.alleretretour.lotr.init.LOTREntities.HOBBIT_BARTENDER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "hobbit_male", 13, 0.62f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.SHORT, false));
        def("bree_innkeeper", fr.alleretretour.lotr.init.LOTREntities.BREE_INNKEEPER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "bree_male", 30));
        def("dale_blacksmith", fr.alleretretour.lotr.init.LOTREntities.DALE_BLACKSMITH,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "dale_male", 3));
        def("easterling_blacksmith", fr.alleretretour.lotr.init.LOTREntities.EASTERLING_BLACKSMITH,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "easterling_male", 5));
        def("near_harad_merchant", fr.alleretretour.lotr.init.LOTREntities.NEAR_HARAD_MERCHANT,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "haradrim_male", 5));
        def("moredain_trader", fr.alleretretour.lotr.init.LOTREntities.MOREDAIN_TRADER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "moredain_male", 5));
        def("angmar_orc_trader", fr.alleretretour.lotr.init.LOTREntities.ANGMAR_ORC_TRADER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "orc", 8));
        def("dol_guldur_orc_trader", fr.alleretretour.lotr.init.LOTREntities.DOL_GULDUR_ORC_TRADER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "orc", 8));

        // --- LOT PORTE-BANNIERES ---
        def("gondor_banner_bearer", fr.alleretretour.lotr.init.LOTREntities.GONDOR_BANNER_BEARER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "gondor_male", 10));
        def("rohan_banner_bearer", fr.alleretretour.lotr.init.LOTREntities.ROHAN_BANNER_BEARER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "rohan_male", 6));
        def("dwarf_banner_bearer", fr.alleretretour.lotr.init.LOTREntities.DWARF_BANNER_BEARER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "dwarf_male", 3, 0.75f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.SHORT, false));
        def("blue_dwarf_banner_bearer", fr.alleretretour.lotr.init.LOTREntities.BLUE_DWARF_BANNER_BEARER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "blue_mountains_male", 3, 0.75f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.SHORT, false));
        def("high_elf_banner_bearer", fr.alleretretour.lotr.init.LOTREntities.HIGH_ELF_BANNER_BEARER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "high_elf_male", 18, 1.0f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.LONG, true));
        def("rivendell_banner_bearer", fr.alleretretour.lotr.init.LOTREntities.RIVENDELL_BANNER_BEARER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "high_elf_male", 18, 1.0f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.LONG, true));
        def("galadhrim_banner_bearer", fr.alleretretour.lotr.init.LOTREntities.GALADHRIM_BANNER_BEARER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "galadhrim_male", 4, 1.0f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.LONG, true));
        def("ranger_north_banner_bearer", fr.alleretretour.lotr.init.LOTREntities.RANGER_NORTH_BANNER_BEARER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "ranger_male", 6));
        def("banner_placed", fr.alleretretour.lotr.init.LOTREntities.BANNER_PLACED,
            fr.alleretretour.lotr.client.render.LOTRRenderBannerPlaced::new);
    
        // --- MONTURES ---
        def("horse", fr.alleretretour.lotr.init.LOTREntities.HORSE,
            net.minecraft.client.renderer.entity.HorseRenderer::new);
        def("wild_boar", fr.alleretretour.lotr.init.LOTREntities.WILD_BOAR,
            fr.alleretretour.lotr.client.render.LOTRRenderWildBoar::new);
    
        // --- mega-lot 2 ---
        def("hobbit_shirriff", fr.alleretretour.lotr.init.LOTREntities.HOBBIT_SHIRRIFF,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "hobbit_male", 13, 0.62f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.SHORT, false));
        def("bree_ruffian", fr.alleretretour.lotr.init.LOTREntities.BREE_RUFFIAN,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "ruffian", 5));
        def("ruffian_brute", fr.alleretretour.lotr.init.LOTREntities.RUFFIAN_BRUTE,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "ruffian", 5));
        def("ruffian_spy", fr.alleretretour.lotr.init.LOTREntities.RUFFIAN_SPY,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "ruffian", 5));
        def("ranger_ithilien", fr.alleretretour.lotr.init.LOTREntities.RANGER_ITHILIEN,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "ithilien_ranger", 3));
        def("ranger_ithilien_captain", fr.alleretretour.lotr.init.LOTREntities.RANGER_ITHILIEN_CAPTAIN,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "ithilien_ranger", 3));
        def("harnedor_warrior", fr.alleretretour.lotr.init.LOTREntities.HARNEDOR_WARRIOR,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "harnedor_warrior", 5));
        def("harnedor_archer", fr.alleretretour.lotr.init.LOTREntities.HARNEDOR_ARCHER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "harnedor_warrior", 5));
    
        def("angmar_hillman_chieftain", fr.alleretretour.lotr.init.LOTREntities.ANGMAR_HILLMAN_CHIEFTAIN,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "hillman_male", 3));
        def("dol_guldur_orc_chieftain", fr.alleretretour.lotr.init.LOTREntities.DOL_GULDUR_ORC_CHIEFTAIN,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "orc", 8));
        def("dunlending_warlord", fr.alleretretour.lotr.init.LOTREntities.DUNLENDING_WARLORD,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "dunlending_male", 4));
        def("easterling_warlord", fr.alleretretour.lotr.init.LOTREntities.EASTERLING_WARLORD,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "easterling_male", 5));
        def("half_troll_warlord", fr.alleretretour.lotr.init.LOTREntities.HALF_TROLL_WARLORD,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "half_troll", 3, 1.2f));
        def("harnedor_warlord", fr.alleretretour.lotr.init.LOTREntities.HARNEDOR_WARLORD,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "harnedor_warrior", 5));
        def("near_haradrim_warlord", fr.alleretretour.lotr.init.LOTREntities.NEAR_HARADRIM_WARLORD,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "haradrim_warlord", 1));
        def("moredain_chieftain", fr.alleretretour.lotr.init.LOTREntities.MOREDAIN_CHIEFTAIN,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "moredain_male", 5));
        def("tauredain_chieftain", fr.alleretretour.lotr.init.LOTREntities.TAUREDAIN_CHIEFTAIN,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "tauredain_male", 4));
    
        // --- civils elfes (reinjectes) ---
        def("galadhrim_elf", fr.alleretretour.lotr.init.LOTREntities.GALADHRIM_ELF,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "galadhrim_male", 4, 1.0f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.LONG, true));
        def("high_elf", fr.alleretretour.lotr.init.LOTREntities.HIGH_ELF,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "high_elf_male", 18, 1.0f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.LONG, true));
        def("rivendell_elf", fr.alleretretour.lotr.init.LOTREntities.RIVENDELL_ELF,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "high_elf_male", 18, 1.0f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.LONG, true));
    
        // --- creatures ---
        def("rhino", fr.alleretretour.lotr.init.LOTREntities.RHINO,
            fr.alleretretour.lotr.client.render.LOTRRenderRhino::new);
    
        // --- lot troupes 3 ---
        def("angmar_hillman", fr.alleretretour.lotr.init.LOTREntities.ANGMAR_HILLMAN,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "hillman_male", 3));
        def("angmar_hillman_axe_thrower", fr.alleretretour.lotr.init.LOTREntities.ANGMAR_HILLMAN_AXE_THROWER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "hillman_male", 3));
        def("dunlending_archer", fr.alleretretour.lotr.init.LOTREntities.DUNLENDING_ARCHER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "dunlending_male", 4));
        def("dunlending_axe_thrower", fr.alleretretour.lotr.init.LOTREntities.DUNLENDING_AXE_THROWER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "dunlending_male", 4));
        def("easterling_levyman", fr.alleretretour.lotr.init.LOTREntities.EASTERLING_LEVYMAN,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "easterling_male", 5));
        def("southron_champion", fr.alleretretour.lotr.init.LOTREntities.SOUTHRON_CHAMPION,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "haradrim_male", 5));
    
        // --- porte-bannieres (mega-lot 2) ---
        def("angmar_hillman_banner_bearer", fr.alleretretour.lotr.init.LOTREntities.ANGMAR_HILLMAN_BANNER_BEARER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "hillman_male", 3));
        def("dol_guldur_banner_bearer", fr.alleretretour.lotr.init.LOTREntities.DOL_GULDUR_BANNER_BEARER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "orc", 8));
        def("dunlending_banner_bearer", fr.alleretretour.lotr.init.LOTREntities.DUNLENDING_BANNER_BEARER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "dunlending_male", 4));
        def("easterling_banner_bearer", fr.alleretretour.lotr.init.LOTREntities.EASTERLING_BANNER_BEARER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "easterling_male", 5));
        def("half_troll_banner_bearer", fr.alleretretour.lotr.init.LOTREntities.HALF_TROLL_BANNER_BEARER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "half_troll", 3, 1.2f));
        def("harnedor_banner_bearer", fr.alleretretour.lotr.init.LOTREntities.HARNEDOR_BANNER_BEARER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "harnedor_warrior", 5));
        def("near_harad_banner_bearer", fr.alleretretour.lotr.init.LOTREntities.NEAR_HARAD_BANNER_BEARER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "near_harad_warrior", 1));
        def("moredain_banner_bearer", fr.alleretretour.lotr.init.LOTREntities.MOREDAIN_BANNER_BEARER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "moredain_male", 5));
        def("tauredain_banner_bearer", fr.alleretretour.lotr.init.LOTREntities.TAUREDAIN_BANNER_BEARER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "tauredain_male", 4));
    
        // --- creatures 2 ---
        def("mirkwood_spider", fr.alleretretour.lotr.init.LOTREntities.MIRKWOOD_SPIDER,
            fr.alleretretour.lotr.client.render.LOTRRenderMirkwoodSpider::new);
        def("zebra", fr.alleretretour.lotr.init.LOTREntities.ZEBRA,
            fr.alleretretour.lotr.client.render.LOTRRenderZebra::new);
        def("mirk_troll", fr.alleretretour.lotr.init.LOTREntities.MIRK_TROLL,
            fr.alleretretour.lotr.client.render.LOTRRenderMirkTroll::new);
    
        // --- lot pot de feu ---
        def("easterling_fire_thrower", fr.alleretretour.lotr.init.LOTREntities.EASTERLING_FIRE_THROWER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "easterling_male", 5));
        def("fire_pot", fr.alleretretour.lotr.init.LOTREntities.FIRE_POT,
            m -> new net.minecraft.client.renderer.entity.SpriteRenderer<>(
                    m, net.minecraft.client.Minecraft.getInstance().getItemRenderer()));
    
        // --- lot 7 recruteurs ---

    
        def("dale_captain", fr.alleretretour.lotr.init.LOTREntities.DALE_CAPTAIN,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "dale_soldier", 3));
        def("blue_dwarf_commander", fr.alleretretour.lotr.init.LOTREntities.BLUE_DWARF_COMMANDER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "blue_mountains_male", 3, 0.75f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.SHORT, false));
        def("dorwinion_captain", fr.alleretretour.lotr.init.LOTREntities.DORWINION_CAPTAIN,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "dorwinion_male", 4, 1.0f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.LONG, true));
        def("umbar_captain", fr.alleretretour.lotr.init.LOTREntities.UMBAR_CAPTAIN,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "haradrim_male", 5));
        def("corsair_captain", fr.alleretretour.lotr.init.LOTREntities.CORSAIR_CAPTAIN,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "haradrim_male", 5));
        def("bree_captain", fr.alleretretour.lotr.init.LOTREntities.BREE_CAPTAIN,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "bree_male", 30));
        def("mordor_orc_mercenary_captain", fr.alleretretour.lotr.init.LOTREntities.MORDOR_ORC_MERCENARY_CAPTAIN,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "orc", 8));
        def("uruk_hai_mercenary_captain", fr.alleretretour.lotr.init.LOTREntities.URUK_HAI_MERCENARY_CAPTAIN,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "uruk_hai", 3));
        def("gundabad_orc_mercenary_captain", fr.alleretretour.lotr.init.LOTREntities.GUNDABAD_ORC_MERCENARY_CAPTAIN,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "orc", 8));
        def("angmar_orc_mercenary_captain", fr.alleretretour.lotr.init.LOTREntities.ANGMAR_ORC_MERCENARY_CAPTAIN,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "orc", 8));
    
        def("elk", fr.alleretretour.lotr.init.LOTREntities.ELK,
            fr.alleretretour.lotr.client.render.LOTRRenderElk::new);
    
        // --- lot 8 ombre ---
        def("isengard_snaga", fr.alleretretour.lotr.init.LOTREntities.ISENGARD_SNAGA,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "orc", 8));
        def("isengard_snaga_archer", fr.alleretretour.lotr.init.LOTREntities.ISENGARD_SNAGA_ARCHER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "orc", 8));
        def("uruk_hai_sapper", fr.alleretretour.lotr.init.LOTREntities.URUK_HAI_SAPPER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "uruk_hai", 3));
        def("uruk_hai_berserker", fr.alleretretour.lotr.init.LOTREntities.URUK_HAI_BERSERKER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "uruk_hai", 3));
        def("gundabad_uruk_archer", fr.alleretretour.lotr.init.LOTREntities.GUNDABAD_URUK_ARCHER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "uruk_hai", 3));
        def("mordor_orc_bombardier", fr.alleretretour.lotr.init.LOTREntities.MORDOR_ORC_BOMBARDIER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "orc", 8));
        def("angmar_orc_bombardier", fr.alleretretour.lotr.init.LOTREntities.ANGMAR_ORC_BOMBARDIER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "orc", 8));
        def("wood_elf", fr.alleretretour.lotr.init.LOTREntities.WOOD_ELF,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "wood_elf_male", 4, 1.0f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.LONG, true));
    
        // --- wargs de faction ---
        def("mordor_warg", fr.alleretretour.lotr.init.LOTREntities.MORDOR_WARG,
            fr.alleretretour.lotr.client.render.LOTRRenderWarg::new);
        def("uruk_warg", fr.alleretretour.lotr.init.LOTREntities.URUK_WARG,
            fr.alleretretour.lotr.client.render.LOTRRenderWarg::new);
        def("gundabad_warg", fr.alleretretour.lotr.init.LOTREntities.GUNDABAD_WARG,
            fr.alleretretour.lotr.client.render.LOTRRenderWarg::new);
        def("angmar_warg", fr.alleretretour.lotr.init.LOTREntities.ANGMAR_WARG,
            fr.alleretretour.lotr.client.render.LOTRRenderWarg::new);
    
        // --- porte-bannieres (lot 9) ---
        def("mordor_banner_bearer", fr.alleretretour.lotr.init.LOTREntities.MORDOR_BANNER_BEARER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "orc", 8));
        def("minas_morgul_banner_bearer", fr.alleretretour.lotr.init.LOTREntities.MINAS_MORGUL_BANNER_BEARER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "orc", 8));
        def("uruk_hai_banner_bearer", fr.alleretretour.lotr.init.LOTREntities.URUK_HAI_BANNER_BEARER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "uruk_hai", 3));
        def("gundabad_banner_bearer", fr.alleretretour.lotr.init.LOTREntities.GUNDABAD_BANNER_BEARER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "orc", 8));
        def("angmar_banner_bearer", fr.alleretretour.lotr.init.LOTREntities.ANGMAR_BANNER_BEARER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "orc", 8));
        def("wood_elf_banner_bearer", fr.alleretretour.lotr.init.LOTREntities.WOOD_ELF_BANNER_BEARER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "wood_elf_male", 4, 1.0f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.LONG, true));
        def("dale_banner_bearer", fr.alleretretour.lotr.init.LOTREntities.DALE_BANNER_BEARER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "dale_soldier", 3));
        def("esgaroth_banner_bearer", fr.alleretretour.lotr.init.LOTREntities.ESGAROTH_BANNER_BEARER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "dale_soldier", 3));
        def("umbar_banner_bearer", fr.alleretretour.lotr.init.LOTREntities.UMBAR_BANNER_BEARER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "haradrim_male", 5));
        def("bree_banner_bearer", fr.alleretretour.lotr.init.LOTREntities.BREE_BANNER_BEARER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "bree_male", 30));
        def("dorwinion_banner_bearer", fr.alleretretour.lotr.init.LOTREntities.DORWINION_BANNER_BEARER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "dorwinion_male", 4, 1.0f, fr.alleretretour.lotr.client.model.LOTRModelNPC.Hair.LONG, true));
        def("ranger_ithilien_banner_bearer", fr.alleretretour.lotr.init.LOTREntities.RANGER_ITHILIEN_BANNER_BEARER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "ithilien_ranger", 3));
    
        // --- trolls et bombardiers ---
        def("troll", fr.alleretretour.lotr.init.LOTREntities.TROLL,
            fr.alleretretour.lotr.client.render.LOTRRenderTroll::new);
        def("mountain_troll", fr.alleretretour.lotr.init.LOTREntities.MOUNTAIN_TROLL,
            fr.alleretretour.lotr.client.render.LOTRRenderMountainTroll::new);
        def("olog_hai", fr.alleretretour.lotr.init.LOTREntities.OLOG_HAI,
            fr.alleretretour.lotr.client.render.LOTRRenderOlogHai::new);
        def("mordor_warg_bombardier", fr.alleretretour.lotr.init.LOTREntities.MORDOR_WARG_BOMBARDIER,
            fr.alleretretour.lotr.client.render.LOTRRenderWargBombardier::new);
        def("uruk_warg_bombardier", fr.alleretretour.lotr.init.LOTREntities.URUK_WARG_BOMBARDIER,
            fr.alleretretour.lotr.client.render.LOTRRenderWargBombardier::new);
        def("angmar_warg_bombardier", fr.alleretretour.lotr.init.LOTREntities.ANGMAR_WARG_BOMBARDIER,
            fr.alleretretour.lotr.client.render.LOTRRenderWargBombardier::new);
    }

    // ==================================================================
    // CHEMIN NORMAL : RenderingRegistry au client setup
    // ==================================================================
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void registerRenderers() {
        defineRenderers();
        DEFS.forEach((name, d) -> safe("render_" + name, () ->
            net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(
                    (EntityType) d.type.get(), (IRenderFactory) d.factory)));
    }

    // ==================================================================
    // FILET DE SECURITE : verification + reparation du dispatcher
    // ==================================================================
    private static void onLoadComplete(FMLLoadCompleteEvent event) {
        event.enqueueWork(ClientSetup::verifyAndHealRenderers);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void verifyAndHealRenderers() {
        try {
            defineRenderers();
            net.minecraft.client.renderer.entity.EntityRendererManager erm =
                    net.minecraft.client.Minecraft.getInstance().getEntityRenderDispatcher();

            Map<EntityType<?>, Object> renderersMap = findRenderersMap(erm);
            if (renderersMap == null) {
                fr.alleretretour.lotr.LOTRMod.LOGGER.error(
                        "LOTR DISPATCHER : map interne des renderers introuvable par reflexion !");
                return;
            }

            List<String> missing = new ArrayList<>();
            List<String> healed = new ArrayList<>();
            for (Map.Entry<String, Def> e : DEFS.entrySet()) {
                EntityType<?> type = e.getValue().type.get();
                if (renderersMap.containsKey(type)) {
                    continue;
                }
                missing.add(e.getKey());
                try {
                    Object renderer = ((IRenderFactory) e.getValue().factory).createRenderFor(erm);
                    renderersMap.put(type, renderer);
                    healed.add(e.getKey());
                } catch (Throwable t) {
                    fr.alleretretour.lotr.LOTRMod.LOGGER.error(
                            "LOTR DISPATCHER : echec de re-application pour " + e.getKey() + " : ", t);
                }
            }

            if (missing.isEmpty()) {
                fr.alleretretour.lotr.LOTRMod.LOGGER.info(
                        "LOTR DISPATCHER : {}/{} renderers presents, aucune reparation necessaire.",
                        DEFS.size(), DEFS.size());
            } else {
                fr.alleretretour.lotr.LOTRMod.LOGGER.error(
                        "LOTR DISPATCHER : {} renderer(s) ABSENT(S) apres le chargement Forge "
                        + "(loadEntityRenderers a echoue en silence) : {}",
                        missing.size(), String.join(", ", missing));
                fr.alleretretour.lotr.LOTRMod.LOGGER.info(
                        "LOTR DISPATCHER : {} renderer(s) re-applique(s) directement : {}",
                        healed.size(), String.join(", ", healed));
            }
        } catch (Throwable t) {
            fr.alleretretour.lotr.LOTRMod.LOGGER.error("LOTR DISPATCHER : verification impossible : ", t);
        }
    }

    /**
     * Retrouve la map EntityType -> EntityRenderer du dispatcher.
     * 1) par nom de champ ("renderers", valable en dev MojMap) ;
     * 2) sinon en scannant les champs Map dont les cles sont des EntityType
     *    (robuste en production ou les noms de champs sont obfusques).
     */
    @SuppressWarnings("unchecked")
    private static Map<EntityType<?>, Object> findRenderersMap(
            net.minecraft.client.renderer.entity.EntityRendererManager erm) throws Exception {
        try {
            java.lang.reflect.Field f =
                    net.minecraft.client.renderer.entity.EntityRendererManager.class
                            .getDeclaredField("renderers");
            f.setAccessible(true);
            Object v = f.get(erm);
            if (v instanceof Map) {
                return (Map<EntityType<?>, Object>) v;
            }
        } catch (NoSuchFieldException ignored) {
            // production obfusquee : on passe au scan
        }
        for (java.lang.reflect.Field f :
                net.minecraft.client.renderer.entity.EntityRendererManager.class.getDeclaredFields()) {
            if (!Map.class.isAssignableFrom(f.getType())) {
                continue;
            }
            f.setAccessible(true);
            Object v = f.get(erm);
            if (v instanceof Map) {
                Map<?, ?> map = (Map<?, ?>) v;
                if (!map.isEmpty() && map.keySet().iterator().next() instanceof EntityType) {
                    return (Map<EntityType<?>, Object>) map;
                }
            }
        }
        return null;
    }

    private static void safe(String name, Runnable r) {
        try {
            r.run();
        } catch (Throwable t) {
            FAILURES.add(name);
            fr.alleretretour.lotr.LOTRMod.LOGGER.error("LOTR ERREUR client setup [" + name + "] : " + t);
        }
    }

    private static void onClientSetup(FMLClientSetupEvent event) {
        // ===== 1. RENDERERS D'ENTITES (registres peuples a ce stade) =====
        registerRenderers();

        // ===== 2. ECRANS DES CONTAINERS =====
        safe("screen_faction_crafting", () ->
            net.minecraft.client.gui.ScreenManager.register(
                fr.alleretretour.lotr.init.LOTRContainers.FACTION_CRAFTING.get(),
                fr.alleretretour.lotr.client.gui.LOTRScreenFactionCrafting::new));
        safe("screen_barrel", () ->
            net.minecraft.client.gui.ScreenManager.register(
                fr.alleretretour.lotr.init.LOTRContainers.BARREL.get(),
                fr.alleretretour.lotr.client.gui.LOTRScreenBarrel::new));
        safe("screen_trade", () ->
            net.minecraft.client.gui.ScreenManager.register(
                fr.alleretretour.lotr.init.LOTRContainers.TRADE.get(),
                fr.alleretretour.lotr.client.gui.LOTRScreenTrade::new));
        safe("screen_hire", () ->
            net.minecraft.client.gui.ScreenManager.register(
                fr.alleretretour.lotr.init.LOTRContainers.HIRE.get(),
                fr.alleretretour.lotr.client.gui.LOTRScreenHire::new));

        // ===== 3. TOUCHE + OVERLAY D'ALIGNEMENT =====
        safe("key_alignment", () -> {
            ALIGNMENT_KEY = new net.minecraft.client.settings.KeyBinding(
                    "key.lotr.factions", org.lwjgl.glfw.GLFW.GLFW_KEY_O, "key.categories.lotr");
            net.minecraftforge.fml.client.registry.ClientRegistry.registerKeyBinding(ALIGNMENT_KEY);
            net.minecraftforge.common.MinecraftForge.EVENT_BUS.addListener(
                    fr.alleretretour.lotr.client.LOTRHudOverlay::onRenderOverlay);
            net.minecraftforge.common.MinecraftForge.EVENT_BUS.addListener(ClientSetup::onClientTick);
        });

        // ===== 4. PROPRIETE DE LA PIECE (paliers 1/10/100) =====
        event.enqueueWork(() -> safe("coin_tier", () ->
            net.minecraft.item.ItemModelsProperties.register(
                fr.alleretretour.lotr.init.LOTRItems.SILVER_COIN.get(),
                new net.minecraft.util.ResourceLocation("lotr", "coin_tier"),
                (stack, world, entity) -> fr.alleretretour.lotr.item.LOTRItemCoin.coinTier(stack))));

        // ===== 5. RENDU CUTOUT (feuillages, fleurs, torches, portes, tonneau) =====
        event.enqueueWork(() -> safe("cutout", () ->
            fr.alleretretour.lotr.init.LOTRBlocks.BLOCKS.getEntries().forEach(ro -> {
                net.minecraft.block.Block b = ro.get();
                if (b instanceof net.minecraft.block.LeavesBlock) {
                    net.minecraft.client.renderer.RenderTypeLookup.setRenderLayer(
                            b, net.minecraft.client.renderer.RenderType.cutoutMipped());
                } else if (b instanceof net.minecraft.block.FlowerBlock
                        || b instanceof net.minecraft.block.TorchBlock
                        || b instanceof net.minecraft.block.WallTorchBlock
                        || b instanceof net.minecraft.block.LadderBlock
                        || b instanceof net.minecraft.block.DoorBlock
                        || b instanceof net.minecraft.block.TrapDoorBlock
                        || b instanceof fr.alleretretour.lotr.block.LOTRBlockBarrel) {
                    net.minecraft.client.renderer.RenderTypeLookup.setRenderLayer(
                            b, net.minecraft.client.renderer.RenderType.cutout());
                }
            })));

        // ===== 6. BILAN DE SETUP =====
        event.enqueueWork(() -> {
            if (FAILURES.isEmpty()) {
                fr.alleretretour.lotr.LOTRMod.LOGGER.info(
                        "LOTR client setup : OK, aucun echec d'enregistrement.");
            } else {
                fr.alleretretour.lotr.LOTRMod.LOGGER.error(
                        "LOTR client setup : " + FAILURES.size()
                        + " ECHEC(S) d'enregistrement -> " + String.join(", ", FAILURES));
            }
        });
    }

    private static void onClientTick(net.minecraftforge.event.TickEvent.ClientTickEvent event) {
        if (event.phase != net.minecraftforge.event.TickEvent.Phase.END || ALIGNMENT_KEY == null) {
            return;
        }
        while (ALIGNMENT_KEY.consumeClick()) {
            net.minecraft.client.Minecraft mc = net.minecraft.client.Minecraft.getInstance();
            if (mc.screen == null && mc.player != null) {
                mc.setScreen(new fr.alleretretour.lotr.client.gui.LOTRScreenFactions());
            }
        }
    }

    /**
     * Couleurs des oeufs d'apparition.
     * SpawnEggItem.BY_ID est indexe par EntityType ; nos oeufs passent null au
     * constructeur (le type n'existe pas encore), ils s'ecrasent donc tous sous
     * la meme cle et SpawnEggItem.eggs() n'en expose qu'un seul : le gestionnaire
     * de couleurs vanilla n'en teintait qu'un, les autres restaient blancs.
     * On enregistre donc nos propres teintes pour TOUS les oeufs du mod.
     */
    private static void onItemColors(net.minecraftforge.client.event.ColorHandlerEvent.Item event) {
        java.util.List<net.minecraft.item.Item> eggs = new ArrayList<>();
        for (RegistryObject<net.minecraft.item.Item> ro
                : fr.alleretretour.lotr.init.LOTRItems.ITEMS.getEntries()) {
            net.minecraft.item.Item item = ro.get();
            if (item instanceof net.minecraft.item.SpawnEggItem) {
                eggs.add(item);
            }
        }
        if (eggs.isEmpty()) {
            return;
        }
        event.getItemColors().register(
                (stack, layer) -> ((net.minecraft.item.SpawnEggItem) stack.getItem())
                        .getColor(layer),
                eggs.toArray(new net.minecraft.util.IItemProvider[0]));
        fr.alleretretour.lotr.LOTRMod.LOGGER.info("LOTR COULEURS : {} oeufs d'apparition teintes", eggs.size());
    }
}
