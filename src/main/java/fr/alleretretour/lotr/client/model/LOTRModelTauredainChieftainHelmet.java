package fr.alleretretour.lotr.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

/**
 * TRANSPILE AUTOMATIQUEMENT depuis lotr.client.model.LOTRModelTauredainChieftainHelmet (1.7.10).
 * Geometrie identique a l'original ; textures Legacy reutilisees telles quelles.
 */
public class LOTRModelTauredainChieftainHelmet extends BipedModel<LivingEntity> {

    public LOTRModelTauredainChieftainHelmet(float f) {
        super(f);
        texWidth = 64;
        texHeight = 32;

        head = new ModelRenderer(this, 0, 0);
        head.setPos(0.0f, 0.0f, 0.0f);
        head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, f);
        head.texOffs(32, 0).addBox(-5.0f, -9.0f, 0.0f, 10, 6, 3, f);
        ModelRenderer crest = new ModelRenderer(this, 0, 16);
        crest.setPos(0.0f, -f, 0.0f);
        crest.addBox(-8.0f, -23.0f, 0.0f, 16, 14, 0, 0.0f);
        crest.xRot = -0.17453292519943295f;
        head.addChild(crest);
        hat = new ModelRenderer(this, 0, 0);
        body = new ModelRenderer(this, 0, 0);
        rightArm = new ModelRenderer(this, 0, 0);
        leftArm = new ModelRenderer(this, 0, 0);
        rightLeg = new ModelRenderer(this, 0, 0);
        leftLeg = new ModelRenderer(this, 0, 0);
    }
}
