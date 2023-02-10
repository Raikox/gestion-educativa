/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckEntidad;

/**
 *
 * @author PC01
 */
public class ClsEntidadBoleta {
    private String Id_Boleta;
    private String Numero_Boleta;
    private String Nombre_Boleta;
    private String Fecha_Boleta;   
    private String Dni_Boleta;
    private Double Total_Boleta;
    private String Id_Matricula;

    public String getId_Matricula() {
        return Id_Matricula;
    }

    public String getNombre_Boleta() {
        return Nombre_Boleta;
    }

    public void setNombre_Boleta(String Nombre_Boleta) {
        this.Nombre_Boleta = Nombre_Boleta;
    }
    
    public void setId_Matricula(String Id_Matricula) {
        this.Id_Matricula = Id_Matricula;
    }
    
    public String getId_Boleta() {
        return Id_Boleta;
    }

    public void setId_Boleta(String Id_Boleta) {
        this.Id_Boleta = Id_Boleta;
    }

    public String getNumero_Boleta() {
        return Numero_Boleta;
    }

    public void setNumero_Boleta(String Numero_Boleta) {
        this.Numero_Boleta = Numero_Boleta;
    }

    
    public String getFecha_Boleta() {
        return Fecha_Boleta;
    }

    public void setFecha_Boleta(String Fecha_Boleta) {
        this.Fecha_Boleta = Fecha_Boleta;
    }

    public String getDni_Boleta() {
        return Dni_Boleta;
    }

    public void setDni_Boleta(String Dni_Boleta) {
        this.Dni_Boleta = Dni_Boleta;
    }

    public Double getTotal_Boleta() {
        return Total_Boleta;
    }

    public void setTotal_Boleta(Double Total_Boleta) {
        this.Total_Boleta = Total_Boleta;
    }
    
   
    
}
