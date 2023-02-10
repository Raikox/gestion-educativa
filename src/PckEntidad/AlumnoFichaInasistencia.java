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
public class AlumnoFichaInasistencia {

    private String Id_Alumno;
    private String Apellidos_Nombres;
    private String Aula;

    public AlumnoFichaInasistencia(String Id_Alumno, String Apellidos_Nombres) {
        this.Id_Alumno = Id_Alumno;
        this.Apellidos_Nombres = Apellidos_Nombres;        
    }

    public AlumnoFichaInasistencia(String Id_Alumno, String Apellidos_Nombres, String Aula) {
        this.Id_Alumno = Id_Alumno;
        this.Apellidos_Nombres = Apellidos_Nombres;
        this.Aula = Aula;
    }    

    public String getId_Alumno() {
        return Id_Alumno;
    }

    public void setId_Alumno(String Id_Alumno) {
        this.Id_Alumno = Id_Alumno;
    }

    public String getApellidos_Nombres() {
        return Apellidos_Nombres;
    }

    public void setApellidos_Nombres(String Apellidos_Nombres) {
        this.Apellidos_Nombres = Apellidos_Nombres;
    }

    public String getAula() {
        return Aula;
    }

    public void setAula(String Aula) {
        this.Aula = Aula;
    }    
    
    @Override
    public String toString() {
        return Apellidos_Nombres;
    }
    
    @Override
    public boolean equals(Object obj) {
        return (this.Id_Alumno.equals(((AlumnoFichaInasistencia) obj).Id_Alumno) && (this.Apellidos_Nombres.equals(((AlumnoFichaInasistencia) obj).Apellidos_Nombres)));

    }
    
}
