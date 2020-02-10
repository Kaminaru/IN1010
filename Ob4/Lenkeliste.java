import java.util.NoSuchElementException;
import java.util.Iterator;

public class Lenkeliste<T> implements Liste<T>,Iterable<T>{
  private Node start; // variabel som skal holde Node som representerer start av lenkeliste
  private Node end;  // variabel som skal holde Node som representerer siste element i lenkeliste

  // lenke liste Node
  private class Node{
    Node neste = null; // variabel som pekker paa neste Node
    T data;  // data som er i gitt Node
    // Konstrukt%r for Node
    Node(T x){data = x;}
  }

  // Lenkeliste Iterator brukes for aa gaa gjennom lenked liste. Bruker den ogsaa for aa gaa gjennom liste med bruk av 'for each'
  private class LenkelisteIterator implements Iterator<T> {
    private int i = 0;
    private Lenkeliste<T> liste;

    public LenkelisteIterator(Lenkeliste<T> liste){
      this.liste = liste;
    }

    @Override
    public boolean hasNext(){ // return true om element har neste (om index er mindre err stoerrelse av liste)
      return i < liste.stoerrelse();
    }

    @Override
    public T next(){ // returnerer neste om det finnes neste i lenkedliste
      if(hasNext()){
        return liste.hent(i++);
      }
      throw new NoSuchElementException();
    }

    // metode brukes ikke i program
    @Override
    public void remove(){
      throw new UnsupportedOperationException();
    }
  }



  // metode som finner storelse for lenkedliste og returnerer den
  @Override
  public int stoerrelse(){
    Node p = start;
    int ant = 0;
    while (p !=null){ // saa lenge element er ikke null saa skal det adderes 1 til antall elementer i liste
      ant++;
      p = p.neste;
    }
    return ant;
  }


  // legger inn et nytt element i listen og skyver neste element ett hakk lenger bak
  @Override
  public void leggTil(int pos, T x) throws UgyldigListeIndeks{
    if(pos > 0 && pos < stoerrelse()){ // Det er for pos > 0. Fordi for posisjon 0 finnes ikke "forigePosisjon"
      Node nyNode = new Node(x);
      Node p = start;
      Node forigePos = null; // bruker det videre for at det blir lettere aa skyve elementer ett hakk lengre bak
      for (int i = 0; i < pos; i++){ // for loopen som gaar til bestemt posisjon
        forigePos = p;
        p = p.neste;
      }
      forigePos.neste = nyNode; // element som var f%r element som ble syvet. Og naa peker paa NyNode
      nyNode.neste = p; // NyNode tar element plass og peker paa den, og skyver den bak
      //  | forigePos ---neste--->  NyNode ---neste---> p |
    }else if(pos == 0){  // hvis valgt posisjon er 0
      Node nyNode = new Node(x);
      Node temp = start;
      nyNode.neste = temp; // ny node peker paa gammel start element
      start = nyNode; // ny node faar start verdi
      end = nyNode.neste; // end skal faa verdi av neste element etter nyNode
    }else if(pos == stoerrelse()){ // hvis for eksempel liste har 5 element (som har maks index 4) og vi
      // prover aa lege ny paa index 5, skal det bare bety at vi maa kale leggTil(T x) som setter element paa siste plass
      leggTil(x);
    }else{
      throw new UgyldigListeIndeks(pos); // om ingen av if eller else if fungere ikke skal det returnerers feil om at det er feil posisjon valg
    }
  }


  // setter inn et element paa slutten av listen
  @Override
  public void leggTil(T x){
    Node nyNode = new Node(x);
    if (start == null){  // Hvis lenkeliste er tomt fra start
      start = nyNode;
    }else if(start != null && end == null){ // Hvis det er bare 1 element i liste (start)
      end = nyNode; // siste naa er nyNode
      start.neste = end;
    }else{ // hvis det er mer enn 2 elementer i liste
      // Noen ganger noen skal slett siste element i liste. Saa end skal miste sin verdi.
      // Det er mulig aa lage ekstra if settning i fjern metode. Ellers kan jeg lage while loppen her, som lager ny end verdi
      Node p = start;
      while(p.neste !=null){
        p = p.neste;}
      p.neste = nyNode; // neste av siste = nyNode
      end = nyNode; // siste naa er nyNode
    }
  }

   //setter inn elementet paa en
  @Override
  public void sett(int pos, T x) throws UgyldigListeIndeks{
    // gitt posisjon og overskrive det som var der f%r
    if(stoerrelse() != 0){  // hvis liste er ikke tomt
      if(pos > 0 && pos < stoerrelse()){
        Node p = start;
        for (int i = 0; i < pos; i++){
          p = p.neste;
        }
        p.data = x;
      }else if (pos == 0){  // hvis index er 0
        start.data = x;
      }else{
        throw new UgyldigListeIndeks(pos);
      }
    }else{  // hvis liste er tomt saa returneres det feil
      throw new UgyldigListeIndeks(0);
    }
  }


  // henter ut et element paa oppgitt indeks
  @Override
  public T hent(int pos) throws UgyldigListeIndeks{
    if(pos > 0 && pos < stoerrelse()){
      Node p = start;
      for (int i = 0; i < pos; i++){
        p = p.neste;
      }
      return p.data;
    }else if (pos == 0){  // hvis posisjon er 0
      if(stoerrelse() != 0){ // sjekker om det er tomt liste
      return start.data;
      }
    }
    throw new UgyldigListeIndeks(pos);
  }


  // fjerner og returner element paa gitt posisjon
  @Override
  public T fjern(int pos) throws UgyldigListeIndeks{
    if(pos > 0 && pos < stoerrelse()){
      if(stoerrelse() > 1){ // for aa sjekke om liste har mer enn 1 element
        Node p = start;
        for (int i = 1; i < pos; i++){ // for loopen som kommer til bestemt posisjon i lenkedliste
          p = p.neste;
        }
        Node n = p.neste; // her
        p.neste = n.neste; // og her endrer jeg pekkere av neste for at gammel element skal ikke ha noen som pekker paa den
        return n.data;
      }else{ // hvis lenkedliste har 0 eller 1 element
        Node n = start;
        start = null;
        return n.data;
      }
    }else if(pos == 0){ // hvis posisjon er lik 0
      if(stoerrelse() != 0){ // sjekker om det er tomt liste
        Node temp = start;
        start = start.neste;
        return temp.data;
      }
    }
    throw new UgyldigListeIndeks(pos);
  }

  // fjerener forste element og returnerer den
  @Override
  public T fjern() throws UgyldigListeIndeks{
    if(stoerrelse() != 0){
      Node returnode = start;
      start = start.neste; // hode lik neste node.
      return returnode.data; // returnerer data som ble slettet
    } // hvis finnes ikke noen elementer i lister fra f%r saa kommer unntak
    throw new UgyldigListeIndeks(0);
  }


  // Skriver ut hele lenkedliste
  public void skrivAlle(){
    if(start == null){
      System.out.println("Lenke liste er tomt");
    }else{
      for(T r: this){
        System.out.println(r);
      }
    }
  }

  // returnerer nytt lenkelisteiterator-objekt
  public Iterator<T> iterator(){
    return new LenkelisteIterator(this);
  }


}
