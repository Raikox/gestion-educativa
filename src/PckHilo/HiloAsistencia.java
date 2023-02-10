/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckHilo;

import PckConexion.ClsConexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 */
public class HiloAsistencia extends JTextPane implements Runnable{
    Thread h2;
    Connection con=null;
    private volatile boolean isRunning = true;
    
    public HiloAsistencia() 
    {     
        h2 = new  Thread(this); 
        h2.start(); 
    }

    @Override
    public void run() 
    {
        while(isRunning)
        {
            //JTextPane//
            //se instancia el estilo del documento para poder trabajar sus atributos
            StyledDocument doc = getStyledDocument();
            //se instancian los atributos a center
            SimpleAttributeSet estilos = new SimpleAttributeSet();
            //agregamos los estilos a center
            StyleConstants.setAlignment(estilos, StyleConstants.ALIGN_CENTER);
            StyleConstants.setBold(estilos, true);
            StyleConstants.setFontSize(estilos, 12);
            //permite que el texto se centre
            doc.setParagraphAttributes(0, doc.getLength(), estilos, false);
            //Fin JTextPane
            con = ClsConexion.getConection();
            //Texto que ira dentro del JTextPane
            ArrayList<String> salones = salones();           
            //Limpiamos el JTextPane
            setText("");
            //Le negamos el atributo de editar
            setEditable(false);
            int a=0;
            for(String hola:salones)
            {
                try 
                {
                    //Se inserta el array con los estilos creados
                    doc.insertString(doc.getLength(), salones.get(a)+"\n", estilos);                    
                    a++;
                } catch (BadLocationException ex) {
                    Logger.getLogger(HiloAsistencia.class.getName()).log(Level.SEVERE, null, ex);
                }
            }           
            
            //cada segundo se ejecuta el hilo
            try {
            Thread.sleep(3000);
            }catch(InterruptedException e) {
            isRunning = false;
            e.printStackTrace();
            }
        }
    }
    
    public void terminate() {
        isRunning = false;
        ClsConexion.closeConnection(con); 
    }
    
    private ArrayList<String> salones()
    {
        ArrayList<String> salones = new ArrayList();
        try 
        {
            
            ResultSet resultado = ListarSeccionesTomadas(con);
            while(resultado.next())
            {
                salones.add(resultado.getString("nombre_seccion"));
            }
            
        } catch (Exception ex) {
            Logger.getLogger(HiloAsistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return salones;
    }
    
    private ResultSet ListarSeccionesTomadas(Connection con)  throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement st = con.prepareCall("{call sp_L_SeccionesTomadas()}");            
            rs = st.executeQuery();
            return rs;
            
        }catch(SQLException ex){
            throw ex;
        }
    } 
}
