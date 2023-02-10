/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckInterfaces;


import PckConexion.ClsConexion;
import PckHilo.HiloEdad;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JRootPane;
import mpsDAL.SeguridadDAL;
import mpsEntity.SeguridadPermisosAdministracionEntity;
import mpsEntity.SeguridadPermisosAulaEntity;
import mpsEntity.SeguridadPermisosContabilidadEntity;
import mpsEntity.SeguridadPermisosHerramientaEntity;
import mpsEntity.SeguridadPermisosMatriculaEntity;
import mpsEntity.SeguridadPermisosProductoServicioEntity;
import mpsEntity.SeguridadPermisosSeguridadEntity;
import prymatricula.ClsMisc;

/**
 *****Sistema de Gestión para Centros Educativos (SGCE)*****
 * @author John Kevin Montes De Oca Vizcarra
 * @email jkevin.mv@gmail.com 
 */
public class FrmPrincipal extends javax.swing.JFrame {

public static String TipoPago="";
public static String modifica="";
public  static String CodAlumno;
//declaraciones login
public static String usuarioLogeado;
public static String idRolLogeado;
public static String idPersonalLogeado;
public static String nombreRolLogeado;

//validacion producto y servicio
public static boolean mantenimientoProducto;
public static boolean asignarProducto;
public static boolean mantenimientoServicio;
public static boolean asignarServicio;
public static boolean editarPago;

//validacion administracion
public static boolean editarCasoPsicologia;

//validacion contabilidad
public static boolean editarEgreso;


    /**
     * Creates new form FrmPrincipal
     */
    public FrmPrincipal() {
        
        initComponents();
        
        System.out.println("AQUI: "+System.getProperty("user.dir"));
        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        int x = (dskpPrincipal.getWidth());
        int y = (dskpPrincipal.getHeight()); 
        PrimeraPantalla primeraPantalla = new PrimeraPantalla();        
        dskpPrincipal.removeAll();
        dskpPrincipal.updateUI();
        if (primeraPantalla.isShowing()) 
        {
            primeraPantalla.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(primeraPantalla);
            primeraPantalla.setBounds(0,0,x, y);            
            primeraPantalla.setVisible(true);      
            mnuPrincipal.requestFocus();
        }
        
//        
//        try {
//            
//            
//            String url1="/Img/sunshine.png";
//            String urlSistema1 = System.getProperty("user.dir")+"/demo/mps";
//            //urlSistema = urlSistema.replace("\\", "/");
//            //System.out.println(urlSistema+url);
//            Image fot1 = null;
//            try {
//                fot1 = ImageIO.read(new File(urlSistema1+url1));
//                setIconImage(fot1);
//            } catch (IOException ex) {
//                Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } catch (Exception ex) {
//            
//        }
        
        
        Connection con;
        lblUsuario.setText(usuarioLogeado);
        System.out.println(usuarioLogeado);
                              
        
        FrmFichaMatricula.condi="";        
        
        //CARGA FECHA ACTUAL           
        lblFecha.setText(ClsMisc.fechaActual());
        
        //instancia un Thread e inicializa el hilo
        HiloEdad edad = new HiloEdad();       
                
        con = ClsConexion.getConection();
        SeguridadDAL seguridadDAL = new SeguridadDAL();
        
        ArrayList<SeguridadPermisosAulaEntity> arrayPermisosAula;
        ArrayList<SeguridadPermisosMatriculaEntity> arrayPermisosMatricula;
        ArrayList<SeguridadPermisosAdministracionEntity> arrayPermisosAdministracion;
        ArrayList<SeguridadPermisosProductoServicioEntity> arrayPermisosProductoServicio;
        ArrayList<SeguridadPermisosContabilidadEntity> arrayPermisosContabilidad;
        ArrayList<SeguridadPermisosHerramientaEntity> arrayPermisosHerramienta;
        ArrayList<SeguridadPermisosSeguridadEntity> arrayPermisosSeguridad;
        
        //Permisos Aula
        
        arrayPermisosAula = seguridadDAL.ListarPermisosAula(idRolLogeado, con);
        arrayPermisosMatricula = seguridadDAL.ListarPermisosMatricula(idRolLogeado, con);
        arrayPermisosAdministracion = seguridadDAL.ListarPermisosAdministracion(idRolLogeado, con);
        arrayPermisosProductoServicio = seguridadDAL.ListarPermisosProductoServicio(idRolLogeado, con);
        arrayPermisosContabilidad = seguridadDAL.ListarPermisosContabilidad(idRolLogeado, con);
        arrayPermisosHerramienta = seguridadDAL.ListarPermisosHerramientas(idRolLogeado, con);
        arrayPermisosSeguridad = seguridadDAL.ListarPermisosSeguridad(idRolLogeado, con);
        
        ValidarPermisosAula(arrayPermisosAula);
        ValidarPermisosMatricula(arrayPermisosMatricula);
        ValidarPermisosAdministracion(arrayPermisosAdministracion);
        ValidarPermisosProductoServicio(arrayPermisosProductoServicio);
        ValidarPermisosContabilidad(arrayPermisosContabilidad);
        ValidarPermisosHerramienta(arrayPermisosHerramienta);
        ValidarPermisosSeguridad(arrayPermisosSeguridad);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dskpPrincipal = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        mnuPrincipal = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        mnuMiAula = new javax.swing.JMenu();
        muiAnecdotario = new javax.swing.JMenuItem();
        muiPsicologia = new javax.swing.JMenuItem();
        jSeparator14 = new javax.swing.JPopupMenu.Separator();
        muiAsistencia = new javax.swing.JMenuItem();
        muiInasistencias = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenuItem6 = new javax.swing.JMenuItem();
        muiFormato = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();
        muiCumplimiento = new javax.swing.JMenuItem();
        jSeparator13 = new javax.swing.JPopupMenu.Separator();
        muiAlmacenRequerimiento = new javax.swing.JMenuItem();
        muiAlmacenItem = new javax.swing.JMenuItem();
        mnuMatricula = new javax.swing.JMenu();
        muiMatricula = new javax.swing.JMenuItem();
        muiHuella = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        muiNivel = new javax.swing.JMenuItem();
        muiPeriodo = new javax.swing.JMenuItem();
        muiAula = new javax.swing.JMenuItem();
        mnuAdministracion = new javax.swing.JMenu();
        muiCantidadLoncheras = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        muiAdministrarAnecdotario = new javax.swing.JMenuItem();
        muiAdmPsicologia = new javax.swing.JMenuItem();
        jSeparator15 = new javax.swing.JPopupMenu.Separator();
        muiAdministrarAsistencia = new javax.swing.JMenuItem();
        muiConsultaAsistencia = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        muiAdministrarEntregable = new javax.swing.JMenuItem();
        muiEstadoEntregable = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        muiCumplimientoAula = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JPopupMenu.Separator();
        muiAlmacenAdm = new javax.swing.JMenuItem();
        muiAlmacenItemAdm = new javax.swing.JMenuItem();
        muiAlmacenRequerimientoAdm = new javax.swing.JMenuItem();
        muiConsultarItemAlmacen = new javax.swing.JMenuItem();
        mnuProductoServicio = new javax.swing.JMenu();
        muiProducto = new javax.swing.JMenuItem();
        muiServicio = new javax.swing.JMenuItem();
        mnuContabilidad = new javax.swing.JMenu();
        muiPagoGeneral = new javax.swing.JMenuItem();
        muiBoletaAnulada = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        muiDeudaMonto = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        muiIngresoEgreso = new javax.swing.JMenuItem();
        muiEgreso = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        muiImpresionBoleta = new javax.swing.JMenuItem();
        muiConstancias = new javax.swing.JMenuItem();
        mnuReportes = new javax.swing.JMenu();
        muiRptBoleta = new javax.swing.JMenuItem();
        muiRptBoletaDetalle = new javax.swing.JMenuItem();
        muiRptPendienteSinDeuda = new javax.swing.JMenuItem();
        muiRptAlumnoDescuento = new javax.swing.JMenuItem();
        muiRptDeudaMonto = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        jMenuItem7 = new javax.swing.JMenuItem();
        muiRptSexoAula = new javax.swing.JMenuItem();
        muiRptVacanteLibre = new javax.swing.JMenuItem();
        muiRptAlumnoRetirado = new javax.swing.JMenuItem();
        muiRptListado = new javax.swing.JMenuItem();
        muiRptSalida = new javax.swing.JMenuItem();
        muiRptDirectorio = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        muiRptCumpleanosCard = new javax.swing.JMenuItem();
        muiRptCumpleanos = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        muiRptGeneral = new javax.swing.JMenuItem();
        muiRptAlumnoResumen = new javax.swing.JMenuItem();
        muiRptPersonal = new javax.swing.JMenuItem();
        mnuHerramientas = new javax.swing.JMenu();
        muiCorrelativoBoleta = new javax.swing.JMenuItem();
        jSeparator16 = new javax.swing.JPopupMenu.Separator();
        muiAlimentacionPrecio = new javax.swing.JMenuItem();
        muiAjusteAlimentacion = new javax.swing.JMenuItem();
        muiAjusteVencimientoMes = new javax.swing.JMenuItem();
        mnuConfiguracion = new javax.swing.JMenu();
        muiRol = new javax.swing.JMenuItem();
        muiUsuario = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Gestión Institucional");
        setUndecorated(true);

        dskpPrincipal.setName(""); // NOI18N
        dskpPrincipal.setPreferredSize(new java.awt.Dimension(700, 600));
        dskpPrincipal = new javax.swing.JDesktopPane() {
            private Image image;
            {
                try {
                    image = ImageIO.read(new File(System.getProperty("user.dir")+"/Img/fondo.jpg"));
                } catch (IOException e) {
                    Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        dskpPrincipal.setLayout(new java.awt.BorderLayout());
        getContentPane().add(dskpPrincipal, java.awt.BorderLayout.CENTER);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setMinimumSize(new java.awt.Dimension(500, 20));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ususario:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 30));

        lblUsuario.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 310, 30));

        lblFecha.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lblFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, 260, 30));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Fecha:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, -1, 30));

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        mnuPrincipal.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/document.png"))); // NOI18N
        jMenu1.setText("Archivo");
        jMenu1.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        jMenu1.setMargin(new java.awt.Insets(0, 5, 0, 5));

        jMenuItem24.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        jMenuItem24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/1457564973_Log Out.png"))); // NOI18N
        jMenuItem24.setText("Cerrar Sesión");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem24);

        jMenuItem2.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/shut_down.png"))); // NOI18N
        jMenuItem2.setText("Salir");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        mnuPrincipal.add(jMenu1);

        mnuMiAula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/Courses-icon (1).png"))); // NOI18N
        mnuMiAula.setText("Mi Aula");
        mnuMiAula.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        mnuMiAula.setMargin(new java.awt.Insets(0, 5, 0, 5));

        muiAnecdotario.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiAnecdotario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/checklist-icon.png"))); // NOI18N
        muiAnecdotario.setText("Anecdotario");
        muiAnecdotario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiAnecdotarioActionPerformed(evt);
            }
        });
        mnuMiAula.add(muiAnecdotario);

        muiPsicologia.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiPsicologia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/Courses-icon.png"))); // NOI18N
        muiPsicologia.setText("Psicologia");
        muiPsicologia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiPsicologiaActionPerformed(evt);
            }
        });
        mnuMiAula.add(muiPsicologia);
        mnuMiAula.add(jSeparator14);

        muiAsistencia.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiAsistencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/user.png"))); // NOI18N
        muiAsistencia.setText("Asistencia");
        muiAsistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiAsistenciaActionPerformed(evt);
            }
        });
        mnuMiAula.add(muiAsistencia);

        muiInasistencias.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiInasistencias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/x.png"))); // NOI18N
        muiInasistencias.setText("Inasistencias");
        muiInasistencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiInasistenciasActionPerformed(evt);
            }
        });
        mnuMiAula.add(muiInasistencias);
        mnuMiAula.add(jSeparator6);

        jMenuItem6.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/1477321983_2.png"))); // NOI18N
        jMenuItem6.setText("Mis Entregables");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        mnuMiAula.add(jMenuItem6);

        muiFormato.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiFormato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/1477404534_EditDocument.png"))); // NOI18N
        muiFormato.setText("Formatos");
        muiFormato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiFormatoActionPerformed(evt);
            }
        });
        mnuMiAula.add(muiFormato);
        mnuMiAula.add(jSeparator11);

        muiCumplimiento.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiCumplimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/requerimiento.png"))); // NOI18N
        muiCumplimiento.setText("Cumplimientos");
        muiCumplimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiCumplimientoActionPerformed(evt);
            }
        });
        mnuMiAula.add(muiCumplimiento);
        mnuMiAula.add(jSeparator13);

        muiAlmacenRequerimiento.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiAlmacenRequerimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/ico-recepcion.png"))); // NOI18N
        muiAlmacenRequerimiento.setText("Administrar Requerimiento Almacen");
        muiAlmacenRequerimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiAlmacenRequerimientoActionPerformed(evt);
            }
        });
        mnuMiAula.add(muiAlmacenRequerimiento);

        muiAlmacenItem.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiAlmacenItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/almacen.png"))); // NOI18N
        muiAlmacenItem.setText("Consultar Items de Almacen");
        muiAlmacenItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiAlmacenItemActionPerformed(evt);
            }
        });
        mnuMiAula.add(muiAlmacenItem);

        mnuPrincipal.add(mnuMiAula);

        mnuMatricula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/list_information.png"))); // NOI18N
        mnuMatricula.setText("Matrícula");
        mnuMatricula.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        mnuMatricula.setMargin(new java.awt.Insets(0, 5, 0, 5));

        muiMatricula.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiMatricula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/gnome_main_menu.png"))); // NOI18N
        muiMatricula.setText("Matricula Principal");
        muiMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiMatriculaActionPerformed(evt);
            }
        });
        mnuMatricula.add(muiMatricula);

        muiHuella.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiHuella.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/fingerprint.png"))); // NOI18N
        muiHuella.setText("Registro de Huellas");
        muiHuella.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiHuellaActionPerformed(evt);
            }
        });
        mnuMatricula.add(muiHuella);
        mnuMatricula.add(jSeparator4);

        muiNivel.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiNivel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/diagram_v2_17.png"))); // NOI18N
        muiNivel.setText("Nivel");
        muiNivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiNivelActionPerformed(evt);
            }
        });
        mnuMatricula.add(muiNivel);

        muiPeriodo.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiPeriodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/calendar.png"))); // NOI18N
        muiPeriodo.setText("Periodo");
        muiPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiPeriodoActionPerformed(evt);
            }
        });
        mnuMatricula.add(muiPeriodo);

        muiAula.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiAula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/1457822836_tutorials.png"))); // NOI18N
        muiAula.setText("Aula");
        muiAula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiAulaActionPerformed(evt);
            }
        });
        mnuMatricula.add(muiAula);

        mnuPrincipal.add(mnuMatricula);

        mnuAdministracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/1456002598_administrator.png"))); // NOI18N
        mnuAdministracion.setText("Administracion");
        mnuAdministracion.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        mnuAdministracion.setMargin(new java.awt.Insets(0, 5, 0, 5));

        muiCantidadLoncheras.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiCantidadLoncheras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/super_mario_question_box.png"))); // NOI18N
        muiCantidadLoncheras.setText("Cantidad Loncheras");
        muiCantidadLoncheras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiCantidadLoncherasActionPerformed(evt);
            }
        });
        mnuAdministracion.add(muiCantidadLoncheras);
        mnuAdministracion.add(jSeparator2);

        muiAdministrarAnecdotario.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiAdministrarAnecdotario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/checklist-icon.png"))); // NOI18N
        muiAdministrarAnecdotario.setText("Administrar Anecdotario");
        muiAdministrarAnecdotario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiAdministrarAnecdotarioActionPerformed(evt);
            }
        });
        mnuAdministracion.add(muiAdministrarAnecdotario);

        muiAdmPsicologia.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiAdmPsicologia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/Courses-icon.png"))); // NOI18N
        muiAdmPsicologia.setText("Administrar Psicologia");
        muiAdmPsicologia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiAdmPsicologiaActionPerformed(evt);
            }
        });
        mnuAdministracion.add(muiAdmPsicologia);
        mnuAdministracion.add(jSeparator15);

        muiAdministrarAsistencia.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiAdministrarAsistencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/user.png"))); // NOI18N
        muiAdministrarAsistencia.setText("Administrar Asistencia");
        muiAdministrarAsistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiAdministrarAsistenciaActionPerformed(evt);
            }
        });
        mnuAdministracion.add(muiAdministrarAsistencia);

        muiConsultaAsistencia.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiConsultaAsistencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/mail_find.png"))); // NOI18N
        muiConsultaAsistencia.setText("Consulta Asistencia");
        muiConsultaAsistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiConsultaAsistenciaActionPerformed(evt);
            }
        });
        mnuAdministracion.add(muiConsultaAsistencia);
        mnuAdministracion.add(jSeparator5);

        muiAdministrarEntregable.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiAdministrarEntregable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/1477321983_2.png"))); // NOI18N
        muiAdministrarEntregable.setText("Administrar Entregables");
        muiAdministrarEntregable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiAdministrarEntregableActionPerformed(evt);
            }
        });
        mnuAdministracion.add(muiAdministrarEntregable);

        muiEstadoEntregable.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiEstadoEntregable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/1477284251_icons-11.png"))); // NOI18N
        muiEstadoEntregable.setText("Estado Entregables");
        muiEstadoEntregable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiEstadoEntregableActionPerformed(evt);
            }
        });
        mnuAdministracion.add(muiEstadoEntregable);
        mnuAdministracion.add(jSeparator10);

        muiCumplimientoAula.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiCumplimientoAula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/requerimiento.png"))); // NOI18N
        muiCumplimientoAula.setText("Administrar Cumplimiento Aula");
        muiCumplimientoAula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiCumplimientoAulaActionPerformed(evt);
            }
        });
        mnuAdministracion.add(muiCumplimientoAula);
        mnuAdministracion.add(jSeparator12);

        muiAlmacenAdm.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiAlmacenAdm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/almacen.png"))); // NOI18N
        muiAlmacenAdm.setText("Administrar Almacen");
        muiAlmacenAdm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiAlmacenAdmActionPerformed(evt);
            }
        });
        mnuAdministracion.add(muiAlmacenAdm);

        muiAlmacenItemAdm.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiAlmacenItemAdm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/ico_21-1.png"))); // NOI18N
        muiAlmacenItemAdm.setText("Administrar Items de Almacen");
        muiAlmacenItemAdm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiAlmacenItemAdmActionPerformed(evt);
            }
        });
        mnuAdministracion.add(muiAlmacenItemAdm);

        muiAlmacenRequerimientoAdm.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiAlmacenRequerimientoAdm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/ico-recepcion.png"))); // NOI18N
        muiAlmacenRequerimientoAdm.setText("Administrar Requerimiento Almacen");
        muiAlmacenRequerimientoAdm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiAlmacenRequerimientoAdmActionPerformed(evt);
            }
        });
        mnuAdministracion.add(muiAlmacenRequerimientoAdm);

        muiConsultarItemAlmacen.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiConsultarItemAlmacen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/ic_search_black_24dp.png"))); // NOI18N
        muiConsultarItemAlmacen.setText("Consultar Items de Almacen");
        muiConsultarItemAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiConsultarItemAlmacenActionPerformed(evt);
            }
        });
        mnuAdministracion.add(muiConsultarItemAlmacen);

        mnuPrincipal.add(mnuAdministracion);

        mnuProductoServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/organize.png"))); // NOI18N
        mnuProductoServicio.setText("Productos / Servicios");
        mnuProductoServicio.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        mnuProductoServicio.setMargin(new java.awt.Insets(0, 5, 0, 5));

        muiProducto.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/1458167015_box.png"))); // NOI18N
        muiProducto.setText("Productos");
        muiProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiProductoActionPerformed(evt);
            }
        });
        mnuProductoServicio.add(muiProducto);

        muiServicio.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/network_service.png"))); // NOI18N
        muiServicio.setText("Servicios/Cuotas");
        muiServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiServicioActionPerformed(evt);
            }
        });
        mnuProductoServicio.add(muiServicio);

        mnuPrincipal.add(mnuProductoServicio);

        mnuContabilidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/cash_register.png"))); // NOI18N
        mnuContabilidad.setText("Contabilidad");
        mnuContabilidad.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        mnuContabilidad.setMargin(new java.awt.Insets(0, 5, 0, 5));

        muiPagoGeneral.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiPagoGeneral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/dollar_coin.png"))); // NOI18N
        muiPagoGeneral.setText("Pagos Generales");
        muiPagoGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiPagoGeneralActionPerformed(evt);
            }
        });
        mnuContabilidad.add(muiPagoGeneral);

        muiBoletaAnulada.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiBoletaAnulada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/document_delete3.png"))); // NOI18N
        muiBoletaAnulada.setText("Boletas Anuladas");
        muiBoletaAnulada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiBoletaAnuladaActionPerformed(evt);
            }
        });
        mnuContabilidad.add(muiBoletaAnulada);

        jMenuItem19.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        jMenuItem19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/Order History_24px.png"))); // NOI18N
        jMenuItem19.setText("Historial Autorizaciones");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        mnuContabilidad.add(jMenuItem19);

        muiDeudaMonto.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiDeudaMonto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/if_coin-money-dollar-currency_2009869.png"))); // NOI18N
        muiDeudaMonto.setText("Deudas por Monto");
        muiDeudaMonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiDeudaMontoActionPerformed(evt);
            }
        });
        mnuContabilidad.add(muiDeudaMonto);
        mnuContabilidad.add(jSeparator7);

        muiIngresoEgreso.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiIngresoEgreso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/emblem_money.png"))); // NOI18N
        muiIngresoEgreso.setText("Ingresos / Egresos");
        muiIngresoEgreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiIngresoEgresoActionPerformed(evt);
            }
        });
        mnuContabilidad.add(muiIngresoEgreso);

        muiEgreso.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiEgreso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/money_delete.png"))); // NOI18N
        muiEgreso.setText("Egresos");
        muiEgreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiEgresoActionPerformed(evt);
            }
        });
        mnuContabilidad.add(muiEgreso);
        mnuContabilidad.add(jSeparator3);

        muiImpresionBoleta.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiImpresionBoleta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/printer_green.png"))); // NOI18N
        muiImpresionBoleta.setText("Impresión Boletas");
        muiImpresionBoleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiImpresionBoletaActionPerformed(evt);
            }
        });
        mnuContabilidad.add(muiImpresionBoleta);

        muiConstancias.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiConstancias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/icons8_Documents_24px.png"))); // NOI18N
        muiConstancias.setText("Constancias");
        muiConstancias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiConstanciasActionPerformed(evt);
            }
        });
        mnuContabilidad.add(muiConstancias);

        mnuPrincipal.add(mnuContabilidad);

        mnuReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/repo.png"))); // NOI18N
        mnuReportes.setText("Reportes");
        mnuReportes.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        mnuReportes.setMargin(new java.awt.Insets(0, 5, 0, 5));

        muiRptBoleta.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiRptBoleta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/invoice.png"))); // NOI18N
        muiRptBoleta.setText("Reporte Boletas");
        muiRptBoleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiRptBoletaActionPerformed(evt);
            }
        });
        mnuReportes.add(muiRptBoleta);

        muiRptBoletaDetalle.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiRptBoletaDetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/invoice.png"))); // NOI18N
        muiRptBoletaDetalle.setText("Reporte Boletas Detalle");
        muiRptBoletaDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiRptBoletaDetalleActionPerformed(evt);
            }
        });
        mnuReportes.add(muiRptBoletaDetalle);

        muiRptPendienteSinDeuda.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiRptPendienteSinDeuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/appointment_urgent.png"))); // NOI18N
        muiRptPendienteSinDeuda.setText("Pendientes / Sin deudas");
        muiRptPendienteSinDeuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiRptPendienteSinDeudaActionPerformed(evt);
            }
        });
        mnuReportes.add(muiRptPendienteSinDeuda);

        muiRptAlumnoDescuento.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiRptAlumnoDescuento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/Money Box_24px.png"))); // NOI18N
        muiRptAlumnoDescuento.setText("Alumnos con Descuento");
        muiRptAlumnoDescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiRptAlumnoDescuentoActionPerformed(evt);
            }
        });
        mnuReportes.add(muiRptAlumnoDescuento);

        muiRptDeudaMonto.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiRptDeudaMonto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/icons8_Debt_24px.png"))); // NOI18N
        muiRptDeudaMonto.setText("Reporte Deudores por Monto");
        muiRptDeudaMonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiRptDeudaMontoActionPerformed(evt);
            }
        });
        mnuReportes.add(muiRptDeudaMonto);
        mnuReportes.add(jSeparator8);

        jMenuItem7.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/requerimiento.png"))); // NOI18N
        jMenuItem7.setText("Cumplimientos Aula");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        mnuReportes.add(jMenuItem7);

        muiRptSexoAula.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiRptSexoAula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/gender.png"))); // NOI18N
        muiRptSexoAula.setText("Reporte Sexo por Aula");
        muiRptSexoAula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiRptSexoAulaActionPerformed(evt);
            }
        });
        mnuReportes.add(muiRptSexoAula);

        muiRptVacanteLibre.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiRptVacanteLibre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/Babys Room_24px.png"))); // NOI18N
        muiRptVacanteLibre.setText("Reporte Vacantes Libres");
        muiRptVacanteLibre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiRptVacanteLibreActionPerformed(evt);
            }
        });
        mnuReportes.add(muiRptVacanteLibre);

        muiRptAlumnoRetirado.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiRptAlumnoRetirado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/icons8_Exit_24px.png"))); // NOI18N
        muiRptAlumnoRetirado.setText("Alumnos Retirados");
        muiRptAlumnoRetirado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiRptAlumnoRetiradoActionPerformed(evt);
            }
        });
        mnuReportes.add(muiRptAlumnoRetirado);

        muiRptListado.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiRptListado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/estu.png"))); // NOI18N
        muiRptListado.setText("Listados de Alumnos");
        muiRptListado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiRptListadoActionPerformed(evt);
            }
        });
        mnuReportes.add(muiRptListado);

        muiRptSalida.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiRptSalida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/1408831217_Natsu-UserControl.png"))); // NOI18N
        muiRptSalida.setText("Salida Alumnos");
        muiRptSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiRptSalidaActionPerformed(evt);
            }
        });
        mnuReportes.add(muiRptSalida);

        muiRptDirectorio.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiRptDirectorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/directory.png"))); // NOI18N
        muiRptDirectorio.setText("Directorio Telefónico");
        muiRptDirectorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiRptDirectorioActionPerformed(evt);
            }
        });
        mnuReportes.add(muiRptDirectorio);
        mnuReportes.add(jSeparator1);

        muiRptCumpleanosCard.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiRptCumpleanosCard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/icons8_Gift_Card_24px.png"))); // NOI18N
        muiRptCumpleanosCard.setText("Tarjeta Cumpleaños ");
        muiRptCumpleanosCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiRptCumpleanosCardActionPerformed(evt);
            }
        });
        mnuReportes.add(muiRptCumpleanosCard);

        muiRptCumpleanos.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiRptCumpleanos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/cookie.png"))); // NOI18N
        muiRptCumpleanos.setText("Directorio Cumpleaños");
        muiRptCumpleanos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiRptCumpleanosActionPerformed(evt);
            }
        });
        mnuReportes.add(muiRptCumpleanos);
        mnuReportes.add(jSeparator9);

        muiRptGeneral.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiRptGeneral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/repo.png"))); // NOI18N
        muiRptGeneral.setText("Reportes Generales");
        muiRptGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiRptGeneralActionPerformed(evt);
            }
        });
        mnuReportes.add(muiRptGeneral);

        muiRptAlumnoResumen.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiRptAlumnoResumen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/kids.png"))); // NOI18N
        muiRptAlumnoResumen.setText("Reporte Resumen Alumnos");
        muiRptAlumnoResumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiRptAlumnoResumenActionPerformed(evt);
            }
        });
        mnuReportes.add(muiRptAlumnoResumen);

        muiRptPersonal.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiRptPersonal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/kids.png"))); // NOI18N
        muiRptPersonal.setText("Reporte Personal");
        muiRptPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiRptPersonalActionPerformed(evt);
            }
        });
        mnuReportes.add(muiRptPersonal);

        mnuPrincipal.add(mnuReportes);

        mnuHerramientas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/administrative_tools.png"))); // NOI18N
        mnuHerramientas.setText("Herramientas");
        mnuHerramientas.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        mnuHerramientas.setMargin(new java.awt.Insets(0, 5, 0, 5));

        muiCorrelativoBoleta.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiCorrelativoBoleta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/pencil.png"))); // NOI18N
        muiCorrelativoBoleta.setText("Ajuste Código Boleta");
        muiCorrelativoBoleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiCorrelativoBoletaActionPerformed(evt);
            }
        });
        mnuHerramientas.add(muiCorrelativoBoleta);
        mnuHerramientas.add(jSeparator16);

        muiAlimentacionPrecio.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiAlimentacionPrecio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/1458166758_apple.png"))); // NOI18N
        muiAlimentacionPrecio.setText("Ajuste Lonchera por Mes");
        muiAlimentacionPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiAlimentacionPrecioActionPerformed(evt);
            }
        });
        mnuHerramientas.add(muiAlimentacionPrecio);

        muiAjusteAlimentacion.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiAjusteAlimentacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/brightness-7.png"))); // NOI18N
        muiAjusteAlimentacion.setText("Ajuste Servicio Lonchera");
        muiAjusteAlimentacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiAjusteAlimentacionActionPerformed(evt);
            }
        });
        mnuHerramientas.add(muiAjusteAlimentacion);

        muiAjusteVencimientoMes.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiAjusteVencimientoMes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/calendar.png"))); // NOI18N
        muiAjusteVencimientoMes.setText("Ajuste Vencimiento por Mes");
        muiAjusteVencimientoMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiAjusteVencimientoMesActionPerformed(evt);
            }
        });
        mnuHerramientas.add(muiAjusteVencimientoMes);

        mnuPrincipal.add(mnuHerramientas);

        mnuConfiguracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/service_manager.png"))); // NOI18N
        mnuConfiguracion.setText("Configuración");
        mnuConfiguracion.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        mnuConfiguracion.setMargin(new java.awt.Insets(0, 5, 0, 5));

        muiRol.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiRol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/groupevent.png"))); // NOI18N
        muiRol.setText("Rol");
        muiRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiRolActionPerformed(evt);
            }
        });
        mnuConfiguracion.add(muiRol);

        muiUsuario.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        muiUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/user_group.png"))); // NOI18N
        muiUsuario.setText("Usuarios");
        muiUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muiUsuarioActionPerformed(evt);
            }
        });
        mnuConfiguracion.add(muiUsuario);

        mnuPrincipal.add(mnuConfiguracion);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/question.png"))); // NOI18N
        jMenu5.setText("Ayuda");
        jMenu5.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        jMenu5.setMargin(new java.awt.Insets(0, 5, 0, 5));

        jMenuItem3.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/get_info.png"))); // NOI18N
        jMenuItem3.setText("Acerca de...");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem3);

        mnuPrincipal.add(jMenu5);

        jMenu2.setEnabled(false);
        jMenu2.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        jMenu2.setPreferredSize(new java.awt.Dimension(200, 24));
        mnuPrincipal.add(jMenu2);

        setJMenuBar(mnuPrincipal);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void muiMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiMatriculaActionPerformed
          llamaMatricula();
    }//GEN-LAST:event_muiMatriculaActionPerformed

    private void muiAulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiAulaActionPerformed
        llamaSeccion();
    }//GEN-LAST:event_muiAulaActionPerformed

    private void muiPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiPeriodoActionPerformed
        llamaPeriodo();
    }//GEN-LAST:event_muiPeriodoActionPerformed

    private void muiRptListadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiRptListadoActionPerformed
       llamaReporteNinosPorSalon();
    }//GEN-LAST:event_muiRptListadoActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
       System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void muiProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiProductoActionPerformed
        llamaProducto();
    }//GEN-LAST:event_muiProductoActionPerformed

    private void muiServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiServicioActionPerformed
        llamaServicio();
    }//GEN-LAST:event_muiServicioActionPerformed

    private void muiRptBoletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiRptBoletaActionPerformed
                  
           PckInterfaces.FrmRptBoleta servi= new PckInterfaces.FrmRptBoleta();
        
        int x = (dskpPrincipal.getWidth() / 2) - servi.getWidth() / 2;
                int y = (dskpPrincipal.getHeight() / 2) - servi.getHeight() / 2;
 
                if (servi.isShowing()) {
                    servi.setLocation(x, y);
                } else {
                    dskpPrincipal.add(servi);
                    servi.setLocation(x, y);
                    servi.show();
                }
    }//GEN-LAST:event_muiRptBoletaActionPerformed

    private void muiBoletaAnuladaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiBoletaAnuladaActionPerformed
       PckInterfaces.FrmPgoAnulado servi= new PckInterfaces.FrmPgoAnulado();
        
        int x = (dskpPrincipal.getWidth() / 2) - servi.getWidth() / 2;
                int y = (dskpPrincipal.getHeight() / 2) - servi.getHeight() / 2;
 
                if (servi.isShowing()) {
                    servi.setLocation(x, y);
                } else {
                    dskpPrincipal.add(servi);
                    servi.setLocation(x, y);
                    servi.show();
                }
    }//GEN-LAST:event_muiBoletaAnuladaActionPerformed

    private void muiRptPendienteSinDeudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiRptPendienteSinDeudaActionPerformed
       PckInterfaces.FrmMora servi= new PckInterfaces.FrmMora();        
        int x = (dskpPrincipal.getWidth() / 2) - servi.getWidth() / 2;
         int y = (dskpPrincipal.getHeight() / 2) - servi.getHeight() / 2; 
        if (servi.isShowing()) {
            servi.setLocation(x, y);
        } else {
            dskpPrincipal.add(servi);
            servi.setLocation(x, y);
            servi.show();
        }
    }//GEN-LAST:event_muiRptPendienteSinDeudaActionPerformed

    private void muiIngresoEgresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiIngresoEgresoActionPerformed
        PckInterfaces.ContabilidadIngresosEgresos asistenciaConsulta= new PckInterfaces.ContabilidadIngresosEgresos();
        int x = dskpPrincipal.getWidth();
        int y = dskpPrincipal.getHeight(); 
        dskpPrincipal.removeAll();
        dskpPrincipal.updateUI();
        if (asistenciaConsulta.isShowing())
        {
            asistenciaConsulta.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(asistenciaConsulta);
            asistenciaConsulta.setBounds(0,0,x, y);
            asistenciaConsulta.setVisible(true);
        }
    }//GEN-LAST:event_muiIngresoEgresoActionPerformed

    private void muiEgresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiEgresoActionPerformed
       PckInterfaces.FrmEgresos servi= new PckInterfaces.FrmEgresos();
        int x = (dskpPrincipal.getWidth() / 2) - servi.getWidth() / 2;
         int y = (dskpPrincipal.getHeight() / 2) - servi.getHeight() / 2; 
        if (servi.isShowing()) {
            servi.setLocation(x, y);
        } else {
            dskpPrincipal.add(servi);
            servi.setLocation(x, y);
            servi.show();
        }
    }//GEN-LAST:event_muiEgresoActionPerformed

    private void muiRptSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiRptSalidaActionPerformed
       PckInterfaces.FrmRptSalida salida= new PckInterfaces.FrmRptSalida();        
        int x = (dskpPrincipal.getWidth() / 2) - salida.getWidth() / 2;
        int y = (dskpPrincipal.getHeight() / 2) - salida.getHeight() / 2; 
        if (salida.isShowing()) {
            salida.setLocation(x, y);
        } else {
            dskpPrincipal.add(salida);
            salida.setLocation(x, y);
            salida.show();
        }
    }//GEN-LAST:event_muiRptSalidaActionPerformed

    private void muiRptGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiRptGeneralActionPerformed
         PckInterfaces.FrmReportesFicha salida= new PckInterfaces.FrmReportesFicha();        
        int x = (dskpPrincipal.getWidth() / 2) - salida.getWidth() / 2;
        int y = (dskpPrincipal.getHeight() / 2) - salida.getHeight() / 2; 
        if (salida.isShowing()) {
            salida.setLocation(x, y);
        } else {
            dskpPrincipal.add(salida);
            salida.setLocation(x, y);
            salida.show();
        }
    }//GEN-LAST:event_muiRptGeneralActionPerformed

    private void muiCorrelativoBoletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiCorrelativoBoletaActionPerformed
        PckInterfaces.FrmCorrelativo salida= new PckInterfaces.FrmCorrelativo();        
        int x = (dskpPrincipal.getWidth() / 2) - salida.getWidth() / 2;
        int y = (dskpPrincipal.getHeight() / 2) - salida.getHeight() / 2; 
        if (salida.isShowing()) {
            salida.setLocation(x, y);
        } else {
            dskpPrincipal.add(salida);
            salida.setLocation(x, y);
            salida.show();
        }
    }//GEN-LAST:event_muiCorrelativoBoletaActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        PckInterfaces.FrmAcercaDe salida= new PckInterfaces.FrmAcercaDe();    
        int x = (dskpPrincipal.getWidth() / 2) - salida.getWidth() / 2;
        int y = (dskpPrincipal.getHeight() / 2) - salida.getHeight() / 2; 
        if (salida.isShowing()) {
            salida.setLocation(x, y);
        } else {
            dskpPrincipal.add(salida);
            salida.setLocation(x, y);
            salida.show();
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void muiRptBoletaDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiRptBoletaDetalleActionPerformed
       PckInterfaces.FrmRptBoletaDetalle seccion= new PckInterfaces.FrmRptBoletaDetalle();
        
        int x = (dskpPrincipal.getWidth() / 2) - seccion.getWidth() / 2;
                int y = (dskpPrincipal.getHeight() / 2) - seccion.getHeight() / 2;
 
                if (seccion.isShowing()) {
                    seccion.setLocation(x, y);
                } else {
                    dskpPrincipal.add(seccion);
                    seccion.setLocation(x, y);
                    seccion.show();
                }
    }//GEN-LAST:event_muiRptBoletaDetalleActionPerformed

    private void muiAlimentacionPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiAlimentacionPrecioActionPerformed
        PckInterfaces.FrmComidaMes comida= new PckInterfaces.FrmComidaMes();
        
        int x = (dskpPrincipal.getWidth() / 2) - comida.getWidth() / 2;
                int y = (dskpPrincipal.getHeight() / 2) - comida.getHeight() / 2;
 
                if (comida.isShowing()) {
                    comida.setLocation(x, y);
                } else {
                    dskpPrincipal.add(comida);
                    comida.setLocation(x, y);
                    comida.show();
                }
    }//GEN-LAST:event_muiAlimentacionPrecioActionPerformed

    private void muiPagoGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiPagoGeneralActionPerformed
        PckInterfaces.FrmPagoTodo comida= new PckInterfaces.FrmPagoTodo();
        
        int x = (dskpPrincipal.getWidth() / 2) - comida.getWidth() / 2;
                int y = (dskpPrincipal.getHeight() / 2) - comida.getHeight() / 2;
 
                if (comida.isShowing()) {
                    comida.setLocation(x, y);
                } else {
                    dskpPrincipal.add(comida);
                    comida.setLocation(x, y);
                    comida.show();
                }
    }//GEN-LAST:event_muiPagoGeneralActionPerformed

    private void muiNivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiNivelActionPerformed
        PckInterfaces.FrmNivel comida= new PckInterfaces.FrmNivel();
        
        int x = (dskpPrincipal.getWidth() / 2) - comida.getWidth() / 2;
                int y = (dskpPrincipal.getHeight() / 2) - comida.getHeight() / 2;
 
                if (comida.isShowing()) {
                    comida.setLocation(x, y);
                } else {
                    dskpPrincipal.add(comida);
                    comida.setLocation(x, y);
                    comida.show();
                }
    }//GEN-LAST:event_muiNivelActionPerformed

    private void muiCantidadLoncherasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiCantidadLoncherasActionPerformed
        
        PckInterfaces.FrmAlimentacionReporte comida= new PckInterfaces.FrmAlimentacionReporte();
        
        int x = (dskpPrincipal.getWidth() / 2) - comida.getWidth() / 2;
        int y = (dskpPrincipal.getHeight() / 2) - comida.getHeight() / 2;
        if (comida.isShowing()) {
            comida.setLocation(x, y);
        } else {
            dskpPrincipal.add(comida);
            comida.setLocation(x, y);
            comida.show();
        }
        
    }//GEN-LAST:event_muiCantidadLoncherasActionPerformed

    private void muiConsultaAsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiConsultaAsistenciaActionPerformed
        PckInterfaces.AsistenciaConsulta asistenciaConsulta= new PckInterfaces.AsistenciaConsulta();
        int x = dskpPrincipal.getWidth();
        int y = dskpPrincipal.getHeight(); 
        dskpPrincipal.removeAll();
        dskpPrincipal.updateUI();
        if (asistenciaConsulta.isShowing())
        {
            asistenciaConsulta.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(asistenciaConsulta);
            asistenciaConsulta.setBounds(0,0,x, y);
            asistenciaConsulta.setVisible(true);
        }
    }//GEN-LAST:event_muiConsultaAsistenciaActionPerformed

    private void muiRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiRolActionPerformed
        
        PckInterfaces.ConfiguracionSeguridad configuracionSeguridad= new PckInterfaces.ConfiguracionSeguridad();
        int x = dskpPrincipal.getWidth();
        int y = dskpPrincipal.getHeight(); 
        dskpPrincipal.removeAll();
        dskpPrincipal.updateUI();
        if (configuracionSeguridad.isShowing())
        {
            configuracionSeguridad.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(configuracionSeguridad);
            configuracionSeguridad.setBounds(0,0,x, y);
            configuracionSeguridad.setVisible(true);
        }
        
    }//GEN-LAST:event_muiRolActionPerformed

    private void muiAnecdotarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiAnecdotarioActionPerformed
        PckInterfaces.AnecdotarioDocente anecdotarioDocente = new PckInterfaces.AnecdotarioDocente();
        int x = dskpPrincipal.getWidth();
        int y = dskpPrincipal.getHeight(); 
        dskpPrincipal.removeAll();
        dskpPrincipal.updateUI();
        if (anecdotarioDocente.isShowing())
        {
            anecdotarioDocente.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(anecdotarioDocente);
            anecdotarioDocente.setBounds(0,0,x, y);
            anecdotarioDocente.setVisible(true);
        }
        
    }//GEN-LAST:event_muiAnecdotarioActionPerformed

    private void muiAdministrarAnecdotarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiAdministrarAnecdotarioActionPerformed
        
        PckInterfaces.AnecdotarioAdministracion anecdotarioAdministracion= new PckInterfaces.AnecdotarioAdministracion();        
        int x = dskpPrincipal.getWidth();
        int y = dskpPrincipal.getHeight(); 
        dskpPrincipal.removeAll();
        dskpPrincipal.updateUI();
        if (anecdotarioAdministracion.isShowing())
        {
            anecdotarioAdministracion.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(anecdotarioAdministracion);
            anecdotarioAdministracion.setBounds(0,0,x, y);
            anecdotarioAdministracion.setVisible(true);
        }
    }//GEN-LAST:event_muiAdministrarAnecdotarioActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        PckInterfaces.FrmMpsLogin obj= new PckInterfaces.FrmMpsLogin();            
        obj.setVisible(true);
        obj.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void muiAsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiAsistenciaActionPerformed
        PckInterfaces.AsistenciaDocente asistenciaDocente= new PckInterfaces.AsistenciaDocente();
        int x = dskpPrincipal.getWidth();
        int y = dskpPrincipal.getHeight(); 
        dskpPrincipal.removeAll();
        dskpPrincipal.updateUI();
        if (asistenciaDocente.isShowing())
        {
            asistenciaDocente.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(asistenciaDocente);
            asistenciaDocente.setBounds(0,0,x, y);
            asistenciaDocente.setVisible(true);
        }
    }//GEN-LAST:event_muiAsistenciaActionPerformed

    private void muiUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiUsuarioActionPerformed
        PckInterfaces.ConfiguracionUsuario asistenciaDocente= new PckInterfaces.ConfiguracionUsuario();
        int x = dskpPrincipal.getWidth();
        int y = dskpPrincipal.getHeight(); 
        dskpPrincipal.removeAll();
        dskpPrincipal.updateUI();
        if (asistenciaDocente.isShowing())
        {
            asistenciaDocente.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(asistenciaDocente);
            asistenciaDocente.setBounds(0,0,x, y);
            asistenciaDocente.setVisible(true);
        }
    }//GEN-LAST:event_muiUsuarioActionPerformed

    private void muiImpresionBoletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiImpresionBoletaActionPerformed
        PckInterfaces.FrmImpresionBoleta servi= new PckInterfaces.FrmImpresionBoleta();
        int x = (dskpPrincipal.getWidth() / 2) - servi.getWidth() / 2;
        int y = (dskpPrincipal.getHeight() / 2) - servi.getHeight() / 2; 
        if (servi.isShowing()) 
        {
            servi.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(servi);
            servi.setLocation(x, y);
            servi.show();
        }
    }//GEN-LAST:event_muiImpresionBoletaActionPerformed

    private void muiRptDirectorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiRptDirectorioActionPerformed
        PckInterfaces.FrmDirectorio servi= new PckInterfaces.FrmDirectorio();
        int x = (dskpPrincipal.getWidth() / 2) - servi.getWidth() / 2;
        int y = (dskpPrincipal.getHeight() / 2) - servi.getHeight() / 2; 
        if (servi.isShowing()) 
        {
            servi.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(servi);
            servi.setLocation(x, y);
            servi.show();
        }
    }//GEN-LAST:event_muiRptDirectorioActionPerformed

    private void muiRptCumpleanosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiRptCumpleanosActionPerformed
        PckInterfaces.FrmRptCumpleanos servi= new PckInterfaces.FrmRptCumpleanos();
        int x = (dskpPrincipal.getWidth() / 2) - servi.getWidth() / 2;
        int y = (dskpPrincipal.getHeight() / 2) - servi.getHeight() / 2; 
        if (servi.isShowing()) 
        {
            servi.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(servi);
            servi.setLocation(x, y);
            servi.show();
        }
    }//GEN-LAST:event_muiRptCumpleanosActionPerformed

    private void muiAdministrarAsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiAdministrarAsistenciaActionPerformed
        PckInterfaces.AsistenciaAdministracion asistenciaAdministracion= new PckInterfaces.AsistenciaAdministracion();
        int x = dskpPrincipal.getWidth();
        int y = dskpPrincipal.getHeight(); 
        dskpPrincipal.removeAll();
        dskpPrincipal.updateUI();
        if (asistenciaAdministracion.isShowing())
        {
            asistenciaAdministracion.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(asistenciaAdministracion);
            asistenciaAdministracion.setBounds(0,0,x, y);
            asistenciaAdministracion.setVisible(true);
        }
    }//GEN-LAST:event_muiAdministrarAsistenciaActionPerformed

    private void muiAjusteAlimentacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiAjusteAlimentacionActionPerformed
       PckInterfaces.FrmLoncheraAjuste servi= new PckInterfaces.FrmLoncheraAjuste();
        int x = (dskpPrincipal.getWidth() / 2) - servi.getWidth() / 2;
        int y = (dskpPrincipal.getHeight() / 2) - servi.getHeight() / 2; 
        if (servi.isShowing()) 
        {
            servi.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(servi);
            servi.setLocation(x, y);
            servi.show();
        }
    }//GEN-LAST:event_muiAjusteAlimentacionActionPerformed

    private void muiAdministrarEntregableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiAdministrarEntregableActionPerformed
       PckInterfaces.FrmEntregables servi= new PckInterfaces.FrmEntregables();
        int x = (dskpPrincipal.getWidth() / 2) - servi.getWidth() / 2;
        int y = (dskpPrincipal.getHeight() / 2) - servi.getHeight() / 2; 
        if (servi.isShowing()) 
        {
            servi.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(servi);
            servi.setLocation(x, y);
            servi.show();
        }
    }//GEN-LAST:event_muiAdministrarEntregableActionPerformed

    private void muiEstadoEntregableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiEstadoEntregableActionPerformed
        PckInterfaces.FrmEntregablesEstado servi= new PckInterfaces.FrmEntregablesEstado();
        int x = (dskpPrincipal.getWidth() / 2) - servi.getWidth() / 2;
        int y = (dskpPrincipal.getHeight() / 2) - servi.getHeight() / 2; 
        if (servi.isShowing()) 
        {
            servi.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(servi);
            servi.setLocation(x, y);
            servi.show();
        }
    }//GEN-LAST:event_muiEstadoEntregableActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        PckInterfaces.FrmEntregablesDocente servi= new PckInterfaces.FrmEntregablesDocente();
        int x = (dskpPrincipal.getWidth() / 2) - servi.getWidth() / 2;
        int y = (dskpPrincipal.getHeight() / 2) - servi.getHeight() / 2; 
        if (servi.isShowing()) 
        {
            servi.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(servi);
            servi.setLocation(x, y);
            servi.show();
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void muiFormatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiFormatoActionPerformed
        
        
        PckInterfaces.FrmFormato servi= new PckInterfaces.FrmFormato();
        int x = (dskpPrincipal.getWidth() / 2) - servi.getWidth() / 2;
        int y = (dskpPrincipal.getHeight() / 2) - servi.getHeight() / 2; 
        if (servi.isShowing()) 
        {
            servi.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(servi);
            servi.setLocation(x, y);
            servi.show();
        }
        
    }//GEN-LAST:event_muiFormatoActionPerformed

    private void muiCumplimientoAulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiCumplimientoAulaActionPerformed
        
        PckInterfaces.FrmPagoAula servi= new PckInterfaces.FrmPagoAula();
        int x = (dskpPrincipal.getWidth() / 2) - servi.getWidth() / 2;
        int y = (dskpPrincipal.getHeight() / 2) - servi.getHeight() / 2; 
        if (servi.isShowing()) 
        {
            servi.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(servi);
            servi.setLocation(x, y);
            servi.show();
        }
        
    }//GEN-LAST:event_muiCumplimientoAulaActionPerformed

    private void muiCumplimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiCumplimientoActionPerformed
        
        PckInterfaces.FrmMpsRequerimiento servi= new PckInterfaces.FrmMpsRequerimiento();
        int x = (dskpPrincipal.getWidth() / 2) - servi.getWidth() / 2;
        int y = (dskpPrincipal.getHeight() / 2) - servi.getHeight() / 2; 
        if (servi.isShowing()) 
        {
            servi.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(servi);
            servi.setLocation(x, y);
            servi.show();
        }
        
    }//GEN-LAST:event_muiCumplimientoActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        
        PckInterfaces.FrmRptRequerimientoAula servi= new PckInterfaces.FrmRptRequerimientoAula();
        int x = (dskpPrincipal.getWidth() / 2) - servi.getWidth() / 2;
        int y = (dskpPrincipal.getHeight() / 2) - servi.getHeight() / 2; 
        if (servi.isShowing()) 
        {
            servi.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(servi);
            servi.setLocation(x, y);
            servi.show();
        }
        
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void muiAlmacenAdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiAlmacenAdmActionPerformed
        
        PckInterfaces.AlmacenAdministracion almacenAdministracion= new PckInterfaces.AlmacenAdministracion();
        int x = dskpPrincipal.getWidth();
        int y = dskpPrincipal.getHeight(); 
        dskpPrincipal.removeAll();
        dskpPrincipal.updateUI();
        if (almacenAdministracion.isShowing())
        {
            almacenAdministracion.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(almacenAdministracion);
            almacenAdministracion.setBounds(0,0,x, y);
            almacenAdministracion.setVisible(true);
        }
    }//GEN-LAST:event_muiAlmacenAdmActionPerformed

    private void muiAlmacenItemAdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiAlmacenItemAdmActionPerformed
        PckInterfaces.AlmacenAdministracionItem almacenAdministracionItem= new PckInterfaces.AlmacenAdministracionItem();
        int x = dskpPrincipal.getWidth();
        int y = dskpPrincipal.getHeight(); 
        dskpPrincipal.removeAll();
        dskpPrincipal.updateUI();
        if (almacenAdministracionItem.isShowing())
        {
            almacenAdministracionItem.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(almacenAdministracionItem);
            almacenAdministracionItem.setBounds(0,0,x, y);
            almacenAdministracionItem.setVisible(true);
        }
    }//GEN-LAST:event_muiAlmacenItemAdmActionPerformed

    private void muiAlmacenRequerimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiAlmacenRequerimientoActionPerformed
        
        LlamarAlmacenRequerimiento();
        
    }//GEN-LAST:event_muiAlmacenRequerimientoActionPerformed

    public static void LlamarAlmacenRequerimiento()
    {
        PckInterfaces.AlmacenRequerimiento almacenRequerimiento= new PckInterfaces.AlmacenRequerimiento();
        int x = dskpPrincipal.getWidth();
        int y = dskpPrincipal.getHeight(); 
        dskpPrincipal.removeAll();
        dskpPrincipal.updateUI();
        if (almacenRequerimiento.isShowing())
        {
            almacenRequerimiento.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(almacenRequerimiento);
            almacenRequerimiento.setBounds(0,0,x, y);
            almacenRequerimiento.setVisible(true);
        }
    }
    
    public static void LlamarAdministracionAlmacenRequerimiento()
    {
        PckInterfaces.AlmacenAdministracionRequerimiento almacenRequerimiento= new PckInterfaces.AlmacenAdministracionRequerimiento();
        int x = dskpPrincipal.getWidth();
        int y = dskpPrincipal.getHeight(); 
        dskpPrincipal.removeAll();
        dskpPrincipal.updateUI();
        if (almacenRequerimiento.isShowing())
        {
            almacenRequerimiento.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(almacenRequerimiento);
            almacenRequerimiento.setBounds(0,0,x, y);
            almacenRequerimiento.setVisible(true);
        }
    }
    
    private void muiAlmacenRequerimientoAdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiAlmacenRequerimientoAdmActionPerformed
        
        LlamarAdministracionAlmacenRequerimiento();
    }//GEN-LAST:event_muiAlmacenRequerimientoAdmActionPerformed

    private void muiAlmacenItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiAlmacenItemActionPerformed
        PckInterfaces.AlmacenItemConsultar almacenRequerimiento= new PckInterfaces.AlmacenItemConsultar();
        int x = dskpPrincipal.getWidth();
        int y = dskpPrincipal.getHeight(); 
        dskpPrincipal.removeAll();
        dskpPrincipal.updateUI();
        if (almacenRequerimiento.isShowing())
        {
            almacenRequerimiento.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(almacenRequerimiento);
            almacenRequerimiento.setBounds(0,0,x, y);
            almacenRequerimiento.setVisible(true);
        }
    }//GEN-LAST:event_muiAlmacenItemActionPerformed

    private void muiConsultarItemAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiConsultarItemAlmacenActionPerformed
        PckInterfaces.AlmacenAdministracionItemConsultar almacenRequerimiento= new PckInterfaces.AlmacenAdministracionItemConsultar();
        int x = dskpPrincipal.getWidth();
        int y = dskpPrincipal.getHeight(); 
        dskpPrincipal.removeAll();
        dskpPrincipal.updateUI();
        if (almacenRequerimiento.isShowing())
        {
            almacenRequerimiento.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(almacenRequerimiento);
            almacenRequerimiento.setBounds(0,0,x, y);
            almacenRequerimiento.setVisible(true);
        }
    }//GEN-LAST:event_muiConsultarItemAlmacenActionPerformed

    private void muiInasistenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiInasistenciasActionPerformed
        PckInterfaces.AsistenciaDocenteFichaInasistencia asistenciaDocenteFicha= new PckInterfaces.AsistenciaDocenteFichaInasistencia();
        int x = dskpPrincipal.getWidth();
        int y = dskpPrincipal.getHeight(); 
        dskpPrincipal.removeAll();
        dskpPrincipal.updateUI();
        if (asistenciaDocenteFicha.isShowing())
        {
            asistenciaDocenteFicha.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(asistenciaDocenteFicha);
            asistenciaDocenteFicha.setBounds(0,0,x, y);
            asistenciaDocenteFicha.setVisible(true);
        }
    }//GEN-LAST:event_muiInasistenciasActionPerformed

    private void muiRptAlumnoResumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiRptAlumnoResumenActionPerformed
       
        PckInterfaces.ReporteAlumnoResumenHoy asistenciaConsulta= new PckInterfaces.ReporteAlumnoResumenHoy();
        int x = dskpPrincipal.getWidth();
        int y = dskpPrincipal.getHeight(); 
        dskpPrincipal.removeAll();
        dskpPrincipal.updateUI();
        if (asistenciaConsulta.isShowing())
        {
            asistenciaConsulta.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(asistenciaConsulta);
            asistenciaConsulta.setBounds(0,0,x, y);
            asistenciaConsulta.setVisible(true);
        }
    }//GEN-LAST:event_muiRptAlumnoResumenActionPerformed

    private void muiDeudaMontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiDeudaMontoActionPerformed
        PckInterfaces.FrmDeudasPorMonto servi= new PckInterfaces.FrmDeudasPorMonto();   
        int x = (dskpPrincipal.getWidth() / 2) - servi.getWidth() / 2;
        int y = (dskpPrincipal.getHeight() / 2) - servi.getHeight() / 2; 
        if (servi.isShowing()) 
        {
            servi.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(servi);
            servi.setLocation(x, y);
            servi.show();
        }
    }//GEN-LAST:event_muiDeudaMontoActionPerformed

    private void muiRptSexoAulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiRptSexoAulaActionPerformed
        
        PckInterfaces.FrmRptSexoAlumno servi= new PckInterfaces.FrmRptSexoAlumno();
        int x = (dskpPrincipal.getWidth() / 2) - servi.getWidth() / 2;
        int y = (dskpPrincipal.getHeight() / 2) - servi.getHeight() / 2; 
        if (servi.isShowing()) 
        {
            servi.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(servi);
            servi.setLocation(x, y);
            servi.show();
        }
        
    }//GEN-LAST:event_muiRptSexoAulaActionPerformed

    private void muiRptAlumnoDescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiRptAlumnoDescuentoActionPerformed
        
        PckInterfaces.FrmAlumnosDescuento servi= new PckInterfaces.FrmAlumnosDescuento();
        int x = (dskpPrincipal.getWidth() / 2) - servi.getWidth() / 2;
        int y = (dskpPrincipal.getHeight() / 2) - servi.getHeight() / 2; 
        if (servi.isShowing()) 
        {
            servi.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(servi);
            servi.setLocation(x, y);
            servi.show();
        }
        
    }//GEN-LAST:event_muiRptAlumnoDescuentoActionPerformed

    private void muiRptVacanteLibreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiRptVacanteLibreActionPerformed
        PckInterfaces.FrmRptVacantesLibres servi= new PckInterfaces.FrmRptVacantesLibres();
        int x = (dskpPrincipal.getWidth() / 2) - servi.getWidth() / 2;
        int y = (dskpPrincipal.getHeight() / 2) - servi.getHeight() / 2; 
        if (servi.isShowing()) 
        {
            servi.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(servi);
            servi.setLocation(x, y);
            servi.show();
        }
    }//GEN-LAST:event_muiRptVacanteLibreActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        PckInterfaces.FrmPagoHistorial servi= new PckInterfaces.FrmPagoHistorial();
        int x = (dskpPrincipal.getWidth() / 2) - servi.getWidth() / 2;
        int y = (dskpPrincipal.getHeight() / 2) - servi.getHeight() / 2; 
        if (servi.isShowing()) 
        {
            servi.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(servi);
            servi.setLocation(x, y);
            servi.show();
        }
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void muiPsicologiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiPsicologiaActionPerformed
        PckInterfaces.PsicologiaDocente psicologiaDocente = new PckInterfaces.PsicologiaDocente();
        int x = dskpPrincipal.getWidth();
        int y = dskpPrincipal.getHeight(); 
        dskpPrincipal.removeAll();
        dskpPrincipal.updateUI();
        if (psicologiaDocente.isShowing())
        {
            psicologiaDocente.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(psicologiaDocente);
            psicologiaDocente.setBounds(0,0,x, y);
            psicologiaDocente.setVisible(true);
        }
    }//GEN-LAST:event_muiPsicologiaActionPerformed

    private void muiAdmPsicologiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiAdmPsicologiaActionPerformed
        PckInterfaces.PsicologiaAdministracion psicologiaAdministracion = new PckInterfaces.PsicologiaAdministracion();
        int x = dskpPrincipal.getWidth();
        int y = dskpPrincipal.getHeight(); 
        dskpPrincipal.removeAll();
        dskpPrincipal.updateUI();
        if (psicologiaAdministracion.isShowing())
        {
            psicologiaAdministracion.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(psicologiaAdministracion);
            psicologiaAdministracion.setBounds(0,0,x, y);
            psicologiaAdministracion.setVisible(true);
        }
    }//GEN-LAST:event_muiAdmPsicologiaActionPerformed

    private void muiHuellaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiHuellaActionPerformed
        PckInterfaces.MatriculaHuella matriculaHuella= new PckInterfaces.MatriculaHuella();
        int x = dskpPrincipal.getWidth();
        int y = dskpPrincipal.getHeight(); 
        dskpPrincipal.removeAll();
        dskpPrincipal.updateUI();
        if (matriculaHuella.isShowing())
        {
            matriculaHuella.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(matriculaHuella);
            matriculaHuella.setBounds(0,0,x, y);
            matriculaHuella.setVisible(true);
        }
    }//GEN-LAST:event_muiHuellaActionPerformed

    private void muiRptDeudaMontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiRptDeudaMontoActionPerformed
        PckInterfaces.ReporteDeudaMonto reporteDeudaMonto= new PckInterfaces.ReporteDeudaMonto();
        int x = dskpPrincipal.getWidth();
        int y = dskpPrincipal.getHeight(); 
        dskpPrincipal.removeAll();
        dskpPrincipal.updateUI();
        if (reporteDeudaMonto.isShowing())
        {
            reporteDeudaMonto.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(reporteDeudaMonto);
            reporteDeudaMonto.setBounds(0,0,x, y);
            reporteDeudaMonto.setVisible(true);
        }
    }//GEN-LAST:event_muiRptDeudaMontoActionPerformed

    private void muiAjusteVencimientoMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiAjusteVencimientoMesActionPerformed
        PckInterfaces.AjusteVencimientoMes ajusteVencimientoMes= new PckInterfaces.AjusteVencimientoMes();
        int x = dskpPrincipal.getWidth();
        int y = dskpPrincipal.getHeight(); 
        dskpPrincipal.removeAll();
        dskpPrincipal.updateUI();
        if (ajusteVencimientoMes.isShowing())
        {
            ajusteVencimientoMes.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(ajusteVencimientoMes);
            ajusteVencimientoMes.setBounds(0,0,x, y);
            ajusteVencimientoMes.setVisible(true);
        }
    }//GEN-LAST:event_muiAjusteVencimientoMesActionPerformed

    private void muiRptCumpleanosCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiRptCumpleanosCardActionPerformed
        
        PckInterfaces.ReporteImprimirCumpleanos imprimirCumpleanos= new PckInterfaces.ReporteImprimirCumpleanos();
        int x = dskpPrincipal.getWidth();
        int y = dskpPrincipal.getHeight(); 
        dskpPrincipal.removeAll();
        dskpPrincipal.updateUI();
        if (imprimirCumpleanos.isShowing())
        {
            imprimirCumpleanos.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(imprimirCumpleanos);
            imprimirCumpleanos.setBounds(0,0,x, y);
            imprimirCumpleanos.setVisible(true);
        }
    }//GEN-LAST:event_muiRptCumpleanosCardActionPerformed

    private void muiRptAlumnoRetiradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiRptAlumnoRetiradoActionPerformed
        PckInterfaces.ReporteAlumnosRetirados reporteAlumnosRetirados= new PckInterfaces.ReporteAlumnosRetirados();
        int x = dskpPrincipal.getWidth();
        int y = dskpPrincipal.getHeight(); 
        dskpPrincipal.removeAll();
        dskpPrincipal.updateUI();
        if (reporteAlumnosRetirados.isShowing())
        {
            reporteAlumnosRetirados.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(reporteAlumnosRetirados);
            reporteAlumnosRetirados.setBounds(0,0,x, y);
            reporteAlumnosRetirados.setVisible(true);
        }
    }//GEN-LAST:event_muiRptAlumnoRetiradoActionPerformed

    private void muiRptPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiRptPersonalActionPerformed
        
        PckInterfaces.ReportePersonal asistenciaConsulta= new PckInterfaces.ReportePersonal();
        int x = dskpPrincipal.getWidth();
        int y = dskpPrincipal.getHeight(); 
        dskpPrincipal.removeAll();
        dskpPrincipal.updateUI();
        if (asistenciaConsulta.isShowing())
        {
            asistenciaConsulta.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(asistenciaConsulta);
            asistenciaConsulta.setBounds(0,0,x, y);
            asistenciaConsulta.setVisible(true);
        }
        
    }//GEN-LAST:event_muiRptPersonalActionPerformed

    private void muiConstanciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muiConstanciasActionPerformed
        PckInterfaces.ContabilidadConstancia contabilidadConstancia= new PckInterfaces.ContabilidadConstancia();
        int x = dskpPrincipal.getWidth();
        int y = dskpPrincipal.getHeight(); 
        dskpPrincipal.removeAll();
        dskpPrincipal.updateUI();
        if (contabilidadConstancia.isShowing())
        {
            contabilidadConstancia.setLocation(x, y);
        } 
        else 
        {
            dskpPrincipal.add(contabilidadConstancia);
            contabilidadConstancia.setBounds(0,0,x, y);
            contabilidadConstancia.setVisible(true);
        }
    }//GEN-LAST:event_muiConstanciasActionPerformed

   
    void llamaServicio(){
    PckInterfaces.FrmServicio servi= new PckInterfaces.FrmServicio();
        
        int x = (dskpPrincipal.getWidth() / 2) - servi.getWidth() / 2;
        int y = (dskpPrincipal.getHeight() / 2) - servi.getHeight() / 2;
 
        if (servi.isShowing()) {
            servi.setLocation(x, y);
        } else {
            dskpPrincipal.add(servi);
            servi.setLocation(x, y);
            servi.show();
            
        }
     }
    void llamaProducto(){
    PckInterfaces.FrmProducto fichamatri= new PckInterfaces.FrmProducto();
        
        int x = (dskpPrincipal.getWidth() / 2) - fichamatri.getWidth() / 2;
                int y = (dskpPrincipal.getHeight() / 2) - fichamatri.getHeight() / 2;
 
                if (fichamatri.isShowing()) {
                    fichamatri.setLocation(x, y);
                } else {
                    dskpPrincipal.add(fichamatri);
                    fichamatri.setLocation(x, y);
                    fichamatri.show();
                }
     }
 
    private void llamaMatricula()
    {
        
        PckInterfaces.FrmMatricula matri= new PckInterfaces.FrmMatricula();
        
        int x = (dskpPrincipal.getWidth() / 2) - matri.getWidth() / 2;
                int y = (dskpPrincipal.getHeight() / 2) - matri.getHeight() / 2;
 
                if (matri.isShowing()) {
                    matri.setLocation(x, y);
                } else {
                    dskpPrincipal.add(matri);
                    matri.setLocation(x, y);
                    matri.show();
                }
                
    }
   
    
    private void llamaReporteNinosPorSalon()
    {
        PckInterfaces.FrmRptNinosPorSalon fichamatri= new PckInterfaces.FrmRptNinosPorSalon();
        
        int x = (dskpPrincipal.getWidth() / 2) - fichamatri.getWidth() / 2;
                int y = (dskpPrincipal.getHeight() / 2) - fichamatri.getHeight() / 2;
 
                if (fichamatri.isShowing()) {
                    fichamatri.setLocation(x, y);
                } else {
                    dskpPrincipal.add(fichamatri);
                    fichamatri.setLocation(x, y);
                    fichamatri.show();
                }
        
    }
    
        
    private void llamaSeccion()
    {
        PckInterfaces.FrmSeccion seccion= new PckInterfaces.FrmSeccion();
        
        int x = (dskpPrincipal.getWidth() / 2) - seccion.getWidth() / 2;
                int y = (dskpPrincipal.getHeight() / 2) - seccion.getHeight() / 2;
 
                if (seccion.isShowing()) {
                    seccion.setLocation(x, y);
                } else {
                    dskpPrincipal.add(seccion);
                    seccion.setLocation(x, y);
                    seccion.show();
                }
        
    }
    
   
    
    private void llamaPeriodo()
    {
        PckInterfaces.FrmPeriodo periodo= new PckInterfaces.FrmPeriodo();
        
        int x = (dskpPrincipal.getWidth() / 2) - periodo.getWidth() / 2;
                int y = (dskpPrincipal.getHeight() / 2) - periodo.getHeight() / 2;
 
                if (periodo.isShowing()) {
                    periodo.setLocation(x, y);
                } else {
                    dskpPrincipal.add(periodo);
                    periodo.setLocation(x, y);
                    periodo.show();
                }
        
    }
    
    private void ValidarPermisosAula(ArrayList<SeguridadPermisosAulaEntity> arrayPermisosAula) {
        
        for(SeguridadPermisosAulaEntity pae : arrayPermisosAula) {
            
            if(pae.getAnecdotario().equals("1")) { muiAnecdotario.setVisible(true); }
            else { muiAnecdotario.setVisible(false); }
            
            if(pae.getPsicologia().equals("1")) { muiPsicologia.setVisible(true); }
            else { muiPsicologia.setVisible(false); }
            
            if(pae.getAsistencia().equals("1")) { muiAsistencia.setVisible(true); }
            else { muiAsistencia.setVisible(false); }
            
            if(pae.getInasistencia().equals("1")) { muiInasistencias.setVisible(true); }
            else { muiInasistencias.setVisible(false); }
            
            if(pae.getCumplimiento().equals("1")) { muiCumplimiento.setVisible(true); }
            else { muiCumplimiento.setVisible(false); }
            
            if(pae.getRequerimiento_almacen().equals("1")) { muiAlmacenRequerimiento.setVisible(true); }
            else { muiAlmacenRequerimiento.setVisible(false); }
            
            if(pae.getItem_consulta().equals("1")) { muiAlmacenItem.setVisible(true); }
            else { muiAlmacenItem.setVisible(false); }
            
        }
    }
    
    private void ValidarPermisosMatricula(ArrayList<SeguridadPermisosMatriculaEntity> arrayPermisosMatricula) {
        
        for(SeguridadPermisosMatriculaEntity pme : arrayPermisosMatricula) {
            
            if(pme.getMatricula().equals("1")) { muiMatricula.setVisible(true); }
            else { muiMatricula.setVisible(false); }
            
            if(pme.getHuella().equals("1")) { muiHuella.setVisible(true); }
            else { muiHuella.setVisible(false); }
            
            if(pme.getNivel().equals("1")) { muiNivel.setVisible(true); }
            else { muiNivel.setVisible(false); }
            
            if(pme.getPeriodo().equals("1")) { muiPeriodo.setVisible(true); }
            else { muiPeriodo.setVisible(false); }
            
            if(pme.getAula().equals("1")) { muiAula.setVisible(true); }
            else { muiAula.setVisible(false); }
            
            if(pme.getMatricula().equals("0") && pme.getHuella().equals("0") && pme.getNivel().equals("0") &&
                pme.getPeriodo().equals("0") && pme.getAula().equals("0") ) { 
                mnuMatricula.setVisible(false);
            }
        }
    }
    
    private void ValidarPermisosAdministracion(ArrayList<SeguridadPermisosAdministracionEntity> arrayPermisosAdministracion) {
        
        for(SeguridadPermisosAdministracionEntity pae : arrayPermisosAdministracion) {
            
            if(pae.getLonchera().equals("1")) { muiCantidadLoncheras.setVisible(true); }
            else { muiCantidadLoncheras.setVisible(false); }
            
            if(pae.getAnecdotario().equals("1")) { muiAdministrarAnecdotario.setVisible(true); }
            else { muiAdministrarAnecdotario.setVisible(false); }
            
            if(pae.getPsicologia().equals("1")) { muiAdmPsicologia.setVisible(true); }
            else { muiAdmPsicologia.setVisible(false); }
            
            editarCasoPsicologia = pae.getPsicologia_editar().equals("1");
            
            if(pae.getAsistencia().equals("1")) { muiAdministrarAsistencia.setVisible(true); }
            else { muiAdministrarAsistencia.setVisible(false); }
            
            if(pae.getAsistencia_consulta().equals("1")) { muiConsultaAsistencia.setVisible(true); }
            else { muiConsultaAsistencia.setVisible(false); }
            
            if(pae.getCumplimiento().equals("1")) { muiCumplimientoAula.setVisible(true); }
            else { muiCumplimientoAula.setVisible(false); }
            
            if(pae.getAlmacen().equals("1")) { muiAlmacenAdm.setVisible(true); }
            else { muiAlmacenAdm.setVisible(false); }
            
            if(pae.getItem().equals("1")) { muiAlmacenItemAdm.setVisible(true); }
            else { muiAlmacenItemAdm.setVisible(false); }
            
            if(pae.getRequerimiento().equals("1")) { muiAlmacenRequerimientoAdm.setVisible(true); }
            else { muiAlmacenRequerimientoAdm.setVisible(false); }
            
            if(pae.getItem_consulta().equals("1")) { muiConsultarItemAlmacen.setVisible(true); }
            else { muiConsultarItemAlmacen.setVisible(false); }
            
            if(pae.getLonchera().equals("0") && pae.getAnecdotario().equals("0") && pae.getPsicologia().equals("0") &&
                pae.getAsistencia().equals("0") && pae.getAsistencia_consulta().equals("0") && pae.getCumplimiento().equals("0") &&
                pae.getAlmacen().equals("0") && pae.getItem().equals("0")  && pae.getRequerimiento().equals("0") ) { 
                mnuAdministracion.setVisible(false);
            }
        }
    }
    
    private void ValidarPermisosProductoServicio(ArrayList<SeguridadPermisosProductoServicioEntity> arrayPermisosProductoServicio) {
        
        for(SeguridadPermisosProductoServicioEntity ppse : arrayPermisosProductoServicio) {
                       
            mantenimientoProducto = ppse.getProducto_mantenimiento().equals("1");            
            asignarProducto = ppse.getProducto_asignar().equals("1");
            
            mantenimientoServicio = ppse.getServicio_mantenimiento().equals("1");
            asignarServicio = ppse.getServicio_asignar().equals("1");
            
            if(!mantenimientoProducto && !asignarProducto) { muiProducto.setVisible(false); }
            if(!mantenimientoServicio && !asignarServicio) { muiServicio.setVisible(false); }
            
            if(!mantenimientoProducto && !asignarProducto && !mantenimientoServicio && !asignarServicio) {
                mnuProductoServicio.setVisible(false);
            }
        }
    }
    
    private void ValidarPermisosContabilidad(ArrayList<SeguridadPermisosContabilidadEntity> arrayPermisosContabilidad) {
        
        for(SeguridadPermisosContabilidadEntity pce : arrayPermisosContabilidad) {
            
            if(pce.getPago().equals("1")) { muiPagoGeneral.setVisible(true); }
            else { muiPagoGeneral.setVisible(false); }
            
            editarPago = pce.getPago_editar().equals("1");
            
            if(pce.getBoleta_anular().equals("1")) { muiBoletaAnulada.setVisible(true); }
            else { muiBoletaAnulada.setVisible(false); }
            
            if(pce.getHistorial_autorizacion().equals("1")) { jMenuItem19.setVisible(true); }
            else { jMenuItem19.setVisible(false); }
            
            if(pce.getDeuda_monto().equals("1")) { muiDeudaMonto.setVisible(true); }
            else { muiDeudaMonto.setVisible(false); }
            
            if(pce.getIngreso_egreso().equals("1")) { muiIngresoEgreso.setVisible(true); }
            else { muiIngresoEgreso.setVisible(false); }
            
            if(pce.getEgreso().equals("1")) { muiEgreso.setVisible(true); }
            else { muiEgreso.setVisible(false); }
            
            editarEgreso = pce.getEgreso_editar().equals("1");
            
            if(pce.getBoleta_imprimir().equals("1")) { muiImpresionBoleta.setVisible(true); }
            else { muiImpresionBoleta.setVisible(false); }            
            
            if(pce.getPago().equals("0") && pce.getBoleta_anular().equals("0") && pce.getHistorial_autorizacion().equals("0") &&
                pce.getDeuda_monto().equals("0") &&  pce.getIngreso_egreso().equals("0") && pce.getEgreso().equals("0") &&
                pce.getBoleta_imprimir().equals("0") ) {
                
                mnuContabilidad.setVisible(false);
            }                            
            
        }
    }
    
    private void ValidarPermisosHerramienta(ArrayList<SeguridadPermisosHerramientaEntity> arrayPermisosHerramientas) {
        
        for(SeguridadPermisosHerramientaEntity phe : arrayPermisosHerramientas) {
            
            if(phe.getCodigo_boleta_ajuste().equals("1")) { muiCorrelativoBoleta.setVisible(true); }
            else { muiCorrelativoBoleta.setVisible(false); }
            
            if(phe.getLonchera_mes_ajuste().equals("1")) { muiAlimentacionPrecio.setVisible(true); }
            else { muiAlimentacionPrecio.setVisible(false); }
            
            if(phe.getLonchera_servicio_ajuste().equals("1")) { muiAjusteAlimentacion.setVisible(true); }
            else { muiAjusteAlimentacion.setVisible(false); }
            
            if(phe.getCodigo_boleta_ajuste().equals("0") && phe.getLonchera_mes_ajuste().equals("0") && phe.getLonchera_servicio_ajuste().equals("0")) {
                
                mnuHerramientas.setVisible(false);
            }                
                        
        }
    }
    
    private void ValidarPermisosSeguridad(ArrayList<SeguridadPermisosSeguridadEntity> arrayPermisosSeguridad) {
        
        for(SeguridadPermisosSeguridadEntity pse : arrayPermisosSeguridad) {
            
            if(pse.getRol().equals("1")) { muiRol.setVisible(true); }
            else { muiRol.setVisible(false); }
            
            if(pse.getUsuario().equals("1")) { muiUsuario.setVisible(true); }
            else { muiUsuario.setVisible(false); }
                        
            if( pse.getRol().equals("0") && pse.getUsuario().equals("0") ) {
                
                mnuConfiguracion.setVisible(false);
            }                
                        
        }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane dskpPrincipal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator12;
    private javax.swing.JPopupMenu.Separator jSeparator13;
    private javax.swing.JPopupMenu.Separator jSeparator14;
    private javax.swing.JPopupMenu.Separator jSeparator15;
    private javax.swing.JPopupMenu.Separator jSeparator16;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JMenu mnuAdministracion;
    private javax.swing.JMenu mnuConfiguracion;
    private javax.swing.JMenu mnuContabilidad;
    private javax.swing.JMenu mnuHerramientas;
    private javax.swing.JMenu mnuMatricula;
    private javax.swing.JMenu mnuMiAula;
    private javax.swing.JMenuBar mnuPrincipal;
    private javax.swing.JMenu mnuProductoServicio;
    private javax.swing.JMenu mnuReportes;
    private javax.swing.JMenuItem muiAdmPsicologia;
    private javax.swing.JMenuItem muiAdministrarAnecdotario;
    private javax.swing.JMenuItem muiAdministrarAsistencia;
    private javax.swing.JMenuItem muiAdministrarEntregable;
    private javax.swing.JMenuItem muiAjusteAlimentacion;
    private javax.swing.JMenuItem muiAjusteVencimientoMes;
    private javax.swing.JMenuItem muiAlimentacionPrecio;
    private javax.swing.JMenuItem muiAlmacenAdm;
    private javax.swing.JMenuItem muiAlmacenItem;
    private javax.swing.JMenuItem muiAlmacenItemAdm;
    private javax.swing.JMenuItem muiAlmacenRequerimiento;
    private javax.swing.JMenuItem muiAlmacenRequerimientoAdm;
    private javax.swing.JMenuItem muiAnecdotario;
    private javax.swing.JMenuItem muiAsistencia;
    private javax.swing.JMenuItem muiAula;
    private javax.swing.JMenuItem muiBoletaAnulada;
    private javax.swing.JMenuItem muiCantidadLoncheras;
    private javax.swing.JMenuItem muiConstancias;
    private javax.swing.JMenuItem muiConsultaAsistencia;
    private javax.swing.JMenuItem muiConsultarItemAlmacen;
    private javax.swing.JMenuItem muiCorrelativoBoleta;
    private javax.swing.JMenuItem muiCumplimiento;
    private javax.swing.JMenuItem muiCumplimientoAula;
    private javax.swing.JMenuItem muiDeudaMonto;
    private javax.swing.JMenuItem muiEgreso;
    private javax.swing.JMenuItem muiEstadoEntregable;
    private javax.swing.JMenuItem muiFormato;
    private javax.swing.JMenuItem muiHuella;
    private javax.swing.JMenuItem muiImpresionBoleta;
    private javax.swing.JMenuItem muiInasistencias;
    private javax.swing.JMenuItem muiIngresoEgreso;
    private javax.swing.JMenuItem muiMatricula;
    private javax.swing.JMenuItem muiNivel;
    private javax.swing.JMenuItem muiPagoGeneral;
    private javax.swing.JMenuItem muiPeriodo;
    private javax.swing.JMenuItem muiProducto;
    private javax.swing.JMenuItem muiPsicologia;
    private javax.swing.JMenuItem muiRol;
    private javax.swing.JMenuItem muiRptAlumnoDescuento;
    private javax.swing.JMenuItem muiRptAlumnoResumen;
    private javax.swing.JMenuItem muiRptAlumnoRetirado;
    private javax.swing.JMenuItem muiRptBoleta;
    private javax.swing.JMenuItem muiRptBoletaDetalle;
    private javax.swing.JMenuItem muiRptCumpleanos;
    private javax.swing.JMenuItem muiRptCumpleanosCard;
    private javax.swing.JMenuItem muiRptDeudaMonto;
    private javax.swing.JMenuItem muiRptDirectorio;
    private javax.swing.JMenuItem muiRptGeneral;
    private javax.swing.JMenuItem muiRptListado;
    private javax.swing.JMenuItem muiRptPendienteSinDeuda;
    private javax.swing.JMenuItem muiRptPersonal;
    private javax.swing.JMenuItem muiRptSalida;
    private javax.swing.JMenuItem muiRptSexoAula;
    private javax.swing.JMenuItem muiRptVacanteLibre;
    private javax.swing.JMenuItem muiServicio;
    private javax.swing.JMenuItem muiUsuario;
    // End of variables declaration//GEN-END:variables
}
