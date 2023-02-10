package PckNegocio;

import PckConexion.*;
import PckEntidad.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Lee
 */
public class ClsClinico {
    
    
    
    public void AgregarClinico(ClsEntidadClinico Clinico,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_I_Dclinico(?,?,?,?,?,?,?,?)}");
            statement.setString("pmedicamento_clinico", Clinico.getMedicamento_Clinico());
            statement.setString("palimento_clinico", Clinico.getAlimento_Clinico());
            statement.setString("pvaricelo_clinico", Clinico.getVaricelo_Clinico());
            statement.setString("prubeola_clinico", Clinico.getRubeola_Clinico());
            statement.setString("psarampion_clinico", Clinico.getSarampion_Clinico());
            statement.setString("ppaperas_clinico", Clinico.getPaperas_Clinico());
            statement.setString("phepatitis_clinico", Clinico.getHepatitis_Clinico());
            statement.setString("potro_clinico", Clinico.getOtro_Clinico());
            statement.execute();
           
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }
    
    public void ModificarClinico(String codigo,Connection con,ClsEntidadClinico Clinico){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_DClinico(?,?,?,?,?,?,?,?,?)}");
            statement.setString("pid_clinico", codigo);
            statement.setString("pmedicamento_clinico", Clinico.getMedicamento_Clinico());
            statement.setString("palimento_clinico", Clinico.getAlimento_Clinico());
            statement.setString("pvaricelo_clinico", Clinico.getVaricelo_Clinico());
            statement.setString("prubeola_clinico", Clinico.getRubeola_Clinico());
            statement.setString("psarampion_clinico", Clinico.getSarampion_Clinico());
            statement.setString("ppaperas_clinico", Clinico.getPaperas_Clinico());
            statement.setString("phepatitis_clinico", Clinico.getHepatitis_Clinico());
            statement.setString("potro_clinico", Clinico.getOtro_Clinico());
            statement.execute();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public void EliminarClinico(String codigo,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_D_Dclinico(?)}");
            statement.setString("pid_clinico",codigo);
            statement.execute();
            con.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public ArrayList<ClsEntidadClinico> ListarClinico(Connection con){
        ArrayList<ClsEntidadClinico> Clinicos = new ArrayList<ClsEntidadClinico>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_L_Dclinico}");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadClinico clinico = new ClsEntidadClinico();
                clinico.setId_Clinico(rs.getString("id_clinico"));
                clinico.setMedicamento_Clinico(rs.getString("medicamento_clinico"));
                clinico.setAlimento_Clinico(rs.getString("alimento_clinico"));
                clinico.setVaricelo_Clinico(rs.getString("varicelo_clinico"));
                clinico.setRubeola_Clinico(rs.getString("rubeola_clinico"));
                clinico.setSarampion_Clinico(rs.getString("sarampion_clinico"));
                clinico.setPaperas_Clinico(rs.getString("paperas_clinico"));
                clinico.setHepatitis_Clinico(rs.getString("hepatitis_clinico"));
                clinico.setOtro_Clinico(rs.getString("otro_clinico"));
                Clinicos.add(clinico);
                
            }
            con.close();
            return Clinicos;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }

    public ResultSet SeleccionarClinico(String codigo,Connection con)  throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_Dclinico(?)}");
            statement.setString("pid_clinico", codigo);
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
}


