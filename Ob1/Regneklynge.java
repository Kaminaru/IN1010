import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
// Klasse for representasjon av regneklynge med racks og noder
class Regneklynge{
  private String filnavn;
  private ArrayList<Rack> listeRacks;
  private int noderPerRack;
  // Oppretter tom regneklynge for racks objekter
  // Leser data om regneklynge fra fil, bygger datastrukturen
  // Tar paramter filnavn, som er navn til fil med data for regneklynge
  public Regneklynge(String filename){
    filnavn = filename;
    listeRacks = new ArrayList<Rack>(); // Liste med alle racks objekter i regneklynge
    File file = new File(filnavn);
    Scanner scan = null;

    Rack newRack = new Rack();
    // Legger til ny og forst rack objekt
    listeRacks.add(newRack);

    // prover aa lese fil, og hvis det gaar ikke saa kommer det feil melding og program skal stoppe kjoring
    try {
    scan = new Scanner(file);
    } catch (Exception e) {
    System.out.println("Kan ikke lese " + filnavn + "!");
    System.exit(1);
    }

    noderPerRack = scan.nextInt(); // Tar antal noder per rack fra fil (den forste int paa forste linje)
    while(scan.hasNextLine()){  // loopen som gaar saa lenge det finnes linjer aa lese i filen
      int antallNoder = scan.nextInt(); // leser antall noder fra fil
      int minnePerNode = scan.nextInt(); // leser minne per node fra fil
      int antallProsPerNode = scan.nextInt(); // leser antall prosesorer per node fra fil
      for (int i=0;i<antallNoder;i++){ // loopen som lager saa mange noder som trenges
        Node newNode = new Node(minnePerNode,antallProsPerNode); // lager ny node objekt med minnepernode og antallprospernode som argument
        this.settInnNode(newNode); // kaller paa metode settInnNode med ny Node objekt som argument
      }
   } scan.close();
  }

  // Plasserer en node inn i et siste rack med ledig plass, eller
  // lager den ny rack objekt, og setter den i den om det er ikke plass i forige
  // Tar paramtere node som er referanse til node som skal settes inn i rack
  public void settInnNode(Node node){
     if (listeRacks.get(listeRacks.size()-1).getAntNoder() == noderPerRack){
       Rack newRack = new Rack();
       listeRacks.add(newRack);
       listeRacks.get(listeRacks.size()-1).settInn(node);
   } else if (listeRacks.get(listeRacks.size()-1).getAntNoder() < noderPerRack){
       listeRacks.get(listeRacks.size()-1).settInn(node);
     }
  }

  // Beregner og returnerer totalt antall prosessorer i hele regnelynge
  public int antProsessorer(){
    int antPro = 0; // antall prosessorer i helle regneklyngen
    for(int i=0;i<listeRacks.size();i++){ //loppen for alle racks i rackliste
      antPro += listeRacks.get(i).antProsessorer(); // legger til antall prosessorer i hver rack
    }
    return antPro;
  }

  // Beregner antall noder i regneklynge med minne over angitt grense
  // Tar parameter paakrevdMinne - hvor mye minne skal noder som telles med ha
  // Returnerer antall noder med tilstrekkellig minne
  public int noderMedNokMinne(int paakrevdMinne){
    int antallNod = 0; // antall noder i regnekylnge med nok minne
    for(int i=0;i<listeRacks.size();i++){ // loopen for alle racks i rackliste
      antallNod += listeRacks.get(i).noderMedNokMinne(paakrevdMinne);
    }
    return antallNod;
  }

  // Henter og returnerer antall racks i regneklynge
  public int antRacks(){
    return listeRacks.size();
  }

}
