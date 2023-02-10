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
public class ClsEntidadPagoAulaDeuda {
    
    private String pgo_aula_deuda_id;
    private String pgo_aula_deuda_estado;
    private String id_matricula;
    private String pgo_aula_id;

    public ClsEntidadPagoAulaDeuda(String pgo_aula_deuda_id, String pgo_aula_deuda_estado, String id_matricula, String pgo_aula_id) {
        this.pgo_aula_deuda_id = pgo_aula_deuda_id;
        this.pgo_aula_deuda_estado = pgo_aula_deuda_estado;
        this.id_matricula = id_matricula;
        this.pgo_aula_id = pgo_aula_id;
    }

    public String getPgo_aula_id() {
        return pgo_aula_id;
    }

    public void setPgo_aula_id(String pgo_aula_id) {
        this.pgo_aula_id = pgo_aula_id;
    }

    public String getPgo_aula_deuda_id() {
        return pgo_aula_deuda_id;
    }

    public void setPgo_aula_deuda_id(String pgo_aula_deuda_id) {
        this.pgo_aula_deuda_id = pgo_aula_deuda_id;
    }

    public String getPgo_aula_deuda_estado() {
        return pgo_aula_deuda_estado;
    }

    public void setPgo_aula_deuda_estado(String pgo_aula_deuda_estado) {
        this.pgo_aula_deuda_estado = pgo_aula_deuda_estado;
    }

    public String getId_matricula() {
        return id_matricula;
    }

    public void setId_matricula(String id_matricula) {
        this.id_matricula = id_matricula;
    }
    
    
}
