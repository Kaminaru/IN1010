import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

class Main{
  public static void main(String[] args) {

    Scanner myObj = new Scanner(System.in);
    int antallKryptografister = 0; int antallTelegrafister = 0;


    // Leser innput fra bruker
    while(antallKryptografister == 0 || antallTelegrafister == 0){
      try{
        System.out.println("\nSkriv antall telegrafister:");
        antallTelegrafister = Integer.parseInt(myObj.nextLine());
        System.out.println("\nSkriv antall kryptografer:");
        antallKryptografister = Integer.parseInt(myObj.nextLine());
      }catch(Exception e){
        System.out.println("Feil input");
      }
    }

    CountDownLatch latch = new CountDownLatch(antallKryptografister); //lager latch for kryptografer

    // lager operasjonsentral og kanal
    Operasjonssentral ops = new Operasjonssentral(antallTelegrafister); // oppretter en ny operasjonssentral
    Kanal[] kanaler = ops.hentKanalArray(); // henter ut kanalene

    // Lager monitor for krypter og dekryptert meldinger
    Monitor monKry = new Monitor();
    Monitor monDekry = new Monitor();


    // lager telegrafister og legger dem inn i traader
    System.out.println("\nTelegrafister og Kryptografer jobber!\n");
    for(int i = 0; i < antallTelegrafister; i++){
      Telegrafist telegrafist = new Telegrafist(kanaler[i], monKry, antallTelegrafister);
      Thread traaden = new Thread(telegrafist);
      traaden.start();
    }

    // Lager kryptografer og legger dem inn i traader
    try{
      for(int i = 0; i < antallKryptografister; i++){
        Kryptograf kryptograf = new Kryptograf(latch, monDekry, monKry);
        Thread traaden = new Thread(kryptograf);
        traaden.start();
      }
      latch.await(); // venter for alle kryptografer traader aa bli ferdig
    }catch(InterruptedException e){
      System.out.println("Feil med CountDownLatch for Kryptograf");
    }

    Operasjonsleder operLeder = new Operasjonsleder(monDekry.hentMeldingerListe());

  }


}
