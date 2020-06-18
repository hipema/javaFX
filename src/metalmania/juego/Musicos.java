package metalmania.juego;

import java.util.ArrayList;
import java.util.HashMap;

import metalmania.excepciones.ValorIncorrectoExcepcion;

public class Musicos {
  public static ArrayList<Musico> insertarMusicos () throws ValorIncorrectoExcepcion {
    ArrayList<Musico> listaMusicos = new ArrayList<Musico>();
    HashMap<String, Integer> ejecucion = new HashMap<String, Integer>();
    
    ejecucion.put("Voz", 2);
    listaMusicos.add(new Musico("Axel Browse", 3, 5, 2, 2, "HR", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Guitarra",4);
    ejecucion.put("Voz",2);
    ejecucion.put("Guitarra Eléctrica", 3);
    listaMusicos.add(new Musico("Dave Mustang", 5, 2, 3, 3, "EX", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Bajo", 3);
    listaMusicos.add(new Musico("Steve Diyork", 3, 4, 1, 2, "EX", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Batería", 5);
    listaMusicos.add(new Musico("Dave Bombard", 3, 3, 3, 3, "EX", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Voz", 3);
    ejecucion.put("Guitarra Eléctrica", 3);
    listaMusicos.add(new Musico("Ann Pilson", 3, 4, 1, 2, "HR", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Teclado", 5);
    listaMusicos.add(new Musico("Tom Airey", 2, 3, 1, 2, "HR", ejecucion));

    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Bajo", 2);
    listaMusicos.add(new Musico("Buff Macanan", 4, 3, 2, 1, "HR", ejecucion));

    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Bajo", 5);
    listaMusicos.add(new Musico("Joe Dimayo", 3, 3, 2, 3, "HM", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Guitarra", 3);
    listaMusicos.add(new Musico("Kirk Hammer", 4, 3, 2, 2, "EX", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Batería", 3);
    listaMusicos.add(new Musico("Scott Travers", 3, 3, 2, 1, "HM", ejecucion));

    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Voz", 4);
    listaMusicos.add(new Musico("Tarja Turner", 3, 3, 1, 2, "HM", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Voz", 2);
    listaMusicos.add(new Musico("Orry Osbourne", 5, 4, 3, 3, "HM", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Guitarra", 2);
    ejecucion.put("Guitarra Eléctrica", 2);
    listaMusicos.add(new Musico("Armand Caster", 4, 3, 1, 1, "HM", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Voz", 4);
    ejecucion.put("Guitarra Eléctrica", 2);
    listaMusicos.add(new Musico("Michael Kish", 2, 4, 1, 2, "HM", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Voz", 3);
    ejecucion.put("Guitarra Eléctrica", 2);
    listaMusicos.add(new Musico("Paul Stanler", 4, 3, 2, 2, "HR", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Voz", 2);
    ejecucion.put("Teclado", 4);
    listaMusicos.add(new Musico("David Defense", 3, 2, 1, 1, "HM", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Voz", 2);
    ejecucion.put("Bajo", 3);
    listaMusicos.add(new Musico("Peter Still", 4, 3, 3, 2, "EX", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Batería", 3);
    listaMusicos.add(new Musico("Hans Ulrich", 4, 4, 3, 3, "EX", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Guitarra", 5);
    ejecucion.put("Voz", 2);
    ejecucion.put("Guitarra Eléctrica", 2);
    listaMusicos.add(new Musico("Kyle Hanser", 3, 3, 2, 3, "HM", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Voz", 3);
    listaMusicos.add(new Musico("John Tarrdi", 4, 3, 1, 2, "EX", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Voz", 2);
    ejecucion.put("Guitarra Eléctrica", 4);
    listaMusicos.add(new Musico("Jon Safell", 4, 2, 1, 2, "EX", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Bajo", 3);
    listaMusicos.add(new Musico("David Ellenson", 3, 2, 2, 1, "EX", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Voz", 3);
    listaMusicos.add(new Musico("Jack Billy", 3, 3, 2, 1, "EX", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Batería", 3);
    listaMusicos.add(new Musico("Micky Deer", 4, 3, 2, 1, "HM", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Guitarra", 3);
    ejecucion.put("Voz", 2);
    listaMusicos.add(new Musico("Lita Fork", 3, 3, 1, 1, "HR", ejecucion));

    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Guitarra", 3);
    ejecucion.put("Teclado", 2);
    listaMusicos.add(new Musico("Troy Azatoth", 4, 4, 2, 3, "EX", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Voz", 2);
    ejecucion.put("Bajo", 3);
    listaMusicos.add(new Musico("Gin Simons", 3, 4, 3, 2, "HR", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Guitarra", 4);
    listaMusicos.add(new Musico("Splash", 3, 3, 3, 2, "HR", ejecucion));

    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Batería", 4);
    listaMusicos.add(new Musico("Gene Hogam", 2, 4, 1, 2, "EX", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Batería", 2);
    listaMusicos.add(new Musico("Tommy Link", 3, 4, 2, 1, "HR", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Voz", 2);
    ejecucion.put("Teclado", 4);
    listaMusicos.add(new Musico("Jon Oliver", 5, 2, 2, 3, "HM", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Guitarra", 3);
    listaMusicos.add(new Musico("Agus John", 3, 5, 3, 3, "HR", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Voz", 5);
    listaMusicos.add(new Musico("Rob Halforck", 2, 4, 3, 3, "HM", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Guitarra", 2);
    ejecucion.put("Guitarra Eléctrica", 3);
    listaMusicos.add(new Musico("Michael Weimar", 5, 3, 2, 3, "HM", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Batería", 4);
    listaMusicos.add(new Musico("Mike Perrana", 3, 3, 2, 2, "HM", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Batería", 4);
    listaMusicos.add(new Musico("Mike Portnel", 4, 2, 1, 2, "HR", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Voz", 4);
    listaMusicos.add(new Musico("Bruce Vickinson", 3, 4, 2, 3, "HM", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Guitarra", 3);
    ejecucion.put("Guitarra Eléctrica", 3);
    listaMusicos.add(new Musico("Adrian Speed", 4, 3, 1, 2, "HM", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Voz", 3);
    ejecucion.put("Bajo", 3);
    ejecucion.put("Guitarra Eléctrica", 2);
    listaMusicos.add(new Musico("Blackie Loveless", 4, 4, 3, 3, "HM", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Teclado", 3);
    listaMusicos.add(new Musico("Jan Wirman", 4, 3, 2, 2, "EX", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Bajo", 4);
    listaMusicos.add(new Musico("Steve Parrish", 5, 2, 2, 3, "HM", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Batería", 3);
    listaMusicos.add(new Musico("Ringo McBrain", 2, 5, 1, 2, "HM", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Batería", 3);
    listaMusicos.add(new Musico("Alec Van Haley", 5, 3, 3, 3, "HR", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Bajo", 2);
    ejecucion.put("Teclado", 2);
    listaMusicos.add(new Musico("Jon Poljon", 3, 5, 2, 2, "HR", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Guitarra", 4);
    ejecucion.put("Guitarra Eléctrica", 3);
    listaMusicos.add(new Musico("Dave Hurray", 2, 4, 1, 2, "HM", ejecucion));

    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Teclado", 3);
    listaMusicos.add(new Musico("Jens Hanson", 2, 4, 1, 1, "HM", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Bajo", 4);
    listaMusicos.add(new Musico("John Miau", 2, 3, 1, 1, "HR", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Guitarra", 4);
    listaMusicos.add(new Musico("Alex Scolding", 3, 3, 2, 2, "EX", ejecucion));
    
    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Voz", 2);
    ejecucion.put("Guitarra Eléctrica", 5);
    listaMusicos.add(new Musico("James Hotfield", 3, 3, 3, 3, "EX", ejecucion));

    ejecucion = new HashMap<String, Integer>();
    ejecucion.put("Voz", 4);
    listaMusicos.add(new Musico("Doro Peck", 3, 2, 1, 1, "HR", ejecucion));
    
    return listaMusicos;
  }
}