 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckInterfaces;

import PckConexion.ClsConexion;
import PckEntidad.ClsEntidadComida;
import PckEntidad.ClsEntidadDeudaProd;
import PckEntidad.ClsEntidadDeudaServ;
import PckEntidad.ClsEntidadMatMen;
import PckEntidad.ClsEntidadMen;
import PckEntidad.ClsEntidadPgoCambio;
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
public class FrmAccesoPago extends javax.swing.JInternalFrame {
DecimalFormat df = new DecimalFormat("0.00"); 
Double matricula=0.0;
Double mensualidad=0.0;
Double total=0.0;
Double pendiente=0.0;
//variables del otro formulario
static Double tot=0.0;
static Double pend= 0.0;
static String opcion="";
static String tipo = "";
static String codAlu="";
static String cod="";
static String mesD="";
static String codDeuda="";
Connection con = null;

public FrmPagoTodo PObjTodo;
    /**
     * Creates new form FrmAccesoPago
     */
        Date date = new Date();
        String format = new String("dd/MM/yyyy");
        SimpleDateFormat formato = new SimpleDateFormat(format);
        
    public FrmAccesoPago() 
    {
        initComponents();
        txtObservacion.setLineWrap(true);
        if (opcion.equals("+"))
        {
        lbl.setText("Monto a agregar:");
        }
        else
        {
        lbl.setText("Monto a quitar:");
        }        
        if(tipo.equals("matricula"))
        {
            chbAdelante.setEnabled(false); 
            chbAdelante.setVisible(false); 
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
        lbl = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservacion = new javax.swing.JTextArea();
        chbAdelante = new javax.swing.JCheckBox();

        setTitle("Modificar monto");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        lbl.setText("Monto a quitar:");
        jPanel1.add(lbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, 20));

        txtMonto.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jPanel1.add(txtMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 60, -1));

        jLabel2.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel2.setText("Observación:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, 20));

        btnAceptar.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, -1, -1));

        btnCancelar.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 230, -1, -1));

        txtObservacion.setColumns(20);
        txtObservacion.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtObservacion.setRows(5);
        jScrollPane1.setViewportView(txtObservacion);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 170, 100));

        chbAdelante.setBackground(new java.awt.Color(255, 255, 255));
        chbAdelante.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        chbAdelante.setText("Mes hacia adelante");
        jPanel1.add(chbAdelante, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
   
    public void masmenosTodo(FrmPagoTodo obj) {
            this.PObjTodo = obj;
    }
    
    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
     String k=txtMonto.getText();
     String kk= txtObservacion.getText();
           
       ClsEntidadPgoCambio epc = new ClsEntidadPgoCambio();
       ClsHistorial histo = new ClsHistorial();
           
    switch (tipo) {
        case "matricula": 
            ClsEntidadMatMen matmen = new ClsEntidadMatMen();
            ClsMatMen mm = new ClsMatMen();
            switch (opcion) {
                case "+":
                    //obtenemos el valor nuevo de matricula
                    matricula = tot+Double.parseDouble(k);
                    total = matricula;
                    pendiente = pend+Double.parseDouble(k);
                    matmen.setMatricula_Mat(matricula);
                    matmen.setTotal_Mat(total);
                    matmen.setPendiente_Mat(pendiente);
                    con = ClsConexion.getConection();
                    mm.ModificarMatricula(codAlu, matmen,con);
                    ClsConexion.closeConnection(con);
                    //AGREGAR A HISTORIAL CAMBIO
                    epc.setFecha_Cambio(formato.format(date));
                    epc.setMonto_Cambio("+"+String.valueOf(k));
                    epc.setObs_Cambio(kk);                    
                    epc.setTipo_Cambio(tipo);
                    epc.setId_Alumno(codAlu);
                    histo.AgregarCambios(epc);  
                    PObjTodo.actualiza();  
                    PObjTodo.activaBotonesOpcion(false, false, false, false);
                    this.dispose();
                    break;
                case "-":
                    //obtenemos el valor nuevo de matricula
                    matricula = tot-Double.parseDouble(k);
                    total = matricula;
                    pendiente = pend-Double.parseDouble(k);
                    matmen.setMatricula_Mat(matricula);
                    matmen.setTotal_Mat(total);
                    matmen.setPendiente_Mat(pendiente);
                    con = ClsConexion.getConection();
                    mm.ModificarMatricula(codAlu, matmen,con);
                    ClsConexion.closeConnection(con);
                    //AGREGAR A HISTORIAL CAMBIO
                    epc.setFecha_Cambio(formato.format(date));
                    epc.setMonto_Cambio("-"+String.valueOf(k));
                    epc.setObs_Cambio(kk);                    
                    epc.setTipo_Cambio(tipo);
                    epc.setId_Alumno(codAlu);
                    histo.AgregarCambios(epc);
                    PObjTodo.actualiza(); 
                    PObjTodo.activaBotonesOpcion(false, false, false, false);
                    this.dispose();
                    break;
            }
            break;
        case "mensualidad":
            ClsEntidadMen menn = new ClsEntidadMen();
            ClsMatMen mmm = new ClsMatMen();
            switch (opcion) {
                case "+":
                    
                    mensualidad = tot + Double.parseDouble(k);
                    total = mensualidad;
                    pendiente = pend+Double.parseDouble(k); 
                    //MES HACIA ADELANTE
                    
                    if(chbAdelante.isSelected())//MES HACIA ADELANTE
                    {
                        //convertir el mes en int para realizar el for
                        int mesnumero = obtenerNumeroMes(mesD);                        
                        int limite = 12-mesnumero;
                        int i;
                        
                            con = ClsConexion.getConection();
                            for(i=0;i<limite;i++)                            
                            {
                                menn.setCantidad_Mensualidad(mensualidad);
                                menn.setTotal_Mensualidad(total);
                                menn.setPendiente_Mensualidad(pendiente);                                   
                                mmm.ModificarMen(codAlu,obtenerNombreMes(mesnumero), menn,con);                                
                                mesnumero++;
                            }
                            ClsConexion.closeConnection(con);
                            
                        //AGREGAR A HISTORIAL CAMBIO
                        epc.setFecha_Cambio(formato.format(date));
                        epc.setMonto_Cambio("+"+String.valueOf(k));
                        epc.setObs_Cambio(kk);                        
                        epc.setTipo_Cambio(tipo);
                        epc.setId_Alumno(codAlu);
                        histo.AgregarCambios(epc);
                        
                        
                    }
                    else //SI NO SE MARCO EL CHECK
                    {
                    menn.setCantidad_Mensualidad(mensualidad);
                    menn.setTotal_Mensualidad(total);
                    menn.setPendiente_Mensualidad(pendiente);
                    con = ClsConexion.getConection();
                    mmm.ModificarMen(codAlu,mesD, menn,con);
                    ClsConexion.closeConnection(con);
                    //AGREGAR A HISTORIAL CAMBIO
                    epc.setFecha_Cambio(formato.format(date));
                    epc.setMonto_Cambio("+"+String.valueOf(k));
                    epc.setObs_Cambio(kk);                    
                    epc.setTipo_Cambio(tipo);
                    epc.setId_Alumno(codAlu);
                    histo.AgregarCambios(epc);
                    //MANDAMOS VARIABLES PARA ACTUALIZAR                    
                    }
                    PObjTodo.actualiza();
                    PObjTodo.activaBotonesOpcion(false, false, false, false);
                    this.dispose();
                    break;
                case "-":
                                                                            
                    mensualidad = tot - Double.parseDouble(k);
                    total = mensualidad;
                    pendiente = pend - Double.parseDouble(k);
                    
                    if(chbAdelante.isSelected())//MES HACIA ADELANTE
                    {
                        //convertir el mes en int para realizar el for
                        int mesnumero = obtenerNumeroMes(mesD);                        
                        int limite = 12-mesnumero;
                        int i;
                            con = ClsConexion.getConection();
                            for(i=0;i<limite;i++)                            
                            {
                                menn.setCantidad_Mensualidad(mensualidad);
                                menn.setTotal_Mensualidad(total);
                                menn.setPendiente_Mensualidad(pendiente);
                                mmm.ModificarMen(codAlu,obtenerNombreMes(mesnumero), menn,con);

                                mesnumero++;
                            }
                            ClsConexion.closeConnection(con);
                        //AGREGAR A HISTORIAL CAMBIO
                        epc.setFecha_Cambio(formato.format(date));
                        epc.setMonto_Cambio("-"+String.valueOf(k));
                        epc.setObs_Cambio(kk);                        
                        epc.setTipo_Cambio(tipo);
                        epc.setId_Alumno(codAlu);
                        histo.AgregarCambios(epc);   
                    }
                    else
                    {
                        menn.setCantidad_Mensualidad(mensualidad);
                        menn.setTotal_Mensualidad(total);
                        menn.setPendiente_Mensualidad(pendiente);
                        con = ClsConexion.getConection();
                        mmm.ModificarMen(codAlu,mesD, menn,con);
                        ClsConexion.closeConnection(con);
                        //AGREGAR A HISTORIAL CAMBIO
                        epc.setFecha_Cambio(formato.format(date));
                        epc.setMonto_Cambio("-"+String.valueOf(k));
                        epc.setObs_Cambio(kk);                        
                        epc.setTipo_Cambio(tipo);
                        epc.setId_Alumno(codAlu);
                        histo.AgregarCambios(epc);
                    }
                    PObjTodo.actualiza();
                    PObjTodo.activaBotonesOpcion(false, false, false, false);
                    this.dispose();
                    break;    
            }
            break;
        case "comida":
            ClsEntidadComida co = new ClsEntidadComida();
            ClsMatMen mmmm = new ClsMatMen();
            switch (opcion) {
                case "+":
                    mensualidad = tot + Double.parseDouble(k);
                    total = mensualidad;
                    pendiente = pend+Double.parseDouble(k);                    
                    if(chbAdelante.isSelected())//MES HACIA ADELANTE
                    {
                        int mesnumero = obtenerNumeroMes(mesD);                        
                        int limite = 12-mesnumero;
                        int i;         
                        con = ClsConexion.getConection();
                            for(i=0;i<limite;i++)                            
                            {
                                co.setMonto_Comida(mensualidad);
                                co.setTotal_Comida(total);
                                co.setPendiente_Comida(pendiente);
                                mmmm.ModificarPagoComida(codAlu,obtenerNombreMes(mesnumero), co,con);                             

                                mesnumero++;
                            }
                        ClsConexion.closeConnection(con);    
                        //AGREGAR A HISTORIAL CAMBIO
                        epc.setFecha_Cambio(formato.format(date));
                        epc.setMonto_Cambio("+"+String.valueOf(k));
                        epc.setObs_Cambio(kk);                        
                        epc.setTipo_Cambio(tipo);
                        epc.setId_Alumno(codAlu);
                        histo.AgregarCambios(epc);
                    }
                    else
                    {
                        co.setMonto_Comida(mensualidad);
                        co.setTotal_Comida(total);
                        co.setPendiente_Comida(pendiente);
                        con = ClsConexion.getConection();
                        mmmm.ModificarPagoComida(codAlu,mesD, co,con);
                        ClsConexion.closeConnection(con);
                        //AGREGAR A HISTORIAL CAMBIO
                        epc.setFecha_Cambio(formato.format(date));
                        epc.setMonto_Cambio("+"+String.valueOf(k));
                        epc.setObs_Cambio(kk);                        
                        epc.setTipo_Cambio(tipo);
                        epc.setId_Alumno(codAlu);
                        histo.AgregarCambios(epc);                        
                    }
                     PObjTodo.actualiza();
                     PObjTodo.activaBotonesOpcion(false, false, false, false);
                    this.dispose();
                    break;
                case "-":                    
                    mensualidad = tot - Double.parseDouble(k);
                    total = mensualidad;
                    pendiente = pend-Double.parseDouble(k);                    
                    if(chbAdelante.isSelected())//MES HACIA ADELANTE
                    {
                        int mesnumero = obtenerNumeroMes(mesD);                        
                        int limite = 12-mesnumero;
                        int i;        
                            con = ClsConexion.getConection();
                            for(i=0;i<limite;i++)                            
                            {
                                co.setMonto_Comida(mensualidad);
                                co.setTotal_Comida(total);
                                co.setPendiente_Comida(pendiente);
                                mmmm.ModificarPagoComida(codAlu,obtenerNombreMes(mesnumero), co,con);
                                mesnumero++;
                            }
                            ClsConexion.closeConnection(con);
                        //AGREGAR A HISTORIAL CAMBIO
                        epc.setFecha_Cambio(formato.format(date));
                        epc.setMonto_Cambio("-"+String.valueOf(k));
                        epc.setObs_Cambio(kk);             
                        epc.setTipo_Cambio(tipo);
                        epc.setId_Alumno(codAlu);
                        histo.AgregarCambios(epc);
                    }
                    else
                    {
                        co.setMonto_Comida(mensualidad);
                        co.setTotal_Comida(total);
                        co.setPendiente_Comida(pendiente);
                        con = ClsConexion.getConection();
                        mmmm.ModificarPagoComida(codAlu,mesD,co,con);
                        ClsConexion.closeConnection(con);
                        //AGREGAR A HISTORIAL CAMBIO
                        epc.setFecha_Cambio(formato.format(date));
                        epc.setMonto_Cambio("-"+String.valueOf(k));
                        epc.setObs_Cambio(kk);
                        epc.setTipo_Cambio(tipo);
                        epc.setId_Alumno(codAlu);
                        histo.AgregarCambios(epc); 
                    }                    
                    PObjTodo.actualiza();
                    PObjTodo.activaBotonesOpcion(false, false, false, false);
                    this.dispose();
                    break;    
            }
            break;
        case "producto":
            ClsDeudaProd dp = new ClsDeudaProd();
            ClsEntidadDeudaProd ep = new ClsEntidadDeudaProd();
            switch (opcion) {
                case "+":
                    mensualidad = tot + Double.parseDouble(k);
                    total = mensualidad;
                    pendiente = pend+Double.parseDouble(k);
                    ep.setTotal_Deudap(String.valueOf(mensualidad));
                    ep.setPendiente_Deudap(String.valueOf(pendiente));
                    con = ClsConexion.getConection();
                    dp.ModificarDeudaProd(codDeuda, ep,con);
                    ClsConexion.closeConnection(con);
                    //AGREGAR A HISTORIAL CAMBIO
                    epc.setFecha_Cambio(formato.format(date));
                    epc.setMonto_Cambio("+"+String.valueOf(k));
                    epc.setObs_Cambio(kk);                    
                    epc.setTipo_Cambio(tipo);
                    epc.setId_Alumno(codAlu);
                    histo.AgregarCambios(epc);
                    //MANDAMOS VARIABLES PARA ACTUALIZAR
                    PObjTodo.actualiza();
                    PObjTodo.activaBotonesOpcion(false, false, false, false);
                    this.dispose();
                    break;
                case "-":
                    mensualidad = tot - Double.parseDouble(k);
                    total = mensualidad;
                    pendiente = pend-Double.parseDouble(k);
                    ep.setTotal_Deudap(String.valueOf(mensualidad));
                    ep.setPendiente_Deudap(String.valueOf(pendiente));
                    con = ClsConexion.getConection();
                    dp.ModificarDeudaProd(codDeuda, ep,con);
                    ClsConexion.closeConnection(con);
                    //AGREGAR A HISTORIAL CAMBIO
                    epc.setFecha_Cambio(formato.format(date));
                    epc.setMonto_Cambio("-"+String.valueOf(k));
                    epc.setObs_Cambio(kk); 
                    epc.setTipo_Cambio(tipo);
                    epc.setId_Alumno(codAlu);
                    histo.AgregarCambios(epc);
                     PObjTodo.actualiza();
                     PObjTodo.activaBotonesOpcion(false, false, false, false);
                    this.dispose();
                    break; 
            }
            break;
        case "servicio":
            ClsEntidadDeudaServ es = new ClsEntidadDeudaServ();
            ClsDeudaServ ds = new ClsDeudaServ();
            switch (opcion) {
                case "+":                    
                    mensualidad = tot + Double.parseDouble(k);
                    total = mensualidad;
                    pendiente = pend+Double.parseDouble(k);
                    es.setTotal_Deuda_Servicio(String.valueOf(mensualidad));
                    es.setPendiente_Deuda_Servicio(String.valueOf(pendiente));
                    con = ClsConexion.getConection();
                    ds.ModificarDeudaServ(codDeuda, es,con);
                    ClsConexion.closeConnection(con);
                    
                    //AGREGAR A HISTORIAL CAMBIO
                    epc.setFecha_Cambio(formato.format(date));
                    epc.setMonto_Cambio("+"+String.valueOf(k));
                    epc.setObs_Cambio(kk);
                    epc.setTipo_Cambio(tipo);
                    epc.setId_Alumno(codAlu);
                    histo.AgregarCambios(epc);
                    PObjTodo.actualiza();
                    this.dispose();
                    break;
                case "-":
                    mensualidad = tot - Double.parseDouble(k);
                    total = mensualidad;
                    pendiente = pend-Double.parseDouble(k);
                    es.setTotal_Deuda_Servicio(String.valueOf(mensualidad));
                    es.setPendiente_Deuda_Servicio(String.valueOf(pendiente));
                    con = ClsConexion.getConection();
                    ds.ModificarDeudaServ(codDeuda, es,con);
                    ClsConexion.closeConnection(con);
                    //AGREGAR A HISTORIAL CAMBIO
                    epc.setFecha_Cambio(formato.format(date));
                    epc.setMonto_Cambio("-"+String.valueOf(k));
                    epc.setObs_Cambio(kk);    
                epc.setTipo_Cambio(tipo);
                epc.setId_Alumno(codAlu);
                histo.AgregarCambios(epc);
                PObjTodo.actualiza();  
                this.dispose();
                break; 
            }    
            break;
    }
    
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    static int obtenerNumeroMes(String mes)//obtiene el n° del mes
    {
          switch(mes)
          {          
          case "ENERO":  // Febrero
          return 1;             
          case "FEBRERO":
          return 2;
          case "MARZO":
          return 3;
          case "ABRIL":
          return 4;
          case "MAYO":
          return 5;
          case "JUNIO":
          return 6;
          case "JULIO":
          return 7;
          case "AGOSTO":
          return 8;
          case "SEPTIEMBRE":
          return 9;
          case "OCTUBRE":
          return 10;
          case "NOVIEMBRE":
          return 11;
          case "DICIEMBRE":
          return 12;
          default:
          throw new java.lang.IllegalArgumentException(
                          "El mes debe ser valido");
          }      
    
    }
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JCheckBox chbAdelante;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextArea txtObservacion;
    // End of variables declaration//GEN-END:variables
}