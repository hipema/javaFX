package metalmania.juego;

import java.util.ArrayList;
import java.util.HashMap;

import metalmania.excepciones.ValorIncorrectoExcepcion;

public class Jugador {
  //Estado
  private String nombre;
  private int fama; //posición
  private int dinero;
  private int dadosATirar = 3;

  private int actuacion, grabacion, publicidad;
  private int nivelActuacion, nivelGrabacion, nivelPublicidad;
  

  private boolean actuacionUsada, grabacionUsada, publicidadUsada;
  private boolean haIdoDeFiesta;
  private boolean sinPagar;
  
  private ArrayList<Musico> banda = new ArrayList<Musico>();
  private HashMap<String, String> discografica;
  
  // Constructor
  public Jugador (String nombre) throws ValorIncorrectoExcepcion {
    setNombre(nombre);
    setFama(0);
    setDinero(13);
    setActuacion(0);
    setActuacionUsada(false);
    setGrabacion(0);
    setGrabacionUsada(false);
    setPublicidad(0);
    setPublicidadUsada(false);
    setHaIdoDeFiesta(false);
  }
  
  // Getters y Setters
  public String getNombre() {
    return nombre;
  }

  public int getFama() {
    return fama;
  }

  public int getDinero() {
    return dinero;
  }

  public int getActuacion() {
    return actuacion;
  }

  public boolean isActuacionUsada() {
    return actuacionUsada;
  }

  public int getGrabacion() {
    return grabacion;
  }

  public boolean isGrabacionUsada() {
    return grabacionUsada;
  }

  public int getPublicidad() {
    return publicidad;
  }

  public boolean isPublicidadUsada() {
    return publicidadUsada;
  }
  
  public int getNivelActuacion() {
    return nivelActuacion;
  }

  public int getNivelGrabacion() {
    return nivelGrabacion;
  }

  public int getNivelPublicidad() {
    return nivelPublicidad;
  }

  public void setNivelActuacion(int nivelActuacion) {
    this.nivelActuacion = filtroAcciones(getNivelActuacion()+nivelActuacion);
  }

  public void setNivelGrabacion(int nivelGrabacion) {
    this.nivelGrabacion = filtroAcciones(getNivelGrabacion()+nivelGrabacion);
  }

  public void setNivelPublicidad(int nivelPublicidad) {
    this.nivelPublicidad = filtroAcciones(getNivelPublicidad()+nivelPublicidad);
  }

  public ArrayList<Musico> getBanda() {
    return banda;
  }

  public HashMap<String, String> getDiscografica() {
    return discografica;
  }
  
  public boolean getHaIdoDeFiesta() {
    return haIdoDeFiesta;
  }

  private void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setFama(int fama) throws ValorIncorrectoExcepcion {
    if (fama <0) {
      throw new ValorIncorrectoExcepcion ("La fama no puede ser menor a 0");
    }
    this.fama = fama;
  }

  public void setDinero(int dinero) throws ValorIncorrectoExcepcion {
    if (dinero < 0) {
      throw new ValorIncorrectoExcepcion ("El saldo no puede ser negativo");
    }
    this.dinero = dinero;
  }

  public void setActuacion(int actuacion) {
    this.actuacion = filtroAcciones(actuacion);
  }

  public void setActuacionUsada(boolean actuacionUsada) {
    this.actuacionUsada = actuacionUsada;
  }

  public void setGrabacion(int grabacion) {
    this.grabacion = filtroAcciones(grabacion);
    
  }

  public void setGrabacionUsada(boolean grabacionUsada) {
    this.grabacionUsada = grabacionUsada;
  }

  public void setPublicidad(int publicidad) {
    this.publicidad = filtroAcciones(publicidad);
  }

  public void setPublicidadUsada(boolean publicidadUsada) {
    this.publicidadUsada = publicidadUsada;
  }


  private void setDiscografica(HashMap<String, String> discografica) {
    this.discografica = discografica;
  }
  
  public int getDadosATirar() {
    return dadosATirar;
  }

  public void setDadosATirar(int dadosATirar) throws ValorIncorrectoExcepcion {
    if (dadosATirar < 1 || dadosATirar > 5) {
      throw new ValorIncorrectoExcepcion ("El valor de dadosATirar debe ser entre 1 y 5 puntos, y es " + dadosATirar);
    }
    this.dadosATirar = dadosATirar;
  }


  public void setHaIdoDeFiesta(boolean haIdoDeFiesta) {
    this.haIdoDeFiesta = haIdoDeFiesta;
  }
  
  // Métodos
  
  public void aumentarFama(int valor) throws ValorIncorrectoExcepcion {
    setFama(getFama()+valor);
  }
  
  public void pagar(int valor) throws ValorIncorrectoExcepcion {
    setDinero(getDinero()-valor);
  }
  
  
  
  void accionFama(String accion) {
   // Lanza tantos dados como músicos tiene la banda.
      // Asigna valor de cada dado a un músico
      // Valora si valor dado > valor (acción)
          // Si dado < (acción) -> +Éxito, Fama(pdte. asignar)+ValorDado.
          // Si dado = (acción) -> --
          // Si dado > (acción) -> - Fracaso, EstadoMúsico(true)
      // Cuenta número de Éxitos. Si >= 2. --> Aumenta Fama en la fama pdte. asignar.
  }
  
// DAVID: Lo he comentado porque he cambiado accionBirra y me da un error y me parece que esto no lo vamos a usar así.s
//  void accionMantenimiento (String accion) throws ValorIncorrectoExcepcion {
//    if (accion.compareTo("audicion") == 0) accionAudicion();
//    else if (accion.compareTo("fondos") == 0) accionFondos();
//    else accionBirra();
//  }

  
  public void accionAudicion () {
    // Visualizar los 9 músicos "disponibles"
    // si se desea - alta de nuevo músico
        // si hay más de 4 músicos
            // debe eliminar automáticamente el músico que tenga el mismo instrumento
        // si hay 4 músicos:
            // comprobar si el instrumento está disponible (2º guitarra o teclista)
            // si está disponible, banda pasa a 5 miembros. Sino, se elmina el músico anterior.
  }
  
  void accionFondos() throws ValorIncorrectoExcepcion {
    int valorDado = 0; // se lanza un dado (pendiente implementar)
    setDinero(getDinero()+valorDado+6);
  }
    
/*
  
  -accionFama(String)
  Lanza tantos dados como músicos y pide asociación
  Calculo victorias/derrotas y consecuencias (Cambios de estado / Suma de puntos)

  -accionMantenimiento(String)
    s, llama a métodos del jugador.


  -Dar alta
  -Dar baja
  -Fondos
  -Birras
   si hay dos o tres 6:
    jugador.fiesta() 
  si es 6 Músico.modificarEstado(False)
  si no: Músico.modificarEstado(True)
  -Pagar 

  -fiesta()
    haIdoDeFiesta = True */
  
  // Métodos Auxiliares
  private int filtroAcciones(int valor) {
    if (valor < 0) {
      return 0;
    } else if (valor > 3){
      return 3;
    } else {
      return valor;
    }
  }
  
  // Sobrecarga
  @Override
  public String toString() {
    String texto = "Nombre de la banda: " + this.getNombre() + "\n";
    for (Musico musico : this.banda) {
      texto += musico.toString();
    }
    return texto;      
  }

  public boolean isSinPagar() {
    return sinPagar;
  }

  public void setSinPagar(boolean sinPagar) {
    this.sinPagar = sinPagar;
  }

}
