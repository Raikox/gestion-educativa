/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mpsEntity;

import java.text.ParseException;
import static prymatricula.ClsMisc.formatoFechaEUA;
import static prymatricula.ClsMisc.formatoFechaPalabra;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email jkevin.mv@gmail.com
 */
public class NoAdeudoEntity {

    String id_matricula;
    String num_boleta;
    String desc_detalle;
    String fecha_boleta;
    String tipo_detalle;    

    public NoAdeudoEntity(String id_matricula, String num_boleta, String desc_detalle, String fecha_boleta, String tipo_detalle) {
        this.id_matricula = id_matricula;
        this.num_boleta = num_boleta;
        this.desc_detalle = desc_detalle;
        this.fecha_boleta = fecha_boleta;
        this.tipo_detalle = tipo_detalle;
    }
    
    public String getId_matricula() {
        return id_matricula;
    }

    public void setId_matricula(String id_matricula) {
        this.id_matricula = id_matricula;
    }

    public String getNum_boleta() {
        return num_boleta;
    }

    public void setNum_boleta(String num_boleta) {
        this.num_boleta = num_boleta;
    }

    public String getDesc_detalle() {
        return desc_detalle;
    }

    public void setDesc_detalle(String desc_detalle) {
        this.desc_detalle = desc_detalle;
    }

    public String getFecha_boleta() throws ParseException {
        return ObtenerFechaEscrita(fecha_boleta);
    }

    public void setFecha_boleta(String fecha_boleta) throws ParseException {
        this.fecha_boleta = ObtenerFechaEscrita(fecha_boleta);
    }

    public String getTipo_detalle() {
        return tipo_detalle;
    }

    public void setTipo_detalle(String tipo_detalle) {
        this.tipo_detalle = tipo_detalle;
    }

    public String ObtenerFechaEscrita(String fecha) throws ParseException {
        return formatoFechaPalabra(fecha);
    }   
    
    
}
