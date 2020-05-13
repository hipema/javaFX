package ejercicios.gui;


/**
 * Controlador-vista para los eventos de NúmerosPrimos.
 * 
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Reflection;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import ejercicios.gui.UtilidadesControlador;

public class TextFlowyEfectosControlador implements Initializable {
  @FXML
  private GridPane inicio;
  
  @FXML
  private ImageView imagen;
  
  @FXML
  private TextField archivoImagen;
  
  @FXML
  private TextFlow texto;
  
  @FXML
  private Text texto1, texto2, texto3, textoColor, textoTamano;
  
  @FXML
  private Button BotonArchivoImagen;
  
  @FXML
  private CheckBox justificarTexto, cambioColor, cambioTamano, cambioFuente;

  @FXML
  private ChoiceBox<String> opciones;  
  
  @FXML
  private Button aplicarEfectos;
  

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    opciones.getItems().add("Original");
    opciones.getItems().add("Difuminar");
    opciones.getItems().add("Reflejar");
    opciones.getItems().add("Tonos Sepia");
  }
  
  
  public void seleccionarArchivo (ActionEvent e) {
    imagen.setImage(new Image(UtilidadesControlador.seleccionarArchivoImagen(e, BotonArchivoImagen, inicio)));
  }
 
  
  public void justificarElTexto (ActionEvent e) {
    if (justificarTexto.isSelected()) {
      texto.setTextAlignment(TextAlignment.JUSTIFY);
    } else {
      texto.setTextAlignment(TextAlignment.LEFT);
    }    
  }
  
  public void cambioDeColor (ActionEvent e) {
    if (cambioColor.isSelected()) {
      textoColor.setFill(Color.GOLD);
    } else {
      textoColor.setFill(Color.BLACK);
    }
  }
  
  public void cambioDeFuente (ActionEvent e) {
    if (cambioFuente.isSelected()) {
      texto3.setFont(new Font("Impact", 14));
    } else {
      texto3.setFont(new Font(13));
    }
  }
  
  public void cambioDeTamano (ActionEvent e) {
    if (cambioTamano.isSelected()) {
      textoTamano.setFont(new Font(25));
    } else {
      textoTamano.setFont(new Font(13));
    }
  }
  
  public void mostrarImagen(ActionEvent e) {
    imagen.setImage(new Image(archivoImagen.getText()));
  }
  
  public void desenfoque () {
    GaussianBlur blur = new GaussianBlur();
    blur.setRadius(5);
    imagen.setEffect(blur);
  }
  
  public void reflejar () {
    Reflection reflection = new Reflection();
    reflection.setTopOffset(0);
    reflection.setTopOpacity(0.75);
    reflection.setBottomOpacity(0.10);
    reflection.setFraction(0.7);
    
    imagen.setEffect(reflection);
  }
  
  public void sepia() {
    SepiaTone sepiaTone = new SepiaTone(); 
    // Set Level Indica el valor de efecto que se le aplica 
    sepiaTone.setLevel(0.8);
    imagen.setEffect(sepiaTone);
  }
  
  public void aplicarLosEfectos (ActionEvent e) {
    if (opciones.getValue().compareTo("Difuminar")==0) {
      desenfoque();
    } else if (opciones.getValue().compareTo("Reflejar")==0) {
      reflejar();
    } else if (opciones.getValue().compareTo("Original")==0) {
      imagen.setEffect(null);
    } else if (opciones.getValue().compareTo("Tonos Sepia")==0) {
      sepia();
    }    
  }
}
