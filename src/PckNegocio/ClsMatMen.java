/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckNegocio;

import PckConexion.ClsConexion;

import PckEntidad.ClsEntidadComida;
import PckEntidad.ClsEntidadMatMen;
import PckEntidad.ClsEntidadMen;
import PckEntidad.ClsEntidadPeriodo;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author kmv
 */
public class ClsMatMen {
    
    
    
      public void AgregarAlumnoPagoMatricula(ClsEntidadMatMen MatMen,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_I_PgoMatricula(?,?,?,?,?)}");
            statement.setDouble("pmatricula_mat", MatMen.getMatricula_Mat());            
            statement.setDouble("ptotal_mat", MatMen.getTotal_Mat());
            statement.setDouble("ppendiente_mat", MatMen.getPendiente_Mat());
            statement.setString("pven_mat", MatMen.getVencimiento_Mat());
            statement.setString("pid_matricula", MatMen.getId_Matricula());          
            statement.execute();
        
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }
      //para Pagos (aun sin usar)
      public ResultSet ListaCdogioAlumno(Connection con)  throws Exception{
        ResultSet rs = null;
        try{
            Statement statement = con.createStatement();
            rs = statement.executeQuery("select max(id_alumno) AS id_alu from pgo_alumno");              
            return rs;
            
        }catch(SQLException ex){
            throw ex;
        }
        }
      
     public ResultSet SeleccionarCodAlumno(String ApellidoP,String ApellidoM, String Nombres,Connection con)  throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_PgoCodAlu(?,?,?)}");
            statement.setString("ppaterno", ApellidoP);
            statement.setString("pmaterno", ApellidoM);
            statement.setString("pnombres", Nombres);
            rs = statement.executeQuery();
            return rs;
            
        }catch(SQLException ex){
            throw ex;
        }
    } 
     
     public ResultSet SeleccionarFechasPeriodo(String periodo,Connection con)  throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_FechaPeriodo(?)}");
            statement.setString("pid_periodo", periodo);
            rs = statement.executeQuery();
            return rs;
            
        }catch(SQLException ex){
            throw ex;
        }
    } 
    
    public ResultSet SeleccionarMatriculaPago(String codigo,Connection con)  throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_MatriculaPago(?)}");
            statement.setString("pbusqueda", codigo);
            rs = statement.executeQuery();
            return rs;
            
        }catch(SQLException ex){
            throw ex;
        }
    }
    
    public void ModificarMatricula(String codigo,ClsEntidadMatMen Matmen,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_MatriculaPago(?,?,?,?)}");
            statement.setString("pbusqueda", codigo);
            statement.setDouble("pmatricula_mat", Matmen.getMatricula_Mat());
            statement.setDouble("ptotal_mat", Matmen.getTotal_Mat());
            statement.setDouble("ppendiente_mat", Matmen.getPendiente_Mat());
            statement.execute();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
   
      public void ModificarPendienteMatricula(String codigo, double pendiente,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_PendienteMatricula(?,?)}");
            statement.setString("pbusqueda", codigo);
            statement.setDouble("ppendiente_mat", pendiente);            
            statement.execute();
           
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
      
    /*MENSUALIDADDDDDD*/      
    public void AgregarAlumnoPagoMensualidad(ClsEntidadMen men,Connection con)
    {
        try{
            CallableStatement statement = con.prepareCall("{call sp_I_PgoMensualidad(?,?,?,?,?,?)}");
            statement.setString("pnombre_men", men.getNombre_Mensualidad());
            statement.setDouble("pcantidad_men", men.getCantidad_Mensualidad());
            statement.setDouble("ptotal_men", men.getTotal_Mensualidad());
            statement.setDouble("ppendiente_men", men.getPendiente_Mensualidad());
            statement.setString("pven_men", men.getVencimiento_Mensualidad());
            statement.setString("pid_matricula", men.getId_Matricula());                     
            statement.execute();
          
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }
      
      
       
      
      public ArrayList<ClsEntidadMen> ListarMeses(String codigo,Connection con){
        ArrayList<ClsEntidadMen> Mens = new ArrayList<ClsEntidadMen>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_L_MesesMensualidad(?)}");
            statement.setString("pbusqueda", codigo);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadMen men = new ClsEntidadMen();
                men.setId_Mensualidad(rs.getString("id_men"));
                men.setNombre_Mensualidad(rs.getString("nombre_men"));
                men.setId_Matricula(rs.getString("id_matricula"));
                
                Mens.add(men);
                 
            }
           
            return Mens;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
      
       public void ModificarMen(String codigo,String mes,ClsEntidadMen Matmen,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_PagoMen(?,?,?,?,?)}");
            statement.setString("pbusqueda", codigo);
            statement.setString("pmes", mes); 
            statement.setDouble("pmensualidad_men", Matmen.getCantidad_Mensualidad());
            statement.setDouble("ptotal_men", Matmen.getTotal_Mensualidad());
            statement.setDouble("ppendiente_men", Matmen.getPendiente_Mensualidad());
            
            statement.execute();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
       
       public ResultSet SeleccionarMensualidadPago(String codigo,String mes,Connection con)  throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_MensualidadPago(?,?)}");
            statement.setString("pbusqueda", codigo);
            statement.setString("pmes", mes); 
            rs = statement.executeQuery();
            return rs;
            
        }catch(SQLException ex){
            throw ex;
        }
    }
      public void ModificarPendienteMensualidad(String codigo, double pendiente,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_PendienteMensualidad(?,?)}");
            statement.setString("pbusqueda", codigo);             
            statement.setDouble("ppendiente_men", pendiente);
            
            statement.execute();
           
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
      
      //COMIDAAAAAAAAAAAAAAAA
      
      public void AgregarAlumnoPagoComida(ClsEntidadComida men,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_I_PgoComida(?,?,?,?,?,?)}");
            statement.setString("pmes_comida", men.getMes_Comida());
            statement.setDouble("pmonto_comida", men.getMonto_Comida());
            statement.setDouble("ptotal_comida", men.getTotal_Comida());
            statement.setDouble("ppendiente_comida", men.getPendiente_Comida());
            statement.setString("pven_comida", men.getVencimiento_Comida());
            statement.setString("pid_matricula", men.getId_Matricula());                    
            statement.execute();          
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }
      
       public void ModificarPagoComida(String codigo,String mes,ClsEntidadComida Matmen,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_PagoComida(?,?,?,?,?)}");
            statement.setString("pbusqueda", codigo);
            statement.setString("pmes", mes); 
            statement.setDouble("pmonto_comida", Matmen.getMonto_Comida());
            statement.setDouble("ptotal_comida", Matmen.getTotal_Comida());
            statement.setDouble("ppendiente_comida", Matmen.getPendiente_Comida());            
            statement.execute();
//            con.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        }
       
        public void EliminarPagoComida(String codigo,String mes,Connection con){
            try{
                CallableStatement statement = con.prepareCall("{call sp_D_PagoComida(?,?)}");
                statement.setString("pid_matricula",codigo);
                statement.setString("pmes",mes);
                statement.execute();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
       
        public void ModificarPendienteComida(String codigo,double pendiente,Connection con){
            try{
                CallableStatement statement = con.prepareCall("{call sp_U_PendienteComida(?,?)}");
                statement.setString("pbusqueda", codigo);                
                statement.setDouble("ppendiente_comida", pendiente);

                statement.execute();
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
     
      /////////////////////
      public ResultSet SeleccionarComidaPago(String codigo,String mes,Connection con)  throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_ComidaPago(?,?)}");
            statement.setString("pbusqueda", codigo);
            statement.setString("pmes", mes); 
            rs = statement.executeQuery();
            return rs;
            
        }catch(SQLException ex){
            throw ex;
        }
    }
      
      public ArrayList<ClsEntidadComida> ListarMesesComida(String codigo,Connection con){
        ArrayList<ClsEntidadComida> Mens = new ArrayList<ClsEntidadComida>();
        try
        {
            CallableStatement statement = con.prepareCall("{call sp_L_MesesComida(?)}");
            statement.setString("pbusqueda", codigo);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadComida men = new ClsEntidadComida();
                men.setId_Comida(rs.getString("id_comida"));
                men.setMes_Comida(rs.getString("mes_comida"));
                men.setId_Matricula(rs.getString("id_matricula"));
                
                Mens.add(men);
                 
            }
           
            return Mens;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
      
        
     
}
