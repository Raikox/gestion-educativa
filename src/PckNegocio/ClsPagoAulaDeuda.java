/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PckNegocio;

import PckEntidad.ClsEntidadPagoAulaDeuda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Kevin
 */
public class ClsPagoAulaDeuda {
    
    
    public void CrearPagoAulaDeuda
        (
            ClsEntidadPagoAulaDeuda xClsEntidadPagoAulaDeuda, 
            Connection xConexion
        ) {
        
        try
        {            
            PreparedStatement st = xConexion.prepareStatement("insert into "
                    + "mps_pgo_aula_deuda (pgo_aula_deuda_estado, id_matricula, pgo_aula_id)  "
                    + "values ('NO', ?, ?)");
            st.setString(1, xClsEntidadPagoAulaDeuda.getId_matricula());
            st.setString(2, xClsEntidadPagoAulaDeuda.getPgo_aula_id());
            
            st.executeUpdate();            
        }
        catch(SQLException ex)
        {
            Logger.getLogger(ClsPagoAulaDeuda.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        
    public void CrearPagoAulaDeudaRelacion
        (
            String pgo_aula_id, 
            String id_seccion, 
            Connection xConexion
        ) {
        
        try
        {            
            PreparedStatement st = xConexion.prepareStatement("insert into "
                    + "mps_pgo_aula_relacion (pgo_aula_id, id_seccion)  "
                    + "values (?, ?)");
            st.setString(1, pgo_aula_id);
            st.setString(2, id_seccion);
            
            st.executeUpdate();            
        }
        catch(SQLException ex)
        {
            Logger.getLogger(ClsPagoAulaDeuda.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        
    public void EditarPagoAulaDeuda
        (
            ClsEntidadPagoAulaDeuda xClsEntidadPagoAulaDeuda, 
            Connection xConexion
        ) {
        
        try
        {            
            PreparedStatement st = xConexion.prepareStatement("update mps_pgo_aula_deuda set\n" 
                        + "pgo_aula_deuda_estado = ?\n" 
                        + "where pgo_aula_deuda_id = ?");
            st.setString(1, xClsEntidadPagoAulaDeuda.getPgo_aula_deuda_estado());
            st.setString(2, xClsEntidadPagoAulaDeuda.getPgo_aula_deuda_id());
            
            st.executeUpdate();            
        }
        catch(SQLException ex)
        {
            Logger.getLogger(ClsPagoAulaDeuda.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
        
        
      //ESTE ES IGUAL A SELECCIONAR PAGO AULA DEUDA // O AL MENOS DEBERIA  
    public ArrayList<ClsEntidadPagoAulaDeuda> ListarRequerimientoAula(String salon,String pgo_aula_id, Connection con) {
        ArrayList<ClsEntidadPagoAulaDeuda> Aula = new ArrayList<ClsEntidadPagoAulaDeuda>();
        try
        {
            ResultSet rs;
            PreparedStatement st = con.prepareStatement("select ad.*, "
                    + "concat(a.apellidop_alumno,\" \",a.apellidom_alumno, \", \",a.nombre_alumno) as 'apellidos_nombres',\n" 
                    + "m.id_matricula "
                    + "from mps_pgo_aula_deuda as ad inner join mat_matricula as m\n" 
                    + "on ad.id_matricula = m.id_matricula inner join mat_seccion as s\n" 
                    + "on m.seccion_id = s.id_seccion inner join mat_alumno as a\n" 
                    + "on m.MPS_Alumno_id_alumno = a.id_alumno \n"                 
                    + "where s.id_seccion = ? and ad.pgo_aula_id = ? and m.retiro = 'NO'\n" 
                    + "order by a.apellidop_alumno");
            st.setString(1, salon);
            st.setString(2, pgo_aula_id);
            rs = st.executeQuery(); 
            while (rs.next())
            {
                ClsEntidadPagoAulaDeuda aula = new ClsEntidadPagoAulaDeuda(
                            rs.getString("pgo_aula_deuda_id"),
                            rs.getString("pgo_aula_deuda_estado"),
                            rs.getString("id_matricula"),
                            rs.getString("pgo_aula_id")
                );
                
                Aula.add(aula);
            }            
            return Aula;
        } 
        catch(SQLException ex)
        {
            Logger.getLogger(ClsPagoAulaDeuda.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }    
    
    public ResultSet SeleccionarPagoAulaDeuda(String xAulaId, String xItemId, Connection xConexion) throws SQLException {
        
        ResultSet rsResultado;
        try
        {            
            PreparedStatement st = xConexion.prepareStatement("select ad.*, "
                    + "concat(a.apellidop_alumno,\" \",a.apellidom_alumno, \", \",a.nombre_alumno) as 'apellidos_nombres',\n" 
                    + "m.id_matricula, concat(pl.personal_apellido_paterno,' ',pl.personal_apellido_materno,', ',pl.personal_nombre) "
                    + "as 'docente' "
                    + "from mps_pgo_aula_deuda as ad inner join mat_matricula as m\n" 
                    + "on ad.id_matricula = m.id_matricula inner join mat_seccion as s\n" 
                    + "on m.seccion_id = s.id_seccion inner join mat_alumno as a\n" 
                    + "on m.MPS_Alumno_id_alumno = a.id_alumno inner join adm_personal as pl\n"
                    + "on s.MPS_Profesor_id_profesor = pl.personal_id "
                    + "where s.id_seccion = ? and ad.pgo_aula_id = ? and m.retiro = 'NO' \n" 
                    + "order by a.apellidop_alumno");
            st.setString(1, xAulaId);
            st.setString(2, xItemId);
            rsResultado = st.executeQuery();  
            
            return rsResultado;
        }
        catch(SQLException ex)
        {
            throw ex;
        }
        
    }
}
