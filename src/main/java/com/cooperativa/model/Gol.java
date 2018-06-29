/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooperativa.model;

/**
 *
 * @author ftorrejon
 */
public class Gol {
  private int alturaEntrada;
  private int minutoJuego;
  private String jugador;
  private String equipo;
  private String marcadorParcial;

  public Gol() {
  }

  public int getAlturaEntrada() {
    return alturaEntrada;
  }

  public void setAlturaEntrada(int alturaEntrada) {
    this.alturaEntrada = alturaEntrada;
  }

  public int getMinutoJuego() {
    return minutoJuego;
  }

  public void setMinutoJuego(int minutoJuego) {
    this.minutoJuego = minutoJuego;
  }

  public String getJugador() {
    return jugador;
  }

  public void setJugador(String jugador) {
    this.jugador = jugador;
  }

  public String getEquipo() {
    return equipo;
  }

  public void setEquipo(String equipo) {
    this.equipo = equipo;
  }

  public String getMarcadorParcial() {
    return marcadorParcial;
  }

  public void setMarcadorParcial(String marcadorParcial) {
    this.marcadorParcial = marcadorParcial;
  }
  
}
