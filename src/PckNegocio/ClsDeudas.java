/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckNegocio;

import PckEntidad.ClsEntidadBoletaMasDetalle;
import PckEntidad.ClsEntidadDeudas;
import PckEntidad.ClsEntidadNotificacion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kmv
 */
public class ClsDeudas {

    
    public String ObtenerAnioPeriodoDeBoleta(String IdBoleta, Connection con) {
        
        String sqlQuery = "SELECT p.id_periodo, p.nombre_periodo, p.anio_periodo\n" 
                        + "FROM pgo_boleta AS b \n" 
                        + "INNER JOIN mat_matricula AS m ON b.id_matricula = m.id_matricula\n" 
                        + "INNER JOIN mat_seccion AS s ON m.seccion_id = s.id_seccion\n" 
                        + "INNER JOIN mat_periodo AS p ON s.MPS_Periodo_id_periodo = p.id_periodo\n" 
                        + "WHERE b.id_boleta = ? ";
        
        String AnioPeriodo="";
        try( 
            PreparedStatement pst = con.prepareStatement(sqlQuery);             
            ) {
            pst.setString(1, IdBoleta);
            
            try(ResultSet rs = pst.executeQuery()) {
                
                while( rs.next() ) {
                    
                    AnioPeriodo = rs.getString("anio_periodo");
                }
            }
            
        }catch (SQLException ex) {
            Logger.getLogger(ClsDeudas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return AnioPeriodo;
    }
    
    
     public ArrayList<ClsEntidadDeudas> ListarDeudas(String codAlu,Connection con){
        ArrayList<ClsEntidadDeudas> Deudas = new ArrayList<ClsEntidadDeudas>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_S_DeudaAlumno(?)}");
            statement.setString("codigo", codAlu);
            
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadDeudas deuda = new ClsEntidadDeudas();
                deuda.setConcepto(rs.getString("concepto"));
                deuda.setMes(rs.getString("mes"));
                deuda.setUnitario(rs.getString("total"));
                deuda.setPendiente(rs.getString("pendiente"));
                deuda.setImporte(rs.getString("pendiente"));
                deuda.setVencimiento(rs.getString("vencimiento"));
                deuda.setTipo(rs.getString("tipo"));
                deuda.setId_Deuda(rs.getString("id_deuda")); 
                Deudas.add(deuda);                
            }       
            return Deudas;
        } catch(SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
     
     public void ModificarClinico(String idMatricula,String mes,String total,String pendiente,
             Connection con){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_DeudaComida(?,?,?,?)}");
            statement.setString("pid_matricula", idMatricula);
            statement.setString("pmes", mes);
            statement.setString("ptotal_comida", total);
            statement.setString("ppendiente_comida", pendiente);            
            statement.execute();
            con.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
     
    public ArrayList<ClsEntidadDeudas> ListarDeudasSinVencer(String codAlu,Connection con)
    {
        ArrayList<ClsEntidadDeudas> Deudas = new ArrayList<ClsEntidadDeudas>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_S_DeudaAlumnoSinVencer(?)}");
            statement.setString("codigo", codAlu);
            
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadDeudas deuda = new ClsEntidadDeudas();
                deuda.setConcepto(rs.getString("concepto"));
                deuda.setMes(rs.getString("mes"));
                deuda.setUnitario(rs.getString("total"));
                deuda.setImporte(rs.getString("pendiente"));
                deuda.setVencimiento(rs.getString("vencimiento"));
                deuda.setTipo(rs.getString("tipo"));
                deuda.setId_Deuda(rs.getString("id_deuda")); 
                Deudas.add(deuda);
                
            }
        
            return Deudas;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    } 
    
    public ArrayList<ClsEntidadDeudas> ListarPagos(String codAlu,Connection con)
    {
        ArrayList<ClsEntidadDeudas> Deudas = new ArrayList<ClsEntidadDeudas>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_S_AlumnoPago(?)}");
            statement.setString("codigo", codAlu);
            
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadDeudas deuda = new ClsEntidadDeudas();
                deuda.setConcepto(rs.getString("concepto"));
                deuda.setUnitario(rs.getString("total"));
                deuda.setImporte(rs.getString("pendiente"));
                deuda.setVencimiento(rs.getString("vencimiento"));
                deuda.setTipo(rs.getString("tipo"));
                deuda.setId_Deuda(rs.getString("id_deuda")); 
                deuda.setMes(rs.getString("mes"));
                Deudas.add(deuda);                
            }
        
            return Deudas;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    } 
     
    public ArrayList<ClsEntidadBoletaMasDetalle> ListarBoletasTodas(String codMatricula,Connection con){
      ArrayList<ClsEntidadBoletaMasDetalle> Mostrar = new ArrayList<ClsEntidadBoletaMasDetalle>();
      try
      {
            CallableStatement statement = con.prepareCall("{call sp_S_Boleta(?)}");
            statement.setString("pid_matricula", codMatricula);
            
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                ClsEntidadBoletaMasDetalle mostrar = new ClsEntidadBoletaMasDetalle();
                mostrar.setId_Matricula(rs.getString("id_matricula"));
                mostrar.setId_Boleta(rs.getString("id_boleta"));
                mostrar.setNumero_Boleta(rs.getString("num_boleta"));
                mostrar.setDescripcion_Boleta(rs.getString("descripcion_boleta"));
                mostrar.setImporte_Boleta(rs.getString("importe"));
                mostrar.setFecha_Boleta(rs.getString("fecha_boleta"));
                mostrar.setTipo_Detalle_Boleta(rs.getString("tipo_detalle"));
                Mostrar.add(mostrar);
            }        
            return Mostrar;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    
    
    public ArrayList<ClsEntidadBoletaMasDetalle> ListarBoletasDetalle(String codMatricula,String tipo,Connection con)
    {
      ArrayList<ClsEntidadBoletaMasDetalle> Mostrar = new ArrayList<ClsEntidadBoletaMasDetalle>();
      try
      {
            CallableStatement statement = con.prepareCall("{call sp_S_BoletaOpcion(?,?)}");
            statement.setString("pid_matricula", codMatricula);
            statement.setString("ptipo_detalle", tipo);
            
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                ClsEntidadBoletaMasDetalle mostrar = new ClsEntidadBoletaMasDetalle();
                mostrar.setId_Matricula(rs.getString("id_matricula"));
                mostrar.setId_Boleta(rs.getString("id_boleta"));
                mostrar.setNumero_Boleta(rs.getString("num_boleta"));
                mostrar.setDescripcion_Boleta(rs.getString("descripcion_boleta"));
                mostrar.setImporte_Boleta(rs.getString("importe"));
                mostrar.setFecha_Boleta(rs.getString("fecha_boleta"));
                mostrar.setTipo_Detalle_Boleta(rs.getString("tipo_detalle"));
                Mostrar.add(mostrar);
            }        
            return Mostrar;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }

    public ClsEntidadDeudas produce(String Concepto, String Unitario,String Pendiente,
            String Vencimiento,String Tipo,String Id_Deuda) 
    {
        ClsEntidadDeudas dataBean = new ClsEntidadDeudas();
        dataBean.setConcepto(Concepto);
        dataBean.setPendiente(Unitario);
        dataBean.setImporte(Pendiente);
        dataBean.setVencimiento(Vencimiento);
        dataBean.setTipo(Tipo);
        dataBean.setId_Deuda(Id_Deuda); 
        return dataBean;
    }
    
    public ClsEntidadNotificacion produceNotificacion(String concepto, String total,
        String pendiente,String vencimiento,String tipo,String id_Deuda) {
        
            ClsEntidadNotificacion dataBean = new ClsEntidadNotificacion();
            dataBean.setConcepto(concepto);
            dataBean.setTotal(total);
            dataBean.setPendiente(pendiente);
            dataBean.setVencimiento(vencimiento);
            dataBean.setTipo(tipo);
            dataBean.setId_Deuda(id_Deuda); 
            return dataBean;
        }
    
    public ClsEntidadDeudas produceBoleta(String Concepto, String Unitario,String Importe,
            String Vencimiento,String Tipo,String Id_Deuda) {
        ClsEntidadDeudas dataBean = new ClsEntidadDeudas();
        dataBean.setConcepto(Concepto);
        dataBean.setUnitario(Unitario);
        dataBean.setImporte(Importe);
        dataBean.setVencimiento(Vencimiento);
        dataBean.setTipo(Tipo);
        dataBean.setId_Deuda(Id_Deuda); 
        return dataBean;
    }
    
    
    
    
    
//     public ClsEntidadDeudasBean produce2(String Concepto, String Total,String Pendiente,String Vencimiento) {
//        ClsEntidadDeudasBean dataBean = new ClsEntidadDeudasBean();
//        dataBean.setconcepto(Concepto);
//        dataBean.settotal(Total);
//        dataBean.setpendiente(Pendiente);
//        dataBean.setvencimiento(Vencimiento);
//        return dataBean;
//    }
//      public ClsEntidadDeudasAlumno produce3(String nino, String aula) {
//        ClsEntidadDeudasAlumno dataBean = new ClsEntidadDeudasAlumno();
//        dataBean.setNino(nino);
//        dataBean.setAula(aula);
//        
//        return dataBean;
//    }
}
