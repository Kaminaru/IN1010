class Blue extends Resept{
  public Blue(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
    super(legemiddel, utskrivendeLege, pasientId, reit);
  }

  public String farge(){  // metode som returnerer "hvit", naar metode kalles med hjelp av classe Blue
    return "blaa"; // returnerer string "blaa" hvis metode ble kalt av Blue klasse
  }

  public double prisAaBetale(double startPris){ // returnerer ny pris etter rabatt
    double pris = startPris*0.25; // rabatt med 75%
    return pris;
  }

  public String toString(){ // skriver informasjon om objekt av classe Blue
    return "|Legemiddel: "+legeM+"*|Resept ID: "+ownId+"|Lege: "+legeRef+"|Pasient ID: "+ pasientId+"|Reit: "+reit+"|";
}

}
