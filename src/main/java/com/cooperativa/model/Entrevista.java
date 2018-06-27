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
public class Entrevista extends Audio{
  private ArrayList<String> periodista;
  private ArrayList<Personaje> entrevistado;
  private ArrayList<String> tema;

  public Entrevista() {
    super("Entrevista");
  }

  public ArrayList<String> getPeriodista() {
    return periodista;
  }

  public ArrayList<Personaje> getEntrevistado() {
    return entrevistado;
  }

  public ArrayList<String> getTema() {
    return tema;
  }
  
  /**
   * Método para agregar Periodista a la lista de Periodistas.
   * @param p - String que se almacenará en la lista de periodistas.
   * @return boolean - Valor que verifica que el String se insertó correctamente en la lista.
   */
  public boolean agregarPeriodista(String p) {
    return true;
  }
  
  /**
   * Método para agregar Entrevistado a la lista de Entrevistados.
   * @param p - String que se almacenará en la lista de entrevistados.
   * @return boolean - Valor que verifica que el String se insertó correctamente en la lista.
   */
  public boolean agregarEntrevistado(Personaje p) {
    return true;
  }
  
  /**
   * Método para agregar un tema a la lista de Temas
   * @param t - String que se va a agregar a la lista de Temas
   * @return boolean - Valor que confirma que el String se agregó satisfactoriamente  a la lista.
   */
  public boolean agregarTema(String t) {
    return true;
  }
  
}
