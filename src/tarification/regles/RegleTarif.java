package tarification.regles;

import tarification.domaine.Calcul;
import tarification.domaine.Panier;

/**
 * Pattern STRATEGY : le contrat commun à toutes les règles de prix.
 * Une règle reçoit le panier et le calcul en cours, et renvoie le nouveau calcul.
 */
@FunctionalInterface
public interface RegleTarif {

    Calcul appliquer(Panier panier, Calcul calcul);
}