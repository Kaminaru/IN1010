import java.io.File;
import java.io.FileNotFoundException;

class Test{
  public static void main(String[] args) {
    try{
      File fil = new File("3.in");
      Labyrint lab = Labyrint.lesFraFil(fil);
      Liste<String> liste = lab.finnUtveiFra(5,3);
        for(String p : liste){

          System.out.println("\n" + p);
        }
      // System.out.println(lab);
      // int minst = Integer.MAX_VALUE;
      // String veien = "";
      // for(String p: liste){
      //   System.out.println("\n"+p);
      //   if(p.length() < minst){
      //     minst = p.length();
      //     veien = p;
      //   }
      // }
      // System.out.println("\nDet ble fant:" + liste.stoerrelse());
      // System.out.println("Korteste veien: ");
      System.out.println(lab);
    }catch(FileNotFoundException e){
      System.out.println(e);
    }
  }
}
