/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckHilo;

import PckConexion.ClsConexion;
import PckEntidad.ClsEntidadEdad;
import PckNegocio.ClsEdad;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import static prymatricula.ClsMisc.calculaEdad;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 */
public class HiloEdad implements Runnable{
    Thread h1;
    Connection con = null;
    private volatile boolean isRunning = true;
    
    //Constructor 
    public HiloEdad() 
    {     
        h1 = new  Thread(this); 
        h1.start(); 
    }
        
    @Override
    public void run() 
    {
        System.out.println("Actualizando edades ...\nConexión abierta");
        con = ClsConexion.getConection();
        while(isRunning)
        {
            ClsEdad edades = new ClsEdad();
            ArrayList<ClsEntidadEdad> edad =  edades.ListarNacimiento(con);                                
            Iterator it = edad.iterator();        
            String codMatricula; 
            Date nacAlumno;
            String edadAlumno;
            while(it.hasNext())
            {
                //Se obtiene el nacimiento del alumno            
                ClsEntidadEdad Edad = (ClsEntidadEdad) it.next();                        
                codMatricula = Edad.getId_Matricula();
                nacAlumno = Edad.getNacimiento_Alumno();

                //Se hace la operacion para obtener la edad actual
                edadAlumno = calculaEdad(nacAlumno);
                //Modificar el registro en la tabla matricula
                edades.ModificarEdad(codMatricula, edadAlumno,con);
                //System.out.println("edad actualizada: "+codAlumno+" - "+edadAlumno);
                
            }
            System.out.println("Edades actualizadas");
            terminate();
        }
    }
    
    public void terminate() 
    {
        isRunning = false;
        ClsConexion.closeConnection(con);
        System.out.println("Conexión cerrada");
    }

}
