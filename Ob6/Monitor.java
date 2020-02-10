import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.ArrayList;

class Monitor{
  private ArrayList<Melding> meldinger; // liste med alle meldinger i Monitor
  private Lock endringLaas;
  private Condition listeIkkeTom;

  public Monitor(){
    meldinger = new ArrayList<Melding>();
    endringLaas = new ReentrantLock();
    listeIkkeTom = endringLaas.newCondition();
  }

  public Melding hentM(){
    endringLaas.lock(); // laaser seg om Traade bruker den metode.
    try{
      while(meldinger.size() == 0 && Telegrafist.hentAntallAktive() > 0){ // hvis det er ingen meldinger i monitor saa skal traad vente
        // sjekker ogsaa om alle Telegrafistene er ferdig
        listeIkkeTom.await(); // venter for signal (signal kommer etter ny melding ble lagt inn)
      }
      if(meldinger.size() > 0){
        return meldinger.remove(0); // sletter den forste melding i liste og returnerer den
      }
    }catch(InterruptedException e){
    }finally{ // gjor den uansett om det ble feil eller nei
      endringLaas.unlock(); // Laaser seg opp etter traade ferdig med den metode. For at andre traade skal ha mulighet aa bruke den
    }
    return null;
  }

  // setter ny melding i sin meldinger array liste
  public void leggInnM(Melding melding){
    endringLaas.lock();
    try{
      meldinger.add(melding);
      listeIkkeTom.signalAll(); // signal til traaden som venter paa ny melding
    }finally{
      endringLaas.unlock();
    }
  }

  // returnerer ArrayList
  public ArrayList<Melding> hentMeldingerListe(){
    return meldinger;
  }


}
