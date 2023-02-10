/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mpsDAL;

import PckConexion.ClsConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import mpsEntity.RequerimientoEntity;


/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email jkevin.mv@gmail.com
 */
public class RequerimientoDAL {

    public ArrayList<RequerimientoEntity> ListarRequerimientos()
    {
        ArrayList<RequerimientoEntity> arrayRequerimiento = new ArrayList<>();
        Connection con = ClsConexion.getConection();
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sqlQuery = "select r.requerimiento_id, r.requerimiento_numero, r.requerimiento_fecha,\n" +
                        "r.requerimiento_tema, r.requerimiento_estado, s.id_seccion, s.nombre_seccion\n" +
                        "from mps_requerimiento as r inner join mat_seccion as s on\n" +
                        "r.id_seccion = s.id_seccion";
        
        try
        {
            pst = con.prepareStatement(sqlQuery);
            rs = pst.executeQuery();
                       
            while(rs.next())
            {
                RequerimientoEntity requerimientoEntity = new RequerimientoEntity();
                requerimientoEntity.setRequerimiento_id(rs.getInt("requerimiento_id"));
                requerimientoEntity.setRequerimiento_numero(rs.getString("requerimiento_numero"));
                requerimientoEntity.setRequerimiento_fecha(rs.getString("requerimiento_fecha"));
                requerimientoEntity.setRequerimiento_tema(rs.getString("requerimiento_tema"));
                requerimientoEntity.setRequerimiento_estado(rs.getString("requerimiento_estado"));
                requerimientoEntity.setId_seccion(rs.getInt("id_seccion"));
                requerimientoEntity.setNombre_seccion(rs.getString("nombre_seccion"));
                arrayRequerimiento.add(requerimientoEntity);
            }
        }
        catch(SQLException ex){
            Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex){
            Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try 
            {
                pst.close();
                rs.close();
                ClsConexion.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
        
        return arrayRequerimiento;
    }
    
    public void EliminarRequerimiento(int _IdRequerimiento)
    {
         Connection con = ClsConexion.getConection();
         PreparedStatement pst = null;
         PreparedStatement pst2 = null;
         String sqlQuery = "delete from mps_requerimiento_item "
                 + "where mps_requerimiento_item.requerimiento_id = ? ";
         
         String sqlQuery2 = "delete from mps_requerimiento "
                 + "where mps_requerimiento.requerimiento_id = ? ";
        try 
        {
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, _IdRequerimiento); 
            pst.executeUpdate();
            
            pst2 = con.prepareStatement(sqlQuery2);
            pst2.setInt(1, _IdRequerimiento); 
            pst2.executeUpdate();
            
        } catch (SQLException ex){
            Logger.getLogger(RequerimientoDAL.class.getName()).log(Level.SEVERE, null, ex);            
        }
        catch(Exception ex){
            Logger.getLogger(RequerimientoDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try 
            {
                pst.close();                
                ClsConexion.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(RequerimientoDAL.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
    }
    
}
