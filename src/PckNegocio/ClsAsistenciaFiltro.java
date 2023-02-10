/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckNegocio;

import PckConexion.ClsConexion;
import PckEntidad.ClsEntidadAsistenciaFiltro;
import PckEntidad.ClsEntidadInasistenciaFicha;
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
 */
public class ClsAsistenciaFiltro {

    private final Connection con = new ClsConexion().getConection();
    
    
    public ResultSet ListarAsistenciaFiltroAlumnoRS(String codigo,String fechai,
            String fechaf,String asiste,String tarde,String falta){
        
      try{
            CallableStatement statement = con.prepareCall("{call sp_S_AsistenciaFiltroAlumno(?,?,?,?,?,?)}");
            statement.setString("codigo", codigo);
            statement.setString("fechai", fechai); 
            statement.setString("fechaf", fechaf); 
            statement.setString("asiste", asiste); 
            statement.setString("tarde", tarde); 
            statement.setString("falta", falta); 
            
            ResultSet rs = statement.executeQuery();
                      
            return rs;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    
    public ArrayList<ClsEntidadAsistenciaFiltro> ListarAsistenciaFiltroAlumno(String codigo,String fechai,
            String fechaf,String asiste,String tarde,String falta){
        ArrayList<ClsEntidadAsistenciaFiltro> Asistencia = new ArrayList<ClsEntidadAsistenciaFiltro>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_S_AsistenciaFiltroAlumno(?,?,?,?,?,?)}");
            statement.setString("codigo", codigo);
            statement.setString("fechai", fechai); 
            statement.setString("fechaf", fechaf); 
            statement.setString("asiste", asiste); 
            statement.setString("tarde", tarde); 
            statement.setString("falta", falta); 
            
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadAsistenciaFiltro asistencia = new ClsEntidadAsistenciaFiltro();
                asistencia.setAsistencia_Id(rs.getString("asistencia_id"));
                asistencia.setAlumno_Id(rs.getString("asistencia_alumno_id"));
                asistencia.setAsistencia_Fecha(rs.getString("asistencia_fecha"));
                asistencia.setAsistencia_Hora(rs.getString("asistencia_hora"));
                asistencia.setAsistencia_Asistencia(rs.getString("asistencia_asistencia"));                 
                asistencia.setSeccion_Id(rs.getString("id_seccion")); 
                asistencia.setSeccion_Nombre(rs.getString("nombre_seccion")); 
                asistencia.setAlumno_Nombre(rs.getString("apellidos_nombres")); 
                Asistencia.add(asistencia);            }            
            return Asistencia;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<ClsEntidadAsistenciaFiltro> ListarAsistenciaFiltroSeccion(String codigo,String fechai,
            String fechaf,String asiste,String tarde,String falta){
        ArrayList<ClsEntidadAsistenciaFiltro> Asistencia = new ArrayList<ClsEntidadAsistenciaFiltro>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_S_AsistenciaFiltroSeccion(?,?,?,?,?,?)}");
            statement.setString("codigo", codigo);
            statement.setString("fechai", fechai); 
            statement.setString("fechaf", fechaf); 
            statement.setString("asiste", asiste); 
            statement.setString("tarde", tarde); 
            statement.setString("falta", falta); 
            
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadAsistenciaFiltro asistencia = new ClsEntidadAsistenciaFiltro();
                asistencia.setAsistencia_Id(rs.getString("asistencia_id"));
                asistencia.setAlumno_Id(rs.getString("asistencia_alumno_id"));
                asistencia.setAsistencia_Fecha(rs.getString("asistencia_fecha"));
                asistencia.setAsistencia_Hora(rs.getString("asistencia_hora"));
                asistencia.setAsistencia_Asistencia(rs.getString("asistencia_asistencia"));                 
                asistencia.setSeccion_Id(rs.getString("id_seccion")); 
                asistencia.setSeccion_Nombre(rs.getString("nombre_seccion")); 
                asistencia.setAlumno_Nombre(rs.getString("apellidos_nombres")); 
                asistencia.setAlumno_Comida(rs.getString("comida_alumno"));
                Asistencia.add(asistencia);            
            }            
            return Asistencia;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<ClsEntidadAsistenciaFiltro> ListarFaltaPeriodo(String periodo, String fecha)
    {
        ArrayList<ClsEntidadAsistenciaFiltro> Asistencia = new ArrayList<>();
        ResultSet rs;
        try
        {
            PreparedStatement st = con.prepareStatement("select concat(alu.apellidop_alumno,' ',alu.apellidom_alumno, ', ',alu.nombre_alumno) as 'apellidos_nombres',"
                    + " a.asistencia_asistencia, s.nombre_seccion,a.asistencia_alumno_id from mat_asistencia as a"
                    + " inner join mat_matricula as m on a.asistencia_alumno_id = m.MPS_Alumno_id_alumno"
                    + " inner join mat_seccion as s on s.id_seccion = m.seccion_id"
                    + " inner join mat_alumno as alu on alu.id_alumno = m.MPS_Alumno_id_alumno"
                    + " inner join mat_periodo as p on p.id_periodo = s.MPS_Periodo_id_periodo"
                    + " where p.id_periodo = (?) and a.asistencia_fecha = (STR_TO_DATE(REPLACE((?),'/','.') ,GET_FORMAT(date,'EUR')))"
                    + " and a.asistencia_asistencia = 'F'"
                    + "order by s.nombre_seccion, alu.apellidop_alumno;");
            st.setString(1, periodo);
            st.setString(2, fecha);
            rs = st.executeQuery();
            
            while(rs.next())
            {
                ClsEntidadAsistenciaFiltro asistencia = new ClsEntidadAsistenciaFiltro();  
                asistencia.setAlumno_Id(rs.getString("asistencia_alumno_id"));
                asistencia.setAsistencia_Asistencia(rs.getString("asistencia_asistencia"));                 
                asistencia.setSeccion_Nombre(rs.getString("nombre_seccion")); 
                asistencia.setAlumno_Nombre(rs.getString("apellidos_nombres")); 
                Asistencia.add(asistencia);   
            }
            return Asistencia;
        }        
        catch(SQLException ex)
        {
            return null;
        }
        
    }
    
    public ArrayList<ClsEntidadAsistenciaFiltro> ListarFaltaAula(String aula, String fecha)
    {
        ArrayList<ClsEntidadAsistenciaFiltro> Asistencia = new ArrayList<ClsEntidadAsistenciaFiltro>();
        ResultSet rs;
        try
        {
            PreparedStatement st = con.prepareStatement("select concat(alu.apellidop_alumno,' ',alu.apellidom_alumno, ', ',alu.nombre_alumno) as 'apellidos_nombres',"
                    + " a.asistencia_asistencia, s.nombre_seccion, a.asistencia_alumno_id from mat_asistencia as a"
                    + " inner join mat_matricula as m on a.asistencia_alumno_id = m.MPS_Alumno_id_alumno"
                    + " inner join mat_seccion as s on s.id_seccion = m.seccion_id"
                    + " inner join mat_alumno as alu on alu.id_alumno = m.MPS_Alumno_id_alumno"
                    + " inner join mat_periodo as p on p.id_periodo = s.MPS_Periodo_id_periodo"
                    + " where s.id_seccion = (?) and a.asistencia_fecha = (STR_TO_DATE(REPLACE((?),'/','.') ,GET_FORMAT(date,'EUR')))"
                    + " and a.asistencia_asistencia = 'F'"
                    + "order by s.nombre_seccion, alu.apellidop_alumno;");
            st.setString(1, aula);
            st.setString(2, fecha);
            rs = st.executeQuery();
            
            while(rs.next())
            {
                ClsEntidadAsistenciaFiltro asistencia = new ClsEntidadAsistenciaFiltro();  
                asistencia.setAlumno_Id(rs.getString("asistencia_alumno_id"));
                asistencia.setAsistencia_Asistencia(rs.getString("asistencia_asistencia"));                 
                asistencia.setSeccion_Nombre(rs.getString("nombre_seccion")); 
                asistencia.setAlumno_Nombre(rs.getString("apellidos_nombres")); 
                Asistencia.add(asistencia);   
            }
            return Asistencia;
        }        
        catch(SQLException ex)
        {
            return null;
        }
        
    }
    
    public ResultSet ComprobarFicha (String xIdAlumno,String xFechaFicha)
    {
       
      try{
            PreparedStatement st = con.prepareStatement("SELECT 'SI' as 'ficha', i.mat_alumno_id \n" +
                "FROM mat_inasistencia_ficha as i\n" +
                "where i.mat_alumno_id = (?) and "  + 
                "i.inasistencia_ficha_fecha = (STR_TO_DATE(REPLACE((?),'/','.') ,GET_FORMAT(date,'EUR')))");
            st.setString(1, xIdAlumno);
            st.setString(2, xFechaFicha);            
            
            ResultSet rs = st.executeQuery();
                      
            return rs;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public ResultSet ListarFichaDatos (String xIdAlumno,String xFechaFicha)
    {
       
      try{
            PreparedStatement st = con.prepareStatement("select DATE_FORMAT(i.inasistencia_ficha_fecha, '%d/%m/%Y') as 'ficha_fecha',"
                    + " i.inasistencia_ficha_telefono,\n" 
                    + "i.inasistencia_ficha_contacto,DATE_FORMAT(i.inasistencia_ficha_retorno, '%d/%m/%Y') as 'fecha_retorno',\n" 
                    + "i.inasistencia_ficha_motivo, s.nombre_seccion, i.inasistencia_ficha_id\n" 
                    + "from mat_inasistencia_ficha as i inner join mat_alumno as a on i.mat_alumno_id = a.id_alumno\n" 
                    + "inner join mat_seccion as s on i.mat_seccion_id = s.id_seccion\n" 
                    + "where i.mat_alumno_id = (?) and "
                    + "i.inasistencia_ficha_fecha = (STR_TO_DATE(REPLACE((?),'/','.') ,GET_FORMAT(date,'EUR')))");
            st.setString(1, xIdAlumno);
            st.setString(2, xFechaFicha);            
            
            ResultSet rs = st.executeQuery();
                      
            return rs;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public void InsertarInasistenciaFicha(ClsEntidadInasistenciaFicha xFicha) {
        
        try 
        {
            //xConexion.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement("insert into mat_inasistencia_ficha \n" 
                    + "(inasistencia_ficha_fecha,"
                    + " inasistencia_ficha_justificada,"
                    + " inasistencia_ficha_motivo,"
                    + " inasistencia_ficha_retorno,"
                    + " inasistencia_ficha_contacto,"
                    + " inasistencia_ficha_telefono,"
                    + " mat_alumno_id,mat_seccion_id)\n" 
                    + "values ("
                    + "(STR_TO_DATE(REPLACE(?,'/','.') ,GET_FORMAT(date,'EUR'))),"
                    + "?,?,"
                    + "(STR_TO_DATE(REPLACE(?,'/','.') ,GET_FORMAT(date,'EUR'))),"
                    + "?,?,?,? )");
            st.setString(1, xFicha.getInasistencia_ficha_fecha());
            st.setInt(2, Integer.parseInt(xFicha.getInasistencia_ficha_justificada()));
            st.setString(3, xFicha.getInasistencia_ficha_motivo());
            st.setString(4, xFicha.getInasistencia_ficha_retorno());
            st.setString(5, xFicha.getInasistencia_ficha_contacto());
            st.setString(6, xFicha.getInasistencia_ficha_telefono());
            st.setString(7, xFicha.getMat_alumno_id());
            st.setString(8, xFicha.getMat_seccion_id());
            
            st.executeUpdate();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(ClsEntidadInasistenciaFicha.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void ModificarInasistenciaFicha(ClsEntidadInasistenciaFicha xFicha, String xCod) {
        
        try 
        {
            //xConexion.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement("update mat_inasistencia_ficha set\n" +
                "inasistencia_ficha_fecha = (STR_TO_DATE(REPLACE(?,'/','.') ,GET_FORMAT(date,'EUR'))),\n" +
                "inasistencia_ficha_justificada = ?,\n" +
                "inasistencia_ficha_motivo = ?,\n" +
                "inasistencia_ficha_retorno = (STR_TO_DATE(REPLACE(?,'/','.') ,GET_FORMAT(date,'EUR'))),\n" +
                "inasistencia_ficha_contacto = ?,\n" +
                "inasistencia_ficha_telefono = ?,\n" +
                "mat_alumno_id = ?,\n" +
                "mat_seccion_id = ?\n" +
                "where\n" +
                "inasistencia_ficha_id = ?");
            st.setString(1, xFicha.getInasistencia_ficha_fecha());
            st.setInt(2, Integer.parseInt(xFicha.getInasistencia_ficha_justificada()));
            st.setString(3, xFicha.getInasistencia_ficha_motivo());
            st.setString(4, xFicha.getInasistencia_ficha_retorno());
            st.setString(5, xFicha.getInasistencia_ficha_contacto());
            st.setString(6, xFicha.getInasistencia_ficha_telefono());
            st.setString(7, xFicha.getMat_alumno_id());
            st.setString(8, xFicha.getMat_seccion_id());
            st.setString(9, xCod);            
            st.executeUpdate();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(ClsEntidadInasistenciaFicha.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
