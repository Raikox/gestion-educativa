/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckNegocio;

import PckConexion.ClsConexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 */
public class ClsPendienteBoleta {
    private Connection con = new ClsConexion().getConection();
    
    public void ModificarPendienteMatricula(String codigo,Double pnuevo,String opcion){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_PendienteMatriculaBoleta(?,?,?)}");
            statement.setString("pbusqueda", codigo);
            statement.setDouble("pnuevo", pnuevo);
            statement.setString("opcion", opcion);
            statement.execute();
            
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    public void ModificarPendienteMensualidad(String codigo,Double pnuevo,String opcion){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_PendienteMensualidadBoleta(?,?,?)}");
            statement.setString("pbusqueda", codigo);
            statement.setDouble("pnuevo", pnuevo);
            statement.setString("opcion", opcion);
            statement.execute();
            
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    public void ModificarPendienteComida(String codigo,Double pnuevo,String opcion){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_PendienteComidaBoleta(?,?,?)}");
            statement.setString("pbusqueda", codigo);
            statement.setDouble("pnuevo", pnuevo);
            statement.setString("opcion", opcion);
            statement.execute();
            
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    public void ModificarPendienteProducto(String codigo,Double pnuevo,String opcion){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_PendienteProductoBoleta(?,?,?)}");
            statement.setString("pbusqueda", codigo);
            statement.setDouble("pnuevo", pnuevo);
            statement.setString("opcion", opcion);
            statement.execute();
            
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    public void ModificarPendienteServicio(String codigo,Double pnuevo,String opcion){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_PendienteServicioBoleta(?,?,?)}");
            statement.setString("pbusqueda", codigo);
            statement.setDouble("pnuevo", pnuevo);
            statement.setString("opcion", opcion);
            statement.execute();
            
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
}
