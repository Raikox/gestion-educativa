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
public class SeguridadPermisosProductoServicioEntity {

    String rol_id;
    String producto_mantenimiento;
    String producto_asignar;
    String servicio_mantenimiento;
    String servicio_asignar;

    public SeguridadPermisosProductoServicioEntity(String producto_mantenimiento, String producto_asignar, String servicio_mantenimiento, String servicio_asignar) {
        this.producto_mantenimiento = producto_mantenimiento;
        this.producto_asignar = producto_asignar;
        this.servicio_mantenimiento = servicio_mantenimiento;
        this.servicio_asignar = servicio_asignar;
    }
    
    public String getRol_id() {
        return rol_id;
    }

    public void setRol_id(String rol_id) {
        this.rol_id = rol_id;
    }

    public String getProducto_mantenimiento() {
        return producto_mantenimiento;
    }

    public void setProducto_mantenimiento(String producto_mantenimiento) {
        this.producto_mantenimiento = producto_mantenimiento;
    }

    public String getProducto_asignar() {
        return producto_asignar;
    }

    public void setProducto_asignar(String producto_asignar) {
        this.producto_asignar = producto_asignar;
    }

    public String getServicio_mantenimiento() {
        return servicio_mantenimiento;
    }

    public void setServicio_mantenimiento(String servicio_mantenimiento) {
        this.servicio_mantenimiento = servicio_mantenimiento;
    }

    public String getServicio_asignar() {
        return servicio_asignar;
    }

    public void setServicio_asignar(String servicio_asignar) {
        this.servicio_asignar = servicio_asignar;
    }
    
    
    
}
