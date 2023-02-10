/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckNegocio;

import PckConexion.ClsConexion;
import PckEntidad.ClsEntidadServicio;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author PC01
 */
public class ClsServicio {
    
    private Connection con = new ClsConexion().getConection();
    
     public void AgregarServicio(ClsEntidadServicio Servicio){
        try{
            CallableStatement statement = con.prepareCall("{call sp_I_PgoServicio(?,?,?,?)}");
            statement.setString("pnombre_servicio", Servicio.getNombre_Servicio());
            statement.setString("pprecio_servicio", Servicio.getPrecio_Servicio());
            statement.setString("pdesc_servicio", Servicio.getDescripcion_Servicio());
            statement.setString("panio_servicio", Servicio.getAnio_Servicio());
            statement.execute();           
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }
    
    public void ModificarServicio(ClsEntidadServicio Servicio,String codigo){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_PgoServicio(?,?,?,?,?)}");
            statement.setString("pid_servicio", codigo); 
            statement.setString("pnombre_servicio", Servicio.getNombre_Servicio());
            statement.setString("pprecio_servicio", Servicio.getPrecio_Servicio());
            statement.setString("pdesc_servicio", Servicio.getDescripcion_Servicio());
            statement.setString("panio_servicio", Servicio.getAnio_Servicio());
            statement.execute();           
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    } 
     public ResultSet SeleccionarServicio(String codigo)  throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_PgoServicio(?)}");
            statement.setString("codigo", codigo);            
            rs = statement.executeQuery();
            return rs;
            
        }catch(SQLException ex){
            throw ex;
        }
      }
    public ArrayList<ClsEntidadServicio> ListarServicio(){
        ArrayList<ClsEntidadServicio> Servicio = new ArrayList<ClsEntidadServicio>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_L_PgoServicio}");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadServicio servicio = new ClsEntidadServicio();
                servicio.setId_Servicio(rs.getString("id_servicio"));
                servicio.setNombre_Servicio(rs.getString("nombre_servicio"));
                servicio.setPrecio_Servicio(rs.getString("precio_servicio"));
                servicio.setDescripcion_Servicio(rs.getString("desc_servicio")); 
                servicio.setAnio_Servicio(rs.getString("anio_servicio"));
                Servicio.add(servicio);
            }            
            return Servicio;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    public ArrayList<ClsEntidadServicio> ListarServicioAlumno(String codAlu){
        ArrayList<ClsEntidadServicio> Servicio = new ArrayList<ClsEntidadServicio>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_S_PgoServicioAlumno(?)}");
            statement.setString("pbusqueda", codAlu);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadServicio servicio = new ClsEntidadServicio();  
                servicio.setId_Servicio(rs.getString("id_deudas"));
                servicio.setNombre_Servicio(rs.getString("nombre_servicio"));                              
                Servicio.add(servicio);
            }            
            return Servicio;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public void EliminarServicio(String codigo,Connection con)
    {
        try
        {
            PreparedStatement st = con.prepareStatement("delete from pgo_servicio "
                    + "where id_servicio = ?");            
            st.setString(1, codigo);
            st.executeUpdate();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(ClsPersonal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "El servicio no puede ser borrado, esta asignado a un alumno o boleta","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
