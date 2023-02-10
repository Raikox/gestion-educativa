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
public class ItemEntity {

    private int item_id;
    private String item_nombre;
    private String item_medida;
    private int item_stock;
    private String item_descripcion;
    private int almacen_id;
    private String almacen_nombre;

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItem_nombre() {
        return item_nombre;
    }

    public void setItem_nombre(String item_nombre) {
        this.item_nombre = item_nombre;
    }

    public String getItem_medida() {
        return item_medida;
    }

    public void setItem_medida(String item_medida) {
        this.item_medida = item_medida;
    }

    public int getItem_stock() {
        return item_stock;
    }

    public void setItem_stock(int item_stock) {
        this.item_stock = item_stock;
    }

    public String getItem_descripcion() {
        return item_descripcion;
    }

    public void setItem_descripcion(String item_descripcion) {
        this.item_descripcion = item_descripcion;
    }

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
    
    
}
