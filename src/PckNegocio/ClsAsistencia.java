/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckNegocio;

import PckEntidad.AlumnoFichaInasistencia;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import PckEntidad.ClsEntidadAsistencia;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 */
public class ClsAsistencia {

    public void InsertarAsistencia(ClsEntidadAsistencia asistencia,Connection con)
    {
        PreparedStatement stmt;
        String sql = "insert into mat_asistencia (asistencia_alumno_id,asistencia_fecha, asistencia_hora)"
                + " VALUES (?,?,?)";
        try 
        {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, asistencia.getAsistencia_Alumno_Id());
            stmt.setString(2, asistencia.getAsistencia_Fecha());
            stmt.setString(3, asistencia.getAsistencia_Hora());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClsAsistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void BorrarAsistencia(ClsEntidadAsistencia asistencia,Connection con)
    {
        PreparedStatement stmt;
        String sql = "delete from mat_asistencia where asistencia_alumno_id = ? and asistencia_fecha = ?";
        try
        {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, asistencia.getAsistencia_Alumno_Id());
            stmt.setString(2, asistencia.getAsistencia_Fecha());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClsAsistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void AgregarAsistencia(ClsEntidadAsistencia Asistencia, Connection con)
    {
            try{
                CallableStatement statement = con.prepareCall("{call sp_I_Asistencia(?,?,?,?)}");
                statement.setString("pasistencia_alumno_id", Asistencia.getAsistencia_Alumno_Id());               
                statement.setString("pasistencia_fecha", Asistencia.getAsistencia_Fecha());
                statement.setString("pasistencia_hora", Asistencia.getAsistencia_Hora());
                //statement.setString("pasistencia_asistencia", Asistencia.getAsistencia_Asistencia()); 
                statement.execute();
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }    
    }

    public void ModificarAsistencia(ClsEntidadAsistencia Asistencia,String codigo, Connection con){
            try{
                CallableStatement statement = con.prepareCall("{call sp_U_Asistencia(?,?,?,?)}");
                statement.setString("pasistencia_id", codigo);               
                statement.setString("pasistencia_fecha", Asistencia.getAsistencia_Fecha());
                statement.setString("pasistencia_hora", Asistencia.getAsistencia_Hora());
                //statement.setString("pasistencia_asistencia", Asistencia.getAsistencia_Asistencia()); 
                statement.execute();
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }    
    }
    
     public ResultSet UltimoIdInsertado(Connection con)  throws Exception{
        ResultSet rs = null;
        try{
            Statement statement = con.createStatement();
            rs = statement.executeQuery("select last_insert_id() as 'id_insertado';");   
            
            return rs;
            
        }catch(SQLException ex){
            throw ex;
        }
    }
   
     
     
     public ArrayList<ClsEntidadAsistencia> ListarAsistencia(String salon,String fecha, Connection con){
        ArrayList<ClsEntidadAsistencia> Asistencia = new ArrayList<ClsEntidadAsistencia>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_S_Asistencia(?,?)}");
            statement.setString("salon_id", salon);
            statement.setString("fecha", fecha); 
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadAsistencia asistencia = new ClsEntidadAsistencia();
                asistencia.setAsistencia_Id(rs.getString("asistencia_id"));
                asistencia.setAsistencia_Alumno_Id(rs.getString("asistencia_alumno_id"));
                asistencia.setAsistencia_Fecha(rs.getString("asistencia_fecha"));
                asistencia.setAsistencia_Hora(rs.getString("asistencia_hora"));
                //asistencia.setAsistencia_Asistencia(rs.getString("asistencia_asistencia"));                 
                Asistencia.add(asistencia);
            }            
            return Asistencia;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
     
     public ArrayList<ClsEntidadAsistencia> ListarAsistenciaAula(String idAula, String fecha, Connection con)
     {
        ArrayList<ClsEntidadAsistencia> arrayAsistencia = new ArrayList<>();
        PreparedStatement pstmt;
        ResultSet rs;
        String sqlQuery = "select alu.id_alumno,m.id_matricula, concat(alu.apellidop_alumno,\" \",alu.apellidom_alumno, \", \",alu.nombre_alumno) as 'apellidos_nombres',\n" +
                    "s.nombre_seccion, a.asistencia_fecha\n" +
                    "from mat_asistencia as a \n" +
                    "inner join mat_alumno as alu on alu.id_alumno = a.asistencia_alumno_id\n" +
                    "inner join mat_matricula as m on m.MPS_Alumno_id_alumno = alu.id_alumno\n" +
                    "inner join mat_seccion as s on s.id_seccion = m.seccion_id\n" +
                    "where m.retiro = 'NO' AND s.id_seccion = ? AND a.asistencia_fecha = ?";
        
        try 
        {
            pstmt = con.prepareStatement(sqlQuery);
            pstmt.setString(1, idAula);
            pstmt.setString(2, fecha);
            rs = pstmt.executeQuery(); 
            while(rs.next())
            {
                ClsEntidadAsistencia asistencia = new ClsEntidadAsistencia
                (
                        rs.getString("id_alumno"),
                        rs.getString("asistencia_fecha"),
                        rs.getString("apellidos_nombres")
                );
                arrayAsistencia.add(asistencia);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ClsAsistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arrayAsistencia;
     }
     
     public boolean ComprobarAsistenciaAlumno(String idAlumno,String fechaAsistencia,Connection con) {
        boolean asistio = false; 
        PreparedStatement pstmt;
        ResultSet rs;
        String sqlQuery = "SELECT a.asistencia_id, a.asistencia_alumno_id, a.asistencia_fecha, a.asistencia_hora\n" +
                        "FROM mat_asistencia as a\n" +
                        "where a.asistencia_alumno_id = ? AND a.asistencia_fecha = ?";
        
        try {
            
            pstmt = con.prepareStatement(sqlQuery);
            pstmt.setString(1, idAlumno);
            pstmt.setString(2, fechaAsistencia);
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
                asistio = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClsAsistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return asistio;
     }
     
     public boolean ComprobarAsistenciaAlumnoAlimentacion(String idAlumno,String fechaAsistencia,Connection con) {
        
        boolean conLonchera = false; 
        PreparedStatement pstmt;
        ResultSet rs;
        String sqlQuery = "SELECT a.asistencia_id, a.asistencia_alumno_id, a.asistencia_fecha, a.asistencia_hora,\n" +
            "m.comida_alumno\n" +
            "FROM mat_asistencia AS a INNER JOIN mat_alumno AS alu ON a.asistencia_alumno_id = alu.id_alumno\n" +
            "INNER JOIN mat_matricula as m on alu.id_alumno = m.MPS_Alumno_id_alumno\n" +
            "WHERE a.asistencia_alumno_id = ? AND a.asistencia_fecha = ?";
        
        try {
            
            pstmt = con.prepareStatement(sqlQuery);
            pstmt.setString(1, idAlumno);
            pstmt.setString(2, fechaAsistencia);
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
                
                if(rs.getString("comida_alumno").equals("SI")) {
                    conLonchera = true;
                }
                else {
                    conLonchera = false;
                }
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClsAsistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return conLonchera;
     }
     
     public ArrayList<AlumnoFichaInasistencia> ListarAsistenciaAulaBasico(String idAula, String fecha, Connection con)
     {
        ArrayList<AlumnoFichaInasistencia> arrayAsistencia = new ArrayList<>();
        PreparedStatement pstmt;
        ResultSet rs;
        String sqlQuery = "select alu.id_alumno,m.id_matricula, concat(alu.apellidop_alumno,\" \",alu.apellidom_alumno, \", \",alu.nombre_alumno) as 'apellidos_nombres',\n" +
                    "s.nombre_seccion, a.asistencia_fecha\n" +
                    "from mat_asistencia as a \n" +
                    "inner join mat_alumno as alu on alu.id_alumno = a.asistencia_alumno_id\n" +
                    "inner join mat_matricula as m on m.MPS_Alumno_id_alumno = alu.id_alumno\n" +
                    "inner join mat_seccion as s on s.id_seccion = m.seccion_id\n" +
                    "where m.retiro = 'NO' AND s.id_seccion = ? AND a.asistencia_fecha = ?";
        
        try 
        {
            pstmt = con.prepareStatement(sqlQuery);
            pstmt.setString(1, idAula);
            pstmt.setString(2, fecha);
            rs = pstmt.executeQuery(); 
            while(rs.next())
            {
                AlumnoFichaInasistencia asistencia = new AlumnoFichaInasistencia
                (
                        rs.getString("id_alumno"),                        
                        rs.getString("apellidos_nombres")
                );
                arrayAsistencia.add(asistencia);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ClsAsistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arrayAsistencia;
     }
     
     public ArrayList<AlumnoFichaInasistencia> ListarAsistenciaPeriodoBasico(String idPeriodo, String fecha, Connection con)
     {
        ArrayList<AlumnoFichaInasistencia> arrayAsistencia = new ArrayList<>();
        PreparedStatement pstmt;
        ResultSet rs;
        String sqlQuery = "select alu.id_alumno,m.id_matricula, concat(alu.apellidop_alumno,\" \",alu.apellidom_alumno, \", \",alu.nombre_alumno) as 'apellidos_nombres',\n" +
                    "s.nombre_seccion, a.asistencia_fecha\n" +
                    "from mat_asistencia as a \n" +
                    "inner join mat_alumno as alu on alu.id_alumno = a.asistencia_alumno_id\n" +
                    "inner join mat_matricula as m on m.MPS_Alumno_id_alumno = alu.id_alumno\n" +
                    "inner join mat_seccion as s on s.id_seccion = m.seccion_id\n" +
                    "where m.retiro = 'NO' AND s.MPS_Periodo_id_periodo = ? AND a.asistencia_fecha = ?";
        
        try 
        {
            pstmt = con.prepareStatement(sqlQuery);
            pstmt.setString(1, idPeriodo);
            pstmt.setString(2, fecha);
            rs = pstmt.executeQuery(); 
            while(rs.next())
            {
                AlumnoFichaInasistencia asistencia = new AlumnoFichaInasistencia
                (
                        rs.getString("id_alumno"),                        
                        rs.getString("apellidos_nombres")
                );
                arrayAsistencia.add(asistencia);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ClsAsistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arrayAsistencia;
     }
     
    public boolean ListarSeccionTomada(Connection con,String codigo,String fecha)  throws Exception{
        ResultSet rs = null;
        int cont=0;
        try
        {
            CallableStatement st = con.prepareCall("{call sp_S_AsistenciaSeccion(?,?)}");            
            st.setString("codigo", codigo);
            st.setString("fecha", fecha);
            rs = st.executeQuery();            
            while(rs.next())
            {
                cont++;
            }
            System.out.println(cont);
            return cont>0;
        }
        catch(SQLException ex)
        {
            throw ex;
        }
    } 
     
    public boolean VerificarAlumnoAsistencia(String idAlumno,String fecha, Connection con) {
        boolean asistio = false;
        String sqlQuery = "SELECT asistencia_id FROM mat_asistencia "
                        + "WHERE asistencia_alumno_id = ? AND asistencia_fecha = ?";
        ResultSet rs = null;
        PreparedStatement pst = null;
        
        try
        {
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, idAlumno);
            pst.setString(2, fecha);
            rs = pst.executeQuery();
                       
            while(rs.next())
            {
               asistio = true;
            }
        }
        catch(SQLException ex){
            Logger.getLogger(ClsAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex){
            Logger.getLogger(ClsAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return asistio;
    }
    
}
