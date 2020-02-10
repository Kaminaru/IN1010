class Node{
  private int minne;
  private int antPros;
  // Konstruktor som tar minne til prosessor og antall prosessorer som parameter
  public Node(int minne1, int antPros1){
    minne = minne1;
    antPros = antPros1;
  }
  // Henter og returnerer antall prosessorer i node
  public int antProsessorer(){
    return antPros;
  }
  // Sjekker om det er nok minne for et program
  // Tar parameter paakrevdMinne som er minne som kreves for programmet
  // true for nok minne, og false for ikke nok minne
  public boolean nokMinne(int paakrevdMinne){
    return (paakrevdMinne <= minne); // returnerer false eller true
  }


}
