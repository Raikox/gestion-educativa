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
public class ClsEntidadComidaAjuste {
    
    private String comida_ajuste_id;
    private String comida_ajuste_fecha;
    private String id_comida;

    public ClsEntidadComidaAjuste(String comida_ajuste_id, String comida_ajuste_fecha, String id_comida) {
        this.comida_ajuste_id = comida_ajuste_id;
        this.comida_ajuste_fecha = comida_ajuste_fecha;
        this.id_comida = id_comida;
    }
    
    public String getComida_ajuste_id() {
        return comida_ajuste_id;
    }

    public void setComida_ajuste_id(String comida_ajuste_id) {
        this.comida_ajuste_id = comida_ajuste_id;
    }

    public String getComida_ajuste_fecha() {
        return comida_ajuste_fecha;
    }

    public void setComida_ajuste_fecha(String comida_ajuste_fecha) {
        this.comida_ajuste_fecha = comida_ajuste_fecha;
    }

    public String getId_comida() {
        return id_comida;
    }

    public void setId_comida(String id_comida) {
        this.id_comida = id_comida;
    }
    
    
    
}
