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
public class RequerimientoEntity {

    private int requerimiento_id;
    private String requerimiento_numero;
    private String requerimiento_fecha;
    private String requerimiento_tema;
    private String requerimiento_estado;
    private  int id_seccion;
    private String nombre_seccion;

    public int getRequerimiento_id() {
        return requerimiento_id;
    }

    public void setRequerimiento_id(int requerimiento_id) {
        this.requerimiento_id = requerimiento_id;
    }

    public String getRequerimiento_numero() {
        return requerimiento_numero;
    }

    public void setRequerimiento_numero(String requerimiento_numero) {
        this.requerimiento_numero = requerimiento_numero;
    }

    public String getRequerimiento_fecha() {
        return requerimiento_fecha;
    }

    public void setRequerimiento_fecha(String requerimiento_fecha) {
        this.requerimiento_fecha = requerimiento_fecha;
    }

    public String getRequerimiento_tema() {
        return requerimiento_tema;
    }

    public void setRequerimiento_tema(String requerimiento_tema) {
        this.requerimiento_tema = requerimiento_tema;
    }

    public String getRequerimiento_estado() {
        return requerimiento_estado;
    }

    public void setRequerimiento_estado(String requerimiento_estado) {
        this.requerimiento_estado = requerimiento_estado;
    }

    public int getId_seccion() {
        return id_seccion;
    }

    public void setId_seccion(int id_seccion) {
        this.id_seccion = id_seccion;
    }

    public String getNombre_seccion() {
        return nombre_seccion;
    }

    public void setNombre_seccion(String nombre_seccion) {
        this.nombre_seccion = nombre_seccion;
    }
    
    
}
