package fr.alleretretour.lotr.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import fr.alleretretour.lotr.client.model.LOTRModelOrc;
import fr.alleretretour.lotr.entity.npc.LOTREntityNPC;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.util.ResourceLocation;

/** Renderer des orques : modele a nez/oreilles + peaux a variantes + stature reduite. */
public class LOTRRenderOrc<T extends LOTREntityNPC> extends BipedRenderer<T, LOTRModelOrc<T>> {

    private final ResourceLocation[] textures;

    public LOTRRenderOrc(EntityRendererManager manager, String skinFolder, int variants) {
        super(manager, new LOTRModelOrc<>(0.0f), 0.5f);
        this.textures = new ResourceLocation[variants];
        for (int i = 0; i < variants; i++) {
            textures[i] = new ResourceLocation("lotr",
                    "textures/entity/" + skinFolder + "/" + i + ".png");
        }
        addLayer(new BipedArmorLayer<>(this,
                new BipedModel<>(0.5f), new BipedModel<>(1.0f)));
    }

    @Override
    protected void scale(T entity, MatrixStack ms, float partialTicks) {
        ms.scale(0.95f, 0.92f, 0.95f);
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return textures[Math.floorMod(entity.getUUID().hashCode(), textures.length)];
    }
}
