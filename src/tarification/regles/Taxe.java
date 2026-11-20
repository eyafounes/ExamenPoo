package tarification.regles;

import tarification.domaine.Calcul;
import tarification.domaine.Panier;

/** Taxe de X % ajoutée au montant. Ex. : TVA 19 %. */
public class Taxe implements RegleTarif {

    private String nom;
    private double taux;

    public Taxe(String nom, double taux) {
        if (taux < 0 || taux > 100) {
            throw new IllegalArgumentException("Le taux doit être entre 0 et 100 : " + taux);
        }
        this.nom = nom;
        this.taux = taux;
    }

    @Override
    public Calcul appliquer(Panier panier, Calcul calcul) {
        double montantTaxe = calcul.getMontant() * taux / 100;
        return calcul.avec(calcul.getMontant() + montantTaxe, nom + " " + taux + " %");
    }
}