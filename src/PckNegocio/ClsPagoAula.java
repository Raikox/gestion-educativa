/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PckNegocio;

import PckEntidad.ClsEntidadPagoAula;
import PckEntidad.ClsEntidadPagoHistorial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kevin
 */
public class ClsPagoAula {
    
    public ResultSet SeleccionarPagoAulaRelacion(String xAulaId,Connection xConexion) throws Exception {
        
        ResultSet rs;
        try
        {            
            PreparedStatement st = xConexion.prepareStatement("select distinct pa.pgo_aula_id, "
                    + "pa.pgo_aula_nombre, pa.pgo_aula_anio\n" 
                    + "from mps_pgo_aula_relacion as ar inner join mps_pgo_aula as pa "
                    + "on ar.pgo_aula_id = pa.pgo_aula_id\n" 
                    + "where ar.id_seccion = ?");            
            st.setString(1, xAulaId);
            rs = st.executeQuery();            
            return rs;
        }
        catch(SQLException ex)
        {
            throw ex;            
        }
        
    } 
        
    public ResultSet SeleccionarPagoAula(Connection xConexion) throws Exception{
        ResultSet rs;
        try
        {            
            PreparedStatement st = xConexion.prepareStatement("select * from "
                    + "mps_pgo_aula");            
            rs = st.executeQuery();            
            return rs;
        }
        catch(SQLException ex)
        {
            throw ex;            
        }
    } 
    
    public ResultSet SeleccionarPagoAulaItem(Connection xConexion, String xId) throws Exception{
        ResultSet rs;
        try
        {            
            PreparedStatement st = xConexion.prepareStatement("select * from "
                    + "mps_pgo_aula where pgo_aula_id = ?");   
            st.setString(1,xId);
            rs = st.executeQuery();            
            return rs;
        }
        catch(SQLException ex)
        {
            throw ex;            
        }
    } 
    
    public void CrearPagoAula
        (
            ClsEntidadPagoAula xClsEntidadPagoAula, 
            Connection xConexion
        ) {
        
        try
        {            
            PreparedStatement st = xConexion.prepareStatement("insert into "
                    + "mps_pgo_aula (pgo_aula_nombre, pgo_aula_anio)  "
                    + "values (?, ?)");
            st.setString(1, xClsEntidadPagoAula.getPgo_aula_nombre());
            st.setString(2, xClsEntidadPagoAula.getPgo_aula_anio());
            
            st.executeUpdate();            
        }
        catch(SQLException ex)
        {
            Logger.getLogger(ClsPagoAula.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        
    public void EditarPagoAula
    (
        String xCodigo,
        String xNombre,
        String xAnio,
        Connection xConexion
    ) {
        
        try
        {            
            PreparedStatement st = xConexion.prepareStatement("update mps_pgo_aula set\n" 
                        + "pgo_aula_nombre = ?,\n" 
                        + "pgo_aula_anio = ?\n" 
                        + "where pgo_aula_id = ?");
            st.setString(1, xNombre);
            st.setString(2, xAnio);
            st.setString(3, xCodigo);
            
            st.executeUpdate();            
        }
        catch(SQLException ex)
        {
            Logger.getLogger(ClsPagoAula.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void EditarPagoAulaEstado
    (
        String xEstado,
        String xCodigo,
        Connection xConexion
    ) {
        
        try
        {            
            PreparedStatement st = xConexion.prepareStatement("update mps_pgo_aula set\n" 
                        + "pgo_aula_estado = ? \n" 
                        + "where pgo_aula_id = ?");
            st.setString(1, xEstado);            
            st.setString(2, xCodigo);
            st.executeUpdate();            
        }
        catch(SQLException ex)
        {
            Logger.getLogger(ClsPagoAula.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
}
