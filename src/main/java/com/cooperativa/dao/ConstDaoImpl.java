/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooperativa.dao;

import com.connection.Conexion;
import com.cooperativa.idao.IConstDao;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.connection.Connection;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author ftorrejon
 */
public class ConstDaoImpl implements IConstDao {
  
  private final String NOMBREBD = "cooperativa";
  private final String NOMBRECOL = "constantes";
  
  @Override
  public boolean crearColeccion(String nombreColeccion){
    boolean coleccionCreada = false;
    Conexion conexion = new Conexion();
    MongoClient cliente = null;
    MongoDatabase db = null;
    MongoCollection tabla = null;
    
    try{
      cliente = conexion.conectar();
      db = cliente.getDatabase(NOMBREBD);
      tabla = db.getCollection(NOMBRECOL);
      
      tabla.insertOne(new Document()
              .append("nombreColeccion", nombreColeccion)
              .append("constantes", new ArrayList()));
      
      coleccionCreada = true;
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    cliente.close();
    
    return coleccionCreada;
  }

  @Override
  public boolean agregarConstante(String nombreColeccion, Object nuevoElemento) {
    boolean constanteAgregada = false;
    Conexion conexion = new Conexion();
    MongoClient cliente = null;
    MongoDatabase db = null;
    MongoCollection tabla = null;
    
    try {
      cliente = conexion.conectar();
      db = cliente.getDatabase(NOMBREBD);
      tabla = db.getCollection(NOMBRECOL);
      
      Document updateQuery = new Document()
              .append("nombreColeccion", nombreColeccion);
      Document updateOption = new Document()
              .append("$push",new Document().append("constantes", nuevoElemento));
      
      constanteAgregada = tabla.updateMany(updateQuery,updateOption).wasAcknowledged();
      
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    cliente.close();
    return constanteAgregada;
  }

  @Override
  public List listarColeccion(String nombreColeccion) {
    List listado = null;
    Conexion conexion = new Conexion();
    MongoClient cliente = null;
    MongoDatabase db = null;
    MongoCollection tabla = null;
    
    try {
      cliente = conexion.conectar();
      db = cliente.getDatabase(NOMBREBD);
      tabla = db.getCollection(NOMBRECOL);
      
      Document query = new Document()
              .append("nombreColeccion", nombreColeccion);
      Document resultado = (Document)tabla.find(query).first();
      listado = (List)resultado.get("constantes");
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    cliente.close();
    return listado;
  }

  @Override
  public boolean eliminarConstante(String nombreColeccion, Object cadenaEliminacion) {
    boolean constanteEliminada = false;
    Conexion conexion = new Conexion();
    MongoClient cliente = null;
    MongoDatabase db = null;
    MongoCollection tabla = null;
    
    try {
      cliente = conexion.conectar();
      db = cliente.getDatabase(NOMBREBD);
      tabla = db.getCollection(NOMBRECOL);
      
      Document updateQuery = new Document()
              .append("nombreColeccion", nombreColeccion);
      Document updateOption = new Document()
              .append("$pull",new Document().append("constantes", cadenaEliminacion));
      
      constanteEliminada = tabla.updateMany(updateQuery,updateOption).wasAcknowledged();
      
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    cliente.close();
    return constanteEliminada;
  }

  @Override
  public boolean eliminarColección(String nombreColeccion) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  
}
