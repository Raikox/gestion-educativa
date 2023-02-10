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
public class SeguridadPermisosHerramientaEntity {

    String rol_id;
    String codigo_boleta_ajuste;
    String lonchera_mes_ajuste;
    String lonchera_servicio_ajuste;

    public SeguridadPermisosHerramientaEntity(String codigo_boleta_ajuste, String lonchera_mes_ajuste, String lonchera_servicio_ajuste) {
        this.codigo_boleta_ajuste = codigo_boleta_ajuste;
        this.lonchera_mes_ajuste = lonchera_mes_ajuste;
        this.lonchera_servicio_ajuste = lonchera_servicio_ajuste;
    }
    
    public String getRol_id() {
        return rol_id;
    }

    public void setRol_id(String rol_id) {
        this.rol_id = rol_id;
    }

    public String getCodigo_boleta_ajuste() {
        return codigo_boleta_ajuste;
    }

    public void setCodigo_boleta_ajuste(String codigo_boleta_ajuste) {
        this.codigo_boleta_ajuste = codigo_boleta_ajuste;
    }

    public String getLonchera_mes_ajuste() {
        return lonchera_mes_ajuste;
    }

    public void setLonchera_mes_ajuste(String lonchera_mes_ajuste) {
        this.lonchera_mes_ajuste = lonchera_mes_ajuste;
    }

    public String getLonchera_servicio_ajuste() {
        return lonchera_servicio_ajuste;
    }

    public void setLonchera_servicio_ajuste(String lonchera_servicio_ajuste) {
        this.lonchera_servicio_ajuste = lonchera_servicio_ajuste;
    }
    
    
}
