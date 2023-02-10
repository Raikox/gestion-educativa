/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckInterfaces;



import PckConexion.ClsConexion;
import PckEntidad.ClsEntidadAlumnoPago;
import PckEntidad.ClsEntidadNivel;
import PckEntidad.ClsEntidadPeriodo;
import PckEntidad.ClsEntidadSeccion;
import PckEntidad.ClsEntidadServicio;
import PckNegocio.ClsAlumno;
import PckNegocio.ClsNivel;
import PckNegocio.ClsPeriodo;
import PckNegocio.ClsSeccion;
import PckNegocio.ClsServicio;
import java.awt.event.KeyEvent;
import static java.awt.image.ImageObserver.WIDTH;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import prymatricula.ClsMisc;
import static prymatricula.ClsMisc.obtenerAnioActual;

/**
 *
 * @author Kevin Montes de Oca
 */
public class FrmServicio extends javax.swing.JInternalFrame {

String strCodigo="";
String nivel="";
String periodo="";
String seccion="";
String StrCodAlu="";
String nombre ="";
String venServicio="";
String actServicio="";
SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
Connection con = null;
//Listas para guardar codigos de los combos
  ArrayList<String> codigoNivel = new ArrayList();
  ArrayList<String> codigoPeriodo = new ArrayList();
  ArrayList<String> codigoSeccion = new ArrayList();
  ArrayList<String> codigoPeriodo2 = new ArrayList();
  ArrayList<String> codigoSeccion2 = new ArrayList();
  

      
    /**
     * Creates new form FrmServicio
     */
    public FrmServicio() {
        initComponents();
        grupo.add(radioPeriodo);
        grupo.add(radioSeccion);
        grupo.add(radioAlumno);
        
        String seleccionado = llenaCombo();
        cmbAnio.setSelectedItem(seleccionado); 
        cmbAnioo.setSelectedItem(seleccionado);
        
        Date abc = new Date();
        jdcFecha.setDate(abc);
        jdcFechag.setDate(abc);
        
        desactivaRadio();
        desactivaAsignacion();
        desactivaAlumno();        
        
        
        txtDescripcion.setLineWrap(true);
        desactivaCampos();               
        cmbAnio.setSelectedItem(obtenerAnioActual()); 
        actualizarTablaServicio();
        activaBotones(true,false,false,true,false);
        
        //carga los combosde asignacion 
        cargaComboNivel();
        cargaComboPeriodo();
        if(cmbPeriodo.getSelectedIndex() != -1)
        {            
            cargaComboSeccion();
        }
        
        cargaPeriodoA();
        cargaSeccionA();
        
        if(!codigoSeccion.isEmpty())
        {
           actualizarTablaAlumno();  
        }
 
        
        if(!FrmPrincipal.mantenimientoServicio)            
        {
            desactivarBotones();
        }
        
        
        if(FrmPrincipal.asignarServicio)
        {
            tbdServicio.setEnabledAt(1, true);
        }
        else
        {
            tbdServicio.setEnabledAt(1, false);
        }
        
    }
    private void desactivarBotones()
    {
        btnNuevo.setEnabled(false);
        btnModificar.setEnabled(false);
        btnAgregar.setEnabled(false);
    }
    
    private void desactivaRadio()
    {   
        radioPeriodo.setEnabled(false);
        radioSeccion.setEnabled(false);
        radioAlumno.setEnabled(false);
    }
    
    
    private void cargaPeriodoA()
    {
        ClsPeriodo periodos = new ClsPeriodo();
        ArrayList<ClsEntidadPeriodo> periodo =  periodos.ListarPeriodo();
        Iterator iterator = periodo.iterator();
        DefaultComboBoxModel DefaultComboBoxModel = new DefaultComboBoxModel();
        DefaultComboBoxModel.removeAllElements();
        cmbPeriodo2.removeAllItems();
        String fila[] = new String[4];     
        while(iterator.hasNext())
        {
        ClsEntidadPeriodo Periodo = new ClsEntidadPeriodo();
        Periodo = (ClsEntidadPeriodo) iterator.next();               
        fila[0] = Periodo.getNombre_Periodo();
        fila[1] = Periodo.getEstado_Periodo();        
            if(Periodo.getEstado_Periodo().equals("Activo"))
            {   
            codigoPeriodo2.add(Periodo.getId_Periodo());     
            DefaultComboBoxModel.addElement(Periodo.getNombre_Periodo());        
            }        
        }
        cmbPeriodo2.setModel(DefaultComboBoxModel);
        //cambia estado del combo a lleno
        //conComboPeriodo2=1;
    }
    
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
        ClsEntidadNivel Nivel = new ClsEntidadNivel();
        Nivel = (ClsEntidadNivel) it.next();       
        codigoNivel.add(Nivel.getNivel_id());
        dtm.addElement(Nivel.getNivel_nombre());                
        }
        cmbNivel.setModel(dtm); 
    }
    
    private void desactivaAsignacion()
    {    
        cmbNivel.setEnabled(false);
        cmbPeriodo.setEnabled(false);
        cmbSeccion.setEnabled(false);
    }
    
    private void cargaComboPeriodo()
    {
        codigoPeriodo.clear();
        ClsPeriodo periodos = new ClsPeriodo();
        ArrayList<ClsEntidadPeriodo> pperiodo =  periodos.ListarPeriodo();
        Iterator iterator = pperiodo.iterator();
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
        fila[2] = Periodo.getNivel_Id();
        int z = cmbNivel.getSelectedIndex();
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
    
    private void actualizarTablaAlumno()
    {
        String titulos[] = {"ID","N°","Apellidos y nombres"};
        con = ClsConexion.getConection();
        ClsAlumno alumnos = new ClsAlumno();
        ArrayList<ClsEntidadAlumnoPago> alumno = alumnos.ListarAlumnoPago(con);
        Iterator iterator = alumno.iterator();
        DefaultTableModel defaultTableModel =new DefaultTableModel(null,titulos){
        public boolean isCellEditable(int rowIndex,int columnIndex){return false;} 
        };                 
        String fila[] = new String[12];                
        int aux=1;
        while(iterator.hasNext())
        {
            ClsEntidadAlumnoPago Alumno = new ClsEntidadAlumnoPago();
            Alumno = (ClsEntidadAlumnoPago) iterator.next();
            fila[0] = Alumno.getId_Matricula();
            fila[1] = String.valueOf(aux);
            fila[2] = Alumno.getApellidos_Nombres();
            fila[3] = Alumno.getId_Seccion(); 
            
           
            if(fila[3].equals(codigoSeccion2.get(cmbSeccion2.getSelectedIndex())))
            {
                aux++;
                defaultTableModel.addRow(fila);                
            }
        }
        ClsConexion.closeConnection(con);
        tblAlumno.setModel(defaultTableModel);
        tblAlumno.removeColumn(tblAlumno.getColumnModel().getColumn(0));
        TableColumn  columnaPer =tblAlumno.getColumnModel().getColumn(0);
        columnaPer.setPreferredWidth(10);
        TableColumn  columnaP =tblAlumno.getColumnModel().getColumn(1);
        columnaP.setPreferredWidth(200);
        TableCellRenderer tcr =  tblAlumno.getTableHeader().getDefaultRenderer();                       
        ClsMisc.miRender mr = new ClsMisc.miRender(tcr);
        mr.getTableCellRendererComponent(tblAlumno, fila, isSelected, isIcon, WIDTH, 0);
    }
    private void desactivaAlumno()
    {
    cmbPeriodo2.setEnabled(false);
    cmbSeccion2.setEnabled(false);
    tblAlumno.setEnabled(false);
    }
    private void cargaSeccionA()
    {
        codigoSeccion2.clear();
        ClsSeccion secciones = new ClsSeccion();
        ArrayList<ClsEntidadSeccion> seccion =  secciones.ListarSeccion();
        Iterator it = seccion.iterator();
        DefaultComboBoxModel DefaultComboBoxModel = new DefaultComboBoxModel();
        DefaultComboBoxModel.removeAllElements();
        cmbSeccion2.removeAllItems();
        String fila2[] = new String[5];        
        while(it.hasNext())
        {
        ClsEntidadSeccion Seccion = (ClsEntidadSeccion) it.next();             
        fila2[0] = Seccion.getNombre_Seccion();
        fila2[1] = Seccion.getEstado_Periodo();       
        fila2[3] = Seccion.getNombre_Periodo();
        
            if (codigoPeriodo2.get(cmbPeriodo2.getSelectedIndex()).equals(Seccion.getId_Periodo())) 
            {
                codigoSeccion2.add(Seccion.getId_Seccion());
                DefaultComboBoxModel.addElement(fila2[0]);
            }       
        }
        cmbSeccion2.setModel(DefaultComboBoxModel); 
    }
    
    private void activaItem(boolean nivel,boolean periodo,boolean seccion)
    {
    cmbNivel.setEnabled(nivel);
    cmbPeriodo.setEnabled(periodo);
    cmbSeccion.setEnabled(seccion);    
    }
    
    private void activaAlumno()
    {
    cmbPeriodo2.setEnabled(true);
    cmbSeccion2.setEnabled(true);
    tblAlumno.setEnabled(true);
    }
    
    private void actualizarTablaAlumnoBusqueda(String texto)
    {
        int p=1;
        String titulos[] = {"ID","N°","Apellidos y nombres","Sección","Periodo"};
        con = ClsConexion.getConection();
        ClsAlumno alumnos = new ClsAlumno();
        ArrayList<ClsEntidadAlumnoPago> alumnoo = alumnos.ListarAlumnoBusqueda(texto,con);
        Iterator iterator = alumnoo.iterator();
        DefaultTableModel defaultTableModel =new DefaultTableModel(null,titulos);               
        String fila[] = new String[12];                
        while(iterator.hasNext())
        {
            ClsEntidadAlumnoPago Alumno;
            Alumno = (ClsEntidadAlumnoPago) iterator.next();
            fila[0] = Alumno.getId_Matricula();
            fila[1] = String.valueOf(p);
            fila[2] = Alumno.getApellidos_Nombres();
            fila[3] = Alumno.getNombre_Seccion();
            fila[4] = Alumno.getNombre_Periodo();

                p++;
                defaultTableModel.addRow(fila);                

        }         
        ClsConexion.closeConnection(con);
        tblAlumno.setModel(defaultTableModel);
        tblAlumno.removeColumn(tblAlumno.getColumnModel().getColumn(0));
        TableColumn  columnaN =tblAlumno.getColumnModel().getColumn(0);
        columnaN.setPreferredWidth(12);
        TableColumn  columnaPer =tblAlumno.getColumnModel().getColumn(1);
        columnaPer.setPreferredWidth(150);
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
        tbdServicio = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        cmbAnioo = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblServicio = new javax.swing.JTable();
        cmbAnio = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        cmbSeccion2 = new javax.swing.JComboBox();
        cmbPeriodo2 = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAlumno = new javax.swing.JTable();
        radioAlumno = new javax.swing.JRadioButton();
        jPanel9 = new javax.swing.JPanel();
        txtBusqueda = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        cmbNivel = new javax.swing.JComboBox();
        cmbPeriodo = new javax.swing.JComboBox();
        cmbSeccion = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        radioSeccion = new javax.swing.JRadioButton();
        radioPeriodo = new javax.swing.JRadioButton();
        jPanel10 = new javax.swing.JPanel();
        jdcFecha = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jdcFechag = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        btnGenerar = new javax.swing.JButton();
        btnSalir1 = new javax.swing.JButton();

        setTitle("Servicios/Cuotas");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbdServicio.setBackground(new java.awt.Color(255, 255, 255));
        tbdServicio.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Servicio"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombre.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });
        jPanel2.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 140, -1));

        txtPrecio.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioKeyTyped(evt);
            }
        });
        jPanel2.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 140, -1));

        jLabel2.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel2.setText("Precio:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, 20));

        jLabel1.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel1.setText("Nombre:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, 20));

        jLabel4.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel4.setText("Descripción:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, -1, -1));

        txtDescripcion.setColumns(20);
        txtDescripcion.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtDescripcion.setRows(5);
        txtDescripcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel2.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, -1, 110));

        jLabel12.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel12.setText("Año:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, 20));

        cmbAnioo.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbAnioo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbAniooItemStateChanged(evt);
            }
        });
        jPanel2.add(cmbAnioo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 110, -1));

        jPanel5.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 520, 160));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Servicios"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblServicio.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        tblServicio.setModel(new javax.swing.table.DefaultTableModel(
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
        tblServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblServicioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblServicio);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 480, 220));

        cmbAnio.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbAnio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbAnioItemStateChanged(evt);
            }
        });
        jPanel4.add(cmbAnio, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 110, -1));

        jPanel5.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 520, 270));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnNuevo.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIconos/ic_library_add_black_24dp.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.setToolTipText("Nuevo");
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel3.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 50));

        btnAgregar.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIconos/ic_save_black_24dp.png"))); // NOI18N
        btnAgregar.setText("Guardar");
        btnAgregar.setToolTipText("Guardar");
        btnAgregar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAgregar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel3.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 80, 50));

        btnModificar.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIconos/ic_mode_edit_black_24dp.png"))); // NOI18N
        btnModificar.setText("Editar");
        btnModificar.setToolTipText("Editar");
        btnModificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnModificar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel3.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, -1, 50));

        btnSalir.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIconos/ic_exit_to_app_black_24dp.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setToolTipText("Salir");
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel3.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 60, 50));

        btnEliminar.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/ic_delete_forever_black_24dp.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setToolTipText("Eliminar");
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel3.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, -1, 50));

        jPanel5.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 520, 90));

        tbdServicio.addTab("Servicio", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Asignar por Alumno"));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel9.setText("Seleccione Sección:");
        jPanel7.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, 20));

        cmbSeccion2.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbSeccion2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSeccion2ItemStateChanged(evt);
            }
        });
        jPanel7.add(cmbSeccion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 220, -1));

        cmbPeriodo2.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbPeriodo2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbPeriodo2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPeriodo2ItemStateChanged(evt);
            }
        });
        jPanel7.add(cmbPeriodo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 220, -1));

        jLabel8.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel8.setText("Seleccione Periodo:");
        jPanel7.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 120, 20));

        jScrollPane2.setPreferredSize(new java.awt.Dimension(300, 64));

        tblAlumno.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        tblAlumno.setModel(new javax.swing.table.DefaultTableModel(
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
        tblAlumno.setEnabled(false);
        tblAlumno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAlumnoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblAlumno);

        jPanel7.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 300, 350));

        radioAlumno.setBackground(new java.awt.Color(255, 255, 255));
        radioAlumno.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radioAlumnoItemStateChanged(evt);
            }
        });
        jPanel7.add(radioAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 0, 20, -1));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Búsqueda"));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtBusqueda.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtBusqueda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBusquedaMouseClicked(evt);
            }
        });
        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyTyped(evt);
            }
        });
        jPanel9.add(txtBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 240, -1));

        jPanel7.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 300, 50));

        jPanel6.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 340, 550));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Asignar por"));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmbNivel.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbNivel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "GUARDERIA", "INICIAL" }));
        cmbNivel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbNivelItemStateChanged(evt);
            }
        });
        jPanel8.add(cmbNivel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 118, -1));

        cmbPeriodo.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbPeriodo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbPeriodo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPeriodoItemStateChanged(evt);
            }
        });
        jPanel8.add(cmbPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 118, -1));

        cmbSeccion.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbSeccion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbSeccion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSeccionItemStateChanged(evt);
            }
        });
        jPanel8.add(cmbSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 118, -1));

        jLabel5.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel5.setText("Por Periodo:");
        jPanel8.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 70, 20));

        jLabel6.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel6.setText("Por Sección:");
        jPanel8.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, 20));

        jLabel7.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel7.setText("Nivel:");
        jPanel8.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 60, 20));

        radioSeccion.setBackground(new java.awt.Color(255, 255, 255));
        radioSeccion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radioSeccionItemStateChanged(evt);
            }
        });
        jPanel8.add(radioSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 20, -1));

        radioPeriodo.setBackground(new java.awt.Color(255, 255, 255));
        radioPeriodo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radioPeriodoItemStateChanged(evt);
            }
        });
        jPanel8.add(radioPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 20, -1));

        jPanel6.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 160, 220));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Asignación"));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jdcFecha.setEnabled(false);
        jdcFecha.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jPanel10.add(jdcFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 120, -1));

        jLabel10.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel10.setText("Fecha Vencimiento:");
        jPanel10.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 120, -1));

        jdcFechag.setEnabled(false);
        jdcFechag.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jPanel10.add(jdcFechag, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 120, -1));

        jLabel11.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel11.setText("Fecha Pedido:");
        jPanel10.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 100, -1));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnGenerar.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnGenerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/brightness-7.png"))); // NOI18N
        btnGenerar.setText("<html> <p>Generar</p> <p align=center>Deuda</p> </html>");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });
        jPanel11.add(btnGenerar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 100, -1));

        btnSalir1.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnSalir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIconos/ic_exit_to_app_black_24dp.png"))); // NOI18N
        btnSalir1.setText("Salir");
        btnSalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir1ActionPerformed(evt);
            }
        });
        jPanel11.add(btnSalir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 100, 40));

        jPanel10.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 120, 120));

        jPanel6.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 260, 160, 310));

        tbdServicio.addTab("Asignar", jPanel6);

        jPanel1.add(tbdServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 560, 630));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        txtNombre.setText(convertirMayu(txtNombre.getText()));
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyTyped
        char c = evt.getKeyChar();
        if (txtPrecio.getText().length()== 7 || !(Character.isDigit(c) ||
            (c == KeyEvent.VK_BACK_SPACE) ||
            (c == KeyEvent.VK_DELETE)||
            (c == KeyEvent.VK_PERIOD) ))
    {
        evt.consume();
        }
    }//GEN-LAST:event_txtPrecioKeyTyped

    private void tblServicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblServicioMouseClicked
        int reg;
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        reg = tblServicio.getSelectedRow();
        if(reg == -1)
        { JOptionPane.showMessageDialog(null, "Debe Seleccionar un registro");
        }
        else
        {
            try {
                defaultTableModel = (DefaultTableModel)tblServicio.getModel();
                strCodigo = ((String)defaultTableModel.getValueAt(reg, 0));
                txtNombre.setText((String) defaultTableModel.getValueAt(reg, 1));
                txtPrecio.setText((String)defaultTableModel.getValueAt(reg, 2));
                txtDescripcion.setText((String)defaultTableModel.getValueAt(reg, 3));
                cmbAnioo.setSelectedItem((String)defaultTableModel.getValueAt(reg, 4)); 
            } catch (Exception ex) {
                Logger.getLogger(FrmPeriodo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(!FrmPrincipal.mantenimientoServicio)            
            {
                desactivarBotones();
                activaRadio();
            }
            else
            {
                activaBotones(true,false,true,true,true);  
                
                activaCampos();               
                activaRadio();
            }
        }        
    }//GEN-LAST:event_tblServicioMouseClicked

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        txtNombre.requestFocus();
        activaBotones(true,true,false,true,false);
        activaCampos();
        limpiaCampos();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        ClsEntidadServicio es = new ClsEntidadServicio();
        ClsServicio s = new ClsServicio();

        es.setNombre_Servicio(txtNombre.getText());
        es.setPrecio_Servicio(txtPrecio.getText());
        es.setDescripcion_Servicio(txtDescripcion.getText());
        es.setAnio_Servicio(cmbAnioo.getSelectedItem().toString()); 
        s.AgregarServicio(es);

        activaBotones(true,false,false,true,false);
        limpiaCampos();
        actualizarTablaServicio();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        ClsEntidadServicio es = new ClsEntidadServicio();
        ClsServicio s = new ClsServicio();

        es.setNombre_Servicio(txtNombre.getText());
        es.setPrecio_Servicio(txtPrecio.getText());
        es.setDescripcion_Servicio(txtDescripcion.getText());
        es.setAnio_Servicio(cmbAnioo.getSelectedItem().toString());
        s.ModificarServicio(es,strCodigo);

        activaBotones(true,false,false,true,false);
        limpiaCampos();
        actualizarTablaServicio();
        desactivaCampos();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void cmbAnioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbAnioItemStateChanged
        if(cmbAnio.getSelectedIndex() != -1) //solo cargara si el index no es -1
        {            
            actualizarTablaServicio();
        }
      
    }//GEN-LAST:event_cmbAnioItemStateChanged

    private void cmbAniooItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbAniooItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbAniooItemStateChanged

    private void cmbSeccion2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSeccion2ItemStateChanged
        if(cmbSeccion2.getSelectedIndex() != -1)
        {
            actualizarTablaAlumno();
        }
    }//GEN-LAST:event_cmbSeccion2ItemStateChanged

    private void cmbPeriodo2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPeriodo2ItemStateChanged
        if(cmbPeriodo2.getSelectedIndex() != -1)
        {
            cargaSeccionA();
            actualizarTablaAlumno();
        }
    }//GEN-LAST:event_cmbPeriodo2ItemStateChanged

    private void tblAlumnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAlumnoMouseClicked
        int reg;
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        reg = tblAlumno.getSelectedRow();
        if(reg == -1)
        {
            JOptionPane.showMessageDialog(null, "Primero debe seleccionar una opción de asignación");
        }
        else
        {
            defaultTableModel = (DefaultTableModel)tblAlumno.getModel();
            StrCodAlu = ((String)defaultTableModel.getValueAt(reg, 0));
            nombre = ((String) defaultTableModel.getValueAt(reg, 2));
            
        }
    }//GEN-LAST:event_tblAlumnoMouseClicked
    
    private void activaRadio()
    {    
        radioPeriodo.setEnabled(true);
        radioSeccion.setEnabled(true);
        radioAlumno.setEnabled(true);
    }
    
    private void radioAlumnoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radioAlumnoItemStateChanged
        if(radioAlumno.isSelected())
        {
            activaItem(false,false,false);
            jdcFecha.setEnabled(true);
            jdcFechag.setEnabled(true);
            activaAlumno();
        }
    }//GEN-LAST:event_radioAlumnoItemStateChanged

    private void txtBusquedaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBusquedaMouseClicked
        int fin = txtBusqueda.getHeight();
        txtBusqueda.setSelectionStart(0);
        txtBusqueda.setSelectionEnd(fin);
    }//GEN-LAST:event_txtBusquedaMouseClicked

    private void txtBusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyTyped

        String a = txtBusqueda.getText();
        if(a.equals(""))
        {
            actualizarTablaAlumno();
        }
        else
        {
            actualizarTablaAlumnoBusqueda(a);
        }
    }//GEN-LAST:event_txtBusquedaKeyTyped

    private void cmbNivelItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbNivelItemStateChanged
        if(cmbNivel.getSelectedIndex() != -1)
        {
            cargaComboPeriodo();
            cargaComboSeccion();
        }
    }//GEN-LAST:event_cmbNivelItemStateChanged

    private void cmbPeriodoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPeriodoItemStateChanged
        if(cmbPeriodo.getSelectedIndex() != -1) //solo cargara si el index no es -1
        {
            cargaComboSeccion();
        }
    }//GEN-LAST:event_cmbPeriodoItemStateChanged

    private void radioSeccionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radioSeccionItemStateChanged
        if(radioSeccion.isSelected())
        {
            activaItem(true,true,true);
            jdcFecha.setEnabled(true);
            jdcFechag.setEnabled(true);
            desactivaAlumno();

        }
    }//GEN-LAST:event_radioSeccionItemStateChanged

    private void radioPeriodoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radioPeriodoItemStateChanged
        if(radioPeriodo.isSelected())
        {
            activaItem(true,true,true);
            jdcFecha.setEnabled(true);
            jdcFechag.setEnabled(true);
            desactivaAlumno();
        }
    }//GEN-LAST:event_radioPeriodoItemStateChanged

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        venServicio = formato.format(jdcFecha.getDate());
        actServicio = formato.format(jdcFechag.getDate());
        //        Date actual = new Date();
        //        String act= formatoEUslash.format(actual);
        try {
            Date fec1 = formato.parse(venServicio);
            Date fec2 = formato.parse(actServicio);
            if(fec1.before(fec2))
            {
                JOptionPane.showMessageDialog(null, "La fecha de vencimiento no puede ser menor a la fecha de pedido");
            }
            else
            {

                if(radioPeriodo.isSelected())
                {
                    periodo = (String) cmbPeriodo.getSelectedItem();
                    String idPeriodo = codigoPeriodo.get(cmbPeriodo.getSelectedIndex());
                    String options[] = {"Aceptar","Cancelar"};
                    int option = JOptionPane.showOptionDialog(this, "¿Seguro que desea generar deuda para "+periodo+"?",
                        "Confirmación", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                    if(option == 0) // pressing OK button
                    {
                        prymatricula.AsignarServicio.asignarPeriodo(idPeriodo, strCodigo,actServicio,venServicio);
                        JOptionPane.showMessageDialog(this, "Deuda generada correctamente");
                    }
                }
                else if(radioSeccion.isSelected())
                {
                    seccion = (String) cmbSeccion.getSelectedItem();
                    String idSeccion = codigoSeccion.get(cmbSeccion.getSelectedIndex());
                    String options[] = {"Aceptar","Cancelar"};
                    int option = JOptionPane.showOptionDialog(this, "¿Seguro que desea generar deuda para "+seccion+"?",
                        "Confirmación", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                    if(option == 0) // pressing OK button
                    {
                        prymatricula.AsignarServicio.asignarSeccion(idSeccion, strCodigo,actServicio,venServicio);
                        JOptionPane.showMessageDialog(this, "Deuda generada correctamente");
                    }
                }
                else if(radioAlumno.isSelected())
                {
                    if(tblAlumno.getSelectedRow()<0){JOptionPane.showMessageDialog(this, "Seleccione un registro");}
                    else{
                        String options[] = {"Aceptar","Cancelar"};
                        int option = JOptionPane.showOptionDialog(this, "¿Seguro que desea generar deuda para "+nombre+"?",
                            "Confirmación", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                        if(option == 0) // pressing OK button
                        {
                            prymatricula.AsignarServicio.asignarAlumno(StrCodAlu, strCodigo,actServicio,venServicio);
                            JOptionPane.showMessageDialog(this, "Deuda generada correctamente");
                        }}
                    }
                }

            } catch (ParseException ex) {
                Logger.getLogger(FrmServicio.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_btnGenerarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnSalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir1ActionPerformed
       this.dispose();
    }//GEN-LAST:event_btnSalir1ActionPerformed

    private void cmbSeccionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSeccionItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSeccionItemStateChanged

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int choice = JOptionPane.showOptionDialog(null,"¿Seguro de eliminar el servicio seleccionado?", 
        "Eliminar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null); 
        if (choice == JOptionPane.YES_OPTION)
        {
            ClsServicio servi = new ClsServicio();
            con = ClsConexion.getConection();
            servi.EliminarServicio(strCodigo, con);
            actualizarTablaServicio();
            activaBotones(true,false,false,true,false);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed
    
    private void desactivaCampos()
    {
        txtNombre.setEnabled(false);
        txtPrecio.setEnabled(false);
        cmbAnioo.setEnabled(false);
        txtDescripcion.setEnabled(false);
    }
    String convertirMayu(String texto)
    {
    String text;
    text =texto.toUpperCase();
    return text;
    }
    private void activaCampos()
    {
        txtNombre.setEnabled(true);
        txtPrecio.setEnabled(true);
        cmbAnioo.setEnabled(true);
        txtDescripcion.setEnabled(true);
    }
    private  void activaBotones(boolean nuevo,boolean agregar,boolean modificar,boolean salir,boolean eliminar)
    {
        btnNuevo.setEnabled(nuevo);
        btnAgregar.setEnabled(agregar);
        btnModificar.setEnabled(modificar);
        btnSalir.setEnabled(salir); 
        btnEliminar.setEnabled(eliminar);
    }
    private void limpiaCampos()
    {
        txtNombre.setText("");
        txtPrecio.setText("");    
        txtDescripcion.setText("");
    }
   
    
    
    private void actualizarTablaServicio()
    {
        String titulos[] = {"Codigo","Nombre","Precio","Descripcion","Año"};
        ClsServicio servicios = new ClsServicio();
        ArrayList<ClsEntidadServicio> producto = servicios.ListarServicio();
        Iterator iterator = producto.iterator();
        DefaultTableModel defaultTableModel =new DefaultTableModel(null,titulos){
        public boolean isCellEditable(int rowIndex,int columnIndex){return false;} 
        };         
        String fila[] = new String[6];
        while(iterator.hasNext())
        {
            ClsEntidadServicio Servicio = new ClsEntidadServicio();
            Servicio = (ClsEntidadServicio) iterator.next();
            fila[0] = Servicio.getId_Servicio();
            fila[1] = Servicio.getNombre_Servicio();
            fila[2] = Servicio.getPrecio_Servicio();
            fila[3] = Servicio.getDescripcion_Servicio();
            fila[4] = Servicio.getAnio_Servicio();            
            String aux = cmbAnio.getSelectedItem().toString();
            if(aux.equals(fila[4])) 
            {  defaultTableModel.addRow(fila);  }    
        }
        tblServicio.setModel(defaultTableModel);    
        tblServicio.removeColumn(tblServicio.getColumnModel().getColumn(0));
        TableColumn  columnaNombre =tblServicio.getColumnModel().getColumn(0);
        columnaNombre.setPreferredWidth(200);      
        TableColumn  columnaApe =tblServicio.getColumnModel().getColumn(2);
        columnaApe.setPreferredWidth(220);
    }
    private String llenaCombo()
    {
        cmbAnio.removeAllItems();
        cmbAnioo.removeAllItems();
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
            cmbAnioo.addItem(String.valueOf(burbuja[o]));
        }        
        //agrego los 4 años siguientes
        for (int j=1;j<5;j++)
        {
           cmbAnio.addItem(String.valueOf(Anioactual + j));
           cmbAnioo.addItem(String.valueOf(Anioactual + j));
        }
        
        return String.valueOf(Anioactual);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSalir1;
    private javax.swing.JComboBox cmbAnio;
    private javax.swing.JComboBox cmbAnioo;
    private javax.swing.JComboBox cmbNivel;
    private javax.swing.JComboBox cmbPeriodo;
    private javax.swing.JComboBox cmbPeriodo2;
    private javax.swing.JComboBox cmbSeccion;
    private javax.swing.JComboBox cmbSeccion2;
    private javax.swing.ButtonGroup grupo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdcFecha;
    private com.toedter.calendar.JDateChooser jdcFechag;
    private javax.swing.JRadioButton radioAlumno;
    private javax.swing.JRadioButton radioPeriodo;
    private javax.swing.JRadioButton radioSeccion;
    private javax.swing.JTabbedPane tbdServicio;
    private javax.swing.JTable tblAlumno;
    private javax.swing.JTable tblServicio;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
