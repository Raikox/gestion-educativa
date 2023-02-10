/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mpsModel;

import PckConexion.ClsConexion;
import com.toedter.calendar.JDateChooser;
import static java.awt.image.ImageObserver.WIDTH;
import java.sql.Connection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import mpsDAL.ReporteDAL;
import mpsDAL.UsuarioDAL;
import mpsEntity.RolEntity;
import mpsEntity.UsuarioEntity;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import prymatricula.ClsMisc;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email jkevin.mv@gmail.com
 */
public class UsuarioModelo {

    UsuarioDAL usuarioDAL = new UsuarioDAL();
    ReporteDAL reporteDAL = new ReporteDAL();
    
    public void CargarTablaPersonal(JTable Table) throws ParseException {
        
        String columnTitle[] = {"N°","ROL","NOMBRE","DNI","FEC. NACIMIENTO","CELULAR"};
        
        ArrayList<UsuarioEntity> arrayUsuario;
        Object row[] = new Object[6];
        
        Connection con;
        DefaultTableModel dtmPersonal = new DefaultTableModel(null, columnTitle) {
            
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        
        con = ClsConexion.getConection();
        int count = 1;
        arrayUsuario = reporteDAL.SeleccionarUsuarios(con);
        
        for(UsuarioEntity ue : arrayUsuario) {
            
            
                
                row[0] = count;
                row[1] = ue.getRol_nombre();
                row[2] = ue.getNombresApellidos();
                row[3] = ue.getPersonal_dni();
                row[4] = ue.getFecha_nacimientoEu();
                row[5] = ue.getPersonal_telefono();
                count++;
                dtmPersonal.addRow(row);
            
        }
        
        ClsConexion.closeConnection(con);
        
        Table.setModel(dtmPersonal);
        Table.setRowHeight(20);
        
        TableCellRenderer tcr =  Table.getTableHeader().getDefaultRenderer();
        ClsMisc.miRender miRender = new ClsMisc.miRender(tcr); 
        
        miRender.getTableCellRendererComponent(Table, row, true, true, WIDTH, 0);
        miRender.getTableCellRendererComponent(Table, row, true, true, WIDTH, 1);
        miRender.getTableCellRendererComponent(Table, row, true, true, WIDTH, 3);
        miRender.getTableCellRendererComponent(Table, row, true, true, WIDTH, 4);
        
        TableColumn colOrden = Table.getColumnModel().getColumn(0);colOrden.setMaxWidth(35);
        TableColumn colRol = Table.getColumnModel().getColumn(1);colRol.setPreferredWidth(100);
        TableColumn colNombres = Table.getColumnModel().getColumn(2);colNombres.setPreferredWidth(200);
    }
    
    public void GenerarReporteUsuarios() {
        
        try {
            Connection con;
            ArrayList<UsuarioEntity> arrayUsuario;
            int count = 0;
            con = ClsConexion.getConection();
            arrayUsuario = reporteDAL.SeleccionarUsuarios(con);
            
            for(UsuarioEntity ue : arrayUsuario) {
                
                    count++;            
                
            }
            
            Map mapa = new HashMap();
            JasperPrint jpReporte;
            JRBeanCollectionDataSource dsReporte;
            JasperViewer jvReporte;
            String pathJRXML = "/Reportes/rptPersonal.jrxml";
            String pathJASPER = "/Reportes/rptPersonal.jasper";
            
            mapa.put("contador", String.valueOf(count));
            
            dsReporte = new JRBeanCollectionDataSource(arrayUsuario);
            JasperCompileManager.compileReportToFile
                (
                        System.getProperty("user.dir")+pathJRXML,
                        System.getProperty("user.dir")+pathJASPER
                );
            jpReporte = JasperFillManager.fillReport(System.getProperty("user.dir")+pathJASPER, mapa,dsReporte);
            jvReporte = new JasperViewer(jpReporte, false);
            jvReporte.setTitle("Reporte Personal");
            jvReporte.setVisible(true);
            
        } catch (JRException ex) {
            Logger.getLogger(UsuarioModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void CargarTablaUsuarios(JTable Table) {
        
        String columnTitle[] = {"ID USUARIO","N°","NOMBRES","APELLIDOS","ROL"};
        
        ArrayList<UsuarioEntity> arrayUsuario;
        Object row[] = new Object[6];
        Connection con;
        
        DefaultTableModel dtmUsuario = new DefaultTableModel(null, columnTitle) {
            
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
            
        int count = 1;
        con = ClsConexion.getConection();
        
        arrayUsuario = usuarioDAL.SeleccionarUsuarios(con);
        
        for(UsuarioEntity ue : arrayUsuario) {
            
            //Solo activos
            if(ue.getPersonal_estado().equals("A")) {
                
                row[0] = ue.getPersonal_id();
                row[1] = count;
                row[2] = ue.getPersonal_nombre();
                row[3] = ue.getApellidos();
                row[4] = ue.getRol_nombre();
                
                dtmUsuario.addRow(row); 
                
                count++;
            }             
        }
        
        ClsConexion.closeConnection(con);
        
        Table.setModel(dtmUsuario);
        Table.setRowHeight(20);
        Table.removeColumn(Table.getColumnModel().getColumn(0));
        TableColumn colOrden = Table.getColumnModel().getColumn(0);colOrden.setMaxWidth(35);
        TableColumn colNombres = Table.getColumnModel().getColumn(1);colNombres.setPreferredWidth(200);//colNombres.setMaxWidth(250);
        TableColumn colApellidos = Table.getColumnModel().getColumn(2);colApellidos.setPreferredWidth(200);//colApellidos.setMaxWidth(250);
        TableColumn colRol = Table.getColumnModel().getColumn(3);colRol.setPreferredWidth(150);colRol.setMaxWidth(200);
        
        TableCellRenderer tcr =  Table.getTableHeader().getDefaultRenderer();
        ClsMisc.miRender miRender = new ClsMisc.miRender(tcr);
        
        miRender.getTableCellRendererComponent(Table, row, true, true, WIDTH, 0);
        miRender.getTableCellRendererComponent(Table, row, true, true, WIDTH, 3);
        
        
    }
    
    //cargarUsuarioDetalle
    public void CargarUsuario(JTextField FechaNacimiento, JTextField DNI, JTextField Telefono, String IdUsuario) throws ParseException {
                
        ArrayList<UsuarioEntity> arrayUsuario;
        Connection con;
        
        con = ClsConexion.getConection();
        arrayUsuario = usuarioDAL.SeleccionarUsuarioDetalle(con, IdUsuario);
        
        for(UsuarioEntity ue : arrayUsuario) {
            
            FechaNacimiento.setText(ue.getFecha_nacimientoEu());
            DNI.setText(ue.getPersonal_dni());
            Telefono.setText(ue.getPersonal_telefono());
        }
        
        ClsConexion.closeConnection(con);
    }
    
    //cargarUsuarioEditar
    public void CargarUsuario(JTextField Nombres, JTextField ApellidoPaterno, JTextField ApellidoMaterno, 
            JDateChooser FechaNacimiento, JTextField DNI, JTextField Telefono, String IdUsuario) throws ParseException {
                
        ArrayList<UsuarioEntity> arrayUsuario;
        Connection con;
        
        con = ClsConexion.getConection();
        arrayUsuario = usuarioDAL.SeleccionarUsuario(con, IdUsuario);
        
        for(UsuarioEntity ue : arrayUsuario) {
            
            Nombres.setText(ue.getPersonal_nombre());
            ApellidoPaterno.setText(ue.getPersonal_apellido_paterno());
            ApellidoMaterno.setText(ue.getPersonal_apellido_materno());
            DNI.setText(ue.getPersonal_dni());
            Telefono.setText(ue.getPersonal_telefono());
            FechaNacimiento.setDate(ue.getFecha_nacimientoDate());            
        }
        
        ClsConexion.closeConnection(con);
    }
    
    public void CargarUsuarioLogin(JTextField Usuario, JPasswordField Password, String IdUsuario) {
        
        ArrayList<UsuarioEntity> arrayUsuario;
        Connection con;
        
        con = ClsConexion.getConection();
        arrayUsuario = usuarioDAL.SeleccionarUsuarioLogin(con, IdUsuario);
        
        arrayUsuario.stream().map((ue) -> {
            Usuario.setText(ue.getPersonal_usuario());
            return ue;
        }).forEachOrdered((ue) -> {
            Password.setText(ue.getPersonal_password());
        });
        
        ClsConexion.closeConnection(con);
    }
    
    public void GuardarUsuario(JTextField Nombres, JTextField ApellidoPaterno, 
            JTextField ApellidoMaterno, JTextField DNI, JTextField Telefono,
            JDateChooser FechaNacimiento, JComboBox Rol) throws ParseException {
             
        RolEntity rolEntity = (RolEntity) Rol.getSelectedItem();
        
        String fechaEUA = ClsMisc.formatoFechaEUA(FechaNacimiento.getDate());
        String idRol = rolEntity.getRol_id();
        
        UsuarioEntity usuarioEntity = new UsuarioEntity (
        
                Nombres.getText(),
                ApellidoPaterno.getText(),
                ApellidoMaterno.getText(),
                DNI.getText(),
                Telefono.getText(),
                "",     //usuario
                "",     //password
                fechaEUA,
                "A",    //activo
                idRol
        );
        
        Connection con = ClsConexion.getConection();
        usuarioDAL.GuardarUsuario(con, usuarioEntity);
        ClsConexion.closeConnection(con);
    }
    
    public void EditarUsuarioEstado(String IdUsuario, String Estado) {
        
        Connection con = ClsConexion.getConection();
        usuarioDAL.EditarUsarioEstado(IdUsuario, Estado, con);
        ClsConexion.closeConnection(con);
    }
    
    public void EditarUsuario(JTextField Nombres, JTextField ApellidoPaterno, 
            JTextField ApellidoMaterno, JTextField DNI, JTextField Telefono,
            JDateChooser FechaNacimiento, JComboBox Rol,String IdUsuario) throws ParseException {
             
        RolEntity rolEntity = (RolEntity) Rol.getSelectedItem();
        
        String fechaEUA = ClsMisc.formatoFechaEUA(FechaNacimiento.getDate());
        String idRol = rolEntity.getRol_id();
        
        UsuarioEntity usuarioEntity = new UsuarioEntity (
        
                Nombres.getText(),
                ApellidoPaterno.getText(),
                ApellidoMaterno.getText(),
                DNI.getText(),
                Telefono.getText(),
                "",     //usuario
                "",     //password
                fechaEUA,
                "",    //activo
                idRol
        );
        
        Connection con = ClsConexion.getConection();
        usuarioDAL.EditarUsuario(con, usuarioEntity, IdUsuario);
        ClsConexion.closeConnection(con);
    }
    
    public void EditarLogin(JTextField Usuario, JPasswordField Password, String IdUsuario) {
        
        String password;
        String usuario;
                
        char[] pass = Password.getPassword();
        password = new String(pass);
        usuario = Usuario.getText();
        
        Connection con = ClsConexion.getConection();
        
        usuarioDAL.EditarUsarioLogin(IdUsuario, usuario, password, con);
        
        ClsConexion.closeConnection(con);
    }
    
    public void CargarComboRol(JComboBox CmbRol) {
        
        CmbRol.removeAllItems();
        Connection con = ClsConexion.getConection();
        ArrayList<RolEntity> arrayRol = usuarioDAL.SeleccionarRoles(con);
        
        arrayRol.stream().map(
                (r) -> new RolEntity (
                r.getRol_id(),
                r.getRol_nombre()
            )
        ).forEachOrdered((rolNombre) -> { CmbRol.addItem(rolNombre); });
        
        ClsConexion.closeConnection(con);
    }
    
    public void SeleccionarUsuarioRol(JComboBox CmbRol, String NombreRol) {
        
        //CmbRol.getModel().setSelectedItem(NombreRol);
        RolEntity rolEntity;
        for (int i = 0; i < CmbRol.getItemCount(); i++)
        {
            rolEntity = (RolEntity)CmbRol.getItemAt(i);
            if (rolEntity.getRol_nombre().equalsIgnoreCase(NombreRol))
            {
                CmbRol.setSelectedIndex(i);
                break;
            }
        }
    }
    
}
