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
import mpsEntity.PeriodoEntity;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email jkevin.mv@gmail.com
 */
public class PeriodoDAL {

    public ArrayList<PeriodoEntity> ListarPeriodos()
    {
        ArrayList<PeriodoEntity> arrayPeriodo = new ArrayList<>();
        Connection con = ClsConexion.getConection();
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sqlQuery = "select p.id_periodo, p.nombre_periodo, p.anio_periodo, p.estado_periodo,\n" +
                        "p.inicio_periodo, p.fin_periodo, p.matricula_periodo,\n" +
                        "p.hora_periodo, p.horb_periodo, p.horc_periodo, p.alimentacion_periodo,\n" +
                        "p.alimentacion_dia, p.asistencia_periodo, n.nivel_id, n.nivel_nombre\n" +
                        "from mat_periodo as p inner join mat_nivel as n\n" +
                        "on p.nivel_id = n.nivel_id order by p.anio_periodo desc,p.nombre_periodo";
        
        try
        {
            pst = con.prepareStatement(sqlQuery);
            rs = pst.executeQuery();
            
            while(rs.next())
            {
                PeriodoEntity periodoEntity = new PeriodoEntity();
                periodoEntity.setId_periodo(rs.getInt("id_periodo"));
                periodoEntity.setNombre_periodo(rs.getString("nombre_periodo"));
                periodoEntity.setAnio_periodo(rs.getString("anio_periodo"));
                periodoEntity.setEstado_periodo(rs.getString("estado_periodo"));
                periodoEntity.setInicio_periodo(rs.getString("inicio_periodo"));
                periodoEntity.setFin_periodo(rs.getString("fin_periodo"));
                periodoEntity.setMatricula_periodo(rs.getDouble("matricula_periodo"));
                periodoEntity.setHora_periodo(rs.getDouble("hora_periodo"));
                periodoEntity.setHorb_periodo(rs.getDouble("horb_periodo"));
                periodoEntity.setHorc_periodo(rs.getDouble("horc_periodo"));
                periodoEntity.setAlimentacion_periodo(rs.getDouble("alimentacion_periodo"));
                periodoEntity.setAlimentacion_dia(rs.getDouble("alimentacion_dia"));
                periodoEntity.setAsistencia_periodo(rs.getInt("asistencia_periodo"));
                periodoEntity.setNivel_id(rs.getInt("nivel_id"));
                periodoEntity.setNivel_nombre(rs.getString("nivel_nombre"));
                arrayPeriodo.add(periodoEntity);
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
        return arrayPeriodo;    
    }
}
