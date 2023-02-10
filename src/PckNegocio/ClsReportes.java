/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckNegocio;

import PckConexion.*;
import PckEntidad.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClsReportes {
    private Connection con = new ClsConexion().getConection();
    
    public ResultSet AlumnoPorSalon(String busqueda) throws Exception{
    ResultSet rs = null;
    try{
       CallableStatement statement = con.prepareCall("{call sp_Excel_AlumnoSalon(?)}");
       statement.setString("pbusqueda", busqueda);
       rs = statement.executeQuery();
    
       return rs;
    }catch(SQLException ex){
     throw ex;
    }
    }
    
    
}
