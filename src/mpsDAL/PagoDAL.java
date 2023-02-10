/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mpsDAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mpsEntity.BoletaEntity;
import mpsEntity.EgresoEntity;
import mpsEntity.NoAdeudoEntity;


/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email jkevin.mv@gmail.com
 */
public class PagoDAL {
    
    public void GuardarComprobante(String CodigoBoleta, String Fecha, Double total, String TipoPago, String NumeroOperacion ,String IdMatricula, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "INSERT INTO pgo_boleta (num_boleta,fecha_boleta,total_boleta,tipo_pago,numero_operacion,id_matricula)\n" 
                        + "VALUES (?,?,?,?,?,?)";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, CodigoBoleta);
            pst.setString(2, Fecha);
            pst.setDouble(3, total);
            pst.setString(4, TipoPago);
            pst.setString(5, NumeroOperacion);
            pst.setString(6, IdMatricula);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(PagoDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex){
            Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<String> ListarNumeroOperacionPeriodo (String IdPeriodo, Connection con) {
        
        ArrayList<String> listNumeroOperacion = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sqlQuery = "SELECT b.numero_operacion\n" +
            "FROM pgo_boleta AS b\n" +
            "INNER JOIN mat_matricula AS a ON b.id_matricula = a.id_matricula\n" +
            "INNER JOIN mat_seccion AS s ON s.id_seccion = a.seccion_id\n" +
            "WHERE s.MPS_Periodo_id_periodo = ? ";

        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, IdPeriodo);
            rs = pst.executeQuery();
            
            while(rs.next()) {
                
                String numOperacion = rs.getString("numero_operacion");
                listNumeroOperacion.add(numOperacion);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MatriculaDAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch(Exception ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }        
        finally {
            try 
            {
                if(pst != null) { pst.close(); }
                if(rs != null) { rs.close(); } 
                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return listNumeroOperacion;
    }
    
    public List<NoAdeudoEntity> ListarAlumnoMensualidadPagada(String IdMatricula, Connection con) {
        
        List<NoAdeudoEntity> listNoAdeudo = new ArrayList<>();
        
        String sqlQuery = "select m.id_matricula,b.id_boleta,b.num_boleta,db.desc_detalle, "
                        + "db.importe_detalle,\n" 
                        + "b.fecha_boleta, db.tipo_detalle \n" 
                        + "from pgo_boleta as b inner join mat_matricula as m\n" 
                        + "on b.id_matricula = m.id_matricula inner join pgo_detalle_boleta as db\n" 
                        + "on db.id_boleta = b.id_boleta\n" 
                        + "where b.id_matricula = ? AND db.tipo_detalle = 'mensualidad'\n" 
                        + "order by b.fecha_boleta;";
        
        try( 
            PreparedStatement pst = con.prepareStatement(sqlQuery);             
            ) {
            
            pst.setString(1, IdMatricula);
            
            try(ResultSet rs = pst.executeQuery()) {
                
                while( rs.next() ) {
                
                    NoAdeudoEntity noAdeudoEntity = new NoAdeudoEntity (
                        
                        rs.getString("id_matricula"),
                        rs.getString("num_boleta"),
                        rs.getString("desc_detalle"),
                        rs.getString("fecha_boleta"),
                        rs.getString("tipo_detalle")

                    );

                    listNoAdeudo.add(noAdeudoEntity);
                }
            }            
            
        } catch (SQLException ex) {
            Logger.getLogger(PagoDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listNoAdeudo;
        
    }
    
    public List<BoletaEntity> ListarIngresosCaja(Date date, Connection con) {
        
        List<BoletaEntity> listIngresosCaja = new ArrayList<>();
        String sqlQuery = "SELECT b.id_boleta, b.num_boleta, b.tipo_pago, a.nombre_alumno, "
                        + "a.apellidop_alumno, a.apellidom_alumno, s.nombre_seccion, db.desc_detalle, "
                        + "db.importe_detalle, db.tipo_detalle\n" 
                        + "FROM pgo_detalle_boleta AS db\n" 
                        + "INNER JOIN pgo_boleta AS b ON db.id_boleta = b.id_boleta\n" 
                        + "INNER JOIN mat_matricula AS m ON b.id_matricula = m.id_matricula\n" 
                        + "INNER JOIN mat_alumno AS a ON m.MPS_Alumno_id_alumno = a.id_alumno\n" 
                        + "INNER JOIN mat_seccion AS s ON m.seccion_id = s.id_seccion\n"
                        + "WHERE (b.fecha_boleta = ? AND b.tipo_pago = 'caja') AND (db.tipo_detalle != 'anulada') \n" 
                        + "ORDER BY b.num_boleta";
        
        try( 
            PreparedStatement pst = con.prepareStatement(sqlQuery);             
            ) {
            
            pst.setDate(1, date);
            
            try(ResultSet rs = pst.executeQuery()) {
                
                while( rs.next() ) {
                
                    BoletaEntity boletaEntity = new BoletaEntity (

                        rs.getInt("id_boleta"),
                        rs.getString("num_boleta"),
                        rs.getString("tipo_pago"),
                        rs.getString("nombre_alumno"),
                        rs.getString("apellidop_alumno"),
                        rs.getString("apellidom_alumno"),
                        rs.getString("nombre_seccion"),
                        rs.getString("desc_detalle"),
                        rs.getDouble("importe_detalle")                        

                    );

                    listIngresosCaja.add(boletaEntity);
                }
            }            
            
        } catch (SQLException ex) {
            Logger.getLogger(PagoDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listIngresosCaja;
    }
    
    public List<EgresoEntity> ListarEgresosCaja(Date date, Connection con) {
        
        List<EgresoEntity> listEgresosCaja = new ArrayList<>();
        String sqlQuery = "SELECT e.id_egreso, p.personal_id, p.personal_nombre, "
                        + "p.personal_apellido_paterno, p.personal_apellido_materno, "
                        + "e.concepto_egreso, e.desc_egreso, e.monto_egreso,e.fecha_egreso\n" 
                        + "FROM pgo_egreso AS e\n" 
                        + "INNER JOIN adm_personal AS p ON e.id_persona = p.personal_id\n" 
                        + "WHERE e.fecha_egreso = ?";
        
        try( 
            PreparedStatement pst = con.prepareStatement(sqlQuery);             
            ) {
            
            pst.setDate(1, date);
            
            try(ResultSet rs = pst.executeQuery()) {
                
                while( rs.next() ) {
                
                    EgresoEntity egresosEntity = new EgresoEntity(
                    
                        rs.getInt("id_egreso"),
                        rs.getInt("personal_id"),
                        rs.getString("concepto_egreso"),
                        rs.getString("desc_egreso"),
                        rs.getDouble("monto_egreso"),
                        rs.getDate("fecha_egreso"),
                        rs.getString("personal_nombre"),
                        rs.getString("personal_apellido_paterno"),
                        rs.getString("personal_apellido_materno")
                    
                    );

                    listEgresosCaja.add(egresosEntity);
                }
            }            
            
        } catch (SQLException ex) {
            Logger.getLogger(PagoDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listEgresosCaja;
    }
    
    public List<BoletaEntity> ListarIngresosBanco(Date date, Connection con) {
        
        List<BoletaEntity> listIngresosBanco = new ArrayList<>();
        String sqlQuery = "SELECT b.id_boleta, b.num_boleta, b.tipo_pago, a.nombre_alumno, "
                        + "a.apellidop_alumno, a.apellidom_alumno, s.nombre_seccion, db.desc_detalle, "
                        + "db.importe_detalle, db.tipo_detalle, b.numero_operacion\n" 
                        + "FROM pgo_detalle_boleta AS db\n" 
                        + "INNER JOIN pgo_boleta AS b ON db.id_boleta = b.id_boleta\n" 
                        + "INNER JOIN mat_matricula AS m ON b.id_matricula = m.id_matricula\n" 
                        + "INNER JOIN mat_alumno AS a ON m.MPS_Alumno_id_alumno = a.id_alumno\n" 
                        + "INNER JOIN mat_seccion AS s ON m.seccion_id = s.id_seccion\n"
                        + "WHERE (b.fecha_boleta = ? AND b.tipo_pago = 'banco') AND (db.tipo_detalle != 'anulada') \n" 
                        + "ORDER BY s.nombre_seccion, a.nombre_alumno";
        
        try( 
            PreparedStatement pst = con.prepareStatement(sqlQuery);             
            ) {
            
            pst.setDate(1, date);
            
            try(ResultSet rs = pst.executeQuery()) {
                
                while( rs.next() ) {
                
                    BoletaEntity boletaEntity = new BoletaEntity (

                        rs.getInt("id_boleta"),
                        rs.getString("num_boleta"),
                        rs.getString("tipo_pago"),
                        rs.getString("numero_operacion"),
                        rs.getString("nombre_alumno"),
                        rs.getString("apellidop_alumno"),
                        rs.getString("apellidom_alumno"),
                        rs.getString("nombre_seccion"),
                        rs.getString("desc_detalle"),
                        rs.getDouble("importe_detalle")                        

                    );

                    listIngresosBanco.add(boletaEntity);
                }
            }            
            
        } catch (SQLException ex) {
            Logger.getLogger(PagoDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listIngresosBanco;
    }
    
}
