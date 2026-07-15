package fr.alleretretour.lotr.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

/**
 * Base des armes de jet (lances, haches de lancer) - port de LOTREntitySpear.
 * L'ItemStack lance est synchronise (DataParameter) pour le rendu multijoueur,
 * comme les barres de boss de SaroumaneBoss - fiable sur Mohist.
 * Herite d'AbstractArrowEntity : physique, plantage, ramassage geres par vanilla.
 */
public class LOTREntityThrownWeapon extends AbstractArrowEntity {

    private static final DataParameter<ItemStack> WEAPON =
            EntityDataManager.defineId(LOTREntityThrownWeapon.class, DataSerializers.ITEM_STACK);

    public LOTREntityThrownWeapon(EntityType<? extends LOTREntityThrownWeapon> type, World world) {
        super(type, world);
    }

    public LOTREntityThrownWeapon(EntityType<? extends LOTREntityThrownWeapon> type, World world,
                                  LivingEntity shooter, ItemStack stack, double damage) {
        super(type, shooter, world);
        setWeapon(stack);
        setBaseDamage(damage);
    }

    public void setWeapon(ItemStack stack) {
        entityData.set(WEAPON, stack.copy());
    }

    public ItemStack getWeapon() {
        return entityData.get(WEAPON);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(WEAPON, ItemStack.EMPTY);
    }

    @Override
    protected ItemStack getPickupItem() {
        return getWeapon();
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.put("LOTRWeapon", getWeapon().save(new CompoundNBT()));
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        setWeapon(ItemStack.of(nbt.getCompound("LOTRWeapon")));
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
