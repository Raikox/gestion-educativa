/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package prymatricula;


import PckConexion.ClsConexion;
import PckEntidad.ClsEntidadComida;
import PckEntidad.ClsEntidadMatMen;

import PckEntidad.ClsEntidadMen;

import PckNegocio.ClsAlumno;
import PckNegocio.ClsGenerar;
import PckNegocio.ClsMatMen;

import PckNegocio.ClsPeriodo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import static prymatricula.ClsMisc.obtenerDiasDelMes;
import static prymatricula.ClsMisc.obtenerMeses;

/**
 *
 * @author kmv
 */
public class Pagos {
    
    static Connection con = null;
     
    static double MensualidadA=0.0;
    static double MensualidadB=0.0;
    static double MensualidadC=0.0;    
    
    static ResultSet rsCod = null;
    static ResultSet rsAlumno = null;
    
    static String nivel="";
    static double comidabase = 0.00;
    static  double comida = 0.0;
    
    static String month[] = {"ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"};
    static String fec="";
    static Date date = new Date();
    static String format = new String("dd/MM/yyyy");
    static SimpleDateFormat formato = new SimpleDateFormat(format);
        
   public static void GenerarAlumnoPago(String codMatricula,String fechaComida)
   {
        DecimalFormat df = new DecimalFormat("0.00");        
        
        //variables
        String inicioClases="";
        String entrada="";
        String salida="";
        String alimentacion="";
        String codSeccion="";
        String codPeriodo="";
        double montoMatricula=0.0;
        double MensualidadBase=0.0;
        double Mensualidad=0.0;
        
        //Obtengo datos necesarios para trabajar
        ClsGenerar genera = new ClsGenerar();   
        
        try
        { 
            con = ClsConexion.getConection();
            ResultSet rsGenera = genera.SeleccionarPagoMatricula(codMatricula,con);      
            while(rsGenera.next())
            {
                inicioClases = rsGenera.getString("inicio_clases");
                entrada      = rsGenera.getString("horarioe_matricula");
                salida       = rsGenera.getString("horarios_matricula");
                alimentacion = rsGenera.getString("comida_alumno");
                codSeccion   = rsGenera.getString("seccion_id");     
                codPeriodo   = rsGenera.getString("id_periodo");
            }
           ClsConexion.closeConnection(con);
        }
        catch(Exception ex)
        {  ex.printStackTrace();  }        
    
        /*PGO_MATRICULA*/        
        //agregar datos a pgo_matricula (matricula,mensualidad,total,pendiente,id_matricula)
        ClsPeriodo pe = new ClsPeriodo();
        try 
        {
             ResultSet rsp = pe.SeleccionarPeriodo(codPeriodo);
             while (rsp.next())
             {
                String h = rsp.getString("matricula_periodo");
                //Se obtiene el monto de matricula
                montoMatricula = Double.parseDouble(h);
             }
        }catch (Exception ex) {
             Logger.getLogger(Pagos.class.getName()).log(Level.SEVERE, null, ex);
        }
        ClsMatMen m = new ClsMatMen();
        ClsEntidadMatMen mm = new ClsEntidadMatMen();        
        //estos campos se calculan de acuerdo a nivel_matricula e inicio_clases
        mm.setMatricula_Mat(montoMatricula);             
        mm.setTotal_Mat(montoMatricula);
        mm.setPendiente_Mat(montoMatricula);
        //fec es la fecha actual
        fec = formato.format(date);                       
        //obtengo el ultimo dia del mes segun el mes://mando el ultimo dia mas el mes y año
        //int k = obtenerDiasDelMes(Integer.parseInt(inicioClases.substring(3,5))-1);        
        //mm.setVencimiento_Mat(String.valueOf(k)+"/"+inicioClases.substring(3));        
        mm.setVencimiento_Mat(inicioClases);
        mm.setId_Matricula(codMatricula);
        con = ClsConexion.getConection();
        m.AgregarAlumnoPagoMatricula(mm,con);  
        ClsConexion.closeConnection(con);
        ///////////////////////////////////////////////////////////////////////////////////  
        
        /*PGO_MENSUALIDAD*/     
        //calculo de la mensualidad proporcional//
        /*obtenemos la mensualidad de acuerdo al horario del periodo*/
        MensualidadBase = obtenerMensualidadBase(codPeriodo,entrada,salida);            
        /*de acuerdo a los días del mes se calcula el total 
        a pagar de mensualidad*/            
        String mes = inicioClases.substring(3, 5);
        String diaInicio = inicioClases.substring(0,2);
        int diasmes = obtenerDiasDelMes(Integer.parseInt(mes)-1);
        int diasrestantes = diasmes - Integer.parseInt(diaInicio)+1;             
        //(mensualidad / días del mes) * días restantes           
        Mensualidad = (MensualidadBase / diasmes) * (diasrestantes);        
        //mes inicial y final para usarlo e el for
        ClsMatMen mame = new ClsMatMen();   
        String mesi="";
        String mesf="";
        try 
        {
            con = ClsConexion.getConection();
            ResultSet rsPer = mame.SeleccionarFechasPeriodo(codPeriodo,con);        
            //mes de inicio de clases 
            mesi = inicioClases.substring(3, 5);
            while (rsPer.next())
            {
                //mes de fin periodo
                mesf = rsPer.getString("fin_periodo").substring(3, 5);
            }
            ClsConexion.closeConnection(con);
        } catch (Exception ex) 
        { Logger.getLogger(Pagos.class.getName()).log(Level.SEVERE, null, ex);
        }
        //a es mes inicial -1 (numero del mes)
        int a = Integer.parseInt(mesi)-1;                
        int meses = obtenerMeses(mesi,mesf);
        int i;           
        
        for(i=0;i<=meses;i++)
        {   
            if(i==0) //para el primer mes solo se aplica la proporcional
            {
                ClsEntidadMen em = new ClsEntidadMen();     
                //month es un array de meses
                em.setNombre_Mensualidad(month[a+i]);
                //AGREGA MENSUALIDAD PROPORCIONAL                                            
                em.setCantidad_Mensualidad(Mensualidad);    
                em.setTotal_Mensualidad(Mensualidad);                        
                em.setPendiente_Mensualidad(Mensualidad);
                //indico el mes que se esta colocando, queda así: 15/asd/YYYY
                int asd = a+i+1;
                int diasMes = obtenerDiasDelMes(asd-1);//menos 1 porque cuenta desde 0
                em.setVencimiento_Mensualidad(String.valueOf(diasMes)+"/"+String.valueOf(asd)+inicioClases.substring(5));
                em.setId_Matricula(codMatricula);
                con = ClsConexion.getConection();
                mame.AgregarAlumnoPagoMensualidad(em,con); 
                ClsConexion.closeConnection(con);
            }
            else
            {
                ClsEntidadMen em = new ClsEntidadMen();     
                //month es un array de meses
                em.setNombre_Mensualidad(month[a+i]);
                //AGREGA MENSUALIDAD NORMAL                                            
                em.setCantidad_Mensualidad(MensualidadBase);    
                em.setTotal_Mensualidad(MensualidadBase);                        
                em.setPendiente_Mensualidad(MensualidadBase);
                //indico el mes que se esta colocando, queda así: 15/asd/YYYY
                int asd = a+i+1;
                int diasMes = obtenerDiasDelMes(asd-1);
                em.setVencimiento_Mensualidad(String.valueOf(diasMes)+"/"+String.valueOf(asd)+inicioClases.substring(5));
                em.setId_Matricula(codMatricula);
                con = ClsConexion.getConection();
                mame.AgregarAlumnoPagoMensualidad(em,con); 
                ClsConexion.closeConnection(con);
            }
        }
        /////////////////////////////////////////////////////////////////////////////////
        
        /*PGO_COMIDA*/          
        if(alimentacion.equals("SI"))
        {
            String mesi2 = fechaComida.substring(3, 5);
            int a2 = Integer.parseInt(mesi2)-1;   
            //obtener cantidad de meses para usarlo en el for (desde periodo)
            for(i=0;i<=meses;i++)
            {  
                ClsEntidadComida em = new ClsEntidadComida();                    
                em.setMes_Comida(month[a2+i]);  
                //se calcula a partir de la fecha de inicio de S. Alimentación
                //por default coincide co el inicio de clases
                String mes2 = fechaComida.substring(3, 5);
                String diaInicio2 = fechaComida.substring(0,2);
                int diasmes2 = obtenerDiasDelMes(Integer.parseInt(mes2)-1);
                int diasrestantes2 = diasmes2 - Integer.parseInt(diaInicio2)+1;   
                //Se obtiene la alimentacion base segun el periodo asignado
                //es el precio del sevicio de alimentación
                comidabase = obtenerAlimentacionBase(codPeriodo);
                comida = (comidabase / diasmes2) * (diasrestantes2);                
                if(i==0) //solo para el primer mes se agrega la comida proporcional
                {                        
                    em.setMonto_Comida(comida);    
                    em.setTotal_Comida(comida);
                    em.setPendiente_Comida(comida); 
                    int asd = a2+i+1;
                    int diasMes = obtenerDiasDelMes(asd-1);
                    em.setVencimiento_Comida(String.valueOf(diasMes)+"/"+String.valueOf(asd)+inicioClases.substring(5));
                    em.setId_Matricula(codMatricula);
                    con = ClsConexion.getConection();
                    mame.AgregarAlumnoPagoComida(em,con);
                    ClsConexion.closeConnection(con);
                }
                else
                {
                    em.setMonto_Comida(comidabase);
                    em.setTotal_Comida(comidabase);                    
                    em.setPendiente_Comida(comidabase); 
                    int asd = a2+i+1;
                    int diasMes = obtenerDiasDelMes(asd-1);
                    em.setVencimiento_Comida(String.valueOf(diasMes)+"/"+String.valueOf(asd)+inicioClases.substring(5));
                    em.setId_Matricula(codMatricula);
                    con = ClsConexion.getConection();
                    mame.AgregarAlumnoPagoComida(em,con);
                    ClsConexion.closeConnection(con);
                }                    
            } 
        }                      
    }
   
  
   
   
  static double obtenerMensualidadBase(String CodPeriodo,String entrada, String salida)
    {
       double menbase;
       //Se obtienen los montos del a base de datos segun horario
       ClsPeriodo pe = new ClsPeriodo();
         try {
             ResultSet rsp = pe.SeleccionarPeriodo(CodPeriodo);
             while (rsp.next())
             {
                 String h1 = rsp.getString("hora_periodo");
                 String h2 = rsp.getString("horb_periodo");
                 String h3 = rsp.getString("horc_periodo");
                 MensualidadA = Double.parseDouble(h1);
                 MensualidadB = Double.parseDouble(h2);
                 MensualidadC = Double.parseDouble(h3);
             }                          
         } catch (Exception ex) {
             Logger.getLogger(Pagos.class.getName()).log(Level.SEVERE, null, ex);
         }
       
         //Los comparamos con los que el niño se a registrado y se asigna
        if(entrada.equals("8:00") && salida.equals("14:00"))
        {
            menbase = MensualidadA;
        }
        else if(entrada.equals("8:00") && salida.equals("17:00"))
        {
            menbase = MensualidadB;
        }
        else if(entrada.equals("8:00") && salida.equals("19:00"))
        {
            menbase = MensualidadC;
        }
        else menbase = 0.00;
       return menbase; 
    }
    static Double obtenerAlimentacionBase(String CodPeriodo)
    {
        Double base=0.0;
        ClsPeriodo pe = new ClsPeriodo();
           try {
               ResultSet rsp = pe.SeleccionarPeriodo(CodPeriodo);
               while (rsp.next())
               {
                   String monto = rsp.getString("alimentacion_periodo");

                   base = Double.parseDouble(monto);

               }                          
           } catch (Exception ex) {
               Logger.getLogger(Pagos.class.getName()).log(Level.SEVERE, null, ex);
           }
           return base;
    }
  
    
  
  //MODIFICAR ALIMENTACION ALUMNO!!!
  
    public static void ModificarAlumnoComida(String codMatricula,String fechaComida){
     DecimalFormat df = new DecimalFormat("0.00");           
        ClsAlumno alumnos = new ClsAlumno();
        
        //variables
        String inicioClases="";
        String entrada="";
        String salida="";
        String alimentacion="";
        String codSeccion="";
        String codPeriodo="";
       
        
        //Obtengo datos necesarios para trabajar
        ClsGenerar genera = new ClsGenerar();        
        try
        {   
            con = ClsConexion.getConection();
            ResultSet rsGenera = genera.SeleccionarPagoMatricula(codMatricula,con);      
            while(rsGenera.next())
            {
                inicioClases = rsGenera.getString("inicio_clases");
                entrada      = rsGenera.getString("horarioe_matricula");
                salida       = rsGenera.getString("horarios_matricula");
                alimentacion = rsGenera.getString("comida_alumno");
                codSeccion   = rsGenera.getString("seccion_id");     
                codPeriodo   = rsGenera.getString("id_periodo");
            }
            ClsConexion.closeConnection(con);
        }
        catch(Exception ex)
        {  ex.printStackTrace();  } 
        
        String mesi2;
        String mesf2 = null;
        ClsMatMen mame = new ClsMatMen();
        try 
        {
            con = ClsConexion.getConection();
            ResultSet rsPer = mame.SeleccionarFechasPeriodo(codPeriodo,con);            
            while (rsPer.next())
            {
                //mes de fin periodo
                mesf2 = rsPer.getString("fin_periodo").substring(3, 5);
            }
            ClsConexion.closeConnection(con);
        } catch (Exception ex) 
        { Logger.getLogger(Pagos.class.getName()).log(Level.SEVERE, null, ex);
        }
         mesi2 = fechaComida.substring(3, 5);
        int a2 = Integer.parseInt(mesi2)-1;  
        int i;
        int meses = obtenerMeses(mesi2,mesf2);
        
        switch (alimentacion) {
            case "SI":
                //obtener cantidad de meses para usarlo en el for (desde periodo)
                for(i=0;i<=meses;i++)
                {
                    ClsEntidadComida em = new ClsEntidadComida();
                    em.setMes_Comida(month[a2+i]);
                    //se calcula a partir de la fecha de inicio de S. Alimentación
                    //por default coincide co el inicio de clases
                    String mes2 = fechaComida.substring(3, 5);
                    String diaInicio2 = fechaComida.substring(0,2);
                    int diasmes2 = obtenerDiasDelMes(Integer.parseInt(mes2)-1);
                    int diasrestantes2 = diasmes2 - Integer.parseInt(diaInicio2)+1;
                    //Se obtiene la alimentacion base segun el periodo asignado
                    //es el precio del sevicio de alimentación
                    comidabase = obtenerAlimentacionBase(codPeriodo);
                    comida = (comidabase / diasmes2) * (diasrestantes2);
                    if(i==0) //solo para el primer mes se agrega la comida proporcional
                    {
                        em.setMonto_Comida(comida);
                        em.setTotal_Comida(comida);
                        em.setPendiente_Comida(comida);
                        int asd = a2+i+1;
                        em.setVencimiento_Comida("15/"+String.valueOf(asd)+fechaComida.substring(5));
                        em.setId_Matricula(codMatricula);
                        con = ClsConexion.getConection();
                        mame.AgregarAlumnoPagoComida(em,con);
                        ClsConexion.closeConnection(con);
                    }
                    else
                    {
                        em.setMonto_Comida(comidabase);
                        em.setTotal_Comida(comidabase);
                        em.setPendiente_Comida(comidabase);
                        int asd = a2+i+1;
                        em.setVencimiento_Comida("15/"+String.valueOf(asd)+fechaComida.substring(5));
                        em.setId_Matricula(codMatricula);
                        con = ClsConexion.getConection();
                        mame.AgregarAlumnoPagoComida(em,con);
                        ClsConexion.closeConnection(con);
                    }
                }   break;
 
            case "NO":
                //eliminar con meses e id_matricula
                //se necesita a partir de que dia se va a retirar (dias que se le va a cobrar)
                //y a partir del siguiente mes se borran las deudas
                 for(i=0;i<=meses;i++)
                {
                    ClsEntidadComida em = new ClsEntidadComida();
                    em.setMes_Comida(month[a2+i]);
                    //se calcula a partir de la fecha de inicio de S. Alimentación
                    //por default coincide co el inicio de clases
                    String mes2 = fechaComida.substring(3, 5);
                    String diaInicio2 = fechaComida.substring(0,2);
                    int diasmes2 = obtenerDiasDelMes(Integer.parseInt(mes2)-1);
                    int diasrestantes2 = diasmes2 - Integer.parseInt(diaInicio2)+1;
                    //Se obtiene la alimentacion base segun el periodo asignado
                    //es el precio del sevicio de alimentación
                    comidabase = obtenerAlimentacionBase(codPeriodo);
                    comida = (comidabase / diasmes2) * (diasrestantes2);
                    if(i==0) //solo para fecha que se retirara = comida proporcional
                    {
                        em.setMonto_Comida(comida);
                        em.setTotal_Comida(comida);
                        em.setPendiente_Comida(comida);
                        int asd = a2+i+1;
                        em.setVencimiento_Comida("15/"+String.valueOf(asd)+fechaComida.substring(5));
                        em.setId_Matricula(codMatricula);        
                        con = ClsConexion.getConection();
                        mame.ModificarPagoComida(codMatricula,month[a2+i],em,con);
                        ClsConexion.closeConnection(con);
                    }
                    else
                    {
                        //ELIMINAR MESES                
                        con = ClsConexion.getConection();
                       mame.EliminarPagoComida(codMatricula, month[a2+i],con);    
                       ClsConexion.closeConnection(con);
                    }
                }   break;
        }
                  
    }
  
  
}
