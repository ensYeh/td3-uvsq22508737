package fr.uvsq.cprog.collex;

public class AdresseIP implements Comparable<AdresseIP>{
    private final int[] octets = new int[4];
    public AdresseIP(String adresse) {
        String[] parts = adresse.trim().split("\\.");
        if (parts.length != 4)
            throw new IllegalArgumentException("Adresse IP invalide : " + adresse);

        for (int i = 0; i < 4; i++) {
            try {
                int v = Integer.parseInt(parts[i]);
                if (v < 0 || v > 255)
                    throw new IllegalArgumentException("Octet hors limite : " + v);
                octets[i] = v;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Octet non numérique : " + parts[i]);
            }
        }

    }


    @Override
    public String toString() {
        return String.format("%d.%d.%d.%d", octets[0], octets[1], octets[2], octets[3]);
    }


    @Override
    public int compareTo(AdresseIP other) {
        for (int i = 0; i < 4; i++) {
            int diff = this.octets[i] - other.octets[i];
            if (diff != 0) return diff;
        }
        return 0;
    }
}
