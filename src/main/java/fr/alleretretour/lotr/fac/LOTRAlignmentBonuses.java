package fr.alleretretour.lotr.fac;

import fr.alleretretour.lotr.LOTRMod;
import fr.alleretretour.lotr.network.LOTRPacketHandler;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Gains d'alignement par kill. Les PNJ s'enregistrent via register().
 * Tuer un membre de la faction X : gain aupres des ennemis de X,
 * perte directe aupres de X (comme l'original).
 */
@Mod.EventBusSubscriber(modid = LOTRMod.MOD_ID)
public class LOTRAlignmentBonuses {

    private static final Map<Supplier<EntityType<?>>, Entry> PENDING = new HashMap<>();
    private static final Map<EntityType<?>, Entry> BONUSES = new HashMap<>();
    private static boolean resolved;

    public static class Entry {
        public final LOTRFaction faction;
        public final float bonus;

        public Entry(LOTRFaction faction, float bonus) {
            this.faction = faction;
            this.bonus = bonus;
        }
    }

    /** Enregistrement differe (RegistryObject pas encore resolus au chargement). */
    public static void register(Supplier<EntityType<?>> type, LOTRFaction faction, float bonus) {
        PENDING.put(type, new Entry(faction, bonus));
    }

    private static void resolve() {
        if (!resolved) {
            for (Map.Entry<Supplier<EntityType<?>>, Entry> e : PENDING.entrySet()) {
                BONUSES.put(e.getKey().get(), e.getValue());
            }
            resolved = true;
        }
    }

    static {
        register(() -> fr.alleretretour.lotr.init.LOTREntities.GONDOR_SOLDIER.get(),
                LOTRFaction.GONDOR, 1.0f);
        register(() -> fr.alleretretour.lotr.init.LOTREntities.GONDOR_ARCHER.get(),
                LOTRFaction.GONDOR, 1.0f);
        register(() -> fr.alleretretour.lotr.init.LOTREntities.GONDOR_TOWER_GUARD.get(),
                LOTRFaction.GONDOR, 2.0f);
        register(() -> fr.alleretretour.lotr.init.LOTREntities.GONDORIAN_CAPTAIN.get(),
                LOTRFaction.GONDOR, 2.0f);
        register(() -> fr.alleretretour.lotr.init.LOTREntities.GONDOR_LEVYMAN.get(),
                LOTRFaction.GONDOR, 1.0f);
        register(() -> fr.alleretretour.lotr.init.LOTREntities.MORDOR_ORC.get(),
                LOTRFaction.MORDOR, 1.0f);
        register(() -> fr.alleretretour.lotr.init.LOTREntities.MORDOR_ORC_ARCHER.get(),
                LOTRFaction.MORDOR, 1.0f);
        register(() -> fr.alleretretour.lotr.init.LOTREntities.URUK_HAI.get(),
                LOTRFaction.ISENGARD, 2.0f);
        register(() -> fr.alleretretour.lotr.init.LOTREntities.ROHIRRIM_WARRIOR.get(), LOTRFaction.ROHAN, 2.0f);
        register(() -> fr.alleretretour.lotr.init.LOTREntities.ROHIRRIM_ARCHER.get(), LOTRFaction.ROHAN, 1.0f);
        register(() -> fr.alleretretour.lotr.init.LOTREntities.ROHIRRIM_MARSHAL.get(), LOTRFaction.ROHAN, 5.0f);
        register(() -> fr.alleretretour.lotr.init.LOTREntities.ROHAN_SHIELDMAIDEN.get(), LOTRFaction.ROHAN, 1.0f);
        register(() -> fr.alleretretour.lotr.init.LOTREntities.GALADHRIM_WARRIOR.get(), LOTRFaction.LOTHLORIEN, 2.0f);
        register(() -> fr.alleretretour.lotr.init.LOTREntities.GALADHRIM_WARDEN.get(), LOTRFaction.LOTHLORIEN, 2.0f);
        register(() -> fr.alleretretour.lotr.init.LOTREntities.GALADHRIM_LORD.get(), LOTRFaction.LOTHLORIEN, 5.0f);
        register(() -> fr.alleretretour.lotr.init.LOTREntities.HIGH_ELF_WARRIOR.get(), LOTRFaction.HIGH_ELF, 2.0f);
        register(() -> fr.alleretretour.lotr.init.LOTREntities.HIGH_ELF_LORD.get(), LOTRFaction.HIGH_ELF, 5.0f);
        register(() -> fr.alleretretour.lotr.init.LOTREntities.WOOD_ELF_WARRIOR.get(), LOTRFaction.WOOD_ELF, 2.0f);
        register(() -> fr.alleretretour.lotr.init.LOTREntities.WOOD_ELF_SCOUT.get(), LOTRFaction.WOOD_ELF, 2.0f);
        register(() -> fr.alleretretour.lotr.init.LOTREntities.WOOD_ELF_CAPTAIN.get(), LOTRFaction.WOOD_ELF, 5.0f);
        register(() -> fr.alleretretour.lotr.init.LOTREntities.RIVENDELL_WARRIOR.get(), LOTRFaction.HIGH_ELF, 2.0f);
        register(() -> fr.alleretretour.lotr.init.LOTREntities.RIVENDELL_LORD.get(), LOTRFaction.HIGH_ELF, 5.0f);
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (!(event.getSource().getEntity() instanceof ServerPlayerEntity)) {
            return;
        }
        resolve();
        LivingEntity victim = event.getEntityLiving();
        Entry entry = BONUSES.get(victim.getType());
        if (entry == null) {
            return;
        }
        ServerPlayerEntity player = (ServerPlayerEntity) event.getSource().getEntity();
        LOTRPlayerData data = LOTRPlayerDataProvider.get(player);

        for (LOTRFaction f : LOTRFaction.values()) {
            LOTRFaction.Relation rel = LOTRFaction.getRelation(entry.faction, f);
            if (rel == LOTRFaction.Relation.ENEMY || rel == LOTRFaction.Relation.MORTAL_ENEMY) {
                data.setAlignment(f, data.getAlignment(f) + entry.bonus);
            }
        }
        data.setAlignment(entry.faction, data.getAlignment(entry.faction) - entry.bonus);
        LOTRPacketHandler.syncAlignments(player);

        player.displayClientMessage(new StringTextComponent("+" +
                String.format("%.0f", entry.bonus) + " ")
                .append(new TranslationTextComponent("lotr.alignment.gained"))
                .withStyle(TextFormatting.YELLOW), true);
    }
}
