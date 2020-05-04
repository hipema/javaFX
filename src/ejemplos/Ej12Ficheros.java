package ejemplos;

import java.io.File;

/**
 /**
 * Ejemplo de login.
 * 
 * Similar al ejemplo anterior pero sin FXML.
 * 
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Ej12Ficheros extends Application {

  public static void main(String[] args) {
    launch(args);
}

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("JavaFX App");

    FileChooser fileChooser = new FileChooser();

    Button button = new Button("Select File");
    button.setOnAction(e -> {
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
    });

    VBox vBox = new VBox(button);
    Scene scene = new Scene(vBox, 960, 600);

    primaryStage.setScene(scene);
    primaryStage.show();
}
}