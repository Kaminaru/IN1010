class Militarresepter extends White{
  public Militarresepter(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
    super(legemiddel, utskrivendeLege, pasientId, reit);
  }

  public double prisAaBetale(double startPris){ // skriver ut pris for betaling
    return 0;   // Militarrespeter koster alltid 0
  }

}
