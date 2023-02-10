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
public class ClsEntidadSubEntregable {
    private String subentregable_id;
    private String subentregable_nombre;
    private String subentregable_estado;
    private String subentregable_fecha;
    private String subentregable_mensaje;
    private String entregable_id;

    public String getSubentregable_fecha() {
        return subentregable_fecha;
    }

    public void setSubentregable_fecha(String subentregable_fecha) {
        this.subentregable_fecha = subentregable_fecha;
    }    
    
    public String getSubentregable_id() {
        return subentregable_id;
    }

    public void setSubentregable_id(String subentregable_id) {
        this.subentregable_id = subentregable_id;
    }

    public String getSubentregable_nombre() {
        return subentregable_nombre;
    }

    public void setSubentregable_nombre(String subentregable_nombre) {
        this.subentregable_nombre = subentregable_nombre;
    }

    public String getSubentregable_estado() {
        return subentregable_estado;
    }

    public void setSubentregable_estado(String subentregable_estado) {
        this.subentregable_estado = subentregable_estado;
    }

    public String getSubentregable_mensaje() {
        return subentregable_mensaje;
    }

    public void setSubentregable_mensaje(String subentregable_mensaje) {
        this.subentregable_mensaje = subentregable_mensaje;
    }

    public String getEntregable_id() {
        return entregable_id;
    }

    public void setEntregable_id(String entregable_id) {
        this.entregable_id = entregable_id;
    }
    
    
}
