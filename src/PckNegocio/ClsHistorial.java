/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckNegocio;

import PckConexion.ClsConexion;

import PckEntidad.ClsEntidadHistorial;
import PckEntidad.ClsEntidadPgoCambio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kmv
 */
public class ClsHistorial {
    private Connection con = new ClsConexion().getConection();
    
    
    //HISTORIAL CAMBIOS

    
    public void AgregarCambios(ClsEntidadPgoCambio Cambio){
        try{
            CallableStatement statement = con.prepareCall("{call sp_I_PgoCambio(?,?,?,?,?)}");            
            statement.setString("pfecha_cambio", Cambio.getFecha_Cambio());
            statement.setString("pmonto_cambio", Cambio.getMonto_Cambio());
            statement.setString("pobs_cambio", Cambio.getObs_Cambio());            
            statement.setString("ptipo_cambio", Cambio.getTipo_Cambio());
            statement.setString("pid_matricula", Cambio.getId_Alumno());      
            statement.execute();
            con.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }
    
    public ArrayList<ClsEntidadPgoCambio> ListarCambio(String idalumno,String tipoo)
    {
        ArrayList<ClsEntidadPgoCambio> Cambio = new ArrayList<ClsEntidadPgoCambio>();

        try
        {
            CallableStatement st = con.prepareCall("{call sp_S_PgoCambio(?,?)}");
            st.setString("pbusqueda", idalumno);
            st.setString("ptipo", tipoo); 
            
            ResultSet rs = st.executeQuery();
            while (rs.next())
            { 
            ClsEntidadPgoCambio cambio=new ClsEntidadPgoCambio();
            cambio.setId_Cambio(rs.getString("id_cambio"));
            cambio.setFecha_Cambio(rs.getString("fecha_cambio")); 
            cambio.setMonto_Cambio(rs.getString("monto_cambio"));
            cambio.setObs_Cambio(rs.getString("obs_cambio"));            
            cambio.setTipo_Cambio(rs.getString("tipo_cambio"));
            cambio.setId_Alumno(rs.getString("id_alu"));
            cambio.setApeNombre_Alumno(rs.getString("apellidos_nombres"));
            Cambio.add(cambio);
            }
            return Cambio;            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    
    
}
