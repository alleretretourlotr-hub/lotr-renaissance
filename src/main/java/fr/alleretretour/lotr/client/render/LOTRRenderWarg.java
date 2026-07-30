package fr.alleretretour.lotr.client.render;

import fr.alleretretour.lotr.client.model.LOTRModelWarg;
import fr.alleretretour.lotr.entity.npc.LOTREntityWarg;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import java.util.EnumMap;
import java.util.Map;

/** PORT de LOTRRenderWarg : texture selon le type de pelage. */
public class LOTRRenderWarg
        extends MobRenderer<LOTREntityWarg, LOTRModelWarg<LOTREntityWarg>> {

    private static final Map<LOTREntityWarg.WargType, ResourceLocation> SKINS =
            new EnumMap<>(LOTREntityWarg.WargType.class);

    static {
        for (LOTREntityWarg.WargType type : LOTREntityWarg.WargType.values()) {
            SKINS.put(type, new ResourceLocation("lotr",
                    "textures/entity/warg/" + type.textureName() + ".png"));
        }
    }

    public LOTRRenderWarg(EntityRendererManager manager) {
        super(manager, new LOTRModelWarg<>(), 0.5f);
        addLayer(new LOTRLayerMountArmor<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(LOTREntityWarg entity) {
        return SKINS.get(entity.getWargType());
    }
}
