class EgenTest{
  public static void main(String[] args) {
    // try{
    // Lenkeliste<String> liste = new Lenkeliste<String>();
    // liste.leggTil("Element 0");
    // liste.leggTil("Element 1");
    // liste.leggTil("Element 2");
    // liste.leggTil("Element 3");
    // liste.leggTil("Element 4");
    // System.out.println(liste.stoerrelse());
    // liste.sett(0, "nyVerdi 0");
    // liste.sett(2, "nyVerdi 2");
    //   liste.skrivAlle();
    // liste.fjern(3);
    // liste.fjern(3);
    // liste.leggTil("NyttElement");
    // liste.skrivAlle();
    // }catch(UgyldigListeIndeks e){
    //   System.out.println(e);
    // }

    // andre test
    SortertLenkeliste<String> liste1 = new SortertLenkeliste<String>();
    liste1.leggTil("Dr. Cox");
      liste1.skrivAlle();
    liste1.leggTil("Dr. Wilson");
      liste1.skrivAlle();
    liste1.leggTil("Dr. House");
      liste1.skrivAlle();
    liste1.leggTil("Dr. Hillestad Lovold");
      liste1.skrivAlle();

  }
}
