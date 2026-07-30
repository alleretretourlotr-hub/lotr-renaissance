package fr.alleretretour.lotr.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

/**
 * PORT de LOTRModelHuman / LOTRModelElf / LOTRModelDwarf / LOTRModelHobbit.
 *
 * Le point essentiel : dans le Legacy, les CHEVEUX ne sont pas la couche
 * "chapeau" des skins modernes (UV 32,0) mais une boite dediee en UV (0, 32) :
 *   - humains et elfes : 8 x 16 x 8 (chevelure longue) ;
 *   - nains et hobbits : 8 x 12 x 8 ;
 * toujours avec un gonflement de 0.5.
 *
 * Les membres reprennent les UV du modele 1.7.10 : le bras et la jambe gauches
 * sont les copies MIROIR des droits (les peaux du Legacy n'ont pas de membres
 * gauches distincts - c'est justement cette zone qui contient les cheveux).
 *
 * Les elfes ajoutent leurs oreilles pointues.
 */
public class LOTRModelNPC<T extends LivingEntity> extends BipedModel<T> {

    /** Type de chevelure du Legacy. */
    public enum Hair {
        /** humains, elfes : boite de 16 de haut */
        LONG(16),
        /** nains, hobbits : boite de 12 de haut */
        SHORT(12),
        /** orques, trolls... : pas de boite de cheveux */
        NONE(0);

        final int height;

        Hair(int height) {
            this.height = height;
        }
    }

    public LOTRModelNPC(float scale, Hair hair, boolean pointedEars) {
        super(scale, 0.0f, 64, 64);

        // membres gauches en MIROIR des droits (UV 1.7.10)
        leftArm = new ModelRenderer(this, 40, 16);
        leftArm.mirror = true;
        leftArm.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4, scale);
        leftArm.setPos(5.0f, 2.0f, 0.0f);

        leftLeg = new ModelRenderer(this, 0, 16);
        leftLeg.mirror = true;
        leftLeg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4, scale);
        leftLeg.setPos(1.9f, 12.0f, 0.0f);

        // Les cheveux sont une piece DEDIEE, enfant du crane : elle suit la tete
        // sans la double rotation que subirait "hat" (BipedModel lui recopie
        // deja la rotation de la tete dans setupAnim).
        if (hair != Hair.NONE) {
            ModelRenderer hairPart = new ModelRenderer(this, 0, 32);
            hairPart.addBox(-4.0f, -8.0f, -4.0f, 8, hair.height, 8, 0.5f + scale);
            hairPart.setPos(0.0f, 0.0f, 0.0f);
            head.addChild(hairPart);
        }
        // "hat" reste vide : les peaux du Legacy n'ont pas de couche chapeau
        hat = new ModelRenderer(this, 0, 0);
        hat.setPos(0.0f, 0.0f, 0.0f);

        if (pointedEars) {
            // PORT de LOTRModelElf : oreilles pointues
            ModelRenderer earRight = new ModelRenderer(this, 0, 0);
            earRight.addBox(-4.0f, -6.5f, -1.0f, 1, 4, 2, scale);
            earRight.setPos(0.0f, 0.0f, 0.0f);
            head.addChild(earRight);
            ModelRenderer earLeft = new ModelRenderer(this, 0, 0);
            earLeft.mirror = true;
            earLeft.addBox(3.0f, -6.5f, -1.0f, 1, 4, 2, scale);
            earLeft.setPos(0.0f, 0.0f, 0.0f);
            head.addChild(earLeft);
        }
    }
}
