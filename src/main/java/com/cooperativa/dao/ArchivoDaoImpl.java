/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooperativa.dao;

import com.connection.Conexion;
import com.cooperativa.model.*;
import com.mongodb.MongoException;
import com.mongodb.MongoClient;
import java.util.List;
import com.cooperativa.idao.IArchivoDao;
import org.bson.Document;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;


/**
 *
 * @author Felipe Torrejon (ftorrejon@cooperativa.cl)
 */
public class ArchivoDaoImpl implements IArchivoDao {

  @Override
  public boolean crearArchivo(Archivo archivo) {
    boolean registrado = false;
    
    Conexion conexion = new Conexion();
    MongoClient cliente = null;
    Morphia morphia = null;
    
    try {
      cliente = conexion.conectar();
      morphia = new Morphia();
      morphia.mapPackage("com.cooperativa.model");
      final Datastore datastore = morphia.createDatastore(cliente, "cooperativa");
      datastore.ensureIndexes(true);
      
      datastore.save(archivo);
      registrado = true;
    
    } catch (MongoException e) {
      System.out.println("ERROR: Clase ArchivoDaoImpl, método crearArchivo");
      e.printStackTrace();
    }
    
    cliente.close();
    
    return registrado;
    
  }
  
  @Override
  public boolean insertarPrograma(String idArchivo, Programa programa) {
    boolean registrado = false;
    
    Conexion conexion = new Conexion();
    MongoClient cliente = null;
    Morphia morphia = null;
    
    try {
      cliente = conexion.conectar();
      morphia = new Morphia();
      morphia.mapPackage("com.cooperativa.model");
      final Datastore datastore = morphia.createDatastore(cliente, "cooperativa");
      //datastore.ensureIndexes(true);
      
      Query<Archivo> updateQuery = datastore.createQuery(Archivo.class)
              .filter("_id ==", idArchivo);
            
      UpdateOperations updateOperations = datastore.createUpdateOperations(Archivo.class)
              .push("programas", programa);
      
      registrado = datastore.updateFirst(updateQuery, updateOperations).getUpdatedExisting();
      
    } catch (MongoException e) {
      System.out.println("ERROR: Clase ArchivoDaoImpl, método insertarPrograma");
      e.printStackTrace();
      return registrado;
    }
    
    cliente.close();
    
    return registrado;
  }
  
  @Override
  public boolean insertarAudio(String idArchivo, String idPrograma, Audio audio) {
    boolean registrado = false;
    
    Conexion conexion = new Conexion();
    MongoClient cliente = null;
    Morphia morphia = null;
    
    try {
      cliente = conexion.conectar();
      morphia = new Morphia();
      morphia.mapPackage("com.cooperativa.model");
      final Datastore datastore = morphia.createDatastore(cliente, "cooperativa");
      //datastore.ensureIndexes(true);
      
      Query<Archivo> updateQuery = datastore.createQuery(Archivo.class)
              .filter("_id ==", idArchivo)
              .filter("programas.idPrograma ==", idPrograma);
      
      UpdateOperations updateOperations = datastore.createUpdateOperations(Archivo.class)
              .push("programas.$.fragmentos", audio);
      
      registrado = datastore.updateFirst(updateQuery, updateOperations).getUpdatedExisting();
      
    } catch (MongoException e) {
      System.out.println("ERROR: Clase ArchivoDaoImpl, método insertarAudio");
      e.printStackTrace();
      return registrado;
    }
    
    cliente.close();
    
    return true;
  }

  @Override
  public List<Archivo> obtenerTodos() {
    Conexion conexion = new Conexion();
    MongoClient cliente = null;
    Morphia morphia = null;
    List<Archivo> resultado = null;
    
    try {
      cliente = conexion.conectar();
      morphia = new Morphia();
      morphia.mapPackage("com.cooperativa.model");
      final Datastore datastore = morphia.createDatastore(cliente, "cooperativa");
      
      final Query<Archivo> consulta = datastore.createQuery(Archivo.class);
      resultado = consulta.asList();
      
    } catch (MongoException e){
      System.out.println("ERROR: Clase ArchivoDaoImpl, método obtenerTodos");
      e.printStackTrace();
    }
    
    cliente.close();
    return resultado;
  }
  
  @Override
  public boolean modificarArchivo(Archivo archivo){
    Conexion conexion = new Conexion();
    MongoClient cliente = null;
    Morphia morphia = null;
    boolean archivoActualizado = false;
    
    try {
      cliente = conexion.conectar();
      morphia = new Morphia();
      morphia.mapPackage("com.cooperativa.model");
      final Datastore datastore = morphia.createDatastore(cliente, "cooperativa");
      
      Query<Archivo> updateQuery = datastore.createQuery(Archivo.class)
              .filter("_id ==",archivo.getId());
      
      UpdateOperations updateOperations = datastore.createUpdateOperations(Archivo.class)
              .set("fechaIngreso", archivo.getFechaIngreso())
              .set("responsableDigitalizacion", archivo.getResponsableDigitalizacion())
              .set("codigoSoporte", archivo.getCodigoSoporte())
              .set("tipoSoporte", archivo.getTipoSoporte())
              .set("descripcionExterior", archivo.getDescripcionExterior())
              .set("nombreArchivo", archivo.getNombreArchivo())
              .set("tamanhoArchivo", archivo.getTamanhoArchivo())
              .set("duracionArchivo", archivo.getDuracionArchivo())
              .set("fechaDigitalizacion", archivo.getFechaDigitalizacion());
      
      archivoActualizado = datastore.updateFirst(updateQuery, updateOperations).getUpdatedExisting();
      
    } catch (MongoException e){
      System.out.println("ERROR: Clase ArchivoDaoImpl, método modificarArchivo");
      e.printStackTrace();
    }
    
    cliente.close();
    return archivoActualizado;
  }
  
  @Override
  public boolean modificarPrograma(String idArchivo, int indicePrograma, Programa programa){
    Conexion conexion = new Conexion();
    MongoClient cliente = null;
    Morphia morphia = null;
    boolean programaActualizado = false;
    
    try {
      cliente = conexion.conectar();
      morphia = new Morphia();
      morphia.mapPackage("com.cooperativa.model");
      final Datastore datastore = morphia.createDatastore(cliente, "cooperativa");
      
      Query<Archivo> updateQuery = datastore.createQuery(Archivo.class)
              .filter("_id ==", idArchivo);
      
      String programaAModificar = "programa." + indicePrograma;
      UpdateOperations updateOperations = datastore.createUpdateOperations(Archivo.class)
              .set(programaAModificar + ".idPrograma", programa.getIdPrograma())
              .set(programaAModificar + ".alturaInicio", programa.getAlturaInicio())
              .set(programaAModificar + ".alturaTermino", programa.getAlturaTermino())
              .set(programaAModificar + ".nombrePrograma", programa.getNombrePrograma())
              .set(programaAModificar + ".fechaEmision", programa.getFechaEmision());
      
      programaActualizado = datastore.updateFirst(updateQuery, updateOperations).getUpdatedExisting();
      
    } catch (MongoException e){
      System.out.println("ERROR: Clase ArchivoDaoImpl, método modificarPrograma");
      e.printStackTrace();
    }
    
    cliente.close();
    
    return programaActualizado;
  }

  @Override
  public boolean modificarAudio(String idArchivo, int indicePrograma, int indiceAudio, Audio audio) {
    Conexion conexion = new Conexion();
    MongoClient cliente = null;
    Morphia morphia = null;
    boolean audioActualizado = false;
    
    
    try {
      cliente = conexion.conectar();
      morphia = new Morphia();
      morphia.mapPackage("com.cooperativa.model");
      final Datastore datastore = morphia.createDatastore(cliente, "cooperativa");
      
      Query<Archivo> updateQuery = datastore.createQuery(Archivo.class)
              .filter("_id ==", idArchivo);
      
      UpdateOperations updateOperation = datastore.createUpdateOperations(Archivo.class)
              .set("programas." + indicePrograma + ".fragmentos." + indiceAudio, audio);
      
      audioActualizado = datastore.updateFirst(updateQuery, updateOperation).getUpdatedExisting();
      
    } catch (MongoException e) {
      System.out.println("ERROR: Clase ArchivoDaoImpl, método actualizar");
      e.printStackTrace();
    }
    
    cliente.close();
    return audioActualizado;
  }

  @Override
  public List<Archivo> buscar(String cadenaBusqueda) {
    Conexion conexion = new Conexion();
    MongoClient cliente = null;
    Morphia morphia = null;
    List<Archivo> resultado = null;
    
    try {
      cliente = conexion.conectar();
      morphia = new Morphia();
      morphia.mapPackage("com.cooperativa.model");
      final Datastore datastore = morphia.createDatastore(cliente, "cooperativa");
      
      final Query<Archivo> consulta = datastore.createQuery(Archivo.class);
      
      resultado = consulta
              .search(cadenaBusqueda)
              .asList();
      
    } catch (MongoException e){
      System.out.println("ERROR: Clase ArchivoDaoImpl, método buscar");
      e.printStackTrace();
    }
    
    return resultado;
  }
  
  @Override
  public boolean eliminarArchivo(String idArchivo) {
    Conexion conexion = new Conexion();
    MongoClient cliente = null;
    Morphia morphia = null;
    boolean audioEliminado = false;
    
    try {
      cliente = conexion.conectar();
      morphia = new Morphia();
      morphia.mapPackage("com.cooperativa.model");
      
      final Datastore datastore = morphia.createDatastore(cliente, "cooperativa");
      
      final Query<Archivo> deleteQuery = datastore.createQuery(Archivo.class);
      
      audioEliminado = datastore.delete(deleteQuery).isUpdateOfExisting();
      
    } catch (MongoException e){
      System.out.println("ERROR: Clase ArchivoDaoImpl, método eliminarArchivo");
      e.printStackTrace();
    }
    
    cliente.close();
    return audioEliminado;
  } 
  
  @Override
  public boolean eliminarPrograma(String idArchivo, String idPrograma) {
    Conexion conexion = new Conexion();
    MongoClient cliente = null;
    Morphia morphia = null;
    boolean programaEliminado = false;
    
    try {
      cliente = conexion.conectar();
      morphia = new Morphia();
      morphia.mapPackage("com.cooperativa.model");
      
      Datastore datastore = morphia.createDatastore(cliente, "cooperativa");
      
      Query<Archivo> updateQuery = datastore.createQuery(Archivo.class)
              .filter("_id ==", idArchivo);
      
      UpdateOperations updateOperations = datastore.createUpdateOperations(Archivo.class)
              .removeAll("programas", new Document("programa", idPrograma));
      
      programaEliminado = datastore.updateFirst(updateQuery, updateOperations).getUpdatedExisting();
      
    } catch (MongoException e) {
      System.out.println("ERROR: Clase ArchivoDaoImpl, método eliminarPrograma");
    }
    
    cliente.close();
    return programaEliminado;
  }
  
  @Override
  public boolean eliminarAudio(String idArchivo, int indicePrograma, String idAudio) {
    Conexion conexion = new Conexion();
    MongoClient cliente = null;
    Morphia morphia = null;
    boolean audioEliminado = false;
    
    try {
      cliente = conexion.conectar();
      morphia = new Morphia();
      morphia.mapPackage("com.cooperativa.model");
      
      Datastore datastore = morphia.createDatastore(cliente, "cooperativa");
      
      Query<Archivo> updateQuery = datastore.createQuery(Archivo.class)
              .filter("_id ==", idArchivo);
      
      UpdateOperations updateOperations = datastore.createUpdateOperations(Archivo.class)
              .removeAll("programas." + indicePrograma + ".fragmentos", new Document("idAudio",idAudio));
      
      audioEliminado = datastore.updateFirst(updateQuery, updateOperations).getUpdatedExisting();
      
    } catch (MongoException e) {
      System.out.println("ERROR: Clase ArchivoDaoImpl, método eliminarAudio");
    }
    
    cliente.close();
    return audioEliminado;
  } 
}
