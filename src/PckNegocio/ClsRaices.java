/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PckNegocio;

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
 * @author Kevin
 */
public class ClsRaices {
    
    public String SeleccionarRaizEntregable(Connection con)  throws Exception{
        ResultSet rs = null;
        String raices = null;
        try{
            Statement statement = con.createStatement();
            rs = statement.executeQuery("select raiz_entregable from ent_raices");
            while(rs.next())
            {
                raices = rs.getString("raiz_entregable");
            }
            return raices;
        }catch(SQLException ex){
            throw ex;
        }
    }
    
    public String SeleccionarRaizFormato(Connection con)  throws Exception{
        ResultSet rs = null;
        String raices = null;
        try{
            Statement statement = con.createStatement();
            rs = statement.executeQuery("select raiz_formato from ent_raices");
            while(rs.next())
            {
                raices = rs.getString("raiz_formato");
            }
            return raices;
        }catch(SQLException ex){
            throw ex;
        }
    }
    
    public void BorrarRaiz(Connection con){
        try{
            
            Statement statement = con.createStatement();     
            statement.executeUpdate("delete from ent_raices");                    
        }catch(SQLException ex){
            Logger.getLogger(ClsEntregable.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    
    public void EditarRaizEntregable(String xEntregable,Connection con){
        try
        {               
            PreparedStatement statement = con.prepareStatement("update ent_raices "
                    + "set raiz_entregable = (?) where raices_id = 1;");
            statement.setString(1, xEntregable);
            statement.executeUpdate();
        }catch(SQLException ex){
            Logger.getLogger(ClsEntregable.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public void EditarRaizFormato(String xFormato,Connection con){
        try
        {       
           
            PreparedStatement statement = con.prepareStatement("update ent_raices set "
                    + "raiz_formato = (?) where raices_id = 1 ");   
            statement.setString(1, xFormato);
            statement.executeUpdate();
        }catch(SQLException ex){
            Logger.getLogger(ClsEntregable.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
}
