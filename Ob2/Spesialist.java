class Spesialist extends Lege implements Godkjenningsfritak{
  int konID;
  public Spesialist(String navn, int kontrollID){
    super(navn);
    konID = kontrollID;
  }
  public String toString(){ // Skriver ut informasjon om LegeSpesialist
    return "|Lege navn: "+legeNavn+"|KontrollID: "+konID+"|";
  }
  //
  @Override
  public int hentKontrollID(){return konID;} // returnerer kontroll id

  // skriver ut resept, fordi spesialist kan skrive hvilken som helst resept
  public Resept skrivResept(Legemiddel legemiddel, int pasientID, int reit) throws UlovligUtskrift{
      Blue res = new Blue(legemiddel,this,pasientID,reit);
      return res;
    }


}
