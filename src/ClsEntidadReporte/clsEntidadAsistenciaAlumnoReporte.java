/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClsEntidadReporte;

import PckNegocio.clsEntidadAsistenciaAlmunoReporteBean;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Kevin
 */
public class clsEntidadAsistenciaAlumnoReporte {
    private String inicio;
    private String fin;
    private String nino;
    private String aula;
    

    public List asistenciaPorcentaje = new ArrayList();
    
    public clsEntidadAsistenciaAlumnoReporte(String inicio, String fin, String nino, String aula) {
        this.inicio = inicio;
        this.fin = fin;
        this.nino = nino;
        this.aula = aula;        
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
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
    
    public List getAsistenciaPorcentaje() {
        return asistenciaPorcentaje;
    }

    public void setAsistenciaPorcentaje(List asistenciaPorcentaje) {
        this.asistenciaPorcentaje = asistenciaPorcentaje;
    }
    
    public void addAsistenciaPorcentaje(clsEntidadAsistenciaAlmunoReporteBean aporcentaje)   
    {       
        this.asistenciaPorcentaje.add(aporcentaje);   
    }
    
    public JRDataSource getdsreport()   
    {   //se envia al dsreport la lista de deudas que pertenecera a un 
        //determinado alumno
        return new JRBeanCollectionDataSource(asistenciaPorcentaje);   
    }
}
