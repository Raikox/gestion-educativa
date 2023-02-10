/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckEntidad;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email Jkevin.mv@gmail.com
 */
public class ClsEntidadAlumnoAnecdotario {
    
    private String anecdotario_id;
    private String anecdotario_solucion;
    private String anecdotario_estado;
    private String anecdotario_fecha;
    private String id_alumno;
    private String nombre_alumno;
    private String apellidop_alumno;
    private String apellidom_alumno;
    private String id_matricula;
    private String seccion_id;
    private String nombre_seccion;

    public ClsEntidadAlumnoAnecdotario(String anecdotario_id, String anecdotario_solucion, String anecdotario_estado, String anecdotario_fecha, String id_alumno, String nombre_alumno, String apellidop_alumno, String apellidom_alumno, String id_matricula, String seccion_id, String nombre_seccion) {
        this.anecdotario_id = anecdotario_id;
        this.anecdotario_solucion = anecdotario_solucion;
        this.anecdotario_estado = anecdotario_estado;
        this.anecdotario_fecha = anecdotario_fecha;
        this.id_alumno = id_alumno;
        this.nombre_alumno = nombre_alumno;
        this.apellidop_alumno = apellidop_alumno;
        this.apellidom_alumno = apellidom_alumno;
        this.id_matricula = id_matricula;
        this.seccion_id = seccion_id;
        this.nombre_seccion = nombre_seccion;
    }

    public ClsEntidadAlumnoAnecdotario() {
    }
    
    public String getAnecdotario_id() {
        return anecdotario_id;
    }

    public void setAnecdotario_id(String anecdotario_id) {
        this.anecdotario_id = anecdotario_id;
    }

    public String getAnecdotario_solucion() {
        return anecdotario_solucion;
    }

    public void setAnecdotario_solucion(String anecdotario_solucion) {
        this.anecdotario_solucion = anecdotario_solucion;
    }

    public String getAnecdotario_estado() {
        return anecdotario_estado;
    }

    public void setAnecdotario_estado(String anecdotario_estado) {
        this.anecdotario_estado = anecdotario_estado;
    }

    public String getAnecdotario_fecha() {
        return anecdotario_fecha;
    }

    public void setAnecdotario_fecha(String anecdotario_fecha) {
        this.anecdotario_fecha = anecdotario_fecha;
    }

    public String getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(String id_alumno) {
        this.id_alumno = id_alumno;
    }

    public String getNombre_alumno() {
        return nombre_alumno;
    }

    public void setNombre_alumno(String nombre_alumno) {
        this.nombre_alumno = nombre_alumno;
    }

    public String getApellidop_alumno() {
        return apellidop_alumno;
    }

    public void setApellidop_alumno(String apellidop_alumno) {
        this.apellidop_alumno = apellidop_alumno;
    }

    public String getApellidom_alumno() {
        return apellidom_alumno;
    }

    public void setApellidom_alumno(String apellidom_alumno) {
        this.apellidom_alumno = apellidom_alumno;
    }

    public String getId_matricula() {
        return id_matricula;
    }

    public void setId_matricula(String id_matricula) {
        this.id_matricula = id_matricula;
    }

    public String getSeccion_id() {
        return seccion_id;
    }

    public void setSeccion_id(String seccion_id) {
        this.seccion_id = seccion_id;
    }

    public String getNombre_seccion() {
        return nombre_seccion;
    }

    public void setNombre_seccion(String nombre_seccion) {
        this.nombre_seccion = nombre_seccion;
    }

    public String getNombreCompleto() {
        return apellidop_alumno + " " + apellidom_alumno + ", " + nombre_alumno; 
    }
    
}
