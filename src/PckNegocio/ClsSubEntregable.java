/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PckNegocio;

import PckEntidad.ClsEntidadSubEntregable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kevin
 */
public class ClsSubEntregable {
    
    public void AgregarEntregable(ClsEntidadSubEntregable Entregable, Connection con){
        try{
            String nombre = Entregable.getSubentregable_nombre();
            String estado=Entregable.getSubentregable_estado();
            String mensaje=Entregable.getSubentregable_mensaje();
            String idEntregable=Entregable.getEntregable_id();
            Statement statement = con.createStatement();     
            statement.executeUpdate("insert into ent_subentregable "
                    + "(subentregable_nombre,subentregable_estado,"
                    + "subentregable_mensaje,entregable_id) values "
                    + "('"+nombre+"','"+estado+"','"+mensaje+"','"+idEntregable+"')");            
        }catch(SQLException ex){
            Logger.getLogger(ClsEntregable.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
     public void BorrarSubEntregableFiltro(String xPeriodo,Connection con){
        try{
            
            Statement statement = con.createStatement();     
            statement.executeUpdate("delete se.* from ent_subentregable as se \n" +
                "inner join ent_entregable as e on se.entregable_id = e.entregable_id\n" +
                "inner join mat_seccion as s on e.id_seccion = s.id_seccion \n" +
                "where s.MPS_Periodo_id_periodo = "+xPeriodo);
        }catch(SQLException ex){
            Logger.getLogger(ClsEntregable.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
     
     public void EditarSubEntregableEstado(String xIdEntregable,String xEstado,Connection con){
        try
        { 
            System.out.println("Editar: "+xIdEntregable+" "+xEstado);
            Statement st = con.createStatement();             
            st.executeUpdate("update ent_subentregable as s set \n" +
                        "s.subentregable_estado = '"+xEstado+"' \n" +
                        "where s.subentregable_id = "+xIdEntregable);
        }catch(SQLException ex){
            Logger.getLogger(ClsEntregable.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
     
    public void EditarSubEntregableFecha(String xFecha, String xNombreSubEntregable,
                                String xNombreEntregable, Connection con) {
         
        try
        {               
            PreparedStatement st = con.prepareStatement("update ent_subentregable as s inner join ent_entregable as e\n" +
                                        "on s.entregable_id = e.entregable_id\n" +
                                        "set\n" +
                                        "s.subentregable_fecha = (STR_TO_DATE(REPLACE((?),'/','.') ,GET_FORMAT(date,'EUR')))\n" +
                                        "where s.subentregable_nombre = (?) and\n" +
                                        "e.entregable_nombre = (?)"); 
                        
            st.setString(1, xFecha);
            st.setString(2, xNombreSubEntregable);
            st.setString(3, xNombreEntregable);
            st.executeUpdate();
        }catch(SQLException ex){
            Logger.getLogger(ClsEntregable.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
     
     public void EditarSubEntregableEstadoComentario(String xIdEntregable, String xEstado,
                                String xComentario, Connection con) {
         
        try
        {               
            PreparedStatement st = con.prepareStatement("update ent_subentregable as s set\n" +
                        "s.subentregable_estado = (?),\n" +
                        "s.subentregable_mensaje = (?) \n" +
                        "where s.subentregable_id = (?)");
            st.setString(1, xEstado);
            st.setString(2, xComentario);
            st.setString(3, xIdEntregable);
            st.executeUpdate();
        }catch(SQLException ex){
            Logger.getLogger(ClsEntregable.class.getName()).log(Level.SEVERE, null, ex);
        }    
        
    }
     
     
     public ArrayList<ClsEntidadSubEntregable> SeleccionarSubEntregables(String xIdSeccion,Connection con)
    {
        ArrayList<ClsEntidadSubEntregable> Sube = new ArrayList<>();
      try{
            PreparedStatement statement = con.prepareStatement("select * from ent_subentregable as se \n" +
                "inner join ent_entregable as e on se.entregable_id = e.entregable_id\n" +
                "where e.id_seccion = (?)");
            statement.setString(1, xIdSeccion);            
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadSubEntregable sube = new ClsEntidadSubEntregable();
                sube.setSubentregable_id(rs.getString("subentregable_id"));
                sube.setSubentregable_nombre(rs.getString("subentregable_nombre")); 
                sube.setSubentregable_estado(rs.getString("subentregable_estado"));
                sube.setSubentregable_fecha(rs.getString("subentregable_fecha"));
                sube.setSubentregable_mensaje(rs.getString("subentregable_mensaje"));
                Sube.add(sube);
            }           
            return Sube;
        } catch(SQLException ex){
            Logger.getLogger(ClsEntregable.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
     
     
     
}
