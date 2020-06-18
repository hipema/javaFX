package metalmania.juego;

import java.util.ArrayList;
import java.util.Arrays;

import metalmania.excepciones.ValorIncorrectoExcepcion;

public class Partida {
  
  private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
  
  
  Partida (ArrayList<String> bandas){
    for (String banda : bandas) {
//      jugadores.add(new Jugador(banda));
    }
  }
  
  void inicioTurno(Jugador jugador) {
    boolean hayInactivo = false;
    
    jugador.setHaIdoDeFiesta(false);
    jugador.setActuacionUsada(false);
    jugador.setGrabacionUsada(false);
    jugador.setPublicidadUsada(false);
    for (Musico musico : jugador.getBanda()) {
      if (musico.getEstado() == 3) {
//        jugador.accionMantenimiento(Teclado.leerCadena("audicion/fondos/birras: ")); //Preguntar opción
        hayInactivo = true;
        break;
      }
    }
    if (!hayInactivo) {
      int dado1 = 0; // Dados aleatorios
      int dado2 = 0;
//      this.evento(jugador, dado1, dado2);//Evento, se tiran dos dados aleatorios
    }
//    jugador.accionMantenimiento(Teclado.leerCadena("audicion/fondos/birras: "));
    
    if (jugador.getDinero() < jugador.getBanda().size()) {
      // No tiene dinero para pagar a todos ¿Quien no cobra este mes?
      
    } else {
//      jugador.pagar(jugador.getBanda().size());
    }
    
  }
  
  void evento(Jugador jugador, int dado1, int dado2) throws ValorIncorrectoExcepcion {
    String respuesta = "S";
    int valor = 2;
    int total = dado1 + dado2;
    while (respuesta.compareTo("N") != 0 || jugador.getDinero() < 2) {
//      respuesta = Teclado.leerCadena("Tu evento es el " + total + "¿Quieres pagar 2$ para alterar el resultado? (S/N)").toUpperCase();
      if (respuesta.compareTo("S") == 0) {
        do {
          
//          valor = Teclado.leerNumeroEntero("Inserta un 1 para el siguiente evento, un -1 para el anterior o 0 para cancelar.");
            
          if (total + valor < 2) {
            System.err.println("Te encuentras en el evento 2, el más bajo. No está permitida esta opción.");
            valor = 2; //Le ponemos este número para que no salga del bucle y vuelva a preguntarte la opción.
          }
          else if (total + valor > 12) {
            System.err.println("Te encuentras en el evento 12, el más alto. No está permitida esta opción.");
            valor = 2; //Le ponemos este número para que no salga del bucle y vuelva a preguntarte la opción.
          }
        
          if (valor == 1 || valor == -1) {
            jugador.pagar(2);
          }
        } while (valor != -1 && valor != 1 && valor != 0);
        
        total += valor;
      }
    }
    // Búsqueda del evento. Total debe estar entre 2 y 12
    llamarEvento(jugador, total);
  }
  
  void llamarEvento(Jugador jugador, int total) {
    switch (total) {
    case 2:
      // felicidades(jugador); // Todos pierden 2$ menos el jugador que gana 6.
      break;
    case 3:
//      jugador.pagar(-4); // Ganas 4 dólares
      break;
    case 4:
      // escandalo(jugadorQueTuQuieras); // El jugador que elijas pierde 4 de fama.
      break;
    case 5:
//      jugador.setFama(jugador.getFama() + 2); // El jugador gana 2 de fama
      break;
    case 6:
      // gafado(); // Pasas a conflictivo a un músico de otro jugador
      break;
    case 7:
      jugador.accionAudicion(); //
      break;
    case 8:
      // laVidaEsBella(jugador); // Pasa a activo uno de tus músicos.
      break;
    case 9:
      // deudasDeJuego(); // Otro jugador pierde 4 dólares
      break;
    case 10:
      try {
        jugador.pagar(-2);
      } catch (ValorIncorrectoExcepcion e) {
        e.printStackTrace();
      }
      break;
    case 11:
      // juegoDeBarras(jugador); // Eliges a otro jugador para irte de birras con él.
      break;
    case 12:
      for (Jugador deBirras : this.jugadores) {
//        deBirras.accionBirra();
      }
      break;
    }
    
  }

}
