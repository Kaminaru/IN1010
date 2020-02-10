class Lege{
  protected String legeNavn;

  public Lege(String navn){
   legeNavn = navn;
}

  public String hentNavn(){return legeNavn;} // returnerer legens navn

  // Skriver ut Resept objekt. Hvis objekt av type A saa kommer det feil melding.
  // Fordi det er bare spesialister som kan skrive ut resepter av A typen preparat.
  public Resept skrivResept(Legemiddel legemiddel, int pasientID, int reit) throws UlovligUtskrift{
    // hvis legemiddel er PreparatA
    if((legemiddel instanceof PreparatA)){
      throw new UlovligUtskrift(this, legemiddel);
    }else{ // ellers lages ny resept objekt og returneres
      Blue res = new Blue(legemiddel,this,pasientID,reit);
      return res;
    }
  }


  public String toString(){ // Skriver ut informasjon om Lege
    return "|Lege navn: "+legeNavn+"|";
  }

}
