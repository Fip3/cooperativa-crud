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
public abstract class Deporte extends Audio{
  
  private ArrayList<String> relator;
  private ArrayList<String> locutorComercial;
  private ArrayList<String> encargadoRedesSociales;
  private ArrayList<String> reportero;
  private String disciplina;
  private String competencia;
  private String lugar;

  public Deporte() {
    super("Deporte");
  }
  
  public Deporte(String disciplina) {
    super("Deporte");
    this.disciplina = disciplina;
  }

  public ArrayList<String> getRelator() {
    return relator;
  }

  public ArrayList<String> getLocutorComercial() {
    return locutorComercial;
  }

  public ArrayList<String> getEncargadoRedesSociales() {
    return encargadoRedesSociales;
  }

  public ArrayList<String> getReportero() {
    return reportero;
  }

  public String getDisciplina() {
    return disciplina;
  }

  public String getCompetencia() {
    return competencia;
  }

  public String getLugar() {
    return lugar;
  }

  public void setDisciplina(String disciplina) {
    this.disciplina = disciplina;
  }

  public void setCompetencia(String competencia) {
    this.competencia = competencia;
  }

  public void setLugar(String lugar) {
    this.lugar = lugar;
  }
  
  /**
   * Método para agregar Relator a la lista de Relatores.
   * @param r - String que se almacenará en la lista de relators.
   * @return boolean - Valor que verifica que el String se insertó correctamente en la lista.
   */
  public boolean agregarRelator(String r) {
    return true;
  }
  
  /**
   * Método para agregar Comentarista a la lista de Comentaristas.
   * @param c - String que se almacenará en la lista de comentaristas.
   * @return boolean - Valor que verifica que el String se insertó correctamente en la lista.
   */
  public boolean agregarComentarista(String c) {
    return true;
  }
  
  /**
   * Método para agregar LocutorComercial a la lista de Locutores Comerciales.
   * @param lc - String que se almacenará en la lista de locutores comerciales.
   * @return boolean - Valor que verifica que el String se insertó correctamente en la lista.
   */
  public boolean agregarLocutorComercial(String lc) {
    return true;
  }
  
  /**
   * Método para agregar EncargadoRS a la lista de Encargado Redes Sociales.
   * @param ers - String que se almacenará en la lista de encargado de redes sociales.
   * @return boolean - Valor que verifica que el String se insertó correctamente en la lista.
   */
  public boolean agregarEncargadoRS(String ers) {
    return true;
  }
  
  /**
   * Método para agregar Reportero a la lista de Reporteros.
   * @param r - String que se almacenará en la lista de reporteros.
   * @return boolean - Valor que verifica que el String se insertó correctamente en la lista.
   */
  public boolean agregarReportero(String r) {
    return true;
  }
  
  
  
}
