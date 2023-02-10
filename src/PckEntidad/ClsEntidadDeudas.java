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
public class ClsEntidadDeudas {
    public String Concepto;
    public String Unitario;
    public String Importe;
    public String Vencimiento;
    public String Pendiente;
    public String Mes;
    public String Tipo;
    public String Id_Deuda;
  
    public String getConcepto() {
        return Concepto;
    }

    public String getPendiente() {
        return Pendiente;
    }

    public void setPendiente(String Pendiente) {
        this.Pendiente = Pendiente;
    }
    
    public void setConcepto(String Concepto) {
        this.Concepto = Concepto;
    }

    public String getUnitario() {
        return Unitario;
    }

    public void setUnitario(String Unitario) {
        this.Unitario = Unitario;
    }

    public String getImporte() {
        return Importe;
    }

    public void setImporte(String Importe) {
        this.Importe = Importe;
    }
  
    public String getVencimiento() {
        return Vencimiento;
    }

    public void setVencimiento(String Vencimiento) {
        this.Vencimiento = Vencimiento;
    }

    public String getMes() {
        return Mes;
    }

    public void setMes(String Mes) {
        this.Mes = Mes;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getId_Deuda() {
        return Id_Deuda;
    }

    public void setId_Deuda(String Id_Deuda) {
        this.Id_Deuda = Id_Deuda;
    }
        
    
}
