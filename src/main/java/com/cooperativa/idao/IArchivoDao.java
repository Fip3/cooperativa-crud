/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooperativa.idao;

import com.cooperativa.model.Archivo;
import java.util.List;

/**
 *
 * @author Felipe Torrejon (ftorrejon@cooperativa.cl)
 */
public interface IArchivoDao {
  public boolean registrar(Archivo archivo);
  public List<Archivo> obtener();
  public boolean actualizar(Archivo archivo);
  public boolean eliminar(Archivo archivo);
}
