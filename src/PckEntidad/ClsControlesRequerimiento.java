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
 * @author Kevin
 */
public class ClsControlesRequerimiento {
    
    public JLabel lblOrden;
    public JLabel lblAlumno;
    public JRadioButton rbnSI;
    public JRadioButton rbnNO;

    public ClsControlesRequerimiento(JLabel lblOrden, JLabel lblAlumno, JRadioButton rbnSI, JRadioButton rbnNO) {
        this.lblOrden = lblOrden;
        this.lblAlumno = lblAlumno;
        this.rbnSI = rbnSI;
        this.rbnNO = rbnNO;
    }
    
    
    
}
