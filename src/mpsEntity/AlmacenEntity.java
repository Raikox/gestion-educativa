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
public class AlmacenEntity {
    
    private int almacen_id;
    private String almacen_nombre;
    private String almacen_descripcion;
    private int id_seccion;
    private String nombre_seccion;
    private int id_periodo;

    public int getAlmacen_id() {
        return almacen_id;
    }

    public void setAlmacen_id(int almacen_id) {
        this.almacen_id = almacen_id;
    }

    public String getAlmacen_nombre() {
        return almacen_nombre;
    }

    public void setAlmacen_nombre(String almacen_nombre) {
        this.almacen_nombre = almacen_nombre;
    }

    public String getAlmacen_descripcion() {
        return almacen_descripcion;
    }

    public void setAlmacen_descripcion(String almacen_descripcion) {
        this.almacen_descripcion = almacen_descripcion;
    }

    public int getId_seccion() {
        return id_seccion;
    }

    public void setId_seccion(int id_seccion) {
        this.id_seccion = id_seccion;
    }

    public String getNombre_seccion() {
        return nombre_seccion;
    }

    public void setNombre_seccion(String nombre_seccion) {
        this.nombre_seccion = nombre_seccion;
    }

    public int getId_periodo() {
        return id_periodo;
    }

    public void setId_periodo(int id_periodo) {
        this.id_periodo = id_periodo;
    }
    
        
}
