package ejercicios.gui;

/**
 * Controlador-vista para los eventos de CajeroCambio.
 * 
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class BuscaPatronControlador implements Initializable {
  
  @FXML
  private TextField importeTotal; // valor numérico que introduce el usuario
  
  @FXML
  private TextField unCentimo, dosCentimos, cincoCentimos, diezCentimos, veinteCentimos,
  cincuentaCentimos, unEuro, dosEuros, cincoEuros, diezEuros, veinteEuros, cincuentaEuros,
  cienEuros, doscientosEuros, quinientosEuros; 
  
  private void TipoDatoIncorrecto(String mensaje) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText(null);
      alert.setTitle("Error");
      alert.setContentText(mensaje);
      alert.showAndWait();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
  }
  
  public void calcular () {
    try {
      int resultado;
      int valores[] = {50000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5, 2, 1};
      TextField resultados[] = {quinientosEuros, doscientosEuros, cienEuros, cincuentaEuros, veinteEuros,
                                diezEuros, cincoEuros, dosEuros, unEuro, cincuentaCentimos, veinteCentimos,
                                diezCentimos, cincoCentimos, dosCentimos, unCentimo};
      
      for (int i = 0; i< resultados.length; i++) {
          resultados[i].setText("");
      }

      double importe = Double.parseDouble(importeTotal.getText())*100;
      for (int i = 0; i< valores.length; i++) {
        if (importe >= valores[i]) {
          resultado = (int)(importe/valores[i]);
          importe = importe - (resultado)* valores[i];
          resultados[i].setText(Integer.toString(resultado));
        }
      }
      
    } catch (Exception e) {
      TipoDatoIncorrecto("Error, el tipo de dato debe ser numérico\n"
                       + "Separación números decimales con punto.");
    }
  }
}
