package ejercicios;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ejercicios.Mina;

public class Buscaminas extends Application {
  static int filas = 5;
  static int columnas = 4;
  static Mina casilla[][] = new Mina[columnas][filas];
  
  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("App " + this.getClass().getSimpleName());
    // Layout GridPane: nos permite crear una matriz/cuadricula
    GridPane root = new GridPane();
    root.setHgap(1);   // separación entre columnas
    root.setVgap(1);   // separación entre filas
    root.setPadding(new Insets(15, 15, 15, 15));

    // Inserción de botones
    for (int i =0; i < columnas; i++) {
      for (int j=0; j < filas; j++) {
        casilla[i][j] = new Mina();
//        casilla[i][j].setText(i+","+j);
        root.add(casilla[i][j], i, j);
      }
    }
    
    // Creamos minas aleatorias
    int numeroMinas = 2;
    int minaFila;
    int minaColumna;
    
    for (int i=0; i < numeroMinas; i++) {
      do {
        minaFila = (int)(Math.random()*filas);
        System.out.println("Fila"+minaFila);
        minaColumna = (int)(Math.random()*columnas); 
        System.out.println("Columna: "+minaColumna);
      } while (casilla[minaColumna][minaFila].isEsMina() == true);
      
      casilla[minaColumna][minaFila].setEsMina(true);
    }
    
    // Comprobar si es mina
    for (int i =0; i < columnas; i++) {
      for (int j=0; j < filas; j++) {
        int a =i;
        int b =j;
        casilla[i][j].setOnAction(new EventHandler<ActionEvent>() {

          @Override
          public void handle(ActionEvent e) {
            actualizarMina(a,b);
          }
        });
      }  
    }
    
//    casilla[0][1].setText("hola");
//    casilla[0][1].setStyle("-fx-background-color: #0000ff;" + "-fx-border-color: #ff0000;");
//    casilla[0][1].setEstado(1);
    
//    Label rectangulo = new Label("Rectángulo");
//    root.add(rectangulo, 0, 0, 2, 1);
//    GridPane.setHalignment(rectangulo, HPos.CENTER);
//    rectangulo.setFont(new Font("Arial Black", 25));
// 
//    root.add(new Label ("Base: "), 0, 1);
//    
//    TextField base = new TextField();
//    root.add(base, 1, 1);
//
//    root.add(new Label("Altura: "), 0, 2);
//
//    TextField altura = new TextField();
//    root.add(altura, 1, 2);
//
//    Button calcular = new Button("Calcular");
//    calcular.setDefaultButton(true);
//    root.add(calcular, 0, 3, 2, 1); //los parámetros tercero y cuarto son el colspan=2, rowspan=1
//    GridPane.setHalignment(calcular, HPos.CENTER);
//    
//    TextArea resultado = new TextArea();
//    root.add(resultado,0,4,2,1);
    
    // manejo de eventos del botón
//    calcular.setOnAction(new EventHandler<ActionEvent>() {

//      @Override
//      public void handle(ActionEvent e) {
//        try {
////          if (base.getText().compareTo("") == 0 || base.getText().compareTo("")==0) {
////            mostrarError("Debes introducir el valor de base y altura");
////          } else if (Double.parseDouble(base.getText())<=0 || (Double.parseDouble(altura.getText())<= 0)) {
////            mostrarError("El valor introducido debe ser positivo.");
////          } else {
////            double area = Double.parseDouble(base.getText()) * Double.parseDouble(altura.getText());
////            double perimetro = 2 * (Double.parseDouble(base.getText()) + Double.parseDouble(altura.getText()));
////            resultado.setText("El triángulo de base " + base.getText() + " y altura "+ altura.getText() +" tiene:\n"+
////                               "Área: "+area+"\n"+
////                               "Perímetro: " + perimetro);
//          }
//        } catch (Exception error) {
//            mostrarError("Los valores en base y altura deben ser numéricos");
//        }
//
//    });

    // creamos escena y asignamos a escenario
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  public static void actualizarMina (int i, int j) {
    if (casilla[i][j].isEsMina() == true){
      casilla[i][j].setStyle("-fx-background-color: #0000ff;" + "-fx-border-color: #ff0000;");
      mostrarError("Perdiste!");
    } else {
      casilla[i][j].setStyle("-fx-background-color: #000000;" + "-fx-border-color: #ff0000;");
      contarAlrededor(i, j);
      casilla[i][j].setText(Integer.toString(casilla[i][j].getContador()));
    }
  } 
  
  public static void contarAlrededor (int columna, int fila) {
    try{
      if (casilla[columna-1][fila-1].isEsMina() && (columna != 0 || fila != 0)) {
        casilla[columna][fila].setContador(casilla[columna][fila].getContador()+1);
      }
      if (casilla[columna-1][fila].isEsMina() && (columna != 0)) {
        casilla[columna][fila].setContador(casilla[columna][fila].getContador()+1);
      }
      if (casilla[columna-1][fila+1].isEsMina() && (columna != 0 && fila != filas)) {
        casilla[columna][fila].setContador(casilla[columna][fila].getContador()+1);
      }
      if (casilla[columna][fila-1].isEsMina() && (fila != 0)) {
        casilla[columna][fila].setContador(casilla[columna][fila].getContador()+1);
      }
      if (casilla[columna][fila+1].isEsMina() && (fila != filas)) {
        casilla[columna][fila].setContador(casilla[columna][fila].getContador()+1);
      }
      if (casilla[columna+1][fila-1].isEsMina() && (columna != columnas || fila != 0)) {
        casilla[columna][fila].setContador(casilla[columna][fila].getContador()+1);
      }
      if (casilla[columna+1][fila].isEsMina() && (columna != columnas)) {
        casilla[columna][fila].setContador(casilla[columna][fila].getContador()+1);
      }
      if (casilla[columna+1][fila+1].isEsMina() && (columna != columnas || filas != fila)) {
        casilla[columna][fila].setContador(casilla[columna][fila].getContador()+1);
      }
//      for (int i = 0; i < columna; i++) {
//        for (int j = 0; j < fila; j++) {
//          if (i == columna || i == (columna-1) || i == (columna+1)){
//            if (j == fila || j == (fila-1) || j == (fila+1)) {
//              if (casilla[i][j].isEsMina() == true && (columna != i && fila != j)) {
//                casilla[columna][fila].setContador(casilla[columna][fila].getContador()+1); 
//              }
//            }
//          }
//        }
//      }
    } catch (Exception e){
      System.err.println("asi no");
    }
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