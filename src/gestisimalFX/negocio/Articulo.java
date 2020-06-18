package gestisimalFX.negocio;
import gestisimalFX.excepciones.PrecioNegativoException;
import gestisimalFX.excepciones.StockIncorrectoException;

public class Articulo {
  //Estado
  private static int contador=1;
  private int codigo;
  private String descripcion;
  private double precioCompra;
  private double precioVenta;
  private int numUds;
  
  // Constructor
  Articulo (String descripcion, double precioCompra, double precioVenta, int numUds) throws StockIncorrectoException, PrecioNegativoException { 
    setDescripcion(descripcion);
    setPrecioCompra(precioCompra);
    setPrecioVenta(precioVenta);
    setNumUds(numUds);
    this.codigo = generaCodigo(); 
  }
  
  /**
   * Constructor para la búsqueda de artículos
   * @param codigoArticulo
   */
  Articulo (int codigoArticulo){
    this.codigo = codigoArticulo;
  }
  
  // Getters y Setters
  public String getDescripcion() {
    return descripcion;
  }
  
  public int getCodigo() {
    return codigo;
  }

  public double getPrecioCompra() {
    return precioCompra;
  }

  public double getPrecioVenta() {
    return precioVenta;
  }

  public int getNumUds() {
    return numUds;
  }

  void setDescripcion (String descripcion) {
    this.descripcion = descripcion;
  }

  void setPrecioCompra(double precioCompra) throws PrecioNegativoException {
    if (precioCompra < 0) {
      throw new PrecioNegativoException("El precio no puede ser negativo.");
    }
    this.precioCompra = precioCompra;
  }

  void setPrecioVenta(double precioVenta) throws PrecioNegativoException {
    if (precioVenta < 0) {
      throw new PrecioNegativoException("El precio no puede ser negativo.");
    }
    this.precioVenta = precioVenta;
  }
  private void setNumUds (int numUds) throws StockIncorrectoException {
    if (numUds < 0) {
      throw new StockIncorrectoException ("El stock no puede ser negativo.");
    }
    this.numUds = numUds;
  }
  
  // Métodos de la clase
  void entraMercancia (int numUds) throws StockIncorrectoException{
    setNumUds(this.getNumUds() + numUds);
  }
  void saleMercancia (int numUds) throws StockIncorrectoException{
    setNumUds(this.getNumUds() - numUds);
  }
  
  private int generaCodigo () {
    return contador++;
  }
  
  // Sobrecarga de funciones
  @Override
  public String toString() { 
    return "Código del artículo: " + this.codigo + "\nDescripción: "
        + this.descripcion + "\nPrecio de compra: " + this.precioCompra + " €"
        + "\nPrecio de venta: " + this.precioVenta + " €" + "\nNúmero de unidades: " + this.numUds + "\n\n";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + codigo;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Articulo other = (Articulo) obj;
    if (codigo != other.codigo)
      return false;
    return true;
  }
  
}
