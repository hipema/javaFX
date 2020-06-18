package ejercicios.gui;

/**
 * Controlador-vista para los eventos de NúmerosPrimos.
 * 
 */

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MaxMinMedVarControlador implements Initializable {
  
  private ArrayList <Double> notasIntroducidas = new ArrayList<Double>();
  private double minimo = 11;
  private double maximo = -1;
  private double sumaNotas = 0;
  private double media;
  private double varianza;
  
  @FXML
  private Label numNotas; // Indica el número de nota que vas a introducir.
  
  @FXML
  private TextField nota; // valor numérico que introduce el usuario
  
  @FXML
  private TextArea resultados;  // área para mostrar los resultados calculados.
  
  private int totalNotas = 10;
//  private int totalNotas = Integer.parseInt(numNotas.getText());
  
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
  
  public void introducirNota () {
    try {
      double valorNota = Double.parseDouble(nota.getText());
      if (notasIntroducidas.size() == totalNotas) {
        TipoDatoIncorrecto("Ya has introducido todas las notas.");
      } else {
        if (valorNota < 0 || valorNota>10) {
          TipoDatoIncorrecto("Error, debes introducir un número entre 0 y 10.");
        } else {
          if (valorNota < minimo) {
            minimo = valorNota;
          }
          if (valorNota > maximo) {
            maximo = valorNota;
          }
          sumaNotas += valorNota;
          notasIntroducidas.add(valorNota);
          resultados.setText(resultados.getText() + "Nota " + notasIntroducidas.size() + ": " + nota.getText() + "\n");
          nota.setText("");
          
        } if (notasIntroducidas.size() == totalNotas) {
          media = sumaNotas/notasIntroducidas.size();
          
          // Proceso segunda parte: varianza
          sumaNotas = 0;
          for (int i=0; i<notasIntroducidas.size(); i++) {
            sumaNotas += Math.pow(notasIntroducidas.get(i)-media, 2);
          }
          varianza = sumaNotas/notasIntroducidas.size();
          
          // Mostramos los resultados
          resultados.setText( "Nota máxima: " +maximo+"\n"+
                              "Nota mínima: " + minimo +"\n" +
                              "Nota media: " + media + "\n" +
                              "Varianza: "+ varianza + "\n" +
                              "-----------------------------" + "\n\n" +
                              resultados.getText());
          
        }
        if (notasIntroducidas.size() != totalNotas) {
          numNotas.setText("Introduce la Nota nº: "+(notasIntroducidas.size()+1));
        }
      }
    } catch (Exception e) {
      TipoDatoIncorrecto("Error, el tipo de dato debe ser numérico");
    }
  }
}
