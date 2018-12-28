/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooperativa.idao;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe Torrejón (ftorrejon@cooperativa.cl)
 */
public interface IConstDao {
  public boolean crearColeccion(String nombreColeccion);
  public boolean agregarConstante(String nombreColeccion, Object nuevoElemento);
  public List listarColeccion(String nombreColeccion);
  public boolean eliminarConstante(String nombreColeccion, Object cadenaEliminacion);
  public boolean eliminarColeccion(String nombreColeccion);
  
}
