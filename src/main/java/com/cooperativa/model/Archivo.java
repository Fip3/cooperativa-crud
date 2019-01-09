/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooperativa.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Id;

/**
 *
 * @author Felipe Torrejon (ftorrejon@cooperativa.cl)
 */
public class Archivo {

  @Id
  private String id;
  
  private Date fechaIngreso;
  private String responsableIngreso;
  private String responsableDigitalizacion;
  private String codigoSoporte;
  private String tipoSoporte;
  private String descripcionExterior;
  
  @Embedded
  private List<Programa> programas = new ArrayList();
  
  private String nombreArchivo;
  
  @Embedded
  private List<Cambio> historialCambios = new ArrayList();
  
  private int tamanhoArchivo;
  private int duracionArchivo;
  private Formato formatoArchivo;
  private Date fechaDigitalizacion;

  public Archivo() {
  }

  public Archivo(Date fechaIngreso, String responsableIngreso) {
    this.fechaIngreso = fechaIngreso;
    this.responsableIngreso = responsableIngreso;
  }
  
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Date getFechaIngreso() {
    return fechaIngreso;
  }

  public String getResponsableIngreso() {
    return responsableIngreso;
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

  public Date getFechaDigitalizacion() {
    return fechaDigitalizacion;
  }

  public void setFechaDigitalizacion(Date fechaDigitalizacion) {
    this.fechaDigitalizacion = fechaDigitalizacion;
  }

  public List<Programa> getProgramas() {
    return programas;
  }

  public List<Cambio> getHistorialCambios() {
    return historialCambios;
  }
  
  /**
   * Método para agregar un programa a la lista de programas presentes en el Archivo.
   * @param p Programa que se va a agregar a la lista de programas.
   * @return boolean - Valor que confirma si el programa se insertó exitosamente en el listado de programas.
   */
  public boolean agregarPrograma(Programa p){
    return this.programas.add(p);
  }
  /**
   * Método para agregar un cambio a la lista de cambios realizados sobre el registro del Archivo en la base de datos.
   * @param c Cambio que se va a agregar a la lista de cambios realizados sobre el registro en la base de datos del Archivo.
   * @return boolean - Valor que confirma que el cambio se insertó exitosamente en el listado de cambios. 
   */
  public boolean agregarCambio (Cambio c){
    return this.historialCambios.add(c);
  }
  /**
   * Método para calcular la duración real del material contenido en la lista de programas.
   * @return int - Devuelve el tiempo, en segundos, del material descrito en la lista de programas.
   */
  public int duracionReal() {
    int total = 0;
    
    for(Programa p: this.programas){
      total =+ p.duracionPrograma();
    }
    
    return total;
  }
  
  /**
   * Método para conocer la cantidad de fragmentos contenidos en el archivo.
   * @return int - Devuelve la cantidad de fragmentos descritos en el listado de programas.
   */
  public int numeroFragmentos() {
    int total = this.programas.size();
    
    for(Programa p: programas){
      total =+ p.getFragmentos().size();
    }
    
    return total;
  }

}
