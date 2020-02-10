import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class Operasjonsleder{
  private ArrayList<Melding> meldinger;

  public Operasjonsleder(ArrayList<Melding> meldinger){
    this.meldinger = meldinger;
    meldingSortering(); // sorterer
    try{
      skrivIFil(); // skriver i fil
    }catch(IOException e){}
  }

  // Sorterer alle meldinger med bruk av compareTo metode i meldinger
  public void meldingSortering(){
    Collections.sort(meldinger); // sorterer arraylist meldinger
  }

  public void skrivIFil() throws IOException{
    System.out.println("\nSkriver i fil...");
    int bestemtKanal = 0;
    BufferedWriter writer = null;
    for(int i = 0; i < meldinger.size(); i++){
      // kan gjøre det, fordi meldinger liste allerede sortert. Saa naa trenger vi bare dele den i forskjelige filer
      if(meldinger.get(i).hentkanalNmr() != bestemtKanal){
        bestemtKanal ++;

        if(writer != null){ // for aa bli sikkert at filen er ikke aapent
          writer.close();
        }

        try{
          writer = new BufferedWriter(new FileWriter("Kanal-" + bestemtKanal + ".txt"));
        } catch (IOException e) {
          System.out.println("Kan ikke lage filen: Kanal-" + bestemtKanal + ".txt");
          System.exit(1);
        }
      }
      writer.write(meldinger.get(i).tekst() + "\n\n");
    }
    writer.close();
    System.out.println("\nSkriving i fil er ferdig!");
    }

}
