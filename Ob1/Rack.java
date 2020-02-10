import java.util.ArrayList;
class Rack{
  private ArrayList<Node> noder;
  // Konstruktor av klasse rack i en regneklynge
  // Oppretter et rack der det senerer kan plasseres noder objekter
  public Rack(){
    noder = new ArrayList<Node>();
  }
  // Plasserer en ny node inn i racket.
  // Bruker parameter node (som er node objekt)
  public void settInn(Node node){
    noder.add(node);
  }
  // returnerer antall noder i racket
  public int getAntNoder(){
    return noder.size();
  }
  // Beregner og returnerer sammenlagt antall prosessorer i nodene i et rack
  public int antProsessorer(){
    int antallPros = 0; // antall prosessorer i den rack
    for(int i=0;i<noder.size();i++){ // loopen med lengde av ArrayListe
      antallPros += noder.get(i).antProsessorer(); // antall Prosessorer i hvernode legges til antallPros variabel
    }
    return antallPros;
  }
  // Beregner antall noder i racket med minne over gitt grense
  // Tar parameter paakrevdMinne (antall minne som kreves)
  // Returnerer antall noder med tilstrekkelig minne
  public int noderMedNokMinne(int paakrevdMinne){
    int antallNod = 0;
    for(int i=0;i<noder.size();i++){
      if (noder.get(i).nokMinne(paakrevdMinne)){antallNod += 1;}
    }
    return antallNod;
  }
}
