package ejemplos;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppFx1 extends Application {

	@Override
	public void start(Stage primaryStage) {
	  primaryStage.setTitle("Esto es una aplicación de ejemplo.");
	  primaryStage.show();
	  
	  
	  Label etiqueta = new Label("Hola Mundo.");
	  TextField texto = new TextField("Mi campo de texto.");
	  TextArea texto2 = new TextArea("Ahora escribe tu biografía.");
	  VBox vbox = new VBox(etiqueta, texto, texto2);
	  
	  Scene scene = new Scene(vbox, 300, 300);
	 
	  
	  primaryStage.setScene(scene);
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
