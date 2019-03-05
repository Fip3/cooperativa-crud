/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooperativa.model;

import java.util.ArrayList;
import java.util.List;
import org.mongodb.morphia.annotations.Embedded;

/**
 *
 * @author Felipe Torrejon (ftorrejon@cooperativa.cl)
 */
public abstract class Deporte extends Audio{
  
  @Embedded
  private List<String> relator = new ArrayList<>();
  
  @Embedded
  private List<String> comentarista = new ArrayList<>();
  
  @Embedded
  private List<String> locutorComercial = new ArrayList<>();
  
  @Embedded
  private List<String> encargadoRedesSociales = new ArrayList<>();
  
  @Embedded
  private List<String> reportero = new ArrayList<>();
  
  private String disciplina;
  private String competencia;
  private String lugar;

  public Deporte() {
    super("Deporte");
  }
  
  public Deporte(String idAudio) {
    super(idAudio, "Deporte");
  }
  
  public Deporte(String idAudio, String disciplina) {
    super(idAudio, "Deporte");
    this.disciplina = disciplina;
  }

  public List<String> getRelator() {
    return relator;
  }
  
  public List<String> getComentarista() {
    return comentarista;
  }

  public List<String> getLocutorComercial() {
    return locutorComercial;
  }

  public List<String> getEncargadoRedesSociales() {
    return encargadoRedesSociales;
  }

  public List<String> getReportero() {
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
    return this.relator.add(r);
  }
  
  /**
   * Método para agregar Comentarista a la lista de Comentaristas.
   * @param c - String que se almacenará en la lista de comentaristas.
   * @return boolean - Valor que verifica que el String se insertó correctamente en la lista.
   */
  public boolean agregarComentarista(String c) {
    return this.comentarista.add(c);
  }
  
  /**
   * Método para agregar LocutorComercial a la lista de Locutores Comerciales.
   * @param lc - String que se almacenará en la lista de locutores comerciales.
   * @return boolean - Valor que verifica que el String se insertó correctamente en la lista.
   */
  public boolean agregarLocutorComercial(String lc) {
    return this.locutorComercial.add(lc);
  }
  
  /**
   * Método para agregar EncargadoRS a la lista de Encargado Redes Sociales.
   * @param ers - String que se almacenará en la lista de encargado de redes sociales.
   * @return boolean - Valor que verifica que el String se insertó correctamente en la lista.
   */
  public boolean agregarEncargadoRS(String ers) {
    return this.encargadoRedesSociales.add(ers);
  }
  
  /**
   * Método para agregar Reportero a la lista de Reporteros.
   * @param r - String que se almacenará en la lista de reporteros.
   * @return boolean - Valor que verifica que el String se insertó correctamente en la lista.
   */
  public boolean agregarReportero(String r) {
    return this.reportero.add(r);
  }
}
