package fr.alleretretour.lotr.item;

/**
 * Port de lotr.common.item.LOTRItemThrowingAxe : meme mecanique que la lance,
 * charge plus courte (arme plus legere), degats de jet identiques a la mêlee.
 */
public class LOTRItemThrowingAxe extends LOTRItemSpear {

    public LOTRItemThrowingAxe(LOTRMaterial material) {
        super(material);
    }

    @Override
    public int getMaxDrawTime() {
        return 15;
    }
}
