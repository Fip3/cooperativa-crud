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
public class Informe extends Audio{
  private ArrayList<String> periodista;
  private ArrayList<Personaje> cunha;
  private ArrayList<String> tema;
  private String lugar;

  public Informe() {
    super("Informe");
  }

  public String getLugar() {
    return lugar;
  }

  public void setLugar(String lugar) {
    this.lugar = lugar;
  }

  public ArrayList<String> getPeriodista() {
    return periodista;
  }

  public ArrayList<Personaje> getCunha() {
    return cunha;
  }

  public ArrayList<String> getTema() {
    return tema;
  }
  
  /**
   * Método para agregar Periodista a la lista de Periodistas.
   * @param p - String que se almacenará en la lista de periodistas.
   * @return boolean - Valor que verifica que el String se insertó correctamente en la lista.
   */
  public boolean agregarPeriodista(String p){
    return true;
  }
  
  /**
   * Método para agregar Personaje a la lista de Cuñas.
   * @param c - Personaje que se almacenará en la lista de cuñas.
   * @return boolean - Valor que verifica que el Personaje se insertó correctamente en la lista.
   */
  public boolean agregarCunha(String c){
    return true;
  }
  
  /**
   * Método para agregar un tema a la lista de Temas
   * @param t - String que se va a agregar a la lista de Temas
   * @return boolean - Valor que confirma que el String se agregó satisfactoriamente  a la lista.
   */
  public boolean agregarTema(String t){
    return true;
  }
  
}
