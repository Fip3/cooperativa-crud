/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooperativa.model;

import java.util.Date;

/**
 *
 * @author Felipe Torrejon (ftorrejon@cooperativa.cl)
 */
public class Cambio {
  
  private int idCambio;
  private String responsableCambio;
  private Date fechaCambio;
  private String descripcion;

  public Cambio() {
    this.fechaCambio = new Date();
  }

  public int getIdCambio() {
    return idCambio;
  }

  public void setIdCambio(int idCambio) {
    this.idCambio = idCambio;
  }

  public String getResponsableCambio() {
    return responsableCambio;
  }

  public void setResponsableCambio(String responsableCambio) {
    this.responsableCambio = responsableCambio;
  }

  public Date getFechaCambio() {
    return fechaCambio;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }
}
