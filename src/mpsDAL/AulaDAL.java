/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mpsDAL;

import PckConexion.ClsConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mpsEntity.AulaEntity;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email jkevin.mv@gmail.com
 */
public class AulaDAL {
    
    public ArrayList<AulaEntity> ListarAulas()
    {
        ArrayList<AulaEntity> arrayAula = new ArrayList<>();
        Connection con = ClsConexion.getConection();
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sqlQuery = "select s.id_seccion, s.nombre_seccion, s.cantidad_seccion, s.asistencia_seccion, \n" +
                        "s.MPS_Profesor_id_profesor, s.MPS_Profesor_id_auxiliar1, s.MPS_Profesor_id_auxiliar2,\n" +
                        "p.id_periodo, p.nombre_periodo\n" +
                        "from mat_seccion as s inner join mat_periodo as p \n" +
                        "on s.MPS_Periodo_id_periodo  = p.id_periodo";
        
        try
        {
            pst = con.prepareStatement(sqlQuery);
            rs = pst.executeQuery();
            
            while(rs.next())
            {
                AulaEntity aulaEntity = new AulaEntity();
                aulaEntity.setId_seccion(rs.getInt("id_seccion"));
                aulaEntity.setNombre_seccion(rs.getString("nombre_seccion"));
                aulaEntity.setCantidad_seccion(rs.getString("cantidad_seccion"));
                aulaEntity.setAsistencia_seccion(rs.getInt("asistencia_seccion"));
                aulaEntity.setMPS_Profesor_id_profesor(rs.getInt("MPS_Profesor_id_profesor"));
                aulaEntity.setMPS_Profesor_id_auxiliar1(rs.getInt("MPS_Profesor_id_auxiliar1"));
                aulaEntity.setMPS_Profesor_id_auxiliar2(rs.getInt("MPS_Profesor_id_auxiliar2"));
                aulaEntity.setMPS_Periodo_id_periodo(rs.getInt("id_periodo"));
                aulaEntity.setNombre_periodo(rs.getString("nombre_periodo"));
                
                arrayAula.add(aulaEntity);
            }            
        }
        catch(SQLException ex){
            Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex){
            Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try 
            {
                pst.close();
                rs.close();
                ClsConexion.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }        
        return arrayAula;    
    }
    
    public ArrayList<AulaEntity> ListarAulasActivas()
    {
        ArrayList<AulaEntity> arrayAula = new ArrayList<>();
        Connection con = ClsConexion.getConection();
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sqlQuery = "select s.id_seccion, s.nombre_seccion, s.cantidad_seccion, s.asistencia_seccion, \n" +
                        "s.MPS_Profesor_id_profesor, s.MPS_Profesor_id_auxiliar1, s.MPS_Profesor_id_auxiliar2,\n" +
                        "p.id_periodo, p.nombre_periodo\n" +
                        "from mat_seccion as s inner join mat_periodo as p \n" +
                        "on s.MPS_Periodo_id_periodo  = p.id_periodo \n"
                        + "where p.estado_periodo = 'Activo'";
        
        try
        {
            pst = con.prepareStatement(sqlQuery);
            rs = pst.executeQuery();
            
            while(rs.next())
            {
                AulaEntity aulaEntity = new AulaEntity();
                aulaEntity.setId_seccion(rs.getInt("id_seccion"));
                aulaEntity.setNombre_seccion(rs.getString("nombre_seccion"));
                aulaEntity.setCantidad_seccion(rs.getString("cantidad_seccion"));
                aulaEntity.setAsistencia_seccion(rs.getInt("asistencia_seccion"));
                aulaEntity.setMPS_Profesor_id_profesor(rs.getInt("MPS_Profesor_id_profesor"));
                aulaEntity.setMPS_Profesor_id_auxiliar1(rs.getInt("MPS_Profesor_id_auxiliar1"));
                aulaEntity.setMPS_Profesor_id_auxiliar2(rs.getInt("MPS_Profesor_id_auxiliar2"));
                aulaEntity.setMPS_Periodo_id_periodo(rs.getInt("id_periodo"));
                aulaEntity.setNombre_periodo(rs.getString("nombre_periodo"));
                
                arrayAula.add(aulaEntity);
            }            
        }
        catch(SQLException ex){
            Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex){
            Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try 
            {
                pst.close();
                rs.close();
                ClsConexion.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }        
        return arrayAula;    
    }
    
    public ArrayList<AulaEntity> ListarAulaDocente(int _IdPersonal)
    {
        ArrayList<AulaEntity> arrayAula = new ArrayList<>();
        Connection con = ClsConexion.getConection();
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sqlQuery = "SELECT s.id_seccion,s.nombre_seccion,s.cantidad_seccion, s.asistencia_seccion,\n" +
                        "pr.personal_id as 'id_docente',\n" +
                        "CONCAT (pr.personal_apellido_paterno,' ',pr.personal_apellido_materno,', ',pr.personal_nombre) as 'docente',\n" +
                        "pr1.personal_id as 'id_auxiliar1',\n" +
                        "CONCAT (pr1.personal_apellido_paterno,' ',pr1.personal_apellido_materno,', ',pr1.personal_nombre) as 'auxiliar1', \n" +
                        "pr2.personal_id as 'id_auxiliar2',\n" +
                        "CONCAT (pr2.personal_apellido_paterno,' ',pr2.personal_apellido_materno,', ',pr2.personal_nombre) as 'auxiliar2', \n" +
                        "p.id_periodo, p.nombre_periodo, p.estado_periodo, p.anio_periodo\n" +
                        "FROM mat_seccion AS s INNER JOIN mat_periodo AS p \n" +
                        "ON s.MPS_Periodo_id_periodo = p.id_periodo\n" +
                        "INNER JOIN adm_personal AS pr\n" +
                        "ON s.MPS_Profesor_id_profesor = pr.personal_id or s.MPS_Profesor_id_auxiliar1 = null\n" +
                        "INNER JOIN adm_personal AS pr1\n" +
                        "ON s.MPS_Profesor_id_auxiliar1 = pr1.personal_id or s.MPS_Profesor_id_auxiliar2 = null\n" +
                        "INNER JOIN adm_personal AS pr2\n" +
                        "ON s.MPS_Profesor_id_auxiliar2 = pr2.personal_id \n" +
                        "where pr.personal_id = ? and p.estado_periodo ='Activo'\n" +
                        "ORDER BY s.nombre_seccion";
        
        try
        {
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, _IdPersonal);
            rs = pst.executeQuery();
            
            while(rs.next())
            {
                AulaEntity aulaEntity = new AulaEntity();
                aulaEntity.setId_seccion(rs.getInt("id_seccion"));
                aulaEntity.setNombre_seccion(rs.getString("nombre_seccion"));
                aulaEntity.setCantidad_seccion(rs.getString("cantidad_seccion"));
                aulaEntity.setAsistencia_seccion(rs.getInt("asistencia_seccion"));
                aulaEntity.setMPS_Profesor_id_profesor(rs.getInt("id_docente"));
                aulaEntity.setMPS_Profesor_id_auxiliar1(rs.getInt("id_auxiliar1"));
                aulaEntity.setMPS_Profesor_id_auxiliar2(rs.getInt("id_auxiliar2"));
                aulaEntity.setMPS_Periodo_id_periodo(rs.getInt("id_periodo"));
                aulaEntity.setNombre_periodo(rs.getString("nombre_periodo"));
                
                arrayAula.add(aulaEntity);
            }            
        }
        catch(SQLException ex){
            Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex){
            Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try 
            {
                pst.close();
                rs.close();
                ClsConexion.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }        
        return arrayAula;    
    }
    
    public ArrayList<AulaEntity> ListarAulasPorPeriodo(int _IdPeriodo)
    {
        ArrayList<AulaEntity> arrayAula = new ArrayList<>();
        Connection con = ClsConexion.getConection();
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sqlQuery = "select s.id_seccion, s.nombre_seccion, s.cantidad_seccion, s.asistencia_seccion, \n" +
                        "s.MPS_Profesor_id_profesor, s.MPS_Profesor_id_auxiliar1, s.MPS_Profesor_id_auxiliar2,\n" +
                        "p.id_periodo, p.nombre_periodo, p.estado_periodo\n" +
                        "from mat_seccion as s inner join mat_periodo as p \n" +
                        "on s.MPS_Periodo_id_periodo  = p.id_periodo where p.id_periodo = ?";
        
        try
        {
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, _IdPeriodo);
            rs = pst.executeQuery();
            
            while(rs.next())
            {
                AulaEntity aulaEntity = new AulaEntity();
                aulaEntity.setId_seccion(rs.getInt("id_seccion"));
                aulaEntity.setNombre_seccion(rs.getString("nombre_seccion"));
                aulaEntity.setCantidad_seccion(rs.getString("cantidad_seccion"));
                aulaEntity.setAsistencia_seccion(rs.getInt("asistencia_seccion"));
                aulaEntity.setMPS_Profesor_id_profesor(rs.getInt("MPS_Profesor_id_profesor"));
                aulaEntity.setMPS_Profesor_id_auxiliar1(rs.getInt("MPS_Profesor_id_auxiliar1"));
                aulaEntity.setMPS_Profesor_id_auxiliar2(rs.getInt("MPS_Profesor_id_auxiliar2"));
                aulaEntity.setMPS_Periodo_id_periodo(rs.getInt("id_periodo"));
                aulaEntity.setNombre_periodo(rs.getString("nombre_periodo"));
                
                arrayAula.add(aulaEntity);
            }            
        }
        catch(SQLException ex){
            Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex){
            Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try 
            {
                pst.close();
                rs.close();
                ClsConexion.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }        
        return arrayAula;    
    }
}
