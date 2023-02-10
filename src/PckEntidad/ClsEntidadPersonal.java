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
public class ClsEntidadPersonal {
    
    private String personal_id;
    private String personal_nombre;
    private String personal_apellido_paterno;
    private String personal_apellido_materno;
    private String personal_dni;
    private String personal_telefono;
    private String personal_usuario;
    private String personal_password;
    private String rol_id;
    private String rol_nombre;
    private String personal_estado;

    public String getPersonal_estado() {
        return personal_estado;
    }

    public void setPersonal_estado(String personal_estado) {
        this.personal_estado = personal_estado;
    }
        
    public String getPersonal_id() {
        return personal_id;
    }

    public void setPersonal_id(String personal_id) {
        this.personal_id = personal_id;
    }
    
    public String getPersonal_nombre() {
        return personal_nombre;
    }

    public void setPersonal_nombre(String personal_nombre) {
        this.personal_nombre = personal_nombre;
    }

    public String getPersonal_apellido_paterno() {
        return personal_apellido_paterno;
    }

    public void setPersonal_apellido_paterno(String personal_apellido_paterno) {
        this.personal_apellido_paterno = personal_apellido_paterno;
    }

    public String getPersonal_apellido_materno() {
        return personal_apellido_materno;
    }

    public void setPersonal_apellido_materno(String personal_apellido_materno) {
        this.personal_apellido_materno = personal_apellido_materno;
    }

    public String getPersonal_dni() {
        return personal_dni;
    }

    public void setPersonal_dni(String personal_dni) {
        this.personal_dni = personal_dni;
    }

    public String getPersonal_telefono() {
        return personal_telefono;
    }

    public void setPersonal_telefono(String personal_telefono) {
        this.personal_telefono = personal_telefono;
    }

    public String getPersonal_usuario() {
        return personal_usuario;
    }

    public void setPersonal_usuario(String personal_usuario) {
        this.personal_usuario = personal_usuario;
    }

    public String getPersonal_password() {
        return personal_password;
    }
    
    /**
    * Asigna el password encriptado en Sha
    * @param personal_password String en Sha
    */
    public void setPersonal_password(String personal_password) {
        this.personal_password = personal_password;
    }

    public String getRol_id() {
        return rol_id;
    }

    public void setRol_id(String rol_id) {
        this.rol_id = rol_id;
    }

    public String getRol_nombre() {
        return rol_nombre;
    }

    public void setRol_nombre(String rol_nombre) {
        this.rol_nombre = rol_nombre;
    }

}
