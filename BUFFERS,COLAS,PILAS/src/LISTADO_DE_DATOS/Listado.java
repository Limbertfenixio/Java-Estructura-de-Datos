/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LISTADO_DE_DATOS;
import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 *
 * @author Limbert Canqui Tambo
 */
public class Listado {
  // Variables Globales
  public static int MAX = 15;
  public static String lista [] = new String [MAX];
  public static int lista_elem = 0;
  public static void main(String [] args){
    BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
    int op = -1;
    while(true){
      switch(op){
        case 1:
          System.out.println("Dato ");
          ingresa(leerLinea(teclado));
          break;
        case 2:
          listar();
          break;
        case 3:
          System.out.println("Item a borrar: ");
          borrar(opcion(teclado));
          break;
        case 0:
          System.out.println("Terminando");
          System.exit(0);
          break;
      }
      if(op!=1){
        imprimir_menu();
      }
      System.out.println("Opcion: ");
      op = opcion(teclado);
    }
  }
  // Funciones o metodos auxiliares
  public static void ingresa(String dato){
    lista[lista_elem++] = dato;
  }
  public static void listar(){
    for(int i = 0;i<lista_elem;i++){
      System.out.println("Item[" + i + "]:[" + lista [i] + "]");
    }
  }
  public static void borrar(int item){
    lista_elem --;
    for(int i = item;i<lista_elem;i++){
      lista[i] = lista[i+1];
    }
  }
  public static void imprimir_menu(){
    System.out.println("SELECCIONE UNA OPCION: ");
    System.out.println("1. Ingresar un elemento al listado");
    System.out.println("2. Listar los elementos de la lista");
    System.out.println("3. Borrar un elemento de la lista");
    System.out.println("0. Salir");
  }
  public static int opcion(BufferedReader buff){
    int lee = 0;
    boolean error ;
    do{
      error = false;
      try{
        return lee = Integer.parseInt(buff.readLine());
      }
      catch(NumberFormatException n){
        System.err.println("Entrada erronea, repetir: ");
        error = true;
      }
      catch(Exception ex){
        ex.printStackTrace(System.err);
      }
    }while(error);
    return lee;
  }
  public static String leerLinea(BufferedReader buff){
    try{
      return buff.readLine();
    }
    catch(Exception ex){
      ex.printStackTrace(System.err);
    }
    return "";
  }
}
