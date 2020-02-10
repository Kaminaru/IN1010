class Lege implements Comparable<Lege>{
  protected String legeNavn;
  protected Lenkeliste<Resept> utskrevedeResepter; // lenkedliste med alle resepter som leger utskreved

  public Lege(String navn){
   legeNavn = navn;
   utskrevedeResepter = new Lenkeliste<Resept>();
 }

  public Lenkeliste<Resept> hentReseptListe(){return utskrevedeResepter;} // returnerer lenkedliste med resepter
  public int hentKontrollID(){return 0;} // ikke spesialist lege har alltid kontrollid 0 (eller ikke noen)
  public String hentNavn(){return legeNavn;} // returnerer legens navn

// Maa pr%ve aa forkorte de her
  public Resept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
     if(legemiddel.minType().equals("a") && this.hentKontrollID() == 0){ // bruker den for aa ikke bruker instanceOf. Men kunne bli lettere aa bare bruke instanceOf
       throw new UlovligUtskrift(this, legemiddel);
     }else{
       White res = new White(legemiddel,this,pasient,reit);
       utskrevedeResepter.leggTil(res); // legger inn resept i utskrevedeReseoter liste for leger
       pasient.leggResept(res); // legger inn respet for pasient resept liste
       return res;
     }
  }

  public Resept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
    if(legemiddel.minType().equals("a") && this.hentKontrollID() == 0){ // bruker den for aa ikke bruker instanceOf. Men kunne bli lettere aa bare bruke instanceOf
      throw new UlovligUtskrift(this, legemiddel);
    }else{
      Blue res = new Blue(legemiddel,this,pasient,reit);
      utskrevedeResepter.leggTil(res); // legger inn resept i utskrevedeReseoter liste for leger
      pasient.leggResept(res); // legger inn respet for pasient resept liste
      return res;
    }
 }

  public Resept skrivMillitarResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
    if(legemiddel.minType().equals("a") && this.hentKontrollID() == 0){ // bruker den for aa ikke bruker instanceOf. Men kunne bli lettere aa bare bruke instanceOf
      throw new UlovligUtskrift(this, legemiddel);
    }else{
      Militarresepter res = new Militarresepter(legemiddel,this,pasient,reit);
      utskrevedeResepter.leggTil(res); // legger inn resept i utskrevedeReseoter liste for leger
      pasient.leggResept(res); // legger inn respet for pasient resept liste
      return res;
    }
 }

  public Resept skrivPResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift{
    if(legemiddel.minType().equals("a") && this.hentKontrollID() == 0){ // bruker den for aa ikke bruker instanceOf. Men kunne bli lettere aa bare bruke instanceOf
      throw new UlovligUtskrift(this, legemiddel);
    }else{
      PResepter res = new PResepter(legemiddel,this,pasient);
      utskrevedeResepter.leggTil(res); // legger inn resept i utskrevedeReseoter liste for leger
      pasient.leggResept(res); // legger inn respet for pasient resept liste
      return res;
    }
 }

  @Override
  public String toString(){ // Skriver ut informasjon om Lege
    return "|Lege navn: "+legeNavn+"|";
  }

  // bruker den i Lenkedliste compareTo metoder naar comparer 2 objekter av lege
  @Override
  public int compareTo(Lege lege){
    return this.legeNavn.compareTo(lege.hentNavn());
  }

}
