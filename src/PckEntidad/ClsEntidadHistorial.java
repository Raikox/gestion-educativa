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
public class ClsEntidadHistorial {
    
    private String Id_Historial;
    private Double Monto_Historial;
    private String Observacion_Historial;
    private String Fecha_Boleta;
    private String Tipo_Historial;
    private String Apellidos_Nombres_Alumno;
    private String Id_Alumno;
    private String Id_Boleta;
    private String Codigo_Boleta;

    public String getCodigo_Boleta() {
        return Codigo_Boleta;
    }

    public void setCodigo_Boleta(String Codigo_Boleta) {
        this.Codigo_Boleta = Codigo_Boleta;
    }
        
    public String getId_Boleta() {
        return Id_Boleta;
    }

    public void setId_Boleta(String Id_Boleta) {
        this.Id_Boleta = Id_Boleta;
    }
        
    public String getId_Historial() {
        return Id_Historial;
    }

    public void setId_Historial(String Id_Historial) {
        this.Id_Historial = Id_Historial;
    }

    public Double getMonto_Historial() {
        return Monto_Historial;
    }

    public void setMonto_Historial(Double Monto_Historial) {
        this.Monto_Historial = Monto_Historial;
    }

    public String getObservacion_Historial() {
        return Observacion_Historial;
    }

    public void setObservacion_Historial(String Observacion_Historial) {
        this.Observacion_Historial = Observacion_Historial;
    }

    public String getFecha_Boleta() {
        return Fecha_Boleta;
    }

    public void setFecha_Boleta(String Fecha_Boleta) {
        this.Fecha_Boleta = Fecha_Boleta;
    }

   
    public String getTipo_Historial() {
        return Tipo_Historial;
    }

    public void setTipo_Historial(String Tipo_Historial) {
        this.Tipo_Historial = Tipo_Historial;
    }

    public String getApellidos_Nombres_Alumno() {
        return Apellidos_Nombres_Alumno;
    }

    public void setApellidos_Nombres_Alumno(String Apellidos_Nombres_Alumno) {
        this.Apellidos_Nombres_Alumno = Apellidos_Nombres_Alumno;
    }
  
    public String getId_Alumno() {
        return Id_Alumno;
    }

    public void setId_Alumno(String Id_Alumno) {
        this.Id_Alumno = Id_Alumno;
    }
    
    
    
    
}
