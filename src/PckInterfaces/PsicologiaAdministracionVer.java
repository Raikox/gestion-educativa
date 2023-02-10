/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PckInterfaces;

import PckConexion.ClsConexion;
import java.awt.Window;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import prymatricula.ClsMisc;

/**
 *
 * @author Kevin
 */
public class PsicologiaAdministracionVer extends javax.swing.JDialog {

    int _casoId;
    int _idMatricula;
    
    
    DocumentFilter filtroMayuscula = new ClsMisc.UppercaseDocumentFilter(); 
    
    /**
     * Creates new form AlmacenAdministracionNuevo
     * @param parentWindow               
     * @param psc_id               
     * @param id_matricula
     
     */
    public PsicologiaAdministracionVer(Window parentWindow, int psc_id,int id_matricula) {
        super(parentWindow);
        this._casoId = psc_id;
        this._idMatricula = id_matricula;
        initComponents();
        
        this.setLocationRelativeTo(parentWindow);        

        
        CargarDatos();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlbot = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAcciones = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        txtAula = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtAlumno = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtDocente = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtCircunstancias = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtFrecuencia = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtProblema = new javax.swing.JTextArea();
        jdcFecha = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(560, 616));
        setPreferredSize(new java.awt.Dimension(560, 616));

        pnlDerecha.setBackground(new java.awt.Color(255, 255, 255));
        pnlDerecha.setMinimumSize(new java.awt.Dimension(586, 414));
        pnlDerecha.setPreferredSize(new java.awt.Dimension(586, 414));
        pnlDerecha.setLayout(new java.awt.BorderLayout());

        pnltop.setBackground(new java.awt.Color(255, 255, 255));
        pnltop.setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel3.setText("FICHA DE DERIVACION PSICOLOGIA");
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

        jScrollPane1.setBorder(null);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(620, 406));

        pnlbot.setBackground(new java.awt.Color(255, 255, 255));
        pnlbot.setLayout(new java.awt.GridBagLayout());

        jLabel4.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel4.setText("En qué circunstancias.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 20);
        pnlbot.add(jLabel4, gridBagConstraints);

        jScrollPane2.setMinimumSize(new java.awt.Dimension(250, 110));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(250, 110));

        txtAcciones.setColumns(20);
        txtAcciones.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtAcciones.setLineWrap(true);
        txtAcciones.setRows(5);
        txtAcciones.setWrapStyleWord(true);
        txtAcciones.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtAcciones.setEnabled(false);
        txtAcciones.setPreferredSize(new java.awt.Dimension(264, 95));
        jScrollPane2.setViewportView(txtAcciones);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 20, 20);
        pnlbot.add(jScrollPane2, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel7.setText("Aula.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        pnlbot.add(jLabel7, gridBagConstraints);

        txtAula.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtAula.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtAula.setEnabled(false);
        txtAula.setMinimumSize(new java.awt.Dimension(200, 20));
        txtAula.setPreferredSize(new java.awt.Dimension(250, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 20);
        pnlbot.add(txtAula, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel8.setText("Fecha.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        pnlbot.add(jLabel8, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel9.setText("Alumno.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        pnlbot.add(jLabel9, gridBagConstraints);

        txtAlumno.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtAlumno.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtAlumno.setEnabled(false);
        txtAlumno.setMinimumSize(new java.awt.Dimension(200, 20));
        txtAlumno.setPreferredSize(new java.awt.Dimension(250, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 20);
        pnlbot.add(txtAlumno, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel10.setText("Docente.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        pnlbot.add(jLabel10, gridBagConstraints);

        txtDocente.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtDocente.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDocente.setEnabled(false);
        txtDocente.setMinimumSize(new java.awt.Dimension(200, 20));
        txtDocente.setPreferredSize(new java.awt.Dimension(250, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 20);
        pnlbot.add(txtDocente, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel11.setText("Acciones tomadas por el docente.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 20);
        pnlbot.add(jLabel11, gridBagConstraints);

        jScrollPane3.setMinimumSize(new java.awt.Dimension(283, 80));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(283, 80));

        txtCircunstancias.setColumns(20);
        txtCircunstancias.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtCircunstancias.setLineWrap(true);
        txtCircunstancias.setRows(5);
        txtCircunstancias.setWrapStyleWord(true);
        txtCircunstancias.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCircunstancias.setEnabled(false);
        txtCircunstancias.setPreferredSize(new java.awt.Dimension(264, 95));
        jScrollPane3.setViewportView(txtCircunstancias);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 20);
        pnlbot.add(jScrollPane3, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel12.setText("Con qué frecuencia se da la conducta.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 20);
        pnlbot.add(jLabel12, gridBagConstraints);

        jScrollPane4.setMinimumSize(new java.awt.Dimension(283, 80));
        jScrollPane4.setPreferredSize(new java.awt.Dimension(283, 80));

        txtFrecuencia.setColumns(20);
        txtFrecuencia.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtFrecuencia.setLineWrap(true);
        txtFrecuencia.setRows(5);
        txtFrecuencia.setWrapStyleWord(true);
        txtFrecuencia.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtFrecuencia.setEnabled(false);
        jScrollPane4.setViewportView(txtFrecuencia);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 20);
        pnlbot.add(jScrollPane4, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel13.setText("Problema que presenta el menor. Motivo (describir brevemente).");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 20);
        pnlbot.add(jLabel13, gridBagConstraints);

        jScrollPane5.setMinimumSize(new java.awt.Dimension(250, 110));
        jScrollPane5.setPreferredSize(new java.awt.Dimension(250, 110));

        txtProblema.setColumns(20);
        txtProblema.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtProblema.setLineWrap(true);
        txtProblema.setRows(5);
        txtProblema.setWrapStyleWord(true);
        txtProblema.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtProblema.setEnabled(false);
        txtProblema.setPreferredSize(new java.awt.Dimension(264, 95));
        jScrollPane5.setViewportView(txtProblema);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 20);
        pnlbot.add(jScrollPane5, gridBagConstraints);

        jdcFecha.setEnabled(false);
        jdcFecha.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        jdcFecha.setMinimumSize(new java.awt.Dimension(150, 23));
        jdcFecha.setPreferredSize(new java.awt.Dimension(150, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        pnlbot.add(jdcFecha, gridBagConstraints);

        jScrollPane1.setViewportView(pnlbot);

        pnlDerecha.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlDerecha, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ValidarCampos()
    {        
        ((AbstractDocument) txtAula.getDocument()).setDocumentFilter(filtroMayuscula);
        ((AbstractDocument) txtAcciones.getDocument()).setDocumentFilter(filtroMayuscula);
        ((AbstractDocument) txtProblema.getDocument()).setDocumentFilter(filtroMayuscula);
        ((AbstractDocument) txtFrecuencia.getDocument()).setDocumentFilter(filtroMayuscula);
        ((AbstractDocument) txtCircunstancias.getDocument()).setDocumentFilter(filtroMayuscula);
    }
    
    
    private void CargarDatos() //COMPLETAR
    {
        Connection con = ClsConexion.getConection();
        PreparedStatement st;
        ResultSet rsResultado;
        String sqlQuery = "select pc.psc_problema,pc.psc_frecuencia,pc.psc_circunstancia,pc.psc_acciones, DATE_FORMAT(pc.psc_fecha, '%d/%m/%Y') as 'psc_fecha', \n" +
                        "concat(a.apellidop_alumno,' ',a.apellidom_alumno,', ',a.nombre_alumno) as 'alumno', s.nombre_seccion,\n" +
                        "concat(p.personal_apellido_paterno,' ',p.personal_apellido_materno,', ',p.personal_nombre) as 'docente'\n" +
                        "from mps_psicologia_caso as pc inner join mat_matricula as m on pc.id_matricula = m.id_matricula\n" +
                        "inner join mat_alumno as a on m.MPS_Alumno_id_alumno = a.id_alumno\n" +
                        "inner join mat_seccion as s on m.seccion_id = s.id_seccion\n" +
                        "inner join adm_personal as p on s.MPS_Profesor_id_profesor = p.personal_id\n" +
                        "where psc_id = ?";
        
        try 
        {
            st = con.prepareStatement(sqlQuery);
            st.setInt(1, _casoId);
            rsResultado = st.executeQuery();
            while(rsResultado.next())
            {
                txtProblema.setText(rsResultado.getString("psc_problema"));
                txtFrecuencia.setText(rsResultado.getString("psc_frecuencia"));
                txtCircunstancias.setText(rsResultado.getString("psc_circunstancia"));
                txtAcciones.setText(rsResultado.getString("psc_acciones"));
                txtAlumno.setText(rsResultado.getString("alumno"));
                txtAula.setText(rsResultado.getString("nombre_seccion"));
                txtDocente.setText(rsResultado.getString("docente"));
                jdcFecha.setDate(ClsMisc.formatoFechaDate(rsResultado.getString("psc_fecha")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PsicologiaAdministracionVer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ClsConexion.closeConnection(con);
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator2;
    private com.toedter.calendar.JDateChooser jdcFecha;
    private javax.swing.JPanel pnlDerecha;
    private javax.swing.JPanel pnlbot;
    private javax.swing.JPanel pnltop;
    private javax.swing.JTextArea txtAcciones;
    private javax.swing.JTextField txtAlumno;
    private javax.swing.JTextField txtAula;
    private javax.swing.JTextArea txtCircunstancias;
    private javax.swing.JTextField txtDocente;
    private javax.swing.JTextArea txtFrecuencia;
    private javax.swing.JTextArea txtProblema;
    // End of variables declaration//GEN-END:variables
}