/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PckInterfaces;



import PckConexion.ClsConexion;
import PckEntidad.ClsRequerimiento;

import java.awt.Font;
import java.awt.Window;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import mpsModel.AlmacenModelo;
import prymatricula.ClsMisc;

/**
 *
 * @author Kevin
 */
public class AlmacenAdministracionRequerimientoEvaluar extends javax.swing.JPanel {
    
    AlmacenModelo almacenModelo = new AlmacenModelo();    
    ArrayList<Object> arrayIdAlmacen = new ArrayList<>();
    public AlmacenAdministracionRequerimiento almacenRequerimiento;
    int _IdAula;
    ArrayList<ClsRequerimiento> arrayPedido = new ArrayList<>();    
    ArrayList<ClsRequerimiento> arrayPedidoLast = new ArrayList<>();
    int rowPedido;
    String _IdRequerimiento;    
    public String _Cantidad,_Sustento,_StockRestante;
    public boolean guardar = false;
    private static final Font FUENTE_DROID = new Font("Droid Sans", Font.PLAIN, 14);
    DocumentFilter filtroMayuscula = new ClsMisc.UppercaseDocumentFilter();    
    boolean Consumo = true;
    /**
     * Creates new form AlmacenAdministracionn
     * @param _IdAula
     * @param _IdRequerimiento
     */
    public AlmacenAdministracionRequerimientoEvaluar(int _IdAula,String _IdRequerimiento) {
        this._IdAula = _IdAula;
        this._IdRequerimiento = _IdRequerimiento;
        initComponents();
        ValidarCampos();
        CargarDatosRequerimiento();
        CargarDatosPedido();
        
        srpPrincipal.getVerticalScrollBar().setUnitIncrement(16);
        
        arrayIdAlmacen = almacenModelo.MostarComboAlmacenesAula(cmbAlmacen, _IdAula);       
    
        if(cmbAlmacen.getSelectedIndex() != -1)
        {
            int aux = (int) arrayIdAlmacen.get(cmbAlmacen.getSelectedIndex());
            MostrarItems(aux);
            MostrarTablaPedido();
        }        
        
    }

    private void ValidarCampos()
    {        
        ((AbstractDocument) txtTema.getDocument()).setDocumentFilter(filtroMayuscula);        
    }
    
    private void CargarDatosRequerimiento()
    {
        Connection con = ClsConexion.getConection();
        ResultSet rs;
        PreparedStatement st;
        
        try 
        {
            st = con.prepareStatement("select * from mps_requerimiento where requerimiento_id = ?");
            st.setString(1, _IdRequerimiento);
            rs = st.executeQuery();
            
            while(rs.next())
            {
                txtNumero.setText(rs.getString("requerimiento_numero"));
                txtFecha.setText(rs.getString("requerimiento_fecha"));
                txtTema.setText(rs.getString("requerimiento_tema"));
                cbxEstado.setSelectedItem(rs.getString("requerimiento_estado"));                                
            }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(AlmacenAdministracionRequerimientoEvaluar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void CargarDatosPedido()
    {
        Connection con = ClsConexion.getConection();
        ResultSet rs;
        PreparedStatement st;
        
        try 
        {
            st = con.prepareStatement("select ri.ritem_id,ri.item_id,ri.ritem_cantidad,"
                    + "ai.item_nombre,ri.ritem_sustento,ri.requerimiento_id, ai.item_medida, ai.item_descripcion \n" 
                    + "from mps_requerimiento_item as ri\n" 
                    + "inner join mps_almacen_item as ai on ri.item_id = ai.item_id\n" 
                    + "where ri.requerimiento_id = ?");
            st.setString(1, _IdRequerimiento);
            rs = st.executeQuery();
            
            while(rs.next())
            {
                arrayPedido.add(new ClsRequerimiento
                    (
                        rs.getString("ritem_id"),                            
                        rs.getString("ritem_cantidad"),
                        rs.getString("item_nombre"),
                        rs.getString("item_id"),
                        rs.getString("ritem_sustento"),
                        rs.getString("item_medida"),
                        rs.getString("item_descripcion")
                    ));
                arrayPedidoLast.add(new ClsRequerimiento
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
            Logger.getLogger(AlmacenAdministracionRequerimientoEvaluar.class.getName()).log(Level.SEVERE, null, ex);
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

        pnlTitulo1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        pnlMain = new javax.swing.JPanel();
        srpPrincipal = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        pnlRequerimientoItem = new javax.swing.JPanel();
        pnlContenido = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cmbAlmacen = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPedido = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblItems = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnQuitar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        rbnConsumo = new javax.swing.JRadioButton();
        rbnInventario = new javax.swing.JRadioButton();
        txtBusqueda = new javax.swing.JTextField();
        pnlBot = new javax.swing.JPanel();
        btnEmitir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnlRequerimiento1 = new javax.swing.JPanel();
        pnlContenido1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTema = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cbxEstado = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(680, 597));
        setPreferredSize(new java.awt.Dimension(680, 597));
        setLayout(new java.awt.BorderLayout());

        pnlTitulo1.setBackground(new java.awt.Color(237, 240, 242));
        pnlTitulo1.setPreferredSize(new java.awt.Dimension(273, 50));
        pnlTitulo1.setLayout(new java.awt.GridBagLayout());

        jLabel9.setFont(new java.awt.Font("Droid Sans", 1, 12)); // NOI18N
        jLabel9.setText("EVALUAR REQUERIMIENTO ALMACEN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 0);
        pnlTitulo1.add(jLabel9, gridBagConstraints);

        add(pnlTitulo1, java.awt.BorderLayout.PAGE_START);

        pnlMain.setBackground(new java.awt.Color(237, 240, 242));
        pnlMain.setLayout(new java.awt.GridBagLayout());

        srpPrincipal.setBorder(null);

        jPanel1.setBackground(new java.awt.Color(237, 240, 242));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        pnlRequerimientoItem.setBackground(new java.awt.Color(255, 255, 255));
        pnlRequerimientoItem.setLayout(new java.awt.BorderLayout());

        pnlContenido.setBackground(new java.awt.Color(255, 255, 255));
        pnlContenido.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel2.setText("Almacen.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 20);
        pnlContenido.add(jLabel2, gridBagConstraints);

        cmbAlmacen.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbAlmacen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbAlmacen.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbAlmacenItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 20);
        pnlContenido.add(cmbAlmacen, gridBagConstraints);

        jScrollPane1.setMinimumSize(new java.awt.Dimension(452, 302));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 600));

        tblPedido.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        tblPedido.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPedidoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPedido);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 20, 20);
        pnlContenido.add(jScrollPane1, gridBagConstraints);

        jScrollPane2.setMinimumSize(new java.awt.Dimension(452, 302));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(452, 600));

        tblItems.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        tblItems.setModel(new javax.swing.table.DefaultTableModel(
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
        tblItems.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblItemsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblItems);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 20, 20);
        pnlContenido.add(jScrollPane2, gridBagConstraints);

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIconos/ic_horizontal_right.png"))); // NOI18N
        btnAgregar.setToolTipText("Agregar item");
        btnAgregar.setEnabled(false);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        pnlContenido.add(btnAgregar, gridBagConstraints);

        btnQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIconos/ic_horizontal_left.png"))); // NOI18N
        btnQuitar.setToolTipText("Quitar item");
        btnQuitar.setEnabled(false);
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        pnlContenido.add(btnQuitar, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel3.setText("Pedido.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 20, 20);
        pnlContenido.add(jLabel3, gridBagConstraints);

        jSeparator5.setForeground(new java.awt.Color(224, 224, 224));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 269;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlContenido.add(jSeparator5, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel4.setText("Tipo.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 20);
        pnlContenido.add(jLabel4, gridBagConstraints);

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
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 0);
        pnlContenido.add(rbnConsumo, gridBagConstraints);

        rbnInventario.setBackground(new java.awt.Color(255, 255, 255));
        rbnInventario.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        rbnInventario.setText("INVENTARIO");
        rbnInventario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbnInventarioItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 20);
        pnlContenido.add(rbnInventario, gridBagConstraints);

        txtBusqueda.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 20);
        pnlContenido.add(txtBusqueda, gridBagConstraints);

        pnlRequerimientoItem.add(pnlContenido, java.awt.BorderLayout.CENTER);

        pnlBot.setBackground(new java.awt.Color(255, 255, 255));
        pnlBot.setLayout(new java.awt.GridBagLayout());

        btnEmitir.setFont(new java.awt.Font("Droid Sans", 1, 12)); // NOI18N
        btnEmitir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIconos/ic_file-check.png"))); // NOI18N
        btnEmitir.setText("EVALUAR REQUERIMIENTO");
        btnEmitir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmitirActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 500, 20, 20);
        pnlBot.add(btnEmitir, gridBagConstraints);

        btnCancelar.setFont(new java.awt.Font("Droid Sans", 1, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIconos/ic_exit_to_app_black_24dp.png"))); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 20);
        pnlBot.add(btnCancelar, gridBagConstraints);

        pnlRequerimientoItem.add(pnlBot, java.awt.BorderLayout.PAGE_END);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        jPanel1.add(pnlRequerimientoItem, gridBagConstraints);

        pnlRequerimiento1.setBackground(new java.awt.Color(255, 255, 255));
        pnlRequerimiento1.setLayout(new java.awt.BorderLayout());

        pnlContenido1.setBackground(new java.awt.Color(255, 255, 255));
        pnlContenido1.setMinimumSize(new java.awt.Dimension(362, 75));
        pnlContenido1.setName(""); // NOI18N
        pnlContenido1.setPreferredSize(new java.awt.Dimension(362, 75));
        pnlContenido1.setLayout(new java.awt.GridBagLayout());

        jLabel5.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel5.setText("N?? Requerimiento.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 20);
        pnlContenido1.add(jLabel5, gridBagConstraints);

        txtNumero.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtNumero.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 20, 20);
        pnlContenido1.add(txtNumero, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel6.setText("Tema.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 20);
        pnlContenido1.add(jLabel6, gridBagConstraints);

        txtTema.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtTema.setMinimumSize(new java.awt.Dimension(50, 23));
        txtTema.setPreferredSize(new java.awt.Dimension(50, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 20, 20);
        pnlContenido1.add(txtTema, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel7.setText("Fecha.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 20);
        pnlContenido1.add(jLabel7, gridBagConstraints);

        txtFecha.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtFecha.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtFecha.setEnabled(false);
        txtFecha.setMinimumSize(new java.awt.Dimension(50, 23));
        txtFecha.setPreferredSize(new java.awt.Dimension(50, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 20, 20);
        pnlContenido1.add(txtFecha, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel8.setText("Estado.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 20);
        pnlContenido1.add(jLabel8, gridBagConstraints);

        cbxEstado.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cbxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PENDIENTE", "APROBADO" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 20, 20);
        pnlContenido1.add(cbxEstado, gridBagConstraints);

        pnlRequerimiento1.add(pnlContenido1, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(pnlRequerimiento1, gridBagConstraints);

        srpPrincipal.setViewportView(jPanel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        pnlMain.add(srpPrincipal, gridBagConstraints);

        add(pnlMain, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        guardar = false;                
        AbrirDialogAlmacenAdministracionRequerimiento(); //vuelve guardar en true

        
        String _IdItem,_Item, _Unidad, _Descripcion;        
        if(guardar)
        {             
            int row = tblItems.getSelectedRow();

            if(row != -1)
            {
                DefaultTableModel dtmItems = (DefaultTableModel) tblItems.getModel();
                _IdItem = String.valueOf((int) dtmItems.getValueAt(row, 0));
                _Item = (String) dtmItems.getValueAt(row, 2);
                _Unidad = (String) dtmItems.getValueAt(row, 4); 
                _Descripcion = (String) dtmItems.getValueAt(row, 5);
                arrayPedido.add(new ClsRequerimiento(_Cantidad, _Item, _IdItem,_Sustento,_Unidad,_Descripcion));
                MostrarTablaPedido();
            }   
        }
        
    }//GEN-LAST:event_btnAgregarActionPerformed

    
    
    private void btnEmitirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmitirActionPerformed
        
        if(!txtTema.getText().equals(""))
        {
            String _Numero, _Fecha, _Tema, _Estado;
            
            boolean aprobado=false;
            _Numero = txtNumero.getText();
            _Fecha = txtFecha.getText();
            _Tema = txtTema.getText();
            _Estado = cbxEstado.getSelectedItem().toString();

            Connection con = ClsConexion.getConection();
            
            try 
            {    
                //revisar si el requerimiento es nuevo o revisado
                PreparedStatement stEstado = con.prepareStatement("select requerimiento_estado "
                        + "from mps_requerimiento \n"
                        + "where requerimiento_id = ?");
                stEstado.setString(1, _IdRequerimiento);
                ResultSet rsEstado = stEstado.executeQuery();
                while(rsEstado.next())
                {
                    aprobado = rsEstado.getString("requerimiento_estado").equals("APROBADO");
                }
                
                //actualizar requerimiento
                PreparedStatement st = con.prepareStatement("update mps_requerimiento set\n" 
                        + "requerimiento_tema = ?,\n" 
                        + "requerimiento_estado = ?\n" 
                        + "where requerimiento_id = ?");
                st.setString(1, _Tema);
                st.setString(2, _Estado);
                st.setString(3, _IdRequerimiento);
                st.executeUpdate();   
                
                PreparedStatement stt;
                PreparedStatement sttt;
                try 
                {                    
                    //se borran todos los items del requerimiento
                    stt = con.prepareStatement("delete from mps_requerimiento_item where requerimiento_id = ?");
                    stt.setString(1, _IdRequerimiento);
                    stt.executeUpdate();
                    
                    //se vuelven a insertar segun el nuevo pedido
                    for(ClsRequerimiento r : arrayPedido)
                    { 
                        sttt = con.prepareStatement("insert into mps_requerimiento_item\n" 
                                    + "(ritem_cantidad,ritem_sustento,item_id,requerimiento_id)\n" 
                                    + "values (?,?,?,?)");
                            sttt.setString(1, r.getCantidad());
                            sttt.setString(2, r.getSustento());
                            sttt.setString(3, r.getIdItem());
                            sttt.setString(4, _IdRequerimiento);
                            sttt.executeUpdate();
                    }
                     
                    FrmPrincipal.LlamarAdministracionAlmacenRequerimiento();
                    
                }
                catch (SQLException ex) 
                {
                    Logger.getLogger(AlmacenAdministracionRequerimientoEvaluar.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(AlmacenAdministracionRequerimientoEvaluar.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else
        {
            JOptionPane.showMessageDialog(this, "Complete los campos necesarios", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_btnEmitirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        FrmPrincipal.LlamarAdministracionAlmacenRequerimiento();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        arrayPedido.remove(rowPedido);
        btnQuitar.setEnabled(false);
        MostrarTablaPedido();
    }//GEN-LAST:event_btnQuitarActionPerformed

    private void tblPedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPedidoMouseClicked
        rowPedido = tblPedido.getSelectedRow();

        if(rowPedido != -1)
        {
            btnAgregar.setEnabled(false);
            btnQuitar.setEnabled(true);
        }
        
        if(rowPedido != -1)
        {
            if(evt.getClickCount() == 2)
            {
                DefaultTableModel dtm = (DefaultTableModel)tblPedido.getModel();
                String itemId =  ((String) dtm.getValueAt(rowPedido, 0));
                String itemNombre =  ((String) dtm.getValueAt(rowPedido, 2));
                String itemMedida =  ((String) dtm.getValueAt(rowPedido, 3));
                String itemCantidad = ((String) dtm.getValueAt(rowPedido, 4));
                String itemSustento = ((String) dtm.getValueAt(rowPedido, 5));
                String itemDescripcion = ((String) dtm.getValueAt(rowPedido, 6));
                
                JLabel lblCantidad;
                JPanel pnlContenedor = new JPanel();
                JTextField txtCantidad;
                JLabel lblSustento;
                JTextField txtSustento;
                String[] options = new String[]{"GUARDAR","CANCELAR"};
                
                java.awt.GridBagConstraints gridBagConstraints;

                lblCantidad = new javax.swing.JLabel();
                txtCantidad = new javax.swing.JTextField();
                lblSustento = new javax.swing.JLabel();
                txtSustento = new javax.swing.JTextField();

                pnlContenedor.setLayout(new java.awt.GridBagLayout());

                lblCantidad.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
                lblCantidad.setText("Cantidad.");
                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
                pnlContenedor.add(lblCantidad, gridBagConstraints);

                txtCantidad.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
                txtCantidad.setMinimumSize(new java.awt.Dimension(60, 21));
                txtCantidad.setPreferredSize(new java.awt.Dimension(50, 21));
                txtCantidad.setText(itemCantidad);
                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
                gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
                pnlContenedor.add(txtCantidad, gridBagConstraints);

                lblSustento.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
                lblSustento.setText("Sustento.");
                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 1;
                gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 0);
                pnlContenedor.add(lblSustento, gridBagConstraints);

                txtSustento.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
                txtSustento.setMinimumSize(new java.awt.Dimension(200, 21));
                txtSustento.setName(""); // NOI18N
                txtSustento.setPreferredSize(new java.awt.Dimension(400, 21));      
                txtSustento.setText(itemSustento);
                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.gridx = 1;
                gridBagConstraints.gridy = 1;
                gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 0);
                pnlContenedor.add(txtSustento, gridBagConstraints);
                txtCantidad.requestFocus();
                int option = JOptionPane.showOptionDialog(null, pnlContenedor, "EDITAR ITEM: "+itemNombre,
                JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
                
                if(option == 0) {
                    
                    itemCantidad = txtCantidad.getText();
                    
//                    int nuevaCantidad = Integer.parseInt(itemCantidad);
//                    int restanteCantidad = Integer.parseInt(_StockRestante);
//                    
//                    if(nuevaCantidad <= restanteCantidad) {
                        
                        itemSustento = txtSustento.getText();
                        arrayPedido.get(rowPedido).setCantidad(itemCantidad);
                        arrayPedido.get(rowPedido).setSustento(itemSustento);
                        MostrarTablaPedido();
                        
//                    }
//                    else
//                    {
//                        JOptionPane.showMessageDialog(this, "La cantidad solicitada no est?? disponible, elija un monto menor", 
//                                "Error al agregar item", JOptionPane.ERROR_MESSAGE);
//                    }
                    
                }
                                
            }
        }
    }//GEN-LAST:event_tblPedidoMouseClicked

    private void tblItemsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblItemsMouseClicked
        int row = tblItems.getSelectedRow();

        if(row != -1)
        {
            btnAgregar.setEnabled(true);
            btnQuitar.setEnabled(false);
            DefaultTableModel dtmItems = (DefaultTableModel) tblItems.getModel();
            _StockRestante = String.valueOf((int) dtmItems.getValueAt(row, 3));//para validar que no sea 0
        }
    }//GEN-LAST:event_tblItemsMouseClicked

    private void cmbAlmacenItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbAlmacenItemStateChanged
       if(cmbAlmacen.getSelectedIndex() != -1)
       {
           MostrarItems((int) arrayIdAlmacen.get(cmbAlmacen.getSelectedIndex()));
       }
    }//GEN-LAST:event_cmbAlmacenItemStateChanged

    private void rbnConsumoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbnConsumoItemStateChanged
        Consumo = true;
         if(cmbAlmacen.getSelectedIndex() != -1)
        {
            MostrarItems((int) arrayIdAlmacen.get(cmbAlmacen.getSelectedIndex()));
        }
    }//GEN-LAST:event_rbnConsumoItemStateChanged

    private void rbnInventarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbnInventarioItemStateChanged
        Consumo = false;
         if(cmbAlmacen.getSelectedIndex() != -1)
        {
            MostrarItems((int) arrayIdAlmacen.get(cmbAlmacen.getSelectedIndex()));
        }
    }//GEN-LAST:event_rbnInventarioItemStateChanged

    private void txtBusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyTyped
        
        String busqueda = txtBusqueda.getText();
        int idAlmacen = (int) arrayIdAlmacen.get(cmbAlmacen.getSelectedIndex());
        if(!busqueda.equals("")) {
            
            MostrarItemsBusqueda(idAlmacen, busqueda);
        }
        else {
            MostrarItems(idAlmacen);
        }
        
    }//GEN-LAST:event_txtBusquedaKeyTyped
    
    private void MostrarItems(int _IdAlmacen)
    {
        almacenModelo.MostrarTablaAlmacenItems(tblItems, _IdAlmacen,Consumo);
    }
    
    private void MostrarItemsBusqueda(int IdAlmacen, String busqueda) {
        
        almacenModelo.MostrarTablaAlmacenItemsBusqueda(tblItems, IdAlmacen,Consumo,busqueda);
        
    }
    
    private void AbrirDialogAlmacenAdministracionRequerimiento()
    {
        Window parentWindow = SwingUtilities.windowForComponent(this);
        AlmacenAdministracionRequerimientoNuevoPedido almacenAdministracionRequerimiento = new AlmacenAdministracionRequerimientoNuevoPedido(parentWindow);
        almacenAdministracionRequerimiento.FormularioPadreEditar(this);
        almacenAdministracionRequerimiento.setModal(true);
        almacenAdministracionRequerimiento.setLocationRelativeTo(null);
        almacenAdministracionRequerimiento.setVisible(true);
        almacenAdministracionRequerimiento.toFront();
    }
    
    
    private void MostrarTablaPedido()
    {
        String titulos[] = {"IDITEM","N??","ITEM","U. MEDIDA","CANTIDAD","SUSTENTO","DESCRIPCION"};
        Object fila[];
        DefaultTableModel dtmItem;
        fila = new String[9];
        dtmItem = new DefaultTableModel(null,titulos) {        
            @Override
            public boolean isCellEditable(int rowIndex,int columnIndex){return false;}               
        }; 
        int count = 1;
        for(ClsRequerimiento e : arrayPedido)
        {            
            fila[0] = e.getIdItem();
            fila[1] = String.valueOf(count);
            fila[2] = e.getItem();
            fila[3] = e.getUnidad();
            fila[4] = e.getCantidad();
            fila[5] = e.getSustento();
            fila[6] = e.getDescripcion();
            count++;
            dtmItem.addRow(fila);
        }
        
        tblPedido.setModel(dtmItem);
        tblPedido.setRowHeight(20);
                
        tblPedido.removeColumn(tblPedido.getColumnModel().getColumn(0));
        
        TableColumn colNumero = tblPedido.getColumnModel().getColumn(0);
        colNumero.setPreferredWidth(35);
        TableColumn colItem = tblPedido.getColumnModel().getColumn(1);
        colItem.setPreferredWidth(130);
        TableColumn colUnidad = tblPedido.getColumnModel().getColumn(2);
        colUnidad.setPreferredWidth(50);
        TableColumn colCantidad = tblPedido.getColumnModel().getColumn(3);
        colCantidad.setPreferredWidth(50);
        TableColumn colSustento = tblPedido.getColumnModel().getColumn(4);
        colSustento.setPreferredWidth(170);
        TableColumn colDescripcion = tblPedido.getColumnModel().getColumn(5);
        colDescripcion.setPreferredWidth(170);
        
        TableCellRenderer tcr =  tblPedido.getTableHeader().getDefaultRenderer();
        ClsMisc.miRender mr = new ClsMisc.miRender(tcr);
        mr.getTableCellRendererComponent(tblPedido, fila, true, true, WIDTH, 0);
        mr.getTableCellRendererComponent(tblPedido, fila, true, true, WIDTH, 2);
        mr.getTableCellRendererComponent(tblPedido, fila, true, true, WIDTH, 3);        

    }
    
    public void FormularioPadre(AlmacenAdministracionRequerimiento almacenRequerimiento)
    {
        this.almacenRequerimiento = almacenRequerimiento;
    }
    

    
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEmitir;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JComboBox<String> cbxEstado;
    private javax.swing.JComboBox<String> cmbAlmacen;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JPanel pnlBot;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlContenido1;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlRequerimiento1;
    private javax.swing.JPanel pnlRequerimientoItem;
    private javax.swing.JPanel pnlTitulo1;
    private javax.swing.JRadioButton rbnConsumo;
    private javax.swing.JRadioButton rbnInventario;
    private javax.swing.JScrollPane srpPrincipal;
    private javax.swing.JTable tblItems;
    private javax.swing.JTable tblPedido;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtTema;
    // End of variables declaration//GEN-END:variables
}
