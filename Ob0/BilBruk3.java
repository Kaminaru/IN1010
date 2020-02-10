class BilBruk3 {
  public static void main(String[] arg) {
    Bil3 bil = new Bil3("TS1236");  // lager objekt bil med nummer som argument
    Person person1 = new Person(bil); // Lager objekt person1 med bil objekt som argument

    person1.skrivUt(); // kaller paa metode SkrivUt fra person objekt
}
}
