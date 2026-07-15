package fr.alleretretour.lotr.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

/**
 * TRANSPILE AUTOMATIQUEMENT depuis lotr.client.model.LOTRModelMoredainLionHelmet (1.7.10).
 * Geometrie identique a l'original ; textures Legacy reutilisees telles quelles.
 */
public class LOTRModelMoredainLionHelmet extends BipedModel<LivingEntity> {

    private ModelRenderer panelRight;
    private ModelRenderer panelLeft;
    private ModelRenderer panelBack;
    private ModelRenderer panelTop;

    public LOTRModelMoredainLionHelmet(float f) {
        super(f);
        texWidth = 64;
        texHeight = 32;

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, f);
        head.setPos(0.0f, 0.0f, 0.0f);
        head.texOffs(34, 16).addBox(-4.5f, -9.0f, -2.5f, 9, 2, 5, f);
        head.texOffs(0, 17).addBox(-2.5f, -10.0f, -7.0f, 5, 3, 12, f);
        head.texOffs(34, 23).addBox(-1.0f, -10.4f, -7.2f, 2, 2, 7, f);
        head.texOffs(0, 0).addBox(-2.0f, -8.0f, -6.8f - f, 1, 3, 1, 0.0f);
        head.mirror = true;
        head.addBox(1.0f, -8.0f, -6.8f - f, 1, 3, 1, 0.0f);
        panelRight = new ModelRenderer(this, 32, 0);
        panelRight.addBox(-5.0f - f, -8.0f, -3.0f, 0, 8, 8, 0.0f);
        panelRight.zRot = 0.06981317007977318f;
        panelLeft = new ModelRenderer(this, 32, 0);
        panelLeft.mirror = true;
        panelLeft.addBox(5.0f + f, -8.0f, -3.0f, 0, 8, 8, 0.0f);
        panelLeft.zRot = -0.06981317007977318f;
        panelBack = new ModelRenderer(this, 44, 0);
        panelBack.addBox(-4.0f, -8.0f, 4.8f + f, 8, 10, 0, 0.0f);
        panelBack.xRot = 0.06981317007977318f;
        panelTop = new ModelRenderer(this, 52, 25);
        panelTop.addBox(-2.5f, -16.0f - f, -2.0f, 5, 7, 0, 0.0f);
        panelTop.xRot = -0.17453292519943295f;
        head.addChild(panelRight);
        head.addChild(panelLeft);
        head.addChild(panelBack);
        head.addChild(panelTop);
        hat = new ModelRenderer(this, 0, 0);
        body = new ModelRenderer(this, 0, 0);
        rightArm = new ModelRenderer(this, 0, 0);
        leftArm = new ModelRenderer(this, 0, 0);
        rightLeg = new ModelRenderer(this, 0, 0);
        leftLeg = new ModelRenderer(this, 0, 0);
    }
}
