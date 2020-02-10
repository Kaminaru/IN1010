class Stabel<T> extends Lenkeliste<T>{

  // setter inn et element paa slutt av lenkedliste
  public void leggPaa(T x){
    leggTil(x);
  }

  // fjerner og returnerer siste element i lenkedliste
  public T taAv(){
    int fjernElPos = stoerrelse() - 1; // posisjon til siste element
    return fjern(fjernElPos);
  }
}
