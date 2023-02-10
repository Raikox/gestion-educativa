package PckConexion;

import java.sql.Connection;
import com.mysql.jdbc.jdbc2.optional.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClsConexion 
{
    
    public static Connection getConection()
    {
        Connection conection = null;
        try
        {
            MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
//            ds.setServerName("192.168.1.46");
//            ds.setPort(3306);
//            ds.setDatabaseName("bd_mps");
//            conection = ds.getConnection("admin","iddqd");
            
//            ds.setServerName("192.168.1.48"); 
            ds.setServerName("192.168.18.123"); //production
            ds.setPort(3306);
            ds.setDatabaseName("bd_mps");
            conection = ds.getConnection("admin","iddqd");
        }
        catch(SQLException ex)
        {
            Logger.getLogger(ClsConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conection;
    }
    
    public static void closeConnection(Connection conection)
    {
        try
        {
            conection.close();            
        }
        catch(SQLException ex)
        {
            Logger.getLogger(ClsConexion.class.getName()).log(Level.SEVERE, null, ex);  
        }
    }
}
