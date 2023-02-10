/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckNegocio;


import PckEntidad.ClsEntidadEgresoReporte;
import PckEntidad.ClsEntidadEgresos;
import PckEntidad.ClsEntidadIngEgre;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kmv
 */
public class ClsIngresosEgresos {
    
    
    
    public ArrayList<ClsEntidadIngEgre> SeleccionarIngresos(String fecha,Connection con)
    {
        ArrayList<ClsEntidadIngEgre> Ingresos = new ArrayList<ClsEntidadIngEgre>();
        try
        {
            CallableStatement st = con.prepareCall("{call sp_S_Ingresos(?)}");
            st.setString("fecha", fecha);           
            ResultSet rs = st.executeQuery();
            while (rs.next())
            { 
            ClsEntidadIngEgre ingreso=new ClsEntidadIngEgre();
            ingreso.setNiño(rs.getString("apellidos_nombres"));
            ingreso.setConcepto(rs.getString("obs_histo"));
            ingreso.setSeccion(rs.getString("nombre_seccion"));
            ingreso.setRecibo(rs.getString("num_boleta"));
            ingreso.setMonto(rs.getString("monto"));
            ingreso.setTipo(rs.getString("tipo_detalle"));
            ingreso.setId_Boleta(rs.getString("id_boleta"));   
            ingreso.setTipo_pago(rs.getString("tipo_pago"));
            Ingresos.add(ingreso);
            }
            return Ingresos;            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<ClsEntidadEgresos> SeleccionarEgresos(String fecha,Connection con)
    {
        ArrayList<ClsEntidadEgresos> Egresos = new ArrayList<ClsEntidadEgresos>();
        try
        {
            CallableStatement st = con.prepareCall("{call sp_S_Egresos(?)}");
            st.setString("fecha", fecha);           
            ResultSet rs = st.executeQuery();
            while (rs.next())
            { 
            ClsEntidadEgresos egreso=new ClsEntidadEgresos();
            egreso.setId_Egreso(rs.getString("id_egreso"));
            egreso.setNombre_Persona(rs.getString("persona"));
            egreso.setConcepto_Egreso(rs.getString("concepto_egreso"));
            egreso.setDescripcion_Egreso(rs.getString("desc_egreso"));
            egreso.setMonto_Egreso(rs.getString("monto_egreso"));
//            egreso.setFecha_Egreso(rs.getString("fecha"));
                    
            Egresos.add(egreso);
            }
            return Egresos;            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<ClsEntidadEgresoReporte> SeleccionarEgresoReporte(String fecha,Connection con)
    {
        ArrayList<ClsEntidadEgresoReporte> Egresos = new ArrayList<ClsEntidadEgresoReporte>();
        try
        {
            CallableStatement st = con.prepareCall("{call sp_S_Egresos(?)}");
            st.setString("fecha", fecha);           
            ResultSet rs = st.executeQuery();
            while (rs.next())
            { 
            ClsEntidadEgresoReporte egreso=new ClsEntidadEgresoReporte();
            
            egreso.setPersona(rs.getString("persona"));
            egreso.setConcepto(rs.getString("concepto_egreso"));
            egreso.setDescripcion(rs.getString("desc_egreso"));
            egreso.setMonto(rs.getString("monto_egreso"));
//            egreso.setFecha_Egreso(rs.getString("fecha"));
                    
            Egresos.add(egreso);
            }
            return Egresos;            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    
    
    
    
}
