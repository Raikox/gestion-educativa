/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckNegocio;

import PckConexion.ClsConexion;

import PckEntidad.ClsEntidadComida;
import PckEntidad.ClsEntidadMatMen;
import PckEntidad.ClsEntidadMen;
import PckEntidad.ClsEntidadPeriodo;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author kmv
 */
public class ClsComida {
    private Connection con = new ClsConexion().getConection();
    
    
      
      //COMIDAAAAAAAAAAAAAAAA
      

     //MODIFICANDO PENDIENTES      

      /////////////////////
      public ResultSet SeleccionarComidaPago(String codigo,String mes)  throws Exception{
        ResultSet rs = null;
        try
        {
            CallableStatement statement = con.prepareCall("{call sp_S_ComidaPago(?,?)}");
            statement.setString("pbusqueda", codigo);
            statement.setString("pmes", mes); 
            rs = statement.executeQuery();
            return rs;
            
        }catch(SQLException ex){
            throw ex;
        }
    }
      
      public ArrayList<ClsEntidadComida> ListarMesesComida(String codigo){
        ArrayList<ClsEntidadComida> Mens = new ArrayList<ClsEntidadComida>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_L_MesesComida(?)}");
            statement.setString("pbusqueda", codigo);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadComida men = new ClsEntidadComida();
                men.setId_Comida(rs.getString("id_comida"));
                men.setMes_Comida(rs.getString("mes_comida"));
//                men.setId_Alumno(rs.getString("id_alumno"));
                
                Mens.add(men);
                 
            }
           
            return Mens;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
      
        
     
}
