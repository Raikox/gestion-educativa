/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckInterfaces;

import PckEntidad.ClsEntidadMora;
import PckEntidad.ClsEntidadPeriodo;
import PckEntidad.ClsEntidadProducto;
import PckEntidad.ClsEntidadSeccion;
import PckEntidad.ClsEntidadServicio;
import PckNegocio.ClsNoDeuda;
import PckNegocio.ClsPeriodo;
import PckNegocio.ClsProducto;
import PckNegocio.ClsSeccion;
import PckNegocio.ClsServicio;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
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
public class FrmPagaron extends javax.swing.JInternalFrame {
DefaultListModel dlm = new DefaultListModel();
String salon="";
String opcion="";
String  url2 ="/Reportes/RptMora.jasper";
String  url3 ="/Reportes/RptMora.jrxml";
    /**
     * Creates new form FrmMora
     */
    public FrmPagaron() {
        initComponents();
        grupo.add(raMatricula);
        grupo.add(raMensualidad);
        grupo.add(raAlimentacion);
        grupo.add(raProductos);
        grupo.add(raServicios);
        
        cargaPeriodo();
        cargaPeriodo2();
        cargaSeccion();
        
        String tit[] = {"Apelidos y Nombres","Total a Pagar","Pendiente","Fec. Vencimiento"};
        DefaultTableModel dtm = new DefaultTableModel(null,tit);
        tblMora.setModel(dtm);
        TableColumn  columnaPer =tblMora.getColumnModel().getColumn(0);
            columnaPer.setMinWidth(300);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        raMatricula = new javax.swing.JRadioButton();
        raMensualidad = new javax.swing.JRadioButton();
        raAlimentacion = new javax.swing.JRadioButton();
        raServicios = new javax.swing.JRadioButton();
        raProductos = new javax.swing.JRadioButton();
        cmbServicio = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lista = new javax.swing.JList();
        jPanel4 = new javax.swing.JPanel();
        cmbPeriodo = new javax.swing.JComboBox();
        cmbSeccion = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        cmbPeriodo1 = new javax.swing.JComboBox();
        chbNivel = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMora = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txta = new javax.swing.JTextField();
        btnVer = new javax.swing.JButton();
        btnReporte = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setTitle("Alumnos sin deuda");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pagos"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        raMatricula.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        raMatricula.setText("Matrícula");
        raMatricula.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                raMatriculaItemStateChanged(evt);
            }
        });
        jPanel2.add(raMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        raMensualidad.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        raMensualidad.setText("Mensualidad");
        raMensualidad.setToolTipText("");
        raMensualidad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                raMensualidadItemStateChanged(evt);
            }
        });
        jPanel2.add(raMensualidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        raAlimentacion.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        raAlimentacion.setText("Alimentación");
        raAlimentacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                raAlimentacionItemStateChanged(evt);
            }
        });
        jPanel2.add(raAlimentacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        raServicios.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        raServicios.setText("Servicios");
        raServicios.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                raServiciosItemStateChanged(evt);
            }
        });
        jPanel2.add(raServicios, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));

        raProductos.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        raProductos.setText("Productos");
        raProductos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                raProductosItemStateChanged(evt);
            }
        });
        jPanel2.add(raProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

        cmbServicio.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbServicio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020" }));
        cmbServicio.setSelectedIndex(2);
        cmbServicio.setSelectedItem(2014);
        cmbServicio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbServicioItemStateChanged(evt);
            }
        });
        jPanel2.add(cmbServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 80, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 230, 200));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Concepto"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lista.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        lista.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lista);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 210, 150));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 250, 200));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Periodo o Sección"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmbPeriodo.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbPeriodo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbPeriodo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPeriodoItemStateChanged(evt);
            }
        });
        jPanel4.add(cmbPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 310, -1));

        cmbSeccion.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jPanel4.add(cmbSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 310, -1));

        jLabel1.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel1.setText("Periodo:");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel2.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel2.setText("Sección:");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Por Periodo"));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmbPeriodo1.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbPeriodo1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbPeriodo1.setEnabled(false);
        cmbPeriodo1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPeriodo1ItemStateChanged(evt);
            }
        });
        jPanel6.add(cmbPeriodo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 290, -1));

        chbNivel.setEnabled(false);
        chbNivel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chbNivelItemStateChanged(evt);
            }
        });
        jPanel6.add(chbNivel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 20, 20));

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 340, 70));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, 360, 200));

        tblMora.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        tblMora.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblMora);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 370, 650, 240));

        jLabel3.setText(" Encontradas:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 620, 70, -1));

        txta.setEnabled(false);
        jPanel1.add(txta, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 620, 30, -1));

        btnVer.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnVer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIconos/search.png"))); // NOI18N
        btnVer.setText("<html> <p align=center>Ver</p>  </html>");
        btnVer.setEnabled(false);
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });
        jPanel1.add(btnVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 290, 120, 40));

        btnReporte.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIconos/book.png"))); // NOI18N
        btnReporte.setText("<html> <p align=center>Generar</p> <p align=center>Reporte</p> </html>");
        btnReporte.setEnabled(false);
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });
        jPanel1.add(btnReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 290, 120, 40));

        btnSalir.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIconos/fileclose.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 290, 120, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 908, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbPeriodoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPeriodoItemStateChanged
        cargaSeccion();
       
    }//GEN-LAST:event_cmbPeriodoItemStateChanged

    private void raMatriculaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_raMatriculaItemStateChanged
        btnVer.setEnabled(true);
       dlm.removeAllElements();
       lista.setModel(dlm);
       chbNivel.setEnabled(true);
    }//GEN-LAST:event_raMatriculaItemStateChanged

    private void raMensualidadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_raMensualidadItemStateChanged
      cargaMeses();
       btnVer.setEnabled(false);
       chbNivel.setEnabled(true);
    }//GEN-LAST:event_raMensualidadItemStateChanged

    private void raAlimentacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_raAlimentacionItemStateChanged
      cargaMeses();
      btnVer.setEnabled(false);
      chbNivel.setEnabled(true);
    }//GEN-LAST:event_raAlimentacionItemStateChanged

    private void raServiciosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_raServiciosItemStateChanged
        if(raServicios.isSelected())
        {
        rellenaServicio();
        btnVer.setEnabled(false);
        chbNivel.setEnabled(true);
        }
        
    }//GEN-LAST:event_raServiciosItemStateChanged

    private void raProductosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_raProductosItemStateChanged
        rellenaProducto();
        btnVer.setEnabled(false);
        chbNivel.setEnabled(true);
    }//GEN-LAST:event_raProductosItemStateChanged

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        btnReporte.setEnabled(true);
        salon = cmbSeccion.getSelectedItem().toString();
        if(raMatricula.isSelected())
        {        
           if(chbNivel.isSelected())
           {
           String asd =cmbPeriodo1.getSelectedItem().toString();
           actualizarMatricula(asd);
           }else{
           actualizarMatricula(salon);}   
        }
        else if(raMensualidad.isSelected())
        {
            if(chbNivel.isSelected())
           {
            String asd =cmbPeriodo1.getSelectedItem().toString();  
            opcion = lista.getSelectedValue().toString();
            actualizarMensualidad(asd,opcion);
           }else{
            opcion = lista.getSelectedValue().toString();
            actualizarMensualidad(salon,opcion);}
        }
        else if(raAlimentacion.isSelected())
        {
           if(chbNivel.isSelected())
           {
            String asd =cmbPeriodo1.getSelectedItem().toString(); 
            opcion = lista.getSelectedValue().toString();
            actualizarAlimentacion(asd,opcion);
           }else{
            opcion = lista.getSelectedValue().toString();
            actualizarAlimentacion(salon,opcion);}
        }
        else if(raServicios.isSelected())
        {
           if(chbNivel.isSelected())
           {
            String asd =cmbPeriodo1.getSelectedItem().toString(); 
            opcion = lista.getSelectedValue().toString();
            actualizarServicio(asd,opcion);
           }else{
            opcion = lista.getSelectedValue().toString();
            actualizarServicio(salon,opcion);}
        }
        else if(raProductos.isSelected())
        {
           if(chbNivel.isSelected())
           {
            String asd =cmbPeriodo1.getSelectedItem().toString(); 
            opcion = lista.getSelectedValue().toString();
            actualizarProducto(asd,opcion);
           }else{
            opcion = lista.getSelectedValue().toString();
            actualizarProducto(salon,opcion);}
        }
        
       
        
    }//GEN-LAST:event_btnVerActionPerformed

    private void listaValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaValueChanged
        btnVer.setEnabled(true);
    }//GEN-LAST:event_listaValueChanged

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        Map p = new HashMap();
            String criterio = cmbSeccion.getSelectedItem().toString();
            String op ="";
            Double aux=0.0;
            
        int cont=0;                            
        ClsNoDeuda salones = new ClsNoDeuda();
        salon = cmbSeccion.getSelectedItem().toString();
        String asd =cmbPeriodo1.getSelectedItem().toString(); 
        ArrayList<ClsEntidadMora> dataBean = new ArrayList<ClsEntidadMora>();        
           
        if(raMatricula.isSelected())
        {   
            op = "MATRÍCULA SIN DEUDA";
            cont=0;
            if(chbNivel.isSelected())
            {
            dataBean = salones.ListarMoraMatriculaNivel(asd);
            criterio = cmbPeriodo1.getSelectedItem().toString();
            }
            else{
            dataBean = salones.ListarMoraMatricula(salon);
            }
            
            for(ClsEntidadMora i: dataBean)
            { cont++; 
              aux = aux + Double.parseDouble(i.Total);
            }
        }
        else if(raMensualidad.isSelected())
        {
            aux=0.0;
            cont=0;
        opcion = lista.getSelectedValue().toString();
            if(chbNivel.isSelected())
            {
            dataBean = salones.ListarMoraMensualidadNivel(asd,opcion);
            criterio = cmbPeriodo1.getSelectedItem().toString();
            }
            else{
            dataBean = salones.ListarMoraMensualidad(salon, opcion);
            }
        for(ClsEntidadMora i: dataBean)
        { cont++;  
         aux = aux + Double.parseDouble(i.Total);}
        
        
        op = "MENSUALIDAD MES DE "+opcion;
        
        }
        else if(raAlimentacion.isSelected())
        {
            aux=0.0;
            cont=0;
        opcion = lista.getSelectedValue().toString();
            if(chbNivel.isSelected())
                {
                dataBean = salones.ListarMoraAlimentacionNivel(asd,opcion);
                criterio = cmbPeriodo1.getSelectedItem().toString();
                }
            else{
                dataBean = salones.ListarMoraAlimentacion(salon, opcion);}
        for(ClsEntidadMora i: dataBean)
        { cont++;  aux = aux + Double.parseDouble(i.Total); }
       
        op = "ALIMENTACIÓN MES DE "+opcion;
        }
        else if(raServicios.isSelected())
        {
            aux=0.0;
            cont=0;
        opcion = lista.getSelectedValue().toString();
            if(chbNivel.isSelected())
            {
            dataBean = salones.ListarMoraServicioNivel(asd,opcion);
            criterio = cmbPeriodo1.getSelectedItem().toString();
            }
            else{
            dataBean = salones.ListarMoraServicio(salon, opcion);
            }
        for(ClsEntidadMora i: dataBean)
        { cont++;  aux = aux + Double.parseDouble(i.Total); }
       
        op = opcion;
        }
        else if(raProductos.isSelected())
        {aux=0.0;
            cont=0;
        opcion = lista.getSelectedValue().toString();
            if(chbNivel.isSelected())
            {
            dataBean = salones.ListarMoraProductoNivel(asd,opcion);
            criterio = cmbPeriodo1.getSelectedItem().toString();
            }
            else{
            dataBean = salones.ListarMoraProducto(salon, opcion);}
        for(ClsEntidadMora i: dataBean)
        { cont++;  aux = aux + Double.parseDouble(i.Total); }
        
        op = opcion;
        }
            
            
            p.put("op", op);
            p.put("aux", String.valueOf(aux));
            p.put("salon", criterio);
//            p.put("logo", this.getClass().getResourceAsStream(logotipo));
           
            JasperPrint print;
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(dataBean);
            try{
              
                JasperCompileManager.compileReportToFile(System.getProperty("user.dir")+url3, System.getProperty("user.dir")+url2);              
                print = JasperFillManager.fillReport(System.getProperty("user.dir")+url2, p,ds);
                JasperViewer view = new JasperViewer(print,false);
                view.setTitle("Reporte Niños al día por Periodo/Salón");
                view.setVisible(true);
            }
            catch(JRException ex)
            {
                JOptionPane.showMessageDialog(this, ex);
            }
    }//GEN-LAST:event_btnReporteActionPerformed

    private void cmbPeriodo1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPeriodo1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPeriodo1ItemStateChanged

    private void chbNivelItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chbNivelItemStateChanged
        if (chbNivel.isSelected())
        {
        cmbPeriodo1.setEnabled(true);
        cmbPeriodo.setEnabled(false);
        cmbSeccion.setEnabled(false);        
        }
        else
        {
        cmbPeriodo1.setEnabled(false);
        cmbPeriodo.setEnabled(true);
        cmbSeccion.setEnabled(true);        
        }
    }//GEN-LAST:event_chbNivelItemStateChanged

    private void cmbServicioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbServicioItemStateChanged
        if(raServicios.isSelected())
        {
            rellenaServicio();

        }
        else if(raProductos.isSelected())
        {
            rellenaServicio();

        }
    }//GEN-LAST:event_cmbServicioItemStateChanged
    void actualizarMatricula(String seccion){
         int a=0;
         String titulos[] = {"Apelidos y Nombres","Total a Pagar","Pendiente","Fec. Vencimiento","Periodo"};
        ClsNoDeuda moras = new ClsNoDeuda();
        ArrayList<ClsEntidadMora> mora = new ArrayList<ClsEntidadMora>();
        if(chbNivel.isSelected())
           {
            mora = moras.ListarMoraMatriculaNivel(seccion);
           }else{
            mora = moras.ListarMoraMatricula(seccion);
            }
        Iterator iterator = mora.iterator();
        DefaultTableModel defaultTableModel =new DefaultTableModel(null,titulos);        
        String fila[] = new String[5];                
        while(iterator.hasNext()){
            ClsEntidadMora Mora = new ClsEntidadMora();
            Mora = (ClsEntidadMora) iterator.next();
            fila[0] = Mora.getAlumno();
            fila[1] = Mora.getTotal();            
            fila[2] = Mora.getPendiente();
            fila[3] = Mora.getVencimiento();
            fila[4] = Mora.getPeriodo();
                    
            defaultTableModel.addRow(fila);  
            a++;
            
        }         
        if(a==0){JOptionPane.showMessageDialog(this, "No se encontraron registros");}
        tblMora.setModel(defaultTableModel);        
        TableColumn  columnaPer =tblMora.getColumnModel().getColumn(0);
            columnaPer.setMinWidth(300);       
            txta.setText(String.valueOf(a));
    }
    
    void actualizarMensualidad(String seccion,String mes){
         int a=0;
        String titulos[] = {"Apelidos y Nombres","Total a Pagar","Pendiente","Fec. Vencimiento"};
        ClsNoDeuda moras = new ClsNoDeuda();
        ArrayList<ClsEntidadMora> mora = new ArrayList<ClsEntidadMora>();
         if(chbNivel.isSelected())
         {
         mora = moras.ListarMoraMensualidadNivel(seccion,mes);
         }
         else{
        mora = moras.ListarMoraMensualidad(seccion,mes);
         }
        Iterator iterator = mora.iterator();
        DefaultTableModel defaultTableModel =new DefaultTableModel(null,titulos);        
        String fila[] = new String[4];                
        while(iterator.hasNext()){
            ClsEntidadMora Mora = new ClsEntidadMora();
            Mora = (ClsEntidadMora) iterator.next();
            fila[0] = Mora.getAlumno();
            fila[1] = Mora.getTotal();            
            fila[2] = Mora.getPendiente();
            fila[3] = Mora.getVencimiento();            
            defaultTableModel.addRow(fila);   
            a++;
        }        
        if(a==0){JOptionPane.showMessageDialog(this, "No se encontraron registros");}
        tblMora.setModel(defaultTableModel);        
        TableColumn  columnaPer =tblMora.getColumnModel().getColumn(0);
            columnaPer.setMinWidth(300);   
            txta.setText(String.valueOf(a));
    }
    void actualizarAlimentacion(String seccion,String mes){
         int a=0;
        String titulos[] = {"Apelidos y Nombres","Total a Pagar","Pendiente","Fec. Vencimiento"};
        ClsNoDeuda moras = new ClsNoDeuda();
        ArrayList<ClsEntidadMora> mora = new ArrayList<ClsEntidadMora>();
         if(chbNivel.isSelected())
         {mora = moras.ListarMoraAlimentacionNivel(seccion,mes);
         }
         else{
        mora = moras.ListarMoraAlimentacion(seccion,mes);}
        Iterator iterator = mora.iterator();
        DefaultTableModel defaultTableModel =new DefaultTableModel(null,titulos);        
        String fila[] = new String[4];                
        while(iterator.hasNext()){
            ClsEntidadMora Mora = new ClsEntidadMora();
            Mora = (ClsEntidadMora) iterator.next();
            fila[0] = Mora.getAlumno();
            fila[1] = Mora.getTotal();            
            fila[2] = Mora.getPendiente();
            fila[3] = Mora.getVencimiento();            
            defaultTableModel.addRow(fila);                 
            a++;
        }       
        if(a==0){JOptionPane.showMessageDialog(this, "No se encontraron registros");}
        tblMora.setModel(defaultTableModel);        
        TableColumn  columnaPer =tblMora.getColumnModel().getColumn(0);
            columnaPer.setMinWidth(300); 
            txta.setText(String.valueOf(a));
    }
    void actualizarServicio(String seccion,String servicio){
         int a=0;
        String titulos[] = {"Apelidos y Nombres","Total a Pagar","Pendiente","Fec. Vencimiento"};
        ClsNoDeuda moras = new ClsNoDeuda();
        ArrayList<ClsEntidadMora> mora = new ArrayList<ClsEntidadMora>();
         if(chbNivel.isSelected())
         {mora = moras.ListarMoraServicioNivel(seccion,servicio);}
         else{
        mora = moras.ListarMoraServicio(seccion,servicio);}
        Iterator iterator = mora.iterator();
        DefaultTableModel defaultTableModel =new DefaultTableModel(null,titulos);        
        String fila[] = new String[4];                
        while(iterator.hasNext()){
            ClsEntidadMora Mora = new ClsEntidadMora();
            Mora = (ClsEntidadMora) iterator.next();
            fila[0] = Mora.getAlumno();
            fila[1] = Mora.getTotal();            
            fila[2] = Mora.getPendiente();
            fila[3] = Mora.getVencimiento();            
            defaultTableModel.addRow(fila);   
            a++;
        }         
        if(a==0){JOptionPane.showMessageDialog(this, "No se encontraron registros");}
        tblMora.setModel(defaultTableModel);        
        TableColumn  columnaPer =tblMora.getColumnModel().getColumn(0);
            columnaPer.setMinWidth(300);     
            txta.setText(String.valueOf(a));
    }
    void actualizarProducto(String seccion,String producto){
         int a=0;
        String titulos[] = {"Apelidos y Nombres","Total a Pagar","Pendiente","Fec. Vencimiento"};
        ClsNoDeuda moras = new ClsNoDeuda();
        ArrayList<ClsEntidadMora> mora = new ArrayList<ClsEntidadMora>();
        if(chbNivel.isSelected())
        {mora = moras.ListarMoraProductoNivel(seccion,producto);}
        else{
        mora = moras.ListarMoraProducto(seccion,producto);}
        Iterator iterator = mora.iterator();
        DefaultTableModel defaultTableModel =new DefaultTableModel(null,titulos);        
        String fila[] = new String[4];                
        while(iterator.hasNext()){
            ClsEntidadMora Mora = new ClsEntidadMora();
            Mora = (ClsEntidadMora) iterator.next();
            fila[0] = Mora.getAlumno();
            fila[1] = Mora.getTotal();            
            fila[2] = Mora.getPendiente();
            fila[3] = Mora.getVencimiento();            
            defaultTableModel.addRow(fila);
            a++;
        }   
        if(a==0){JOptionPane.showMessageDialog(this, "No se encontraron registros");}
        tblMora.setModel(defaultTableModel);        
        TableColumn  columnaPer =tblMora.getColumnModel().getColumn(0);
            columnaPer.setMinWidth(300);  
            txta.setText(String.valueOf(a));
    }
    void rellenaServicio()
    {
        ClsServicio servicios = new ClsServicio();
        ArrayList<ClsEntidadServicio> producto = servicios.ListarServicio();        
        dlm.removeAllElements();
        //lista.setModel(dlm);
        Iterator iterator = producto.iterator();            
        String fila[] = new String[6];
        while(iterator.hasNext()){
            ClsEntidadServicio Servicio = new ClsEntidadServicio();
            Servicio = (ClsEntidadServicio) iterator.next();
            fila[0] = Servicio.getId_Servicio();
            fila[1] = Servicio.getNombre_Servicio();           
            
           fila[2] = Servicio.getAnio_Servicio();
          
            if(fila[2].equals(cmbServicio.getSelectedItem().toString())){
            dlm.addElement(fila[1]);      
            }
        }     
        lista.setModel(dlm); 
    }
    void rellenaProducto()
    {
   
    ClsProducto productos = new ClsProducto();
    ArrayList<ClsEntidadProducto> producto = productos.ListarProducto();
    Iterator iterator = producto.iterator();
    dlm.removeAllElements();
    String fila[] = new String[6];
    while(iterator.hasNext())
    {
        ClsEntidadProducto Producto = new ClsEntidadProducto();
        Producto = (ClsEntidadProducto) iterator.next();
        fila[0] = Producto.getId_Producto();
        fila[1] = Producto.getNombre_Producto();
        fila[2] = Producto.getAnio_Producto();
        if(fila[2].equals(cmbServicio.getSelectedItem().toString())){
        dlm.addElement(fila[1]);                  
        }   
    }
    lista.setModel(dlm); 
    }    
    void cargaPeriodo(){
        ClsPeriodo periodos = new ClsPeriodo();
        ArrayList<ClsEntidadPeriodo> periodo =  periodos.ListarPeriodo();
        Iterator iterator = periodo.iterator();
        DefaultComboBoxModel DefaultComboBoxModel = new DefaultComboBoxModel();
        DefaultComboBoxModel.removeAllElements();
        cmbPeriodo.removeAllItems();
        
        String fila[] = new String[4];      
        while(iterator.hasNext())
        {
        ClsEntidadPeriodo Periodo = new ClsEntidadPeriodo();
        Periodo = (ClsEntidadPeriodo) iterator.next();        
        fila[0] = Periodo.getNombre_Periodo();        
        fila[2] = Periodo.getEstado_Periodo();        
        if(Periodo.getEstado_Periodo().equals("Activo")){        
        DefaultComboBoxModel.addElement(Periodo.getNombre_Periodo());        
        }        
        }
        cmbPeriodo.setModel(DefaultComboBoxModel);
        
    }
    void cargaPeriodo2(){
        ClsPeriodo periodos = new ClsPeriodo();
        ArrayList<ClsEntidadPeriodo> periodo =  periodos.ListarPeriodo();
        Iterator iterator = periodo.iterator();
        DefaultComboBoxModel DefaultComboBoxModel = new DefaultComboBoxModel();
        DefaultComboBoxModel.removeAllElements();
        
        cmbPeriodo1.removeAllItems();
        String fila[] = new String[4];      
        while(iterator.hasNext())
        {
        ClsEntidadPeriodo Periodo = new ClsEntidadPeriodo();
        Periodo = (ClsEntidadPeriodo) iterator.next();        
        fila[0] = Periodo.getNombre_Periodo();        
        fila[2] = Periodo.getEstado_Periodo();        
        if(Periodo.getEstado_Periodo().equals("Activo")){        
        DefaultComboBoxModel.addElement(Periodo.getNombre_Periodo());        
        }        
        }
        cmbPeriodo1.setModel(DefaultComboBoxModel);
        
    }
    void cargaSeccion()
    {                
        ClsSeccion secciones = new ClsSeccion();
        ArrayList<ClsEntidadSeccion> seccion =  secciones.ListarSeccion();
        Iterator it = seccion.iterator();
        DefaultComboBoxModel DefaultComboBoxModel = new DefaultComboBoxModel();
        DefaultComboBoxModel.removeAllElements();
        cmbSeccion.removeAllItems();
        String fila2[] = new String[5];
        
        while(it.hasNext())
        {
        ClsEntidadSeccion Seccion = new ClsEntidadSeccion();
        Seccion = (ClsEntidadSeccion) it.next();        
        fila2[0] = Seccion.getNombre_Seccion();
        fila2[1] = Seccion.getEstado_Periodo();
 
        fila2[3] = Seccion.getNombre_Periodo(); 
        
        if (fila2[3].equals(cmbPeriodo.getSelectedItem())){
              //System.out.println("El periodo es: "+fila2[3]);
              DefaultComboBoxModel.addElement(Seccion.getNombre_Seccion());
              }
        }    
        cmbSeccion.setModel(DefaultComboBoxModel);
    
    }
    void cargaMeses()
    {
    dlm.removeAllElements();
       dlm.addElement("ENERO");
       dlm.addElement("FEBRERO");
       dlm.addElement("MARZO");
       dlm.addElement("ABRIL");
       dlm.addElement("MAYO");
       dlm.addElement("JUNIO");
       dlm.addElement("JULIO");
       dlm.addElement("AGOSTO");
       dlm.addElement("SEPTIEMBRE");
       dlm.addElement("OCTUBRE");
       dlm.addElement("NOVIEMBRE");
       dlm.addElement("DICIEMBRE");
       lista.setModel(dlm);
    
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReporte;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnVer;
    private javax.swing.JCheckBox chbNivel;
    private javax.swing.JComboBox cmbPeriodo;
    private javax.swing.JComboBox cmbPeriodo1;
    private javax.swing.JComboBox cmbSeccion;
    private javax.swing.JComboBox cmbServicio;
    private javax.swing.ButtonGroup grupo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList lista;
    private javax.swing.JRadioButton raAlimentacion;
    private javax.swing.JRadioButton raMatricula;
    private javax.swing.JRadioButton raMensualidad;
    private javax.swing.JRadioButton raProductos;
    private javax.swing.JRadioButton raServicios;
    private javax.swing.JTable tblMora;
    private javax.swing.JTextField txta;
    // End of variables declaration//GEN-END:variables
}
