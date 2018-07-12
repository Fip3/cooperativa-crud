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
public class Futbol extends Deporte{
  
  private String equipoLocal;
  private String equipoVisita;
  private String marcador;
  
  @Embedded
  private List<Gol> gol = new ArrayList<>();

  public Futbol() {
    super("Futbol");
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
  
  public List<Gol> getGol() {
    return gol;
  }
  
  /**
   * Método para agregar Gol a la lista de Goles.
   * @param j - String que se almacenará en la lista de goles.
   * @return boolean - Valor que verifica que el String se insertó correctamente en la lista.
   */
  public boolean agregarGol(Gol g) {
    return this.gol.add(g);
  }
  
  
}
