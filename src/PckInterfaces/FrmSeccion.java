/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckInterfaces;

import PckConexion.ClsConexion;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;
import PckEntidad.*;
import PckNegocio.*;
import static java.awt.image.ImageObserver.WIDTH;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import prymatricula.ClsMisc;
import static prymatricula.ClsMisc.obtenerAnioActual;
/**
 *
 * @author kmv
 */
public class FrmSeccion extends javax.swing.JInternalFrame {

ArrayList<String> codigoPeriodo = new ArrayList();
ArrayList<String> codigoProfesora = new ArrayList();
ArrayList<String> codigoAux1 = new ArrayList();
ArrayList<String> codigoAux2 = new ArrayList();
Connection con = null;
String codSeccion;

    public FrmSeccion() {
        initComponents();
        String seleccionado = llenaCombo();
        cmbAnio.setSelectedItem(seleccionado);
        cargarComboPeriodo();
        cargarComboProfesora();
        cargarComboAux1();
        cargarComboAux2();
        
        activaBotones(true,false,false,true);
        desactivaCampos();
        actualizarTabla();
       
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
    private void cargarComboPeriodo()
    {
        ClsPeriodo periodos = new ClsPeriodo();
        ArrayList<ClsEntidadPeriodo> periodo =  periodos.ListarPeriodo();
        Iterator iterator = periodo.iterator();
        DefaultComboBoxModel DefaultComboBoxModel = new DefaultComboBoxModel();
        DefaultComboBoxModel.removeAllElements();
        cmbPeriodo.removeAllItems();
        String fila[] = new String[4];

        while(iterator.hasNext())
        {
        ClsEntidadPeriodo Periodo;
        Periodo = (ClsEntidadPeriodo) iterator.next();               
        fila[0] = Periodo.getNombre_Periodo();
        fila[1] = Periodo.getEstado_Periodo();        
            if(fila[1].equals("Activo"))
            {   
            codigoPeriodo.add(Periodo.getId_Periodo());     
            DefaultComboBoxModel.addElement(fila[0]);  
            }        
        }
        cmbPeriodo.setModel(DefaultComboBoxModel);
        //cambia estado del combo a activado

    }
    
    private void cargarComboProfesora()
    {
        codigoProfesora.clear();
        ClsPersonal personas = new ClsPersonal();
        con = ClsConexion.getConection();
        ArrayList<ClsEntidadPersonal> persona =  personas.SeleccionarPersonalNombre(con);
        Iterator it = persona.iterator();
        DefaultComboBoxModel dcm = new DefaultComboBoxModel();
        dcm.removeAllElements();
        cmbProfesora.removeAllItems();
        String fila[] = new String[3];

        while(it.hasNext())
        {
            ClsEntidadPersonal Persona = (ClsEntidadPersonal) it.next();
            if(Persona.getPersonal_estado().equals("A")) {
                codigoProfesora.add(Persona.getPersonal_id());
                fila[0] = Persona.getPersonal_nombre();
                dcm.addElement(fila[0]);     
            }
                  

        }
        ClsConexion.closeConnection(con);
        cmbProfesora.setModel(dcm);
    }
    
    private void cargarComboAux1()
    {
        codigoAux1.clear();
        ClsPersonal personas = new ClsPersonal();
        con = ClsConexion.getConection();
        ArrayList<ClsEntidadPersonal> persona =  personas.SeleccionarPersonalNombre(con);
        Iterator it = persona.iterator();
        DefaultComboBoxModel dcm = new DefaultComboBoxModel();
        dcm.removeAllElements();
        cmbAux1.removeAllItems();
        String fila[] = new String[3];

        while(it.hasNext())
        {
            ClsEntidadPersonal Persona = (ClsEntidadPersonal) it.next();
            if(Persona.getPersonal_estado().equals("A")) {
                codigoAux1.add(Persona.getPersonal_id());
                fila[0] = Persona.getPersonal_nombre();
                dcm.addElement(fila[0]); 
            }
                      

        }
        ClsConexion.closeConnection(con);
        cmbAux1.setModel(dcm);
    }
    
    private void cargarComboAux2()
    {
        codigoAux2.clear();
        ClsPersonal personas = new ClsPersonal();
        con = ClsConexion.getConection();
        ArrayList<ClsEntidadPersonal> persona =  personas.SeleccionarPersonalNombre(con);
        Iterator it = persona.iterator();
        DefaultComboBoxModel dcm = new DefaultComboBoxModel();
        dcm.removeAllElements();
        cmbAux2.removeAllItems();
        String fila[] = new String[3];

        while(it.hasNext())
        {
            ClsEntidadPersonal Persona = (ClsEntidadPersonal) it.next();
            if(Persona.getPersonal_estado().equals("A")) {
                codigoAux2.add(Persona.getPersonal_id());
                fila[0] = Persona.getPersonal_nombre();
                dcm.addElement(fila[0]);       
            }
                 

        }
        ClsConexion.closeConnection(con);
        cmbAux2.setModel(dcm);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox2 = new javax.swing.JCheckBox();
        panSeccion = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblSecNombre = new javax.swing.JLabel();
        lblPlaza = new javax.swing.JLabel();
        lblPeriodo = new javax.swing.JLabel();
        txtSecNombre = new javax.swing.JTextField();
        txtSecPlaza = new javax.swing.JTextField();
        cmbPeriodo = new javax.swing.JComboBox();
        lblProfesora = new javax.swing.JLabel();
        cmbProfesora = new javax.swing.JComboBox();
        cmbAux1 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        cmbAux2 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cbxAsistencia = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtAlumnos = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtRestante = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSeccion = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        cmbAnio = new javax.swing.JComboBox();

        jCheckBox2.setText("jCheckBox2");

        setTitle("Aula");

        panSeccion.setBackground(new java.awt.Color(255, 255, 255));
        panSeccion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones"));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnNuevo.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIconos/ic_library_add_black_24dp.png"))); // NOI18N
        btnNuevo.setToolTipText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 40, 40));

        btnModificar.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIconos/ic_mode_edit_black_24dp.png"))); // NOI18N
        btnModificar.setToolTipText("Editar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 40, 40));

        btnGuardar.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIconos/ic_save_black_24dp.png"))); // NOI18N
        btnGuardar.setToolTipText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 40, 40));

        btnEliminar.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIconos/ic_delete_forever_black_24dp.png"))); // NOI18N
        btnEliminar.setToolTipText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 40, 40));

        btnSalir.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIconos/ic_exit_to_app_black_24dp.png"))); // NOI18N
        btnSalir.setToolTipText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 40, 40));

        panSeccion.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 60, 190, 190));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSecNombre.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        lblSecNombre.setText("Nombre:");
        jPanel2.add(lblSecNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, 20));

        lblPlaza.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        lblPlaza.setText("Vacantes:");
        jPanel2.add(lblPlaza, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, 20));

        lblPeriodo.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        lblPeriodo.setText("Periodo:");
        jPanel2.add(lblPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, 20));

        txtSecNombre.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtSecNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSecNombreFocusLost(evt);
            }
        });
        jPanel2.add(txtSecNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 170, -1));

        txtSecPlaza.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jPanel2.add(txtSecPlaza, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 50, -1));

        cmbPeriodo.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbPeriodo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(cmbPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 170, -1));

        lblProfesora.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        lblProfesora.setText("Profesora:");
        jPanel2.add(lblProfesora, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, -1, 20));

        cmbProfesora.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbProfesora.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Lourdes Cabezudo", "Luz Merina", "Edith Peralta", "Cinthia Condori", "Dalila Torres", "Fiorella Gonza", "Paty Huere", "Ana Soto", "Yuri Paulsen", "Yennifer Calderon", "Itzel Bottger", "Mariela Veliz", "Yenny Calderon", "Evelyn Ayma", "Vanessa Peralta" }));
        jPanel2.add(cmbProfesora, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, 260, -1));

        cmbAux1.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbAux1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbAux1.setEnabled(false);
        jPanel2.add(cmbAux1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 260, -1));

        jLabel1.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel1.setText("1er Auxiliar:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, -1, 20));

        cmbAux2.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbAux2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbAux2.setEnabled(false);
        jPanel2.add(cmbAux2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 120, 260, -1));

        jLabel2.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel2.setText("2do Auxiliar:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, -1, 20));

        cbxAsistencia.setBackground(new java.awt.Color(255, 255, 255));
        cbxAsistencia.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cbxAsistencia.setText("Aula habilitada para tomar asistencia");
        cbxAsistencia.setEnabled(false);
        jPanel2.add(cbxAsistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        panSeccion.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 670, 200));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Aulas"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setText("Alumnos Matriculados:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 120, 20));

        txtAlumnos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtAlumnos.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        txtAlumnos.setEnabled(false);
        jPanel3.add(txtAlumnos, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 340, 30, -1));

        jLabel3.setText("Vacantes Restantes:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 340, -1, 20));

        txtRestante.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtRestante.setEnabled(false);
        jPanel3.add(txtRestante, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 340, 30, 20));

        tblSeccion.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        tblSeccion.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSeccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSeccionMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSeccion);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 850, 290));

        jLabel4.setText("Año:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, 20));

        cmbAnio.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbAnio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021" }));
        cmbAnio.setSelectedIndex(2);
        cmbAnio.setToolTipText("");
        cmbAnio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbAnioItemStateChanged(evt);
            }
        });
        jPanel3.add(cmbAnio, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 26, 110, -1));

        panSeccion.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 890, 370));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panSeccion, javax.swing.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panSeccion, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
      this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
       activaCampos();
       activaBotones(true,true,false,true);
       cbxAsistencia.setEnabled(true);
       limpiaCampos();
       txtSecNombre.requestFocus();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void tblSeccionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSeccionMouseClicked
       
        int reg;
        DefaultTableModel defaultTableModel;
        reg = tblSeccion.getSelectedRow();
        if(reg == -1)
        {  JOptionPane.showMessageDialog(null, "Debe Seleccionar un registro");   }
        else{
            defaultTableModel = (DefaultTableModel)tblSeccion.getModel();
            codSeccion = ((String)defaultTableModel.getValueAt(reg, 0));
            txtSecNombre.setText((String)defaultTableModel.getValueAt(reg, 1));
            txtSecPlaza.setText((String)defaultTableModel.getValueAt(reg, 2));
            cmbProfesora.setSelectedItem((String)defaultTableModel.getValueAt(reg, 3));
            cmbAux1.setSelectedItem((String)defaultTableModel.getValueAt(reg, 4));
            cmbAux2.setSelectedItem((String)defaultTableModel.getValueAt(reg, 5));
            String aux = (String)defaultTableModel.getValueAt(reg, 6);
            if(aux.equals("SI"))
            {
                cbxAsistencia.setSelected(true);
            }
            else
            {
                cbxAsistencia.setSelected(false);
            }            
            cmbPeriodo.setSelectedItem((String)defaultTableModel.getValueAt(reg, 7));           
            //miseccion = (String)defaultTableModel.getValueAt(reg, 1);                   
        }   
        ResultSet rsAlu;
        ClsSeccion s = new ClsSeccion();
            try 
            {
                rsAlu = s.ListaMatriculados(codSeccion);
                while(rsAlu.next())
                {                   
                    txtAlumnos.setText(rsAlu.getString("alumnos"));
                }
            } 
            catch (Exception ex) 
            {
                Logger.getLogger(FrmPeriodo.class.getName()).log(Level.SEVERE, null, ex);
                txtAlumnos.setText("0");
            }
            
        int aux = Integer.parseInt(txtSecPlaza.getText())- Integer.parseInt(txtAlumnos.getText()); 
        txtRestante.setText(String.valueOf(aux));
        activaBotones(true,false,true,true); 
        activaCampos();
        cbxAsistencia.setEnabled(true);
    
    }//GEN-LAST:event_tblSeccionMouseClicked

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
     
        if(txtSecNombre.getText().equals("") || txtSecPlaza.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Por favor llene los campos necesarios");
            txtSecNombre.requestFocus();
        } 
        else
        { 
            ClsSeccion secciones = new ClsSeccion();
            ClsEntidadSeccion seccion = new ClsEntidadSeccion();
            seccion.setId_Periodo(codigoPeriodo.get(cmbPeriodo.getSelectedIndex()));
            seccion.setNombre_Seccion(txtSecNombre.getText());
            seccion.setCantidad_Seccion(txtSecPlaza.getText());
            if(cbxAsistencia.isSelected())
            {
                seccion.setAsistencia_Seccion("1");
            }
            else
            {
                seccion.setAsistencia_Seccion("0");
            }            
            seccion.setId_Profesor(codigoProfesora.get(cmbProfesora.getSelectedIndex()));
            seccion.setId_Auxiliar1(codigoAux1.get(cmbAux1.getSelectedIndex()));
            seccion.setId_Auxiliar2(codigoAux2.get(cmbAux2.getSelectedIndex()));
            secciones.AgregarSeccion(seccion);
            desactivaCampos();  
            activaBotones(true,false,false,true);
            actualizarTabla();
            limpiaCampos();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        ClsSeccion secciones = new ClsSeccion();
        ClsEntidadSeccion seccion = new ClsEntidadSeccion();       
        seccion.setId_Periodo(codigoPeriodo.get(cmbPeriodo.getSelectedIndex()));        
        seccion.setNombre_Seccion(txtSecNombre.getText());        
        seccion.setCantidad_Seccion(txtSecPlaza.getText());    
        if(cbxAsistencia.isSelected())
        {
            seccion.setAsistencia_Seccion("1");
        }
        else
        {
            seccion.setAsistencia_Seccion("0");
        }        
        seccion.setId_Profesor(codigoProfesora.get(cmbProfesora.getSelectedIndex()));        
        seccion.setId_Auxiliar1(codigoAux1.get(cmbAux1.getSelectedIndex()));        
        seccion.setId_Auxiliar2(codigoAux2.get(cmbAux2.getSelectedIndex()));
        secciones.ModificarSeccion(codSeccion, seccion);
        desactivaCampos();  
        activaBotones(true,false,false,true);
        limpiaCampos();
        actualizarTabla();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void txtSecNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSecNombreFocusLost
       txtSecNombre.setText(convertirMayu(txtSecNombre.getText()));
    }//GEN-LAST:event_txtSecNombreFocusLost

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro de Eliminar el Registro?","Eliminar",0);
        if(respuesta == 0)
        {
            ClsSeccion secciones = new ClsSeccion();
            secciones.EliminarSeccion(codSeccion);
            activaBotones(true,false,false,true);
            actualizarTabla();
            desactivaCampos();
            limpiaCampos();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void cmbAnioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbAnioItemStateChanged
        if(cmbAnio.getSelectedIndex() != -1)
        {
            actualizarTabla();
        }
    }//GEN-LAST:event_cmbAnioItemStateChanged
    String convertirMayu(String texto)
    {
    String text;
    text =texto.toUpperCase();
    return text;
    }
    
    private void activaBotones(boolean n, boolean g, boolean m, boolean s)
    {
        btnNuevo.setEnabled(n);
        btnGuardar.setEnabled(g);
        btnModificar.setEnabled(m);
        btnEliminar.setEnabled(m);
        btnSalir.setEnabled(s);
    }
    private void limpiaCampos()
    {
        txtSecNombre.setText("");
        txtSecPlaza.setText("");
    }
    
    private void desactivaCampos()
    {
        txtSecNombre.setEnabled(false);
        txtSecPlaza.setEnabled(false);     
        cmbProfesora.setEnabled(false);
        cmbAux1.setEnabled(false);
        cmbAux2.setEnabled(false);
        cmbPeriodo.setEnabled(false);
       
    }
    
    private void activaCampos()
    {
        txtSecNombre.setEnabled(true);
        txtSecPlaza.setEnabled(true);    
        cmbProfesora.setEnabled(true);
        cmbAux1.setEnabled(true);
        cmbAux2.setEnabled(true);
        cmbPeriodo.setEnabled(true);        
        
    }
    private void actualizarTabla(){
        
        String titulos[] = {"ID","Nombre","Vacantes","Profesora","1er Auxiliar","2do Auxiliar","Asistencia","Periodo"};
        ClsSeccion secciones = new ClsSeccion();
        ArrayList<ClsEntidadSeccion> seccion = secciones.ListarSeccion();
        Iterator iterator = seccion.iterator();
        DefaultTableModel defaultTableModel = new DefaultTableModel(null,titulos){ 
        @Override
        public boolean isCellEditable(int rowIndex,int columnIndex){return false;} 
        }; 
        String fila[] = new String[11];
        while(iterator.hasNext()){
            ClsEntidadSeccion Seccion = new ClsEntidadSeccion();            
            Seccion = (ClsEntidadSeccion) iterator.next();            
            fila[0] = Seccion.getId_Seccion();            
            fila[1] = Seccion.getNombre_Seccion();
            fila[2] = Seccion.getCantidad_Seccion();           
            fila[3] = Seccion.getNombre_Profesora();
            fila[4] = Seccion.getNombre_Auxiliar1();
            fila[5] = Seccion.getNombre_Auxiliar2();
            if(Seccion.getAsistencia_Seccion().equals("1"))
            {
                fila[6] = "SI";
            }            
            else
            {
                fila[6] = "NO";
            }
            fila[7] = Seccion.getNombre_Periodo();
            fila[8] = Seccion.getEstado_Periodo();
            fila[9] = Seccion.getAnio_Periodo();
          
            if(fila[8].equals("Activo") && cmbAnio.getSelectedItem().equals(fila[9])) 
            {
            defaultTableModel.addRow(fila);
            }            
        }
        tblSeccion.setModel(defaultTableModel);
        tblSeccion.setModel(defaultTableModel);
        tblSeccion.removeColumn(tblSeccion.getColumnModel().getColumn(0));
        
        TableColumn  as =tblSeccion.getColumnModel().getColumn(2);
        as.setPreferredWidth(120);
        TableColumn  aw =tblSeccion.getColumnModel().getColumn(3);
        aw.setPreferredWidth(120);
        TableColumn  aq =tblSeccion.getColumnModel().getColumn(4);
        aq.setPreferredWidth(120);
        
        TableCellRenderer tcr =  tblSeccion.getTableHeader().getDefaultRenderer();                       
        ClsMisc.miRender mr = new ClsMisc.miRender(tcr);
        mr.getTableCellRendererComponent(tblSeccion, fila, isSelected, isIcon, WIDTH, 1);
        mr.getTableCellRendererComponent(tblSeccion, fila, isSelected, isIcon, WIDTH, 5);
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JCheckBox cbxAsistencia;
    private javax.swing.JComboBox cmbAnio;
    private javax.swing.JComboBox cmbAux1;
    private javax.swing.JComboBox cmbAux2;
    private javax.swing.JComboBox cmbPeriodo;
    private javax.swing.JComboBox cmbProfesora;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPeriodo;
    private javax.swing.JLabel lblPlaza;
    private javax.swing.JLabel lblProfesora;
    private javax.swing.JLabel lblSecNombre;
    private javax.swing.JPanel panSeccion;
    private javax.swing.JTable tblSeccion;
    private javax.swing.JTextField txtAlumnos;
    private javax.swing.JTextField txtRestante;
    private javax.swing.JTextField txtSecNombre;
    private javax.swing.JTextField txtSecPlaza;
    // End of variables declaration//GEN-END:variables
}
