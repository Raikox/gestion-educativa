/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mpsEntity;

import javax.swing.JComboBox;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email jkevin.mv@gmail.com
 */
public class PeriodoEntity {
    private int id_periodo;
    private String nombre_periodo;
    private String anio_periodo;
    private String estado_periodo;
    private String inicio_periodo;
    private String fin_periodo;    
    private double matricula_periodo;
    private double hora_periodo;
    private double horb_periodo;
    private double horc_periodo;
    private double alimentacion_periodo;
    private double alimentacion_dia;
    private int asistencia_periodo;
    private int nivel_id;
    private String nivel_nombre;

    public PeriodoEntity() {
    }
    
    public PeriodoEntity(int id_periodo, String nombre_periodo, String estado_periodo, int nivel_id) {
        this.id_periodo = id_periodo;
        this.nombre_periodo = nombre_periodo;
        this.estado_periodo = estado_periodo;
        this.nivel_id = nivel_id;
    }
        
    public int getId_periodo() {
        return id_periodo;
    }

    public void setId_periodo(int id_periodo) {
        this.id_periodo = id_periodo;
    }

    public String getNombre_periodo() {
        return nombre_periodo;
    }

    public void setNombre_periodo(String nombre_periodo) {
        this.nombre_periodo = nombre_periodo;
    }

    public String getAnio_periodo() {
        return anio_periodo;
    }

    public void setAnio_periodo(String anio_periodo) {
        this.anio_periodo = anio_periodo;
    }

    public String getEstado_periodo() {
        return estado_periodo;
    }

    public void setEstado_periodo(String estado_periodo) {
        this.estado_periodo = estado_periodo;
    }

    public String getInicio_periodo() {
        return inicio_periodo;
    }

    public void setInicio_periodo(String inicio_periodo) {
        this.inicio_periodo = inicio_periodo;
    }

    public String getFin_periodo() {
        return fin_periodo;
    }

    public void setFin_periodo(String fin_periodo) {
        this.fin_periodo = fin_periodo;
    }

    public double getMatricula_periodo() {
        return matricula_periodo;
    }

    public void setMatricula_periodo(double matricula_periodo) {
        this.matricula_periodo = matricula_periodo;
    }

    public double getHora_periodo() {
        return hora_periodo;
    }

    public void setHora_periodo(double hora_periodo) {
        this.hora_periodo = hora_periodo;
    }

    public double getHorb_periodo() {
        return horb_periodo;
    }

    public void setHorb_periodo(double horb_periodo) {
        this.horb_periodo = horb_periodo;
    }

    public double getHorc_periodo() {
        return horc_periodo;
    }

    public void setHorc_periodo(double horc_periodo) {
        this.horc_periodo = horc_periodo;
    }

    public double getAlimentacion_periodo() {
        return alimentacion_periodo;
    }

    public void setAlimentacion_periodo(double alimentacion_periodo) {
        this.alimentacion_periodo = alimentacion_periodo;
    }

    public double getAlimentacion_dia() {
        return alimentacion_dia;
    }

    public void setAlimentacion_dia(double alimentacion_dia) {
        this.alimentacion_dia = alimentacion_dia;
    }

    public int getAsistencia_periodo() {
        return asistencia_periodo;
    }

    public void setAsistencia_periodo(int asistencia_periodo) {
        this.asistencia_periodo = asistencia_periodo;
    }

    public int getNivel_id() {
        return nivel_id;
    }

    public void setNivel_id(int nivel_id) {
        this.nivel_id = nivel_id;
    }

    public String getNivel_nombre() {
        return nivel_nombre;
    }

    public void setNivel_nombre(String nivel_nombre) {
        this.nivel_nombre = nivel_nombre;
    }
    
    @Override
    public String toString() {
        return this.getNombre_periodo();
    }
    
    JComboBox<PeriodoEntity> cmbPeriodo = new JComboBox<>();
    
}
