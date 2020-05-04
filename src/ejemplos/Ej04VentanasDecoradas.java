package ejemplos;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Ej04VentanasDecoradas extends Application {
  public static void main (String[] args) {
    launch(args);
  }
  @Override
  public void start (Stage primaryStage) {
    primaryStage.setTitle("JavaFX App");
    primaryStage.show();
    
    // vector con cinco escenarios
    Stage[] stages = {new Stage(), new Stage(), new Stage(), new Stage(), new Stage()} ;
   
    // vector con cinco estilos de decoración de ventana
    StageStyle[] estilos = {  StageStyle.DECORATED, StageStyle.UNDECORATED,
                              StageStyle.UNIFIED, StageStyle.TRANSPARENT,
                              StageStyle.UTILITY};
    
    // aplicamos estilo a cada escenario y ponemos por tículo el escenario en la ventana.
    for (int i=0; i<stages.length; i++) {
      stages[i].initStyle(estilos[i]);
      stages[i].setTitle(estilos[i].toString());
      stages[i].show();
    }
    
  }  
}