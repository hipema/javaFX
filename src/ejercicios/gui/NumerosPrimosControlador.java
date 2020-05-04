package ejercicios.gui;

/**
 * Controlador-vista para los eventos de NúmerosPrimos.
 * 
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NumerosPrimosControlador implements Initializable {
  
  @FXML
  private TextField numeros;
  
  @FXML
  private TextArea textoModificar;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
  }
  
  
  public void mostrarPrimos () {
    try {
      int numPrimos = Integer.parseInt(numeros.getText());
      int contar =1;
      int esPrimo;
      int divisor;
      int num;
      String almacenado = "Resultado número 1: el 2  es número primo.\n";
  
      contar = 1;
      num = 3;
     
      while (contar < numPrimos){
        esPrimo = 1;
        divisor=3;
        while ((divisor<=Math.sqrt((num))) && esPrimo == 1) {
          if (num%divisor==0) {
            esPrimo = 0;
          }
          divisor +=2;
        }
        if (esPrimo == 1){
          contar +=1;
          almacenado += "Resultado número " + contar + ": el " + num + " es número primo.\n";
        }
        num +=2;
      }
      textoModificar.setText(almacenado);
    } catch (Exception e) {
      textoModificar.setText("Debes introducir un número");
    }
  }
}
