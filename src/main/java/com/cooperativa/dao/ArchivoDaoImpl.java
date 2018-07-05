/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooperativa.dao;

import com.connection.Conexion;
import com.cooperativa.model.*;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.List;
import org.bson.Document;
import com.cooperativa.idao.IArchivoDao;

/**
 *
 * @author Felipe Torrejon (ftorrejon@cooperativa.cl)
 */
public class ArchivoDaoImpl implements IArchivoDao {

  @Override
  public boolean registrar(Archivo archivo) {
    boolean registrar = false;
    
    MongoClient client = null;
    MongoDatabase database = null;
    MongoCollection<Document> collection = null;
    Document document = null;
    
    try {
      client = Conexion.conectar();
      database = client.getDatabase("cooperativa");
      collection = database.getCollection("archivos");      
      
    } catch (MongoException e) {
      
    }
    
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
