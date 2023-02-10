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
public class ClsEntidadPagoHistorial {
    
    private String id_ha;
    private String concepto_ha;
    private String importe_ha;
    private String fecha_ha;
    private String personal_id;

    public ClsEntidadPagoHistorial(String concepto_ha, String importe_ha, String fecha_ha, String personal_id) {
        this.concepto_ha = concepto_ha;
        this.importe_ha = importe_ha;
        this.fecha_ha = fecha_ha;
        this.personal_id = personal_id;
    }

    public String getId_ha() {
        return id_ha;
    }

    public void setId_ha(String id_ha) {
        this.id_ha = id_ha;
    }

    public String getConcepto_ha() {
        return concepto_ha;
    }

    public void setConcepto_ha(String concepto_ha) {
        this.concepto_ha = concepto_ha;
    }

    public String getImporte_ha() {
        return importe_ha;
    }

    public void setImporte_ha(String importe_ha) {
        this.importe_ha = importe_ha;
    }

    public String getFecha_ha() {
        return fecha_ha;
    }

    public void setFecha_ha(String fecha_ha) {
        this.fecha_ha = fecha_ha;
    }

    public String getPersonal_id() {
        return personal_id;
    }

    public void setPersonal_id(String personal_id) {
        this.personal_id = personal_id;
    }

   
    
    
}
