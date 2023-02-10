/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckNegocio;


import PckEntidad.ClsEntidadPorSalon;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kmv
 */
public class ClsPorSalon {
   
    
     public ArrayList<ClsEntidadPorSalon> ListarNinosPorSalon(String Salon,Connection con){
        ArrayList<ClsEntidadPorSalon> Deudas = new ArrayList<ClsEntidadPorSalon>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_R_NinosPorSalon(?)}");
            statement.setString("pbusqueda", Salon);            
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadPorSalon deuda = new ClsEntidadPorSalon();
                deuda.setApellidos(rs.getString("apellidos_alumno"));
                deuda.setNombres(rs.getString("nombre_alumno"));
                deuda.setComida(rs.getString("comida_alumno"));
                deuda.setEntrada(rs.getString("horarioe_matricula"));
                deuda.setSalida(rs.getString("horarios_matricula"));
                deuda.setPeriodo(rs.getString("nombre_periodo"));
                deuda.setNivel(rs.getString("nivel_nombre"));
                deuda.setEdad(rs.getString("edadanio_matricula"));
                deuda.setNacimiento(rs.getString("fecha_nacimiento"));
                Deudas.add(deuda);                
            }
            con.close();
            return Deudas;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
     
     public ClsEntidadPorSalon produce(String Apellidos, String Nombres,String Comida,String Entrada,String Salida, 
             String Periodo,String Nivel,String Edad, String Nacimiento) {
        ClsEntidadPorSalon dataBean = new ClsEntidadPorSalon();
        dataBean.setApellidos(Apellidos);
        dataBean.setNombres(Nombres);
        dataBean.setComida(Comida);
        dataBean.setEntrada(Entrada);
        dataBean.setSalida(Salida);
        dataBean.setPeriodo(Periodo);
        dataBean.setNivel(Nivel);
        dataBean.setEdad(Edad);
        dataBean.setNacimiento(Nacimiento);
        return dataBean;
    }
}
