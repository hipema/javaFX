package ejemplos;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Ej02HolaMundo extends Application {
  
  @Override
  public void start (Stage primaryStage) {
    primaryStage.setTitle("Mi primera aplicación JavaFX"); // título de la ventana
    
    // para visualizar contenido en la ventana de la aplicación.
    // hay que añadir un componente a un objeto escena, y este al objeto stage.
    Label label = new Label ("Hola Mundo, JavaFX!");
    Scene scene = new Scene(label, 400, 200);
    primaryStage.setScene(scene);
    
    primaryStage.show();
  }
  
  public static void main (String[] args) {
    launch(args);
  }
}
