/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PckInterfaces;

import PckConexion.ClsConexion;
import PckEntidad.ClsEntidadSeccion;
import PckEntidad.ClsEntidadSubEntregable;
import PckNegocio.ClsAnecdotario;
import PckNegocio.ClsMatMen;
import PckNegocio.ClsRaices;
import PckNegocio.ClsSubEntregable;
import java.awt.Font;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import prymatricula.ClsMisc;
import static prymatricula.ClsMisc.fechaActual;
import static prymatricula.ClsMisc.obtenerMeses;
import prymatricula.JTreeFile;

/**
 *
 * @author Kevin
 */
public class FrmEntregablesDocente extends javax.swing.JInternalFrame {
    Font fuente=new Font("Dialog", Font.BOLD, 15);
    Connection con = null;
    //almacena los codigos de Seccion del CombBox
    ArrayList<String> codigoSeccion = new ArrayList();
    //almacena los codigos de Periodo del CombBox
    ArrayList<String> codigoPeriodo = new ArrayList();
    
    DefaultComboBoxModel dcmMeses = new DefaultComboBoxModel();
    ArrayList<String> numeroMes = new ArrayList();
    String subEntregable="";
    String hola=fechaActual();
    
    /*
    *month[]: Array de meses 
    *Valores: Desde Enero hasta Diciembre
    */
    String month[] = {"ENERO","FEBRERO","MARZO","ABRIL",
        "MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"};
    
    
    private String rutaDestino;
    private String rutaOrigen;
    private String archivoOrigen;
    private String idEntregable;
    /**
     * Creates new form FrmEntregablesDocente
     */
    public FrmEntregablesDocente() {
        initComponents();
        System.out.println(hola);
        txtUsuario.setFont(fuente);
        txtUsuario.setText(FrmPrincipal.usuarioLogeado);
        rutaDestino = cargarRaiz();
        System.out.println("destino "+rutaDestino);
        actualizarComboPeriodo();       
        actualizarComboSeccion();
        actualizarArbol();
        cargaMes();
        
        /*
        *modelo para Estado: agrega los estados para cmbEstado
        */
        DefaultComboBoxModel dcm = new DefaultComboBoxModel();        
        //dcm.addElement("aprobado");
        //dcm.addElement("corregir");
        dcm.addElement("PENDIENTE");
        dcm.addElement("FALTA");
        cmbEstado.setModel(dcm);        
        
        actualizarTabla();
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnDescargar = new javax.swing.JButton();
        btnSalir1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        txtArchivo = new javax.swing.JTextField();
        btnFile = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        treEntregable = new javax.swing.JTree();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cmbPeriodo = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        cmbSeccion = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEstado = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtComentario = new javax.swing.JTextArea();
        jPanel12 = new javax.swing.JPanel();
        btnEstado = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        cmbMes = new javax.swing.JComboBox<>();

        setTitle("Mis Entregables");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones"));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Subir Archivo"));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, 270, 70));

        btnGuardar.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnGuardar.setText("<html> <p align=center>Subir</p> <p align=center>Archivo</p> </html>");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel5.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 90, -1));

        btnDescargar.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnDescargar.setText("<html> <p align=center>Descargar</p> <p align=center>Archivo</p> </html>");
        btnDescargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescargarActionPerformed(evt);
            }
        });
        jPanel5.add(btnDescargar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 90, -1));

        btnSalir1.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnSalir1.setText("Salir");
        btnSalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir1ActionPerformed(evt);
            }
        });
        jPanel5.add(btnSalir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 90, 40));

        jPanel9.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 60, 130, 250));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Archivo a Subir"));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtArchivo.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtArchivo.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtArchivo.setEnabled(false);
        jPanel7.add(txtArchivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 550, -1));

        btnFile.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnFile.setText("...");
        btnFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFileActionPerformed(evt);
            }
        });
        jPanel7.add(btnFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 30, -1, 20));

        jPanel9.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 710, 70));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Mis Entregables"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        treEntregable.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        treEntregable.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                treEntregableValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(treEntregable);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 310, 300));

        jPanel9.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 400, 350));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccione su aula"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel8.setText("Periodo:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 120, 20));

        cmbPeriodo.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbPeriodo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbPeriodo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPeriodoItemStateChanged(evt);
            }
        });
        jPanel2.add(cmbPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 220, -1));

        jLabel9.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel9.setText("Aula:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, 20));

        cmbSeccion.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbSeccion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSeccionItemStateChanged(evt);
            }
        });
        jPanel2.add(cmbSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 220, -1));

        jPanel9.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 280, 170));

        jLabel1.setFont(new java.awt.Font("Droid Sans", 1, 12)); // NOI18N
        jLabel1.setText("Profesor(a):");
        jPanel9.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 80, 30));

        txtUsuario.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtUsuario.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtUsuario.setEnabled(false);
        jPanel9.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 280, 30));

        jTabbedPane1.addTab("Entregables", jPanel9);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Estado Entregables"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblEstado.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        tblEstado.setModel(new javax.swing.table.DefaultTableModel(
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
        tblEstado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEstadoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblEstado);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 650, 170));

        jPanel10.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 710, 210));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel2.setText("Estado:");
        jPanel11.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, 20));

        cmbEstado.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel11.add(cmbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 150, -1));

        jLabel3.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel3.setText("Comentario Adm.:");
        jPanel11.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, -1, 20));

        txtComentario.setColumns(20);
        txtComentario.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtComentario.setLineWrap(true);
        txtComentario.setRows(5);
        txtComentario.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtComentario.setEnabled(false);
        jScrollPane3.setViewportView(txtComentario);

        jPanel11.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 200, 110));

        jPanel10.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 470, 200));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones"));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEstado.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnEstado.setText("Cambiar Estado");
        btnEstado.setEnabled(false);
        btnEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstadoActionPerformed(evt);
            }
        });
        jPanel12.add(btnEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 120, 30));

        btnSalir.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel12.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 120, 30));

        jPanel10.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, 200, 130));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Vista por Mes"));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmbMes.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbMes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbMesItemStateChanged(evt);
            }
        });
        jPanel13.add(cmbMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 140, -1));

        jPanel10.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, 200, 70));

        jTabbedPane1.addTab("Estado Entregables", jPanel10);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 510));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbSeccionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSeccionItemStateChanged
        if(cmbSeccion.getSelectedIndex() != -1)
        {
            
        }
    }//GEN-LAST:event_cmbSeccionItemStateChanged

    private void cmbPeriodoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPeriodoItemStateChanged
        if(cmbPeriodo.getSelectedIndex() != -1)
        {
            actualizarComboSeccion();
        }
    }//GEN-LAST:event_cmbPeriodoItemStateChanged

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(!txtArchivo.getText().equals(""))
        {
            //valida 3ra seleccion
            boolean encontrado=false;            
            String seleccionado[] = rutaDestino.split("\\\\");
            encontrado = seleccionado.length == 6;
            
            if(encontrado)
            {
                int op =JOptionPane.showConfirmDialog(this, "Se guardará el archivo en la"
                    + " siguiente ruta: \n "+rutaDestino+"\n¿Desea Continuar? " ,
                    "Subir archivo",JOptionPane.INFORMATION_MESSAGE);
            
                System.out.println("Destino: "+rutaDestino+File.separator+archivoOrigen);
                System.out.println("Origen: "+rutaOrigen);
                if(op==0)
                {
                    try {
                        //do
                        Path from = Paths.get(rutaOrigen);
                        Path to = Paths.get(rutaDestino+File.separator+archivoOrigen);
                        //sobreescribir el fichero de destino, si existe, y copiar
                        // los atributos, incluyendo los permisos rwx
                        CopyOption[] options = new CopyOption[] {

                            StandardCopyOption.REPLACE_EXISTING,
                            StandardCopyOption.COPY_ATTRIBUTES

                        };      

                        Files.copy(from, to, options);

                        String fecha = fechaActual();
                        ClsSubEntregable se = new ClsSubEntregable();
                        con = ClsConexion.getConection();
                        se.EditarSubEntregableFecha(fecha, subEntregable, cmbSeccion.getSelectedItem().toString(), con);
                        ClsConexion.closeConnection(con);
                        btnEstado.setEnabled(false);        
                        rutaDestino = cargarRaiz();
                        actualizarArbol();
                        actualizarTabla();
                    } catch (IOException ex) {
                        Logger.getLogger(FrmEntregablesDocente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Seleccione como ruta de destino un MES");
            }
            
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFileActionPerformed
        JFileChooser se = new JFileChooser();
        se.setFileSelectionMode(JFileChooser.FILES_ONLY);        
        int estado = se.showOpenDialog(se);        
        if(estado == JFileChooser.APPROVE_OPTION)
        { 
            archivoOrigen = se.getSelectedFile().getName();
            rutaOrigen = se.getSelectedFile().getAbsolutePath();
            txtArchivo.setText(rutaOrigen);
        }        
        
    }//GEN-LAST:event_btnFileActionPerformed

    private void treEntregableValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_treEntregableValueChanged
        String miRuta="";
        rutaDestino="";
        rutaDestino=cargarRaiz();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) treEntregable.getLastSelectedPathComponent();
        if(node != null)
        {
            Object[] paths = treEntregable.getSelectionPath().getPath();
            for (int i=0; i<paths.length; i++) {
                if(i==1){
                    subEntregable += paths[i];
                    System.out.println(subEntregable);
                }
                miRuta += paths[i];
                if (i+1 <paths.length ) {
                    miRuta += File.separator;
                }
            } 
            rutaDestino = rutaDestino + File.separator + cmbPeriodo.getSelectedItem().toString() + 
                        File.separator + miRuta;

            System.out.println("ruta Destino seleccionada: "+rutaDestino);
        }
        
    }//GEN-LAST:event_treEntregableValueChanged

    private void btnSalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalir1ActionPerformed

    private void cmbMesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbMesItemStateChanged
        actualizarTabla();
    }//GEN-LAST:event_cmbMesItemStateChanged

    private void tblEstadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEstadoMouseClicked
                
        int reg;
        DefaultTableModel dtm = new DefaultTableModel();
        reg = tblEstado.getSelectedRow();
        if(reg == -1)
        {
            JOptionPane.showMessageDialog(null, "Debe Seleccionar un registro");
        }
        else
        {
            dtm = (DefaultTableModel)tblEstado.getModel();
            idEntregable = (String) dtm.getValueAt(reg, 0);
            System.out.println("idEnt "+idEntregable);
            cmbEstado.setSelectedItem((String) dtm.getValueAt(reg, 3)); 
            txtComentario.setText((String) dtm.getValueAt(reg, 4));
            btnEstado.setEnabled(true);
        }
        
    }//GEN-LAST:event_tblEstadoMouseClicked

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadoActionPerformed
        
        ClsSubEntregable se = new ClsSubEntregable();
        con = ClsConexion.getConection();
        se.EditarSubEntregableEstado(idEntregable, cmbEstado.getSelectedItem().toString(), con);
        ClsConexion.closeConnection(con);
        btnEstado.setEnabled(false);        
        actualizarTabla();
        
    }//GEN-LAST:event_btnEstadoActionPerformed

    private void btnDescargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescargarActionPerformed
        String archivoDestino = null;
        String rDestino = null;
        String origen;
        JFileChooser se = new JFileChooser();
        se.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int estado = se.showOpenDialog(se);        
        if(estado == JFileChooser.APPROVE_OPTION)
        { 
            archivoDestino = se.getSelectedFile().getName();
            rDestino = se.getSelectedFile().getAbsolutePath();
            //txtArchivo.setText(rutaOrigen);
        }         
               
        try {
            //do
            File f = new File(rutaDestino);
            String nombre = f.getName();
            Path from = Paths.get(rutaDestino);
            System.out.println("descargar en: "+rDestino+File.separator+nombre);
            Path to = Paths.get(rDestino+File.separator+nombre);
            //sobreescribir el fichero de destino, si existe, y copiar
            // los atributos, incluyendo los permisos rwx
            CopyOption[] options = new CopyOption[] {

                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES

            };      

            Files.copy(from, to, options);
            rutaDestino = cargarRaiz();
        } catch (IOException ex) {
            Logger.getLogger(FrmEntregablesDocente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }//GEN-LAST:event_btnDescargarActionPerformed

    private void actualizarTabla() {
        try 
        {
            ClsRaices ra = new ClsRaices();
            String url;
            String mes;
            String periodo;
            String aula;
            String titulos[] = {"ID","Entregable","Archivos","Estado","Comentario"};
            ClsSubEntregable se = new ClsSubEntregable();
            con = ClsConexion.getConection();
            url = ra.SeleccionarRaizEntregable(con);
            periodo = cmbPeriodo.getSelectedItem().toString();
            aula = cmbSeccion.getSelectedItem().toString();
            mes = cmbMes.getSelectedItem().toString();
            ArrayList<ClsEntidadSubEntregable> ese = 
                    se.SeleccionarSubEntregables(codigoSeccion.get(cmbSeccion.getSelectedIndex()), con);
            String fila[]= new String[6];
            DefaultTableModel dtm = new DefaultTableModel(null,titulos){
                @Override
                public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
            };
            for(ClsEntidadSubEntregable e : ese)
            {                   
                fila[0] = e.getSubentregable_id();
                fila[1] = e.getSubentregable_nombre();  
                File folder = new File(url+"/"+periodo+"/"+aula+"/"+fila[1]+"/"
                        +numeroMes.get(cmbMes.getSelectedIndex())+"."+mes);
                int a = listFilesForFolder(folder);
                fila[2] = String.valueOf(a);
                fila[3] = e.getSubentregable_estado();
                fila[4] = e.getSubentregable_mensaje();
                dtm.addRow(fila);
            }            
            tblEstado.setModel(dtm);
            tblEstado.removeColumn(tblEstado.getColumnModel().getColumn(0)); 
            //CENRAR COLUMNAS Y FILAS (ClsMisc)
            TableCellRenderer tcr =  tblEstado.getTableHeader().getDefaultRenderer();                       
            ClsMisc.miRender mr = new ClsMisc.miRender(tcr);
            mr.getTableCellRendererComponent(tblEstado, fila, isSelected, isIcon, WIDTH, 1);
            mr.getTableCellRendererComponent(tblEstado, fila, isSelected, isIcon, WIDTH, 2);
            mr.getTableCellRendererComponent(tblEstado, fila, isSelected, isIcon, WIDTH, 3);
        } catch (Exception ex) {
            Logger.getLogger(FrmEntregablesEstado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     private int listFilesForFolder(final File folder) {
        int cont = 0;
        for (final File fileEntry : folder.listFiles()) 
        {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                System.out.println(fileEntry.getName());
                cont++;
            }
        }
        return cont;
    }
    
    private void cargaMes() {
        String mesi="";
        String mesf="";
        ClsMatMen mame = new ClsMatMen();   
        dcmMeses.removeAllElements();
        //obtener mes inicio mes fin del periodo                
        try 
        {
            con = ClsConexion.getConection();
            ResultSet rsPer = mame.SeleccionarFechasPeriodo(codigoPeriodo.get(cmbPeriodo.getSelectedIndex()),con);        
                       
            while (rsPer.next())
            {
                //mes de inicio de clases 
                mesi = rsPer.getString("inicio_periodo").substring(3, 5);
                //mes de fin periodo
                mesf = rsPer.getString("fin_periodo").substring(3, 5);
            }
            ClsConexion.closeConnection(con);
        } catch (Exception ex) 
        { Logger.getLogger(FrmEntregables.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int meses = obtenerMeses(mesi,mesf);        
        for(int j=0;j<=meses;j++)
        { 
            dcmMeses.addElement(month[(Integer.parseInt(mesi)-1)+j]);    
            numeroMes.add(String.valueOf(j+1));
        }  
        cmbMes.setModel(dcmMeses);
    }
    
    private void actualizarComboPeriodo() {
        
        codigoPeriodo.clear();       
        System.out.println("Personal ="+FrmPrincipal.idPersonalLogeado);
        //comprobar si es profesor        
        ClsAnecdotario ane = new ClsAnecdotario();
        con = ClsConexion.getConection();        
        ArrayList<ClsEntidadSeccion> seccion = ane.ListarSeccionProfesor(FrmPrincipal.idPersonalLogeado, con);
        ClsConexion.closeConnection(con);        
        DefaultComboBoxModel dcbPeriodo = new DefaultComboBoxModel();
        dcbPeriodo.removeAllElements();
        cmbPeriodo.removeAllItems();
        Iterator it = seccion.iterator();
        String periodo="";        
        while(it.hasNext())
        {           
            ClsEntidadSeccion Seccion;
            Seccion = (ClsEntidadSeccion) it.next();
            //periodo                       
            if(!periodo.equals(Seccion.getNombre_Periodo()))
            {
                dcbPeriodo.addElement(Seccion.getNombre_Periodo()); 
                codigoPeriodo.add(Seccion.getId_Periodo());    
                periodo = Seccion.getNombre_Periodo();               
            }                    
        }
        cmbPeriodo.setModel(dcbPeriodo);
        
    }
    
    private void actualizarComboSeccion() {
        
        codigoSeccion.clear();       
        System.out.println("Personal ="+FrmPrincipal.idPersonalLogeado);
        //comprobar si es profesor        
        ClsAnecdotario ane = new ClsAnecdotario();
        con = ClsConexion.getConection();        
        ArrayList<ClsEntidadSeccion> seccion = ane.ListarSeccionProfesor(FrmPrincipal.idPersonalLogeado, con);
        ClsConexion.closeConnection(con);
        DefaultComboBoxModel dcbSeccion = new DefaultComboBoxModel();
        dcbSeccion.removeAllElements();
        cmbSeccion.removeAllItems();
        Iterator it = seccion.iterator();
        String sec="";        
        while(it.hasNext())
        {           
            ClsEntidadSeccion Seccion;
            Seccion = (ClsEntidadSeccion) it.next();
            //seccion      
            if(!sec.equals(Seccion.getNombre_Seccion()))
            {                
                if(codigoPeriodo.get(cmbPeriodo.getSelectedIndex()).equals(Seccion.getId_Periodo()))
                {
                    dcbSeccion.addElement(Seccion.getNombre_Seccion()); 
                    codigoSeccion.add(Seccion.getId_Seccion());
                    sec = Seccion.getNombre_Seccion();
                }                
            }
        }
        cmbSeccion.setModel(dcbSeccion);
        
    }
    
        
    private void actualizarArbol() {
        
        try{
        String nombrePeriodo = cmbPeriodo.getSelectedItem().toString();
        String nombreSeccion = cmbSeccion.getSelectedItem().toString();
        String rutaSeccion = rutaDestino+"/"+nombrePeriodo+"/"+nombreSeccion;
        JTreeFile jtf = new JTreeFile();
        jtf.setJTree(treEntregable);
        jtf.init(nombreSeccion,rutaSeccion);
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "El aula no tiene asignda una carpeta");
        }
        
    }    
    
    private String cargarRaiz() {
        
        con = ClsConexion.getConection();
        ClsRaices raiz = new ClsRaices();
        String texto="";        
        try 
        {
            texto = raiz.SeleccionarRaizEntregable(con);
            
        } catch (Exception ex) {
            Logger.getLogger(FrmEntregables.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ClsConexion.closeConnection(con);
        }                  
        return texto;
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDescargar;
    private javax.swing.JButton btnEstado;
    private javax.swing.JButton btnFile;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSalir1;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JComboBox<String> cmbMes;
    private javax.swing.JComboBox cmbPeriodo;
    private javax.swing.JComboBox cmbSeccion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblEstado;
    private javax.swing.JTree treEntregable;
    private javax.swing.JTextField txtArchivo;
    private javax.swing.JTextArea txtComentario;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}