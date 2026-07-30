package fr.alleretretour.lotr.entity.npc;

import fr.alleretretour.lotr.fac.LOTRFaction;
import fr.alleretretour.lotr.inventory.LOTRContainerTrade;
import fr.alleretretour.lotr.trade.LOTRTradeTables;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/** GENERE (lot marchands) - table Legacy MOREDAIN_HUNTSMAN */
public class LOTREntityMoredainTrader extends LOTREntityNPC {

    public LOTREntityMoredainTrader(EntityType<? extends LOTREntityMoredainTrader> type, World world) {
        super(type, world);
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.MORWAITH;
    }

    @Override
    protected String getSpeechBank() {
        return "moredain/warrior";
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return createNPCAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.ATTACK_DAMAGE, 2.0);
    }

    @Override
    protected ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        if (hand != Hand.MAIN_HAND) {
            return super.mobInteract(player, hand);
        }
        float alignment = fr.alleretretour.lotr.fac.LOTRPlayerDataProvider
                .get(player).getAlignment(getFaction());
        if (alignment < 0.0f) {
            return super.mobInteract(player, hand);
        }
        if (!level.isClientSide && player instanceof ServerPlayerEntity) {
            player.openMenu(new INamedContainerProvider() {
                @Override
                public ITextComponent getDisplayName() {
                    return getName();
                }

                @Nullable
                @Override
                public Container createMenu(int id, net.minecraft.entity.player.PlayerInventory inv,
                                            PlayerEntity p) {
                    return new LOTRContainerTrade(id, inv,
                            LOTRTradeTables.moredainTraderBuy(),
                            LOTRTradeTables.moredainTraderSell());
                }
            });
        }
        return ActionResultType.CONSUME;
    }
}
