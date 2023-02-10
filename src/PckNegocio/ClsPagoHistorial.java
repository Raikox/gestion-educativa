/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckNegocio;

import PckEntidad.ClsEntidadPagoHistorial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email jkevin.mv@gmail.com
 */
public class ClsPagoHistorial {

    
    public void CrearPagoHistorial
        (
            ClsEntidadPagoHistorial xClsEntidadPagoHistorial, 
            Connection xConexion
        ) {
        
        try
        {            
            PreparedStatement st = xConexion.prepareStatement("insert into "
                    + "pgo_historial_autorizado (concepto_ha, importe_ha, fecha_ha, personal_id)  "
                    + "values (?, ?, ?, ?)");
            st.setString(1, xClsEntidadPagoHistorial.getConcepto_ha());
            st.setString(2, xClsEntidadPagoHistorial.getImporte_ha());
            st.setString(3, xClsEntidadPagoHistorial.getFecha_ha());
            st.setString(4, xClsEntidadPagoHistorial.getPersonal_id());
            st.executeUpdate();            
        }
        catch(SQLException ex)
        {
            Logger.getLogger(ClsPagoAula.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        
    public ResultSet SeleccionarPagoHistorial(Connection xConexion) throws Exception{
    ResultSet rs;
    try
    {            
        PreparedStatement st = xConexion.prepareStatement("select ha.id_ha, concat(p.personal_apellido_paterno,' ',p.personal_apellido_materno,', ',p.personal_nombre) as 'nombres',\n" +
        "ha.concepto_ha, ha.importe_ha, ha.fecha_ha\n" +
        "from pgo_historial_autorizado as ha inner join adm_personal as p\n" +
        "on ha.personal_id = p.personal_id ");   
      
        rs = st.executeQuery();            
        return rs;
    }
    catch(SQLException ex)
    {
        throw ex;            
    }
    }
}
