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

  public Formato(){
  }
  
  public Formato(byte canales, byte profundidadBits, int frecuenciaMuestreo, String codec, short tasaBits) {
    this.canales = canales;
    this.profundidadBits = profundidadBits;
    this.frecuenciaMuestreo = frecuenciaMuestreo;
    this.codec = codec;
    this.tasaBits = tasaBits;
  }

  public byte getCanales() {
    return canales;
  }

  public void setCanales(byte canales) {
    this.canales = canales;
  }

  public byte getProfundidadBits() {
    return profundidadBits;
  }

  public void setProfundidadBits(byte profundidadBits) {
    this.profundidadBits = profundidadBits;
  }

  public int getFrecuenciaMuestreo() {
    return frecuenciaMuestreo;
  }

  public void setFrecuenciaMuestreo(int frecuenciaMuestreo) {
    this.frecuenciaMuestreo = frecuenciaMuestreo;
  }

  public String getCodec() {
    return codec;
  }

  public void setCodec(String codec) {
    this.codec = codec;
  }

  public short getTasaBits() {
    return tasaBits;
  }

  public void setTasaBits(short tasaBits) {
    this.tasaBits = tasaBits;
  }

}
