/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckEntidad;

import java.util.Date;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 */
public class ClsEntidadEdad {
    private String Id_Alumno;
    private Date Nacimiento_Alumno;   
    private String Id_Matricula;   
    private String Edad_Matricula;

    public String getId_Alumno() {
        return Id_Alumno;
    }

    public void setId_Alumno(String Id_Alumno) {
        this.Id_Alumno = Id_Alumno;
    }

    public Date getNacimiento_Alumno() {
        return Nacimiento_Alumno;
    }

    public void setNacimiento_Alumno(Date Nacimiento_Alumno) {
        this.Nacimiento_Alumno = Nacimiento_Alumno;
    }

    public String getId_Matricula() {
        return Id_Matricula;
    }

    public void setId_Matricula(String Id_Matricula) {
        this.Id_Matricula = Id_Matricula;
    }

    public String getEdad_Matricula() {
        return Edad_Matricula;
    }

    public void setEdad_Matricula(String Edad_Matricula) {
        this.Edad_Matricula = Edad_Matricula;
    }
    
    
}
