/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckEntidad;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email Jkevin.mv@gmail.com
 */
public class ClsEntidadPadreMadreDirectorio {
    private String padre;
    private String numero1;
    private String numero2;

    //constructor

    public ClsEntidadPadreMadreDirectorio(String padre, String numero1, String numero2) 
    {
        this.padre = padre;
        this.numero1 = numero1;
        this.numero2 = numero2;
    }    

    public String getPadre() {
        return padre;
    }

    public void setPadre(String padre) {
        this.padre = padre;
    }
        
    public String getNumero1() {
        return numero1;
    }

    public void setNumero1(String numero1) {
        this.numero1 = numero1;
    }

    public String getNumero2() {
        return numero2;
    }

    public void setNumero2(String numero2) {
        this.numero2 = numero2;
    }
}
