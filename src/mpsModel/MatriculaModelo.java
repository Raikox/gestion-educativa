/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mpsModel;

import PckConexion.ClsConexion;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import mpsDAL.AulaDAL;
import mpsDAL.MatriculaDAL;
import mpsEntity.AulaEntity;
import mpsDAL.PeriodoDAL;
import mpsEntity.ContactoEntity;
import mpsEntity.NivelEntity;
import mpsEntity.PeriodoEntity;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email jkevin.mv@gmail.com
 */
public class MatriculaModelo {

    MatriculaDAL matriculaDAL = new MatriculaDAL();
    
    public void CargarComboNivel(JComboBox CmbNivel) {
        
        CmbNivel.removeAllItems();
        Connection con = ClsConexion.getConection();
        ArrayList<NivelEntity> arrayNivel = matriculaDAL.SeleccionarNiveles(con);
        
        arrayNivel.stream().map(
                (n) -> new NivelEntity (
                        n.getNivel_id() ,
                        n.getNivel_nombre()
                )
        ).forEachOrdered( 
                //se llama toda la clase porque tiene su metodo toString sobreescrito
                (nivelEntity)-> { CmbNivel.addItem(nivelEntity); } 
        );
        ClsConexion.closeConnection(con);
        
    }
    
    //requiere nivel para obtener ID
    public void CargarComboPeriodo(JComboBox CmbPeriodo, JComboBox CmbNivel) {
        
        NivelEntity nivelEntity = (NivelEntity) CmbNivel.getSelectedItem();
        int idNivel = Integer.parseInt(nivelEntity.getNivel_id());
        
        CmbPeriodo.removeAllItems();
        Connection con = ClsConexion.getConection();
        ArrayList<PeriodoEntity> arrayPeriodo = matriculaDAL.SeleccionarPeriodos(con, String.valueOf(idNivel));
        
        arrayPeriodo.stream().filter( (p) -> (idNivel == p.getNivel_id()) ).map(
                (p) -> new PeriodoEntity (
                        p.getId_periodo(), 
                        p.getNombre_periodo(), 
                        p.getEstado_periodo(), 
                        idNivel
                )
        ).forEachOrdered(
                //se llama toda la clase porque tiene su metodo toString sobreescrito
                (periodoEntity) -> { CmbPeriodo.addItem(periodoEntity); } 
        );
        ClsConexion.closeConnection(con);
        
    }
    
    public void CargarComboAula(JComboBox CmbAula, JComboBox CmbPeriodo) {
        
        PeriodoEntity periodoEntity = (PeriodoEntity) CmbPeriodo.getSelectedItem();
        int idPeriodo = periodoEntity.getId_periodo();
        
        CmbAula.removeAllItems();
        Connection con = ClsConexion.getConection();
        ArrayList<AulaEntity> arrayAula = matriculaDAL.SeleccionarAulas(con, String.valueOf(idPeriodo));
        
        arrayAula.stream().filter( (a) -> (idPeriodo == a.getMPS_Periodo_id_periodo()) ).map(
                (p) -> new AulaEntity (
                        p.getId_seccion(), 
                        p.getNombre_seccion(), 
                        p.getMPS_Periodo_id_periodo()                         
                )
        ).forEachOrdered(
                //se llama toda la clase porque tiene su metodo toString sobreescrito
                (aulaEntity) -> { CmbAula.addItem(aulaEntity); } 
        );
        ClsConexion.closeConnection(con);
    }
    
    /*
    * Muestra Los periodos activos
    */
    public ArrayList<Object> MostrarComboPeriodos(JComboBox _Combo)
    {
        ArrayList<Object> arrayIdPeriodo = new ArrayList<>();
        ArrayList<PeriodoEntity> arrayPeriodo;
        DefaultComboBoxModel dcm = new DefaultComboBoxModel();
        PeriodoDAL periodoDAL = new PeriodoDAL();
        int count=0;
        
        dcm.removeAllElements();        
        arrayPeriodo = periodoDAL.ListarPeriodos();
        
        for(PeriodoEntity pe : arrayPeriodo)
        {
            if(pe.getEstado_periodo().equals("Activo"))
            {                
                arrayIdPeriodo.add(pe.getId_periodo());
                dcm.addElement(pe.getNombre_periodo());
                count++;
            }            
        }
        _Combo.setModel(dcm);
        
        return arrayIdPeriodo;
    }
    
    /*
    * Muestra las aulas de un determinado periodo activo
    */
    public ArrayList<Object> MostrarComboAulasPeriodo(JComboBox _Combo, int _IdPeriodo) {
        ArrayList<Object> arrayIdAula = new ArrayList<>();
        ArrayList<AulaEntity> arrayAula;
        DefaultComboBoxModel dcm = new DefaultComboBoxModel();
        AulaDAL aulaDAL = new AulaDAL();
        int count=0;
        
        dcm.removeAllElements();        
        arrayAula = aulaDAL.ListarAulasPorPeriodo(_IdPeriodo);
        
        for(AulaEntity ae : arrayAula)
        {                           
            arrayIdAula.add(ae.getId_seccion());
            dcm.addElement(ae.getNombre_seccion());
            count++;
            
        }
        _Combo.setModel(dcm);
        
        return arrayIdAula;
    }
    

    
    public void CargarComboContacto(JComboBox CmbContacto, String IdAlumno) {
        
        CmbContacto.removeAllItems();
        Connection con = ClsConexion.getConection();
        
        ArrayList<ContactoEntity> arrayContacto = matriculaDAL.SeleccionarContactos(con, IdAlumno);
        
        arrayContacto.stream().map(
                (c) -> new ContactoEntity 
                        (
                                c.getContacto(),
                                c.getContacto_telefono()
                        )                
        ).forEachOrdered( (ContactoEntity) -> { CmbContacto.addItem(ContactoEntity); } );
        ClsConexion.closeConnection(con);
        
    }
    
    public ArrayList<Object> MostrarComboAulas(JComboBox _Combo)
    {
        ArrayList<Object> arrayIdAula = new ArrayList<>();
        ArrayList<AulaEntity> arrayAula;
        DefaultComboBoxModel dcm = new DefaultComboBoxModel();
        AulaDAL aulaDAL = new AulaDAL();
        int count=0;
        
        dcm.removeAllElements();        
        arrayAula = aulaDAL.ListarAulasActivas();
        
        for(AulaEntity ae : arrayAula)
        {                           
            arrayIdAula.add(ae.getId_seccion());
            dcm.addElement(ae.getNombre_seccion());
            count++;
            
        }
        _Combo.setModel(dcm);
        
        return arrayIdAula;
    }
    
    public ArrayList<Object> MostrarComboAulasDocente(JComboBox _Combo, int _IdDocente)
    {
        ArrayList<Object> arrayIdAula = new ArrayList<>();
        ArrayList<AulaEntity> arrayAula;
        DefaultComboBoxModel dcm = new DefaultComboBoxModel();
        AulaDAL aulaDAL = new AulaDAL();
        int count=0;
        
        dcm.removeAllElements();        
        arrayAula = aulaDAL.ListarAulaDocente(_IdDocente);
        
        for(AulaEntity ae : arrayAula)
        {                           
            arrayIdAula.add(ae.getId_seccion());
            dcm.addElement(ae.getNombre_seccion());
            count++;
            
        }
        _Combo.setModel(dcm);
        
        return arrayIdAula;
    }
    
}
