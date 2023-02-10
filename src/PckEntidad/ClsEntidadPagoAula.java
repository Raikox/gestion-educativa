/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PckEntidad;

/**
 *
 * @author Kevin
 */
public class ClsEntidadPagoAula {
    private String pgo_aula_id;
    private String pgo_aula_nombre;
    private String pgo_aula_anio;

    public ClsEntidadPagoAula(String pgo_aula_id, String pgo_aula_nombre, String pgo_aula_anio) {
        this.pgo_aula_id = pgo_aula_id;
        this.pgo_aula_nombre = pgo_aula_nombre;
        this.pgo_aula_anio = pgo_aula_anio;
    }
    
    public String getPgo_aula_id() {
        return pgo_aula_id;
    }

    public void setPgo_aula_id(String pgo_aula_id) {
        this.pgo_aula_id = pgo_aula_id;
    }

    public String getPgo_aula_nombre() {
        return pgo_aula_nombre;
    }

    public void setPgo_aula_nombre(String pgo_aula_nombre) {
        this.pgo_aula_nombre = pgo_aula_nombre;
    }

    public String getPgo_aula_anio() {
        return pgo_aula_anio;
    }

    public void setPgo_aula_anio(String pgo_aula_anio) {
        this.pgo_aula_anio = pgo_aula_anio;
    }
    
    
}
