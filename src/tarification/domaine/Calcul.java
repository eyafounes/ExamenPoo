package tarification.domaine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Calcul {

    private final double montant;
    private final List<EtapeCalcul> etapes;

    // private : on passe par depart() ou avec() pour creer un Calcul
    private Calcul(double montant, List<EtapeCalcul> etapes) {
        this.montant = montant;
        this.etapes = etapes;
    }

    public static Calcul depart(double totalBrut) {
        return new Calcul(arrondir(totalBrut), new ArrayList<>());
    }

    public Calcul avec(double nouveauMontant, String libelle) {
        double nouveau = arrondir(nouveauMontant);
        double variation = arrondir(nouveau - montant);

        List<EtapeCalcul> nouvellesEtapes = new ArrayList<>(etapes);
        nouvellesEtapes.add(new EtapeCalcul(libelle, variation, nouveau));

        return new Calcul(nouveau, nouvellesEtapes);
    }

    public double getMontant() {
        return montant;
    }

    public List<EtapeCalcul> getEtapes() {
        return Collections.unmodifiableList(new ArrayList<>(etapes));
    }

    public static double arrondir(double m) {
        return Math.round(m * 1000) / 1000.0;
    }

    public static String format(double m) {
        return String.format(Locale.ROOT, "%.3f DT", m);
    }
}
