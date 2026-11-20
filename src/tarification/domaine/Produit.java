package tarification.domaine;

public class Produit {

    private final String code;
    private final String nom;
    private final Categorie categorie;
    private final double prixUnitaire;

    public Produit(String code, String nom, Categorie categorie, double prixUnitaire) {
        if (prixUnitaire < 0) {
            throw new IllegalArgumentException("Le prix ne peut pas etre negatif : " + prixUnitaire);
        }
        this.code = code;
        this.nom = nom;
        this.categorie = categorie;
        this.prixUnitaire = prixUnitaire;
    }

    public String getCode() {
        return code;
    }

    public String getNom() {
        return nom;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }
}
