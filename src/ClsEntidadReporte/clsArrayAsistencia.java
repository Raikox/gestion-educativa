/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClsEntidadReporte;

/**
 *
 * @author Kevin
 */
public class clsArrayAsistencia {
    private String asistencia;
    private String porcentaje;
    private String valor;

    public clsArrayAsistencia(String asistencia, String porcentaje, String valor) {
        this.asistencia = asistencia;
        this.porcentaje = porcentaje;
        this.valor = valor;
    }
    
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }   
    
    public String getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }
    
}
