package metalmania.gui;

import java.net.URL;
import metalmania.gui.MetalmaniaControlador;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class InicioMetalmaniaControlador implements Initializable {
  ArrayList <String> nombreJugadores = new ArrayList<String>();
  
  @FXML
  private TextField texto1, texto2, texto3, texto4, texto5;
  
  @FXML
  private Button confirmar;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
  }
  /**
   * Comprueba el tipo de evento recibido para capturar los datos correctos.
   * @param event
   */
  public void filtraEvento (Event event) {
    if (event.getEventType().toString().compareTo("ACTION") == 0) {
      capturaDatos(event);
    } else if (((KeyEvent) event).getCode() == KeyCode.ENTER){
      capturaDatos(event);
    } 
  }

  /**
   * Captura los nombres de los jugadores que participarán en la partida.
   * @param event
   */
  private void capturaDatos(Event event) {
    ArrayList <TextField> textos = new ArrayList <TextField>(Arrays.asList(texto1, texto2, texto3, texto4, texto5));
    for (TextField texto : textos) {
      if (texto.getText().compareTo("") != 0) {
        nombreJugadores.add(texto.getText());
      }
    }
    MetalmaniaControlador.insertarJugadores(nombreJugadores);
    Node source = (Node) event.getSource();
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
  }
}
