class White extends Resept{
  public White(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
    super(legemiddel, utskrivendeLege, pasient, reit); // sender 4 argumenter videre i super class konstruktor
  }

  @Override
  public String farge(){ // metode som returnerer "hvit", naar metode kalles med hjelp av classe White
    return "hvit";
  }

  @Override
  public double prisAaBetale(double startPris){ // returnerer pris for betaling
    return startPris; // Hvite Resepter har ikke neon rabatt
  }

  @Override
  public String toString(){  // skriver ut informasjon om objekt av classe White
    return "|Legemiddel Navn/Type: "+legeM.hentNavn()+"/"+legeM.minType()+" |Resept ID: "+ownId+" |Lege: "+legeRef.hentNavn()+" |Pasient Navn: "+ pasient.hentNavn()+" |Reit: "+reit+"|";
}

}
