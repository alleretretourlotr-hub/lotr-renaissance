package fr.alleretretour.lotr.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

/**
 * TRANSPILE AUTOMATIQUEMENT depuis lotr.client.model.LOTRModelHighElvenHelmet (1.7.10).
 * Geometrie identique a l'original ; textures Legacy reutilisees telles quelles.
 */
public class LOTRModelHighElvenHelmet extends BipedModel<LivingEntity> {

    private ModelRenderer crest;

    public LOTRModelHighElvenHelmet(float f) {
        super(f);
        texWidth = 64;
        texHeight = 32;

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, f);
        head.setPos(0.0f, 0.0f, 0.0f);
        head.addBox(-0.5f, -11.0f, -2.0f, 1, 3, 1, 0.0f);
        head.texOffs(0, 4).addBox(-0.5f, -10.0f, 2.0f, 1, 2, 1, 0.0f);
        crest = new ModelRenderer(this, 32, 0);
        crest.addBox(-1.0f, -11.0f, -8.0f, 2, 1, 11, 0.0f);
        crest.texOffs(32, 12).addBox(-1.0f, -10.0f, -8.0f, 2, 1, 1, 0.0f);
        crest.xRot = -0.2792526803190927f;
        head.addChild(crest);
        hat = new ModelRenderer(this, 0, 0);
        body = new ModelRenderer(this, 0, 0);
        rightArm = new ModelRenderer(this, 0, 0);
        leftArm = new ModelRenderer(this, 0, 0);
        rightLeg = new ModelRenderer(this, 0, 0);
        leftLeg = new ModelRenderer(this, 0, 0);
    }
}
