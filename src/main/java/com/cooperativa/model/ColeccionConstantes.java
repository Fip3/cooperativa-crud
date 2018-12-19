/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooperativa.model;

import java.util.ArrayList;
import org.mongodb.morphia.annotations.*;

/**
 *
 * @author ftorrejon
 */
public class ColeccionConstantes {
  
  @Id
  private String id;
  private String nombreColeccion;
  @Embedded
  private ArrayList constantes;
  
  public void ColeccionConstantes(){
  }
  
  public boolean insert(Object o) {
    return this.constantes.add(o);
  }
}
