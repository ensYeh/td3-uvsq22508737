package fr.uvsq.cprog.collex;

public class DnsTUI {

    public Commande nextCommande(String saisie) {
        String[] parts = saisie.trim().split("\\s+");
        try {
            if (parts[0].equalsIgnoreCase("quit")) {
                return dns -> { System.out.println("Au revoir !"); System.exit(0); return ""; };
            } else if (parts[0].equals("ls")) {
                boolean all = parts.length > 2 && parts[1].equals("-a");
                String domaine = all ? parts[2] : parts[1];
                return dns -> {
                    for (DnsItem i : dns.getItems(domaine, all)) {
                        System.out.println(i.getAdresse() + " " + i.getNomMachine());
                    }
                    return "";
                };
            } else if (parts[0].equals("add")) {
                return dns -> {
                    dns.addItem(new AdresseIP(parts[1]), new NomMachine(parts[2]));
                    System.out.println("Ajout effectué !");
                    return "";
                };
            } else if (parts[0].contains(".")) {
                if (parts[0].matches("[0-9.]+")) {
                    return dns -> {
                        DnsItem item = dns.getItem(new AdresseIP(parts[0]));
                        System.out.println(item == null ? "Inconnu" : item.getNomMachine());
                        return "";
                    };
                } else {
                    return dns -> {
                        DnsItem item = dns.getItem(new NomMachine(parts[0]));
                        System.out.println(item == null ? "Inconnu" : item.getAdresse());
                        return "";
                    };
                }
            } else {
                return dns -> { System.out.println("Commande inconnue"); return ""; };
            }
        } catch (Exception e) {
            return dns -> { System.out.println("Erreur : " + e.getMessage()); return ""; };
        }
    }
}
