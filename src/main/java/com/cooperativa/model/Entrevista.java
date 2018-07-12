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
public class Entrevista extends Audio{
  @Embedded
  private List<String> periodista = new ArrayList<>();
  
  @Embedded
  private List<Personaje> entrevistado = new ArrayList<>();
  
  @Embedded
  private List<String> tema = new ArrayList<>();

  public Entrevista() {
    super("Entrevista");
  }

  public List<String> getPeriodista() {
    return periodista;
  }

  public List<Personaje> getEntrevistado() {
    return entrevistado;
  }

  public List<String> getTema() {
    return tema;
  }
  
  /**
   * Método para agregar Periodista a la lista de Periodistas.
   * @param p - String que se almacenará en la lista de periodistas.
   * @return boolean - Valor que verifica que el String se insertó correctamente en la lista.
   */
  public boolean agregarPeriodista(String p) {
    return this.periodista.add(p);
  }
  
  /**
   * Método para agregar Entrevistado a la lista de Entrevistados.
   * @param p - String que se almacenará en la lista de entrevistados.
   * @return boolean - Valor que verifica que el String se insertó correctamente en la lista.
   */
  public boolean agregarEntrevistado(Personaje p) {
    return this.entrevistado.add(p);
  }
  
  /**
   * Método para agregar un tema a la lista de Temas
   * @param t - String que se va a agregar a la lista de Temas
   * @return boolean - Valor que confirma que el String se agregó satisfactoriamente  a la lista.
   */
  public boolean agregarTema(String t) {
    return this.tema.add(t);
  }
  
}
