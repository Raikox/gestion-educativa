/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package prymatricula;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author kmv
 */
public class PryMatricula {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            /**/ /**/
//        try
//        {
//           UIManager.setLookAndFeel(new SyntheticaBlackMoonLookAndFeel());
//           PckInterfaces.FrmMpsLogin obj= new PckInterfaces.FrmMpsLogin();
//            
//            obj.setVisible(true);
//            obj.setLocationRelativeTo(null);
//
//        }
//        catch (ParseException | UnsupportedLookAndFeelException e) 
//        {
//          e.printStackTrace();
//        }
        
//        try {
//            UIManager.setLookAndFeel ( new WebLookAndFeel () );
//        } catch (UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(PryMatricula.class.getName()).log(Level.SEVERE, null, ex);
//        }

//            PckInterfaces.FrmPrincipal obj = new PckInterfaces.FrmPrincipal();
//            obj.setVisible(true);
//            obj.setLocationRelativeTo(null);


//        SwingUtilities.invokeLater(new Runnable() {
//            
//            @Override
//            public void run() {
//                WebLookAndFeel.install();
//            }
//            
//        });
//
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(PryMatricula.class.getName()).log(Level.SEVERE, null, ex);
        }
        PckInterfaces.FrmMpsLogin obj= new PckInterfaces.FrmMpsLogin();
        obj.setVisible(true);
        obj.setLocationRelativeTo(null);
//               

    }
   
}
