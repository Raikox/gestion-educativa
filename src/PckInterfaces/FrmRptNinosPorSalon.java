/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckInterfaces;

import PckConexion.ClsConexion;
import PckEntidad.ClsEntidadNivel;
import PckEntidad.ClsEntidadPeriodo;
import PckEntidad.ClsEntidadPorSalon;
import PckEntidad.ClsEntidadSeccion;
import PckNegocio.ClsNivel;
import PckNegocio.ClsPeriodo;
import PckNegocio.ClsPorSalon;
import PckNegocio.ClsSeccion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author kmv
 */
public class FrmRptNinosPorSalon extends javax.swing.JInternalFrame {
 
    String per;
    String id[] = new String[1000];
    static ResultSet rs  = null;
    private static final String LOGOTIPO= "/PckImages/solr.jpg";
    String codPeriodo="";
    String nivel;
    ArrayList<String> codigoNivel = new ArrayList();
    ArrayList<String> codigoPeriodo = new ArrayList();
    ArrayList<String> codigoSeccion = new ArrayList();
    Connection con = null;
    public FrmRptNinosPorSalon() 
    {
        initComponents();
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

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblNivel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblSeccion = new javax.swing.JLabel();
        cmbSeccion = new javax.swing.JComboBox();
        cmbPeriodo = new javax.swing.JComboBox();
        cmbNivel = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        chkAlimento = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel2.setText("jLabel2");

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setTitle("Listado de ni??os por salon");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Droid Sans", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 153));
        jLabel3.setText("REPORTE LISTADO DE NI??OS");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccione aula"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNivel.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        lblNivel.setText("Nivel:");
        jPanel2.add(lblNivel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, 20));

        jLabel4.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel4.setText("Periodo:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, 20));

        lblSeccion.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        lblSeccion.setText("Aula:");
        jPanel2.add(lblSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, 20));

        cmbSeccion.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jPanel2.add(cmbSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 180, -1));

        cmbPeriodo.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbPeriodo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbPeriodo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPeriodoItemStateChanged(evt);
            }
        });
        jPanel2.add(cmbPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 180, -1));

        cmbNivel.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbNivel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbNivel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbNivelItemStateChanged(evt);
            }
        });
        jPanel2.add(cmbNivel, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 180, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 310, 170));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel1.setText("Con servicio de Alimentaci??n:");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, 20));

        chkAlimento.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.add(chkAlimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, -1, -1));

        jButton1.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/file-document.png"))); // NOI18N
        jButton1.setText("<html>\n<p>Generar</p>\n<p align=center>Reporte</p>\n</html>");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 120, 50));

        jButton2.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/ic_exit_to_app_black_24dp.png"))); // NOI18N
        jButton2.setText("Cerrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 110, 50));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, 310, 150));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String  url ="/Reportes/RptNinosAlimento.jasper";
        String  url2 ="/Reportes/RptNinosPorSalon.jasper";
        String  url3 ="/Reportes/RptNinosPorSalon.jrxml";
        if (chkAlimento.isSelected())
        {
            Map p = new HashMap();
            String criterio = codigoSeccion.get(cmbSeccion.getSelectedIndex());
            p.put("pbusqueda", criterio);
            p.put("logo", this.getClass().getResourceAsStream(LOGOTIPO));    
            JasperPrint print;
            try
            {
                print = JasperFillManager.fillReport(System.getProperty("user.dir")+url,p ,con);
                JasperViewer view = new JasperViewer(print,false);
                view.setTitle("Reporte Ni??os Alimento");
                view.setVisible(true);
            }
            catch(JRException ex)
            {
                JOptionPane.showMessageDialog(this, ex);
                ex.printStackTrace();
            }
        }
        
        else
        {
            Map p = new HashMap();
            String criterio = codigoSeccion.get(cmbSeccion.getSelectedIndex());
            int cont1 = 0;
            int cont2 = 0;
            ClsPorSalon salones = new ClsPorSalon();
            con = ClsConexion.getConection();
            ArrayList<ClsEntidadPorSalon> salon = salones.ListarNinosPorSalon(criterio,con);        
            for(ClsEntidadPorSalon i: salon)
            {
                System.out.println(i);
                if (i.Comida.equals("SI"))
                {
                cont1++;
                }
                else{
                cont2++;
                }
            }
            ClsConexion.closeConnection(con);
            String nombresalon = cmbSeccion.getSelectedItem().toString();            
            p.put("logo", this.getClass().getResourceAsStream(LOGOTIPO));
            p.put("comen", String.valueOf(cont1));
            p.put("nocomen", String.valueOf(cont2));
            p.put("nombresalon", nombresalon);
            JasperPrint print;
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(salon);
            try{
              
                JasperCompileManager.compileReportToFile(System.getProperty("user.dir")+url3, System.getProperty("user.dir")+url2);              
                print = JasperFillManager.fillReport(System.getProperty("user.dir")+url2, p,ds);
                JasperViewer view = new JasperViewer(print,false);
                view.setTitle("Reporte Ni??os por Salon");
                view.setVisible(true);
            }
            catch(JRException ex)
            {
                JOptionPane.showMessageDialog(this, ex);
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

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

    private void cmbPeriodoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPeriodoItemStateChanged
        if(cmbPeriodo.getSelectedIndex() != -1)
        {
            System.out.println("Periodo: "+cmbPeriodo.getSelectedItem()+" ID: "+codigoPeriodo.get(cmbPeriodo.getSelectedIndex()));
            cargaComboSeccion();
        }
    }//GEN-LAST:event_cmbPeriodoItemStateChanged
  
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
        String fila[] = new String[4];     
        while(iterator.hasNext())
        {
        ClsEntidadPeriodo Periodo = new ClsEntidadPeriodo();
        Periodo = (ClsEntidadPeriodo) iterator.next();               
        fila[0] = Periodo.getNombre_Periodo();
        fila[1] = Periodo.getEstado_Periodo();        
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
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chkAlimento;
    private javax.swing.JComboBox cmbNivel;
    private javax.swing.JComboBox cmbPeriodo;
    private javax.swing.JComboBox cmbSeccion;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNivel;
    private javax.swing.JLabel lblSeccion;
    // End of variables declaration//GEN-END:variables
}
