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
public class ClsEntidadDetalleBoleta {
    
   
    private String Id_Detalle;
    private int Cantidad_Detalle;
    private String Descripcion_Detalle;
    private String Id_Deuda;
    private Double Importe_Detalle;
    private Double Unitario_Detalle;
    private String Tipo_Detalle;
    private String Id_Boleta;
    

    public String getId_Deuda() {
        return Id_Deuda;
    }

    public void setId_Deuda(String Id_Deuda) {
        this.Id_Deuda = Id_Deuda;
    }
    
    public Double getUnitario_Detalle() {
        return Unitario_Detalle;
    }

    public void setUnitario_Detalle(Double Unitario_Detalle) {
        this.Unitario_Detalle = Unitario_Detalle;
    }
    
    public String getTipo_Detalle() {
        return Tipo_Detalle;
    }

    public void setTipo_Detalle(String Tipo_Detalle) {
        this.Tipo_Detalle = Tipo_Detalle;
    }
        
    public String getId_Detalle() {
        return Id_Detalle;
    }

    public void setId_Detalle(String Id_Detalle) {
        this.Id_Detalle = Id_Detalle;
    }

    public int getCantidad_Detalle() {
        return Cantidad_Detalle;
    }

    public void setCantidad_Detalle(int Cantidad_Detalle) {
        this.Cantidad_Detalle = Cantidad_Detalle;
    }
    
    public String getDescripcion_Detalle() {
        return Descripcion_Detalle;
    }

    public void setDescripcion_Detalle(String Descripcion_Detalle) {
        this.Descripcion_Detalle = Descripcion_Detalle;
    }
  
    public Double getImporte_Detalle() {
        return Importe_Detalle;
    }

    public void setImporte_Detalle(Double Importe_Detalle) {
        this.Importe_Detalle = Importe_Detalle;
    }

    public String getId_Boleta() {
        return Id_Boleta;
    }

    public void setId_Boleta(String Id_Boleta) {
        this.Id_Boleta = Id_Boleta;
    }
    
    
    
}
