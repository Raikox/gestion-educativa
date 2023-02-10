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
public class PsicologiaAccionEntity {

    private int psa_id;
    private String psa_accion;
    private int psc_id;
    private int personal_id;
    private String personal_nombre;
    private String psa_fecha;

    public int getPsa_id() {
        return psa_id;
    }

    public void setPsa_id(int psa_id) {
        this.psa_id = psa_id;
    }

    public String getPsa_accion() {
        return psa_accion;
    }

    public void setPsa_accion(String psa_accion) {
        this.psa_accion = psa_accion;
    }

    public int getPsc_id() {
        return psc_id;
    }

    public void setPsc_id(int psc_id) {
        this.psc_id = psc_id;
    }

    public int getPersonal_id() {
        return personal_id;
    }

    public void setPersonal_id(int personal_id) {
        this.personal_id = personal_id;
    }

    public String getPersonal_nombre() {
        return personal_nombre;
    }

    public void setPersonal_nombre(String personal_nombre) {
        this.personal_nombre = personal_nombre;
    }

    public String getPsa_fecha() {
        return psa_fecha;
    }

    public void setPsa_fecha(String psa_fecha) {
        this.psa_fecha = psa_fecha;
    }

    
    
    
    
}
