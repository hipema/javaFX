package metalmania.gui;

import java.net.URL;
import metalmania.juego.Musicos;
import metalmania.excepciones.ValorIncorrectoExcepcion;
import metalmania.juego.Dado;
import metalmania.juego.Jugador;
import metalmania.juego.Musico;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MetalmaniaControlador implements Initializable {
  // MARCA Atributos
  // Stage enviado para recoger información de configuración al inicio de la
  // partida.
  Stage newStage = new Stage();
  // ArrayList donde se insertan los nombres de los jugadores desde el
  // InicioMetalmaniaControlador.
  private static ArrayList<String> nombreJugadores = new ArrayList<String>();
  // Almacena los objetos "Jugador" que desarrollan la partida.
  private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
  // Almacena todos los músicos disponibles
  private ArrayList<Musico> cartasMusicos;
  // Almacena todos los músicos descartados
  private ArrayList<Musico> cartasDescartes = new ArrayList<Musico>();
  // Almacena los músicos que están de birras en ese momento.
  private ArrayList<Musico> musicosDeBirras;
  // Almacena los jugadores que están de birras en ese momento.
  private ArrayList<Jugador> jugadoresDeBirras;
  // Almacena los Músicos disponibles para la Audición
  private ArrayList<Musico> musicosAudicion = new ArrayList<Musico>();
  // Variable booleana para saber si es inicio de partida
  private boolean esComienzoPartida = true;
  private boolean hayGanador = false;
  private boolean alguienDeBirras = false;
  private boolean estamosEnAccionFama = false;
  private boolean estamosEnAccionMantenimiento = false;
  private boolean estamosEnAccionFondos = false;
  private boolean estamosEnAccionAudicion = false;
  private boolean tocaPagar = false;
  private boolean hayInactivo;
  private boolean mantenimientoExtra = false;
  // Variables introducidas para desarrollo de eventos
  private int resultadoEvento = 0;
  private int indicadorJugador = -1;
  private int indiceElegido = 0;
  private int indiceMusicoElegido = 0;
  private int indiceDeBirras = 0;

  // Variable para almacenar acción a comprobar
  private String accionARealizar;

  //Variables para almacenar los musicos relacionados con la accionAudicion
  private Musico musicoAContratar;
  private Musico musicoADespedir;
  /**
   * Casillas de Información de los Jugadores
   */
  @FXML
  private Label j1fama, j1fondos, j2fama, j2fondos, j3fama, j3fondos, j4fama, j4fondos, j5fama, j5fondos;
  private ArrayList<Label> informacionFama;
  private ArrayList<Label> informacionFondos;

  /**
   * Casillas de Eventos
   */
  @FXML
  private Button eventos2, eventos3, eventos4, eventos5, eventos6, eventos7, eventos8, eventos9, eventos10, eventos11,
      eventos12, eventoOK, eventoSumar, eventoRestar;
  private ArrayList<Button> listadoEventos;

  /**
   * Botones de Acción Fama
   */
  @FXML
  private Button bactuacion, bgrabacion, bpublicidad;

  /**
   * Botones de Acción Mantenimiento
   */
  @FXML
  private Button bfondos, baudicion, bbirras;

  @FXML
  private HBox fondofama, fondomantenimiento;
  
  @FXML
  private VBox fondoevento;

  /**
   * Tabs de los Jugadores y TabPane
   */
  @FXML
  private Tab jugador1Tab, jugador2Tab, jugador3Tab, jugador4Tab, jugador5Tab;

  @FXML
  private TabPane tabJugadores;

  /**
   * Pantalla de Músicos de cada Jugador.
   */
  @FXML
  private GridPane MusicosJ1, MusicosJ2, MusicosJ3, MusicosJ4, MusicosJ5;
  /**
   * ArrayList con todas las pantallas de músicos de cada Jugador para recorrerlo
   * automáticamente.
   */
  private ArrayList<GridPane> guiMusicos;
  
  /**
   * ToogleGroup con la información de los jugadores en Acción Fama de cada jugador
   */
  @FXML
  private ToggleGroup toggleActJ1, toggleActJ2, toggleActJ3, toggleActJ4, toggleActJ5;
  
  private ArrayList<ToggleGroup> togglesActuaciones;
  
  /**
   * ComboBox  para asignar musicos a los dados
   */
  @FXML
  private ComboBox<String> combo1, combo2, combo3, combo4, combo5;

  private ArrayList<ComboBox<String>> combos;
  /**
   * Acciones de los Jugadores
   */
  @FXML
  private ImageView actuacionJ1, actuacionJ2, actuacionJ3, actuacionJ4, actuacionJ5;

  /**
   * ArrayList con todas las imágenes de acciones de los jugadores.
   */
  private ArrayList<ImageView> actuaciones;

  /**
   * Pantalla para mostrar mensajes al usuario durante la partida.
   */
  @FXML
  private TextArea mensajes;

  @FXML
  private TableView<Jugador> resumen;

  /**
   * Casillas de dados
   */
  @FXML
  private Button dado1, dado2, dado3, dado4, dado5, comenzarPartida;
  private ArrayList<Button> botonesDados;
  private ArrayList<Dado> dados = new ArrayList<Dado>(
      Arrays.asList(new Dado(), new Dado(), new Dado(), new Dado(), new Dado()));
  
  // Controladores de musicosAudicion
  @FXML
  private GridPane gridAudicon;

  // MARCA Métodos controlador de botones
  public void comenzarContinuar(ActionEvent e) throws ValorIncorrectoExcepcion, InterruptedException {
    Jugador jugador;
    if (esComienzoPartida) {
      esComienzoPartida = false;
      comenzarPartida.setText("Continuar");
      comenzarPartida.setVisible(false);
      tiradaInicial();
      mensajes.appendText(
          "\nEs el turno de " + jugadores.get(0).getNombre() + "\n¡Pulsa continuar y que comience la fiesta!");
      comenzarPartida.setVisible(true);

    } else if (alguienDeBirras) {
      Musico musicoDeBirrasActivo = musicosDeBirras.get(indiceDeBirras);
      jugador = buscarJugadorPropietario(musicoDeBirrasActivo);
      int contadorDeSeis = musicoDeBirrasActivo.accionBirra(dados);
//      mostrarTabJugador(jugadores.indexOf(jugador));
      switch (contadorDeSeis) {
      case 0:
        if (musicoDeBirrasActivo.getEstado() == 1) {
          mensajes.appendText(
              "\n" + musicoDeBirrasActivo.getNombre() + " ya se encontraba activo, continúamos la partida.");
        } else {
          mensajes
          .appendText("\n" + musicoDeBirrasActivo.getNombre() + " pasa de " + musicoDeBirrasActivo.getEstadoString());
          musicoDeBirrasActivo.modificarEstado(-1); // Se "recupera"
          mensajes.appendText(" a " + musicoDeBirrasActivo.getEstadoString());
        }
        break;
      case 1:
        if (musicoDeBirrasActivo.getEstado()==3) {
          mensajes.appendText(
              "\n" + musicoDeBirrasActivo.getNombre() + " ya se encontraba inactivo, continúamos la partida.");
        } else {
          mensajes
          .appendText("\n" + musicoDeBirrasActivo.getNombre() + " pasa de " + musicoDeBirrasActivo.getEstadoString());
          musicoDeBirrasActivo.modificarEstado(1); // Va a peor
          mensajes.appendText(" a " + musicoDeBirrasActivo.getEstadoString());
        }
        break;
      case 2:
        mensajes.appendText("\n¡Felicidades " + jugador.getNombre() + "! gracias a " + musicoDeBirrasActivo.getNombre()
            + "se ha montado una fiesta excelente.");
        fiestaAntologica(jugador, 5);
        indiceDeBirras += jugador.getBanda().size() - jugador.getBanda().indexOf(musicoDeBirrasActivo) -1;
//        mostrarTabJugador(jugadores.indexOf(buscarJugadorPropietario(musicosDeBirras.get(indiceDeBirras+1))));
        break;
      case 3:
        mensajes.appendText("\n¡Felicidades " + jugador.getNombre() + "! gracias a " + musicoDeBirrasActivo.getNombre()
            + "se ha montado una fiesta BRUTAL.");
        fiestaAntologica(jugador, 10);
        indiceDeBirras += jugador.getBanda().size() - jugador.getBanda().indexOf(musicoDeBirrasActivo) -1;
//        mostrarTabJugador(jugadores.indexOf(buscarJugadorPropietario(musicosDeBirras.get(indiceDeBirras+1))));
        break;
      }
      escribirGuiMusicos();
      indiceDeBirras++;
        
      // Era el último músico que se iba de marcha.
      if (indiceDeBirras > musicosDeBirras.size() - 1) { // Se acabó la fiesta
        indiceDeBirras = 0;
        alguienDeBirras = false;
        mostrarTabJugador(indicadorJugador);
        if (!estamosEnAccionMantenimiento) {
          terminaEventoEmpiezaFama();
        } else if (mantenimientoExtra) {
          mantenimientoExtra = false;
          empiezaMantenimiento();
        } else {
          estamosEnAccionMantenimiento = false;
          tocaPagar = true;
        }
      } else { // ¡Le toca al siguiente!
        mensajes.appendText("\n¡" + musicosDeBirras.get(indiceDeBirras).getNombre() + " va de marcha!");
        vaDeBirras(musicosDeBirras.get(indiceDeBirras));
      }
    } else if (estamosEnAccionFama) {
      if (comprobarComboBox()) {
        estamosEnAccionFama = false;
        jugadorHaceAccionFama(jugadores.get(indicadorJugador));
        
        // Deshabilitamos los combos
        for (ComboBox<String> combo : combos) {
          combo.setDisable(true);
          combo.getItems().clear();
        }
        empiezaMantenimiento();
      }
    } else if (estamosEnAccionFondos) {
        if (mantenimientoExtra) {
          mantenimientoExtra = false;
          empiezaMantenimiento();
        } else {
          estamosEnAccionMantenimiento = false;
          tocaPagar = true;
        }
        estamosEnAccionFondos = false;
        jugadores.get(indicadorJugador).pagar(-(dados.get(0).getCara()+6));
        escribirInfo();
        mensajes.appendText("\n\nHas sumado "+ (dados.get(0).getCara()+6) + "$ a tu bolsillo.");
    } else if (tocaPagar) {
      if (!jugadores.get(indicadorJugador).isSinPagar()) {
        mensajes.appendText("\n\nVas a realizar el pago a la banda, cada músico cobra 1$ por su actuación.");
        if (jugadores.get(indicadorJugador).getDinero() < jugadores.get(indicadorJugador).getBanda().size()) {
          noHayFondos();
          jugadores.get(indicadorJugador).setDinero(0);
        } else {
          jugadores.get(indicadorJugador).pagar(jugadores.get(indicadorJugador).getBanda().size());
        }
        jugadores.get(indicadorJugador).setSinPagar(true);
        mensajes.appendText("\n\nHa terminado tu turno, quedas en paz con tu banda.");
      } else {
        mensajes.appendText("\n\nHa terminado tu turno, te libras de pagar a la banda.");  
      }
      escribirInfo();
      escribirGuiMusicos();
      
      tocaPagar = false;
    } else if (!hayGanador) {
      if (jugadores.size() - 1 <= indicadorJugador)
        indicadorJugador = -1;
      indicadorJugador++;
      hayGanador = inicioTurno(jugadores.get(indicadorJugador));
//      if (!hayGanador) {
//        if (indicadorJugador == Jugadores.size()-1) {
//          mensajes.appendText("\nEs el turno de " + Jugadores.get(0).getNombre());
//        } else {
//          mensajes.appendText("\nEs el turno de " + Jugadores.get(indicadorJugador+1).getNombre());
//        }
//      }
    }
    // mensajes.setText("Ya hay ganador");
//      pausa = false;
//      comenzarPartida.setDisable(true);
  }


  private void jugadorHaceAccionFama(Jugador jugador) {
    int contadorExito = 0;
    for (Musico musico : jugador.getBanda()) {
      for (int i = 0; i < jugador.getBanda().size(); i++) {
        if (musico.getNombre().compareTo(combos.get(i).getValue().toString()) == 0) {
          contadorExito += calculoAccionFamaMusico(musico, i);
        }
      }
    }
    if (contadorExito > 1) {
      int nivelAntiguo = 0;
      int nivelActual = 0;
      if (accionARealizar.compareTo("actuacion") == 0) {
        nivelAntiguo = jugador.getNivelActuacion();
        jugador.setNivelActuacion(1);
        nivelActual = jugador.getNivelActuacion();
        jugador.setActuacion(jugador.getActuacion()+1);
      } else if (accionARealizar.compareTo("grabacion") == 0) {
        nivelAntiguo = jugador.getNivelGrabacion();
        jugador.setNivelGrabacion(1);
        nivelActual = jugador.getNivelGrabacion();
        jugador.setGrabacion(jugador.getGrabacion()+1);
      } else if (accionARealizar.compareTo("publicidad") == 0) {
        nivelAntiguo = jugador.getNivelPublicidad();
        jugador.setNivelPublicidad(1);
        nivelActual = jugador.getNivelPublicidad();
        jugador.setPublicidad(jugador.getPublicidad()+1);
      }
      if (nivelAntiguo != nivelActual) {
        mensajes.appendText("\nHas subido un nivel de " + accionARealizar+".");
      }
      escribirGuiAcciones(jugador, accionARealizar);
    }
  }

  private int calculoAccionFamaMusico(Musico musico, int i) {
    int resultado = 0;
    if (accionARealizar.equals("actuacion")) {
      resultado = dados.get(i).getCara() - musico.getEjecucionUso();
    } else if (accionARealizar.equals("grabacion")) {
      resultado = dados.get(i).getCara() - musico.getInspiracion();
    } else if (accionARealizar.equals("publicidad")) {
      resultado = dados.get(i).getCara() - musico.getCarisma();
    }
    if (dados.get(i).getCara() == 6 || resultado > 0) {
      if (musico.modificarEstado(1)) {
        mensajes.appendText("\nFallo: " + musico.getNombre() + " ha tenido una actuación horrible, su estado cambia a "
            + musico.getEstadoString() + "\n");
      }
      try {
        escribirGuiMusicos();
      } catch (ValorIncorrectoExcepcion e) {
        e.printStackTrace();
      }
    } else if (resultado == 0) {
      mensajes.appendText("\nRutina: " + musico.getNombre() + " ha llevado a cabo una actuación pasable. \n");// rutina, no
                                                                                                           // pasa nada
    } else if (musico.getEstado() == 2) {
      mensajes.appendText("\nÉxito: " + musico.getNombre()
          + " ha tenido una gran actuación, pero por su conflictividad con la banda no aumenta la fama.\n");
      return 1;
    } else {
      try {
        jugadores.get(indicadorJugador).aumentarFama(dados.get(i).getCara());
        mensajes
            .appendText("\nÉxito: " + musico.getNombre() + " ha tenido una actuación brillante, gracias a él la fama de "
                + jugadores.get(indicadorJugador).getNombre() + " aumenta en " + dados.get(i).getCara() + "\n");
        escribirInfo();
        return 1;
      } catch (ValorIncorrectoExcepcion e) {
        e.printStackTrace();
      }
    }
    return 0;

  }

  private void terminaEventoEmpiezaFama() {
    hayInactivo = false;
    for (Musico musico : jugadores.get(indicadorJugador).getBanda()) {
      if (musico.getEstado() == 3) {
        hayInactivo = true;
        mantenimientoExtra = true;
        break;
      }
    }
    if (!hayInactivo) {
      comenzarPartida.setDisable(true);
      botonesFama(false);
      mensajes.appendText("\n\nElige la acción fama a realizar.");
    } else {
      mensajeAlerta("Tienes algún jugador inactivo, en vez de acción fama harás dos veces acción mantenimiento");
      empiezaMantenimiento();
    }
//    comenzarPartida.setDisable(false); //esto está habilitado para poder seguir probando la partida.
  }

  private void empiezaMantenimiento() {
    estamosEnAccionMantenimiento = true;
    comenzarPartida.setDisable(true);
    botonesMantenimiento(false);
    mensajes.appendText("\n\nElige la acción de mantenimiento a realizar.");
  }
  
  public void accionBirras (ActionEvent e) {
    mensajes.appendText("\n\nHas seleccionado irte de fiesta.");
    botonesMantenimiento(true);
    nosVamosDeBirras(new ArrayList<Jugador>(Arrays.asList(jugadores.get(indicadorJugador))));
    comenzarPartida.setDisable(false);
  }

  
  public void accionAudicion (ActionEvent e) {
    
    jugadorDecideAudicion();

  }
  
  public void accionFondos (ActionEvent e) {
    botonesMantenimiento(true);
    setDadosATirar(jugadores.get(indicadorJugador), 1);
    dialogoTirar(jugadores.get(indicadorJugador));
    estamosEnAccionFondos = true;
    comenzarPartida.setDisable(false);
  }

  private void fiestaAntologica(Jugador jugador, int fama) throws ValorIncorrectoExcepcion {
    for (Musico musico : jugador.getBanda()) {
      musico.estadoActivo();
    }
    jugador.aumentarFama(fama);
    jugador.setSinPagar(true);
    escribirInfo();
  }

  /**
   * Esta función modifica en 1 ó -1 el valor del evento, restando en cada
   * interacción 2$ al jugador.
   * 
   * @param e
   */
  public void modificarEvento(ActionEvent e) {
    listadoEventos.get(resultadoEvento).setStyle("");
    if (e.getSource().equals(eventoSumar) && resultadoEvento < 10) {
      moverEvento(1);
    } else if (e.getSource().equals(eventoRestar) && resultadoEvento > 0) {
      moverEvento(-1);
    }
    listadoEventos.get(resultadoEvento).setStyle("-fx-background-color: gold");
  }

  private void moverEvento(int valor) {
    try {
      jugadores.get(indicadorJugador).pagar(2);
      escribirInfo();
      resultadoEvento += valor;
    } catch (ValorIncorrectoExcepcion e1) {
      mensajeAlerta("No dispones de fondos para realizar la operación");
    }
  }
  
  

  // MARCA Lanzamiento partida

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    guiMusicos = new ArrayList<GridPane>(Arrays.asList(MusicosJ1, MusicosJ2, MusicosJ3, MusicosJ4, MusicosJ5));
    botonesDados = new ArrayList<Button>(Arrays.asList(dado1, dado2, dado3, dado4, dado5));
    listadoEventos = new ArrayList<Button>(Arrays.asList(eventos2, eventos3, eventos4, eventos5, eventos6, eventos7,
        eventos8, eventos9, eventos10, eventos11, eventos12));
    actuaciones = new ArrayList<ImageView>(
        Arrays.asList(actuacionJ1, actuacionJ2, actuacionJ3, actuacionJ4, actuacionJ5));
    togglesActuaciones = new ArrayList<ToggleGroup>(Arrays.asList(toggleActJ1, toggleActJ2, toggleActJ3, toggleActJ4, toggleActJ5));
    resumen.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nombre"));
    resumen.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("fama"));
    resumen.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("dinero"));
    combos = new ArrayList<ComboBox<String>>(Arrays.asList(combo1, combo2, combo3, combo4, combo5));
    // TODO Scroll TextArea . Esto debería hacer que el texto estuviese siempre abajo, hay que revisarlo.
    mensajes.textProperty().addListener(new ChangeListener<Object>() {
      @Override
      public void changed(ObservableValue<?> observable, Object oldValue,
              Object newValue) {
          mensajes.setScrollTop(Double.MAX_VALUE); 
      }
    });
    lanzarMenu();

  }

  private void lanzarMenu() {
    try {
      FXMLLoader fxml = new FXMLLoader(this.getClass().getResource("InicioMetalmania.fxml"));
      VBox root = fxml.<VBox>load();
      Scene escenaInicio = new Scene(root);
      newStage.setScene(escenaInicio);
      newStage.setTitle("Metalmania. Configura tu partida"); // nombre de la clase
      newStage.setResizable(false);
      newStage.showAndWait();
      config();
      if (nombreJugadores.isEmpty()) {
        System.exit(0);
      }
      nombreJugadores.clear();
      // Rellenamos los músicos disponibles para la audición
      for (int i=0; i < 9; i++) {
        Musico carta;
        carta = cartasMusicos.get((int) (Math.random() * (cartasMusicos.size())));
        musicosAudicion.add(carta);
        cartasMusicos.remove(carta);
      }
      //mostrarBandas();
      mostrarMusicosAudicion();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }

  public static void insertarJugadores(ArrayList<String> jugadoresRecibidos) {
    nombreJugadores = jugadoresRecibidos;
  }

  // Métodos de configuración
  /**
   * Configuración de la partida y aspecto gráfico del juego
   * 
   * @throws ValorIncorrectoExcepcion
   */
  private void config() throws ValorIncorrectoExcepcion {
    modificarNombres();
    escribirEventos();
    crearObjetosJugador();
    informacionFama = new ArrayList<Label>(Arrays.asList(j1fama, j2fama, j3fama, j4fama, j5fama));
    informacionFondos = new ArrayList<Label>(Arrays.asList(j1fondos, j2fondos, j3fondos, j4fondos, j5fondos));
    escribirInfo();
    repartirMusicos();
    escribirGuiMusicos();
  }

  private void modificarNombres() {
    int sizeTabs = tabJugadores.getTabs().size();
    for (int i = 0; i < sizeTabs; i++) {
      if (i < nombreJugadores.size()) {
        tabJugadores.getTabs().get(i).setText(nombreJugadores.get(i));
      } else {
        tabJugadores.getTabs().remove(nombreJugadores.size());
      }
    }
// jugadores.clear?
  }

  private void escribirEventos() {
    eventos2.setTooltip(new Tooltip("Felicidades: Los otros jugadores pierden 2$ y tú ganas 6$."));
    eventos3.setTooltip(new Tooltip("Venta de camisetas: ganas 4$."));
    eventos4.setTooltip(new Tooltip("¡Escándalo!: Otro jugador a tu elección pierde 4 de fama."));
    eventos5.setTooltip(new Tooltip("Fan Party: Ganas 2 de Fama."));
    eventos6.setTooltip(new Tooltip("Gafado: Pasa a conflictivo el músico de otro jugador."));
    eventos7.setTooltip(new Tooltip("Quiero tocar contigo: Haz una audición extra."));
    eventos8.setTooltip(new Tooltip("La vida es bella: Pasa a activo uno de tus músicos."));
    eventos9.setTooltip(new Tooltip("Deudas de juego: Otro jugador a tu elección pierde 4$."));
    eventos10.setTooltip(new Tooltip("Noche de póker: Ganas 2$."));
    eventos11.setTooltip(new Tooltip("Duelo de jarras: Tú y otro jugador os “Vais de Birras”"));
    eventos12.setTooltip(new Tooltip("Farra Forever: Todos os “Vais de Birras”"));
  }

  private void crearObjetosJugador() throws ValorIncorrectoExcepcion {
    for (String jugador : nombreJugadores) {
      jugadores.add(new Jugador(jugador));
    }
  }

  private void escribirInfo() {
    for (int i = 0; i < tabJugadores.getTabs().size(); i++) {
      informacionFama.get(i).setText(Integer.toString(jugadores.get(i).getFama()));
      informacionFondos.get(i).setText(Integer.toString(jugadores.get(i).getDinero()));
    }
    resumen.getItems().clear();
    for (Jugador jugador : jugadores) {
      resumen.getItems().add(jugador);
    }
    resumen.getColumns().get(1).setSortType(TableColumn.SortType.DESCENDING);
    resumen.getSortOrder().add(resumen.getColumns().get(1));
    resumen.sort();
  }

  private void repartirMusicos() throws ValorIncorrectoExcepcion {
    cartasMusicos = Musicos.insertarMusicos();
    for (Jugador jugador : jugadores) {
      repartirPorTipo(jugador, "Voz");
      repartirPorTipo(jugador, "Guitarra");
      repartirPorTipo(jugador, "Bajo");
      repartirPorTipo(jugador, "Batería");
    }
  }

  private void repartirPorTipo(Jugador jugador, String tipo) throws ValorIncorrectoExcepcion {
    Musico carta;
    do {
      carta = cartasMusicos.get((int) (Math.random() * (cartasMusicos.size())));
    } while (!carta.getEjecucion().containsKey(tipo));
    jugador.getBanda().add(carta);
    carta.setInstrumentoUso(tipo);
    cartasMusicos.remove(carta);
  }

  private void escribirGuiMusicos() throws ValorIncorrectoExcepcion {
    int i = 0;
    for (Jugador jugador : jugadores) {
      // Para limpiar memoria... limpiamos el contenido del grid pane que vamos a
      // reemplazar.
      GridPane musicosDelJugador = guiMusicos.get(i);
      musicosDelJugador.getChildren().clear();

      int indiceDeStats = 0;
      for (Musico musico : jugador.getBanda()) {
        asignarImagenMusico(i, indiceDeStats, musico);
        indiceDeStats ++;
      }
      if (jugador.getBanda().size() == 4) {
        asignarImagenMusico(i, indiceDeStats, new Musico("Reverso"));
      }
      i++;
    }
  }

  private void asignarImagenMusico(int i, int indiceDeStats, Musico musico) {
    Label instrumento = null;
    if (indiceDeStats == 0) {
      instrumento = new Label("Voz");
    } else if (indiceDeStats == 1) {
      instrumento = new Label("Guitarra");
    } else if (indiceDeStats == 2) {
      instrumento = new Label("Bajo");
    } else if (indiceDeStats == 3) {
      instrumento = new Label("Batería");
    } else if (indiceDeStats == 4) {
      instrumento = new Label("2ºGuitarra/Teclado");
    }
    GridPane stats = nuevaGuiMusico(musico, 1);
    guiMusicos.get(i).add(stats, indiceDeStats, 0);
    guiMusicos.get(i).add(instrumento, indiceDeStats, 1);
  }

  private ImageView nuevaPortadaMusico(Musico musico, double multiplicador) {
    ImageView imagen = new ImageView(new Image(
      "file:src/metalmania/gui/imagenes/musicos/" + musico.getNombre().replaceAll("\\s", "").toLowerCase() + ".jpg"));
    imagen.setFitHeight(175*multiplicador);
    imagen.setFitWidth(128*multiplicador);
    if (musico.getEstado() == 2) {
      imagen.setEffect(new GaussianBlur(4));
    } else if (musico.getEstado() == 3) {
      ponerEnBlancoYNegro(imagen);
    }
    return imagen;
  }

  @SuppressWarnings("unused")
  private GridPane nuevaGuiMusico(Musico musico, double multiplicador) {
    GridPane stats = new GridPane();
    ImageView imagen = new ImageView();
    
    // CAMBIO aqui abajo hay un 5 pero antes habia un 6, es porque he comentado el case 5
    //FOR que rellena el lateral derecho
    for (int z = 0; z < 5; z++) {
      switch (z) {
      case 0:
        imagen = asignarImagenStats("ins" + musico.getInspiracion(), multiplicador);
        break;
      case 1:
        imagen = asignarImagenStats("car" + musico.getCarisma(), multiplicador);
        break;
      case 2:
        imagen = asignarImagenStats("mar" + musico.getMarcha(), multiplicador);
        break;
      case 3:
        imagen = asignarImagenStats("rep" + musico.getReputacion(), multiplicador);
        break;
      case 4:
        imagen = asignarImagenStats("est" + musico.getEstilo(), multiplicador);
        break;
      }
      if (musico.getNombre().compareTo("Reverso") == 0) {
        imagen.setStyle("-fx-opacity: 0.3;");
      }
      stats.add(imagen, 1, z);
      stats.setPadding(new Insets(20, 0, 20, 0));
      stats.setVgap(2);
      // ENDFOR
    }
    //Creamos la imagen de la portada (la carta) y la añadimos al grid
    ImageView portada = nuevaPortadaMusico(musico, multiplicador);
    stats.add(portada, 0, 0, 1, 5);
    
    //Creamos las imagenes de los instrumentos y se añaden al grid
    GridPane gridEjecucion = new GridPane();
    int index = 0;
    ImageView estaImagenSi = new ImageView();
    for (String key : musico.getEjecucion().keySet()) {
      try{
        if (key.compareTo("Guitarra") == 0) {
        estaImagenSi = asignarImagenStats("gui" + musico.getEjecucion().get(key), multiplicador);
        if (musico.getInstrumentoUso().compareTo("Guitarra") != 0) {
          ponerEnBlancoYNegro(estaImagenSi);
        };
      } else if (key.compareTo("Guitarra Eléctrica") == 0) {
        estaImagenSi = asignarImagenStats("guielec" + musico.getEjecucion().get(key), multiplicador);
        if (musico.getInstrumentoUso().compareTo("Guitarra Eléctrica") != 0) {
          ponerEnBlancoYNegro(estaImagenSi);
        };
      } else if (key.compareTo("Voz") == 0) {
        estaImagenSi = asignarImagenStats("voz" + musico.getEjecucion().get(key), multiplicador);
        if (musico.getInstrumentoUso().compareTo("Voz") != 0) {
          ponerEnBlancoYNegro(estaImagenSi);
        };
      } else if (key.compareTo("Bajo") == 0) {
        estaImagenSi = asignarImagenStats("baj" + musico.getEjecucion().get(key), multiplicador);
        if (musico.getInstrumentoUso().compareTo("Bajo") != 0) {
          ponerEnBlancoYNegro(estaImagenSi);
        };
      } else if (key.compareTo("Teclado") == 0) {
        estaImagenSi = asignarImagenStats("tec" + musico.getEjecucion().get(key), multiplicador);
        if (musico.getInstrumentoUso().compareTo("Teclado") != 0) {
          ponerEnBlancoYNegro(estaImagenSi);
        };
      } else if (key.compareTo("Batería") == 0) {
        estaImagenSi = asignarImagenStats("bat" + musico.getEjecucion().get(key), multiplicador);
        if (musico.getInstrumentoUso().compareTo("Batería") != 0) {
          ponerEnBlancoYNegro(estaImagenSi);
        }
      }
      gridEjecucion.add(estaImagenSi, index, 0);
      index++;
    }catch (Exception e) {
    }
    }
    gridEjecucion.setHgap(3);
    stats.add(gridEjecucion, 0, 5);
    stats.setAlignment(Pos.CENTER);
    stats.setHgap(5);
    stats.setVgap(3);
    
    
    return stats;
  }


  private void ponerEnBlancoYNegro(ImageView imagen) {
    ColorAdjust monochrome = new ColorAdjust();
    monochrome.setSaturation(-1);
    imagen.setEffect(monochrome);
  }

  private ImageView asignarImagenStats(String ruta, double multiplicador) {
    ImageView imagen;
    imagen = new ImageView(new Image("file:src/metalmania/gui/imagenes/statsMusicos/" + ruta + ".png"));
    imagen.setFitHeight(32*multiplicador);
    imagen.setFitWidth(32*multiplicador);
    return imagen;
  }

  private void escribirGuiAcciones(Jugador jugador, String accion) {
    int indice = jugadores.indexOf(jugador);
    String numero;
    if (accion.compareTo("actuacion") == 0) {
      numero = Integer.toString(jugador.getActuacion());
      actuaciones.get(indice)
          .setImage(new Image("file:src/metalmania/gui/imagenes/acciones/" + accion + numero + ".png"));
    } else if (accion.compareTo("grabacion") == 0) {
      numero = Integer.toString(jugador.getGrabacion());
//      actuaciones.get(indice).setImage(new Image("file:src/metalmania/gui/imagenes/acciones/"+accion+numero+".png"));
      System.out.println("Esto debería modificar la grabación " + numero);
    } else {
      numero = Integer.toString(jugador.getPublicidad());
//      actuaciones.get(indice).setImage(new Image("file:src/metalmania/gui/imagenes/acciones/"+accion+numero+".png"));
      System.out.println("Esto debería modificar la publicidad " + numero);
    }
  }

  // MARCA Métodos de desarrollo de la partida

  private void tiradaInicial() throws ValorIncorrectoExcepcion {
    mensajes.setText("¡¡Vamos a ver quien empieza!!\n Lancemos dos dados y que la suerte decida. \n");
    ArrayList<Jugador> ganador = tiranXJugadores(jugadores);
    // Ya hay un ganador, o varios, en su defecto...
    while (ganador.size() > 1) {
      mensajes.appendText("¡Ronda de desempate! \n");
      ganador = tiranXJugadores(ganador);
    }

    mensajes.appendText("El jugador que va a comenzar es: " + ganador.get(0).getNombre() + "\n");

    // MARCA Reordenamos el arraydeJugadores
    ArrayList<Jugador> auxiliar = new ArrayList<Jugador>();
    TabPane aux = new TabPane();
    ArrayList<Label> auxFondos = new ArrayList<Label>();
    ArrayList<Label> auxFama = new ArrayList<Label>();
    ArrayList<GridPane> auxGuiMusicos = new ArrayList<GridPane>();
    ArrayList<ImageView> auxActuaciones = new ArrayList<ImageView>();
    ArrayList<ToggleGroup> auxTogglesActuaciones = new ArrayList<ToggleGroup>();

    int j = 0;
    int indice = jugadores.indexOf(ganador.get(0));
    for (int i = 0; i < jugadores.size(); i++) {
      if ((indice + i) < jugadores.size()) {
        auxiliar.add(jugadores.get(indice + i));
        aux.getTabs().add(tabJugadores.getTabs().get(indice + i));
        auxFondos.add(informacionFondos.get(indice + i));
        auxFama.add(informacionFama.get(indice + i));
        auxGuiMusicos.add(guiMusicos.get(indice + i));
        auxActuaciones.add(actuaciones.get(indice + i));
        auxTogglesActuaciones.add(togglesActuaciones.get(indice+i));
      } else {
        auxiliar.add(jugadores.get(j));
        aux.getTabs().add(tabJugadores.getTabs().get(j));
        auxFondos.add(informacionFondos.get(j));
        auxFama.add(informacionFama.get(j));
        auxGuiMusicos.add(guiMusicos.get(j));
        auxActuaciones.add(actuaciones.get(j));
        auxTogglesActuaciones.add(togglesActuaciones.get(j));
        j++;
      }
    }
    jugadores.clear();
    tabJugadores.getTabs().clear();
    informacionFama.clear();
    informacionFondos.clear();
    guiMusicos.clear();
    actuaciones.clear();
    togglesActuaciones.clear();
    for (int i = 0; i < auxiliar.size(); i++) {
      jugadores.add(auxiliar.get(i));
      tabJugadores.getTabs().add(aux.getTabs().get(i));
      informacionFama.add(auxFama.get(i));
      informacionFondos.add(auxFondos.get(i));
      guiMusicos.add(auxGuiMusicos.get(i));
      actuaciones.add(auxActuaciones.get(i));
      togglesActuaciones.add(auxTogglesActuaciones.get(i));
    }
    for (ToggleGroup toggles : togglesActuaciones) {
      int i = 0;
      for (Toggle jugador : toggles.getToggles()) {
        RadioButton boton = (RadioButton) jugador;
        if (i < jugadores.size()) {
          boton.setText(jugadores.get(i).getNombre());
        } else {
          boton.setVisible(false);
        }
        i++;
      }
    }
  }

  private boolean inicioTurno(Jugador jugador) throws ValorIncorrectoExcepcion, InterruptedException {
    mostrarTabJugador(indicadorJugador);
    mensajes.setText("Es el turno de " + jugador.getNombre());
    jugador.setActuacion(jugador.getNivelActuacion());
    escribirGuiAcciones(jugador, "actuacion");
    jugador.setGrabacion(jugador.getNivelGrabacion());
    //escribirGuiAcciones(jugador, "grabacion");
    jugador.setPublicidad(jugador.getNivelPublicidad());
    //escribirGuiAcciones(jugador, "publicidad");
    jugador.setSinPagar(false);
    setDadosATirar(jugador, 2);
    dialogoTirar(jugador);
    evento(jugador, dados.get(0).getCara() + dados.get(1).getCara());

    return jugador.getFama() >= 60;
  }

  private void mostrarTabJugador(int indicador) {
    tabJugadores.getSelectionModel().select(indicador);
    TabPane subPane = (TabPane) tabJugadores.getTabs().get(indicador).getContent();
    subPane.getSelectionModel().select(0);
  }

  private void evento(Jugador jugador, int i) {
    resultadoEvento = i - 2;
    comenzarPartida.setDisable(true);
    botonesEvento(false);
    listadoEventos.get(resultadoEvento).setStyle("-fx-background-color: gold");
  }

  public void accionFama(ActionEvent e) {
    try {
      if (e.getSource().equals(bactuacion)) {
        accionARealizar = "actuacion";
      } else if (e.getSource().equals(bgrabacion)) {
        accionARealizar = "grabacion";
      } else if (e.getSource().equals(bpublicidad)) {
        accionARealizar = "publicidad";
      }
      mensajes.appendText("\nHas elegido " + accionARealizar+"\n\n");
      jugadores.get(indicadorJugador).setDadosATirar(jugadores.get(indicadorJugador).getBanda().size());
      dialogoTirar(jugadores.get(indicadorJugador));
      for (int i = 0; i < combos.size(); i++) {
        //combos.get(i).getItems().clear();
        if (i < jugadores.get(indicadorJugador).getBanda().size()) {
          for (Musico musico : jugadores.get(indicadorJugador).getBanda()) {
            combos.get(i).getItems().add(musico.getNombre());
          }
          combos.get(i).setDisable(false);
        } else {
          combos.get(i).setDisable(true);
        }
      }
      botonesFama(true);
      comenzarPartida.setDisable(false);
      estamosEnAccionFama = true;
    } catch (ValorIncorrectoExcepcion e1) {
      e1.printStackTrace();
    }
  }



  // MARCA Métodos Auxiliares
  private ArrayList<Jugador> tiranXJugadores(ArrayList<Jugador> ganador) {
    ArrayList<Jugador> aux = new ArrayList<Jugador>();
    int max = 0;
    for (Jugador jugador : ganador) {
      setDadosATirar(jugador, 2);

      dialogoTirar(jugador);
      int suma = dados.get(0).getCara() + dados.get(1).getCara();
      mensajes.appendText(jugador.getNombre() + " ha sacado: " + (suma) + "\n");
      if (suma > max) {
        max = suma;
        aux.clear();
        aux.add(jugador);
      } else if (suma == max) {
        aux.add(jugador);
      }
    }
    return aux;
  }

  public void llamarEvento(ActionEvent e) throws ValorIncorrectoExcepcion {
    estamosEnAccionAudicion = false; //TODO
    Jugador jugador = jugadores.get(indicadorJugador);
    botonesEvento(true);
    mensajes.appendText("\nHa seleccionado el evento " + (resultadoEvento + 2));
    switch (resultadoEvento + 2) {
    case 2: // Todos pierden 2$ menos el jugador que gana 6.
      for (Jugador jugadoresEnTurno : jugadores) {
        if (jugadoresEnTurno != jugador) {
          try {
            jugadoresEnTurno.pagar(2);
          } catch (ValorIncorrectoExcepcion e1) {
            jugadoresEnTurno.setDinero(0);
          }
        } else {
          jugador.pagar(-6);
        }
      }
      mensajes.appendText("\nUff, eso ha dolido, ¡todos han perdido 2$ y tu ganas 6$!");
      break;
    case 3: // Ganas 4 dólares
      jugador.pagar(-4);
      mensajes.appendText("\nQue buena inversión, has ganado 4$.");
      break;
    case 4: // escandalo(jugadorQueTuQuieras); // El jugador que elijas pierde 4 de fama.
      try {
        seleccionarJugador("perder 4 de fama.");
        jugadores.get(indiceElegido).aumentarFama(-4);
      } catch (ValorIncorrectoExcepcion e2) {
        jugadores.get(indiceElegido).setFama(0);
      }
      mensajes.appendText(
          "\n¿Te caía mal? " + jugadores.get(indiceElegido).getNombre() + " te está mirando con mala cara.");
      break;
    case 5: // El jugador gana 2 de fama.setFama(jugador.getFama() + 2);
      jugador.aumentarFama(2);
      mensajes.appendText("\nEstás más cerca de llegar a la cima,\n¡2 puntos de fama siempre vienen bien!");
      break;
    case 6:
      // gafado(); // Pasas a conflictivo a un músico de otro jugador
      seleccionarMusico("pasar a conflictivo.", jugadores);
      if (jugadores.get(indiceElegido).getBanda().get(indiceMusicoElegido).getEstado() == 2) {
        mensajes.appendText("\n"+jugadores.get(indiceElegido).getBanda().get(indiceMusicoElegido).getNombre()
            + " ya estaba conflictivo.");
      } else {
        jugadores.get(indiceElegido).getBanda().get(indiceMusicoElegido).estadoConflictivo();
        escribirGuiMusicos();
        mensajes.appendText("\nParece que " + jugadores.get(indiceElegido).getBanda().get(indiceMusicoElegido).getNombre()
            + " está conflictivo.");
      }
      break;
    case 7:
      accionAudicion(e); // CAMBIO D: Llamada al dialog desde el evento
      escribirGuiMusicos();
      break;
    case 8: // laVidaEsBella(jugador); // Pasa a activo uno de tus músicos.
      seleccionarMusico("pasar a activo.", new ArrayList<Jugador>(Arrays.asList(jugador)));
      if (jugador.getBanda().get(indiceMusicoElegido).getEstado()==1) {
        mensajes.appendText(
            "\n" + jugador.getBanda().get(indiceMusicoElegido).getNombre() + " ya estaba activo, ¡continuamos!");
      } else {
        jugador.getBanda().get(indiceMusicoElegido).estadoActivo();
        escribirGuiMusicos();
        mensajes.appendText(
            "\n" + jugador.getBanda().get(indiceMusicoElegido).getNombre() + " está dispuesto a darlo todo de nuevo.");
      }
      break;
    case 9: // deudasDeJuego(); // Otro jugador pierde 4 dólares
      try {
        seleccionarJugador("perder 4$.");
        jugadores.get(indiceElegido).pagar(4);
      } catch (ValorIncorrectoExcepcion e2) {
        jugadores.get(indiceElegido).setDinero(0);
      }
      mensajes.appendText(
          "\n¿Te caía mal? " + jugadores.get(indiceElegido).getNombre() + " te está mirando con mala cara.");
      break;
    case 10: // Ganas 2$
      jugador.pagar(-2);
      mensajes.appendText("\nEstos 2$ te pueden ayudar en tu economía.");
      break;
    case 11:
      // juegoDeBirras(jugador); // Eliges a otro jugador para irte de birras con él.
      mensajes.appendText("\nVaya noche de birras nos espera... \nvamos amigo, y bebe conmigo...");
      seleccionarJugador("ir juntos de birras.");
      nosVamosDeBirras(new ArrayList<Jugador>(Arrays.asList(jugador, jugadores.get(indiceElegido))));
      break;
    case 12:
      mensajes.appendText("\nQue tiemblen los bares... vamos a darlo todo.");
      // ¿Enviarlos ordenados para que empiece "jugador"?
      nosVamosDeBirras(jugadores);
      break;
    }
    escribirInfo();
    if (!alguienDeBirras && !estamosEnAccionAudicion) {
      terminaEventoEmpiezaFama();
    } else if (alguienDeBirras) {
      comenzarPartida.setDisable(false);
    } else {
      
    }
    if (!estamosEnAccionAudicion) {
      for (Button evento : listadoEventos) {
        evento.setStyle("");
      }
    }
  }

  private void nosVamosDeBirras(ArrayList<Jugador> jugadores) {
    indiceDeBirras = 0;
    alguienDeBirras = true;
    musicosDeBirras = new ArrayList<Musico>();
    jugadoresDeBirras = jugadores;
    for (Jugador jugador : jugadores) {
      for (Musico musico : jugador.getBanda()) {
        musicosDeBirras.add(musico);
      }
    }
    // El primer músico se va de Birras
    mensajes.appendText("\n¡" + musicosDeBirras.get(indiceDeBirras).getNombre() + " empieza la noche!");
    vaDeBirras(musicosDeBirras.get(indiceDeBirras));

  }

  private void vaDeBirras(Musico musico) {
    Jugador jugador = buscarJugadorPropietario(musico);
    setDadosATirar(jugador, musico.getMarcha());
    mostrarTabJugador(jugadores.indexOf(jugador));
    dialogoTirar(jugador);
    comenzarPartida.setDisable(false);

  }

  private Jugador buscarJugadorPropietario(Musico musico) {
    for (Jugador jugador : jugadoresDeBirras) {
      for (Musico musicoParaBuscar : jugador.getBanda()) {
        if (musico.equals(musicoParaBuscar)) {
          return jugador;
        }
      }
    }
    return null;
  }

  private boolean comprobarComboBox() {
    for (int i = 0; i < jugadores.get(indicadorJugador).getBanda().size(); i++) {
      if (combos.get(i).getValue() == null) {
        mensajeAlerta("El dado " + (i + 1) + " no ha sido asignado a ninguna carta");
        return false;
      }
      for (int j = 0; j < jugadores.get(indicadorJugador).getBanda().size(); j++) {
        if (combos.get(i).getValue() == combos.get(j).getValue() && combos.get(i) != combos.get(j)) {
          mensajeAlerta("Coinciden los valores de los dados " + (i + 1) + " y " + (j + 1));
          return false;
        }
      }

    }
    return true;
  }

  /**
   * Este método se utiliza para dejar habilitados sólo el número de "dados"
   * usados. Utilizado como envoltorio para Jugador.setDadosATirar();
   */
  private void setDadosATirar(Jugador jugador, int numeroDados) {
    try {
      jugador.setDadosATirar(numeroDados);
      for (Button dado : botonesDados) {
        if (botonesDados.indexOf(dado) >= numeroDados)
          dado.setDisable(true);
      }
    } catch (ValorIncorrectoExcepcion e) {
      e.printStackTrace();
    }
  }
  
  private void botonesFama(boolean desactivar) {
    bactuacion.setDisable(desactivar);
    bpublicidad.setDisable(desactivar);
    bgrabacion.setDisable(desactivar);
    if (desactivar) {
      fondofama.setStyle("");
    } else {
      fondofama.setStyle("-fx-background-color: gold");
    }
    
  }
  
  private void botonesMantenimiento(boolean desactivar) {
    baudicion.setDisable(desactivar);
    bfondos.setDisable(desactivar);
    bbirras.setDisable(desactivar);
    if (desactivar) {
      fondomantenimiento.setStyle("");
    } else {
      fondomantenimiento.setStyle("-fx-background-color: gold");
    }
  }
  
  private void botonesEvento(boolean desactivar) {
    eventoSumar.setDisable(desactivar);
    eventoRestar.setDisable(desactivar);
    eventoOK.setDisable(desactivar);
    if (desactivar) {
      fondoevento.setStyle("");
    } else {
      fondoevento.setStyle("-fx-background-color: gold");
    }
  }
  
  // TODO
  public void actuacionSumar (ActionEvent e) {
    usarPoderActuacion(1);
  }
  public void actuacionRestar (ActionEvent e) {
    usarPoderActuacion(-1);
  }
  
  public void grabacionLanzar (ActionEvent e) {
    
  }


  private void usarPoderActuacion(int valor) {
    try{
      int indice = buscarJugadorTabSelec();
      if (jugadores.get(indice).getActuacion() <= 0) {
        mensajes.appendText("\n¡No tienes puntos disponibles para usar este poder!");
      }else {
        RadioButton boton = (RadioButton) togglesActuaciones.get(indice).getSelectedToggle();
        for (Jugador jugador : jugadores) {
          if (jugador.getNombre().compareTo(boton.getText()) == 0) {
            jugador.aumentarFama(valor);
            jugadores.get(indice).setActuacion(jugadores.get(indice).getActuacion()-1);
            if (jugadores.get(indice).equals(jugador)) {
              mensajes.appendText(jugador.getNombre() + " ha usado su poder de actuación para quedarse con " +
            jugador.getFama() + " puntos de Fama.");
            } else {
              mensajes.appendText(jugadores.get(indice).getNombre() + " ha usado su poder de actuación para dejar a "+
             jugador.getNombre() + " con " +
                  jugador.getFama() + " puntos de Fama.");
            }
          }
        }
        
        escribirInfo();
        escribirGuiAcciones(jugadores.get(indice), "actuacion");
      }
    } catch (Exception error) {
    }
  }
  
  private int buscarJugadorTabSelec () {
    int indice=0;
    for (Tab jugador : tabJugadores.getTabs() ) {
      if (jugador.isSelected()) {
        return indice; 
      }
      indice++;
    }
    return -1;
  }

  // Estos métodos son de control durante el desarrollo
  private void mostrarBandas() {
    for (Jugador jugador : jugadores) {
      System.out.println(jugador.getBanda().toString());
    }
  }
  
  private void mostrarMusicosAudicion() {
    for (Musico musico : musicosAudicion) {
      System.out.println(musico.getNombre());
    }
  }
 

  // MARCA Diseño de Dialogs
  private Optional<ButtonType> dialogoTirar(Jugador jugador) {
    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setTitle("Tirada de dados");
    dialog.setHeaderText("Es el turno de " + jugador.getNombre());

    // Poner los botones que queramos
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
    dialog.getDialogPane().lookupButton(ButtonType.OK).setDisable(true);
    // Create los fields y labels
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(20, 150, 10, 10));

    ArrayList<Button> botonesDados2 = new ArrayList<Button>();

    for (int i = 0; i < jugador.getDadosATirar(); i++) {
      Button dado = new Button("1");
      dado.setPrefHeight(45);
      dado.setPrefWidth(45);
      botonesDados2.add(dado);
      grid.add(botonesDados2.get(i), i, 0);
    }
    Button tirar = new Button("Tirar");
    grid.add(tirar, 0, 1);
    tirar.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent e) {
        for (int i = 0; i < jugador.getDadosATirar(); i++) {
          botonesDados2.get(i).setDisable(false);
          botonesDados2.get(i).setText(Integer.toString(dados.get(i).tirada()));
        }
        tirar.setDisable(true);
        dialog.getDialogPane().lookupButton(ButtonType.OK).setDisable(false);
      }
    });

    dialog.getDialogPane().setContent(grid);

    Optional<ButtonType> result = dialog.showAndWait();
    if (result.get() == ButtonType.OK) {
      for (int i = 0; i < jugador.getDadosATirar(); i++) {
        botonesDados.get(i).setText(Integer.toString(dados.get(i).getCara()));
        botonesDados.get(i).setDisable(false);
      }
    }
    return result;
  }

  private int seleccionarJugador(String evento) {
    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setTitle("Selecciona un jugador");
    dialog.setHeaderText("Selecciona al jugador para " + evento);

    // Poner los botones que queramos
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
    dialog.getDialogPane().lookupButton(ButtonType.OK).setDisable(true);

    // Create los fields y labels
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(20, 150, 10, 10));

    ChoiceBox<String> jugadoresAElegir = new ChoiceBox<String>();
    for (Jugador jugador : jugadores) {
      if (jugador != jugadores.get(indicadorJugador)) {
        jugadoresAElegir.getItems().add(jugador.getNombre());
      }
    }

    grid.add(jugadoresAElegir, 0, 0);
    Button seleccionar = new Button("Seleccionar");
    grid.add(seleccionar, 0, 1);
    seleccionar.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent e) {
        try {
          for (Jugador jugador : jugadores) {
            if (jugadoresAElegir.getValue().compareTo(jugador.getNombre()) == 0) {
              indiceElegido = jugadores.indexOf(jugador);
              jugadoresAElegir.setDisable(true);
            }
          }
          dialog.getDialogPane().lookupButton(ButtonType.OK).setDisable(false);
        } catch (Exception e2) {
        }

      }
    });
    dialog.getDialogPane().setContent(grid);
    dialog.showAndWait();

    return indiceElegido;
  }

  private ArrayList<Integer> seleccionarMusico(String evento, ArrayList<Jugador> jugadoresRecibidos) {
    GridPane portadasMusico = new GridPane();
    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setTitle("Selecciona un músico");
    dialog.setHeaderText("Selecciona al Músico para " + evento);
    dialog.setResizable(true);

    // Poner los botones que queramos
//    dialog.getDialogPane().getButtonTypes().set(0, new ButtonType("OK", ButtonBar.ButtonData.LEFT));
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
    dialog.getDialogPane().lookupButton(ButtonType.OK).setDisable(true);

    // Create los fields y labels
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(20, 150, 10, 10));

    ChoiceBox<String> musicosAElegir = new ChoiceBox<String>();
    Button seleccionar = new Button("Seleccionar");
    seleccionar.setDisable(true);

    if (jugadoresRecibidos.size() > 1) {
      int indicePortadas = 0;
      try {
        for (Musico musico : Arrays.asList(new Musico("Reverso"),new Musico("Reverso"),new Musico("Reverso"),new Musico("Reverso"),new Musico("Reverso"))) {
          portadasMusico.add(nuevaGuiMusico(musico, 0.8), indicePortadas, 0);
          indicePortadas++;
        }
        grid.add(portadasMusico, 0, 1, 3, 1);
      } catch (ValorIncorrectoExcepcion e1) {
        e1.printStackTrace();
      }
      ChoiceBox<String> jugadoresAElegir = new ChoiceBox<String>();
      for (Jugador jugador : jugadores) {
        if (jugador != jugadores.get(indicadorJugador)) {
          jugadoresAElegir.getItems().add(jugador.getNombre());
        }
      }
      grid.add(jugadoresAElegir, 0, 0);

      // Función al pulsar sobre el botón buscar
      jugadoresAElegir.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent e) {
          try {
            for (Jugador jugador : jugadores) {
              if (jugadoresAElegir.getValue().compareTo(jugador.getNombre()) == 0) {
                indiceElegido = jugadores.indexOf(jugador);
              }
            }
            musicosAElegir.getItems().clear();
            portadasMusico.getChildren().clear();
            rellenarDialogMusicos(portadasMusico, musicosAElegir);
            
            seleccionar.setDisable(false);
          } catch (Exception e2) {
          }

        }
      });
    } else {
      indiceElegido = indicadorJugador;
      rellenarDialogMusicos(portadasMusico, musicosAElegir);
      grid.add(portadasMusico, 0, 1, 3, 1);
      seleccionar.setDisable(false);
    }
    grid.add(musicosAElegir, 1, 0);
    grid.add(seleccionar, 2, 0);
 
    // Función al pulsar sobre el botón seleccionar
    seleccionar.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent e) {
        try {
          for (Musico musico : jugadores.get(indiceElegido).getBanda()) {
            if (musico.getNombre().compareTo(musicosAElegir.getValue()) == 0) {
              indiceMusicoElegido = jugadores.get(indiceElegido).getBanda().indexOf(musico);
            }
          }
          seleccionar.setDisable(true);
          dialog.getDialogPane().lookupButton(ButtonType.OK).setDisable(false);
        } catch (Exception e2) {
        }

      }
    });

    dialog.getDialogPane().setContent(grid);
    dialog.showAndWait();
    return new ArrayList<Integer>(Arrays.asList(indiceElegido, indiceMusicoElegido));
  }


  private void rellenarDialogMusicos(GridPane portadasMusico, ChoiceBox<String> musicosAElegir) {
    int indicePortadas = 0;
    for (Musico musico : jugadores.get(indiceElegido).getBanda()) {
      musicosAElegir.getItems().add(musico.getNombre());
      portadasMusico.add(nuevaGuiMusico(musico, 0.8), indicePortadas, 0);
      indicePortadas++;
    }
    if (jugadores.get(indiceElegido).getBanda().size() < 5) {
      try {
        portadasMusico.add(nuevaGuiMusico(new Musico("Reverso"), 0.8), indicePortadas, 0);
      } catch (ValorIncorrectoExcepcion e) {
        e.printStackTrace();
      }
      
    }
  }
  
  private void noHayFondos() {
    mostrarTabJugador(indicadorJugador);
    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setTitle("No hay dinero para todos");
    int musicosSinCobrar = jugadores.get(indicadorJugador).getBanda().size()-jugadores.get(indicadorJugador).getDinero();
    if (musicosSinCobrar == 1) {
      dialog.setHeaderText("Selecciona al músico que no cobrará");
    } else {
      dialog.setHeaderText("Selecciona los " + musicosSinCobrar + " músicos que no cobrarán");
    }
    
    // Poner los botones que queramos
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
    dialog.getDialogPane().lookupButton(ButtonType.OK).setDisable(true);

    // Create los fields y labels
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(20, 150, 10, 10));
    ArrayList<ChoiceBox<String>> musicosAElegir = new ArrayList<ChoiceBox<String>>();
    for (int i=0; i < musicosSinCobrar; i++) {
      musicosAElegir.add(new ChoiceBox<String>());
      for (Musico musico : jugadores.get(indicadorJugador).getBanda()) {
        musicosAElegir.get(i).getItems().add(musico.getNombre());
      }
      grid.add(musicosAElegir.get(i), (i+1), 0);
    }
    Button seleccionar = new Button("Seleccionar");
    grid.add(seleccionar, 1, 1);

    // Función al pulsar sobre el botón seleccionar
    seleccionar.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent e) {
        try {
          boolean camposLlenos = true;
          boolean camposSinRepetir = true;
          // CAMBIO D: AÑadidos BREAK a cada IF para que solo te salte un mensaje
          for (int i = 0; i < musicosSinCobrar; i++) {
            if (musicosAElegir.get(i).getValue() == null) {
              mensajeAlerta("Faltan valores por añadir");
              camposLlenos = false;
              break;
            }
            for (int j = 0; j < musicosSinCobrar; j++) {
              if (musicosAElegir.get(i).getValue() == musicosAElegir.get(j).getValue() && musicosAElegir.get(i) != musicosAElegir.get(j)) {
                mensajeAlerta("Coinciden los valores de los músicos " + (i + 1) + " y " + (j + 1));
                camposSinRepetir = false;
                break;
              }
            }
          }
          if (camposLlenos && camposSinRepetir) {
            for (ChoiceBox<String> musicoElegido : musicosAElegir) {
              for (Musico musico : jugadores.get(indicadorJugador).getBanda()) {
                if (musicoElegido.getValue().toString().compareTo(musico.getNombre())==0) {
                  
                  if (musico.modificarEstado(1)) {
                    mensajes.appendText("\n"+musico.getNombre()+" no ha cobrado y cambia su estado a " + musico.getEstadoString());
                    escribirGuiMusicos();
                  } else {
                    mensajes.appendText("\n"+musico.getNombre()+" no ha cobrado y continúa inactivo.");
                    escribirGuiMusicos();
                  }
                }
              }
            }
            
            seleccionar.setDisable(true);
            dialog.getDialogPane().lookupButton(ButtonType.OK).setDisable(false);
          }
        } catch (Exception e2) {
          e2.printStackTrace();
        }
      }
    });

    dialog.getDialogPane().setContent(grid);
    dialog.showAndWait();
    
  }
  
  
  public Optional<ButtonType> jugadorDecideAudicion() {
    estamosEnAccionAudicion = true;
    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setTitle("Metalmanía. Audición");
    dialog.setHeaderText("Músicos disponibles para la audición");

    // Poner los botones que queramos
    ButtonType pasarTurno = new ButtonType("Pasar Turno");
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, pasarTurno, ButtonType.CANCEL);
    dialog.getDialogPane().lookupButton(ButtonType.OK).setDisable(true);

    // Create los fields y labels
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(20, 150, 10, 10));
    Label musicosDisponibles = new Label("Músicos disponibles");
    grid.add(musicosDisponibles, 0, 0, 3, 1);
    
    GridPane botonesMusicosAContratar = new GridPane();
    // Información
    Label coste = new Label("Coste");
    coste.setFont(new Font("Impact", 20));
    Label fama = new Label("Fama");
    fama.setFont(new Font("Impact", 20));
    
    VBox contenedor = new VBox(coste, fama);
    contenedor.setSpacing(20);
    contenedor.setAlignment(Pos.CENTER);
    int indiceFila = 0;
    int indiceColumna = 0;
    ArrayList<ToggleButton> botonesAudicion = new ArrayList<ToggleButton>();
    ArrayList<ToggleButton> botonesTusMusicos = new ArrayList<ToggleButton>();
    
    for (Musico musico : musicosAudicion) {
      ToggleButton botonMusico = creaBotonMusico(musico);
      if (musico.getReputacion() > jugadores.get(indicadorJugador).getDinero()) botonMusico.setDisable(true);
      botonesAudicion.add(botonMusico);
      //CAMBIO D: Acción que realiza cada botón de los músicos a contratar
      botonMusico.setOnAction(new EventHandler<ActionEvent>() {


        @Override
        public void handle(ActionEvent e) {
          try {
            dialog.getDialogPane().lookupButton(ButtonType.OK).setDisable(true);
            int i = 0;
            int indiceMusicoAHabilitar = 0;
            musicoAContratar = new Musico("Reverso");
            //
            fama.setText("Fama");
            // Deshabilitar el grid con tus músicos.
            for (ToggleButton boton : botonesTusMusicos) {
              boton.setDisable(true);
              boton.setStyle("");
              boton.setSelected(false);
            }
            // Escoger un botón y deseleccionar el resto
            for (ToggleButton boton : botonesAudicion) {
              if (boton.equals(e.getSource())) {
                boton.setStyle("-fx-background-color: gold");
                musicoAContratar = musicosAudicion.get(i);
              } else {
                boton.setStyle("");
                boton.setSelected(false);
              }
              i++;
            }
            for (String instrumento : musicoAContratar.getEjecucion().keySet()) { 
              indiceMusicoAHabilitar =0;
              for (Musico musico : jugadores.get(indicadorJugador).getBanda()) {
                if (musico.getInstrumentoUso().compareTo(instrumento)==0) {
                  botonesTusMusicos.get(indiceMusicoAHabilitar).setDisable(false);
                }
                indiceMusicoAHabilitar++;
              }
              if (instrumento.compareTo("Guitarra Eléctrica") == 0 || instrumento.compareTo("Teclado") == 0) { 
                botonesTusMusicos.get(4).setDisable(false);
              }
            }
            coste.setText("Coste: "+musicoAContratar.getReputacion() + " $.");
          } catch (ValorIncorrectoExcepcion e1) {
            e1.printStackTrace();
          }
        }
      });
      
      botonesMusicosAContratar.add(botonMusico, indiceColumna, indiceFila);
      if (indiceFila == 2) {
        indiceFila = 0;
        indiceColumna++;
      } else {
        indiceFila++;
      }
    }
    grid.add(botonesMusicosAContratar, 0,0);
    
    
    //CAMBIO GRID DE TUS PROPIOS JUGADORES
    GridPane gridTusMusicos = new GridPane();
    int indiceFila2 = 0;
    int indiceColumna2 = 0;
    for (Musico musico : jugadores.get(indicadorJugador).getBanda()) {
      ToggleButton botonMusico = crearBotonTusMusicos(botonesTusMusicos, gridTusMusicos, musico, fama, dialog);
      gridTusMusicos.add(botonMusico, indiceColumna2, indiceFila2);
      if (indiceColumna2 == 1) {
        indiceColumna2 = 0;
        indiceFila2++;
      } else {
        indiceColumna2++;
      }
    }
    if (jugadores.get(indicadorJugador).getBanda().size() < 5) {
      try {
      ToggleButton botonMusico = crearBotonTusMusicos(botonesTusMusicos, gridTusMusicos, new Musico("Reverso"), fama, dialog);
      gridTusMusicos.add(botonMusico, 0, 2);
      } catch (ValorIncorrectoExcepcion e2) {
        e2.printStackTrace();
      }
    }
    gridTusMusicos.add(contenedor, 1, 2);
    grid.add(gridTusMusicos, 1, 0);
    
    dialog.getDialogPane().setContent(grid);
    Optional<ButtonType> audicion = dialog.showAndWait();
    if (audicion.get() == pasarTurno) {
      estamosEnAccionAudicion = false;
      if (estamosEnAccionMantenimiento) {
        mensajes.appendText("\nHas pasado turno.");
        
      } else {
        mensajes.appendText("\nHas pasado turno, continuamos con la Acción Fama");
        //Deshabilitar los tres botones de evento
        botonesEvento(true);
        eventos7.setStyle("");
      }
    } else if (audicion.get() == ButtonType.OK) {
      try {
        estamosEnAccionAudicion = false;
        // Levamos al músico despedido al ArrayList de Descartes y cambiamos en la banda por el nuevo.
        int indiceEnBanda;
        int famaGanada = 0;
        int costeContratacion = 0;
        if (musicoADespedir.getNombre().compareTo("Reverso") != 0) {
          famaGanada = musicoADespedir.getReputacion();
          costeContratacion = musicoAContratar.getReputacion();
          cartasDescartes.add(musicoADespedir);
          indiceEnBanda = jugadores.get(indicadorJugador).getBanda().indexOf(musicoADespedir);
          jugadores.get(indicadorJugador).getBanda().remove(musicoADespedir);
          musicoAContratar.setInstrumentoUso(musicoADespedir.getInstrumentoUso());
          mensajes.appendText("\n\nHa habido cambios en la banda, "+ musicoADespedir.getNombre() +
              " deja el grupo ante un revuelo en la prensa que genera un aumento de fama de " +
              famaGanada + " y en su lugar, entra " + musicoAContratar.getNombre() +
              " con un coste de " + costeContratacion + "$.");
          
        } else {
          indiceEnBanda = 4;
          for (String instrumento : musicoAContratar.getEjecucion().keySet()) {
            if ((instrumento.compareTo("Guitarra Eléctrica") == 0) || (instrumento.compareTo("Teclado") == 0)) {
              musicoAContratar.setInstrumentoUso(instrumento);
              costeContratacion = musicoAContratar.getReputacion();
              break;
            }
          }
          mensajes.appendText("\n\nAumenta el tamaño de la banda con la contratación de " + 
          musicoAContratar.getNombre() + " con un coste de " + costeContratacion + "$.");
        }
        jugadores.get(indicadorJugador).getBanda().add(indiceEnBanda, musicoAContratar);
        musicosAudicion.remove(musicoAContratar);
        
        Musico carta;
        carta = cartasMusicos.get((int) (Math.random() * (cartasMusicos.size())));
        musicosAudicion.add(carta);
        cartasMusicos.remove(carta);
        jugadores.get(indicadorJugador).aumentarFama(famaGanada);
        jugadores.get(indicadorJugador).pagar(costeContratacion);
        
      } catch (ValorIncorrectoExcepcion e) {
        e.printStackTrace();
      }
      
      if (estamosEnAccionMantenimiento) {
        mensajes.appendText("\nHas completado la acción mantenimiento de audición.");
        botonesMantenimiento(true);
        
      } else {
        mensajes.appendText("\nHas completado el evento de audición.");
        estamosEnAccionAudicion = false; //TODO
        // SI LA AUDICION DEL EVENTO NO SE COMPLETA POSIBLEMENTE HAYA QUE "COMPLETARLA" AQUI
      }
    } else {
      // musicoADesPedir = null;
      // musicoAContratar = null;
      if (estamosEnAccionMantenimiento) {
        mensajes.appendText("\nHas cancelado la acción de mantenimiento para audición, vuelve a seleccionar esta u otra.");
        
      } else {
        mensajes.appendText("\nHas cancelado el evento para audición, vuelve a escoger este u otro.");
        //Habilitar los tres botones de evento
        botonesEvento(false);
        eventos7.setStyle("-fx-background-color: gold");
      }
    }
    escribirInfo();
    return audicion;
  }


  private ToggleButton crearBotonTusMusicos(ArrayList<ToggleButton> botonesTusMusicos, GridPane gridTusMusicos,
      Musico musico, Label fama,  Dialog<ButtonType> dialog) {
    ToggleButton botonMusico;
      botonMusico = creaBotonMusico(musico);
    botonMusico.setDisable(true);
    botonesTusMusicos.add(botonMusico);
    // Acción que realiza cada botón de tus músicos
    
    botonMusico.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent e) { //TODO funcionalidad de los botones de tus jugadores
        int i = 0;
        musicoADespedir = musico;
        dialog.getDialogPane().lookupButton(ButtonType.OK).setDisable(false);
        // Escoger un botón y deseleccionar el resto
        for (ToggleButton boton : botonesTusMusicos) {
          if (boton.equals(e.getSource())) {
            boton.setStyle("-fx-background-color: gold");
            try{
              musicoADespedir = jugadores.get(indicadorJugador).getBanda().get(i);
            } catch (IndexOutOfBoundsException error) {
              try {
                musicoADespedir = new Musico("Reverso");
              } catch (ValorIncorrectoExcepcion e1) {
                e1.printStackTrace();
              }
            }
          } else {
            boton.setStyle("");
            boton.setSelected(false);
          }
          i++;
        }
        if (musicoADespedir.getNombre().compareTo("Reverso")==0) {
          fama.setText("Fama: 0.");
        }else {
          fama.setText("Fama: +"+musicoADespedir.getReputacion() + ".");
        }
      }
    });
    return botonMusico;
  }

  private ToggleButton creaBotonMusico(Musico musico) {
    ToggleButton botonMusico = new ToggleButton();
    GridPane datosBotonMusico = new GridPane();
    datosBotonMusico.add(nuevaGuiMusico(musico, 0.8), 1, 0);
    botonMusico.setGraphic(datosBotonMusico);
    return botonMusico;
  }


  private void mensajeAlerta(String mensaje) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setHeaderText(null);
    alert.setTitle("¡Atención!");
    alert.setContentText(mensaje);
    alert.showAndWait();
  }
  
  // MARCA Otros escenarios
  
//  public void verMusicosAudicion() {
//    try {
//      FXMLLoader fxml = new FXMLLoader(this.getClass().getResource("musicosAudicion.fxml"));
//      VBox root = fxml.<VBox>load();
//      Scene audicion = new Scene(root);
//      newStage.setScene(audicion);
//      newStage.setTitle("Metalmania. Músicos disponibles para la audición"); // nombre de la clase
//      newStage.setResizable(false);
//      newStage.showAndWait();
//    } catch (Exception e) {
//      System.out.println(e.getMessage());
//      e.printStackTrace();
//    }
//  }
  
  // TODO Cosas a tener en cuenta

  // - Estamos haciendo la función: jugadorDecideAudicion()
  // - Crear un ENUM con los instrumentos para utilizarlo en los métodos en los que comprobamos si es un instrumento u otro.
  
  // TODO Cosas a preguntar a Rafa
  
  // - Función seleccionarMusico (el botón aceptar, para colocarlo en la fila tres de forma centrada).
}
