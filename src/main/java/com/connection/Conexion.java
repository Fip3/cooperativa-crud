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
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;

public class Conexion {
  public static MongoClient conectar(){
    MongoClient mongoClient = null;
    String url = "mongodb://localhost:27017";
    
    try {
      mongoClient = MongoClients.create(url);
      if (mongoClient != null){
        System.out.println("Conectado");
      }
    } catch (Exception e){
      System.out.println("No se pudo conectar a la base de datos");
    }
    return mongoClient;
    
  }
}
