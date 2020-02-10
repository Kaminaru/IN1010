class PreparatA extends Legemiddel{
  int narkStyrke = 0;  // Sier hvor sterk narkotisk det er
  public PreparatA(String navn, double pris, double virkestoff, int styrke){
    super(navn, pris, virkestoff); // sender tre argumenter i super konstruktor
    narkStyrke = styrke;
  }

  protected int hentNarkotiskStyrke(){return narkStyrke;}  // returnerer narkotisk styrke

  public String toString(){ // Skriver ut informasjon om PreparatA objekt
    return "|Navn: "+legemNavn+"|Preparat ID: "+ownId+"|Pris: "+legemPris+"|Virkestoff: "+legemVirkestoff+"|Styrke: "+narkStyrke+"|";
  }
}
