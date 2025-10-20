package fr.uvsq.cprog.collex;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Dns {
    private final Path dbFile;
    private final Map<String, DnsItem> parNom = new HashMap<>();
    private final Map<AdresseIP, DnsItem> parAdresse = new HashMap<>();


    public Dns(Path propertiesPath) throws IOException {
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(propertiesPath)) {
            props.load(in);
        }
        dbFile = Paths.get(props.getProperty("dns.file"));
        charger();
    }

    private void charger() throws IOException {
        for (String ligne : Files.readAllLines(dbFile, StandardCharsets.UTF_8)) {
            if (ligne.isBlank() || ligne.startsWith("#")) continue;
            String[] parts = ligne.trim().split("\\s+");
            NomMachine nom = new NomMachine(parts[0]);
            AdresseIP ip = new AdresseIP(parts[1]);
            DnsItem item = new DnsItem(ip, nom);
            parNom.put(nom.toString(), item);
            parAdresse.put(ip, item);
        }
    }

    public void addItem(AdresseIP ip, NomMachine nom) throws IOException {
        if (parAdresse.containsKey(ip)) throw new IllegalArgumentException("ERREUR : L'adresse IP existe déjà !");
        if (parNom.containsKey(nom.toString())) throw new IllegalArgumentException("ERREUR : Le nom de machine existe déjà !");
        DnsItem item = new DnsItem(ip, nom);
        parAdresse.put(ip, item);
        parNom.put(nom.toString(), item);
        sauvegarder();
    }

    public List<DnsItem> getItems(String domaine, boolean triParAdresse) {
        Comparator<DnsItem> cmp = triParAdresse
                ? Comparator.comparing(DnsItem::getAdresse)
                : Comparator.comparing(d -> d.getNomMachine().getNom());
        return parNom.values().stream()
                .filter(i -> i.getNomMachine().getDomaine().equalsIgnoreCase(domaine))
                .sorted(cmp)
                .collect(Collectors.toList());
    }

    private void sauvegarder() throws IOException {
        List<String> lignes = parNom.values().stream()
                .map(DnsItem::toLine)
                .sorted()
                .collect(Collectors.toList());
        Files.write(dbFile, lignes, StandardCharsets.UTF_8);
    }









    public DnsItem getItem(AdresseIP ip) { return parAdresse.get(ip); }
    public DnsItem getItem(NomMachine nom) { return parNom.get(nom.toString()); }

}
