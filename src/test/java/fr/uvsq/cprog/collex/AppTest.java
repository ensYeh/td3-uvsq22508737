package fr.uvsq.cprog.collex;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest 
{

    @Test
    public void testAdresseIPValide(){
        AdresseIP adresseIP = new AdresseIP("192.51.25.12");
        assertTrue(adresseIP.is_valid_adresse());
    }

    @Test
    public void testAdresseIPInvalide_OctetHorsLimite() {
        AdresseIP adresseIP = new AdresseIP("192.51.25.300"); // 300 invalide
        try {
            adresseIP.is_valid_adresse();
            fail("Une exception aurait dû être levée");
        } catch (IllegalArgumentException e) {
            assertEquals("Octet hors limite : 300", e.getMessage());
        }
    }

    @Test
    public void testAdresseIPInvalide_MauvaisFormat(){
        AdresseIP adresseIP = new AdresseIP("192.51.25");
        try {
            adresseIP.is_valid_adresse();
            fail("Une exception aurait dû être levée");
        } catch (IllegalArgumentException e) {
            assertEquals("Adresse IP invalide : 192.51.25", e.getMessage());
        }
    }

    @Test
    public void testAdresseIPInvalide_NonNumerique() {
        AdresseIP adresseIP = new AdresseIP("192.51.abc.12"); // "abc" non numérique
        try {
            adresseIP.is_valid_adresse();
            fail("Une exception aurait dû être levée");
        } catch (IllegalArgumentException e) {
            assertEquals("Octet non numérique : abc", e.getMessage());
        }
    }




    //Des test pour la class NomMachine

    @Test
    public void testNomMachineValide() {
        NomMachine machine = new NomMachine("www.uvsq.fr");

        assertEquals("www.uvsq.fr", machine.getFullName());
        assertEquals("www", machine.getNom());
        assertEquals("uvsq.fr", machine.getDomaine());
    }

    @Test
    public void testNomMachineAvecMajuscules() {
        NomMachine machine = new NomMachine("Serveur.Example.COM");
        assertEquals("serveur.example.com", machine.getFullName());
        assertEquals("serveur", machine.getNom());
        assertEquals("example.com", machine.getDomaine());
    }

    @Test
    public void testNomMachineInvalideSansPoint() {
        try {
            new NomMachine("serveurexamplecom");
            fail("Une exception aurait dû être levée");
        } catch (IllegalArgumentException e) {
            assertEquals("Nom invalide : serveurexamplecom", e.getMessage());
        }
    }

    @Test
    public void testNomMachineNull() {
        try {
            new NomMachine(null);
            fail("Une exception aurait dû être levée");
        } catch (IllegalArgumentException e) {
            assertEquals("Nom invalide : null", e.getMessage());
        }
    }

}
