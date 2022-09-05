public class Person {
  private Bil3 bilPerson;    // definerer variabel bilperson som har bil3 objekt typen
  public Person(Bil3 bil){
    // konstruktor for objekt
      bilPerson = bil;
  }
  public void skrivUt(){
    // metode som skriver ut bilnummeret til bilen som personen eier
    System.out.println(bilPerson.hentNummer());
  }
}
