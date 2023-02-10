/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckNegocio;

import PckConexion.*;
import PckEntidad.*;
import java.awt.Image;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class ClsAdjunto {
    private Connection con = new ClsConexion().getConection();
    
    public void AgregarAdjunto(ClsEntidadAdjunto Adjunto){
        try{
            CallableStatement statement = con.prepareCall("{call sp_I_Adjunto(?,?,?,?,?,?)}");
            statement.setString("ppartida_nac",Adjunto.getPartida_Nacimiento() );
            statement.setString("pdni_padre", Adjunto.getDni_Padre());
            statement.setString("pdni_madre", Adjunto.getDni_Madre());
            statement.setString("pdni_menor", Adjunto.getDni_Menor());
            statement.setString("ptarjeta_vac", Adjunto.getTarjeta_Vacunacion());
            statement.execute();
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }
    
    public void ModificarAdjunto(String codigo,ClsEntidadAdjunto Adjunto){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_Adjunto(?,?,?,?,?,?,?)}");
            statement.setString("pid_adjunto", codigo);
              statement.setString("ppartida_nac",Adjunto.getPartida_Nacimiento() );
            statement.setString("pdni_padre", Adjunto.getDni_Padre());
            statement.setString("pdni_madre", Adjunto.getDni_Madre());
            statement.setString("pdni_menor", Adjunto.getDni_Menor());
            statement.setString("ptarjeta_vac", Adjunto.getTarjeta_Vacunacion());
            statement.execute();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public void EliminarAdjunto(String codigo){
        try{
            CallableStatement statement = con.prepareCall("{call sp_D_Adjunto(?)}");
            statement.setString("pid_adjunto",codigo);
            statement.execute();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public ArrayList<ClsEntidadAdjunto> ListarAdjunto(){
        ArrayList<ClsEntidadAdjunto> Adjuntos = new ArrayList<ClsEntidadAdjunto>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_L_Adjunto}");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadAdjunto adjunto = new ClsEntidadAdjunto();
                adjunto.setId_Adjunto(rs.getString("id_adjunto"));
                adjunto.setPartida_Nacimiento(rs.getString("partida_nac"));
                adjunto.setDni_Padre(rs.getString("dni_padre"));
                adjunto.setDni_Madre(rs.getString("dni_madre"));
                adjunto.setDni_Menor(rs.getString("dni_menor"));
                adjunto.setTarjeta_Vacunacion("tarjeta_vac");
                adjunto.setOtro("Otro");
                Adjuntos.add(adjunto);
            }
            return Adjuntos;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public ResultSet SeleccionarAdjunto(String codigo)  throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_Adjunto(?)}");
            statement.setString("pid_apoderado", codigo);
            rs = statement.executeQuery();
            return rs;
        }catch(SQLException ex){
            throw ex;
        }
    }
    
    
    
    
}
