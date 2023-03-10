/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PckInterfaces;

import ClsEntidadReporte.ClsCantidad;
import PckConexion.ClsConexion;
import PckEntidad.ClsEntidadAsistenciaFiltro;
import PckEntidad.ClsEntidadNivel;
import PckEntidad.ClsEntidadPeriodo;
import PckNegocio.ClsAsistenciaFiltro;
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
public class FrmRptCantidad extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmRptCantidad
     */
    Connection con = null;
    ArrayList<String> codigoNivel = new ArrayList();
    ArrayList<String> codigoPeriodo = new ArrayList();
    
    public FrmRptCantidad() {
        initComponents();
        cargaComboNivel();
        cargaComboPeriodo();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCantidad = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbPeriodo = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cmbNivel = new javax.swing.JComboBox<>();
        btnVer = new javax.swing.JButton();
        btnGenerar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtTConAlimentacion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTMatriculados = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTSinAlimentacion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTAsistieron = new javax.swing.JTextField();
        txtTFaltaron = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtThoy = new javax.swing.JTextField();

        setTitle("Reporte Cantidad Ni??os");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblCantidad.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        tblCantidad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Aula", "Matriculados", "Con S. Alimentacion", "Sin S. Alimentacion", "Asistieron", "Faltaron", "S. Alimentacion Hoy"
            }
        ));
        jScrollPane1.setViewportView(tblCantidad);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 860, 310));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 860, 10));

        jLabel1.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel1.setText("Cantidad de Ni??os:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 100, -1));

        jLabel2.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel2.setText("Seleccione Periodo:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, 20));

        cmbPeriodo.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbPeriodo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cmbPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 200, -1));

        jLabel3.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel3.setText("Seleccione Nivel:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, 20));

        cmbNivel.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbNivel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbNivel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbNivelItemStateChanged(evt);
            }
        });
        jPanel1.add(cmbNivel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, 200, -1));

        btnVer.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnVer.setText("Ver");
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });
        jPanel1.add(btnVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 60, -1, 30));

        btnGenerar.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnGenerar.setText("Generar Reporte");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGenerar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 60, -1, 30));

        btnSalir.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 60, -1, 30));

        jLabel4.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel4.setText("T. Matriculados:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, -1, 20));

        txtTConAlimentacion.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtTConAlimentacion.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTConAlimentacion.setEnabled(false);
        jPanel1.add(txtTConAlimentacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 530, 40, -1));

        jLabel5.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel5.setText("T. Con S. Alimentacion:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 510, -1, 20));

        txtTMatriculados.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtTMatriculados.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTMatriculados.setEnabled(false);
        jPanel1.add(txtTMatriculados, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, 40, -1));

        jLabel6.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel6.setText("T. Sin S. Alimentacion:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 510, -1, 20));

        txtTSinAlimentacion.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtTSinAlimentacion.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTSinAlimentacion.setEnabled(false);
        jPanel1.add(txtTSinAlimentacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 530, 40, -1));

        jLabel7.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel7.setText("T. Asistieron:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 510, -1, 20));

        txtTAsistieron.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtTAsistieron.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTAsistieron.setEnabled(false);
        jPanel1.add(txtTAsistieron, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 530, 40, -1));

        txtTFaltaron.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtTFaltaron.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTFaltaron.setEnabled(false);
        jPanel1.add(txtTFaltaron, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 530, 40, -1));

        jLabel8.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel8.setText("T. Faltaron:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 510, -1, 20));

        jLabel9.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel9.setText("T. S. Alimentacion Hoy:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 510, -1, 20));

        txtThoy.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtThoy.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtThoy.setEnabled(false);
        jPanel1.add(txtThoy, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 530, 40, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void cmbNivelItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbNivelItemStateChanged
        
        if(cmbNivel.getSelectedIndex() != -1)
        {
            cargaComboPeriodo();            
        }
        
    }//GEN-LAST:event_cmbNivelItemStateChanged

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        
        con = ClsConexion.getConection();
        //Declaraci??n de variables        
        int contAsistioSI = 0;
        int contAsistioNO = 0;
        int contAlimentacionSI = 0;
        int contAlimentacionNO = 0;
        int contMatriculado = 0;        
        int contAlimentacionSiAsistio = 0;
        
        int contTAsistioSI = 0;
        int contTAsistioNO = 0;
        int contTAlimentacionSI = 0;
        int contTAlimentacionNO = 0;
        int contTMatriculado = 0;        
        int contTAlimentacionSiAsistio = 0;
        String nombreAula;
        String idAula;
        Date date = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        
        ResultSet rsResultado;
        ResultSet rsAulas;
        ResultSet rsMatricula;
        
        ClsCantidad clsCantidad;
        ClsSeccion clsSeccion = new ClsSeccion();
        ClsMatricula clsMatricula = new ClsMatricula();
        ClsAsistenciaFiltro clsAsistenciaFiltro = new ClsAsistenciaFiltro();
        ArrayList <ClsCantidad> arrayCantidad = new ArrayList<>();
        ArrayList<ClsEntidadAsistenciaFiltro> arrayAlumno = new ArrayList<>();
        
        Map mapa = new HashMap();
        JasperPrint jpReporte;
        JRBeanCollectionDataSource dsReporte;
        JasperViewer jvReporte;
        String pathJRXML = "";
        String pathJASPER = "";
        
        //conseguir la lista de aulas (id) del periodo seleccionado
        rsAulas = clsSeccion.ListarAulasPorPeriodo(codigoPeriodo.get(cmbPeriodo.getSelectedIndex()));
        try 
        {            
            while(rsAulas.next())
            {                
                idAula = rsAulas.getString("id_seccion");
                nombreAula = rsAulas.getString("nombre_seccion");
                
                contMatriculado = 0;
                contAlimentacionSI = 0;
                contAlimentacionNO = 0;
                contAsistioSI = 0;
                contAsistioNO = 0;
                contAlimentacionSiAsistio = 0;
                
                //Datos de Matricula                
                rsMatricula = clsMatricula.ListarMatriculaAula(idAula,con);
                while(rsMatricula.next())
                {
                    contMatriculado++;
                    contTMatriculado++;
                    if(rsMatricula.getString("comida_alumno").equals("SI"))
                    {
                        contAlimentacionSI++;
                        contTAlimentacionSI++;
                    }
                    else
                    {
                        contAlimentacionNO++;
                        contTAlimentacionNO++;
                    }
                }
                
                //Datos de Asistencia
                arrayAlumno.clear();
                arrayAlumno = clsAsistenciaFiltro.ListarAsistenciaFiltroSeccion(idAula, formato.format(date), formato.format(date), "1", "1", "1");                
                for(ClsEntidadAsistenciaFiltro af : arrayAlumno)
                {
                    switch(af.getAsistencia_Asistencia())
                    {
                        case "A":
                            contAsistioSI++;
                            contTAsistioSI++;
                            break;
                        case "T":
                            contAsistioSI++;
                            contTAsistioSI++;
                            break;
                        case "F":
                            contAsistioNO++;
                            contTAsistioNO++;
                    }
                    
                    if(af.getAlumno_Comida().equals("SI") && (af.getAsistencia_Asistencia().equals("A") || af.getAsistencia_Asistencia().equals("T")))
                    {
                        contAlimentacionSiAsistio++;
                        contTAlimentacionSiAsistio++;
                    }                    
                }     
                
                //Asignando datos al Array principal
                clsCantidad = new ClsCantidad
                (
                    nombreAula, 
                    String.valueOf(contMatriculado), 
                    String.valueOf(contAlimentacionSI), 
                    String.valueOf(contAlimentacionNO), 
                    String.valueOf(contAsistioSI), 
                    String.valueOf(contAsistioNO), 
                    String.valueOf(contAlimentacionSiAsistio)
                );
                arrayCantidad.add(clsCantidad);
            }  
            
            //Creando Tabla
            String titulos[] = {"Aula","Matriculados","Con S. Alimentacion","Sin S. Alimentacion","Asistieron","Faltaron","S.Alimentacion Hoy"};
            String fila[] = new String[8];
            
            DefaultTableModel dtm =new DefaultTableModel(null,titulos){
            @Override
            public boolean isCellEditable(int rowIndex,int columnIndex){return false;} 
            }; 
            
            for(ClsCantidad c : arrayCantidad)
            {
                fila[0] = c.getAula();
                fila[1] = c.getMatriculados();
                fila[2] = c.getConAlimentacion();
                fila[3] = c.getSinAlimentacion();
                fila[4] = c.getSiAsistieron();
                fila[5] = c.getNoAsistieron();
                fila[6] = c.getAlimentacionHoy();
                
                dtm.addRow(fila);
            }
            
            tblCantidad.setModel(dtm);
            TableCellRenderer tcr =  tblCantidad.getTableHeader().getDefaultRenderer();                       
            ClsMisc.miRender mr = new ClsMisc.miRender(tcr);        
            mr.getTableCellRendererComponent(tblCantidad, fila, isSelected, isIcon, WIDTH, 0);
            mr.getTableCellRendererComponent(tblCantidad, fila, isSelected, isIcon, WIDTH, 1);
            mr.getTableCellRendererComponent(tblCantidad, fila, isSelected, isIcon, WIDTH, 2);
            mr.getTableCellRendererComponent(tblCantidad, fila, isSelected, isIcon, WIDTH, 3);
            mr.getTableCellRendererComponent(tblCantidad, fila, isSelected, isIcon, WIDTH, 4);
            mr.getTableCellRendererComponent(tblCantidad, fila, isSelected, isIcon, WIDTH, 5);
            mr.getTableCellRendererComponent(tblCantidad, fila, isSelected, isIcon, WIDTH, 6);
            
            txtTMatriculados.setText(String.valueOf(contTMatriculado));
            txtTConAlimentacion.setText(String.valueOf(contTAlimentacionSI));
            txtTSinAlimentacion.setText(String.valueOf(contTAlimentacionNO));
            txtTAsistieron.setText(String.valueOf(contTAsistioSI));
            txtTFaltaron.setText(String.valueOf(contTAsistioNO));
            txtThoy.setText(String.valueOf(contTAlimentacionSiAsistio));
            ClsConexion.closeConnection(con);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(FrmRptCantidad.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_btnVerActionPerformed

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        //Declaraci??n de variables        
        int contAsistioSI = 0;
        int contAsistioNO = 0;
        int contAlimentacionSI = 0;
        int contAlimentacionNO = 0;
        int contMatriculado = 0;        
        int contAlimentacionSiAsistio = 0;
        
        int contTAsistioSI = 0;
        int contTAsistioNO = 0;
        int contTAlimentacionSI = 0;
        int contTAlimentacionNO = 0;
        int contTMatriculado = 0;        
        int contTAlimentacionSiAsistio = 0;
        
        String nombreAula;
        String idAula;
        Date date = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        
        ResultSet rsResultado;
        ResultSet rsAulas;
        ResultSet rsMatricula;
        con = ClsConexion.getConection();
        ClsCantidad clsCantidad;
        ClsSeccion clsSeccion = new ClsSeccion();
        ClsMatricula clsMatricula = new ClsMatricula();
        ClsAsistenciaFiltro clsAsistenciaFiltro = new ClsAsistenciaFiltro();
        ArrayList <ClsCantidad> arrayCantidad = new ArrayList<>();
        ArrayList<ClsEntidadAsistenciaFiltro> arrayAlumno = new ArrayList<>();        
        
        Map mapa = new HashMap();
        JasperPrint jpReporte;
        JRBeanCollectionDataSource dsReporte;
        JasperViewer jvReporte;
        String pathJRXML = "/Reportes/RptCantidadNinos.jrxml";
        String pathJASPER = "/Reportes/RptCantidadNinos.jasper";
        
        //conseguir la lista de aulas (id) del periodo seleccionado
        rsAulas = clsSeccion.ListarAulasPorPeriodo(codigoPeriodo.get(cmbPeriodo.getSelectedIndex()));
        try 
        {            
            while(rsAulas.next())
            {                
                idAula = rsAulas.getString("id_seccion");
                nombreAula = rsAulas.getString("nombre_seccion");
                
                contMatriculado = 0;
                contAlimentacionSI = 0;
                contAlimentacionNO = 0;
                contAsistioSI = 0;
                contAsistioNO = 0;
                contAlimentacionSiAsistio = 0;
                
                //Datos de Matricula                
                rsMatricula = clsMatricula.ListarMatriculaAula(idAula,con);
                while(rsMatricula.next())
                {
                    contMatriculado++;
                    contTMatriculado++;
                    if(rsMatricula.getString("comida_alumno").equals("SI"))
                    {
                        contAlimentacionSI++;
                        contTAlimentacionSI++;
                    }
                    else
                    {
                        contAlimentacionNO++;
                        contTAlimentacionNO++;
                    }
                }
                
                //Datos de Asistencia
                arrayAlumno.clear();
                arrayAlumno = clsAsistenciaFiltro.ListarAsistenciaFiltroSeccion(idAula, formato.format(date), formato.format(date), "1", "1", "1");                
                for(ClsEntidadAsistenciaFiltro af : arrayAlumno)
                {
                    switch(af.getAsistencia_Asistencia())
                    {
                        case "A":
                            contAsistioSI++;
                            contTAsistioSI++;
                            break;
                        case "T":
                            contAsistioSI++;
                            contTAsistioSI++;
                            break;
                        case "F":
                            contAsistioNO++;
                            contTAsistioNO++;
                    }
                    
                    if(af.getAlumno_Comida().equals("SI") && (af.getAsistencia_Asistencia().equals("A") || af.getAsistencia_Asistencia().equals("T")))
                    {
                        contAlimentacionSiAsistio++;
                        contTAlimentacionSiAsistio++;
                    }                    
                }     
                
                //Asignando datos al Array principal
                clsCantidad = new ClsCantidad
                (
                    nombreAula, 
                    String.valueOf(contMatriculado), 
                    String.valueOf(contAlimentacionSI), 
                    String.valueOf(contAlimentacionNO), 
                    String.valueOf(contAsistioSI), 
                    String.valueOf(contAsistioNO), 
                    String.valueOf(contAlimentacionSiAsistio)
                );
                arrayCantidad.add(clsCantidad);
            }  
            
            //creando reporte  
            mapa.put("matriculado", String.valueOf(contTMatriculado));
            mapa.put("alimentacionsi", String.valueOf(contTAlimentacionSI));
            mapa.put("alimentacionno", String.valueOf(contTAlimentacionNO));
            mapa.put("asistiosi", String.valueOf(contTAsistioSI));
            mapa.put("asistiono", String.valueOf(contTAsistioNO));
            mapa.put("alimentacionsiasistio", String.valueOf(contTAlimentacionSiAsistio));
            
            dsReporte = new JRBeanCollectionDataSource(arrayCantidad);
            JasperCompileManager.compileReportToFile
                (
                    System.getProperty("user.dir")+pathJRXML,
                    System.getProperty("user.dir")+pathJASPER
                );
            jpReporte = JasperFillManager.fillReport(System.getProperty("user.dir")+pathJASPER, mapa,dsReporte);
            jvReporte = new JasperViewer(jpReporte, false);
            jvReporte.setTitle("Reporte Cantidad Ni??os");
            jvReporte.setVisible(true);
            
        } 
        catch (SQLException | JRException ex) 
        {
            Logger.getLogger(FrmRptCantidad.class.getName()).log(Level.SEVERE, null, ex);
        }
        ClsConexion.closeConnection(con);
    }//GEN-LAST:event_btnGenerarActionPerformed

    
    private void cargaComboNivel()
    {
        ClsNivel niveles = new ClsNivel();
        ArrayList<ClsEntidadNivel> nivele =  niveles.SeleccionarNivel();
        Iterator it = nivele.iterator();
        DefaultComboBoxModel dtm = new DefaultComboBoxModel();
        dtm.removeAllElements();
        cmbNivel.removeAllItems();
                
        while(it.hasNext())
        {
        ClsEntidadNivel Nivel = new ClsEntidadNivel();
        Nivel = (ClsEntidadNivel) it.next();       
        codigoNivel.add(Nivel.getNivel_id());
        dtm.addElement(Nivel.getNivel_nombre());                
        }
        cmbNivel.setModel(dtm); 
    }
    
    private void cargaComboPeriodo()
    {
        codigoPeriodo.clear();
        ClsPeriodo periodos = new ClsPeriodo();
        ArrayList<ClsEntidadPeriodo> periodo =  periodos.ListarPeriodo();
        Iterator iterator = periodo.iterator();
        DefaultComboBoxModel DefaultComboBoxModel = new DefaultComboBoxModel();
        DefaultComboBoxModel.removeAllElements();
        cmbPeriodo.removeAllItems();
            
        while(iterator.hasNext())
        {
        ClsEntidadPeriodo Periodo = new ClsEntidadPeriodo();
        Periodo = (ClsEntidadPeriodo) iterator.next();               
        
            if(Periodo.getEstado_Periodo().equals("Activo") &&                     
            codigoNivel.get(cmbNivel.getSelectedIndex()).equals(Periodo.getNivel_Id()))
            {   
            codigoPeriodo.add(Periodo.getId_Periodo());     
            DefaultComboBoxModel.addElement(Periodo.getNombre_Periodo());        
            }
        }
        cmbPeriodo.setModel(DefaultComboBoxModel);       
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnVer;
    private javax.swing.JComboBox<String> cmbNivel;
    private javax.swing.JComboBox<String> cmbPeriodo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblCantidad;
    private javax.swing.JTextField txtTAsistieron;
    private javax.swing.JTextField txtTConAlimentacion;
    private javax.swing.JTextField txtTFaltaron;
    private javax.swing.JTextField txtTMatriculados;
    private javax.swing.JTextField txtTSinAlimentacion;
    private javax.swing.JTextField txtThoy;
    // End of variables declaration//GEN-END:variables
}
