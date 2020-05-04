package ejercicios;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MezclaArchivos extends Application {

	@Override
	public void start(Stage primaryStage) {
    try {
      
      FXMLLoader fxml = new FXMLLoader(this.getClass().getResource("gui/MezclaArchivos.fxml"));
      GridPane root = fxml.<GridPane>load();  
      Scene scene = new Scene(root);
      primaryStage.setScene(scene);
      primaryStage.setTitle("App " + this.getClass().getSimpleName());     // nombre de la clase
      primaryStage.setResizable(false);
      primaryStage.show();
      
      
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
	}

	public static void main(String[] args) {
		launch(args);
	}
}
