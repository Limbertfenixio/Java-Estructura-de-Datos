/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ASISTENTE_BIBLIOTECARIA;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 *
 * @author Limbert Canqui Tambo
 */
public class Asistente {
  // Declaramos una variables globales a este tipo de datos
  public static int PORT = 4567;
  public static int BUFF_SIZE = 24;
  public static int TIMER_SLEEP = 60*1000;//60*1000ms
  public static int buff_elem = 0;
  public static String [] buffer = new String [BUFF_SIZE];
  public static void main (String [] args){
    // Declaramos la variable socket (sera un apuntador a objeto)
    Socket skt = (Socket)null;
    // Declaramos vacio el servidor de sockets para inicializarllo
    ServerSocket Ss = (ServerSocket)null;
    // Tratamos de escuchar la variable definida por la variable PORT
    System.err.println("Escuchando el puerto " + PORT);
    try{
      Ss = new ServerSocket(PORT);
    }
    catch(IOException ex){
      System.err.println("El sistema no puede abrir el puerto ");
      System.exit(-1);
    }
    // Si no ocurrio error arriba entonces esperamos a la secretaria
    System.err.println("Esperando conexion......");
    try{
      skt = Ss.accept();
    }
    catch(IOException e){
      e.printStackTrace(System.err);
      System.exit(-1);
    }
    // Si no ocurrio error arriba la secretaria esta lista para enviar
    System.err.println("Conectado.......Esperando libros");
    try{
      BufferedReader datos = new BufferedReader(new InputStreamReader((skt.getInputStream())));
      long timer = 0;
      boolean timer_on = false;
      boolean ultimo = false;
      while(true){
        if(!ultimo && (skt.isClosed() || ((buff_elem > 0) && buffer[buff_elem-1] !=null && buffer[buff_elem-1].equals("fin")))){
          // Terminamos el programa si la bibliotecaria termino
          System.err.println("No hay mas, nos vamos cuando terminemos");
          // El libro fin no se debe guardar es el aviso
          buff_elem--;
          ultimo = true;
        }
        if(ultimo && (buff_elem == 0)){
          System.err.println("Ya no es necesario esperar, terminando");
          System.exit(0);
        }
        // Si hay titulos los guardamos
        if(!ultimo && datos.ready()){
          put_tit(datos.readLine());
        }
        if(timer_on){
          // Si el timer funciona no hacer nada, si se paso pararlo
          if((timer + TIMER_SLEEP) < System.currentTimeMillis()){
            timer_on = false;
          }
        }
        else{
          // Si el timer esta apagado, mostramos un tel si es que hay
          if(buff_elem > 0){
            System.out.println("Libro: " + get_tit());
            // Encendemos el timer y guardamos la hora en la que empezo
            timer_on = true;
            timer = System.currentTimeMillis();
          }
        }
        // Pausamos 1000ms para no sobrecargar el procesador
        try{
          Thread.sleep(1000);
        }
        catch(InterruptedException e){ 
        }
      }
    }
    catch(Exception e2){
      e2.printStackTrace(System.err);
      System.exit(-1);
    }
  }
  // Funciones o metodos auxiliares
  public static void put_tit(String tit){
    // Si se supera el espacio producir un error
    if(BUFF_SIZE < (buff_elem+1)){
      System.err.println("Buffer Overrun: El buffer se lleno demasiado rapido");
      System.exit(-1);
    }
    // Guardamos el tel y aumentamos en uno el contador
    buffer[buff_elem ++] = tit;
  }
  public static String get_tit(){
    // Quitamos uno al contador de elementos
    // Devolvemos el ultimo libro
    return buffer[--buff_elem];
  }
}