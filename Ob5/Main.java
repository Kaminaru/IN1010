import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        String filnavn = null;
        boolean detaljert = false;
        if (args.length > 0) {
            filnavn = args[0];
        } else {
            System.out.println("FEIL! Riktig bruk av programmet: "
                               +"java Oblig5 <labyrintfil>");
            return;
        }
        // hvis bruker skriver detaljert saa skal det skrives ut alle mulighe utveier, ellers skal de bare skrives maks utveier og korteste utvei
        if(args.length > 1){
          if(args[1].equals("detaljert")){
            detaljert = true;
          }
        }

        File fil = new File(filnavn);
        Labyrint l = null;
        try {
            l = Labyrint.lesFraFil(fil);
        } catch (FileNotFoundException e) {
            System.out.printf("FEIL: Kunne ikke lese fra '%s'\n", filnavn);
            System.exit(1);
        }

        // les start-koordinater fra standard input
        Scanner inn = new Scanner(System.in);
        System.out.println("Skriv inn koordinater <kolonne> <rad> ('a' for aa avslutte)");
        String[] ord = inn.nextLine().split(" ");
        while (!ord[0].equals("a")) {

            try {
                int startKol = Integer.parseInt(ord[0]);
                int startRad = Integer.parseInt(ord[1]);

                Liste<String> utveier = l.finnUtveiFra(startKol, startRad);
                if (utveier.stoerrelse() != 0) {
                    int minst = Integer.MAX_VALUE;
                    String veien = "";
                    for (String s : utveier) {
                        if(detaljert == true){
                          System.out.println("\n"+s);
                        }

                        if(s.length() < minst){
                          minst = s.length();
                          veien = s;
                        }
                    }
                // Skriver ut antall utveier og korteste vei
                    System.out.println("\nDet ble fant: " + utveier.stoerrelse());
                    if(detaljert == true){
                      System.out.println(l);
                    }
                    System.out.println("Korteste veien: ");
                    System.out.println(veien);
                } else {
                    System.out.println("Ingen utveier.");
                }
                System.out.println();
            } catch (NumberFormatException e) {
                System.out.println("Ugyldig input!");
            }
            System.out.println("Skriv inn nye koordinater ('a' for aa avslutte)");
            ord = inn.nextLine().split(" ");
        }
    }
}
