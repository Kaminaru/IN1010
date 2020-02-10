class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T>{
// compareTo / Returns a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object


  // setter inn elementer i sortert rekkef%lge (fra mins til st%rst)
  @Override
  public void leggTil(T x){
    if(stoerrelse() == 0){ // hvis lenkedliste er tomt
      super.leggTil(x); // legger ny elementi lenkedliste;
    }else if (stoerrelse() == 1){ // if det er bare 1 element i lenkedliste
      if(hent(0).compareTo(x) < 0){ // x er storre enn forste element i lenkedliste hvis compare blir mindre enn 0
        super.leggTil(x); // legger element paa slutten av liste
      }else{ // hvis ny element er mindre. Eller hvis compareTo returnerer 0,1
        super.leggTil(0,x); // setter element paa start av liste
      }
    }else{ // for mer enn 2 objekter i lenekdliste
      for(int i=0; i < stoerrelse(); i++){ // for loopen som gaar gjennom hele lenkedliste
        if(hent(i).compareTo(x) > 0){ // hvis data paa git posisjon av index i er storre enn ny data
          super.leggTil(i,x); // legger ny element pa i posisjon og dytter storre verdi lengre bak
          return; // stopper for loopen etter element ble lagt inn. Fordi det er ikke vits aa gaa videre i for loopen.
          // Fordi vi %nsker ikke legge flere samme elementer
        }
      }
      super.leggTil(x); // om ingenting ble lagt inn betyr det at ny verdi er storst og skal legges inn paa slutt av lenkedliste
    }
  }


  @Override
  public T fjern(){ // st%rste elementet skal tas ut
    int fjernElPos = stoerrelse() - 1; // posisjon til siste element
    return fjern(fjernElPos);
  }


  @Override
  public void sett(int pos, T x) throws UnsupportedOperationException{
    throw new UnsupportedOperationException();
  }
  @Override
  public void leggTil(int pos, T x) throws UnsupportedOperationException{
    throw new UnsupportedOperationException();
  }
}
