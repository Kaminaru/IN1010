import java.util.concurrent.CountDownLatch;

class Kryptograf implements Runnable{
  private Monitor monitorDekry, monitorKry;
  public CountDownLatch latch;

  public Kryptograf(CountDownLatch latch, Monitor monitorDekry, Monitor monitorKry){
    this.monitorDekry = monitorDekry;
    this.monitorKry = monitorKry;
    this.latch = latch;
  }


  @Override
  public void run(){
      String krypMelding, dekryptertMld;

      Melding melding = monitorKry.hentM(); // henter kryptert melding objekt
      while(melding != null){ // hvis det fortsatt finnes Kanaler som er ikke ferdig
        if(melding.tekst().equals("SLUTT")){ // *Hvis det er siste kryptert melding i kanalen
          System.out.println("Loading...");
        }else{
          krypMelding = melding.tekst(); // kaller paa tekst() metode for Melding objekt
          dekryptertMld = Kryptografi.dekrypter(krypMelding); // dekripterer melding
          melding.endreTekst(dekryptertMld); // endrer tekst i "kryptert melding" Objekt til dekryptert
          monitorDekry.leggInnM(melding); // legger inn dekryptertmelding objekt i monitor for dekryptert meldinger
        }
        melding = monitorKry.hentM();
      }
      latch.countDown();
  }


}
