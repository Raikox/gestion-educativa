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
public class ClsEntidadAnecdotarioComentario {
    private String comentario_id;
    private String comentario_comentario;
    private String comentario_fecha;
    private String anecdotario_id;
    private String personal_id;
    private String personal_nombre;

    public String getComentario_id() {
        return comentario_id;
    }

    public void setComentario_id(String comentario_id) {
        this.comentario_id = comentario_id;
    }

    public String getComentario_comentario() {
        return comentario_comentario;
    }

    public void setComentario_comentario(String comentario_comentario) {
        this.comentario_comentario = comentario_comentario;
    }

    public String getComentario_fecha() {
        return comentario_fecha;
    }

    public void setComentario_fecha(String comentario_fecha) {
        this.comentario_fecha = comentario_fecha;
    }

    public String getAnecdotario_id() {
        return anecdotario_id;
    }

    public void setAnecdotario_id(String anecdotario_id) {
        this.anecdotario_id = anecdotario_id;
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
    
}
