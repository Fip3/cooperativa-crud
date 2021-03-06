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
public class Seccion extends Audio{
  
  private String nombre;
  
  @Embedded
  private List<String> panelista = new ArrayList<>();
  
  @Embedded
  private List<String> tema = new ArrayList<>();
  
  @Embedded
  private List<Personaje> invitado = new ArrayList<>();

  public Seccion() {
    super("Seccion");
  }
  
  public Seccion(String idAudio) {
    super(idAudio, "Seccion");
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public List<String> getPanelista() {
    return panelista;
  }

  public List<String> getTema() {
    return tema;
  }
  
  public List<Personaje> getInvitado() {
    return invitado;
  }

  /**
   * Método para agregar Panelista a la lista de Panelistas.
   * @param p - String que se almacenará en la lista de panelistas.
   * @return boolean - Valor que verifica que el String se insertó correctamente en la lista.
   */
  public boolean agregarPanelista(String p) {
    return this.panelista.add(p);
  }
  
  /**
   * Método para agregar un tema a la lista de Temas
   * @param t - String que se va a agregar a la lista de Temas
   * @return boolean - Valor que confirma que el String se agregó satisfactoriamente  a la lista.
   */
  public boolean agregarTema(String t) {
    return this.tema.add(t);
  }
  
  /**
   * Método para agregar Personaje a la lista de Invitados.
   * @param p - Personaje que se almacenará en la lista de panelistas.
   * @return boolean - Valor que verifica que el Personaje se insertó correctamente en la lista.
   */
  public boolean agregarInvitado(Personaje p) {
    return this.invitado.add(p);
  }
}
