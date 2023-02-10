/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mpsEntity;

import java.text.ParseException;
import static prymatricula.ClsMisc.ConvertirEUAtoEUstring;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email jkevin.mv@gmail.com
 */
public class AlumnoMatriculaEntity {

    private String id_matricula;
    private String id_alumno;
    private String nombre_alumno;
    private String apellidop_alumno;
    private String apellidom_alumno;
    private String retiro;
    private String retiro_fecha;
    private String retiro_motivo;
    private String id_seccion;
    private String nombre_seccion;

    public AlumnoMatriculaEntity(String id_matricula, String id_alumno, String nombre_alumno, String apellidop_alumno, String apellidom_alumno, String retiro, String retiro_fecha, String retiro_motivo, String id_seccion, String nombre_seccion) {
        this.id_matricula = id_matricula;
        this.id_alumno = id_alumno;
        this.nombre_alumno = nombre_alumno;
        this.apellidop_alumno = apellidop_alumno;
        this.apellidom_alumno = apellidom_alumno;
        this.retiro = retiro;
        this.retiro_fecha = retiro_fecha;
        this.retiro_motivo = retiro_motivo;
        this.id_seccion = id_seccion;
        this.nombre_seccion = nombre_seccion;
    }

    
    public String getId_matricula() {
        return id_matricula;
    }

    public void setId_matricula(String id_matricula) {
        this.id_matricula = id_matricula;
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

    public String getRetiro() {
        return retiro;
    }

    public void setRetiro(String retiro) {
        this.retiro = retiro;
    }

    public String getRetiro_fecha() {
        return retiro_fecha;
    }

    public void setRetiro_fecha(String retiro_fecha) {
        this.retiro_fecha = retiro_fecha;
    }

    public String getRetiro_motivo() {
        return retiro_motivo;
    }

    public void setRetiro_motivo(String retiro_motivo) {
        this.retiro_motivo = retiro_motivo;
    }

    public String getId_seccion() {
        return id_seccion;
    }

    public void setId_seccion(String id_seccion) {
        this.id_seccion = id_seccion;
    }

    public String getNombre_seccion() {
        return nombre_seccion;
    }

    public void setNombre_seccion(String nombre_seccion) {
        this.nombre_seccion = nombre_seccion;
    }

    ////
    
    public String getFecha_retiroEu() throws ParseException{
        return ConvertirEUAtoEUstring(this.retiro_fecha);
    }
    
    public String getAlumno_nombre_completo() {
        return this.getNombre_alumno()+" "+this.getApellidop_alumno()+" "+this.getApellidom_alumno();
    }
    
    
    
}
