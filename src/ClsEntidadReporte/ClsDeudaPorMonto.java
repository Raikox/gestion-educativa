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
public class ClsDeudaPorMonto {
    
    private String alumno;
    private String monto;
    private String aula;
    private String mama;
    private String telefonom;
    private String papa;
    private String telefonop;

    public ClsDeudaPorMonto(String alumno, String monto, String aula, String mama, String telefonom, String papa, String telefonop) {
        this.alumno = alumno;
        this.monto = monto;
        this.aula = aula;
        this.mama = mama;
        this.telefonom = telefonom;
        this.papa = papa;
        this.telefonop = telefonop;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getMama() {
        return mama;
    }

    public void setMama(String mama) {
        this.mama = mama;
    }

    public String getTelefonom() {
        return telefonom;
    }

    public void setTelefonom(String telefonom) {
        this.telefonom = telefonom;
    }

    public String getPapa() {
        return papa;
    }

    public void setPapa(String papa) {
        this.papa = papa;
    }

    public String getTelefonop() {
        return telefonop;
    }

    public void setTelefonop(String telefonop) {
        this.telefonop = telefonop;
    }

 
    
    
}
