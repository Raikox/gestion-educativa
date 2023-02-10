/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ClsEntidadReporte;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email jkevin.mv@gmail.com
 */
public class ClsEntidadDescuento {
    private String aula;
    private String alumno;
    private String motivo;
    private String deberia;
    private String paga;
    private String diferencia;

    public ClsEntidadDescuento(String aula, String alumno, String motivo, String deberia, String paga, String diferencia) {
        this.aula = aula;
        this.alumno = alumno;
        this.motivo = motivo;
        this.deberia = deberia;
        this.paga = paga;
        this.diferencia = diferencia;
    }    
    
    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDeberia() {
        return deberia;
    }

    public void setDeberia(String deberia) {
        this.deberia = deberia;
    }

    public String getPaga() {
        return paga;
    }

    public void setPaga(String paga) {
        this.paga = paga;
    }

    public String getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(String diferencia) {
        this.diferencia = diferencia;
    }
    
    
}
