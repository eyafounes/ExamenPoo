package tarification.regles;

import tarification.domaine.Calcul;
import tarification.domaine.Panier;

/** Remise de X % sur tout le panier. Ex. : -10 %. */
public class RemiseGlobale implements RegleTarif {

    private double taux; // ex. 10 pour 10 %

    public RemiseGlobale(double taux) {
        if (taux < 0 || taux > 100) {
            throw new IllegalArgumentException("Le taux doit être entre 0 et 100 : " + taux);
        }
        this.taux = taux;
    }

    @Override
    public Calcul appliquer(Panier panier, Calcul calcul) {
        double remise = calcul.getMontant() * taux / 100;
        return calcul.avec(calcul.getMontant() - remise, "Remise globale " + taux + " %");
    }
}