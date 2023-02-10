/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mpsModel;

import PckConexion.ClsConexion;
import static java.awt.image.ImageObserver.WIDTH;
import java.sql.Connection;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import mpsDAL.ReporteDAL;
import mpsEntity.AlumnoMatriculaEntity;
import mpsEntity.UsuarioEntity;
import prymatricula.ClsMisc;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email jkevin.mv@gmail.com
 */
public class ReporteAlumnoModelo {

    ReporteDAL reporteDAL = new ReporteDAL();
    
    
    
    public void CargarTablaRetiradosPeriodo(JTable Table, String IdPeriodo) throws ParseException {
        
        String columnTitle[] = {"N°","FECHA RETIRO","AULA","ALUMNO","MOTIVO"};
        ArrayList<AlumnoMatriculaEntity> arrayAlumnoMatricula;
        Object row[] = new Object[5];
        
        Connection con;
        DefaultTableModel dtmRetiro = new DefaultTableModel(null, columnTitle) {
            
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        int count = 1;
        con = ClsConexion.getConection();
        
        arrayAlumnoMatricula = reporteDAL.SeleccionarAlumnosRetiradosPeriodo(IdPeriodo, con);
        
        for(AlumnoMatriculaEntity ame : arrayAlumnoMatricula) {
            
            row[0] = count;
            row[1] = ame.getFecha_retiroEu();
            row[2] = ame.getNombre_seccion();
            row[3] = " "+ame.getAlumno_nombre_completo();
            row[4] = " "+ame.getRetiro_motivo();
            dtmRetiro.addRow(row); 
            count++;
        }
        
        ClsConexion.closeConnection(con);
        Table.setModel(dtmRetiro);
        Table.setRowHeight(20);
        
        TableColumn colOrden = Table.getColumnModel().getColumn(0);colOrden.setMaxWidth(35);
        TableColumn colFecha = Table.getColumnModel().getColumn(1);colFecha.setPreferredWidth(100);colFecha.setMaxWidth(140);
        TableColumn colAula = Table.getColumnModel().getColumn(2);colAula.setPreferredWidth(130);colAula.setMaxWidth(150);
        TableColumn colAlumno = Table.getColumnModel().getColumn(3);colAlumno.setPreferredWidth(300);colAlumno.setMaxWidth(370);
        TableColumn colMotivo = Table.getColumnModel().getColumn(4);colMotivo.setPreferredWidth(300);
        
        TableCellRenderer tcr =  Table.getTableHeader().getDefaultRenderer();
        ClsMisc.miRender miRender = new ClsMisc.miRender(tcr);
        miRender.getTableCellRendererComponent(Table, row, true, true, WIDTH, 0);
        miRender.getTableCellRendererComponent(Table, row, true, true, WIDTH, 1);
        miRender.getTableCellRendererComponent(Table, row, true, true, WIDTH, 2);
    }
    
    public void CargarTablaRetiradosAula(JTable Table, String IdAula) throws ParseException {
        
        String columnTitle[] = {"N°","FECHA RETIRO","ALUMNO","MOTIVO"};
        ArrayList<AlumnoMatriculaEntity> arrayAlumnoMatricula;
        Object row[] = new Object[4];
        
        Connection con;
        DefaultTableModel dtmRetiro = new DefaultTableModel(null, columnTitle) {
            
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        int count = 1;
        con = ClsConexion.getConection();
        
        arrayAlumnoMatricula = reporteDAL.SeleccionarAlumnosRetiradosAula(IdAula, con);
        
        for(AlumnoMatriculaEntity ame : arrayAlumnoMatricula) {
            
            row[0] = count;
            row[1] = ame.getFecha_retiroEu();            
            row[2] = " "+ame.getAlumno_nombre_completo();
            row[3] = " "+ame.getRetiro_motivo();
            dtmRetiro.addRow(row); 
            count++;
        }
        
        ClsConexion.closeConnection(con);
        Table.setModel(dtmRetiro);
        Table.setRowHeight(20);
        
        TableColumn colOrden = Table.getColumnModel().getColumn(0);colOrden.setMaxWidth(35);
        TableColumn colFecha = Table.getColumnModel().getColumn(1);colFecha.setPreferredWidth(100);colFecha.setMaxWidth(140); 
        TableColumn colAlumno = Table.getColumnModel().getColumn(2);colAlumno.setPreferredWidth(300);colAlumno.setMaxWidth(370);
        TableColumn colMotivo = Table.getColumnModel().getColumn(3);colMotivo.setPreferredWidth(300);
        
        TableCellRenderer tcr =  Table.getTableHeader().getDefaultRenderer();
        ClsMisc.miRender miRender = new ClsMisc.miRender(tcr);
        miRender.getTableCellRendererComponent(Table, row, true, true, WIDTH, 0);
        miRender.getTableCellRendererComponent(Table, row, true, true, WIDTH, 1);
        
    }
    
    
}
