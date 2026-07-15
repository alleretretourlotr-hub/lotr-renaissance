package fr.alleretretour.lotr;

import fr.alleretretour.lotr.client.ClientSetup;
import fr.alleretretour.lotr.init.LOTRBlocks;
import fr.alleretretour.lotr.init.LOTRContainers;
import fr.alleretretour.lotr.init.LOTREnchantments;
import fr.alleretretour.lotr.init.LOTREntities;
import fr.alleretretour.lotr.init.LOTRItems;
import fr.alleretretour.lotr.init.LOTRRecipeTypes;
import fr.alleretretour.lotr.init.LOTRSounds;
import fr.alleretretour.lotr.init.LOTRTileEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Aller & Retour - Portage LOTR 1.16.5
 * Port prive du mod LOTR Legacy (Mevans) - usage serveur uniquement.
 */
@Mod(LOTRMod.MOD_ID)
public class LOTRMod {

    public static final String MOD_ID = "lotr";
    public static final Logger LOGGER = LogManager.getLogger("AllerEtRetour-LOTR");

    public LOTRMod() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Registres differes - l'ordre n'a pas d'importance, Forge gere les phases
        LOTRBlocks.BLOCKS.register(modBus);
        fr.alleretretour.lotr.init.LOTRBlocksWood.init();
        LOTRItems.ITEMS.register(modBus);
        fr.alleretretour.lotr.init.LOTRItemsCombat.init();
        fr.alleretretour.lotr.init.LOTRItemsRanged.init();
        fr.alleretretour.lotr.init.LOTRItemsFoodTools.init();
        fr.alleretretour.lotr.init.LOTRItemsDrinks.init();
        fr.alleretretour.lotr.init.LOTRBlocksDoors.init();
        fr.alleretretour.lotr.init.LOTRBlocksRock.init();
        fr.alleretretour.lotr.init.LOTRItemsMinerals.init();
        fr.alleretretour.lotr.init.LOTRBlocksStoneStairs.init();
        fr.alleretretour.lotr.init.LOTRBlocksWalls.init();
        fr.alleretretour.lotr.init.LOTRBlocksPillars.init();
        fr.alleretretour.lotr.init.LOTRBlocksFlora.init();
        fr.alleretretour.lotr.init.LOTRBlocksBarrel.init();
        fr.alleretretour.lotr.init.LOTRBlocksMisc.init();
        fr.alleretretour.lotr.init.LOTRSpawnEggs.init();
        fr.alleretretour.lotr.init.LOTRBrewingRecipesData.init();
        fr.alleretretour.lotr.init.LOTRBlocksTables.init();
        LOTRRecipeTypes.SERIALIZERS.register(modBus);
        LOTREntities.ENTITIES.register(modBus);
        LOTRTileEntities.TILE_ENTITIES.register(modBus);
        LOTRContainers.CONTAINERS.register(modBus);
        LOTRSounds.SOUNDS.register(modBus);
        LOTREnchantments.ENCHANTMENTS.register(modBus);

        modBus.addListener(this::commonSetup);
        modBus.addListener(this::onEntityAttributes);

        // Bus Forge : evenements de jeu (commandes, capabilities joueur...)
        MinecraftForge.EVENT_BUS.addListener(this::onRegisterCommands);

        // Setup client isole - jamais charge sur le serveur dedie (Mohist safe)
        if (FMLEnvironment.dist == Dist.CLIENT) {
            ClientSetup.init(modBus);
        }
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("LOTR Aller & Retour - common setup");
        fr.alleretretour.lotr.item.LOTRRepairMaterials.wireUp();

        // Phase 3 : capability d'alignement + canal reseau
        // (doivent attendre le common setup, pas le constructeur)
        event.enqueueWork(() -> {
            fr.alleretretour.lotr.fac.LOTRPlayerDataProvider.register();
            fr.alleretretour.lotr.network.LOTRPacketHandler.register();
        });
    }

    private void onEntityAttributes(net.minecraftforge.event.entity.EntityAttributeCreationEvent event) {
        event.put(fr.alleretretour.lotr.init.LOTREntities.GONDOR_SOLDIER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityGondorSoldier.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.GONDOR_ARCHER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityGondorArcher.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.GONDOR_TOWER_GUARD.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityGondorTowerGuard.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.GONDORIAN_CAPTAIN.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityGondorianCaptain.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.GONDOR_LEVYMAN.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityGondorLevyman.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.MORDOR_ORC.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityMordorOrc.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.MORDOR_ORC_ARCHER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityMordorOrcArcher.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.URUK_HAI.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityUrukHai.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.GONDOR_BLACKSMITH.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityGondorBlacksmith.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.ROHIRRIM_WARRIOR.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityRohirrimWarrior.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.ROHIRRIM_ARCHER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityRohirrimArcher.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.ROHIRRIM_MARSHAL.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityRohirrimMarshal.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.ROHAN_SHIELDMAIDEN.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityRohanShieldmaiden.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.GALADHRIM_WARRIOR.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityGaladhrimWarrior.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.GALADHRIM_WARDEN.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityGaladhrimWarden.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.GALADHRIM_LORD.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityGaladhrimLord.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.HIGH_ELF_WARRIOR.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityHighElfWarrior.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.HIGH_ELF_LORD.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityHighElfLord.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.WOOD_ELF_WARRIOR.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityWoodElfWarrior.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.WOOD_ELF_SCOUT.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityWoodElfScout.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.WOOD_ELF_CAPTAIN.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityWoodElfCaptain.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.RIVENDELL_WARRIOR.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityRivendellWarrior.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.RIVENDELL_LORD.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityRivendellLord.createAttributes().build());
    }

    private void onRegisterCommands(net.minecraftforge.event.RegisterCommandsEvent event) {
        fr.alleretretour.lotr.command.LOTRCommandAlignment.register(event.getDispatcher());
    }
}
