package fr.alleretretour.lotr.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

/** CONVERTI AUTOMATIQUEMENT de lotr.client.model.LOTRModelRhino (1.7.10). */
public class LOTRModelRhino<T extends fr.alleretretour.lotr.entity.animal.LOTREntityRhino> extends EntityModel<T> {

    public final ModelRenderer head;
    public final ModelRenderer neck;
    public final ModelRenderer horn1;
    public final ModelRenderer horn2;
    public final ModelRenderer body;
    public final ModelRenderer tail;
    public final ModelRenderer leg1;
    public final ModelRenderer leg2;
    public final ModelRenderer leg3;
    public final ModelRenderer leg4;

    public LOTRModelRhino() {
        this(0.0f);
    }

    public LOTRModelRhino(float f) {
        texWidth = 128;
        texHeight = 128;
        head = new ModelRenderer(this, 0, 0);
        head.setPos(0.0f, 3.0f, -12.0f);
        head.addBox(-5.0f, -2.0f, -22.0f, 10, 10, 16, f);
        head.addBox(-4.0f, -4.0f, -10.0f, 1, 2, 2, f);
        head.mirror = true;
        head.addBox(3.0f, -4.0f, -10.0f, 1, 2, 2, f);
        neck = new ModelRenderer(this, 52, 0);
        neck.setPos(0.0f, 3.0f, -12.0f);
        neck.addBox(-7.0f, -4.0f, -7.0f, 14, 13, 8, f);
        horn1 = new ModelRenderer(this, 36, 0);
        horn1.addBox(-1.0f, -14.0f, -20.0f, 2, 8, 2, f);
        horn1.xRot = 0.2617993877991494f;
        head.addChild(horn1);
        horn2 = new ModelRenderer(this, 44, 0);
        horn2.addBox(-1.0f, -3.0f, -17.0f, 2, 4, 2, f);
        horn2.xRot = -0.17453292519943295f;
        head.addChild(horn2);
        body = new ModelRenderer(this, 0, 26);
        body.setPos(0.0f, 5.0f, 0.0f);
        body.addBox(-8.0f, -7.0f, -13.0f, 16, 16, 34, f);
        tail = new ModelRenderer(this, 100, 63);
        tail.setPos(0.0f, 7.0f, 21.0f);
        tail.addBox(-1.5f, -1.0f, -1.0f, 3, 8, 2, f);
        leg1 = new ModelRenderer(this, 30, 76);
        leg1.setPos(-8.0f, 3.0f, 14.0f);
        leg1.addBox(-8.0f, -3.0f, -5.0f, 8, 12, 10, f);
        leg1.texOffs(0, 95).addBox(-7.0f, 9.0f, -3.0f, 6, 12, 6, f);
        leg2 = new ModelRenderer(this, 30, 76);
        leg2.setPos(8.0f, 3.0f, 14.0f);
        leg2.mirror = true;
        leg2.addBox(0.0f, -3.0f, -5.0f, 8, 12, 10, f);
        leg2.texOffs(0, 95).addBox(1.0f, 9.0f, -3.0f, 6, 12, 6, f);
        leg3 = new ModelRenderer(this, 0, 76);
        leg3.setPos(-8.0f, 4.0f, -6.0f);
        leg3.addBox(-7.0f, -3.0f, -4.0f, 7, 11, 8, f);
        leg3.texOffs(0, 95).addBox(-6.5f, 8.0f, -3.0f, 6, 12, 6, f);
        leg4 = new ModelRenderer(this, 0, 76);
        leg4.setPos(8.0f, 4.0f, -6.0f);
        leg4.mirror = true;
        leg4.addBox(0.0f, -3.0f, -4.0f, 7, 11, 8, f);
        leg4.texOffs(0, 95).addBox(0.5f, 8.0f, -3.0f, 6, 12, 6, f);
    }

    @Override
    public void setupAnim(T entity, float f, float f1, float f2, float f3, float f4) {
        head.xRot = 0.20943951023931956f;
        head.yRot = 0.0f;
        head.xRot += net.minecraft.util.math.MathHelper.cos(f * 0.2f) * 0.3f * f1;
        head.xRot += (float) Math.toRadians(f4);
        head.yRot += (float) Math.toRadians(f3);
        neck.xRot = head.xRot;
        neck.yRot = head.yRot;
        neck.zRot = head.zRot;
        tail.xRot = 0.6981317007977318f;
        tail.xRot += net.minecraft.util.math.MathHelper.cos(f * 0.3f) * 0.5f * f1;
        leg1.xRot = net.minecraft.util.math.MathHelper.cos(f * 0.4f) * 1.0f * f1;
        leg2.xRot = net.minecraft.util.math.MathHelper.cos(f * 0.4f + 3.1415927f) * 1.0f * f1;
        leg3.xRot = net.minecraft.util.math.MathHelper.cos(f * 0.4f + 3.1415927f) * 1.0f * f1;
        leg4.xRot = net.minecraft.util.math.MathHelper.cos(f * 0.4f) * 1.0f * f1;
    }

    @Override
    public void renderToBuffer(MatrixStack ms, IVertexBuilder buf, int light, int overlay,
                               float red, float green, float blue, float alpha) {
        head.render(ms, buf, light, overlay, red, green, blue, alpha);
        neck.render(ms, buf, light, overlay, red, green, blue, alpha);
        body.render(ms, buf, light, overlay, red, green, blue, alpha);
        tail.render(ms, buf, light, overlay, red, green, blue, alpha);
        leg1.render(ms, buf, light, overlay, red, green, blue, alpha);
        leg2.render(ms, buf, light, overlay, red, green, blue, alpha);
        leg3.render(ms, buf, light, overlay, red, green, blue, alpha);
        leg4.render(ms, buf, light, overlay, red, green, blue, alpha);
    }
}
