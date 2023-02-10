package PckNegocio;

import PckConexion.*;
import PckEntidad.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author MapaSoft
 */
public class ClsMatricula_Padre {
    
    private Connection con = new ClsConexion().getConection();


    public void ModificarMatricula_Padre(String codigo,ClsEntidadMatricula_Padre Matricula_Padre){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_Matricula_Padre(?,?)}");
            statement.setString("pMPS_Matricula_id_matricula", codigo);
            
            statement.setString("pMPS_Padre_id_padre", Matricula_Padre.getId_Padre());
            statement.execute();
            con.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public void EliminarMatricula_Padre(String codigo){
        try{
            CallableStatement statement = con.prepareCall("{call sp_D_Matricula_Padre(?)}");
            statement.setString("pMPS_Matricula_id_matricula", codigo);
            statement.execute();
            con.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public ArrayList<ClsEntidadMatricula_Padre> ListarMatricula_Padre(){
        ArrayList<ClsEntidadMatricula_Padre> Matricula_Padre = new ArrayList<ClsEntidadMatricula_Padre>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_L_Matricula_Padre}");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadMatricula_Padre Mat_Padre = new ClsEntidadMatricula_Padre();
                Mat_Padre.setId_Matricula(rs.getString("MPS_Matricula_id_matricula"));
               
                Mat_Padre.setId_Padre(rs.getString("MPS_Padre_id_padre"));
                Matricula_Padre.add(Mat_Padre);
            }
            con.close();
            return Matricula_Padre;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }

    public ResultSet SeleccionarMatricula_Padre(String codigo)  throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_Matricula_Padre(?)}");
            statement.setString("pMPS_Matricula_id_matricula", codigo);
            rs = statement.executeQuery();
          
            return rs;
        }catch(SQLException ex){
            throw ex;
        }
    }
    
}
