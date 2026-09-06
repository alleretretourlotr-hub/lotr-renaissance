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
        fr.alleretretour.lotr.init.LOTRItemsBanners.init();
        fr.alleretretour.lotr.init.LOTRItemsRhun.init();
        fr.alleretretour.lotr.init.LOTRItemsMountArmor.init();
        fr.alleretretour.lotr.init.LOTRBlocksStorage.init();
        fr.alleretretour.lotr.init.LOTRItemsFoodTools.init();
        fr.alleretretour.lotr.init.LOTRItemsDrinks.init();
        fr.alleretretour.lotr.init.LOTRBlocksDoors.init();
        fr.alleretretour.lotr.init.LOTRBlocksRock.init();
        fr.alleretretour.lotr.init.LOTRBlocksRuins.init();
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
        fr.alleretretour.lotr.init.LOTRBiomes.register(modBus);
        fr.alleretretour.lotr.init.LOTRFeatures.register(modBus);

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
            fr.alleretretour.lotr.world.LOTRDimensions.registerCodecs();
            fr.alleretretour.lotr.init.LOTRFeatures.registerConfigured();
            fr.alleretretour.lotr.init.LOTRTreeFeatures.register();
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
        event.put(fr.alleretretour.lotr.init.LOTREntities.DWARF.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityDwarf.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.DWARF_WARRIOR.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityDwarfWarrior.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.DWARF_COMMANDER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityDwarfCommander.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.BLUE_DWARF_WARRIOR.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityBlueDwarfWarrior.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.HOBBIT.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityHobbit.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.HOBBIT_BOUNDER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityHobbitBounder.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.BREE_MAN.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityBreeMan.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.BREE_GUARD.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityBreeGuard.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.RANGER_NORTH.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityRangerNorth.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.RANGER_NORTH_CAPTAIN.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityRangerNorthCaptain.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.DALE_SOLDIER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityDaleSoldier.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.DALE_ARCHER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityDaleArcher.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.DALE_LEVYMAN.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityDaleLevyman.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.DORWINION_GUARD.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityDorwinionGuard.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.DORWINION_ELF_WARRIOR.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityDorwinionElfWarrior.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.DORWINION_ELF_ARCHER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityDorwinionElfArcher.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.ANGMAR_ORC.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityAngmarOrc.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.ANGMAR_ORC_ARCHER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityAngmarOrcArcher.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.ANGMAR_HILLMAN_WARRIOR.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityAngmarHillmanWarrior.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.GUNDABAD_ORC.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityGundabadOrc.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.GUNDABAD_ORC_ARCHER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityGundabadOrcArcher.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.GUNDABAD_URUK.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityGundabadUruk.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.DOL_GULDUR_ORC.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityDolGuldurOrc.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.DOL_GULDUR_ORC_ARCHER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityDolGuldurOrcArcher.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.DUNLENDING.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityDunlending.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.DUNLENDING_WARRIOR.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityDunlendingWarrior.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.DUNLENDING_BERSERKER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityDunlendingBerserker.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.NEAR_HARADRIM_WARRIOR.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityNearHaradrimWarrior.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.NEAR_HARADRIM_ARCHER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityNearHaradrimArcher.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.CORSAIR.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityCorsair.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.UMBAR_WARRIOR.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityUmbarWarrior.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.UMBAR_ARCHER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityUmbarArcher.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.EASTERLING_WARRIOR.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityEasterlingWarrior.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.EASTERLING_ARCHER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityEasterlingArcher.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.EASTERLING_GOLD_WARRIOR.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityEasterlingGoldWarrior.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.MOREDAIN_WARRIOR.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityMoredainWarrior.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.MOREDAIN_HUNTSMAN.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityMoredainHuntsman.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.TAUREDAIN_WARRIOR.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityTauredainWarrior.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.TAUREDAIN_BLOWGUNNER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityTauredainBlowgunner.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.HALF_TROLL.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityHalfTroll.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.HALF_TROLL_WARRIOR.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityHalfTrollWarrior.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.DWARF_AXE_THROWER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityDwarfAxeThrower.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.BLUE_DWARF_AXE_THROWER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityBlueDwarfAxeThrower.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.DORWINION_CROSSBOWER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityDorwinionCrossbower.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.URUK_HAI_CROSSBOWER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityUrukHaiCrossbower.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.DWARF_SMITH.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityDwarfSmith.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.BLUE_MOUNTAINS_SMITH.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityBlueMountainsSmith.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.HOBBIT_BARTENDER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityHobbitBartender.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.BREE_INNKEEPER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityBreeInnkeeper.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.DALE_BLACKSMITH.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityDaleBlacksmith.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.EASTERLING_BLACKSMITH.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityEasterlingBlacksmith.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.NEAR_HARAD_MERCHANT.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityNearHaradMerchant.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.MOREDAIN_TRADER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityMoredainTrader.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.ANGMAR_ORC_TRADER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityAngmarOrcTrader.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.DOL_GULDUR_ORC_TRADER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityDolGuldurOrcTrader.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.GONDOR_BANNER_BEARER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityGondorBannerBearer.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.ROHAN_BANNER_BEARER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityRohanBannerBearer.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.DWARF_BANNER_BEARER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityDwarfBannerBearer.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.BLUE_DWARF_BANNER_BEARER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityBlueDwarfBannerBearer.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.HIGH_ELF_BANNER_BEARER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityHighElfBannerBearer.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.RIVENDELL_BANNER_BEARER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityRivendellBannerBearer.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.GALADHRIM_BANNER_BEARER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityGaladhrimBannerBearer.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.RANGER_NORTH_BANNER_BEARER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityRangerNorthBannerBearer.createAttributes().build());
    
        event.put(fr.alleretretour.lotr.init.LOTREntities.GALADHRIM_ELF.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityGaladhrimElf.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.HIGH_ELF.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityHighElf.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.RIVENDELL_ELF.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityRivendellElf.createAttributes().build());
    
        event.put(fr.alleretretour.lotr.init.LOTREntities.HORSE.get(),
                net.minecraft.entity.passive.horse.AbstractHorseEntity
                        .createBaseHorseAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.WILD_BOAR.get(),
                fr.alleretretour.lotr.entity.animal.LOTREntityWildBoar
                        .createAttributes().build());
    
        event.put(fr.alleretretour.lotr.init.LOTREntities.HOBBIT_SHIRRIFF.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityHobbitShirriff.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.BREE_RUFFIAN.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityBreeRuffian.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.RUFFIAN_BRUTE.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityRuffianBrute.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.RUFFIAN_SPY.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityRuffianSpy.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.RANGER_ITHILIEN.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityRangerIthilien.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.RANGER_ITHILIEN_CAPTAIN.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityRangerIthilienCaptain.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.HARNEDOR_WARRIOR.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityHarnedorWarrior.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.HARNEDOR_ARCHER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityHarnedorArcher.createAttributes().build());
    
        event.put(fr.alleretretour.lotr.init.LOTREntities.ANGMAR_HILLMAN_CHIEFTAIN.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityAngmarHillmanChieftain.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.DOL_GULDUR_ORC_CHIEFTAIN.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityDolGuldurOrcChieftain.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.DUNLENDING_WARLORD.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityDunlendingWarlord.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.EASTERLING_WARLORD.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityEasterlingWarlord.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.HALF_TROLL_WARLORD.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityHalfTrollWarlord.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.HARNEDOR_WARLORD.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityHarnedorWarlord.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.NEAR_HARADRIM_WARLORD.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityNearHaradrimWarlord.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.MOREDAIN_CHIEFTAIN.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityMoredainChieftain.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.TAUREDAIN_CHIEFTAIN.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityTauredainChieftain.createAttributes().build());
    
        event.put(fr.alleretretour.lotr.init.LOTREntities.RHINO.get(),
                fr.alleretretour.lotr.entity.animal.LOTREntityRhino.createAttributes().build());
    
        event.put(fr.alleretretour.lotr.init.LOTREntities.ANGMAR_HILLMAN.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityAngmarHillman.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.ANGMAR_HILLMAN_AXE_THROWER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityAngmarHillmanAxeThrower.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.DUNLENDING_ARCHER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityDunlendingArcher.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.DUNLENDING_AXE_THROWER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityDunlendingAxeThrower.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.EASTERLING_LEVYMAN.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityEasterlingLevyman.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.SOUTHRON_CHAMPION.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntitySouthronChampion.createAttributes().build());
    
        event.put(fr.alleretretour.lotr.init.LOTREntities.ANGMAR_HILLMAN_BANNER_BEARER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityAngmarHillmanBannerBearer.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.DOL_GULDUR_BANNER_BEARER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityDolGuldurBannerBearer.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.DUNLENDING_BANNER_BEARER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityDunlendingBannerBearer.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.EASTERLING_BANNER_BEARER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityEasterlingBannerBearer.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.HALF_TROLL_BANNER_BEARER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityHalfTrollBannerBearer.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.HARNEDOR_BANNER_BEARER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityHarnedorBannerBearer.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.NEAR_HARAD_BANNER_BEARER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityNearHaradBannerBearer.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.MOREDAIN_BANNER_BEARER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityMoredainBannerBearer.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.TAUREDAIN_BANNER_BEARER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityTauredainBannerBearer.createAttributes().build());
    
        event.put(fr.alleretretour.lotr.init.LOTREntities.MIRKWOOD_SPIDER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityMirkwoodSpider.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.ZEBRA.get(),
                net.minecraft.entity.passive.horse.AbstractHorseEntity
                        .createBaseHorseAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.MIRK_TROLL.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityMirkTroll.createAttributes().build());
    
        event.put(fr.alleretretour.lotr.init.LOTREntities.EASTERLING_FIRE_THROWER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityEasterlingFireThrower
                        .createAttributes().build());
    

    
        event.put(fr.alleretretour.lotr.init.LOTREntities.DALE_CAPTAIN.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityDaleCaptain.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.BLUE_DWARF_COMMANDER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityBlueDwarfCommander.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.DORWINION_CAPTAIN.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityDorwinionCaptain.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.UMBAR_CAPTAIN.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityUmbarCaptain.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.CORSAIR_CAPTAIN.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityCorsairCaptain.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.BREE_CAPTAIN.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityBreeCaptain.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.MORDOR_ORC_MERCENARY_CAPTAIN.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityMordorOrcMercenaryCaptain.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.URUK_HAI_MERCENARY_CAPTAIN.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityUrukHaiMercenaryCaptain.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.GUNDABAD_ORC_MERCENARY_CAPTAIN.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityGundabadOrcMercenaryCaptain.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.ANGMAR_ORC_MERCENARY_CAPTAIN.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityAngmarOrcMercenaryCaptain.createAttributes().build());
    
        event.put(fr.alleretretour.lotr.init.LOTREntities.ELK.get(),
                net.minecraft.entity.passive.horse.AbstractHorseEntity
                        .createBaseHorseAttributes().build());
    
        event.put(fr.alleretretour.lotr.init.LOTREntities.ISENGARD_SNAGA.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityIsengardSnaga.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.ISENGARD_SNAGA_ARCHER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityIsengardSnagaArcher.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.URUK_HAI_SAPPER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityUrukHaiSapper.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.URUK_HAI_BERSERKER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityUrukHaiBerserker.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.GUNDABAD_URUK_ARCHER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityGundabadUrukArcher.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.MORDOR_ORC_BOMBARDIER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityMordorOrcBombardier.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.ANGMAR_ORC_BOMBARDIER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityAngmarOrcBombardier.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.WOOD_ELF.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityWoodElf.createAttributes().build());
    
        event.put(fr.alleretretour.lotr.init.LOTREntities.MORDOR_WARG.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityMordorWarg.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.URUK_WARG.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityUrukWarg.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.GUNDABAD_WARG.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityGundabadWarg.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.ANGMAR_WARG.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityAngmarWarg.createAttributes().build());
    
        event.put(fr.alleretretour.lotr.init.LOTREntities.MORDOR_BANNER_BEARER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityMordorBannerBearer.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.MINAS_MORGUL_BANNER_BEARER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityMinasMorgulBannerBearer.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.URUK_HAI_BANNER_BEARER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityUrukHaiBannerBearer.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.GUNDABAD_BANNER_BEARER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityGundabadBannerBearer.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.ANGMAR_BANNER_BEARER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityAngmarBannerBearer.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.WOOD_ELF_BANNER_BEARER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityWoodElfBannerBearer.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.DALE_BANNER_BEARER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityDaleBannerBearer.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.ESGAROTH_BANNER_BEARER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityEsgarothBannerBearer.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.UMBAR_BANNER_BEARER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityUmbarBannerBearer.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.BREE_BANNER_BEARER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityBreeBannerBearer.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.DORWINION_BANNER_BEARER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityDorwinionBannerBearer.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.RANGER_ITHILIEN_BANNER_BEARER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityRangerIthilienBannerBearer.createAttributes().build());
    
        event.put(fr.alleretretour.lotr.init.LOTREntities.TROLL.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityTroll.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.MOUNTAIN_TROLL.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityMountainTroll.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.OLOG_HAI.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityOlogHai.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.MORDOR_WARG_BOMBARDIER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityMordorWargBombardier.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.URUK_WARG_BOMBARDIER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityUrukWargBombardier.createAttributes().build());
        event.put(fr.alleretretour.lotr.init.LOTREntities.ANGMAR_WARG_BOMBARDIER.get(),
                fr.alleretretour.lotr.entity.npc.LOTREntityAngmarWargBombardier.createAttributes().build());
    }

    private void onRegisterCommands(net.minecraftforge.event.RegisterCommandsEvent event) {
        fr.alleretretour.lotr.command.LOTRCommandWaypoint.register(event.getDispatcher());
        fr.alleretretour.lotr.command.LOTRCommandAlignment.register(event.getDispatcher());
    }
}
