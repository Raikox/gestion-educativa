package PckNegocio;

import PckConexion.*;
import PckEntidad.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ClsPeriodo {
    
    private Connection con = new ClsConexion().getConection();
    
    public void AgregarPeriodo(ClsEntidadPeriodo Periodo){
        try{
            CallableStatement statement = con.prepareCall("{call sp_I_Periodo(?,?,?,?,?,?,?,?,?,?,?,?)}");
            statement.setString("pnombre_periodo", Periodo.getNombre_Periodo());               
            statement.setString("pestado_periodo", Periodo.getEstado_Periodo());
            statement.setString("panio_periodo", Periodo.getAnio_Periodo());
            statement.setString("pinicio_periodo", Periodo.getInicio_Periodo());
            statement.setString("pfin_periodo", Periodo.getFin_Periodo());            
            statement.setString("pmatricula_periodo", Periodo.getMatricula_Periodo());
            statement.setString("phora_periodo", Periodo.getHorA_Periodo());
            statement.setString("phorb_periodo", Periodo.getHorB_Periodo());
            statement.setString("phorc_periodo", Periodo.getHorC_Periodo());
            statement.setString("palimentacion_periodo", Periodo.getAlimentacion_Periodo());
            statement.setString("palimentacion_dia", Periodo.getAlimentacion_Dia());
            statement.setString("pnivel_id", Periodo.getNivel_Id());
            statement.execute();
            con.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }
    
    public void ModificarPeriodo(String codigo,ClsEntidadPeriodo Periodo){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_Periodo(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            statement.setString("pid_periodo", codigo);
            statement.setString("pnombre_periodo", Periodo.getNombre_Periodo());            
            statement.setString("pestado_periodo", Periodo.getEstado_Periodo());
            statement.setString("panio_periodo", Periodo.getAnio_Periodo());
            statement.setString("pinicio_periodo", Periodo.getInicio_Periodo());
            statement.setString("pfin_periodo", Periodo.getFin_Periodo());            
            statement.setString("pmatricula_periodo", Periodo.getMatricula_Periodo());
            statement.setString("phora_periodo", Periodo.getHorA_Periodo());
            statement.setString("phorb_periodo", Periodo.getHorB_Periodo());
            statement.setString("phorc_periodo", Periodo.getHorC_Periodo());
            statement.setString("palimentacion_periodo", Periodo.getAlimentacion_Periodo());
            statement.setString("palimentacion_dia", Periodo.getAlimentacion_Dia());
            statement.setString("pnivel_id", Periodo.getNivel_Id()); 
            statement.execute();
            con.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public void EliminarPeriodo(String codigo){
        try{
            CallableStatement statement = con.prepareCall("{call sp_D_Periodo(?)}");
            statement.setString("pid_periodo",codigo);
            statement.execute();
            con.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public ArrayList<ClsEntidadPeriodo> ListarPeriodo(){
        ArrayList<ClsEntidadPeriodo> Periodos = new ArrayList<ClsEntidadPeriodo>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_L_Periodo}");
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                ClsEntidadPeriodo periodo = new ClsEntidadPeriodo();
                periodo.setId_Periodo(rs.getString("id_periodo"));
                periodo.setNombre_Periodo(rs.getString("nombre_periodo"));
                periodo.setNivel_Periodo(rs.getString("nivel_nombre"));
                periodo.setEstado_Periodo(rs.getString("estado_periodo"));
                periodo.setInicio_Periodo(rs.getString("inicio_periodo"));
                periodo.setFin_Periodo(rs.getString("fin_periodo"));
                periodo.setNivel_Id(rs.getString("nivel_id"));
                Periodos.add(periodo);                
            }           
            return Periodos;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public ResultSet ListarAlumnosDescuento(Connection xConexion,String xIdPeriodo) throws SQLException {
        
        ResultSet rsResultado;
        try
        {            
            PreparedStatement st = xConexion.prepareStatement("select m.id_matricula as 'idmat',s.nombre_seccion, "
                    + "concat(a.apellidop_alumno,' ',a.apellidom_alumno,', ',a.nombre_alumno) as 'alumno',\n" +
            "m.partida_nac as 'motivo', m.mensualidad_matricula as 'mensualidad', m.Otro as 'diferencia', \n" +
            "(select(mensualidad-diferencia) from mat_matricula where id_matricula = idmat ) as 'paga'\n" +
            "from mat_matricula as m \n" +
            "inner join mat_alumno as a on m.MPS_Alumno_id_alumno = a.id_alumno\n" +
            "inner join mat_seccion as s on m.seccion_id = s.id_seccion\n" +
            "inner join mat_periodo as p on s.MPS_Periodo_id_periodo = p.id_periodo\n" +
            "where (m.partida_nac != '' or m.Otro != '') and p.id_periodo = ? and m.retiro='NO' order by s.nombre_seccion, a.apellidop_alumno");
            st.setString(1, xIdPeriodo);
            rsResultado = st.executeQuery();            
            return rsResultado;
        }
        catch(SQLException ex)
        {
            throw ex;
        }
        
    }
    
    public ResultSet SeleccionarPeriodo(String codigo)  throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_Periodo(?)}");
            statement.setString("pid_periodo", codigo);
            rs = statement.executeQuery();
           
            return rs;
        }catch(SQLException ex){
            throw ex;
        }
    }
    
    public ResultSet SeleccionarComidaPeriodo(String codigo)  throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_PeriodoComida(?)}");
            statement.setString("pid_periodo", codigo);
            rs = statement.executeQuery();
           
            return rs;
        }catch(SQLException ex){
            throw ex;
        }
    }

}
