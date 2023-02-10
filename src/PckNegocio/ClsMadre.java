/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckNegocio;

import PckEntidad.ClsEntidadMadre;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 */
public class ClsMadre {
    
    
    public void AgregarMadre(ClsEntidadMadre Madre,Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_I_Madre(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            statement.setString("pmadre_apellido_paterno", Madre.getApellidoP_Madre());
            statement.setString("pmadre_apellido_materno", Madre.getApellidoM_Madre());
            statement.setString("pmadre_nombre", Madre.getNombre_Madre());
            statement.setString("pmadre_vive", Madre.getVive_Madre());
            statement.setString("pmadre_fecha_nacimiento", Madre.getFechaN_Madre());
            statement.setString("pmadre_grado_instruccion", Madre.getGradoI_Madre());
            statement.setString("pmadre_dni", Madre.getDni_Madre());
            statement.setString("pmadre_estado_civil", Madre.getEstadoC_Madre());
            statement.setString("pmadre_ocupacion", Madre.getOcupacion_Madre());
            statement.setString("pmadre_centro_laboral", Madre.getCentroL_Madre());
            statement.setString("pmadre_correo", Madre.getEmail_Madre());
            statement.setString("pmadre_telefono1", Madre.getTelefono_Madre());
            statement.setString("pmadre_telefono2", Madre.getTelefono2_Madre());
            statement.setString("pmadre_vive_con_alumno", Madre.getViveCona_Madre());
            statement.execute();
            //con.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }
    
    public void ModificarMadre(String codigo,Connection con,ClsEntidadMadre Madre){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_Madre(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            statement.setString("pmadre_id", codigo);
            statement.setString("pmadre_apellido_paterno", Madre.getApellidoP_Madre());
            statement.setString("pmadre_apellido_materno", Madre.getApellidoM_Madre());
            statement.setString("pmadre_nombre", Madre.getNombre_Madre());
            statement.setString("pmadre_vive", Madre.getVive_Madre());
            statement.setString("pmadre_fecha_nacimiento", Madre.getFechaN_Madre());
            statement.setString("pmadre_grado_instruccion", Madre.getGradoI_Madre());
            statement.setString("pmadre_dni", Madre.getDni_Madre());
            statement.setString("pmadre_estado_civil", Madre.getEstadoC_Madre());
            statement.setString("pmadre_ocupacion", Madre.getOcupacion_Madre());
            statement.setString("pmadre_centro_laboral", Madre.getCentroL_Madre());
            statement.setString("pmadre_correo", Madre.getEmail_Madre());
            statement.setString("pmadre_telefono1", Madre.getTelefono_Madre());
            statement.setString("pmadre_telefono2", Madre.getTelefono2_Madre());
            statement.setString("pmadre_vive_con_alumno", Madre.getViveCona_Madre());
            statement.execute();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
  
    
    public ArrayList<ClsEntidadMadre> ListarMadre(Connection con){
        ArrayList<ClsEntidadMadre> Madres = new ArrayList<ClsEntidadMadre>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_L_Madre}");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadMadre madre = new ClsEntidadMadre();
                madre.setId_Madre(rs.getString("madre_id"));
                madre.setApellidoM_Madre(rs.getString("madre_apellido_paterno"));
                madre.setApellidoM_Madre(rs.getString("madre_apellido_materno"));
                madre.setNombre_Madre(rs.getString("madre_nombre"));
                madre.setVive_Madre(rs.getString("madre_vive"));
                madre.setFechaN_Madre(rs.getString("madre_fecha_nacimiento"));
                madre.setGradoI_Madre(rs.getString("madre_grado_instruccion"));
                madre.setDni_Madre(rs.getString("madre_dni"));
                madre.setEstadoC_Madre(rs.getString("madre_estado_civil"));
                madre.setOcupacion_Madre(rs.getString("madre_ocupacion"));
                madre.setCentroL_Madre(rs.getString("madre_centro_laboral"));
                madre.setEmail_Madre(rs.getString("madre_correo"));
                madre.setTelefono_Madre(rs.getString("madre_telefono1"));
                madre.setTelefono2_Madre(rs.getString("madre_telefono2"));
                madre.setViveCona_Madre(rs.getString("madre_vive_con_alumno"));
                Madres.add(madre);                
            }            
            return Madres;            
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
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
