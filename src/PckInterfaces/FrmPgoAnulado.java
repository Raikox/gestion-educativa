/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckInterfaces;

import PckConexion.ClsConexion;
import PckEntidad.ClsEntidadAnulaBoleta;
import PckEntidad.ClsEntidadBoleta;
import PckEntidad.ClsEntidadDetalleBoleta;

import PckNegocio.ClsBoleta;
import PckNegocio.ClsDetalleBoleta;
import PckNegocio.ClsMatricula;
import java.awt.event.KeyEvent;
import static java.awt.image.ImageObserver.WIDTH;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import prymatricula.ClsMisc;
import static prymatricula.ClsMisc.obtenerAnioActual;

/**
 *
 * @author kmv
 */
public class FrmPgoAnulado extends javax.swing.JInternalFrame {
SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
String codBoleta;
String codDetalleBoleta;
String codHistorial;

Date actual = new Date();

Connection con = null;   
    /**
     * Creates new form FrmPgoAnulado
     */
    public FrmPgoAnulado() {
        initComponents();
        String seleccionado = llenaCombo();
        cmbAnio.setSelectedItem(seleccionado); 
        actualizarTabla();
        activaBotones(true,false,false,false);
        
    }
    private String llenaCombo()
    {
        cmbAnio.removeAllItems();
        //obtengo año actual
        int Anioactual = Integer.parseInt(obtenerAnioActual());   
        int burbuja[] = new int[4];
        //lleno la matriz inicial (2015-2014-2013-2012-2011)
        //es necesario ordenarla
        for (int i=0;i<4;i++)
        {
           burbuja[i] = Anioactual - i;
        }        
        int temp;
        for(int m=0;m<burbuja.length;m++)//recorre cada elemento
        {            
            for(int n=0;n<burbuja.length-1;n++)//el elemento es comparado con todos
            {                
                if(burbuja[n]>burbuja[n+1]) //+1 porque ya tomamos el valor 0
                {
                    temp = burbuja[n];
                    burbuja[n] = burbuja[n+1];
                    burbuja[n+1] = temp;
                }
            }
        }
        //agrego los años ordenados
        for(int o=0;o<burbuja.length;o++)
        {
            cmbAnio.addItem(String.valueOf(burbuja[o]));
        }        
        //agrego los 4 años siguientes
        for (int j=1;j<5;j++)
        {
           cmbAnio.addItem(String.valueOf(Anioactual + j));
        }
        
        return String.valueOf(Anioactual);
    }
    
    private void codigoBoleta() 
    {
        ClsMatricula matricula = new ClsMatricula();
        con = ClsConexion.getConection();
        String numero = matricula.obtenSerie(con);
        txtNumero.setText(numero);
        ClsConexion.closeConnection(con);
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
        jPanel2 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBoleta = new javax.swing.JTable();
        cmbAnio = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtFecha = new com.toedter.calendar.JDateChooser();
        txtNumero = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setTitle("Ingresar Boleta Anulada");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnNuevo.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIconos/add.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel2.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 120, -1));

        btnGuardar.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIconos/accept_page.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel2.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 120, -1));

        btnModificar.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIconos/edit_page.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel2.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 120, -1));

        btnEliminar.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIconos/delete_page.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel2.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 120, -1));

        btnSalir.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIconos/delete.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel2.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 120, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 40, 180, 330));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Boletas Anuladas"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblBoleta.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        tblBoleta.setModel(new javax.swing.table.DefaultTableModel(
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
        tblBoleta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBoletaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBoleta);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 510, 200));

        cmbAnio.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbAnio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020" }));
        cmbAnio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbAnioItemStateChanged(evt);
            }
        });
        jPanel3.add(cmbAnio, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 110, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 560, 260));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel1.setText("Número Boleta:");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 20));

        jLabel3.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel3.setText("Fecha:");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, 20));

        txtFecha.setEnabled(false);
        txtFecha.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jPanel4.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 110, -1));

        txtNumero.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtNumero.setEnabled(false);
        txtNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroKeyTyped(evt);
            }
        });
        jPanel4.add(txtNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 110, -1));

        jLabel2.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel2.setText("Motivo de");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, -1, -1));

        jLabel4.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel4.setText("Anulación:");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, -1, -1));

        txtDescripcion.setColumns(20);
        txtDescripcion.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtDescripcion.setRows(5);
        txtDescripcion.setEnabled(false);
        jScrollPane2.setViewportView(txtDescripcion);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 180, 100));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 560, 190));

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Droid Sans", 0, 13)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("Nota: Usar esta ventana solamente si no hubo error en el registro de pago, es decir, en caso de rotura de papel, código boleta erróneo, etc.");
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        jTextArea1.setEnabled(false);
        jScrollPane3.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 380, 180, 130));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

          ClsEntidadDetalleBoleta edb = new ClsEntidadDetalleBoleta();
          ClsDetalleBoleta db = new ClsDetalleBoleta();
          //DETALLE BOLETA
                                  
          edb.setCantidad_Detalle(1);
          edb.setDescripcion_Detalle(txtDescripcion.getText());
          edb.setUnitario_Detalle(0.00);
          edb.setImporte_Detalle(0.00);   
          edb.setTipo_Detalle("anulada");
          edb.setId_Deuda("0");
                //BOLETA
                ClsEntidadBoleta eb = new ClsEntidadBoleta();
                ClsBoleta b = new ClsBoleta();                              
                //en este caso el total es el importe al haber un solo pago (contador = 1)
                Double total = 0.00;
                eb.setNumero_Boleta(txtNumero.getText());
                if (txtFecha.getDate() == null)
                {
                    eb.setFecha_Boleta("");
                }
                else{eb.setFecha_Boleta(formato.format(txtFecha.getDate()));}
                eb.setTotal_Boleta(total);
                eb.setId_Matricula("169"); //numero de boleta pre establecida en sistema
                con = ClsConexion.getConection();
                b.AgregarBoleta(eb,con);                
                //agrega detalle boleta
                ClsMisc misc = new ClsMisc();
                try 
                {
                    codBoleta = misc.UltimoIdInsertado(con);        
                                  
                } catch (Exception ex) {
                 ex.printStackTrace();
                }
            ClsConexion.closeConnection(con);
            edb.setId_Boleta(codBoleta);
            con = ClsConexion.getConection();
            db.AgregarDetalleBoleta(edb,con);
            ClsConexion.closeConnection(con);
                  
        actualizarTabla();
        activaBotones(true,false,false,false);
        limpiaCampos();
        desactivaCampos();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void tblBoletaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBoletaMouseClicked
       activaBotones(true,false,true,true);
       
       int reg;
    DefaultTableModel defaultTableModel ;
    reg = tblBoleta.getSelectedRow();
    if(reg == -1)
    {
        JOptionPane.showMessageDialog(null, "Debe Seleccionar un registro");
    }
    else{
        defaultTableModel = (DefaultTableModel)tblBoleta.getModel();
        codBoleta = ((String)defaultTableModel.getValueAt(reg, 0));
        codDetalleBoleta=((String)defaultTableModel.getValueAt(reg, 1));
        //codHistorial=((String)defaultTableModel.getValueAt(reg, 2));
        txtNumero.setText((String)defaultTableModel.getValueAt(reg, 2));
        txtDescripcion.setText((String)defaultTableModel.getValueAt(reg, 3));
        try 
        { 
           txtFecha.setDate(formato.parse((String)defaultTableModel.getValueAt(reg, 4)));
        } catch (ParseException ex) {
           Logger.getLogger(FrmPgoAnulado.class.getName()).log(Level.SEVERE, null, ex);
        }
        activaCampos();
        txtNumero.requestFocus();
        
    }
    }//GEN-LAST:event_tblBoletaMouseClicked

    private void txtNumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroKeyTyped
        char c = evt.getKeyChar();
        if (txtNumero.getText().length()== 12 || !(Character.isDigit(c) ||
        (c == KeyEvent.VK_BACK_SPACE) ||
        (c == KeyEvent.VK_DELETE) ||
        (c == KeyEvent.VK_SPACE) || 
        (c == KeyEvent.VK_MINUS) ))
        {
            
        evt.consume();
        }
    }//GEN-LAST:event_txtNumeroKeyTyped

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        ClsEntidadDetalleBoleta edb = new ClsEntidadDetalleBoleta();
          ClsDetalleBoleta db = new ClsDetalleBoleta();
          //DETALLE BOLETA
                                  
          edb.setCantidad_Detalle(1);
          edb.setDescripcion_Detalle(txtDescripcion.getText());
          edb.setUnitario_Detalle(0.00);
          edb.setImporte_Detalle(0.00);        
          
            //BOLETA
            ClsEntidadBoleta eb = new ClsEntidadBoleta();
            ClsBoleta b = new ClsBoleta();                              
            //en este caso el total es el importe al haber un solo pago (contador = 1)
            Double total = 0.00;
            eb.setNumero_Boleta(txtNumero.getText());

            if (txtFecha.getDate() == null){
                eb.setFecha_Boleta("");
            }
            else{eb.setFecha_Boleta(formato.format(txtFecha.getDate()));}
            eb.setTotal_Boleta(total);
            con = ClsConexion.getConection();
            b.ModificaBoleta(eb, codBoleta,con);

            //agrega detalle boleta
                
            edb.setId_Boleta(codBoleta);
            con = ClsConexion.getConection();
            db.ModificarDetalleBoletaFull(edb, codDetalleBoleta,con);
            
            try
            {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(FrmPgoAnulado.class.getName()).log(Level.SEVERE, null, ex);
            }
         
        
        actualizarTabla();
        desactivaCampos();
        limpiaCampos();
        activaBotones(true,false,false,false);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        
          ClsDetalleBoleta db = new ClsDetalleBoleta();          
          ClsBoleta b = new ClsBoleta();         
          con = ClsConexion.getConection();
          db.EliminarDetalleBoleta(codDetalleBoleta,con); 
          ClsConexion.closeConnection(con);
          con = ClsConexion.getConection();
          b.EliminarBoleta(codBoleta,con); 
          ClsConexion.closeConnection(con);
          actualizarTabla();
          activaBotones(true,false,false,false);
          desactivaCampos();
          limpiaCampos();
          
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void cmbAnioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbAnioItemStateChanged

        if(cmbAnio.getSelectedIndex() != -1) //solo cargara si el index no es -1
        {            
            actualizarTabla();
        }

    }//GEN-LAST:event_cmbAnioItemStateChanged

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        activaCampos();
        limpiaCampos();
        txtNumero.requestFocus();
        codigoBoleta();
        activaBotones(true,true,false,false);
        txtFecha.setDate(actual);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void actualizarTabla()
    {         
        String titulos[] = {"ID BOLETA","ID DET BOLETA","Numero Boleta","Descripcion","Fecha"};
        ClsBoleta boletas = new ClsBoleta();
        con = ClsConexion.getConection();
        ArrayList<ClsEntidadAnulaBoleta> alumno = boletas.ListarBoletasAnuladas(con);
        Iterator iterator = alumno.iterator();
        DefaultTableModel defaultTableModel =new DefaultTableModel(null,titulos){
        public boolean isCellEditable(int rowIndex,int columnIndex){return false;} 
        };               
        String fila[] = new String[12];                
        while(iterator.hasNext()){
            ClsEntidadAnulaBoleta Alumno ;
            Alumno = (ClsEntidadAnulaBoleta) iterator.next();
            fila[0] = Alumno.getID_Boleta();
            fila[1] = Alumno.getId_Detalle_Boleta();            
            fila[2] = Alumno.getNumero_Boleta();
            fila[3] = Alumno.getDescripcion_Boleta();          
            fila[4] = Alumno.getFecha_Boleta();  
            if(fila[4].substring(6).equals(cmbAnio.getSelectedItem()))
            {
                defaultTableModel.addRow(fila);
            }                                                          
        }       
        ClsConexion.closeConnection(con);
        tblBoleta.setModel(defaultTableModel);
        tblBoleta.removeColumn(tblBoleta.getColumnModel().getColumn(0));
        tblBoleta.removeColumn(tblBoleta.getColumnModel().getColumn(0));
        //tblBoleta.removeColumn(tblBoleta.getColumnModel().getColumn(0));
                
        TableColumn  columnaPer =tblBoleta.getColumnModel().getColumn(0);
            columnaPer.setPreferredWidth(60);
        TableColumn  columnaPerr =tblBoleta.getColumnModel().getColumn(1);
            columnaPerr.setPreferredWidth(250);
        TableCellRenderer tcr =  tblBoleta.getTableHeader().getDefaultRenderer();                       
        ClsMisc.miRender mr = new ClsMisc.miRender(tcr);
        mr.getTableCellRendererComponent(tblBoleta, fila, isSelected, isIcon, WIDTH, 2);
        mr.getTableCellRendererComponent(tblBoleta, fila, isSelected, isIcon, WIDTH, 0);
                           
    }
    private void activaCampos()
    {
        txtNumero.setEnabled(true);
        txtDescripcion.setEnabled(true);
        txtFecha.setEnabled(true);
    }
    private void desactivaCampos()
    {
        txtNumero.setEnabled(false);
        txtDescripcion.setEnabled(false);
        txtFecha.setEnabled(false);
    }

    private void activaBotones(boolean n,boolean g,boolean m,boolean e)
    {
        btnNuevo.setEnabled(n);
        btnGuardar.setEnabled(g);
        btnModificar.setEnabled(m);
        btnEliminar.setEnabled(e);
    }
    private void limpiaCampos()
    {
        txtNumero.setText("");
        txtDescripcion.setText("");
        txtFecha.setDate(null);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cmbAnio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTable tblBoleta;
    private javax.swing.JTextArea txtDescripcion;
    private com.toedter.calendar.JDateChooser txtFecha;
    private javax.swing.JTextField txtNumero;
    // End of variables declaration//GEN-END:variables
}
