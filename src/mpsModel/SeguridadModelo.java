/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mpsModel;

import PckConexion.ClsConexion;
import static java.awt.image.ImageObserver.WIDTH;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import mpsDAL.SeguridadDAL;
import mpsEntity.SeguridadPermisosAdministracionEntity;
import mpsEntity.SeguridadPermisosAulaEntity;
import mpsEntity.SeguridadPermisosContabilidadEntity;
import mpsEntity.SeguridadPermisosHerramientaEntity;
import mpsEntity.SeguridadPermisosMatriculaEntity;
import mpsEntity.SeguridadPermisosProductoServicioEntity;
import mpsEntity.SeguridadPermisosSeguridadEntity;
import mpsEntity.SeguridadRolEntity;
import prymatricula.ClsMisc;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email jkevin.mv@gmail.com
 */
public class SeguridadModelo {
    
    SeguridadDAL seguridadDAL = new SeguridadDAL();
    
    public void CargarTablaSeguridadRol(JTable Tabla) {
        //<<alt60 alt62>>
        String columnTitle[] = {"ID ROL","N°", "ROL", "DESCRIPCION"};
        ArrayList<SeguridadRolEntity> arraySeguridad;
        Object row[] = new Object[4];        
        Connection con;
                
        DefaultTableModel dtmRol = new DefaultTableModel(null,columnTitle) {
        
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
            
        };
        
        int count = 1;
        con = ClsConexion.getConection();
        arraySeguridad = seguridadDAL.ListarRoles(con);
        
        for(SeguridadRolEntity se : arraySeguridad) {
            
            row[0] = se.getRol_id();
            row[1] = count;
            row[2] = se.getRol_nombre();
            row[3] = se.getRol_descripcion();
            dtmRol.addRow(row);
            count++;
        }
        
        ClsConexion.closeConnection(con);
        Tabla.setModel(dtmRol);
        Tabla.setRowHeight(20);
        
        Tabla.removeColumn(Tabla.getColumnModel().getColumn(0));
        
        TableColumn colOrden = Tabla.getColumnModel().getColumn(0);colOrden.setMaxWidth(35);
        TableColumn colRol = Tabla.getColumnModel().getColumn(1);colRol.setMaxWidth(120);colRol.setPreferredWidth(110);
        
        TableCellRenderer tcr =  Tabla.getTableHeader().getDefaultRenderer();
        ClsMisc.miRender miRender = new ClsMisc.miRender(tcr);
        //Tabla.setDefaultRenderer(Object.class, new Render());
        
        miRender.getTableCellRendererComponent(Tabla, row, true, true, WIDTH, 0);        
        
    }
    
    public void CargarCheckBoxPermisosAula(ArrayList<JCheckBox> aulaCheckBoxes, String IdRol) {
        
        Connection con = ClsConexion.getConection();
        Object dato[] = new Object[7];
        ArrayList<SeguridadPermisosAulaEntity> arrayPermisos = seguridadDAL.ListarPermisosAula(IdRol, con);
        
        for(SeguridadPermisosAulaEntity pae : arrayPermisos) {
            
            dato[0] = pae.getAnecdotario();
            dato[1] = pae.getPsicologia();
            dato[2] = pae.getAsistencia();
            dato[3] = pae.getInasistencia();
            dato[4] = pae.getCumplimiento();
            dato[5] = pae.getRequerimiento_almacen();
            dato[6] = pae.getItem_consulta();
            
        }
        
        int i = 0;
        for(JCheckBox c : aulaCheckBoxes) {
            
            if(dato[i].equals("1")) {
                c.setSelected(true);
            }
            else {
                c.setSelected(false);
            }
            
            i++;
        }
        
    }
    
    public void CargarCheckBoxPermisosMatricula(ArrayList<JCheckBox> matriculaCheckBoxes, String IdRol) {
        
        Connection con = ClsConexion.getConection();
        Object dato[] = new Object[5];
        ArrayList<SeguridadPermisosMatriculaEntity> arrayPermisos = seguridadDAL.ListarPermisosMatricula(IdRol, con);
        
        for(SeguridadPermisosMatriculaEntity pae : arrayPermisos) {
            
            dato[0] = pae.getMatricula();
            dato[1] = pae.getHuella();
            dato[2] = pae.getNivel();
            dato[3] = pae.getPeriodo();
            dato[4] = pae.getAula();            
            
        }
        
        int i = 0;
        for(JCheckBox c : matriculaCheckBoxes) {
            
            if(dato[i].equals("1")) {
                c.setSelected(true);
            }
            else {
                c.setSelected(false);
            }
            
            i++;
        }
        
    }
    
    public void CargarCheckBoxPermisosAdministracion(ArrayList<JCheckBox> administracionCheckBoxes, String IdRol) {
        
        Connection con = ClsConexion.getConection();
        Object dato[] = new Object[11];
        ArrayList<SeguridadPermisosAdministracionEntity> arrayPermisos = seguridadDAL.ListarPermisosAdministracion(IdRol, con);
        
        for(SeguridadPermisosAdministracionEntity pae : arrayPermisos) {
            
            dato[0] = pae.getLonchera();
            dato[1] = pae.getAnecdotario();
            dato[2] = pae.getPsicologia();
            dato[3] = pae.getPsicologia_editar();
            dato[4] = pae.getAsistencia();
            dato[5] = pae.getAsistencia_consulta();
            dato[6] = pae.getCumplimiento();
            dato[7] = pae.getAlmacen();
            dato[8] = pae.getItem();
            dato[9] = pae.getRequerimiento();
            dato[10] = pae.getItem_consulta();
            
        }
        
        int i = 0;
        for(JCheckBox c : administracionCheckBoxes) {
            
            if(dato[i].equals("1")) {
                c.setSelected(true);
            }
            else {
                c.setSelected(false);
            }
            
            i++;
        }
        
    }
    
    public void CargarCheckBoxPermisosProductoServicio(ArrayList<JCheckBox> productoservicioCheckBoxes, String IdRol) {
        
        Connection con = ClsConexion.getConection();
        Object dato[] = new Object[4];
        ArrayList<SeguridadPermisosProductoServicioEntity> arrayPermisos = seguridadDAL.ListarPermisosProductoServicio(IdRol, con);
        
        for(SeguridadPermisosProductoServicioEntity pae : arrayPermisos) {
            
            dato[0] = pae.getProducto_mantenimiento();
            dato[1] = pae.getProducto_asignar();
            dato[2] = pae.getServicio_mantenimiento();
            dato[3] = pae.getServicio_asignar();            
            
        }
        
        int i = 0;
        for(JCheckBox c : productoservicioCheckBoxes) {
            
            if(dato[i].equals("1")) {
                c.setSelected(true);
            }
            else {
                c.setSelected(false);
            }
            
            i++;
        }
        
    }
    
    public void CargarCheckBoxPermisosContabilidad(ArrayList<JCheckBox> contabilidadCheckBoxes, String IdRol) {
        
        Connection con = ClsConexion.getConection();
        Object dato[] = new Object[9];
        ArrayList<SeguridadPermisosContabilidadEntity> arrayPermisos = seguridadDAL.ListarPermisosContabilidad(IdRol, con);
        
        for(SeguridadPermisosContabilidadEntity pce : arrayPermisos) {
            
            dato[0] = pce.getPago();
            dato[1] = pce.getPago_editar();
            dato[2] = pce.getBoleta_anular();
            dato[3] = pce.getHistorial_autorizacion();
            dato[4] = pce.getDeuda_monto();
            dato[5] = pce.getIngreso_egreso();
            dato[6] = pce.getEgreso();
            dato[7] = pce.getEgreso_editar();
            dato[8] = pce.getBoleta_imprimir();
            
        }
        
        int i = 0;
        for(JCheckBox c : contabilidadCheckBoxes) {
            
            if(dato[i].equals("1")) {
                c.setSelected(true);
            }
            else {
                c.setSelected(false);
            }
            
            i++;
        }
        
    }
    
    public void CargarCheckBoxPermisosHerramientas(ArrayList<JCheckBox> herramientasCheckBoxes, String IdRol) {
        
        Connection con = ClsConexion.getConection();
        Object dato[] = new Object[3];
        ArrayList<SeguridadPermisosHerramientaEntity> arrayPermisos = seguridadDAL.ListarPermisosHerramientas(IdRol, con);
        
        for(SeguridadPermisosHerramientaEntity phe : arrayPermisos) {
            
            dato[0] = phe.getCodigo_boleta_ajuste();
            dato[1] = phe.getLonchera_mes_ajuste();
            dato[2] = phe.getLonchera_servicio_ajuste();            
                        
        }
        
        int i = 0;
        for(JCheckBox c : herramientasCheckBoxes) {
            
            if(dato[i].equals("1")) {
                c.setSelected(true);
            }
            else {
                c.setSelected(false);
            }
            
            i++;
        }
        
    }
    
    public void CargarCheckBoxPermisosSeguridad(ArrayList<JCheckBox> seguridadCheckBoxes, String IdRol) {
        
        Connection con = ClsConexion.getConection();
        Object dato[] = new Object[2];
        ArrayList<SeguridadPermisosSeguridadEntity> arrayPermisos = seguridadDAL.ListarPermisosSeguridad(IdRol, con);
        
        for(SeguridadPermisosSeguridadEntity pse : arrayPermisos) {
            
            dato[0] = pse.getRol();
            dato[1] = pse.getUsuario();
                        
        }
        
        int i = 0;
        for(JCheckBox c : seguridadCheckBoxes) {
            
            if(dato[i].equals("1")) {
                c.setSelected(true);
            }
            else {
                c.setSelected(false);
            }
            
            i++;
        }
        
    }
    
    
}
