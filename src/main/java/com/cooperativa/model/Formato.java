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
  
  private byte canales;
  private byte profundidadBits;
  private int frecuenciaMuestreo;
  private String codec;
  private short tasaBits;

  public Formato(byte canales, byte profundidadBits, int frecuenciaMuestreo, String codec, short tasaBits) {
    this.canales = canales;
    this.profundidadBits = profundidadBits;
    this.frecuenciaMuestreo = frecuenciaMuestreo;
    this.codec = codec;
    this.tasaBits = tasaBits;
  }
  
}
