

class Telegrafist implements Runnable{ // runnable implementeres fordi den skal kjores i en traad
  private Kanal kanal; // egen kanal med krypterte meldinger
  private int sekvensNmr,kanalNmr;
  private Monitor monitorKry;
  public static int aktiveTelegrafister;

  // Konstrutkor som tar ett parameter for kanal og ett parameter for monitor
  public Telegrafist(Kanal kanal, Monitor monitorKry, int aktiveTelegrafister){
    this.kanal = kanal;
    this.monitorKry = monitorKry;
    kanalNmr = kanal.hentId();
    sekvensNmr = 0;
    this.aktiveTelegrafister = aktiveTelegrafister;
  }

  @Override
  public void run(){
    String krypMelding = kanal.lytt(); // faar krypter melding

    while(krypMelding != null){ // mens finnes ny melding
      Melding melding = new Melding(krypMelding, sekvensNmr, kanalNmr); // lager ny melding objekt
      sekvensNmr++; // %ker sekvensNmr for hver melding
      monitorKry.leggInnM(melding); // leger melding objekt med krypter melding i Monitor for krypterte meldinger
      krypMelding = kanal.lytt(); // lytter kanalen for ny melding
    }

    // naar det er ingen meldinger igjen

    Melding melding = new Melding("SLUTT", sekvensNmr, kanalNmr);
    sekvensNmr++;
    monitorKry.leggInnM(melding);
    System.out.println("Lytting av kanal: " + kanalNmr + " avsluttes");
    aktiveTelegrafister--;
  }

  // bruker den for aa sjekke om Telegrafister har noen aktive traader
  public static int hentAntallAktive(){
    return aktiveTelegrafister;
  }

}
