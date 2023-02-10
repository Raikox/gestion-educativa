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
public class ClsEntidadInasistenciaFicha {
    
    private String inasistencia_ficha_id;
    private String inasistencia_ficha_justificada;
    private String inasistencia_ficha_fecha;
    private String inasistencia_ficha_telefono;
    private String inasistencia_ficha_contacto;
    private String inasistencia_ficha_motivo;
    private String inasistencia_ficha_retorno;
    private String mat_seccion_id;
    private String mat_alumno_id;

    public ClsEntidadInasistenciaFicha(String inasistencia_ficha_id, String inasistencia_ficha_justificada, String inasistencia_ficha_fecha, String inasistencia_ficha_telefono, String inasistencia_ficha_contacto, String inasistencia_ficha_motivo, String inasistencia_ficha_retorno, String mat_seccion_id, String mat_alumno_id) {
        this.inasistencia_ficha_id = inasistencia_ficha_id;
        this.inasistencia_ficha_justificada = inasistencia_ficha_justificada;
        this.inasistencia_ficha_fecha = inasistencia_ficha_fecha;
        this.inasistencia_ficha_telefono = inasistencia_ficha_telefono;
        this.inasistencia_ficha_contacto = inasistencia_ficha_contacto;
        this.inasistencia_ficha_motivo = inasistencia_ficha_motivo;
        this.inasistencia_ficha_retorno = inasistencia_ficha_retorno;
        this.mat_seccion_id = mat_seccion_id;
        this.mat_alumno_id = mat_alumno_id;
    }
    
    public String getInasistencia_ficha_id() {
        return inasistencia_ficha_id;
    }

    public void setInasistencia_ficha_id(String inasistencia_ficha_id) {
        this.inasistencia_ficha_id = inasistencia_ficha_id;
    }

    public String getInasistencia_ficha_justificada() {
        return inasistencia_ficha_justificada;
    }

    public void setInasistencia_ficha_justificada(String inasistencia_ficha_justificada) {
        this.inasistencia_ficha_justificada = inasistencia_ficha_justificada;
    }
    
    public String getInasistencia_ficha_fecha() {
        return inasistencia_ficha_fecha;
    }

    public void setInasistencia_ficha_fecha(String inasistencia_ficha_fecha) {
        this.inasistencia_ficha_fecha = inasistencia_ficha_fecha;
    }

    public String getInasistencia_ficha_telefono() {
        return inasistencia_ficha_telefono;
    }

    public void setInasistencia_ficha_telefono(String inasistencia_ficha_telefono) {
        this.inasistencia_ficha_telefono = inasistencia_ficha_telefono;
    }

    public String getInasistencia_ficha_contacto() {
        return inasistencia_ficha_contacto;
    }

    public void setInasistencia_ficha_contacto(String inasistencia_ficha_contacto) {
        this.inasistencia_ficha_contacto = inasistencia_ficha_contacto;
    }

    public String getInasistencia_ficha_motivo() {
        return inasistencia_ficha_motivo;
    }

    public void setInasistencia_ficha_motivo(String inasistencia_ficha_motivo) {
        this.inasistencia_ficha_motivo = inasistencia_ficha_motivo;
    }

    public String getInasistencia_ficha_retorno() {
        return inasistencia_ficha_retorno;
    }

    public void setInasistencia_ficha_retorno(String inasistencia_ficha_retorno) {
        this.inasistencia_ficha_retorno = inasistencia_ficha_retorno;
    }

    public String getMat_seccion_id() {
        return mat_seccion_id;
    }

    public void setMat_seccion_id(String mat_seccion_id) {
        this.mat_seccion_id = mat_seccion_id;
    }

    public String getMat_alumno_id() {
        return mat_alumno_id;
    }

    public void setMat_alumno_id(String mat_alumno_id) {
        this.mat_alumno_id = mat_alumno_id;
    }
    
    
}
