/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ORDENANDO_POR_NODO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 *
 * @author Limbert Canqui Tambo
 */
public class Nodo {
  public Nodo izquiera = null;
  public Nodo derecha = null;
  public int Dato;
  // Metodo constructor para Nodo
  public Nodo(int Dato){
    this.Dato = Dato;
  }
  // Variables globales
  public static Nodo ini = null ; // inicio
  public static Nodo fin = null ; // final
  public static int total = 0;
  public static void main(String [] args){
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Ingrese 10 numeros, presionando ENTER");
    for(int i=0 ; i<10; i ++){
      // pasamos como parametro el objeto de lectura de datos para que nos devuelva un entero
      addObject(leerInt(br));
    }
    printlist();
  }
  // Funciones o metodos auxiliares
  public static void addObject(int Dato){
    Nodo dato = new Nodo (Dato);
    Nodo apuntador ;
    total ++;
    if(total == 1){
      ini = dato;
      fin = ini;
    }
    else{
      if(fin.Dato <= Dato){
        fin.derecha = dato;
        dato.izquiera = fin;
        fin = dato;
        return;
      }
      if(ini.Dato >= Dato){
       ini.izquiera = dato;
       dato.izquiera = ini;
       ini = dato;
       return;
      }
      apuntador = ini;
      while(apuntador.Dato < Dato){
        apuntador = apuntador.derecha;
      }
      dato.izquiera = apuntador.izquiera;
      apuntador.izquiera.derecha = dato;
      dato.derecha = apuntador;
      apuntador.izquiera = dato;
    }
  }
  public static void printlist(){
    Nodo apuntador = ini;
    int elementos = 0;
    while(apuntador != null){
      System.out.println("Elemento [" + (elementos++) + "]:" + apuntador.Dato);
      apuntador = apuntador.derecha;
    }
  }
  public static int leerInt(BufferedReader buff){
    int lee = 0;
    boolean error;
    do{
      error = false;
      try{
        lee = Integer.parseInt(buff.readLine());
      }
      catch(NumberFormatException n){
        System.err.println("Error, intentelo otra vez");
        error = true;
      }
      catch(IOException e){
        e.printStackTrace(System.err);
      }
    }while(error);
    return lee;
  }
}
