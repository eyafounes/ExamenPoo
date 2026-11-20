package tarification.regles;

import tarification.domaine.Calcul;
import tarification.domaine.Panier;

/** Remise de X % si le panier contient au moins seuil articles. Ex. : -10 % dès 5 articles. */
public class RemiseQuantite implements RegleTarif {

    private int seuil;
    private double taux;

    public RemiseQuantite(int seuil, double taux) {
        if (seuil <= 0) {
            throw new IllegalArgumentException("Le seuil doit être > 0 : " + seuil);
        }
        if (taux < 0 || taux > 100) {
            throw new IllegalArgumentException("Le taux doit être entre 0 et 100 : " + taux);
        }
        this.seuil = seuil;
        this.taux = taux;
    }

    @Override
    public Calcul appliquer(Panier panier, Calcul calcul) {
        if (panier.quantiteTotale() < seuil) {
            return calcul; // pas assez d'articles : on ne change rien
        }
        double remise = calcul.getMontant() * taux / 100;
        return calcul.avec(calcul.getMontant() - remise, "Remise " + taux + " % dès " + seuil + " articles");
    }
}
