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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class FraccionesControlador implements Initializable {
  
  @FXML
  private TextField fraccion1Num, fraccion1Den, fraccion2Num, fraccion2Den, resNum, resDen;
  
  @FXML
  private RadioButton suma, resta, producto, division;
  
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
  
  public void calcularResultado () {
    try {
      FraccionClase f1 = new FraccionClase (Integer.parseInt(fraccion1Num.getText()), Integer.parseInt(fraccion1Den.getText()));
      FraccionClase f2 = new FraccionClase (Integer.parseInt(fraccion2Num.getText()), Integer.parseInt(fraccion2Den.getText()));
      
      if (suma.isSelected()) {
        f1.sumarFracciones(f2);
      } else if (resta.isSelected()) {
        f1.restarFracciones(f2);  
      } else if (producto.isSelected()) {
        f1.multiplicaFracciones(f2);
      } else if (division.isSelected()) {
        f1.dividirFracciones(f2);
      }
      resNum.setText(Integer.toString(f1.getNumerador()));
      resDen.setText(Integer.toString(f1.getDenominador()));
      
    } catch (Exception e) {
      TipoDatoIncorrecto("Error, numeradores y denomiadores deben ser números enteros.");
    }
  }
}
