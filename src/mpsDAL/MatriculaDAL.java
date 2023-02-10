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
import mpsEntity.AulaEntity;
import mpsEntity.ContactoEntity;
import mpsEntity.MatriculaEntity;
import mpsEntity.NivelEntity;
import mpsEntity.PeriodoEntity;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email jkevin.mv@gmail.com
 */
public class MatriculaDAL {

    public ArrayList<MatriculaEntity> ListarIdMatricula (String IdPeriodo, Connection con) {
        
        ArrayList<MatriculaEntity> arrayMatricula = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sqlQuery = "SELECT m.id_matricula FROM mat_matricula AS m "
                    + "INNER JOIN mat_seccion AS s ON m.seccion_id = s.id_seccion "
                    + "INNER JOIN mat_periodo AS p ON s.MPS_Periodo_id_periodo = p.id_periodo "
                    + "WHERE p.id_periodo = ? ";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, IdPeriodo);
            rs = pst.executeQuery();
            
            while(rs.next()) {
                
                MatriculaEntity matriculaEntity = new MatriculaEntity(rs.getString("id_matricula"));
                arrayMatricula.add(matriculaEntity);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MatriculaDAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch(Exception ex) {
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
        
        return arrayMatricula;
    }
    
    public void EditarVencimientoLonchera(String Mes, String IdMatricula,String FechaVencimiento, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE pgo_comida SET "
                        + "ven_comida = ? "
                        + "WHERE mes_comida = ? "
                        + "AND id_matricula = ? ";
                        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, FechaVencimiento);
            pst.setString(2, Mes);
            pst.setString(3, IdMatricula);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarVencimientoMensualidad(String Mes, String IdMatricula,String FechaVencimiento, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE pgo_mensualidad SET "
                        + "ven_men = ? "
                        + "WHERE nombre_men = ? "
                        + "AND id_matricula = ? ";
                        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, FechaVencimiento);
            pst.setString(2, Mes);
            pst.setString(3, IdMatricula);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public ArrayList<ContactoEntity> SeleccionarContactos(Connection con, String IdAlumno) {
        
        ArrayList<ContactoEntity> arrayContacto = new ArrayList<>();
        
        ResultSet rs = null;
        PreparedStatement pst = null;
        
        String sqlQuery = "select concat(a.apellidop_alumno,' ',a.apellidom_alumno,', ', a.nombre_alumno) as 'alumno', a.telefono_alumno, \n" +
            "concat(m.madre_apellido_paterno,' ',m.madre_apellido_materno,', ', m.madre_nombre) as 'madre', m.madre_telefono1,\n" +
            "concat(p.padre_apellido_paterno,' ',p.padre_apellido_materno,', ', p.padre_nombre) as 'padre', p.padre_telefono1\n" +
            "from mat_alumno as a inner join mat_madre as m on a.madre_id = m.madre_id\n" +
            "inner join mat_padre as p on a.padre_id = p.padre_id\n" +
            "where a.id_alumno = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, IdAlumno);
            rs = pst.executeQuery();
            
            while(rs.next()) {
                
                ContactoEntity contactoEntityAlumno = new ContactoEntity( rs.getString("alumno"), rs.getString("telefono_alumno") );
                ContactoEntity contactoEntityMadre = new ContactoEntity( rs.getString("madre"), rs.getString("madre_telefono1") );
                ContactoEntity contactoEntityPadre = new ContactoEntity( rs.getString("padre"), rs.getString("padre_telefono1") );
                arrayContacto.add(contactoEntityAlumno);
                arrayContacto.add(contactoEntityMadre);
                arrayContacto.add(contactoEntityPadre);
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
       
        return arrayContacto;
    }
    
    public ArrayList<NivelEntity> SeleccionarNiveles(Connection con) {
        
        ArrayList<NivelEntity> arrayNivel = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pst = null;
        
        String sqlQuery = "SELECT nivel_id, nivel_nombre FROM mat_nivel ORDER BY nivel_nombre ";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            rs = pst.executeQuery();
            
            while(rs.next()) {
                
                NivelEntity nivelEntity = new NivelEntity( rs.getString("nivel_id"), rs.getString("nivel_nombre") );
                arrayNivel.add(nivelEntity);                 
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
        return arrayNivel;
    }
    
    public ArrayList<PeriodoEntity> SeleccionarPeriodos(Connection con, String IdNivel) {
        
        ArrayList<PeriodoEntity> arrayPeriodo = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pst = null;
        
        String sqlQuery = "SELECT id_periodo, nombre_periodo, estado_periodo, nivel_id FROM mat_periodo "
                + "WHERE nivel_id = ? AND estado_periodo = 'Activo' "
                + "ORDER BY nombre_periodo ";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, IdNivel);
            rs = pst.executeQuery();
            
            while(rs.next()) {
                
                PeriodoEntity periodoEntity = new PeriodoEntity( 
                        rs.getInt("id_periodo"), 
                        rs.getString("nombre_periodo"),
                        rs.getString("estado_periodo"),
                        rs.getInt("nivel_id")
                );
                arrayPeriodo.add(periodoEntity);                 
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
        return arrayPeriodo;
    }
    
    public ArrayList<AulaEntity> SeleccionarAulas(Connection con, String IdPeriodo) {
        
        ArrayList<AulaEntity> arrayAula = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pst = null;
        
        String sqlQuery = "SELECT id_seccion, nombre_seccion, MPS_Periodo_id_periodo FROM mat_seccion "
                + "WHERE MPS_Periodo_id_periodo = ? "
                + "ORDER BY nombre_seccion ";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, IdPeriodo);
            rs = pst.executeQuery();
            
            while(rs.next()) {
                
                AulaEntity aulaEntity = new AulaEntity( 
                        rs.getInt("id_seccion"), 
                        rs.getString("nombre_seccion"),
                        rs.getInt("MPS_Periodo_id_periodo")                        
                );
                arrayAula.add(aulaEntity);                 
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
        return arrayAula;
    }
    
}
