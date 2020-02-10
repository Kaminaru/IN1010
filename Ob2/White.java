class White extends Resept{
  public White(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
    super(legemiddel, utskrivendeLege, pasientId, reit); // sender 4 argumenter videre i super class konstruktor
  }

  public String farge(){ // metode som returnerer "hvit", naar metode kalles med hjelp av classe White
    return "hvit";
  }

  public double prisAaBetale(double startPris){ // returnerer pris for betaling
    return startPris; // Hvite Resepter har ikke neon rabatt
  }

  public String toString(){  // skriver ut informasjon om objekt av classe White
    return "|Legemiddel: "+legeM+"*|Resept ID: "+ownId+"|Lege: "+legeRef+"|Pasient ID: "+ pasientId+"|Reit: "+reit+"|";
    // Printer ut informasjon om legeM forst
}

}
