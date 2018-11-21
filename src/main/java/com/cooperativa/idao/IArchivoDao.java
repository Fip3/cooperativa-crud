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
  public boolean crearArchivo(Archivo archivo);
  public boolean insertarPrograma(String idArchivo, Programa programa);
  public boolean insertarAudio(String idArchivo, String idPrograma, Audio audio);
  public List<Archivo> obtenerTodos();
  public List<Archivo> buscar(String cadenaBusqueda);
  public boolean modificarArchivo(Archivo archivo);
  public boolean modificarPrograma(String idArchivo, int indicePrograma, Programa programa);
  public boolean modificarAudio(String idArchivo, int indicePrograma, int indiceAudio, Audio audio);
  public boolean eliminarArchivo(String archivo);
  public boolean eliminarPrograma(String idArchivo, String idPrograma);
  public boolean eliminarAudio(String idArchivo, int indicePrograma, String idAudio);
  public boolean agregarCambio(String idArchivo, Cambio cambio);
  public boolean modificarCambio(String idArchivo, Cambio cambio);
  public List<Cambio> listarCambios(String idArchivo);
  public boolean borrarCambio(String idArchivo, String idCambio);
}
