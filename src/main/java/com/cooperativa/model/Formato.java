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
public class Formato {
  
  private int canales;
  private int profundidadBits;
  private int frecuenciaMuestreo;
  private String codec;
  private int tasaBits;

  public Formato() {
  }

  public int getFrecuenciaMuestreo() {
    return frecuenciaMuestreo;
  }

  public void setFrecuenciaMuestreo(int frecuenciaMuestreo) {
    this.frecuenciaMuestreo = frecuenciaMuestreo;
  }

  public int getCanales() {
    return canales;
  }

  public void setCanales(int canales) {
    this.canales = canales;
  }

  public int getProfundidadBits() {
    return profundidadBits;
  }

  public void setProfundidadBits(int profundidadBits) {
    this.profundidadBits = profundidadBits;
  }

  public String getCodec() {
    return codec;
  }

  public void setCodec(String codec) {
    this.codec = codec;
  }

  public int getTasaBits() {
    return tasaBits;
  }

  public void setTasaBits(int tasaBits) {
    this.tasaBits = tasaBits;
  }
  
  
  
}
