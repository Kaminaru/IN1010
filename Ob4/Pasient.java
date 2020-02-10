class Pasient{
  protected String navn,fodNmr; // navn og fodselsnummer til pasient
  protected static int id; // static id som skal oke hvor hver ny objekt av resept
  protected int ownId;  // egen (ikke statisk) id for hver resept objekt
  protected Stabel<Resept> pasReseptListe; // liste med resepter som pasient har

  // egen (ikke statisk) id for hver resept objekt
  public Pasient(String navn, String fodNmr){
    this.navn = navn;
    this.fodNmr = fodNmr;
    ownId = id;
    id +=1;
    pasReseptListe = new Stabel<Resept>();
  }

  // legger resept inn i stabel liste
  public void leggResept(Resept resept){
    pasReseptListe.leggPaa(resept);
  }

  // skrever ut hele resept liste som pasient har
  public void hentHeleRes(){
    pasReseptListe.skrivAlle();
  }

  public int hentId(){return ownId;} // returnerer id
  public String hentNavn(){return navn;} // returnerer navn
  public Stabel<Resept> hentReseptListe(){return pasReseptListe;} // returnerer reseptliste
  public String hentFod(){return fodNmr;} // returnerer fodselsnummer

  @Override
  public String toString(){ // Skriver ut informasjon om Pasient
    return ownId + ": |Pasient: "+navn+ " |Fods.: "+fodNmr+"|";
  }

}
