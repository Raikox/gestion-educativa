package PckNegocio;

import PckConexion.*;
import PckEntidad.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ClsPadre {
    
    private Connection con = new ClsConexion().getConection();
    
    public void AgregarPadre(ClsEntidadPadre Padre){
        try{
            CallableStatement statement = con.prepareCall("{call sp_I_Padre(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            statement.setString("papellidop_padre", Padre.getApellidoP_Padre());
            statement.setString("papellidom_padre", Padre.getApellidoM_Padre());
            statement.setString("pnombre_padre", Padre.getNombre_Padre());
            statement.setString("pvive_padre", Padre.getVive_Padre());
            statement.setString("pfechan_padre", Padre.getFechaN_Padre());
            statement.setString("pgradoi_padre", Padre.getGradoI_Padre());
            statement.setString("pdni_padre", Padre.getDni_Padre());
            statement.setString("pestadoc_padre", Padre.getEstadoC_Padre());
            statement.setString("pocupacion_padre", Padre.getOcupacion_Padre());
            statement.setString("pcentrol_padre", Padre.getCentroL_Padre());
            statement.setString("pemail_padre", Padre.getEmail_Padre());
            statement.setString("ptelefono_padre", Padre.getTelefono_Padre());
            statement.setString("ptelefono_padre2", Padre.getTelefono2_Padre());
            statement.setString("pvivecona_padre", Padre.getViveCona_Padre());
            statement.execute();
           
        }catch(SQLException ex){
            ex.printStackTrace();
        }    
    }
    
    public void ModificarPadre(String codigo,ClsEntidadPadre Padre){
        try{
            CallableStatement statement = con.prepareCall("{call sp_U_Padre(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            statement.setString("pid_padre", codigo);
            statement.setString("papellidop_padre", Padre.getApellidoP_Padre());
            statement.setString("papellidom_padre", Padre.getApellidoM_Padre());
            statement.setString("pnombre_padre", Padre.getNombre_Padre());
            statement.setString("pvive_padre", Padre.getVive_Padre());
            statement.setString("pfechan_padre", Padre.getFechaN_Padre());
            statement.setString("pgradoi_padre", Padre.getGradoI_Padre());
            statement.setString("pdni_padre", Padre.getDni_Padre());
            statement.setString("pestadoc_padre", Padre.getEstadoC_Padre());
            statement.setString("pocupacion_padre", Padre.getOcupacion_Padre());
            statement.setString("pcentrol_padre", Padre.getCentroL_Padre());
            statement.setString("pemail_padre", Padre.getEmail_Padre());
            statement.setString("ptelefono_padre", Padre.getTelefono_Padre());
            statement.setString("ptelefono_padre2", Padre.getTelefono2_Padre());
            statement.setString("pvivecona_padre", Padre.getViveCona_Padre());
            statement.execute();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public void EliminarPadre(String codigo){
        try{
            CallableStatement statement = con.prepareCall("{call sp_D_Padre(?)}");
            statement.setString("pid_padre",codigo);
            statement.execute();
            con.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public ArrayList<ClsEntidadPadre> ListarPadre(){
        ArrayList<ClsEntidadPadre> Padres = new ArrayList<ClsEntidadPadre>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_L_Padre}");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadPadre padre = new ClsEntidadPadre();
                padre.setId_Padre(rs.getString("id_padre"));
                padre.setApellidoP_Padre(rs.getString("apellidop_padre"));
                padre.setApellidoM_Padre(rs.getString("apellidom_padre"));
                padre.setNombre_Padre(rs.getString("nombre_padre"));
                padre.setVive_Padre(rs.getString("vive_padre"));
                padre.setFechaN_Padre(rs.getString("fechan_padre"));
                padre.setGradoI_Padre(rs.getString("gradoi_padre"));
                padre.setDni_Padre(rs.getString("dni_padre"));
                padre.setEstadoC_Padre(rs.getString("estadoc_padre"));
                padre.setOcupacion_Padre(rs.getString("ocupacion_padre"));
                padre.setCentroL_Padre(rs.getString("centrol_padre"));
                padre.setEmail_Padre(rs.getString("email_padre"));
                padre.setTelefono_Padre(rs.getString("telefono_padre"));
                padre.setTelefono2_Padre(rs.getString("telefono_padre2"));
                padre.setViveCona_Padre(rs.getString("vivecona_padre"));
                Padres.add(padre);
                
            }
            con.close();
            return Padres;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public ResultSet SeleccionarPadre(String codigo)  throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = con.prepareCall("{call sp_S_Padre(?)}");
            statement.setString("pid_padre", codigo);
            rs = statement.executeQuery();
           
            return rs;
        }catch(SQLException ex){
            throw ex;
        }
    }

    public String UltimoIdInsertado()  throws Exception{
        ResultSet rs;
        String cod="";
        try{
            Statement statement = con.createStatement();
            rs = statement.executeQuery("select last_insert_id() as 'id_insertado';"); 
            while(rs.next()){
                cod = rs.getString("id_insertado");
            }
            return cod;            
        }catch(SQLException ex){
            throw ex;
        }
    }
}
