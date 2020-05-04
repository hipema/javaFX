package ejercicios.gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

public class QuitaComentariosControlador2 implements Initializable {
  String archivoOrigen;
  
  @FXML
  private GridPane inicio;
  
  @FXML
  private Button buscar;
  
  @FXML
  private TextField ficheroDestino; // valor numérico que introduce el usuario
  
  @FXML
  private TextArea resultado;
  @FXML
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
  
  public void seleccionarArchivo () {
    FileChooser fileChooser = new FileChooser();
    File file = fileChooser.showOpenDialog(inicio.getScene().getWindow());
    archivoOrigen = file.getPath();
  }
  
  
  
  public void quitarComentarios () {
    // Proceso
    try {
      // Abrimos fichero origen
      BufferedReader bOrigen = new BufferedReader(new FileReader(archivoOrigen));
      // Abrimos fichero destino
      BufferedWriter bDestino = new BufferedWriter(new FileWriter(ficheroDestino.getText()));
      
      // necesaria para detectar bloques de comentarios /* ...*/
      boolean estoyEnBloqueComentario = false; 
      // Leo cada línea del fichero origen y si no es un comentario la escribo en el destino.
      String linea = bOrigen.readLine();
      while (linea != null) {
        // ¿Bloque /*....*/
        boolean escribeLinea = true;
        if (estoyEnBloqueComentario) {
          if (linea.trim().endsWith("*/")) {
            estoyEnBloqueComentario = false;
          }
        } else if (linea.trim().startsWith("/*")) {
          estoyEnBloqueComentario = true;
        } else {
          
          if (linea.contains("//")){ // ¿hay comentario en la línea?
            // quitar de línea el comentario //
            int dondeEmpiezaBarra2 = linea.indexOf("//");
            linea = linea.substring(0, dondeEmpiezaBarra2);
            // si la línea contiene solo espacios o está vacía, no la escribo.
            if (linea.trim().equals("")) {
              escribeLinea = false;
            }
          }
          // escribimos línea sin comentarios
          if (escribeLinea && !estoyEnBloqueComentario) {
            bDestino.write(linea);
            bDestino.newLine(); // añade salto de línea
          }  
        }
       
        // Leemos nueva línea
        linea = bOrigen.readLine();
        }
      resultado.setText("Archivo sin comentarios creado.");
      // Cerramos los archivos.
      bOrigen.close();
      bDestino.close();
      
    } catch (FileNotFoundException e){
      TipoDatoIncorrecto("No se encuentra el archivo.");
    } catch (IOException e) {
      TipoDatoIncorrecto("Error de entrada/salida al manejar el fichero");
    }
  }
  
  public void muestraSinComentarios () {
    // Proceso
    try {
      // Abrimos fichero origen
      BufferedReader bOrigen = new BufferedReader(new FileReader(archivoOrigen));
      
      // necesaria para detectar bloques de comentarios /* ...*/
      boolean estoyEnBloqueComentario = false; 
      // Leo cada línea del fichero origen y si no es un comentario la escribo en el destino.
      String linea = bOrigen.readLine();
      while (linea != null) {
        // ¿Bloque /*....*/
        boolean escribeLinea = true;
        if (estoyEnBloqueComentario) {
          if (linea.trim().endsWith("*/")) {
            estoyEnBloqueComentario = false;
          }
        } else if (linea.trim().startsWith("/*")) {
          estoyEnBloqueComentario = true;
        } else {
          
          if (linea.contains("//")){ // ¿hay comentario en la línea?
            // quitar de línea el comentario //
            int dondeEmpiezaBarra2 = linea.indexOf("//");
            linea = linea.substring(0, dondeEmpiezaBarra2);
            // si la línea contiene solo espacios o está vacía, no la escribo.
            if (linea.trim().equals("")) {
              escribeLinea = false;
            }
          }
          // escribimos línea sin comentarios
          if (escribeLinea && !estoyEnBloqueComentario) {
            resultado.setText(resultado.getText()+linea+"\n");
          }  
        }
       
        // Leemos nueva línea
        linea = bOrigen.readLine();
        }
      // Cerramos los archivos.
      bOrigen.close();
      
    } catch (FileNotFoundException e){
      TipoDatoIncorrecto("No se encuentra el archivo.");
    } catch (IOException e) {
      TipoDatoIncorrecto("Error de entrada/salida al manejar el fichero");
    }
  }
}
