/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooperativa.vista;

import com.cooperativa.dao.*;
import com.cooperativa.model.*;
import java.awt.Component;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author ftorrejon
 */
public class VentanaBusqueda extends javax.swing.JFrame {

  
  //declaracion de atributos personalizados
  private byte contadorArchivos;
  private byte contadorProgramas;
  private byte contadorFragmentos;
  private int contadorResultados;
  private final ConstDaoImpl constDao;
  private final ArchivoDaoImpl archivoDao;
  private List<Archivo> resultados;
  private int totalResultados;
  private final String operador;
  private VentanaPrincipal principal;
  
  /**
   * Creates new form VentanaModificar
   * @param operador
   * @param principal
   */
  public VentanaBusqueda(String operador, VentanaPrincipal principal) {
    this.operador = operador;
    this.principal = principal;
    
    initComponents();
    
    //inicializacion de paneles no visibles al cargar ventana
    panelArchivo.setVisible(false);
    panelPrograma.setVisible(false);
    panelFragmento.setVisible(false);
    scrollTipos.setVisible(false);
    panelNoticia.setVisible(false);
    panelInforme.setVisible(false);
    panelEntrevista.setVisible(false);
    panelPanel.setVisible(false);
    panelSeccion.setVisible(false);
    panelDeporte.setVisible(false);
    panelDisciplina.setVisible(false);
    panelBasquetball.setVisible(false);
    panelFutbol.setVisible(false);
    panelTenis.setVisible(false);
    
    
    //inicializacion DAOs
    this.constDao = new ConstDaoImpl();
    this.archivoDao = new ArchivoDaoImpl();
    
    //inicializacion de contadores
    this.contadorArchivos = 0;
    this.contadorProgramas = 0;
    this.contadorFragmentos = 0;
    this.totalResultados = 0;
    
    //inicializacion de botones
    botonResultadoAnterior.setEnabled(false);
    botonResultadoSiguiente.setEnabled(false);
   
  }
  
  //declaracion de metodos personalizados
    
  /**
   * Agrega el <code>String</code> a la <code>JList</code> asociada
   * @param textOrigen <code>String</code> donde se encuentra el valor a agregar
   * @param listaFinal <code>JList</code> que recibe el valor seleccionado
   */
  private void agregarALista(String textOrigen, javax.swing.JList listaFinal){
    DefaultListModel modelo = new DefaultListModel();
    boolean elementoExiste = false;
    
    //ciclo para rellenar modelo nuevo con elementos previos de la lista
    for(int i=0; i < listaFinal.getModel().getSize(); i++){
      modelo.addElement(listaFinal.getModel().getElementAt(i));
      //revisa si el elemento seleccionado existe previamente
      if(!elementoExiste) {
        if(textOrigen.equals(listaFinal.getModel().getElementAt(i))){
          elementoExiste = true;
        }
      }
    }
    if(!elementoExiste){
      modelo.addElement(textOrigen);
    }
    
    listaFinal.setModel(modelo);
  }
  
  /**
   * Limpia la <code>JList</code> indicada
   * @param lista <code>JList</code> que recibe el valor seleccionado
   */
  private void limpiarLista(javax.swing.JList lista){
    DefaultListModel modelo = new DefaultListModel();
    lista.setModel(modelo);
  }
  
  /**
   * Habilita o deshabilita un <code>JPanel</code> y todos sus componentes
   * @param panel <code>JPanel</code> que se desea habilitar o deshabilitar
   * @param set si se indica como <code>true</code> habilita el <code>JPanel</code> y sus componentes; <code>false</code> deshabilita el panel y sus componentes.
   * 
   */
  private void setPanelEnabled(javax.swing.JPanel panel, boolean set){
    panel.setEnabled(set);
    for(Component c : panel.getComponents()){
      if(c instanceof javax.swing.JPanel){
        this.setPanelEnabled((javax.swing.JPanel)c, set);
      } else if(c instanceof javax.swing.JLayeredPane){
        this.setPanelEnabled((javax.swing.JLayeredPane)c, set);
      } else if(c instanceof javax.swing.JScrollPane){
        this.setPanelEnabled((javax.swing.JScrollPane)c, set);
      } else {
        c.setEnabled(set);
      }
    }
  }
  
  /**
   * Habilita o deshabilita un <code>JLayeredPane</code> y todos sus componentes
   * @param panel <code>JLayeredPane</code> que se desea habilitar o deshabilitar
   * @param set si se indica como <code>true</code> habilita el <code>JLayeredPane</code> y sus componentes; <code>false</code> deshabilita el panel y sus componentes.
   * 
   */
  private void setPanelEnabled(javax.swing.JLayeredPane panel, boolean set){
    panel.setEnabled(set);
    for(Component c : panel.getComponents()){
      if(c instanceof javax.swing.JPanel){
        this.setPanelEnabled((javax.swing.JPanel)c, set);
      } else if(c instanceof javax.swing.JLayeredPane){
        this.setPanelEnabled((javax.swing.JLayeredPane)c, set);
      } else if(c instanceof javax.swing.JScrollPane){
        this.setPanelEnabled((javax.swing.JScrollPane)c, set);
      } else {
        c.setEnabled(set);
      }
    }
  }
  
  /**
   * Habilita o deshabilita un <code>JScrollPane</code> y todos sus componentes
   * @param panel <code>JScrollPane</code> que se desea habilitar o deshabilitar
   * @param set si se indica como <code>true</code> habilita el <code>JScrollPane</code> y sus componentes; <code>false</code> deshabilita el panel y sus componentes.
   * 
   */
  private void setPanelEnabled(javax.swing.JScrollPane panel, boolean set){
    panel.setEnabled(set);
    panel.getVerticalScrollBar().setEnabled(set);
    panel.getHorizontalScrollBar().setEnabled(set);
    this.setViewportEnabled(panel.getViewport(),set);
  }
  
  /**
   * Habilita o deshabilita el <code>JViewport</code> dentro de un <code>JScrollPane</code> y todos sus componentes
   * @param viewport <code>JViewport</code> que se desea habilitar o deshabilitar
   * @param set si se indica como <code>true</code> habilita el <code>JViewport</code> y sus componentes; <code>false</code> deshabilita el viewport y sus componentes.
   * 
   */
  private void setViewportEnabled(javax.swing.JViewport viewport, boolean set){
    viewport.setEnabled(set);
    for(Component c : viewport.getComponents()){
      if(c instanceof javax.swing.JPanel){
        this.setPanelEnabled((javax.swing.JPanel)c, set);
      } else if(c instanceof javax.swing.JLayeredPane){
        this.setPanelEnabled((javax.swing.JLayeredPane)c, set);
      } else if(c instanceof javax.swing.JScrollPane){
        this.setPanelEnabled((javax.swing.JScrollPane)c, set);
      } else {
        c.setEnabled(set);
      }
    }
  }
  
  private void limpiarPanel(javax.swing.JPanel panel){
    for(Component c : panel.getComponents()){
      if(c instanceof javax.swing.JTextField){
        ((javax.swing.JTextField) c).setText("");
      }
      if(c instanceof javax.swing.JTextArea){
        ((javax.swing.JTextArea) c).setText("");
      }
      if(c instanceof javax.swing.JComboBox){
        ((javax.swing.JComboBox) c).setSelectedIndex(0);
      }
      if(c instanceof javax.swing.JList){
        ((javax.swing.JList) c).setModel(new DefaultListModel());
      }
      if(c instanceof javax.swing.JPanel){
        this.limpiarPanel((javax.swing.JPanel)c);
      }
      if(c instanceof javax.swing.JLayeredPane){
        this.limpiarPanel((javax.swing.JLayeredPane)c);
      }
      if(c instanceof javax.swing.JScrollPane){
        this.limpiarPanel((javax.swing.JScrollPane)c);
      }
    }
  }
  
  private void limpiarPanel(javax.swing.JLayeredPane panel){
    for(Component c : panel.getComponents()){
      if(c instanceof javax.swing.JTextField){
        ((javax.swing.JTextField) c).setText("");
      }
      if(c instanceof javax.swing.JTextArea){
        ((javax.swing.JTextArea) c).setText("");
      }
      if(c instanceof javax.swing.JComboBox){
        ((javax.swing.JComboBox) c).setSelectedIndex(0);
      }
      if(c instanceof javax.swing.JList){
        ((javax.swing.JList) c).setModel(new DefaultListModel());
      }
      if(c instanceof javax.swing.JPanel){
        this.limpiarPanel((javax.swing.JPanel)c);
      }
      if(c instanceof javax.swing.JLayeredPane){
        this.limpiarPanel((javax.swing.JLayeredPane)c);
      }
      if(c instanceof javax.swing.JScrollPane){
        this.limpiarPanel((javax.swing.JScrollPane)c);
      }
    }
  }
  
  private void limpiarPanel(javax.swing.JScrollPane panel){
    limpiarViewport(panel.getViewport());
  }
  
  private void limpiarViewport(javax.swing.JViewport viewport){
    for(Component c : viewport.getComponents()){
      if(c instanceof javax.swing.JTextField){
        ((javax.swing.JTextField) c).setText("");
      }
      if(c instanceof javax.swing.JTextArea){
        ((javax.swing.JTextArea) c).setText("");
      }
      if(c instanceof javax.swing.JComboBox){
        ((javax.swing.JComboBox) c).setSelectedIndex(0);
      }
      if(c instanceof javax.swing.JList){
        ((javax.swing.JList) c).setModel(new DefaultListModel());
      }
      if(c instanceof javax.swing.JPanel){
        this.limpiarPanel((javax.swing.JPanel)c);
      }
      if(c instanceof javax.swing.JLayeredPane){
        this.limpiarPanel((javax.swing.JLayeredPane)c);
      }
      if(c instanceof javax.swing.JScrollPane){
        this.limpiarPanel((javax.swing.JScrollPane)c);
      }
    }
  }
  
  private void desplegarArchivo(int indiceArchivo){
    
    try {
      panelArchivo.setVisible(true);
      textIdArchivo.setText(this.resultados.get(indiceArchivo).getId());
      textNombreArchivo.setText(this.resultados.get(indiceArchivo).getNombreArchivo());

      panelPrograma.setVisible(true);
      panelFragmento.setVisible(true);
    } catch (NullPointerException npe) {
      System.out.println("ATENCION: Existen claves inexistentes o sin valores definidos (desplegarArchivo)");
    } catch (IndexOutOfBoundsException ioube) {
      System.out.println("ATENCION: Existen archivos sin programas definidos (desplegarArchivo)");
    }
      
    
  }
  
  private void desplegarPrograma(int indicePrograma){
    try {
      
      Programa programa = this.resultados.get(contadorArchivos).getProgramas().get(indicePrograma);
      valorAlturaInicioProgramaHora.setText(String.valueOf(programa.getAlturaInicio() / 3600));
      valorAlturaInicioProgramaMinutos.setText(String.valueOf((programa.getAlturaInicio() % 3600) / 60));
      valorAlturaInicioProgramaSegundos.setText(String.valueOf((programa.getAlturaInicio() % 3600) % 60));
      valorAlturaTerminoProgramaHora.setText(String.valueOf(programa.getAlturaTermino() / 3600));
      valorAlturaTerminoProgramaMinutos.setText(String.valueOf((programa.getAlturaTermino() % 3600) / 60));
      valorAlturaTerminoProgramaSegundos.setText(String.valueOf((programa.getAlturaTermino() % 3600) % 60));
      textNombrePrograma.setText(programa.getNombrePrograma());
      textDiaEmision.setText(String.valueOf(programa.getFechaEmision().toInstant().atZone(ZoneId.of("Z")).getDayOfMonth()));
      textMesEmision.setText(String.valueOf(programa.getFechaEmision().toInstant().atZone(ZoneId.of("Z")).getMonthValue()));
      textAnhoEmision.setText(String.valueOf(programa.getFechaEmision().toInstant().atZone(ZoneId.of("Z")).getYear()));
      
      limpiarLista(listaConductor);
      for(String c: programa.getConductor()){
        this.agregarALista(c, listaConductor);
      }

    } catch (NullPointerException npe) {
      System.out.println("ATENCION: Existen claves inexistentes o sin valores definidos (desplegarPrograma)");
    } catch (IndexOutOfBoundsException ioube) {
      System.out.println("ATENCION: Existen programas sin fragmentos definidos (desplegarPrograma)");
    }
  }
  
  private void desplegarFragmento(int indiceAudio){
    try {
      

      Audio fragmento = this.resultados.get(this.contadorArchivos).getProgramas().get(this.contadorProgramas).getFragmentos().get(indiceAudio);

      valorIdFragmento.setText(fragmento.getIdAudio());
      valorAlturaInicioFragmentoHora.setText(String.valueOf(fragmento.getAlturaInicio() / 3600));
      valorAlturaInicioFragmentoMinutos.setText(String.valueOf((fragmento.getAlturaInicio() % 3600) / 60));
      valorAlturaInicioFragmentoSegundos.setText(String.valueOf((fragmento.getAlturaInicio() % 3600) % 60));
      valorAlturaTerminoFragmentoHora.setText(String.valueOf(fragmento.getAlturaTermino() / 3600));
      valorAlturaTerminoFragmentoMinutos.setText(String.valueOf((fragmento.getAlturaTermino() % 3600) / 60));
      valorAlturaTerminoFragmentoSegundos.setText(String.valueOf((fragmento.getAlturaTermino() % 3600) % 60));

      String[] tiposAudio = {"Panel", "Deporte", "Entrevista", "Seccion", "Informe", "Noticia"};
      String[] disciplinasDeporte = {"Tenis", "Basquetball", "Futbol"};

      this.textTipoAudio.setText(fragmento.getTipo());
      
      int indexTipoAudio = Arrays.asList(tiposAudio).indexOf(fragmento.getTipo()) + 1;
            
      //despliegue de tipos
      switch(indexTipoAudio){
        case 1: {
          Panel panel = (Panel)fragmento;
          for(Personaje p: panel.getPanelista()){
            this.agregarALista(p.toString(), this.listaPanelistas);
          }
          for(String t: panel.getTema()){
            this.agregarALista(t, this.listaTemaPanel);
          }
          break;
        }

        case 2: {
          Deporte deporte = (Deporte)fragmento;
          for(String r:deporte.getRelator()){
            this.agregarALista(r,listaRelator);
          }
          for(String lc:deporte.getLocutorComercial()){
            this.agregarALista(lc,listaLocutorComercial);
          }
          for(String ers:deporte.getEncargadoRedesSociales()){
            this.agregarALista(ers,listaEncargadoRRSS);
          }
          for(String c:deporte.getComentarista()){
            this.agregarALista(c,listaComentarista);
          }
          for(String rc:deporte.getReportero()){
            this.agregarALista(rc,listaReportero);
          }
          textCompetencia.setText(deporte.getCompetencia());
          textLugar.setText(deporte.getLugar());

          int indexDisciplinaDeporte = Arrays.asList(disciplinasDeporte).indexOf(deporte.getDisciplina()) + 1;

          switch(indexDisciplinaDeporte){
            case 1: {
              Tenis tenis = (Tenis)deporte;
              String cadena = "";
              for(String j: tenis.getJugador()){
                cadena += j + ";";
              }
              if(!cadena.equals("")){
                textJugadores.setText(cadena.substring(0, cadena.length()-2));
              }

              textMarcadorFinalTenis.setText(tenis.getMarcador());

              break;
            }
            case 2: {
              Basquetball basquetball = (Basquetball)deporte;
              textEquipoLocalBasquetball.setText(basquetball.getEquipoLocal());
              textEquipoVisitaBasquetball.setText(basquetball.getEquipoVisita());
              textMarcadorFinalBasquetball.setText(basquetball.getMarcador());
              break;
            }
            case 3: {
              Futbol futbol = (Futbol)deporte;

              textEquipoLocalFutbol.setText(futbol.getEquipoLocal());
              textEquipoVisitaFutbol.setText(futbol.getEquipoVisita());
              textMarcadorFinalFutbol.setText(futbol.getMarcador());

              break;
            }
          }
          
          for(Component c: panelDisciplina.getComponents()){
            c.setVisible(false);
          }
          
          panelDisciplina.getComponent(indexDisciplinaDeporte - 1).setVisible(true);
          panelDisciplina.setVisible(true);

          break;
        }

        case 3: {
          Entrevista entrevista = (Entrevista)fragmento;
          for(String p: entrevista.getPeriodista()){
            agregarALista(p, listaPeriodistaEntrevista);
          }
          for(Personaje e: entrevista.getEntrevistado()){
            agregarALista(e.toString(), listaEntrevistados);
          }
          for(String t: entrevista.getTema()){
            agregarALista(t,listaTemaEntrevista);
          }
          break;
        }
        case 4: {
          Seccion seccion = (Seccion)fragmento;
          textNombreSeccion.setText(seccion.getNombre());
          for(String p: seccion.getPanelista()){
            agregarALista(p, listaPanelistasSeccion);
          }
          for(String t: seccion.getTema()){
            agregarALista(t, listaTemaSeccion);
          }
          for(Personaje p: seccion.getInvitado()){
            agregarALista(p.toString(), listaInvitadosSeccion);
          }
          break;
        }
        case 5: {
          Informe informe = (Informe)fragmento;
          for(String p: informe.getPeriodista()){
            agregarALista(p, listaPeriodistaInforme);
          }
          for(String t: informe.getTema()){
            agregarALista(t, listaTemaInforme);
          }
          textLugarInforme.setText(informe.getLugar());
          for(Personaje p: informe.getCunha()){
            agregarALista(p.toString(), listaPersonajeInforme);
          }

          break;
        }
        case 6: {
          Noticia noticia = (Noticia)fragmento;
          for(String t: noticia.getTema()){
            agregarALista(t, listaTemaNoticia);
          }
          break;
        }
        default: {
          break;
        }
      }


      for(String pc: fragmento.getPalabrasClave()){
        this.agregarALista(pc, this.listaPalabrasClave);
      }
      textAreaDescripcionFragmento.setText(fragmento.getDescripcion());
      
      
      for(Component c: panelTipos.getComponents()){
        c.setVisible(false);
      }
      
      panelTipos.getComponent(indexTipoAudio-1).setVisible(true);
      scrollTipos.setVisible(true);
      
    } catch (NullPointerException npe) {
      System.out.println("ATENCION: Existen claves inexistentes o sin valores definidos (desplegarFragmewnto)");
    } catch (IndexOutOfBoundsException ioube) {
      System.out.println("ATENCION: Existen programas sin fragmentos definidos (desplegarFragmento)");
    }
    
  }
  
  private void actualizarMarcadorResultados(){
    this.valorResultados.setText(this.contadorResultados + "/" + totalResultados);
  }
  
  
  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT
   * modify this code. The content of this method is always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    labelSubtitulo = new javax.swing.JLabel();
    labelTitulo = new javax.swing.JLabel();
    labelBuscarTexto = new javax.swing.JLabel();
    textBuscarTexto = new javax.swing.JTextField();
    botonBuscarTexto = new javax.swing.JButton();
    panelArchivo = new javax.swing.JPanel();
    labelIdArchivo = new javax.swing.JLabel();
    textIdArchivo = new javax.swing.JTextField();
    labelNombreArchivo = new javax.swing.JLabel();
    textNombreArchivo = new javax.swing.JTextField();
    panelPrograma = new javax.swing.JPanel();
    labelAlturaInicioPrograma = new javax.swing.JLabel();
    panelAlturaInicioPrograma = new javax.swing.JPanel();
    valorAlturaInicioProgramaHora = new javax.swing.JLabel();
    labelAlturaInicioProgramaHora = new javax.swing.JLabel();
    valorAlturaInicioProgramaMinutos = new javax.swing.JLabel();
    labelAlturaInicioProgramaMinutos = new javax.swing.JLabel();
    valorAlturaInicioProgramaSegundos = new javax.swing.JLabel();
    labelAlturaInicioProgramaSegundos = new javax.swing.JLabel();
    labelAlturaTerminoPrograma = new javax.swing.JLabel();
    panelAlturaTerminoPrograma = new javax.swing.JPanel();
    valorAlturaTerminoProgramaHora = new javax.swing.JLabel();
    labelAlturaTerminoProgramaHora = new javax.swing.JLabel();
    valorAlturaTerminoProgramaMinutos = new javax.swing.JLabel();
    labelAlturaTerminoProgramaMinutos = new javax.swing.JLabel();
    valorAlturaTerminoProgramaSegundos = new javax.swing.JLabel();
    labelAlturaTerminoProgramaSegundos = new javax.swing.JLabel();
    labelNombrePrograma = new javax.swing.JLabel();
    labelFechaEmisionPrograma = new javax.swing.JLabel();
    panelFechaEmisionPrograma = new javax.swing.JPanel();
    textDiaEmision = new javax.swing.JTextField();
    labelSeparador5 = new javax.swing.JLabel();
    textMesEmision = new javax.swing.JTextField();
    labelSeparador6 = new javax.swing.JLabel();
    textAnhoEmision = new javax.swing.JTextField();
    labelConductor = new javax.swing.JLabel();
    scrollConductor = new javax.swing.JScrollPane();
    listaConductor = new javax.swing.JList<>();
    textNombrePrograma = new javax.swing.JTextField();
    panelFragmento = new javax.swing.JPanel();
    labelIdFragmento = new javax.swing.JLabel();
    valorIdFragmento = new javax.swing.JLabel();
    labelAlturaInicioFragmento = new javax.swing.JLabel();
    panelAlturaInicioFragmento = new javax.swing.JPanel();
    valorAlturaInicioFragmentoHora = new javax.swing.JLabel();
    labelAlturaInicioFragmentoHora = new javax.swing.JLabel();
    valorAlturaInicioFragmentoMinutos = new javax.swing.JLabel();
    labelAlturaInicioFragmentoMinutos = new javax.swing.JLabel();
    valorAlturaInicioFragmentoSegundos = new javax.swing.JLabel();
    labelAlturaInicioFragmentoSegundos = new javax.swing.JLabel();
    labelAlturaTerminoFragmento = new javax.swing.JLabel();
    panelAlturaTerminoFragmento = new javax.swing.JPanel();
    valorAlturaTerminoFragmentoHora = new javax.swing.JLabel();
    labelAlturaTerminoFragmentoHora = new javax.swing.JLabel();
    valorAlturaTerminoFragmentoMinutos = new javax.swing.JLabel();
    labelAlturaTerminoFragmentoMinutos = new javax.swing.JLabel();
    valorAlturaTerminoFragmentoSegundos = new javax.swing.JLabel();
    labelAlturaTerminoFragmentoSegundos = new javax.swing.JLabel();
    labelTipoAudio = new javax.swing.JLabel();
    textTipoAudio = new javax.swing.JTextField();
    scrollTipos = new javax.swing.JScrollPane();
    panelTipos = new javax.swing.JLayeredPane();
    panelNoticia = new javax.swing.JPanel();
    labelTemaNoticia = new javax.swing.JLabel();
    scrollTemaNoticia = new javax.swing.JScrollPane();
    listaTemaNoticia = new javax.swing.JList<>();
    panelInforme = new javax.swing.JPanel();
    labelPeriodistaInforme = new javax.swing.JLabel();
    scrollPeriodistaInforme = new javax.swing.JScrollPane();
    listaPeriodistaInforme = new javax.swing.JList<>();
    labelTemaInforme = new javax.swing.JLabel();
    scrollTemaInforme = new javax.swing.JScrollPane();
    listaTemaInforme = new javax.swing.JList<>();
    labelLugarInforme = new javax.swing.JLabel();
    textLugarInforme = new javax.swing.JTextField();
    labelPersonajeInforme = new javax.swing.JLabel();
    scrollPersonajeInforme = new javax.swing.JScrollPane();
    listaPersonajeInforme = new javax.swing.JList<>();
    panelEntrevista = new javax.swing.JPanel();
    labelPeriodistaEntrevista = new javax.swing.JLabel();
    scrollPeriodistaEntrevista = new javax.swing.JScrollPane();
    listaPeriodistaEntrevista = new javax.swing.JList<>();
    labelEntrevistados = new javax.swing.JLabel();
    scrollEntrevistados = new javax.swing.JScrollPane();
    listaEntrevistados = new javax.swing.JList<>();
    labelTemaEntrevista = new javax.swing.JLabel();
    scrollTemaEntrevista = new javax.swing.JScrollPane();
    listaTemaEntrevista = new javax.swing.JList<>();
    panelSeccion = new javax.swing.JPanel();
    labelNombreSeccion = new javax.swing.JLabel();
    textNombreSeccion = new javax.swing.JTextField();
    labelPanelistasSeccion = new javax.swing.JLabel();
    scrollPanelistasSeccion = new javax.swing.JScrollPane();
    listaPanelistasSeccion = new javax.swing.JList<>();
    labelTemaSeccion = new javax.swing.JLabel();
    scrollTemaSeccion = new javax.swing.JScrollPane();
    listaTemaSeccion = new javax.swing.JList<>();
    labelInvitadosSeccion = new javax.swing.JLabel();
    scrollInvitadosSeccion = new javax.swing.JScrollPane();
    listaInvitadosSeccion = new javax.swing.JList<>();
    panelPanel = new javax.swing.JPanel();
    labelPanelistas = new javax.swing.JLabel();
    scrollPanelistas = new javax.swing.JScrollPane();
    listaPanelistas = new javax.swing.JList<>();
    labelTemaPanel = new javax.swing.JLabel();
    scrollTemaPanel = new javax.swing.JScrollPane();
    listaTemaPanel = new javax.swing.JList<>();
    panelDeporte = new javax.swing.JPanel();
    labelRelator = new javax.swing.JLabel();
    scrollRelator = new javax.swing.JScrollPane();
    listaRelator = new javax.swing.JList<>();
    labelLocutorComercial = new javax.swing.JLabel();
    scrollLocutorComercial = new javax.swing.JScrollPane();
    listaLocutorComercial = new javax.swing.JList<>();
    labelEncargadoRRSS = new javax.swing.JLabel();
    scrollEncargadoRRSS = new javax.swing.JScrollPane();
    listaEncargadoRRSS = new javax.swing.JList<>();
    labelComentarista = new javax.swing.JLabel();
    scrollComentarista = new javax.swing.JScrollPane();
    listaComentarista = new javax.swing.JList<>();
    labelReportero = new javax.swing.JLabel();
    scrollReportero = new javax.swing.JScrollPane();
    listaReportero = new javax.swing.JList<>();
    labelCompetencia = new javax.swing.JLabel();
    textCompetencia = new javax.swing.JTextField();
    labelLugar = new javax.swing.JLabel();
    textLugar = new javax.swing.JTextField();
    labelDisciplina = new javax.swing.JLabel();
    textDisciplina = new javax.swing.JTextField();
    panelDisciplina = new javax.swing.JLayeredPane();
    panelFutbol = new javax.swing.JPanel();
    labelEquipoLocalFutbol = new javax.swing.JLabel();
    textEquipoLocalFutbol = new javax.swing.JTextField();
    labelEquipoVisitaFutbol = new javax.swing.JLabel();
    textEquipoVisitaFutbol = new javax.swing.JTextField();
    labelMarcadorFinalFutbol = new javax.swing.JLabel();
    textMarcadorFinalFutbol = new javax.swing.JTextField();
    labelGoles = new javax.swing.JLabel();
    botonAgregarGoles = new javax.swing.JButton();
    panelTenis = new javax.swing.JPanel();
    labelJugadores = new javax.swing.JLabel();
    textJugadores = new javax.swing.JTextField();
    labelMarcadorFinalTenis = new javax.swing.JLabel();
    textMarcadorFinalTenis = new javax.swing.JTextField();
    panelBasquetball = new javax.swing.JPanel();
    labelEquipoLocalBasquetball = new javax.swing.JLabel();
    textEquipoLocalBasquetball = new javax.swing.JTextField();
    labelEquipoVisitaBasquetball = new javax.swing.JLabel();
    textEquipoVisitaBasquetball = new javax.swing.JTextField();
    labelMarcadorFinalBasquetball = new javax.swing.JLabel();
    textMarcadorFinalBasquetball = new javax.swing.JTextField();
    labelPalabrasClave = new javax.swing.JLabel();
    scrollPalabrasClave = new javax.swing.JScrollPane();
    listaPalabrasClave = new javax.swing.JList<>();
    labelDescripcionFragmento = new javax.swing.JLabel();
    textAreaDescripcionFragmento = new javax.swing.JTextArea();
    panelNavegacion = new javax.swing.JPanel();
    botonResultadoAnterior = new javax.swing.JButton();
    valorResultados = new javax.swing.JLabel();
    botonResultadoSiguiente = new javax.swing.JButton();
    botonSalir = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("REGISTRO DE ARCHIVOS");

    labelSubtitulo.setText("Archivo Radio Cooperativa");

    labelTitulo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    labelTitulo.setText("ACTUALIZACION DE ARCHIVOS");

    labelBuscarTexto.setText("Ingresa texto a buscar");

    textBuscarTexto.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textBuscarTextoActionPerformed(evt);
      }
    });

    botonBuscarTexto.setText("Buscar");
    botonBuscarTexto.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        botonBuscarTextoActionPerformed(evt);
      }
    });

    panelArchivo.setBorder(javax.swing.BorderFactory.createTitledBorder("Archivo"));

    labelIdArchivo.setLabelFor(textIdArchivo);
    labelIdArchivo.setText("Id Archivo");

    textIdArchivo.setEditable(false);

    labelNombreArchivo.setText("Nombre de Archivo");

    textNombreArchivo.setEditable(false);

    javax.swing.GroupLayout panelArchivoLayout = new javax.swing.GroupLayout(panelArchivo);
    panelArchivo.setLayout(panelArchivoLayout);
    panelArchivoLayout.setHorizontalGroup(
      panelArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelArchivoLayout.createSequentialGroup()
        .addComponent(labelIdArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(textIdArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
      .addGroup(panelArchivoLayout.createSequentialGroup()
        .addComponent(labelNombreArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(textNombreArchivo))
    );
    panelArchivoLayout.setVerticalGroup(
      panelArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelArchivoLayout.createSequentialGroup()
        .addContainerGap(26, Short.MAX_VALUE)
        .addGroup(panelArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelIdArchivo)
          .addComponent(textIdArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelNombreArchivo)
          .addComponent(textNombreArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
    );

    panelPrograma.setBorder(javax.swing.BorderFactory.createTitledBorder("Programa"));
    panelPrograma.setName("panelPrograma"); // NOI18N

    labelAlturaInicioPrograma.setText("Altura Inicio");

    valorAlturaInicioProgramaHora.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    valorAlturaInicioProgramaHora.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    valorAlturaInicioProgramaHora.setText("--");

    labelAlturaInicioProgramaHora.setText("h");

    valorAlturaInicioProgramaMinutos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    valorAlturaInicioProgramaMinutos.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    valorAlturaInicioProgramaMinutos.setText("--");

    labelAlturaInicioProgramaMinutos.setText("m");

    valorAlturaInicioProgramaSegundos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    valorAlturaInicioProgramaSegundos.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    valorAlturaInicioProgramaSegundos.setText("--");

    labelAlturaInicioProgramaSegundos.setText("s");

    javax.swing.GroupLayout panelAlturaInicioProgramaLayout = new javax.swing.GroupLayout(panelAlturaInicioPrograma);
    panelAlturaInicioPrograma.setLayout(panelAlturaInicioProgramaLayout);
    panelAlturaInicioProgramaLayout.setHorizontalGroup(
      panelAlturaInicioProgramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelAlturaInicioProgramaLayout.createSequentialGroup()
        .addComponent(valorAlturaInicioProgramaHora, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(labelAlturaInicioProgramaHora)
        .addGap(12, 12, 12)
        .addComponent(valorAlturaInicioProgramaMinutos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(labelAlturaInicioProgramaMinutos)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(valorAlturaInicioProgramaSegundos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(labelAlturaInicioProgramaSegundos)
        .addContainerGap())
    );
    panelAlturaInicioProgramaLayout.setVerticalGroup(
      panelAlturaInicioProgramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelAlturaInicioProgramaLayout.createSequentialGroup()
        .addGap(3, 3, 3)
        .addGroup(panelAlturaInicioProgramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelAlturaInicioProgramaSegundos)
          .addComponent(labelAlturaInicioProgramaMinutos)
          .addComponent(labelAlturaInicioProgramaHora)
          .addComponent(valorAlturaInicioProgramaHora, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(valorAlturaInicioProgramaMinutos, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(valorAlturaInicioProgramaSegundos, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(0, 0, 0))
    );

    labelAlturaTerminoPrograma.setText("Altura Término");

    valorAlturaTerminoProgramaHora.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    valorAlturaTerminoProgramaHora.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    valorAlturaTerminoProgramaHora.setText("--");

    labelAlturaTerminoProgramaHora.setText("h");

    valorAlturaTerminoProgramaMinutos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    valorAlturaTerminoProgramaMinutos.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    valorAlturaTerminoProgramaMinutos.setText("--");

    labelAlturaTerminoProgramaMinutos.setText("m");

    valorAlturaTerminoProgramaSegundos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    valorAlturaTerminoProgramaSegundos.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    valorAlturaTerminoProgramaSegundos.setText("--");

    labelAlturaTerminoProgramaSegundos.setText("s");

    javax.swing.GroupLayout panelAlturaTerminoProgramaLayout = new javax.swing.GroupLayout(panelAlturaTerminoPrograma);
    panelAlturaTerminoPrograma.setLayout(panelAlturaTerminoProgramaLayout);
    panelAlturaTerminoProgramaLayout.setHorizontalGroup(
      panelAlturaTerminoProgramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelAlturaTerminoProgramaLayout.createSequentialGroup()
        .addComponent(valorAlturaTerminoProgramaHora, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(labelAlturaTerminoProgramaHora)
        .addGap(12, 12, 12)
        .addComponent(valorAlturaTerminoProgramaMinutos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(labelAlturaTerminoProgramaMinutos)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(valorAlturaTerminoProgramaSegundos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(labelAlturaTerminoProgramaSegundos)
        .addContainerGap())
    );
    panelAlturaTerminoProgramaLayout.setVerticalGroup(
      panelAlturaTerminoProgramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelAlturaTerminoProgramaLayout.createSequentialGroup()
        .addGap(3, 3, 3)
        .addGroup(panelAlturaTerminoProgramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelAlturaTerminoProgramaSegundos)
          .addComponent(labelAlturaTerminoProgramaMinutos)
          .addComponent(labelAlturaTerminoProgramaHora)
          .addComponent(valorAlturaTerminoProgramaHora, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(valorAlturaTerminoProgramaMinutos, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(valorAlturaTerminoProgramaSegundos, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(0, 0, 0))
    );

    labelNombrePrograma.setText("Nombre del Programa");

    labelFechaEmisionPrograma.setText("Fecha Emision");

    textDiaEmision.setEditable(false);
    textDiaEmision.setColumns(2);
    textDiaEmision.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    textDiaEmision.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textDiaEmisionActionPerformed(evt);
      }
    });

    labelSeparador5.setText("/");

    textMesEmision.setEditable(false);
    textMesEmision.setColumns(2);
    textMesEmision.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    textMesEmision.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textMesEmisionActionPerformed(evt);
      }
    });

    labelSeparador6.setText("/");

    textAnhoEmision.setEditable(false);
    textAnhoEmision.setColumns(4);
    textAnhoEmision.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    textAnhoEmision.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textAnhoEmisionActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout panelFechaEmisionProgramaLayout = new javax.swing.GroupLayout(panelFechaEmisionPrograma);
    panelFechaEmisionPrograma.setLayout(panelFechaEmisionProgramaLayout);
    panelFechaEmisionProgramaLayout.setHorizontalGroup(
      panelFechaEmisionProgramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelFechaEmisionProgramaLayout.createSequentialGroup()
        .addComponent(textDiaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(labelSeparador5)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(textMesEmision, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(labelSeparador6)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(textAnhoEmision, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    panelFechaEmisionProgramaLayout.setVerticalGroup(
      panelFechaEmisionProgramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelFechaEmisionProgramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
        .addComponent(textMesEmision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addComponent(labelSeparador6)
        .addComponent(textAnhoEmision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
      .addGroup(panelFechaEmisionProgramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
        .addComponent(textDiaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addComponent(labelSeparador5))
    );

    labelConductor.setText("Conductor");

    listaConductor.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    listaConductor.setToolTipText("");
    listaConductor.setMaximumSize(new java.awt.Dimension(0, 3));
    listaConductor.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        listaConductorMouseClicked(evt);
      }
    });
    scrollConductor.setViewportView(listaConductor);

    textNombrePrograma.setEditable(false);

    javax.swing.GroupLayout panelProgramaLayout = new javax.swing.GroupLayout(panelPrograma);
    panelPrograma.setLayout(panelProgramaLayout);
    panelProgramaLayout.setHorizontalGroup(
      panelProgramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelProgramaLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(panelProgramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(panelProgramaLayout.createSequentialGroup()
            .addComponent(labelAlturaTerminoPrograma)
            .addGap(56, 56, 56)
            .addComponent(panelAlturaTerminoPrograma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          .addGroup(panelProgramaLayout.createSequentialGroup()
            .addGroup(panelProgramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(labelConductor)
              .addComponent(labelFechaEmisionPrograma)
              .addComponent(labelNombrePrograma))
            .addGap(23, 23, 23)
            .addGroup(panelProgramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(panelFechaEmisionPrograma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(textNombrePrograma)
              .addComponent(scrollConductor, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
          .addGroup(panelProgramaLayout.createSequentialGroup()
            .addComponent(labelAlturaInicioPrograma)
            .addGap(69, 69, 69)
            .addComponent(panelAlturaInicioPrograma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        .addContainerGap())
    );
    panelProgramaLayout.setVerticalGroup(
      panelProgramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelProgramaLayout.createSequentialGroup()
        .addGap(0, 0, 0)
        .addGroup(panelProgramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(labelAlturaInicioPrograma)
          .addComponent(panelAlturaInicioPrograma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelProgramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(labelAlturaTerminoPrograma)
          .addComponent(panelAlturaTerminoPrograma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelProgramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelNombrePrograma)
          .addComponent(textNombrePrograma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelProgramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(labelFechaEmisionPrograma)
          .addComponent(panelFechaEmisionPrograma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelProgramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(labelConductor)
          .addComponent(scrollConductor, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    panelFragmento.setBorder(javax.swing.BorderFactory.createTitledBorder("Fragmento"));
    panelFragmento.setName("panelFragmento"); // NOI18N

    labelIdFragmento.setText("Id Fragmento");

    valorIdFragmento.setText("--");

    labelAlturaInicioFragmento.setText("Altura de Inicio");

    valorAlturaInicioFragmentoHora.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    valorAlturaInicioFragmentoHora.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    valorAlturaInicioFragmentoHora.setText("--");

    labelAlturaInicioFragmentoHora.setText("h");

    valorAlturaInicioFragmentoMinutos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    valorAlturaInicioFragmentoMinutos.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    valorAlturaInicioFragmentoMinutos.setText("--");

    labelAlturaInicioFragmentoMinutos.setText("m");

    valorAlturaInicioFragmentoSegundos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    valorAlturaInicioFragmentoSegundos.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    valorAlturaInicioFragmentoSegundos.setText("--");

    labelAlturaInicioFragmentoSegundos.setText("s");

    javax.swing.GroupLayout panelAlturaInicioFragmentoLayout = new javax.swing.GroupLayout(panelAlturaInicioFragmento);
    panelAlturaInicioFragmento.setLayout(panelAlturaInicioFragmentoLayout);
    panelAlturaInicioFragmentoLayout.setHorizontalGroup(
      panelAlturaInicioFragmentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelAlturaInicioFragmentoLayout.createSequentialGroup()
        .addComponent(valorAlturaInicioFragmentoHora, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(labelAlturaInicioFragmentoHora)
        .addGap(12, 12, 12)
        .addComponent(valorAlturaInicioFragmentoMinutos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(labelAlturaInicioFragmentoMinutos)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(valorAlturaInicioFragmentoSegundos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(labelAlturaInicioFragmentoSegundos)
        .addContainerGap())
    );
    panelAlturaInicioFragmentoLayout.setVerticalGroup(
      panelAlturaInicioFragmentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelAlturaInicioFragmentoLayout.createSequentialGroup()
        .addGap(3, 3, 3)
        .addGroup(panelAlturaInicioFragmentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelAlturaInicioFragmentoSegundos)
          .addComponent(labelAlturaInicioFragmentoMinutos)
          .addComponent(labelAlturaInicioFragmentoHora)
          .addComponent(valorAlturaInicioFragmentoHora, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(valorAlturaInicioFragmentoMinutos, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(valorAlturaInicioFragmentoSegundos, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(0, 0, 0))
    );

    labelAlturaTerminoFragmento.setText("Altura de Término");

    valorAlturaTerminoFragmentoHora.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    valorAlturaTerminoFragmentoHora.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    valorAlturaTerminoFragmentoHora.setText("--");

    labelAlturaTerminoFragmentoHora.setText("h");

    valorAlturaTerminoFragmentoMinutos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    valorAlturaTerminoFragmentoMinutos.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    valorAlturaTerminoFragmentoMinutos.setText("--");

    labelAlturaTerminoFragmentoMinutos.setText("m");

    valorAlturaTerminoFragmentoSegundos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    valorAlturaTerminoFragmentoSegundos.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    valorAlturaTerminoFragmentoSegundos.setText("--");

    labelAlturaTerminoFragmentoSegundos.setText("s");

    javax.swing.GroupLayout panelAlturaTerminoFragmentoLayout = new javax.swing.GroupLayout(panelAlturaTerminoFragmento);
    panelAlturaTerminoFragmento.setLayout(panelAlturaTerminoFragmentoLayout);
    panelAlturaTerminoFragmentoLayout.setHorizontalGroup(
      panelAlturaTerminoFragmentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelAlturaTerminoFragmentoLayout.createSequentialGroup()
        .addComponent(valorAlturaTerminoFragmentoHora, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(labelAlturaTerminoFragmentoHora)
        .addGap(12, 12, 12)
        .addComponent(valorAlturaTerminoFragmentoMinutos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(labelAlturaTerminoFragmentoMinutos)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(valorAlturaTerminoFragmentoSegundos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(labelAlturaTerminoFragmentoSegundos)
        .addContainerGap())
    );
    panelAlturaTerminoFragmentoLayout.setVerticalGroup(
      panelAlturaTerminoFragmentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelAlturaTerminoFragmentoLayout.createSequentialGroup()
        .addGap(3, 3, 3)
        .addGroup(panelAlturaTerminoFragmentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelAlturaTerminoFragmentoSegundos)
          .addComponent(labelAlturaTerminoFragmentoMinutos)
          .addComponent(labelAlturaTerminoFragmentoHora)
          .addComponent(valorAlturaTerminoFragmentoHora, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(valorAlturaTerminoFragmentoMinutos, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(valorAlturaTerminoFragmentoSegundos, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(0, 0, 0))
    );

    labelTipoAudio.setText("Tipo de Audio");

    textTipoAudio.setEditable(false);

    scrollTipos.setBorder(null);

    panelTipos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    panelNoticia.setName("panelNoticia"); // NOI18N

    labelTemaNoticia.setText("Tema de la Noticia");

    listaTemaNoticia.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    listaTemaNoticia.setToolTipText("Click derecho para borrar nombre");
    listaTemaNoticia.setMaximumSize(new java.awt.Dimension(0, 3));
    listaTemaNoticia.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        listaTemaNoticiaMouseClicked(evt);
      }
    });
    scrollTemaNoticia.setViewportView(listaTemaNoticia);

    javax.swing.GroupLayout panelNoticiaLayout = new javax.swing.GroupLayout(panelNoticia);
    panelNoticia.setLayout(panelNoticiaLayout);
    panelNoticiaLayout.setHorizontalGroup(
      panelNoticiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNoticiaLayout.createSequentialGroup()
        .addComponent(labelTemaNoticia, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(scrollTemaNoticia, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );
    panelNoticiaLayout.setVerticalGroup(
      panelNoticiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelNoticiaLayout.createSequentialGroup()
        .addGroup(panelNoticiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(panelNoticiaLayout.createSequentialGroup()
            .addComponent(labelTemaNoticia)
            .addGap(0, 60, Short.MAX_VALUE))
          .addComponent(scrollTemaNoticia, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        .addContainerGap())
    );

    panelTipos.setLayer(panelNoticia, 1);
    panelTipos.add(panelNoticia, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

    panelInforme.setName("panelInforme"); // NOI18N

    labelPeriodistaInforme.setText("Periodista");

    listaPeriodistaInforme.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    listaPeriodistaInforme.setToolTipText("Click derecho para borrar nombre");
    listaPeriodistaInforme.setMaximumSize(new java.awt.Dimension(0, 3));
    listaPeriodistaInforme.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        listaPeriodistaInformeMouseClicked(evt);
      }
    });
    scrollPeriodistaInforme.setViewportView(listaPeriodistaInforme);

    labelTemaInforme.setText("Tema del Informe");

    listaTemaInforme.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    listaTemaInforme.setToolTipText("Click derecho para borrar nombre");
    listaTemaInforme.setMaximumSize(new java.awt.Dimension(0, 3));
    listaTemaInforme.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        listaTemaInformeMouseClicked(evt);
      }
    });
    scrollTemaInforme.setViewportView(listaTemaInforme);

    labelLugarInforme.setText("Lugar desde donde se informa");

    textLugarInforme.setEditable(false);
    textLugarInforme.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textLugarInformeActionPerformed(evt);
      }
    });

    labelPersonajeInforme.setText("Cuñas de...");

    listaPersonajeInforme.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    listaPersonajeInforme.setToolTipText("Click derecho para borrar nombre");
    listaPersonajeInforme.setMaximumSize(new java.awt.Dimension(0, 3));
    listaPersonajeInforme.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        listaPersonajeInformeMouseClicked(evt);
      }
    });
    scrollPersonajeInforme.setViewportView(listaPersonajeInforme);

    javax.swing.GroupLayout panelInformeLayout = new javax.swing.GroupLayout(panelInforme);
    panelInforme.setLayout(panelInformeLayout);
    panelInformeLayout.setHorizontalGroup(
      panelInformeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelInformeLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(panelInformeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(panelInformeLayout.createSequentialGroup()
            .addGroup(panelInformeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(panelInformeLayout.createSequentialGroup()
                .addComponent(labelPersonajeInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
              .addGroup(panelInformeLayout.createSequentialGroup()
                .addComponent(labelTemaInforme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
              .addGroup(panelInformeLayout.createSequentialGroup()
                .addComponent(labelLugarInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)))
            .addGap(3, 3, 3)
            .addGroup(panelInformeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(scrollTemaInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
              .addGroup(panelInformeLayout.createSequentialGroup()
                .addGroup(panelInformeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(textLugarInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(scrollPersonajeInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, Short.MAX_VALUE))))
          .addGroup(panelInformeLayout.createSequentialGroup()
            .addComponent(labelPeriodistaInforme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(scrollPeriodistaInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addGap(0, 0, 0))
    );
    panelInformeLayout.setVerticalGroup(
      panelInformeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelInformeLayout.createSequentialGroup()
        .addGroup(panelInformeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(panelInformeLayout.createSequentialGroup()
            .addComponent(labelPeriodistaInforme)
            .addGap(51, 51, 51))
          .addGroup(panelInformeLayout.createSequentialGroup()
            .addComponent(scrollPeriodistaInforme, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
        .addGroup(panelInformeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addGroup(panelInformeLayout.createSequentialGroup()
            .addComponent(labelTemaInforme)
            .addGap(57, 57, 57))
          .addGroup(panelInformeLayout.createSequentialGroup()
            .addComponent(scrollTemaInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
        .addGroup(panelInformeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelLugarInforme)
          .addComponent(textLugarInforme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelInformeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(labelPersonajeInforme)
          .addComponent(scrollPersonajeInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap())
    );

    panelTipos.setLayer(panelInforme, 2);
    panelTipos.add(panelInforme, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

    panelEntrevista.setName("panelEntrevista"); // NOI18N

    labelPeriodistaEntrevista.setText("Periodista");

    listaPeriodistaEntrevista.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    listaPeriodistaEntrevista.setToolTipText("Click derecho para borrar nombre");
    listaPeriodistaEntrevista.setMaximumSize(new java.awt.Dimension(0, 3));
    listaPeriodistaEntrevista.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        listaPeriodistaEntrevistaMouseClicked(evt);
      }
    });
    scrollPeriodistaEntrevista.setViewportView(listaPeriodistaEntrevista);

    labelEntrevistados.setText("Entrevistados");

    listaEntrevistados.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    listaEntrevistados.setToolTipText("Click derecho para borrar nombre");
    listaEntrevistados.setMaximumSize(new java.awt.Dimension(0, 3));
    listaEntrevistados.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        listaEntrevistadosMouseClicked(evt);
      }
    });
    scrollEntrevistados.setViewportView(listaEntrevistados);

    labelTemaEntrevista.setText("Tema");

    listaTemaEntrevista.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    listaTemaEntrevista.setToolTipText("Click derecho para borrar nombre");
    listaTemaEntrevista.setMaximumSize(new java.awt.Dimension(0, 3));
    listaTemaEntrevista.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        listaTemaEntrevistaMouseClicked(evt);
      }
    });
    scrollTemaEntrevista.setViewportView(listaTemaEntrevista);

    javax.swing.GroupLayout panelEntrevistaLayout = new javax.swing.GroupLayout(panelEntrevista);
    panelEntrevista.setLayout(panelEntrevistaLayout);
    panelEntrevistaLayout.setHorizontalGroup(
      panelEntrevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEntrevistaLayout.createSequentialGroup()
        .addGroup(panelEntrevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(labelTemaEntrevista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(labelEntrevistados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(labelPeriodistaEntrevista))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
        .addGroup(panelEntrevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(scrollTemaEntrevista, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
          .addComponent(scrollEntrevistados, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
          .addComponent(scrollPeriodistaEntrevista, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        .addContainerGap())
    );
    panelEntrevistaLayout.setVerticalGroup(
      panelEntrevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelEntrevistaLayout.createSequentialGroup()
        .addGroup(panelEntrevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addGroup(panelEntrevistaLayout.createSequentialGroup()
            .addComponent(labelPeriodistaEntrevista)
            .addGap(57, 57, 57))
          .addGroup(panelEntrevistaLayout.createSequentialGroup()
            .addComponent(scrollPeriodistaEntrevista, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
        .addGroup(panelEntrevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addGroup(panelEntrevistaLayout.createSequentialGroup()
            .addComponent(labelEntrevistados)
            .addGap(57, 57, 57))
          .addGroup(panelEntrevistaLayout.createSequentialGroup()
            .addComponent(scrollEntrevistados, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
        .addGroup(panelEntrevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(labelTemaEntrevista)
          .addComponent(scrollTemaEntrevista, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(24, 24, 24))
    );

    panelTipos.setLayer(panelEntrevista, 4);
    panelTipos.add(panelEntrevista, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

    panelSeccion.setName("panelSeccion"); // NOI18N

    labelNombreSeccion.setText("Nombre de la Sección");

    textNombreSeccion.setEditable(false);
    textNombreSeccion.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textNombreSeccionActionPerformed(evt);
      }
    });

    labelPanelistasSeccion.setText("Panelistas");

    listaPanelistasSeccion.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    listaPanelistasSeccion.setToolTipText("Click derecho para borrar nombre");
    listaPanelistasSeccion.setMaximumSize(new java.awt.Dimension(0, 3));
    listaPanelistasSeccion.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        listaPanelistasSeccionMouseClicked(evt);
      }
    });
    scrollPanelistasSeccion.setViewportView(listaPanelistasSeccion);

    labelTemaSeccion.setText("Tema");

    listaTemaSeccion.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    listaTemaSeccion.setToolTipText("Click derecho para borrar nombre");
    listaTemaSeccion.setMaximumSize(new java.awt.Dimension(0, 3));
    listaTemaSeccion.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        listaTemaSeccionMouseClicked(evt);
      }
    });
    scrollTemaSeccion.setViewportView(listaTemaSeccion);

    labelInvitadosSeccion.setText("Invitados");

    listaInvitadosSeccion.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    listaInvitadosSeccion.setToolTipText("Click derecho para borrar nombre");
    listaInvitadosSeccion.setMaximumSize(new java.awt.Dimension(0, 3));
    listaInvitadosSeccion.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        listaInvitadosSeccionMouseClicked(evt);
      }
    });
    scrollInvitadosSeccion.setViewportView(listaInvitadosSeccion);

    javax.swing.GroupLayout panelSeccionLayout = new javax.swing.GroupLayout(panelSeccion);
    panelSeccion.setLayout(panelSeccionLayout);
    panelSeccionLayout.setHorizontalGroup(
      panelSeccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelSeccionLayout.createSequentialGroup()
        .addGroup(panelSeccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
          .addComponent(labelTemaSeccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(labelPanelistasSeccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(labelNombreSeccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(labelInvitadosSeccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelSeccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(scrollInvitadosSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
          .addComponent(scrollTemaSeccion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
          .addComponent(scrollPanelistasSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
          .addComponent(textNombreSeccion, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
        .addContainerGap())
    );
    panelSeccionLayout.setVerticalGroup(
      panelSeccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelSeccionLayout.createSequentialGroup()
        .addGroup(panelSeccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelNombreSeccion)
          .addComponent(textNombreSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGroup(panelSeccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(panelSeccionLayout.createSequentialGroup()
            .addGap(6, 6, 6)
            .addComponent(labelPanelistasSeccion))
          .addGroup(panelSeccionLayout.createSequentialGroup()
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(scrollPanelistasSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelSeccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(labelTemaSeccion)
          .addComponent(scrollTemaSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelSeccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(labelInvitadosSeccion)
          .addComponent(scrollInvitadosSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(27, 27, 27))
    );

    panelTipos.setLayer(panelSeccion, 3);
    panelTipos.add(panelSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

    panelPanel.setName("panelPanel"); // NOI18N

    labelPanelistas.setText("Panelistas");

    listaPanelistas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    listaPanelistas.setToolTipText("Click derecho para borrar nombre");
    listaPanelistas.setMaximumSize(new java.awt.Dimension(0, 3));
    listaPanelistas.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        listaPanelistasMouseClicked(evt);
      }
    });
    scrollPanelistas.setViewportView(listaPanelistas);

    labelTemaPanel.setText("Tema");

    listaTemaPanel.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    listaTemaPanel.setToolTipText("Click derecho para borrar nombre");
    listaTemaPanel.setMaximumSize(new java.awt.Dimension(0, 3));
    listaTemaPanel.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        listaTemaPanelMouseClicked(evt);
      }
    });
    scrollTemaPanel.setViewportView(listaTemaPanel);

    javax.swing.GroupLayout panelPanelLayout = new javax.swing.GroupLayout(panelPanel);
    panelPanel.setLayout(panelPanelLayout);
    panelPanelLayout.setHorizontalGroup(
      panelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelPanelLayout.createSequentialGroup()
        .addGroup(panelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(labelPanelistas)
          .addComponent(labelTemaPanel))
        .addGap(18, 18, 18)
        .addGroup(panelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(scrollTemaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
          .addComponent(scrollPanelistas, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        .addContainerGap())
    );
    panelPanelLayout.setVerticalGroup(
      panelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelPanelLayout.createSequentialGroup()
        .addGroup(panelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(labelPanelistas)
          .addComponent(scrollPanelistas, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(labelTemaPanel)
          .addComponent(scrollTemaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(0, 0, 0))
    );

    panelTipos.setLayer(panelPanel, 6);
    panelTipos.add(panelPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

    panelDeporte.setName("panelDeporte"); // NOI18N

    labelRelator.setText("Relator");

    listaRelator.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    listaRelator.setToolTipText("Click derecho para borrar nombre");
    listaRelator.setMaximumSize(new java.awt.Dimension(0, 3));
    listaRelator.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        listaRelatorMouseClicked(evt);
      }
    });
    scrollRelator.setViewportView(listaRelator);

    labelLocutorComercial.setText("Locutor Comercial");

    listaLocutorComercial.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    listaLocutorComercial.setToolTipText("Click derecho para borrar nombre");
    listaLocutorComercial.setMaximumSize(new java.awt.Dimension(0, 3));
    listaLocutorComercial.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        listaLocutorComercialMouseClicked(evt);
      }
    });
    scrollLocutorComercial.setViewportView(listaLocutorComercial);

    labelEncargadoRRSS.setText("Encargado Redes Sociales");

    listaEncargadoRRSS.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    listaEncargadoRRSS.setToolTipText("Click derecho para borrar nombre");
    listaEncargadoRRSS.setMaximumSize(new java.awt.Dimension(0, 3));
    listaEncargadoRRSS.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        listaEncargadoRRSSMouseClicked(evt);
      }
    });
    scrollEncargadoRRSS.setViewportView(listaEncargadoRRSS);

    labelComentarista.setText("Comentarista");

    listaComentarista.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    listaComentarista.setToolTipText("Click derecho para borrar nombre");
    listaComentarista.setMaximumSize(new java.awt.Dimension(0, 3));
    listaComentarista.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        listaComentaristaMouseClicked(evt);
      }
    });
    scrollComentarista.setViewportView(listaComentarista);

    labelReportero.setText("Reportero en Cancha");

    listaReportero.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    listaReportero.setToolTipText("Click derecho para borrar nombre");
    listaReportero.setMaximumSize(new java.awt.Dimension(0, 3));
    listaReportero.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        listaReporteroMouseClicked(evt);
      }
    });
    scrollReportero.setViewportView(listaReportero);

    labelCompetencia.setText("Competencia");

    textCompetencia.setEditable(false);

    labelLugar.setText("Lugar");

    textLugar.setEditable(false);

    labelDisciplina.setText("Disciplina");

    textDisciplina.setEditable(false);

    panelDisciplina.setName("panelDisciplina"); // NOI18N
    panelDisciplina.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    panelFutbol.setAlignmentX(0.0F);
    panelFutbol.setAlignmentY(0.0F);
    panelFutbol.setName("panelFutbol"); // NOI18N

    labelEquipoLocalFutbol.setText("Equipo Local");

    textEquipoLocalFutbol.setEditable(false);
    textEquipoLocalFutbol.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textEquipoLocalFutbolActionPerformed(evt);
      }
    });

    labelEquipoVisitaFutbol.setText("Equipo Visita");

    textEquipoVisitaFutbol.setEditable(false);
    textEquipoVisitaFutbol.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textEquipoVisitaFutbolActionPerformed(evt);
      }
    });

    labelMarcadorFinalFutbol.setText("Marcador Final");

    textMarcadorFinalFutbol.setEditable(false);
    textMarcadorFinalFutbol.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textMarcadorFinalFutbolActionPerformed(evt);
      }
    });

    labelGoles.setText("Goles");

    botonAgregarGoles.setText("Agregar");

    javax.swing.GroupLayout panelFutbolLayout = new javax.swing.GroupLayout(panelFutbol);
    panelFutbol.setLayout(panelFutbolLayout);
    panelFutbolLayout.setHorizontalGroup(
      panelFutbolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelFutbolLayout.createSequentialGroup()
        .addGroup(panelFutbolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(labelMarcadorFinalFutbol)
          .addComponent(labelGoles)
          .addComponent(labelEquipoVisitaFutbol)
          .addComponent(labelEquipoLocalFutbol))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
        .addGroup(panelFutbolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(textEquipoLocalFutbol, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
          .addComponent(textEquipoVisitaFutbol, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFutbolLayout.createSequentialGroup()
            .addComponent(botonAgregarGoles)
            .addGap(1, 1, 1))
          .addComponent(textMarcadorFinalFutbol))
        .addContainerGap())
    );
    panelFutbolLayout.setVerticalGroup(
      panelFutbolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelFutbolLayout.createSequentialGroup()
        .addGroup(panelFutbolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelEquipoLocalFutbol)
          .addComponent(textEquipoLocalFutbol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelFutbolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelEquipoVisitaFutbol)
          .addComponent(textEquipoVisitaFutbol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelFutbolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelMarcadorFinalFutbol)
          .addComponent(textMarcadorFinalFutbol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelFutbolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelGoles)
          .addComponent(botonAgregarGoles)))
    );

    panelDisciplina.setLayer(panelFutbol, 6);
    panelDisciplina.add(panelFutbol, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 170));

    panelTenis.setAlignmentX(0.0F);
    panelTenis.setAlignmentY(0.0F);
    panelTenis.setName("panelTenis"); // NOI18N

    labelJugadores.setText("Jugadores");

    textJugadores.setEditable(false);
    textJugadores.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textJugadoresActionPerformed(evt);
      }
    });

    labelMarcadorFinalTenis.setText("Marcador Final");

    textMarcadorFinalTenis.setEditable(false);
    textMarcadorFinalTenis.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textMarcadorFinalTenisActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout panelTenisLayout = new javax.swing.GroupLayout(panelTenis);
    panelTenis.setLayout(panelTenisLayout);
    panelTenisLayout.setHorizontalGroup(
      panelTenisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelTenisLayout.createSequentialGroup()
        .addGroup(panelTenisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(labelMarcadorFinalTenis)
          .addComponent(labelJugadores))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(panelTenisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(textJugadores, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(textMarcadorFinalTenis, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(0, 163, Short.MAX_VALUE))
    );
    panelTenisLayout.setVerticalGroup(
      panelTenisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelTenisLayout.createSequentialGroup()
        .addGroup(panelTenisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelJugadores)
          .addComponent(textJugadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(panelTenisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelMarcadorFinalTenis)
          .addComponent(textMarcadorFinalTenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(0, 0, 0))
    );

    panelDisciplina.setLayer(panelTenis, 8);
    panelDisciplina.add(panelTenis, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 170));

    panelBasquetball.setAlignmentX(0.0F);
    panelBasquetball.setAlignmentY(0.0F);
    panelBasquetball.setName("panelBasquetball"); // NOI18N

    labelEquipoLocalBasquetball.setText("Equipo Local");

    textEquipoLocalBasquetball.setEditable(false);
    textEquipoLocalBasquetball.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textEquipoLocalBasquetballActionPerformed(evt);
      }
    });

    labelEquipoVisitaBasquetball.setText("Equipo Visita");

    textEquipoVisitaBasquetball.setEditable(false);
    textEquipoVisitaBasquetball.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textEquipoVisitaBasquetballActionPerformed(evt);
      }
    });

    labelMarcadorFinalBasquetball.setText("Marcador Final");

    textMarcadorFinalBasquetball.setEditable(false);
    textMarcadorFinalBasquetball.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textMarcadorFinalBasquetballActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout panelBasquetballLayout = new javax.swing.GroupLayout(panelBasquetball);
    panelBasquetball.setLayout(panelBasquetballLayout);
    panelBasquetballLayout.setHorizontalGroup(
      panelBasquetballLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelBasquetballLayout.createSequentialGroup()
        .addGroup(panelBasquetballLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(panelBasquetballLayout.createSequentialGroup()
            .addComponent(labelEquipoLocalBasquetball)
            .addGap(18, 18, 18)
            .addComponent(textEquipoLocalBasquetball))
          .addGroup(panelBasquetballLayout.createSequentialGroup()
            .addComponent(labelMarcadorFinalBasquetball)
            .addGap(8, 8, 8)
            .addComponent(textMarcadorFinalBasquetball, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
          .addGroup(panelBasquetballLayout.createSequentialGroup()
            .addComponent(labelEquipoVisitaBasquetball)
            .addGap(18, 18, 18)
            .addComponent(textEquipoVisitaBasquetball, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)))
        .addGap(0, 0, 0))
    );
    panelBasquetballLayout.setVerticalGroup(
      panelBasquetballLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelBasquetballLayout.createSequentialGroup()
        .addGroup(panelBasquetballLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelEquipoLocalBasquetball)
          .addComponent(textEquipoLocalBasquetball, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelBasquetballLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelEquipoVisitaBasquetball)
          .addComponent(textEquipoVisitaBasquetball, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelBasquetballLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelMarcadorFinalBasquetball)
          .addComponent(textMarcadorFinalBasquetball, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(0, 0, 0))
    );

    panelDisciplina.setLayer(panelBasquetball, 7);
    panelDisciplina.add(panelBasquetball, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 170));

    javax.swing.GroupLayout panelDeporteLayout = new javax.swing.GroupLayout(panelDeporte);
    panelDeporte.setLayout(panelDeporteLayout);
    panelDeporteLayout.setHorizontalGroup(
      panelDeporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(panelDisciplina, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDeporteLayout.createSequentialGroup()
        .addGroup(panelDeporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(labelReportero, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
          .addComponent(labelCompetencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(labelLugar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(labelDisciplina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelDeporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(textLugar)
          .addComponent(textCompetencia)
          .addComponent(scrollReportero, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
          .addComponent(textDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
      .addGroup(panelDeporteLayout.createSequentialGroup()
        .addGroup(panelDeporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
          .addComponent(labelComentarista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(labelEncargadoRRSS, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelDeporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(scrollEncargadoRRSS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
          .addComponent(scrollComentarista, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
      .addGroup(panelDeporteLayout.createSequentialGroup()
        .addGroup(panelDeporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(labelRelator, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
          .addComponent(labelLocutorComercial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelDeporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(scrollRelator, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
          .addComponent(scrollLocutorComercial, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
    );
    panelDeporteLayout.setVerticalGroup(
      panelDeporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelDeporteLayout.createSequentialGroup()
        .addGroup(panelDeporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(labelRelator)
          .addComponent(scrollRelator, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelDeporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(labelLocutorComercial)
          .addComponent(scrollLocutorComercial, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelDeporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(labelEncargadoRRSS)
          .addComponent(scrollEncargadoRRSS, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelDeporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(labelComentarista)
          .addComponent(scrollComentarista, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelDeporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(labelReportero)
          .addComponent(scrollReportero, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelDeporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelCompetencia)
          .addComponent(textCompetencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(7, 7, 7)
        .addGroup(panelDeporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelLugar)
          .addComponent(textLugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelDeporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelDisciplina)
          .addComponent(textDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(panelDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(0, 0, 0))
    );

    panelTipos.setLayer(panelDeporte, 5);
    panelTipos.add(panelDeporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

    scrollTipos.setViewportView(panelTipos);

    labelPalabrasClave.setText("Palabras Clave");

    listaPalabrasClave.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    listaPalabrasClave.setToolTipText("Click derecho para borrar nombre");
    listaPalabrasClave.setMaximumSize(new java.awt.Dimension(0, 3));
    listaPalabrasClave.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        listaPalabrasClaveMouseClicked(evt);
      }
    });
    scrollPalabrasClave.setViewportView(listaPalabrasClave);

    labelDescripcionFragmento.setText("Descripcion");

    textAreaDescripcionFragmento.setEditable(false);
    textAreaDescripcionFragmento.setColumns(20);
    textAreaDescripcionFragmento.setRows(5);
    textAreaDescripcionFragmento.setWrapStyleWord(true);

    javax.swing.GroupLayout panelFragmentoLayout = new javax.swing.GroupLayout(panelFragmento);
    panelFragmento.setLayout(panelFragmentoLayout);
    panelFragmentoLayout.setHorizontalGroup(
      panelFragmentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelFragmentoLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(panelFragmentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(panelFragmentoLayout.createSequentialGroup()
            .addGroup(panelFragmentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(labelDescripcionFragmento)
              .addComponent(labelPalabrasClave))
            .addGap(40, 40, 40)
            .addGroup(panelFragmentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(scrollPalabrasClave, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
              .addComponent(textAreaDescripcionFragmento, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)))
          .addComponent(scrollTipos, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
          .addGroup(panelFragmentoLayout.createSequentialGroup()
            .addGroup(panelFragmentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(labelAlturaTerminoFragmento)
              .addComponent(labelTipoAudio)
              .addComponent(labelAlturaInicioFragmento))
            .addGap(18, 18, 18)
            .addGroup(panelFragmentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(panelAlturaInicioFragmento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addGroup(panelFragmentoLayout.createSequentialGroup()
                .addGroup(panelFragmentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(panelAlturaTerminoFragmento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(textTipoAudio, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))))
          .addGroup(panelFragmentoLayout.createSequentialGroup()
            .addComponent(labelIdFragmento)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(valorIdFragmento, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE)))
        .addContainerGap())
    );
    panelFragmentoLayout.setVerticalGroup(
      panelFragmentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelFragmentoLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(panelFragmentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelIdFragmento)
          .addComponent(valorIdFragmento, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(8, 8, 8)
        .addGroup(panelFragmentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(labelAlturaInicioFragmento)
          .addComponent(panelAlturaInicioFragmento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(8, 8, 8)
        .addGroup(panelFragmentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(labelAlturaTerminoFragmento)
          .addComponent(panelAlturaTerminoFragmento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(12, 12, 12)
        .addGroup(panelFragmentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelTipoAudio)
          .addComponent(textTipoAudio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(scrollTipos, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelFragmentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(labelPalabrasClave)
          .addComponent(scrollPalabrasClave, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelFragmentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelDescripcionFragmento)
          .addComponent(textAreaDescripcionFragmento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(41, Short.MAX_VALUE))
    );

    botonResultadoAnterior.setText("<<");
    botonResultadoAnterior.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        botonResultadoAnteriorActionPerformed(evt);
      }
    });

    valorResultados.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    valorResultados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    valorResultados.setText("0/0");

    botonResultadoSiguiente.setText(">>");
    botonResultadoSiguiente.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        botonResultadoSiguienteActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout panelNavegacionLayout = new javax.swing.GroupLayout(panelNavegacion);
    panelNavegacion.setLayout(panelNavegacionLayout);
    panelNavegacionLayout.setHorizontalGroup(
      panelNavegacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelNavegacionLayout.createSequentialGroup()
        .addComponent(botonResultadoAnterior)
        .addGap(18, 18, 18)
        .addComponent(valorResultados)
        .addGap(18, 18, 18)
        .addComponent(botonResultadoSiguiente))
    );
    panelNavegacionLayout.setVerticalGroup(
      panelNavegacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelNavegacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
        .addComponent(botonResultadoAnterior)
        .addComponent(botonResultadoSiguiente)
        .addComponent(valorResultados))
    );

    botonSalir.setText("SALIR");
    botonSalir.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        botonSalirActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
              .addComponent(panelArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(panelPrograma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(18, 18, 18)
            .addComponent(panelFragmento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(labelTitulo)
              .addComponent(labelSubtitulo))
            .addGap(126, 126, 126)
            .addComponent(labelBuscarTexto)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(textBuscarTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(botonBuscarTexto)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelNavegacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(95, 95, 95)
            .addComponent(botonSalir)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(labelBuscarTexto)
              .addComponent(textBuscarTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(botonBuscarTexto)
              .addComponent(botonSalir))
            .addGap(12, 12, 12))
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(layout.createSequentialGroup()
                .addComponent(labelTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelSubtitulo))
              .addComponent(panelNavegacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(panelArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(panelPrograma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          .addComponent(panelFragmento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
    );

    getAccessibleContext().setAccessibleName("ACTUALIZACION DE ARCHIVOS");

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void textDiaEmisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textDiaEmisionActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_textDiaEmisionActionPerformed

  private void textMesEmisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textMesEmisionActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_textMesEmisionActionPerformed

  private void textAnhoEmisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textAnhoEmisionActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_textAnhoEmisionActionPerformed

  private void listaConductorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaConductorMouseClicked
    // TODO add your handling code here:
  }//GEN-LAST:event_listaConductorMouseClicked

  private void listaPalabrasClaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaPalabrasClaveMouseClicked
    // TODO add your handling code here:
  }//GEN-LAST:event_listaPalabrasClaveMouseClicked

  private void listaPanelistasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaPanelistasMouseClicked
    // TODO add your handling code here:
  }//GEN-LAST:event_listaPanelistasMouseClicked

  private void listaTemaPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaTemaPanelMouseClicked
    // TODO add your handling code here:
  }//GEN-LAST:event_listaTemaPanelMouseClicked

  private void listaPanelistasSeccionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaPanelistasSeccionMouseClicked
    // TODO add your handling code here:
  }//GEN-LAST:event_listaPanelistasSeccionMouseClicked

  private void listaTemaSeccionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaTemaSeccionMouseClicked
    // TODO add your handling code here:
  }//GEN-LAST:event_listaTemaSeccionMouseClicked

  private void listaInvitadosSeccionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaInvitadosSeccionMouseClicked
    // TODO add your handling code here:
  }//GEN-LAST:event_listaInvitadosSeccionMouseClicked

  private void listaTemaEntrevistaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaTemaEntrevistaMouseClicked
    // TODO add your handling code here:
  }//GEN-LAST:event_listaTemaEntrevistaMouseClicked

  private void listaEntrevistadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaEntrevistadosMouseClicked
    // TODO add your handling code here:
  }//GEN-LAST:event_listaEntrevistadosMouseClicked

  private void listaPeriodistaEntrevistaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaPeriodistaEntrevistaMouseClicked
    // TODO add your handling code here:
  }//GEN-LAST:event_listaPeriodistaEntrevistaMouseClicked

  private void listaTemaNoticiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaTemaNoticiaMouseClicked
    // TODO add your handling code here:
  }//GEN-LAST:event_listaTemaNoticiaMouseClicked

  private void listaPersonajeInformeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaPersonajeInformeMouseClicked
    // TODO add your handling code here:
  }//GEN-LAST:event_listaPersonajeInformeMouseClicked

  private void textLugarInformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textLugarInformeActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_textLugarInformeActionPerformed

  private void listaTemaInformeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaTemaInformeMouseClicked
    // TODO add your handling code here:
  }//GEN-LAST:event_listaTemaInformeMouseClicked

  private void listaPeriodistaInformeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaPeriodistaInformeMouseClicked
    // TODO add your handling code here:
  }//GEN-LAST:event_listaPeriodistaInformeMouseClicked

  private void textNombreSeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNombreSeccionActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_textNombreSeccionActionPerformed

  private void textBuscarTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textBuscarTextoActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_textBuscarTextoActionPerformed

  private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
    
    principal.setVisible(true);
    this.dispose();  
      
  }//GEN-LAST:event_botonSalirActionPerformed

  private void botonBuscarTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarTextoActionPerformed
    this.contadorArchivos = 0;
    this.contadorProgramas = 0;
    this.contadorFragmentos = 0;
    this.contadorResultados = 0;
    this.totalResultados = 0;
    
    if(!textBuscarTexto.getText().equals("")){
      this.resultados = archivoDao.buscar(textBuscarTexto.getText());
    } else {
      this.resultados = archivoDao.obtenerTodos();
    }
    
    //inicializaciones botones navegacion
    botonResultadoAnterior.setEnabled(false);
    
    for(Archivo archivo: this.resultados) {
      totalResultados += archivo.numeroFragmentos();
    }
    
    if(!this.resultados.isEmpty()){
      if(this.resultados.size() > 1 || this.resultados.get(this.contadorResultados).numeroFragmentos() > 1){
        botonResultadoSiguiente.setEnabled(true);
      }
      contadorResultados = 1;
      desplegarArchivo(contadorArchivos);
      desplegarPrograma(contadorProgramas);
      desplegarFragmento(contadorFragmentos);
      this.actualizarMarcadorResultados();
    } else {
      this.valorResultados.setText("NO HAY RESULTADOS");
    }
    
    
    
  }//GEN-LAST:event_botonBuscarTextoActionPerformed

  private void textMarcadorFinalBasquetballActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textMarcadorFinalBasquetballActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_textMarcadorFinalBasquetballActionPerformed

  private void textEquipoVisitaBasquetballActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textEquipoVisitaBasquetballActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_textEquipoVisitaBasquetballActionPerformed

  private void textEquipoLocalBasquetballActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textEquipoLocalBasquetballActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_textEquipoLocalBasquetballActionPerformed

  private void textMarcadorFinalTenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textMarcadorFinalTenisActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_textMarcadorFinalTenisActionPerformed

  private void textJugadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textJugadoresActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_textJugadoresActionPerformed

  private void textMarcadorFinalFutbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textMarcadorFinalFutbolActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_textMarcadorFinalFutbolActionPerformed

  private void textEquipoVisitaFutbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textEquipoVisitaFutbolActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_textEquipoVisitaFutbolActionPerformed

  private void textEquipoLocalFutbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textEquipoLocalFutbolActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_textEquipoLocalFutbolActionPerformed

  private void listaReporteroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaReporteroMouseClicked
    // TODO add your handling code here:
  }//GEN-LAST:event_listaReporteroMouseClicked

  private void listaComentaristaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaComentaristaMouseClicked
    // TODO add your handling code here:
  }//GEN-LAST:event_listaComentaristaMouseClicked

  private void listaEncargadoRRSSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaEncargadoRRSSMouseClicked
    // TODO add your handling code here:
  }//GEN-LAST:event_listaEncargadoRRSSMouseClicked

  private void listaLocutorComercialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaLocutorComercialMouseClicked
    // TODO add your handling code here:
  }//GEN-LAST:event_listaLocutorComercialMouseClicked

  private void listaRelatorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaRelatorMouseClicked
    // TODO add your handling code here:
  }//GEN-LAST:event_listaRelatorMouseClicked

  private void botonResultadoSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonResultadoSiguienteActionPerformed
    
    byte viArchivos = contadorArchivos;
    byte viProgramas = contadorProgramas;
    byte viFragmentos =  contadorFragmentos;
    contadorFragmentos++;
    this.contadorResultados++;
    
    
    if(resultados.get(contadorArchivos).getProgramas().get(contadorProgramas).getFragmentos().size() == contadorFragmentos){
      
      contadorFragmentos = 0;
      contadorProgramas++;
      
      if(resultados.get(contadorArchivos).getProgramas().size() == contadorProgramas) {
        contadorProgramas = 0;
        contadorArchivos++;
        
        if(resultados.size() == contadorArchivos){
          contadorArchivos = viArchivos;
          contadorProgramas = viProgramas;
          contadorFragmentos = viFragmentos;
          
          botonResultadoSiguiente.setEnabled(false);
        }
      }
    }
    
    botonResultadoAnterior.setEnabled(true);
    actualizarMarcadorResultados();
    
    if(this.contadorResultados == totalResultados){
      botonResultadoSiguiente.setEnabled(false);
    }
    
    desplegarArchivo(contadorArchivos);
    desplegarPrograma(contadorProgramas);
    desplegarFragmento(contadorFragmentos);
    
  }//GEN-LAST:event_botonResultadoSiguienteActionPerformed

  private void botonResultadoAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonResultadoAnteriorActionPerformed
    byte viArchivos = contadorArchivos;
    byte viProgramas = contadorProgramas;
    byte viFragmentos =  contadorFragmentos;
    
    this.contadorResultados--;
    contadorFragmentos--;
    
    if(contadorFragmentos < 0){
      contadorProgramas--;
      
      if(contadorProgramas < 0){
        contadorArchivos--;
        
        if(contadorArchivos < 0) {
          contadorArchivos = 0;
          contadorProgramas = 0;
          contadorFragmentos = 0;
          
        }
        contadorProgramas = (byte)(resultados.get(contadorArchivos).getProgramas().size() - 1);
      }
      contadorFragmentos = (byte)(resultados.get(contadorArchivos).getProgramas().get(contadorProgramas).getFragmentos().size() - 1);
    }
    
    botonResultadoSiguiente.setEnabled(true);
    
    
    if(this.contadorResultados > 0){
      actualizarMarcadorResultados();
    } 
    
    if(this.contadorResultados <= 1){
      botonResultadoAnterior.setEnabled(false);
    }
    
    desplegarArchivo(contadorArchivos);
    desplegarPrograma(contadorProgramas);
    desplegarFragmento(contadorFragmentos);
  }//GEN-LAST:event_botonResultadoAnteriorActionPerformed

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(VentanaBusqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(VentanaBusqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(VentanaBusqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(VentanaBusqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        new VentanaBusqueda("OPERADOR DE PRUEBA",new VentanaPrincipal("OPERADOR DE PRUEBA")).setVisible(true);
      }
      });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton botonAgregarGoles;
  private javax.swing.JButton botonBuscarTexto;
  private javax.swing.JButton botonResultadoAnterior;
  private javax.swing.JButton botonResultadoSiguiente;
  private javax.swing.JButton botonSalir;
  private javax.swing.JLabel labelAlturaInicioFragmento;
  private javax.swing.JLabel labelAlturaInicioFragmentoHora;
  private javax.swing.JLabel labelAlturaInicioFragmentoMinutos;
  private javax.swing.JLabel labelAlturaInicioFragmentoSegundos;
  private javax.swing.JLabel labelAlturaInicioPrograma;
  private javax.swing.JLabel labelAlturaInicioProgramaHora;
  private javax.swing.JLabel labelAlturaInicioProgramaMinutos;
  private javax.swing.JLabel labelAlturaInicioProgramaSegundos;
  private javax.swing.JLabel labelAlturaTerminoFragmento;
  private javax.swing.JLabel labelAlturaTerminoFragmentoHora;
  private javax.swing.JLabel labelAlturaTerminoFragmentoMinutos;
  private javax.swing.JLabel labelAlturaTerminoFragmentoSegundos;
  private javax.swing.JLabel labelAlturaTerminoPrograma;
  private javax.swing.JLabel labelAlturaTerminoProgramaHora;
  private javax.swing.JLabel labelAlturaTerminoProgramaMinutos;
  private javax.swing.JLabel labelAlturaTerminoProgramaSegundos;
  private javax.swing.JLabel labelBuscarTexto;
  private javax.swing.JLabel labelComentarista;
  private javax.swing.JLabel labelCompetencia;
  private javax.swing.JLabel labelConductor;
  private javax.swing.JLabel labelDescripcionFragmento;
  private javax.swing.JLabel labelDisciplina;
  private javax.swing.JLabel labelEncargadoRRSS;
  private javax.swing.JLabel labelEntrevistados;
  private javax.swing.JLabel labelEquipoLocalBasquetball;
  private javax.swing.JLabel labelEquipoLocalFutbol;
  private javax.swing.JLabel labelEquipoVisitaBasquetball;
  private javax.swing.JLabel labelEquipoVisitaFutbol;
  private javax.swing.JLabel labelFechaEmisionPrograma;
  private javax.swing.JLabel labelGoles;
  private javax.swing.JLabel labelIdArchivo;
  private javax.swing.JLabel labelIdFragmento;
  private javax.swing.JLabel labelInvitadosSeccion;
  private javax.swing.JLabel labelJugadores;
  private javax.swing.JLabel labelLocutorComercial;
  private javax.swing.JLabel labelLugar;
  private javax.swing.JLabel labelLugarInforme;
  private javax.swing.JLabel labelMarcadorFinalBasquetball;
  private javax.swing.JLabel labelMarcadorFinalFutbol;
  private javax.swing.JLabel labelMarcadorFinalTenis;
  private javax.swing.JLabel labelNombreArchivo;
  private javax.swing.JLabel labelNombrePrograma;
  private javax.swing.JLabel labelNombreSeccion;
  private javax.swing.JLabel labelPalabrasClave;
  private javax.swing.JLabel labelPanelistas;
  private javax.swing.JLabel labelPanelistasSeccion;
  private javax.swing.JLabel labelPeriodistaEntrevista;
  private javax.swing.JLabel labelPeriodistaInforme;
  private javax.swing.JLabel labelPersonajeInforme;
  private javax.swing.JLabel labelRelator;
  private javax.swing.JLabel labelReportero;
  private javax.swing.JLabel labelSeparador5;
  private javax.swing.JLabel labelSeparador6;
  private javax.swing.JLabel labelSubtitulo;
  private javax.swing.JLabel labelTemaEntrevista;
  private javax.swing.JLabel labelTemaInforme;
  private javax.swing.JLabel labelTemaNoticia;
  private javax.swing.JLabel labelTemaPanel;
  private javax.swing.JLabel labelTemaSeccion;
  private javax.swing.JLabel labelTipoAudio;
  private javax.swing.JLabel labelTitulo;
  private javax.swing.JList<String> listaComentarista;
  private javax.swing.JList<String> listaConductor;
  private javax.swing.JList<String> listaEncargadoRRSS;
  private javax.swing.JList<String> listaEntrevistados;
  private javax.swing.JList<String> listaInvitadosSeccion;
  private javax.swing.JList<String> listaLocutorComercial;
  private javax.swing.JList<String> listaPalabrasClave;
  private javax.swing.JList<String> listaPanelistas;
  private javax.swing.JList<String> listaPanelistasSeccion;
  private javax.swing.JList<String> listaPeriodistaEntrevista;
  private javax.swing.JList<String> listaPeriodistaInforme;
  private javax.swing.JList<String> listaPersonajeInforme;
  private javax.swing.JList<String> listaRelator;
  private javax.swing.JList<String> listaReportero;
  private javax.swing.JList<String> listaTemaEntrevista;
  private javax.swing.JList<String> listaTemaInforme;
  private javax.swing.JList<String> listaTemaNoticia;
  private javax.swing.JList<String> listaTemaPanel;
  private javax.swing.JList<String> listaTemaSeccion;
  private javax.swing.JPanel panelAlturaInicioFragmento;
  private javax.swing.JPanel panelAlturaInicioPrograma;
  private javax.swing.JPanel panelAlturaTerminoFragmento;
  private javax.swing.JPanel panelAlturaTerminoPrograma;
  private javax.swing.JPanel panelArchivo;
  private javax.swing.JPanel panelBasquetball;
  private javax.swing.JPanel panelDeporte;
  private javax.swing.JLayeredPane panelDisciplina;
  private javax.swing.JPanel panelEntrevista;
  private javax.swing.JPanel panelFechaEmisionPrograma;
  private javax.swing.JPanel panelFragmento;
  private javax.swing.JPanel panelFutbol;
  private javax.swing.JPanel panelInforme;
  private javax.swing.JPanel panelNavegacion;
  private javax.swing.JPanel panelNoticia;
  private javax.swing.JPanel panelPanel;
  private javax.swing.JPanel panelPrograma;
  private javax.swing.JPanel panelSeccion;
  private javax.swing.JPanel panelTenis;
  private javax.swing.JLayeredPane panelTipos;
  private javax.swing.JScrollPane scrollComentarista;
  private javax.swing.JScrollPane scrollConductor;
  private javax.swing.JScrollPane scrollEncargadoRRSS;
  private javax.swing.JScrollPane scrollEntrevistados;
  private javax.swing.JScrollPane scrollInvitadosSeccion;
  private javax.swing.JScrollPane scrollLocutorComercial;
  private javax.swing.JScrollPane scrollPalabrasClave;
  private javax.swing.JScrollPane scrollPanelistas;
  private javax.swing.JScrollPane scrollPanelistasSeccion;
  private javax.swing.JScrollPane scrollPeriodistaEntrevista;
  private javax.swing.JScrollPane scrollPeriodistaInforme;
  private javax.swing.JScrollPane scrollPersonajeInforme;
  private javax.swing.JScrollPane scrollRelator;
  private javax.swing.JScrollPane scrollReportero;
  private javax.swing.JScrollPane scrollTemaEntrevista;
  private javax.swing.JScrollPane scrollTemaInforme;
  private javax.swing.JScrollPane scrollTemaNoticia;
  private javax.swing.JScrollPane scrollTemaPanel;
  private javax.swing.JScrollPane scrollTemaSeccion;
  private javax.swing.JScrollPane scrollTipos;
  private javax.swing.JTextField textAnhoEmision;
  private javax.swing.JTextArea textAreaDescripcionFragmento;
  private javax.swing.JTextField textBuscarTexto;
  private javax.swing.JTextField textCompetencia;
  private javax.swing.JTextField textDiaEmision;
  private javax.swing.JTextField textDisciplina;
  private javax.swing.JTextField textEquipoLocalBasquetball;
  private javax.swing.JTextField textEquipoLocalFutbol;
  private javax.swing.JTextField textEquipoVisitaBasquetball;
  private javax.swing.JTextField textEquipoVisitaFutbol;
  private javax.swing.JTextField textIdArchivo;
  private javax.swing.JTextField textJugadores;
  private javax.swing.JTextField textLugar;
  private javax.swing.JTextField textLugarInforme;
  private javax.swing.JTextField textMarcadorFinalBasquetball;
  private javax.swing.JTextField textMarcadorFinalFutbol;
  private javax.swing.JTextField textMarcadorFinalTenis;
  private javax.swing.JTextField textMesEmision;
  private javax.swing.JTextField textNombreArchivo;
  private javax.swing.JTextField textNombrePrograma;
  private javax.swing.JTextField textNombreSeccion;
  private javax.swing.JTextField textTipoAudio;
  private javax.swing.JLabel valorAlturaInicioFragmentoHora;
  private javax.swing.JLabel valorAlturaInicioFragmentoMinutos;
  private javax.swing.JLabel valorAlturaInicioFragmentoSegundos;
  private javax.swing.JLabel valorAlturaInicioProgramaHora;
  private javax.swing.JLabel valorAlturaInicioProgramaMinutos;
  private javax.swing.JLabel valorAlturaInicioProgramaSegundos;
  private javax.swing.JLabel valorAlturaTerminoFragmentoHora;
  private javax.swing.JLabel valorAlturaTerminoFragmentoMinutos;
  private javax.swing.JLabel valorAlturaTerminoFragmentoSegundos;
  private javax.swing.JLabel valorAlturaTerminoProgramaHora;
  private javax.swing.JLabel valorAlturaTerminoProgramaMinutos;
  private javax.swing.JLabel valorAlturaTerminoProgramaSegundos;
  private javax.swing.JLabel valorIdFragmento;
  private javax.swing.JLabel valorResultados;
  // End of variables declaration//GEN-END:variables
}
