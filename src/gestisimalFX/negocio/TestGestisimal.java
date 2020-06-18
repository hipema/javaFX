package gestisimalFX.negocio;

import java.io.IOException;
import javax.xml.transform.TransformerException;

import gestisimalFX.negocio.Almacen;
import gestisimalFX.excepciones.ArticuloIncorrectoException;
import gestisimalFX.excepciones.PrecioNegativoException;
import gestisimalFX.excepciones.StockIncorrectoException;
import gestisimalFX.utilidades.Menu;
import gestisimalFX.utilidades.Teclado;

public class TestGestisimal {
  private static Almacen tienda = new Almacen();
  // Creamos el menú
private static Menu menu = new Menu("Gestión del almacén", new String[] { 
      "Alta de nuevo Artículo", "Baja de Artículo existente", "Modificar descripción de artículo", 
      "Modificar Precio de compra de un artículo", "Modificar Precio de venta de un artículo", 
      "Entrada de mercancía", "Salida de mercancía", "Mostrar un artículo", "Listar Almacén", 
      "Exportar en XML", "Exportar en JSON", "Exportar en CSV", "Importa XML", "Importa CSV", "Importa JSON"});

  public static void main(String[] args) {     
    // Artículos para cargar prueba de test
//    tienda.altaArticulo("Camiseta",1.20,1.50,20);
//    tienda.altaArticulo("Bañador",2.20,3.50,10);
//    tienda.altaArticulo("Gorra",1.75,2.90,5);
//    tienda.altaArticulo("Camisa",4.20,8.50,50);
//    tienda.altaArticulo("Delantal",4.75,8.50,10);
//    tienda.altaArticulo("Taza",1.15,8.50,36);
//    tienda.altaArticulo("Puzzle",5.20,9.50,1);
    
    while (true) {
      switch (menu.gestionar()) {
      case 16:
        System.out.println("Fin del programa");
        System.exit(0);
      case 1:
        altaNuevoArticulo();
        break;
      case 2:
        bajaArticulo();
        break;
      case 3:
        modificarArticulo();
        break;
      case 4:
        modificarPrecioCompra();
        break;
      case 5:
        modificarPrecioVenta();
        break;
      case 6:
        entradaMercancia();
        break;
      case 7:
        salidaMercancia();
        break;
      case 8:
        mostrarArticulo();
        break;
      case 9:
        listarAlmacen();
        break;
      case 10:
        exportarXML();
        break;
      case 11:
        exportarJSON();
        break;
      case 12:
        exportarCSV();
        break;
      case 13: 
        importarXML();
        break;
      case 14: 
        importarCSV();
        break;
      case 15: 
        importarJSON();
        break;
      }
    }
   }
    
//Métodos de la clase
  private static void altaNuevoArticulo () {
    try {
      System.out.println();
      System.out.println("Alta de nuevo artículo");
      System.out.println("----------------------");
      tienda.altaArticulo(Teclado.leerCadena("Descripción: "), 
                     Teclado.leerDouble("Precio de compra: "), 
                     Teclado.leerDouble("Precio de venta: "), 
                     Teclado.leerNumeroEntero("Unidades iniciales"));
      System.out.println();
    } catch (StockIncorrectoException | PrecioNegativoException e) {
      System.err.println("Error en alta de artículo." + e.getMessage());
    }
  }
  
  private static void bajaArticulo () {

      System.out.println();
      System.out.println("Baja de artículo existente");
      System.out.println("--------------------------");
      if (!tienda.bajaArticulo(Teclado.leerNumeroEntero("Indica el código del artículo a borrar: ")))
          System.err.println("Error en baja de artículo. El código no existe");
      System.out.println();
    
  }
  
  private static void modificarArticulo (){
    try{
      System.out.println();
      System.out.println("Modificar descripción artículo");
      System.out.println("------------------------------");
      System.out.println("Descripción actual: ");
      System.out.println(tienda.descripcionArticulo(Teclado.leerNumeroEntero("Código del artículo: ")));
      tienda.modificaDescripcion(Teclado.leerNumeroEntero("Código del artículo: "), Teclado.leerCadena("Incluye la nueva descripción: "));
      System.out.println();
    } catch (ArticuloIncorrectoException e) {
      System.err.println("Error al modificar descripción." + e.getMessage());
    }
    
  }
  
  private static void modificarPrecioCompra () {
    try {
      System.out.println();
      System.out.println("Modificar Precio de Compra");
      System.out.println("--------------------------"); 
      int codigoArticulo = Teclado.leerNumeroEntero("Código del artículo: ");
      System.out.println("Precio de compra actual: ");
      System.out.println(tienda.precioCompraArticulo(codigoArticulo));
      tienda.modificaPrecioCompra(codigoArticulo, Teclado.leerDouble("Incluye nuevo precio de compra: "));
      System.out.println();
    } catch (PrecioNegativoException | ArticuloIncorrectoException e) {
      System.err.println("Error al modificar precio de compra." + e.getMessage());
    
    }
    
  }
  
  private static void modificarPrecioVenta (){
    
      try {
        System.out.println();
        System.out.println("Modificar Precio de Venta");
        System.out.println("-------------------------");
        int codigoArticulo = Teclado.leerNumeroEntero("Código del artículo: ");
        System.out.println("Precio de venta actual: ");      
        System.out.println(tienda.precioVentaArticulo(codigoArticulo));
        tienda.modificaPrecioVenta(codigoArticulo, Teclado.leerDouble("Incluye nuevo precio de venta: "));
        System.out.println();
      } catch (ArticuloIncorrectoException | PrecioNegativoException e) {
        System.err.println("Error al modificar precio de venta." + e.getMessage());
      }
    
    
  }
  
  private static void entradaMercancia (){
    try {
      System.out.println();
      System.out.println("Entrada de mercancía");
      System.out.println("--------------------");
      int codigoArticulo = Teclado.leerNumeroEntero("Código del artículo: ");
      System.out.println("Stock inicial: "+tienda.stockArticulo(codigoArticulo));
      tienda.entraMercancia(codigoArticulo, Teclado.leerNumeroEntero("Unidades recibidas: "));
      System.out.println("Stock actualizado de: "+tienda.stockArticulo(codigoArticulo));
      System.out.println();
      
    } catch (StockIncorrectoException | ArticuloIncorrectoException  e) {
      System.err.println("Error en la entrada de mercancía. "+ e.getMessage());
    }
    
  }
  
  private static void salidaMercancia (){
      try {
        System.out.println();
        System.out.println("Salida de mercancía");
        System.out.println("-------------------");
        int codigoArticulo = Teclado.leerNumeroEntero("Código del artículo: ");
        tienda.saleMercancia(codigoArticulo, Teclado.leerNumeroEntero("Unidades vendidas: "));
        System.out.println("Stock actualizado de: "+tienda.stockArticulo(codigoArticulo));
        System.out.println();
      } catch (StockIncorrectoException | ArticuloIncorrectoException e) {
        System.err.println("Error en la salida de mercancía." + e.getMessage());
      }
    
  }
  
  private static void mostrarArticulo () {
    try {
      System.out.println();
      System.out.println("Muestra datos de un artículo");
      System.out.println("----------------------------");
      System.out.println(tienda.muestraArticulo(Teclado.leerNumeroEntero("Código del artículo: ")));
      System.out.println();
    } catch (ArticuloIncorrectoException e) {
      System.err.println("Error al mostrar artículo." + e.getMessage());
    }
  }
  private static void listarAlmacen () {
    System.out.println();
    System.out.println("Lista de Almacén");
    System.out.println("----------------");
    System.out.println(tienda);
    System.out.println();
  }
  private static void exportarXML() {
    try {
      System.out.println();
      System.out.println("Exportamos el listado de Almacén a XML");
      String fichero = Teclado.leerCadena("Indica nombre para guardar el archivo: ");
      tienda.exportaXML(fichero);
      System.out.println("Fichero creado correctamente.");
      System.out.println();
    } catch (TransformerException e) {
      System.err.println("Error al exportar archivo XML." + e.getMessage());
    }
  }
  private static void exportarJSON() {
    try {
      System.out.println();
      System.out.println("Exportamos el listado de Almacén a JSON");
      String fichero = Teclado.leerCadena("Indica nombre para guardar el archivo: ");
      tienda.exportaJSON(fichero);
      System.out.println("Fichero creado correctamente.");
      System.out.println();
    } catch (IOException e) {
      System.err.println("Error al exportar archivo JSON." + e.getMessage());
    }
  }
  private static void exportarCSV() {
    try {
      System.out.println();
      System.out.println("Exportamos el listado de Almacén a CSV");
      String fichero = Teclado.leerCadena("Indica nombre para guardar el archivo: ");
      tienda.exportaCSV(fichero);
      System.out.println("Fichero creado correctamente.");
      System.out.println();
    } catch (Exception e) {
      System.err.println("Error al exportar archivo CSV." + e.getMessage());
    }
  }
  private static void importarCSV() {
    try {
      tienda.importaCSV(Teclado.leerCadena("Nombre del archivo CSV a importar"));
      System.out.println("Fichero importado correctamente.");
      System.out.println();
    } catch (NumberFormatException | StockIncorrectoException | PrecioNegativoException e) {
      System.err.println("Error al importar CSV." + e.getMessage());
    }
  }
  private static void importarXML() {
    tienda.importaXML(Teclado.leerCadena("Nombre del archivo XML a importar"));
    System.out.println("Fichero importado correctamente.");
    System.out.println();
  }
  private static void importarJSON() {
    try {
      tienda.importaJSON(Teclado.leerCadena("Nombre del archivo JSON a importar"));
      System.out.println("Fichero importado correctamente.");
      System.out.println();
    } catch (StockIncorrectoException | PrecioNegativoException | IOException e) {
      System.err.println("Error al importar JSON." + e.getMessage());
    }
  }
}