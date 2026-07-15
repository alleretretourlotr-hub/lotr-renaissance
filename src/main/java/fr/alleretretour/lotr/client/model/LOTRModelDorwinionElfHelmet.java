package fr.alleretretour.lotr.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

/**
 * TRANSPILE AUTOMATIQUEMENT depuis lotr.client.model.LOTRModelDorwinionElfHelmet (1.7.10).
 * Geometrie identique a l'original ; textures Legacy reutilisees telles quelles.
 */
public class LOTRModelDorwinionElfHelmet extends BipedModel<LivingEntity> {

    public LOTRModelDorwinionElfHelmet(float f) {
        super(f);
        texWidth = 64;
        texHeight = 32;

        head = new ModelRenderer(this, 0, 0);
        head.setPos(0.0f, 0.0f, 0.0f);
        head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, f);
        head.texOffs(20, 16).addBox(0.0f, -10.0f, 4.0f, 0, 10, 4, 0.0f);
        hat = new ModelRenderer(this, 32, 0);
        hat.setPos(0.0f, 0.0f, 0.0f);
        hat.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, f + 0.5f);
        ModelRenderer crest = new ModelRenderer(this, 0, 16);
        crest.setPos(0.0f, -f, 0.0f);
        crest.addBox(-1.0f, -11.0f, -6.0f, 2, 5, 8, 0.0f);
        crest.xRot = -0.2617993877991494f;
        head.addChild(crest);
        body = new ModelRenderer(this, 0, 0);
        rightArm = new ModelRenderer(this, 0, 0);
        leftArm = new ModelRenderer(this, 0, 0);
        rightLeg = new ModelRenderer(this, 0, 0);
        leftLeg = new ModelRenderer(this, 0, 0);
    }
}
