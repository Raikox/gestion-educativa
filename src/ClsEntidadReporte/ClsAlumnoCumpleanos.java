/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ClsEntidadReporte;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email Jkevin.mv@gmail.com
 */
public class ClsAlumnoCumpleanos {
    private String nombre;
    private String fechan;
    private String edad;
    private List padremadre = new ArrayList(); 
    //constructor

    public ClsAlumnoCumpleanos(String nombre, String fechan, String edad) {
        this.nombre = nombre;
        this.fechan = fechan;
        this.edad = edad;
    }
    
    public String getFechan() {
        return fechan;
    }

    public void setFechan(String fechan) {
        this.fechan = fechan;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
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
    public void addPadreMadre(ClsPadreMadreCumpleanos pm)   
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
