/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckNegocio;

import PckConexion.ClsConexion;
import PckEntidad.ClsEntidadReportesGenerales;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClsReportesGenerales {
    private Connection con = new ClsConexion().getConection();
    /*BANDA*/
    //POR SECCION  
    public ArrayList<ClsEntidadReportesGenerales> ListarReporteBandaSeccion(String seccion){
        ArrayList<ClsEntidadReportesGenerales> Mens = new ArrayList<ClsEntidadReportesGenerales>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_R_BandaSeccion(?)}");
            statement.setString("seccion", seccion);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadReportesGenerales men = new ClsEntidadReportesGenerales();
                String hola;
                hola = rs.getString("instrumento");
                if(hola.length()>0)
                {
                    men.setAlumno(rs.getString("alumno"));
                    men.setSeccion(rs.getString("seccion"));                
                    men.setInstrumento(rs.getString("instrumento")); 
                     Mens.add(men);   
                }                
                            
            }           
            return Mens;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    
    
    //POR PERIODO  
    public ArrayList<ClsEntidadReportesGenerales> ListarReporteBandaPeriodo(String periodo){
        ArrayList<ClsEntidadReportesGenerales> Mens = new ArrayList<ClsEntidadReportesGenerales>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_R_BandaPeriodo(?)}");
            statement.setString("periodo", periodo);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadReportesGenerales men = new ClsEntidadReportesGenerales();
                String hola;
                hola = rs.getString("instrumento");
                if(hola.length()>0)
                {
                men.setAlumno(rs.getString("alumno"));
                men.setSeccion(rs.getString("seccion")); 
                men.setInstrumento(rs.getString("instrumento"));              
                Mens.add(men);   
                } 
            }           
            return Mens;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    /*END BANDA*/
    
    /*ASISTE LOS SABADOS */
    //POR SECCION  
    public ArrayList<ClsEntidadReportesGenerales> ListarReporteSabadoSeccion(String seccion){
        ArrayList<ClsEntidadReportesGenerales> Mens = new ArrayList<ClsEntidadReportesGenerales>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_R_SabadoSeccion(?)}");
            statement.setString("seccion", seccion);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadReportesGenerales men = new ClsEntidadReportesGenerales();                
                men.setAlumno(rs.getString("alumno"));
                men.setSeccion(seccion);                              
                Mens.add(men);                                                               
            }           
            return Mens;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    //POR PERIODO  
    public ArrayList<ClsEntidadReportesGenerales> ListarReporteSabadoPeriodo(String periodo){
        ArrayList<ClsEntidadReportesGenerales> Mens = new ArrayList<ClsEntidadReportesGenerales>();
      try{
            CallableStatement statement = con.prepareCall("{call sp_R_SabadoPeriodo(?)}");
            statement.setString("periodo", periodo);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ClsEntidadReportesGenerales men = new ClsEntidadReportesGenerales();
                men.setAlumno(rs.getString("alumno"));
                men.setSeccion(rs.getString("seccion"));                      
                Mens.add(men);                  
            }           
            return Mens;
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    /*CUMPLEAÑOS MPS*/
        //POR SECCION    
        public void ExcelCumpleSeccion(String seccion){
            try{
               Statement statement = con.createStatement();
                statement.executeQuery("select concat(a.nombre_alumno,' ',a.apellidop_alumno,' ',a.apellidom_alumno)as 'alumno', "
                        + "DATE_FORMAT(a.fechan_alumno, '%d/%m/%Y') as 'nacimiento',\n" +
"concat(pa.nombre_padre,' ',pa.apellidop_padre,' ',pa.apellidom_padre)as 'padre' ,pa.fechan_padre as 'nacimiento' , pa.telefono_padre,\n" +
"pa.telefono_padre2 from mat_alumno as a inner join \n" +
"mat_matricula as m on a.id_alumno = m.MPS_Alumno_id_alumno\n" +
"inner join mps_matricula_has_mps_padre as mp on mp.MPS_Matricula_id_matricula = m.id_matricula\n" +
"inner join mps_padre as pa on mp.MPS_Padre_id_padre = pa.id_padre  where m.seccion_matricula= "+"'"+seccion+"' and m.retiro='NO'"+ 
" order by alumno into outfile 'D:\\CumpleañosSeccion.csv'\n" +
"fields terminated by ' ,'\n" +
"enclosed by '\"'\n" +
"lines terminated by '\\n'");
JOptionPane.showMessageDialog(null, "Generado Correctamente en D:/CumpleañosSeccion.csv");
            }catch(SQLException ex){
               //JOptionPane.showMessageDialog(null, "El archivo ya existe, no se puede reemplazar");
                ex.printStackTrace();
            }    
        }
        //POR PERIODO
        public void ExcelCumplePeriodo(String periodo){
            try{
                Statement statement = con.createStatement();
                statement.executeQuery("select concat(a.nombre_alumno,' ',a.apellidop_alumno,' ',a.apellidom_alumno)as 'alumno', "
                        + "DATE_FORMAT(a.fechan_alumno, '%d/%m/%Y') as 'nacimiento',\n" +
"concat(pa.nombre_padre,' ',pa.apellidop_padre,' ',pa.apellidom_padre)as 'padre' ,pa.fechan_padre as 'nacimiento', pa.telefono_padre,\n" +
"pa.telefono_padre2 from mps_alumno as a inner join \n" +
"mps_matricula as m on a.id_alumno = m.MPS_Alumno_id_alumno\n" +
"inner join mps_matricula_has_mps_padre as mp on mp.MPS_Matricula_id_matricula = m.id_matricula\n" +
"inner join mps_padre as pa on mp.MPS_Padre_id_padre = pa.id_padre  where m.periodo_matricula= "+"'"+periodo+"' and m.retiro='NO'"+ 
" order by alumno into outfile 'D:\\CumpleañosPeriodo.csv'\n" +
"fields terminated by ' ,'\n" +
"enclosed by '\"'\n" +
"lines terminated by '\\n'");
               JOptionPane.showMessageDialog(null, "Generado Correctamente en D:/CumpleañosPeriodo.csv");
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "El archivo ya existe, no se puede reemplazar");
            }    
        }
    
}
