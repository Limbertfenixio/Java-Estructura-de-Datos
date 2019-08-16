/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TELEFONISTA_SECRETARIA;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.ObjectInputStream;
/**
 *
 * @author Limbert Canqui Tambo
 */
public class Telefonista {
  // Desclaramos unas variables globales a este tipo de datos
  public static int PORT = 4567;
  public static int BUFF_SIZE = 40;
  public static int TIMER_SLEEP = 60 * 1000 ;// 60 * 1000ms
  public static int buff_elem = 0;
  public static int [] buffer = new int [BUFF_SIZE];
  public static void main(String [] args){
    // Declaramos la variable socket sera un puntero a un objeto
    Socket skt = (Socket)null;
    // Declaramos vacio el servidor de sockets para inicializarlo
    ServerSocket Ss = (ServerSocket)null;
    // Tratamos de escuchar el puerto definido por la variable PORT
    System.err.println("Escuchando el puerto:" + PORT);
    try{
      Ss = new ServerSocket(PORT);
    }
    catch(IOException e){
      System.out.println("El sistema no permite abrir el puerto");
      System.exit(-1);
    }
    // Si no ocurrio error arriba entonces esperamos a la secretaria
    System.err.println("Esperando conexion....");
    try{
      skt = Ss.accept();
    }
    catch(IOException e1){
      e1.printStackTrace(System.err);
      System.exit(-1);
    }
    // Si no ocurrio error arriba la secretaria esta lista para enviar
    System.err.println("Conectando esperando telefonos...");
    try{
      ObjectInputStream datos = new ObjectInputStream(skt.getInputStream());
      long timer  = 0;
      boolean timer_on = false;
      while(true){
        if((skt.isClosed() && (buff_elem < 1)) || (buffer[0] == -1)){
          // Terminamos el programa si la secretaria termino
          System.err.println("Ultima llamada, nos vamos.....terminando");
          System.exit(0);
        }
        // Si hay telefonos los guardamos
        if(datos.available()>0){
          put_tel(datos.readInt());
        }
        if(timer_on){
          // Si el timer funciona no hacer nada, si se paso pararlo
          if((timer + TIMER_SLEEP)< System.currentTimeMillis()){
            timer_on = false;
          }
        }
        else{
          // Si el timer esta apagado, mostramos un tel si es que hay
          if(buff_elem > 0){
            System.out.println("Secretaria llamando al tel: " + get_tel());
            // Encendemos el timer y guardamos la hora en la que empezo
            timer_on = true;
            timer = System.currentTimeMillis();
          }
        }
        // Pausamos 100ms para no sobrecargar al microprocesador
        try{
          Thread.sleep(1000);
        }
        catch(InterruptedException e2){
        }
      }
    }
    catch(IOException e2){
      e2.printStackTrace(System.err);
      System.exit(-1);
    }
  }
  // Funciones o metodos auxiliares
  public static void put_tel(int tel){
    // Si se supera el espacio producir un error
    if(BUFF_SIZE<(buff_elem+1)){
      System.err.println("Buffer overrun: El buffer se lleno demaciado rapido");
      System.exit(-1);
    }
    // Guardamos el tel y aumentamos en uno el contador
      buffer[buff_elem++] = tel;
  }
  public static int get_tel(){
    // Almacenamos el primer telefono
    int tel = buffer[0];
    // Quitamos uno al contador de elementos
    buff_elem-- ;
    // Recorremos los otros elementos
    for(int i = 0; i < buff_elem; i++){
      buffer[i] = buffer[i+1];
    }
      // Devolvemos el primer telefono
      return tel;
  }
}
