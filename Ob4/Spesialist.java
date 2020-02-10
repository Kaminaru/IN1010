class Spesialist extends Lege implements Godkjenningsfritak{
  int konID;
  public Spesialist(String navn, int kontrollID){
    super(navn);
    konID = kontrollID;
  }

  @Override
  public String toString(){ // Skriver ut informasjon om LegeSpesialist
    return "|Lege navn: "+legeNavn+" |Kon.ID: "+konID+"|";
  }

  @Override
  public int hentKontrollID(){return konID;} // returnerer kontroll id

}
