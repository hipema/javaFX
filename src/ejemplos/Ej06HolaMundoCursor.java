package ejemplos;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Ej06HolaMundoCursor extends Application {
  
  @Override
  public void start (Stage primaryStage) {
    
    // Creamos etiqueta y la posicionamos dentro de su espacio.
    Label label = new Label ("Hola Mundo, JavaFX!");
    label.setFont(new Font("Arial", 50));
    label.setAlignment(Pos.CENTER);
    
    // Creamos escenario e introducimos en ella la etiqueta.
    Scene scene = new Scene(label);
    
    // damos especificaciones a la escena y mostramos a pantalla completa.
    primaryStage.setScene(scene);
    primaryStage.setFullScreen(true);
    primaryStage.setTitle("Saludos");
    
    primaryStage.show();
  }
  
  public static void main (String[] args) {
    launch(args);
  }
}
