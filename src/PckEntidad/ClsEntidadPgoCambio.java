/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckEntidad;

/**
 *
 * @author kmv
 */
public class ClsEntidadPgoCambio {
    private String Id_Cambio;
    private String Fecha_Cambio;
    private String Monto_Cambio;
    private String Obs_Cambio;    
    private String Tipo_Cambio;
    private String Id_Alumno;
    private String ApeNombre_Alumno;

    public String getApeNombre_Alumno() {
        return ApeNombre_Alumno;
    }

    public void setApeNombre_Alumno(String ApeNombre_Alumno) {
        this.ApeNombre_Alumno = ApeNombre_Alumno;
    }    
        
    public String getMonto_Cambio() {
        return Monto_Cambio;
    }

    public void setMonto_Cambio(String Monto_Cambio) {
        this.Monto_Cambio = Monto_Cambio;
    }    
    
    public String getId_Cambio() {
        return Id_Cambio;
    }

    public void setId_Cambio(String Id_Cambio) {
        this.Id_Cambio = Id_Cambio;
    }

    public String getFecha_Cambio() {
        return Fecha_Cambio;
    }

    public void setFecha_Cambio(String Fecha_Cambio) {
        this.Fecha_Cambio = Fecha_Cambio;
    }

    public String getObs_Cambio() {
        return Obs_Cambio;
    }

    public void setObs_Cambio(String Obs_Cambio) {
        this.Obs_Cambio = Obs_Cambio;
    }

    public String getTipo_Cambio() {
        return Tipo_Cambio;
    }

    public void setTipo_Cambio(String Tipo_Cambio) {
        this.Tipo_Cambio = Tipo_Cambio;
    }

    public String getId_Alumno() {
        return Id_Alumno;
    }

    public void setId_Alumno(String Id_Alumno) {
        this.Id_Alumno = Id_Alumno;
    }
    
    
}
