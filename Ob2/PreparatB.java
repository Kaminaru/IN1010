class PreparatB extends Legemiddel{
  int vanedan = 0; // sier hvor vanedannende det er
  public PreparatB(String navn, double pris, double virkestoff, int styrke){
    super(navn, pris, virkestoff);
    vanedan = styrke;
  }

  protected int hentVanedannendeStyrke(){return vanedan;} // returnerer vanedannende Styrke

  public String toString(){ // Skriver ut informasjon om PreparatB objekt
    return "|Navn: "+legemNavn+"|Preparat ID: "+ownId+"|Pris: "+legemPris+"|Virkestoff: "+legemVirkestoff+"|Styrke: "+vanedan+"|";
  }
}
