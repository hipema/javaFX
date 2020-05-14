package ejercicios.gui;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class UtilidadesControlador {
  
  public static String seleccionarArchivoImagen (ActionEvent e, Button BotonArchivoImagen, GridPane inicio) {
    File file = null;
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Archivos de imagen", "*.png", "*.jpg"),
        new FileChooser.ExtensionFilter("jpg", "*.jpg"),
        new FileChooser.ExtensionFilter("png", "*.png"));
    if (e.getSource().equals(BotonArchivoImagen)) {
      file = fileChooser.showOpenDialog(inicio.getScene().getWindow());
    } else {
      TipoDatoIncorrecto("Error al leer el archivo");      
    }    
    return "file:"+file.getPath();
  }
  
  public static String seleccionarArchivoTxt (ActionEvent e, Button BotonArchivoTexto, GridPane inicio) {
    File file = null;
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Archivos de texto", "*.txt"));
    if (e.getSource().equals(BotonArchivoTexto)) {
      file = fileChooser.showOpenDialog(inicio.getScene().getWindow());
    } else {
      TipoDatoIncorrecto("Error al leer el archivo");      
    }    
    return "file:"+file.getPath();
  }
  
  public static String seleccionarArchivoTxtVBox (ActionEvent e, Button BotonArchivoTexto, VBox inicio) {
    File file = null;
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Archivos de texto", "*.txt"));
    if (e.getSource().equals(BotonArchivoTexto)) {
      file = fileChooser.showOpenDialog(inicio.getScene().getWindow());
    } else {
      TipoDatoIncorrecto("Error al leer el archivo");      
    }    
    return file.getPath();
  }
  
  public static String seleccionarCualquierArchivo (ActionEvent e, Button BotonArchivo, GridPane inicio) {
    File file = null;
    FileChooser fileChooser = new FileChooser();
    if (e.getSource().equals(BotonArchivo)) {
      file = fileChooser.showOpenDialog(inicio.getScene().getWindow());
    } else {
      TipoDatoIncorrecto("Error al leer el archivo");      
    }    
    return "file:"+file.getPath();
  }
  
  public static void TipoDatoIncorrecto(String mensaje) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setHeaderText(null);
    alert.setTitle("Error");
    alert.setContentText(mensaje);
    alert.showAndWait();
}
  
}
