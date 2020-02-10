public class UlovligUtskrift extends Exception{
  UlovligUtskrift(Lege l, Legemiddel lm){
    super("Lege: "+l.hentNavn()+ " har ikke lov til aa skrive ut: " + lm.hentNavn());
  }
}
