class PreparatC extends Legemiddel{
  public PreparatC(String navn, double pris, double virkestoff){
    super(navn, pris, virkestoff);
  }

  public String toString(){ // Skriver ut informasjon om PreparatC objekt
    return "|Navn: "+legemNavn+"|Preparat ID: "+ownId+"|Pris: "+legemPris+"|Virkestoff: "+legemVirkestoff+"|";
  }
}
