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
public class Archivo {

  private String id;
  private String fechaIngreso;
  private String responsableDigitalizacion;
  private String codigoSoporte;
  private String tipoSoporte;
  private String descripcionExterior;
  private ArrayList<Programa> programas;
  private String nombreArchivo;
  private ArrayList<Cambio> historialCambios;
  private int tamanhoArchivo;
  private int duracionArchivo;
  private Formato formatoArchivo;
  private String fechaDigitalizacion;

  public Archivo() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFechaIngreso() {
    return fechaIngreso;
  }

  public void setFechaIngreso(String fechaIngreso) {
    this.fechaIngreso = fechaIngreso;
  }

  public String getResponsableDigitalizacion() {
    return responsableDigitalizacion;
  }

  public void setResponsableDigitalizacion(String responsableDigitalizacion) {
    this.responsableDigitalizacion = responsableDigitalizacion;
  }

  public String getCodigoSoporte() {
    return codigoSoporte;
  }

  public void setCodigoSoporte(String codigoSoporte) {
    this.codigoSoporte = codigoSoporte;
  }

  public String getTipoSoporte() {
    return tipoSoporte;
  }

  public void setTipoSoporte(String tipoSoporte) {
    this.tipoSoporte = tipoSoporte;
  }

  public String getDescripcionExterior() {
    return descripcionExterior;
  }

  public void setDescripcionExterior(String descripcionExterior) {
    this.descripcionExterior = descripcionExterior;
  }

  public String getNombreArchivo() {
    return nombreArchivo;
  }

  public void setNombreArchivo(String nombreArchivo) {
    this.nombreArchivo = nombreArchivo;
  }

  public int getTamanhoArchivo() {
    return tamanhoArchivo;
  }

  public void setTamanhoArchivo(int tamanhoArchivo) {
    this.tamanhoArchivo = tamanhoArchivo;
  }

  public int getDuracionArchivo() {
    return duracionArchivo;
  }

  public void setDuracionArchivo(int duracionArchivo) {
    this.duracionArchivo = duracionArchivo;
  }

  public Formato getFormatoArchivo() {
    return formatoArchivo;
  }

  public void setFormatoArchivo(Formato formatoArchivo) {
    this.formatoArchivo = formatoArchivo;
  }

  public String getFechaDigitalizacion() {
    return fechaDigitalizacion;
  }

  public void setFechaDigitalizacion(String fechaDigitalizacion) {
    this.fechaDigitalizacion = fechaDigitalizacion;
  }

  public ArrayList<Programa> getProgramas() {
    return programas;
  }

  public ArrayList<Cambio> getHistorialCambios() {
    return historialCambios;
  }
  
  /**
   * Método para agregar un programa a la lista de programas presentes en el Archivo.
   * @param p Programa que se va a agregar a la lista de programas.
   * @return boolean - Valor que confirma si el programa se insertó exitosamente en el listado de programas.
   */
  public boolean agregarPrograma(Programa p){
    return true;
  }
  /**
   * Método para agregar un cambio a la lista de cambios realizados sobre el registro del Archivo en la base de datos.
   * @param c Cambio que se va a agregar a la lista de cambios realizados sobre el registro en la base de datos del Archivo.
   * @return boolean - Valor que confirma que el cambio se insertó exitosamente en el listado de cambios. 
   */
  public boolean agregarCambio (Cambio c){
    return true;
  }
  /**
   * Método para calcular la duración real del material contenido en la lista de programas.
   * @return int - Devuelve el tiempo, en segundos, del material descrito en la lista de programas.
   */
  public int duracionReal() {
    return 0;
  }
  
  /**
   * Método para conocer la cantidad de programas contenidos en el archivo.
   * @return int - Devuelve la cantidad de programas descritos en el listado de programas.
   */
  public int numeroFragmentos() {
    return 0;
  }
  
  

}
