/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mpsEntity;

import java.util.Date;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email jkevin.mv@gmail.com
 */
public class BoletaEntity {

    int id_boleta;
    String num_boleta;
    Date fecha_boleta;
    Double total_boleta;
    String tipo_pago;
    String numero_operacion;
    //--
    int id_matricula;
    String nombre_alumno;
    String apellidop_alumno;
    String apellidom_alumno;
    String nombre_seccion;
    //--
    String desc_detalle;
    Double importe_detalle;
    String tipo_detalle;

    public BoletaEntity(int id_boleta, String num_boleta, String tipo_pago, String nombre_alumno, String apellidop_alumno, String apellidom_alumno, String nombre_seccion, String desc_detalle, Double importe_detalle) {
        this.id_boleta = id_boleta;
        this.num_boleta = num_boleta;
        this.tipo_pago = tipo_pago;
        this.nombre_alumno = nombre_alumno;
        this.apellidop_alumno = apellidop_alumno;
        this.apellidom_alumno = apellidom_alumno;
        this.nombre_seccion = nombre_seccion;
        this.desc_detalle = desc_detalle;
        this.importe_detalle = importe_detalle;        
    }

    public BoletaEntity(int id_boleta, String num_boleta, String tipo_pago, String numero_operacion, String nombre_alumno, String apellidop_alumno, String apellidom_alumno, String nombre_seccion, String desc_detalle, Double importe_detalle) {
        this.id_boleta = id_boleta;
        this.num_boleta = num_boleta;
        this.tipo_pago = tipo_pago;
        this.numero_operacion = numero_operacion;
        this.nombre_alumno = nombre_alumno;
        this.apellidop_alumno = apellidop_alumno;
        this.apellidom_alumno = apellidom_alumno;
        this.nombre_seccion = nombre_seccion;
        this.desc_detalle = desc_detalle;
        this.importe_detalle = importe_detalle;
    }
    
    
    
    public int getId_boleta() {
        return id_boleta;
    }

    public void setId_boleta(int id_boleta) {
        this.id_boleta = id_boleta;
    }

    public String getNum_boleta() {
        return num_boleta;
    }

    public void setNum_boleta(String num_boleta) {
        this.num_boleta = num_boleta;
    }

    public Date getFecha_boleta() {
        return fecha_boleta;
    }

    public void setFecha_boleta(Date fecha_boleta) {
        this.fecha_boleta = fecha_boleta;
    }

    public Double getTotal_boleta() {
        return total_boleta;
    }

    public void setTotal_boleta(Double total_boleta) {
        this.total_boleta = total_boleta;
    }

    public String getTipo_pago() {
        return tipo_pago;
    }

    public void setTipo_pago(String tipo_pago) {
        this.tipo_pago = tipo_pago;
    }

    public String getNumero_operacion() {
        return numero_operacion;
    }

    public void setNumero_operacion(String numero_operacion) {
        this.numero_operacion = numero_operacion;
    }

    public int getId_matricula() {
        return id_matricula;
    }

    public void setId_matricula(int id_matricula) {
        this.id_matricula = id_matricula;
    }

    public String getNombre_alumno() {
        return nombre_alumno;
    }

    public void setNombre_alumno(String nombre_alumno) {
        this.nombre_alumno = nombre_alumno;
    }

    public String getApellidop_alumno() {
        return apellidop_alumno;
    }

    public void setApellidop_alumno(String apellidop_alumno) {
        this.apellidop_alumno = apellidop_alumno;
    }

    public String getApellidom_alumno() {
        return apellidom_alumno;
    }

    public void setApellidom_alumno(String apellidom_alumno) {
        this.apellidom_alumno = apellidom_alumno;
    }

    public String getNombre_seccion() {
        return nombre_seccion;
    }

    public void setNombre_seccion(String nombre_seccion) {
        this.nombre_seccion = nombre_seccion;
    }

    public String getDesc_detalle() {
        return desc_detalle;
    }

    public void setDesc_detalle(String desc_detalle) {
        this.desc_detalle = desc_detalle;
    }

    public Double getImporte_detalle() {
        return importe_detalle;
    }

    public void setImporte_detalle(Double importe_detalle) {
        this.importe_detalle = importe_detalle;
    }

    public String getTipo_detalle() {
        return tipo_detalle;
    }

    public void setTipo_detalle(String tipo_detalle) {
        this.tipo_detalle = tipo_detalle;
    }

    public String getNombresApellidosAlumno() {
        
        return this.getNombre_alumno() +" "+ this.getApellidop_alumno() +" "+ this.getApellidom_alumno();
    }
    
}
