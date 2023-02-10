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
public class SeguridadRolEntity {

    String rol_id;
    String rol_nombre;
    String rol_descripcion;

    public SeguridadRolEntity(String rol_id, String rol_nombre, String rol_descripcion) {
        this.rol_id = rol_id;
        this.rol_nombre = rol_nombre;
        this.rol_descripcion = rol_descripcion;
    }
    
    public String getRol_id() {
        return rol_id;
    }

    public void setRol_id(String rol_id) {
        this.rol_id = rol_id;
    }

    public String getRol_nombre() {
        return rol_nombre;
    }

    public void setRol_nombre(String rol_nombre) {
        this.rol_nombre = rol_nombre;
    }

    public String getRol_descripcion() {
        return rol_descripcion;
    }

    public void setRol_descripcion(String rol_descripcion) {
        this.rol_descripcion = rol_descripcion;
    }
    
    
    
}
