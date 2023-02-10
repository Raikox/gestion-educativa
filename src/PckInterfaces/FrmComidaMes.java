/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PckInterfaces;

import PckEntidad.ClsEntidadDeudasComida;


import PckNegocio.ClsDeudasComida;
import PckNegocio.ClsPeriodo;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import mpsModel.MatriculaModelo;

/**
 *
 * @author KevinMontesDeOcaVizcarra
 */
public class FrmComidaMes extends javax.swing.JInternalFrame {

DecimalFormat df = new DecimalFormat("0.00");
ArrayList<Object> arrayIdPeriodo = new ArrayList();
MatriculaModelo matriculaModelo = new MatriculaModelo();
int aux= 0;
    /**
     * Creates new form FrmComidaMes
     */
    public FrmComidaMes() {
        initComponents();
        arrayIdPeriodo = matriculaModelo.MostrarComboPeriodos(cmbPeriodo);
        cargaAlimentacion();
        aux=1;
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
        btnCambiar = new javax.swing.JButton();
        cmbPeriodo = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jmcMes = new com.toedter.calendar.JMonthChooser();
        jLabel2 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        checkAdelante = new javax.swing.JCheckBox();

        setTitle("Ajustar monto alimentación por mes");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCambiar.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnCambiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIconos/accept.png"))); // NOI18N
        btnCambiar.setText("Cambiar");
        btnCambiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCambiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 110, 40));

        cmbPeriodo.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbPeriodo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbPeriodo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPeriodoItemStateChanged(evt);
            }
        });
        jPanel1.add(cmbPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 130, -1));

        jLabel1.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel1.setText("Selecciones Periodo:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, -1, 20));

        jmcMes.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jPanel1.add(jmcMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, 110, 20));

        jLabel2.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel2.setText("Seleccione Mes:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, -1, 20));

        txtMonto.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jPanel1.add(txtMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, 100, -1));

        jLabel3.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel3.setText("Ingrese nuevo monto:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 120, 20));

        jButton1.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIconos/delete.png"))); // NOI18N
        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, 110, 40));

        checkAdelante.setBackground(new java.awt.Color(255, 255, 255));
        checkAdelante.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        checkAdelante.setLabel("Mes hacia adelante");
        jPanel1.add(checkAdelante, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCambiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarActionPerformed
        String idPeriodo="";
        int mes=0;
        Double montoComidaNuevo=0.00;
        Double montoComidaActual=0.00;
        Double totalcomida=0.00;
        Double pendientecomida=0.00;
        Double diferenciaComida=0.00;
        String idcomida ="";
        idPeriodo = String.valueOf(arrayIdPeriodo.get(cmbPeriodo.getSelectedIndex()));
        System.out.println("Periodo: "+idPeriodo);
        mes = jmcMes.getMonth()+1;
        System.out.println(mes);
        montoComidaNuevo = Double.parseDouble(txtMonto.getText());
        System.out.println(montoComidaNuevo);
        
        ClsDeudasComida DeudaComida = new ClsDeudasComida();
        
        
        if(checkAdelante.isSelected())//MES HACIA ADELANTE
        {
            int limite = 12-mes;
            int i;
            for(i=0;i<limite;i++)
            {
                
            
                //obtendremos un array de deudas que seran modificadas (dependiendo el mes y periodo)
                ArrayList<ClsEntidadDeudasComida> comida = DeudaComida.ListarDeudasComida(obtenerNombreMes(mes),idPeriodo);
                Iterator it = comida.iterator();
                String fila[] = new String[10];

                while(it.hasNext())
                {
                    ClsEntidadDeudasComida edc = new ClsEntidadDeudasComida();
                    edc = (ClsEntidadDeudasComida) it.next();
                    //id_comida,mes_comida,monto_comida,total_comida,pendiente_comida,id_periodo,periodo_alu            
                    fila[0] = edc.getId_Comida();
                    fila[1] = edc.getMes_Comida();
                    fila[2] = edc.getMonto_Comida();
                    fila[3] = edc.getTotal_Comida();
                    fila[4] = edc.getPendiente_Comida();
                    fila[5] = edc.getId_Periodo();
                    fila[6] = edc.getPeriodo_Alumno();

                    montoComidaActual = Double.parseDouble(fila[2]);
                    totalcomida = Double.parseDouble(fila[3]);
                    idcomida = fila[0];

                    if(montoComidaActual<montoComidaNuevo)
                    {
                        diferenciaComida = montoComidaNuevo-montoComidaActual;

                        pendientecomida = Double.parseDouble(df.format(montoComidaActual+diferenciaComida).replaceAll(",", "."));

                        //UPDATE
                        DeudaComida.ModificarComidaPendiente(pendientecomida, idcomida);
                        System.out.println("nuevo: "+pendientecomida);
                    }
                    else if(montoComidaNuevo < montoComidaActual) { //50 < 70
                    
                    diferenciaComida = montoComidaActual - montoComidaNuevo;
                    pendientecomida = Double.parseDouble(df.format(montoComidaActual-diferenciaComida).replaceAll(",", "."));
                    System.out.println("nuevo: "+pendientecomida +" resta: "+diferenciaComida);
                    //UPDATE
                    DeudaComida.ModificarComidaPendiente(pendientecomida, idcomida);
                }

                }
                
                mes++;
            }
            JOptionPane.showMessageDialog(null, "Cambios Realizados con éxito.", "¡En buena hora!",
                        JOptionPane.INFORMATION_MESSAGE, new ImageIcon("/PckImages/happy.png"));
            mes=0;
        }
        else
        {
            //obtendremos un array de deudas que seran modificadas (dependiendo el mes y periodo)
            ArrayList<ClsEntidadDeudasComida> comida = DeudaComida.ListarDeudasComida(obtenerNombreMes(mes),idPeriodo);
            Iterator it = comida.iterator();
            String fila[] = new String[10];

            while(it.hasNext())
            {
                ClsEntidadDeudasComida edc = new ClsEntidadDeudasComida();
                edc = (ClsEntidadDeudasComida) it.next();
                //id_comida,mes_comida,monto_comida,total_comida,pendiente_comida,id_periodo,periodo_alu            
                fila[0] = edc.getId_Comida();
                fila[1] = edc.getMes_Comida();
                fila[2] = edc.getMonto_Comida();
                fila[3] = edc.getTotal_Comida();
                fila[4] = edc.getPendiente_Comida();
                fila[5] = edc.getId_Periodo();
                fila[6] = edc.getPeriodo_Alumno();

                montoComidaActual = Double.parseDouble(fila[2]);
                totalcomida = Double.parseDouble(fila[3]);
                idcomida = fila[0];

                if(montoComidaActual<montoComidaNuevo) //70 < 80
                {
                    diferenciaComida = montoComidaNuevo-montoComidaActual;

                    pendientecomida = Double.parseDouble(df.format(montoComidaActual+diferenciaComida).replaceAll(",", "."));
                    System.out.println("nuevo: "+pendientecomida +" suma: "+diferenciaComida);
                    //UPDATE
                    DeudaComida.ModificarComidaPendiente(pendientecomida, idcomida);
                }
                else if(montoComidaNuevo < montoComidaActual) { //50 < 70
                    
                    diferenciaComida = montoComidaActual - montoComidaNuevo;
                    pendientecomida = Double.parseDouble(df.format(montoComidaActual-diferenciaComida).replaceAll(",", "."));
                    System.out.println("nuevo: "+pendientecomida +" resta: "+diferenciaComida);
                    //UPDATE
                    DeudaComida.ModificarComidaPendiente(pendientecomida, idcomida);
                }

            }
            JOptionPane.showMessageDialog(null, "Cambios Realizados con éxito.", "¡En buena hora!",
                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon("/PckImages/happy.png"));

        mes=0;
            
        }
        
        
        
    }//GEN-LAST:event_btnCambiarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmbPeriodoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPeriodoItemStateChanged
      if (aux ==1)
      {
        cargaAlimentacion();  
      }
        
    }//GEN-LAST:event_cmbPeriodoItemStateChanged
   

     static String obtenerNombreMes(int mes)
    {
          switch(mes)
          {          
          case 1:  // Febrero
          return "ENERO";             
          case 2:
          return "FEBRERO";
          case 3:
          return "MARZO";
          case 4:
          return "ABRIL";
          case 5:
          return "MAYO";
          case 6:
          return "JUNIO";
          case 7:
          return "JULIO";
          case 8:
          return "AGOSTO";
          case 9:
          return "SEPTIEMBRE";
          case 10:
          return "OCTUBRE";
          case 11:
          return "NOVIEMBRE";
          case 12:
          return "DICIEMBRE";
          default:
          throw new java.lang.IllegalArgumentException(
                          "El mes debe estar entre 1 y 12");
          }      
    
    }
    
     void cargaAlimentacion()
     {
         ClsPeriodo per = new ClsPeriodo();
         String idPeriodo = String.valueOf(arrayIdPeriodo.get(cmbPeriodo.getSelectedIndex()));
        try {
            ResultSet rs = per.SeleccionarPeriodo(idPeriodo);
            
            while (rs.next())
            {              
                txtMonto.setText(rs.getString("alimentacion_periodo"));
            }
            
        } catch (Exception ex) {
            Logger.getLogger(FrmPeriodo.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCambiar;
    private javax.swing.JCheckBox checkAdelante;
    private javax.swing.JComboBox cmbPeriodo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private com.toedter.calendar.JMonthChooser jmcMes;
    private javax.swing.JTextField txtMonto;
    // End of variables declaration//GEN-END:variables
}
