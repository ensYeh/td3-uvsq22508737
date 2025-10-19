package fr.uvsq.cprog.collex;

public class DnsItem {
    private final AdresseIP adresse;
    private final NomMachine nomMachine;

    public DnsItem(AdresseIP adresse, NomMachine nomMachine) {
        this.adresse = adresse;
        this.nomMachine = nomMachine;
    }

    public AdresseIP getAdresse() { return adresse; }
    public NomMachine getNomMachine() { return nomMachine; }

    public String toLine() {
        return nomMachine.toString() + " " + adresse.toString();
    }
}
