package fr.uvsq.cprog.collex;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest 
{

    @Test
    public void testAdresseIPValide(){
        AdresseIP adresseIP = new AdresseIP("192.51.25.12");
        assertEquals("192.51.25.12" , adresseIP.toString());
    }

    @Test
    public void testAdresseIPInvalide_OctetHorsLimite() {
        try {
            AdresseIP adresseIP = new AdresseIP("192.51.25.300");
            fail("Une exception aurait dû être levée");
        } catch (IllegalArgumentException e) {
            assertEquals("Octet hors limite : 300", e.getMessage());
        }
    }

    @Test
    public void testAdresseIPInvalide_MauvaisFormat(){
        try {
            AdresseIP adresseIP = new AdresseIP("192.51.25");
            fail("Une exception aurait dû être levée");
        } catch (IllegalArgumentException e) {
            assertEquals("Adresse IP invalide : 192.51.25", e.getMessage());
        }
    }

    @Test
    public void testAdresseIPInvalide_NonNumerique() {
        try {
            AdresseIP adresseIP = new AdresseIP("192.51.abc.12");
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
        NomMachine machine = new NomMachine("Www.uVsq.FR");
        assertEquals("www.uvsq.fr", machine.getFullName());
        assertEquals("www", machine.getNom());
        assertEquals("uvsq.fr", machine.getDomaine());
    }

    @Test
    public void testNomMachineInvalideSansPoint() {
        try {
            new NomMachine("wwwuvsqfr");
            fail("Une exception aurait dû être levée");
        } catch (IllegalArgumentException e) {
            assertEquals("Nom invalide : wwwuvsqfr", e.getMessage());
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


    //DnsItem
    @Test
    public void test_item(){

        NomMachine machine = new NomMachine("www.uvsq.fr");
        AdresseIP adresseIP = new AdresseIP("192.51.25.12");
        DnsItem dnsItem = new DnsItem(adresseIP , machine);
        assertEquals("www.uvsq.fr 192.51.25.12", dnsItem.toLine());

    }

}
