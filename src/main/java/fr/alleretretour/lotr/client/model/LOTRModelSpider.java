package fr.alleretretour.lotr.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

/** CONVERTI AUTOMATIQUEMENT de lotr.client.model.LOTRModelSpider (1.7.10). */
public class LOTRModelSpider<T extends fr.alleretretour.lotr.entity.npc.LOTREntityMirkwoodSpider> extends EntityModel<T> {

    public final ModelRenderer head;
    public final ModelRenderer thorax;
    public final ModelRenderer abdomen;
    public final ModelRenderer leg1;
    public final ModelRenderer leg2;
    public final ModelRenderer leg3;
    public final ModelRenderer leg4;
    public final ModelRenderer leg5;
    public final ModelRenderer leg6;
    public final ModelRenderer leg7;
    public final ModelRenderer leg8;

    public LOTRModelSpider() {
        this(0.0f);
    }

    public LOTRModelSpider(float f) {
        texWidth = 64;
        texHeight = 32;
        head = new ModelRenderer(this, 32, 0);
        head.addBox(-4.0f, -4.0f, -8.0f, 8, 8, 8, f);
        head.setPos(0.0f, 17.0f, -3.0f);
        thorax = new ModelRenderer(this, 0, 0);
        thorax.addBox(-3.0f, -3.0f, -3.0f, 6, 6, 6, f);
        thorax.setPos(0.0f, 17.0f, 0.0f);
        abdomen = new ModelRenderer(this, 0, 12);
        abdomen.addBox(-5.0f, -4.0f, -0.5f, 10, 8, 12, f);
        abdomen.setPos(0.0f, 17.0f, 3.0f);
        leg1 = new ModelRenderer(this, 36, 16);
        leg1.addBox(-11.0f, -1.0f, -1.0f, 12, 2, 2, f);
        leg1.setPos(-4.0f, 17.0f, 2.0f);
        leg1.texOffs(60, 20).addBox(-10.5f, 0.0f, -0.5f, 1, 10, 1, f);
        leg2 = new ModelRenderer(this, 36, 16);
        leg2.mirror = true;
        leg2.addBox(-1.0f, -1.0f, -1.0f, 12, 2, 2, f);
        leg2.setPos(4.0f, 17.0f, 2.0f);
        leg2.texOffs(60, 20).addBox(9.5f, 0.0f, -0.5f, 1, 10, 1, f);
        leg3 = new ModelRenderer(this, 36, 16);
        leg3.addBox(-11.0f, -1.0f, -1.0f, 12, 2, 2, f);
        leg3.setPos(-4.0f, 17.0f, 1.0f);
        leg3.texOffs(60, 20).addBox(-10.5f, 0.0f, -0.5f, 1, 10, 1, f);
        leg4 = new ModelRenderer(this, 36, 16);
        leg4.mirror = true;
        leg4.addBox(-1.0f, -1.0f, -1.0f, 12, 2, 2, f);
        leg4.setPos(4.0f, 17.0f, 1.0f);
        leg4.texOffs(60, 20).addBox(9.5f, 0.0f, -0.5f, 1, 10, 1, f);
        leg5 = new ModelRenderer(this, 36, 16);
        leg5.addBox(-11.0f, -1.0f, -1.0f, 12, 2, 2, f);
        leg5.setPos(-4.0f, 17.0f, 0.0f);
        leg5.texOffs(60, 20).addBox(-10.5f, 0.0f, -0.5f, 1, 10, 1, f);
        leg6 = new ModelRenderer(this, 36, 16);
        leg6.mirror = true;
        leg6.addBox(-1.0f, -1.0f, -1.0f, 12, 2, 2, f);
        leg6.setPos(4.0f, 17.0f, 0.0f);
        leg6.texOffs(60, 20).addBox(9.5f, 0.0f, -0.5f, 1, 10, 1, f);
        leg7 = new ModelRenderer(this, 36, 16);
        leg7.addBox(-11.0f, -1.0f, -1.0f, 12, 2, 2, f);
        leg7.setPos(-4.0f, 17.0f, -1.0f);
        leg7.texOffs(60, 20).addBox(-10.5f, 0.0f, -0.5f, 1, 10, 1, f);
        leg8 = new ModelRenderer(this, 36, 16);
        leg8.mirror = true;
        leg8.addBox(-1.0f, -1.0f, -1.0f, 12, 2, 2, f);
        leg8.setPos(4.0f, 17.0f, -1.0f);
        leg8.texOffs(60, 20).addBox(9.5f, 0.0f, -0.5f, 1, 10, 1, f);
    }

    @Override
    public void setupAnim(T entity, float f, float f1, float f2, float f3, float f4) {
        head.yRot = f3 / 57.295776f;
        head.xRot = f4 / 57.295776f;
        abdomen.yRot = net.minecraft.util.math.MathHelper.cos(f * 0.6662f) * 0.5f * f1;
        float f6 = -0.51460177f;
        leg1.zRot = -f6;
        leg2.zRot = f6;
        leg3.zRot = -f6 * 0.74f;
        leg4.zRot = f6 * 0.74f;
        leg5.zRot = -f6 * 0.74f;
        leg6.zRot = f6 * 0.74f;
        leg7.zRot = -f6;
        leg8.zRot = f6;
        float f7 = -0.0f;
        float f8 = 0.3926991f;
        leg1.yRot = f8 * 2.0f + f7;
        leg2.yRot = -f8 * 2.0f - f7;
        leg3.yRot = f8 + f7;
        leg4.yRot = -f8 - f7;
        leg5.yRot = -f8 + f7;
        leg6.yRot = f8 - f7;
        leg7.yRot = -f8 * 2.0f + f7;
        leg8.yRot = f8 * 2.0f - f7;
        float f9 = -(net.minecraft.util.math.MathHelper.cos(f * 0.6662f * 2.0f + 0.0f) * 0.4f) * f1;
        float f10 = -(net.minecraft.util.math.MathHelper.cos(f * 0.6662f * 2.0f + 3.1415927f) * 0.4f) * f1;
        float f11 = -(net.minecraft.util.math.MathHelper.cos(f * 0.6662f * 2.0f + 1.5707964f) * 0.4f) * f1;
        float f12 = -(net.minecraft.util.math.MathHelper.cos(f * 0.6662f * 2.0f + 4.712389f) * 0.4f) * f1;
        float f13 = Math.abs(net.minecraft.util.math.MathHelper.sin(f * 0.6662f + 0.0f) * 0.4f) * f1;
        float f14 = Math.abs(net.minecraft.util.math.MathHelper.sin(f * 0.6662f + 3.1415927f) * 0.4f) * f1;
        float f15 = Math.abs(net.minecraft.util.math.MathHelper.sin(f * 0.6662f + 1.5707964f) * 0.4f) * f1;
        float f16 = Math.abs(net.minecraft.util.math.MathHelper.sin(f * 0.6662f + 4.712389f) * 0.4f) * f1;
        leg1.yRot += f9;
        leg2.yRot -= f9;
        leg3.yRot += f10;
        leg4.yRot -= f10;
        leg5.yRot += f11;
        leg6.yRot -= f11;
        leg7.yRot += f12;
        leg8.yRot -= f12;
        leg1.zRot += f13;
        leg2.zRot -= f13;
        leg3.zRot += f14;
        leg4.zRot -= f14;
        leg5.zRot += f15;
        leg6.zRot -= f15;
        leg7.zRot += f16;
        leg8.zRot -= f16;
    }

    @Override
    public void renderToBuffer(MatrixStack ms, IVertexBuilder buf, int light, int overlay,
                               float red, float green, float blue, float alpha) {
        head.render(ms, buf, light, overlay, red, green, blue, alpha);
        thorax.render(ms, buf, light, overlay, red, green, blue, alpha);
        abdomen.render(ms, buf, light, overlay, red, green, blue, alpha);
        leg1.render(ms, buf, light, overlay, red, green, blue, alpha);
        leg2.render(ms, buf, light, overlay, red, green, blue, alpha);
        leg3.render(ms, buf, light, overlay, red, green, blue, alpha);
        leg4.render(ms, buf, light, overlay, red, green, blue, alpha);
        leg5.render(ms, buf, light, overlay, red, green, blue, alpha);
        leg6.render(ms, buf, light, overlay, red, green, blue, alpha);
        leg7.render(ms, buf, light, overlay, red, green, blue, alpha);
        leg8.render(ms, buf, light, overlay, red, green, blue, alpha);
    }
}
