/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckNegocio;

import PckEntidad.ClsEntidadAnecdotarioComentario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email Jkevin.mv@gmail.com
 */
public class ClsAnecdotarioComentario {

    
    public void GuardarAnecdotarioComentario(ClsEntidadAnecdotarioComentario Anecdotario,Connection con){
        try
        {
            CallableStatement statement = con.prepareCall("{call sp_I_AnecdotarioComentario(?,?,?,?)}");
            statement.setString("pcomentario_comentario", Anecdotario.getComentario_comentario());
            statement.setString("pcomentario_fecha", Anecdotario.getComentario_fecha());
            statement.setString("panecdotario_id", Anecdotario.getAnecdotario_id());
            statement.setString("ppersonal_id", Anecdotario.getPersonal_id());

            statement.executeUpdate();
        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }    
    }
    
    public void EditarAnecdotarioComentario(ClsEntidadAnecdotarioComentario Anecdotario,String codigo,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_AnecdotarioComentario(?,?)}");
            statement.setString("pcomentario_id", codigo); 
            //System.out.println("Comentario nuevo: "+Anecdotario.getComentario_comentario());
            statement.setString("pcomentario_comentario", Anecdotario.getComentario_comentario());
   
            statement.executeUpdate();           
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }
    
    public void EliminarAnecdotarioComentario(String codigo,Connection con)
    {
        try
        {
            CallableStatement statement = con.prepareCall("{call sp_D_AnecdotarioComentario(?)}");
            statement.setString("pcomentario_id", codigo);            
            statement.executeUpdate();           
        }catch(SQLException ex){
            ex.printStackTrace();
        }  
    }
    
    public ArrayList<ClsEntidadAnecdotarioComentario> SeleccionarAnecdotarioComentario(String codigo,Connection con)
    {
        ArrayList<ClsEntidadAnecdotarioComentario> Anecdotario = new ArrayList<ClsEntidadAnecdotarioComentario>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_S_AnecdotarioComentario(?)}");     
            statement.setString("panecdotario_id", codigo);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadAnecdotarioComentario anecdotario = new ClsEntidadAnecdotarioComentario();
                
                anecdotario.setComentario_id(rs.getString("comentario_id"));                 
                anecdotario.setPersonal_id(rs.getString("personal_id")); 
                anecdotario.setPersonal_nombre(rs.getString("personal_nombre")); 
                anecdotario.setComentario_comentario(rs.getString("comentario_comentario")); 
                anecdotario.setComentario_fecha(rs.getString("comentario_fecha")); 
                Anecdotario.add(anecdotario);
            }
            return Anecdotario;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    
}
