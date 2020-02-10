class PreparatC extends Legemiddel{
  public PreparatC(String navn, double pris, double virkestoff){
    super(navn, pris, virkestoff);
  }

  @Override
  public String toString(){ // Skriver ut informasjon om PreparatC objekt
    return ownId+ ": |Navn: "+legemNavn+"|Preparat ID: "+ownId+"|Pris: "+legemPris+"|Virkestoff: "+legemVirkestoff+"|";
  }

  @Override
  public String minType(){ // for aa ikke bruke instanceof i noen steder
    return "c";
  }

  @Override
  public int hentStyrkeFil(){ // returnerer Styrke. Den legemmiddel har ikke noen styrke saa bare returneres 0 (trenges for skriving i fil)
    return 0;
  }
}
