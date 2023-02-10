/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckInterfaces;

import PckEntidad.ClsEntidadNivel;
import PckEntidad.ClsEntidadPeriodo;
import PckEntidad.ClsEntidadReportesGenerales;
import PckEntidad.ClsEntidadSeccion;
import PckNegocio.ClsNivel;
import PckNegocio.ClsPeriodo;
import PckNegocio.ClsReportesGenerales;
import PckNegocio.ClsSeccion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author kmv
 */
public class FrmReportesFicha extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmReportesFicha
     */
    ArrayList<String> codigoNivel = new ArrayList();
    ArrayList<String> codigoPeriodo = new ArrayList();
    ArrayList<String> codigoSeccion = new ArrayList();
    public FrmReportesFicha() {
        initComponents();
        grupoFiltro.add(radioNivel);
        grupoFiltro.add(radioPeriodo);
        grupoFiltro.add(radioSeccion);
        radioSeccion.setSelected(true);
        
        grupoOpciones.add(ra1);
        grupoOpciones.add(ra2);
        grupoOpciones.add(ra3);
        grupoOpciones.add(ra4);
        grupoOpciones.add(ra5);
        grupoOpciones.add(ra6);
        ra2.setSelected(true); 
        
        cargaComboNivel();
        cargaComboPeriodo();
        if(cmbPeriodo.getSelectedIndex() != -1)
        {            
            cargaComboSeccion();
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

        grupoFiltro = new javax.swing.ButtonGroup();
        grupoOpciones = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        cmbNivel = new javax.swing.JComboBox();
        cmbPeriodo = new javax.swing.JComboBox();
        cmbSeccion = new javax.swing.JComboBox();
        radioNivel = new javax.swing.JRadioButton();
        radioPeriodo = new javax.swing.JRadioButton();
        radioSeccion = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        ra1 = new javax.swing.JRadioButton();
        ra2 = new javax.swing.JRadioButton();
        ra3 = new javax.swing.JRadioButton();
        ra4 = new javax.swing.JRadioButton();
        ra5 = new javax.swing.JRadioButton();
        ra6 = new javax.swing.JRadioButton();
        btnGenerar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setTitle("Reportes");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Droid Sans", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 153));
        jLabel3.setText("REPORTES GENERALES MPS");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, -1, -1));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtro"));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmbNivel.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbNivel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "GUARDERIA", "INICIAL" }));
        cmbNivel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbNivelItemStateChanged(evt);
            }
        });
        jPanel7.add(cmbNivel, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 118, -1));

        cmbPeriodo.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbPeriodo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbPeriodo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPeriodoItemStateChanged(evt);
            }
        });
        jPanel7.add(cmbPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 118, -1));

        cmbSeccion.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbSeccion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel7.add(cmbSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 118, -1));

        radioNivel.setBackground(new java.awt.Color(255, 255, 255));
        radioNivel.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        radioNivel.setText("Por Nivel:");
        radioNivel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radioNivelItemStateChanged(evt);
            }
        });
        jPanel7.add(radioNivel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 90, -1));

        radioPeriodo.setBackground(new java.awt.Color(255, 255, 255));
        radioPeriodo.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        radioPeriodo.setText("Por Periodo:");
        radioPeriodo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radioPeriodoItemStateChanged(evt);
            }
        });
        jPanel7.add(radioPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 90, -1));

        radioSeccion.setBackground(new java.awt.Color(255, 255, 255));
        radioSeccion.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        radioSeccion.setText("Por Sección:");
        radioSeccion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radioSeccionItemStateChanged(evt);
            }
        });
        jPanel7.add(radioSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 90, -1));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 250, 180));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ra1.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        ra1.setText("Asiste los sábados");
        jPanel2.add(ra1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        ra2.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        ra2.setText("Pertenece a la banda");
        jPanel2.add(ra2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        ra3.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        ra3.setText("Fecha de cumpleaños y números");
        jPanel2.add(ra3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        ra4.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        ra4.setText("Cuenta con descuento");
        jPanel2.add(ra4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        ra5.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        ra5.setText("Masculino");
        jPanel2.add(ra5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        ra6.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        ra6.setText("Femenino");
        jPanel2.add(ra6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 130, 240, 160));

        btnGenerar.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnGenerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/file-document.png"))); // NOI18N
        btnGenerar.setText("Generar");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGenerar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, 110, 40));

        btnSalir.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/ic_exit_to_app_black_24dp.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 350, 90, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbPeriodoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPeriodoItemStateChanged
        if(cmbPeriodo.getSelectedIndex() != -1)
        {
            cargaComboSeccion();
        }
    }//GEN-LAST:event_cmbPeriodoItemStateChanged

    private void radioNivelItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radioNivelItemStateChanged
        if(radioNivel.isSelected())
        {
            activaItem(true,false,false);
          
        }
    }//GEN-LAST:event_radioNivelItemStateChanged

    private void radioPeriodoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radioPeriodoItemStateChanged
        if(radioPeriodo.isSelected())
        {
            activaItem(false,true,false);
           
        }
    }//GEN-LAST:event_radioPeriodoItemStateChanged

    private void radioSeccionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radioSeccionItemStateChanged
        if(radioSeccion.isSelected())
        {
            activaItem(false,false,true);
          
        }
    }//GEN-LAST:event_radioSeccionItemStateChanged

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
      ClsReportesGenerales rpt = new ClsReportesGenerales();
      ArrayList<ClsEntidadReportesGenerales> dataBean;
      String opcion;
      int cont;
      if(ra1.isSelected()) //ASISTE LOS SÁBADOS
      {
          //filtro: por nivel (aun no implementado), por periodo o por seccion   
          if(radioPeriodo.isSelected())
          {   cont=0;
              opcion = cmbPeriodo.getSelectedItem().toString();
              dataBean = rpt.ListarReporteSabadoPeriodo(opcion);
              for(ClsEntidadReportesGenerales i: dataBean)
              { cont++;             
              }  
                 //Reporte sabado periodo
                 String  url1 ="/Reportes/RptSabadoPeriodo.jasper";
                 String  url2 ="/Reportes/RptSabadoPeriodo.jrxml";
                 Map p = new HashMap();            
                 p.put("total", String.valueOf(cont));
                 p.put("opcion", opcion);           
                 JasperPrint print;
                 JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(dataBean);
                 try{              
                    JasperCompileManager.compileReportToFile(System.getProperty("user.dir")+url2, System.getProperty("user.dir")+url1);              
                    print = JasperFillManager.fillReport(System.getProperty("user.dir")+url1, p,ds);
                    JasperViewer view = new JasperViewer(print,false);
                    view.setTitle("Reporte Asiste Sábados Periodo");
                    view.setVisible(true);
                 }
                 catch(JRException ex)
                 {
                    JOptionPane.showMessageDialog(this, ex);
                 }
          }
          else if(radioSeccion.isSelected())
          {   cont=0;
              opcion = cmbSeccion.getSelectedItem().toString();
              dataBean = rpt.ListarReporteSabadoSeccion(opcion);
              for(ClsEntidadReportesGenerales i: dataBean)
              { cont++;             
              }             
                 //Reporte banda sección
                 String  url1 ="/Reportes/RptSabadoSeccion.jasper";
                 String  url2 ="/Reportes/RptSabadoSeccion.jrxml";
                 Map p = new HashMap();            
                 p.put("total", String.valueOf(cont));
                 p.put("opcion", opcion);           
                 JasperPrint print;
                 JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(dataBean);
                 try{              
                    JasperCompileManager.compileReportToFile(System.getProperty("user.dir")+url2, System.getProperty("user.dir")+url1);              
                    print = JasperFillManager.fillReport(System.getProperty("user.dir")+url1, p,ds);
                    JasperViewer view = new JasperViewer(print,false);
                    view.setTitle("Reporte Asiste Sábados Sección");
                    view.setVisible(true);
                 }
                 catch(JRException ex)
                 {
                    Logger.getLogger(FrmReportesFicha.class.getName()).log(Level.SEVERE, null, ex);
                 }
          }
      }
      if (ra2.isSelected()) // BANDA
      {
          //filtro: por nivel (aun no implementado), por periodo o por seccion   
          if(radioPeriodo.isSelected())
          {   cont=0;
              opcion = cmbPeriodo.getSelectedItem().toString();
              dataBean = rpt.ListarReporteBandaPeriodo(opcion);
              for(ClsEntidadReportesGenerales i: dataBean)
              { cont++;             
              }  
                 //Reporte banda periodo
                 String  url1 ="/Reportes/RptBandaPeriodo.jasper";
                 String  url2 ="/Reportes/RptBandaPeriodo.jrxml";
                 Map p = new HashMap();            
                 p.put("total", String.valueOf(cont));
                 p.put("opcion", opcion);           
                 JasperPrint print;
                 JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(dataBean);
                 try{              
                    JasperCompileManager.compileReportToFile(System.getProperty("user.dir")+url2, System.getProperty("user.dir")+url1);              
                    print = JasperFillManager.fillReport(System.getProperty("user.dir")+url1, p,ds);
                    JasperViewer view = new JasperViewer(print,false);
                    view.setTitle("Reporte Banda Periodo");
                    view.setVisible(true);
                 }
                 catch(JRException ex)
                 {
                    JOptionPane.showMessageDialog(this, ex);
                 }
          }
          else if(radioSeccion.isSelected())
          {   cont=0;
              opcion = cmbSeccion.getSelectedItem().toString();
              dataBean = rpt.ListarReporteBandaSeccion(opcion);
              for(ClsEntidadReportesGenerales i: dataBean)
              { cont++;             
              }             
                 //Reporte banda sección
                 String  url1 ="/Reportes/RptBandaSeccion.jasper";
                 String  url2 ="/Reportes/RptBandaSeccion.jrxml";
                 Map p = new HashMap();            
                 p.put("total", String.valueOf(cont));
                 p.put("opcion", opcion);           
                 JasperPrint print;
                 JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(dataBean);
                 try{              
                    JasperCompileManager.compileReportToFile(System.getProperty("user.dir")+url2, System.getProperty("user.dir")+url1);              
                    print = JasperFillManager.fillReport(System.getProperty("user.dir")+url1, p,ds);
                    JasperViewer view = new JasperViewer(print,false);
                    view.setTitle("Reporte Banda Sección");
                    view.setVisible(true);
                 }
                 catch(JRException ex)
                 {
                    Logger.getLogger(FrmReportesFicha.class.getName()).log(Level.SEVERE, null, ex);
                 }
          }            
      }  
      if (ra3.isSelected()) // CUMPLEAÑOS
      {
          //filtro: por nivel (aun no implementado), por periodo o por seccion   
          if(radioPeriodo.isSelected())
          {   
              opcion = cmbPeriodo.getSelectedItem().toString();
              try{
              rpt.ExcelCumplePeriodo(opcion);

              }
              catch(Exception ex)
              {

              }
          }
          else if(radioSeccion.isSelected())
          { 
              opcion = cmbSeccion.getSelectedItem().toString();
              try{
              rpt.ExcelCumpleSeccion(opcion);
              
              }
              catch(Exception ex)
              {
                 
              }
          }          
      }
      
        
    }//GEN-LAST:event_btnGenerarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
       this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void cmbNivelItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbNivelItemStateChanged
        
        if(cmbNivel.getSelectedIndex() != -1)
        {
            cargaComboPeriodo();
            
            if(cmbPeriodo.getSelectedIndex() != -1)
            {
                cargaComboSeccion();
            }
        }
        
    }//GEN-LAST:event_cmbNivelItemStateChanged
   
    private void cargaComboNivel()
    {
        ClsNivel niveles = new ClsNivel();
        ArrayList<ClsEntidadNivel> nivel =  niveles.SeleccionarNivel();
        Iterator it = nivel.iterator();
        DefaultComboBoxModel dtm = new DefaultComboBoxModel();
        dtm.removeAllElements();
        cmbNivel.removeAllItems();
                
        while(it.hasNext())
        {
        ClsEntidadNivel Nivel;
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
        ClsEntidadPeriodo Periodo ;
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
   private void cargaComboSeccion()
   {    
        codigoSeccion.clear();
        ClsSeccion secciones = new ClsSeccion();
        ArrayList<ClsEntidadSeccion> seccion =  secciones.ListarSeccion();
        Iterator it = seccion.iterator();
        DefaultComboBoxModel DefaultComboBoxModel = new DefaultComboBoxModel();
        DefaultComboBoxModel.removeAllElements();
        cmbSeccion.removeAllItems();
        String fila2[] = new String[5];        
        while(it.hasNext())
        {
        ClsEntidadSeccion Seccion = (ClsEntidadSeccion) it.next();             
        fila2[0] = Seccion.getNombre_Seccion();
        fila2[1] = Seccion.getEstado_Periodo();       
        fila2[3] = Seccion.getNombre_Periodo();
        
            if (codigoPeriodo.get(cmbPeriodo.getSelectedIndex()).equals(Seccion.getId_Periodo())) 
            {
                codigoSeccion.add(Seccion.getId_Seccion());
                DefaultComboBoxModel.addElement(fila2[0]);
            }       
        }
        cmbSeccion.setModel(DefaultComboBoxModel);         
   }
    

    void activaItem(boolean nivel,boolean periodo,boolean seccion)
    {
        cmbNivel.setEnabled(nivel);
        cmbPeriodo.setEnabled(periodo);
        cmbSeccion.setEnabled(seccion);    
    }
    
       
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cmbNivel;
    private javax.swing.JComboBox cmbPeriodo;
    private javax.swing.JComboBox cmbSeccion;
    private javax.swing.ButtonGroup grupoFiltro;
    private javax.swing.ButtonGroup grupoOpciones;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JRadioButton ra1;
    private javax.swing.JRadioButton ra2;
    private javax.swing.JRadioButton ra3;
    private javax.swing.JRadioButton ra4;
    private javax.swing.JRadioButton ra5;
    private javax.swing.JRadioButton ra6;
    private javax.swing.JRadioButton radioNivel;
    private javax.swing.JRadioButton radioPeriodo;
    private javax.swing.JRadioButton radioSeccion;
    // End of variables declaration//GEN-END:variables
}
