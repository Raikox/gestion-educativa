/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PckInterfaces;

import ClsEntidadReporte.ClsCantidad;
import PckConexion.ClsConexion;
import PckEntidad.AlumnoFichaInasistencia;
import PckEntidad.ClsEntidadNivel;
import PckEntidad.ClsEntidadPeriodo;
import PckNegocio.ClsAlumno;
import PckNegocio.ClsAsistencia;
import PckNegocio.ClsMatricula;
import PckNegocio.ClsNivel;
import PckNegocio.ClsPeriodo;
import PckNegocio.ClsSeccion;
import static java.awt.image.ImageObserver.WIDTH;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import prymatricula.ClsMisc;

/**
 *
 * @author Kevin
 */
public class ReporteAlumnoResumenHoy extends javax.swing.JPanel {

    List<String> listIdNivel = new ArrayList();
    List<String> listIdPeriodo = new ArrayList();
    List<AlumnoFichaInasistencia> listAlumno = new ArrayList<>();
    List<AlumnoFichaInasistencia> listAsistencia = new ArrayList<>();
    List<ClsCantidad> listCantidad = new ArrayList<>();
    
    ClsCantidad cantidad;
    
    Connection con;
    
    ClsSeccion aula = new ClsSeccion();
    ClsMatricula matricula = new ClsMatricula();
    ClsAlumno alumno = new ClsAlumno();
    ClsAsistencia asistencia = new ClsAsistencia();
    
    SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd");
    Date abc = new Date();
    
    //private boolean firstRun = false;
    
    /**
     * Creates new form ReporteAlumnoResumenHoy
     */
    public ReporteAlumnoResumenHoy() {
        initComponents();
        
        jdcFecha.setDate(abc);
        
        CargarComboNivel(); 
        if(cmbNivel.getSelectedIndex() != -1) {
            CargarComboPeriodo();
            if(cmbPeriodo.getSelectedIndex() != -1) {
                System.out.println("hola");
                CargarTablaAlumnos();
            }
            
        }
       
        
        
                
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        pnlDerecha = new javax.swing.JPanel();
        pnltop = new javax.swing.JPanel();
        cmbNivel = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        cmbPeriodo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jdcFecha = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        pnlCenter = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAlumnos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtMatriculados = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtSinLonchera = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtConLonchera = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtAsistieron = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtFaltaron = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtLoncherasHoy = new javax.swing.JTextField();
        pnlBot = new javax.swing.JPanel();
        btnGenerarReporte = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(237, 240, 242));
        jPanel4.setPreferredSize(new java.awt.Dimension(911, 50));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel5.setBackground(new java.awt.Color(237, 240, 242));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Droid Sans", 1, 12)); // NOI18N
        jLabel1.setText("RESUMEN DE ALUMNOS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel5.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(jPanel5, gridBagConstraints);

        add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBackground(new java.awt.Color(237, 240, 242));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        pnlDerecha.setBackground(new java.awt.Color(255, 255, 255));
        pnlDerecha.setMinimumSize(new java.awt.Dimension(200, 414));
        pnlDerecha.setPreferredSize(new java.awt.Dimension(300, 400));
        pnlDerecha.setLayout(new java.awt.BorderLayout());

        pnltop.setBackground(new java.awt.Color(255, 255, 255));
        pnltop.setLayout(new java.awt.GridBagLayout());

        cmbNivel.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbNivel.setMinimumSize(new java.awt.Dimension(300, 20));
        cmbNivel.setPreferredSize(new java.awt.Dimension(500, 20));
        cmbNivel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbNivelItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 10, 20);
        pnltop.add(cmbNivel, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel12.setText("Periodo.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        pnltop.add(jLabel12, gridBagConstraints);

        cmbPeriodo.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbPeriodo.setMinimumSize(new java.awt.Dimension(300, 20));
        cmbPeriodo.setPreferredSize(new java.awt.Dimension(500, 20));
        cmbPeriodo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPeriodoItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        pnltop.add(cmbPeriodo, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel4.setText("Nivel.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 10, 0);
        pnltop.add(jLabel4, gridBagConstraints);

        jdcFecha.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jdcFecha.setMinimumSize(new java.awt.Dimension(50, 23));
        jdcFecha.setPreferredSize(new java.awt.Dimension(150, 22));
        jdcFecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcFechaPropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        pnltop.add(jdcFecha, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel5.setText("Fecha.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 10, 0);
        pnltop.add(jLabel5, gridBagConstraints);

        jSeparator1.setForeground(new java.awt.Color(237, 240, 242));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 10, 0, 10);
        pnltop.add(jSeparator1, gridBagConstraints);

        pnlDerecha.add(pnltop, java.awt.BorderLayout.PAGE_START);

        pnlCenter.setBackground(new java.awt.Color(255, 255, 255));
        pnlCenter.setLayout(new java.awt.GridBagLayout());

        tblAlumnos.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        tblAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jScrollPane1.setViewportView(tblAlumnos);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 20);
        pnlCenter.add(jScrollPane1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel2.setText("Matriculados.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        pnlCenter.add(jLabel2, gridBagConstraints);

        txtMatriculados.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtMatriculados.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtMatriculados.setEnabled(false);
        txtMatriculados.setMinimumSize(new java.awt.Dimension(40, 20));
        txtMatriculados.setPreferredSize(new java.awt.Dimension(40, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(20, 10, 0, 0);
        pnlCenter.add(txtMatriculados, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel3.setText("Sin lonchera.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        pnlCenter.add(jLabel3, gridBagConstraints);

        txtSinLonchera.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtSinLonchera.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtSinLonchera.setEnabled(false);
        txtSinLonchera.setMinimumSize(new java.awt.Dimension(40, 20));
        txtSinLonchera.setPreferredSize(new java.awt.Dimension(40, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(20, 10, 0, 0);
        pnlCenter.add(txtSinLonchera, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel6.setText("Con lonchera.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        pnlCenter.add(jLabel6, gridBagConstraints);

        txtConLonchera.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtConLonchera.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtConLonchera.setEnabled(false);
        txtConLonchera.setMinimumSize(new java.awt.Dimension(40, 20));
        txtConLonchera.setPreferredSize(new java.awt.Dimension(40, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(20, 10, 0, 0);
        pnlCenter.add(txtConLonchera, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel7.setText("Asistieron.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        pnlCenter.add(jLabel7, gridBagConstraints);

        txtAsistieron.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtAsistieron.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtAsistieron.setEnabled(false);
        txtAsistieron.setMinimumSize(new java.awt.Dimension(40, 20));
        txtAsistieron.setPreferredSize(new java.awt.Dimension(40, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(20, 10, 0, 0);
        pnlCenter.add(txtAsistieron, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel8.setText("Faltaron.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        pnlCenter.add(jLabel8, gridBagConstraints);

        txtFaltaron.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtFaltaron.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtFaltaron.setEnabled(false);
        txtFaltaron.setMinimumSize(new java.awt.Dimension(40, 20));
        txtFaltaron.setPreferredSize(new java.awt.Dimension(40, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(20, 10, 0, 0);
        pnlCenter.add(txtFaltaron, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel9.setText("Loncheras hoy.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        pnlCenter.add(jLabel9, gridBagConstraints);

        txtLoncherasHoy.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtLoncherasHoy.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtLoncherasHoy.setEnabled(false);
        txtLoncherasHoy.setMinimumSize(new java.awt.Dimension(40, 20));
        txtLoncherasHoy.setPreferredSize(new java.awt.Dimension(40, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 10, 0, 0);
        pnlCenter.add(txtLoncherasHoy, gridBagConstraints);

        pnlDerecha.add(pnlCenter, java.awt.BorderLayout.CENTER);

        pnlBot.setBackground(new java.awt.Color(255, 255, 255));
        pnlBot.setLayout(new java.awt.GridBagLayout());

        btnGenerarReporte.setFont(new java.awt.Font("Droid Sans", 1, 12)); // NOI18N
        btnGenerarReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIconos/ic_assignment_black_24dp.png"))); // NOI18N
        btnGenerarReporte.setText("GENERAR REPORTE");
        btnGenerarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarReporteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 20);
        pnlBot.add(btnGenerarReporte, gridBagConstraints);

        pnlDerecha.add(pnlBot, java.awt.BorderLayout.PAGE_END);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        jPanel3.add(pnlDerecha, gridBagConstraints);

        add(jPanel3, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void cmbNivelItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbNivelItemStateChanged
        
        if(cmbNivel.getSelectedIndex() != -1 )
        {
            CargarComboPeriodo(); 
            if(cmbPeriodo.getSelectedIndex() != -1) {
                
            }
            CargarTablaAlumnos();
        }
    }//GEN-LAST:event_cmbNivelItemStateChanged

    private void cmbPeriodoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPeriodoItemStateChanged
        
        if (jdcFecha.getDate()!= null && cmbNivel.getSelectedIndex() != -1 && cmbPeriodo.getSelectedIndex() != -1)
        {
            CargarTablaAlumnos();
        }
    }//GEN-LAST:event_cmbPeriodoItemStateChanged

    private void jdcFechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdcFechaPropertyChange
        
        
        if (jdcFecha.getDate()!= null && cmbNivel.getSelectedIndex() != -1 && cmbPeriodo.getSelectedIndex() != -1)
        {
            CargarTablaAlumnos();
        }
        
        
    }//GEN-LAST:event_jdcFechaPropertyChange

    private void btnGenerarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarReporteActionPerformed
        
        try {
            
            ResultSet rsAula, rsMatricula;
            con = ClsConexion.getConection();
            
            int contadorAsistieron, contadorFaltaron, contadorLoncheraSi, contadorLoncheraNo, contadorMatriculados, contadorLoncheraHoy;
            int contadorAsistieronT = 0, contadorFaltaronT = 0, contadorLoncheraSiT = 0, contadorLoncheraNoT = 0, contadorMatriculadosT = 0, contadorLoncheraHoyT = 0;
            
            String nombreAula, idAula;
            String fecha = formatoOriginal.format(jdcFecha.getDate());
            
            rsAula = aula.ListarAulasPorPeriodo(listIdPeriodo.get(cmbPeriodo.getSelectedIndex()));
            
            try {
                
                while(rsAula.next()) {
                    
                    contadorAsistieron = 0; contadorFaltaron = 0; contadorLoncheraSi = 0; contadorLoncheraNo = 0;
                    contadorMatriculados = 0; contadorLoncheraHoy = 0;
                    
                    //Matricula y lonchera
                    idAula = rsAula.getString("id_seccion");
                    nombreAula = rsAula.getString("nombre_seccion");
                    
                    rsMatricula = matricula.ListarMatriculaAula(idAula,con);
                    while(rsMatricula.next()) {
                        
                        contadorMatriculados++;
                        contadorMatriculadosT++;
                        
                        if(rsMatricula.getString("comida_alumno").equals("SI")) {
                            contadorLoncheraSi++;
                            contadorLoncheraSiT++;
                        }
                        else {
                            contadorLoncheraNo++;
                            contadorLoncheraNoT++;
                        }
                        
                    }
                    
                    //Asistencia
                    listAlumno = alumno.ListarAlumnoPagoSeccionBasico(String.valueOf(idAula),con);
                    
                    for(AlumnoFichaInasistencia alu : listAlumno) {
                        
                        if(asistencia.ComprobarAsistenciaAlumno(alu.getId_Alumno(), fecha, con)) {
                            contadorAsistieron++;
                            contadorAsistieronT++;
                        }
                        else {
                            contadorFaltaron++;
                            contadorFaltaronT++;
                        }
                        
                        if(asistencia.ComprobarAsistenciaAlumnoAlimentacion(alu.getId_Alumno(), fecha, con)) {
                            
                            contadorLoncheraHoy++;
                            contadorLoncheraHoyT++;
                        }
                    }
                    
                    
                    cantidad = new ClsCantidad
                        (
                                nombreAula,
                                String.valueOf(contadorMatriculados),
                                String.valueOf(contadorLoncheraSi),
                                String.valueOf(contadorLoncheraNo),
                                String.valueOf(contadorAsistieron),
                                String.valueOf(contadorFaltaron),
                                String.valueOf(contadorLoncheraHoy)
                        );
                    listCantidad.add(cantidad);
                    
                }
            }
            catch (SQLException ex)
            {
                Logger.getLogger(ReporteAlumnoResumenHoy.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //reporte
            Map mapa = new HashMap();
            JasperPrint jpReporte;
            JRBeanCollectionDataSource dsReporte;
            JasperViewer jvReporte;
            String pathJRXML = "/Reportes/RptCantidadNinos.jrxml";
            String pathJASPER = "/Reportes/RptCantidadNinos.jasper";
            
            mapa.put("matriculado", String.valueOf(contadorMatriculadosT));
            mapa.put("alimentacionsi", String.valueOf(contadorLoncheraSiT));
            mapa.put("alimentacionno", String.valueOf(contadorLoncheraNoT));
            mapa.put("asistiosi", String.valueOf(contadorAsistieronT));
            mapa.put("asistiono", String.valueOf(contadorFaltaronT));
            mapa.put("alimentacionsiasistio", String.valueOf(contadorLoncheraHoyT));
            mapa.put("fecha", fecha);
            
            dsReporte = new JRBeanCollectionDataSource(listCantidad);
            JasperCompileManager.compileReportToFile
                    (
                            System.getProperty("user.dir")+pathJRXML,
                            System.getProperty("user.dir")+pathJASPER
                    );
            jpReporte = JasperFillManager.fillReport(System.getProperty("user.dir")+pathJASPER, mapa,dsReporte);
            jvReporte = new JasperViewer(jpReporte, false);
            jvReporte.setTitle("Reporte Alumnos Resumen");
            jvReporte.setVisible(true);
            
        }
        catch (JRException ex) 
        {
            Logger.getLogger(ReporteAlumnoResumenHoy.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnGenerarReporteActionPerformed
    
    private void CargarTablaAlumnos() {
        
        Object fila[] = new Object[8];
        String columnaTitulos[]={"AULA","MATRICULADOS","CON LONCHERA","SIN LONCHERA", "ASISTIERON","FALTARON","LONCHERAS HOY"};
        DefaultTableModel dtmAlumno  = new DefaultTableModel(null,columnaTitulos) 
        {        
            @Override
            public boolean isCellEditable(int rowIndex,int columnIndex){return false;}               
        };
        
        ResultSet rsAula, rsMatricula;
        con = ClsConexion.getConection();
        
        int contadorAsistieron, contadorFaltaron, contadorLoncheraSi, contadorLoncheraNo, contadorMatriculados, contadorLoncheraHoy;
        int contadorAsistieronT = 0, contadorFaltaronT = 0, contadorLoncheraSiT = 0, contadorLoncheraNoT = 0, contadorMatriculadosT = 0, contadorLoncheraHoyT = 0;
        
        String nombreAula, idAula; 
        String fecha = formatoOriginal.format(jdcFecha.getDate());
        
        rsAula = aula.ListarAulasPorPeriodo(listIdPeriodo.get(cmbPeriodo.getSelectedIndex()));
        
        try {
            
            while(rsAula.next()) {
                
                contadorAsistieron = 0; contadorFaltaron = 0; contadorLoncheraSi = 0; contadorLoncheraNo = 0;
                contadorMatriculados = 0; contadorLoncheraHoy = 0;                
                
                //Matricula y lonchera
                idAula = rsAula.getString("id_seccion");
                nombreAula = rsAula.getString("nombre_seccion");
                
                rsMatricula = matricula.ListarMatriculaAula(idAula,con);
                while(rsMatricula.next()) {
                    
                    contadorMatriculados++;
                    contadorMatriculadosT++;
                    
                    if(rsMatricula.getString("comida_alumno").equals("SI")) {
                        contadorLoncheraSi++;
                        contadorLoncheraSiT++;
                    }
                    else {
                        contadorLoncheraNo++;
                        contadorLoncheraNoT++;
                    }
                    
                }
                
                //Asistencia
                listAlumno = alumno.ListarAlumnoPagoSeccionBasico(String.valueOf(idAula),con);
                
                for(AlumnoFichaInasistencia alu : listAlumno) {
                    
                    if(asistencia.ComprobarAsistenciaAlumno(alu.getId_Alumno(), fecha, con)) {
                        contadorAsistieron++;
                        contadorAsistieronT++;
                    }
                    else {
                        contadorFaltaron++;
                        contadorFaltaronT++;
                    }
                    
                    if(asistencia.ComprobarAsistenciaAlumnoAlimentacion(alu.getId_Alumno(), fecha, con)) {
                        
                        contadorLoncheraHoy++;
                        contadorLoncheraHoyT++;
                    }
                }
                
                
                fila[0] = nombreAula;
                fila[1] = contadorMatriculados;
                fila[2] = contadorLoncheraSi;
                fila[3] = contadorLoncheraNo;
                fila[4] = contadorAsistieron;
                fila[5] = contadorFaltaron;
                fila[6] = contadorLoncheraHoy;
                
                dtmAlumno.addRow(fila);
                
            }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(ReporteAlumnoResumenHoy.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ClsConexion.closeConnection(con);
        
        tblAlumnos.setModel(dtmAlumno);
        tblAlumnos.setRowHeight(20);
        
        TableCellRenderer tcr =  tblAlumnos.getTableHeader().getDefaultRenderer();
        ClsMisc.miRender miRender = new ClsMisc.miRender(tcr); 
        miRender.getTableCellRendererComponent(tblAlumnos, fila, true, true, WIDTH, 1);
        miRender.getTableCellRendererComponent(tblAlumnos, fila, true, true, WIDTH, 2);
        miRender.getTableCellRendererComponent(tblAlumnos, fila, true, true, WIDTH, 3);
        miRender.getTableCellRendererComponent(tblAlumnos, fila, true, true, WIDTH, 4);
        miRender.getTableCellRendererComponent(tblAlumnos, fila, true, true, WIDTH, 5);
        miRender.getTableCellRendererComponent(tblAlumnos, fila, true, true, WIDTH, 6);
        
        txtMatriculados.setText(String.valueOf(contadorMatriculadosT));
        txtConLonchera.setText(String.valueOf(contadorLoncheraSiT));
        txtSinLonchera.setText(String.valueOf(contadorLoncheraNoT));
        txtAsistieron.setText(String.valueOf(contadorAsistieronT));
        txtFaltaron.setText(String.valueOf(contadorFaltaronT));
        txtLoncherasHoy.setText(String.valueOf(contadorLoncheraHoyT));
        
    }
        
    private void CargarComboNivel()
    {
        ClsNivel niveles = new ClsNivel();
        ArrayList<ClsEntidadNivel> nivele =  niveles.SeleccionarNivel();
        Iterator it = nivele.iterator();
        DefaultComboBoxModel dtm = new DefaultComboBoxModel();
        dtm.removeAllElements();
        cmbNivel.removeAllItems();
                
        while(it.hasNext())
        {
        ClsEntidadNivel Nivel = (ClsEntidadNivel) it.next();       
        listIdNivel.add(Nivel.getNivel_id());
        dtm.addElement(Nivel.getNivel_nombre());                
        }
        cmbNivel.setModel(dtm); 
    }
    
    private void CargarComboPeriodo()
    {
        listIdPeriodo.clear();
        ClsPeriodo periodos = new ClsPeriodo();
        ArrayList<ClsEntidadPeriodo> periodo =  periodos.ListarPeriodo();
        Iterator iterator = periodo.iterator();
        DefaultComboBoxModel DefaultComboBoxModel = new DefaultComboBoxModel();
        DefaultComboBoxModel.removeAllElements();
        cmbPeriodo.removeAllItems();
            
        while(iterator.hasNext())
        {
        ClsEntidadPeriodo Periodo = (ClsEntidadPeriodo) iterator.next();               
        
            if(Periodo.getEstado_Periodo().equals("Activo") &&                     
            listIdNivel.get(cmbNivel.getSelectedIndex()).equals(Periodo.getNivel_Id()))
            {   
            listIdPeriodo.add(Periodo.getId_Periodo());     
            DefaultComboBoxModel.addElement(Periodo.getNombre_Periodo());        
            }
        }
        cmbPeriodo.setModel(DefaultComboBoxModel);       
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarReporte;
    private javax.swing.JComboBox<String> cmbNivel;
    private javax.swing.JComboBox<String> cmbPeriodo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private com.toedter.calendar.JDateChooser jdcFecha;
    private javax.swing.JPanel pnlBot;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlDerecha;
    private javax.swing.JPanel pnltop;
    private javax.swing.JTable tblAlumnos;
    private javax.swing.JTextField txtAsistieron;
    private javax.swing.JTextField txtConLonchera;
    private javax.swing.JTextField txtFaltaron;
    private javax.swing.JTextField txtLoncherasHoy;
    private javax.swing.JTextField txtMatriculados;
    private javax.swing.JTextField txtSinLonchera;
    // End of variables declaration//GEN-END:variables
}
