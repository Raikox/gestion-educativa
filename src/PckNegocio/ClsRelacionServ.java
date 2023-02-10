/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckNegocio;

import PckConexion.ClsConexion;
import PckEntidad.ClsEntidadRelacionServ;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author kmv
 */
public class ClsRelacionServ {
     private Connection con = new ClsConexion().getConection();
     
     public void AgregarRelacionServicio(ClsEntidadRelacionServ Servicio){
        try{
            CallableStatement statement = con.prepareCall("{call sp_I_PgoRelacionServicio(?,?,?)}");
            statement.setString("pid_servicio", Servicio.getId_Servicio());
            statement.setString("pid_matricula", Servicio.getId_Alumno());
            statement.setString("pid_deudas", Servicio.getId_Deuda_Servicio());
            
            statement.execute(); 
            con.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }
     
      public void EliminarRelacionServicio(String codigo){
        try{
            CallableStatement statement = con.prepareCall("{call sp_D_PgoRelacionServicio(?)}");
            statement.setString("pid_deudas",codigo);
            statement.execute();
            con.close();
        }catch(SQLException ex){
            ex.printStackTrace();            
        }
    }
     
}
