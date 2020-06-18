package gestisimalFX.utilidades;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class ControladorEntrada {
  
  public static int leerEntero(String lectura) {
    do{
      try {
        return Integer.parseInt(lectura);
      } catch (Exception e){
        TipoDatoIncorrecto("Introduce un número entero");
      }
    } while (true);
  }
  
  public static void TipoDatoIncorrecto(String mensaje) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setHeaderText(null);
    alert.setTitle("Error");
    alert.setContentText(mensaje);
    alert.showAndWait();
}
  
}
