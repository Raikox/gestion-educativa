/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PckInterfaces;

import PckConexion.ClsConexion;
import PckEntidad.ClsControlesRequerimiento;
import PckEntidad.ClsEntidadAlumnoPago;
import PckEntidad.ClsEntidadPagoAulaDeuda;
import PckNegocio.ClsAlumno;
import PckNegocio.ClsPagoAulaDeuda;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import layout.TableLayout;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email jkevin.mv@gmail.com
 */
public class FrmMpsRequerimientoAula extends javax.swing.JInternalFrame {

    Font fuente=new Font("DroidSans", Font.BOLD, 15);
    Font fuente2 = new Font("DroidSans",Font.BOLD,11);
    Font fuente3 = new Font("DroidSans",Font.PLAIN,13);
    
    ArrayList<ClsControlesRequerimiento> arrayListadoDeControles = new ArrayList<>();
    ArrayList<String> arrayMatriculaId = new ArrayList();
    ArrayList<String> arrayAulaDeudaId = new ArrayList();
    
    static String nombreAula;
    static String idAula;
    static String idItem;
    static String requerimiento;
    
    boolean llenado=false;
    
    Connection conexion;
    /**
     * Creates new form FrmRequerimientoAula
     */
    public FrmMpsRequerimientoAula() {
        initComponents();
        
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);
        txtAula.setFont(fuente);
        txtRequerimiento.setFont(fuente);
        txtAula.setText(nombreAula); 
        txtRequerimiento.setText(requerimiento);
        //--------------------------------------------------------------------------------------------//
        conexion = ClsConexion.getConection();
        int limite = 0;
        ArrayList<String> arrayAlumnoNombre = new ArrayList();
        ClsAlumno alumno = new ClsAlumno();
        ClsPagoAulaDeuda aulaDeuda = new ClsPagoAulaDeuda();
        ResultSet rsAxu;
        //Obtiene los alumnos de un aula con item asignado
        ArrayList<ClsEntidadAlumnoPago> arrayAlumno = new ArrayList<>();
        try 
        {
            rsAxu = aulaDeuda.SeleccionarPagoAulaDeuda(idAula, idItem, conexion);
            while(rsAxu.next())
            {
                ClsEntidadAlumnoPago alumnoPago = new ClsEntidadAlumnoPago();
                alumnoPago.setApellidos_Nombres(rsAxu.getString("apellidos_nombres"));
                alumnoPago.setId_Matricula(rsAxu.getString("id_matricula"));
                arrayAlumno.add(alumnoPago);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrmMpsRequerimientoAula.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        for(ClsEntidadAlumnoPago eap : arrayAlumno)
        {   
            limite ++;
            arrayAlumnoNombre.add(eap.getApellidos_Nombres());
            arrayMatriculaId.add(eap.getId_Matricula());
        }
        ClsConexion.closeConnection(conexion);
        
        double size[][] = new double[2][limite+7];
        //registramos las columnas
        size[0][0]=10; //espacio blanco
        size[0][1]=20; //numero orden
        size[0][2]=10; //espacio blanco
        size[0][3]=300;//nombre alumno
        size[0][4]=30; //espacio blanco
        size[0][5]=50; //SI
        size[0][6]=15; //espacio blanco
        size[0][7]=50; //NO
        
        //Registro de filas
        for(int j=0;j<=limite;j++)
        {
            if(j==0)
            {
                //ancho primera fila es 10 (espacio blanco)
                size[1][j]= 10;    
            }
            else//las demas quedan con 20
            {
                size[1][j]= 20;    
            }            
        }
        
        pnlContenedor.setLayout( new TableLayout(size));
         
        for(int i = 1; i<=limite; i++)
        {
            JLabel lblOrden = new JLabel(String.valueOf(i));
            lblOrden.setFont(fuente2);
            final JLabel lblAlumno = new JLabel(arrayAlumnoNombre.get(i-1));
            lblAlumno.setFont(fuente3);
            final JRadioButton rbnSI = new JRadioButton("SI");
            final JRadioButton rbnNO = new JRadioButton("NO");
            
            pnlContenedor.add(lblOrden,"1,"+i+",c,c");
            pnlContenedor.add(lblAlumno,"3,"+i);
            pnlContenedor.add(rbnSI,"5,"+i+",c,c");
            pnlContenedor.add(rbnNO,"7,"+i+",c,c");
            
            ButtonGroup grupo = new ButtonGroup();
            grupo.add(rbnSI);
            grupo.add(rbnNO);
            
            final ClsControlesRequerimiento CONTROLES = new ClsControlesRequerimiento (
                                lblOrden,
                                lblAlumno,
                                rbnSI,
                                rbnNO
                            );            
            arrayListadoDeControles.add(CONTROLES);
            
            rbnSI.addMouseListener(new MouseListener() {
                Color colorAntiguo;
                @Override
                public void mouseClicked(MouseEvent e) {
                    colorAntiguo = lblAlumno.getBackground();
                }

                @Override
                public void mousePressed(MouseEvent e) {
                   
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                   
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    colorAntiguo = lblAlumno.getBackground();
                    lblAlumno.setBackground(new Color(153,255,153));
                    lblAlumno.setOpaque(true);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    lblAlumno.setBackground(colorAntiguo);
                }
            });
            
            rbnNO.addMouseListener(new MouseListener() {
                Color colorAntiguo;
                @Override
                public void mouseClicked(MouseEvent e) {
                    colorAntiguo = lblAlumno.getBackground();
                }

                @Override
                public void mousePressed(MouseEvent e) {
                   
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    colorAntiguo = lblAlumno.getBackground();
                    lblAlumno.setBackground(new Color(153,255,153));
                    lblAlumno.setOpaque(true);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    lblAlumno.setBackground(colorAntiguo);
                }
            });
            
            rbnSI.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    
                    if(rbnSI.isSelected())
                    {
                        if(llenado)
                        {
                            int orden = arrayListadoDeControles.indexOf(CONTROLES);
                            EditarRegistro(orden,"SI");
                            lblAlumno.setBackground(new Color(153,255,153));
                            lblAlumno.setOpaque(true);
                        }
                        else
                        {
                            lblAlumno.setBackground(new Color(153,255,153));
                            lblAlumno.setOpaque(true);
                        }
                    }
                    
                }
            }); 
            
            rbnNO.addItemListener(new ItemListener() {
                
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if(rbnSI.isSelected())
                    {
                        if(llenado)
                        {
                            int orden = arrayListadoDeControles.indexOf(CONTROLES);
                            EditarRegistro(orden,"NO");
                            lblAlumno.setBackground(new Color(255,255,153));
                            lblAlumno.setOpaque(true);
                        }
                        else
                        {
                            lblAlumno.setBackground(new Color(255,255,153));
                            lblAlumno.setOpaque(true);
                        }
                    }
                }
                
            });
            
            
        }
        
        
        
        //relleno
        ArrayList<String> arrayRequerimiento = new ArrayList();
        ArrayList<ClsEntidadPagoAulaDeuda> arrayPagoAulaDeuda;
        ClsPagoAulaDeuda pagoAulaDeuda = new ClsPagoAulaDeuda();
        int cont = 0;
        
        conexion = ClsConexion.getConection();
        arrayPagoAulaDeuda =  pagoAulaDeuda.ListarRequerimientoAula(idAula, idItem, conexion);
        ClsConexion.closeConnection(conexion);
        
        for(ClsEntidadPagoAulaDeuda pad : arrayPagoAulaDeuda)
        {
            arrayRequerimiento.add(pad.getPgo_aula_deuda_estado());
            arrayAulaDeudaId.add(pad.getPgo_aula_deuda_id());
        }
        
        try
        {
            for(ClsControlesRequerimiento control : arrayListadoDeControles)
            {
                switch(arrayRequerimiento.get(cont))
                {
                    case "SI":
                        control.rbnSI.setSelected(true);
                        break;
                        
                    case "NO":
                        control.rbnNO.setSelected(true);
                        break;
                }
                cont++;
            }
            llenado=true;
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnlAsistencia = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlContenedor = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnSalir = new javax.swing.JButton();
        txtRequerimiento = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtAula = new javax.swing.JTextField();

        setTitle("Requerimientos del aula");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Droid Sans", 1, 12)); // NOI18N
        jLabel1.setText("Aula:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, 30));

        pnlAsistencia.setBackground(new java.awt.Color(255, 255, 255));
        pnlAsistencia.setBorder(javax.swing.BorderFactory.createTitledBorder("Requerimiento"));
        pnlAsistencia.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Droid Sans", 1, 11)); // NOI18N
        jLabel3.setText("SI");
        pnlAsistencia.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, -1, 10));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pnlContenedor.setBackground(new java.awt.Color(255, 255, 255));
        pnlContenedor.setPreferredSize(new java.awt.Dimension(470, 650));

        javax.swing.GroupLayout pnlContenedorLayout = new javax.swing.GroupLayout(pnlContenedor);
        pnlContenedor.setLayout(pnlContenedorLayout);
        pnlContenedorLayout.setHorizontalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 501, Short.MAX_VALUE)
        );
        pnlContenedorLayout.setVerticalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(pnlContenedor);

        pnlAsistencia.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 520, 540));

        jLabel7.setFont(new java.awt.Font("Droid Sans", 1, 11)); // NOI18N
        jLabel7.setText("NO");
        pnlAsistencia.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, -1, 10));

        jPanel1.add(pnlAsistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 610, 600));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSalir.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIconos/ic_exit_to_app_black_24dp.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setToolTipText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel3.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 40, 100, 40));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 270, 150, 110));

        txtRequerimiento.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtRequerimiento.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtRequerimiento.setEnabled(false);
        jPanel1.add(txtRequerimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 350, 30));

        jLabel5.setFont(new java.awt.Font("Droid Sans", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("Cumplimiento:");
        jLabel5.setToolTipText("");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, -1, 30));

        txtAula.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtAula.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtAula.setEnabled(false);
        jPanel1.add(txtAula, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 260, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 852, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        
        this.dispose();
        
    }//GEN-LAST:event_btnSalirActionPerformed


    private void EditarRegistro(int orden,String estado)
    {
        
        ClsEntidadPagoAulaDeuda ePagoAulaDeuda;
        ClsPagoAulaDeuda pagoAulaDeuda;        
        
        String codigo = arrayAulaDeudaId.get(orden);                            
        
        pagoAulaDeuda = new ClsPagoAulaDeuda();
        ePagoAulaDeuda = new ClsEntidadPagoAulaDeuda ( 
                    codigo,
                    estado,
                    "",
                    ""
                );
        
        conexion = ClsConexion.getConection();
        pagoAulaDeuda.EditarPagoAulaDeuda(ePagoAulaDeuda, conexion);
        ClsConexion.closeConnection(conexion);
        System.out.println("Modificado: "+codigo);
        
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlAsistencia;
    private javax.swing.JPanel pnlContenedor;
    private javax.swing.JTextField txtAula;
    private javax.swing.JTextField txtRequerimiento;
    // End of variables declaration//GEN-END:variables
}
