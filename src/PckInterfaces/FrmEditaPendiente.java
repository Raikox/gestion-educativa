 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckInterfaces;

import PckConexion.ClsConexion;

import PckNegocio.ClsDeudaProd;
import PckNegocio.ClsDeudaServ;
import PckNegocio.ClsHistorial;
import PckNegocio.ClsMatMen;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author kmv
 */
public class FrmEditaPendiente extends javax.swing.JInternalFrame {
DecimalFormat df = new DecimalFormat("0.00"); 
Double matricula=0.0;
Double mensualidad=0.0;
Double total=0.0;
Double pendiente=0.0;
//variables del otro formulario
static Double mat=0.0;
static Double men=0.0;
static Double pen= 0.0;
Connection con = null;

static String tipo = "";

static String codigoDeuda = "";
public FrmPagoTodo PObjTodo;
    /**
     * Creates new form FrmAccesoPago
     */
Date date = new Date();
        String format = "dd/MM/yyyy";
        SimpleDateFormat formato = new SimpleDateFormat(format);
    public FrmEditaPendiente() {
        initComponents();       
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
        lbl = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setTitle("Modificar pendiente");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        lbl.setText("Monto nuevo");
        jPanel1.add(lbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, -1, 20));

        txtMonto.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jPanel1.add(txtMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 70, 20));

        btnAceptar.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, -1, -1));

        btnCancelar.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
      
    public void editarTodo(FrmPagoTodo obj) {
            this.PObjTodo = obj;
    }
    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
     String k=txtMonto.getText();
    
     //declaracion de clases       
       ClsMatMen mm = new ClsMatMen();          
       ClsDeudaServ ds = new ClsDeudaServ();
       ClsDeudaProd dp = new ClsDeudaProd();
           
    switch (tipo) {
        case "matricula":
            con = ClsConexion.getConection();
            mm.ModificarPendienteMatricula(codigoDeuda, Double.parseDouble(k),con);
            ClsConexion.closeConnection(con); 
            PObjTodo.actualiza();
            
            this.dispose();
            break;
        case "mensualidad":
            con = ClsConexion.getConection();
            mm.ModificarPendienteMensualidad(codigoDeuda, Double.parseDouble(k),con);   
            ClsConexion.closeConnection(con); 
            PObjTodo.actualiza();
            
            this.dispose();
            break;
        case "comida":
            con = ClsConexion.getConection();
            mm.ModificarPendienteComida(codigoDeuda, Double.parseDouble(k),con);   
            ClsConexion.closeConnection(con); 
            PObjTodo.actualiza();
            
            this.dispose();
            break;
        case "servicio":
            con = ClsConexion.getConection();
            ds.ModificarPendienteServ(codigoDeuda, k,con);            
            ClsConexion.closeConnection(con); 
            PObjTodo.actualiza();
            
            this.dispose();
            break;
        case "producto":
            con = ClsConexion.getConection();
            dp.ModificarPendienteProducto(codigoDeuda, Double.parseDouble(k),con);    
            ClsConexion.closeConnection(con); 
            PObjTodo.actualiza();            
            this.dispose();
            break;
    }
    
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl;
    private javax.swing.JTextField txtMonto;
    // End of variables declaration//GEN-END:variables
}
