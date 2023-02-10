/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckNegocio;

import PckConexion.ClsConexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 */
public class ClsGenerar {
   
    
    public ResultSet SeleccionarPagoMatricula(String codigo,Connection con)  throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_PagoMatricula(?)}");
            statement.setString("pid_matricula", codigo);
            rs = statement.executeQuery();
            return rs;
            
        }catch(SQLException ex){
            throw ex;
        }
    }
}
