package ejercicios.gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Controlador-vista para los eventos de CajeroCambio.
 * 
 */

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class BuscaPatronControlador implements Initializable {
  String archivoOrigen;
  double tamLetra;
  String fuenteLetra;
  
  @FXML
  private TextField patron;
  
  @FXML
  private TextArea texto, resultado;
  
  @FXML
  private Button cargar;
  
  @FXML
  private VBox inicio;
  
  
  @FXML
  private ComboBox<String> fuentes;
  
  @FXML
  private Spinner<Integer> spinner;
    
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    fuentes.getItems().addAll("Arial", "Comic Sans MS", "Impact", "Verdana", "Gill Sans");
    tamLetra = resultado.getFont().getSize();
    fuenteLetra = resultado.getFont().getName(); 
    spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 150, (int) tamLetra));
  }
  
  public void seleccionarArchivo (ActionEvent e) {
    archivoOrigen = UtilidadesControlador.seleccionarArchivoTxtVBox(e, cargar, inicio);
    mostrarTexto();
  }
  
  public void mostrarTexto () {
    try {
      // Abrimos fichero origen
      BufferedReader bOrigen = new BufferedReader(new FileReader(archivoOrigen));
      String linea = bOrigen.readLine();
      while (linea != null) {
        texto.setText(texto.getText()+linea+"\n");
        linea = bOrigen.readLine();  
      }
      
      // Cerramos el fichero.
      bOrigen.close();
      
    } catch (FileNotFoundException e){
      UtilidadesControlador.TipoDatoIncorrecto("No se encuentra el archivo.");
    } catch (IOException e) {
      UtilidadesControlador.TipoDatoIncorrecto("Error de entrada/salida al manejar el fichero");
    }
  }
  
  public void buscar(ActionEvent e) {
    if (patron.getText().compareTo("")== 0) {
      resultado.setText("Debes introducir un patrón de búsqueda");
    } else {
      Pattern patronABuscar = Pattern.compile("("+patron.getText()+")\\b");
      Matcher matcher = patronABuscar.matcher(texto.getText());
      resultado.setText("");
      while (matcher.find()) {
        resultado.setText(resultado.getText()+ matcher.group(1)+"\n");
      }
    }
  } 
  
  public void cambiarFuente (ActionEvent e) {
    if (fuentes.getValue().toString().compareTo("Arial")==0) {
      fuenteLetra = "Arial";
    } else if (fuentes.getValue().toString().compareTo("Comic Sans MS")==0) {
      fuenteLetra = "Comic Sans MS";
    } else if (fuentes.getValue().toString().compareTo("Impact")==0) {
      fuenteLetra = "Impact";
    } else if (fuentes.getValue().toString().compareTo("Verdana")==0) {
      fuenteLetra = "Verdana";
    } else if (fuentes.getValue().toString().compareTo("Gill Sans")==0) {
      fuenteLetra = "Gill Sans";
    } else {
      fuenteLetra = fuentes.getValue().toString();
    }
    resultado.setFont(new Font(fuenteLetra, tamLetra));
    texto.setFont(new Font(fuenteLetra, tamLetra));    
    //fuentes.getItems().addAll("Arial", "Comic Sans MS", "Impact", "Verdana", "Gill Sans");
  }
  public void cambiarTamano (MouseEvent e) {
    tamLetra = spinner.getValue();
    resultado.setFont(new Font(fuenteLetra, tamLetra));
  }
  
  
}
