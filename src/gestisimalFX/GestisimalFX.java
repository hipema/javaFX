package gestisimalFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GestisimalFX extends Application {

	@Override
	public void start(Stage primaryStage) {
    try {
      
      FXMLLoader fxml = new FXMLLoader(this.getClass().getResource("gui/GestisimalFX.fxml"));
      VBox root = fxml.<VBox>load();  
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
