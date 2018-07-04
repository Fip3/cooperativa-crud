/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooperativa.idao;

import com.cooperativa.model.Programa;
import java.util.List;

/**
 *
 * @author Felipe Torrejon (ftorrejon@cooperativa.cl)
 */
public interface IProgramaDao {
  public boolean registrar(Programa programa);
  public List<Programa> obtener();
  public boolean actualizar(Programa programa);
  public boolean eliminar(Programa programa);
}
