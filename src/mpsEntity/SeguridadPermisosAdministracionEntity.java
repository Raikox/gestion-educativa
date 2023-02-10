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
public class SeguridadPermisosAdministracionEntity {

    String rol_id;
    String lonchera;
    String anecdotario;
    String psicologia;
    String psicologia_editar;
    String asistencia;
    String asistencia_consulta;
    String cumplimiento;
    String almacen;
    String item;
    String requerimiento;
    String item_consulta;

    public SeguridadPermisosAdministracionEntity(String lonchera, String anecdotario, String psicologia, String psicologia_editar, String asistencia, String asistencia_consulta, String cumplimiento, String almacen, String item, String requerimiento, String item_consulta) {
        this.lonchera = lonchera;
        this.anecdotario = anecdotario;
        this.psicologia = psicologia;
        this.psicologia_editar = psicologia_editar;
        this.asistencia = asistencia;
        this.asistencia_consulta = asistencia_consulta;
        this.cumplimiento = cumplimiento;
        this.almacen = almacen;
        this.item = item;
        this.requerimiento = requerimiento;
        this.item_consulta = item_consulta;
    }

    public String getRol_id() {
        return rol_id;
    }

    public void setRol_id(String rol_id) {
        this.rol_id = rol_id;
    }

    public String getLonchera() {
        return lonchera;
    }

    public void setLonchera(String lonchera) {
        this.lonchera = lonchera;
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

    public String getPsicologia_editar() {
        return psicologia_editar;
    }

    public void setPsicologia_editar(String psicologia_editar) {
        this.psicologia_editar = psicologia_editar;
    }

    public String getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }

    public String getAsistencia_consulta() {
        return asistencia_consulta;
    }

    public void setAsistencia_consulta(String asistencia_consulta) {
        this.asistencia_consulta = asistencia_consulta;
    }

    public String getCumplimiento() {
        return cumplimiento;
    }

    public void setCumplimiento(String cumplimiento) {
        this.cumplimiento = cumplimiento;
    }

    public String getAlmacen() {
        return almacen;
    }

    public void setAlmacen(String almacen) {
        this.almacen = almacen;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getRequerimiento() {
        return requerimiento;
    }

    public void setRequerimiento(String requerimiento) {
        this.requerimiento = requerimiento;
    }

    public String getItem_consulta() {
        return item_consulta;
    }

    public void setItem_consulta(String item_consulta) {
        this.item_consulta = item_consulta;
    }

    
    
}
