package fr.alleretretour.lotr.fac;

import fr.alleretretour.lotr.entity.npc.LOTREntityNPC;
import fr.alleretretour.lotr.hire.LOTRHireRosters;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

/**
 * PORT FIDELE de la mecanique d'embauche (LOTRUnitTradeEntry.getCost) :
 * - non-jure a la faction : cout DOUBLE, remise = surplus d'alignement / 2000 ;
 * - jure : cout de base, remise = surplus / 1500 ;
 * - remise plafonnee a 50 %, cout minimal 1 piece ;
 * - certaines unites exigent un serment (PledgeType).
 * L'unite apparait aupres du joueur, comme dans le Legacy.
 */
public final class LOTRUnitHiring {

    public enum Result { HIRED, NO_ALIGNMENT, NO_PLEDGE, NO_COINS }

    private LOTRUnitHiring() {
    }

    /** Formule exacte de LOTRUnitTradeEntry.getCost. */
    public static int getCost(LOTRHireRosters.Entry entry, PlayerEntity player,
                              LOTRFaction faction) {
        LOTRPlayerData data = LOTRPlayerDataProvider.get(player);
        float alignment = data.getAlignment(faction);
        boolean pledged = data.getPledgeFaction() == faction;
        float cost = entry.initialCost;
        float alignSurplus = Math.max(alignment - entry.minAlignment, 0.0f);
        float f;
        if (pledged) {
            f = alignSurplus / 1500.0f;
        } else {
            cost *= 2.0f;
            f = alignSurplus / 2000.0f;
        }
        f = MathHelper.clamp(f, 0.0f, 1.0f);
        cost *= 1.0f - f * 0.5f;
        return Math.max(Math.round(cost), 1);
    }

    public static int countCoins(PlayerEntity player) {
        int total = 0;
        for (ItemStack stack : player.inventory.items) {
            if (stack.getItem() == fr.alleretretour.lotr.init.LOTRItems.SILVER_COIN.get()) {
                total += stack.getCount();
            }
        }
        return total;
    }

    private static void deductCoins(PlayerEntity player, int amount) {
        for (ItemStack stack : player.inventory.items) {
            if (amount <= 0) {
                return;
            }
            if (stack.getItem() == fr.alleretretour.lotr.init.LOTRItems.SILVER_COIN.get()) {
                int take = Math.min(amount, stack.getCount());
                stack.shrink(take);
                amount -= take;
            }
        }
    }

    public static Result tryHire(LOTREntityNPC recruiter, ServerPlayerEntity player,
                                 LOTRHireRosters.Entry entry) {
        LOTRFaction faction = recruiter.getFaction();
        LOTRPlayerData data = LOTRPlayerDataProvider.get(player);
        float alignment = data.getAlignment(faction);
        if (alignment < entry.minAlignment) {
            player.displayClientMessage(new StringTextComponent(
                    String.format("Alignement requis : %d (vous : %.0f)",
                            entry.minAlignment, alignment)), true);
            return Result.NO_ALIGNMENT;
        }
        if (!entry.pledgeType.isMet(data.getPledgeFaction(), faction)) {
            player.displayClientMessage(new StringTextComponent(
                    entry.pledgeType.requirementText()), true);
            return Result.NO_PLEDGE;
        }
        int cost = getCost(entry, player, faction);
        int coins = countCoins(player);
        if (coins < cost) {
            player.displayClientMessage(new StringTextComponent(
                    "Pas assez de pieces d'argent : " + coins + "/" + cost), true);
            return Result.NO_COINS;
        }
        deductCoins(player, cost);
        LOTREntityNPC unit = entry.type.get().create(recruiter.level);
        if (unit != null) {
            // l'unite rejoint directement son commanditaire (fidele au Legacy)
            unit.moveTo(player.getX() + player.getRandom().nextInt(3) - 1.0,
                    player.getY(), player.getZ() + player.getRandom().nextInt(3) - 1.0,
                    player.yRot, 0.0f);
            if (recruiter.level instanceof ServerWorld) {
                unit.finalizeSpawn((ServerWorld) recruiter.level,
                        recruiter.level.getCurrentDifficultyAt(unit.blockPosition()),
                        SpawnReason.MOB_SUMMONED, null, null);
            }
            unit.hireBy(player);
            recruiter.level.addFreshEntity(unit);
            if (entry.mount != null) {
                net.minecraft.entity.MobEntity mount = null;
                if ("horse".equals(entry.mount)) {
                    fr.alleretretour.lotr.entity.animal.LOTREntityHorse horse =
                            fr.alleretretour.lotr.init.LOTREntities.HORSE.get()
                                    .create(recruiter.level);
                    if (horse != null) {
                        horse.setupAsUnitMount();
                    }
                    mount = horse;
                } else if ("warg".equals(entry.mount)) {
                    // warg de la faction du recruteur (PORT : MordorWarg, UrukWarg...)
                    net.minecraftforge.fml.RegistryObject<? extends net.minecraft.entity.EntityType<
                            ? extends fr.alleretretour.lotr.entity.npc.LOTREntityWarg>> wargType;
                    switch (faction) {
                        case ISENGARD:
                            wargType = fr.alleretretour.lotr.init.LOTREntities.URUK_WARG;
                            break;
                        case GUNDABAD:
                            wargType = fr.alleretretour.lotr.init.LOTREntities.GUNDABAD_WARG;
                            break;
                        case MORDOR:
                            wargType = fr.alleretretour.lotr.init.LOTREntities.MORDOR_WARG;
                            break;
                        default:
                            wargType = fr.alleretretour.lotr.init.LOTREntities.ANGMAR_WARG;
                            break;
                    }
                    fr.alleretretour.lotr.entity.npc.LOTREntityWarg warg =
                            wargType.get().create(recruiter.level);
                    if (warg != null) {
                        warg.setupAsUnitMount();
                    }
                    mount = warg;
                } else if ("rhino".equals(entry.mount)) {
                    fr.alleretretour.lotr.entity.animal.LOTREntityRhino rhino =
                            fr.alleretretour.lotr.init.LOTREntities.RHINO.get()
                                    .create(recruiter.level);
                    if (rhino != null) {
                        rhino.setupAsUnitMount();
                    }
                    mount = rhino;
                } else if ("spider".equals(entry.mount)) {
                    fr.alleretretour.lotr.entity.npc.LOTREntityMirkwoodSpider spider =
                            fr.alleretretour.lotr.init.LOTREntities.MIRKWOOD_SPIDER.get()
                                    .create(recruiter.level);
                    if (spider != null) {
                        spider.setupAsUnitMount();
                    }
                    mount = spider;
                } else if ("zebra".equals(entry.mount)) {
                    fr.alleretretour.lotr.entity.animal.LOTREntityZebra zebra =
                            fr.alleretretour.lotr.init.LOTREntities.ZEBRA.get()
                                    .create(recruiter.level);
                    if (zebra != null) {
                        zebra.setupAsUnitMount();
                    }
                    mount = zebra;
                } else if ("elk".equals(entry.mount)) {
                    fr.alleretretour.lotr.entity.animal.LOTREntityElk elk =
                            fr.alleretretour.lotr.init.LOTREntities.ELK.get()
                                    .create(recruiter.level);
                    if (elk != null) {
                        elk.setupAsUnitMount();
                    }
                    mount = elk;
                } else if ("boar".equals(entry.mount)) {
                    fr.alleretretour.lotr.entity.animal.LOTREntityWildBoar boar =
                            fr.alleretretour.lotr.init.LOTREntities.WILD_BOAR.get()
                                    .create(recruiter.level);
                    if (boar != null) {
                        boar.setupAsUnitMount();
                    }
                    mount = boar;
                }
                if (mount != null && entry.mountArmor != null) {
                    net.minecraft.item.ItemStack armor =
                            new net.minecraft.item.ItemStack(entry.mountArmor.get());
                    if (mount instanceof fr.alleretretour.lotr.entity.animal.LOTRMountArmored) {
                        ((fr.alleretretour.lotr.entity.animal.LOTRMountArmored) mount)
                                .setMountArmor(armor);
                    } else if (mount instanceof net.minecraft.entity.passive.horse.AbstractHorseEntity) {
                        // chevaux/zebres : emplacement d'armure vanilla (slot 401)
                        mount.setSlot(401, armor);
                    }
                }
                if (mount != null) {
                    mount.moveTo(unit.getX(), unit.getY(), unit.getZ(), unit.yRot, 0.0f);
                    recruiter.level.addFreshEntity(mount);
                    unit.startRiding(mount);
                }
            }
            player.displayClientMessage(new StringTextComponent(
                    "Unite engagee : " + unit.getName().getString() + " (-" + cost + " pieces)"),
                    true);
        }
        return Result.HIRED;
    }
}
