/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ClsEntidadReporte;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email jkevin.mv@gmail.com
 */
public class ClsEntidadVacantesLibres {

    private String aula;
    private String capacidad;
    private String ocupadas;
    private String libres;
    private String porcentaje;

    public ClsEntidadVacantesLibres(String aula, String capacidad, String ocupadas, String libres, String porcentaje) {
        this.aula = aula;
        this.capacidad = capacidad;
        this.ocupadas = ocupadas;
        this.libres = libres;
        this.porcentaje = porcentaje;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public String getOcupadas() {
        return ocupadas;
    }

    public void setOcupadas(String ocupadas) {
        this.ocupadas = ocupadas;
    }

    public String getLibres() {
        return libres;
    }

    public void setLibres(String libres) {
        this.libres = libres;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }

   
    
    
            
    
}
