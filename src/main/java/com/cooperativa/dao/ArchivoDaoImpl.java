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
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 *
 * @author Felipe Torrejon (ftorrejon@cooperativa.cl)
 */
public class ArchivoDaoImpl implements IArchivoDao {

  @Override
  public boolean registrar(Archivo archivo) {
    boolean registrar = false;
    
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
      return true;
    
    } catch (MongoException e) {
      System.out.println("ERROR: Clase ArchivoDaoImpl, método registrar");
      e.printStackTrace();
    }
    
    cliente.close();
    
    return registrar;
    
  }

  @Override
  public List<Archivo> obtener() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean actualizar(Archivo archivo) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean eliminar(Archivo archivo) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  
}
