package ejercicios;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Rectangulo extends Application {

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("App " + this.getClass().getSimpleName());
    
    // Layout GridPane: nos permite crear una matriz/cuadricula
    GridPane root = new GridPane();
    root.setHgap(15);   // separación entre columnas
    root.setVgap(15);   // separación entre filas
    root.setPadding(new Insets(15, 15, 15, 15));

    // controles
    Label rectangulo = new Label("Rectángulo");
    root.add(rectangulo, 0, 0, 2, 1);
    GridPane.setHalignment(rectangulo, HPos.CENTER);
    rectangulo.setFont(new Font("Arial Black", 25));
 
    root.add(new Label ("Base: "), 0, 1);
    
    TextField base = new TextField();
    root.add(base, 1, 1);

    root.add(new Label("Altura: "), 0, 2);

    TextField altura = new TextField();
    root.add(altura, 1, 2);

    Button calcular = new Button("Calcular");
    calcular.setDefaultButton(true);
    root.add(calcular, 0, 3, 2, 1); //los parámetros tercero y cuarto son el colspan=2, rowspan=1
    GridPane.setHalignment(calcular, HPos.CENTER);
    
    TextArea resultado = new TextArea();
    root.add(resultado,0,4,2,1);
    
    // manejo de eventos del botón
    calcular.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent e) {
        try {
          if (base.getText().compareTo("") == 0 || base.getText().compareTo("")==0) {
            mostrarError("Debes introducir el valor de base y altura");
          } else if (Double.parseDouble(base.getText())<=0 || (Double.parseDouble(altura.getText())<= 0)) {
            mostrarError("El valor introducido debe ser positivo.");
          } else {
            double area = Double.parseDouble(base.getText()) * Double.parseDouble(altura.getText());
            double perimetro = 2 * (Double.parseDouble(base.getText()) + Double.parseDouble(altura.getText()));
            resultado.setText("El triángulo de base " + base.getText() + " y altura "+ altura.getText() +" tiene:\n"+
                               "Área: "+area+"\n"+
                               "Perímetro: " + perimetro);
          }
        } catch (Exception error) {
            mostrarError("Los valores en base y altura deben ser numéricos");
        }
      }
    });

    // creamos escena y asignamos a escenario
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void mostrarError (String mensaje) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setHeaderText(null);
    alert.setTitle("Error");
    alert.setContentText(mensaje);
    alert.showAndWait();
  }
  public static void main(String[] args) {
    launch(args);
  }
}