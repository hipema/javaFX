package ejemplos;

import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Ej03Ventanas extends Application {
  public static void main (String[] args) {
    launch(args);
  }
  @Override
  public void start (Stage primaryStage) {
    primaryStage.setTitle("JavaFX App");
    
    Stage stage = new Stage();
    // stage.initOwner(primaryStage);
    // stage.initModality(Modality.WINDOW_MODAL);// bloquea la ventana principal (dueña de este escenario)    
    // stage.initModality(Modality.NONE); // permitiría cerrar las ventanas
    stage.initModality(Modality.APPLICATION_MODAL); // bloquea todas las ventanas salvo esta.
    
    stage.setTitle("Modo ventana: "+ stage.getModality());
    
    primaryStage.show();
    System.out.println("Hemos lanzado el 'Stage' primario (pero no cerrado)");
    
    stage.showAndWait();  // esto hace que se muestre y quede esperando antes de pasar a las
                          // siguientes instrucciones.
    System.out.println("Hemos cerrado el 'Stage' Secundario.");
    
  }
  
}