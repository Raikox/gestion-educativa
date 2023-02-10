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
public class ClsEntidadAnecdotario {
    private String Anecdotario_id;
    private String Anecdotario_anecdota;
    private String Anecdotario_solucion;
    private String Anecdotario_estado;
    private String Anecdotario_fecha;
    private String Id_Alumno;

    public ClsEntidadAnecdotario(String Anecdotario_id, String Anecdotario_anecdota, String Anecdotario_solucion, String Anecdotario_estado, String Anecdotario_fecha, String Id_Alumno) {
        this.Anecdotario_id = Anecdotario_id;
        this.Anecdotario_anecdota = Anecdotario_anecdota;
        this.Anecdotario_solucion = Anecdotario_solucion;
        this.Anecdotario_estado = Anecdotario_estado;
        this.Anecdotario_fecha = Anecdotario_fecha;
        this.Id_Alumno = Id_Alumno;
    }

    public ClsEntidadAnecdotario(String Anecdotario_id, String Anecdotario_anecdota, String Anecdotario_estado, String Anecdotario_fecha) {
        this.Anecdotario_id = Anecdotario_id;
        this.Anecdotario_anecdota = Anecdotario_anecdota;
        this.Anecdotario_estado = Anecdotario_estado;
        this.Anecdotario_fecha = Anecdotario_fecha;
    }
    
    public ClsEntidadAnecdotario(String Anecdotario_id, String Anecdotario_anecdota, String Anecdotario_estado) {
        this.Anecdotario_id = Anecdotario_id;
        this.Anecdotario_anecdota = Anecdotario_anecdota;
        this.Anecdotario_estado = Anecdotario_estado;
    }

    public ClsEntidadAnecdotario() {
    }
       
    
    public String getAnecdotario_id() {
        return Anecdotario_id;
    }

    public void setAnecdotario_id(String Anecdotario_id) {
        this.Anecdotario_id = Anecdotario_id;
    }

    public String getAnecdotario_anecdota() {
        return Anecdotario_anecdota;
    }

    public void setAnecdotario_anecdota(String Anecdotario_anecdota) {
        this.Anecdotario_anecdota = Anecdotario_anecdota;
    }

    public String getAnecdotario_solucion() {
        return Anecdotario_solucion;
    }

    public void setAnecdotario_solucion(String Anecdotario_solucion) {
        this.Anecdotario_solucion = Anecdotario_solucion;
    }

    public String getAnecdotario_estado() {
        return Anecdotario_estado;
    }

    public void setAnecdotario_estado(String Anecdotario_estado) {
        this.Anecdotario_estado = Anecdotario_estado;
    }

    public String getAnecdotario_fecha() {
        return Anecdotario_fecha;
    }

    public void setAnecdotario_fecha(String Anecdotario_fecha) {
        this.Anecdotario_fecha = Anecdotario_fecha;
    }

    public String getId_Alumno() {
        return Id_Alumno;
    }

    public void setId_Alumno(String Id_Alumno) {
        this.Id_Alumno = Id_Alumno;
    }
    
    
}
