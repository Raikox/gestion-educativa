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
public class ClsEntidadMatMen {
    private double Matricula_Mat;
    private double Mensualidad_Mat;
    private double Total_Mat;
    private double Pendiente_Mat;
    private String Vencimiento_Mat;
    private String Id_Matricula;

    public String getVencimiento_Mat() {
        return Vencimiento_Mat;
    }

    public void setVencimiento_Mat(String Vencimiento_Mat) {
        this.Vencimiento_Mat = Vencimiento_Mat;
    }
    
    public double getMatricula_Mat() {
        return Matricula_Mat;
    }

    public void setMatricula_Mat(double Matricula_Mat) {
        this.Matricula_Mat = Matricula_Mat;
    }

    public double getMensualidad_Mat() {
        return Mensualidad_Mat;
    }

    public void setMensualidad_Mat(double Mensualidad_Mat) {
        this.Mensualidad_Mat = Mensualidad_Mat;
    }

    public double getTotal_Mat() {
        return Total_Mat;
    }

    public void setTotal_Mat(double Total_Mat) {
        this.Total_Mat = Total_Mat;
    }

    public double getPendiente_Mat() {
        return Pendiente_Mat;
    }

    public void setPendiente_Mat(double Pendiente_Mat) {
        this.Pendiente_Mat = Pendiente_Mat;
    }

    public String getId_Matricula() {
        return Id_Matricula;
    }

    public void setId_Matricula(String Id_Matricula) {
        this.Id_Matricula = Id_Matricula;
    }      
    
}
