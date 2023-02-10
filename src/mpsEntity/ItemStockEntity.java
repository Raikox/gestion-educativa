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
public class ItemStockEntity {

    private int item_stock_id;
    private String item_stock_fecha;
    private int item_stock_asignado;
    private int personal_id;
    private String personal_nombre;
    private String personal_apellido_paterno;
    private String personal_apellido_materno;

    public int getItem_stock_id() {
        return item_stock_id;
    }

    public void setItem_stock_id(int item_stock_id) {
        this.item_stock_id = item_stock_id;
    }

    public String getItem_stock_fecha() {
        return item_stock_fecha;
    }

    public void setItem_stock_fecha(String item_stock_fecha) {
        this.item_stock_fecha = item_stock_fecha;
    }

    public int getItem_stock_asignado() {
        return item_stock_asignado;
    }

    public void setItem_stock_asignado(int item_stock_asignado) {
        this.item_stock_asignado = item_stock_asignado;
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
    
    public String nombres()
    {
        return personal_apellido_paterno+" "+personal_apellido_materno+", "+personal_nombre;
    }
    
}
