/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package prymatricula;


import PckConexion.ClsConexion;

import PckEntidad.ClsEntidadAsignar;
import PckEntidad.ClsEntidadDeudaServ;
import PckEntidad.ClsEntidadRelacionServ;
import PckNegocio.ClsAlumno;
import PckNegocio.ClsDeudaServ;
import PckNegocio.ClsRelacionServ;
import PckNegocio.ClsServicio;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author kmv
 */
public class AsignarServicio {
  static ResultSet rsProd = null;
  static ResultSet rsAlu = null;
  static ResultSet rsDeudaS = null;
  static String total, pendiente,codDeuda;
  static int aux=0;
  static Date date = new Date();
  static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
 static Connection con = null;
    /*CASO NIVEL*/
    
    public static void asignarNivel(String nivel,String codServ,String actualfecha,String fecha)
    {
        con = ClsConexion.getConection();
        //obtener alumnos segun el nivel (guarderia o inicial)
        ClsAlumno alumnos = new ClsAlumno();                
        ArrayList<ClsEntidadAsignar> alumno = alumnos.SeleccionarAlumnoPagoNivel(nivel,con);
        Iterator iterator = alumno.iterator();                
        String fila[] = new String[4];
        while(iterator.hasNext())
        {
            ClsEntidadAsignar Alumno = new ClsEntidadAsignar();
            Alumno = (ClsEntidadAsignar) iterator.next();
            fila[0] = Alumno.getId_Matricula();
            fila[1] = Alumno.getApellidos_Nombres();
            fila[2] = Alumno.getId_Nivel();        

            //obtenemos los datos de servicio
            ClsServicio s = new ClsServicio();
            try 
            {
                rsProd = s.SeleccionarServicio(codServ);
                while(rsProd.next())
                {
                total = rsProd.getString("precio_servicio");            
                pendiente = total;
                }            
            } catch (Exception ex) 
            {
                Logger.getLogger(AsignarProducto.class.getName()).log(Level.SEVERE, null, ex);
            }

            //asignamos datos a la tabla pgo_deuda_servicio
            ClsEntidadDeudaServ eds = new ClsEntidadDeudaServ();
            ClsDeudaServ ds = new ClsDeudaServ();
            eds.setTotal_Deuda_Servicio(total);
            eds.setPendiente_Deuda_Servicio(pendiente);
            eds.setFecha_Deuda_Servicio(actualfecha);         
            eds.setVencimiento_Deuda_Servicio(fecha);        
            ds.AgregarDeudaServicio(eds,con); 

            //obtenemos el ultimo codigo ingresado
            ClsDeudaServ DS = new ClsDeudaServ();
            try 
            {                  
                rsDeudaS = DS.ListaCodigoDeudaServicio(con);
                while(rsDeudaS.next())
                {
                    codDeuda = rsDeudaS.getString("id");
                }   

            } catch (Exception ex) {
            Logger.getLogger(AsignarServicio.class.getName()).log(Level.SEVERE, null, ex);
            }
            //relacionamos los codigos en la tabla pgo_relacion_servicio        
            ClsEntidadRelacionServ ers = new ClsEntidadRelacionServ();
            ClsRelacionServ rs = new ClsRelacionServ();
            ers.setId_Alumno(fila[0]);
            ers.setId_Servicio(codServ);        
            ers.setId_Deuda_Servicio(codDeuda);
            rs.AgregarRelacionServicio(ers); 
        } 
        ClsConexion.closeConnection(con); 
    }
    
    /*CASO PERIODO*/
    
     public static void asignarPeriodo(String periodo,String codServ,String actualfecha,String fecha)
    {
        con = ClsConexion.getConection();
        ClsAlumno alumnos = new ClsAlumno();                
        ArrayList<ClsEntidadAsignar> alumno = alumnos.SeleccionarAlumnoPagoPeriodo(periodo,con);
        Iterator iterator = alumno.iterator();                
        String fila[] = new String[13];
        while(iterator.hasNext())
        {
            ClsEntidadAsignar Alumno = new ClsEntidadAsignar();
            Alumno = (ClsEntidadAsignar) iterator.next();
            fila[0] = Alumno.getId_Matricula();
            fila[1] = Alumno.getApellidos_Nombres();
            fila[2] = Alumno.getId_Nivel();       

            //obtenemos los datos de servicio
            ClsServicio s = new ClsServicio();
            try {
                rsProd = s.SeleccionarServicio(codServ);
                while(rsProd.next())
                {
                total = rsProd.getString("precio_servicio");            
                pendiente = total;
                }            
            } catch (Exception ex) {
                Logger.getLogger(AsignarProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
            //asignamos datos a la tabla pgo_deuda_servicio
            ClsEntidadDeudaServ eds = new ClsEntidadDeudaServ();
            ClsDeudaServ ds = new ClsDeudaServ();
            eds.setTotal_Deuda_Servicio(total);
            eds.setPendiente_Deuda_Servicio(pendiente);
            eds.setFecha_Deuda_Servicio(actualfecha); 
            eds.setVencimiento_Deuda_Servicio(fecha);

            ds.AgregarDeudaServicio(eds,con);  

            //obtenemos el ultimo codigo ingresado
            ClsDeudaServ DS = new ClsDeudaServ();
            try 
            {           

                rsDeudaS = DS.ListaCodigoDeudaServicio(con);
                while(rsDeudaS.next())
                {
                    codDeuda = rsDeudaS.getString("id");
                }        

            } catch (Exception ex) {
            Logger.getLogger(AsignarServicio.class.getName()).log(Level.SEVERE, null, ex);
            }
            //relacionamos los codigos en la tabla pgo_relacion_servicio        
            ClsEntidadRelacionServ ers = new ClsEntidadRelacionServ();
            ClsRelacionServ rs = new ClsRelacionServ();
            ers.setId_Alumno(fila[0]);
            ers.setId_Servicio(codServ);        
            ers.setId_Deuda_Servicio(codDeuda);
            rs.AgregarRelacionServicio(ers); 
                             
        }
        ClsConexion.closeConnection(con); 
    }
    
    /*CASO SECCION*/ 
     public static void asignarSeccion(String seccion,String codServ,String actualfecha,String fecha)
    {
        con  = ClsConexion.getConection();
        ClsAlumno alumnos = new ClsAlumno();                
        ArrayList<ClsEntidadAsignar> alumno = alumnos.SeleccionarAlumnoPagoSeccion(seccion,con);
        Iterator iterator = alumno.iterator();                
        String fila[] = new String[13];
        while(iterator.hasNext())
        {
            ClsEntidadAsignar Alumno = new ClsEntidadAsignar();
            Alumno = (ClsEntidadAsignar) iterator.next();
            fila[0] = Alumno.getId_Matricula();
            fila[1] = Alumno.getApellidos_Nombres();
            fila[2] = Alumno.getId_Nivel();    
            //obtenemos los datos de servicio
            ClsServicio s = new ClsServicio();
            try {
                rsProd = s.SeleccionarServicio(codServ);
                while(rsProd.next())
                {
                total = rsProd.getString("precio_servicio");            
                pendiente = total;
                }            
            } catch (Exception ex) {
                Logger.getLogger(AsignarProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
            //asignamos datos a la tabla pgo_deuda_servicio
            ClsEntidadDeudaServ eds = new ClsEntidadDeudaServ();
            ClsDeudaServ ds = new ClsDeudaServ();
            eds.setTotal_Deuda_Servicio(total);
            eds.setPendiente_Deuda_Servicio(pendiente);
            eds.setFecha_Deuda_Servicio(actualfecha); 
            eds.setVencimiento_Deuda_Servicio(fecha);

            ds.AgregarDeudaServicio(eds,con);         

            //obtenemos el ultimo codigo ingresado
            ClsDeudaServ DS = new ClsDeudaServ();
            try 
            {            

                rsDeudaS = DS.ListaCodigoDeudaServicio(con);
                while(rsDeudaS.next())
                {
                    codDeuda = rsDeudaS.getString("id");
                } 

            } 
            catch (Exception ex) 
            {
            Logger.getLogger(AsignarServicio.class.getName()).log(Level.SEVERE, null, ex);
            }
            //relacionamos los codigos en la tabla pgo_relacion_servicio        
            ClsEntidadRelacionServ ers = new ClsEntidadRelacionServ();
            ClsRelacionServ rs = new ClsRelacionServ();
            ers.setId_Alumno(fila[0]);
            ers.setId_Servicio(codServ);        
            ers.setId_Deuda_Servicio(codDeuda);
            rs.AgregarRelacionServicio(ers);                
        }
        ClsConexion.closeConnection(con);
    }
     
     /*POR ALUMNO*/
    public static void asignarAlumno(String codAlu,String codServ,String actualfecha,String fecha)
    {
        ClsServicio s = new ClsServicio();
        try {
            rsProd = s.SeleccionarServicio(codServ);
            while(rsProd.next())
            {
            total = rsProd.getString("precio_servicio");            
            pendiente = total;
            }            
        } catch (Exception ex) {
            Logger.getLogger(AsignarProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
       //asignamos datos a la tabla pgo_deuda_servicio
        ClsEntidadDeudaServ eds = new ClsEntidadDeudaServ();
        ClsDeudaServ ds = new ClsDeudaServ();
        eds.setTotal_Deuda_Servicio(total);
        eds.setPendiente_Deuda_Servicio(pendiente);
        eds.setFecha_Deuda_Servicio(actualfecha);  
        eds.setVencimiento_Deuda_Servicio(fecha);
        con = ClsConexion.getConection();
        ds.AgregarDeudaServicio(eds,con);        
        ClsConexion.closeConnection(con);
        //obtenemos el ultimo codigo ingresado
        ClsDeudaServ DS = new ClsDeudaServ();
        try 
        {   
            con = ClsConexion.getConection();
            rsDeudaS = DS.ListaCodigoDeudaServicio(con);
            while(rsDeudaS.next())
            {
                codDeuda = rsDeudaS.getString("id");
            }       
            ClsConexion.closeConnection(con);
        } catch (Exception ex) {
        Logger.getLogger(AsignarServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        //relacionamos los codigos en la tabla pgo_relacion_servicio        
        ClsEntidadRelacionServ ers = new ClsEntidadRelacionServ();
        ClsRelacionServ rs = new ClsRelacionServ();
        ers.setId_Alumno(codAlu);
        ers.setId_Servicio(codServ);        
        ers.setId_Deuda_Servicio(codDeuda);
        rs.AgregarRelacionServicio(ers);      
    }
     
}
