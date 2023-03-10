/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PckInterfaces;


import PckConexion.ClsConexion;
import PckEntidad.ClsRequerimiento;
import static PckInterfaces.FrmPrincipal.dskpPrincipal;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mpsModel.AlmacenModelo;
import mpsModel.MatriculaModelo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author Kevin
 */
public class AlmacenRequerimiento extends javax.swing.JPanel {

    MatriculaModelo matriculaModelo = new MatriculaModelo();
    AlmacenModelo almacenModelo = new AlmacenModelo();
    ArrayList<ClsRequerimiento> arrayRequerimiento = new ArrayList<>();
    ArrayList<Object> arrayIdAula = new ArrayList<>();
    String idRequerimiento;
    
    /**
     * Creates new form AlmacenAdministracionn
     */
    public AlmacenRequerimiento() {
        initComponents();
        
        txtDocente.setText(FrmPrincipal.usuarioLogeado);
        arrayIdAula = matriculaModelo.MostrarComboAulasDocente(
                    cmbAula, 
                    Integer.parseInt(FrmPrincipal.idPersonalLogeado)
                );
          
        MostrarRequerimientosAlmacen();
          
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
        jPanel1 = new javax.swing.JPanel();
        pnlIzquierda = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        pnlCenter = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRequerimiento = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDocente = new javax.swing.JTextField();
        cmbAula = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        btnReporte = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(680, 597));
        setPreferredSize(new java.awt.Dimension(680, 597));
        setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(237, 240, 242));
        jPanel4.setPreferredSize(new java.awt.Dimension(911, 50));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel5.setBackground(new java.awt.Color(237, 240, 242));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Droid Sans", 1, 12)); // NOI18N
        jLabel1.setText("REQUERIMIENTOS ALMACEN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel5.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel4.add(jPanel5, gridBagConstraints);

        add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(237, 240, 242));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        pnlIzquierda.setBackground(new java.awt.Color(255, 255, 255));
        pnlIzquierda.setMinimumSize(new java.awt.Dimension(200, 390));
        pnlIzquierda.setPreferredSize(new java.awt.Dimension(400, 10));
        pnlIzquierda.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.BorderLayout());

        pnlCenter.setBackground(new java.awt.Color(255, 255, 255));
        pnlCenter.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(23, 250));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 280));

        tblRequerimiento.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        tblRequerimiento.setModel(new javax.swing.table.DefaultTableModel(
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
        tblRequerimiento.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblRequerimiento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRequerimientoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblRequerimiento);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 20);
        pnlCenter.add(jScrollPane1, gridBagConstraints);

        jSeparator1.setForeground(new java.awt.Color(224, 224, 224));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        pnlCenter.add(jSeparator1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel2.setText("Docente.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 20);
        pnlCenter.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel3.setText("Aula.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 20);
        pnlCenter.add(jLabel3, gridBagConstraints);

        txtDocente.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtDocente.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDocente.setDoubleBuffered(true);
        txtDocente.setEnabled(false);
        txtDocente.setMinimumSize(new java.awt.Dimension(250, 23));
        txtDocente.setPreferredSize(new java.awt.Dimension(280, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 20);
        pnlCenter.add(txtDocente, gridBagConstraints);

        cmbAula.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbAula.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbAula.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbAulaItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 20);
        pnlCenter.add(cmbAula, gridBagConstraints);

        jPanel2.add(pnlCenter, java.awt.BorderLayout.CENTER);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new java.awt.GridBagLayout());

        btnReporte.setFont(new java.awt.Font("Droid Sans", 1, 12)); // NOI18N
        btnReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIconos/ic_assignment_black_24dp.png"))); // NOI18N
        btnReporte.setText("GENERAR REPORTE");
        btnReporte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReporte.setEnabled(false);
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 20);
        jPanel6.add(btnReporte, gridBagConstraints);

        btnNuevo.setFont(new java.awt.Font("Droid Sans", 1, 12)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIconos/ic_add_black_24dp.png"))); // NOI18N
        btnNuevo.setText("NUEVO REQUERIMIENTO");
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 20);
        jPanel6.add(btnNuevo, gridBagConstraints);

        jPanel2.add(jPanel6, java.awt.BorderLayout.PAGE_END);

        pnlIzquierda.add(jPanel2, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        jPanel1.add(pnlIzquierda, gridBagConstraints);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        LlenarArray();
        Map mapa; 
        ResultSet rsResultado;
        Connection con;
        ResultSet rs;
        PreparedStatement st;
        ResultSet rs2;
        PreparedStatement st2;
        String aula = "";
        String tema = "";
        String estado = "";
        String numero = "";
        String fecha = "";
        
        JasperPrint print;
        JRBeanCollectionDataSource ds;
        JasperViewer view;
        String urlJRXML="/Reportes/RptRequerimientoAlmacen.jrxml";
        String urlJASPER="/Reportes/RptRequerimientoAlmacen.jasper";        
        con = ClsConexion.getConection();
        mapa = new HashMap();        
        
        try 
        {            
            
            st = con.prepareStatement("select * from mps_requerimiento where requerimiento_id = ?");
            st.setString(1, idRequerimiento);
            rs = st.executeQuery();
            
            while(rs.next())
            {
                numero = rs.getString("requerimiento_numero");
                fecha = rs.getString("requerimiento_fecha");
                tema = rs.getString("requerimiento_tema");
                estado =  rs.getString("requerimiento_estado");
                
            }
            
            st2 = con.prepareStatement("select s.nombre_seccion from mat_seccion as s \n" 
                    + "inner join mps_requerimiento as r on s.id_seccion = r.id_seccion\n" 
                    + "where r.requerimiento_id = ? ");
            st2.setString(1, idRequerimiento);
            rs2 = st2.executeQuery();
            
            while(rs2.next())
            {
                aula = rs2.getString("nombre_seccion");
                
            }            
            
            mapa.put("numero", numero); 
            mapa.put("aula", aula);
            mapa.put("tema", tema);
            mapa.put("estado", estado);
            mapa.put("fecha", fecha);
            
            System.out.println("req:"+ numero + " "+aula+ " "+tema+ " "+estado);
            for(ClsRequerimiento s : arrayRequerimiento)
            {
                System.out.println("=> "+s.getItem() + " "+ s.getUnidad()+ " "+ s.getCantidad()+ " "+ s.getSustento());
                
            }
            
            ds = new JRBeanCollectionDataSource(arrayRequerimiento);
            JasperCompileManager.compileReportToFile(System.getProperty("user.dir")+ urlJRXML,
                            System.getProperty("user.dir")+urlJASPER);
            print = JasperFillManager.fillReport(System.getProperty("user.dir")+urlJASPER, mapa,ds);
            view = new JasperViewer(print,false);
            view.setTitle("Reporte Requerimiento Aula");
            view.setVisible(true);
            
        }
        catch (SQLException | JRException ex) 
        {
            Logger.getLogger(FrmRptRequerimientoAula.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnReporteActionPerformed
    
    private void tblRequerimientoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRequerimientoMouseClicked
        
        int column = tblRequerimiento.getSelectedColumn();
        int row = tblRequerimiento.getSelectedRow();
        
        if(row != -1)
        {
            Object value = tblRequerimiento.getValueAt(row, column);
            btnReporte.setEnabled(true);
            if(value instanceof JButton) 
            {
                ((JButton)value).doClick();
                JButton boton = (JButton) value;
                if(boton.getName().equals("btnEditar")) //BOTON EDITAR
                {
                    DefaultTableModel dtmRequerimiento = (DefaultTableModel) tblRequerimiento.getModel();
                    idRequerimiento = String.valueOf(dtmRequerimiento.getValueAt(row, 0));
                    String estado = String.valueOf(dtmRequerimiento.getValueAt(row, 5));
                    if(estado.equals("PENDIENTE"))
                    {
                        AbrirAlmacenRequerimientoEditar(idRequerimiento);
                    }
                    else
                    {
                        JLabel label = new JLabel("El requerimiento se encuentra aprobado, no se puede editar.");
                        label.setFont(new Font("DroidSans", 0, 14));
                        JOptionPane.showMessageDialog(this, label
                                , "Requerimiento editado", JOptionPane.INFORMATION_MESSAGE);
                    }
                    
                }                
            }
        }
    }//GEN-LAST:event_tblRequerimientoMouseClicked

    private void LlenarArray()
    {
        arrayRequerimiento.clear();
        Connection con = ClsConexion.getConection();
        ResultSet rs;
        PreparedStatement st;
        int row = tblRequerimiento.getSelectedRow();
        
        if(row != -1)
        {
        DefaultTableModel dtmRequerimiento = (DefaultTableModel) tblRequerimiento.getModel();
        idRequerimiento = String.valueOf(dtmRequerimiento.getValueAt(row, 0));
        }
        try 
        {
             st = con.prepareStatement("select ri.ritem_id, ri.item_id,ri.ritem_cantidad,"
                    + "ai.item_nombre,ai.item_medida,ri.ritem_sustento,ri.requerimiento_id, \n"
                    + "ai.item_descripcion \n" 
                    + "from mps_requerimiento_item as ri\n" 
                    + "inner join mps_almacen_item as ai on ri.item_id = ai.item_id\n" 
                    + "where ri.requerimiento_id = ? order by ai.item_nombre");
            st.setString(1, idRequerimiento);
            rs = st.executeQuery();
            
            while(rs.next())
            {
                arrayRequerimiento.add(new ClsRequerimiento
                    (
                        rs.getString("ritem_id"),                            
                        rs.getString("ritem_cantidad"),
                        rs.getString("item_nombre"),
                        rs.getString("item_id"),
                        rs.getString("ritem_sustento"),
                        rs.getString("item_medida"),
                        rs.getString("item_descripcion")
                    ));
            }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(AlmacenRequerimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    
    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        
        int idAux = (int) arrayIdAula.get(cmbAula.getSelectedIndex());
        PckInterfaces.AlmacenRequerimientoNuevo almacenRequerimientoNuevo= new PckInterfaces.AlmacenRequerimientoNuevo(idAux);
        int x = dskpPrincipal.getWidth();
        int y = dskpPrincipal.getHeight(); 
        dskpPrincipal.removeAll();
        dskpPrincipal.updateUI();
        if (almacenRequerimientoNuevo.isShowing())
        {
            almacenRequerimientoNuevo.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(almacenRequerimientoNuevo);
            almacenRequerimientoNuevo.setBounds(0,0,x, y);
            almacenRequerimientoNuevo.FormularioPadre(this);            
            almacenRequerimientoNuevo.setVisible(true);            
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void cmbAulaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbAulaItemStateChanged
        MostrarRequerimientosAlmacen();
    }//GEN-LAST:event_cmbAulaItemStateChanged

    public void MostrarRequerimientosAlmacen()
    {
        if(cmbAula.getSelectedIndex() != -1)
        {
            int idAula = (int) arrayIdAula.get(cmbAula.getSelectedIndex());
            almacenModelo.MostrarTablaAlmacenRequerimientoBotones(tblRequerimiento, idAula, false);
        }
    }

    
    private void AbrirAlmacenRequerimientoEditar(String idRequerimiento)
    {
        int idAux = (int) arrayIdAula.get(cmbAula.getSelectedIndex());
        PckInterfaces.AlmacenRequerimientoEditar almacenRequerimientoEditar= new PckInterfaces.AlmacenRequerimientoEditar(idAux,idRequerimiento);
        int x = dskpPrincipal.getWidth();
        int y = dskpPrincipal.getHeight(); 
        dskpPrincipal.removeAll();
        dskpPrincipal.updateUI();
        if (almacenRequerimientoEditar.isShowing())
        {
            almacenRequerimientoEditar.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(almacenRequerimientoEditar);
            almacenRequerimientoEditar.setBounds(0,0,x, y);
            almacenRequerimientoEditar.FormularioPadre(this);            
            almacenRequerimientoEditar.setVisible(true);            
        }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnReporte;
    private javax.swing.JComboBox<String> cmbAula;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlIzquierda;
    private javax.swing.JTable tblRequerimiento;
    private javax.swing.JTextField txtDocente;
    // End of variables declaration//GEN-END:variables
}
