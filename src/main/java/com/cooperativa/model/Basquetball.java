/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooperativa.model;

/**
 *
 * @author Felipe Torrejon (ftorrejon@cooperativa.cl)
 */
public class Basquetball extends Deporte{
  
  private String equipoLocal;
  private String equipoVisita;
  private String marcador;

  public Basquetball() {
    super("Basquetball");
  }

  public String getEquipoLocal() {
    return equipoLocal;
  }

  public void setEquipoLocal(String equipoLocal) {
    this.equipoLocal = equipoLocal;
  }

  public String getEquipoVisita() {
    return equipoVisita;
  }

  public void setEquipoVisita(String equipoVisita) {
    this.equipoVisita = equipoVisita;
  }

  public String getMarcador() {
    return marcador;
  }

  public void setMarcador(String marcador) {
    this.marcador = marcador;
  }
}
