/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckNegocio;

import PckEntidad.ClsEntidadAnulaBoleta;
import PckEntidad.ClsEntidadBoleta;
import PckEntidad.ClsEntidadImpresionBoleta;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author PC01
 */


public class ClsBoleta {
    
    
    public void AgregarBoleta(ClsEntidadBoleta Boleta,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_I_Boleta(?,?,?,?)}");
            statement.setString("pnum_boleta", Boleta.getNumero_Boleta());            
            statement.setString("pfecha_boleta", Boleta.getFecha_Boleta());
            statement.setDouble("ptotal_boleta", Boleta.getTotal_Boleta());
            statement.setString("pid_matricula", Boleta.getId_Matricula());
            statement.executeUpdate();           
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }
   
     public void EliminarBoleta(String codigo,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_D_Boleta(?)}");
            statement.setString("pid_boleta",codigo);
            statement.executeUpdate();
            con.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    public void ModificaBoleta(ClsEntidadBoleta Boleta,String codigo,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_Boleta(?,?,?,?)}");
            statement.setString("pid_boleta", codigo); 
            statement.setString("pnum_boleta", Boleta.getNumero_Boleta());            
            statement.setString("pfecha_boleta", Boleta.getFecha_Boleta());   
            statement.setDouble("ptotal_boleta", Boleta.getTotal_Boleta());
            statement.executeUpdate();           
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }
    
    public void ModificaTotalBoleta(Double total,String codigo,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_BoletaTotal(?,?)}");
            statement.setString("pid_boleta", codigo);     
            statement.setDouble("ptotal_boleta", total);
            statement.executeUpdate();           
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }    

    public ArrayList<ClsEntidadAnulaBoleta> ListarBoletasAnuladas(Connection con){
        ArrayList<ClsEntidadAnulaBoleta> Mens = new ArrayList<ClsEntidadAnulaBoleta>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_L_BoletaAnulada()}");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadAnulaBoleta men = new ClsEntidadAnulaBoleta();
                men.setID_Boleta(rs.getString("id_boleta"));
                men.setId_Detalle_Boleta(rs.getString("id_detalle"));                
                men.setNumero_Boleta(rs.getString("num_boleta")); 
                men.setDescripcion_Boleta(rs.getString("desc_detalle"));
                men.setFecha_Boleta(rs.getString("fecha_boleta"));
                men.setTipo_Boleta(rs.getString("tipo_detalle"));
                Mens.add(men);
                 
            }
           
            return Mens;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<ClsEntidadImpresionBoleta> SeleccionarBoletaDetalle(String boleta,Connection con){
        ArrayList<ClsEntidadImpresionBoleta> Boleta = new ArrayList<ClsEntidadImpresionBoleta>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_S_BoletaDetalle(?)}");
            statement.setString("pboleta_id", boleta);            
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                ClsEntidadImpresionBoleta ib = new ClsEntidadImpresionBoleta();
                ib.setConcepto(rs.getString("descripcion_boleta"));
                ib.setUnitario(rs.getString("unitario_detalle"));
                ib.setImporte(rs.getString("importe"));
                ib.setId_Deuda(rs.getString("deuda_id"));
                ib.setTipo(rs.getString("tipo_detalle"));                
                Boleta.add(ib);
                 
            }
           
            return Boleta;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public ResultSet SeleccionarBoletaUnica(String codBoleta,Connection con)  throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_BoletaUnica(?)}");
            statement.setString("pboleta_id", codBoleta);
            rs = statement.executeQuery();
            return rs;
            
        }catch(SQLException ex){
            throw ex;
        }
    } 
    
    public ResultSet SeleccionarBoletaMatricula(String codMatricula,Connection con)  throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_BoletaMatricula(?)}");
            statement.setString("pid_matricula", codMatricula);
            rs = statement.executeQuery();
            return rs;
            
        }catch(SQLException ex){
            throw ex;
        }
    } 
    /*
    public ArrayList<ClsEntidadBoleta> SeleccionarBoletaMatriculaArray(String codMatricula,Connection con){
        ArrayList<ClsEntidadBoleta> Boleta = new ArrayList<ClsEntidadBoleta>();
        try
        {
            CallableStatement statement = con.prepareCall("{call sp_S_BoletaMatricula(?)}");
            statement.setString("pid_matricula", codMatricula);            
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                ClsEntidadBoleta ib = new ClsEntidadBoleta();
                ib.setId_Matricula(rs.getString("id_matricula"));
                ib.setId_Boleta(rs.getString("id_boleta"));
                ib.setNumero_Boleta(rs.getString("num_boleta"));
                ib.setNombre_Boleta(rs.getString("nombre"));
                ib.setFecha_Boleta(rs.getString("fecha_boleta"));                
                ib.setTotal_Boleta(Double.parseDouble(rs.getString("total_boleta")));
                Boleta.add(ib);
            }
            return Boleta;
        } 
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    */
    public ResultSet SeleccionarTotalBoleta(String inicio,String fin,Connection con)  throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_TotalBoleta(?,?)}");
            statement.setString("inicio", inicio);
            statement.setString("fin", fin);
            rs = statement.executeQuery();
            return rs;
            
        }catch(SQLException ex){
            throw ex;
        }
    } 
    
}

