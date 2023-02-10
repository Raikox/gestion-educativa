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
public class ClsEntidadEgresos {
//    id_egreso		int(11) not null primary key auto_increment,
//id_persona		int(11) not null,
//concepto_egreso	varchar(100) not null,
//desc_egreso		varchar(150)	not null,
//monto_egreso	double(5,2) not null,
//fecha_egreso	date,
    
    private String Id_Egreso;
    private String Id_Persona;
    private String Nombre_Persona;
    private String Concepto_Egreso;
    private String Descripcion_Egreso;
    public String Monto_Egreso;
    private String Fecha_Egreso;

    public String getId_Egreso() {
        return Id_Egreso;
    }

    public void setId_Egreso(String Id_Egreso) {
        this.Id_Egreso = Id_Egreso;
    }

    public String getId_Persona() {
        return Id_Persona;
    }

    public void setId_Persona(String Id_Persona) {
        this.Id_Persona = Id_Persona;
    }

    public String getNombre_Persona() {
        return Nombre_Persona;
    }

    public void setNombre_Persona(String Nombre_Persona) {
        this.Nombre_Persona = Nombre_Persona;
    }

    public String getConcepto_Egreso() {
        return Concepto_Egreso;
    }

    public void setConcepto_Egreso(String Concepto_Egreso) {
        this.Concepto_Egreso = Concepto_Egreso;
    }

    public String getDescripcion_Egreso() {
        return Descripcion_Egreso;
    }

    public void setDescripcion_Egreso(String Descripcion_Egreso) {
        this.Descripcion_Egreso = Descripcion_Egreso;
    }

    public String getMonto_Egreso() {
        return Monto_Egreso;
    }

    public void setMonto_Egreso(String Monto_Egreso) {
        this.Monto_Egreso = Monto_Egreso;
    }

    public String getFecha_Egreso() {
        return Fecha_Egreso;
    }

    public void setFecha_Egreso(String Fecha_Egreso) {
        this.Fecha_Egreso = Fecha_Egreso;
    }
    
    
}
