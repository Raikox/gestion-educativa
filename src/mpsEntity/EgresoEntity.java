/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mpsEntity;

import java.util.Date;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email jkevin.mv@gmail.com
 */
public class EgresoEntity {

    private int id_egreso;
    private int id_persona;
    private String concepto_egreso;
    private String desc_egreso;
    private Double monto_egreso;
    private Date fecha_egreso;
    //--
    private String personal_nombre;
    private String personal_apellido_paterno;
    private String personal_apellido_materno;

    public EgresoEntity(int id_egreso, int id_persona, String concepto_egreso, String desc_egreso, Double monto_egreso, Date fecha_egreso, String personal_nombre, String personal_apellido_paterno, String personal_apellido_materno) {
        this.id_egreso = id_egreso;
        this.id_persona = id_persona;
        this.concepto_egreso = concepto_egreso;
        this.desc_egreso = desc_egreso;
        this.monto_egreso = monto_egreso;
        this.fecha_egreso = fecha_egreso;
        this.personal_nombre = personal_nombre;
        this.personal_apellido_paterno = personal_apellido_paterno;
        this.personal_apellido_materno = personal_apellido_materno;
    }    
    
    public int getId_egreso() {
        return id_egreso;
    }

    public void setId_egreso(int id_egreso) {
        this.id_egreso = id_egreso;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public String getConcepto_egreso() {
        return concepto_egreso;
    }

    public void setConcepto_egreso(String concepto_egreso) {
        this.concepto_egreso = concepto_egreso;
    }

    public String getDesc_egreso() {
        return desc_egreso;
    }

    public void setDesc_egreso(String desc_egreso) {
        this.desc_egreso = desc_egreso;
    }

    public Double getMonto_egreso() {
        return monto_egreso;
    }

    public void setMonto_egreso(Double monto_egreso) {
        this.monto_egreso = monto_egreso;
    }

    public Date getFecha_egreso() {
        return fecha_egreso;
    }

    public void setFecha_egreso(Date fecha_egreso) {
        this.fecha_egreso = fecha_egreso;
    }

    public String getPersonal_nombre() {
        return personal_nombre;
    }

    public void setPersonal_nombre(String personal_nombre) {
        this.personal_nombre = personal_nombre;
    }

    public String getPersonal_apellido_paterno() {
        return personal_apellido_paterno;
    }

    public void setPersonal_apellido_paterno(String personal_apellido_paterno) {
        this.personal_apellido_paterno = personal_apellido_paterno;
    }

    public String getPersonal_apellido_materno() {
        return personal_apellido_materno;
    }

    public void setPersonal_apellido_materno(String personal_apellido_materno) {
        this.personal_apellido_materno = personal_apellido_materno;
    }
    
    public String getNombresApellidosPersona() {
        
        return this.getPersonal_nombre()+" "+ this.getPersonal_apellido_paterno()+" "+ this.getPersonal_apellido_materno();
    } 
    
    
}
