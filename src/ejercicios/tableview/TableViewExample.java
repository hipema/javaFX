package ejercicios.tableview;
/**
 * The JavaFX TableView enables you to display table views inside your JavaFX applications. 
 * 
 * The JavaFX TableView is represented by the class javafx.scene.control.TableView 
 * 
 * See more at http://tutorials.jenkov.com/javafx/tableview.html
 */

import java.util.Optional;
import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TableViewExample extends Application {
  TableView<Contact> tableView = new TableView<Contact>();
  String nombre, apellido, telefono;
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    // In order to use a JavaFX TableView component you must first create a TableView instance
    

    /*
     * Having created a TableView you need to add one or more TableColumn instances to the 
     * TableView instance. 
     * 
     * A TableColumn represents a vertical column of values. 
     * 
     * Each value is displayed on its own row, and is typically extracted from the list of objects 
     * being displayed in the TableView.
     */

    TableColumn<Contact, String> column1 = new TableColumn<>("First Name");
    TableColumn<Contact, String> column2 = new TableColumn<>("Last Name");
    TableColumn<Contact, String> column3 = new TableColumn<>("Name");
    TableColumn<Contact, String> column4 = new TableColumn<>("Phone");

    /*
     * A TableColumn must have a cell value factory set on it. 
     * 
     * The cell value factory extracts the value to be displayed in each cell (on each row) in the 
     * column. 
     * 
     * The PropertyValueFactory factory can extract a property value (field value) from a Java object. 
     * 
     * The name of the property is passed as a parameter to the PropertyValueFactory constructor.
     */

    column1.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    column2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    column3.setCellValueFactory(new PropertyValueFactory<>("name"));
    column4.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

    // Add columns
    tableView.getColumns().add(column1);
    tableView.getColumns().add(column2);
    tableView.getColumns().add(column3);
    tableView.getColumns().add(column4);

    /*
     * You can set a placeholder to be displayed when the JavaFX TableView has no rows to display. 
     * 
     * The placeholder must be an instance of the JavaFX Node class, which most (if not all) JavaFX 
     * controls are. 
     */

    tableView.setPlaceholder(new Label("No rows to display"));

    /*
     * Once you have added TableColumn instances to the JavaFX TableView, you can add the data 
     * to be displayed to the TableView. 
     */

    tableView.getItems().add(new Contact("John", "Doe", "666111222"));
    tableView.getItems().add(new Contact("Jane", "Deer", "666111222"));
    tableView.getItems().add(new Contact("Mike", "Scot", "666111222"));
    tableView.getItems().add(new Contact("Lucy", "Bonn", "666111222"));
    tableView.getItems().add(new Contact("Pepe", "Bond", "666111222"));

    TextArea textArea = new TextArea();

    // To obtain a list of the row items of a JavaFX TableView, you call getItems() method. 

    Button buttonPrint = new Button("Print Table");
    buttonPrint.setOnAction(e -> {
      for (Contact contact : tableView.getItems()) {
        textArea.appendText(contact + "\n");
      }
      textArea.appendText("---\n");
    });

    /*
     * The JavaFX TableView component has an internal SelectionModel which is used to either read 
     * what rows and / or cells the user has selected, or to select rows and cells programmatically
     */

    TableViewSelectionModel<Contact> selectionModel = tableView.getSelectionModel();

    // set selection mode to only 1 row (default)
    selectionModel.setSelectionMode(SelectionMode.SINGLE);

    // You can set selection mode to multiple rows

    Button buttonSelection = new Button("Multiple Selection");
    buttonSelection.setOnAction(e -> {
      if (selectionModel.getSelectionMode().equals(SelectionMode.SINGLE)) {
        selectionModel.setSelectionMode(SelectionMode.MULTIPLE);
        buttonSelection.setText("Single Selection");
      } else {
        selectionModel.setSelectionMode(SelectionMode.SINGLE);
        buttonSelection.setText("Multiple Selection");
      }
    });

    /*
     *  To obtain a list of the selected row items of a JavaFX TableView, you call the 
     *  SelectionModel getSelectedItems() method. 
     */

    Button buttonItems = new Button("Selected Items");
    buttonItems.setOnAction(e -> {
      ObservableList<Contact> selectedItems = selectionModel.getSelectedItems();
      for (Contact contact : selectedItems) {
        textArea.appendText(contact + "\n");
      }
      textArea.appendText("---\n");
    });

    /*
     * You can also just obtain a list of the indices of the selected rows, 
     * instead of the selected items themselves. 
     */

    Button buttonIndices = new Button("Selected Indices");
    buttonIndices.setOnAction(e -> {
      ObservableList<Integer> selectedIndices = selectionModel.getSelectedIndices();
      textArea.appendText("Indices: " + selectedIndices.toString() + "\n");
      textArea.appendText("---\n");
    });

    /*
     *  You can clear all selected rows and cells using the TableViewSelectionModel clearSelection() 
     *  method.
     */

    Button buttonClear = new Button("Clear");
    buttonClear.setOnAction(e -> {
      selectionModel.clearSelection();
      textArea.clear();
    });
    
    //    botonAnadir, botonModificar, botonBorrar, botonGuardar, botonCargar

    /*
     * Creamos botón añadir
     */
    Button botonAnadir = new Button("Añadir");
    botonAnadir.setOnAction(e -> {
      selectionModel.clearSelection();
      dialogoAnadir();
      textArea.clear();
      textArea.setText("Contacto añadido");
    });
    
    /*
     * Creamos botón modificar
     */
    Button botonModificar = new Button("Modificar");
    botonModificar.setOnAction(e -> {
      ObservableList<Contact> selectedItems = selectionModel.getSelectedItems();
      int indice = 0;
      for (Contact contact : selectedItems) {
        nombre = contact.getFirstName();
        apellido = contact.getLastName();
        telefono = contact.getPhoneNumber();
        indice = tableView.getItems().indexOf(contact);
      }
      dialogoModificar(nombre, apellido, telefono, indice);
      tableView.refresh();
    });
    
    
//    Button buttonIndices = new Button("Selected Indices");
//    buttonIndices.setOnAction(e -> {
//      ObservableList<Integer> selectedIndices = selectionModel.getSelectedIndices();
//      textArea.appendText("Indices: " + selectedIndices.toString() + "\n");
//      textArea.appendText("---\n");
//    });
    /*
     * Creamos botón Borrar
     */
    Button botonBorrar = new Button("Borrar");
    botonBorrar.setOnAction(e -> {
      ObservableList<Contact> selectedItems = selectionModel.getSelectedItems();
      for (Contact contact : selectedItems) {
        tableView.getItems().remove(contact);
      }
      tableView.refresh();
    });
    
    /*
     * Creamos botón Guardar
     */
    //Button botonGuardar = new Button("Guardar");
    
    /*
     * Creamos botón Cargar
     */
    //Button botonCargar = new Button("Cargar");
    
    // It is possible to listen for selection changes in the TableViewSelectionModel

    ObservableList<Contact> selectedItems = selectionModel.getSelectedItems();

    selectedItems.addListener(new ListChangeListener<Contact>() {
      @Override
      public void onChanged(Change<? extends Contact> change) {
        System.out.println("Selection changed: " + change.getList());
        }
    });
    
    // Layout, scene and stage
    HBox hBox = new HBox(10, buttonPrint, buttonItems, buttonIndices, buttonSelection, buttonClear);
    hBox.setAlignment(Pos.CENTER);
    HBox hBox2 = new HBox(10, botonAnadir, botonModificar, botonBorrar);
    hBox2.setAlignment(Pos.CENTER);
    VBox root = new VBox(10, tableView, hBox, hBox2, textArea);
    root.setPadding(new Insets(10));

    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.setTitle("TableView Example");
    primaryStage.show();
    
  }
  
  private void dialogoAnadir() {
    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setTitle("Añadir Contacto");
    dialog.setHeaderText("Introduce los datos para añadir un nuevo contacto.");

    // Poner los botones que queramos
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
    // Create los fields y labels
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(20, 150, 10, 10));

    Label firstName = new Label("First Name: ");
    Label lastName = new Label("Last Name: ");
    Label phoneNumber = new Label ("Phone Number: ");
    
    TextField firstNameField = new TextField();
    TextField lastNameField = new TextField();
    TextField phoneNumberField = new TextField();
    
    grid.add(firstName, 0, 0);
    grid.add(lastName, 0, 1);
    grid.add(phoneNumber, 0, 2);
    grid.add(firstNameField, 1, 0);
    grid.add(lastNameField, 1, 1);
    grid.add(phoneNumberField, 1, 2);

    dialog.getDialogPane().setContent(grid);

    Optional<ButtonType> result = dialog.showAndWait();
    if (result.get() == ButtonType.OK) {
      tableView.getItems().add(new Contact(firstNameField.getText(), lastNameField.getText(), phoneNumberField.getText()));
    }
  }
  
  private void dialogoModificar(String nombre, String apellido, String telefono, int indice) {
    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setTitle("Añadir Contacto");
    dialog.setHeaderText("Introduce los datos para añadir un nuevo contacto.");

    // Poner los botones que queramos
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
    // Create los fields y labels
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(20, 150, 10, 10));

    Label firstName = new Label("First Name: ");
    Label lastName = new Label("Last Name: ");
    Label phoneNumber = new Label ("Phone Number: ");
    
    TextField firstNameField = new TextField(nombre);
    firstNameField.setPromptText("Nombre");
    TextField lastNameField = new TextField(apellido);
    lastNameField.setPromptText("Apellido");
    TextField phoneNumberField = new TextField(telefono);
    
    grid.add(firstName, 0, 0);
    grid.add(lastName, 0, 1);
    grid.add(phoneNumber, 0, 2);
    grid.add(firstNameField, 1, 0);
    grid.add(lastNameField, 1, 1);
    grid.add(phoneNumberField, 1, 2);

    dialog.getDialogPane().setContent(grid);

    Optional<ButtonType> result = dialog.showAndWait();
    if (result.get() == ButtonType.OK) {
      tableView.getItems().get(indice).setFirstName(firstNameField.getText());
      tableView.getItems().get(indice).setLastName(lastNameField.getText());
      tableView.getItems().get(indice).setPhoneNumber(phoneNumberField.getText());
    }
  }
}