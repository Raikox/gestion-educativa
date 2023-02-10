package PckNegocio;

import PckConexion.*;
import PckEntidad.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author MapaSoft
 */
public class ClsSeccion {
    
    private Connection con = new ClsConexion().getConection();
    
    public void AgregarSeccion(ClsEntidadSeccion Seccion){
        try{
            CallableStatement statement = con.prepareCall("{call sp_I_Seccion(?,?,?,?,?,?,?)}");
            statement.setString("pnombre_seccion", Seccion.getNombre_Seccion());
            statement.setString("pcantidad_seccion", Seccion.getCantidad_Seccion());
            statement.setString("pasistencia_seccion", Seccion.getAsistencia_Seccion());
            statement.setString("pMPS_Profesor_id_profesor", Seccion.getId_Profesor());
            statement.setString("pMPS_Profesor_id_auxiliar1", Seccion.getId_Auxiliar1());
            statement.setString("pMPS_Profesor_id_auxiliar2", Seccion.getId_Auxiliar2());
            statement.setString("pMPS_Periodo_id_periodo", Seccion.getId_Periodo());
            statement.execute();
            con.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }
    
    public void ModificarSeccion(String codigo,ClsEntidadSeccion Seccion){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_Seccion(?,?,?,?,?,?,?,?)}");
            statement.setString("pid_seccion", codigo);
            statement.setString("pnombre_seccion", Seccion.getNombre_Seccion());
            statement.setString("pcantidad_seccion", Seccion.getCantidad_Seccion());
            statement.setString("pasistencia_seccion", Seccion.getAsistencia_Seccion());
            statement.setString("pMPS_Profesor_id_profesor", Seccion.getId_Profesor());
            statement.setString("pMPS_Profesor_id_auxiliar1", Seccion.getId_Auxiliar1());
            statement.setString("pMPS_Profesor_id_auxiliar2", Seccion.getId_Auxiliar2());
            statement.setString("pMPS_Periodo_id_periodo", Seccion.getId_Periodo());
            statement.execute();
            con.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public void EliminarSeccion(String codigo){
        try{
            CallableStatement statement = con.prepareCall("{call sp_D_Seccion(?)}");
            statement.setString("pid_seccion",codigo);
            statement.execute();
            con.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,
                "La sección actualmente tiene alumnos matriculados",
                "No se pudo eliminar",
                JOptionPane.ERROR_MESSAGE);
                System.out.println(ex);
            ex.printStackTrace();
        }
    }
    
    public ArrayList<ClsEntidadSeccion> ListarSeccionPeriodo(String xIdPeriodo) {
        ArrayList<ClsEntidadSeccion> Secciones = new ArrayList<>();
      try{
            PreparedStatement statement = con.prepareStatement("select id_seccion,nombre_seccion "
                    + "from mat_seccion where MPS_Periodo_id_periodo = ? order by nombre_seccion"); 
            statement.setString(1, xIdPeriodo); 
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                ClsEntidadSeccion seccion = new ClsEntidadSeccion();
                seccion.setId_Seccion(rs.getString("id_seccion"));                
                seccion.setNombre_Seccion(rs.getString("nombre_seccion"));
                Secciones.add(seccion);                
            }
            con.close();
            return Secciones;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public ResultSet ListarAulasPorPeriodo(String xIdPeriodo)
    {
        ResultSet rsResultado = null;
        try 
        {
            PreparedStatement st = con.prepareStatement("select id_seccion,nombre_seccion "
                    + "from mat_seccion where MPS_Periodo_id_periodo = ? "
                    + "order by nombre_seccion");
            st.setString(1, xIdPeriodo);
            rsResultado = st.executeQuery();            
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(ClsSeccion.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return rsResultado;
        
    }
    
    public ResultSet ListarAulaVacante(Connection xConexion,String xIdPeriodo) throws SQLException {
        
        ResultSet rsResultado;
        try
        {            
            PreparedStatement st = xConexion.prepareStatement("select DISTINCT s.id_seccion,s.nombre_seccion,s.cantidad_seccion, (select contarMatriculados(s.id_seccion)) as 'ocupado',\n" +
            "(select cantidad_seccion - ocupado) as 'libre', (select TRUNCATE(((ocupado / cantidad_seccion)*100),0)) as 'porcentaje'\n" +
            "from mat_matricula as m\n" +
            "inner join mat_seccion as s on m.seccion_id = s.id_seccion\n" +
            "inner join mat_periodo as p on s.MPS_Periodo_id_periodo = p.id_periodo\n" +
            "where p.id_periodo = ? order by s.nombre_seccion");
            st.setString(1, xIdPeriodo);
            rsResultado = st.executeQuery();            
            return rsResultado;
        }
        catch(SQLException ex)
        {
            throw ex;
        }
        
    }
    
    public ArrayList<ClsEntidadSeccion> ListarSeccion(){
        ArrayList<ClsEntidadSeccion> Secciones = new ArrayList<ClsEntidadSeccion>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_L_Seccion}");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadSeccion seccion = new ClsEntidadSeccion();
                seccion.setId_Seccion(rs.getString("id_seccion"));
                seccion.setAsistencia_Seccion(rs.getString("asistencia_seccion"));
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
            con.close();
            return Secciones;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public ResultSet SeleccionarSeccion(String codigo)  throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_Seccion(?)}");
            statement.setString("pid_seccion", codigo);
            rs = statement.executeQuery();
           
            return rs;
        }catch(SQLException ex){
            throw ex;
        }
    }
    
    public ResultSet ListaUnaSeccion(String codigo)  throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = con.prepareCall("{call sp_L_Seccion2(?)}");
            statement.setString("pbusqueda", codigo);
            rs = statement.executeQuery();
            
            return rs;
        }catch(SQLException ex){
            throw ex;
        }
    }
    
    public ResultSet ListaMatriculados(String codigo)  throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_CountMatriculados(?)}");
            statement.setString("pbusqueda", codigo);
            rs = statement.executeQuery();  
            
            return rs;
            
        }catch(SQLException ex){
            throw ex;
        }
        
        
    }
    
    //------Asistencia Comen----//
    
    public ArrayList<ClsEntidadSeccion> ListarSeccionComen(){
        ArrayList<ClsEntidadSeccion> Secciones = new ArrayList<ClsEntidadSeccion>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_L_SeccionComida()}");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadSeccion seccion = new ClsEntidadSeccion();
                seccion.setId_Seccion(rs.getString("id_seccion"));
                seccion.setNombre_Periodo(rs.getString("nombre_seccion"));               
                seccion.setId_Periodo(rs.getString("id_periodo")); 
                Secciones.add(seccion);                
            }
           
            return Secciones;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    

    public ResultSet ListarComidasPorSeccion(String codigo)  throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_ComidaPorSeccion(?)}");
            statement.setString("idseccion", codigo);
            rs = statement.executeQuery();  
            
            return rs;
            
        }catch(SQLException ex){
            throw ex;
        }
        
        
    }
    
    public String LoncherasPorSalon(String idAula,String fecha, Connection con)
    {
        String loncheras="";
        ResultSet rs;
        PreparedStatement pstmt;
        String sql = "SELECT COUNT(asistencia_alumno_id) as 'lonchera' "
                + "FROM mat_asistencia as asis "
                + "INNER JOIN mat_alumno as a ON asis.asistencia_alumno_id = a.id_alumno "
                + "INNER JOIN mat_matricula as m ON a.id_alumno = m.MPS_Alumno_id_alumno "
                + "WHERE m.comida_alumno = 'SI' AND m.retiro = 'NO' "
                + "AND m.seccion_id = ? AND asis.asistencia_fecha = ?";
        
        try 
        {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, idAula);
            pstmt.setString(2, fecha);
            rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                loncheras = rs.getString("lonchera");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClsSeccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return loncheras;
                
    }
    
        
     public ClsEntidadAsistencia produce(String comen, String secion) 
     {
        ClsEntidadAsistencia dataBean = new ClsEntidadAsistencia();
        dataBean.setComen(comen);
        dataBean.setSeccion(secion);        
        return dataBean;
    }
     
    
    
}
