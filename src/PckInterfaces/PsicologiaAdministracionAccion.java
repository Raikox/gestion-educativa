/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PckInterfaces;

import PckConexion.ClsConexion;
import PckEntidad.ClsEntidadAnecdotario;
import PckEntidad.ClsEntidadAnecdotarioComentario;
import PckNegocio.ClsAnecdotario;
import PckNegocio.ClsAnecdotarioComentario;
import java.awt.Window;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import prymatricula.ClsMisc;
import static prymatricula.ClsMisc.fechaActual;

/**
 *
 * @author Kevin
 */
public class PsicologiaAdministracionAccion extends javax.swing.JDialog {
    
    
    public PsicologiaAdministracion psicologiaAdministracion;
    DocumentFilter filtroMayuscula = new ClsMisc.UppercaseDocumentFilter(); 
    String _IdCaso;    
    
    /**
     * Creates new form AlmacenAdministracionNuevo
     * @param parentWindow     
     * @param idCaso     
     */
    public PsicologiaAdministracionAccion(Window parentWindow, String idCaso) {
        super(parentWindow);
        _IdCaso = idCaso;
        initComponents();
        
        this.setLocationRelativeTo(parentWindow);
        
        txtFecha.setText(fechaActual());
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

        pnlDerecha = new javax.swing.JPanel();
        pnltop = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        pnlbot = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAccion = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(436, 414));

        pnlDerecha.setBackground(new java.awt.Color(255, 255, 255));
        pnlDerecha.setMinimumSize(new java.awt.Dimension(586, 414));
        pnlDerecha.setPreferredSize(new java.awt.Dimension(586, 414));
        pnlDerecha.setLayout(new java.awt.BorderLayout());

        pnltop.setBackground(new java.awt.Color(255, 255, 255));
        pnltop.setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel3.setText("NUEVA ACCION");
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

        jLabel4.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel4.setText("Acción.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        pnlbot.add(jLabel4, gridBagConstraints);

        jScrollPane2.setMinimumSize(new java.awt.Dimension(200, 100));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(250, 150));

        txtAccion.setColumns(20);
        txtAccion.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtAccion.setLineWrap(true);
        txtAccion.setRows(5);
        txtAccion.setWrapStyleWord(true);
        txtAccion.setPreferredSize(new java.awt.Dimension(264, 95));
        jScrollPane2.setViewportView(txtAccion);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 20, 20);
        pnlbot.add(jScrollPane2, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel7.setText("Fecha.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        pnlbot.add(jLabel7, gridBagConstraints);

        txtFecha.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtFecha.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtFecha.setEnabled(false);
        txtFecha.setMinimumSize(new java.awt.Dimension(200, 20));
        txtFecha.setPreferredSize(new java.awt.Dimension(250, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 20);
        pnlbot.add(txtFecha, gridBagConstraints);

        jSeparator3.setForeground(new java.awt.Color(224, 224, 224));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlbot.add(jSeparator3, gridBagConstraints);

        btnGuardar.setFont(new java.awt.Font("Droid Sans", 1, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIconos/ic_save_black_24dp.png"))); // NOI18N
        btnGuardar.setText("GUARDAR ACCION");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 20);
        pnlbot.add(btnGuardar, gridBagConstraints);

        pnlDerecha.add(pnlbot, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlDerecha, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ValidarCampos()
    {        
        ((AbstractDocument) txtFecha.getDocument()).setDocumentFilter(filtroMayuscula);
        ((AbstractDocument) txtAccion.getDocument()).setDocumentFilter(filtroMayuscula);
    }
    
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        
        Connection con = ClsConexion.getConection();
        PreparedStatement st;
        String sqlQuery= "insert into mps_psicologia_accion (psa_accion,psa_fecha,psc_id,personal_id) values"
                + "(?,(STR_TO_DATE(REPLACE(?,'/','.') ,GET_FORMAT(date,'EUR'))),?,?)";
                
        try 
        {
            st = con.prepareStatement(sqlQuery);
            st.setString(1, txtAccion.getText());
            st.setString(2, fechaActual());
            st.setString(3, _IdCaso);
            st.setString(4, FrmPrincipal.idPersonalLogeado);
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(PsicologiaAdministracionAccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ClsConexion.closeConnection(con);
        
        psicologiaAdministracion.CargarTablaPsicologiaAccion(_IdCaso);
        this.dispose();
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    public void FormularioPadre(PsicologiaAdministracion psicologiaAdministracion)
    {
        this.psicologiaAdministracion = psicologiaAdministracion;
    }
    
    
    
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(AlmacenAdministracionNuevo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(AlmacenAdministracionNuevo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(AlmacenAdministracionNuevo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(AlmacenAdministracionNuevo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPanel pnlDerecha;
    private javax.swing.JPanel pnlbot;
    private javax.swing.JPanel pnltop;
    private javax.swing.JTextArea txtAccion;
    private javax.swing.JTextField txtFecha;
    // End of variables declaration//GEN-END:variables
}
