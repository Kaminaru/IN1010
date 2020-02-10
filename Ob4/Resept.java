abstract class Resept{
  protected static int id; // static id som skal oke hvor hver ny objekt av resept
  protected Legemiddel legeM;// referanse til legemiddel
  protected Lege legeRef;  // referanse til lege som skrevet ut resepten
  protected int reit; // hvis antall reit er 0 saa resept er ugyldig
  protected int ownId;  // egen (ikke statisk) id for hver resept objekt
  protected Pasient pasient; // referanse til pasient

  public Resept(Legemiddel legemid, Lege lege, Pasient pasient, int reitet){
    ownId = id;
    legeM = legemid;
    legeRef = lege;
    this.pasient = pasient;
    reit = reitet;
    id +=1;
  }

  protected int hentId(){return ownId;} // returner id av reseptet
  protected Legemiddel hentLegemiddel(){return legeM;} // returner referanse til legemiddel
  protected Lege hentLege(){return legeRef;} // returner referanse til lege
  protected Pasient hentPasient(){return pasient;} // returnerer referanse til pasient
  protected int hentReit(){return reit;} // returnerer antall reit igjen

  public boolean bruk(){ // bruker resept 
    if(reit >= 1){
      reit --;
      return true;
    }
    return false;  // returnerer false om resepten er oppbrukt, og true hvis det er fortsatt noen reit
  }

  abstract public String farge();  // abstrakt metode
  abstract public double prisAaBetale(double startPris); // abstrakt metode

}
