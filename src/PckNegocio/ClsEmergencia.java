/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckNegocio;
import PckConexion.*;
import PckEntidad.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class ClsEmergencia {
    private Connection con = new ClsConexion().getConection();
    
    public void AgregarEmergencia(ClsEntidadEmergencia Emergencia){
        try{
            CallableStatement statement = con.prepareCall("{call sp_I_Emergencia(?,?,?)}");
            
            statement.setString("pemergencia_emer", Emergencia.getEmergencia_Emergencia());
            statement.setString("ptelefono_emer", Emergencia.getTelefono_Emergencia());
            statement.setString("pparentesco_emer", Emergencia.getParentesco_Emergencia());
            
            statement.execute();
           
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }
    
    public void ModificarEmergencia(String codigo,ClsEntidadEmergencia Emergencia){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_Emergencia(?,?,?,?)}");
            statement.setString("pid_emer", codigo);
            statement.setString("pemergencia_emer", Emergencia.getEmergencia_Emergencia());
            statement.setString("ptelefono_emer", Emergencia.getTelefono_Emergencia());
            statement.setString("pparentesco_emer", Emergencia.getParentesco_Emergencia());
            statement.execute();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public void EliminarEmergencia(String codigo){
        try{
            CallableStatement statement = con.prepareCall("{call sp_D_Emergencia(?)}");
            statement.setString("pid_emer",codigo);
            statement.execute();
            con.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    public ArrayList<ClsEntidadEmergencia> ListarEmergencia(){
        ArrayList<ClsEntidadEmergencia> Emergencias = new ArrayList<ClsEntidadEmergencia>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_L_Emergencia}");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadEmergencia emergencia = new ClsEntidadEmergencia();
                emergencia.setId_Emergencia(rs.getString("id_emer"));
                emergencia.setEmergencia_Emergencia(rs.getString("emergencia_emer"));
                emergencia.setTelefono_Emergencia(rs.getString("telefono_emer"));
                emergencia.setParentesco_Emergencia(rs.getString("parentesco_emer"));
                Emergencias.add(emergencia);
                
            }
            con.close();
            return Emergencias;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    public ResultSet SeleccionarEmergencia(String codigo)  throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_Emergencia(?)}");
            statement.setString("pid_emer", codigo);
            rs = statement.executeQuery();
        
            return rs;
        }catch(SQLException ex){
            throw ex;
        }
    }
    
    public String UltimoIdInsertado()  throws Exception{
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
