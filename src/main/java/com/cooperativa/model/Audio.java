/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooperativa.model;

import java.util.ArrayList;
import java.util.List;
import org.mongodb.morphia.annotations.Embedded;

/**
 *
 * @author Felipe Torrejon (ftorrejon@cooperativa.cl)
 */
public abstract class Audio {
  
  private String idAudio;
  
  private String tipo;
  private int alturaInicio;
  private int alturaTermino;
  
  @Embedded
  private List<String> palabrasClave = new ArrayList<>();
  
  private String descripcion;

  public Audio() {
  }

  public Audio(String tipo) {
    this.tipo = tipo;
  }
  
  public String getIdAudio() {
    return idAudio;
  }

  public void setIdAudio(String idAudio) {
    this.idAudio = idAudio;
  }

  public String getTipo() {
    return tipo;
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

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public List<String> getPalabrasClave() {
    return palabrasClave;
  }
  
  /**
   * Método para caluclar la duración del fragmento descrito.
   * @return int - El valor, en segundos, de la duración del fragmento.
   */
  public int duracionFragmento(){
    return (this.alturaTermino - this.alturaInicio);
  }
  
  /**
   * Método para agregar una palabra clave a la lista de palabras claves.
   * @param pc - String correspondiente a la palabra clave que se insertará en la lista de palabras claves.
   * @return boolean - El valor que confirma que la palabra clave se insertó exitosamente en la lista de palabras claves.
   */
  public boolean agregarPalabraClave(String pc){
    return this.palabrasClave.add(pc);
  }
  
}
