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
public class Tenis extends Deporte{
  private ArrayList<String> jugador;
  private String marcador;
  private boolean dobles;

  public Tenis() {
    super("Tenis");
  }

  public String getMarcador() {
    return marcador;
  }

  public void setMarcador(String marcador) {
    this.marcador = marcador;
  }

  public boolean isDobles() {
    return dobles;
  }

  public void setDobles(boolean dobles) {
    this.dobles = dobles;
  }

  public ArrayList<String> getJugador() {
    return jugador;
  }
  
  /**
   * Método para agregar Jugador a la lista de Jugadores.
   * @param j - String que se almacenará en la lista de jugadores.
   * @return boolean - Valor que verifica que el String se insertó correctamente en la lista.
   */
  public boolean agregarJugador(String j) {
    return true;
  }
  
  
}
