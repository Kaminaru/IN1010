class PreparatB extends Legemiddel{
  int vanedan = 0; // sier hvor vanedannende det er
  public PreparatB(String navn, double pris, double virkestoff, int styrke){
    super(navn, pris, virkestoff);
    vanedan = styrke;
  }

  @Override
  protected int hentVanedannendeStyrke(){return vanedan;} // returnerer vanedannende Styrke

  @Override
  public String toString(){ // Skriver ut informasjon om PreparatB objekt
    return ownId+ ": |Navn: "+legemNavn+"|Preparat ID: "+ownId+"|Pris: "+legemPris+"|Virkestoff: "+legemVirkestoff+"|Styrke: "+vanedan+"|";
  }

  @Override
  public String minType(){ // for aa ikke bruke instanceof i noen steder
    return "b";
  }
  
  @Override
  public int hentStyrkeFil(){ // bruker i database lagring
    return vanedan;
  }
}
