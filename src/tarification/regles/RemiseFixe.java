package tarification.regles;

import tarification.domaine.Calcul;
import tarification.domaine.Panier;

/** Remise d'un montant fixe. Ex. : -10 DT. Le prix ne descend jamais sous 0. */
public class RemiseFixe implements RegleTarif {

    private double valeur; // ex. 10 pour -10 DT

    public RemiseFixe(double valeur) {
        if (valeur < 0) {
            throw new IllegalArgumentException("La remise fixe ne peut pas être négative : " + valeur);
        }
        this.valeur = valeur;
    }

    @Override
    public Calcul appliquer(Panier panier, Calcul calcul) {
        double nouveauMontant = Math.max(0, calcul.getMontant() - valeur);
        return calcul.avec(nouveauMontant, "Remise fixe " + valeur + " DT");
    }
}