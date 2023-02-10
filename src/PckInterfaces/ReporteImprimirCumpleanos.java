/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PckInterfaces;

import PckConexion.ClsConexion;
import PckEntidad.ClsEntidadNivel;
import PckEntidad.ClsEntidadPeriodo;
import PckNegocio.ClsAlumno;
import PckNegocio.ClsDeudas;
import PckNegocio.ClsMatricula;
import PckNegocio.ClsNivel;
import PckNegocio.ClsPeriodo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import mpsEntity.AlumnoCumpleEntity;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import static prymatricula.ClsMisc.ConvertirEUAtoEUstring;
import static prymatricula.ClsMisc.FechaISOtoFechaEuropea;
import static prymatricula.ClsMisc.obtenerMesNumero;

/**
 *
 * @author Kevin
 */
public class ReporteImprimirCumpleanos extends javax.swing.JPanel {

    private static final String LOGOTIPO = System.getProperty("user.dir")+"/Img/solr.jpg";
    
    List<String> listIdNivel = new ArrayList();
    List<String> listIdPeriodo = new ArrayList();
    /**
     * Creates new form ReporteDeudaMonto
     */
    public ReporteImprimirCumpleanos() {
        initComponents();
        
        grupo.add(rbnAlumno);
        grupo.add(rbnUsuario);
        
        CargarComboNivel(); 
        if(cmbNivel.getSelectedIndex() != -1) {
            CargarComboPeriodo();
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

        grupo = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        pnlDerecha = new javax.swing.JPanel();
        pnlBot = new javax.swing.JPanel();
        btnGenerarReporte = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cmbNivel = new javax.swing.JComboBox<>();
        cmbPeriodo = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cmbFin = new javax.swing.JComboBox<>();
        cmbInicio = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        rbnAlumno = new javax.swing.JRadioButton();
        rbnUsuario = new javax.swing.JRadioButton();

        setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(237, 240, 242));
        jPanel4.setPreferredSize(new java.awt.Dimension(911, 50));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel5.setBackground(new java.awt.Color(237, 240, 242));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Droid Sans", 1, 12)); // NOI18N
        jLabel1.setText("REPORTE TARJETA DE CUMPLEAÑOS");
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
        pnlDerecha.setPreferredSize(new java.awt.Dimension(300, 400));
        pnlDerecha.setLayout(new java.awt.BorderLayout());

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
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 0, 0);
        pnlBot.add(btnGenerarReporte, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel5.setText("Nivel.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 0);
        pnlBot.add(jLabel5, gridBagConstraints);

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
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 20);
        pnlBot.add(cmbNivel, gridBagConstraints);

        cmbPeriodo.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbPeriodo.setMinimumSize(new java.awt.Dimension(300, 20));
        cmbPeriodo.setPreferredSize(new java.awt.Dimension(500, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        pnlBot.add(cmbPeriodo, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel13.setText("Mes fin.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        pnlBot.add(jLabel13, gridBagConstraints);

        jLabel14.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel14.setText("Periodo.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        pnlBot.add(jLabel14, gridBagConstraints);

        jLabel15.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel15.setText("Mes inicio.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        pnlBot.add(jLabel15, gridBagConstraints);

        cmbFin.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbFin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SETIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE" }));
        cmbFin.setMinimumSize(new java.awt.Dimension(100, 20));
        cmbFin.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 20);
        pnlBot.add(cmbFin, gridBagConstraints);

        cmbInicio.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbInicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SETIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE" }));
        cmbInicio.setMinimumSize(new java.awt.Dimension(100, 20));
        cmbInicio.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 20);
        pnlBot.add(cmbInicio, gridBagConstraints);

        jLabel16.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel16.setText("Buscar.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        pnlBot.add(jLabel16, gridBagConstraints);

        rbnAlumno.setBackground(new java.awt.Color(255, 255, 255));
        rbnAlumno.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        rbnAlumno.setSelected(true);
        rbnAlumno.setText("Alumnos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 20);
        pnlBot.add(rbnAlumno, gridBagConstraints);

        rbnUsuario.setBackground(new java.awt.Color(255, 255, 255));
        rbnUsuario.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        rbnUsuario.setText("Usuarios");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 20);
        pnlBot.add(rbnUsuario, gridBagConstraints);

        pnlDerecha.add(pnlBot, java.awt.BorderLayout.CENTER);

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

    private void btnGenerarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarReporteActionPerformed
        
        if(rbnAlumno.isSelected()) {
            
            ClsAlumno alumnos = new ClsAlumno();
            ClsDeudas deudas = new ClsDeudas();

            ArrayList<AlumnoCumpleEntity> listCumple = new ArrayList<>();

            //+1 para poder compararlos con meses estandar (1 al 12)
            int mesInicial = obtenerMesNumero(cmbInicio.getSelectedItem().toString()) + 1;
            int mesFinal = obtenerMesNumero(cmbFin.getSelectedItem().toString()) + 1;
            System.out.println(mesInicial + " - "+mesFinal);
            String idMatricula; String idPeriodo; String nombreAula; 
            String nombreAlumno; String fechaNacimientoAlumno;
            String nombreMadre; String fechaNacimientoMadre;
            String nombrePadre; String fechaNacimientoPadre;

            int mesAlumno; int mesPadre; int mesMadre;

            Connection con;
            
            idPeriodo = listIdPeriodo.get(cmbPeriodo.getSelectedIndex());

            try {

                //Listar los alumnos por periodo
                con = ClsConexion.getConection();
                ResultSet rsAlu = alumnos.SeleccionarAlumnoPadreMadreNacimiento(idPeriodo,con);

                while(rsAlu.next()) {

                    //Se obtienen los datos 
                    idMatricula = rsAlu.getString("id_matricula");
                    nombreAula = rsAlu.getString("nombre_seccion");
                    nombreAlumno = rsAlu.getString("alumno");   
                    fechaNacimientoAlumno = rsAlu.getString("fechan_alumno");
                    nombreMadre = rsAlu.getString("madre");
                    fechaNacimientoMadre = rsAlu.getString("madre_fecha_nacimiento");
                    nombrePadre = rsAlu.getString("padre");
                    fechaNacimientoPadre = rsAlu.getString("padre_fecha_nacimiento");

                    mesAlumno = Integer.parseInt(fechaNacimientoAlumno.substring(5,7));
                    
                    if(mesAlumno >= mesInicial && mesAlumno <= mesFinal) {
                        //System.out.println("alu: "+mesAlumno);
                        listCumple.add(new AlumnoCumpleEntity(nombreAlumno,nombreAlumno,nombreAula,ConvertirEUAtoEUstring(fechaNacimientoAlumno)));
                    }

                    if( !fechaNacimientoPadre.trim().equals("") ) {

                        mesPadre = Integer.parseInt(fechaNacimientoPadre.substring(3,5));
                       
                        if(mesPadre >= mesInicial && mesPadre <= mesFinal) {
                             //System.out.println("padre: "+mesPadre);
                            listCumple.add(new AlumnoCumpleEntity(nombrePadre,nombreAlumno,nombreAula,fechaNacimientoPadre));
                        }
                    }

                    if(!fechaNacimientoMadre.trim().equals("") ) {

                        mesMadre = Integer.parseInt(fechaNacimientoMadre.substring(3,5));
                        
                        if(mesMadre >= mesInicial && mesMadre <= mesFinal) {
                           // System.out.println("madre: "+mesMadre);
                            listCumple.add(new AlumnoCumpleEntity(nombreMadre,nombreAlumno,nombreAula,fechaNacimientoMadre));
                        }
                    }
                }

                ClsConexion.closeConnection(con);

            } catch (NumberFormatException | SQLException | ParseException ex) {
                Logger.getLogger(ReporteImprimirCumpleanos.class.getName()).log(Level.SEVERE, null, ex);
            }

            //REPORTE

            JasperPrint jPrint;
            JasperViewer jView;
            String reporteJrxml = "/Reportes/RptCumpleanosCard.jrxml";
            String reporteJasper = "/Reportes/RptCumpleanosCard.jasper";

            try {

                JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listCumple);
                JasperDesign jasperDesign = JRXmlLoader.load(System.getProperty("user.dir") + reporteJrxml);
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                jPrint = JasperFillManager.fillReport(jasperReport, null, ds);
                jView = new JasperViewer(jPrint, false);
                jView.setTitle("Tarjeta de cumpleaños");
                jView.setVisible(true);

            } catch (JRException ex) {
                Logger.getLogger(FrmRptSexoAlumno.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
        else if (rbnUsuario.isSelected()) {
            GenerarReporteUsuario();
        }
        
    }//GEN-LAST:event_btnGenerarReporteActionPerformed

    private void GenerarReporteUsuario() {
        
        Connection con;
        ClsAlumno alumnos = new ClsAlumno();
        int mes; String nombreUsuario; String fechaNacimiento; String nombreAula;

        int mesInicial = obtenerMesNumero(cmbInicio.getSelectedItem().toString()) + 1;
        int mesFinal = obtenerMesNumero(cmbFin.getSelectedItem().toString()) + 1;
        ArrayList<AlumnoCumpleEntity> listCumple = new ArrayList<>();
        String idPeriodo = listIdPeriodo.get(cmbPeriodo.getSelectedIndex());
        con = ClsConexion.getConection();
        
        try {
            
            ResultSet rsAlu = alumnos.SeleccionarUsuarioNacimiento(idPeriodo,con);
            
            while(rsAlu.next()) {
                
                nombreUsuario = rsAlu.getString("usuario");
                fechaNacimiento = rsAlu.getString("fecha_nacimiento");
                nombreAula = rsAlu.getString("nombre_seccion");
                
                mes = Integer.parseInt(fechaNacimiento.substring(5,7));
                
                if(mes >= mesInicial && mes <= mesFinal) {
                    
                    listCumple.add(new AlumnoCumpleEntity(nombreUsuario,"",nombreAula,ConvertirEUAtoEUstring(fechaNacimiento)));
                }
            }
                        
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(ReporteImprimirCumpleanos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JasperPrint jPrint;
        JasperViewer jView;
        String reporteJrxml = "/Reportes/RptCumpleanosCard.jrxml";
        String reporteJasper = "/Reportes/RptCumpleanosCard.jasper";
                
        try {
            
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listCumple);
            JasperDesign jasperDesign = JRXmlLoader.load(System.getProperty("user.dir") + reporteJrxml);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            jPrint = JasperFillManager.fillReport(jasperReport, null, ds);
            jView = new JasperViewer(jPrint, false);
            jView.setTitle("Tarjeta de cumpleaños");
            jView.setVisible(true);
            
        } catch (JRException ex) {
            Logger.getLogger(FrmRptSexoAlumno.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    private void cmbNivelItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbNivelItemStateChanged
        
        if(cmbNivel.getSelectedIndex() != -1 ) {
            
            CargarComboPeriodo();             
        }
    }//GEN-LAST:event_cmbNivelItemStateChanged

    private void CargarComboNivel() {
        
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
    
    private void CargarComboPeriodo() {
        
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
    
    private int obtenerMesInicioClases(String codMatricula,Connection conx) {
        int mmes;
        String inicioClases = "";
        ClsMatricula matricula = new ClsMatricula();
        ResultSet rsMatricula;    

        try 
        {
            rsMatricula = matricula.SeleccionarMatriculaSolo(codMatricula,conx);
            while(rsMatricula.next())
            {

                inicioClases = rsMatricula.getString("inicio_clases");                    
            }                

        } catch (Exception ex) {
            Logger.getLogger(FrmPagoTodo.class.getName()).log(Level.SEVERE, null, ex);
        }

        mmes = Integer.parseInt(inicioClases.substring(3, 5))-1;
        return mmes;
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarReporte;
    private javax.swing.JComboBox<String> cmbFin;
    private javax.swing.JComboBox<String> cmbInicio;
    private javax.swing.JComboBox<String> cmbNivel;
    private javax.swing.JComboBox<String> cmbPeriodo;
    private javax.swing.ButtonGroup grupo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel pnlBot;
    private javax.swing.JPanel pnlDerecha;
    private javax.swing.JRadioButton rbnAlumno;
    private javax.swing.JRadioButton rbnUsuario;
    // End of variables declaration//GEN-END:variables
}
