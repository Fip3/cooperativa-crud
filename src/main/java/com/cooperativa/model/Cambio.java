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
public class Cambio {
  
  private String idCambio;
  private String responsableCambio;
  private String fechaCambio;
  private String descripcion;

  public Cambio() {
  }

  public String getIdCambio() {
    return idCambio;
  }

  public void setIdCambio(String idCambio) {
    this.idCambio = idCambio;
  }

  public String getResponsableCambio() {
    return responsableCambio;
  }

  public void setResponsableCambio(String responsableCambio) {
    this.responsableCambio = responsableCambio;
  }

  public String getFechaCambio() {
    return fechaCambio;
  }

  public void setFechaCambio(String fechaCambio) {
    this.fechaCambio = fechaCambio;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }
}
