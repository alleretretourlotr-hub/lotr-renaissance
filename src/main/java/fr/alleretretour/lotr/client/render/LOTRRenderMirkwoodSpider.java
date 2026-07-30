package fr.alleretretour.lotr.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import fr.alleretretour.lotr.client.model.LOTRModelSpider;
import fr.alleretretour.lotr.entity.npc.LOTREntityMirkwoodSpider;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

/** PORT de LOTRRenderSpider : texture selon le venin, echelle selon la taille. */
public class LOTRRenderMirkwoodSpider extends
        MobRenderer<LOTREntityMirkwoodSpider, LOTRModelSpider<LOTREntityMirkwoodSpider>> {

    private static final ResourceLocation PLAIN =
            new ResourceLocation("lotr", "textures/entity/spider/spider_mirkwood.png");
    private static final ResourceLocation SLOWNESS =
            new ResourceLocation("lotr", "textures/entity/spider/spider_mirkwood_slowness.png");
    private static final ResourceLocation POISON =
            new ResourceLocation("lotr", "textures/entity/spider/spider_mirkwood_poison.png");

    public LOTRRenderMirkwoodSpider(EntityRendererManager manager) {
        super(manager, new LOTRModelSpider<>(), 0.8f);
    }

    @Override
    public ResourceLocation getTextureLocation(LOTREntityMirkwoodSpider entity) {
        switch (entity.getSpiderType()) {
            case LOTREntityMirkwoodSpider.VENOM_SLOWNESS:
                return SLOWNESS;
            case LOTREntityMirkwoodSpider.VENOM_POISON:
                return POISON;
            default:
                return PLAIN;
        }
    }

    @Override
    protected void scale(LOTREntityMirkwoodSpider entity, MatrixStack ms, float partialTicks) {
        float s = entity.getSpiderScaleAmount();
        ms.scale(s, s, s);
    }
}
