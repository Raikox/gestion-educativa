/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckEntidad;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 */
public class ClsEntidadAsistencia {
    
    private String Asistencia_Id;
    private String Asistencia_Alumno_Id;
    private String Asistencia_Fecha;
    private String Asistencia_Hora;    
    private String Seccion;
    private String Comen;
    private String Apellidos_Nombres;
    
    public ClsEntidadAsistencia() {
    }

    public ClsEntidadAsistencia(String Asistencia_Alumno_Id, String Asistencia_Fecha, String Apellidos_Nombres) {
        this.Asistencia_Alumno_Id = Asistencia_Alumno_Id;
        this.Asistencia_Fecha = Asistencia_Fecha;
        this.Apellidos_Nombres = Apellidos_Nombres;
    }

    public String getApellidos_Nombres() {
        return Apellidos_Nombres;
    }

    public void setApellidos_Nombres(String Apellidos_Nombres) {
        this.Apellidos_Nombres = Apellidos_Nombres;
    }
          
    public String getSeccion() {
        return Seccion;
    }

    public void setSeccion(String Seccion) {
        this.Seccion = Seccion;
    }

    public String getComen() {
        return Comen;
    }

    public void setComen(String Comen) {
        this.Comen = Comen;
    }
        
    public String getAsistencia_Id() {
        return Asistencia_Id;
    }

    public void setAsistencia_Id(String Asistencia_Id) {
        this.Asistencia_Id = Asistencia_Id;
    }

    public String getAsistencia_Alumno_Id() {
        return Asistencia_Alumno_Id;
    }

    public void setAsistencia_Alumno_Id(String Asistencia_Alumno_Id) {
        this.Asistencia_Alumno_Id = Asistencia_Alumno_Id;
    }

    public String getAsistencia_Fecha() {
        return Asistencia_Fecha;
    }

    public void setAsistencia_Fecha(String Asistencia_Fecha) {
        this.Asistencia_Fecha = Asistencia_Fecha;
    }

    public String getAsistencia_Hora() {
        return Asistencia_Hora;
    }

    public void setAsistencia_Hora(String Asistencia_Hora) {
        this.Asistencia_Hora = Asistencia_Hora;
    }

    
    
    
}
