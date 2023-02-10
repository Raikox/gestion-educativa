/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package prymatricula;


import PckConexion.ClsConexion;
import PckEntidad.ClsEntidadAsignar;
import PckEntidad.ClsEntidadDeudaProd;
import PckEntidad.ClsEntidadProducto;
import PckEntidad.ClsEntidadRelacionProd;
import PckNegocio.ClsAlumno;
import PckNegocio.ClsDeudaProd;
import PckNegocio.ClsProducto;
import PckNegocio.ClsRelacionProd;
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
public class AsignarProducto {
  static ResultSet rsProd = null;
  static ResultSet rsAlu = null;
  static ResultSet rsDeudaP = null;
  static String total, pendiente,stock,codDeuda;
  static int aux=0;
  static Date date = new Date();
  static Connection con = null;
  static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
  
    /*CASO NIVEL*/    
    public static void asignarNivel(String nivel,String codProd,String actfecha,String fecha)
    {
        //obtener alumnos segun el nivel (guarderia o inicial)
        con = ClsConexion.getConection();
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
        
        //obtenemos los datos de producto
        ClsProducto p = new ClsProducto();
        try 
        {
            rsProd = p.SeleccionarProducto(codProd);
            while(rsProd.next())
            {
            total = rsProd.getString("precio_producto");
            stock = rsProd.getString("stock_producto");
            pendiente = total;
            }            
        } catch (Exception ex) {
            Logger.getLogger(AsignarProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //asignamos datos a la tabla pgo_deuda_producto
        ClsEntidadDeudaProd edp = new ClsEntidadDeudaProd();
        ClsDeudaProd dp = new ClsDeudaProd();
        edp.setTotal_Deudap(total);
        edp.setPendiente_Deudap(pendiente);
        edp.setFecha_Deudap(actfecha);
        edp.setVencimiento_Deudap(fecha);
        
        dp.AgregarDeudaProducto(edp,con);    
        
        
        //obtenemos el ultimo codigo ingresado
        ClsDeudaProd DP = new ClsDeudaProd();
        try 
        {  
           
            rsDeudaP = DP.ListaCodigoDeudaProducto(con);
            while(rsDeudaP.next())
            {codDeuda = rsDeudaP.getString("id");} 
            
        }
        catch (Exception ex) {
        Logger.getLogger(AsignarServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        //relacionamos los codigos en la tabla pgo_relacion_producto
        ClsEntidadRelacionProd erp = new ClsEntidadRelacionProd();
        ClsRelacionProd rs = new ClsRelacionProd();
        erp.setId_Alumno(fila[0]);
        erp.setId_Producto(codProd);
        erp.setId_Deuda_Producto(codDeuda);
        rs.AgregarRelacionProducto(erp); 
        
        //modificar stock producto
        ClsEntidadProducto ep = new ClsEntidadProducto();
        ClsProducto ps = new ClsProducto();
        aux = Integer.parseInt(stock)-1;                
        ep.setStock_Producto(String.valueOf(aux));
        ps.ModificaStock(ep, codProd);  
        
        }
        ClsConexion.closeConnection(con);
    }
    
    public static void asignarPeriodo(String periodo,String codProd,String actfecha,String fecha)
    {
       //obtener alumnos segun el nivel (guarderia o inicial)
        con = ClsConexion.getConection();
        ClsAlumno alumnos = new ClsAlumno();                
        ArrayList<ClsEntidadAsignar> alumno = alumnos.SeleccionarAlumnoPagoPeriodo(periodo,con);
        Iterator iterator = alumno.iterator();                
        String fila[] = new String[4];
        while(iterator.hasNext())
        {
            ClsEntidadAsignar Alumno = new ClsEntidadAsignar();
            Alumno = (ClsEntidadAsignar) iterator.next();
            fila[0] = Alumno.getId_Matricula();
            fila[1] = Alumno.getApellidos_Nombres();
            fila[2] = Alumno.getId_Periodo();   

            //obtenemos los datos de producto
            ClsProducto p = new ClsProducto();
            try {
                rsProd = p.SeleccionarProducto(codProd);
                while(rsProd.next())
                {
                total = rsProd.getString("precio_producto");
                stock = rsProd.getString("stock_producto");
                pendiente = total;
                }            
            } catch (Exception ex) {
                Logger.getLogger(AsignarProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
            //asignamos datos a la tabla pgo_deuda_producto
            ClsEntidadDeudaProd edp = new ClsEntidadDeudaProd();
            ClsDeudaProd dp = new ClsDeudaProd();
            edp.setTotal_Deudap(total);
            edp.setPendiente_Deudap(pendiente);
            edp.setFecha_Deudap(actfecha);
            edp.setVencimiento_Deudap(fecha);
            dp.AgregarDeudaProducto(edp,con);     

            //obtenemos el ultimo codigo ingresado
            ClsDeudaProd DP = new ClsDeudaProd();
            try 
            {   

                rsDeudaP = DP.ListaCodigoDeudaProducto(con);
                while(rsDeudaP.next())
                {
                    codDeuda = rsDeudaP.getString("id");
                }  

            } 
            catch (Exception ex) 
            {
            Logger.getLogger(AsignarServicio.class.getName()).log(Level.SEVERE, null, ex);
            }
            //relacionamos los codigos en la tabla pgo_relacion_producto
            ClsEntidadRelacionProd erp = new ClsEntidadRelacionProd();
            ClsRelacionProd rs = new ClsRelacionProd();
            erp.setId_Alumno(fila[0]);
            erp.setId_Producto(codProd);
            erp.setId_Deuda_Producto(codDeuda);
            rs.AgregarRelacionProducto(erp);

            //modificar stock producto
            ClsEntidadProducto ep = new ClsEntidadProducto();
            ClsProducto ps = new ClsProducto();
            aux = Integer.parseInt(stock)-1;                
            ep.setStock_Producto(String.valueOf(aux));
            ps.ModificaStock(ep, codProd);    
        } 
        ClsConexion.closeConnection(con);
    }
    
    public static void asignarSeccion(String seccion,String codProd,String actfecha,String fecha)
    {
        //obtener alumnos segun el nivel (guarderia o inicial)
        con = ClsConexion.getConection();
        ClsAlumno alumnos = new ClsAlumno();                
        ArrayList<ClsEntidadAsignar> alumno = alumnos.SeleccionarAlumnoPagoSeccion(seccion,con);
        Iterator iterator = alumno.iterator();                
        String fila[] = new String[4];
        while(iterator.hasNext())
        {
            ClsEntidadAsignar Alumno = new ClsEntidadAsignar();
            Alumno = (ClsEntidadAsignar) iterator.next();
            fila[0] = Alumno.getId_Matricula();
            fila[1] = Alumno.getApellidos_Nombres();
            fila[2] = Alumno.getId_Seccion();   
        
            //obtenemos los datos de producto
            ClsProducto p = new ClsProducto();
            try {
                rsProd = p.SeleccionarProducto(codProd);
                while(rsProd.next())
                {
                total = rsProd.getString("precio_producto");
                stock = rsProd.getString("stock_producto");
                pendiente = total;
                }            
            } catch (Exception ex) {
                Logger.getLogger(AsignarProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
            //asignamos datos a la tabla pgo_deuda_producto
            ClsEntidadDeudaProd edp = new ClsEntidadDeudaProd();
            ClsDeudaProd dp = new ClsDeudaProd();
            edp.setTotal_Deudap(total);
            edp.setPendiente_Deudap(pendiente);
            edp.setFecha_Deudap(actfecha);
            edp.setVencimiento_Deudap(fecha);
           
            dp.AgregarDeudaProducto(edp,con);     
           

            //obtenemos el ultimo codigo ingresado
            ClsDeudaProd DP = new ClsDeudaProd();
            try 
            {   
                
                rsDeudaP = DP.ListaCodigoDeudaProducto(con);
                while(rsDeudaP.next())
                {codDeuda = rsDeudaP.getString("id");}  
                
            } catch (Exception ex) {
            Logger.getLogger(AsignarServicio.class.getName()).log(Level.SEVERE, null, ex);
            }
            //relacionamos los codigos en la tabla pgo_relacion_producto
            ClsEntidadRelacionProd erp = new ClsEntidadRelacionProd();
            ClsRelacionProd rs = new ClsRelacionProd();
            erp.setId_Alumno(fila[0]);
            erp.setId_Producto(codProd);
            erp.setId_Deuda_Producto(codDeuda);
            rs.AgregarRelacionProducto(erp);

            //modificar stock producto
            ClsEntidadProducto ep = new ClsEntidadProducto();
            ClsProducto ps = new ClsProducto();
            aux = Integer.parseInt(stock)-1;                
            ep.setStock_Producto(String.valueOf(aux));
            ps.ModificaStock(ep, codProd);                       
        }
        ClsConexion.closeConnection(con);
    }
    
    public static void asignarAlumno(String codAlu,String codProd,String actfecha,String fecha)
    {
       //obtenemos los datos de producto
        ClsProducto p = new ClsProducto();
        try {
            rsProd = p.SeleccionarProducto(codProd);
            while(rsProd.next())
            {
            total = rsProd.getString("precio_producto");
            stock = rsProd.getString("stock_producto");
            pendiente = total;
            }            
        } catch (Exception ex) {
            Logger.getLogger(AsignarProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        //asignamos datos a la tabla pgo_deuda_producto
        ClsEntidadDeudaProd edp = new ClsEntidadDeudaProd();
        ClsDeudaProd dp = new ClsDeudaProd();
        edp.setTotal_Deudap(total);
        edp.setPendiente_Deudap(pendiente);
        edp.setFecha_Deudap(actfecha);
        edp.setVencimiento_Deudap(fecha);
        con = ClsConexion.getConection();
        dp.AgregarDeudaProducto(edp,con);     
        
        //obtenemos el ultimo codigo ingresado
        ClsDeudaProd DP = new ClsDeudaProd();
        try 
        {   
           
            rsDeudaP = DP.ListaCodigoDeudaProducto(con);
            while(rsDeudaP.next())
            {codDeuda = rsDeudaP.getString("id");}   
            ClsConexion.closeConnection(con);
        } catch (Exception ex) {
        Logger.getLogger(AsignarServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        //relacionamos los codigos en la tabla pgo_relacion_producto
        ClsEntidadRelacionProd erp = new ClsEntidadRelacionProd();
        ClsRelacionProd rs = new ClsRelacionProd();
        erp.setId_Alumno(codAlu);
        erp.setId_Producto(codProd);
        erp.setId_Deuda_Producto(codDeuda);
        rs.AgregarRelacionProducto(erp); 
        
        //modificar stock producto
        ClsEntidadProducto ep = new ClsEntidadProducto();
        ClsProducto ps = new ClsProducto();
        aux = Integer.parseInt(stock)-1;                
        ep.setStock_Producto(String.valueOf(aux));
        ps.ModificaStock(ep, codProd);    
    }
}
