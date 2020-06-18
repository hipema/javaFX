package ejercicios;
/**
 * JavaFX uses a single-threaded rendering design, meaning only a single thread can render anything on 
 * the screen, and that is the JavaFX application thread. 
 * 
 * In fact, only the JavaFX application thread is allowed to make any changes to the JavaFX Scene Graph 
 * in general.
 * 
 * A single-threaded rendering design is easier to implement correctly, but long-running tasks that run 
 * within the JavaFX application thread make the GUI unresponsive until the task is completed. 
 * 
 * No JavaFX GUI controls react to mouse clicks, mouse over, keyboard input while the JavaFX application 
 * thread is busy running that task.
 * 
 * See more at http://tutorials.jenkov.com/javafx/concurrency.html
 * 
 * In this example we implement a clock.
 * 
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Cronometro extends Application {
  Thread taskThread = new Thread();
  private boolean keepRunning = true;
  private int hora=0;
  private int minuto=0;
  private int segundo=0;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    
    // clock controls

    Font font = new Font("Arial", 50);
    
    Label sep1 = new Label(":");
    sep1.setFont(font);
    
    Label sep2 = new Label(":");
    sep2.setFont(font);
    
    Label hour = new Label("00");
    hour.setFont(font);
    
    Label minute = new Label("00");
    minute.setFont(font);
    
    Label second = new Label("00");
    second.setFont(font);
    
    /*
     * Sometimes you absolutely need to perform some long-running task in a JavaFX application. 
     * You don't want to leave the GUI unresponsive while the task is running, so you want to run the 
     * ask in its own thread. However, you would like the running task to update the GUI - either along
     * the way, or when the task is completed. The task thread cannot update the GUI (the scene graph) 
     * directly - but JavaFX has a solution for this problem.
     * 
     * JavaFX contains the Platform class which has a runLater() method. The runLater() method takes a 
     * Runnable which is executed by the JavaFX application thread when it has time. From inside this 
     * Runnable you can modify the JavaFX scene graph.
     */
    
    
    // Creamos botones
    Button iniciar = new Button ("Iniciar / Continuar");
    Button pausar = new Button ("Pausar");
    Button reiniciar = new Button ("Reiniciar");
    
    iniciar.setOnAction(e -> {
      if (!keepRunning) {
        keepRunning = true;
      } else {
        
      }
      cronometro(hour, minute, second);  
      iniciar.setDisable(true);
      pausar.setDisable(false);
      reiniciar.setDisable(false);
    });
    
    
    pausar.setOnAction(e -> {
      keepRunning = false;
      iniciar.setDisable(false);
      pausar.setDisable(true);
    });
    
    
    reiniciar.setOnAction(e ->{
      keepRunning = false;
      hora = 0;
      minuto = 0;
      segundo = 0;
      hour  .setText(String.format("%02d", hora));
      minute.setText(String.format("%02d", minuto));
      second.setText(String.format("%02d", segundo));
      iniciar.setDisable(false);
      reiniciar.setDisable(true);
      pausar.setDisable(true);
    });
    
    // Layout, scene and stage
    
    HBox raizCronometro = new HBox(5, hour, sep1, minute, sep2, second);
    raizCronometro.setPadding(new Insets(10));
    GridPane raiz = new GridPane();
    raiz.add(raizCronometro, 0, 0, 3, 1);
    raiz.add(iniciar, 0, 1);
    raiz.add(pausar, 1, 1);
    raiz.add(reiniciar, 2, 1);
    Scene scene = new Scene(raiz);

    primaryStage.setScene(scene);
    primaryStage.setTitle("Cronómetro");
    primaryStage.setOnCloseRequest(e -> keepRunning = false);   // to stop the thread
    primaryStage.show();
  }

  private void cronometro(Label hour, Label minute, Label second) {
    taskThread = new Thread(new Runnable() {
      
      
      @Override
      public void run() {
        while (keepRunning) { 
          System.out.println(hora+":"+minuto+":"+segundo+"\n");
          if (segundo <59) {
            segundo++;
          } else if (segundo == 59 && minuto < 59) {
            minuto++;
            segundo = 0;
          } else if (segundo == 59 && minuto == 59){
            hora++;
            segundo=0;
            minuto=0;
          }
          
          Platform.runLater(new Runnable() {    // controls JavaFX update
            @Override
            public void run() {
              // escribe hora:minutos:segundos (actualizandose a medida que va pasando cada segundo)
              hour  .setText(String.format("%02d", hora));
              minute.setText(String.format("%02d", minuto));
              second.setText(String.format("%02d", segundo));
            }
          });
          
          try {                                 // wait one second
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }   
        }
      }
    });

    taskThread.start();
  }
}