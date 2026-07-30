package fr.alleretretour.lotr.entity.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * PORT de lotr.common.entity.item.LOTREntityBanner - premiere tranche :
 * la banniere plantee au sol (socle, hampe, etoffe de faction), frappable
 * pour la recuperer. La protection de territoire (LOTRBannerProtection)
 * viendra dans son propre lot.
 */
public class LOTREntityBannerPlaced extends Entity {

    private static final DataParameter<String> BANNER_ITEM =
            EntityDataManager.defineId(LOTREntityBannerPlaced.class, DataSerializers.STRING);
    private static final DataParameter<java.util.Optional<java.util.UUID>> PLACER_SYNC =
            EntityDataManager.defineId(LOTREntityBannerPlaced.class, DataSerializers.OPTIONAL_UUID);
    private static final DataParameter<String> WHITELIST_NAMES =
            EntityDataManager.defineId(LOTREntityBannerPlaced.class, DataSerializers.STRING);

    public LOTREntityBannerPlaced(EntityType<? extends LOTREntityBannerPlaced> type, World world) {
        super(type, world);
    }

    @Override
    protected void defineSynchedData() {
        entityData.define(BANNER_ITEM, "lotr:gondor_banner");
        entityData.define(PLACER_SYNC, java.util.Optional.empty());
        entityData.define(WHITELIST_NAMES, "");
    }

    public void setBannerItem(String id) {
        entityData.set(BANNER_ITEM, id);
    }

    public String getBannerItem() {
        return entityData.get(BANNER_ITEM);
    }

    // ==================== PROTECTION (PORT LOTREntityBanner) ====================

    public static final float ALIGNMENT_PROTECTION_MIN = 1.0f;
    public static final float ALIGNMENT_PROTECTION_MAX = 10000.0f;
    public static final int WHITELIST_MAX = 16;

    @javax.annotation.Nullable
    private java.util.UUID placer;
    private final java.util.List<java.util.UUID> whitelist = new java.util.ArrayList<>();
    private float alignmentProtection = ALIGNMENT_PROTECTION_MIN;

    public void setPlacingPlayer(net.minecraft.entity.player.PlayerEntity player) {
        this.placer = player.getUUID();  // Legacy : whitelistPlayer(0, profile)
        entityData.set(PLACER_SYNC, java.util.Optional.of(player.getUUID()));
    }

    public boolean isPlacer(net.minecraft.entity.player.PlayerEntity player) {
        return player.getUUID().equals(placer);
    }

    /** Cote client : le poseur synchronise (ouverture de la GUI d'edition). */
    public boolean isPlacerClient(net.minecraft.entity.player.PlayerEntity player) {
        return entityData.get(PLACER_SYNC).map(u -> u.equals(player.getUUID())).orElse(false);
    }

    public String getWhitelistNames() {
        return entityData.get(WHITELIST_NAMES);
    }

    private final java.util.List<String> whitelistNames = new java.util.ArrayList<>();

    private void syncNames() {
        entityData.set(WHITELIST_NAMES, String.join(";", whitelistNames));
    }

    public void addToWhitelistByName(net.minecraft.server.MinecraftServer server, String name) {
        if (server == null || whitelist.size() >= WHITELIST_MAX
                || whitelistNames.contains(name)) {
            return;
        }
        com.mojang.authlib.GameProfile profile = server.getProfileCache().get(name);
        if (profile != null && addToWhitelist(profile.getId())) {
            whitelistNames.add(profile.getName());
            syncNames();
        }
    }

    public void removeFromWhitelistByName(String name) {
        int i = whitelistNames.indexOf(name);
        if (i >= 0) {
            whitelistNames.remove(i);
            whitelist.remove(i);
            syncNames();
        }
    }

    @Override
    public net.minecraft.util.ActionResultType interact(
            net.minecraft.entity.player.PlayerEntity player, net.minecraft.util.Hand hand) {
        if (hand == net.minecraft.util.Hand.MAIN_HAND
                && (level.isClientSide ? isPlacerClient(player) : isPlacer(player))) {
            if (level.isClientSide) {
                net.minecraftforge.fml.DistExecutor.unsafeRunWhenOn(
                        net.minecraftforge.api.distmarker.Dist.CLIENT,
                        () -> () -> fr.alleretretour.lotr.client.LOTRClientHooks
                                .openBannerScreen(this));
                return net.minecraft.util.ActionResultType.SUCCESS;
            }
            return net.minecraft.util.ActionResultType.CONSUME;
        }
        return super.interact(player, hand);
    }

    /** Faction de la banniere, deduite de l'item (gondor_banner -> GONDOR...). */
    public fr.alleretretour.lotr.fac.LOTRFaction getFaction() {
        String id = getBannerItem();
        String key = id.substring(id.indexOf(':') + 1).replace("_banner", "");
        switch (key) {
            case "gondor": return fr.alleretretour.lotr.fac.LOTRFaction.GONDOR;
            case "rohan": return fr.alleretretour.lotr.fac.LOTRFaction.ROHAN;
            case "dwarf": return fr.alleretretour.lotr.fac.LOTRFaction.DURINS_FOLK;
            case "blue_mountains": return fr.alleretretour.lotr.fac.LOTRFaction.BLUE_MOUNTAINS;
            case "high_elf": return fr.alleretretour.lotr.fac.LOTRFaction.HIGH_ELF;
            case "rivendell": return fr.alleretretour.lotr.fac.LOTRFaction.HIGH_ELF;
            case "galadhrim": return fr.alleretretour.lotr.fac.LOTRFaction.LOTHLORIEN;
            case "ranger": return fr.alleretretour.lotr.fac.LOTRFaction.RANGER_NORTH;
            default: return fr.alleretretour.lotr.fac.LOTRFaction.UNALIGNED;
        }
    }

    public String getFactionName() {
        return getFaction().name();
    }

    /** PORT de getProtectionRange : bloc situe sous la banniere. */
    public int getProtectionRange() {
        net.minecraft.util.math.BlockPos below = blockPosition().below();
        return fr.alleretretour.lotr.fac.LOTRBannerProtection
                .getProtectionRange(level.getBlockState(below).getBlock());
    }

    /** PORT de createProtectionCube. */
    public net.minecraft.util.math.AxisAlignedBB protectionCube(int range) {
        net.minecraft.util.math.BlockPos p = blockPosition();
        return new net.minecraft.util.math.AxisAlignedBB(p).inflate(range);
    }

    /** Autorise : poseur, liste blanche, ou alignement de faction >= seuil. */
    public boolean isPlayerAllowed(net.minecraft.entity.player.PlayerEntity player) {
        if (player.getUUID().equals(placer) || whitelist.contains(player.getUUID())) {
            return true;
        }
        return fr.alleretretour.lotr.fac.LOTRPlayerDataProvider.get(player)
                .getAlignment(getFaction()) >= alignmentProtection;
    }

    public void setAlignmentProtection(float f) {
        alignmentProtection = net.minecraft.util.math.MathHelper.clamp(
                f, ALIGNMENT_PROTECTION_MIN, ALIGNMENT_PROTECTION_MAX);
    }

    public float getAlignmentProtection() {
        return alignmentProtection;
    }

    public boolean addToWhitelist(java.util.UUID id) {
        if (whitelist.size() >= WHITELIST_MAX || whitelist.contains(id)) {
            return false;
        }
        whitelist.add(id);
        return true;
    }

    @Override
    public void tick() {
        super.tick();
        if (!level.isClientSide && !level.getBlockState(blockPosition().below())
                .isSolidRender(level, blockPosition().below())) {
            dropAndRemove();  // fidele au Legacy : la banniere tombe si son support disparait
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (!level.isClientSide && !removed) {
            net.minecraft.entity.Entity attacker = source.getEntity();
            if (attacker instanceof net.minecraft.entity.player.PlayerEntity
                    && getProtectionRange() > 0
                    && !isPlayerAllowed((net.minecraft.entity.player.PlayerEntity) attacker)) {
                return false;  // Legacy : Permission.FULL requise
            }
            dropAndRemove();
        }
        return true;
    }

    private void dropAndRemove() {
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(getBannerItem()));
        if (item != null) {
            spawnAtLocation(new ItemStack(item), 0.5f);
        }
        remove();
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    protected void readAdditionalSaveData(CompoundNBT nbt) {
        if (nbt.contains("BannerItem")) {
            setBannerItem(nbt.getString("BannerItem"));
        }
        if (nbt.hasUUID("Placer")) {
            placer = nbt.getUUID("Placer");
        }
        alignmentProtection = nbt.contains("AlignProt")
                ? nbt.getFloat("AlignProt") : ALIGNMENT_PROTECTION_MIN;
        whitelist.clear();
        net.minecraft.nbt.ListNBT list = nbt.getList("Whitelist", 11);
        for (int i = 0; i < list.size(); i++) {
            whitelist.add(net.minecraft.nbt.NBTUtil.loadUUID(list.get(i)));
        }
        whitelistNames.clear();
        net.minecraft.nbt.ListNBT names = nbt.getList("WhitelistNames", 8);
        for (int i = 0; i < names.size(); i++) {
            whitelistNames.add(names.getString(i));
        }
        syncNames();
        if (placer != null) {
            entityData.set(PLACER_SYNC, java.util.Optional.of(placer));
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundNBT nbt) {
        nbt.putString("BannerItem", getBannerItem());
        if (placer != null) {
            nbt.putUUID("Placer", placer);
        }
        nbt.putFloat("AlignProt", alignmentProtection);
        net.minecraft.nbt.ListNBT list = new net.minecraft.nbt.ListNBT();
        for (java.util.UUID id : whitelist) {
            list.add(net.minecraft.nbt.NBTUtil.createUUID(id));
        }
        nbt.put("Whitelist", list);
        net.minecraft.nbt.ListNBT names = new net.minecraft.nbt.ListNBT();
        for (String n : whitelistNames) {
            names.add(net.minecraft.nbt.StringNBT.valueOf(n));
        }
        nbt.put("WhitelistNames", names);
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
