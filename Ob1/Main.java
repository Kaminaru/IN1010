class Main{
  public static void main(String[] args) {
    Regneklynge abel = new Regneklynge("regneklynge2.txt"); // lager ny Regneklynge objekt med filnavn som paramter.
    // Printer 3 ganger antallNoder som har nok minne med ruk av noderMedNokMinne metode
    System.out.println("Noder med minst 32GB: " + abel.noderMedNokMinne(32));
    System.out.println("Noder med minst 64GB: " + abel.noderMedNokMinne(64));
    System.out.println("Noder med minst 128GB: " + abel.noderMedNokMinne(128));
    // Skriver ut antall prosessorer i helle regneklynge
    System.out.println("Antall prosessorer: " + abel.antProsessorer());
    // Skriver ut antall rack i regneklynge (abel) objekter
    System.out.println("Antall rack: " + abel.antRacks());
  }
}
