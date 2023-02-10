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
public class ClsEntidadComida {
    private String Id_Comida;
    private String Mes_Comida;
    private Double Monto_Comida;
    private Double Total_Comida;
    private Double Pendiente_Comida;
    private String Vencimiento_Comida;
    private String Id_Matricula;

    public String getVencimiento_Comida() {
        return Vencimiento_Comida;
    }

    public void setVencimiento_Comida(String Vencimiento_Comida) {
        this.Vencimiento_Comida = Vencimiento_Comida;
    }

    public String getId_Matricula() {
        return Id_Matricula;
    }

    public void setId_Matricula(String Id_Matricula) {
        this.Id_Matricula = Id_Matricula;
    }
     
    public String getId_Comida() {
        return Id_Comida;
    }

    public void setId_Comida(String Id_Comida) {
        this.Id_Comida = Id_Comida;
    }

    public String getMes_Comida() {
        return Mes_Comida;
    }

    public void setMes_Comida(String Mes_Comida) {
        this.Mes_Comida = Mes_Comida;
    }
    
    public Double getMonto_Comida() {
        return Monto_Comida;
    }

    public void setMonto_Comida(Double Monto_Comida) {
        this.Monto_Comida = Monto_Comida;
    }

    public Double getTotal_Comida() {
        return Total_Comida;
    }

    public void setTotal_Comida(Double Total_Comida) {
        this.Total_Comida = Total_Comida;
    }

    public Double getPendiente_Comida() {
        return Pendiente_Comida;
    }

    public void setPendiente_Comida(Double Pendiente_Comida) {
        this.Pendiente_Comida = Pendiente_Comida;
    }
    
    
            
}
