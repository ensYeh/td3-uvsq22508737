package fr.uvsq.cprog.collex;

public class NomMachine {
    private final String fullName;
    private final String nom;
    private final String domaine;


    public NomMachine(String fullName) {
        if (fullName == null || !fullName.contains(".")) {
            throw new IllegalArgumentException("Nom invalide : " + fullName);
        }
        this.fullName = fullName.toLowerCase();
        int idx = this.fullName.indexOf('.');
        this.nom = this.fullName.substring(0, idx);
        this.domaine = this.fullName.substring(idx + 1);
    }


    @Override
    public String toString() {
        return fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getNom() {
        return nom;
    }

    public String getDomaine() {
        return domaine;
    }
}
