package ejemplos;

import javafx.application.Application;
import javafx.stage.Stage;

public class Ej01HolaMundo extends Application {
  
  @Override
  public void start (Stage primaryStage) {
    primaryStage.setTitle("Mi primera aplicación JavaFX");
    primaryStage.show();
  }
  
  public static void main (String[] args) {
    launch(args);
  }
}
