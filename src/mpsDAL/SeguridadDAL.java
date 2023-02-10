/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mpsDAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mpsEntity.SeguridadPermisosAdministracionEntity;
import mpsEntity.SeguridadPermisosAulaEntity;
import mpsEntity.SeguridadPermisosContabilidadEntity;
import mpsEntity.SeguridadPermisosHerramientaEntity;
import mpsEntity.SeguridadPermisosMatriculaEntity;
import mpsEntity.SeguridadPermisosProductoServicioEntity;
import mpsEntity.SeguridadPermisosSeguridadEntity;
import mpsEntity.SeguridadRolEntity;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email jkevin.mv@gmail.com
 */
public class SeguridadDAL {

    
    public void GuardarRol(String Nombre, String Descripcion, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "INSERT INTO adm_rol (rol_nombre,rol_descripcion) VALUES "
                            + "(?,?)";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, Nombre);
            pst.setString(2, Descripcion);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRol(String Nombre, String Descripcion, String IdRol,Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol SET "
                        + "rol_nombre = ?, "
                        + "rol_descripcion = ? "
                        + "WHERE rol_id = ?";        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, Nombre);
            pst.setString(2, Descripcion);
            pst.setString(3, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    //////// Permisos para nuevo Rol ///////////
    
    public void GuardarRolAula(String IdRol, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "INSERT INTO adm_rol_aula (rol_id) VALUES "
                            + "(?)";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, IdRol);            
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void GuardarRolMatricula(String IdRol, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "INSERT INTO adm_rol_matricula (rol_id) VALUES "
                            + "(?)";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, IdRol);            
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void GuardarRolAdministracion(String IdRol, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "INSERT INTO adm_rol_administracion (rol_id) VALUES "
                            + "(?)";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, IdRol);            
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void GuardarRolProductoServicio(String IdRol, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "INSERT INTO adm_rol_productoservicio (rol_id) VALUES "
                            + "(?)";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, IdRol);            
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void GuardarRolContabilidad(String IdRol, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "INSERT INTO adm_rol_contabilidad (rol_id) VALUES "
                            + "(?)";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, IdRol);            
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void GuardarRolHerramienta(String IdRol, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "INSERT INTO adm_rol_herramienta (rol_id) VALUES "
                            + "(?)";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, IdRol);            
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
        
    public void GuardarRolSeguridad(String IdRol, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "INSERT INTO adm_rol_seguridad (rol_id) VALUES "
                            + "(?)";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, IdRol);            
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    ///////////////////////////////////////////
    
    public ArrayList<SeguridadRolEntity> ListarRoles(Connection con) {
        
        ArrayList<SeguridadRolEntity> arraySeguridad = new ArrayList<>();
        
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sqlQuery = "SELECT rol_id, rol_nombre, rol_descripcion FROM adm_rol "
                        + "ORDER BY rol_nombre";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            rs = pst.executeQuery();
                       
            while(rs.next())
            {
                SeguridadRolEntity seguridadEntity = new SeguridadRolEntity (
                        rs.getString("rol_id"),
                        rs.getString("rol_nombre"),
                        rs.getString("rol_descripcion")
                );                
                arraySeguridad.add(seguridadEntity);
            }
        }
        catch(SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }        
        finally {
            try 
            {
                if(pst != null) { pst.close(); }
                if(rs != null) { rs.close(); } 
                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return arraySeguridad;
    }
    
    public ArrayList<SeguridadPermisosAulaEntity> ListarPermisosAula (String IdRol, Connection con) {
     
        ArrayList<SeguridadPermisosAulaEntity> arrayPermisos = new ArrayList<>();
        
        ResultSet rs = null;
        PreparedStatement pst = null;
        
        String sqlQuery = "SELECT anecdotario, psicologia, asistencia, inasistencia, cumplimiento, requerimiento_almacen, item_consulta FROM adm_rol_aula "
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, IdRol);             
            rs = pst.executeQuery();
                       
            while(rs.next())
            {
                SeguridadPermisosAulaEntity seguridadPermisosAulaEntity = new SeguridadPermisosAulaEntity (
                        rs.getString("anecdotario"),
                        rs.getString("psicologia"),
                        rs.getString("asistencia"),
                        rs.getString("inasistencia"),
                        rs.getString("cumplimiento"),
                        rs.getString("requerimiento_almacen"),
                        rs.getString("item_consulta")
                );                
                arrayPermisos.add(seguridadPermisosAulaEntity);
            }
        }
        catch(SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }        
        finally {
            try 
            {
                if(pst != null) { pst.close(); }
                if(rs != null) { rs.close(); } 
                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return arrayPermisos;
    }
    
    public ArrayList<SeguridadPermisosMatriculaEntity> ListarPermisosMatricula (String IdRol, Connection con) {
     
        ArrayList<SeguridadPermisosMatriculaEntity> arrayPermisos = new ArrayList<>();
        
        ResultSet rs = null;
        PreparedStatement pst = null;
        
        String sqlQuery = "SELECT matricula, huella, nivel, periodo, aula FROM adm_rol_matricula "
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, IdRol);             
            rs = pst.executeQuery();
                       
            while(rs.next())
            {
                SeguridadPermisosMatriculaEntity seguridadPermisosMatriculaEntity = new SeguridadPermisosMatriculaEntity (
                        rs.getString("matricula"),
                        rs.getString("huella"),
                        rs.getString("nivel"),
                        rs.getString("periodo"),
                        rs.getString("aula")                        
                );                
                arrayPermisos.add(seguridadPermisosMatriculaEntity);
            }
        }
        catch(SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }        
        finally {
            try 
            {
                if(pst != null) { pst.close(); }
                if(rs != null) { rs.close(); } 
                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return arrayPermisos;
    }
    
    public ArrayList<SeguridadPermisosAdministracionEntity> ListarPermisosAdministracion (String IdRol, Connection con) {
     
        ArrayList<SeguridadPermisosAdministracionEntity> arrayPermisos = new ArrayList<>();
        
        ResultSet rs = null;
        PreparedStatement pst = null;
        
        String sqlQuery = "SELECT lonchera, anecdotario, psicologia, psicologia_editar, asistencia, asistencia_consulta, "
                        + " cumplimiento, almacen, item, requerimiento, item_consulta FROM adm_rol_administracion "
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, IdRol);             
            rs = pst.executeQuery();
                       
            while(rs.next())
            {
                SeguridadPermisosAdministracionEntity seguridadPermisosAdministracionEntity = new SeguridadPermisosAdministracionEntity (
                        rs.getString("lonchera"),
                        rs.getString("anecdotario"),
                        rs.getString("psicologia"),
                        rs.getString("psicologia_editar"),
                        rs.getString("asistencia"),
                        rs.getString("asistencia_consulta"),
                        rs.getString("cumplimiento"),
                        rs.getString("almacen"),
                        rs.getString("item"),
                        rs.getString("requerimiento"),
                        rs.getString("item_consulta")
                );                
                arrayPermisos.add(seguridadPermisosAdministracionEntity);
            }
        }
        catch(SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }        
        finally {
            try 
            {
                if(pst != null) { pst.close(); }
                if(rs != null) { rs.close(); } 
                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return arrayPermisos;
    }
    
    public ArrayList<SeguridadPermisosProductoServicioEntity> ListarPermisosProductoServicio (String IdRol, Connection con) {
     
        ArrayList<SeguridadPermisosProductoServicioEntity> arrayPermisos = new ArrayList<>();
        
        ResultSet rs = null;
        PreparedStatement pst = null;
        
        String sqlQuery = "SELECT producto_mantenimiento, producto_asignar, servicio_mantenimiento, servicio_asignar FROM adm_rol_productoservicio "
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, IdRol);             
            rs = pst.executeQuery();
                       
            while(rs.next())
            {
                SeguridadPermisosProductoServicioEntity seguridadPermisosProductoServicioEntity = new SeguridadPermisosProductoServicioEntity (
                        rs.getString("producto_mantenimiento"),
                        rs.getString("producto_asignar"),
                        rs.getString("servicio_mantenimiento"),
                        rs.getString("servicio_asignar")                        
                );                
                arrayPermisos.add(seguridadPermisosProductoServicioEntity);
            }
        }
        catch(SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }        
        finally {
            try 
            {
                if(pst != null) { pst.close(); }
                if(rs != null) { rs.close(); } 
                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return arrayPermisos;
    }
    
    public ArrayList<SeguridadPermisosContabilidadEntity> ListarPermisosContabilidad (String IdRol, Connection con) {
     
        ArrayList<SeguridadPermisosContabilidadEntity> arrayPermisos = new ArrayList<>();
        
        ResultSet rs = null;
        PreparedStatement pst = null;
        
        String sqlQuery = "SELECT pago, pago_editar, boleta_anular, historial_autorizacion, deuda_monto, "
                        + "ingreso_egreso, egreso, egreso_editar, boleta_imprimir FROM adm_rol_contabilidad "
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, IdRol);             
            rs = pst.executeQuery();
                       
            while(rs.next())
            {
                SeguridadPermisosContabilidadEntity seguridadPermisosContabilidadEntity = new SeguridadPermisosContabilidadEntity (
                        rs.getString("pago"),
                        rs.getString("pago_editar"),
                        rs.getString("boleta_anular"),
                        rs.getString("historial_autorizacion"),
                        rs.getString("deuda_monto"),
                        rs.getString("ingreso_egreso"),
                        rs.getString("egreso"),
                        rs.getString("egreso_editar"),
                        rs.getString("boleta_imprimir")
                );                
                arrayPermisos.add(seguridadPermisosContabilidadEntity);
            }
        }
        catch(SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }        
        finally {
            try 
            {
                if(pst != null) { pst.close(); }
                if(rs != null) { rs.close(); } 
                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return arrayPermisos;
    }
    
    public ArrayList<SeguridadPermisosHerramientaEntity> ListarPermisosHerramientas (String IdRol, Connection con) {
     
        ArrayList<SeguridadPermisosHerramientaEntity> arrayPermisos = new ArrayList<>();
        
        ResultSet rs = null;
        PreparedStatement pst = null;
        
        String sqlQuery = "SELECT codigo_boleta_ajuste, lonchera_mes_ajuste, lonchera_servicio_ajuste FROM adm_rol_herramienta "
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, IdRol);             
            rs = pst.executeQuery();
                       
            while(rs.next())
            {
                SeguridadPermisosHerramientaEntity seguridadPermisosHerramientaEntity = new SeguridadPermisosHerramientaEntity (
                        rs.getString("codigo_boleta_ajuste"),
                        rs.getString("lonchera_mes_ajuste"),
                        rs.getString("lonchera_servicio_ajuste")                        
                );                
                arrayPermisos.add(seguridadPermisosHerramientaEntity);
            }
        }
        catch(SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }        
        finally {
            try 
            {
                if(pst != null) { pst.close(); }
                if(rs != null) { rs.close(); } 
                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return arrayPermisos;
    }
    
    public ArrayList<SeguridadPermisosSeguridadEntity> ListarPermisosSeguridad (String IdRol, Connection con) {
     
        ArrayList<SeguridadPermisosSeguridadEntity> arrayPermisos = new ArrayList<>();
        
        ResultSet rs = null;
        PreparedStatement pst = null;
        
        String sqlQuery = "SELECT rol, usuario FROM adm_rol_seguridad "
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, IdRol);             
            rs = pst.executeQuery();
                       
            while(rs.next())
            {
                SeguridadPermisosSeguridadEntity seguridadPermisosSeguridadEntity = new SeguridadPermisosSeguridadEntity (
                        rs.getString("rol"),
                        rs.getString("usuario")                      
                );                
                arrayPermisos.add(seguridadPermisosSeguridadEntity);
            }
        }
        catch(SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }        
        finally {
            try 
            {
                if(pst != null) { pst.close(); }
                if(rs != null) { rs.close(); } 
                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return arrayPermisos;
    }
    
    //este por si acaso
    public void EditarRolAula(String IdRol, int anecdotario, int psicologia, int asistencia, 
            int inasistencia, int cumplimiento, int requerimiento_almacen, int item_consulta, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_aula SET "
                        + "anecdotario = ?, "
                        + "psicologia = ?, "
                        + "asistencia = ?, " 
                        + "inasistencia = ?, "
                        + "cumplimiento = ?, "
                        + "requerimiento_almacen = ?, "
                        + "item_consulta = ? "
                        + "WHERE rol_id = ?";

        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, anecdotario);
            pst.setInt(2, psicologia);
            pst.setInt(3, asistencia);
            pst.setInt(4, inasistencia);
            pst.setInt(5, cumplimiento);
            pst.setInt(6, requerimiento_almacen);
            pst.setInt(7, item_consulta);
            pst.setString(8, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
        
    /////////////Editar Permisos individuales aula.////////////
    
    public void EditarRolAulaAnecdotario(String IdRol, int anecdotario, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_aula SET "
                        + "anecdotario = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, anecdotario);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRolAulaPsicologia(String IdRol, int psicologia, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_aula SET "
                        + "psicologia = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, psicologia);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRolAulaAsistencia(String IdRol, int asistencia, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_aula SET "
                        + "asistencia = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, asistencia);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRolAulaInasistencia(String IdRol, int inasistencia, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_aula SET "
                        + "inasistencia = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, inasistencia);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRolAulaCumplimiento(String IdRol, int cumplimiento, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_aula SET "
                        + "cumplimiento = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, cumplimiento);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRolAulaRequerimientoAlmacen(String IdRol, int requerimiento_almacen, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_aula SET "
                        + "requerimiento_almacen = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, requerimiento_almacen);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRolAulaItemConsulta(String IdRol, int item_consulta, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_aula SET "
                        + "item_consulta = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, item_consulta);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    /////////////Editar Permisos individuales matricula.////////////
    
    public void EditarRolMatriculaPrincipal(String IdRol, int matricula, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_matricula SET "
                        + "matricula = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, matricula);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRolMatriculaHuella(String IdRol, int huella, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_matricula SET "
                        + "huella = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, huella);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRolMatriculaNivel(String IdRol, int nivel, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_matricula SET "
                        + "nivel = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, nivel);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRolMatriculaPeriodo(String IdRol, int periodo, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_matricula SET "
                        + "periodo = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, periodo);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRolMatriculaAula(String IdRol, int aula, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_matricula SET "
                        + "aula = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, aula);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    /////////////Editar Permisos individuales Administracion.////////////
    
    public void EditarRolAdministracionLoncheraCantidad(String IdRol, int Lonchera, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_administracion SET "
                        + "lonchera = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, Lonchera);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRolAdministracionAnecdotario(String IdRol, int Anecdotario, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_administracion SET "
                        + "anecdotario = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, Anecdotario);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRolAdministracionPsicologia(String IdRol, int Psicologia, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_administracion SET "
                        + "psicologia = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, Psicologia);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRolAdministracionPsicologiaEditar(String IdRol, int PsicologiaEditar, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_administracion SET "
                        + "psicologia_editar = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, PsicologiaEditar);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRolAdministracionAsistencia(String IdRol, int Asistencia, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_administracion SET "
                        + "asistencia = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, Asistencia);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRolAdministracionAsistenciaConsulta(String IdRol, int AsistenciaConsulta, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_administracion SET "
                        + "asistencia_consulta = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, AsistenciaConsulta);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRolAdministracionCumplimiento(String IdRol, int Cumplimiento, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_administracion SET "
                        + "cumplimiento = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, Cumplimiento);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRolAdministracionAlmacen(String IdRol, int Almacen, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_administracion SET "
                        + "almacen = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, Almacen);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRolAdministracionItem(String IdRol, int Item, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_administracion SET "
                        + "item = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, Item);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRolAdministracionRequerimiento(String IdRol, int Requerimiento, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_administracion SET "
                        + "requerimiento = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, Requerimiento);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRolAdministracionItemConsulta(String IdRol, int ItemConsulta, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_administracion SET "
                        + "item_consulta = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, ItemConsulta);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    /////////////Editar Permisos individuales Producto y Servicio.////////////
    
    public void EditarRolProductoServicioProductoMantenimiento(String IdRol, int ProductoMantenimiento, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_productoservicio SET "
                        + "producto_mantenimiento = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, ProductoMantenimiento);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRolProductoServicioProductoAsignar(String IdRol, int ProductoAsignar, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_productoservicio SET "
                        + "producto_asignar = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, ProductoAsignar);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRolProductoServicioServicioMantenimiento(String IdRol, int ServicioMantenimiento, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_productoservicio SET "
                        + "servicio_mantenimiento = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, ServicioMantenimiento);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRolProductoServicioServicioAsignar(String IdRol, int ServicioAsignar, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_productoservicio SET "
                        + "servicio_asignar = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, ServicioAsignar);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    /////////////Editar Permisos individuales Contabilidad.////////////
    
    public void EditarRolContabilidadPagos(String IdRol, int Pago, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_contabilidad SET "
                        + "pago = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, Pago);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRolContabilidadPagoEditar(String IdRol, int PagoEditar, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_contabilidad SET "
                        + "pago_editar = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, PagoEditar);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRolContabilidadBoletaAnular(String IdRol, int BoletaAnular, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_contabilidad SET "
                        + "boleta_anular = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, BoletaAnular);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRolContabilidadHistorialAutorizacion(String IdRol, int HistorialAutorizacion, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_contabilidad SET "
                        + "historial_autorizacion = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, HistorialAutorizacion);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRolContabilidadDeudaMonto(String IdRol, int DeudaMonto, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_contabilidad SET "
                        + "deuda_monto = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, DeudaMonto);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRolContabilidadIngresoEgreso(String IdRol, int IngresoEgreso, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_contabilidad SET "
                        + "ingreso_egreso = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, IngresoEgreso);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRolContabilidadEgreso(String IdRol, int Egreso, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_contabilidad SET "
                        + "egreso = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, Egreso);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRolContabilidadEgresoEditar(String IdRol, int EgresoEditar, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_contabilidad SET "
                        + "egreso_editar = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, EgresoEditar);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRolContabilidadBoletaImprimir(String IdRol, int BoletaImprimir, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_contabilidad SET "
                        + "boleta_imprimir = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, BoletaImprimir);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    /////////////Editar Permisos individuales Herramientas.////////////
    
    public void EditarRolHerramientaCodigoBoletaAjuste(String IdRol, int CodigoBoletaAjuste, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_herramienta SET "
                        + "codigo_boleta_ajuste = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, CodigoBoletaAjuste);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRolHerramientaLoncheraMesAjuste(String IdRol, int LoncheraMesAjuste, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_herramienta SET "
                        + "lonchera_mes_ajuste = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, LoncheraMesAjuste);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRolHerramientaLoncheraServicioAjuste(String IdRol, int LoncheraServicioAjuste, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_herramienta SET "
                        + "lonchera_servicio_ajuste = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, LoncheraServicioAjuste);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    /////////////Editar Permisos individuales Seguridad.////////////
    
    public void EditarRolSeguridadRol(String IdRol, int Rol, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_seguridad SET "
                        + "rol = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, Rol);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void EditarRolSeguridadUsuario(String IdRol, int Usuario, Connection con) {
        
        PreparedStatement pst = null;
        String sqlQuery = "UPDATE adm_rol_seguridad SET "
                        + "usuario = ? "                        
                        + "WHERE rol_id = ?";
        
        try {
            
            pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, Usuario);            
            pst.setString(2, IdRol);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if(pst != null) {
                    pst.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(SeguridadDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
