/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mpsEntity;

import java.util.Objects;
import javax.swing.JComboBox;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email jkevin.mv@gmail.com
 */
public class ContactoEntity {

    private String contacto;
    private String contacto_telefono;

    public ContactoEntity(String contacto, String contacto_telefono) {
        this.contacto = contacto;
        this.contacto_telefono = contacto_telefono;
    }

    public ContactoEntity(String contacto_telefono) {
        this.contacto_telefono = contacto_telefono;
    }
    
    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getContacto_telefono() {
        return contacto_telefono;
    }

    public void setContacto_telefono(String contacto_telefono) {
        this.contacto_telefono = contacto_telefono;
    }
    
    @Override
    public String toString() {
        return this.getContacto() + " / " + this.getContacto_telefono();
    }
    
    JComboBox<ContactoEntity> cmbContacto = new JComboBox<>();
    
//    @Override
//    public boolean equals(Object obj) {
//        if (obj == null) {
//            return false;
//        }
//        if (!ContactoEntity.class.isAssignableFrom(obj.getClass())) {
//            return false;
//        }
//
//        final ContactoEntity other = (ContactoEntity) obj;
//        if ((this.contacto_telefono == null) ? (other.contacto_telefono != null) : !this.contacto_telefono.equals(other.contacto_telefono)) {
//            return false;
//        }
//
////        if (this.age != other.age) {
////            return false;
////        }
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 7;
//        hash = 37 * hash + Objects.hashCode(this.contacto_telefono);
//        return hash;
//    }
    
}
