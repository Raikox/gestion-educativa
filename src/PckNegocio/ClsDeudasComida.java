/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PckNegocio;

import PckConexion.ClsConexion;
import PckEntidad.ClsEntidadDeudasComida;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Sistemas
 */
public class ClsDeudasComida {
    private Connection con = new ClsConexion().getConection();
    
    public ArrayList<ClsEntidadDeudasComida> ListarDeudasComida(String mes, String codperiodo)
    {
        ArrayList<ClsEntidadDeudasComida> Comida = new ArrayList<ClsEntidadDeudasComida>();

        try
        {
            CallableStatement st = con.prepareCall("{call sp_L_DeudasComidas(?,?)}");
            st.setString("mes", mes);
            st.setString("periodo", codperiodo); 
            ResultSet rs = st.executeQuery();
            while (rs.next())
            {
            ClsEntidadDeudasComida comida=new ClsEntidadDeudasComida();
            //id_comida,mes_comida,monto_comida,total_comida,pendiente_comida,id_periodo,periodo_alu
            comida.setId_Comida(rs.getString("id_comida"));
            comida.setMes_Comida(rs.getString("mes_comida"));
            comida.setMonto_Comida(rs.getString("monto_comida"));
            comida.setTotal_Comida(rs.getString("total_comida"));
            comida.setPendiente_Comida(rs.getString("pendiente_comida"));
            comida.setId_Periodo(rs.getString("id_periodo"));
            //comida.setPeriodo_Alumno(rs.getString("periodo_alu"));
            Comida.add(comida);
            }
            return Comida;
            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    
    public void ModificarComidaPendiente(Double monto,String codigo){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_DeudasComidas(?,?)}");
            statement.setDouble("monto", monto);
            statement.setString("codigo", codigo);            
            statement.execute();
//            con.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
}
