/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooperativa.vista;

import com.cooperativa.dao.*;
import com.cooperativa.model.*;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.SwingUtilities;

/**
 *
 * @author ftorrejon
 */
public class VentanaRegistrar extends javax.swing.JFrame {

  /**
   * Creates new form VentanaRegistrar
   */
  
  //declaracion de atributos personalizados
  private byte contadorProgramas;
  private byte contadorFragmentos;
  private final ConstDaoImpl constDao;
  private final ArchivoDaoImpl archivoDao;
  private final String operador;
  
  public VentanaRegistrar(String operador) {
    this.operador = operador;
    initComponents();
    
    //inicializacion de paneles no visibles al cargar ventana
    panelAgregarPrograma.setVisible(false);
    panelAgregarFragmento.setVisible(false);
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
    panelAgregarOtro.setVisible(false);
    
    //inicializacion DAOs
    this.constDao = new ConstDaoImpl();
    this.archivoDao = new ArchivoDaoImpl();
    
    //inicializacion de contadores
    this.contadorProgramas = 0;
    this.contadorFragmentos = 0;
    
    //inicializacion de comboBoxs
    completarCombo(comboResponsableDigitalizacion, "operadores");
    completarCombo(comboTipoSoporte, "soportes");
    completarCombo(comboNombrePrograma, "programas");
    completarCombo(comboConductor,"periodistas");
    completarCombo(comboEncargadoRRSS,"periodistas");
    completarCombo(comboLocutorComercial,"periodistas");
    completarCombo(comboComentarista,"periodistas");
    completarCombo(comboReportero,"periodistas");
    completarCombo(comboPalabrasClave,"palabrasClave");
    completarCombo(comboPanelistas,"panelistas");
    completarCombo(comboPanelistasSeccion,"panelistas");
    completarCombo(comboPeriodistaEntrevista,"periodistas");
    completarCombo(comboPeriodistaInforme,"periodistas");
    completarCombo(comboRelator,"periodistas");
    
    
  }
  
  //declaracion de metodos personalizados
  /**
   * Completa las <code>JComboBox</code> de los formularios con los valores almacenados en la base de datos
   * @param combo <code>JComboBox</code> que será configurado
   * @param tipo clase de elemento con que se completará el <code>JComboBox</code> 
   */
  private void completarCombo(javax.swing.JComboBox combo, String tipo){
    List<Object> listado = new ArrayList<>();
    listado = constDao.listarColeccion(tipo);
    for(Object e : listado) {
      combo.addItem(e.toString());
    }
  }
  
  /**
   * Agrega el elemento seleccionado del <code>JComboBox</code> a la <code>JList</code> asociada
   * @param comboOrigen <code>JComboBox</code> donde se encuentra el valor a agregar
   * @param listaFinal <code>JList</code> que recibe el valor seleccionado
   */
  private void agregarALista(javax.swing.JComboBox comboOrigen, javax.swing.JList listaFinal){
    DefaultListModel modelo = new DefaultListModel();
    boolean elementoExiste = false;
    
    //ciclo para rellenar modelo nuevo con elementos previos de la lista
    for(int i=0; i < listaFinal.getModel().getSize(); i++){
      modelo.addElement(listaFinal.getModel().getElementAt(i));
      //revisa si el elemento seleccionado existe previamente
      if(!elementoExiste) {
        if(comboOrigen.getSelectedItem().equals(listaFinal.getModel().getElementAt(i))){
          elementoExiste = true;
        }
      }
    }
    if(!elementoExiste && (comboOrigen.getSelectedIndex() != 0)){
      modelo.addElement(comboOrigen.getSelectedItem());
    }
    
    listaFinal.setModel(modelo);
  }
  
  /**
   * Agrega el elemento seleccionado del <code>JTextField</code> a la <code>JList</code> asociada
   * @param textOrigen <code>JTextField</code> donde se encuentra el valor a agregar
   * @param listaFinal <code>JList</code> que recibe el valor seleccionado
   */
  private void agregarALista(javax.swing.JTextField textOrigen, javax.swing.JList listaFinal){
    DefaultListModel modelo = new DefaultListModel();
    boolean elementoExiste = false;
    
    //ciclo para rellenar modelo nuevo con elementos previos de la lista
    for(int i=0; i < listaFinal.getModel().getSize(); i++){
      modelo.addElement(listaFinal.getModel().getElementAt(i));
      //revisa si el elemento seleccionado existe previamente
      if(!elementoExiste) {
        if(textOrigen.getText().equals(listaFinal.getModel().getElementAt(i))){
          elementoExiste = true;
        }
      }
    }
    if(!elementoExiste){
      modelo.addElement(textOrigen.getText());
    }
    
    listaFinal.setModel(modelo);
  }
  
  /**
   * Eliminar el elemento seleccionado desde la <code>JList</code>
   * @param lista <code>JList</code> donde se desea eliminar el elemento
   */
  private void eliminarElementoEnLista(javax.swing.JList lista){
    DefaultListModel modelo = new DefaultListModel();
    for(int i = 0; i < lista.getModel().getSize(); i++){
      if(!lista.getModel().getElementAt(i).equals(lista.getSelectedValue())){
        modelo.addElement(lista.getModel().getElementAt(i));
      }
    }
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
  
  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT
   * modify this code. The content of this method is always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    popupLista = new javax.swing.JPopupMenu();
    popupListaBorrar = new javax.swing.JMenuItem();
    labelSubtitulo = new javax.swing.JLabel();
    labelTitulo = new javax.swing.JLabel();
    panelCrearArchivo = new javax.swing.JPanel();
    labelIdArchivo = new javax.swing.JLabel();
    textIdArchivo = new javax.swing.JTextField();
    labelResponsableDigitalizacion = new javax.swing.JLabel();
    comboResponsableDigitalizacion = new javax.swing.JComboBox<>();
    labelCodigoSoporte = new javax.swing.JLabel();
    textCodigoSoporte = new javax.swing.JTextField();
    labelTipoSoporte = new javax.swing.JLabel();
    labelDescripcionExterior = new javax.swing.JLabel();
    textAreaDescripcionExterior = new javax.swing.JTextArea();
    labelNombreArchivo = new javax.swing.JLabel();
    textNombreArchivo = new javax.swing.JTextField();
    labelTamanhoArchivo = new javax.swing.JLabel();
    textTamanhoArchivo = new javax.swing.JTextField();
    labelDuracionArchivo = new javax.swing.JLabel();
    textDuracionArchivo = new javax.swing.JTextField();
    labelFormatoArchivo = new javax.swing.JLabel();
    panelFormato = new javax.swing.JPanel();
    labelCanales = new javax.swing.JLabel();
    comboCanales = new javax.swing.JComboBox<>();
    labelProfundidadBits = new javax.swing.JLabel();
    comboProfundidadBits = new javax.swing.JComboBox<>();
    labelFrecuenciaMuestreo = new javax.swing.JLabel();
    comboFrecuenciaMuestreo = new javax.swing.JComboBox<>();
    labelCodec = new javax.swing.JLabel();
    comboCodec = new javax.swing.JComboBox<>();
    labelTasaBits = new javax.swing.JLabel();
    comboTasaBits = new javax.swing.JComboBox<>();
    labelFechaDigitalizacion = new javax.swing.JLabel();
    comboTipoSoporte = new javax.swing.JComboBox<>();
    labelBytesArchivo = new javax.swing.JLabel();
    labelSegundosArchivo = new javax.swing.JLabel();
    panelFechaDigitalizacion = new javax.swing.JPanel();
    textDiaDigitalizacion = new javax.swing.JTextField();
    labelSeparador3 = new javax.swing.JLabel();
    textMesDigitalizacion = new javax.swing.JTextField();
    labelSeparador4 = new javax.swing.JLabel();
    textAnhoDigitalizacion = new javax.swing.JTextField();
    botonAgregarPrograma = new javax.swing.JButton();
    panelAgregarPrograma = new javax.swing.JPanel();
    labelAlturaInicioPrograma = new javax.swing.JLabel();
    textAlturaInicioPrograma = new javax.swing.JTextField();
    labelAlturaTerminoPrograma = new javax.swing.JLabel();
    textAlturaTerminoPrograma = new javax.swing.JTextField();
    labelNombrePrograma = new javax.swing.JLabel();
    comboNombrePrograma = new javax.swing.JComboBox<>();
    labelFechaEmisionPrograma = new javax.swing.JLabel();
    panelFechaEmisionPrograma = new javax.swing.JPanel();
    textDiaEmision = new javax.swing.JTextField();
    labelSeparador5 = new javax.swing.JLabel();
    textMesEmision = new javax.swing.JTextField();
    labelSeparador6 = new javax.swing.JLabel();
    textAnhoEmision = new javax.swing.JTextField();
    labelConductor = new javax.swing.JLabel();
    comboConductor = new javax.swing.JComboBox<>();
    botonAgregarFragmento = new javax.swing.JButton();
    scrollConductor = new javax.swing.JScrollPane();
    listaConductor = new javax.swing.JList<>();
    panelAgregarFragmento = new javax.swing.JPanel();
    labelAlturaInicioFragmento = new javax.swing.JLabel();
    textAlturaInicioFragmento = new javax.swing.JTextField();
    labelAlturaTerminoFragmento = new javax.swing.JLabel();
    textAlturaTerminoFragmento = new javax.swing.JTextField();
    labelTipoAudio = new javax.swing.JLabel();
    comboTipoAudio = new javax.swing.JComboBox<>();
    scrollTipos = new javax.swing.JScrollPane();
    panelTipos = new javax.swing.JLayeredPane();
    panelNoticia = new javax.swing.JPanel();
    labelTemaNoticia = new javax.swing.JLabel();
    textTemaNoticia = new javax.swing.JTextField();
    botonTemaNoticia = new javax.swing.JButton();
    scrollTemaNoticia = new javax.swing.JScrollPane();
    listaTemaNoticia = new javax.swing.JList<>();
    panelInforme = new javax.swing.JPanel();
    labelPeriodistaInforme = new javax.swing.JLabel();
    comboPeriodistaInforme = new javax.swing.JComboBox<>();
    scrollPeriodistaInforme = new javax.swing.JScrollPane();
    listaPeriodistaInforme = new javax.swing.JList<>();
    labelTemaInforme = new javax.swing.JLabel();
    textTemaInforme = new javax.swing.JTextField();
    botonTemaInforme = new javax.swing.JButton();
    scrollTemaInforme = new javax.swing.JScrollPane();
    listaTemaInforme = new javax.swing.JList<>();
    labelLugarInforme = new javax.swing.JLabel();
    textLugarInforme = new javax.swing.JTextField();
    labelPersonajeInforme = new javax.swing.JLabel();
    textPersonajeInforme = new javax.swing.JTextField();
    botonPersonajeInforme = new javax.swing.JButton();
    scrollPersonajeInforme = new javax.swing.JScrollPane();
    listaPersonajeInforme = new javax.swing.JList<>();
    panelEntrevista = new javax.swing.JPanel();
    labelPeriodistaEntrevista = new javax.swing.JLabel();
    comboPeriodistaEntrevista = new javax.swing.JComboBox<>();
    scrollPeriodistaEntrevista = new javax.swing.JScrollPane();
    listaPeriodistaEntrevista = new javax.swing.JList<>();
    labelEntrevistados = new javax.swing.JLabel();
    textEntrevistados = new javax.swing.JTextField();
    botonEntrevistados = new javax.swing.JButton();
    scrollEntrevistados = new javax.swing.JScrollPane();
    listaEntrevistados = new javax.swing.JList<>();
    labelTemaEntrevista = new javax.swing.JLabel();
    textTemaEntrevista = new javax.swing.JTextField();
    botonTemaEntrevista = new javax.swing.JButton();
    scrollTemaEntrevista = new javax.swing.JScrollPane();
    listaTemaEntrevista = new javax.swing.JList<>();
    panelSeccion = new javax.swing.JPanel();
    labelNombreSeccion = new javax.swing.JLabel();
    textNombreSeccion = new javax.swing.JTextField();
    labelPanelistasSeccion = new javax.swing.JLabel();
    comboPanelistasSeccion = new javax.swing.JComboBox<>();
    scrollPanelistasSeccion = new javax.swing.JScrollPane();
    listaPanelistasSeccion = new javax.swing.JList<>();
    labelTemaSeccion = new javax.swing.JLabel();
    textTemaSeccion = new javax.swing.JTextField();
    botonTemaSeccion = new javax.swing.JButton();
    scrollTemaSeccion = new javax.swing.JScrollPane();
    listaTemaSeccion = new javax.swing.JList<>();
    labelInvitadosSeccion = new javax.swing.JLabel();
    textInvitadosSeccion = new javax.swing.JTextField();
    botonInvitadosSeccion = new javax.swing.JButton();
    scrollInvitadosSeccion = new javax.swing.JScrollPane();
    listaInvitadosSeccion = new javax.swing.JList<>();
    panelPanel = new javax.swing.JPanel();
    labelPanelistas = new javax.swing.JLabel();
    comboPanelistas = new javax.swing.JComboBox<>();
    scrollPanelistas = new javax.swing.JScrollPane();
    listaPanelistas = new javax.swing.JList<>();
    labelTemaPanel = new javax.swing.JLabel();
    textTemaPanel = new javax.swing.JTextField();
    scrollTemaPanel = new javax.swing.JScrollPane();
    listaTemaPanel = new javax.swing.JList<>();
    botonTemaPanel = new javax.swing.JButton();
    panelDeporte = new javax.swing.JPanel();
    labelRelator = new javax.swing.JLabel();
    comboRelator = new javax.swing.JComboBox<>();
    scrollRelator = new javax.swing.JScrollPane();
    listaRelator = new javax.swing.JList<>();
    labelLocutorComercial = new javax.swing.JLabel();
    comboLocutorComercial = new javax.swing.JComboBox<>();
    scrollLocutorComercial = new javax.swing.JScrollPane();
    listaLocutorComercial = new javax.swing.JList<>();
    labelEncargadoRRSS = new javax.swing.JLabel();
    comboEncargadoRRSS = new javax.swing.JComboBox<>();
    scrollEncargadoRRSS = new javax.swing.JScrollPane();
    listaEncargadoRRSS = new javax.swing.JList<>();
    labelComentarista = new javax.swing.JLabel();
    comboComentarista = new javax.swing.JComboBox<>();
    scrollComentarista = new javax.swing.JScrollPane();
    listaComentarista = new javax.swing.JList<>();
    labelReportero = new javax.swing.JLabel();
    comboReportero = new javax.swing.JComboBox<>();
    scrollReportero = new javax.swing.JScrollPane();
    listaComentarista1 = new javax.swing.JList<>();
    labelCompetencia = new javax.swing.JLabel();
    textCompetencia = new javax.swing.JTextField();
    labelLugar = new javax.swing.JLabel();
    textLugar = new javax.swing.JTextField();
    labelDisciplina = new javax.swing.JLabel();
    comboDisciplina = new javax.swing.JComboBox<>();
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
    comboPalabrasClave = new javax.swing.JComboBox<>();
    scrollPalabrasClave = new javax.swing.JScrollPane();
    listaPalabrasClave = new javax.swing.JList<>();
    labelDescripcionFragmento = new javax.swing.JLabel();
    textAreaDescripcionFragmento = new javax.swing.JTextArea();
    botonGuardarFragmento = new javax.swing.JButton();
    panelAgregarOtro = new javax.swing.JPanel();
    botonOtroFragmento = new javax.swing.JButton();
    botonOtroPrograma = new javax.swing.JButton();

    popupListaBorrar.setText("Borrar");
    popupListaBorrar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        popupListaBorrarActionPerformed(evt);
      }
    });
    popupLista.add(popupListaBorrar);

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("REGISTRO DE ARCHIVOS");

    labelSubtitulo.setText("Archivo Radio Cooperativa");

    labelTitulo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    labelTitulo.setText("REGISTRO DE ARCHIVOS");

    panelCrearArchivo.setBorder(javax.swing.BorderFactory.createTitledBorder("Crear Archivo"));

    labelIdArchivo.setLabelFor(textIdArchivo);
    labelIdArchivo.setText("Id Archivo");

    labelResponsableDigitalizacion.setText("Responsable Digitalización");

    comboResponsableDigitalizacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige un nombre" }));
    comboResponsableDigitalizacion.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        comboResponsableDigitalizacionActionPerformed(evt);
      }
    });

    labelCodigoSoporte.setText("Código de Soporte");

    labelTipoSoporte.setText("Tipo Soporte");

    labelDescripcionExterior.setText("Descripcion Exterior");

    textAreaDescripcionExterior.setColumns(20);
    textAreaDescripcionExterior.setRows(5);
    textAreaDescripcionExterior.setWrapStyleWord(true);

    labelNombreArchivo.setText("Nombre de Archivo");

    labelTamanhoArchivo.setText("Tamaño");

    labelDuracionArchivo.setText("Duración");

    labelFormatoArchivo.setText("Formato");

    labelCanales.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
    labelCanales.setText("canales");

    comboCanales.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
    comboCanales.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2" }));

    labelProfundidadBits.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
    labelProfundidadBits.setText("<html>profundidad de bits</html>");

    comboProfundidadBits.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
    comboProfundidadBits.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "16", "32" }));
    comboProfundidadBits.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        comboProfundidadBitsActionPerformed(evt);
      }
    });

    labelFrecuenciaMuestreo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
    labelFrecuenciaMuestreo.setText("frecuencia muestreo");

    comboFrecuenciaMuestreo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
    comboFrecuenciaMuestreo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "32000", "44100", "48000" }));

    labelCodec.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
    labelCodec.setText("codec");

    comboCodec.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
    comboCodec.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MP3", "MPEG", "PCM" }));

    labelTasaBits.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
    labelTasaBits.setText("tasa de bits (kbps)");

    comboTasaBits.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
    comboTasaBits.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "48", "96", "112", "128", "256" }));
    comboTasaBits.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        comboTasaBitsActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout panelFormatoLayout = new javax.swing.GroupLayout(panelFormato);
    panelFormato.setLayout(panelFormatoLayout);
    panelFormatoLayout.setHorizontalGroup(
      panelFormatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelFormatoLayout.createSequentialGroup()
        .addGroup(panelFormatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(labelFrecuenciaMuestreo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(comboFrecuenciaMuestreo, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addGroup(panelFormatoLayout.createSequentialGroup()
            .addGroup(panelFormatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(labelCanales)
              .addComponent(comboCanales, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(35, 35, 35)
            .addGroup(panelFormatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(labelCodec)
              .addComponent(comboCodec, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))))
        .addGap(20, 20, 20)
        .addGroup(panelFormatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(comboTasaBits, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(labelProfundidadBits, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
          .addComponent(comboProfundidadBits, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(labelTasaBits))
        .addContainerGap())
    );
    panelFormatoLayout.setVerticalGroup(
      panelFormatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelFormatoLayout.createSequentialGroup()
        .addComponent(labelFrecuenciaMuestreo)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelFormatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(comboFrecuenciaMuestreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(comboProfundidadBits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
        .addGroup(panelFormatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelCanales)
          .addComponent(labelCodec)
          .addComponent(labelTasaBits))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelFormatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(comboCanales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(comboCodec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(comboTasaBits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
      .addGroup(panelFormatoLayout.createSequentialGroup()
        .addComponent(labelProfundidadBits, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(0, 0, Short.MAX_VALUE))
    );

    labelFechaDigitalizacion.setText("Fecha de Digitalización (DD/MM/AAAA)");

    comboTipoSoporte.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige un soporte" }));
    comboTipoSoporte.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        comboTipoSoporteActionPerformed(evt);
      }
    });

    labelBytesArchivo.setText("bytes");

    labelSegundosArchivo.setText("segundos");

    textDiaDigitalizacion.setColumns(2);
    textDiaDigitalizacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    textDiaDigitalizacion.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textDiaDigitalizacionActionPerformed(evt);
      }
    });

    labelSeparador3.setText("/");

    textMesDigitalizacion.setColumns(2);
    textMesDigitalizacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    textMesDigitalizacion.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textMesDigitalizacionActionPerformed(evt);
      }
    });

    labelSeparador4.setText("/");

    textAnhoDigitalizacion.setColumns(4);
    textAnhoDigitalizacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    textAnhoDigitalizacion.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textAnhoDigitalizacionActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout panelFechaDigitalizacionLayout = new javax.swing.GroupLayout(panelFechaDigitalizacion);
    panelFechaDigitalizacion.setLayout(panelFechaDigitalizacionLayout);
    panelFechaDigitalizacionLayout.setHorizontalGroup(
      panelFechaDigitalizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelFechaDigitalizacionLayout.createSequentialGroup()
        .addComponent(textDiaDigitalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(labelSeparador3)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(textMesDigitalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(labelSeparador4)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(textAnhoDigitalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    panelFechaDigitalizacionLayout.setVerticalGroup(
      panelFechaDigitalizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelFechaDigitalizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
        .addComponent(textMesDigitalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addComponent(labelSeparador4)
        .addComponent(textAnhoDigitalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
      .addGroup(panelFechaDigitalizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
        .addComponent(textDiaDigitalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addComponent(labelSeparador3))
    );

    botonAgregarPrograma.setText("Agregar Programa >>");
    botonAgregarPrograma.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        botonAgregarProgramaActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout panelCrearArchivoLayout = new javax.swing.GroupLayout(panelCrearArchivo);
    panelCrearArchivo.setLayout(panelCrearArchivoLayout);
    panelCrearArchivoLayout.setHorizontalGroup(
      panelCrearArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelCrearArchivoLayout.createSequentialGroup()
        .addComponent(panelFormato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(0, 0, Short.MAX_VALUE))
      .addGroup(panelCrearArchivoLayout.createSequentialGroup()
        .addGroup(panelCrearArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(labelNombreArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(labelTipoSoporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(labelCodigoSoporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(labelResponsableDigitalizacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(labelDescripcionExterior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(labelDuracionArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(labelFormatoArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(labelTamanhoArchivo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(labelFechaDigitalizacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(labelIdArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelCrearArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(textIdArchivo)
          .addComponent(textNombreArchivo)
          .addComponent(textCodigoSoporte)
          .addComponent(comboResponsableDigitalizacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(textAreaDescripcionExterior, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
          .addComponent(comboTipoSoporte, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(panelCrearArchivoLayout.createSequentialGroup()
            .addComponent(panelFechaDigitalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
          .addGroup(panelCrearArchivoLayout.createSequentialGroup()
            .addComponent(textDuracionArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelSegundosArchivo)
            .addGap(18, 18, 18))
          .addGroup(panelCrearArchivoLayout.createSequentialGroup()
            .addComponent(textTamanhoArchivo)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(labelBytesArchivo)
            .addGap(45, 45, 45))))
      .addGroup(panelCrearArchivoLayout.createSequentialGroup()
        .addComponent(botonAgregarPrograma)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    panelCrearArchivoLayout.setVerticalGroup(
      panelCrearArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelCrearArchivoLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(panelCrearArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelIdArchivo)
          .addComponent(textIdArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelCrearArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelResponsableDigitalizacion)
          .addComponent(comboResponsableDigitalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelCrearArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelCodigoSoporte)
          .addComponent(textCodigoSoporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelCrearArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelTipoSoporte)
          .addComponent(comboTipoSoporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelCrearArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(labelDescripcionExterior)
          .addComponent(textAreaDescripcionExterior, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(panelCrearArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelNombreArchivo)
          .addComponent(textNombreArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelCrearArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelTamanhoArchivo)
          .addComponent(textTamanhoArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(labelBytesArchivo))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelCrearArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelDuracionArchivo)
          .addComponent(textDuracionArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(labelSegundosArchivo))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(labelFormatoArchivo)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(panelFormato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(panelCrearArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(panelFechaDigitalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(labelFechaDigitalizacion))
        .addGap(18, 18, 18)
        .addComponent(botonAgregarPrograma)
        .addGap(0, 243, Short.MAX_VALUE))
    );

    panelAgregarPrograma.setBorder(javax.swing.BorderFactory.createTitledBorder("Agregar Programa"));
    panelAgregarPrograma.setName("panelAgregarPrograma"); // NOI18N

    labelAlturaInicioPrograma.setText("Altura Inicio");

    textAlturaInicioPrograma.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textAlturaInicioProgramaActionPerformed(evt);
      }
    });

    labelAlturaTerminoPrograma.setText("Altura Término");

    textAlturaTerminoPrograma.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textAlturaTerminoProgramaActionPerformed(evt);
      }
    });

    labelNombrePrograma.setText("Nombre del Programa");

    comboNombrePrograma.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige un programa" }));

    labelFechaEmisionPrograma.setText("Fecha Emision");

    textDiaEmision.setColumns(2);
    textDiaEmision.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    textDiaEmision.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textDiaEmisionActionPerformed(evt);
      }
    });

    labelSeparador5.setText("/");

    textMesEmision.setColumns(2);
    textMesEmision.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    textMesEmision.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textMesEmisionActionPerformed(evt);
      }
    });

    labelSeparador6.setText("/");

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

    comboConductor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige un conductor" }));
    comboConductor.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        comboConductorActionPerformed(evt);
      }
    });

    botonAgregarFragmento.setText("Agregar Fragmento >>");
    botonAgregarFragmento.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        botonAgregarFragmentoActionPerformed(evt);
      }
    });

    listaConductor.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    listaConductor.setToolTipText("Click derecho para borrar nombre");
    listaConductor.setMaximumSize(new java.awt.Dimension(0, 3));
    listaConductor.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        listaConductorMouseClicked(evt);
      }
    });
    scrollConductor.setViewportView(listaConductor);

    javax.swing.GroupLayout panelAgregarProgramaLayout = new javax.swing.GroupLayout(panelAgregarPrograma);
    panelAgregarPrograma.setLayout(panelAgregarProgramaLayout);
    panelAgregarProgramaLayout.setHorizontalGroup(
      panelAgregarProgramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelAgregarProgramaLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(panelAgregarProgramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(panelAgregarProgramaLayout.createSequentialGroup()
            .addComponent(labelAlturaTerminoPrograma)
            .addGap(56, 56, 56)
            .addComponent(textAlturaTerminoPrograma))
          .addGroup(panelAgregarProgramaLayout.createSequentialGroup()
            .addComponent(labelAlturaInicioPrograma)
            .addGap(69, 69, 69)
            .addComponent(textAlturaInicioPrograma))
          .addGroup(panelAgregarProgramaLayout.createSequentialGroup()
            .addGroup(panelAgregarProgramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(botonAgregarFragmento)
              .addGroup(panelAgregarProgramaLayout.createSequentialGroup()
                .addGroup(panelAgregarProgramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(labelConductor)
                  .addComponent(labelFechaEmisionPrograma)
                  .addComponent(labelNombrePrograma))
                .addGap(23, 23, 23)
                .addGroup(panelAgregarProgramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                  .addComponent(scrollConductor, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                  .addComponent(panelFechaEmisionPrograma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .addComponent(comboNombrePrograma, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .addComponent(comboConductor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGap(0, 0, Short.MAX_VALUE)))
        .addContainerGap())
    );
    panelAgregarProgramaLayout.setVerticalGroup(
      panelAgregarProgramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelAgregarProgramaLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(panelAgregarProgramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelAlturaInicioPrograma)
          .addComponent(textAlturaInicioPrograma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelAgregarProgramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelAlturaTerminoPrograma)
          .addComponent(textAlturaTerminoPrograma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelAgregarProgramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelNombrePrograma)
          .addComponent(comboNombrePrograma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelAgregarProgramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(labelFechaEmisionPrograma)
          .addComponent(panelFechaEmisionPrograma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelAgregarProgramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(labelConductor)
          .addComponent(comboConductor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(scrollConductor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(238, 238, 238)
        .addComponent(botonAgregarFragmento)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    panelAgregarFragmento.setBorder(javax.swing.BorderFactory.createTitledBorder("Agregar Fragmento"));
    panelAgregarFragmento.setName("panelAgregarFragmento"); // NOI18N

    labelAlturaInicioFragmento.setText("Altura de Inicio");

    textAlturaInicioFragmento.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textAlturaInicioFragmentoActionPerformed(evt);
      }
    });

    labelAlturaTerminoFragmento.setText("Altura de Término");

    textAlturaTerminoFragmento.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textAlturaTerminoFragmentoActionPerformed(evt);
      }
    });

    labelTipoAudio.setText("Tipo de Audio");

    comboTipoAudio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige el tipo de Audio", "Panel", "Deporte", "Entrevista", "Seccion", "Informe", "Noticia" }));
    comboTipoAudio.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        comboTipoAudioActionPerformed(evt);
      }
    });

    scrollTipos.setBorder(null);

    panelTipos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    panelNoticia.setName("panelNoticia"); // NOI18N

    labelTemaNoticia.setText("Tema de la Noticia");

    textTemaNoticia.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textTemaNoticiaActionPerformed(evt);
      }
    });

    botonTemaNoticia.setText("+");
    botonTemaNoticia.setMargin(new java.awt.Insets(2, 5, 2, 5));
    botonTemaNoticia.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        botonTemaNoticiaActionPerformed(evt);
      }
    });

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
        .addGroup(panelNoticiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(scrollTemaNoticia, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addGroup(panelNoticiaLayout.createSequentialGroup()
            .addComponent(textTemaNoticia, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(botonTemaNoticia)))
        .addContainerGap())
    );
    panelNoticiaLayout.setVerticalGroup(
      panelNoticiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelNoticiaLayout.createSequentialGroup()
        .addGroup(panelNoticiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelTemaNoticia)
          .addComponent(textTemaNoticia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(botonTemaNoticia))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(scrollTemaNoticia, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );

    panelTipos.setLayer(panelNoticia, 1);
    panelTipos.add(panelNoticia, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

    panelInforme.setName("panelInforme"); // NOI18N

    labelPeriodistaInforme.setText("Periodista");

    comboPeriodistaInforme.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige el nombre" }));
    comboPeriodistaInforme.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        comboPeriodistaInformeActionPerformed(evt);
      }
    });

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

    textTemaInforme.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textTemaInformeActionPerformed(evt);
      }
    });

    botonTemaInforme.setText("+");
    botonTemaInforme.setMargin(new java.awt.Insets(2, 5, 2, 5));
    botonTemaInforme.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        botonTemaInformeActionPerformed(evt);
      }
    });

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

    textLugarInforme.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textLugarInformeActionPerformed(evt);
      }
    });

    labelPersonajeInforme.setText("Cuñas de...");

    textPersonajeInforme.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textPersonajeInformeActionPerformed(evt);
      }
    });

    botonPersonajeInforme.setText("+");
    botonPersonajeInforme.setMargin(new java.awt.Insets(2, 5, 2, 5));
    botonPersonajeInforme.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        botonPersonajeInformeActionPerformed(evt);
      }
    });

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
        .addGroup(panelInformeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInformeLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(panelInformeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(labelTemaInforme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(labelLugarInforme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(labelPersonajeInforme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(0, 0, 0)
            .addGroup(panelInformeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInformeLayout.createSequentialGroup()
                .addGroup(panelInformeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(panelInformeLayout.createSequentialGroup()
                    .addComponent(textPersonajeInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(botonPersonajeInforme))
                  .addComponent(scrollPersonajeInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1))
              .addComponent(textLugarInforme, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInformeLayout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelInformeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInformeLayout.createSequentialGroup()
                .addComponent(labelPeriodistaInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInformeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(panelInformeLayout.createSequentialGroup()
                    .addComponent(textTemaInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(botonTemaInforme))
                  .addGroup(panelInformeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollPeriodistaInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(comboPeriodistaInforme, 0, 158, Short.MAX_VALUE))))
              .addComponent(scrollTemaInforme, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))))
        .addContainerGap())
    );
    panelInformeLayout.setVerticalGroup(
      panelInformeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelInformeLayout.createSequentialGroup()
        .addGroup(panelInformeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(comboPeriodistaInforme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(labelPeriodistaInforme))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(scrollPeriodistaInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(0, 0, 0)
        .addGroup(panelInformeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelTemaInforme)
          .addComponent(textTemaInforme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(botonTemaInforme))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(scrollTemaInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelInformeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelLugarInforme)
          .addComponent(textLugarInforme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelInformeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelPersonajeInforme)
          .addComponent(textPersonajeInforme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(botonPersonajeInforme))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(scrollPersonajeInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(0, 0, 0))
    );

    panelTipos.setLayer(panelInforme, 2);
    panelTipos.add(panelInforme, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

    panelEntrevista.setName("panelEntrevista"); // NOI18N

    labelPeriodistaEntrevista.setText("Periodista");

    comboPeriodistaEntrevista.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige el nombre" }));
    comboPeriodistaEntrevista.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        comboPeriodistaEntrevistaActionPerformed(evt);
      }
    });

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

    textEntrevistados.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textEntrevistadosActionPerformed(evt);
      }
    });

    botonEntrevistados.setText("+");
    botonEntrevistados.setMargin(new java.awt.Insets(2, 5, 2, 5));
    botonEntrevistados.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        botonEntrevistadosActionPerformed(evt);
      }
    });

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

    textTemaEntrevista.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textTemaEntrevistaActionPerformed(evt);
      }
    });

    botonTemaEntrevista.setText("+");
    botonTemaEntrevista.setMargin(new java.awt.Insets(2, 5, 2, 5));
    botonTemaEntrevista.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        botonTemaEntrevistaActionPerformed(evt);
      }
    });

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
          .addComponent(scrollTemaEntrevista, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
          .addComponent(scrollEntrevistados, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
          .addComponent(scrollPeriodistaEntrevista, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
          .addComponent(comboPeriodistaEntrevista, 0, 165, Short.MAX_VALUE)
          .addGroup(panelEntrevistaLayout.createSequentialGroup()
            .addComponent(textEntrevistados, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(botonEntrevistados))
          .addGroup(panelEntrevistaLayout.createSequentialGroup()
            .addComponent(textTemaEntrevista, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(botonTemaEntrevista)))
        .addContainerGap())
    );
    panelEntrevistaLayout.setVerticalGroup(
      panelEntrevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelEntrevistaLayout.createSequentialGroup()
        .addGroup(panelEntrevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelPeriodistaEntrevista)
          .addComponent(comboPeriodistaEntrevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(scrollPeriodistaEntrevista, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelEntrevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelEntrevistados)
          .addComponent(textEntrevistados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(botonEntrevistados))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(scrollEntrevistados, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelEntrevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelTemaEntrevista)
          .addComponent(textTemaEntrevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(botonTemaEntrevista))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(scrollTemaEntrevista, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(0, 0, 0))
    );

    panelTipos.setLayer(panelEntrevista, 4);
    panelTipos.add(panelEntrevista, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

    panelSeccion.setName("panelSeccion"); // NOI18N

    labelNombreSeccion.setText("Nombre de la Sección");

    textNombreSeccion.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textNombreSeccionActionPerformed(evt);
      }
    });

    labelPanelistasSeccion.setText("Panelistas");

    comboPanelistasSeccion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige el nombre" }));
    comboPanelistasSeccion.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        comboPanelistasSeccionActionPerformed(evt);
      }
    });

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

    botonTemaSeccion.setText("+");
    botonTemaSeccion.setMargin(new java.awt.Insets(2, 5, 2, 5));
    botonTemaSeccion.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        botonTemaSeccionActionPerformed(evt);
      }
    });

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

    botonInvitadosSeccion.setText("+");
    botonInvitadosSeccion.setMargin(new java.awt.Insets(2, 5, 2, 5));
    botonInvitadosSeccion.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        botonInvitadosSeccionActionPerformed(evt);
      }
    });

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
          .addComponent(comboPanelistasSeccion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(scrollPanelistasSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
          .addGroup(panelSeccionLayout.createSequentialGroup()
            .addComponent(textTemaSeccion)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(botonTemaSeccion))
          .addGroup(panelSeccionLayout.createSequentialGroup()
            .addComponent(textInvitadosSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
            .addComponent(botonInvitadosSeccion))
          .addComponent(textNombreSeccion))
        .addContainerGap())
    );
    panelSeccionLayout.setVerticalGroup(
      panelSeccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelSeccionLayout.createSequentialGroup()
        .addGroup(panelSeccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelNombreSeccion)
          .addComponent(textNombreSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(3, 3, 3)
        .addGroup(panelSeccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelPanelistasSeccion)
          .addComponent(comboPanelistasSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(scrollPanelistasSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelSeccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelTemaSeccion)
          .addComponent(textTemaSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(botonTemaSeccion))
        .addGap(1, 1, 1)
        .addComponent(scrollTemaSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelSeccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelInvitadosSeccion)
          .addComponent(textInvitadosSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(botonInvitadosSeccion))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(scrollInvitadosSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );

    panelTipos.setLayer(panelSeccion, 3);
    panelTipos.add(panelSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

    panelPanel.setName("panelPanel"); // NOI18N

    labelPanelistas.setText("Panelistas");

    comboPanelistas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige el nombre" }));
    comboPanelistas.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        comboPanelistasActionPerformed(evt);
      }
    });

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

    textTemaPanel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textTemaPanelActionPerformed(evt);
      }
    });

    listaTemaPanel.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    listaTemaPanel.setToolTipText("Click derecho para borrar nombre");
    listaTemaPanel.setMaximumSize(new java.awt.Dimension(0, 3));
    listaTemaPanel.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        listaTemaPanelMouseClicked(evt);
      }
    });
    scrollTemaPanel.setViewportView(listaTemaPanel);

    botonTemaPanel.setText("+");
    botonTemaPanel.setMargin(new java.awt.Insets(2, 5, 2, 5));
    botonTemaPanel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        botonTemaPanelActionPerformed(evt);
      }
    });

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
          .addComponent(scrollTemaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
          .addComponent(comboPanelistas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(scrollPanelistas, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
          .addGroup(panelPanelLayout.createSequentialGroup()
            .addComponent(textTemaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(botonTemaPanel)
            .addGap(0, 0, Short.MAX_VALUE)))
        .addContainerGap())
    );
    panelPanelLayout.setVerticalGroup(
      panelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelPanelLayout.createSequentialGroup()
        .addGroup(panelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelPanelistas)
          .addComponent(comboPanelistas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(scrollPanelistas, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelTemaPanel)
          .addComponent(textTemaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(botonTemaPanel))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(scrollTemaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(0, 0, 0))
    );

    panelTipos.setLayer(panelPanel, 6);
    panelTipos.add(panelPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

    panelDeporte.setName("panelDeporte"); // NOI18N

    labelRelator.setText("Relator");

    comboRelator.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige el nombre" }));
    comboRelator.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        comboRelatorActionPerformed(evt);
      }
    });

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

    comboLocutorComercial.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige el nombre" }));
    comboLocutorComercial.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        comboLocutorComercialActionPerformed(evt);
      }
    });

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

    comboEncargadoRRSS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige el nombre" }));
    comboEncargadoRRSS.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        comboEncargadoRRSSActionPerformed(evt);
      }
    });

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

    comboComentarista.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige el nombre" }));
    comboComentarista.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        comboComentaristaActionPerformed(evt);
      }
    });

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

    comboReportero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige el nombre" }));
    comboReportero.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        comboReporteroActionPerformed(evt);
      }
    });

    listaComentarista1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    listaComentarista1.setToolTipText("Click derecho para borrar nombre");
    listaComentarista1.setMaximumSize(new java.awt.Dimension(0, 3));
    listaComentarista1.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        listaComentarista1MouseClicked(evt);
      }
    });
    scrollReportero.setViewportView(listaComentarista1);

    labelCompetencia.setText("Competencia");

    labelLugar.setText("Lugar");

    labelDisciplina.setText("Disciplina");

    comboDisciplina.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige una disciplina", "Tenis", "Basquetball", "Futbol" }));
    comboDisciplina.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        comboDisciplinaActionPerformed(evt);
      }
    });

    panelDisciplina.setName("panelDisciplina"); // NOI18N
    panelDisciplina.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    panelFutbol.setAlignmentX(0.0F);
    panelFutbol.setAlignmentY(0.0F);
    panelFutbol.setName("panelFutbol"); // NOI18N

    labelEquipoLocalFutbol.setText("Equipo Local");

    textEquipoLocalFutbol.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textEquipoLocalFutbolActionPerformed(evt);
      }
    });

    labelEquipoVisitaFutbol.setText("Equipo Visita");

    textEquipoVisitaFutbol.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textEquipoVisitaFutbolActionPerformed(evt);
      }
    });

    labelMarcadorFinalFutbol.setText("Marcador Final");

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

    textJugadores.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textJugadoresActionPerformed(evt);
      }
    });

    labelMarcadorFinalTenis.setText("Marcador Final");

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

    textEquipoLocalBasquetball.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textEquipoLocalBasquetballActionPerformed(evt);
      }
    });

    labelEquipoVisitaBasquetball.setText("Equipo Visita");

    textEquipoVisitaBasquetball.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textEquipoVisitaBasquetballActionPerformed(evt);
      }
    });

    labelMarcadorFinalBasquetball.setText("Marcador Final");

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
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDeporteLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(panelDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        .addContainerGap())
      .addGroup(panelDeporteLayout.createSequentialGroup()
        .addGroup(panelDeporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
          .addComponent(labelComentarista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(labelEncargadoRRSS, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
          .addComponent(labelLocutorComercial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(labelRelator, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelDeporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(scrollRelator, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
          .addComponent(comboRelator, 0, 156, Short.MAX_VALUE)
          .addComponent(comboEncargadoRRSS, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(scrollLocutorComercial, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
          .addComponent(comboLocutorComercial, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(scrollEncargadoRRSS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
          .addComponent(comboComentarista, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(scrollComentarista, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDeporteLayout.createSequentialGroup()
        .addGroup(panelDeporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(labelReportero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(labelCompetencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(labelLugar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(labelDisciplina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelDeporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(comboDisciplina, 0, 156, Short.MAX_VALUE)
          .addComponent(textLugar)
          .addComponent(textCompetencia)
          .addComponent(comboReportero, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(scrollReportero, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
    );
    panelDeporteLayout.setVerticalGroup(
      panelDeporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelDeporteLayout.createSequentialGroup()
        .addGroup(panelDeporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelRelator)
          .addComponent(comboRelator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(scrollRelator, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelDeporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelLocutorComercial)
          .addComponent(comboLocutorComercial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(scrollLocutorComercial, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelDeporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelEncargadoRRSS)
          .addComponent(comboEncargadoRRSS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(2, 2, 2)
        .addComponent(scrollEncargadoRRSS, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelDeporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelComentarista)
          .addComponent(comboComentarista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(2, 2, 2)
        .addComponent(scrollComentarista, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
        .addGroup(panelDeporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelReportero)
          .addComponent(comboReportero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(2, 2, 2)
        .addComponent(scrollReportero, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
          .addComponent(comboDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(18, 18, 18)
        .addComponent(panelDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );

    panelTipos.setLayer(panelDeporte, 5);
    panelTipos.add(panelDeporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

    scrollTipos.setViewportView(panelTipos);

    labelPalabrasClave.setText("Palabras Clave");

    comboPalabrasClave.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige palabras clave" }));
    comboPalabrasClave.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        comboPalabrasClaveActionPerformed(evt);
      }
    });

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

    textAreaDescripcionFragmento.setColumns(20);
    textAreaDescripcionFragmento.setRows(5);
    textAreaDescripcionFragmento.setWrapStyleWord(true);

    botonGuardarFragmento.setText("Guardar Fragmento");
    botonGuardarFragmento.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        botonGuardarFragmentoActionPerformed(evt);
      }
    });

    botonOtroFragmento.setText("Otro Fragmento");
    botonOtroFragmento.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        botonOtroFragmentoActionPerformed(evt);
      }
    });

    botonOtroPrograma.setText("Otro Programa");
    botonOtroPrograma.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        botonOtroProgramaActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout panelAgregarOtroLayout = new javax.swing.GroupLayout(panelAgregarOtro);
    panelAgregarOtro.setLayout(panelAgregarOtroLayout);
    panelAgregarOtroLayout.setHorizontalGroup(
      panelAgregarOtroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelAgregarOtroLayout.createSequentialGroup()
        .addComponent(botonOtroFragmento)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(botonOtroPrograma))
    );
    panelAgregarOtroLayout.setVerticalGroup(
      panelAgregarOtroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelAgregarOtroLayout.createSequentialGroup()
        .addGroup(panelAgregarOtroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(botonOtroFragmento)
          .addComponent(botonOtroPrograma))
        .addGap(0, 0, 0))
    );

    javax.swing.GroupLayout panelAgregarFragmentoLayout = new javax.swing.GroupLayout(panelAgregarFragmento);
    panelAgregarFragmento.setLayout(panelAgregarFragmentoLayout);
    panelAgregarFragmentoLayout.setHorizontalGroup(
      panelAgregarFragmentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelAgregarFragmentoLayout.createSequentialGroup()
        .addGroup(panelAgregarFragmentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(panelAgregarOtro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(panelAgregarFragmentoLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(panelAgregarFragmentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(panelAgregarFragmentoLayout.createSequentialGroup()
                .addComponent(labelAlturaInicioFragmento)
                .addGap(32, 32, 32)
                .addComponent(textAlturaInicioFragmento, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addGroup(panelAgregarFragmentoLayout.createSequentialGroup()
                .addGroup(panelAgregarFragmentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(labelAlturaTerminoFragmento)
                  .addComponent(labelTipoAudio))
                .addGap(18, 18, 18)
                .addGroup(panelAgregarFragmentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                  .addComponent(textAlturaTerminoFragmento)
                  .addComponent(comboTipoAudio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
              .addGroup(panelAgregarFragmentoLayout.createSequentialGroup()
                .addGroup(panelAgregarFragmentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(labelDescripcionFragmento)
                  .addComponent(labelPalabrasClave))
                .addGap(40, 40, 40)
                .addGroup(panelAgregarFragmentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(scrollPalabrasClave, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                  .addComponent(textAreaDescripcionFragmento, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                  .addComponent(comboPalabrasClave, 0, 174, Short.MAX_VALUE)))
              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAgregarFragmentoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(botonGuardarFragmento))
              .addComponent(scrollTipos, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE))))
        .addContainerGap())
    );
    panelAgregarFragmentoLayout.setVerticalGroup(
      panelAgregarFragmentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelAgregarFragmentoLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(panelAgregarFragmentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelAlturaInicioFragmento)
          .addComponent(textAlturaInicioFragmento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelAgregarFragmentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelAlturaTerminoFragmento)
          .addComponent(textAlturaTerminoFragmento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelAgregarFragmentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelTipoAudio)
          .addComponent(comboTipoAudio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(scrollTipos, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelAgregarFragmentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(comboPalabrasClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(labelPalabrasClave))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(scrollPalabrasClave, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelAgregarFragmentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelDescripcionFragmento)
          .addComponent(textAreaDescripcionFragmento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(18, 18, 18)
        .addComponent(botonGuardarFragmento)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(panelAgregarOtro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(panelCrearArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(panelAgregarPrograma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(panelAgregarFragmento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(labelTitulo)
              .addComponent(labelSubtitulo))
            .addGap(0, 0, Short.MAX_VALUE)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(labelTitulo)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(labelSubtitulo)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(panelCrearArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(panelAgregarPrograma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(panelAgregarFragmento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void comboTasaBitsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTasaBitsActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_comboTasaBitsActionPerformed

  private void comboProfundidadBitsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboProfundidadBitsActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_comboProfundidadBitsActionPerformed

  private void comboTipoSoporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoSoporteActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_comboTipoSoporteActionPerformed

  private void comboResponsableDigitalizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboResponsableDigitalizacionActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_comboResponsableDigitalizacionActionPerformed

  private void textDiaDigitalizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textDiaDigitalizacionActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_textDiaDigitalizacionActionPerformed

  private void textMesDigitalizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textMesDigitalizacionActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_textMesDigitalizacionActionPerformed

  private void textAnhoDigitalizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textAnhoDigitalizacionActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_textAnhoDigitalizacionActionPerformed

  private void textAlturaInicioProgramaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textAlturaInicioProgramaActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_textAlturaInicioProgramaActionPerformed

  private void textAlturaTerminoProgramaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textAlturaTerminoProgramaActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_textAlturaTerminoProgramaActionPerformed

  private void textDiaEmisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textDiaEmisionActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_textDiaEmisionActionPerformed

  private void textMesEmisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textMesEmisionActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_textMesEmisionActionPerformed

  private void textAnhoEmisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textAnhoEmisionActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_textAnhoEmisionActionPerformed

  private void botonAgregarProgramaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarProgramaActionPerformed
    
    //variables
    boolean formularioListo = true;
    
    //Al presionar AgregarPrograma, se genera el Archivo y se guarda en la base de datos
    
    //creacion del archivo, con parámetros de fecha y operador no modificable
    Archivo archivo = new Archivo(new Date(), this.operador);
    
    try {
      //Creacion y llenado del Archivo
      if(!textIdArchivo.getText().equals("")){
        labelIdArchivo.setForeground(null);
        archivo.setId(textIdArchivo.getText());
      } else {
        labelIdArchivo.setForeground(Color.red);
        formularioListo = false;
      }
      
      if(comboResponsableDigitalizacion.getSelectedIndex() != 0){
        labelResponsableDigitalizacion.setForeground(null);
        archivo.setResponsableDigitalizacion(comboResponsableDigitalizacion.getSelectedItem().toString());
      } else {
        labelResponsableDigitalizacion.setForeground(Color.red);
        formularioListo = false;
      }
      
      if(textCodigoSoporte.getText().equals("")){
        textCodigoSoporte.setText("{}");
        textCodigoSoporte.setEditable(false);
      }
      archivo.setCodigoSoporte(textCodigoSoporte.getText());
      
      if(comboTipoSoporte.getSelectedIndex() != 0){
        labelTipoSoporte.setForeground(null);
        archivo.setTipoSoporte(comboTipoSoporte.getSelectedItem().toString());
      } else {
        labelTipoSoporte.setForeground(Color.red);
        formularioListo = false;
      }
      
      if(textAreaDescripcionExterior.getDocument().getLength() == 0){
        textAreaDescripcionExterior.setText("{}");
        textAreaDescripcionExterior.setEditable(false);
      }
      archivo.setDescripcionExterior(textAreaDescripcionExterior.getText());
      
      if(!textNombreArchivo.getText().equals("")){
        labelNombreArchivo.setForeground(null);
        archivo.setNombreArchivo(textNombreArchivo.getText());
      } else {
        labelNombreArchivo.setForeground(Color.red);
        formularioListo = false;
      }
      
      if(!textTamanhoArchivo.getText().equals("")){
        labelTamanhoArchivo.setForeground(null);
        archivo.setTamanhoArchivo(Integer.parseInt(textTamanhoArchivo.getText()));
      } else {
        labelTamanhoArchivo.setForeground(Color.red);
        formularioListo = false;
      }
      if(!textDuracionArchivo.getText().equals("")){
        labelDuracionArchivo.setForeground(null);
        archivo.setDuracionArchivo(Integer.parseInt(textDuracionArchivo.getText()));
      } else {
        labelDuracionArchivo.setForeground(Color.red);
        formularioListo = false;
      }
      
      archivo.setFormatoArchivo(new Formato(
              Byte.parseByte(comboCanales.getSelectedItem().toString()),
              Byte.parseByte(comboProfundidadBits.getSelectedItem().toString()),
              Integer.parseInt(comboFrecuenciaMuestreo.getSelectedItem().toString()),
              comboCodec.getSelectedItem().toString(),
              Short.parseShort(comboTasaBits.getSelectedItem().toString())
      ));
      
      archivo.setFechaDigitalizacion(new GregorianCalendar(
              Integer.parseInt(textAnhoDigitalizacion.getText()),
              (Integer.parseInt(textMesDigitalizacion.getText()) - 1),
              Integer.parseInt(textDiaDigitalizacion.getText())
      ).getTime());
      
      
      if(formularioListo){
        //guardado del Archivo en base de datos
        archivoDao.crearArchivo(archivo);

        //Desactiva panel para evitar reingreso de Archivo
        this.setPanelEnabled(panelCrearArchivo, false);
        
        //revela panel Agregar Fragmento
        panelAgregarPrograma.setVisible(true);
    
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }//GEN-LAST:event_botonAgregarProgramaActionPerformed

  private void botonAgregarFragmentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarFragmentoActionPerformed
    
    //Al presionar AgregarFragmento se inserta la informacion del programa al Archivo
    //creación de Programa
    Programa programa = new Programa(String.valueOf(this.contadorProgramas));
    boolean formularioListo = true;
    
    //insercion de informacion en Programa
    try {
      if(!textAlturaInicioPrograma.getText().equals("")){
        labelAlturaInicioPrograma.setForeground(null);
        programa.setAlturaInicio(Integer.parseInt(textAlturaInicioPrograma.getText()));
      } else {
        formularioListo = false;
        labelAlturaInicioPrograma.setForeground(Color.red);        
      }
      
      if(!textAlturaTerminoPrograma.getText().equals("")){
        labelAlturaTerminoPrograma.setForeground(null);
        programa.setAlturaTermino(Integer.parseInt(textAlturaTerminoPrograma.getText()));
      } else {
        formularioListo = false;
        labelAlturaTerminoPrograma.setForeground(Color.red);
      }
      
      if(comboNombrePrograma.getSelectedIndex() != 0){
        labelNombrePrograma.setForeground(null);
        programa.setNombrePrograma(comboNombrePrograma.getSelectedItem().toString());
      } else {
        formularioListo = false;
        labelNombrePrograma.setForeground(Color.red);
      }
      
      programa.setFechaEmision(new GregorianCalendar(
              Integer.parseInt(textAnhoEmision.getText()),
              (Integer.parseInt(textMesEmision.getText()) - 1),
              Integer.parseInt(textDiaEmision.getText())
      ).getTime());
      
      if(listaConductor.getModel().getSize() != 0){
        labelConductor.setForeground(null);
        for(int i = 0; i < listaConductor.getModel().getSize(); i++){
          programa.agregarConductor(listaConductor.getModel().getElementAt(i));
        }
      } else {
        formularioListo = false;
        labelConductor.setForeground(Color.red);
      }
      
      if(formularioListo){
        //agrega Programa a lista Programas en base de datos
        archivoDao.insertarPrograma(textIdArchivo.getText(), programa);
        //desactiva panel para evitar doble grabación del encabezado
        this.setPanelEnabled(panelAgregarPrograma, false);
        // visibiliza, activa y limpia panel AgregarFragmento
        panelAgregarFragmento.setVisible(true);
        this.setPanelEnabled(panelAgregarFragmento, true);
        this.limpiarPanel(panelAgregarFragmento);
      }
    } catch (Exception e){
      e.printStackTrace();
    } 
  }//GEN-LAST:event_botonAgregarFragmentoActionPerformed

  private void textTemaPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textTemaPanelActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_textTemaPanelActionPerformed

  private void textTemaEntrevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textTemaEntrevistaActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_textTemaEntrevistaActionPerformed

  private void textEntrevistadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textEntrevistadosActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_textEntrevistadosActionPerformed

  private void textTemaNoticiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textTemaNoticiaActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_textTemaNoticiaActionPerformed

  private void comboTipoAudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoAudioActionPerformed

    if(!(comboTipoAudio.getSelectedIndex() == 0)){
      if(!scrollTipos.isVisible()){
        scrollTipos.setVisible(true);
      }

      for(Component c: panelTipos.getComponents()){
        c.setVisible(false);
      }
      panelTipos.getComponent(comboTipoAudio.getSelectedIndex()-1).setVisible(true);
    }

  }//GEN-LAST:event_comboTipoAudioActionPerformed

  private void textAlturaTerminoFragmentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textAlturaTerminoFragmentoActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_textAlturaTerminoFragmentoActionPerformed

  private void textAlturaInicioFragmentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textAlturaInicioFragmentoActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_textAlturaInicioFragmentoActionPerformed

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

  private void comboDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboDisciplinaActionPerformed
    if(comboDisciplina.getSelectedIndex() != 0){
      if(!panelDisciplina.isVisible()){
        panelDisciplina.setVisible(true);
      }

      for(Component c: panelDisciplina.getComponents()){
        c.setVisible(false);
      }
      panelDisciplina.getComponent(comboDisciplina.getSelectedIndex()-1).setVisible(true);
    }
  }//GEN-LAST:event_comboDisciplinaActionPerformed

  private void comboPalabrasClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPalabrasClaveActionPerformed
    this.agregarALista(comboPalabrasClave, listaPalabrasClave);
  }//GEN-LAST:event_comboPalabrasClaveActionPerformed

  private void comboEncargadoRRSSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboEncargadoRRSSActionPerformed
    this.agregarALista(comboEncargadoRRSS, listaEncargadoRRSS);
  }//GEN-LAST:event_comboEncargadoRRSSActionPerformed

  private void comboRelatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboRelatorActionPerformed
    this.agregarALista(comboRelator, listaRelator);
  }//GEN-LAST:event_comboRelatorActionPerformed

  private void comboLocutorComercialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboLocutorComercialActionPerformed
    this.agregarALista(comboLocutorComercial, listaLocutorComercial);
  }//GEN-LAST:event_comboLocutorComercialActionPerformed

  private void comboPanelistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPanelistasActionPerformed
    this.agregarALista(comboPanelistas, listaPanelistas);
  }//GEN-LAST:event_comboPanelistasActionPerformed

  private void comboPanelistasSeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPanelistasSeccionActionPerformed
    this.agregarALista(comboPanelistasSeccion, listaPanelistasSeccion);
  }//GEN-LAST:event_comboPanelistasSeccionActionPerformed

  private void comboPeriodistaEntrevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPeriodistaEntrevistaActionPerformed
    this.agregarALista(comboPeriodistaEntrevista, listaPeriodistaEntrevista);
  }//GEN-LAST:event_comboPeriodistaEntrevistaActionPerformed

  private void comboConductorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboConductorActionPerformed
    this.agregarALista(comboConductor, listaConductor);    
  }//GEN-LAST:event_comboConductorActionPerformed

  private void popupListaBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popupListaBorrarActionPerformed
    this.eliminarElementoEnLista((javax.swing.JList)popupLista.getInvoker());
  }//GEN-LAST:event_popupListaBorrarActionPerformed

  private void listaConductorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaConductorMouseClicked
    listaConductor.setSelectedIndex(listaConductor.locationToIndex(evt.getPoint()));
    if(SwingUtilities.isRightMouseButton(evt)){
      popupLista.show(listaConductor, evt.getX(), evt.getY());
    }
  }//GEN-LAST:event_listaConductorMouseClicked

  private void listaPalabrasClaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaPalabrasClaveMouseClicked
    listaPalabrasClave.setSelectedIndex(listaPalabrasClave.locationToIndex(evt.getPoint()));
    if(SwingUtilities.isRightMouseButton(evt)){
      popupLista.show(listaPalabrasClave, evt.getX(), evt.getY());
    }
  }//GEN-LAST:event_listaPalabrasClaveMouseClicked

  private void listaEncargadoRRSSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaEncargadoRRSSMouseClicked
    listaEncargadoRRSS.setSelectedIndex(listaEncargadoRRSS.locationToIndex(evt.getPoint()));
    if(SwingUtilities.isRightMouseButton(evt)){
      popupLista.show(listaEncargadoRRSS, evt.getX(), evt.getY());
    }
  }//GEN-LAST:event_listaEncargadoRRSSMouseClicked

  private void listaLocutorComercialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaLocutorComercialMouseClicked
    listaLocutorComercial.setSelectedIndex(listaLocutorComercial.locationToIndex(evt.getPoint()));
    if(SwingUtilities.isRightMouseButton(evt)){
      popupLista.show(listaLocutorComercial, evt.getX(), evt.getY());
    }
  }//GEN-LAST:event_listaLocutorComercialMouseClicked

  private void listaRelatorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaRelatorMouseClicked
    listaRelator.setSelectedIndex(listaRelator.locationToIndex(evt.getPoint()));
    if(SwingUtilities.isRightMouseButton(evt)){
      popupLista.show(listaRelator, evt.getX(), evt.getY());
    }
  }//GEN-LAST:event_listaRelatorMouseClicked

  private void listaPanelistasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaPanelistasMouseClicked
    listaPanelistas.setSelectedIndex(listaPanelistas.locationToIndex(evt.getPoint()));
    if(SwingUtilities.isRightMouseButton(evt)){
      popupLista.show(listaPanelistas, evt.getX(), evt.getY());
    }
  }//GEN-LAST:event_listaPanelistasMouseClicked

  private void listaTemaPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaTemaPanelMouseClicked
    listaTemaPanel.setSelectedIndex(listaTemaPanel.locationToIndex(evt.getPoint()));
    if(SwingUtilities.isRightMouseButton(evt)){
      popupLista.show(listaTemaPanel, evt.getX(), evt.getY());
    }
  }//GEN-LAST:event_listaTemaPanelMouseClicked

  private void listaPanelistasSeccionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaPanelistasSeccionMouseClicked
    listaPanelistasSeccion.setSelectedIndex(listaPanelistasSeccion.locationToIndex(evt.getPoint()));
    if(SwingUtilities.isRightMouseButton(evt)){
      popupLista.show(listaPanelistasSeccion, evt.getX(), evt.getY());
    }
  }//GEN-LAST:event_listaPanelistasSeccionMouseClicked

  private void listaTemaSeccionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaTemaSeccionMouseClicked
    listaTemaSeccion.setSelectedIndex(listaTemaSeccion.locationToIndex(evt.getPoint()));
    if(SwingUtilities.isRightMouseButton(evt)){
      popupLista.show(listaTemaSeccion, evt.getX(), evt.getY());
    }
  }//GEN-LAST:event_listaTemaSeccionMouseClicked

  private void listaInvitadosSeccionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaInvitadosSeccionMouseClicked
    listaInvitadosSeccion.setSelectedIndex(listaInvitadosSeccion.locationToIndex(evt.getPoint()));
    if(SwingUtilities.isRightMouseButton(evt)){
      popupLista.show(listaInvitadosSeccion, evt.getX(), evt.getY());
    }
  }//GEN-LAST:event_listaInvitadosSeccionMouseClicked

  private void listaTemaEntrevistaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaTemaEntrevistaMouseClicked
    listaTemaEntrevista.setSelectedIndex(listaTemaEntrevista.locationToIndex(evt.getPoint()));
    if(SwingUtilities.isRightMouseButton(evt)){
      popupLista.show(listaTemaEntrevista, evt.getX(), evt.getY());
    }
  }//GEN-LAST:event_listaTemaEntrevistaMouseClicked

  private void listaEntrevistadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaEntrevistadosMouseClicked
    listaEntrevistados.setSelectedIndex(listaEntrevistados.locationToIndex(evt.getPoint()));
    if(SwingUtilities.isRightMouseButton(evt)){
      popupLista.show(listaEntrevistados, evt.getX(), evt.getY());
    }
  }//GEN-LAST:event_listaEntrevistadosMouseClicked

  private void listaPeriodistaEntrevistaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaPeriodistaEntrevistaMouseClicked
    listaPeriodistaEntrevista.setSelectedIndex(listaPeriodistaEntrevista.locationToIndex(evt.getPoint()));
    if(SwingUtilities.isRightMouseButton(evt)){
      popupLista.show(listaPeriodistaEntrevista, evt.getX(), evt.getY());
    }
  }//GEN-LAST:event_listaPeriodistaEntrevistaMouseClicked

  private void listaTemaNoticiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaTemaNoticiaMouseClicked
    listaTemaNoticia.setSelectedIndex(listaTemaNoticia.locationToIndex(evt.getPoint()));
    if(SwingUtilities.isRightMouseButton(evt)){
      popupLista.show(listaTemaNoticia, evt.getX(), evt.getY());
    }
  }//GEN-LAST:event_listaTemaNoticiaMouseClicked

  private void botonGuardarFragmentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarFragmentoActionPerformed
    //se crea un Audio para agregar al listado fragmentos en la base de datos
    //creacion Audio
    Audio audio = null;
    boolean formularioListo = true;
    
    //se completa Audio según opciones elegidas
    try {
      
      switch(comboTipoAudio.getSelectedIndex()){
        case 0: {
          labelTipoAudio.setForeground(Color.red);
          formularioListo = false;
        }
        case 1: {
          //panel
          Panel audioTemp = new Panel(String.valueOf(this.contadorFragmentos));
          for(int i = 0; i < listaPanelistas.getModel().getSize(); i++){
            audioTemp.agregarPanelista( new Personaje(
                    listaPanelistas.getModel().getElementAt(i),
                    listaPanelistas.getModel().getElementAt(i),
                    listaPanelistas.getModel().getElementAt(i)
            ));
          }
          
          if(listaTemaPanel.getModel().getSize() != 0){
            labelTemaPanel.setForeground(null);
            for(int i = 0; i < listaTemaPanel.getModel().getSize(); i++){
              audioTemp.agregarTema(listaTemaPanel.getModel().getElementAt(i));
            }
          } else {
            formularioListo = false;
            labelTemaPanel.setForeground(Color.red);
          }
          audio = audioTemp;
          break;
        }
        case 2: {
          //deporte
          switch(comboDisciplina.getSelectedIndex()){
            case 0: {
              labelDisciplina.setForeground(Color.red);
              formularioListo = false;
              break;
            }
            case 1: {
              //tenis
              Tenis audioTemp = new Tenis(String.valueOf(this.contadorFragmentos));
              if(!textJugadores.getText().equals("") && ((textJugadores.getText().split(";").length % 2) == 0)){
                labelJugadores.setForeground(null);
                for(String s : textJugadores.getText().split(";")){
                  audioTemp.agregarJugador(s);
                }
              } else {
                formularioListo = false;
                labelJugadores.setForeground(Color.red);
              }
              audioTemp.setDobles(textJugadores.getText().split(";").length > 2);
              if(!textMarcadorFinalTenis.getText().equals("")){
                labelMarcadorFinalTenis.setForeground(null);
                audioTemp.setMarcador(textMarcadorFinalTenis.getText());
              } else {
                labelMarcadorFinalTenis.setForeground(Color.red);
                formularioListo = false;
              }
              audio = audioTemp;
              break;
            }
            case 2: {
              //basquetball
              Basquetball audioTemp = new Basquetball(String.valueOf(this.contadorFragmentos));
              
              if(!textEquipoLocalBasquetball.getText().equals("")){
                labelEquipoLocalBasquetball.setForeground(null);
                audioTemp.setEquipoLocal(textEquipoLocalBasquetball.getText());
              } else {
                formularioListo = false;
                labelEquipoLocalBasquetball.setForeground(Color.red);
              }
              
              if(!textEquipoVisitaBasquetball.getText().equals("")){
                labelEquipoVisitaBasquetball.setForeground(null);
                audioTemp.setEquipoVisita(textEquipoVisitaBasquetball.getText());
              } else {
                formularioListo = false;
                labelEquipoVisitaBasquetball.setForeground(Color.red);
              }
              
              audioTemp.setMarcador(textMarcadorFinalBasquetball.getText());
              
              audio = audioTemp;
              
              break;
            }
            case 3: {
              //futbol
              Futbol audioTemp = new Futbol(String.valueOf(this.contadorFragmentos));
              if(!textEquipoLocalFutbol.getText().equals("")){
                labelEquipoLocalFutbol.setForeground(null);
                audioTemp.setEquipoLocal(textEquipoLocalFutbol.getText());
              } else {
                formularioListo = false;
                labelEquipoLocalFutbol.setForeground(Color.red);
              }
              if(!textEquipoVisitaFutbol.getText().equals("")){
                labelEquipoVisitaFutbol.setForeground(null);
                audioTemp.setEquipoVisita(textEquipoVisitaFutbol.getText());
              } else {
                formularioListo = false;
                labelEquipoVisitaFutbol.setForeground(Color.red);
              }
              if(!textMarcadorFinalFutbol.getText().equals("")){
                labelMarcadorFinalFutbol.setForeground(null);
                audioTemp.setMarcador(textMarcadorFinalFutbol.getText());
              } else {
                formularioListo = false;
                labelMarcadorFinalFutbol.setForeground(Color.red);
              }
              
              audio = audioTemp;
              
              break;
            }                   
          }
          
          Deporte audioTemp = (Deporte)audio;
          
          if(listaRelator.getModel().getSize() > 0){
            labelRelator.setForeground(null);
            for(int i = 0; i < listaRelator.getModel().getSize(); i++){
              if(audioTemp.agregarRelator(listaRelator.getModel().getElementAt(i))){
                System.out.println("RELATOR AGREGADO");
              }
            }
          } else {
            formularioListo = false;
            labelRelator.setForeground(Color.red);
          }
          
          for(int i = 0; i < listaLocutorComercial.getModel().getSize(); i++){
            if(audioTemp.agregarLocutorComercial(listaLocutorComercial.getModel().getElementAt(i))){
              System.out.println("LOCUTOR COMERCIAL AGREGADO");
            }
          }
          
          for(int i = 0; i < listaEncargadoRRSS.getModel().getSize(); i++){
            if(audioTemp.agregarEncargadoRS(listaEncargadoRRSS.getModel().getElementAt(i))){
              System.out.println("ENCARGADO RRSS AGREGADO");
            }
          }
          
          for(int i = 0; i < listaComentarista.getModel().getSize(); i++){
            if(audioTemp.agregarComentarista(listaComentarista.getModel().getElementAt(i))){
              System.out.println("COMENTARISTA AGREGADO");
            }
          }
          
          for(int i = 0; i < listaComentarista1.getModel().getSize(); i++){
            if(audioTemp.agregarReportero(listaComentarista1.getModel().getElementAt(i))){
              System.out.println("REPORTERO AGREGADO");
            }
          }
          
          audioTemp.setCompetencia(textCompetencia.getText());
          
          audioTemp.setLugar(textLugar.getText());
          
          audio = audioTemp;
          
          break;
        }
        case 3: {
          //entrevista
          Entrevista audioTemp = new Entrevista(String.valueOf(this.contadorFragmentos));
          
          for(int i = 0; i < listaPeriodistaEntrevista.getModel().getSize(); i++){
            audioTemp.agregarPeriodista(listaPeriodistaEntrevista.getModel().getElementAt(i));
          }
          for(int i = 0; i < listaEntrevistados.getModel().getSize(); i++){
            audioTemp.agregarEntrevistado(new Personaje(
                    listaEntrevistados.getModel().getElementAt(i),
                    listaEntrevistados.getModel().getElementAt(i),
                    listaEntrevistados.getModel().getElementAt(i)
            ));
          }
          if(listaTemaEntrevista.getModel().getSize() != 0){
            labelTemaEntrevista.setForeground(null);
            for(int i = 0; i < listaTemaEntrevista.getModel().getSize(); i++){
              audioTemp.agregarTema(listaTemaEntrevista.getModel().getElementAt(i));
            }
          } else {
            formularioListo = false;
            labelTemaEntrevista.setForeground(Color.red);
          }
          
          audio = audioTemp;
          break;
        }
        case 4: {
          //seccion
          Seccion audioTemp = new Seccion(String.valueOf(this.contadorFragmentos));
          if(!textNombreSeccion.getText().equals("")){
            labelNombreSeccion.setForeground(null);
            audioTemp.setNombre(textNombreSeccion.getText());
          } else {
            formularioListo = false;
            labelNombreSeccion.setForeground(Color.red);
          }
          
          for(int i = 0; i < listaPanelistasSeccion.getModel().getSize(); i++){
            audioTemp.agregarPanelista(listaPanelistasSeccion.getModel().getElementAt(i));
          }
          
          if(listaTemaSeccion.getModel().getSize() != 0){
            labelTemaSeccion.setForeground(null);
            for(int i = 0; i < listaTemaSeccion.getModel().getSize(); i++){
              audioTemp.agregarTema(listaTemaSeccion.getModel().getElementAt(i));
            }
          } else {
            formularioListo = false;
            labelTemaSeccion.setForeground(Color.red);
          }
          if(listaInvitadosSeccion.getModel().getSize() != 0) {
            for(int i = 0; i < listaInvitadosSeccion.getModel().getSize(); i++){
              audioTemp.agregarInvitado(new Personaje(
                      listaInvitadosSeccion.getModel().getElementAt(i),
                      listaInvitadosSeccion.getModel().getElementAt(i),
                      listaInvitadosSeccion.getModel().getElementAt(i)
              ));
            }
          } else {
            audioTemp.agregarInvitado(new Personaje());
          }
          
          audio = audioTemp;
          break;
        }
        case 5: {
          //informe
          Informe audioTemp = new Informe(String.valueOf(this.contadorFragmentos));
          for(int i = 0; i < listaPeriodistaInforme.getModel().getSize(); i++){
            audioTemp.agregarPeriodista(listaPeriodistaInforme.getModel().getElementAt(i));
          }
          if(listaTemaInforme.getModel().getSize() != 0){
            labelTemaInforme.setForeground(null);
            for(int i = 0; i < listaTemaInforme.getModel().getSize(); i++){
              audioTemp.agregarTema(listaTemaInforme.getModel().getElementAt(i));
            }
          } else {
            formularioListo = false;
            labelTemaInforme.setForeground(Color.red);
          }
          
          audioTemp.setLugar(textLugarInforme.getText());
          
          if(listaPersonajeInforme.getModel().getSize() != 0){
            for(int i = 0; i < listaPersonajeInforme.getModel().getSize(); i++){
              audioTemp.agregarCunha(new Personaje(
                      listaPersonajeInforme.getModel().getElementAt(i),
                      listaPersonajeInforme.getModel().getElementAt(i),
                      listaPersonajeInforme.getModel().getElementAt(i)
              ));
            }
          } else {
            audioTemp.agregarCunha(new Personaje());
          }
          
          audio = audioTemp;
          break;
        }
        case 6: {
          //noticia
          Noticia audioTemp = new Noticia(String.valueOf(this.contadorFragmentos));
          if(listaTemaNoticia.getModel().getSize() != 0){
            labelTemaNoticia.setForeground(null);
            for(int i = 0; i < listaTemaNoticia.getModel().getSize(); i++){
              audioTemp.agregarTema(listaTemaNoticia.getModel().getElementAt(i));
            }
          } else {
            formularioListo = false;
            labelTemaNoticia.setForeground(Color.red);
          }
          audio = audioTemp;
          break;
        }
      }
      if(!textAlturaInicioFragmento.getText().equals("")){
        labelAlturaInicioFragmento.setForeground(null);
        audio.setAlturaInicio(Integer.parseInt(textAlturaInicioFragmento.getText()));
      } else {
        formularioListo = false;
        labelAlturaInicioFragmento.setForeground(Color.red);
      }
      
      if(!textAlturaTerminoFragmento.getText().equals("")){
        labelAlturaTerminoFragmento.setForeground(null);
        audio.setAlturaTermino(Integer.parseInt(textAlturaTerminoFragmento.getText()));
      } else {
        formularioListo = false;
        labelAlturaTerminoFragmento.setForeground(Color.red);
      }
      
      if(listaPalabrasClave.getModel().getSize() != 0){
        labelPalabrasClave.setForeground(null);
        for(int i = 0; i < listaPalabrasClave.getModel().getSize(); i++){
          audio.agregarPalabraClave(listaPalabrasClave.getModel().getElementAt(i));
        }
      } else {
        formularioListo = false;
        labelPalabrasClave.setForeground(Color.red);
      }
      
      if(!textAreaDescripcionFragmento.getText().equals("")){
        labelDescripcionFragmento.setForeground(null);
        audio.setDescripcion(textAreaDescripcionFragmento.getText());
      } else {
        formularioListo = false;
        labelDescripcionFragmento.setForeground(Color.red);
      }
      
      if(formularioListo){
        //se inserta el archivo en la lista Fragmentos del programa en la base de datos
        archivoDao.insertarAudio(textIdArchivo.getText(), String.valueOf(this.contadorProgramas), audio);
        //deshabilita panel agregarFragmento
        this.setPanelEnabled(panelAgregarFragmento, false);
        //visibiliza y activa botones OtroFragmento y OtroPrograma
        this.setPanelEnabled(panelAgregarOtro,true);
        panelAgregarOtro.setVisible(true);
        
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }//GEN-LAST:event_botonGuardarFragmentoActionPerformed

  private void listaPersonajeInformeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaPersonajeInformeMouseClicked
    listaPersonajeInforme.setSelectedIndex(listaPersonajeInforme.locationToIndex(evt.getPoint()));
    if(SwingUtilities.isRightMouseButton(evt)){
      popupLista.show(listaPersonajeInforme, evt.getX(), evt.getY());
    }
  }//GEN-LAST:event_listaPersonajeInformeMouseClicked

  private void textPersonajeInformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textPersonajeInformeActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_textPersonajeInformeActionPerformed

  private void textLugarInformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textLugarInformeActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_textLugarInformeActionPerformed

  private void listaTemaInformeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaTemaInformeMouseClicked
    listaTemaInforme.setSelectedIndex(listaTemaInforme.locationToIndex(evt.getPoint()));
    if(SwingUtilities.isRightMouseButton(evt)){
      popupLista.show(listaTemaInforme, evt.getX(), evt.getY());
    }
  }//GEN-LAST:event_listaTemaInformeMouseClicked

  private void textTemaInformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textTemaInformeActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_textTemaInformeActionPerformed

  private void listaPeriodistaInformeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaPeriodistaInformeMouseClicked
    listaPeriodistaInforme.setSelectedIndex(listaPeriodistaInforme.locationToIndex(evt.getPoint()));
    if(SwingUtilities.isRightMouseButton(evt)){
      popupLista.show(listaPeriodistaInforme, evt.getX(), evt.getY());
    }
  }//GEN-LAST:event_listaPeriodistaInformeMouseClicked

  private void comboPeriodistaInformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPeriodistaInformeActionPerformed
    this.agregarALista(comboPeriodistaInforme, listaPeriodistaInforme);
  }//GEN-LAST:event_comboPeriodistaInformeActionPerformed

  private void botonTemaNoticiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonTemaNoticiaActionPerformed
    this.agregarALista(textTemaNoticia, listaTemaNoticia);
  }//GEN-LAST:event_botonTemaNoticiaActionPerformed

  private void botonTemaInformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonTemaInformeActionPerformed
    this.agregarALista(textTemaInforme, listaTemaInforme);
  }//GEN-LAST:event_botonTemaInformeActionPerformed

  private void botonPersonajeInformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPersonajeInformeActionPerformed
    this.agregarALista(textPersonajeInforme, listaPersonajeInforme);
  }//GEN-LAST:event_botonPersonajeInformeActionPerformed

  private void botonEntrevistadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEntrevistadosActionPerformed
    this.agregarALista(textEntrevistados, listaEntrevistados);
  }//GEN-LAST:event_botonEntrevistadosActionPerformed

  private void botonTemaEntrevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonTemaEntrevistaActionPerformed
    this.agregarALista(textTemaEntrevista, listaTemaEntrevista);
  }//GEN-LAST:event_botonTemaEntrevistaActionPerformed

  private void botonTemaSeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonTemaSeccionActionPerformed
    this.agregarALista(textTemaSeccion, listaTemaSeccion);
  }//GEN-LAST:event_botonTemaSeccionActionPerformed

  private void botonInvitadosSeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonInvitadosSeccionActionPerformed
    this.agregarALista(textInvitadosSeccion, listaInvitadosSeccion);
  }//GEN-LAST:event_botonInvitadosSeccionActionPerformed

  private void botonTemaPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonTemaPanelActionPerformed
    this.agregarALista(textTemaPanel, listaTemaPanel);
  }//GEN-LAST:event_botonTemaPanelActionPerformed

  private void textNombreSeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNombreSeccionActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_textNombreSeccionActionPerformed

  private void botonOtroFragmentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonOtroFragmentoActionPerformed
    //incrementa numero de fragmentos en programa
    this.contadorFragmentos++;
    //habilita panel AgregarFragmento
    this.setPanelEnabled(panelAgregarFragmento, true);
    //limpia panel AgregarFragmento
    this.limpiarPanel(panelAgregarFragmento);
    //invisibiliza panel AgregarOtro
    panelAgregarOtro.setVisible(false);
    //invisibiliza panel Tipos
    scrollTipos.setVisible(false);
  }//GEN-LAST:event_botonOtroFragmentoActionPerformed

  private void botonOtroProgramaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonOtroProgramaActionPerformed
    //incrementa numero de programas
    this.contadorProgramas++;
    //reinicia contador de fragmentos para el programa
    this.contadorFragmentos = 0;
    //limpia paneles AgregarPrograma y AgregarFragmento
    this.limpiarPanel(panelAgregarPrograma);
    this.setPanelEnabled(panelAgregarPrograma, true);
    //invisibiliza panel AgregarOtro
    panelAgregarOtro.setVisible(false);
    //invisibiliza paneles AgregarFragmento, Tipos y Disciplina
    panelAgregarFragmento.setVisible(false);
    scrollTipos.setVisible(false);
    panelDisciplina.setVisible(false);
  }//GEN-LAST:event_botonOtroProgramaActionPerformed

  private void comboComentaristaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboComentaristaActionPerformed
    this.agregarALista(comboComentarista, listaComentarista);
  }//GEN-LAST:event_comboComentaristaActionPerformed

  private void listaComentaristaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaComentaristaMouseClicked
    listaComentarista.setSelectedIndex(listaComentarista.locationToIndex(evt.getPoint()));
    if(SwingUtilities.isRightMouseButton(evt)){
      popupLista.show(listaComentarista, evt.getX(), evt.getY());
    }
  }//GEN-LAST:event_listaComentaristaMouseClicked

  private void comboReporteroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboReporteroActionPerformed
    this.agregarALista(comboReportero, listaComentarista1);
  }//GEN-LAST:event_comboReporteroActionPerformed

  private void listaComentarista1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaComentarista1MouseClicked
    listaComentarista1.setSelectedIndex(listaComentarista1.locationToIndex(evt.getPoint()));
    if(SwingUtilities.isRightMouseButton(evt)){
      popupLista.show(listaComentarista1, evt.getX(), evt.getY());
    }
  }//GEN-LAST:event_listaComentarista1MouseClicked

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
      java.util.logging.Logger.getLogger(VentanaRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(VentanaRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(VentanaRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(VentanaRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        new VentanaRegistrar("OPERADOR DE PRUEBA").setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton botonAgregarFragmento;
  private javax.swing.JButton botonAgregarGoles;
  private javax.swing.JButton botonAgregarPrograma;
  private javax.swing.JButton botonEntrevistados;
  private javax.swing.JButton botonGuardarFragmento;
  private javax.swing.JButton botonInvitadosSeccion;
  private javax.swing.JButton botonOtroFragmento;
  private javax.swing.JButton botonOtroPrograma;
  private javax.swing.JButton botonPersonajeInforme;
  private javax.swing.JButton botonTemaEntrevista;
  private javax.swing.JButton botonTemaInforme;
  private javax.swing.JButton botonTemaNoticia;
  private javax.swing.JButton botonTemaPanel;
  private javax.swing.JButton botonTemaSeccion;
  private javax.swing.JComboBox<String> comboCanales;
  private javax.swing.JComboBox<String> comboCodec;
  private javax.swing.JComboBox<String> comboComentarista;
  private javax.swing.JComboBox<String> comboConductor;
  private javax.swing.JComboBox<String> comboDisciplina;
  private javax.swing.JComboBox<String> comboEncargadoRRSS;
  private javax.swing.JComboBox<String> comboFrecuenciaMuestreo;
  private javax.swing.JComboBox<String> comboLocutorComercial;
  private javax.swing.JComboBox<String> comboNombrePrograma;
  private javax.swing.JComboBox<String> comboPalabrasClave;
  private javax.swing.JComboBox<String> comboPanelistas;
  private javax.swing.JComboBox<String> comboPanelistasSeccion;
  private javax.swing.JComboBox<String> comboPeriodistaEntrevista;
  private javax.swing.JComboBox<String> comboPeriodistaInforme;
  private javax.swing.JComboBox<String> comboProfundidadBits;
  private javax.swing.JComboBox<String> comboRelator;
  private javax.swing.JComboBox<String> comboReportero;
  private javax.swing.JComboBox<String> comboResponsableDigitalizacion;
  private javax.swing.JComboBox<String> comboTasaBits;
  private javax.swing.JComboBox<String> comboTipoAudio;
  private javax.swing.JComboBox<String> comboTipoSoporte;
  private javax.swing.JLabel labelAlturaInicioFragmento;
  private javax.swing.JLabel labelAlturaInicioPrograma;
  private javax.swing.JLabel labelAlturaTerminoFragmento;
  private javax.swing.JLabel labelAlturaTerminoPrograma;
  private javax.swing.JLabel labelBytesArchivo;
  private javax.swing.JLabel labelCanales;
  private javax.swing.JLabel labelCodec;
  private javax.swing.JLabel labelCodigoSoporte;
  private javax.swing.JLabel labelComentarista;
  private javax.swing.JLabel labelCompetencia;
  private javax.swing.JLabel labelConductor;
  private javax.swing.JLabel labelDescripcionExterior;
  private javax.swing.JLabel labelDescripcionFragmento;
  private javax.swing.JLabel labelDisciplina;
  private javax.swing.JLabel labelDuracionArchivo;
  private javax.swing.JLabel labelEncargadoRRSS;
  private javax.swing.JLabel labelEntrevistados;
  private javax.swing.JLabel labelEquipoLocalBasquetball;
  private javax.swing.JLabel labelEquipoLocalFutbol;
  private javax.swing.JLabel labelEquipoVisitaBasquetball;
  private javax.swing.JLabel labelEquipoVisitaFutbol;
  private javax.swing.JLabel labelFechaDigitalizacion;
  private javax.swing.JLabel labelFechaEmisionPrograma;
  private javax.swing.JLabel labelFormatoArchivo;
  private javax.swing.JLabel labelFrecuenciaMuestreo;
  private javax.swing.JLabel labelGoles;
  private javax.swing.JLabel labelIdArchivo;
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
  private javax.swing.JLabel labelProfundidadBits;
  private javax.swing.JLabel labelRelator;
  private javax.swing.JLabel labelReportero;
  private javax.swing.JLabel labelResponsableDigitalizacion;
  private javax.swing.JLabel labelSegundosArchivo;
  private javax.swing.JLabel labelSeparador3;
  private javax.swing.JLabel labelSeparador4;
  private javax.swing.JLabel labelSeparador5;
  private javax.swing.JLabel labelSeparador6;
  private javax.swing.JLabel labelSubtitulo;
  private javax.swing.JLabel labelTamanhoArchivo;
  private javax.swing.JLabel labelTasaBits;
  private javax.swing.JLabel labelTemaEntrevista;
  private javax.swing.JLabel labelTemaInforme;
  private javax.swing.JLabel labelTemaNoticia;
  private javax.swing.JLabel labelTemaPanel;
  private javax.swing.JLabel labelTemaSeccion;
  private javax.swing.JLabel labelTipoAudio;
  private javax.swing.JLabel labelTipoSoporte;
  private javax.swing.JLabel labelTitulo;
  private javax.swing.JList<String> listaComentarista;
  private javax.swing.JList<String> listaComentarista1;
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
  private javax.swing.JList<String> listaTemaEntrevista;
  private javax.swing.JList<String> listaTemaInforme;
  private javax.swing.JList<String> listaTemaNoticia;
  private javax.swing.JList<String> listaTemaPanel;
  private javax.swing.JList<String> listaTemaSeccion;
  private javax.swing.JPanel panelAgregarFragmento;
  private javax.swing.JPanel panelAgregarOtro;
  private javax.swing.JPanel panelAgregarPrograma;
  private javax.swing.JPanel panelBasquetball;
  private javax.swing.JPanel panelCrearArchivo;
  private javax.swing.JPanel panelDeporte;
  private javax.swing.JLayeredPane panelDisciplina;
  private javax.swing.JPanel panelEntrevista;
  private javax.swing.JPanel panelFechaDigitalizacion;
  private javax.swing.JPanel panelFechaEmisionPrograma;
  private javax.swing.JPanel panelFormato;
  private javax.swing.JPanel panelFutbol;
  private javax.swing.JPanel panelInforme;
  private javax.swing.JPanel panelNoticia;
  private javax.swing.JPanel panelPanel;
  private javax.swing.JPanel panelSeccion;
  private javax.swing.JPanel panelTenis;
  private javax.swing.JLayeredPane panelTipos;
  private javax.swing.JPopupMenu popupLista;
  private javax.swing.JMenuItem popupListaBorrar;
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
  private javax.swing.JTextField textAlturaInicioFragmento;
  private javax.swing.JTextField textAlturaInicioPrograma;
  private javax.swing.JTextField textAlturaTerminoFragmento;
  private javax.swing.JTextField textAlturaTerminoPrograma;
  private javax.swing.JTextField textAnhoDigitalizacion;
  private javax.swing.JTextField textAnhoEmision;
  private javax.swing.JTextArea textAreaDescripcionExterior;
  private javax.swing.JTextArea textAreaDescripcionFragmento;
  private javax.swing.JTextField textCodigoSoporte;
  private javax.swing.JTextField textCompetencia;
  private javax.swing.JTextField textDiaDigitalizacion;
  private javax.swing.JTextField textDiaEmision;
  private javax.swing.JTextField textDuracionArchivo;
  private javax.swing.JTextField textEntrevistados;
  private javax.swing.JTextField textEquipoLocalBasquetball;
  private javax.swing.JTextField textEquipoLocalFutbol;
  private javax.swing.JTextField textEquipoVisitaBasquetball;
  private javax.swing.JTextField textEquipoVisitaFutbol;
  private javax.swing.JTextField textIdArchivo;
  private javax.swing.JTextField textInvitadosSeccion;
  private javax.swing.JTextField textJugadores;
  private javax.swing.JTextField textLugar;
  private javax.swing.JTextField textLugarInforme;
  private javax.swing.JTextField textMarcadorFinalBasquetball;
  private javax.swing.JTextField textMarcadorFinalFutbol;
  private javax.swing.JTextField textMarcadorFinalTenis;
  private javax.swing.JTextField textMesDigitalizacion;
  private javax.swing.JTextField textMesEmision;
  private javax.swing.JTextField textNombreArchivo;
  private javax.swing.JTextField textNombreSeccion;
  private javax.swing.JTextField textPersonajeInforme;
  private javax.swing.JTextField textTamanhoArchivo;
  private javax.swing.JTextField textTemaEntrevista;
  private javax.swing.JTextField textTemaInforme;
  private javax.swing.JTextField textTemaNoticia;
  private javax.swing.JTextField textTemaPanel;
  private javax.swing.JTextField textTemaSeccion;
  // End of variables declaration//GEN-END:variables
}
