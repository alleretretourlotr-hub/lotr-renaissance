package fr.alleretretour.lotr.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

/**
 * Port de lotr.client.model.LOTRModelGondorHelmet (1.7.10).
 * Geometrie transcrite 1:1 : casque + crete + ailettes avant/arriere.
 * Texture : assets/lotr/textures/armor/gondor_helmet.png (64x32, inchangee).
 */
public class LOTRModelGondorHelmet extends BipedModel<LivingEntity> {

    public LOTRModelGondorHelmet(float f) {
        super(f);
        texWidth = 64;
        texHeight = 32;

        head = new ModelRenderer(this, 0, 0);
        head.setPos(0.0f, 0.0f, 0.0f);
        head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, f);
        head.texOffs(0, 16).addBox(-1.5f, -9.0f, -3.5f, 3, 1, 7, f);
        head.texOffs(20, 16).addBox(-0.5f, -10.0f, -3.5f, 1, 1, 7, f);
        head.texOffs(24, 0).addBox(-1.5f, -10.5f - f, -4.5f - f, 3, 4, 1, 0.0f);
        head.texOffs(24, 5).addBox(-0.5f, -11.5f - f, -4.5f - f, 1, 1, 1, 0.0f);
        head.texOffs(28, 5).addBox(-0.5f, -6.5f - f, -4.5f - f, 1, 1, 1, 0.0f);
        head.texOffs(32, 0).addBox(-1.5f, -9.5f - f, 3.5f + f, 3, 3, 1, 0.0f);
        head.texOffs(32, 4).addBox(-0.5f, -10.5f - f, 3.5f + f, 1, 1, 1, 0.0f);
        head.texOffs(36, 4).addBox(-0.5f, -6.5f - f, 3.5f + f, 1, 1, 1, 0.0f);

        // Seule la tete est rendue par ce modele (equivalent des cubeList.clear() de l'original)
        hat = new ModelRenderer(this, 0, 0);
        body = new ModelRenderer(this, 0, 0);
        rightArm = new ModelRenderer(this, 0, 0);
        leftArm = new ModelRenderer(this, 0, 0);
        rightLeg = new ModelRenderer(this, 0, 0);
        leftLeg = new ModelRenderer(this, 0, 0);
    }
}
