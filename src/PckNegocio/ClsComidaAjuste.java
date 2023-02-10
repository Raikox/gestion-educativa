/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PckNegocio;

import PckEntidad.ClsEntidadComidaAjuste;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kevin
 */
public class ClsComidaAjuste {
    
    public void InsertarComidaAjuste
        (
            ClsEntidadComidaAjuste comidaAjuste, 
            Connection xConexion
        ) {
        
        try
        {            
            PreparedStatement st = xConexion.prepareStatement("insert into pgo_comida_ajuste"
                    + " (comida_ajuste_fecha,id_comida)\n" 
                    + "values((STR_TO_DATE(REPLACE(?,'/','.') ,GET_FORMAT(date,'EUR'))),"
                    + "?)");
            st.setString(1, comidaAjuste.getComida_ajuste_fecha());
            st.setString(2, comidaAjuste.getId_comida());            
            st.executeUpdate();            
        }
        catch(SQLException ex)
        {
            Logger.getLogger(ClsComidaAjuste.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
