package metalmania.juego;

import java.util.ArrayList;
import java.util.HashMap;

import metalmania.excepciones.ValorIncorrectoExcepcion;

/**
 * Clase con la información nesaria de cada carta de músico:
 * Valores enteros: inspiración, carisma, marcha, reputación, estado.
 * Valores String: nombre, estilo, instrumento en uso.
 * ArrayList de diccionario (instrumento, valor): ejecución
 * 
 */

public class Musico {
  //Estado
  private String nombre;
  private int inspiracion;
  private int carisma;
  private int marcha;
  private int reputacion;
  private String estilo;
  private String instrumentoUso;
  private int ejecucionUso;
  private HashMap<String, Integer> ejecucion = new HashMap<String, Integer>();
  private int estado;
  
  // Constructor
  Musico (String nombre, int inspiracion, int carisma, int marcha, int reputacion, String estilo, HashMap<String, Integer> ejecucion) throws ValorIncorrectoExcepcion {
      setNombre(nombre);
      setInspiracion(inspiracion);
      setCarisma(carisma);
      setMarcha(marcha);
      setReputacion(reputacion);
      setEstilo(estilo);
      setEjecucion(ejecucion);
      setEstado(1);
      setInstrumentoUso("Ninguno");
  }
  // Constructor para la carta Reverso
  public Musico (String nombre) throws ValorIncorrectoExcepcion {
      setNombre(nombre);
      setInspiracion(1);
      setCarisma(1);
      setMarcha(1);
      setReputacion(1);
      setEstilo("HR");
      HashMap<String, Integer> ejecucion = new HashMap<String, Integer>();
      ejecucion.put("Guitarra Eléctrica", 1);
      ejecucion.put("Teclado", 1);
      setEjecucion(ejecucion);
      setInstrumentoUso("Ninguno");
      setEstado(1);
  }
 
  // getters y setters
  public String getNombre() {
    return nombre;
  }


  /**
   * Devuelve valor de Inspiración
   * @return 
   */
  public int getInspiracion() {
    return inspiracion;
  }


  /**
   * Devuelve valor de Carisma
   * @return
   */
  public int getCarisma() {
    return carisma;
  }


  /**
   * Devuelve valor de Marcha
   * @return
   */
  public int getMarcha() {
    return marcha;
  }


  /**
   * Devuelve valor de Reputación
   * @return
   */
  public int getReputacion() {
    return reputacion;
  }


  /**
   * Devuelve valor de Estilo
   * @return
   */
  public String getEstilo() {
    return estilo;
  }


  /**
   * Devuelve instrumento en uso
   * @return
   */
  public String getInstrumentoUso() {
    return instrumentoUso;
  }
  
  
  /**
   * Devuelve valor ejecucion instrumento en uso
   * @return
   */
  public int getEjecucionUso () {
    return ejecucionUso;
  }


  /**
   * Devuelve diccionario con instrumentos y valor ejecución con los mismos.
   * @return
   */
  public HashMap<String, Integer> getEjecucion() {
    return ejecucion;
  }


  /**
   * Devuelve valor del estado.
   * @return
   */
  public int getEstado() {
    return estado;
  }
  /**
   * Devuelve valor del estado como texto.
   * @return
   */
  public String getEstadoString() {
    switch (estado) {
    case 1:
      return "activo";
    case 2:
      return "conflictivo";
    case 3:
      return "inactivo";
    }
    return null;
  }


  /**
   * Modifica el atributo Nombre de Músico.
   * @param nombre
   */
  private void setNombre(String nombre) {
    // Sólo se puede utilizar en el constructor de la clase.
    this.nombre = nombre;
  }


  /**
   * Modifica valor de Inspiración del músico.
   * @param inspiracion entero con valor entre 1 y 5.
   * @throws ValorIncorrectoExcepcion, genera excepción para valores inválidos.
   */
  private void setInspiracion(int inspiracion) throws ValorIncorrectoExcepcion {
    if (inspiracion < 1 || inspiracion > 5) {
      throw new ValorIncorrectoExcepcion ("El valor de inspiración debe ser entre 1 y 5 puntos.");
    }
    this.inspiracion = inspiracion;
  }


  /**
   * Modifica valor de Carisma del músico.
   * @param carisma, entero con valor entre 1 y 5.
   * @throws ValorIncorrectoExcepcion, genera excepción para valores inválidos.
   */
  private void setCarisma(int carisma) throws ValorIncorrectoExcepcion {
    if (carisma < 1 || carisma > 5) {
      throw new ValorIncorrectoExcepcion ("El valor de carisma debe ser entre 1 y 5 puntos.");
    }
    this.carisma = carisma;
  }


  /**
   * Modifica valor de Marcha del músico.
   * @param marcha, entero con valor entre 1 y 3.
   * @throws ValorIncorrectoExcepcion, genera excepción para valores inválidos.
   */
  private void setMarcha(int marcha) throws ValorIncorrectoExcepcion {
    if (marcha < 1 || marcha > 3) {
      throw new ValorIncorrectoExcepcion ("El valor de marcha debe ser entre 1 y 3 puntos.");
    }
    this.marcha = marcha;
  }


  /**
   * Modifica valor de Reputación del músico.
   * @param reputacion, entero con valor entre 1 y 3.
   * @throws ValorIncorrectoExcepcion, genera excepción para valores inválidos.
   */
  private void setReputacion(int reputacion) throws ValorIncorrectoExcepcion {
    // sólo se utiliza desde el constructor.
    if (reputacion < 1 || reputacion > 5) {
      throw new ValorIncorrectoExcepcion ("El valor de reputación debe ser entre 1 y 5 puntos.");
    }
    this.reputacion = reputacion;
  }


  /**
   * Modifica valor de Estilo del músico.
   * @param estilo, valores válidos "HR", "HM", "EX".
   * @throws ValorIncorrectoExcepcion, genera excepción para valores inválidos.
   */
  private void setEstilo(String estilo) throws ValorIncorrectoExcepcion {
    // Sólo se utiliza desde el constructor.
    if (estilo.compareTo("HR") !=0 && estilo.compareTo("HM") != 0 && estilo.compareTo("EX") !=0) {
      throw new ValorIncorrectoExcepcion ("Los estilos deben ser \"HR\", \"HM\" o \"EX\".");
    }
    this.estilo = estilo;
  }


  /**
   * Modifica valor del Instrumento en uso del músico.
   * @param instrumentoUso, valores válidos "Guitarra", "Bajo", "Voz", "Teclado", "Batería".
   * @throws ValorIncorrectoExcepcion, genera excepción para valores inválidos.
   */
  public void setInstrumentoUso(String instrumentoUso) throws ValorIncorrectoExcepcion {
    if (instrumentoUso.compareTo("Ninguno") !=0 && instrumentoUso.compareTo("Guitarra") !=0 && 
        instrumentoUso.compareTo("Bajo") != 0 && instrumentoUso.compareTo("Voz") !=0 && 
        instrumentoUso.compareTo("Teclado") !=0 && instrumentoUso.compareTo("Batería") !=0 && 
        instrumentoUso.compareTo("Guitarra Eléctrica") !=0) {
      throw new ValorIncorrectoExcepcion ("El instrumento debe ser \"Guitarra\", \"Bajo\", \"Voz\", \"Teclado\", \"Batería\" o \"Guitarra Eléctrica\".");
    }
    
    this.instrumentoUso = instrumentoUso;
    
    if (instrumentoUso.compareTo("Ninguno") !=0) { 
      setEjecucionUso(ejecucion.get(instrumentoUso)); 
    
    } else {
      setEjecucionUso(1);
    }
  }

  public void setEjecucionUso (int ejecucion) throws ValorIncorrectoExcepcion {
    if (ejecucion < 1 || ejecucion > 5) {
      throw new ValorIncorrectoExcepcion ("El valor de Ejecución debe ser entre 1 y 5 puntos.");
    }
    this.ejecucionUso = ejecucion;
  }

  /**
   * Modifica valor de Instrumentos y destreza con los mismos del músico.
   * @param ejecucion, diccionario con Instrumentos y valor de ejecución.
   */
  public void setEjecucion(HashMap<String, Integer> ejecucion) {
    this.ejecucion = ejecucion;
  }


  /**
   * Modifica valor de Estado del músico
   * @param estado, valor entero entre 1 y 3.
   * @throws ValorIncorrectoExcepcion, genera excepción para valores inválidos.
   */
  private void setEstado(int estado){
    if (estado < 1) {
      this.estado = 1;
    } else if (estado > 3) {
      this.estado = 3;
    } else {
      this.estado = estado;
    }      
  }
  
  // Métodos
  /**
   * Modifica el estado.
   *
   * Excepción ValorIncorrectoException mantiene el valor del estado.
   */
  public boolean modificarEstado (int valor) {
    int estadoInicial = getEstado();
    setEstado(getEstado()+valor);
    if (estadoInicial != getEstado()) {
      return true;
    } else {
      return false;
    }
  }
  
  /**
   * Modifica el estado del músico a Activo.
   */
  public void estadoActivo() throws ValorIncorrectoExcepcion {
    setEstado(1);
  }
  
  public void estadoConflictivo() throws ValorIncorrectoExcepcion {
    setEstado(2);
  }
  
  public int accionBirra (ArrayList<Dado> dados) {
    int contadorDeSeis = 0;
    for (int i = 0; i < this.getMarcha(); i++ ) {
      if (dados.get(i).getCara() == 6) contadorDeSeis++;
    }
    return contadorDeSeis;
  }
  
  /**
   * Lee la tarjeta de discográfica y modifica los atributos del músico.
   * @param discografica
   * @throws ValorIncorrectoExcepcion 
   */
  void leerDiscografica(HashMap<String, String> discografica) throws ValorIncorrectoExcepcion {
    // Desarollar la lectura.
    if (discografica.get("estilo").compareTo(getEstilo()) == 0){
      int inspiracion = getInspiracion()+Integer.parseInt(discografica.get("inspiracion"));
      int carisma = getCarisma()+Integer.parseInt(discografica.get("carisma"));
      int ejecucionUso = getEjecucionUso()+Integer.parseInt(discografica.get("ejecucion"));
      
      if (inspiracion < 1) setInspiracion(1);
      else if (inspiracion > 5) setInspiracion(5);
      else setInspiracion(inspiracion);
      
      if (carisma < 1) setCarisma(1);
      else if (carisma > 5) setCarisma(5);
      else setCarisma(carisma);
      
      if (ejecucionUso < 1) setEjecucionUso(1);
      else if (ejecucionUso > 5) setEjecucionUso(5);
      else setEjecucionUso(ejecucionUso);
    }
  }


  // Sobrecarga
  @Override
  public String toString() {
    return "Nombre: " + this.getNombre() + "\n" +
           "Inspiración: " + this.getInspiracion() + "\n" +
           "Carisma: " + this.getCarisma() + "\n" +
           "Marcha: " + this.getMarcha() + "\n" +
           "Reputación: " + this.getReputacion() + "\n" +
           "Ejecución: " + this.ejecucion.toString() + "\n" +
           "Instrumento en Uso: " + this.instrumentoUso + "\n" +
           "Estado: " + this.getEstado() + "\n";
  }
    
  
}
