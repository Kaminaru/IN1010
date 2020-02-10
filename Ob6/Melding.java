class Melding implements Comparable<Melding>{ // comparable for aa sortere meldinger etter paa
  private int sekvensNmr,kanalNmr;
  private String tekst;

  public Melding(String tekst, int sekvensNmr, int kanalNmr){
    this.tekst = tekst;
    this.sekvensNmr = sekvensNmr;
    this.kanalNmr = kanalNmr;
  }

  public int hentSekvensNmr(){ return sekvensNmr; }
  public int hentkanalNmr(){ return kanalNmr; }
  public String tekst(){ return tekst; }
  public void endreTekst(String tekst){this.tekst = tekst;}

  // Brukt den metode for aa sortere Meldinger i Operasjonsleder classe
  @Override
  public int compareTo(Melding melding){
    if(kanalNmr < melding.hentkanalNmr()){ // hvis den melding classe kanNmr er < enn kanal nummer av melding som er parameter
      return -1;
    }else if(kanalNmr > melding.hentkanalNmr()){
      return 1;
    }else if(sekvensNmr < melding.hentSekvensNmr()){
      return -1;
    }else if(sekvensNmr > melding.hentSekvensNmr()){
      return 1;
    }
    return 0;
  }

  @Override
  public String toString(){
    return tekst;
  }
}
