/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckNegocio;

import PckConexion.ClsConexion;

import PckEntidad.ClsEntidadDetalleBoleta;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author PC01
 */
public class ClsDetalleBoleta {
    
       
  
    public void AgregarDetalleBoleta(ClsEntidadDetalleBoleta Boleta,Connection con){
        try{
            
            CallableStatement statement = con.prepareCall("{call sp_I_Detalle_Boleta(?,?,?,?,?,?,?)}");
            statement.setInt("pcant_detalle", Boleta.getCantidad_Detalle());
            statement.setString("pdesc_detalle", Boleta.getDescripcion_Detalle());
            statement.setDouble("punitario_detalle", Boleta.getUnitario_Detalle());
            statement.setDouble("pimporte_detalle", Boleta.getImporte_Detalle());
            statement.setString("ptipo_detalle", Boleta.getTipo_Detalle());
            statement.setString("pid_boleta", Boleta.getId_Boleta());
            statement.setString("pid_deuda", Boleta.getId_Deuda());           
            statement.executeUpdate();            
        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }    
    }
    
    public void ModificarDetalleBoleta(ClsEntidadDetalleBoleta Boleta,String codigo,Connection con){
        try{
           
            CallableStatement statement = con.prepareCall("{call sp_U_DetalleBoleta(?,?,?)}");
            statement.setString("pid_detalle", codigo);
            statement.setDouble("punitario_detalle", Boleta.getUnitario_Detalle());
            statement.setDouble("pimporte_detalle", Boleta.getImporte_Detalle());                     
            statement.execute();           
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }
    public void ModificarDetalleBoletaFull(ClsEntidadDetalleBoleta Boleta,String codigo,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_DetalleBoletaFull(?,?,?,?,?)}");
            statement.setString("pid_detalle", codigo); 
            statement.setInt("pcant_detalle", Boleta.getCantidad_Detalle());
            statement.setString("pdesc_detalle", Boleta.getDescripcion_Detalle());
            statement.setDouble("punitario_detalle", Boleta.getUnitario_Detalle());
            statement.setDouble("pimporte_detalle", Boleta.getImporte_Detalle());                              
            statement.execute();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }
    
    public void ModificarDetalleBoletaAnulada(String desc,Double importe,String codigo,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_DetalleBoletaAnulada(?,?,?)}");
            statement.setString("pid_detalle", codigo);            
            statement.setString("pdesc_detalle", desc);   
            statement.setDouble("pimporte_detalle", importe);
            statement.execute();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }
    
     public void EliminarDetalleBoleta(String codigo,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_D_DetalleBoleta(?)}");
            statement.setString("pid_detalle",codigo);
            statement.execute();
      
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    public ResultSet SeleccionarCodigoDetalle(String codBoleta,Connection con)  throws Exception{
        
        ResultSet rs = null;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_CodigoDetalleBoleta(?)}");
            statement.setString("codigo", codBoleta);                     
            rs = statement.executeQuery();            
            return rs;
            
        }catch(SQLException ex){
            throw ex;
        }
      }
    
    public ArrayList<ClsEntidadDetalleBoleta> SeleccionarDetalles(String bol,Connection con){
        ArrayList<ClsEntidadDetalleBoleta> Detalles = new ArrayList<ClsEntidadDetalleBoleta>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_S_BoletaDetalle(?)}");
            statement.setString("pboleta_id", bol);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadDetalleBoleta boleta = new ClsEntidadDetalleBoleta();
                boleta.setId_Detalle(rs.getString("id_detalle"));
                boleta.setCantidad_Detalle(rs.getInt("cant_detalle"));
                boleta.setDescripcion_Detalle(rs.getString("descripcion_boleta"));
                boleta.setUnitario_Detalle(rs.getDouble("unitario_detalle"));
                boleta.setImporte_Detalle(rs.getDouble("importe"));
                boleta.setTipo_Detalle(rs.getString("tipo_detalle"));
                boleta.setId_Deuda(rs.getString("deuda_id"));
                Detalles.add(boleta);
            }
            return Detalles;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    
    
    
}

