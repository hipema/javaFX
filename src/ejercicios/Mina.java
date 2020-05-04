package ejercicios;

import javafx.scene.control.Button;

public class Mina extends Button {
// Atributos
  private int estado;
  private boolean esMina;
  private int contador;

  // Constructor
  Mina() { 
    setEstado(0);
    setEsMina(false);
    setContador(0);
  }

  public int getEstado() {
    return estado;
  }

  public boolean isEsMina() {
    return esMina;
  }

  public void setEstado(int estado) {
    this.estado = estado;
  }

  public void setEsMina(boolean esMina) {
    this.esMina = esMina;
  }

  public int getContador() {
    return contador;
  }

  public void setContador(int contador) {
    this.contador = contador;
  }
}
