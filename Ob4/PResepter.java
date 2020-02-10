class PResepter extends White{
  public PResepter(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient){
    // utskrives alltid med 3 reit
    super(legemiddel, utskrivendeLege, pasient, 3);
  }
  
  @Override
  public double prisAaBetale(double startPris){
    double pris = startPris - 108;
    if(pris<0){return 0;}
    return pris;
  }

}
