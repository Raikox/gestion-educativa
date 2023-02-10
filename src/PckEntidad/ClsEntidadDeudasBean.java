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
public class ClsEntidadDeudasBean {
    public String concepto;
    public String total;
    public String pendiente;
    public String vencimiento;
    public String mes;
    public String totalpendiente;
    
    public ClsEntidadDeudasBean
    (String concepto, String total, String pendiente, String vencimiento, String mes, String totalpendiente)
    {
       this.concepto=concepto;
       this.total=total;
       this.pendiente=pendiente;
       this.vencimiento=vencimiento;
       this.mes=mes;
       this.totalpendiente=totalpendiente;
    }
    
    public String getconcepto() {
        return concepto;
    }

    public void setconcepto(String concepto) {
        this.concepto = concepto;
    }

    public String gettotal() {
        return total;
    }

    public void settotal(String total) {
        this.total = total;
    }

    public String getpendiente() {
        return pendiente;
    }

    public void setpendiente(String pendiente) {
        this.pendiente = pendiente;
    }

    public String getvencimiento() {
        return vencimiento;
    }

    public void setvencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getTotalpendiente() {
        return totalpendiente;
    }

    public void setTotalpendiente(String totalpendiente) {
        this.totalpendiente = totalpendiente;
    }
    
}
