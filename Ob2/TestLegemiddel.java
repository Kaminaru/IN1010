class TestLegemiddel {
  public static void main(String[] args) {
    PreparatA prepA = new PreparatA("Aa",10.2,5,1);
    PreparatB prepB = new PreparatB("Bb",11.2,4,1);
    PreparatC prepC = new PreparatC("Cc",12.2,3);
    System.out.println(prepA); // skal kalle paa toString metode
    System.out.println(prepC);
    System.out.println(prepB);
    System.out.println("--------------------------------------------------------------------");
    System.out.println("Pris etter endring:");
    System.out.println("--------------------------------------------------------------------");
    prepC.settNyPris(134.2);  // endrer pris
    prepA.settNyPris(1213.56);
    System.out.println(prepA);
    System.out.println(prepC);
    System.out.println(prepB);
    System.out.println("--------------------------------------------------------------------");
    System.out.println("Vanedannende Styrke: " + prepB.hentVanedannendeStyrke());
    System.out.println("Narkotisk Styrke: " + prepA.hentNarkotiskStyrke());
  }

}
