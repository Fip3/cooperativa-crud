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
  
  private String nombreCompleto;
  private String cargo;
  private String institucion;

  public Personaje(){
  }
  
  public Personaje(String nombreCompleto, String cargo, String institucion) {
    this.nombreCompleto = nombreCompleto;
    this.cargo = cargo;
    this.institucion = institucion;
  }

  public String getNombreCompleto() {
    return nombreCompleto;
  }
  
  public void setNombreCompleto(String nombreCompleto) {
    this.nombreCompleto = nombreCompleto;
  }

  public String getCargo() {
    return cargo;
  }

  public void setCargo(String cargo) {
    this.cargo = cargo;
  }
  
  public String getInstitucion() {
    return institucion;
  } 
  
  public void setInstitucion(String institucion) {
    this.institucion = institucion;
  }
  
  @Override
  public String toString() {
    return (nombreCompleto + ". " + cargo + ", " + institucion);
  }
}
