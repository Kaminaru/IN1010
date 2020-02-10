import java.util.*;
import java.io.*;
// import java.io.File;
// import java.util.Scanner;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.io.PrintWriter;


public class Legesystem{
  static Lenkeliste<Pasient> pasienterListe = new Lenkeliste<Pasient>(); // lenkedliste av pasienter
  static Lenkeliste<Legemiddel> legemidlerListe = new Lenkeliste<Legemiddel>(); // lenkedliste av legemidler
  static SortertLenkeliste<Lege> legeListe = new SortertLenkeliste<Lege>(); // sortert lenkedliste av leger
  static Lenkeliste<Resept> reseptListe = new Lenkeliste<Resept>(); // lenkedliste av resepter

    // Program startes her
    public static void main(String[] args){
     File fil = new File("inndata.txt");
     lesFraFil(fil); // leser info fra film
     hovedmeny(); // starter hovedmeny
    }

    // leser informasjon fra fil og legger n%dvendig objekter i lenkedlister
    private static void lesFraFil(File fil){
        Scanner scanner = null;
        try{
            scanner = new Scanner(fil);
        }catch(FileNotFoundException e){
            System.out.println("Fant ikke filen, starter opp som et tomt Legesystem");
            return;
        }


        String innlest = scanner.nextLine();
        while(scanner.hasNextLine()){

            String[] info = innlest.split(" ");

            // Legger til alle pasienten i filen
            if(info[1].compareTo("Pasienter") == 0){
                while(scanner.hasNextLine()) {
                    innlest = scanner.nextLine();

                    //Om vi er ferdig med å legge til pasienterListe, bryt whileløkken,
                    //slik at vi fortsetter til koden for å legge til legemiddler
                    if(innlest.charAt(0) == '#'){
                        break;
                    }
                    String[] paslinje = innlest.split(", "); // splitter innlest linje paa spesielt maatte
                    Pasient pasient = new Pasient(paslinje[0],paslinje[1]); // lager pasient
                    pasienterListe.leggTil(pasient); // legger inn i liste
                }

            }
            //Legger inn Legemidlene
            else if(info[1].compareTo("Legemidler") == 0){
                while(scanner.hasNextLine()){
                    innlest = scanner.nextLine();
                    //Om vi er ferdig med å legge til legemidler, bryt whileløkken,
                    //slik at vi fortsetter til koden for å legge til leger
                    if(innlest.charAt(0) == '#'){
                        break;
                    }

                    String[] legemiddel = innlest.split(", ");
                    Double pris = Double.valueOf(legemiddel[2]);
                    Double virk = Double.valueOf(legemiddel[3]);
                    if(legemiddel[1].compareTo("a") == 0){
                      PreparatA preparatA = new PreparatA(legemiddel[0], pris, virk, Integer.valueOf(legemiddel[4]));
                      legemidlerListe.leggTil(preparatA);
                    }
                    else if(legemiddel[1].compareTo("b") == 0){
                      PreparatB preparatB = new PreparatB(legemiddel[0], pris, virk, Integer.valueOf(legemiddel[4]));
                      legemidlerListe.leggTil(preparatB);
                    }else if (legemiddel[1].compareTo("c") == 0){
                      PreparatC preparatC = new PreparatC(legemiddel[0], pris, virk);
                      legemidlerListe.leggTil(preparatC);
                    }
                }
            }

            //Legger inn leger
            else if(info[1].compareTo("Leger") == 0){
                while(scanner.hasNextLine()){
                    innlest = scanner.nextLine();
                    //Om vi er ferdig med å legge til leger, bryt whileløkken,
                    //slik at vi fortsetter til koden for å legge til resepter
                    if(innlest.charAt(0) == '#'){
                        break;
                    }

                    info = innlest.split(", ");

                    int kontrollid = Integer.parseInt(info[1]);
                    // sjekker om det er spesialist eller vanlig lege
                    if(kontrollid == 0){
                      Lege lege = new Lege(info[0]);
                      legeListe.leggTil(lege);
                    }else{
                      Spesialist spes = new Spesialist(info[0], kontrollid);
                      legeListe.leggTil(spes);
                    }
                }
            }


            //Legger inn Resepter
            else if(info[1].compareTo("Resepter") == 0){
                while(scanner.hasNextLine()){
                    innlest = scanner.nextLine();
                    info = innlest.split(", ");
                    // valgt Legemiddel

                    Legemiddel valgtLegemid = null;
                    int id = Integer.valueOf(info[0]); // id av legemiddel som trenges aa finne
                    for(Legemiddel l : legemidlerListe){
                      if(l.hentId() == id){
                        valgtLegemid = l;
                      }
                    }


                    // valgt pasient
                    Pasient valgtPasient = null;
                    id = Integer.valueOf(info[2]); // id av pasient som trenges aa finne
                    for(Pasient p : pasienterListe){
                      if(p.hentId() == id){
                        valgtPasient = p;
                      }
                    }

                    // valgt Lege
                    Lege valgtLege = null;
                    for(Lege l : legeListe){
                      if(l.hentNavn().equals(info[1])){
                        valgtLege = l;
                      }
                    }

                    // hhmmm maa jeg velge spesielt resept type?? Valgt Blaa som start
                    try{
                      Resept nyResept = valgtLege.skrivBlaaResept(valgtLegemid, valgtPasient, Integer.valueOf(info[3]));
                      reseptListe.leggTil(nyResept);
                    }catch(UlovligUtskrift e){
                      System.out.println(e);
                    }
                }
            }
        }
        scanner.close();
    }



    // hovedmeny som har forskjellige valgt muligheter.
    public static void hovedmeny(){
      String svar = " ";
      while(!svar.equals("6")){ // skal kj%r opp til bruker skriver 6 i input.
        System.out.println("\nHovedmeny:");
        System.out.println("1. Skriv ut alt\n2. Legge til et nytt element\n3. Bruk av resept");
        System.out.println("4. Statistikk\n5. Skriv DataBase i fil\n6. Avslute program");
        Scanner myObj = new Scanner(System.in);  // lager scanner objekt
        System.out.println("Hva vil du velge? (nummer)");
        svar = myObj.nextLine();  // Read user input

        switch(svar){
          case "1":
            oversikt();
            break;
          case "2":
            leggTilNy();
            break;
          case "3":
            reseptBruk();
            break;
          case "4":
            statistikk();
            break;
          case "5":
            lagreData();
            break;
          case "6":
            System.out.println("\n   Takk for bruk av vaar Database");
            System.exit(1);
          default:
            System.out.println("Ugyldig input"); // for at den skal ikke skrives ut naar bruker skriver
            break;
        }
      }
    }

    public static void oversikt(){
      System.out.println("\n-------------------------");
      System.out.println("   Pasient liste:");
      System.out.println("-------------------------");
      pasienterListe.skrivAlle();
      System.out.println("\n-------------------------");
      System.out.println("   Legemidel liste:");
      System.out.println("-------------------------");
      legemidlerListe.skrivAlle();
      System.out.println("\n-------------------------");
      System.out.println("   Lege liste:");
      System.out.println("-------------------------");
      legeListe.skrivAlle();
      System.out.println("\n-------------------------");
      System.out.println("   Resept liste:");
      System.out.println("-------------------------");
      reseptListe.skrivAlle();
    }

    // bruker velger hva han vil legge inn i database
    public static void leggTilNy(){
      String svar1 = " "; String svar2 = " "; String svar3 = " "; String svar4 = " "; String svar5 = " ";
      Scanner myObj = new Scanner(System.in);  // lager scanner objekt
      // Legg til Meny
      System.out.println("Hva vil du legge til?");
      System.out.println("---------------------");
      System.out.println("1. Lege\n2. Pasient\n3. Resept\n4. Legemiddel");
      System.out.println("---------------------");
      System.out.println("5. Gaa bak til hovedmeny");
      svar1 = myObj.nextLine();  // Read user input

      // legger til Lege
      if(svar1.equals("1")){
        System.out.println("Skriv inn lege navn: ");
        svar1 = myObj.nextLine();

        int valgtKont = -1;
        while(valgtKont < 0){ // for aa bli sikkert at bruker skriver tall og ikke noen andre
          try{
            System.out.println("Skriv kontrollid (0, om det er ingen kontroll id): ");
            svar2 = myObj.nextLine();
            valgtKont = Integer.valueOf(svar2);
          }catch(NumberFormatException i){
            System.out.println("\nBare tall er lovlig\n");
          }
        }

        if(Integer.valueOf(valgtKont) == 0){
          Lege lege = new Lege(svar1);
          legeListe.leggTil(lege);
        }else{
          Spesialist spesialist = new Spesialist(svar1,valgtKont);
          legeListe.leggTil(spesialist);
        }


      // Legger til Pasient
      }else if(svar1.equals("2")){
        System.out.println("Skriv inn pasient navn: ");
        svar1 = myObj.nextLine();

        int fodsels = -1;
        while(fodsels < 0){ // for aa bli sikkert at bruker skriver tall og ikke noen andre
          try{
            System.out.println("Skriv pasientet fodselsnummer: ");
            svar2 = myObj.nextLine();
            fodsels = Integer.valueOf(svar2);
          }catch(NumberFormatException i){
            System.out.println("\nBare tall er lovlig\n");
          }
        }
        Pasient pasient = new Pasient(svar1,String.valueOf(fodsels));  // oppgave onsker at fodselsnum blir String
        pasienterListe.leggTil(pasient);



      // Legger til Resept
      }else if(svar1.equals("3")){
        // valgt Legemiddel
        Legemiddel valgtLegemid = null;
        int f = 0; // bruker har 5 feil forsøk
        while(valgtLegemid == null){ // stopper når bruker skriver riktig Legemiddel
          if(f == 5){
            System.out.println("\nAlt for mye forsok. Returneres i hovedmeny");
            hovedmeny();
          }
          System.out.println("Skriv inn legemiddel nummer: ");
          for(Legemiddel l : legemidlerListe){ // skriver ut alle legemidler
            System.out.println(l);
          }
          svar1 = myObj.nextLine();
          try{
            valgtLegemid = legemidlerListe.hent(Integer.valueOf(svar1));
            System.out.println("\nLegemiddel med navn " + valgtLegemid.hentNavn() + " ble valgt!\n");
          } catch(Exception e){
            System.out.println("\nFeil legemiddel nummer, prov igjen\n");
            f++;
          }
        }

        // valgt pasient
        Pasient valgtPasient = null;
        f = 0;
        while(valgtPasient == null){
          if(f == 5){
            System.out.println("\nAlt for mye forsok. Returneres i hovedmeny");
            hovedmeny();
          }
          System.out.println("Skriv pasientet ID: ");
          for(Pasient p : pasienterListe){ // skriver ut alle pasienter
            System.out.println(p);
          }
          svar2 = myObj.nextLine();
          try{
            valgtPasient = pasienterListe.hent(Integer.valueOf(svar2));
            System.out.println("\nPasient med navn " + valgtPasient.hentNavn() + " ble valgt!\n");
          } catch(Exception e){
            System.out.println("\nFeil i gitt pasient ID, prov igjen\n");
            f++;
          }
        }

        // valgt Lege
        Lege valgtLege = null;
        f = 0;
        while(valgtLege == null){ // kjores opp til bruker velger lege
          if(f == 5){
            System.out.println("\nAlt for mye forsok. Returneres i hovedmeny");
            hovedmeny();
          }
          System.out.println("Skriv lege navn: ");
          for(Lege p : legeListe){ // skriver ut alle legemidler
            System.out.println(p);
          }
          svar3 = myObj.nextLine();
          try{
            for(Lege l : legeListe){
              if(l.hentNavn().equals(svar3)){
                valgtLege = l;
              }
            }
            System.out.println("\nLege med navn " + valgtLege.hentNavn() + " ble valgt!\n");
          } catch(Exception e){
            System.out.println("\nFeil i gitt Lege navn, prov igjen\n");
            f++;
          }
        }

        int valgtReit = -1;
        while(valgtReit < 0){
          try{
            System.out.println("Skriv antall av reit(ikke mindre enn 0): ");
            svar4 = myObj.nextLine();
            valgtReit = Integer.valueOf(svar4);
          }catch(NumberFormatException i){
            System.out.println("\nBare tall er lovlig\n");
          }
        }


        // valg av resept type
        int valgtType = 0;
        while(valgtType < 1 || valgtType > 4){ // kjores opp til bruker velger gyldig nummer i meny
          try{
            System.out.println("\nHvilken resept type vil du lage? Mulig: ");
            System.out.println("1. Hvit\n2. Blaa\n3. Millitar\n4. PResept");
            valgtType = Integer.valueOf(myObj.nextLine());
          }catch(NumberFormatException i){
            System.out.println("\nBare tall er lovlig!\n");
          }
        }

          try{
            String reseptTypeInfo = ""; // bruker det for aa skrive ut info om skrevet ut Resept
            Resept nyResept = null;
            if(valgtType == 1){
              nyResept = valgtLege.skrivHvitResept(valgtLegemid, valgtPasient, valgtReit);
              reseptTypeInfo = "Hvit";
            }else if(valgtType == 2){
              nyResept = valgtLege.skrivBlaaResept(valgtLegemid, valgtPasient, valgtReit);
              reseptTypeInfo = "Blaa";
            }else if(valgtType == 3){
              nyResept = valgtLege.skrivMillitarResept(valgtLegemid, valgtPasient, valgtReit);
              reseptTypeInfo = "Millitar";
            }else if(valgtType == 4){
              nyResept = valgtLege.skrivPResept(valgtLegemid, valgtPasient);
              reseptTypeInfo = "PResept";
            }
            reseptListe.leggTil(nyResept);
            System.out.println("\nNy Resept av type " + reseptTypeInfo +"ble lagt inn i system");
          }catch(UlovligUtskrift e){
            System.out.println(e);
            hovedmeny();
          }





      // legger til Legemiddel
      }else if(svar1.equals("4")){
        System.out.println("Skriv inn navn til legemiddel: ");
        svar1 = myObj.nextLine();
        System.out.println("Type av legemmiddel (a,b,c): ");
        svar2 = myObj.nextLine();

        // alle de variablene her for at aa bli sikkert at bruker skrevet tall og ikke string
        double valgtPris = -1;
        double valgtMengde = -1;
        int valgtStyrke = -1;
        while(valgtStyrke < 0){
          try{
            System.out.println("Pris: ");
            svar3 = myObj.nextLine();
            valgtPris = Double.valueOf(svar3);

            System.out.println("Virkestoff mengde: ");
            svar4 = myObj.nextLine();
            valgtMengde = Double.valueOf(svar4);

            System.out.println("Styrke av legemiddel: ");
            svar5 = myObj.nextLine();
            valgtStyrke = Integer.valueOf(svar5);

          }catch(NumberFormatException i){
            System.out.println("\nFeil input, prov igjen med riktig tall\n");
          }
        }



        if(svar2.equals("a")){
          PreparatA preparatA = new PreparatA(svar1, valgtPris, valgtMengde, valgtStyrke);
          legemidlerListe.leggTil(preparatA);
        }
        else if(svar2.equals("b")){
          PreparatB preparatB = new PreparatB(svar1, valgtPris, valgtMengde, valgtStyrke);
          legemidlerListe.leggTil(preparatB);
        }else if (svar2.equals("c")){
          PreparatC preparatC = new PreparatC(svar1, valgtPris, valgtMengde);
          legemidlerListe.leggTil(preparatC);
        }else{ // hvis bruker skrevet feil bokstav for type. Skal returnerer til hovedmeny
          System.out.println("\n Typen av legemiddel ble vaglt feil, Legemiddel ble ikke lagt inn i system, prov igjen"); // om bruker skriver feil type av legemiddel
        }

      }

      if(svar1.equals("5")){ // hvis bruker vil gaa bak til hovedmeny
        hovedmeny();
      }

    }


    // skriver ut resept, bruker kan velge hvilken resept han vil bruke
    public static void reseptBruk(){
      Scanner myObj = new Scanner(System.in);  // lager scanner objekt
      String svar1 = " ";

      // Velges pasient
      Pasient valgtPasient = null;
      int f = 0;
      while(valgtPasient == null){
        if(f == 5){
          System.out.println("\nAlt for mye forsok. Returneres i hovedmeny");
          hovedmeny();
        }
        try{
          System.out.println("\n Hvilken pasient vil du se resepter for? (ID)");
          pasienterListe.skrivAlle();
          svar1 = myObj.nextLine();
          valgtPasient = pasienterListe.hent(Integer.valueOf(svar1));
          if(valgtPasient.hentReseptListe().stoerrelse() == 0){ // sjekker om pasient har noen resepter for aa bruke
            System.out.println("\nDen pasient har ikke noen resepter");
            hovedmeny();
          }
          System.out.println("\nValgt pasient: " + valgtPasient.hentNavn() + " ( fnr: " + valgtPasient.hentFod() + ")");
        } catch(Exception e){
          System.out.println("\nFeil i gitt pasient ID, prov igjen");
          f++;
        }
      }

      // Skriver ut alle resepter for pasient som ble valgt
      System.out.println("Hvilken resept vil du bruke?");
      try{
        int tempid = 0;
        for(Resept r : valgtPasient.hentReseptListe()){ // for each resept i pasientreseptliste
          System.out.println(tempid+": " + r.hentLegemiddel().hentNavn() + " (" + r.hentReit() + " reit)"); // skriver ut resept og reit som er igjen
          tempid++;
        }
        svar1 = myObj.nextLine();
        Resept tempValgtRes = valgtPasient.hentReseptListe().hent(Integer.valueOf(svar1)); // resept som ble valgt
          if(tempValgtRes.bruk()){ // returnerer True om det er fortsatt noen reit og tar bort en reit fra Resept
            System.out.println("\nBrukte resept paa " + tempValgtRes.hentLegemiddel().hentNavn() + ". Antall gjenvaerende reit :" + tempValgtRes.hentReit());
          }else{
            System.out.println("\nKunne ikke bruke resept paa " + tempValgtRes.hentLegemiddel().hentNavn() + " ( ingen gjenvaerende reit )");
          }
      }catch(UgyldigListeIndeks e){
        System.out.println("\nUgyldig Index");
        hovedmeny();
      }
    }

    // Forstaatt ikke helt hvordan statistikk maa se ut i 'undermeny' av brukermenyen. Saa laget den i egen avdeling i menyen
    // metode som skriver ut statistikk om database
    public static void statistikk(){
      int antallVandan = 0; // antall vanedannende resepter
      int antallNarkot = 0; // antall narkotiske resepter
      for(Resept r : reseptListe){
        if(r.hentLegemiddel().hentVanedannendeStyrke() != 0000000000){ // hvis hentVandennendeStyrke har ikke noen andre tall enn 0000000000 saa betyr det at det er B preparat (vanedannende)
          antallVandan++;
        }
        // hvis legemiddel hentNarkotiskStyrke returnerer noen andre enn 0000000000 betyr det at den legemidel er narkotisk type
        if(r.hentLegemiddel().hentNarkotiskStyrke() != 0000000000){
          antallNarkot++;
        }
      }

      System.out.println("\nTotalt antall utskrevne resepter paa vanedannende legemidler: " + antallVandan);
      System.out.println("Totalt antall utskrevne resepter paa narkotiske legemidler: " + antallNarkot);

      System.out.println("\nListe av leger som har skrevet ut resept paa narkotiske legemidler\n");
      int antall = 0; // antall narkotiske legemiddler som ble skrevet av bestemt lege
      for(Lege l : legeListe){
        antall = 0;
        boolean fant = false;
        for(Resept r : l.hentReseptListe()){
          if(r.hentLegemiddel().hentNarkotiskStyrke() != 0000000000){ // hvis den legemidel er narkotisk saa
            antall++;
            fant = true; // fant lik true
          }
        }
        if(fant == true){ // hvis vi finner at lege har skrevet narkotisk legemiddel
          System.out.println("Navn: " + l.hentNavn() + "  ||  Antall resepter med nark. legemiddler: " + antall);
        }
      }



      // samme logikken som med lege som har skrevet narkotiske legemidler
      System.out.println("\nListe av pasienter som har minst en gyldig resept paa narkotiske Legemiddler:\n");
      for(Pasient p : pasienterListe){
        antall = 0;
        boolean fant = false;
        for(Resept r : p.hentReseptListe()){
          if(r.hentLegemiddel().hentNarkotiskStyrke() != 0000000000){
            antall++;
            fant = true;
          }
        }
        if(fant == true){
          System.out.println("Navn: " + p.hentNavn() + "  ||  Antall resepter med nark. legemiddler: " + antall);
        }
      }

    }


    // metode som lagrer alt som ble opprettet i program til en ny txt fil
    public static void lagreData(){
        PrintWriter f = null;
        try {
          f = new PrintWriter("nyLegesystemData.txt");
        } catch (Exception e) {
          System.out.println("Kan ikke lage filen nyLegesystemData.txt.");
          System.exit(1);
        }

        // Skriver inn Pasient del
        f.println("# Pasienter (navn, fnr)");
        for(Pasient p : pasienterListe){
          f.println(p.hentNavn() + ", " + p.hentFod());
        }
        // Skriver inn Legemidel del
        f.println("# Legemidler (navn, type, pris, virkestoff [, styrke])");
        for(Legemiddel l : legemidlerListe){
          f.println(l.hentNavn() + ", " + l.minType() + ", " + l.hentPris() + ", " + l.hentVirkestoff() + ", " + l.hentStyrkeFil());
        }

        // Skriver inn Leger del
        f.println("# Leger (navn, kontrollid / 0 hvis vanlig lege)");
        for(Lege l : legeListe){
          f.println(l.hentNavn() + ", " + l.hentKontrollID());
        }

        // Skriver inn Resept del
        f.println("# Resepter (legemiddelNummer, legeNavn, persID, reit)");
        for(Resept r : reseptListe){
          f.println(r.hentLegemiddel().hentId() + ", " + r.hentLege().hentNavn() + ", " + r.hentPasient().hentId() + ", " + r.hentReit());
        }

        f.close();

        System.out.println("\nLegesystem DataBase var lagret i nyLegesystemData.txt fil");


    }


}
