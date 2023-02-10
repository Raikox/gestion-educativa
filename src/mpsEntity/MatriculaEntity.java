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
public class MatriculaEntity {

    private String id_matricula;
    private String cod_matricula;
    private String fecha_matricula;
    private String inicio_clases;
    private String edadanio_matricula;
    private String horarioe_matricula;
    private String horarios_matricula;
    private String mensualidad_matricula;
    private String sexo_alumno;
    private String comida_alumno;
    private String partida_nac;
    private String dni_padre;
    private String dni_madre;
    private String dni_menor;
    private String tarjeta_vac;
    private String Otro;
    private String retiro;
    private String MPS_Alumno_id_alumno;
    private String MPS_dclinico_id_clinico;
    private String MPS_emergencia_id_emer;
    private String seccion_id;

    public MatriculaEntity() {
    }

    public MatriculaEntity(String id_matricula, String cod_matricula, String fecha_matricula, String inicio_clases, String edadanio_matricula, String horarioe_matricula, String horarios_matricula, String mensualidad_matricula, String sexo_alumno, String comida_alumno, String partida_nac, String dni_padre, String dni_madre, String dni_menor, String tarjeta_vac, String Otro, String retiro, String MPS_Alumno_id_alumno, String MPS_dclinico_id_clinico, String MPS_emergencia_id_emer, String seccion_id) {
        this.id_matricula = id_matricula;
        this.cod_matricula = cod_matricula;
        this.fecha_matricula = fecha_matricula;
        this.inicio_clases = inicio_clases;
        this.edadanio_matricula = edadanio_matricula;
        this.horarioe_matricula = horarioe_matricula;
        this.horarios_matricula = horarios_matricula;
        this.mensualidad_matricula = mensualidad_matricula;
        this.sexo_alumno = sexo_alumno;
        this.comida_alumno = comida_alumno;
        this.partida_nac = partida_nac;
        this.dni_padre = dni_padre;
        this.dni_madre = dni_madre;
        this.dni_menor = dni_menor;
        this.tarjeta_vac = tarjeta_vac;
        this.Otro = Otro;
        this.retiro = retiro;
        this.MPS_Alumno_id_alumno = MPS_Alumno_id_alumno;
        this.MPS_dclinico_id_clinico = MPS_dclinico_id_clinico;
        this.MPS_emergencia_id_emer = MPS_emergencia_id_emer;
        this.seccion_id = seccion_id;
    }

    public MatriculaEntity(String id_matricula) {
        this.id_matricula = id_matricula;
    }
    
    public String getId_matricula() {
        return id_matricula;
    }

    public void setId_matricula(String id_matricula) {
        this.id_matricula = id_matricula;
    }

    public String getCod_matricula() {
        return cod_matricula;
    }

    public void setCod_matricula(String cod_matricula) {
        this.cod_matricula = cod_matricula;
    }

    public String getFecha_matricula() {
        return fecha_matricula;
    }

    public void setFecha_matricula(String fecha_matricula) {
        this.fecha_matricula = fecha_matricula;
    }

    public String getInicio_clases() {
        return inicio_clases;
    }

    public void setInicio_clases(String inicio_clases) {
        this.inicio_clases = inicio_clases;
    }

    public String getEdadanio_matricula() {
        return edadanio_matricula;
    }

    public void setEdadanio_matricula(String edadanio_matricula) {
        this.edadanio_matricula = edadanio_matricula;
    }

    public String getHorarioe_matricula() {
        return horarioe_matricula;
    }

    public void setHorarioe_matricula(String horarioe_matricula) {
        this.horarioe_matricula = horarioe_matricula;
    }

    public String getHorarios_matricula() {
        return horarios_matricula;
    }

    public void setHorarios_matricula(String horarios_matricula) {
        this.horarios_matricula = horarios_matricula;
    }

    public String getMensualidad_matricula() {
        return mensualidad_matricula;
    }

    public void setMensualidad_matricula(String mensualidad_matricula) {
        this.mensualidad_matricula = mensualidad_matricula;
    }

    public String getSexo_alumno() {
        return sexo_alumno;
    }

    public void setSexo_alumno(String sexo_alumno) {
        this.sexo_alumno = sexo_alumno;
    }

    public String getComida_alumno() {
        return comida_alumno;
    }

    public void setComida_alumno(String comida_alumno) {
        this.comida_alumno = comida_alumno;
    }

    public String getPartida_nac() {
        return partida_nac;
    }

    public void setPartida_nac(String partida_nac) {
        this.partida_nac = partida_nac;
    }

    public String getDni_padre() {
        return dni_padre;
    }

    public void setDni_padre(String dni_padre) {
        this.dni_padre = dni_padre;
    }

    public String getDni_madre() {
        return dni_madre;
    }

    public void setDni_madre(String dni_madre) {
        this.dni_madre = dni_madre;
    }

    public String getDni_menor() {
        return dni_menor;
    }

    public void setDni_menor(String dni_menor) {
        this.dni_menor = dni_menor;
    }

    public String getTarjeta_vac() {
        return tarjeta_vac;
    }

    public void setTarjeta_vac(String tarjeta_vac) {
        this.tarjeta_vac = tarjeta_vac;
    }

    public String getOtro() {
        return Otro;
    }

    public void setOtro(String Otro) {
        this.Otro = Otro;
    }

    public String getRetiro() {
        return retiro;
    }

    public void setRetiro(String retiro) {
        this.retiro = retiro;
    }

    public String getMPS_Alumno_id_alumno() {
        return MPS_Alumno_id_alumno;
    }

    public void setMPS_Alumno_id_alumno(String MPS_Alumno_id_alumno) {
        this.MPS_Alumno_id_alumno = MPS_Alumno_id_alumno;
    }

    public String getMPS_dclinico_id_clinico() {
        return MPS_dclinico_id_clinico;
    }

    public void setMPS_dclinico_id_clinico(String MPS_dclinico_id_clinico) {
        this.MPS_dclinico_id_clinico = MPS_dclinico_id_clinico;
    }

    public String getMPS_emergencia_id_emer() {
        return MPS_emergencia_id_emer;
    }

    public void setMPS_emergencia_id_emer(String MPS_emergencia_id_emer) {
        this.MPS_emergencia_id_emer = MPS_emergencia_id_emer;
    }

    public String getSeccion_id() {
        return seccion_id;
    }

    public void setSeccion_id(String seccion_id) {
        this.seccion_id = seccion_id;
    }
    
    
    
}
