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
public class ClsEntidadBoletaBoletaImpresion {
    private String codigo;
    private String nombre;
    private String fecha;
    public List detalle = new ArrayList(); 
    public ClsEntidadBoletaBoletaImpresion(String codigo, String nombre, String fecha) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List getDetalle() {
        return detalle;
    }

    public void setDetalle(List detalle) {
        this.detalle = detalle;
    }
    
    public void addDetalle(ClsEntidadBoletaBoletaDetalle Detalle)
    {
        this.detalle.add(Detalle);
    }
    
    public JRDataSource getdsreport()   
    {   //se envia al dsreport la lista de deudas que pertenecera a un 
        //determinado alumno
        return new JRBeanCollectionDataSource(detalle);   
    } 
    
}
