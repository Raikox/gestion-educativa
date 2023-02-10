/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckNegocio;

import PckConexion.ClsConexion;
import PckEntidad.ClsEntidadPgoObservacion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kmv
 */
public class ClsPgoObservacion {
    
      private Connection con = new ClsConexion().getConection();
    
    public void AgregarObservacion(ClsEntidadPgoObservacion Obs){
        try{
            CallableStatement statement = con.prepareCall("{call sp_I_PgoObservacion(?,?)}");
            statement.setString("pobs_obs", Obs.getObservacion_Obs());            
            statement.setString("pid_matricula", Obs.getId_Alumno());                      
            statement.execute();
            con.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }
    
    public void ModificarObservacion(ClsEntidadPgoObservacion Obs, String codigo){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_PgoObservacion(?,?)}");
            statement.setString("pobs_obs", Obs.getObservacion_Obs());            
            statement.setString("pbusqueda", codigo);           
            
            statement.execute();
            con.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }
    
    public ResultSet SeleccionarObservacion(String codigo)  throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_PgoObservacion(?)}");
            statement.setString("pbusqueda", codigo);             
            rs = statement.executeQuery();
           
            return rs;
        }catch(SQLException ex){
            throw ex;
        }
    }
}
