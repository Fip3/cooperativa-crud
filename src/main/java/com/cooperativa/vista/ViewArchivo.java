/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooperativa.vista;

import com.cooperativa.dao.ArchivoDaoImpl;
import com.cooperativa.model.Archivo;

/**
 *
 * @author ftorrejon
 */
public class ViewArchivo {
  public static void main(String args[]){
    Archivo archivo = new Archivo();
    ArchivoDaoImpl dao = new ArchivoDaoImpl();
    
    archivo.setId("5555");
    System.out.println(dao.crearArchivo(archivo));
    
  }
  
}
