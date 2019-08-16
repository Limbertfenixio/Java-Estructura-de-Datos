/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TELEFONISTA_SECRETARIA;
import java.net.Socket;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
/**
 *
 * @author Limbert Canqui Tambo
 */
public class Secretaria {
  // Declaramos unas variables globales a este tipo de datos
  public static int PORT = 4567;
  public static String HOST="127.0.0.1";
  public static void main(String [] args){
    System.err.println("Intentando conectar con la telefonista");
    Socket skt = (Socket)null;
    try{
      skt = new Socket(HOST, PORT);
    }
    catch(Exception e){
      System.err.println("La telefonista no esta en linea");
      System.exit(-1);
    }
    int tel;
    BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
    try{
      ObjectOutputStream datos  = new ObjectOutputStream(skt.getOutputStream());
      System.err.println("Nos conectamos con la telefonista" + HOST + ":" + PORT);
      System.err.println("Ingrese numeros -1 termina");
      while(true){
        if((tel = leerInt(teclado)) == -1){
          System.err.println("Programa terminado");
          datos.writeInt(-1);
          datos.flush();
          datos.close();
          skt.close();
          System.exit(0);
        }
        else{
          datos.writeInt(tel);
          datos.flush();
        }
      }
    }
    catch(IOException e1){
      e1.printStackTrace(System.err);
    }
  }
  // Funciones o metodos auxiliares
  public static int leerInt(BufferedReader buff){
    int lee = 0;
    boolean error ;
    do{
      error = false;
      try{
        lee = Integer.parseInt(buff.readLine());
      }
      catch(NumberFormatException n){
        System.err.println("Entrada erronea repetir");
        error = true;
      }
      catch(Exception ex){
        ex.printStackTrace(System.err);
      }
    } while (error);
    return lee;
  }
}
