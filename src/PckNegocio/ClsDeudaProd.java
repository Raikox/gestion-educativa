/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckNegocio;

import PckConexion.ClsConexion;
import PckEntidad.ClsEntidadDeudaProd;
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
public class ClsDeudaProd {
   
    
    public void AgregarDeudaProducto(ClsEntidadDeudaProd Producto,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_I_PgoDeudaProducto(?,?,?,?)}");
            statement.setString("ptotal_deudap", Producto.getTotal_Deudap());
            statement.setString("ppendiente_deudap", Producto.getPendiente_Deudap());
            statement.setString("pfecha_deudap", Producto.getFecha_Deudap());            
            statement.setString("pven_deudap", Producto.getVencimiento_Deudap());
            statement.execute(); 
           
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }
    
    public void EliminarRelacionProducto(String codigo, Connection con)
    {
        try
        {  
            
            PreparedStatement st = con.prepareStatement("delete from pgo_relacion_producto "
                    + "where id_deudap = (?);");
            st.setString(1, codigo);            
            st.executeUpdate();
            
        }catch(SQLException ex){
            Logger.getLogger(ClsEntregable.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
     public void EliminarDeudaProducto(String codigo,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_D_PgoDeudaProducto(?)}");
            statement.setString("pid_deudap",codigo);
            statement.execute();
           
        }catch(SQLException ex){
            Logger.getLogger(ClsEntregable.class.getName()).log(Level.SEVERE, null, ex);     
        }
    }
    
    public ResultSet ListaCodigoDeudaProducto(Connection con)  throws Exception{
        ResultSet rs = null;
        try{
            Statement statement = con.createStatement();
            rs = statement.executeQuery("select max(id_deudap) AS id from pgo_deuda_producto");              
            return rs;            
        }catch(SQLException ex){
            throw ex;
        }
        
    }
    public ResultSet SeleccionarDeudaProducto(String codProd,Connection con)  throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_PgoDeudaProducto(?)}");
            statement.setString("coddeuda", codProd);            
            
            rs = statement.executeQuery();
            return rs;
            
        }catch(SQLException ex){
            throw ex;
        }
      }
    public void ModificarPendienteProducto(String codigo,double pendiente,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_PendienteProducto(?,?)}");
            statement.setString("coddeuda", codigo);            
            statement.setDouble("ppendiente", pendiente);            
            statement.execute();
           
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        }
    
    public void ModificarDeudaProd(String codigo,ClsEntidadDeudaProd Matmen,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_PagoDeudaProd(?,?,?)}");
            statement.setString("pbusqueda", codigo);            
            statement.setString("ptotal_deudap", Matmen.getTotal_Deudap());
            statement.setString("ppendiente_deudap", Matmen.getPendiente_Deudap());    
            
            statement.execute();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
      
}
