/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckEntidad;

/**
 *
 * @author PC01
 */
public class ClsEntidadProducto {
private String Id_Producto;
private String Nombre_Producto;
private String Precio_Producto;
private String Stock_Producto;
private String Descripcion_Producto;
private String Anio_Producto;


    public String getId_Producto() {
        return Id_Producto;
    }

    public void setId_Producto(String Id_Producto) {
        this.Id_Producto = Id_Producto;
    }

    public String getAnio_Producto() {
        return Anio_Producto;
    }

    public void setAnio_Producto(String Anio_Producto) {
        this.Anio_Producto = Anio_Producto;
    }

    public String getNombre_Producto() {
        return Nombre_Producto;
    }

    public void setNombre_Producto(String Nombre_Producto) {
        this.Nombre_Producto = Nombre_Producto;
    }

    public String getPrecio_Producto() {
        return Precio_Producto;
    }

    public void setPrecio_Producto(String Precio_Producto) {
        this.Precio_Producto = Precio_Producto;
    }

    public String getStock_Producto() {
        return Stock_Producto;
    }

    public void setStock_Producto(String Stock_Producto) {
        this.Stock_Producto = Stock_Producto;
    }

    public String getDescripcion_Producto() {
        return Descripcion_Producto;
    }

    public void setDescripcion_Producto(String Descripcion_Producto) {
        this.Descripcion_Producto = Descripcion_Producto;
    }
    
//public String toString()
//{
//    String nombre;
//    nombre = this.getNombre_Producto();
//    return nombre;  
//}

}
