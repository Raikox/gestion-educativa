/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PckNegocio;

/**
 *
 * @author Kevin
 */
public class clsEntidadAsistenciaAlmunoReporteBean {
    private String fecha;
    private String asiste;
    private String a;
    private String t;
    private String f;

    public clsEntidadAsistenciaAlmunoReporteBean(String fecha, String asiste, String a, String t, String f) {
        this.fecha = fecha;
        this.asiste = asiste;
        this.a = a;
        this.t = t;
        this.f = f;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getAsiste() {
        return asiste;
    }

    public void setAsiste(String asiste) {
        this.asiste = asiste;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    

   
    
   
    
}
