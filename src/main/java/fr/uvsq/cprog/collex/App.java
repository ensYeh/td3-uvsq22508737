package fr.uvsq.cprog.collex;


import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class App
{
    public static void main( String[] args ) throws IOException {
        Dns dns = new Dns(Paths.get("D:\\uvsq\\td3\\td3-uvsq22508737\\src\\main\\resources\\dns.properties"));

        DnsTUI tui = new DnsTUI();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String line = sc.nextLine();
            Commande cmd = tui.nextCommande(line);
            cmd.execute(dns);
        }
    }
}
