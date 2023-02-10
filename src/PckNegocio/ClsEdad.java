/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckNegocio;

import PckConexion.ClsConexion;
import PckEntidad.ClsEntidadEdad;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 */
public class ClsEdad {
    
    
    public ArrayList<ClsEntidadEdad> ListarNacimiento(Connection con)
    {
        ArrayList<ClsEntidadEdad> Alumnos = new ArrayList<ClsEntidadEdad>();

        try
        {
            CallableStatement st = con.prepareCall("{call sp_L_Nacimiento}");
            ResultSet rs = st.executeQuery();
            while (rs.next())
            {
            ClsEntidadEdad alumno = new ClsEntidadEdad();
            alumno.setId_Matricula(rs.getString("id_matricula")); 
            alumno.setNacimiento_Alumno(rs.getDate("fechan_alumno"));           
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
    
    public void ModificarEdad(String codigo,String edad,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_Nacimiento(?,?)}");
            statement.setString("pid_matricula", codigo);
            statement.setString("pedad_matricula", edad);            
            statement.execute();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    
}
