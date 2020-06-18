package ejercicios.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AdivinaUnNumeroControlador implements Initializable {
  private int numSecreto = (int)(Math.random()*100+1);
  
  @FXML
  private Label intentosQueQuedan;  
  
  @FXML
  private TextField n;
  
  @FXML
  private Label textoModificar;
  
  @FXML
  private Label numerosProbados;
  
  @FXML
  private Label accion;

  public void probarNumero (ActionEvent e) {
    int INTENTOS_MAXIMOS=10;
    int intentos = Integer.parseInt(intentosQueQuedan.getText());
    if (accion.getText().compareTo("Jugar de nuevo? (s/n)") != 0){
      int numero = Integer.parseInt(n.getText());
      if (numero == numSecreto) {
        textoModificar.setText("¡Exacto! Usted adivinó en "+(INTENTOS_MAXIMOS-intentos+1)+" intentos.");
        n.setText("");
        numerosProbados.setText("Final de la partida");
        accion.setText("Jugar de nuevo? (s/n)");
        
      }else if (numero < numSecreto) {
        textoModificar.setText(numero + " es menor que el número a adivinar");
        intentos--;
        intentosQueQuedan.setText(Integer.toString(intentos));
        n.setText("");
        numerosProbados.setText(numerosProbados.getText()+ "  " + numero);
        
      } else {
        textoModificar.setText(numero + " es mayor que el número a adivinar");
        intentos--;
        intentosQueQuedan.setText(Integer.toString(intentos));
        n.setText("");
        numerosProbados.setText(numerosProbados.getText()+ "  " + numero);
      }
      if (intentos == 0) {
        textoModificar.setText("Has agotado el número máximo de intentos. El número era: " + numSecreto);
        n.setText("");
        numerosProbados.setText("Final de la partida");
        accion.setText("Jugar de nuevo? (s/n)");
      }
    }
    if (accion.getText().compareTo("Jugar de nuevo? (s/n)") == 0 && (n.getText().toUpperCase().compareTo("S") == 0)) {
      numSecreto = (int)(Math.random()*100+1);
      accion.setText("Probar");
      numerosProbados.setText("");
      textoModificar.setText("Nueva partida preparada");
      intentosQueQuedan.setText("10");
      n.setText("");
    } else {
      
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    
  }
}