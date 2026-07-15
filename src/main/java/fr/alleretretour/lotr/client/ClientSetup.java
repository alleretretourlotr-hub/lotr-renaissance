package fr.alleretretour.lotr.client;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * Setup client BLINDE : chaque enregistrement est isole - si une ligne echoue,
 * elle est logguee ("LOTR ERREUR") et n'empeche JAMAIS les suivantes.
 *
 * CORRECTIF RENDERERS (bug "entite SANS RENDERER") :
 * les renderers etaient enregistres au CONSTRUCTEUR du mod, donc AVANT que les
 * registres Forge ne soient peuples -> chaque LOTREntities.XXX.get() levait une
 * exception ("Registry Object not present"), avalee par safe(), et AUCUN
 * renderer n'etait enregistre. En 1.16.5, le bon moment est FMLClientSetupEvent :
 * les registres sont peuples et RenderingRegistry est encore accepte.
 * (La note precedente "FMLClientSetupEvent peut arriver trop tard" etait fausse :
 * c'est l'approche canonique documentee de Forge 1.16.x.)
 *
 * Ordre : renderers d'entites d'abord (critiques : sans eux, PNJ invisibles),
 * puis ecrans, touche, overlay, proprietes d'items, rendu cutout.
 * Un BILAN est loggue en fin de setup : nombre d'echecs + noms.
 */
public class ClientSetup {

    public static net.minecraft.client.settings.KeyBinding ALIGNMENT_KEY;

    /** Echecs collectes pendant le setup, pour le bilan final. */
    private static final java.util.List<String> FAILURES = new java.util.ArrayList<>();

    public static void init(IEventBus modBus) {
        // Ne rien enregistrer ici : les RegistryObject ne sont pas encore valides.
        modBus.addListener(ClientSetup::onClientSetup);
    }

    private static void registerRenderers() {
        safe("render_thrown_weapon", () ->
            net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(
                fr.alleretretour.lotr.init.LOTREntities.THROWN_WEAPON.get(),
                fr.alleretretour.lotr.client.render.LOTRThrownWeaponRenderer::new));
        safe("render_crossbow_bolt", () ->
            net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(
                fr.alleretretour.lotr.init.LOTREntities.CROSSBOW_BOLT.get(),
                manager -> new net.minecraft.client.renderer.entity.ArrowRenderer<
                        fr.alleretretour.lotr.entity.projectile.LOTREntityCrossbowBolt>(manager) {
                    @Override
                    public net.minecraft.util.ResourceLocation getTextureLocation(
                            fr.alleretretour.lotr.entity.projectile.LOTREntityCrossbowBolt e) {
                        return new net.minecraft.util.ResourceLocation(
                                "minecraft", "textures/entity/projectiles/arrow.png");
                    }
                }));
        safe("render_dart", () ->
            net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(
                fr.alleretretour.lotr.init.LOTREntities.DART.get(),
                manager -> new net.minecraft.client.renderer.entity.ArrowRenderer<
                        fr.alleretretour.lotr.entity.projectile.LOTREntityDart>(manager) {
                    @Override
                    public net.minecraft.util.ResourceLocation getTextureLocation(
                            fr.alleretretour.lotr.entity.projectile.LOTREntityDart e) {
                        return new net.minecraft.util.ResourceLocation(
                                "minecraft", "textures/entity/projectiles/arrow.png");
                    }
                }));

        // ===== Gondor =====
        safe("render_gondor_soldier", () ->
            net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(
                fr.alleretretour.lotr.init.LOTREntities.GONDOR_SOLDIER.get(),
                m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "gondor_male", 10)));
        safe("render_gondor_archer", () ->
            net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(
                fr.alleretretour.lotr.init.LOTREntities.GONDOR_ARCHER.get(),
                m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "gondor_male", 10)));
        safe("render_gondor_tower_guard", () ->
            net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(
                fr.alleretretour.lotr.init.LOTREntities.GONDOR_TOWER_GUARD.get(),
                m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "gondor_male", 10)));
        safe("render_gondorian_captain", () ->
            net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(
                fr.alleretretour.lotr.init.LOTREntities.GONDORIAN_CAPTAIN.get(),
                m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "gondor_male", 10)));
        safe("render_gondor_levyman", () ->
            net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(
                fr.alleretretour.lotr.init.LOTREntities.GONDOR_LEVYMAN.get(),
                m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "gondor_male", 10)));
        safe("render_gondor_blacksmith", () ->
            net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(
                fr.alleretretour.lotr.init.LOTREntities.GONDOR_BLACKSMITH.get(),
                m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "gondor_male", 10)));

        // ===== Mordor / Isengard =====
        safe("render_mordor_orc", () ->
            net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(
                fr.alleretretour.lotr.init.LOTREntities.MORDOR_ORC.get(),
                m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "orc", 8)));
        safe("render_mordor_orc_archer", () ->
            net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(
                fr.alleretretour.lotr.init.LOTREntities.MORDOR_ORC_ARCHER.get(),
                m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "orc", 8)));
        safe("render_uruk_hai", () ->
            net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(
                fr.alleretretour.lotr.init.LOTREntities.URUK_HAI.get(),
                m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "uruk_hai", 3)));

        // ===== Rohan =====
        safe("render_rohirrim_warrior", () ->
            net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(
                fr.alleretretour.lotr.init.LOTREntities.ROHIRRIM_WARRIOR.get(),
                m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "rohan_male", 6)));
        safe("render_rohirrim_archer", () ->
            net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(
                fr.alleretretour.lotr.init.LOTREntities.ROHIRRIM_ARCHER.get(),
                m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "rohan_male", 6)));
        safe("render_rohirrim_marshal", () ->
            net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(
                fr.alleretretour.lotr.init.LOTREntities.ROHIRRIM_MARSHAL.get(),
                m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "rohan_male", 6)));
        safe("render_rohan_shieldmaiden", () ->
            net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(
                fr.alleretretour.lotr.init.LOTREntities.ROHAN_SHIELDMAIDEN.get(),
                m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "shieldmaiden", 3)));

        // ===== Elfes =====
        safe("render_galadhrim_warrior", () ->
            net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(
                fr.alleretretour.lotr.init.LOTREntities.GALADHRIM_WARRIOR.get(),
                m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "galadhrim_male", 4)));
        safe("render_galadhrim_warden", () ->
            net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(
                fr.alleretretour.lotr.init.LOTREntities.GALADHRIM_WARDEN.get(),
                m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "galadhrim_male", 4)));
        safe("render_galadhrim_lord", () ->
            net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(
                fr.alleretretour.lotr.init.LOTREntities.GALADHRIM_LORD.get(),
                m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "galadhrim_male", 4)));
        safe("render_high_elf_warrior", () ->
            net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(
                fr.alleretretour.lotr.init.LOTREntities.HIGH_ELF_WARRIOR.get(),
                m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "highElf_male", 18)));
        safe("render_high_elf_lord", () ->
            net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(
                fr.alleretretour.lotr.init.LOTREntities.HIGH_ELF_LORD.get(),
                m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "highElf_male", 18)));
        safe("render_wood_elf_warrior", () ->
            net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(
                fr.alleretretour.lotr.init.LOTREntities.WOOD_ELF_WARRIOR.get(),
                m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "woodElf_male", 4)));
        safe("render_wood_elf_scout", () ->
            net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(
                fr.alleretretour.lotr.init.LOTREntities.WOOD_ELF_SCOUT.get(),
                m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "woodElf_male", 4)));
        safe("render_wood_elf_captain", () ->
            net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(
                fr.alleretretour.lotr.init.LOTREntities.WOOD_ELF_CAPTAIN.get(),
                m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "woodElf_male", 4)));
        safe("render_rivendell_warrior", () ->
            net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(
                fr.alleretretour.lotr.init.LOTREntities.RIVENDELL_WARRIOR.get(),
                m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "highElf_male", 18)));
        safe("render_rivendell_lord", () ->
            net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(
                fr.alleretretour.lotr.init.LOTREntities.RIVENDELL_LORD.get(),
                m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "highElf_male", 18)));
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

        // ===== 6. BILAN DE SETUP (visible d'un coup d'oeil au demarrage) =====
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
}
