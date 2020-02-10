class Blue extends Resept{
  public Blue(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
    super(legemiddel, utskrivendeLege, pasient, reit);
  }

  @Override
  public String farge(){  // metode som returnerer "hvit", naar metode kalles med hjelp av classe Blue
    return "blaa"; // returnerer string "blaa" hvis metode ble kalt av Blue klasse
  }

  @Override
  public double prisAaBetale(double startPris){ // returnerer ny pris etter rabatt
    double pris = startPris*0.25; // rabatt med 75%
    return pris;
  }

  @Override
  public String toString(){ // skriver informasjon om objekt av classe Blue
    return "|Legemiddel Navn/Type: "+legeM.hentNavn()+"/"+legeM.minType()+" |Resept ID: "+ownId+" |Lege: "+legeRef.hentNavn()+" |Pasient Navn: "+ pasient.hentNavn()+" |Reit: "+reit+"|";
}

}
