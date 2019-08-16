/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ARREGLO_ORDENANDO_POR_BURBUJA;
import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 *
 * @author Limbert Canqui Tambo
 */
public class MetodoBurbuja {
  // Variables globales
  public static int comparaciones = 0;
  public static void main(String [] args){
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int [] listado = new int [10];
    System.out.println("Ingrese 10 numeros, de a uno presionando ENTER"); 
    for(int i = 0;i<10;i++){
      listado [i] = leerInt(br);
    }
    burbuja_mayormenor(listado);
    for(int i = 0;i<10;i++){
      System.out.println("NRO >: [" + i + "]" + listado[i]);
    }
    System.out.println("Comparaciones: " + comparaciones);
    burbuja_menormayor(listado);
    for(int i = 0;i<10;i++){
      System.out.println("NRO >: [" + i + "]" + listado[i]);
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
        System.out.println("Error, vuelva a intentarlo: ");
        error = true;
      }
      catch(Exception e){
        e.printStackTrace(System.err);
      }
    }while(error);
    return lee;
  }
  public static void ordenar_burbuja_mayormenor(int listado []){
    int temporal ;
    for(int j = listado.length -1 ;j>0;j--){
      for(int i = 0; i < j;i++){
        comparaciones ++;
        if(listado[i] < listado[i + 1]){
          temporal = listado [i + 1];
          listado [i + 1] = listado[i];
          listado [i] = temporal;
        }
      }
    }  
  }
  public static void burbuja_mayormenor(int listado []){
    int temporal;
    for(int j=0;j<listado.length - 1;j++){
      for(int i = 0;i<listado.length - 1;i++){
        comparaciones ++;
        if(listado[i] < listado[i + 1]){
          temporal = listado[i + 1];
          listado [i + 1] = listado [i];
          listado [i] = temporal;
        }
      }
    }
  }
  public static void ordenar_burbuja_menormayor(int listado []){
    int temporal ;
    for(int j = 0;j < listado.length - 1;j++){
      for(int i = listado.length; i > 0;i--){
        comparaciones ++;
        if(listado[i] < listado[i - 1]){
          temporal = listado [i - 1];
          listado [i + 1] = listado[i];
          listado [i] = temporal;
        }
      }
    }  
  }
  public static void burbuja_menormayor(int listado []){
    int temporal;
    for(int j = 0; j < listado.length - 1; j ++){
      for(int i = listado.length -1; i>0;i--){
        comparaciones ++;
        if(listado [i] < listado [i - 1]){
          temporal = listado [i -1];
          listado [i - 1] = listado [i];
          listado [i] = temporal;
        }
      }
    }
  }
}
