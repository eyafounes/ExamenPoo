package tarification.domaine;

public class LignePanier {

    private final Produit produit;
    private final int quantite;

    public LignePanier(Produit produit, int quantite) {
        if (quantite <= 0) {
            throw new IllegalArgumentException("La quantite doit etre superieure a 0 : " + quantite);
        }
        this.produit = produit;
        this.quantite = quantite;
    }

    public Produit getProduit() {
        return produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public double sousTotal() {
        return Calcul.arrondir(produit.getPrixUnitaire() * quantite);
    }
}
