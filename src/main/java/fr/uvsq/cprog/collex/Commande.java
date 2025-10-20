package fr.uvsq.cprog.collex;

import java.io.IOException;

public interface Commande {
    String execute(Dns dns) throws IOException;

}
