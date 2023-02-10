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
 * @author MapaSoft
 */
public class ClsApoderado {
    
    private Connection con = new ClsConexion().getConection();
    
    public void AgregarApoderado(ClsEntidadApoderado Apoderado){
        try{
            CallableStatement statement = con.prepareCall("{call sp_I_Apoderado(?,?,?,?)}");
            statement.setString("pnombre_apoderado", Apoderado.getNombre_Apoderado());
            statement.setString("pparentesco_apoderado", Apoderado.getParentesco_Apoderado());
            statement.setString("pdni_apoderado", Apoderado.getDni_Apoderado());
            statement.setString("ptelefono_apoderado", Apoderado.getTelefono_Apoderado());
            statement.execute();            
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }
    
    public void ModificarApoderado(String codigo,ClsEntidadApoderado Apoderado){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_Apoderado(?,?,?,?,?)}");
            statement.setString("pid_apoderado", codigo);
            statement.setString("pnombre_apoderado", Apoderado.getNombre_Apoderado());
            statement.setString("pparentesco_apoderado", Apoderado.getParentesco_Apoderado());
            statement.setString("pdni_apoderado", Apoderado.getDni_Apoderado());
            statement.setString("ptelefono_apoderado", Apoderado.getTelefono_Apoderado());
            statement.execute();
            con.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public void EliminarApoderado(String codigo){
        try{
            CallableStatement statement = con.prepareCall("{call sp_D_Apoderado(?)}");
            statement.setString("pid_apoderado",codigo);
            statement.execute();
            con.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public ArrayList<ClsEntidadApoderado> ListarApoderado(){
        ArrayList<ClsEntidadApoderado> Apoderados = new ArrayList<ClsEntidadApoderado>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_L_Apoderado}");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadApoderado apoderado = new ClsEntidadApoderado();
                apoderado.setId_Apoderado(rs.getString("id_apoderado"));
                apoderado.setNombre_Apoderado(rs.getString("nombre_apoderado"));
                apoderado.setParentesco_Apoderado(rs.getString("parentesco_apoderado"));
                apoderado.setDni_Apoderado(rs.getString("dni_apoderado"));
                apoderado.setTelefono_Apoderado(rs.getString("telefono_apoderado"));
                Apoderados.add(apoderado);
                
            }
            con.close();
            return Apoderados;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public ResultSet SeleccionarApoderado(String codigo)  throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_Apoderado(?)}");
            statement.setString("pid_apoderado", codigo);
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
