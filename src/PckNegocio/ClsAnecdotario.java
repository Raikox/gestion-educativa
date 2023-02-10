/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckNegocio;

import PckEntidad.ClsEntidadAlumnoAnecdotario;
import PckEntidad.ClsEntidadAnecdotario;
import PckEntidad.ClsEntidadSeccion;
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
public class ClsAnecdotario {

    
    public void GuardarAnecdotario(ClsEntidadAnecdotario Anecdotario,Connection con){
        try
        {
            CallableStatement statement = con.prepareCall("{call sp_I_Anecdotario(?,?,?,?,?)}");
            statement.setString("panecdotario_anecdota", Anecdotario.getAnecdotario_anecdota());
            statement.setString("panecdotario_solucion", Anecdotario.getAnecdotario_solucion());
            statement.setString("panecdotario_estado", Anecdotario.getAnecdotario_estado());
            statement.setString("panecdotario_fecha", Anecdotario.getAnecdotario_fecha());
            statement.setString("pid_alumno", Anecdotario.getId_Alumno());
            statement.executeUpdate();
        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }    
    }
    
    public void EditarAnecdotario(ClsEntidadAnecdotario Anecdotario,String codigo,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_Anecdotario(?,?,?,?)}");
            statement.setString("panecdotario_id", codigo); 
            statement.setString("panecdotario_anecdota", Anecdotario.getAnecdotario_anecdota());
            statement.setString("panecdotario_solucion", Anecdotario.getAnecdotario_solucion());
            statement.setString("panecdotario_estado", Anecdotario.getAnecdotario_estado()); 
            statement.executeUpdate();           
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }
    
    public void EditarAnecdotarioEstado(String estado,String idAnecdotario,Connection con)
    {
        PreparedStatement ps;
        String sqlQuery = "update mps_anecdotario set anecdotario_estado = ? where anecdotario_id = ?";
        try 
        {
            ps = con.prepareStatement(sqlQuery);
            ps.setString(1, estado);
            ps.setString(2, idAnecdotario);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClsAnecdotario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void EliminarAnecdotario(String codigo,Connection con)
    {
        try
        {
            CallableStatement statement = con.prepareCall("{call sp_D_Anecdotario(?)}");
            statement.setString("panecdotario_id", codigo);            
            statement.executeUpdate();           
        }catch(SQLException ex){
            ex.printStackTrace();
        }  
    }
    
    public ArrayList<ClsEntidadAnecdotario> SeleccionarAnecdotario(String codigo,Connection con)
    {
        PreparedStatement pstmt;
        ResultSet rs;
        String sqlQuery= "SELECT a.anecdotario_id, a.anecdotario_fecha,a.anecdotario_anecdota, a.anecdotario_estado\n" +
            "FROM mps_anecdotario AS a\n" +
            "WHERE a.id_alumno = ? " + 
            "ORDER BY a.anecdotario_fecha DESC";
        ArrayList<ClsEntidadAnecdotario> Anecdotario = new ArrayList<>();
     
        try {
            pstmt = con.prepareStatement(sqlQuery);
        
            pstmt.setString(1, codigo);           
            rs = pstmt.executeQuery(); 
            
            while (rs.next()) {
                
                ClsEntidadAnecdotario anecdotario = new ClsEntidadAnecdotario (
                        rs.getString("anecdotario_id"),
                        rs.getString("anecdotario_anecdota"),
                        rs.getString("anecdotario_estado"),
                        rs.getString("anecdotario_fecha")
                );
                
                Anecdotario.add(anecdotario);
            }           
            return Anecdotario;
       } catch (SQLException ex) {
            Logger.getLogger(ClsAnecdotario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList<ClsEntidadAlumnoAnecdotario> SeleccionarAnecdotarioUltimo (
            String condicion,
            String fecha,
            Connection con
        )
    {
        PreparedStatement pstmt;
        ResultSet rs;
        String sqlQuery="";
        
        switch (condicion) {
            case "pendiente":
                sqlQuery = "SELECT m.id_matricula, a.id_alumno, al.nombre_alumno, al.apellidop_alumno, al.apellidom_alumno,\n" +
                        "a.anecdotario_fecha, a.anecdotario_estado, s.nombre_seccion, a.anecdotario_id, a.anecdotario_solucion, m.seccion_id \n" +
                        "FROM mps_anecdotario AS a INNER JOIN mat_alumno AS al ON a.id_alumno = al.id_alumno\n" +
                        "INNER JOIN mat_matricula AS m ON m.MPS_Alumno_id_alumno = al.id_alumno\n" +
                        "INNER JOIN mat_seccion AS s ON s.id_seccion = m.seccion_id\n" +
                        "WHERE a.anecdotario_estado = 'PENDIENTE' AND a.anecdotario_fecha = ?" +
                        "ORDER BY a.anecdotario_fecha DESC ";
                break;
            case "revisado":
                sqlQuery = "SELECT m.id_matricula, a.id_alumno, al.nombre_alumno, al.apellidop_alumno, al.apellidom_alumno,\n" +
                        "a.anecdotario_fecha, a.anecdotario_estado, s.nombre_seccion, a.anecdotario_id, a.anecdotario_solucion, m.seccion_id \n" +
                        "FROM mps_anecdotario AS a INNER JOIN mat_alumno AS al ON a.id_alumno = al.id_alumno\n" +
                        "INNER JOIN mat_matricula AS m ON m.MPS_Alumno_id_alumno = al.id_alumno\n" +
                        "INNER JOIN mat_seccion AS s ON s.id_seccion = m.seccion_id\n" +
                        "WHERE a.anecdotario_estado = 'REVISADO' AND a.anecdotario_fecha = ?" +
                        "ORDER BY a.anecdotario_fecha DESC ";
                break;
            case "finalizado":
                sqlQuery = "SELECT m.id_matricula, a.id_alumno, al.nombre_alumno, al.apellidop_alumno, al.apellidom_alumno,\n" +
                        "a.anecdotario_fecha, a.anecdotario_estado, s.nombre_seccion, a.anecdotario_id, a.anecdotario_solucion, m.seccion_id \n" +
                        "FROM mps_anecdotario AS a INNER JOIN mat_alumno AS al ON a.id_alumno = al.id_alumno\n" +
                        "INNER JOIN mat_matricula AS m ON m.MPS_Alumno_id_alumno = al.id_alumno\n" +
                        "INNER JOIN mat_seccion AS s ON s.id_seccion = m.seccion_id\n" +
                        "WHERE a.anecdotario_estado = 'FINALIZADO' AND a.anecdotario_fecha = ?" +
                        "ORDER BY a.anecdotario_fecha DESC ";
                break;
            case "todo":
            sqlQuery = "SELECT m.id_matricula, a.id_alumno, al.nombre_alumno, al.apellidop_alumno, al.apellidom_alumno,\n" +
                    "a.anecdotario_fecha, a.anecdotario_estado, s.nombre_seccion, a.anecdotario_id, a.anecdotario_solucion, m.seccion_id \n" +
                    "FROM mps_anecdotario AS a INNER JOIN mat_alumno AS al ON a.id_alumno = al.id_alumno\n" +
                    "INNER JOIN mat_matricula AS m ON m.MPS_Alumno_id_alumno = al.id_alumno\n" +
                    "INNER JOIN mat_seccion AS s ON s.id_seccion = m.seccion_id\n" +
                    "WHERE a.anecdotario_fecha = ?" +
                    "ORDER BY a.anecdotario_fecha DESC ";
            break;
            default:
                break;
        }
        ArrayList<ClsEntidadAlumnoAnecdotario> Anecdotario = new ArrayList<>();
            
        try {
            pstmt = con.prepareStatement(sqlQuery);
            pstmt.setString(1, fecha);            
            rs = pstmt.executeQuery(); 
            while (rs.next())
            {
                ClsEntidadAlumnoAnecdotario anecdotario = new ClsEntidadAlumnoAnecdotario (
                        rs.getString("anecdotario_id"),
                        rs.getString("anecdotario_solucion"), 
                        rs.getString("anecdotario_estado"), 
                        rs.getString("anecdotario_fecha"), 
                        rs.getString("id_alumno"), 
                        rs.getString("nombre_alumno"), 
                        rs.getString("apellidop_alumno"), 
                        rs.getString("apellidom_alumno"), 
                        rs.getString("id_matricula"), 
                        rs.getString("seccion_id"), 
                        rs.getString("nombre_seccion")
                );
                
                Anecdotario.add(anecdotario);
            }           
            return Anecdotario; 
        } catch (SQLException ex) {
            Logger.getLogger(ClsAnecdotario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    public ArrayList<ClsEntidadSeccion> ListarSeccionProfesor(String personalID,Connection con)
    {
        ArrayList<ClsEntidadSeccion> Secciones = new ArrayList<ClsEntidadSeccion>();
        try
        {
            CallableStatement statement = con.prepareCall("{call sp_L_SeccionProfesor(?)}");
            statement.setString("ppersonal_id", personalID); 
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                ClsEntidadSeccion seccion = new ClsEntidadSeccion();
                seccion.setId_Seccion(rs.getString("id_seccion"));
                seccion.setNombre_Periodo(rs.getString("nombre_periodo"));
                seccion.setEstado_Periodo(rs.getString("estado_periodo")); 
                seccion.setId_Periodo(rs.getString("id_periodo"));
                seccion.setNombre_Seccion(rs.getString("nombre_seccion"));
                seccion.setCantidad_Seccion(rs.getString("cantidad_seccion"));
                seccion.setAnio_Periodo(rs.getString("anio_periodo"));
                seccion.setNombre_Profesora(rs.getString("profesora"));
                seccion.setId_Profesor(rs.getString("personal_id"));
                seccion.setNombre_Auxiliar1(rs.getString("auxiliar1"));
                seccion.setId_Auxiliar1(rs.getString("id_aux1"));
                seccion.setNombre_Auxiliar2(rs.getString("auxiliar2"));
                seccion.setId_Auxiliar2(rs.getString("id_aux2"));                
                Secciones.add(seccion);                
            }            
            return Secciones;
        } 
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    
    
}
