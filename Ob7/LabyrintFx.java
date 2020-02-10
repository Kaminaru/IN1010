import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import java.io.File;
import java.util.Scanner;
import javafx.scene.paint.Color;

// GJore VBox

public class LabyrintFx extends Application {
  private static Labyrint labyrint; // referanse til labyrint objekt
  private static Rute[][] ruteList; // 2D liste tar vare paa antall rows og columns
  private static int columns; // fra 1 til ->
  private static int rows; // fra 1 til ->
  private static Liste<String> exits; // liste med alle String objekter som beskriver utveier
  private static boolean[][] losning; // boolean 2D liste
  private static Text statusinfo, statusinfo1; // tekster med info

  private static Pane kulisser = new Pane();
  private static GridPane rutenett = new GridPane();
  private static boolean fantUtveier = false; // for "neste losning" knapp
  private static String shortExit; // korteste utvei
  FinnVei findWay = new FinnVei(); // lager ny objekt av classe FinnVei for button action


  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {

  HBox hpaneButtons = new HBox(); // Horisontal box som skal holde 3 knapper og en statusinfo tekst
  statusinfo = new Text(""); // stats tekst for utveier
  statusinfo1 = new Text("Velg en Labyrint fil som du onsker finne losninger for:");
  statusinfo1.setFont(new Font(16));
	statusinfo1.setFont(new Font(20));
  statusinfo1.setX(0);  statusinfo1.setY(20);

  Button startButton = new Button("Labyrint valg");
  Button nextExit = new Button("Neste utvei");
  Button shortestExit = new Button("Korteste utvei");

  hpaneButtons.getChildren().addAll(startButton, nextExit, shortestExit, statusinfo);
  hpaneButtons.setTranslateX(0); hpaneButtons.setTranslateY(25);


  // Knapp Action (Velger fil, lages referanse til labyrint og vi faar arrayliste med ruter)
  startButton.setOnAction((e) ->{

  File file = new FileChooser().showOpenDialog(stage);
  labyrint= Labyrint.lesFraFil(file);
  columns = labyrint.hentKol();
  rows = labyrint.hentRad();
  ruteList = labyrint.hentRuteListe();


  reset(); // change colors of the labyrinth to standart

  rutenett.getChildren().clear(); // to clear old rutenett from gridplane
  // rutenett.setPrefSize(500,500);
  rutenett.setMinWidth(1000); rutenett.setMaxWidth(1000);
  rutenett.setMinHeight(900); rutenett.setMaxHeight(900);
  rutenett.setGridLinesVisible(true);
  rutenett.setLayoutX(40);  rutenett.setLayoutY(70);

  for(int x = 0; x < rows; x++){
    for(int y = 0; y < columns; y++){
      rutenett.add(ruteList[x][y], y, x); // legger inn Rute i bestemtposisjon i rutenett
    }
  }
  kulisser.getChildren().add(rutenett);

  // slutt av startButton action
  });


  // Knapp Action tegner neste utgang
  nextExit.setOnAction((e) ->{
    if(fantUtveier == true && exits.stoerrelse()>0){
      reset();
      String veien = exits.fjern(); // deletes first element in lenkedlist and returns it
      statusinfo.setText("Utveier igjen: " + exits.stoerrelse());
      losning = losningStringTilTabell(veien,columns,rows); // 2D boolean
      for(int x = 0; x < rows; x++){
        for(int y = 0; y < columns; y++ ){
          if(losning[x][y]){ // if true
            ruteList[x][y].setStyle("-fx-background-color: #9FAEEB");
            final Rute rute = ruteList[x][y];
            ruteList[x][y].setOnMouseExited((g) -> rute.setStyle("-fx-background-color: #9FAEEB"));
          }
        }
      }
    }else{
      statusinfo.setText("Ingen flere utveier");
    }

  // slutt av nextExit button action
  });



  // Knapp Action tegner den korteste vei
  shortestExit.setOnAction((e) ->{
    if(fantUtveier == true){
      reset();
      statusinfo.setText("Den korteste utvei:");
      losning = losningStringTilTabell(shortExit,columns,rows);
      for(int x = 0; x < rows; x++){
        for(int y = 0; y < columns; y++ ){
          if(losning[x][y]){ // if true
            ruteList[x][y].setStyle("-fx-background-color: #9FAEEB");
            // so color won't be changed to white when mouse exit node
            final Rute rute = ruteList[x][y];
            ruteList[x][y].setOnMouseExited((g) -> rute.setStyle("-fx-background-color: #9FAEEB"));
          }
        }
      }
    }
    else{
      statusinfo.setText("Det er ingen utveier");
    }

  // slutt av shortestExit button action
  });



  kulisser.getChildren().addAll(statusinfo1,hpaneButtons);
  //kulisser.getChildren().add(rutenett);

  Scene scene = new Scene(kulisser);
  stage.setMinHeight(1040);
  stage.setMinWidth(1200);
  stage.setTitle("LabyrintFx");
  stage.setScene(scene);
  stage.show();

}


  // naar man trykker paa rute knapp saa skal den kjores
  class FinnVei implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent e) {
      reset(); // endrer farger til ruter til hvit or sort

      // finner kortest veien forste gang man trykker paa knapp
      shortExit = ""; // korteste utveien
      exits =  labyrint.finnUtveiFra(((Rute)e.getSource()).kolPos(),((Rute)e.getSource()).radPos());
      if (exits.stoerrelse() != 0) {
        int minst = Integer.MAX_VALUE;
        for (String s : exits) {
          if(s.length() < minst){
            minst = s.length();
            shortExit = s;
          }
        }
      }


      if(exits.stoerrelse() > 0){ // hvis det finnes utveier
        fantUtveier = true; // bruker den for at program skal vite om bruker har valgt start posisjon
        String veien = exits.fjern(); // fjerner og returnerer forste utvei
        statusinfo.setText("Utveier igjen: " + exits.stoerrelse());

        losning = losningStringTilTabell(veien,columns,rows); // 2D boolean
        // endrer fargene til alle rutene som pa vei til utveien
        for(int x = 0; x < rows; x++){
          for(int y = 0; y < columns; y++ ){
            if(losning[x][y]){ // if true
              ruteList[x][y].setStyle("-fx-background-color: #9FAEEB");

              final Rute rute = ruteList[x][y];
              ruteList[x][y].setOnMouseExited((g) -> rute.setStyle("-fx-background-color: #9FAEEB"));
              // so color won't be changed to white when mouse exit node ^^^^
            }
          }
        }
      }else{
        statusinfo.setText("Ingen utveier fra punkt " + (Rute)e.getSource());
      }

    }
  }

  // endrer alle fargene for labyrint ruter til hvit or sort og legger inn action for mouseentered og mouseexited for hvite ruter
  public void reset(){
    for(int r = 0; r < rows; r++){
      for(int k = 0; k < columns; k++){
        if(ruteList[r][k].tilTegn() == '#'){
          ruteList[r][k].setStyle("-fx-background-color: #000000");
        }else{
          ruteList[r][k].setOnAction(findWay);
          ruteList[r][k].setStyle("-fx-background-color: #FFFFFF");
          final Rute rute = ruteList[r][k];
          ruteList[r][k].setOnMouseEntered((g) -> rute.setStyle("-fx-background-color: #9FAEEB"));
          ruteList[r][k].setOnMouseExited((g) -> rute.setStyle("-fx-background-color: #FFFFFF"));
        }
      }
    }
  }

// Konverterer losning-String fra oblig 5 til en boolean[][]-representasjon
// av losningstien.
  static boolean[][] losningStringTilTabell(String losningString, int bredde, int hoyde) {
    losning = new boolean[hoyde][bredde];
    java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\(([0-9]+),([0-9]+)\\)");
    java.util.regex.Matcher m = p.matcher(losningString.replaceAll("\\s",""));
    while (m.find()) {
      int x = Integer.parseInt(m.group(1));
      int y = Integer.parseInt(m.group(2));
      losning[y][x] = true;
    }
    return losning;
  }





}
