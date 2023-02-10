/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckEntidad;

import javax.swing.JLabel;
import javax.swing.JRadioButton;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 */
public class ClsControles {
        public JLabel lblOrden;
        public JLabel lblAlumno;
        public JRadioButton chkFalta;
	public JRadioButton chkAsiste;
	public JRadioButton chkTarde;
        
        public ClsControles(
                JLabel lblOrden,JLabel lblAlumno,JRadioButton chkAsiste, 
                JRadioButton chkTarde, JRadioButton chkFalta){
                this.lblOrden = lblOrden;
		this.lblAlumno = lblAlumno;
                this.chkFalta = chkFalta;
		this.chkAsiste = chkAsiste;
		this.chkTarde = chkTarde;
	}
        
}
