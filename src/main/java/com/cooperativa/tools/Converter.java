/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooperativa.tools;

/**
 * Conjunto de herramintas anexas
 * 
 * @author ftorrejon
 */
public class Converter {
  
  public static int[] deSegundosAHms(int tiempoSegundos){
    int[] tiempoHms = new int[3];
    tiempoHms[0] = tiempoSegundos / 3600;
    tiempoHms[1] = (tiempoSegundos % 3600) / 60;
    tiempoHms[2] =  (tiempoSegundos % 3600) % 60;
    return tiempoHms;
  }
  
  public static int deHmsASegundos(int horas, int minutos, int segundos){
    int tiempoSegundos = horas * 3600 + minutos * 60 + segundos;
    return tiempoSegundos;
  }
  
}
