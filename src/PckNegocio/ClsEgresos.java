/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckNegocio;


import PckEntidad.ClsEntidadEgresos;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kmv
 */
public class ClsEgresos {
    
    
    public void AgregarEgreso(ClsEntidadEgresos Egreso,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_I_Egreso(?,?,?,?,?)}");
                        
            statement.setString("pid_persona", Egreso.getId_Persona());            
            statement.setString("pconcepto_egreso", Egreso.getConcepto_Egreso());
            statement.setString("pdesc_egreso", Egreso.getDescripcion_Egreso());
            statement.setString("pmonto_egreso", Egreso.getMonto_Egreso());
            statement.setString("pfecha_egreso", Egreso.getFecha_Egreso());
            statement.execute();
        
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }
    public void ModificaEgreso(ClsEntidadEgresos Egreso,String codigo,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_Egreso(?,?,?,?,?,?)}");
            statement.setString("pid_egreso", codigo); 
            statement.setString("pid_persona", Egreso.getId_Persona());            
            statement.setString("pconcepto_egreso", Egreso.getConcepto_Egreso());
            statement.setString("pdesc_egreso", Egreso.getDescripcion_Egreso());
            statement.setString("pmonto_egreso", Egreso.getMonto_Egreso());
            statement.setString("pfecha_egreso", Egreso.getFecha_Egreso());
            statement.execute();        
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }
     public void EliminarEgreso(String codigo,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_D_Egreso(?)}");
            statement.setString("pid_egreso",codigo);
            statement.execute();
         
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
     
     public ArrayList<ClsEntidadEgresos> ListarEgresos(Connection con)
    {
        ArrayList<ClsEntidadEgresos> Egreso = new ArrayList<ClsEntidadEgresos>();
        try
        {   CallableStatement st = con.prepareCall("{call sp_L_Egreso()}");                        
            ResultSet rs = st.executeQuery();
            while (rs.next())
            { 
            ClsEntidadEgresos egreso=new ClsEntidadEgresos();
            egreso.setId_Egreso(rs.getString("id_egreso"));
            egreso.setNombre_Persona(rs.getString("persona"));
            egreso.setConcepto_Egreso(rs.getString("concepto_egreso"));
            egreso.setDescripcion_Egreso(rs.getString("desc_egreso"));
            egreso.setMonto_Egreso(rs.getString("monto_egreso"));
            egreso.setFecha_Egreso(rs.getString("fecha"));
                    
            Egreso.add(egreso);
            }
            return Egreso;            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
     
}
