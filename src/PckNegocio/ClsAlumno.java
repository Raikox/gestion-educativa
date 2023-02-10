package PckNegocio;


import PckEntidad.*;
import java.awt.Image;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mpsEntity.AlumnoCumpleEntity;

public class ClsAlumno {
    
    
    
    public void AgregarAlumno(ClsEntidadAlumno Alumno,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_I_Alumno(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            statement.setString("pnombre_alumno", Alumno.getNombre_Alumno());
            statement.setString("papellidop_alumno", Alumno.getApellidoP_Alumno());
            statement.setString("papellidom_alumno", Alumno.getApellidoM_Alumno());
            statement.setString("pfechan_alumno", Alumno.getFechaN_Alumno());
            statement.setString("plugarn_alumno", Alumno.getLugarN_Alumno());
            statement.setString("pdomicilio_alumno", Alumno.getDomicilio_Alumno());
            statement.setString("ptelefono_alumno", Alumno.getTelefono_Alumno());
            statement.setString("pdni_alumno", Alumno.getDni_Alumno());            
            statement.setString("pedadh_alumno", Alumno.getEdadH_Alumno());
            statement.setString("plugarh_alumno", Alumno.getLugarH_Alumno());
            statement.setString("preligion_alumno", Alumno.getReligion_Alumno());           
            statement.setString("pMPS_Apoderado_id_apoderado", Alumno.getId_Apoderado());
            statement.setString("ppadre_id", Alumno.getId_Padre());
            statement.setString("pmadre_id", Alumno.getId_Madre());
            statement.execute();
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }
    
     public void ModificarAlumno(String codigo,Connection con,ClsEntidadAlumno Alumno){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_Alumno(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            statement.setString("pid_alumno", codigo);
            statement.setString("pnombre_alumno", Alumno.getNombre_Alumno());
            statement.setString("papellidop_alumno", Alumno.getApellidoP_Alumno());
            statement.setString("papellidom_alumno", Alumno.getApellidoM_Alumno());
            statement.setString("pfechan_alumno", Alumno.getFechaN_Alumno());
            statement.setString("plugarn_alumno", Alumno.getLugarN_Alumno());
            statement.setString("pdomicilio_alumno", Alumno.getDomicilio_Alumno());
            statement.setString("ptelefono_alumno", Alumno.getTelefono_Alumno());
            statement.setString("pdni_alumno", Alumno.getDni_Alumno());            
            statement.setString("pedadh_alumno", Alumno.getEdadH_Alumno());
            statement.setString("plugarh_alumno", Alumno.getLugarH_Alumno());
            statement.setString("preligion_alumno", Alumno.getReligion_Alumno());          
            statement.setString("pMPS_Apoderado_id_apoderado", Alumno.getId_Apoderado());
            statement.execute();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
     public void EliminarAlumno(String codigo,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_D_Alumno(?)}");
            statement.setString("pid_alumno",codigo);
            statement.execute();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
     
     public ArrayList<ClsEntidadAlumno> ListarAlumno(Connection con){
        ArrayList<ClsEntidadAlumno> Alumnos = new ArrayList<ClsEntidadAlumno>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_L_Alumno}");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadAlumno alumno = new ClsEntidadAlumno();
                alumno.setId_Alumno(rs.getString("id_alumno"));
                alumno.setNombre_Alumno(rs.getString("nombre_alumno"));
                alumno.setApellidoP_Alumno(rs.getString("apellidop_alumno"));
                alumno.setApellidoM_Alumno(rs.getString("apellidom_alumno"));
                alumno.setFechaN_Alumno(rs.getString("fechan_alumno"));
                alumno.setLugarN_Alumno(rs.getString("lugarn_alumno"));
                alumno.setDomicilio_Alumno(rs.getString("domicilio_alumno"));
                alumno.setTelefono_Alumno(rs.getString("telefono_alumno"));
                alumno.setDni_Alumno(rs.getString("dni_alumno"));
                alumno.setHermanos_Alumno(rs.getString("hemanos_alumno"));
                alumno.setEdadH_Alumno(rs.getString("edadh_alumno"));
                alumno.setLugarH_Alumno(rs.getString("lugarh_alumno"));
                alumno.setReligion_Alumno(rs.getString("religion_alumno"));
                alumno.setFoto_Alumno((Image) rs.getBlob("foto_alumno"));
                alumno.setId_Apoderado(rs.getString("MPS_Apoderado_id_apoderado"));
                Alumnos.add(alumno);
            }
            return Alumnos;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
     
     public ResultSet SeleccionarAlumno(String codigo,Connection con)  throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_Alumno(?)}");
            statement.setString("pid_alumno", codigo);
            rs = statement.executeQuery();
            return rs;
        }catch(SQLException ex){
            throw ex;
        }
    }
     public String UltimoIdInsertado(Connection con)  throws Exception{
        ResultSet rs;
        String cod="";
        try{
            Statement statement = con.createStatement();
            rs = statement.executeQuery("select last_insert_id() as 'id_insertado';"); 
            while(rs.next()){
                cod = rs.getString("id_insertado");
            }
            return cod;            
        }catch(SQLException ex){
            throw ex;
        }
    }
    
    ///////////////PAGO/////////////////
    
    //método que se utilizará en las tablas a usar en pagos 
    public ArrayList<ClsEntidadAlumnoPago> ListarAlumnoPago(Connection con)
    {
        ArrayList<ClsEntidadAlumnoPago> Alumnos = new ArrayList<ClsEntidadAlumnoPago>();

        try
        {
            CallableStatement st = con.prepareCall("{call sp_L_AlumnoPago}");
            ResultSet rs = st.executeQuery();
            while (rs.next())
            {
            ClsEntidadAlumnoPago alumno=new ClsEntidadAlumnoPago();
            alumno.setId_Matricula(rs.getString("id_matricula")); 
            alumno.setApellidos_Nombres(rs.getString("apellidos_nombres")); 
            alumno.setId_Seccion(rs.getString("id_seccion"));
            alumno.setNombre_Seccion(rs.getString("nombre_seccion"));
            alumno.setS_Alimentacion(rs.getString("comida_alumno"));
            alumno.setInicio_Clases(rs.getString("inicio_clases"));
            alumno.setHorario_Entrada(rs.getString("horarioe_matricula"));       
            alumno.setHorario_Salida(rs.getString("horarios_matricula"));            
            Alumnos.add(alumno);
            }
            return Alumnos;
            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    
    
    
    //método que se utilizará en las tablas a usar en pagos 
    public ArrayList<ClsEntidadAlumnoPago> ListarAlumnoPagoSeccion(String seccion,Connection con)
    {
        ArrayList<ClsEntidadAlumnoPago> Alumnos = new ArrayList<ClsEntidadAlumnoPago>();

        try
        {
            CallableStatement st = con.prepareCall("{call sp_L_AlumnoPagoSeccion(?)}");
            st.setString("seccion", seccion);
            ResultSet rs = st.executeQuery();
            while (rs.next())
            {
            ClsEntidadAlumnoPago alumno=new ClsEntidadAlumnoPago();
            alumno.setId_Matricula(rs.getString("id_matricula")); 
            alumno.setId_Alumno(rs.getString("id_alumno"));
            alumno.setApellidos_Nombres(rs.getString("apellidos_nombres")); 
            alumno.setId_Seccion(rs.getString("id_seccion"));
            alumno.setNombre_Seccion(rs.getString("nombre_seccion"));
            alumno.setS_Alimentacion(rs.getString("comida_alumno"));
            alumno.setInicio_Clases(rs.getString("inicio_clases"));
            alumno.setHorario_Entrada(rs.getString("horarioe_matricula"));       
            alumno.setHorario_Salida(rs.getString("horarios_matricula"));        
            alumno.setNacimiento(rs.getString("nacimiento"));
            alumno.setEdad(rs.getString("edadanio_matricula")); 
            Alumnos.add(alumno);
            }
            return Alumnos;
        }
        catch(SQLException ex)
        {
            Logger.getLogger(ClsAlumno.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList<AlumnoFichaInasistencia> ListarAlumnoPagoSeccionBasico(String seccion,Connection con)
    {
        ArrayList<AlumnoFichaInasistencia> Alumnos = new ArrayList<>();

        try
        {
            CallableStatement st = con.prepareCall("{call sp_L_AlumnoPagoSeccion(?)}");
            st.setString("seccion", seccion);
            ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                AlumnoFichaInasistencia alumno=new AlumnoFichaInasistencia
                (
                    rs.getString("id_alumno"),
                    rs.getString("apellidos_nombres")  
                );     
                
                Alumnos.add(alumno);
            }
            return Alumnos;
        }
        catch(SQLException ex)
        {
            Logger.getLogger(ClsAlumno.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    
    public ArrayList<AlumnoFichaInasistencia> ListarAlumnoPagoPeriodoBasico(String idPeriodo,Connection con)
    {
        ArrayList<AlumnoFichaInasistencia> Alumnos = new ArrayList<>();
        PreparedStatement pstmt;
        ResultSet rs;
        String sqlQuery = "SELECT concat(alu.apellidop_alumno,' ',alu.apellidom_alumno, ', ',alu.nombre_alumno) as 'apellidos_nombres',\n" +
            "alu.id_alumno, s.nombre_seccion\n" +
            "FROM mat_alumno as alu INNER JOIN mat_matricula as m ON alu.id_alumno = m.MPS_Alumno_id_alumno\n" +
            "INNER JOIN mat_seccion as s on s.id_seccion = m.seccion_id\n" +
            "WHERE s.MPS_Periodo_id_periodo = ? AND m.retiro = 'NO' \n" +
            "ORDER BY s.nombre_seccion, alu.apellidop_alumno";
        try
        {
            pstmt = con.prepareStatement(sqlQuery);            
            pstmt.setString(1, idPeriodo);
            rs = pstmt.executeQuery();
            while (rs.next())
            {
                AlumnoFichaInasistencia alumno=new AlumnoFichaInasistencia
                (
                    rs.getString("id_alumno"),
                    rs.getString("apellidos_nombres"),
                    rs.getString("nombre_seccion")
                );     
                
                Alumnos.add(alumno);
            }
            return Alumnos;
        }
        catch(SQLException ex)
        {
            Logger.getLogger(ClsAlumno.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList<ClsEntidadAlumnoPago> ListarAlumnoPagoPeriodo(String periodo,Connection con)
    {
        ArrayList<ClsEntidadAlumnoPago> Alumnos = new ArrayList<ClsEntidadAlumnoPago>();

        try
        {
            CallableStatement st = con.prepareCall("{call sp_L_AlumnoPagoPeriodo(?)}");
            st.setString("periodo", periodo);
            ResultSet rs = st.executeQuery();
            while (rs.next())
            {
            ClsEntidadAlumnoPago alumno=new ClsEntidadAlumnoPago();
            alumno.setId_Matricula(rs.getString("id_alumno")); 
            alumno.setApellidos_Nombres(rs.getString("apellidos_nombres")); 
            alumno.setId_Seccion(rs.getString("id_seccion"));
            alumno.setNombre_Seccion(rs.getString("nombre_seccion"));
            alumno.setS_Alimentacion(rs.getString("comida_alumno"));
            alumno.setInicio_Clases(rs.getString("inicio_clases"));
            alumno.setHorario_Entrada(rs.getString("horarioe_matricula"));       
            alumno.setHorario_Salida(rs.getString("horarios_matricula"));        
            alumno.setNacimiento(rs.getString("nacimiento"));
            alumno.setEdad(rs.getString("edadanio_matricula")); 
            Alumnos.add(alumno);
            }
            return Alumnos;
        }
        catch(SQLException ex)
        {
            Logger.getLogger(ClsAlumno.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList<ClsEntidadAlumnoPago> ListarAlumnoBusqueda(String busqueda,Connection con)
    {
        ArrayList<ClsEntidadAlumnoPago> Alumnos = new ArrayList<ClsEntidadAlumnoPago>();

        try
        {
            CallableStatement st = con.prepareCall("{call sp_S_AlumnoBusqueda(?)}");
            st.setString("busqueda", busqueda);
            ResultSet rs = st.executeQuery();
            while (rs.next())
            {
            ClsEntidadAlumnoPago alumno=new ClsEntidadAlumnoPago();
            alumno.setId_Matricula(rs.getString("id_matricula")); 
            alumno.setApellidos_Nombres(rs.getString("apellidos_nombres")); 
            alumno.setId_Seccion(rs.getString("id_seccion"));
            alumno.setNombre_Seccion(rs.getString("nombre_seccion"));
            alumno.setS_Alimentacion(rs.getString("comida_alumno"));
            alumno.setInicio_Clases(rs.getString("inicio_clases"));
            alumno.setHorario_Entrada(rs.getString("horarioe_matricula"));       
            alumno.setHorario_Salida(rs.getString("horarios_matricula")); 
            alumno.setNombre_Periodo(rs.getString("nombre_periodo"));
            Alumnos.add(alumno);
            }
            return Alumnos;
            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

        
    public ResultSet SeleccionarAlumnoPago(String codigo,Connection con)  throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_AlumnoPago(?)}");
            statement.setString("pbusqueda", codigo);
            rs = statement.executeQuery();
            return rs;
            
        }catch(SQLException ex){
            throw ex;
        }
    }
    
    public ResultSet SeleccionarAlumnoPeriodo(String codigo,Connection con)  throws Exception
    {
        ResultSet rs = null;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_AlumnoPagoPeriodo(?)}");
            statement.setString("pid_periodo", codigo);
            rs = statement.executeQuery();
            return rs;
            
        }catch(SQLException ex){
            throw ex;
        }
    }
            
    public ResultSet SeleccionarAlumnoSeccion(String codigo,Connection con)  throws Exception
    {
        ResultSet rs = null;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_AlumnoPagoSeccion(?)}");
            statement.setString("pid_seccion", codigo);
            rs = statement.executeQuery();
            return rs;
            
        }catch(SQLException ex){
            throw ex;
        }
    }
      
     ///////////////LISTAR POR  NIVEL, PERIODO, SECCION O ALUMNO////////////////     
      
    public ArrayList<ClsEntidadAsignar> SeleccionarAlumnoPagoNivel(String nivel,Connection con)
    {
        ArrayList<ClsEntidadAsignar> Alumnos = new ArrayList<ClsEntidadAsignar>();
        try
        {
            CallableStatement st = con.prepareCall("{call sp_S_AlumnoPagoNivel(?)}");
            st.setString("pnivel_id", nivel);
            ResultSet rs = st.executeQuery(); 
            while (rs.next())
            {
            ClsEntidadAsignar alumno=new ClsEntidadAsignar();
            alumno.setId_Matricula(rs.getString("id_matricula")); 
            alumno.setApellidos_Nombres(rs.getString("apellidos_nombres"));
            alumno.setId_Seccion(rs.getString("id_seccion"));
            alumno.setNombre_Seccion(rs.getString("nombre_seccion"));
            alumno.setId_Periodo(rs.getString("id_periodo"));
            alumno.setNombre_Periodo(rs.getString("nombre_periodo"));
            alumno.setId_Nivel(rs.getString("nivel_id"));
            alumno.setNombre_Nivel(rs.getString("nivel_nombre"));           
            Alumnos.add(alumno);
            }
            return Alumnos;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<ClsEntidadAsignar> SeleccionarAlumnoPagoPeriodo(String periodo,Connection con)
    {
        ArrayList<ClsEntidadAsignar> Alumnos = new ArrayList<ClsEntidadAsignar>();
        try
        {
            CallableStatement st = con.prepareCall("{call sp_S_AlumnoPagoPeriodo(?)}");
            st.setString("pid_periodo", periodo);
            ResultSet rs = st.executeQuery(); 
            while (rs.next())
            {
            ClsEntidadAsignar alumno=new ClsEntidadAsignar();
            alumno.setId_Matricula(rs.getString("id_matricula")); 
            alumno.setApellidos_Nombres(rs.getString("apellidos_nombres"));
            alumno.setId_Seccion(rs.getString("id_seccion"));
            alumno.setNombre_Seccion(rs.getString("nombre_seccion"));
            alumno.setId_Periodo(rs.getString("id_periodo"));
            alumno.setNombre_Periodo(rs.getString("nombre_periodo"));
            alumno.setId_Nivel(rs.getString("nivel_id"));
            alumno.setNombre_Nivel(rs.getString("nivel_nombre")); 
            Alumnos.add(alumno);
            }
            return Alumnos;            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    
    public ResultSet SeleccionarUsuarioNacimiento(String IdPeriodo, Connection con) {
        
        ResultSet rsResultado = null;
        PreparedStatement pst;
        String sqlQuery = "SELECT  p.personal_id, concat(p.personal_nombre,' ',p.personal_apellido_paterno,' ',p.personal_apellido_materno) AS usuario, p.fecha_nacimiento ,\n" +
                " s.id_seccion, s.nombre_seccion\n" +
                " FROM adm_personal AS p LEFT JOIN mat_seccion AS s ON p.personal_id = s.MPS_Profesor_id_profesor OR p.personal_id = s.MPS_Profesor_id_auxiliar1\n" +
                " OR p.personal_id = s.MPS_Profesor_id_auxiliar2\n" +
                " WHERE p.personal_estado = 'A'\n" +
                " GROUP BY p.personal_id\n" +
                " ORDER BY usuario";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            //pst.setString(1, IdPeriodo);
            rsResultado = pst.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(ClsAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rsResultado;
    }
    
    public ResultSet SeleccionarAlumnoPadreMadreNacimiento(String IdPeriodo, Connection con) {
        
        ResultSet rsResultado = null;
        PreparedStatement pst;
        String sqlQuery = "SELECT m.id_matricula,concat(a.nombre_alumno,' ',a.apellidop_alumno,' ',a.apellidom_alumno) AS 'alumno', a.fechan_alumno, "
                + "concat(ma.madre_nombre,' ',ma.madre_apellido_paterno,' ',ma.madre_apellido_materno) AS 'madre', ma.madre_fecha_nacimiento, "
                + "concat(pa.padre_nombre,' ',pa.padre_apellido_paterno,' ',pa.padre_apellido_materno) AS 'padre', pa.padre_fecha_nacimiento,"
                + "s.nombre_seccion "
                + "FROM mat_matricula AS m INNER JOIN mat_seccion AS s ON m.seccion_id = s.id_seccion "
                + "INNER JOIN mat_alumno AS a ON m.MPS_Alumno_id_alumno = a.id_alumno "
                + "INNER JOIN mat_padre AS pa ON pa.padre_id = a.padre_id "
                + "INNER JOIN mat_madre AS ma ON ma.madre_id = a.madre_id "
                + "INNER JOIN mat_periodo AS p ON p.id_periodo = s.MPS_Periodo_id_periodo "
                + "WHERE p.id_periodo = ? AND m.retiro = 'NO' "
                + "ORDER BY s.nombre_seccion, alumno";
        
        try {
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, IdPeriodo);
            rsResultado = pst.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(ClsAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rsResultado;
    }
    
    public ResultSet SeleccionarAulaPagoPeriodo(String periodo,Connection con)
    {
        ResultSet rsResultado = null;
        PreparedStatement st;
        
        try 
        {
            st = con.prepareStatement("select s.id_seccion, s.nombre_seccion\n" 
                    + "from mat_seccion as s where s.MPS_Periodo_id_periodo = ?");
            st.setString(1, periodo);
            rsResultado = st.executeQuery();
        }
        catch (SQLException ex) 
        {                   
            Logger.getLogger(ClsAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rsResultado;        
        
    }
    
    public ArrayList<ClsEntidadAsignar> SeleccionarAlumnoPagoSeccion(String seccion,Connection con)
    {
        ArrayList<ClsEntidadAsignar> Alumnos = new ArrayList<ClsEntidadAsignar>();
        try
        {
            CallableStatement st = con.prepareCall("{call sp_S_AlumnoPagoSeccion(?)}");
            st.setString("pid_seccion", seccion);
            ResultSet rs = st.executeQuery(); 
            while (rs.next())
            {
            ClsEntidadAsignar alumno=new ClsEntidadAsignar();
            alumno.setId_Matricula(rs.getString("id_matricula")); 
            alumno.setApellidos_Nombres(rs.getString("apellidos_nombres"));
            alumno.setId_Seccion(rs.getString("id_seccion"));
            alumno.setNombre_Seccion(rs.getString("nombre_seccion"));
            alumno.setId_Periodo(rs.getString("id_periodo"));
            alumno.setNombre_Periodo(rs.getString("nombre_periodo"));
            alumno.setId_Nivel(rs.getString("nivel_id"));
            alumno.setNombre_Nivel(rs.getString("nivel_nombre"));  
            Alumnos.add(alumno);
            }
            return Alumnos;            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    
    
    
    public ResultSet SeleccionarAlumnoModificar(String codigo,Connection con)  throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_AlumnoMps(?)}");
            statement.setString("pbusqueda", codigo);            
            rs = statement.executeQuery();
            return rs;
            
        }catch(SQLException ex){
            throw ex;
        }
      }
    
    public ResultSet SeleccionarMatriculaFichaReporte(String codigoAlumno,Connection con)  throws Exception
    {
        ResultSet rs = null;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_MatriculaFichaReporte(?)}");
            statement.setString("pid_matricula", codigoAlumno);
            rs = statement.executeQuery();
            
            
            return rs;
        }catch(SQLException ex){
            throw ex;
        }
    }
    
    public ResultSet ListarAlumnoTelefonos(String xIdAlumno, Connection con)
    {
        ResultSet rs = null;        
        PreparedStatement pst;
        try 
        {
            pst = con.prepareStatement("select concat(a.apellidop_alumno,' ',a.apellidom_alumno,', ', a.nombre_alumno) as 'alumno', a.telefono_alumno, \n" +
            "concat(m.madre_apellido_paterno,' ',m.madre_apellido_materno,', ', m.madre_nombre) as 'madre', m.madre_telefono1,\n" +
            "concat(p.padre_apellido_paterno,' ',p.padre_apellido_materno,', ', p.padre_nombre) as 'padre', p.padre_telefono1\n" +
            "from mat_alumno as a inner join mat_madre as m on a.madre_id = m.madre_id\n" +
            "inner join mat_padre as p on a.padre_id = p.padre_id\n" +
            "where a.id_alumno = ?");
            pst.setString(1, xIdAlumno);
            rs = pst.executeQuery();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(ClsAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return rs;        
    }
    
}
