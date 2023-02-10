package PckNegocio;

import PckConexion.ClsConexion;
import PckEntidad.*;
import java.io.ByteArrayInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClsMatricula {
    Calendar actual = new GregorianCalendar();
    
    
    public void AgregarMatricula(ClsEntidadMatricula Matricula,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_I_Matricula(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            statement.setString("pcod_matricula", Matricula.getCod_Matricula());
            statement.setString("pfecha_matricula", Matricula.getFecha_Matricula());
            statement.setString("pinicio_clases", Matricula.getInicio_Clases());
            statement.setString("pedadanio_matricula", Matricula.getEdadAnio_Matricula());            
            statement.setString("phorarioe_matricula", Matricula.getHorarioe_Matricula());
            statement.setString("phorarios_matricula", Matricula.getHorarios_Matricula());
            statement.setDouble("pmensualidad_matricula", Matricula.getMensualidad_Matricula());
            statement.setString("psexo_alumno", Matricula.getSexo_Alumno());
            statement.setString("pcomida_alumno", Matricula.getComida_Alumno());            
            statement.setString("ppartida_nac", Matricula.getPartida_Nacimiento());
            statement.setString("pdni_padre", Matricula.getDni_Padre());
            statement.setString("pdni_madre", Matricula.getDni_Madre());
            statement.setString("pdni_menor", Matricula.getDni_Menor());
            statement.setString("ptarjeta_vac", Matricula.getTarjeta_Vacunacion());
            statement.setString("pOtro", Matricula.getOtro());
            statement.setString("pretiro", Matricula.getRetiro());
            statement.setString("pMPS_Alumno_id_alumno", Matricula.getId_Alumno());
            statement.setString("pMPS_dclinico_id_clinico", Matricula.getId_Clinico());
            statement.setString("pMPS_emergencia_id_emer", Matricula.getId_Emergencia());
            statement.setString("pseccion_id", Matricula.getId_Seccion());                       
            statement.execute();
            //con.close();
        }catch(SQLException ex){
            Logger.getLogger(ClsMatricula.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    public void ModificarMatriculaSeccion(String id_matricula,String seccion_id,Connection con)
    {
        try
        {
            CallableStatement st = con.prepareCall("{call sp_U_MatriculaSeccion(?,?)}");
            st.setString("alumno_id", id_matricula);
            st.setString("seccion_id", seccion_id);
            st.execute();
            
        }catch(SQLException ex)
        {
            Logger.getLogger(ClsMatricula.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void ModificarMatriculaComida(String comida, String idMatricula,Connection con)
    {
        try
        {
            
            PreparedStatement st = con.prepareStatement("update mat_matricula set\n" +
                        "comida_alumno = (?) \n" +
                        "where id_matricula = (?) ");     
            st.setString(1, comida);
            st.setString(2, idMatricula);
            st.executeUpdate();
        }
        catch(SQLException ex)
        {
            Logger.getLogger(ClsEntregable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ModificarMatricula(String codigo,ClsEntidadMatricula Matricula,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_Matricula(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            statement.setString("pid_matricula", codigo);
            statement.setString("pcod_matricula", Matricula.getCod_Matricula());
            statement.setString("pfecha_matricula", Matricula.getFecha_Matricula());
            statement.setString("pinicio_clases", Matricula.getInicio_Clases());
            statement.setString("pedadanio_matricula", Matricula.getEdadAnio_Matricula());
            statement.setString("phorarioe_matricula", Matricula.getHorarioe_Matricula());
            statement.setString("phorarios_matricula", Matricula.getHorarios_Matricula());
            statement.setDouble("pmensualidad_matricula", Matricula.getMensualidad_Matricula());
            statement.setString("psexo_alumno", Matricula.getSexo_Alumno());
            statement.setString("pcomida_alumno", Matricula.getComida_Alumno());   
            statement.setString("ppartida_nac", Matricula.getPartida_Nacimiento());
            statement.setString("pdni_padre", Matricula.getDni_Padre());
            statement.setString("pdni_madre", Matricula.getDni_Madre());
            statement.setString("pdni_menor", Matricula.getDni_Menor());
            statement.setString("ptarjeta_vac", Matricula.getTarjeta_Vacunacion());
            statement.setString("pOtro", Matricula.getOtro());
            statement.setString("pMPS_Alumno_id_alumno", Matricula.getId_Alumno());
            statement.setString("pMPS_dclinico_id_clinico", Matricula.getId_Clinico());
            statement.setString("pMPS_emergencia_id_emer", Matricula.getId_Emergencia());            
            statement.execute();
            
        }catch(SQLException ex){
            Logger.getLogger(ClsMatricula.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void EditarRetiro(String IdMatricula,String Retiro,String Fecha,String Motivo,Connection con){
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE mat_matricula SET "
                        + "retiro =  ?, "
                        + "retiro_fecha = ?, "
                        + "retiro_motivo = ? "
                        + "WHERE id_matricula = ?";        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, Retiro);
            pst.setString(2, Fecha);
            pst.setString(3, Motivo);
            pst.setString(4, IdMatricula);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ClsMatricula.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClsMatricula.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRetiro(String IdMatricula,String Retiro, Connection con) {
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE mat_matricula SET "
                        + "retiro =  ? "                        
                        + "WHERE id_matricula = ?";        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, Retiro);            
            pst.setString(2, IdMatricula);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ClsMatricula.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClsMatricula.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void EliminarMatricula(String codigo,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_D_Matricula(?)}");
            statement.setString("pid_matricula",codigo);
            statement.execute();
            con.close();
        }catch(SQLException ex){
            Logger.getLogger(ClsMatricula.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<ClsEntidadMatricula> ListarMatricula(Connection con){
        ArrayList<ClsEntidadMatricula> Matriculas = new ArrayList<>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_L_Matricula}");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadMatricula matricula = new ClsEntidadMatricula();
                matricula.setId_Matricula(rs.getString("id_matricula"));
                matricula.setApe_Alumno(rs.getString("apellidos_nombres"));
                matricula.setComida_Alumno(rs.getString("comida_alumno"));
                matricula.setInicio_Clases(rs.getString("inicio_clases"));
                matricula.setPadre(rs.getString("padre_telefono"));
                matricula.setMadre(rs.getString("madre_telefono"));
                matricula.setRetiro(rs.getString("retiro"));
                matricula.setId_Seccion(rs.getString("id_seccion"));
                matricula.setNombre_Seccion(rs.getString("nombre_seccion"));
                matricula.setSexo_Alumno(rs.getString("sexo_alumno"));
                Matriculas.add(matricula);
                
            }
            
            return Matriculas;
        } catch(SQLException ex){
            Logger.getLogger(ClsMatricula.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public int InsertarMatriculaHuella(int idMatricula, ByteArrayInputStream huellaDatos, int huellaSize, Connection con) {
        
        int resultado = 0;
        PreparedStatement pstmt = null;
        String sqlInsert = "INSERT INTO mat_matricula_huella (id_matricula,HuellaDigital) VALUES "
                        + "(?,?)";
        try {
            
            pstmt = con.prepareStatement(sqlInsert);
            pstmt.setInt(1, idMatricula);
            pstmt.setBinaryStream(2, huellaDatos,huellaSize);
            resultado = pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ClsMatricula.class.getName()).log(Level.SEVERE, null, ex);
        } catch(Exception ex){
            Logger.getLogger(ClsMatricula.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(pstmt != null) {
                    pstmt.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(ClsMatricula.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return resultado;
    }
    
   
   
    
    public int ActualizarMatriculaHuella(int idMatricula, ByteArrayInputStream huellaDatos, int huellaSize, Connection con) {
        
        int resultado = 0;
        PreparedStatement pstmt = null;
        String sqlUpdate = "UPDATE mat_matricula_huella set HuellaDigital = ? WHERE id_matricula = ?";
        
        try {
            
            pstmt = con.prepareStatement(sqlUpdate);
            pstmt.setBinaryStream(1, huellaDatos,huellaSize);
            pstmt.setInt(2, idMatricula);            
            resultado = pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ClsMatricula.class.getName()).log(Level.SEVERE, null, ex);
        } catch(Exception ex){
            Logger.getLogger(ClsMatricula.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(pstmt != null) {
                    pstmt.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(ClsMatricula.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return resultado;
    }
    
    public ArrayList<MatriculaAlumnoHuella> ListarAlumnoHuellas(int idAula) {
       
        ArrayList<MatriculaAlumnoHuella> arrayHuella = new ArrayList<>();
        Connection con = ClsConexion.getConection();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String sqlQuery = "SELECT m.id_matricula, mh.HuellaDigital, a.apellidop_alumno,a.apellidom_alumno,a.nombre_alumno\n" +
                        "FROM mat_matricula AS m\n" +
                        "LEFT JOIN mat_matricula_huella AS mh ON m.id_matricula = mh.id_matricula\n" +
                        "INNER JOIN mat_alumno as a ON a.id_alumno = m.MPS_Alumno_id_alumno\n" +
                        "WHERE m.seccion_id = ? AND m.retiro = 'NO' \n" +
                        "ORDER BY a.apellidop_alumno";
        
        try 
        {
            pstmt = con.prepareStatement(sqlQuery);
            pstmt.setInt(1, idAula);
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
                
                MatriculaAlumnoHuella mah = new MatriculaAlumnoHuella (
                        
                    rs.getInt("id_matricula"), 
                    rs.getObject("HuellaDigital"), 
                    rs.getString("apellidop_alumno") +" "+ rs.getString("apellidom_alumno"), 
                    rs.getString("nombre_alumno")
                );
                
                arrayHuella.add(mah);                
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ClsMatricula.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex) {
            Logger.getLogger(ClsMatricula.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            
            try {
                if(rs != null) { rs.close(); }
                if(pstmt != null) { pstmt.close(); }
            } catch (SQLException ex) {
                Logger.getLogger(ClsMatricula.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            ClsConexion.closeConnection(con);
        }
        
        return arrayHuella;
    }
    
    public ArrayList<MatriculaAlumnoHuella> ListarAlumnoHuellas(String busquedaAlumno, int idPeriodo) {
       
        ArrayList<MatriculaAlumnoHuella> arrayHuella = new ArrayList<>();
        Connection con = ClsConexion.getConection();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String sqlQuery = "SELECT m.id_matricula, a.apellidop_alumno, a.apellidom_alumno, a.nombre_alumno,\n" +
                "s.nombre_seccion, mh.HuellaDigital\n" +
                "FROM mat_matricula AS m\n" +
                "LEFT JOIN mat_matricula_huella AS mh ON m.id_matricula = mh.id_matricula\n" +
                "INNER JOIN mat_alumno AS a ON a.id_alumno = m.MPS_Alumno_id_alumno\n" +
                "INNER JOIN mat_seccion AS s ON m.seccion_id = s.id_seccion\n" +
                "INNER JOIN mat_periodo AS p ON p.id_periodo = s.MPS_Periodo_id_periodo\n" +
                "WHERE p.id_periodo = ? AND m.retiro = 'NO' AND(\n" +
                "a.nombre_alumno LIKE concat('%',?,'%') \n" +
                "OR a.apellidop_alumno LIKE concat('%',?,'%') \n" +
                "OR a.apellidom_alumno LIKE concat('%',?,'%')\n" +
                "OR concat(a.nombre_alumno,' ',a.apellidop_alumno) like concat('%',?,'%') \n" +
                "OR concat(a.apellidop_alumno,' ',a.apellidom_alumno) like concat('%',?,'%') \n" +
                "OR concat(a.nombre_alumno,' ',a.apellidom_alumno) like concat('%',?,'%')\n" +
                "OR MATCH (a.nombre_alumno,a.apellidop_alumno,a.apellidom_alumno) AGAINST (?)\n" +
                ")";
        
        try 
        {
            pstmt = con.prepareStatement(sqlQuery);
            pstmt.setInt(1, idPeriodo);
            pstmt.setString(2, busquedaAlumno);
            pstmt.setString(3, busquedaAlumno);
            pstmt.setString(4, busquedaAlumno);
            pstmt.setString(5, busquedaAlumno);
            pstmt.setString(6, busquedaAlumno);
            pstmt.setString(7, busquedaAlumno);
            pstmt.setString(8, busquedaAlumno);
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
                
                MatriculaAlumnoHuella mah = new MatriculaAlumnoHuella (
                        
                    rs.getInt("id_matricula"), 
                    rs.getObject("HuellaDigital"), 
                    rs.getString("apellidop_alumno") +" "+ rs.getString("apellidom_alumno"), 
                    rs.getString("nombre_alumno"),
                    rs.getString("nombre_seccion")
                );
                
                arrayHuella.add(mah);                
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ClsMatricula.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex) {
            Logger.getLogger(ClsMatricula.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            
            try {
                if(rs != null) { rs.close(); }
                if(pstmt != null) { pstmt.close(); }
            } catch (SQLException ex) {
                Logger.getLogger(ClsMatricula.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            ClsConexion.closeConnection(con);
        }
        
        return arrayHuella;
    }
    
    public ResultSet ListarMatriculaAula(String xIdAula,Connection con)
    {
        ResultSet rsResultado = null;
        try 
        {
            PreparedStatement st = con.prepareStatement("SELECT m.cod_matricula, m.comida_alumno,m.sexo_alumno, m.seccion_id\n" +
            "FROM mat_matricula AS m WHERE m.retiro = 'NO' AND m.seccion_id = ?");
            st.setString(1, xIdAula);
            rsResultado = st.executeQuery();            
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(ClsSeccion.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return rsResultado;
    }
    
    //Busqueda instantanea en FrmMatricula
    public ArrayList<ClsEntidadMatricula> SeleccionarMatriculaBusqueda(String busqueda,Connection con)
    {
        ArrayList<ClsEntidadMatricula> Matriculas = new ArrayList<>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_S_MatriculaBusqueda(?)}");
            statement.setString("busqueda", busqueda);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadMatricula matricula = new ClsEntidadMatricula();
                matricula.setId_Matricula(rs.getString("id_matricula"));
                matricula.setApe_Alumno(rs.getString("apellidos_nombres"));
                matricula.setCod_Matricula(rs.getString("cod_matricula"));
                matricula.setInicio_Clases(rs.getString("inicio_clases"));
                matricula.setPadre(rs.getString("padre_telefono"));
                matricula.setMadre(rs.getString("madre_telefono"));
                matricula.setRetiro(rs.getString("retiro"));
                matricula.setId_Seccion(rs.getString("id_seccion"));
                matricula.setNombre_Seccion(rs.getString("nombre_seccion"));
                matricula.setNombre_Periodo(rs.getString("nombre_periodo"));
                Matriculas.add(matricula);                
            }            
            return Matriculas;
        } catch(SQLException ex){
            Logger.getLogger(ClsMatricula.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ResultSet SeleccionarMatricula(String codigo,Connection con)  throws Exception{
        ResultSet rs;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_Matricula(?)}");
            statement.setString("idMatricula", codigo);
            rs = statement.executeQuery();            
            return rs;
        }catch(SQLException ex){
            throw ex;
        }
    }
    
    public ResultSet SeleccionarMatriculaNumeros(String codigo,Connection con)  throws Exception{
        ResultSet rs;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_MatriculaNumeros(?)}");
            statement.setString("idMatricula", codigo);
            rs = statement.executeQuery();            
            return rs;
        }catch(SQLException ex){
            throw ex;
        }
    }
    
    public ResultSet SeleccionarMatriculaSolo(String codigo, Connection conx)  throws Exception{
        ResultSet rs;
        try{
            CallableStatement statement = conx.prepareCall("{call sp_S_MatriculaSolo(?)}");
            statement.setString("pid_matricula", codigo);
            rs = statement.executeQuery();            
            return rs;
        }catch(SQLException ex){
            throw ex;
        }
    }
    
    
    
    public ArrayList<ClsEntidadMatricula> ListarMatricula2(String codigo,Connection con){
        ArrayList<ClsEntidadMatricula> Matriculas = new ArrayList<>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_L_Matricula2(?)}");
            statement.setString("pbusqueda", codigo);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadMatricula matricula = new ClsEntidadMatricula();
                matricula.setId_Matricula(rs.getString("id_matricula"));
                matricula.setCod_Matricula(rs.getString("cod_matricula"));
                //matricula.setSeccion_Matricula(rs.getString("seccion_matricula"));
                //matricula.setPeriodo_Matricula(rs.getString("nombre_periodo"));
                matricula.setInicio_Clases(rs.getString("inicio_clases"));
                matricula.setDni_Alumno(rs.getString("dni_alumno"));
                matricula.setApe_Alumno(rs.getString("apellidos_nombres"));
                //matricula.setEstado_Matricula(rs.getString("estado_matricula"));
                matricula.setRetiro(rs.getString("retiro"));
                Matriculas.add(matricula);
                
            }
            con.close();
            return Matriculas;
            
        } catch(SQLException ex){
            Logger.getLogger(ClsMatricula.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ResultSet SeleccionaFechaMatricula (String codigo,Connection con) throws Exception
    {
    ResultSet rs;
    try{
            CallableStatement statement = con.prepareCall("{call sp_L_Fecha(?)}");
            statement.setString("pbusqueda", codigo);
            rs = statement.executeQuery();
            
         
            return rs;
            
        }catch(SQLException ex){
            throw ex;
        }
    }
    //Obtenemos los datos para mandarlos al reporte de la ficha
    public ResultSet SeleccionarMatriculaFichaReporte(String codigo,Connection con)  throws Exception
    {
        ResultSet rs;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_MatriculaFichaReporte(?)}");
            statement.setString("pid_matricula", codigo);
            rs = statement.executeQuery();
            
            
            return rs;
        }catch(SQLException ex){
            throw ex;
        }
    }

    
    public ResultSet ObtenerCodigos(String idMat,Connection con)  throws Exception{
        ResultSet rs;
        try{
            Statement statement = con.createStatement();
            rs = statement.executeQuery("select  m.id_matricula,s.id_seccion,a.id_alumno,dc.id_clinico,eme.id_emer,apo.id_apoderado,\n" +
            "pa.padre_id,ma.madre_id,p.id_periodo,n.nivel_id\n" +
            "from mat_matricula as m \n" +
            "inner join mat_seccion as s on m.seccion_id = s.id_seccion\n" +
            "inner join mat_alumno as a on a.id_alumno = m.MPS_Alumno_id_alumno\n" +
            "inner join mat_dclinico as dc on dc.id_clinico = m.MPS_dclinico_id_clinico\n" +
            "inner join mat_emergencia as eme on eme.id_emer = m.MPS_emergencia_id_emer\n" +
            "inner join mat_apoderado as apo on apo.id_apoderado = a.MPS_Apoderado_id_apoderado\n" +
            "inner join mat_padre as pa on pa.padre_id = a.padre_id\n" +
            "inner join mat_madre as ma on ma.madre_id = a.madre_id\n" +
            "inner join mat_periodo as p on p.id_periodo = s.MPS_Periodo_id_periodo\n" +
            "inner join mat_nivel as n on p.nivel_id = n.nivel_id\n" +
            "where m.id_matricula = "+idMat+";");              
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
//ACAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA CORRELATIVOSSSSSSSSSS
        public String  obtenSerie(Connection con){
           String cod="";
           //Connection cn=null;
           //String cad, sql="select max(fac_num) from fac_cabe";
           String cad;
           String serie;
           DecimalFormat fnumero=new DecimalFormat("000000");
           DecimalFormat fserie=new DecimalFormat("000");
           int nro;
          
           try{
           CallableStatement statement = con.prepareCall("{call sp_S_BoletaSerieCodigo()}");
           ResultSet rs=statement.executeQuery();
           if(rs.next()){
               cad=rs.getString(3);
               serie = rs.getString(2);
               nro=Integer.parseInt(cad)+1;
              
               cod=fserie.format(Integer.parseInt(serie))+" - "+fnumero.format(nro);
           }
           
           }catch(SQLException ex){
               Logger.getLogger(ClsMatricula.class.getName()).log(Level.SEVERE, null, ex);
           }
           return cod;
        }
    
        public void ModificaSerieCorrelativo(String serie,String correlativo,Connection con)
        {
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_CodigoBoleta(?,?)}");
            statement.setString("pserie", serie);
            statement.setString("pcodigo", correlativo);
//            statement.setString("pidcod", codigo);
            statement.execute();
            
        }catch(SQLException ex){
            Logger.getLogger(ClsMatricula.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        public void ModificaCorrelativo(String correlativo,Connection con)
        {
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_Correlativo(?)}");            
            statement.setString("pcodigo", correlativo);
            statement.execute();            
        }catch(SQLException ex){
            Logger.getLogger(ClsMatricula.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    
        public String  GeneraCodigoMatricula(Connection con){
           String cod="";
           //Connection cn=null;
           //String cad, sql="select max(fac_num) from fac_cabe";
           String anio;
           String serie;
           DecimalFormat fserie=new DecimalFormat("00000");           
           int nro;
          
           try{
           CallableStatement statement = con.prepareCall("{call sp_S_CodigoMatricula()}");
           ResultSet rs=statement.executeQuery();
                if(rs.next()){
                    anio=rs.getString(2);
                    serie = rs.getString(3);
                    nro=Integer.parseInt(serie)+1;

                    cod=anio+fserie.format(nro);
                }          
           }catch(SQLException ex){
               Logger.getLogger(ClsMatricula.class.getName()).log(Level.SEVERE, null, ex);
           }
           return cod;
        }
        public String  ObtenerAnioCodigoMatricula(Connection con)
        {           
           String anio = null;        
           try{
           CallableStatement statement = con.prepareCall("{call sp_S_CodigoMatricula()}");
           ResultSet rs=statement.executeQuery();
                if(rs.next()){
                    anio=rs.getString(2);                    
                }          
           }catch(SQLException ex){
               Logger.getLogger(ClsMatricula.class.getName()).log(Level.SEVERE, null, ex);
           }
           return anio;
        }
        public String  ObtenerSerieCodigoMatricula(Connection con)
        {           
           String serie = null;        
           try{
           CallableStatement statement = con.prepareCall("{call sp_S_CodigoMatricula()}");
           ResultSet rs=statement.executeQuery();
                if(rs.next()){
                    serie = rs.getString(3); 
                    int a = Integer.parseInt(serie)+1;
                    serie = String.valueOf(a);
                }          
           }catch(SQLException ex){
               Logger.getLogger(ClsMatricula.class.getName()).log(Level.SEVERE, null, ex);
           }
           return serie;
        }
        
        public void ModificaAnioSerieMatricula(String anio, String serie,Connection con)
        {
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_CodigoMatricula(?,?)}");            
            statement.setString("pcodm_anio", anio);
            statement.setString("pcodm_correlativo", serie);
            statement.execute();
          
        }catch(SQLException ex){
            Logger.getLogger(ClsMatricula.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        public ResultSet ListarMadrePadreMatricula(String xIdMatricula, Connection con)
    {
        ResultSet rs = null;        
        PreparedStatement pst;
        try 
        {
            pst = con.prepareStatement("select concat(ma.madre_apellido_paterno,' ',ma.madre_apellido_materno,', ',ma.madre_nombre) as 'madre',\n" +
                "ma.madre_telefono1,\n" +
                "concat(pa.padre_apellido_paterno,' ',pa.padre_apellido_materno,', ',pa.padre_nombre) as 'padre',\n" +
                "pa.padre_telefono1\n" +
                "from mat_madre as ma \n" +
                "inner join mat_alumno as al on ma.madre_id = al.madre_id\n" +
                "inner join mat_padre as pa on pa.padre_id = al.padre_id\n" +
                "inner join mat_matricula as m on m.MPS_Alumno_id_alumno = al.id_alumno\n" +
                "where m.id_matricula = ?");
            pst.setString(1, xIdMatricula);
            rs = pst.executeQuery();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(ClsAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return rs;        
    }
        
}
