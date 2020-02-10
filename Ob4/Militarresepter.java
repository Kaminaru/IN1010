class Militarresepter extends White{
  public Militarresepter(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
    super(legemiddel, utskrivendeLege, pasient, reit);
  }

  @Override
  public double prisAaBetale(double startPris){ // skriver ut pris for betaling
    return 0;   // Militarrespeter koster alltid 0
  }

}
