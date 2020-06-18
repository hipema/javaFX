package ejercicios.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ContarPalabrasControlador implements Initializable {
  
  @FXML
  private TextArea texto;  
  
  @FXML
  private Label resultado;

  public void contarPalabras (ActionEvent e) {
    String cadena = texto.getText();
    cadena = cadena.trim();
    int contadorPalabras = 0;

    // Si nos dan la cadena vacía terminamos la ejecución con un código de error
    if (cadena.isEmpty()) {
      texto.setText("La cadena no puede estar vacía");
    }

    // Desde el primer caracter distinto de espacio
    for (int posicion = 0; posicion<=cadena.length()-1; posicion++) {
      if (cadena.charAt(posicion)==' ' || cadena.charAt(posicion)=='\n') {
        contadorPalabras++;
        // No tengo en cuanta los posibles espacios que haya entre las palabras
        while ((posicion<=cadena.length()-1 && cadena.charAt(posicion)==' ') ||(posicion<=cadena.length()-1 && cadena.charAt(posicion)=='\n')) {
          posicion++;
        }
      }
    }
    // Si no acaba en espacios tengo que contar la última palabra (no hace falta aquí)
    // if (!cadena.endsWith(" ")) {
    //   contadorPalabras++;
    // }
    contadorPalabras++;

    resultado.setText("La cadena tiene "+contadorPalabras+" palabras.");
  }
   
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    
  }
}