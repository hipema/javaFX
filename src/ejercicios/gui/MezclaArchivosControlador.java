package ejercicios.gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Controlador-vista para los eventos de NúmerosPrimos.
 * 
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MezclaArchivosControlador implements Initializable {
  
  @FXML
  private TextField archivo1, archivo2, destino; // valor numérico que introduce el usuario
  
  @FXML
  private TextArea resultado;

  
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
  
  public void combinar () {
    try {
      BufferedReader lectura1 = new BufferedReader(new FileReader(archivo1.getText()));
      BufferedReader lectura2 = new BufferedReader(new FileReader(archivo2.getText()));
      BufferedWriter mezcla = new BufferedWriter(new FileWriter(destino.getText()));
      
      // Leemos y mostramos la primera línea por pantalla.
      String linea1 = lectura1.readLine();
      String linea2 = lectura2.readLine();
      
      while (linea1 != null || linea2 != null) {
        if (linea1 != null) {
          mezcla.write(linea1);
          mezcla.newLine();
          resultado.setText(resultado.getText()+linea1+"\n");
          linea1 = lectura1.readLine();
          
        }
        if (linea2 != null) {
          mezcla.write(linea2);
          mezcla.newLine();
          resultado.setText(resultado.getText()+linea2+"\n");
          linea2 = lectura2.readLine();
        }
      }
      // Cerramos ficheros
      lectura1.close();
      lectura2.close();
      mezcla.close();
      
    } catch (FileNotFoundException error){
      TipoDatoIncorrecto("No se encuentra el archivo.");
    } catch (IOException error) {
      TipoDatoIncorrecto("Error de entrada/salida al manejar el fichero");
    }
  }
}
