package ejercicios.gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Controlador-vista para los eventos de CajeroCambio.
 * 
 */

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class DesencriptaControlador implements Initializable {
  String archivoOrigen;
  String archivoDestino;
  int indice;
  
  @FXML
  private VBox inicio;
  
  @FXML
  private Button bcargar, bdesencripta, bguardar, bmas, bmenos;
  
  @FXML
  private TextArea textoOriginal, textoDesencriptado;
  
  @FXML
  private TextField clave;
  
  @FXML
  private Slider slider;
    
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    clave.setText(Integer.toString(slider.valueProperty().getValue().intValue()));    
  }
  
  public void utilizarSlide (MouseEvent e) {
    slider.valueProperty().addListener((observable, oldValue, newValue) ->  {
      clave.setText(Integer.toString(observable.getValue().intValue()));  // or slider.getValue()
    });  
  }
  
  public void modificarSlide (ActionEvent e) {
    if (e.getSource().equals(bmas)) {
      slider.valueProperty().setValue(slider.valueProperty().getValue().intValue()+1);
      clave.setText(Integer.toString(slider.valueProperty().getValue().intValue()));
    } else if (e.getSource().equals(bmenos)) {
      slider.valueProperty().setValue(slider.valueProperty().getValue().intValue()-1);
      clave.setText(Integer.toString(slider.valueProperty().getValue().intValue()));
    }
  }
  
  
  public void seleccionarArchivo (ActionEvent e) {
    archivoOrigen = UtilidadesControlador.seleccionarArchivoTxtVBox(e, bcargar, inicio);
    mostrarTexto(archivoOrigen);
  }
  
  public void guardarArchivo (ActionEvent e) {
    archivoDestino = UtilidadesControlador.guardarArchivoTextoVBox(e, bguardar, inicio);
    desencriptarArchivo(e);
  }
  
  private void mostrarTexto (String localizacion) {
    try {
      // Abrimos fichero origen
      BufferedReader bOrigen = new BufferedReader(new FileReader(localizacion));
      String linea = bOrigen.readLine();
      while (linea != null) {
        textoOriginal.setText(textoOriginal.getText()+linea+"\n");
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
  
  public void desencriptarArchivo (ActionEvent e) {
    try{
    // Abrimos fichero origen
    BufferedReader bOrigen = bufferOrigen(archivoOrigen);
    
    // Leemos fichero y guardamos en una lista
    ArrayList<String> lineasOrigen = new ArrayList<String>();
    String l = bOrigen.readLine();
    while (l != null) {
      lineasOrigen.add(l);
      l = bOrigen.readLine();
    }
    bOrigen.close();
    
    // Pedimos desplazamiento para el método César
    int desplazamiento = Integer.parseInt(clave.getText());
    
    if (e.getSource().equals(bdesencripta)) {
      textoDesencriptado.setText("");
      for (String linea: lineasOrigen) {
        textoDesencriptado.setText(textoDesencriptado.getText()+ desencriptaCesar(linea, desplazamiento) +"\n");
      }
    } else if (e.getSource().equals(bguardar)) {
      // Abrimos fichero destino (a desencriptar)
      BufferedWriter bDestino = bufferDestino(archivoDestino);
      
      // Desencriptamos y escribimos
      for (String linea: lineasOrigen) {
        bDestino.write(desencriptaCesar(linea, desplazamiento));
        bDestino.newLine(); // salto de línea, mejor que "\n"
      }
      bDestino.close();
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Información de actividad");
      alert.setHeaderText(null);
      alert.setContentText("Archivo guardado correctamente.");

      alert.showAndWait();
    }
    
    } catch (Exception excep) {
      UtilidadesControlador.TipoDatoIncorrecto("hola");
    }
  }
  
  /**
   * 
   * @param ficheroOrigen
   * @return manejador del fichero abierto para lectura
   */
  private static BufferedReader bufferOrigen(String ficheroOrigen) {
    try {
      return new BufferedReader(new FileReader(ficheroOrigen));
    } catch (Exception e) {
      UtilidadesControlador.TipoDatoIncorrecto("No se ha podido abrir " + ficheroOrigen);
    }
    return null;
  }
  
  /**
   * 
   * @param ficheroDestino
   * @return manejador del fichero abierto para escritura
   */
  private static BufferedWriter bufferDestino(String ficheroDestino) {
    try {
      return new BufferedWriter(new FileWriter(ficheroDestino));
    } catch (Exception e) {
      System.err.println("No se ha podido abrir para escritura " + ficheroDestino);
      System.exit(3);
    }
    return null;
  }
  
  /**
   * Encripta la cadena que le pasamos como parámetro. 
   * @param cadena
   * @param desplazamiento
   * @return cadena encriptada
   */
  private static String desencriptaCesar(String cadena, int desplazamiento) {
    String letras = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáéíóúüñÁÉÍÓÚÜÑ";
    String cadenaDesencriptada = "";
    for (char caracter: cadena.toCharArray()) {
      char caracterDesencriptado = caracter;
      // si el carácter es alfabético, desencriptamos
      if (letras.contains(Character.toString(caracter))) {
        int posicionDondeEsta = letras.indexOf(caracter);
        int posicionCaracterDesencriptado = ((posicionDondeEsta - desplazamiento) % letras.length());
        if (posicionCaracterDesencriptado < 0) {
          posicionCaracterDesencriptado = letras.length() + posicionCaracterDesencriptado;
        }
        caracterDesencriptado = letras.charAt(posicionCaracterDesencriptado);
      } 
      cadenaDesencriptada += caracterDesencriptado;
    }
    return cadenaDesencriptada;
  }
  
}
