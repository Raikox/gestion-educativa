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
import mpsEntity.PsicologiaAccionEntity;
import mpsEntity.PsicologiaEntity;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email jkevin.mv@gmail.com
 */
public class PsicologiaDAL {

    public ArrayList<PsicologiaEntity> ListarPsicologiaCasos(boolean Activo, boolean Inactivo, boolean Todo,String idPeriodo)
    {
        ArrayList<PsicologiaEntity> arrayPsicologia = new ArrayList<>();
        Connection con = ClsConexion.getConection();
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sqlQuery = "";
        
        if(Todo) {
            sqlQuery = "SELECT p.psc_id, DATE_FORMAT(p.psc_fecha, '%d/%m/%Y') as 'psc_fecha', p.psc_estado, \n" +
                "CONCAT(a.apellidop_alumno,' ',a.apellidom_alumno, ', ',a.nombre_alumno) as 'apellidos_nombres',\n" +
                "s.nombre_seccion, s.id_seccion,m.id_matricula \n" +
                "FROM mps_psicologia_caso as p \n" +
                "inner join mat_matricula as m on p.id_matricula = m.id_matricula \n" +
                "inner join mat_alumno as a on m.MPS_Alumno_id_alumno = a.id_alumno\n" +
                "inner join mat_seccion as s on m.seccion_id = s.id_seccion "+
                "where m.retiro = 'NO' AND s.MPS_Periodo_id_periodo = ? "+
                "order by s.nombre_seccion, p.psc_fecha DESC";
        }
        if(Activo) {
            sqlQuery = "SELECT p.psc_id, DATE_FORMAT(p.psc_fecha, '%d/%m/%Y') as 'psc_fecha', p.psc_estado, \n" +
                "CONCAT(a.apellidop_alumno,' ',a.apellidom_alumno, ', ',a.nombre_alumno) as 'apellidos_nombres',\n" +
                "s.nombre_seccion, s.id_seccion,m.id_matricula \n" +
                "FROM mps_psicologia_caso as p \n" +
                "inner join mat_matricula as m on p.id_matricula = m.id_matricula \n" +
                "inner join mat_alumno as a on m.MPS_Alumno_id_alumno = a.id_alumno\n" +
                "inner join mat_seccion as s on m.seccion_id = s.id_seccion "+
                "where m.retiro = 'NO' AND P.psc_estado = 'A' AND s.MPS_Periodo_id_periodo = ? "+
                "order by s.nombre_seccion, p.psc_fecha DESC";
        }
        if(Inactivo) {
            sqlQuery = "SELECT p.psc_id, DATE_FORMAT(p.psc_fecha, '%d/%m/%Y') as 'psc_fecha', p.psc_estado, \n" +
                "CONCAT(a.apellidop_alumno,' ',a.apellidom_alumno, ', ',a.nombre_alumno) as 'apellidos_nombres',\n" +
                "s.nombre_seccion, s.id_seccion,m.id_matricula \n" +
                "FROM mps_psicologia_caso as p \n" +
                "inner join mat_matricula as m on p.id_matricula = m.id_matricula \n" +
                "inner join mat_alumno as a on m.MPS_Alumno_id_alumno = a.id_alumno\n" +
                "inner join mat_seccion as s on m.seccion_id = s.id_seccion "+
                "where m.retiro = 'NO' AND P.psc_estado = 'I' AND s.MPS_Periodo_id_periodo = ? "+
                "order by s.nombre_seccion, p.psc_fecha DESC";
        }
        
        
        try
        {
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, idPeriodo);
            rs = pst.executeQuery();
                       
            while(rs.next())
            {
                PsicologiaEntity pscologiaEntity = new PsicologiaEntity();
                pscologiaEntity.setPsc_id(rs.getInt("psc_id"));
                pscologiaEntity.setId_matricula(rs.getString("id_matricula"));
                pscologiaEntity.setPsc_fecha(rs.getString("psc_fecha"));
                pscologiaEntity.setPsc_estado(rs.getString("psc_estado"));
                pscologiaEntity.setPsc_alumno(rs.getString("apellidos_nombres"));
                pscologiaEntity.setPsc_aula(rs.getString("nombre_seccion"));
                pscologiaEntity.setId_seccion(rs.getInt("id_seccion"));
                arrayPsicologia.add(pscologiaEntity);
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
                Logger.getLogger(PsicologiaDAL.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }        
        return arrayPsicologia;        
    }
    
    public ArrayList<PsicologiaEntity> ListarPsicologiaCasosAlumno(String idAlumno)
    {
        ArrayList<PsicologiaEntity> arrayPsicologia = new ArrayList<>();
        Connection con = ClsConexion.getConection();
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sqlQuery = "SELECT p.psc_id, DATE_FORMAT(p.psc_fecha, '%d/%m/%Y') as 'psc_fecha', p.psc_estado, \n" +
                "CONCAT(a.apellidop_alumno,' ',a.apellidom_alumno, ', ',a.nombre_alumno) as 'apellidos_nombres',\n" +
                "s.nombre_seccion, s.id_seccion, p.psc_problema, m.id_matricula \n" +
                "FROM mps_psicologia_caso as p \n" +
                "inner join mat_matricula as m on p.id_matricula = m.id_matricula \n" +
                "inner join mat_alumno as a on m.MPS_Alumno_id_alumno = a.id_alumno\n" +
                "inner join mat_seccion as s on m.seccion_id = s.id_seccion "+
                "where m.id_matricula = ? " + 
                "order by p.psc_fecha";
        
        try
        {
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, idAlumno);
            rs = pst.executeQuery();
                       
            while(rs.next())
            {
                PsicologiaEntity pscologiaEntity = new PsicologiaEntity();
                pscologiaEntity.setPsc_id(rs.getInt("psc_id"));
                pscologiaEntity.setId_matricula(rs.getString("id_matricula"));
                pscologiaEntity.setPsc_fecha(rs.getString("psc_fecha"));
                pscologiaEntity.setPsc_estado(rs.getString("psc_estado"));
                pscologiaEntity.setPsc_alumno(rs.getString("apellidos_nombres"));
                pscologiaEntity.setPsc_aula(rs.getString("nombre_seccion"));
                pscologiaEntity.setId_seccion(rs.getInt("id_seccion"));
                pscologiaEntity.setPsc_problema(rs.getString("psc_problema"));
                arrayPsicologia.add(pscologiaEntity);
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
                Logger.getLogger(PsicologiaDAL.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }        
        return arrayPsicologia;        
    }
            
    public ArrayList<PsicologiaAccionEntity> ListarPsicologiaAccion(String idCaso)
    {
        ArrayList<PsicologiaAccionEntity> arrayPsicologiaCaso = new ArrayList<>();
        Connection con = ClsConexion.getConection();
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sqlQuery = "select a.psa_id, a.psa_accion, DATE_FORMAT(a.psa_fecha, '%d/%m/%Y') as 'psa_fecha', p.personal_id, \n" +
                "concat(p.personal_apellido_paterno,' ',p.personal_apellido_materno,', ',p.personal_nombre) as 'personal_nombre'\n" +
                "from mps_psicologia_accion as a \n" +
                "inner join mps_psicologia_caso as c on a.psc_id = c.psc_id\n" +
                "inner join adm_personal as p on a.personal_id = p.personal_id\n" +
                "where c.psc_id = ? \n" +
                "order by a.psa_fecha DESC";
        
        try
        {
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, idCaso);
            rs = pst.executeQuery();
                       
            while(rs.next())
            {
                PsicologiaAccionEntity psicologiaAccionEntity = new PsicologiaAccionEntity();
                psicologiaAccionEntity.setPsa_id(rs.getInt("psa_id"));
                psicologiaAccionEntity.setPersonal_id(rs.getInt("personal_id"));
                psicologiaAccionEntity.setPsa_accion(rs.getString("psa_accion"));
                psicologiaAccionEntity.setPsa_fecha(rs.getString("psa_fecha")); 
                psicologiaAccionEntity.setPersonal_nombre(rs.getString("personal_nombre"));
                arrayPsicologiaCaso.add(psicologiaAccionEntity);
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
                Logger.getLogger(PsicologiaDAL.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }        
        return arrayPsicologiaCaso;        
    }
    
    public void GuardarPsicologiaCaso
        (
            String _problema,
            String _frecuencia, 
            String _circunstancia,
            String _acciones,
            String _fecha,
            String _estado,
            int _idMatricula
        )
    {
        Connection con = ClsConexion.getConection();
        PreparedStatement pst = null;
        String sqlQuery = "insert into mps_psicologia_caso "
                    + "(psc_problema,psc_frecuencia,psc_circunstancia,psc_acciones,"
                    + "psc_fecha,psc_estado,id_matricula) values"
                    + "(?,?,?,?,(STR_TO_DATE(REPLACE(?,'/','.') ,GET_FORMAT(date,'EUR'))),?,?)";        
        try 
        {
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, _problema);
            pst.setString(2, _frecuencia);
            pst.setString(3, _circunstancia);
            pst.setString(4, _acciones);
            pst.setString(5, _fecha);
            pst.setString(6, _estado);
            pst.setInt(7, _idMatricula);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex){
            Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try 
            {
                pst.close();                
                ClsConexion.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
        
    }
        
    public void EditarPsicologiaCaso
        (
            int _psc_id,
            String _problema,
            String _frecuencia, 
            String _circunstancia,
            String _acciones,
            String _fecha,            
            int _idMatricula
        )
    {
        Connection con = ClsConexion.getConection();
        PreparedStatement pst = null;
        String sqlQuery = "update mps_psicologia_caso set "
                + "psc_problema = ?, "
                + "psc_frecuencia = ?, "
                + "psc_circunstancia = ?, "
                + "psc_acciones = ?, "
                + "psc_fecha = (STR_TO_DATE(REPLACE(?,'/','.') ,GET_FORMAT(date,'EUR'))), "                
                + "id_matricula = ? "
                + "where psc_id = ? ";
        
        try 
        {
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, _problema);
            pst.setString(2, _frecuencia);
            pst.setString(3, _circunstancia);
            pst.setString(4, _acciones);
            pst.setString(5, _fecha);            
            pst.setInt(6, _idMatricula);
            pst.setInt(7, _psc_id);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex){
            Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try 
            {
                pst.close();                
                ClsConexion.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
        
    }    
    
}
