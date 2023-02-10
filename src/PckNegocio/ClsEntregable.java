/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PckNegocio;

import PckEntidad.ClsEntidadEntregable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kevin
 */
public class ClsEntregable {
    
    public ArrayList<ClsEntidadEntregable> SeleccionarEntregable(Connection con)
    {
        ArrayList<ClsEntidadEntregable> Nivel = new ArrayList<>();
      try{
            Statement statement = con.createStatement(); 
            ResultSet rs = statement.executeQuery("");
            while (rs.next()){
                ClsEntidadEntregable nivel = new ClsEntidadEntregable();
                nivel.setEntregable_id(rs.getString(""));
                Nivel.add(nivel);
            }           
            return Nivel;
        } catch(SQLException ex){
            Logger.getLogger(ClsEntregable.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
     public void AgregarEntregable(ClsEntidadEntregable Entregable, Connection con){
        try{
            String nombre = Entregable.getEntregable_nombre();
            String id=Entregable.getId_seccion();
            Statement statement = con.createStatement();     
            statement.executeUpdate("insert into ent_entregable (entregable_nombre,id_seccion) values "
                    + "('"+nombre+"','"+id+"')");            
        }catch(SQLException ex){
            Logger.getLogger(ClsEntregable.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
     
     public void BorrarEntregable(Connection con){
        try{
            
            Statement statement = con.createStatement();     
            statement.executeUpdate("delete from ent_entregable");                    
        }catch(SQLException ex){
            Logger.getLogger(ClsEntregable.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
     public void BorrarEntregableFiltro(String xPeriodo,Connection con){
        try{
            
            Statement statement = con.createStatement();     
            statement.executeUpdate("delete e.* from ent_entregable as e "
                    + "inner join mat_seccion as s on e.id_seccion = s.id_seccion "
                    + "where s.MPS_Periodo_id_periodo = "+xPeriodo);
        }catch(SQLException ex){
            Logger.getLogger(ClsEntregable.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
     
     
}
