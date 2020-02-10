import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Labyrint{
  private static Rute[][] ruteListe; // 2D liste tar vare paa antall rader og kolonner
  private static int kolonner; // fra 1 til ->
  private static int rader; // fra 1 til ->
  private static Liste<String> utveien; // liste med alle String objekter som beskriver utveier

  // konstruktor som skal ikke kunne kalles utenfra klassen
  private Labyrint(Rute[][] ruteListe, int kolonner, int rader){
    this.ruteListe = ruteListe;
    this.kolonner = kolonner;
    this.rader = rader;
  }

  public static int hentKol(){return kolonner;}
  public static int hentRad(){return rader;}
  public static Rute[][] hentRuteListe(){return ruteListe;}

  public static Labyrint lesFraFil(File fil){
        Scanner scanner = null;
        try{
          scanner = new Scanner(fil);
        }catch(FileNotFoundException e){
          System.out.println("Kan ikke finne fil");
        }

        String linjeEn = scanner.nextLine();
        String[] infoOmLab = linjeEn.split(" "); // leser forste linje
        rader = Integer.parseInt(infoOmLab[0]);
        kolonner = Integer.parseInt(infoOmLab[1]);

        Rute[][] board = new Rute[rader][kolonner]; // lager 2d arrayliste

        int rad = 0;
        while(scanner.hasNextLine()){
          int kol = 0; // kolloner i gitt rad
          Rute[] raden = new Rute[kolonner]; // tomt array med lengde av antall kolonner

          String innlest = scanner.nextLine(); // linje med sorte og hvite tegner
          String[] deler = innlest.split("");
          for(String c : deler){
            Rute rute = null;
            if(c.equals("#")){ // hvis tegn lik # saa lages sortRute
              rute = new SortRute(kol,rad,board);
            }else if(c.equals(".")){ // hvis tegn lik . saa lages det hvit rute eller aapening
              if(rad == 0 || rad == rader-1 || kol == 0 || kol == kolonner-1){ // sjekker om det er aapening
                rute = new Aapning(kol,rad,board);
              }else{
                rute = new HvitRute(kol,rad,board);
              }
            }else{
              System.out.println("Feil format av fil");
              System.exit(1);
            }
            raden[kol] = rute; // legger inn rute objekter i bestept kolone posisjon i rad
            kol++;
          }
          board[rad] = raden; // legger inn rad inn i 2 d liste
          rad ++;
        }

        // finner nabo
        // sjekker for nabo og sender naboer for hver rute i sin settInnNabo metode
        for(int i = 0; i < rader; i++){
          for(int j = 0; j < kolonner; j++){
            Rute ost = null; Rute vest = null; Rute syd = null; Rute nord = null;
            Rute valgtRute = null;
            valgtRute = board[i][j]; // ***** kan ta bort
            // NORD
            if(!(i == 0)){ // hvis det er ikke overst rad saa har den noen fra nord side
              nord = board[i-1][j];
            }
            // SYD
            if(!(i == rader-1)){
              syd = board[i+1][j];
            }
            // VEST
            if(!(j == 0)){
              vest = board[i][j-1];
            }
            // OST
            if(!(j == kolonner-1)){
              ost = board[i][j+1];
            }
            valgtRute.settInnNabo(nord, ost, syd, vest);
          }
        }
        return new Labyrint(board,kolonner,rader);
  }


  // finner utveien fra en rute pa plass (kol, rad)
  public Liste<String> finnUtveiFra(int kol, int rad){
    Rute start = ruteListe[rad][kol];
    utveien = start.finnUtvei(); // utveien er liste. FinnUtvei returnerer liste med String objekter

    return utveien;
  }

  // skriver ut labyrint tegning, nar noen prover aa skrive ut Labyrint
  public String toString(){
    String tekst = "";
    for(Rute[] rad : ruteListe){
      for(Rute e : rad){
        tekst += " " + e.tilTegn(); // mellom rom for at alt skal se ut bedre og mer forstaaelig for mennesker
      }
      tekst += "\n";
    }
    return tekst;
  }


}
