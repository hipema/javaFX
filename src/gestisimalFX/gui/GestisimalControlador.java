package gestisimalFX.gui;

import java.io.IOException;

/**
 * Controlador-vista para los eventos de NúmerosPrimos.
 * 
 */

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.xml.transform.TransformerException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import gestisimalFX.excepciones.ArticuloIncorrectoException;
import gestisimalFX.excepciones.PrecioNegativoException;
import gestisimalFX.excepciones.StockIncorrectoException;
import gestisimalFX.negocio.Almacen;
import gestisimalFX.negocio.Articulo;
import gestisimalFX.utilidades.UtilidadesControlador;

public class GestisimalControlador implements Initializable {
  private static Almacen tienda = new Almacen();
  
  @FXML
  private TextField codigoAlta, descripcionAlta, precioCompraAlta, precioVentaAlta, stockAlta, bajaCodigoBuscar,
                    codigoBaja, descripcionBaja, precioCompraBaja, precioVentaBaja, stockBaja, modificaCodigoBuscar,
                    codigoModifica, descripcionModifica, precioCompraModifica, precioVentaModifica, stockModifica, entradaCodigoBuscar,
                    codigoEntrada, descripcionEntrada, precioCompraEntrada, precioVentaEntrada, stockEntrada, salidaCodigoBuscar,
                    codigoSalida, descripcionSalida, precioCompraSalida, precioVentaSalida, stockSalida, listadoCodigoBuscar,
                    codigoListado, descripcionListado, precioCompraListado, precioVentaListado, stockListado;
  @FXML
  private VBox inicio;
  @FXML
  private WebView contenedor;
  @FXML
  private Button bBaja, bModifica, bEntrada, bSalida, mTodos, bListado, modificarListado, modificarListadoIndividual,
                  bImportar, bExportar;
  @FXML
  private MenuItem exit;
  @FXML
  private Spinner<Integer> udsAIncrementar, udsSalida;
  @FXML
  private TabPane tabOpciones;
  @FXML
  private TableView<Articulo> almacenTable;
  @FXML
  private Label tituloSeccion;
  @FXML
  private ScrollBar scrollListado;
  @FXML
  private TableColumn<Articulo, Integer> codigoColumn;// = new TableColumn<Articulo,Integer>("Código");
  @FXML
  private TableColumn<Articulo, String> descripcionColumn;
  @FXML
  private TableColumn<Articulo, Double> precioCompraColumn;// = new TableColumn<Articulo,Double>("Precio Compra");
  @FXML
  private TableColumn<Articulo, Double> precioVentaColumn;// = new TableColumn<Articulo,Double>("Precio Venta");
  @FXML
  private TableColumn<Articulo, Integer> stockColumn;// = new TableColumn<Articulo,Integer>("Stock");
  @FXML
  private Tab tabModificar;
  @FXML 
  private Menu menu; 
  @FXML
  private MenuItem menuModificar, menuListado, menuListadoInd, menuAlta, menuBaja, menuEntrada, menuSalida, menuImportar, menuExportar;
  @FXML
  private ToggleGroup groupImportar, groupExportar;
  
  
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    
    // Artículos para cargar prueba de test
//    try {
//      tienda.altaArticulo("Camiseta",1.20,1.50,20);
//      tienda.altaArticulo("Bañador",2.20,3.50,10);
//      tienda.altaArticulo("Gorra",1.75,2.90,5);
//      tienda.altaArticulo("Camisa",4.20,8.50,50);
//      tienda.altaArticulo("Delantal",4.75,8.50,10);
//      tienda.altaArticulo("Taza",1.15,8.50,36);
//      tienda.altaArticulo("Puzzle",5.20,9.50,1);
//      tienda.altaArticulo("Bicicleta",1.20,1.50,20);
//      tienda.altaArticulo("Silla",2.20,3.50,10);
//      tienda.altaArticulo("Zapato",1.75,2.90,5);
//      tienda.altaArticulo("Flor",4.20,8.50,50);
//      tienda.altaArticulo("Lampara",4.75,8.50,10);
//      tienda.altaArticulo("Móvil",1.15,8.50,36);
//      tienda.altaArticulo("TV",5.20,9.50,1);
//      tienda.altaArticulo("Mochila",1.20,1.50,20);
//      tienda.altaArticulo("Disco Duro",2.20,3.50,10);
//      tienda.altaArticulo("Metalmanía",1.75,2.90,5);
//      tienda.altaArticulo("Ordenador",4.20,8.50,50);
//      tienda.altaArticulo("Frigorífico",4.75,8.50,10);
//      tienda.altaArticulo("Aire Acondicionado",1.15,8.50,36);
//      tienda.altaArticulo("Plastificadora",5.20,9.50,1);
//      tienda.altaArticulo("Tatuaje",1.20,1.50,20);
//      tienda.altaArticulo("Botella de Agua",2.20,3.50,10);
//      tienda.altaArticulo("Tablet",1.75,2.90,5);
//      tienda.altaArticulo("Cerveza",4.20,8.50,50);
//      tienda.altaArticulo("Imán",4.75,8.50,10);
//      tienda.altaArticulo("Ratón",1.15,8.50,36);
//      tienda.altaArticulo("Juego de Mesa",5.20,9.50,1);
//      
//    } catch (StockIncorrectoException e) {
//      e.printStackTrace();
//    } catch (PrecioNegativoException e) {
//      e.printStackTrace();
//    }
    udsAIncrementar.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99999, 0));
    udsSalida.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99999, 0));
    
    ArrayList<TextField> camposEntero = new ArrayList<TextField>(Arrays.asList(stockAlta, bajaCodigoBuscar,
        modificaCodigoBuscar, entradaCodigoBuscar, salidaCodigoBuscar));
    ArrayList<TextField> camposDouble = new ArrayList<TextField>(Arrays.asList(precioCompraAlta, precioVentaAlta,
        precioCompraModifica, precioVentaModifica));
    
    codigoAlta.setText(proximoCodigo());
    
    // Carga de listado de tabla
    
    codigoColumn.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    descripcionColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
    precioCompraColumn.setCellValueFactory(new PropertyValueFactory<>("precioCompra"));
    precioVentaColumn.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
    stockColumn.setCellValueFactory(new PropertyValueFactory<>("numUds"));
    
    tienda.forEach(articulo -> almacenTable.getItems().add(articulo));
    
    tituloSeccion.setText("Gestisimal");
    // Filtros para campos enteros
    
    for (TextField campo : camposEntero) {
      campo.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!esEntero(newValue)) {
          campo.setText(oldValue);
        }
      });
    }
    
    for (TextField campo : camposDouble) {
      campo.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!esDouble(newValue)) {
          campo.setText(oldValue);
        }
      });
    }
    
    // Filtros campos Spinner

      udsAIncrementar.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
        if (!esEntero(newValue)) {
          udsAIncrementar.getEditor().setText(oldValue);
//          System.out.println("Valor incorrecto: " + newValue);
        }
      });
      
      udsSalida.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
        if (!esEntero(newValue)) {
          udsSalida.getEditor().setText(oldValue);
//          System.out.println("Valor incorrecto: " + newValue);
        }
      });
      
      // Creamos datos Scroll
      scrollListado.setMin(0);
      scrollListado.setMax(contarAlmacen()-1);
      
      
      // Función del scroll y actualización del scrollbar
      scrollListado.valueProperty().addListener((property, old, value) -> {
        scrollListado.setMin(0);
        scrollListado.setMax(contarAlmacen()-1);
        int valor = (int)scrollListado.getValue();
        ArrayList<Integer> codigos = new ArrayList<Integer>();
        for (Articulo articulo : tienda) {
          codigos.add(articulo.getCodigo());
        }
        mostrarArticulo(codigos.get(valor));
    });
      
    // Combinaciones de teclas
    KeyCombination ctrlX = new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN);
    exit.setAccelerator(ctrlX);

    exit.setOnAction(event -> {
      System.exit(0);
    });
  }
 
  private void actualizarTabla () {
    almacenTable.getItems().clear();
    tienda.forEach(articulo -> almacenTable.getItems().add(articulo));
  }
  
  private void mostrarArticulo(int codigo) {
    try {
      listadoCodigoBuscar.setText(codigo+"");
      codigoListado.setText(codigo+"");
      descripcionListado.setText(tienda.descripcionArticulo(codigo));
      precioCompraListado.setText(Double.toString(tienda.precioCompraArticulo(codigo)));
      precioVentaListado.setText(Double.toString(tienda.precioVentaArticulo(codigo)));
      stockListado.setText(Integer.toString(tienda.stockArticulo(codigo)));    
    } catch (ArticuloIncorrectoException e) {
      e.printStackTrace();
    }
  }


  public void altaNuevoArticulo (ActionEvent event) throws StockIncorrectoException, PrecioNegativoException {
    try {
      tienda.altaArticulo(descripcionAlta.getText(), Double.parseDouble(precioCompraAlta.getText()),
          Double.parseDouble(precioVentaAlta.getText()), Integer.parseInt(stockAlta.getText()));
      altaArticuloCorrecta();
      descripcionAlta.setText("");
      precioCompraAlta.setText("");
      precioVentaAlta.setText("");
      stockAlta.setText("");
      codigoAlta.setText(proximoCodigo());
      
      System.out.println("Artículo incluido correctamente.");
    } catch (StockIncorrectoException | PrecioNegativoException e) {
      tipoDatoIncorrecto("Error en alta de artículo." + e.getMessage());
    }
  }
  
  public void buscarArticulo (ActionEvent e) throws NumberFormatException, ArticuloIncorrectoException {
    ArrayList<TextField> baja = new ArrayList<TextField>(Arrays.asList(bajaCodigoBuscar, codigoBaja, descripcionBaja, precioCompraBaja, precioVentaBaja, stockBaja));
    ArrayList<TextField> modifica = new ArrayList<TextField>(Arrays.asList(modificaCodigoBuscar, codigoModifica, descripcionModifica, precioCompraModifica, precioVentaModifica, stockModifica));
    ArrayList<TextField> entrada = new ArrayList<TextField>(Arrays.asList(entradaCodigoBuscar, codigoEntrada, descripcionEntrada, precioCompraEntrada, precioVentaEntrada, stockEntrada));
    ArrayList<TextField> salida = new ArrayList<TextField>(Arrays.asList(salidaCodigoBuscar, codigoSalida, descripcionSalida, precioCompraSalida, precioVentaSalida, stockSalida));
    ArrayList<TextField> listado = new ArrayList<TextField>(Arrays.asList(listadoCodigoBuscar, codigoListado, descripcionListado, precioCompraListado, precioVentaListado, stockListado));
    ArrayList<TextField> auxiliar = new ArrayList<TextField>();
    
    if (e.getSource().equals(bBaja)) {
      auxiliar = baja;
    } else if (e.getSource().equals(bModifica)) {
      auxiliar = modifica;
    } else if (e.getSource().equals(bEntrada)) {
      auxiliar = entrada;
    } else if (e.getSource().equals(bSalida)) {
      auxiliar = salida;
    } else if (e.getSource().equals(bListado)) {
      auxiliar = listado;
    }
    try {
      int codigo = Integer.parseInt(auxiliar.get(0).getText());
      auxiliar.get(1).setText(auxiliar.get(0).getText());
      auxiliar.get(2).setText(tienda.descripcionArticulo(codigo));
      auxiliar.get(3).setText(Double.toString(tienda.precioCompraArticulo(codigo)));
      auxiliar.get(4).setText(Double.toString(tienda.precioVentaArticulo(codigo)));
      auxiliar.get(5).setText(Integer.toString(tienda.stockArticulo(codigo)));
      
      
    } catch (ArticuloIncorrectoException error) {
      tipoDatoIncorrecto("El código introducido no existe");
      codigoBaja.setText(bajaCodigoBuscar.getText());
      descripcionBaja.setText("");
      precioCompraBaja.setText("");
      precioVentaBaja.setText("");
      stockBaja.setText("");
    }
  }
  
  public void mostrarModificar (ActionEvent e) {
    ArrayList<TextField> listado = new ArrayList<TextField>(Arrays.asList(listadoCodigoBuscar, codigoListado, descripcionListado, precioCompraListado, precioVentaListado, stockListado));
    ArrayList<TextField> modifica = new ArrayList<TextField>(Arrays.asList(modificaCodigoBuscar, codigoModifica, descripcionModifica, precioCompraModifica, precioVentaModifica, stockModifica));
    if (e.getSource().equals(modificarListado)) {
      // TODO
    } else if (e.getSource().equals(modificarListadoIndividual)) {
      int i= 0;
      for (TextField texto : modifica) {
        texto.setText(listado.get(i).getText());
        i++;
      }
      seleccionarTab(e);
    }
  }
  
  public void seleccionarTab (ActionEvent e) {
    if (e.getSource().equals(modificarListadoIndividual) || e.getSource().equals(modificarListado) || e.getSource().equals(menuModificar)) {
      tabOpciones.getSelectionModel().select(3);
    } else if (e.getSource().equals(menuAlta)) {
      tabOpciones.getSelectionModel().select(1);
    } else if (e.getSource().equals(menuBaja)) {
      tabOpciones.getSelectionModel().select(2);
    } else if (e.getSource().equals(menuEntrada)) {
      tabOpciones.getSelectionModel().select(4);
    } else if (e.getSource().equals(menuSalida)) {
      tabOpciones.getSelectionModel().select(5);
    } else if (e.getSource().equals(menuListado)) {
      tabOpciones.getSelectionModel().select(6);
    } else if (e.getSource().equals(menuListadoInd)) {
      tabOpciones.getSelectionModel().select(7);
    } else if (e.getSource().equals(menuImportar)) {
      tabOpciones.getSelectionModel().select(8);
    } else if (e.getSource().equals(menuExportar)) {
      tabOpciones.getSelectionModel().select(9);
    }
  }
  
  public void bajaArticulo (ActionEvent event) throws NumberFormatException, ArticuloIncorrectoException {
    if (!tienda.bajaArticulo(Integer.parseInt(bajaCodigoBuscar.getText()))) {
      tipoDatoIncorrecto("Error en baja de artículo.");
    }
    codigoBaja.setText("");
    descripcionBaja.setText("");
    precioCompraBaja.setText("");
    precioVentaBaja.setText("");
    stockBaja.setText("");
    bajaArticuloCorrecta();
  }
  
  public void modificaDescripcion (ActionEvent e) {
    try {
      tienda.modificaDescripcion((Integer.parseInt(codigoModifica.getText())), descripcionModifica.getText());
      if (!e.getSource().equals(mTodos)) {
        modificacionArticuloCorrecta("El artículo seleccionado se ha modificado correctamente");  
      }
    } catch (NumberFormatException e1) {
    } catch (ArticuloIncorrectoException e1) {
      tipoDatoIncorrecto("El artículo buscado no existe");
    }
  }
  
  public void modificaPrecioCompra (ActionEvent e) {
    try {
      tienda.modificaPrecioCompra((Integer.parseInt(codigoModifica.getText())), Double.parseDouble(precioCompraModifica.getText()));
      if (!e.getSource().equals(mTodos)) {
        modificacionArticuloCorrecta("El artículo seleccionado se ha modificado correctamente");  
      }
    } catch (NumberFormatException error) {
    } catch (ArticuloIncorrectoException error) {
      tipoDatoIncorrecto("El artículo buscado no existe");
    } catch (PrecioNegativoException error) {
      tipoDatoIncorrecto("El artículo no puede tener precio negativo");
    }
  }
  
  public void modificaPrecioVenta (ActionEvent e) {
    try {
      tienda.modificaPrecioVenta((Integer.parseInt(codigoModifica.getText())), Double.parseDouble(precioVentaModifica.getText()));
      if (!e.getSource().equals(mTodos)) {
        modificacionArticuloCorrecta("El artículo seleccionado se ha modificado correctamente");  
      }
    } catch (NumberFormatException error) {
    } catch (ArticuloIncorrectoException error) {
      tipoDatoIncorrecto("El artículo buscado no existe");
    } catch (PrecioNegativoException error) {
      tipoDatoIncorrecto("El artículo no puede tener precio negativo");
    }
  }
  
  public void modificaTodos (ActionEvent e) {
    modificaDescripcion(e);
    modificaPrecioCompra(e);
    modificaPrecioVenta(e);
    modificacionArticuloCorrecta("El artículo seleccionado se ha modificado correctamente");  
  }
  
  public void entradaArticulos (ActionEvent e) {
    try {
      tienda.entraMercancia(Integer.parseInt(codigoEntrada.getText()), udsAIncrementar.getValue());
      modificacionArticuloCorrecta("Stock actualizado correctamente");
      stockEntrada.setText(Integer.toString(tienda.stockArticulo(Integer.parseInt(codigoEntrada.getText()))));
    } catch (NumberFormatException e1) {
      tipoDatoIncorrecto("El número de unidades debe ser un entero positivo");
    } catch (StockIncorrectoException e1) {
      tipoDatoIncorrecto("El incremento de stock debe ser un entero positivo");
    } catch (ArticuloIncorrectoException e1) {
      tipoDatoIncorrecto("El artículo seleccionado no existe");
    }
  }
  
  public void salidaArticulos (ActionEvent e) {
    try {
      tienda.saleMercancia(Integer.parseInt(codigoSalida.getText()), udsSalida.getValue());
      modificacionArticuloCorrecta("Stock actualizado correctamente");
      stockSalida.setText(Integer.toString(tienda.stockArticulo(Integer.parseInt(codigoSalida.getText()))));
    } catch (NumberFormatException e1) {
      tipoDatoIncorrecto("El número de unidades debe ser un entero positivo");
    } catch (StockIncorrectoException e1) {
      tipoDatoIncorrecto("No hay suficientes unidades disponibles.");
    } catch (ArticuloIncorrectoException e1) {
      tipoDatoIncorrecto("El artículo seleccionado no existe");
    }
  }
  
  public void importarAlmacen (ActionEvent e) {
    if (e.getSource().equals(bImportar)) {
      RadioButton elegido = (RadioButton) groupImportar.getSelectedToggle();
      try {
        if (elegido.getText().compareTo("CSV")==0) {
          String fichero = UtilidadesControlador.seleccionarArchivo(e, bImportar, inicio, "CSV");
          tienda.importaCSV(fichero);
          importacionCorrecta();
        } else if (elegido.getText().compareTo("XML")==0) {
          String fichero = UtilidadesControlador.seleccionarArchivo(e, bImportar, inicio, "XML");
          tienda.importaXML(fichero);
          importacionCorrecta();
        } else if (elegido.getText().compareTo("JSON")==0) {
          String fichero = UtilidadesControlador.seleccionarArchivo(e, bImportar, inicio, "JSON");
          tienda.importaJSON(fichero);
          importacionCorrecta();
        }
      } catch (NumberFormatException e1) {
        tipoDatoIncorrecto("Error al importar archivo");
      } catch (StockIncorrectoException e1) {
        tipoDatoIncorrecto("Error al importar archivo");
      } catch (PrecioNegativoException e1) {
        tipoDatoIncorrecto("Error al importar archivo");
      } catch (IOException e1) {
        tipoDatoIncorrecto("Error al importar archivo");
      }
    }
  }
  
  public void exportarAlmacen (ActionEvent e) {
    if (e.getSource().equals(bExportar)) {
      RadioButton elegido = (RadioButton) groupExportar.getSelectedToggle();
      try {
        if (elegido.getText().compareTo("CSV")==0) {
          String fichero = UtilidadesControlador.guardarArchivo(e, bExportar, inicio, "CSV");
          tienda.exportaCSV(fichero);
          mensajeCorrecto("Exportación realizada con éxito", "Exportar");
        } else if (elegido.getText().compareTo("XML")==0) {
          String fichero = UtilidadesControlador.guardarArchivo(e, bExportar, inicio, "XML");
          tienda.exportaXML(fichero);
          mensajeCorrecto("Exportación realizada con éxito", "Exportar");
        } else if (elegido.getText().compareTo("JSON")==0) {
          String fichero = UtilidadesControlador.guardarArchivo(e, bExportar, inicio, "JSON");
          tienda.exportaJSON(fichero);
          mensajeCorrecto("Exportación realizada con éxito", "Exportar");
        }
      } catch (NumberFormatException e1) {
        tipoDatoIncorrecto("Error al exportar archivo");
      } catch (IOException e1) {
        tipoDatoIncorrecto("Error al exportar archivo");
      } catch (TransformerException e1) {
        tipoDatoIncorrecto("Error al exportar archivo");
      }
    }
  }

  private void importacionCorrecta() {
    mensajeCorrecto("Importación realizada con éxito", "Importación");
    actualizarTabla();
    codigoAlta.setText(proximoCodigo());
  }
  
  private int contarAlmacen() {
    int contador=0;
    for (@SuppressWarnings("unused") Articulo articulo: tienda) {
      contador++;
    }
    return contador;
    
  }
  public String proximoCodigo () {
    int codigo = 0;
    for (Articulo articulo : tienda) {
      codigo = articulo.getCodigo();
    }
    codigo++;
    return codigo+"";
  }
  
  private void lanzarGithub() {
    try {
      Stage newStage = new Stage();
      FXMLLoader fxml = new FXMLLoader(this.getClass().getResource("Github.fxml"));
      VBox root = fxml.<VBox>load();
      Scene escenaInicio = new Scene(root);
      newStage.setScene(escenaInicio);
      newStage.setTitle("gitHub. Manuel Hidalgo"); // nombre de la clase
      newStage.setResizable(false);
      newStage.showAndWait();

    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }
  
  // Filtros para rececpión de datos
  
  private boolean esEntero(String cadena) {
    return Pattern.matches("^[0-9]*$", cadena);
  }
  
  private boolean esDouble(String cadena) {
    return Pattern.matches("^[0-9]*\\.?[0-9]*$", cadena);
  }
  
  
  // Alerts y Dialogs con información de los procesos realizados
  
  private void tipoDatoIncorrecto(String mensaje) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setHeaderText(null);
    alert.setTitle("Error");
    alert.setContentText(mensaje);
    alert.showAndWait();
  }
  
  private void altaArticuloCorrecta() {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Gestisimal. Alta de Artículo");
    alert.setHeaderText(null);
    alert.setContentText("El artículo se ha introducido correctamente");

    alert.showAndWait();
  }
  
  private void mensajeCorrecto(String mensaje, String tipo) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Gestisimal. " + tipo);
    alert.setHeaderText(null);
    alert.setContentText(mensaje);

    alert.showAndWait();
  }
 
  
  private void bajaArticuloCorrecta() {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Gestisimal. Baja de Artículo");
    alert.setHeaderText(null);
    alert.setContentText("El artículo seleccionado se ha eliminado correctamente");

    alert.showAndWait();
  }
  
  private void modificacionArticuloCorrecta(String mensaje) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Gestisimal. Modificación de Artículo");
    alert.setHeaderText(null);
    alert.setContentText(mensaje);

    alert.showAndWait();
  }
  
}
