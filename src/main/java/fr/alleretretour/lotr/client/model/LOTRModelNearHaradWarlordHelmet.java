package fr.alleretretour.lotr.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

/**
 * TRANSPILE AUTOMATIQUEMENT depuis lotr.client.model.LOTRModelNearHaradWarlordHelmet (1.7.10).
 * Geometrie identique a l'original ; textures Legacy reutilisees telles quelles.
 */
public class LOTRModelNearHaradWarlordHelmet extends BipedModel<LivingEntity> {

    private ModelRenderer stickRight;
    private ModelRenderer stickCentre;
    private ModelRenderer stickLeft;

    public LOTRModelNearHaradWarlordHelmet(float f) {
        super(f);
        texWidth = 64;
        texHeight = 32;

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, f);
        head.setPos(0.0f, 0.0f, 0.0f);
        head.texOffs(6, 24).addBox(-2.5f, -3.0f, 4.1f, 5, 3, 2, 0.0f);
        head.texOffs(0, 16).addBox(-9.0f, -16.0f, 5.5f, 18, 8, 0, 0.0f);
        stickRight = new ModelRenderer(this, 36, 0);
        stickRight.addBox(-0.5f, -19.0f, 5.0f, 1, 18, 1, 0.0f);
        stickRight.texOffs(0, 24).addBox(-1.5f, -24.0f, 5.5f, 3, 5, 0, 0.0f);
        stickRight.zRot = -0.4886921905584123f;
        head.addChild(stickRight);
        stickCentre = new ModelRenderer(this, 36, 0);
        stickCentre.addBox(-0.5f, -19.0f, 5.0f, 1, 18, 1, 0.0f);
        stickCentre.texOffs(0, 24).addBox(-1.5f, -24.0f, 5.5f, 3, 5, 0, 0.0f);
        stickCentre.zRot = 0.0f;
        head.addChild(stickCentre);
        stickLeft = new ModelRenderer(this, 36, 0);
        stickLeft.addBox(-0.5f, -19.0f, 5.0f, 1, 18, 1, 0.0f);
        stickLeft.texOffs(0, 24).addBox(-1.5f, -24.0f, 5.5f, 3, 5, 0, 0.0f);
        stickLeft.zRot = 0.4886921905584123f;
        head.addChild(stickLeft);
        hat = new ModelRenderer(this, 0, 0);
        body = new ModelRenderer(this, 0, 0);
        rightArm = new ModelRenderer(this, 0, 0);
        leftArm = new ModelRenderer(this, 0, 0);
        rightLeg = new ModelRenderer(this, 0, 0);
        leftLeg = new ModelRenderer(this, 0, 0);
    }
}
