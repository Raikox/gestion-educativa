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
public class PsicologiaEntity {
    
    private int psc_id;
    private String id_matricula;
    private String psc_problema;
    private String psc_frecuencia;
    private String psc_circunstancia;
    private String psc_acciones;
    private String psc_fecha;
    private String psc_estado;
    private String psc_alumno;
    private String psc_aula;
    private int id_seccion;

    public String getId_matricula() {
        return id_matricula;
    }

    public void setId_matricula(String id_matricula) {
        this.id_matricula = id_matricula;
    }
    
    public String getPsc_estado() {
        return psc_estado;
    }

    public void setPsc_estado(String psc_estado) {
        this.psc_estado = psc_estado;
    }
    
    public int getId_seccion() {
        return id_seccion;
    }

    public void setId_seccion(int id_seccion) {
        this.id_seccion = id_seccion;
    }

    public String getPsc_alumno() {
        return psc_alumno;
    }

    public void setPsc_alumno(String psc_alumno) {
        this.psc_alumno = psc_alumno;
    }

    public String getPsc_aula() {
        return psc_aula;
    }

    public void setPsc_aula(String psc_aula) {
        this.psc_aula = psc_aula;
    }    
    
    public int getPsc_id() {
        return psc_id;
    }

    public void setPsc_id(int psc_id) {
        this.psc_id = psc_id;
    }

    public String getPsc_problema() {
        return psc_problema;
    }

    public void setPsc_problema(String psc_problema) {
        this.psc_problema = psc_problema;
    }

    public String getPsc_frecuencia() {
        return psc_frecuencia;
    }

    public void setPsc_frecuencia(String psc_frecuencia) {
        this.psc_frecuencia = psc_frecuencia;
    }

    public String getPsc_circunstancia() {
        return psc_circunstancia;
    }

    public void setPsc_circunstancia(String psc_circunstancia) {
        this.psc_circunstancia = psc_circunstancia;
    }

    public String getPsc_acciones() {
        return psc_acciones;
    }

    public void setPsc_acciones(String psc_acciones) {
        this.psc_acciones = psc_acciones;
    }

    public String getPsc_fecha() {
        return psc_fecha;
    }

    public void setPsc_fecha(String psc_fecha) {
        this.psc_fecha = psc_fecha;
    }

    
    
    
}
