/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connection;

/**
 *
 * @author ftorrejon
 */

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;
import com.mongodb.Block;
import com.mongodb.MongoClientSettings;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;

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
