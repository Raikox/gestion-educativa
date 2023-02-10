/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckNegocio;

import PckEntidad.ClsEntidadPersonal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email Jkevin.mv@gmail.com
 */
public class ClsPersonal {
    
    public void GuardarPersonal(ClsEntidadPersonal Personal,Connection con)
    {
        try
        {
            CallableStatement statement = con.prepareCall("{call sp_I_Personal(?,?,?,?,?,?,?,?)}");
            statement.setString("ppersonal_nombre", Personal.getPersonal_nombre());
            statement.setString("ppersonal_apellido_paterno", Personal.getPersonal_apellido_paterno());
            statement.setString("ppersonal_apellido_materno", Personal.getPersonal_apellido_materno());
            statement.setString("ppersonal_dni", Personal.getPersonal_dni());
            statement.setString("ppersonal_telefono", Personal.getPersonal_telefono());
            statement.setString("ppersonal_usuario", Personal.getPersonal_usuario());
            statement.setString("ppersonal_password", Personal.getPersonal_password());
            statement.setString("prol_id", Personal.getRol_id());
            statement.executeUpdate();
        }
        catch(SQLException ex)
        {
            Logger.getLogger(ClsPersonal.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    
    public void EditarPersonal(ClsEntidadPersonal Personal,String codigo,Connection con)
    {
        try
        {
            CallableStatement statement = con.prepareCall("{call sp_U_Personal(?,?,?,?,?,?,?,?,?)}");
            statement.setString("ppersonal_id", codigo);
            statement.setString("ppersonal_nombre", Personal.getPersonal_nombre());
            statement.setString("ppersonal_apellido_paterno", Personal.getPersonal_apellido_paterno());
            statement.setString("ppersonal_apellido_materno", Personal.getPersonal_apellido_materno());
            statement.setString("ppersonal_dni", Personal.getPersonal_dni());
            statement.setString("ppersonal_telefono", Personal.getPersonal_telefono());
            statement.setString("ppersonal_usuario", Personal.getPersonal_usuario());
            statement.setString("ppersonal_password", Personal.getPersonal_password());
            statement.setString("prol_id", Personal.getRol_id());
            statement.executeUpdate();           
        }
        catch(SQLException ex)
        {
             Logger.getLogger(ClsPersonal.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public void EditarPersonalSinPassword(ClsEntidadPersonal Personal,String codigo,Connection con)
    {
        try
        {
            CallableStatement statement = con.prepareCall("{call sp_U_PersonalSinPass(?,?,?,?,?,?,?,?)}");
            statement.setString("ppersonal_id", codigo);
            statement.setString("ppersonal_nombre", Personal.getPersonal_nombre());
            statement.setString("ppersonal_apellido_paterno", Personal.getPersonal_apellido_paterno());
            statement.setString("ppersonal_apellido_materno", Personal.getPersonal_apellido_materno());
            statement.setString("ppersonal_dni", Personal.getPersonal_dni());
            statement.setString("ppersonal_telefono", Personal.getPersonal_telefono());
            statement.setString("ppersonal_usuario", Personal.getPersonal_usuario());            
            statement.setString("prol_id", Personal.getRol_id());
            statement.executeUpdate();           
        }
        catch(SQLException ex)
        {
             Logger.getLogger(ClsPersonal.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    
    
    public ArrayList<ClsEntidadPersonal> SeleccionarPersonal(Connection con)
    {
        ArrayList<ClsEntidadPersonal> Personal = new ArrayList<ClsEntidadPersonal>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_S_Personal}");
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                ClsEntidadPersonal personal= new ClsEntidadPersonal();
                personal.setPersonal_id(rs.getString("personal_id"));
                personal.setPersonal_nombre(rs.getString("personal_nombre"));                
                personal.setPersonal_apellido_paterno(rs.getString("personal_apellido_paterno"));
                personal.setPersonal_apellido_materno(rs.getString("personal_apellido_materno"));
                personal.setPersonal_dni(rs.getString("personal_dni"));
                personal.setPersonal_telefono(rs.getString("personal_telefono"));
                personal.setPersonal_usuario(rs.getString("personal_usuario"));
                personal.setPersonal_password(rs.getString("personal_password"));
                personal.setRol_id(rs.getString("rol_id"));
                personal.setRol_nombre(rs.getString("rol_nombre"));
                personal.setPersonal_estado(rs.getString("personal_estado")); 
                Personal.add(personal);
            }          
            return Personal;
        } catch(SQLException ex){
            Logger.getLogger(ClsPersonal.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList<ClsEntidadPersonal> SeleccionarPersonalNombre(Connection con)
    {
        ArrayList<ClsEntidadPersonal> Personal = new ArrayList<ClsEntidadPersonal>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_S_ListarPersonalNombre}");
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                ClsEntidadPersonal personal= new ClsEntidadPersonal();
                personal.setPersonal_id(rs.getString("personal_id"));
                personal.setPersonal_nombre(rs.getString("nombres")); 
                personal.setPersonal_estado(rs.getString("personal_estado"));
                Personal.add(personal);
            }
            return Personal;
        } catch(SQLException ex){
            Logger.getLogger(ClsPersonal.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void EliminarPersonal(String codigo,Connection con)
    {
        try
        {
            CallableStatement statement = con.prepareCall("{call sp_D_Personal(?)}");
            statement.setString("ppersonal_id", codigo);
            statement.executeUpdate();           
        }
        catch(SQLException ex)
        {
             Logger.getLogger(ClsPersonal.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void ModificarEstadoPersonal(String codigo,String estado, Connection con)
    {
        try
        {
            PreparedStatement st = con.prepareStatement("update adm_personal set "
                    + "personal_estado = ? "
                    + "where personal_id = ?");
            st.setString(1, estado);
            st.setString(2, codigo);
            st.executeUpdate();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(ClsPersonal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
