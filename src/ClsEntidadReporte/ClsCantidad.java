/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClsEntidadReporte;

/**
 *
 * @author Kevin
 */
public class ClsCantidad {
    
    private String aula;
    private String matriculados;
    private String conAlimentacion;
    private String sinAlimentacion;
    private String siAsistieron;
    private String noAsistieron;
    private String alimentacionHoy;

    public ClsCantidad(String aula, String matriculados, String conAlimentacion, String sinAlimentacion, String siAsistieron, String noAsistieron, String alimentacionHoy) {
        this.aula = aula;
        this.matriculados = matriculados;
        this.conAlimentacion = conAlimentacion;
        this.sinAlimentacion = sinAlimentacion;
        this.siAsistieron = siAsistieron;
        this.noAsistieron = noAsistieron;
        this.alimentacionHoy = alimentacionHoy;
    }    
    
    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getMatriculados() {
        return matriculados;
    }

    public void setMatriculados(String matriculados) {
        this.matriculados = matriculados;
    }

    public String getConAlimentacion() {
        return conAlimentacion;
    }

    public void setConAlimentacion(String conAlimentacion) {
        this.conAlimentacion = conAlimentacion;
    }

    public String getSinAlimentacion() {
        return sinAlimentacion;
    }

    public void setSinAlimentacion(String sinAlimentacion) {
        this.sinAlimentacion = sinAlimentacion;
    }

    public String getSiAsistieron() {
        return siAsistieron;
    }

    public void setSiAsistieron(String siAsistieron) {
        this.siAsistieron = siAsistieron;
    }

    public String getNoAsistieron() {
        return noAsistieron;
    }

    public void setNoAsistieron(String noAsistieron) {
        this.noAsistieron = noAsistieron;
    }

    public String getAlimentacionHoy() {
        return alimentacionHoy;
    }

    public void setAlimentacionHoy(String alimentacionHoy) {
        this.alimentacionHoy = alimentacionHoy;
    }
    
    
}
