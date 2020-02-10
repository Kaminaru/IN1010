public class Bil2 {
  private String bilnum;
  public Bil2(String n){
    // konstruktor for objekt
      bilnum = n;
  }
  public void skrivUt(){
    System.out.println("Jeg er en bil");  // skriver ut noen
    System.out.println("Bilnummer: " + bilnum); // skriver ut noen og bilnummer
  }
}
