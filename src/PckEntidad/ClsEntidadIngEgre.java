/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckEntidad;

/**
 *
 * @author kmv
 */
public class ClsEntidadIngEgre {
    private String Niño;
    private String Concepto;
    private String Seccion;
    private String Recibo;
    public String Monto;
    public String Tipo;
    private String Id_Boleta;
    private String Tipo_pago;

    public String getTipo_pago() {
        return Tipo_pago;
    }

    public void setTipo_pago(String Tipo_pago) {
        this.Tipo_pago = Tipo_pago;
    }

    
    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }
    
    public String getId_Boleta() {
        return Id_Boleta;
    }

    public void setId_Boleta(String Id_Boleta) {
        this.Id_Boleta = Id_Boleta;
    }
    
    public String getNiño() {
        return Niño;
    }

    public void setNiño(String Niño) {
        this.Niño = Niño;
    }

    public String getConcepto() {
        return Concepto;
    }

    public void setConcepto(String Concepto) {
        this.Concepto = Concepto;
    }

    public String getSeccion() {
        return Seccion;
    }

    public void setSeccion(String Seccion) {
        this.Seccion = Seccion;
    }
    
    public String getRecibo() {
        return Recibo;
    }

    public void setRecibo(String Recibo) {
        this.Recibo = Recibo;
    }

    public String getMonto() {
        return Monto;
    }

    public void setMonto(String Monto) {
        this.Monto = Monto;
    }
    
    
}
