import javafx.scene.control.Button;

abstract class Rute extends Button{
  protected int radPos, kolonnePos, a;
  protected Rute[] naboListe; // liste med alle nabo Ruter
  protected Rute nord, ost, syd, vest; // nabo Ruter til bestemt Rute objekt
  protected static Liste<String> listeUtveier; // liste med alle utveier
  protected boolean besokt = false; // bruker for aa sjekke om rute ble besokt for
  protected Rute[][] board; // for aa lage animasjon

  public Rute(int k, int r, Rute[][] board){
    radPos = r;
    kolonnePos = k;
    this.board = board; //for animasjon
    // setPrefSize(30,5);
    setMaxWidth(12); setMinWidth(12);
    setMinHeight(12); setMaxHeight(12);
  }

  public abstract char tilTegn();
  public int radPos(){return radPos;}
  public int kolPos(){return kolonnePos;}
  // setter inn nabo og legger dem i naboliste
  public void settInnNabo(Rute nord, Rute ost, Rute syd, Rute vest){
        this.nord = nord;
        this.ost = ost;
        this.syd = syd;
        this.vest = vest;
        naboListe = new Rute[]{nord, ost, syd, vest};
    }

  // Metode som sjekker for alle naboer og sporrer dem om hvilken naboer de har, og har de appning nar dem eller nei
  public void gaa(String veien){
    // Animasjon del. Fungerer, men det er bare en punkt som beveger seg gjennom hele labyrinten.
    // *!!!!!!!!!!!!!!!!!!!!!!!* Maa endre etter paa til linje med lengde som skal oke opp til aapning
    //
    // String tekst = "";
    // for(Rute[] rad : board){
    //   for(Rute e : rad){
    //     if(e == this){
    //       tekst += " X"; // mellom rom for at alt skal se ut bedre og mer forstaaelig for mennesker
    //     }else{
    //       tekst += " " + e.tilTegn(); // mellom rom for at alt skal se ut bedre og mer forstaaelig for mennesker
    //     }
    //   }
    //     tekst += "\n";
    // }
    // try{
    //   Thread.sleep(500);
    // }catch(InterruptedException e){
    //   System.out.println(e);
    // }
    //     System.out.println(tekst);
    //


    for(Rute nabo : naboListe){ // gaar gjennom naboListe
      if(this.erAppning()){ // hvis den Rute er aapning saa legger den seg i string, legger string med riktig veien i liste og avsluter sin gaa metode.
        veien += this.toString();
        listeUtveier.leggTil(veien);
        return;
      }
      if(this.erHvit() && nabo.erHvit() && nabo.besokt == false){ // sjekker om start posisjon er hvis, nabo er hvit, og at nabo var ikke besokt for
        // her brukt jeg det fordi naar gaa finner feil vei saa "teleporterer" den bak til riktig posisjon, men den skriver
        // seg selv en gang til, som gjor at det kommer 2 samme punkter etter hverandre i String
        if(a == 0){
          veien += this.toString() + " --> ";
          a++;
        }
        besokt = true;
        nabo.gaa(veien); // kaller paa nabo metode gaa()
      }
    }
    besokt = false; // reseter om for each loopen er ferdig
    a = 0; // reseter om for each loopen er ferdig
  }

  // finner alle utveier fra ruten ved hjelp av kall paa gaa
  public Liste<String> finnUtvei(){
    listeUtveier = new Lenkeliste<String>(); // liste med losninger
    String veien = "";
    this.gaa(veien);
    return listeUtveier;
  }

  @Override
  public String toString(){
    return "(" + kolonnePos + ", " + radPos + ")";
  }

  //bruker den for aa sjekke om rute er aapning
  public boolean erAppning(){
    return false;
  }

  //bruker den for aa sjekke om rute er hvit
  public boolean erHvit(){
    return false;
  }



}
