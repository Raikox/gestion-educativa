/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckNegocio;

import PckConexion.ClsConexion;
import PckEntidad.ClsEntidadMora;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kmv
 */
public class ClsMora {
    private Connection con = new ClsConexion().getConection();
    
    
    public ArrayList<ClsEntidadMora> ListarMoraMatricula(String seccion){
        ArrayList<ClsEntidadMora> Mens = new ArrayList<ClsEntidadMora>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_S_MoraMatricula(?)}");
            statement.setString("pseccion_alu", seccion);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadMora men = new ClsEntidadMora();
                men.setAlumno(rs.getString("alumno"));
                men.setTotal(rs.getString("total_mat"));
                men.setPendiente(rs.getString("pendiente_mat"));
                men.setVencimiento(rs.getString("vencimiento")); 
                Mens.add(men);                 
            }           
            return Mens;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    public ArrayList<ClsEntidadMora> ListarMoraMensualidad(String seccion,String mes){
        ArrayList<ClsEntidadMora> Mens = new ArrayList<ClsEntidadMora>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_S_MoraMensualidad(?,?)}");
            statement.setString("pseccion_alu", seccion);
            statement.setString("pnombre_men", mes); 
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadMora men = new ClsEntidadMora();
                men.setAlumno(rs.getString("alumno"));
                men.setTotal(rs.getString("total_men"));
                men.setPendiente(rs.getString("pendiente_men"));
                men.setVencimiento(rs.getString("vencimiento")); 
                Mens.add(men);                 
            }           
            return Mens;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    public ArrayList<ClsEntidadMora> ListarMoraAlimentacion(String seccion,String mes){
        ArrayList<ClsEntidadMora> Mens = new ArrayList<ClsEntidadMora>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_S_MoraAlimentacion(?,?)}");
            statement.setString("pseccion_alu", seccion);
            statement.setString("pmes_comida", mes); 
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadMora men = new ClsEntidadMora();
                men.setAlumno(rs.getString("alumno"));
                men.setTotal(rs.getString("total_comida"));
                men.setPendiente(rs.getString("pendiente_comida"));
                men.setVencimiento(rs.getString("vencimiento")); 
                Mens.add(men);                 
            }           
            return Mens;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    public ArrayList<ClsEntidadMora> ListarMoraServicio(String seccion,String servicio){
        ArrayList<ClsEntidadMora> Mens = new ArrayList<ClsEntidadMora>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_S_MoraServicio(?,?)}");
            statement.setString("pseccion_alu", seccion);
            statement.setString("pnombre_servicio", servicio); 
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadMora men = new ClsEntidadMora();
                men.setAlumno(rs.getString("alumno"));
                men.setTotal(rs.getString("total_deudas"));
                men.setPendiente(rs.getString("pendiente_deudas"));
                men.setVencimiento(rs.getString("vencimiento")); 
                Mens.add(men);                 
            }           
            return Mens;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    public ArrayList<ClsEntidadMora> ListarMoraProducto(String seccion,String producto){
        ArrayList<ClsEntidadMora> Mens = new ArrayList<ClsEntidadMora>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_S_MoraProducto(?,?)}");
            statement.setString("pseccion_alu", seccion);
            statement.setString("pnombre_producto", producto); 
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadMora men = new ClsEntidadMora();
                men.setAlumno(rs.getString("alumno"));
                men.setTotal(rs.getString("total_deudap"));
                men.setPendiente(rs.getString("pendiente_deudap"));
                men.setVencimiento(rs.getString("vencimiento")); 
                Mens.add(men);                 
            }           
            return Mens;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    ///////////////////POR NIVEL/////////////////////////////
    
     public ArrayList<ClsEntidadMora> ListarMoraMatriculaNivel(String seccion){
        ArrayList<ClsEntidadMora> Mens = new ArrayList<ClsEntidadMora>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_S_MoraMatriculaNivel(?)}");
            statement.setString("pperiodo_alu", seccion);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadMora men = new ClsEntidadMora();
                men.setAlumno(rs.getString("alumno"));
                men.setTotal(rs.getString("total_mat"));
                men.setPendiente(rs.getString("pendiente_mat"));
                men.setVencimiento(rs.getString("vencimiento")); 
                men.setPeriodo(rs.getString("nombre_seccion")); 
                Mens.add(men);                 
            }           
            return Mens;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    public ArrayList<ClsEntidadMora> ListarMoraMensualidadNivel(String seccion,String mes){
        ArrayList<ClsEntidadMora> Mens = new ArrayList<ClsEntidadMora>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_S_MoraMensualidadNivel(?,?)}");
            statement.setString("pperiodo_alu", seccion);
            statement.setString("pnombre_men", mes); 
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadMora men = new ClsEntidadMora();
                men.setAlumno(rs.getString("alumno"));
                men.setTotal(rs.getString("total_men"));
                men.setPendiente(rs.getString("pendiente_men"));
                men.setVencimiento(rs.getString("vencimiento")); 
                men.setPeriodo(rs.getString("nombre_seccion"));
                Mens.add(men);                 
            }           
            return Mens;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    public ArrayList<ClsEntidadMora> ListarMoraAlimentacionNivel(String seccion,String mes){
        ArrayList<ClsEntidadMora> Mens = new ArrayList<ClsEntidadMora>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_S_MoraAlimentacionNivel(?,?)}");
            statement.setString("pperiodo_alu", seccion);
            statement.setString("pmes_comida", mes); 
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadMora men = new ClsEntidadMora();
                men.setAlumno(rs.getString("alumno"));
                men.setTotal(rs.getString("total_comida"));
                men.setPendiente(rs.getString("pendiente_comida"));
                men.setVencimiento(rs.getString("vencimiento")); 
                men.setPeriodo(rs.getString("nombre_seccion"));
                Mens.add(men);                 
            }           
            return Mens;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    public ArrayList<ClsEntidadMora> ListarMoraServicioNivel(String seccion,String servicio){
        ArrayList<ClsEntidadMora> Mens = new ArrayList<ClsEntidadMora>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_S_MoraServicioNivel(?,?)}");
            statement.setString("pperiodo_alu", seccion);
            statement.setString("pnombre_servicio", servicio); 
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadMora men = new ClsEntidadMora();
                men.setAlumno(rs.getString("alumno"));
                men.setTotal(rs.getString("total_deudas"));
                men.setPendiente(rs.getString("pendiente_deudas"));
                men.setVencimiento(rs.getString("vencimiento")); 
                men.setPeriodo(rs.getString("nombre_seccion"));
                Mens.add(men);                 
            }           
            return Mens;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    public ArrayList<ClsEntidadMora> ListarMoraProductoNivel(String seccion,String producto){
        ArrayList<ClsEntidadMora> Mens = new ArrayList<ClsEntidadMora>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_S_MoraProductoNivel(?,?)}");
            statement.setString("pperiodo_alu", seccion);
            statement.setString("pnombre_producto", producto); 
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadMora men = new ClsEntidadMora();
                men.setAlumno(rs.getString("alumno"));
                men.setTotal(rs.getString("total_deudap"));
                men.setPendiente(rs.getString("pendiente_deudap"));
                men.setVencimiento(rs.getString("vencimiento")); 
                men.setPeriodo(rs.getString("nombre_seccion"));
                Mens.add(men);                 
            }           
            return Mens;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
}
