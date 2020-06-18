package gestisimalFX.excepciones;

public class PrecioNegativoException extends Exception {

  public PrecioNegativoException (String mensaje) {
    super(mensaje);
  }

}