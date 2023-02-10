/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mpsDAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mpsEntity.RolEntity;
import mpsEntity.UsuarioEntity;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email jkevin.mv@gmail.com
 */
public class UsuarioDAL {

    public ArrayList<UsuarioEntity> SeleccionarUsuarios(Connection con) {
        
        ArrayList<UsuarioEntity> arrayUsuario = new ArrayList<>();
    
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sqlQuery = "SELECT p.personal_id, p.personal_nombre, p.personal_apellido_paterno, "
                + "p.personal_apellido_materno, p.personal_estado, r.rol_id, r.rol_nombre "
                + "FROM adm_personal AS p "
                + "INNER JOIN adm_rol AS r ON p.rol_id = r.rol_id "
                + "ORDER BY p.personal_nombre ";

        try {
            
            pst = con.prepareStatement(sqlQuery);
            rs = pst.executeQuery();
            
            while(rs.next()) {
                
                UsuarioEntity usuarioEntity = new UsuarioEntity (
                        rs.getString("personal_id"),
                        rs.getString("personal_nombre"),
                        rs.getString("personal_apellido_paterno"),
                        rs.getString("personal_apellido_materno"),
                        rs.getString("personal_estado"),
                        rs.getString("rol_id"),
                        rs.getString("rol_nombre")
                );
                
                arrayUsuario.add(usuarioEntity);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }        
        finally {
            try 
            {
                if(pst != null) { pst.close(); }
                if(rs != null) { rs.close(); } 
                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return arrayUsuario;
    }
    
    public ArrayList<UsuarioEntity> SeleccionarUsuarioDetalle(Connection con, String IdUsuario) {
        
        ArrayList<UsuarioEntity> arrayUsuario = new ArrayList<>();
        
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sqlQuery = "SELECT p.fecha_nacimiento, p.personal_dni, p.personal_telefono "
                + "FROM adm_personal AS p WHERE p.personal_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, IdUsuario);
            rs = pst.executeQuery();
            
            while(rs.next()) {
                
                UsuarioEntity usuarioEntity = new UsuarioEntity (
                        rs.getString("personal_dni"),
                        rs.getString("personal_telefono"),
                        rs.getString("fecha_nacimiento")
                );
               
                arrayUsuario.add(usuarioEntity);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }        
        finally {
            try 
            {
                if(pst != null) { pst.close(); }
                if(rs != null) { rs.close(); } 
                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return arrayUsuario;
    }
    
    public ArrayList<UsuarioEntity> SeleccionarUsuario(Connection con, String IdUsuario) {
        
        ArrayList<UsuarioEntity> arrayUsuario = new ArrayList<>();
        
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sqlQuery = "SELECT p.personal_nombre, p.personal_apellido_paterno, p.personal_apellido_materno, "
                + "p.personal_dni, p.personal_telefono, p.fecha_nacimiento, p.rol_id, r.rol_nombre "
                + "FROM adm_personal AS p INNER JOIN adm_rol AS r ON p.rol_id = r.rol_id "
                + "WHERE p.personal_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, IdUsuario);
            rs = pst.executeQuery();
            
            while(rs.next()) {
                
                UsuarioEntity usuarioEntity = new UsuarioEntity (
                        rs.getString("personal_nombre"),
                        rs.getString("personal_apellido_paterno"),
                        rs.getString("personal_apellido_materno"),
                        rs.getString("personal_dni"),
                        rs.getString("personal_telefono"),
                        rs.getString("fecha_nacimiento"),
                        rs.getString("rol_id"),
                        rs.getString("rol_nombre")
                );
               
                arrayUsuario.add(usuarioEntity);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }        
        finally {
            try 
            {
                if(pst != null) { pst.close(); }
                if(rs != null) { rs.close(); } 
                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return arrayUsuario;
    }
    
    public ArrayList<UsuarioEntity> SeleccionarUsuarioLogin(Connection con, String IdUsuario) {
        
        ArrayList<UsuarioEntity> arrayUsuario = new ArrayList<>();
        
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sqlQuery = "SELECT p.personal_usuario, p.personal_password "
                + "FROM adm_personal AS p WHERE p.personal_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, IdUsuario);
            rs = pst.executeQuery();
            
            while(rs.next()) {
                
                UsuarioEntity usuarioEntity = new UsuarioEntity (
                        rs.getString("personal_usuario"),
                        rs.getString("personal_password")
                );
               
                arrayUsuario.add(usuarioEntity);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex) {
            Logger.getLogger(UsuarioDAL.class.getName()).log(Level.SEVERE, null, ex);
        }        
        finally {
            try 
            {
                if(pst != null) { pst.close(); }
                if(rs != null) { rs.close(); } 
                
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return arrayUsuario;
    }
    
    public void GuardarUsuario(Connection con, UsuarioEntity usuarioEntity) {
        
        PreparedStatement pst = null;
        String sqlQuery = "INSERT INTO adm_personal (personal_nombre, personal_apellido_paterno, "
                + "personal_apellido_materno, personal_dni, personal_telefono, personal_usuario, "
                + "personal_password, fecha_nacimiento, personal_estado, rol_id) VALUES (?,?,?,?,?,?,?,?,?,?)";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, usuarioEntity.getPersonal_nombre());
            pst.setString(2, usuarioEntity.getPersonal_apellido_paterno());
            pst.setString(3, usuarioEntity.getPersonal_apellido_materno());
            pst.setString(4, usuarioEntity.getPersonal_dni());
            pst.setString(5, usuarioEntity.getPersonal_telefono());
            pst.setString(6, usuarioEntity.getPersonal_usuario());
            pst.setString(7, usuarioEntity.getPersonal_password());
            pst.setString(8, usuarioEntity.getFecha_nacimiento());
            pst.setString(9, usuarioEntity.getPersonal_estado());
            pst.setString(10, usuarioEntity.getRol_id());
            
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void EditarUsarioLogin(String IdUsuario, String Usuario, String Password, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_personal SET "
                + "personal_usuario = ?,"
                + "personal_password = sha(?) "
                + "WHERE personal_id = ? ";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, Usuario);
            pst.setString(2, Password);
            pst.setString(3, IdUsuario);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void EditarUsarioEstado(String IdUsuario, String Estado, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_personal SET "
                + "personal_estado = ? "
                + "WHERE personal_id = ? ";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, Estado);
            pst.setString(2, IdUsuario);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void EditarUsuario(Connection con, UsuarioEntity usuarioEntity, String IdUsuario) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_personal SET "
                + "personal_nombre = ?, "
                + "personal_apellido_paterno = ?, "
                + "personal_apellido_materno = ?, "
                + "personal_dni = ?, "
                + "personal_telefono = ?, "
                + "fecha_nacimiento = ?, "
                + "rol_id = ? "
                + "WHERE personal_id = ?";        
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, usuarioEntity.getPersonal_nombre());
            pst.setString(2, usuarioEntity.getPersonal_apellido_paterno());
            pst.setString(3, usuarioEntity.getPersonal_apellido_materno());
            pst.setString(4, usuarioEntity.getPersonal_dni());
            pst.setString(5, usuarioEntity.getPersonal_telefono());            
            pst.setString(6, usuarioEntity.getFecha_nacimiento());
            pst.setString(7, usuarioEntity.getRol_id());
            pst.setString(8, IdUsuario);
            
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public ArrayList<RolEntity> SeleccionarRoles (Connection con) {
        
        ArrayList<RolEntity> arrayRol = new ArrayList<>();
        
        ResultSet rs = null;
        PreparedStatement pst = null;
        
        String sqlQuery = "SELECT rol_id, rol_nombre, rol_descripcion FROM adm_rol";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            rs = pst.executeQuery();
            
            while(rs.next()) {
                
                RolEntity rolEntity = new RolEntity( rs.getString("rol_id"), rs.getString("rol_nombre") );
                arrayRol.add(rolEntity);                 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex) {
            Logger.getLogger(UsuarioDAL.class.getName()).log(Level.SEVERE, null, ex);
        }        
        finally {
            try 
            {
                if(pst != null) { pst.close(); }
                if(rs != null) { rs.close(); } 
                
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return arrayRol;
    }
    
}
