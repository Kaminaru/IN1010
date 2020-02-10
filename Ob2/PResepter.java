class PResepter extends White{
  public PResepter(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId){
    // utskrives alltid med 3 reit
    super(legemiddel, utskrivendeLege, pasientId, 3);
  }

  public double prisAaBetale(double startPris){
    double pris = startPris - 108;
    if(pris<0){return 0;}
    return pris;
  }

}
