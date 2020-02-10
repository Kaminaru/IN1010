class PreparatA extends Legemiddel{
  int narkStyrke = 0;  // Sier hvor sterk narkotisk det er
  public PreparatA(String navn, double pris, double virkestoff, int styrke){
    super(navn, pris, virkestoff); // sender tre argumenter i super konstruktor
    narkStyrke = styrke;
  }

  @Override
  protected int hentNarkotiskStyrke(){return narkStyrke;}  // returnerer narkotisk styrke

  @Override
  public String toString(){ // Skriver ut informasjon om PreparatA objekt
    return ownId+ ": |Navn: "+legemNavn+"|Preparat ID: "+ownId+"|Pris: "+legemPris+"|Virkestoff: "+legemVirkestoff+"|Styrke: "+narkStyrke+"|";
  }

  @Override
  public String minType(){ // for aa ikke bruke instanceof i noen steder
    return "a";
  }

  @Override
  public int hentStyrkeFil(){ // returnerer styrke av preparat (brukes for data lagring)
    return narkStyrke;
  }

}
