/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckNegocio;

import PckConexion.ClsConexion;
import PckEntidad.ClsEntidadDeudaServ;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kmv
 */
public class ClsDeudaServ {
    
    public void AgregarDeudaServicio(ClsEntidadDeudaServ Servicio,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_I_PgoDeudaServicio(?,?,?,?)}");
            statement.setString("ptotal_deudas", Servicio.getTotal_Deuda_Servicio());
            statement.setString("ppendiente_deudas", Servicio.getPendiente_Deuda_Servicio());
            statement.setString("pfecha_deudas", Servicio.getFecha_Deuda_Servicio());
            statement.setString("pven_deudas", Servicio.getVencimiento_Deuda_Servicio());
            statement.execute(); 
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }
    
     public ResultSet ListaCodigoDeudaServicio(Connection con)  throws Exception{
        ResultSet rs = null;
        try{
            Statement statement = con.createStatement();
            rs = statement.executeQuery("select max(id_deudas) AS id from pgo_deuda_servicio");              
            return rs;
            
        }catch(SQLException ex){
            throw ex;
        }        
    }
    public ResultSet SeleccionarDeudaServicio(String codServ,Connection con)  throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_PgoDeudaServicio(?)}");
            statement.setString("coddeuda", codServ);                       
            rs = statement.executeQuery();
            return rs;            
        }catch(SQLException ex){
            throw ex;
        }
      }
    public void ModificarPendienteServicio(String codigo,double pendiente,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_PendienteServicio(?,?)}");
            statement.setString("coddeuda", codigo);            
            statement.setDouble("ppendiente", pendiente);
            statement.execute();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        }
    
    public void ModificarDeudaServ(String codigo,ClsEntidadDeudaServ Matmen,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_PagoDeudaServ(?,?,?)}");
            statement.setString("pbusqueda", codigo);            
            statement.setString("ptotal_deudas", Matmen.getTotal_Deuda_Servicio());
            statement.setString("ppendiente_deudas", Matmen.getPendiente_Deuda_Servicio());  
            statement.execute();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public void EliminarRelacionServicio(String codigo, Connection con)
    {
        try
        {   
            
            PreparedStatement st = con.prepareStatement("delete from pgo_relacion_servicio "
                    + "where id_deudas = (?);");
            st.setString(1, codigo);            
            st.executeUpdate();
            
        }catch(SQLException ex){
            Logger.getLogger(ClsEntregable.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void EliminarDeudaServicio(String codigo,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_D_PgoDeudaServicio(?)}");
            statement.setString("pid_deudas",codigo);
            statement.execute();
            
        }catch(SQLException ex){
            ex.printStackTrace();            
        }
    }
    
    public void ModificarPendienteServ(String codigo, String cantidad,Connection con)
           {
               
              try{
            Statement statement = con.createStatement();
            statement.executeUpdate("update pgo_deuda_servicio set pendiente_deudas = "+cantidad+" where id_deudas = "+codigo);                                      
        }catch(SQLException ex){
       ex.printStackTrace();
        }
              
    }
    
    
}
