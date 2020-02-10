abstract class Resept{
  protected static int id; // static id som skal oke hvor hver ny objekt av resept
  protected Legemiddel legeM;// referanse til legemiddel
  protected Lege legeRef;  // referanse til lege som skrevet ut resepten
  protected int pasientId, reit; // pasient id som eier resepten // hvis antall reit er 0 saa resept er ugyldig
  protected int ownId;  // egen (ikke statisk) id for hver resept objekt

  public Resept(Legemiddel legemid, Lege lege, int pasient, int reitet){
    ownId = id;
    legeM = legemid;
    legeRef = lege;
    pasientId = pasient;
    reit = reitet;
    id +=1;
  }

  protected int hentId(){return ownId;} // returner id av reseptet
  protected Legemiddel hentLegemiddel(){return legeM;} // returner referanse til legemiddel
  protected Lege hentLege(){return legeRef;} // returner referanse til lege
  protected int hentPasientId(){return pasientId;} // returnerer id av pasientet som refereres resept
  protected int hentReit(){return reit;} // returnerer antall reit igjen

  public boolean bruk(){
    if(reit > 1){
      reit --;
      return true;
    }
    return false;  // returnerer false om resepten er oppbrukt, og true hvis det er fortsatt noen reit
  }

  abstract public String farge();  // abstrakt metode
  abstract public double prisAaBetale(double startPris); // abstrakt metode

}
