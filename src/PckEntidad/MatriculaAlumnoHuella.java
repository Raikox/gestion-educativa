/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckEntidad;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email jkevin.mv@gmail.com
 */
public class MatriculaAlumnoHuella {

    private int id_matricula;
    private Object HuellaDigital;
    private String apellidos_alumno;
    private String nombres_alumno;
    private String aula_alumno;

    public MatriculaAlumnoHuella(int id_matricula, Object HuellaDigital, String apellidos_alumno, String nombres_alumno, String aula_alumno) {
        this.id_matricula = id_matricula;
        this.HuellaDigital = HuellaDigital;
        this.apellidos_alumno = apellidos_alumno;
        this.nombres_alumno = nombres_alumno;
        this.aula_alumno = aula_alumno;
    }
    
    public MatriculaAlumnoHuella(int id_matricula, Object HuellaDigital, String apellidos_alumno, String nombres_alumno) {
        this.id_matricula = id_matricula;
        this.HuellaDigital = HuellaDigital;
        this.apellidos_alumno = apellidos_alumno;
        this.nombres_alumno = nombres_alumno;        
    }
    
    public int getId_matricula() {
        return id_matricula;
    }

    public void setId_matricula(int id_matricula) {
        this.id_matricula = id_matricula;
    }

    public Object getHuellaDigital() {
        return HuellaDigital;
    }

    public void setHuellaDigital(Object HuellaDigital) {
        this.HuellaDigital = HuellaDigital;
    }

    public String getApellidos_alumno() {
        return apellidos_alumno;
    }

    public void setApellidos_alumno(String apellidos_alumno) {
        this.apellidos_alumno = apellidos_alumno;
    }

    public String getNombres_alumno() {
        return nombres_alumno;
    }

    public void setNombres_alumno(String nombres_alumno) {
        this.nombres_alumno = nombres_alumno;
    }

    public String getAula_alumno() {
        return aula_alumno;
    }

    public void setAula_alumno(String aula_alumno) {
        this.aula_alumno = aula_alumno;
    }
    
    public String getNombresApellidosAlumno() {
        return apellidos_alumno + ", " +nombres_alumno;
    }
    
    public String getTieneHuella() {
        
        String tieneHuella;
        if(HuellaDigital != null) {
            tieneHuella = "SI";
        }
        else {
            tieneHuella = "NO";
        }
        
        return tieneHuella;
    }
}
