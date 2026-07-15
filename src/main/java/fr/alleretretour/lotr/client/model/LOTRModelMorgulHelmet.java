package fr.alleretretour.lotr.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

/**
 * TRANSPILE AUTOMATIQUEMENT depuis lotr.client.model.LOTRModelMorgulHelmet (1.7.10).
 * Geometrie identique a l'original ; textures Legacy reutilisees telles quelles.
 */
public class LOTRModelMorgulHelmet extends BipedModel<LivingEntity> {

    private final ModelRenderer[] spikes = new ModelRenderer[8];

    public LOTRModelMorgulHelmet(float f) {
        super(f);
        texWidth = 64;
        texHeight = 32;

        int i;
        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4.0f, -8.0f, -4.0f, 8, 12, 8, f);
        head.setPos(0.0f, 0.0f, 0.0f);
        head.texOffs(0, 20).addBox(-3.5f, -18.0f, -3.5f, 7, 10, 1, f);
        for (i = 0; i < spikes.length; ++i) {
        spikes[i] = new ModelRenderer(this, 16, 20);
        spikes[i].setPos(0.0f, 0.0f, 0.0f);
        }
        spikes[0].addBox(-1.0f, -5.5f, -10.0f, 1, 1, 4);
        spikes[0].xRot = -0.3490658503988659f;
        spikes[0].yRot = 0.3490658503988659f;
        spikes[1].addBox(0.0f, -5.5f, -10.0f, 1, 1, 4);
        spikes[1].xRot = -0.3490658503988659f;
        spikes[1].yRot = -0.3490658503988659f;
        spikes[2].addBox(6.0f, -5.5f, -1.0f, 4, 1, 1);
        spikes[2].zRot = -0.3490658503988659f;
        spikes[2].yRot = 0.3490658503988659f;
        spikes[3].addBox(6.0f, -5.5f, 0.0f, 4, 1, 1);
        spikes[3].zRot = -0.3490658503988659f;
        spikes[3].yRot = -0.3490658503988659f;
        spikes[4].addBox(0.0f, -5.5f, 6.0f, 1, 1, 4);
        spikes[4].xRot = 0.3490658503988659f;
        spikes[4].yRot = 0.3490658503988659f;
        spikes[5].addBox(-1.0f, -5.5f, 6.0f, 1, 1, 4);
        spikes[5].xRot = 0.3490658503988659f;
        spikes[5].yRot = -0.3490658503988659f;
        spikes[6].addBox(-10.0f, -5.5f, 0.0f, 4, 1, 1);
        spikes[6].zRot = 0.3490658503988659f;
        spikes[6].yRot = 0.3490658503988659f;
        spikes[7].addBox(-10.0f, -5.5f, -1.0f, 4, 1, 1);
        spikes[7].zRot = 0.3490658503988659f;
        spikes[7].yRot = -0.3490658503988659f;
        for (i = 0; i < spikes.length; ++i) {
        head.addChild(spikes[i]);
        }
        body = new ModelRenderer(this, 0, 0);
        rightArm = new ModelRenderer(this, 0, 0);
        leftArm = new ModelRenderer(this, 0, 0);
        rightLeg = new ModelRenderer(this, 0, 0);
        leftLeg = new ModelRenderer(this, 0, 0);
    }
}
