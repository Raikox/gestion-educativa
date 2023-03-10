/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PckInterfaces;

import PckConexion.ClsConexion;
import PckEntidad.ClsEntidadAnecdotario;
import PckNegocio.ClsAnecdotario;
import java.awt.Window;
import java.sql.Connection;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import prymatricula.ClsMisc;
import static prymatricula.ClsMisc.fechaActual;

/**
 *
 * @author Kevin
 */
public class AnecdotarioDocenteNuevo extends javax.swing.JDialog {
    
    public AnecdotarioDocente anecdotarioDocente;
    private final String _IdAlumno;
    private final String _NombreAlumno;
    DocumentFilter filtroMayuscula = new ClsMisc.UppercaseDocumentFilter(); 
   
    /**
     * Creates new form AlmacenAdministracionNuevo
     * @param parentWindow     
     * @param IdAlumno     
     * @param NombreAlumno     
     */
    public AnecdotarioDocenteNuevo(Window parentWindow, String IdAlumno, String NombreAlumno) {
        super(parentWindow);        
        _IdAlumno = IdAlumno;
        _NombreAlumno = NombreAlumno;
        
        initComponents();
        ValidarCampos();
        txtAlumno.setText(_NombreAlumno);
        
        this.setLocationRelativeTo(parentWindow);
        
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
        txtComentario = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        btnGuardar = new javax.swing.JButton();
        txtNombre1 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAnecdota = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtAlumno = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(640, 528));
        setPreferredSize(new java.awt.Dimension(640, 528));

        pnlDerecha.setBackground(new java.awt.Color(255, 255, 255));
        pnlDerecha.setMinimumSize(new java.awt.Dimension(586, 414));
        pnlDerecha.setPreferredSize(new java.awt.Dimension(586, 414));
        pnlDerecha.setLayout(new java.awt.BorderLayout());

        pnltop.setBackground(new java.awt.Color(255, 255, 255));
        pnltop.setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel3.setText("NUEVA AN??CDOTA");
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
        pnlbot.setMinimumSize(new java.awt.Dimension(640, 528));
        pnlbot.setPreferredSize(new java.awt.Dimension(640, 528));
        pnlbot.setLayout(new java.awt.GridBagLayout());

        jLabel4.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel4.setText("Comentario o soluci??n.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        pnlbot.add(jLabel4, gridBagConstraints);

        jScrollPane2.setMinimumSize(new java.awt.Dimension(200, 100));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(250, 96));

        txtComentario.setColumns(20);
        txtComentario.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtComentario.setLineWrap(true);
        txtComentario.setRows(5);
        txtComentario.setWrapStyleWord(true);
        txtComentario.setPreferredSize(new java.awt.Dimension(264, 95));
        jScrollPane2.setViewportView(txtComentario);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 20, 20);
        pnlbot.add(jScrollPane2, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel7.setText("Estado.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        pnlbot.add(jLabel7, gridBagConstraints);

        jSeparator3.setForeground(new java.awt.Color(224, 224, 224));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlbot.add(jSeparator3, gridBagConstraints);

        btnGuardar.setFont(new java.awt.Font("Droid Sans", 1, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIconos/ic_save_black_24dp.png"))); // NOI18N
        btnGuardar.setText("GUARDAR REGISTRO");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 20);
        pnlbot.add(btnGuardar, gridBagConstraints);

        txtNombre1.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtNombre1.setText("PENDIENTE");
        txtNombre1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtNombre1.setEnabled(false);
        txtNombre1.setMinimumSize(new java.awt.Dimension(200, 20));
        txtNombre1.setPreferredSize(new java.awt.Dimension(250, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 20);
        pnlbot.add(txtNombre1, gridBagConstraints);

        jScrollPane3.setMinimumSize(new java.awt.Dimension(200, 100));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(250, 150));

        txtAnecdota.setColumns(20);
        txtAnecdota.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtAnecdota.setLineWrap(true);
        txtAnecdota.setRows(5);
        txtAnecdota.setWrapStyleWord(true);
        txtAnecdota.setPreferredSize(new java.awt.Dimension(264, 95));
        jScrollPane3.setViewportView(txtAnecdota);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 20, 20);
        pnlbot.add(jScrollPane3, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel8.setText("An??cdota.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        pnlbot.add(jLabel8, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel9.setText("Alumno");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        pnlbot.add(jLabel9, gridBagConstraints);

        txtAlumno.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtAlumno.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtAlumno.setEnabled(false);
        txtAlumno.setMinimumSize(new java.awt.Dimension(200, 20));
        txtAlumno.setPreferredSize(new java.awt.Dimension(250, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 20);
        pnlbot.add(txtAlumno, gridBagConstraints);

        pnlDerecha.add(pnlbot, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlDerecha, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ValidarCampos()
    {        
        ((AbstractDocument) txtAnecdota.getDocument()).setDocumentFilter(filtroMayuscula);
        ((AbstractDocument) txtComentario.getDocument()).setDocumentFilter(filtroMayuscula);
    }
    
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        
        ClsAnecdotario anecdotario = new ClsAnecdotario();
        ClsEntidadAnecdotario eanecdotario = new ClsEntidadAnecdotario();
        Connection con;
        
        eanecdotario.setAnecdotario_fecha(fechaActual()); 
        eanecdotario.setAnecdotario_anecdota(txtAnecdota.getText());
        eanecdotario.setAnecdotario_solucion(txtComentario.getText());
        eanecdotario.setAnecdotario_estado("PENDIENTE");
        eanecdotario.setId_Alumno(_IdAlumno);
        
        con = ClsConexion.getConection();
        anecdotario.GuardarAnecdotario(eanecdotario, con);
        ClsConexion.closeConnection(con);

        anecdotarioDocente.CargarTablaAnecdotarioAlumno(_IdAlumno);
        anecdotarioDocente.LimpiarCampos();
        this.dispose();                
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    public void FormularioPadre(AnecdotarioDocente anecdotarioDocente)
    {
        this.anecdotarioDocente = anecdotarioDocente;
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPanel pnlDerecha;
    private javax.swing.JPanel pnlbot;
    private javax.swing.JPanel pnltop;
    private javax.swing.JTextField txtAlumno;
    private javax.swing.JTextArea txtAnecdota;
    private javax.swing.JTextArea txtComentario;
    private javax.swing.JTextField txtNombre1;
    // End of variables declaration//GEN-END:variables
}
