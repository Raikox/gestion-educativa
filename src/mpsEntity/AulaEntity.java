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
public class AulaEntity {
    
    private int id_seccion;
    private String nombre_seccion;
    private String cantidad_seccion;
    private int asistencia_seccion;
    private int MPS_Profesor_id_profesor;
    private String docente;
    private int MPS_Profesor_id_auxiliar1;
    private String auxliar1;
    private int MPS_Profesor_id_auxiliar2;
    private String auxliar2;
    private int MPS_Periodo_id_periodo;
    private String nombre_periodo;

    public AulaEntity() {
    }
    
    public AulaEntity(int id_seccion, String nombre_seccion, int MPS_Periodo_id_periodo) {
        this.id_seccion = id_seccion;
        this.nombre_seccion = nombre_seccion;
        this.MPS_Periodo_id_periodo = MPS_Periodo_id_periodo;
    }
    
    public int getId_seccion() {
        return id_seccion;
    }

    public void setId_seccion(int id_seccion) {
        this.id_seccion = id_seccion;
    }

    public String getNombre_seccion() {
        return nombre_seccion;
    }

    public void setNombre_seccion(String nombre_seccion) {
        this.nombre_seccion = nombre_seccion;
    }

    public String getCantidad_seccion() {
        return cantidad_seccion;
    }

    public void setCantidad_seccion(String cantidad_seccion) {
        this.cantidad_seccion = cantidad_seccion;
    }

    public int getAsistencia_seccion() {
        return asistencia_seccion;
    }

    public void setAsistencia_seccion(int asistencia_seccion) {
        this.asistencia_seccion = asistencia_seccion;
    }

    public int getMPS_Profesor_id_profesor() {
        return MPS_Profesor_id_profesor;
    }

    public void setMPS_Profesor_id_profesor(int MPS_Profesor_id_profesor) {
        this.MPS_Profesor_id_profesor = MPS_Profesor_id_profesor;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public int getMPS_Profesor_id_auxiliar1() {
        return MPS_Profesor_id_auxiliar1;
    }

    public void setMPS_Profesor_id_auxiliar1(int MPS_Profesor_id_auxiliar1) {
        this.MPS_Profesor_id_auxiliar1 = MPS_Profesor_id_auxiliar1;
    }

    public String getAuxliar1() {
        return auxliar1;
    }

    public void setAuxliar1(String auxliar1) {
        this.auxliar1 = auxliar1;
    }

    public int getMPS_Profesor_id_auxiliar2() {
        return MPS_Profesor_id_auxiliar2;
    }

    public void setMPS_Profesor_id_auxiliar2(int MPS_Profesor_id_auxiliar2) {
        this.MPS_Profesor_id_auxiliar2 = MPS_Profesor_id_auxiliar2;
    }

    public String getAuxliar2() {
        return auxliar2;
    }

    public void setAuxliar2(String auxliar2) {
        this.auxliar2 = auxliar2;
    }

    public int getMPS_Periodo_id_periodo() {
        return MPS_Periodo_id_periodo;
    }

    public void setMPS_Periodo_id_periodo(int MPS_Periodo_id_periodo) {
        this.MPS_Periodo_id_periodo = MPS_Periodo_id_periodo;
    }

    public String getNombre_periodo() {
        return nombre_periodo;
    }

    public void setNombre_periodo(String nombre_periodo) {
        this.nombre_periodo = nombre_periodo;
    }

    @Override
    public String toString() {
        return this.getNombre_seccion();
    }
    
    JComboBox<AulaEntity> cmbAula = new JComboBox<>();
    
}
