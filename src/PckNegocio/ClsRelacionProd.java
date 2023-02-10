/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckNegocio;

import PckConexion.ClsConexion;
import PckEntidad.ClsEntidadRelacionProd;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author kmv
 */
public class ClsRelacionProd {
    private Connection con = new ClsConexion().getConection();
    public void AgregarRelacionProducto(ClsEntidadRelacionProd Producto){
        try{
            CallableStatement statement = con.prepareCall("{call sp_I_PgoRelacionProducto(?,?,?)}");
            statement.setString("pid_producto", Producto.getId_Producto());
            statement.setString("pid_matricula", Producto.getId_Alumno());
            statement.setString("pid_deudap", Producto.getId_Deuda_Producto());
            
            statement.execute(); 
            con.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }
    public void EliminarRelacionProducto(String codigo){
        try{
            CallableStatement statement = con.prepareCall("{call sp_D_PgoRelacionProducto(?)}");
            statement.setString("pid_deudap",codigo);
            statement.execute();
            con.close();
        }catch(SQLException ex){
            ex.printStackTrace();            
        }
    }
}
