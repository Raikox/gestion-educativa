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
public class ClsEntidadAlumnoPago {
    //variables que se usan en la tabla de pagos que es la lista de alumnos,
    //solo se visualizará apellidos y nombres, lo demas será utilizado como
    //vista al momento de hacer clic en la tabla y no tener que hacer más
    //consultas
    private String Id_Matricula;
    private String Id_Alumno;
    private String Apellidos_Nombres;
    private String Id_Seccion;
    private String Nombre_Seccion;
    private String S_Alimentacion;
    private String Inicio_Clases;
    private String Horario_Entrada;
    private String Horario_Salida;
    private String Nombre_Periodo;
    private String Nacimiento;
    private String edad;

    public ClsEntidadAlumnoPago(String Id_Alumno, String Apellidos_Nombres) {
        this.Id_Alumno = Id_Alumno;
        this.Apellidos_Nombres = Apellidos_Nombres;
    }
    
    public ClsEntidadAlumnoPago() {
    }
    
    public String getId_Alumno() {
        return Id_Alumno;
    }

    public void setId_Alumno(String Id_Alumno) {
        this.Id_Alumno = Id_Alumno;
    }
    
    public String getNacimiento() {
        return Nacimiento;
    }

    public void setNacimiento(String Nacimiento) {
        this.Nacimiento = Nacimiento;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getNombre_Periodo() {
        return Nombre_Periodo;
    }

    public void setNombre_Periodo(String Nombre_Periodo) {
        this.Nombre_Periodo = Nombre_Periodo;
    }        

    public String getId_Matricula() {
        return Id_Matricula;
    }

    public void setId_Matricula(String Id_Matricula) {
        this.Id_Matricula = Id_Matricula;
    }

    public String getApellidos_Nombres() {
        return Apellidos_Nombres;
    }

    public void setApellidos_Nombres(String Apellidos_Nombres) {
        this.Apellidos_Nombres = Apellidos_Nombres;
    }

    public String getId_Seccion() {
        return Id_Seccion;
    }

    public void setId_Seccion(String Id_Seccion) {
        this.Id_Seccion = Id_Seccion;
    }

    public String getNombre_Seccion() {
        return Nombre_Seccion;
    }

    public void setNombre_Seccion(String Nombre_Seccion) {
        this.Nombre_Seccion = Nombre_Seccion;
    }

    public String getS_Alimentacion() {
        return S_Alimentacion;
    }

    public void setS_Alimentacion(String S_Alimentacion) {
        this.S_Alimentacion = S_Alimentacion;
    }

    public String getInicio_Clases() {
        return Inicio_Clases;
    }

    public void setInicio_Clases(String Inicio_Clases) {
        this.Inicio_Clases = Inicio_Clases;
    }

    public String getHorario_Entrada() {
        return Horario_Entrada;
    }

    public void setHorario_Entrada(String Horario_Entrada) {
        this.Horario_Entrada = Horario_Entrada;
    }

    public String getHorario_Salida() {
        return Horario_Salida;
    }

    public void setHorario_Salida(String Horario_Salida) {
        this.Horario_Salida = Horario_Salida;
    }
    
}
