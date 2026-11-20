package tarification;

import tarification.domaine.Calcul;
import tarification.domaine.Categorie;
import tarification.domaine.Panier;
import tarification.domaine.Produit;

public class Main {

    public static void main(String[] args) {
        Produit livre = new Produit("L001", "Clean Code", Categorie.LIVRE, 85);
        Produit cafe = new Produit("A001", "Cafe", Categorie.ALIMENTAIRE, 12.5);

        Panier panier = new Panier();
        panier.ajouter(livre, 1);
        panier.ajouter(cafe, 2);

        System.out.println("Total brut         : " + Calcul.format(panier.totalBrut()));
        System.out.println("Sous-total livres  : " + Calcul.format(panier.sousTotal(Categorie.LIVRE)));
        System.out.println("Quantite totale    : " + panier.quantiteTotale());

        Calcul depart = Calcul.depart(panier.totalBrut());
        Calcul apresRemise = depart.avec(100, "Remise test");

        System.out.println();
        System.out.println("Calcul de depart   : " + Calcul.format(depart.getMontant())
                + " (" + depart.getEtapes().size() + " etape)");
        System.out.println("Apres remise test  : " + Calcul.format(apresRemise.getMontant())
                + " (" + apresRemise.getEtapes().size() + " etape)");
        System.out.println("Variation          : " + Calcul.format(apresRemise.getEtapes().get(0).getVariation()));
    }
}
