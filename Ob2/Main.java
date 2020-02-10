import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

class Main{
  public static void main(String[] args) {
/*
    System.out.println("|--------------------|");
    System.out.println("|Mauelt skrevet DATA:|");
    System.out.println("|--------------------|");
    // Legemidler TEST

    PreparatA predizolTEST = new PreparatA("Predizol",450,75,8); // 0
    PreparatB paralginTEST = new PreparatB("Paralgin Forte",65,400,5); // 1
    PreparatC palaceboTEST = new PreparatC("Placebo Pianissimo",10,0); // 2
    PreparatC ibuxTEST = new PreparatC("Ibux",240,200); // 3
    // Leger TEST
    Lege coxTEST = new Lege("Dr. Cox");
    Lege wilsonTEST = new Lege("Dr. Wilson");
    Spesialist houseTEST = new Spesialist("Dr. House",12345);
    Lege hillestadTEST = new Lege("Dr. Hillestad Lovold");
    // Resepter TEST
    Blue zero0TEST = new Blue(paralginTEST,coxTEST,2,3);
    White one1TEST = new White(palaceboTEST,hillestadTEST,3,10000);
    PResepter two2TEST = new PResepter(predizolTEST,houseTEST,1);
    Militarresepter three3TEST = new Militarresepter(ibuxTEST,hillestadTEST,3,2);
*/






    System.out.println("\n\n|-------------|");
    System.out.println("|DATA fra fil:|");
    System.out.println("|-------------|\n");
    String filnavn = "inndata.txt";
    File file = new File(filnavn);
    Scanner scan = null;

    // Prover aa lese fil, om fil leses ikke saa skriver program feilmelding og avsluter jobb
    try {
      scan = new Scanner(file);
    } catch (Exception e) {
      System.out.print("Finner ikke fil: " + filnavn);
      System.exit(1);
    }

    // HashMap for legemidl objekter. Navn er id nummer til legemidel, verdi er Legemiddel objekt
    HashMap<Integer, Legemiddel> legemidlerListe = new HashMap<Integer, Legemiddel>();
    // HashMap for lege objekter. Navn er navn til lege, verdi er Lege objekt
    HashMap<String, Lege> legeListe = new HashMap<String, Lege>();
    // ArrayListe for alle resepter
    ArrayList<Resept> reseptListe = new ArrayList<Resept>();
    int valgtInfo = 0;  // 1 hvis Legemidler, 2 hvis Leger, 3 hvis Resepter (Jeg gjor det hvis rekefolge av data i fil skal endres)

    // Lager alle objekter av forskjelige klasser fra fil
    // Alt som skjer i while loppen, skjer med en og en linje. Dvs while loopen leser en og en linje, og jobber med den
    while(scan.hasNextLine()){
      String line = scan.nextLine(); // leser ny linje
      // Hvis linje har "Legemidler" String saa skal valgInfo endres i 1.
      // Saa vet program at den jobber med Legemidler naa
      // Samme med Leger og Resepter. Gjor det for at rekefolge av informasjon i fil skal ikke spille noen rolle
      if(line.contains("Legemidler")){valgtInfo = 1;}
      if(line.contains("Leger")){valgtInfo = 2;}
      if(line.contains("Resepter")){valgtInfo = 3;}

      Scanner lineScan = new Scanner(line); // lager scanner objekt av linje som jeg lest tidligere
      lineScan.useDelimiter(", "); // deler "scanned" linje med ", "

      if (!(line.contains("#"))){ // alt som er i den if settning kjores bare hvis linje har ikke "#" inne seg

        String[] delMidInfo = new String[5]; // Lager array med 5 plass, (det er maks vi faar fra linje lessing, saa vi trenger ikke mer)
        // For eksempel lege skal bruke bare 2 plass, men for eksemple noen legemidler skal bruke alle 5 plass i array liste.
        // ny kort sikt array liste lages for hver eneste lest linje
        int a = 0; //variabel a som oker med 1, brukes for a legge ting i riktig plass i array liste
        while(lineScan.hasNext()){ // Gaar gjennom hele linja
          delMidInfo[a] = lineScan.next(); // legger inn i array liste alle deler av linje
          a += 1;
        }

        if(valgtInfo == 1){ // Hvis "vi jobber med Legemidler"

          // Lager legemidel objekt
          // Lager Legemidel med bruk av informasjon fra den linje vi jobber med.
          // Integer.valueOf(...) brukes for aa endre String informasjon til integral.
          // Etter paa leger vi lagte Legemidler objekter i HashMap: legemidlerListe
          // Og paa slutt printes objekts informasjon ut
          if(delMidInfo[1].equals("a")){ // Hvis andre verdien i liste er "a"
            PreparatA preparatA = new PreparatA(delMidInfo[0], Integer.valueOf(delMidInfo[2]), Integer.valueOf(delMidInfo[3]), Integer.valueOf(delMidInfo[4]));
            legemidlerListe.put(preparatA.hentId(), preparatA);
            System.out.println("- Legemidel: " + preparatA);
          }else if(delMidInfo[1].contains("b")){
            PreparatB preparatB = new PreparatB(delMidInfo[0], Integer.valueOf(delMidInfo[2]), Integer.valueOf(delMidInfo[3]), Integer.valueOf(delMidInfo[4]));
            legemidlerListe.put(preparatB.hentId(), preparatB);
            System.out.println("- Legemidel: " + preparatB);
          }else if(delMidInfo[1].contains("c")){
            PreparatC preparatC = new PreparatC(delMidInfo[0], Integer.valueOf(delMidInfo[2]), Integer.valueOf(delMidInfo[3]));
            legemidlerListe.put(preparatC.hentId(), preparatC);
            System.out.println("- Legemidel: " + preparatC);
          }


        }else if(valgtInfo == 2){ // Hvis "vi jobber med Leger"

          // Lager Leger objekt
          // Lager Leger med bruk av informasjon fra den linje vi jobber med.
          // Integer.valueOf(...) brukes for aa endre String informasjon til integral.
          // Etter paa leger vi lagte Lege objekter i HashMap: legeListe
          // Og paa slutt printes objekts informasjon ut
          if(!(delMidInfo[1].contains("0"))){ // Hvis forste verdien i liste er "Spesialis"
            Spesialist spesialist = new Spesialist(delMidInfo[0], Integer.valueOf(delMidInfo[1]));
            legeListe.put(spesialist.hentNavn(), spesialist);
            System.out.println("- Spesialist: " + spesialist);
          }else{ // Hvis forste verdien i liste er "Lege"
            Lege lege = new Lege(delMidInfo[0]);
            legeListe.put(lege.hentNavn(), lege);
            System.out.println("- Lege: " + lege);
          }



        }else if(valgtInfo == 3){ // Hvis "vi jobber med Resepter"

          // Lager Resepter objekt
          // Lager Resept med bruk av informasjon fra den linje vi jobber med.
          // Integer.valueOf(...) brukes for aa endre String informasjon til integral.
          // Her jeg ogsa brukt HashMap av leger og HasMap av legemidler for aa helte referanse til lege og legemidel objekt
          // Etter paa leger vi lagte Resept objekter i ArrayListe, for aa mulig bruk i framtiden
          // Og paa slutt printes objekts informasjon ut
          if(delMidInfo[0].contains("blaa")){ // Hvis forste verdien i liste er "blaa"
            Blue bla = new Blue(legemidlerListe.get(Integer.valueOf(delMidInfo[1])), legeListe.get(delMidInfo[2]),
            Integer.valueOf(delMidInfo[3]), Integer.valueOf(delMidInfo[4]));

            reseptListe.add(bla);
            System.out.println("- Resept: " + bla);
          }else if(delMidInfo[0].contains("hvit")){ // Hvis forste verdien i liste er "hvit"
            White hvit = new White(legemidlerListe.get(Integer.valueOf(delMidInfo[1])), legeListe.get(delMidInfo[2]),
             Integer.valueOf(delMidInfo[3]), Integer.valueOf(delMidInfo[4]));

            reseptListe.add(hvit);
            System.out.println("- Resept: " + hvit);
          }else if(delMidInfo[0].contains("prevensjon")){
            PResepter prevensjon = new PResepter(legemidlerListe.get(Integer.valueOf(delMidInfo[1])),
            legeListe.get(delMidInfo[2]), Integer.valueOf(delMidInfo[3]));

            reseptListe.add(prevensjon);
            System.out.println("- Resept: " + prevensjon);
          }else if(delMidInfo[0].contains("militaer")){
            Militarresepter militaer = new Militarresepter(legemidlerListe.get(Integer.valueOf(delMidInfo[1])),
            legeListe.get(delMidInfo[2]), Integer.valueOf(delMidInfo[3]), Integer.valueOf(delMidInfo[4]));
            
            reseptListe.add(militaer);
            System.out.println("- Resept: " + militaer);
          }
        }
      }
    }

  }
}
