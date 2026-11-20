package tarification.domaine;

public class EtapeCalcul {

    private final String libelle;
    private final double variation;
    private final double montantApres;

    public EtapeCalcul(String libelle, double variation, double montantApres) {
        this.libelle = libelle;
        this.variation = variation;
        this.montantApres = montantApres;
    }

    public String getLibelle() {
        return libelle;
    }

    public double getVariation() {
        return variation;
    }

    public double getMontantApres() {
        return montantApres;
    }
}
