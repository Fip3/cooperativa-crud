/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooperativa.idao;

import com.cooperativa.model.*;
import java.util.List;

/**
 *
 * @author Felipe Torrejon (ftorrejon@cooperativa.cl)
 */
public interface IArchivoDao {
  public boolean registrar(Archivo archivo);
  public List<Archivo> obtenerTodos();
  public List<Archivo> buscar(String cadenaBusqueda);
  public boolean actualizar(Archivo archivo, Programa programa, Audio audio);
  public boolean eliminar(Archivo archivo);
}
