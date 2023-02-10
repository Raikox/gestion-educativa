/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PckEntidad;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Sistemas
 */
public class ClsEntidadDeudasAlumno {
    String nino;
    String aula;
    //String total;
  public List deudas = new ArrayList(); 

    public ClsEntidadDeudasAlumno(String nino, String aula) {
        this.nino=nino;
        this.aula=aula;
        //this.total=total;
    }

    
  
    public String getNino() {
        return nino;
    }

    public void setNino(String nino) {
        this.nino = nino;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

//    public String getTotal() {
//        return total;
//    }
//
//    public void setTotal(String total) {
//        this.total = total;
//    }
    
    //esta lista actuará como hija de alumno
    public List getDeudas()   
    {       
        return deudas;   
    }    

    public void setDeudas(List Deudas)   
    {       
        this.deudas = Deudas;   
    }    

    public void addDeuda(ClsEntidadDeudasBean Deuda)   
    {       
        this.deudas.add(Deuda);   
    }
    
    //"dsreport" debe tener el mismo nombre que el Field creado como parámetro
    //en el reporte padre que será enviado como dataSource al hijo
    public JRDataSource getdsreport()   
    {   //se envia al dsreport la lista de deudas que pertenecera a un 
        //determinado alumno
        return new JRBeanCollectionDataSource(deudas);   
    } 
}
