/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ASISTENTE_BIBLIOTECARIA;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
/**
 *
 * @author Limbert Canqui Tambo
 */
public class Bibliotecaria {
  // Declaramos unas variables globales a este tipo de datos
  public static int PORT = 4567;
  public static String HOST = "127.0.0.1";
  public static void main(String [] args){
    System.err.println("Intentando conectar con la asistente");
    Socket skt = (Socket)null;
    try{
      skt = new Socket(HOST,PORT);
    }
    catch(Exception e){
      System.err.println("La asistente no esta en linea");
      System.exit(-1);
    }
    String titulo;
    BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
    try{
      PrintWriter datos = new PrintWriter(skt.getOutputStream());
      System.err.println("Nos conectamos con la asistente: " + HOST + ":" + PORT);
      System.err.println("Ingrese Titulos (Linea vacia termina)");
      while(true){
        if((titulo = leerLinea(teclado)).length() == 0){
          System.err.println("Programa terminado");
          datos.println("Fin");
          datos.flush();
          datos.close();
          System.exit(0);
        }
        else{
          datos.println(titulo);
          datos.flush();
        }
      }
    }
    catch(IOException e){
      e.printStackTrace(System.err);
    }
  }
  // Funciones o metodos auxiliares
  public static String leerLinea(BufferedReader buff){
    try{
      return buff.readLine();
    }
    catch(Exception e){
      e.printStackTrace(System.err);
    }
    return "";
  }
}
