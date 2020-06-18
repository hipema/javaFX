package gestisimalFX.negocio;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import gestisimalFX.negocio.Articulo;
import gestisimalFX.excepciones.ArticuloIncorrectoException;
import gestisimalFX.excepciones.PrecioNegativoException;
import gestisimalFX.excepciones.StockIncorrectoException;

public class Almacen implements Iterable <Articulo> {
  //Estado
  private ArrayList <Articulo> almacen = new ArrayList<Articulo>();
  
  /**
   * Alta de Artículo 
   * @param descripcion
   * @param precioCompra
   * @param precioVenta
   * @param numUds
   * @throws StockIncorrectoException
   * @throws PrecioNegativoException
   */
  public void altaArticulo (String descripcion, double precioCompra, double precioVenta, int numUds) throws StockIncorrectoException, PrecioNegativoException {
    almacen.add(new Articulo(descripcion,precioCompra,precioVenta,numUds));
  }
  
  /**
   * Baja de un artículo.
   * 
   * @param El código del artículo que se va a borrar.
   * @return true si el artículo ha sido eliminado.
   */
  public boolean bajaArticulo(int codigoArticulo){
    
    return almacen.remove(new Articulo(codigoArticulo));
  }
  
  private Articulo getArticulo(int codigoArticulo) throws ArticuloIncorrectoException {
    return almacen.get(devuelveIndice(codigoArticulo));
  }
  /**
   * Modifica descripción
   * @param Codigo del artículo a modificar
   * @param descripción actualizada
   * @throws ArticuloIncorrectoException 
   */
  public void modificaDescripcion (int codigoArticulo, String descripcion) throws ArticuloIncorrectoException {
    getArticulo(codigoArticulo).setDescripcion(descripcion);  
  }
  
  /**
   * Modifica el Precio de Compra
   * @param Código del artículo a modificar
   * @param nuevo precio de compra
   * @throws PrecioNegativoException 
   * @throws ArticuloIncorrectoException 
   */
  public void modificaPrecioCompra (int codigoArticulo, double precioCompra) throws PrecioNegativoException, ArticuloIncorrectoException {
    getArticulo(codigoArticulo).setPrecioCompra(precioCompra); 
  }
  
  /**
   * Modifica el Preco de Venta
   * @param Código del artículo a modificar
   * @param nuevo precio de venta
   * @throws PrecioNegativoException 
   * @throws ArticuloIncorrectoException 
   */
  public void modificaPrecioVenta (int codigoArticulo, double precioVenta) throws PrecioNegativoException, ArticuloIncorrectoException {
    getArticulo(codigoArticulo).setPrecioVenta(precioVenta); 
  }
  
  /**
   * Entrada de mercancía. Aumento de uds
   * @param Código del artículo a modificar
   * @param cantidad recibida
   * @throws StockIncorrectoException 
   * @throws ArticuloIncorrectoException 
   */
  public void entraMercancia (int codigoArticulo, int cantidad) throws StockIncorrectoException, ArticuloIncorrectoException {
    getArticulo(codigoArticulo).entraMercancia(cantidad);
  }
  
  /**
   * Salida de mercancía. Decremento de uds.
   * @param Código del artículo a modificar.
   * @param unidades que salen de almacén.
   * @throws StockIncorrectoException 
   * @throws ArticuloIncorrectoException 
   */
  public void saleMercancia (int codigoArticulo, int cantidad) throws StockIncorrectoException, ArticuloIncorrectoException {
    getArticulo(codigoArticulo).saleMercancia(cantidad); 
  }
  
  /**
   * Muestra un artículo
   * @param Código del artículo a modificar
   * @return cadena con la información del artículo
   * @throws ArticuloIncorrectoException 
   */
  public String muestraArticulo (int codigoArticulo) throws ArticuloIncorrectoException {
    return getArticulo(codigoArticulo).toString();
  }
  
  /**
   * Devuelve la descripción de un artículo
   * @param Código del artículo a mostrar
   * @return cadena con la descrpción del artículo
   * @throws ArticuloIncorrectoException 
   */
  public String descripcionArticulo (int codigoArticulo) throws ArticuloIncorrectoException {
    return getArticulo(codigoArticulo).getDescripcion();
  }
  
  /**
   * Muestra precio de compra del artículo
   * @param Código del artículo a mostrar
   * @return devuelve el precio de compra del artículo
   * @throws ArticuloIncorrectoException 
   */
  public double precioCompraArticulo (int codigoArticulo) throws ArticuloIncorrectoException {
     return getArticulo(codigoArticulo).getPrecioCompra();
  }
   
  /**
   * Muestra el precio de venta de un artículo
   * @param Código del artículo a mostrar
   * @return devuelve el precio de venta del artículo
   * @throws ArticuloIncorrectoException 
   */
  public double precioVentaArticulo (int codigoArticulo) throws ArticuloIncorrectoException {
     return getArticulo(codigoArticulo).getPrecioVenta();
  }
   
  /**
   * Muestar el stock de un artículo
   * @param Código del artículo a mostrar
   * @return devuelve el número de unidades del artículo en almacén
   * @throws ArticuloIncorrectoException 
   */
  public int stockArticulo (int codigoArticulo) throws ArticuloIncorrectoException {
     return getArticulo(codigoArticulo).getNumUds();
  }

  /**
   * Devuelve el índice del artículo
   * @param Código del artículo a busar
   * @return devuelve un entero con el índice donde se encuentra el artículo
   */
  private int devuelveIndice(int codigoArticulo) throws ArticuloIncorrectoException {
    if (almacen.indexOf(new Articulo (codigoArticulo)) == -1) {
      throw new ArticuloIncorrectoException("El código no corresponde con ningún artículo");
    }
    return almacen.indexOf(new Articulo (codigoArticulo));
  }
  
  
  // Exportación del almacén
  /**
   * Crea un fichero con el nombre pasado por el usuario con los datos del almacén
   * en formato json.
   * @param nombre del fichero, se recomienda que termine en .json
   * @throws IOException
   */
  public void exportaJSON (String fichero) throws IOException {
    // Paso almacen a JSON
    String json = new Gson().toJson(almacen);
    
    BufferedWriter archivo = new BufferedWriter(new FileWriter(fichero));
    archivo.write(json);
    archivo.close();
    // falta por y convertir en try y excepciones
  }
  
  /**
   * Crea un fichero con el nombre pasado por el usuario con los datos del almacén
   * en formato CSV .
   * @param nombre del fichero, se recomienda que termine en .csv
   */
  public void exportaCSV(String fichero) {
    try {
      BufferedWriter archivo = new BufferedWriter(new FileWriter(fichero));
      
      archivo.append("Código;Descripcion;Precio Compra;Precio Venta;Stock");
      archivo.newLine();
      for (int i =0; i <= almacen.size()-1; i++) {
        // Con String.join nos crea una cadena separada por el valor que nos indica.
        archivo.append(String.join(";",Integer.toString(almacen.get(i).getCodigo()),almacen.get(i).getDescripcion(),String.valueOf(almacen.get(i).getPrecioCompra()),String.valueOf(almacen.get(i).getPrecioVenta()),Integer.toString(almacen.get(i).getNumUds())));
        archivo.newLine();
      }      
      archivo.close();
      
    } catch (FileNotFoundException e){
      System.err.println("No se encuentra el archivo.");
      System.exit(1);
    } catch (IOException e) {
      System.err.println("Error de entrada/salida al manejar el fichero");
      System.exit(2);
    }
  }
  
  /**
   * Crea un fichero con el nombre pasado por el usuario con los datos del almacén
   * en formato XML
   * @param nombre del fichero, se recomienda que termine en .xml
   * @throws TransformerException
   */
  public void exportaXML (String fichero) throws TransformerException {
    try {
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.newDocument(); // sobre este objeto se trabaja.
      
      // Este será el nodo raíz del XML
      Element almacenXML = doc.createElement("almacen");
      doc.appendChild(almacenXML);
      
      for (Articulo art: almacen) {
        // Empezamos el artículo
        Element articuloXML = doc.createElement("Artículo");
        almacenXML.appendChild(articuloXML);
        
        // Le añadimos su atributo
        Attr codigoXML = doc.createAttribute("codigo");
        codigoXML.setValue(Integer.toString(art.getCodigo()));
        articuloXML.setAttributeNode(codigoXML);
        
        // Leemos los demás elementos anidados
        Element descripcionXML = doc.createElement("Descripcion");
        descripcionXML.appendChild(doc.createTextNode(art.getDescripcion()));
        articuloXML.appendChild(descripcionXML);
        
        Element precioCompraXML = doc.createElement("PrecioCompra");
        precioCompraXML.appendChild(doc.createTextNode(Double.toString(art.getPrecioCompra())));
        articuloXML.appendChild(precioCompraXML);
        
        Element precioVentaXML = doc.createElement("PrecioVenta");
        precioVentaXML.appendChild(doc.createTextNode(Double.toString(art.getPrecioVenta())));
        articuloXML.appendChild(precioVentaXML);
        
        Element stockXML = doc.createElement("NumUds");
        stockXML.appendChild(doc.createTextNode(Integer.toString(art.getNumUds())));
        articuloXML.appendChild(stockXML);
      }
      
      // Guardar XML en fichero.
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer(); // Este es el que nos permitirá escribir en el archivo
      DOMSource source = new DOMSource(doc); // Permite combinar el documento xml con el que vamos a escribir
      StreamResult result = new StreamResult(new FileWriter(fichero));
      transformer.transform(source, result);
      
      // Capturar las posibles excepciones, pendiente personalizarlas creando nuevas excepciones.
    } catch (IOException e) {
      System.err.println("Error al exportar Archivos. IOException");
      System.exit(1);
    } catch (TransformerException e) {
      System.err.println("Error al exportar Archivos. TransformerException");
      System.exit(1);
    } catch (ParserConfigurationException e) {
      System.err.println("Error al exportar Archivos. ParseConfigurarionException");
      System.exit(1);
    } catch (Exception e) {
      System.err.println("Error al exportar Archivos. Exception" +  e.getMessage());
      System.exit(1);
    }
  }
  
  
  // Importación de un almacén
  
  /**
   * Importa los datos de un fichero JSON para añadirlos a nuestro almacén.
   * @param nombre del fichero
   * @throws StockIncorrectoException
   * @throws PrecioNegativoException
   * @throws IOException
   */
  public void importaJSON (String fichero) throws StockIncorrectoException, PrecioNegativoException, IOException {
    // Extraigo JSON del archivo
    BufferedReader bf = new BufferedReader (new FileReader(fichero));
    String json = bf.readLine(); //solo se necesita una línea
    bf.close();
    
//     Convierto JSON en un ArrayList
    Gson gson = new Gson();
    Type ListaArticulosType = new TypeToken<ArrayList<Articulo>>(){}.getType();
    ArrayList<Articulo> articulos = gson.fromJson(json, ListaArticulosType);
    
    // Añado los artículos
    for (Articulo art: articulos) {
      altaArticulo(art.getDescripcion(), art.getPrecioCompra(), art.getPrecioVenta(), art.getNumUds());
    }
    // falta por y convertir en try y excepciones
  }
  
  /**
   * Importa los datos de un fichero CSV para añadirlos a nuestro almacén.
   * @param nombre del fichero
   * @throws NumberFormatException
   * @throws StockIncorrectoException
   * @throws PrecioNegativoException
   */
  public void importaCSV (String fichero) throws NumberFormatException, StockIncorrectoException, PrecioNegativoException {
    try {
      BufferedReader csv = new BufferedReader (new FileReader(fichero));
      String linea = csv.readLine(); // leemos cabecera del csv que no la necesitamos.
      linea = csv.readLine(); // Leemos primera línea
      while (linea != null) {
        String[] campos = linea.split(";");
        // Añado un nuevo artículo con esos campos, obviamos el código del fichero csv
        altaArticulo(campos[1].replace("\"", " "),
            Double.parseDouble(campos[2]),
            Double.parseDouble(campos[3]),
            Integer.parseInt(campos[4]));
        // leo la siguiente línea
        linea = csv.readLine();
      }
      csv.close();
    }catch (IOException e) {
      System.err.println("Error que mostramos");
    }
  }
    
  /**
   * Importa los datos de un fichero XML para añadirlos a nuestro almacén.  
   * @param nombre del fichero
   */
  public void importaXML (String fichero) {
    try {
      // Creamos documento XML
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(new File(fichero)); // sobre este objeto se trabaja.
      doc.getDocumentElement().normalize(); // recomendable por si hubiese nodos vacíos.
      
      // Accedemos a la lista de nodos Artículo
      NodeList articulos = doc.getElementsByTagName("Artículo");
      
      // Recorremos los nodos Artículo
      for (int i=0; i < articulos.getLength(); i++) {
        
        // Nodo Artículo
        Node nodo = articulos.item(i);
        Element articulo = (Element) nodo;
        
        // Campos del artículo
        // Código, es un atributo del nodo anterior, no lo necesitamos, solo para ver como sacarlo.
        //@SuppressWarnings("unused")
        //int codigo = Integer.parseInt(articulo.getAttribute("codigo"));
        
        String descripcion = articulo.getElementsByTagName("Descripcion").item(0).getTextContent();
        double precioCompra = Double.parseDouble(articulo.getElementsByTagName("PrecioCompra").item(0).getTextContent());
        double precioVenta = Double.parseDouble(articulo.getElementsByTagName("PrecioVenta").item(0).getTextContent());
        int stock = Integer.parseInt(articulo.getElementsByTagName("NumUds").item(0).getTextContent());
        
        // Añadimos a Almacén
        altaArticulo(descripcion, precioCompra, precioVenta, stock);        
      }
      
      
    } catch (Exception e) {
      System.err.println("Error al importar archivo" + e.getMessage());
      System.exit(2);
    }
  }
  
  // Sobrecarga de funciones
  
  @Override
  public String toString() {
//    StringBuilder texto = new StringBuilder();
//    for (int i=0; i < this.almacen.size(); i++) {
//      texto.append(almacen.get(i).toString()+"\n\n");
//    }
    
    return almacen.toString();
  }

  @Override
  public Iterator<Articulo> iterator() {
    return almacen.iterator();
  }
}
