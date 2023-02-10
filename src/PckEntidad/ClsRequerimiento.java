/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PckEntidad;

/**
 *
 * @author Kevin
 */
public class ClsRequerimiento {
    
    private String id;
    private String cantidad;
    private String item;
    private String idItem;
    private String sustento;
    private String unidad;
    private String descripcion;

    public ClsRequerimiento(String cantidad, String item, String idItem,String sustento,String unidad,String descripcion) {
        this.cantidad = cantidad;
        this.item = item;
        this.sustento = sustento;
        this.idItem = idItem;
        this.unidad = unidad;
        this.descripcion = descripcion;
    }

    public ClsRequerimiento(String id, String cantidad, String item, String idItem, String sustento) {
        this.id = id;
        this.cantidad = cantidad;
        this.item = item;
        this.idItem = idItem;
        this.sustento = sustento;        
    }
    
    public ClsRequerimiento(String id, String cantidad, String item, String idItem, String sustento, String unidad,String descripcion) {
        this.id = id;
        this.cantidad = cantidad;
        this.item = item;
        this.idItem = idItem;
        this.sustento = sustento;
        this.unidad = unidad;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
    
    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }    
    
    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getSustento() {
        return sustento;
    }

    public void setSustento(String sustento) {
        this.sustento = sustento;
    }
    
    
    
}
