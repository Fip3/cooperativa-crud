/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connection;

/**
 *
 * @author Felipe Torrejon (ftorrejon@cooperativa.cl)
 */
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class Conexion {
  public MongoClient conectar(){
    MongoClient mongoClient = null;
    String url = "localhost";
    
    try {
      mongoClient = new MongoClient(url,27017);      
    } catch (Exception e){
      System.out.println("No se pudo conectar a la base de datos");
    }
    return mongoClient;
    
  }
  
  public MongoClient conectar(String usuario, String password){
    MongoClient mongoClient = null;
    MongoClientURI uri = new MongoClientURI("mongodb+srv://"+usuario+":"+password+"@ftcluster-3njpm.mongodb.net/test?retryWrites=true");
    
    try {
      mongoClient = new MongoClient(uri);    
    } catch (Exception e){
      System.out.println("No se pudo conectar a la base de datos");
    }
    return mongoClient;
    
  }
}
