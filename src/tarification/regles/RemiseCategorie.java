package tarification.regles;

import tarification.domaine.Calcul;
import tarification.domaine.Categorie;
import tarification.domaine.Panier;

/** Remise de X % calculée seulement sur les produits d'une catégorie. Ex. : -20 % sur les livres. */
public class RemiseCategorie implements RegleTarif {

    private Categorie categorie;
    private double taux;

    public RemiseCategorie(Categorie categorie, double taux) {
        if (taux < 0 || taux > 100) {
            throw new IllegalArgumentException("Le taux doit être entre 0 et 100 : " + taux);
        }
        this.categorie = categorie;
        this.taux = taux;
    }

    @Override
    public Calcul appliquer(Panier panier, Calcul calcul) {
        double sousTotal = panier.sousTotal(categorie);
        if (sousTotal == 0) {
            return calcul; // aucun produit de cette catégorie : on ne change rien
        }
        double remise = sousTotal * taux / 100;
        double nouveauMontant = Math.max(0, calcul.getMontant() - remise);
        return calcul.avec(nouveauMontant, "Remise " + taux + " % sur " + categorie);
    }
}