/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PckEntidad;

/**
 *
 * @author Sistemas
 */
public class ClsEntidadDeudasComida {
    //id_comida,mes_comida,monto_comida,total_comida,pendiente_comida,id_periodo,periodo_alu
    
    private String Id_Comida;
    private String Mes_Comida;
    private String Monto_Comida;
    private String Total_Comida;
    private String Pendiente_Comida;
    private String Id_Periodo;
    private String Periodo_Alumno;

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

    public String getMonto_Comida() {
        return Monto_Comida;
    }

    public void setMonto_Comida(String Monto_Comida) {
        this.Monto_Comida = Monto_Comida;
    }

    public String getTotal_Comida() {
        return Total_Comida;
    }

    public void setTotal_Comida(String Total_Comida) {
        this.Total_Comida = Total_Comida;
    }

    public String getPendiente_Comida() {
        return Pendiente_Comida;
    }

    public void setPendiente_Comida(String Pendiente_Comida) {
        this.Pendiente_Comida = Pendiente_Comida;
    }

    public String getId_Periodo() {
        return Id_Periodo;
    }

    public void setId_Periodo(String Id_Periodo) {
        this.Id_Periodo = Id_Periodo;
    }

    public String getPeriodo_Alumno() {
        return Periodo_Alumno;
    }

    public void setPeriodo_Alumno(String Periodo_Alumno) {
        this.Periodo_Alumno = Periodo_Alumno;
    }
    
    
}
