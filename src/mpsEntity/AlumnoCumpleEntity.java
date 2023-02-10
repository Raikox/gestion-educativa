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
public class AlumnoCumpleEntity {

    private String nombreCumple;
    private String alumno;
    private String aula;
    private String fecha;

    public AlumnoCumpleEntity(String nombreCumple, String alumno, String aula, String fecha) {
        this.nombreCumple = nombreCumple;
        this.alumno = alumno;
        this.aula = aula;
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombreCumple() {
        return nombreCumple;
    }

    public void setNombreCumple(String nombreCumple) {
        this.nombreCumple = nombreCumple;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }
    
    
}
