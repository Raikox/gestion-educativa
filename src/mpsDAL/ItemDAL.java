/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mpsDAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import mpsEntity.ItemEntity;
import PckConexion.ClsConexion;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import prymatricula.ClsMisc;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email jkevin.mv@gmail.com
 */
public class ItemDAL {
        
    public Object[] ListarDatosItem(int _CodigoItem)
    {
        Object datos[] = new Object[15];
        Connection con = ClsConexion.getConection();
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sqlQuery = "select ai.item_id, ai.item_nombre, ai.item_medida, \n" +
                        "ai.item_stock, ai.item_descripcion, ai.almacen_id,\n" +
                        "a.almacen_nombre, s.id_seccion, s.nombre_seccion,\n" +
                        "p.id_periodo, p.nombre_periodo, p.estado_periodo, ai.item_tipo \n" +
                        "from mps_almacen_item as ai left join mps_almacen as a\n" +
                        "on ai.almacen_id = a.almacen_id left join mat_seccion as s\n" +
                        "on a.id_seccion = s.id_seccion left join mat_periodo as p\n" +
                        "on s.MPS_Periodo_id_periodo = p.id_periodo\n" +
                        "where ai.item_id = ?";
        
        try
        {
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, _CodigoItem);
            rs = pst.executeQuery();
                       
            while(rs.next())
            {                
                datos[0] = rs.getInt("item_id");
                datos[1] = rs.getString("item_nombre");//
                datos[2] = rs.getString("item_medida");//
                datos[3] = rs.getInt("item_stock");  //
                datos[4] = rs.getString("item_descripcion");//
                datos[5] = rs.getInt("almacen_id");
                datos[6] = rs.getString("almacen_nombre");//
                datos[7] = rs.getInt("id_seccion");
                datos[8] = rs.getString("nombre_seccion");//
                datos[9] = rs.getInt("id_periodo");
                datos[10] = rs.getString("nombre_periodo");
                datos[11] = rs.getString("estado_periodo");
                datos[12] = rs.getString("item_tipo");
            }            
            
        }
        catch(SQLException ex){
            Logger.getLogger(ItemDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex){
            Logger.getLogger(ItemDAL.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public ArrayList<ItemEntity> ListarItemsSinAsignar(boolean _consumo)
    {
        ArrayList<ItemEntity> arrayAlmacenItem = new ArrayList<>();
        Connection con = ClsConexion.getConection();
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sqlQuery;
        
        if(_consumo)
        {
            sqlQuery = "select ai.item_id, ai.item_nombre, ai.item_medida, \n" 
                    + "ai.item_stock, ai.item_descripcion, ai.almacen_id,\n" 
                    + "a.almacen_nombre\n" 
                    + "from mps_almacen_item as ai left join mps_almacen as a\n" 
                    + "on ai.almacen_id = a.almacen_id \n"
                    + "where ai.almacen_id is null and ai.item_tipo = 'DE CONSUMO' \n"
                    + "order by a.almacen_nombre, ai.item_nombre";
        }
        else
        {
            sqlQuery = "select ai.item_id, ai.item_nombre, ai.item_medida, \n" 
                    + "ai.item_stock, ai.item_descripcion, ai.almacen_id,\n" 
                    + "a.almacen_nombre\n" 
                    + "from mps_almacen_item as ai left join mps_almacen as a\n" 
                    + "on ai.almacen_id = a.almacen_id \n"
                    + "where ai.almacen_id is null and ai.item_tipo = 'INVENTARIO' \n"
                    + "order by a.almacen_nombre, ai.item_nombre";
        }
               
        
        
        try
        {
            pst = con.prepareStatement(sqlQuery);
            rs = pst.executeQuery();
                       
            while(rs.next())
            {
                ItemEntity itemEntity = new ItemEntity();
                itemEntity.setItem_id(rs.getInt("item_id"));
                itemEntity.setItem_nombre(rs.getString("item_nombre"));
                itemEntity.setItem_medida(rs.getString("item_medida"));
                itemEntity.setItem_stock(rs.getInt("item_stock"));
                itemEntity.setItem_descripcion(rs.getString("item_descripcion"));
                itemEntity.setAlmacen_id(rs.getInt("almacen_id"));
                itemEntity.setAlmacen_nombre(rs.getString("almacen_nombre"));
                arrayAlmacenItem.add(itemEntity);
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
        
        return arrayAlmacenItem;
    }
    
    public ArrayList<ItemEntity> ListarItems(boolean consumo)
    {
        ArrayList<ItemEntity> arrayAlmacenItem = new ArrayList<>();
        Connection con = ClsConexion.getConection();
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sqlQuery;
        if(consumo)
        {
            sqlQuery = "select ai.item_id, ai.item_nombre, ai.item_medida, \n" 
                        + "ai.item_stock, ai.item_descripcion, ai.almacen_id,\n" 
                        + "a.almacen_nombre\n" 
                        + "from mps_almacen_item as ai left join mps_almacen as a\n" 
                        + "on ai.almacen_id = a.almacen_id where ai.item_tipo = 'DE CONSUMO' "
                        + "order by a.almacen_nombre, ai.item_nombre";
        }
        else
        {
            sqlQuery = "select ai.item_id, ai.item_nombre, ai.item_medida, \n" 
                        + "ai.item_stock, ai.item_descripcion, ai.almacen_id,\n" 
                        + "a.almacen_nombre\n" 
                        + "from mps_almacen_item as ai left join mps_almacen as a\n" 
                        + "on ai.almacen_id = a.almacen_id where ai.item_tipo = 'INVENTARIO' "
                        + "order by a.almacen_nombre, ai.item_nombre";
        }        
        
        try
        {
            pst = con.prepareStatement(sqlQuery);
            rs = pst.executeQuery();
                       
            while(rs.next())
            {
                ItemEntity itemEntity = new ItemEntity();
                itemEntity.setItem_id(rs.getInt("item_id"));
                itemEntity.setItem_nombre(rs.getString("item_nombre"));
                itemEntity.setItem_medida(rs.getString("item_medida"));
                itemEntity.setItem_stock(rs.getInt("item_stock"));
                itemEntity.setItem_descripcion(rs.getString("item_descripcion"));
                itemEntity.setAlmacen_id(rs.getInt("almacen_id"));
                itemEntity.setAlmacen_nombre(rs.getString("almacen_nombre"));
                arrayAlmacenItem.add(itemEntity);
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
        
        return arrayAlmacenItem;
    }
    
    public ArrayList<ItemEntity> ListarItemsBusqueda(boolean consumo, String busqueda)
    {
        ArrayList<ItemEntity> arrayAlmacenItem = new ArrayList<>();
        Connection con = ClsConexion.getConection();
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sqlQuery;
        if(consumo)
        {
            sqlQuery = "select ai.item_id, ai.item_nombre, ai.item_medida, \n" 
                        + "ai.item_stock, ai.item_descripcion, ai.almacen_id,\n" 
                        + "a.almacen_nombre\n" 
                        + "from mps_almacen_item as ai left join mps_almacen as a\n" 
                        + "on ai.almacen_id = a.almacen_id where ai.item_tipo = 'DE CONSUMO' "
                        + "AND ("
                        + "ai.item_nombre LIKE concat('%',?,'%')"
                        + "OR MATCH(ai.item_nombre) AGAINST (?)"
                        + ")"
                        + "order by a.almacen_nombre, ai.item_nombre";
        }
        else
        {
            sqlQuery = "select ai.item_id, ai.item_nombre, ai.item_medida, \n" 
                        + "ai.item_stock, ai.item_descripcion, ai.almacen_id,\n" 
                        + "a.almacen_nombre\n" 
                        + "from mps_almacen_item as ai left join mps_almacen as a\n" 
                        + "on ai.almacen_id = a.almacen_id where ai.item_tipo = 'INVENTARIO' "
                        + "AND ("
                        + "ai.item_nombre LIKE concat('%',?,'%')"
                        + "OR MATCH(ai.item_nombre) AGAINST (?)"
                        + ")"
                        + "order by a.almacen_nombre, ai.item_nombre";
        }        
        
        try
        {
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, busqueda);
            pst.setString(2, busqueda);
            rs = pst.executeQuery();
                       
            while(rs.next())
            {
                ItemEntity itemEntity = new ItemEntity();
                itemEntity.setItem_id(rs.getInt("item_id"));
                itemEntity.setItem_nombre(rs.getString("item_nombre"));
                itemEntity.setItem_medida(rs.getString("item_medida"));
                itemEntity.setItem_stock(rs.getInt("item_stock"));
                itemEntity.setItem_descripcion(rs.getString("item_descripcion"));
                itemEntity.setAlmacen_id(rs.getInt("almacen_id"));
                itemEntity.setAlmacen_nombre(rs.getString("almacen_nombre"));
                arrayAlmacenItem.add(itemEntity);
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
        
        return arrayAlmacenItem;
    }
    
    public int GuardarItem(String _Nombre, String _Medida, int _Stock, String _Descripcion, String _Tipo)
    {
        Connection con = ClsConexion.getConection();
        PreparedStatement pst = null;
        int LastIdItem=0;
        String sqlQuery = "insert into mps_almacen_item "
                    + "(item_nombre,item_medida,item_stock,item_descripcion, item_tipo) values"
                    + "(?,?,?,?,?)";
       
        try 
        {
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, _Nombre);
            pst.setString(2, _Medida);
            pst.setInt(3, _Stock);
            pst.setString(4, _Descripcion);
            pst.setString(5, _Tipo);
            pst.executeUpdate();
            
            LastIdItem = Integer.parseInt(ClsMisc.UltimoIdInsertado(con));
            
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
        return LastIdItem;
    }
    
    public void EditarItem(int _IdItem, String _Nombre, String _Medida, int _Stock, String _Descripcion,String _Tipo)
    {
        Connection con = ClsConexion.getConection();
        PreparedStatement pst = null;
        String sqlQuery = "update mps_almacen_item as ai set\n" +
                        "ai.item_nombre = ?,\n" +
                        "ai.item_medida = ?,\n" +
                        "ai.item_stock = ?,\n" +
                        "ai.item_descripcion = ?,\n" +
                        "ai.item_tipo = ?\n" +
                        "where ai.item_id = ?";
        try 
        {
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, _Nombre);
            pst.setString(2, _Medida);
            pst.setInt(3, _Stock);
            pst.setString(4, _Descripcion);
            pst.setString(5, _Tipo);
            pst.setInt(6, _IdItem);
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
    
    public void EditarItemStock(int _IdItem, int _Stock)
    {
        Connection con = ClsConexion.getConection();
        PreparedStatement pst = null;
        String sqlQuery = "update mps_almacen_item as ai set\n" +                        
                        "ai.item_stock = ? \n" +                       
                        "where ai.item_id = ?";
        try 
        {
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, _Stock);            
            pst.setInt(2, _IdItem);
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
    
    public void EditarItemAlmacen(int _IdItem, int _IdAlmacen)
    {
        Connection con = ClsConexion.getConection();
        PreparedStatement pst = null;
        String sqlQuery = "update mps_almacen_item as ai set\n" +
                        "ai.almacen_id = ? \n" +                        
                        "where ai.item_id = ?";
        try 
        {
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, _IdAlmacen);
            pst.setInt(2, _IdItem);            
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
    
    public void EliminarItemStock(int IdItem, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "delete from mps_almacen_item_stock where item_id = ? ";
         
        try {
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, IdItem);                        
            pst.executeUpdate();
        } catch (SQLException ex){
            Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, "El ítem está asociado a un requerimiento, no se puede borrar", "Error al borrar", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception ex){
            Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void EliminarItem(int _IdItem)
    {
        Connection con = ClsConexion.getConection();
        PreparedStatement pst = null;
        String sqlQuery = "delete from mps_almacen_item where item_id = ? ";
         
        try 
        {
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, _IdItem);                        
            pst.executeUpdate();
        } catch (SQLException ex){
            Logger.getLogger(AlmacenDAL.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "El ítem está asociado a un requerimiento, no se puede borrar", "Error al borrar", JOptionPane.ERROR_MESSAGE);
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
