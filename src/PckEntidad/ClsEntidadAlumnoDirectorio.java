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
 * @author John Kevin Montes De Oca Vizcarra
 * @email Jkevin.mv@gmail.com
 */
public class ClsEntidadAlumnoDirectorio {
    private String nombre;
    private List padremadre = new ArrayList(); 
    //constructor
    public ClsEntidadAlumnoDirectorio(String nombre)
    {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    //Lista clase hija
    public List getPadremadre() {
        return padremadre;
    }

    public void setPadremadre(List padremadre) {
        this.padremadre = padremadre;
    }
    
    //metodo para agregar Padre y Madre a la lista
    public void addPadreMadre(ClsEntidadPadreMadreDirectorio pm)   
    {       
        this.padremadre.add(pm);
    }
    
    //JRDataSource para el reporte (origen de datos) manda la lista
    public JRDataSource getdsreport()   
    {   //se envia al dsreport la lista de deudas que pertenecera a un 
        //determinado alumno
        return new JRBeanCollectionDataSource(padremadre);   
    } 
}
