package ficherosYParametros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class quita_comentarios {

  public static void main(String[] args) {
    
    if (args.length != 2) {
      System.err.println("Error en el número de parámetros"); 
      System.exit(1);
    }
    
    String ficheroOrigen = args[0];
    String ficheroDestino = args[1];
    
    try {
      BufferedReader bOrigen = new BufferedReader(new FileReader(ficheroOrigen));
      BufferedWriter bDestino = new BufferedWriter(new FileWriter(ficheroDestino));
      
      boolean estoyEnBloqueComentario = false; 
      String linea = bOrigen.readLine();
      while (linea != null) {
        boolean escribeLinea = true;
        if (estoyEnBloqueComentario) {
          if (linea.trim().endsWith("*/")) {
            estoyEnBloqueComentario = false;
          }
        } else if (linea.trim().startsWith("/*")) {
          estoyEnBloqueComentario = true;
        } else {
          
          if (linea.contains("
            int dondeEmpiezaBarra2 = linea.indexOf("
            linea = linea.substring(0, dondeEmpiezaBarra2);
            if (linea.trim().equals("")) {
              escribeLinea = false;
            }
          }
          if (escribeLinea && !estoyEnBloqueComentario) {
            bDestino.write(linea);
            bDestino.newLine(); 
          }  
        }
       
        linea = bOrigen.readLine();
        }
      System.out.println("Archivo sin comentarios creado.");
      bOrigen.close();
      bDestino.close();
      
    } catch (FileNotFoundException e){
      System.err.println("No se encuentra el archivo.");
      System.exit(1);
    } catch (IOException e) {
      System.err.println("Error de entrada/salida al manejar el fichero");
      System.exit(2);
    }
  }
}
