package ejemplos;

/**
 * Combinamos VBOX y HBOX para hacer diseños más compactos.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ej08VHBox extends Application {

	@Override
	public void start(Stage primaryStage) {
	  // con esto mostraría el nombre de la aplicación como título.
	  primaryStage.setTitle("Ejemplo "+this.getClass().getSimpleName());
	  
	  // Creamos controles
	  Label etiquetaCabecera = new Label ("Probando JavaFX");
	  etiquetaCabecera.setFont(new Font("Impact", 45));
	  // etiquetaCabecera.setTextAlignment(); -- pendiente de probar a centrar
	  HBox cabecera = new HBox(etiquetaCabecera);
	  
	  Label label11 = new Label("L11L");
	  label11.setFont(new Font("Arial", 24));
	  TextField texto12 = new TextField("T12T");
	  Text texto13 = new Text("T13T");
	  HBox hbox1 = new HBox(label11, texto12, texto13);
	  
	  TextArea texto2 = new TextArea("Rellena");
	  texto2.setFont(new Font("Arial", 14));
	  
	  Label label31 = new Label("L31L");
	  Label label33 = new Label("L33L");
	  VBox vbox32 = new VBox(new Label("321"), new Button("No va"), new Label ("333"));
	  HBox hbox3 = new HBox(label31, vbox32, label33);
	  
	  VBox vbox = new VBox(cabecera, hbox1, texto2, hbox3);
	  
	  Scene scene = new Scene(vbox);
	  primaryStage.setScene(scene);
	  primaryStage.setFullScreen(true);
	  
	  primaryStage.show();
	  
	  

		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
