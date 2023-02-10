/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PckNegocio;

import PckConexion.ClsConexion;
import PckEntidad.ClsEntidadNivel;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author J Kevin Montes De Oca Vizcarra
 */
public class ClsNivel {
    private Connection con = new ClsConexion().getConection();
    
    public void AgregarNivel(ClsEntidadNivel Nivel){
        try{
            CallableStatement statement = con.prepareCall("{call sp_I_nivel(?)}");
                        
//            statement.setString("pnivel_id", Nivel.getNivel_id());            
            statement.setString("pnivel_nombre", Nivel.getNivel_nombre());
            
            statement.executeUpdate();
//            con.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }
    
    
    public ArrayList<ClsEntidadNivel> SeleccionarNivel()
    {
        ArrayList<ClsEntidadNivel> Nivel = new ArrayList<ClsEntidadNivel>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_S_Nivel}");            
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadNivel nivel = new ClsEntidadNivel();
                nivel.setNivel_id(rs.getString("nivel_id"));
                nivel.setNivel_nombre(rs.getString("nivel_nombre"));                
                Nivel.add(nivel);
            }           
            return Nivel;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public void ModificarNivel(ClsEntidadNivel Nivel,String codigo){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_Nivel(?,?)}");
            statement.setString("pnivel_id", codigo); 
            statement.setString("pnivel_nombre", Nivel.getNivel_nombre());            
           
            statement.executeUpdate();           
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }
    
}
