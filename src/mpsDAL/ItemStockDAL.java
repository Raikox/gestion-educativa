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
import mpsEntity.ItemStockEntity;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email jkevin.mv@gmail.com
 */
public class ItemStockDAL {

    public ArrayList<ItemStockEntity> ListarItemsSinAsignar(int xIdItem)
    {
        ArrayList<ItemStockEntity> arrayItemStock = new ArrayList<>();
        Connection con = ClsConexion.getConection();
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sqlQuery = "select DATE_FORMAT(ais.item_stock_fecha, '%d/%m/%Y') as 'item_stock_fecha', ais.item_id,\n" +
                "ais.item_stock_asignado, concat(p.personal_apellido_paterno,' ', p.personal_apellido_materno,', ', p.personal_nombre) as 'personal'\n" +
                "from mps_almacen_item_stock as ais inner join adm_personal as p\n" +
                "on ais.personal_id = p.personal_id\n" +
                "where ais.item_id = ? \n" +
                "order by ais.item_stock_fecha";
        
        try
        {
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, xIdItem);
            rs = pst.executeQuery();
                       
            while(rs.next())
            {
                ItemStockEntity itemStockEntity = new ItemStockEntity();
                itemStockEntity.setItem_stock_id(rs.getInt("item_id"));
                itemStockEntity.setItem_stock_fecha(rs.getString("item_stock_fecha"));
                itemStockEntity.setItem_stock_asignado(rs.getInt("item_stock_asignado"));
                itemStockEntity.setPersonal_nombre(rs.getString("personal"));                
                arrayItemStock.add(itemStockEntity);
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
        
        return arrayItemStock;
    }
    
    public void GuardarItemCantidad(String xFecha, String xCantidad, int xIdPersonal, int xIdItem)
    {
        Connection con = ClsConexion.getConection();
        PreparedStatement pst = null;
        String sqlQuery = "insert into mps_almacen_item_stock "
                    + "(item_stock_fecha,item_stock_asignado,personal_id,item_id) "
                    + "values "
                    + "((STR_TO_DATE(REPLACE(?,'/','.') ,GET_FORMAT(date,'EUR'))),?,?,?)";
        try 
        {
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, xFecha);
            pst.setString(2, xCantidad);
            pst.setInt(3, xIdPersonal);
            pst.setInt(4, xIdItem);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ItemStockDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex){
            Logger.getLogger(ItemStockDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try 
            {
                pst.close();                
                ClsConexion.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(ItemStockDAL.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
        
    }
}
