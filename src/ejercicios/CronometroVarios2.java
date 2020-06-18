package ejercicios;

import java.util.ArrayList;
import java.util.Arrays;

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

public class CronometroVarios2 extends Application {
  Thread taskThread = new Thread();
  private boolean keepRunning = true;
  private int hora=0;
  private int minuto=0;
  private int segundo=0;
  
  Thread taskThread2 = new Thread();
  private boolean keepRunning2 = true;
  private int hora2=0;
  private int minuto2=0;
  private int segundo2=0;
  
  private GridPane principal = new GridPane();
  
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    
    GridPane cronometro1 = crearCronometro(taskThread, keepRunning, hora, minuto, segundo);
    GridPane cronometro2 = crearCronometro(taskThread2, keepRunning2, hora2, minuto2, segundo2);
   
    
    principal.add(cronometro1, 0, 0);
    principal.add(cronometro2, 1, 0);
    
    
    Scene scene = new Scene(principal);

    primaryStage.setScene(scene);
    primaryStage.setTitle("Cronómetro");
    primaryStage.setOnCloseRequest(e -> keepRunning = false);   // to stop the thread
    primaryStage.show();
  }

  private GridPane crearCronometro(Thread taskThread, boolean keepRunning, int hora, int minuto, int segundo) {
    // Controles Cronometro1
    Font font = new Font("Arial", 50);
    Label sep1 = new Label(":");
    Label sep2 = new Label(":");
    Label hour = new Label("00");
    Label minute = new Label("00");
    Label second = new Label("00");
    
    ArrayList<Label> etiquetas = new ArrayList<Label>(Arrays.asList(sep1, sep2, hour, minute, second));
    for (Label etiqueta : etiquetas) {
      aplicarEstilo(etiqueta,font);
    }
    
    // Creamos botones Cronometro 1
    Button iniciar1 = new Button ("Iniciar / Continuar");
    Button pausar1 = new Button ("Pausar");
    Button reiniciar1 = new Button ("Reiniciar");
    
    // Funciones Botones Cronometro 1
    funcionIniciar(hour, minute, second, iniciar1, pausar1, reiniciar1, hora, minuto, segundo);
    funcionPausar(iniciar1, pausar1);
    funcionReiniciar(hour, minute, second, iniciar1, pausar1, reiniciar1, hora, minuto, segundo);
    
    
    // Layout, scene and stage
    
    HBox cronometro1General = new HBox(5, hour, sep1, minute, sep2, second);
    cronometro1General.setPadding(new Insets(10));
    GridPane cronometro1 = new GridPane();
    cronometro1.add(cronometro1General, 0, 0, 3, 1);
    cronometro1.add(iniciar1, 0, 1);
    cronometro1.add(pausar1, 1, 1);
    cronometro1.add(reiniciar1, 2, 1);
    return cronometro1;
  }

  private void funcionReiniciar(Label hour, Label minute, Label second, Button iniciar1, Button pausar1,
      Button reiniciar1) {
    reiniciar1.setOnAction(e ->{
      keepRunning = false;
      hora = 0;
      minuto = 0;
      segundo = 0;
      hour  .setText(String.format("%02d", hora));
      minute.setText(String.format("%02d", minuto));
      second.setText(String.format("%02d", segundo));
      iniciar1.setDisable(false);
      reiniciar1.setDisable(true);
      pausar1.setDisable(true);
    });
  }

  private void funcionPausar(Button iniciar1, Button pausar1) {
    pausar1.setOnAction(e -> {
      keepRunning = false;
      iniciar1.setDisable(false);
      pausar1.setDisable(true);
    });
  }

  private void funcionIniciar(Label hour, Label minute, Label second, Button iniciar1, Button pausar1,
      Button reiniciar1, int hora, int minuto, int segundo) {
      iniciar1.setOnAction(e -> {
      if (!keepRunning) {
        keepRunning = true;
      }  
      cronometro(hour, minute, second, hora, minuto, segundo);  
      iniciar1.setDisable(true);
      pausar1.setDisable(false);
      reiniciar1.setDisable(false);
    });
  }

  private void aplicarEstilo(Label etiqueta, Font font) {
    etiqueta.setFont(font);
  }

  private void cronometro(Label hour, Label minute, Label second, int hora, int minuto, int segundo) {
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