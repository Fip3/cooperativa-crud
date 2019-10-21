/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooperativa.vista;

import com.cooperativa.dao.ArchivoDaoImpl;
import com.cooperativa.model.Noticia;
import com.cooperativa.model.Programa;
import java.util.Date;

/**
 *
 * @author ftorrejon
 */
public class ViewArchivo {
  
  public static void main(String args[]){
    ArchivoDaoImpl archivoDao = new ArchivoDaoImpl();
    
    Programa programa = new Programa("4");
    
    programa.setAlturaInicio(1230);
    programa.setFechaEmision(new Date());
    programa.setNombrePrograma("programa");
    programa.agregarConductor("CONDUCTOR");
    //programa.agregarFragmento(new Noticia("0"));
    
    //archivoDao.insertarPrograma("0000", new Programa());
    if(archivoDao.modificarPrograma("0000", 3, programa)){
      System.out.println("programa agregado");
    }
    
    
    
  }
}
