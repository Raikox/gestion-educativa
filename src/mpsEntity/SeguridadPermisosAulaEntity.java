/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mpsEntity;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email jkevin.mv@gmail.com
 */
public class SeguridadPermisosAulaEntity {

    String rol_id;
    String anecdotario;
    String psicologia;
    String asistencia;
    String inasistencia;
    String cumplimiento;
    String requerimiento_almacen;
    String item_consulta;

    public SeguridadPermisosAulaEntity(String anecdotario, String psicologia, String asistencia, String inasistencia, String cumplimiento, String requerimiento_almacen, String item_consulta) {
        this.anecdotario = anecdotario;
        this.psicologia = psicologia;
        this.asistencia = asistencia;
        this.inasistencia = inasistencia;
        this.cumplimiento = cumplimiento;
        this.requerimiento_almacen = requerimiento_almacen;
        this.item_consulta = item_consulta;
    }
        
    public String getRol_id() {
        return rol_id;
    }

    public void setRol_id(String rol_id) {
        this.rol_id = rol_id;
    }

    public String getAnecdotario() {
        return anecdotario;
    }

    public void setAnecdotario(String anecdotario) {
        this.anecdotario = anecdotario;
    }

    public String getPsicologia() {
        return psicologia;
    }

    public void setPsicologia(String psicologia) {
        this.psicologia = psicologia;
    }

    public String getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }

    public String getInasistencia() {
        return inasistencia;
    }

    public void setInasistencia(String inasistencia) {
        this.inasistencia = inasistencia;
    }

    public String getCumplimiento() {
        return cumplimiento;
    }

    public void setCumplimiento(String cumplimiento) {
        this.cumplimiento = cumplimiento;
    }

    public String getRequerimiento_almacen() {
        return requerimiento_almacen;
    }

    public void setRequerimiento_almacen(String requerimiento_almacen) {
        this.requerimiento_almacen = requerimiento_almacen;
    }

    public String getItem_consulta() {
        return item_consulta;
    }

    public void setItem_consulta(String item_consulta) {
        this.item_consulta = item_consulta;
    }
    
    
    
}
