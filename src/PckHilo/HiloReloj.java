/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckHilo;

import PckConexion.ClsConexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 */
public class HiloReloj extends JTextField implements Runnable{
    Thread h1;
    int hora,minutos,segundos;
    boolean suspended = false;
    Connection con = null;
    DecimalFormat formateador = new DecimalFormat("00");
    //---------------------------------------------------------//
    //590, 40, 120, 30   
    private volatile boolean isRunning = true;    
    public HiloReloj() 
    { //Constructor     
        h1 = new  Thread(this); 
        h1.start(); 
    }    
    
    @Override
    public void run() 
    {
        String horaa = "";  
        con = ClsConexion.getConection();
        String fecha_y_hora=getDate_Full(con);
        ClsConexion.closeConnection(con);
        
        //String fecha="";         
        String subhora="";         
        String x[]=fecha_y_hora.split(" ");
        String aux = x[1].substring(0,8);
        //System.out.println(aux); 
        subhora= aux;
        String h[] = subhora.split(":");
        hora = Integer.parseInt(h[0]);
        minutos = Integer.parseInt(h[1]);
        segundos = Integer.parseInt(h[2]);
        
        //Thread ct = Thread.currentThread();
        //Thread h1 = Thread.currentThread();
        while(isRunning) 
        {
            segundos = segundos+1;
            if(segundos==60)
            {
                minutos = minutos+1;
                segundos = 0;
            }
            if(minutos==60)
            {
                hora =hora+1;
                minutos=0;
            }
            horaa =String.valueOf(formateador.format(hora) + ":" + 
                    formateador.format(minutos) + ":" + 
                    formateador.format(segundos));
            //System.out.println(horaa); 
            setText(" "+horaa);
            
            try {
            Thread.sleep(1000);
            }catch(InterruptedException e) {
            isRunning = false;
            e.printStackTrace();
            }
        }       
    }
    public void terminate() {
        isRunning = false;
    }
    public String getDate_Full(Connection con)     
    {         String sql="SELECT NOW() AS Hora_Fecha";               
            String fx="2008-02-01 00:00:00";         
            try         
            {             
                Statement st = con.createStatement();
                ResultSet  re1= st.executeQuery(sql);             
                while(re1.next())             
                {                 
                    fx=re1.getString("Hora_Fecha");             
                }         
            }catch(Exception e )         
            { 
                System.out.print(e);         
                JOptionPane.showMessageDialog(this, e, "excepción", 0);
            }               
        return fx;     
    }

}
