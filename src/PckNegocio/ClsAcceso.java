/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckNegocio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email Jkevin.mv@gmail.com
 */
public class ClsAcceso {

    public ResultSet SeleccionarUsuario(String usuario,Connection con)  throws Exception
    {        
        ResultSet rs = null;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_LoginUsuario(?)}");
            statement.setString("usuario", usuario);                     
            rs = statement.executeQuery();            
            return rs;
            
        }catch(SQLException ex){
            throw ex;
        }
    }    
}
