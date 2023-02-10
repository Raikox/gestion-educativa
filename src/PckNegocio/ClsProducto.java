/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckNegocio;

import PckConexion.ClsConexion;
import PckEntidad.ClsEntidadProducto;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author PC01
 */
public class ClsProducto {
    private Connection con = new ClsConexion().getConection();
    
     public void AgregarProducto(ClsEntidadProducto Producto){
        try{
            CallableStatement statement = con.prepareCall("{call sp_I_PgoProducto(?,?,?,?,?)}");
            statement.setString("pnombre_producto", Producto.getNombre_Producto());
            statement.setString("pprecio_producto", Producto.getPrecio_Producto());
            statement.setString("pstock_producto", Producto.getStock_Producto());
            statement.setString("pdesc_producto", Producto.getDescripcion_Producto());
            statement.setString("panio_producto", Producto.getAnio_Producto());
            statement.execute();
           
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }
     
     public void ModificaProducto(ClsEntidadProducto Producto,String codigo){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_PgoProducto(?,?,?,?,?,?)}");
            statement.setString("pid_producto", codigo);
            statement.setString("pnombre_producto", Producto.getNombre_Producto());
            statement.setString("pprecio_producto", Producto.getPrecio_Producto());
            statement.setString("pstock_producto", Producto.getStock_Producto());
            statement.setString("pdesc_producto", Producto.getDescripcion_Producto());
            statement.setString("panio_producto", Producto.getAnio_Producto());
            statement.execute();
           
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }
     
     public ArrayList<ClsEntidadProducto> ListarProducto(){
        ArrayList<ClsEntidadProducto> Producto = new ArrayList<ClsEntidadProducto>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_L_PgoProducto}");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadProducto producto = new ClsEntidadProducto();
                producto.setId_Producto(rs.getString("id_producto"));
                producto.setNombre_Producto(rs.getString("nombre_producto"));
                producto.setPrecio_Producto(rs.getString("precio_producto"));
                producto.setStock_Producto(rs.getString("stock_producto"));
                producto.setDescripcion_Producto(rs.getString("desc_producto"));
                producto.setAnio_Producto(rs.getString("anio_producto")); 
                Producto.add(producto);
            }
            
            return Producto;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
     
      public ResultSet SeleccionarProducto(String codigo)  throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_PgoProducto(?)}");
            statement.setString("codigo", codigo);            
            rs = statement.executeQuery();
            return rs;
            
        }catch(SQLException ex){
            throw ex;
        }
      }
      
      public ArrayList<ClsEntidadProducto> ListarProductoAlumno(String codAlu){
        ArrayList<ClsEntidadProducto> Producto = new ArrayList<ClsEntidadProducto>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_S_PgoProductoAlumno(?)}");
            statement.setString("pbusqueda", codAlu);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadProducto producto = new ClsEntidadProducto();  
                producto.setId_Producto(rs.getString("id_deudap"));
                producto.setNombre_Producto(rs.getString("nombre_producto"));                              
                Producto.add(producto);
            }            
            return Producto;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
      
      public void ModificaStock(ClsEntidadProducto Producto,String codigo){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_PgoProductoStock(?,?)}");
            statement.setString("pid_producto", codigo);                       
            statement.setString("pstock_producto", Producto.getStock_Producto());                    
            statement.execute();
           
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }
      
      public void EliminarProducto(String codigo,Connection con)
    {
        try
        {
            PreparedStatement st = con.prepareStatement("delete from pgo_producto "
                    + "where id_producto = ?");            
            st.setString(1, codigo);
            st.executeUpdate();
        }
        catch (SQLException ex) 
        {
            //Logger.getLogger(ClsPersonal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "El producto no puede ser borrado, esta asignado a un alumno o boleta","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
   
}
