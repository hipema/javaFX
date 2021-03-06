package ejercicios.gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Controlador-vista para los eventos de Mezcla Archivos.
 * 
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

public class MezclaArchivosControlador2 implements Initializable {
  String archivoOrigen1;
  String archivoOrigen2;
  
  @FXML
  private Button archivo1, archivo2;
  
  @FXML
  private TextField destino; // valor numérico que introduce el usuario
  
  @FXML
  private TextArea resultado;
  
  @FXML
  private GridPane inicio;
  
  private void TipoDatoIncorrecto(String mensaje) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText(null);
      alert.setTitle("Error");
      alert.setContentText(mensaje);
      alert.showAndWait();
  }
  
  private String seleccionarArchivo () {
    String resultado;
    FileChooser fileChooser = new FileChooser();
    File file = fileChooser.showOpenDialog(inicio.getScene().getWindow());
    resultado = file.getPath();
    return resultado;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
  }
  
  public void archivoOrigen1 () {
    archivoOrigen1 = seleccionarArchivo();
  }
  
  public void archivoOrigen2 () {
    archivoOrigen2 = seleccionarArchivo();
  }
  
  public void combinar () {
    try {
      BufferedReader lectura1 = new BufferedReader(new FileReader(archivoOrigen1));
      BufferedReader lectura2 = new BufferedReader(new FileReader(archivoOrigen2));
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
