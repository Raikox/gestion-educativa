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
import mpsEntity.AlumnoMatriculaEntity;
import mpsEntity.UsuarioEntity;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email jkevin.mv@gmail.com
 */
public class ReporteDAL {

    public ArrayList<UsuarioEntity> SeleccionarUsuarios(Connection con) {
        
        ArrayList<UsuarioEntity> arrayUsuarios = new ArrayList<>();
        
        ResultSet rs = null;
        PreparedStatement pst = null;
        
        String sqlQuery = "select p.personal_id, p.personal_nombre, p.personal_apellido_paterno, p.personal_apellido_materno, p.personal_dni, p.personal_telefono,"
                + " p.fecha_nacimiento, r.rol_nombre, r.rol_id FROM adm_personal as p INNER JOIN adm_rol as r ON p.rol_id = r.rol_id"
                + " WHERE p.personal_estado = 'A' ";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            //pst.setString(1, IdPeriodo);
            rs = pst.executeQuery();
            
            while(rs.next()) {
                
                UsuarioEntity usuarioEntity = new UsuarioEntity( 
                        rs.getString("personal_nombre"), 
                        rs.getString("personal_apellido_paterno"),
                        rs.getString("personal_apellido_materno"),
                        rs.getString("personal_dni"),
                        rs.getString("personal_telefono"),
                        rs.getString("fecha_nacimiento"),
                        rs.getString("rol_id"),
                        rs.getString("rol_nombre")                        
                );
                arrayUsuarios.add(usuarioEntity);                 
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
        
        return arrayUsuarios;
    }
    
    public ArrayList<AlumnoMatriculaEntity> SeleccionarAlumnosRetiradosPeriodo (String IdPeriodo, Connection con) {
        
        ArrayList<AlumnoMatriculaEntity> arrayAlumnoMatricula = new ArrayList<>();
        
        ResultSet rs = null;
        PreparedStatement pst = null;
        
        String sqlQuery = "SELECT m.id_matricula, a.id_alumno, a.nombre_alumno, a.apellidop_alumno, a.apellidom_alumno, \n" +
            "m.retiro, m.retiro_fecha, m.retiro_motivo, s.nombre_seccion, s.id_seccion \n" +
            "FROM mat_matricula AS m INNER JOIN mat_seccion AS s ON m.seccion_id = s.id_seccion\n" +
            "INNER JOIN mat_alumno AS a ON m.MPS_Alumno_id_alumno = a.id_alumno\n" +
            "WHERE m.retiro = 'SI' AND s.MPS_Periodo_id_periodo = ?\n" +
            "ORDER BY s.nombre_seccion, a.nombre_alumno";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, IdPeriodo);
            rs = pst.executeQuery();
            
            while(rs.next()) {
                
                AlumnoMatriculaEntity alumnoMatriculaEntity = new AlumnoMatriculaEntity( 
                        rs.getString("id_matricula"), 
                        rs.getString("id_alumno"),
                        rs.getString("nombre_alumno"),
                        rs.getString("apellidop_alumno"),
                        rs.getString("apellidom_alumno"),
                        rs.getString("retiro"),
                        rs.getString("retiro_fecha"),
                        rs.getString("retiro_motivo"),
                        rs.getString("id_seccion"),
                        rs.getString("nombre_seccion")
                );
                arrayAlumnoMatricula.add(alumnoMatriculaEntity);                 
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
        
        return arrayAlumnoMatricula;
    } 
    
    public ArrayList<AlumnoMatriculaEntity> SeleccionarAlumnosRetiradosAula (String IdAula, Connection con) {
        
        ArrayList<AlumnoMatriculaEntity> arrayAlumnoMatricula = new ArrayList<>();
        
        ResultSet rs = null;
        PreparedStatement pst = null;
        
        String sqlQuery = "SELECT m.id_matricula, a.id_alumno, a.nombre_alumno, a.apellidop_alumno, a.apellidom_alumno, \n" +
            "m.retiro, m.retiro_fecha, m.retiro_motivo, s.nombre_seccion, s.id_seccion\n" +
            "FROM mat_matricula AS m INNER JOIN mat_seccion AS s ON m.seccion_id = s.id_seccion\n" +
            "INNER JOIN mat_alumno AS a ON m.MPS_Alumno_id_alumno = a.id_alumno\n" +
            "WHERE m.retiro = 'SI' AND s.id_seccion = ?\n" +
            "ORDER BY a.nombre_alumno";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, IdAula);
            rs = pst.executeQuery();
            
            while(rs.next()) {
                
                AlumnoMatriculaEntity alumnoMatriculaEntity = new AlumnoMatriculaEntity( 
                        rs.getString("id_matricula"), 
                        rs.getString("id_alumno"),
                        rs.getString("nombre_alumno"),
                        rs.getString("apellidop_alumno"),
                        rs.getString("apellidom_alumno"),
                        rs.getString("retiro"),
                        rs.getString("retiro_fecha"),
                        rs.getString("retiro_motivo"),
                        rs.getString("id_seccion"),
                        rs.getString("nombre_seccion")
                );
                arrayAlumnoMatricula.add(alumnoMatriculaEntity);                 
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
        
        return arrayAlumnoMatricula;
    } 
    
}
