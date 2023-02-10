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
public class SeguridadPermisosContabilidadEntity {

    String rol_id;
    String pago;
    String pago_editar;
    String boleta_anular;
    String historial_autorizacion;
    String deuda_monto;
    String ingreso_egreso;
    String egreso;
    String egreso_editar;
    String boleta_imprimir;

    public SeguridadPermisosContabilidadEntity(String pago, String pago_editar, String boleta_anular, String historial_autorizacion, String deuda_monto, String ingreso_egreso, String egreso, String egreso_editar, String boleta_imprimir) {
        this.pago = pago;
        this.pago_editar = pago_editar;
        this.boleta_anular = boleta_anular;
        this.historial_autorizacion = historial_autorizacion;
        this.deuda_monto = deuda_monto;
        this.ingreso_egreso = ingreso_egreso;
        this.egreso = egreso;
        this.egreso_editar = egreso_editar;
        this.boleta_imprimir = boleta_imprimir;
    }
    
    public String getRol_id() {
        return rol_id;
    }

    public void setRol_id(String rol_id) {
        this.rol_id = rol_id;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public String getPago_editar() {
        return pago_editar;
    }

    public void setPago_editar(String pago_editar) {
        this.pago_editar = pago_editar;
    }

    public String getBoleta_anular() {
        return boleta_anular;
    }

    public void setBoleta_anular(String boleta_anular) {
        this.boleta_anular = boleta_anular;
    }

    public String getHistorial_autorizacion() {
        return historial_autorizacion;
    }

    public void setHistorial_autorizacion(String historial_autorizacion) {
        this.historial_autorizacion = historial_autorizacion;
    }

    public String getDeuda_monto() {
        return deuda_monto;
    }

    public void setDeuda_monto(String deuda_monto) {
        this.deuda_monto = deuda_monto;
    }

    public String getIngreso_egreso() {
        return ingreso_egreso;
    }

    public void setIngreso_egreso(String ingreso_egreso) {
        this.ingreso_egreso = ingreso_egreso;
    }

    public String getEgreso() {
        return egreso;
    }

    public void setEgreso(String egreso) {
        this.egreso = egreso;
    }

    public String getEgreso_editar() {
        return egreso_editar;
    }

    public void setEgreso_editar(String egreso_editar) {
        this.egreso_editar = egreso_editar;
    }

    public String getBoleta_imprimir() {
        return boleta_imprimir;
    }

    public void setBoleta_imprimir(String boleta_imprimir) {
        this.boleta_imprimir = boleta_imprimir;
    }
    
    
    
}
