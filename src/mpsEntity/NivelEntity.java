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
public class NivelEntity {

    private String nivel_id;
    private String nivel_nombre;

    public NivelEntity(String nivel_id, String nivel_nombre) {
        this.nivel_id = nivel_id;
        this.nivel_nombre = nivel_nombre;
    }
    
    public String getNivel_id() {
        return nivel_id;
    }

    public void setNivel_id(String nivel_id) {
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
        return this.getNivel_nombre();
    }
    
    JComboBox<NivelEntity> cmbNivel = new JComboBox<>();
    
}
