package tarification.domaine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Panier {

    private final List<LignePanier> lignes = new ArrayList<>();

    public void ajouter(Produit produit, int quantite) {
        lignes.add(new LignePanier(produit, quantite));
    }

    public List<LignePanier> getLignes() {
        return Collections.unmodifiableList(new ArrayList<>(lignes));
    }

    public boolean estVide() {
        return lignes.isEmpty();
    }

    public double totalBrut() {
        double total = 0;
        for (LignePanier ligne : lignes) {
            total += ligne.sousTotal();
        }
        return Calcul.arrondir(total);
    }

    public double sousTotal(Categorie categorie) {
        double total = 0;
        for (LignePanier ligne : lignes) {
            if (ligne.getProduit().getCategorie() == categorie) {
                total += ligne.sousTotal();
            }
        }
        return Calcul.arrondir(total);
    }

    public int quantiteTotale() {
        int total = 0;
        for (LignePanier ligne : lignes) {
            total += ligne.getQuantite();
        }
        return total;
    }
}
