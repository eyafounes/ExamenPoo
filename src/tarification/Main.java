package tarification;

import tarification.domaine.Calcul;
import tarification.domaine.Categorie;
import tarification.domaine.EtapeCalcul;
import tarification.domaine.Panier;
import tarification.domaine.Produit;
import tarification.regles.RegleTarif;
import tarification.regles.RemiseCategorie;
import tarification.regles.RemiseFixe;
import tarification.regles.RemiseQuantite;
import tarification.regles.Taxe;

/** Main temporaire : on teste les règles une par une. */
public class Main {

    public static void main(String[] args) {
        // 1. Le panier : 1 livre + 2 cafés = 110 DT
        Produit livre = new Produit("L01", "Clean Code", Categorie.LIVRE, 85);
        Produit cafe = new Produit("A01", "Café", Categorie.ALIMENTAIRE, 12.5);
        Panier panier = new Panier();
        panier.ajouter(livre, 1);
        panier.ajouter(cafe, 2);
        // 2. Les règles du magasin (chacune est une Strategy)
        RegleTarif[] regles = {
                new RemiseCategorie(Categorie.LIVRE, 20), // -17 DT (20 % de 85)
                new RemiseFixe(10),                        // -10 DT
                new RemiseQuantite(5, 10),                 // rien : seulement 3 articles
                new Taxe("TVA", 19)                        // +19 %
        };

        // 3. On applique les règles l'une après l'autre
        Calcul calcul = Calcul.depart(panier.totalBrut());
        for (RegleTarif regle : regles) {
            calcul = regle.appliquer(panier, calcul);
        }
        // 4. On affiche le détail
        System.out.println("Total brut : " + Calcul.format(panier.totalBrut()));
        for (EtapeCalcul etape : calcul.getEtapes()) {
            System.out.println("  " + etape.getLibelle() + " : " + Calcul.format(etape.getVariation())
                    + " -> " + Calcul.format(etape.getMontantApres()));
        }
        System.out.println("TOTAL À PAYER : " + Calcul.format(calcul.getMontant()));
    }
}