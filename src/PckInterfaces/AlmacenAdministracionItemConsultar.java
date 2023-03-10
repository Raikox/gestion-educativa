/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PckInterfaces;

import PckConexion.ClsConexion;
import PckRenderTabla.GeneralRender;
import PckRenderTabla.GeneralRender.MiRender;
import static java.awt.image.ImageObserver.WIDTH;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import mpsModel.MatriculaModelo;
import prymatricula.ClsMisc;

/**
 *
 * @author Kevin
 */
public class AlmacenAdministracionItemConsultar extends javax.swing.JPanel {

    ArrayList<Object> arrayIdAula = new ArrayList<>();
    ArrayList<String> arrayAlmacenId = new ArrayList();
    List<Object> listIdPeriodo = new ArrayList<>();
    
    MatriculaModelo matriculaModelo = new MatriculaModelo();
    boolean Consumo = true;
    /**
     * Creates new form AlmacenItemConsultar
     */
    public AlmacenAdministracionItemConsultar() {
        initComponents();
        
        grupo.add(rbnConsumo);
        grupo.add(rbnInventario);
        
        ActualizarTablaPre();
        
        listIdPeriodo = matriculaModelo.MostrarComboPeriodos(cmbPeriodo);   
        
        
        if(!listIdPeriodo.isEmpty()) {
            int idPeriodo = (int) listIdPeriodo.get(cmbPeriodo.getSelectedIndex());
            
            arrayIdAula = matriculaModelo.MostrarComboAulasPeriodo(
                    cmbAula,idPeriodo
                );
        
            if(!arrayIdAula.isEmpty())
            {
                CargarComboAlmacen();
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

        grupo = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        pnlIzquierda = new javax.swing.JPanel();
        pnltop1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        pnlIbot = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        cmbAlmacen = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        cmbAula = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        rbnConsumo = new javax.swing.JRadioButton();
        rbnInventario = new javax.swing.JRadioButton();
        cmbPeriodo = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnConsultar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        pnlDerecha = new javax.swing.JPanel();
        pnltop = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        pnlbot = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblItems = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(237, 237, 237));
        jPanel4.setPreferredSize(new java.awt.Dimension(911, 50));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel5.setBackground(new java.awt.Color(237, 240, 242));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Droid Sans", 1, 12)); // NOI18N
        jLabel1.setText("CONSULTAR ITEMS ALMACEN");
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

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setBorder(null);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        pnlIzquierda.setBackground(new java.awt.Color(255, 255, 255));
        pnlIzquierda.setMinimumSize(new java.awt.Dimension(150, 390));
        pnlIzquierda.setPreferredSize(new java.awt.Dimension(150, 10));
        pnlIzquierda.setLayout(new java.awt.BorderLayout());

        pnltop1.setBackground(new java.awt.Color(255, 255, 255));
        pnltop1.setMinimumSize(new java.awt.Dimension(100, 58));
        pnltop1.setPreferredSize(new java.awt.Dimension(200, 60));
        pnltop1.setLayout(new java.awt.GridBagLayout());

        jLabel8.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel8.setText("SELECCIONE AULA");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 0);
        pnltop1.add(jLabel8, gridBagConstraints);

        jSeparator3.setForeground(new java.awt.Color(224, 224, 224));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 269;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnltop1.add(jSeparator3, gridBagConstraints);

        pnlIzquierda.add(pnltop1, java.awt.BorderLayout.PAGE_START);

        pnlIbot.setBackground(new java.awt.Color(255, 255, 255));
        pnlIbot.setMinimumSize(new java.awt.Dimension(100, 104));
        pnlIbot.setPreferredSize(new java.awt.Dimension(200, 104));
        pnlIbot.setLayout(new java.awt.GridBagLayout());

        jLabel11.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel11.setText("Tipo.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        pnlIbot.add(jLabel11, gridBagConstraints);

        cmbAlmacen.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbAlmacen.setMinimumSize(new java.awt.Dimension(200, 20));
        cmbAlmacen.setPreferredSize(new java.awt.Dimension(300, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 20);
        pnlIbot.add(cmbAlmacen, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel12.setText("Aula.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        pnlIbot.add(jLabel12, gridBagConstraints);

        cmbAula.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbAula.setMinimumSize(new java.awt.Dimension(200, 20));
        cmbAula.setPreferredSize(new java.awt.Dimension(200, 20));
        cmbAula.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbAulaItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 20);
        pnlIbot.add(cmbAula, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel13.setText("Almacen.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        pnlIbot.add(jLabel13, gridBagConstraints);

        rbnConsumo.setBackground(new java.awt.Color(255, 255, 255));
        rbnConsumo.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        rbnConsumo.setSelected(true);
        rbnConsumo.setText("DE CONSUMO");
        rbnConsumo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbnConsumoItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        pnlIbot.add(rbnConsumo, gridBagConstraints);

        rbnInventario.setBackground(new java.awt.Color(255, 255, 255));
        rbnInventario.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        rbnInventario.setText("INVENTARIO");
        rbnInventario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbnInventarioItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        pnlIbot.add(rbnInventario, gridBagConstraints);

        cmbPeriodo.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbPeriodo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbPeriodo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPeriodoItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 20);
        pnlIbot.add(cmbPeriodo, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel9.setText("Periodo.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        pnlIbot.add(jLabel9, gridBagConstraints);

        pnlIzquierda.add(pnlIbot, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMinimumSize(new java.awt.Dimension(100, 33));
        jPanel2.setPreferredSize(new java.awt.Dimension(200, 100));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        btnConsultar.setFont(new java.awt.Font("Droid Sans", 1, 12)); // NOI18N
        btnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIconos/ic_search.png"))); // NOI18N
        btnConsultar.setText("CONSULTAR ITEMS");
        btnConsultar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 20);
        jPanel2.add(btnConsultar, gridBagConstraints);

        jSeparator1.setForeground(new java.awt.Color(224, 224, 224));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jSeparator1, gridBagConstraints);

        pnlIzquierda.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(pnlIzquierda, gridBagConstraints);

        pnlDerecha.setBackground(new java.awt.Color(255, 255, 255));
        pnlDerecha.setMinimumSize(new java.awt.Dimension(200, 414));
        pnlDerecha.setPreferredSize(new java.awt.Dimension(300, 400));
        pnlDerecha.setLayout(new java.awt.BorderLayout());

        pnltop.setBackground(new java.awt.Color(255, 255, 255));
        pnltop.setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel3.setText("LISTADO ITEMS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 0);
        pnltop.add(jLabel3, gridBagConstraints);

        jSeparator2.setForeground(new java.awt.Color(224, 224, 224));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 269;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnltop.add(jSeparator2, gridBagConstraints);

        pnlDerecha.add(pnltop, java.awt.BorderLayout.PAGE_START);

        pnlbot.setBackground(new java.awt.Color(255, 255, 255));
        pnlbot.setLayout(new java.awt.GridBagLayout());

        tblItems.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        tblItems.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblItems);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 20);
        pnlbot.add(jScrollPane1, gridBagConstraints);

        pnlDerecha.add(pnlbot, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel3.add(pnlDerecha, gridBagConstraints);

        jScrollPane3.setViewportView(jPanel3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        jPanel1.add(jScrollPane3, gridBagConstraints);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed

       if(cmbAula.getSelectedIndex() != -1)
        {
            String idAlmacen = arrayAlmacenId.get(cmbAlmacen.getSelectedIndex());
            ActualizarTabla(idAlmacen);
        }

    }//GEN-LAST:event_btnConsultarActionPerformed

    private void cmbAulaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbAulaItemStateChanged
        CargarComboAlmacen();
    }//GEN-LAST:event_cmbAulaItemStateChanged

    private void rbnConsumoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbnConsumoItemStateChanged
        Consumo = true;


    }//GEN-LAST:event_rbnConsumoItemStateChanged

    private void rbnInventarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbnInventarioItemStateChanged
        Consumo = false;

    }//GEN-LAST:event_rbnInventarioItemStateChanged

    private void cmbPeriodoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPeriodoItemStateChanged
        if(cmbPeriodo.getSelectedIndex() != -1)
        {
            int idPeriodo = (int) listIdPeriodo.get(cmbPeriodo.getSelectedIndex());
            
            arrayIdAula = matriculaModelo.MostrarComboAulasPeriodo(
                    cmbAula,idPeriodo
                );
        
            if(!arrayIdAula.isEmpty())
            {
                CargarComboAlmacen();
            }
        }
    }//GEN-LAST:event_cmbPeriodoItemStateChanged

    private void ActualizarTablaPre()
    {
        String titulos[] = {"ID","ITEM","U. MEDIDA","STOCK","DESCRIPCION"};
        DefaultTableModel dtmRequerimiento = new DefaultTableModel(null,titulos) {        
            @Override
            public boolean isCellEditable(int rowIndex,int columnIndex){return false;}               
        };   
        tblItems.setModel(dtmRequerimiento);
        tblItems.setRowHeight(28);
       
        TableCellRenderer tcr =  tblItems.getTableHeader().getDefaultRenderer();
        ClsMisc.miRender miRender = new ClsMisc.miRender(tcr);
        miRender.getTableCellRendererComponent(tblItems, null, true, true, WIDTH, 0);
        
        tblItems.removeColumn(tblItems.getColumnModel().getColumn(0));
        TableColumn colItem = tblItems.getColumnModel().getColumn(0);
        colItem.setPreferredWidth(170);
        TableColumn colMedida = tblItems.getColumnModel().getColumn(0);
        colMedida.setPreferredWidth(90);
        TableColumn colStock = tblItems.getColumnModel().getColumn(2);
        colStock.setPreferredWidth(40);        
        TableColumn colDescripcion = tblItems.getColumnModel().getColumn(3);
        colDescripcion.setPreferredWidth(250);
    }
    
    private void ActualizarTabla(String idAlmacen)
    {
        String titulos[] = {"ID ITEM","ID ALMACEN","DESCRIPCION",
            "N??","NOMBRE","U. MEDIDA","U. ASIGNADAS","U. UTILIZADAS","U. RESTANTES","DESCRIPCION"};
        Object fila[];
        DefaultTableModel dtmRequerimiento;
        fila = new Object[11];
        dtmRequerimiento = new DefaultTableModel(null,titulos) 
        {        
            @Override
            public boolean isCellEditable(int rowIndex,int columnIndex){return false;}               
        };        
        
        int count = 1;
        int itemId;
        int unidadesAsignadas;
        int unidadesUtilizadas;
        int unidadesRestantes;
                
        Connection con = ClsConexion.getConection();
        ResultSet rsItem;
        PreparedStatement st;        
        String sql; 
        try 
        {
            
            if(Consumo)
            {
                sql = "select * from mps_almacen_item where almacen_id = ? and item_tipo = 'DE CONSUMO' "
                        + "order by item_nombre ";
            }
            else
            {
                sql = "select * from mps_almacen_item where almacen_id = ? and item_tipo = 'INVENTARIO'"
                        + "order by item_nombre";
            }
            
            st = con.prepareStatement(sql);
            st.setString(1, idAlmacen);
            rsItem = st.executeQuery();
            while(rsItem.next())
            {
                itemId = Integer.parseInt(rsItem.getString("item_id"));
                unidadesAsignadas = Integer.parseInt(rsItem.getString("item_stock"));
                unidadesUtilizadas = ObtenerUnidadesUtilizadas(itemId);
                unidadesRestantes = unidadesAsignadas - unidadesUtilizadas;
                fila[0] = itemId;
                fila[1] = rsItem.getString("almacen_id");                
                fila[2] = rsItem.getString("item_descripcion");                
                fila[3] = count;
                fila[4] = " "+rsItem.getString("item_nombre");
                fila[5] = rsItem.getString("item_medida");
                fila[6] = unidadesAsignadas; //asignadas
                fila[7] = unidadesUtilizadas; //utilizadas
                fila[8] = unidadesRestantes; //restantes
                fila[9] = rsItem.getString("item_descripcion");
                
                dtmRequerimiento.addRow(fila);   
                count++;
            }
            
            tblItems.setModel(dtmRequerimiento);
            tblItems.setRowHeight(20);
            
            tblItems.removeColumn(tblItems.getColumnModel().getColumn(0));
            tblItems.removeColumn(tblItems.getColumnModel().getColumn(0));
            tblItems.removeColumn(tblItems.getColumnModel().getColumn(0));
            
        
            TableColumn colOrden = tblItems.getColumnModel().getColumn(0);colOrden.setPreferredWidth(35);
            TableColumn colItem = tblItems.getColumnModel().getColumn(1);colItem.setPreferredWidth(230);
            TableColumn colMedida = tblItems.getColumnModel().getColumn(2);colMedida.setPreferredWidth(90);
            TableColumn colAsignadas = tblItems.getColumnModel().getColumn(3);colAsignadas.setPreferredWidth(90);
            TableColumn colUtilizadas = tblItems.getColumnModel().getColumn(4);colUtilizadas.setPreferredWidth(90);
            TableColumn colRestantes = tblItems.getColumnModel().getColumn(5);colRestantes.setPreferredWidth(90);
            TableColumn colDescripcion = tblItems.getColumnModel().getColumn(6);colDescripcion.setPreferredWidth(200);
            
            TableCellRenderer tcr =  tblItems.getTableHeader().getDefaultRenderer();
            ClsMisc.miRender miRender = new ClsMisc.miRender(tcr);   
            
            miRender.getTableCellRendererComponent(tblItems, fila, true, true, WIDTH, 0);
            miRender.getTableCellRendererComponent(tblItems, fila, true, true, WIDTH, 2);
            miRender.getTableCellRendererComponent(tblItems, fila, true, true, WIDTH, 3);
            miRender.getTableCellRendererComponent(tblItems, fila, true, true, WIDTH, 4);
            miRender.getTableCellRendererComponent(tblItems, fila, true, true, WIDTH, 5);
            
            //tblItems.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            
            ClsConexion.closeConnection(con);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(AlmacenAdministracionItemConsultar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private int ObtenerUnidadesUtilizadas(int xIdItem)
    {
        Connection con = ClsConexion.getConection();
        ResultSet rsItem;
        PreparedStatement st;
        int cantidad=0;
        try 
        {
            st = con.prepareStatement("select ai.item_nombre, ai.item_id, sum(ri.ritem_cantidad) as 'cantidad_suma'\n" +
                            "from mps_requerimiento_item as ri \n" +
                            "inner join mps_almacen_item as ai on ri.item_id = ai.item_id\n" +
                            "where ri.item_id = ?");
            st.setInt(1, xIdItem);
            rsItem = st.executeQuery();
            
            while(rsItem.next())
            {
                cantidad = rsItem.getInt("cantidad_suma");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AlmacenAdministracionItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        ClsConexion.closeConnection(con);
        return cantidad;
    }
    
    private void CargarComboAlmacen()
    {
        arrayAlmacenId.clear();
        Connection con = ClsConexion.getConection();
        ResultSet rsResultado;
        DefaultComboBoxModel cbm = new DefaultComboBoxModel();
        cbm.removeAllElements();
        cmbAlmacen.removeAllItems();
        String fila[];
        fila = new String[5];
        try 
        {
            PreparedStatement st = con.prepareStatement("select * from mps_almacen as a where a.id_seccion = ?");
            st.setString(1, String.valueOf(arrayIdAula.get(cmbAula.getSelectedIndex())));
            rsResultado = st.executeQuery();
            
            while(rsResultado.next())
            {
                fila[0] = rsResultado.getString("almacen_id");
                fila[1] = rsResultado.getString("almacen_nombre");   
                
                arrayAlmacenId.add(fila[0]);
                cbm.addElement(fila[1]);
            }
            cmbAlmacen.setModel(cbm);
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(AlmacenAdministracionItemConsultar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultar;
    private javax.swing.JComboBox<String> cmbAlmacen;
    private javax.swing.JComboBox<String> cmbAula;
    private javax.swing.JComboBox<String> cmbPeriodo;
    private javax.swing.ButtonGroup grupo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPanel pnlDerecha;
    private javax.swing.JPanel pnlIbot;
    private javax.swing.JPanel pnlIzquierda;
    private javax.swing.JPanel pnlbot;
    private javax.swing.JPanel pnltop;
    private javax.swing.JPanel pnltop1;
    private javax.swing.JRadioButton rbnConsumo;
    private javax.swing.JRadioButton rbnInventario;
    private javax.swing.JTable tblItems;
    // End of variables declaration//GEN-END:variables
}
