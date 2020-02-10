class TestResepter{
  public static void main(String[] args) {
  PreparatA prepA = new PreparatA("Aa",10.2,5,1);
  PreparatB prepB = new PreparatB("Bb",10.2,5,1);
  Lege lege1 = new Lege("Dr. Cox");
  Spesialist legeSpes = new Spesialist("Dr. Hello",123);
  White hvit1 = new White(prepA,lege1,10,10);
  Blue blaa1 = new Blue(prepA,lege1,10,10);
  PResepter hvit12 = new PResepter(prepA,lege1,12);
  System.out.println(hvit1.farge()); // Skriver ut farge av resept
  System.out.println(blaa1.farge());
  System.out.println("Pris aa betale etter rabatt: " + blaa1.prisAaBetale(123240)); // beregner bestemt prosent av pris som ble sendt inn i metode
  System.out.println("Pris aa betale etter rabatt: " + hvit1.prisAaBetale(123240));
  System.out.println(hvit1);
  System.out.println(blaa1);
  System.out.println(hvit12);
  // Maa ikke fungere
  try{
    lege1.skrivResept(prepA,1,10);
    System.out.println("Alt gikk greit!!");
  } catch (UlovligUtskrift e){
    System.out.println(e);
  }

  // SKal fungere
  try{
    legeSpes.skrivResept(prepA,1,10);
    System.out.println("Alt gikk greit!! " + legeSpes.hentNavn() + " har skrevet resept!");
  } catch (UlovligUtskrift e){
    System.out.println(e);
  }


  System.out.println("-----------------------------------------------------------------");
  System.out.println(hvit1.hentReit());
  System.out.println("------------------------");
  System.out.println(hvit1.bruk()); // returnerer false om resepten er oppbrukt


  }

}
