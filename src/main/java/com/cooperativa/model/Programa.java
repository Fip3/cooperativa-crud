/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooperativa.model;

import java.util.ArrayList;

/**
 *
 * @author Felipe Torrejon (ftorrejon@cooperativa.cl)
 */
public class Programa {
  
  private String idPrograma;
  private int alturaInicio;
  private int alturaTermino;
  private String nombrePrograma;
  private ArrayList<String> conductor;
  private ArrayList<Audio> fragmentos;
  private String fechaEmision;

  public Programa() {
  }

  public String getIdPrograma() {
    return idPrograma;
  }

  public void setIdPrograma(String idPrograma) {
    this.idPrograma = idPrograma;
  }

  public int getAlturaInicio() {
    return alturaInicio;
  }

  public void setAlturaInicio(int alturaInicio) {
    this.alturaInicio = alturaInicio;
  }

  public int getAlturaTermino() {
    return alturaTermino;
  }

  public void setAlturaTermino(int alturaTermino) {
    this.alturaTermino = alturaTermino;
  }

  public String getNombrePrograma() {
    return nombrePrograma;
  }

  public void setNombrePrograma(String nombrePrograma) {
    this.nombrePrograma = nombrePrograma;
  }

  public String getFechaEmision() {
    return fechaEmision;
  }

  public void setFechaEmision(String fechaEmision) {
    this.fechaEmision = fechaEmision;
  }

  public ArrayList<String> getConductor() {
    return conductor;
  }

  public ArrayList<Audio> getFragmentos() {
    return fragmentos;
  }
  
  /**
   * Método para conocer la duración del programa.
   * @return int - El valor, en segundos, de la duración del programa descrito.
   */
  public int duracionPrograma(){
    return 0;
  }
  
  /**
   * Método para agregar un conductor a la lista de conductores del programa.
   * @param c String correspondiente al nombre completo del conductor del programa descrito.
   * @return boolean - valor que confirma que el conductor se agregó exitosamente a la lista de conductores.
   */
  public boolean agregarConductor(String c){
    return true;
  }
  
  /**
   * Método para agregar un Audio a la lista de fragmentos del programa.
   * @param f Audio que será agregado a la lista de fragmentos del programa.
   * @return boolean - valor que confirma que el audio se agregó exitosamente a la lista de fragmentos.
   */
  public boolean agregarFragmento(Audio f) {
    return true;
  }
}
