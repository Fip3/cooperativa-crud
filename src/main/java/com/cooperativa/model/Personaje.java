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
public class Personaje {
  
  private final String nombreCompleto;
  private final String cargo;
  private final String institucion;

  public Personaje(String nombreCompleto, String cargo, String institucion) {
    this.nombreCompleto = nombreCompleto;
    this.cargo = cargo;
    this.institucion = institucion;
  }

  public String getNombreCompleto() {
    return nombreCompleto;
  }

  public String getCargo() {
    return cargo;
  }

  public String getInstitucion() {
    return institucion;
  } 
}
