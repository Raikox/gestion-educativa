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
import mpsEntity.AlmacenEntity;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email jkevin.mv@gmail.com
 */
public class AlmacenDAL {
        
    public Object[] ListarDatosAlmacen(int _CodigoAlmacen)
    {
        Object datos[] = new Object[9];
        Connection con = ClsConexion.getConection();
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sqlQuery = "select almacen_id, a.almacen_nombre,"
                        + "a.almacen_descripcion, s.nombre_seccion, s.id_seccion, \n"
                        + "p.id_periodo, p.nombre_periodo "
                        + "from mps_almacen as a inner join mat_seccion as s\n"
                        + "on a.id_seccion = s.id_seccion inner join mat_periodo as p\n"
                        + "on s.MPS_Periodo_id_periodo = p.id_periodo "
                        + "where almacen_id = ?";
        
        try
        {
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, _CodigoAlmacen);
            rs = pst.executeQuery();
                       
            while(rs.next())
            {                
                datos[0] = rs.getInt("almacen_id");
                datos[1] = rs.getInt("id_seccion");
                datos[2] = rs.getString("almacen_nombre");
                datos[3] = rs.getString("nombre_periodo");  
                datos[4] = rs.getString("nombre_seccion");
                datos[5] = rs.getString("almacen_descripcion");
                datos[6] = rs.getInt("id_periodo");
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
        
        return datos;
    }
    
    public ArrayList<AlmacenEntity> ListarAlmacenesPorPeriodo(int IdPeriodo)
    {
        ArrayList<AlmacenEntity> arrayAlmacen = new ArrayList<>();
        Connection con = ClsConexion.getConection();
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sqlQuery = "select almacen_id, a.almacen_nombre, "
                        + "a.almacen_descripcion, s.nombre_seccion, s.id_seccion, "
                        + "p.id_periodo, p.nombre_periodo, p.estado_periodo \n"
                        + "from mps_almacen as a inner join mat_seccion as s \n"
                        + "on a.id_seccion = s.id_seccion inner join mat_periodo as p \n"
                        + "on s.MPS_Periodo_id_periodo = p.id_periodo \n"
                        + "where p.estado_periodo = 'Activo' AND s.MPS_Periodo_id_periodo = ? "
                        + "order by a.almacen_nombre";
        
        try
        {
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, IdPeriodo);
            rs = pst.executeQuery();
                       
            while(rs.next())
            {
                AlmacenEntity almacenEntity = new AlmacenEntity();
                almacenEntity.setAlmacen_id(rs.getInt("almacen_id"));
                almacenEntity.setId_seccion(rs.getInt("id_seccion"));
                almacenEntity.setAlmacen_nombre(rs.getString("almacen_nombre"));
                almacenEntity.setNombre_seccion(rs.getString("nombre_seccion"));
                arrayAlmacen.add(almacenEntity);
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
                if(pst != null)
                {
                    pst.close();
                }
                if(rs != null)
                {
                    rs.close();
                }                
                ClsConexion.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
        
        return arrayAlmacen;
    }
    
    public ArrayList<AlmacenEntity> ListarAlmacenes()
    {
        ArrayList<AlmacenEntity> arrayAlmacen = new ArrayList<>();
        Connection con = ClsConexion.getConection();
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sqlQuery = "select almacen_id, a.almacen_nombre, "
                        + "a.almacen_descripcion, s.nombre_seccion, s.id_seccion, "
                        + "p.id_periodo, p.nombre_periodo, p.estado_periodo \n"
                        + "from mps_almacen as a inner join mat_seccion as s \n"
                        + "on a.id_seccion = s.id_seccion inner join mat_periodo as p \n"
                        + "on s.MPS_Periodo_id_periodo = p.id_periodo \n"
                        + "where p.estado_periodo = 'Activo' "
                        + "order by a.almacen_nombre";
        
        try
        {
            pst = con.prepareStatement(sqlQuery);            
            rs = pst.executeQuery();
                       
            while(rs.next())
            {
                AlmacenEntity almacenEntity = new AlmacenEntity();
                almacenEntity.setAlmacen_id(rs.getInt("almacen_id"));
                almacenEntity.setId_seccion(rs.getInt("id_seccion"));
                almacenEntity.setAlmacen_nombre(rs.getString("almacen_nombre"));
                almacenEntity.setNombre_seccion(rs.getString("nombre_seccion"));
                arrayAlmacen.add(almacenEntity);
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
                if(pst != null)
                {
                    pst.close();
                }
                if(rs != null)
                {
                    rs.close();
                }                
                ClsConexion.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
        
        return arrayAlmacen;
    }
    
    public ArrayList<AlmacenEntity> ListarAlmacenesPeriodo(int _IdPeriodo)
    {
        ArrayList<AlmacenEntity> arrayAlmacen = new ArrayList<>();
        Connection con = ClsConexion.getConection();
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sqlQuery = "select almacen_id, a.almacen_nombre, "
                        + "a.almacen_descripcion, s.nombre_seccion, s.id_seccion, "
                        + "p.id_periodo, p.nombre_periodo, p.estado_periodo \n"
                        + "from mps_almacen as a inner join mat_seccion as s \n"
                        + "on a.id_seccion = s.id_seccion inner join mat_periodo as p \n"
                        + "on s.MPS_Periodo_id_periodo = p.id_periodo \n"
                        + "where p.id_periodo = ? \n"
                        + "order by a.almacen_nombre";
        
        try
        {            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, _IdPeriodo);
            rs = pst.executeQuery();
                       
            while(rs.next())
            {
                AlmacenEntity almacenEntity = new AlmacenEntity();
                almacenEntity.setAlmacen_id(rs.getInt("almacen_id"));
                almacenEntity.setId_seccion(rs.getInt("id_seccion"));
                almacenEntity.setAlmacen_nombre(rs.getString("almacen_nombre"));
                almacenEntity.setNombre_seccion(rs.getString("nombre_seccion"));
                almacenEntity.setId_periodo(rs.getInt("id_periodo"));
                arrayAlmacen.add(almacenEntity);
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
        
        return arrayAlmacen;
    }
    
    public void GuardarAlmacen(String _Nombre, String _Descripcion, int IdAula)
    {
        Connection con = ClsConexion.getConection();
        PreparedStatement pst = null;
        String sqlQuery = "insert into mps_almacen "
                    + "(almacen_nombre,almacen_descripcion,id_Seccion) values"
                    + "(?,?,?)";
        try 
        {
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, _Nombre);
            pst.setString(2, _Descripcion);
            pst.setInt(3, IdAula);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex){
            Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try 
            {
                pst.close();                
                ClsConexion.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
        
    }
    
    public void EditarAlmacen(int _IdAlmacen, String _Nombre, String _Descripcion, int _IdAula)
    {
        Connection con = ClsConexion.getConection();
        PreparedStatement pst = null;
        String sqlQuery = "update mps_almacen as a set\n" 
                    + "a.almacen_nombre = ?,\n" 
                    + "a.almacen_descripcion = ?,\n" 
                    + "a.id_seccion = ? \n" 
                    + "where a.almacen_id = ? ";
        try 
        {
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(4, _IdAlmacen);
            pst.setString(1, _Nombre);
            pst.setString(2, _Descripcion);
            pst.setInt(3, _IdAula);            
            pst.executeUpdate();
        } catch (SQLException ex){
            Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex){
            Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try 
            {
                pst.close();                
                ClsConexion.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
        
    }
    
    public void EliminarAlmacen(int _IdAlmacen)
    {
         Connection con = ClsConexion.getConection();
         PreparedStatement pst = null;
         String sqlQuery = "delete from mps_almacen where almacen_id = ?";
         
        try 
        {
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, _IdAlmacen);                        
            pst.executeUpdate();
        } catch (SQLException ex){
            Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex){
            Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try 
            {
                pst.close();                
                ClsConexion.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
    }
    
    
    
    
}
