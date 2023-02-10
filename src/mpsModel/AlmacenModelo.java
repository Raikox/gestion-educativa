/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mpsModel;


import PckConexion.ClsConexion;
import PckRenderTabla.GeneralRender;
import PckRenderTabla.GeneralRender.MiRender;
import PckRenderTabla.Render;
import static java.awt.image.ImageObserver.WIDTH;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import mpsDAL.AlmacenDAL;
import mpsDAL.ItemDAL;
import mpsDAL.RequerimientoDAL;
import mpsEntity.AlmacenEntity;
import mpsEntity.ItemEntity;
import mpsEntity.RequerimientoEntity;
import prymatricula.ClsMisc;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email jkevin.mv@gmail.com
 */
public class AlmacenModelo {
   
    public ArrayList<Object> MostarComboAlmacenesAula(JComboBox _Combo, int _IdAula)
    {
        ArrayList<Object> arrayIdAlmacen = new ArrayList<>();
        ArrayList<AlmacenEntity> arrayAlmacen;
        DefaultComboBoxModel dcm = new DefaultComboBoxModel();
        AlmacenDAL almacenDAL = new AlmacenDAL(); 
        int count=0;
        
        dcm.removeAllElements();        
        arrayAlmacen = almacenDAL.ListarAlmacenes();
        
        for(AlmacenEntity ae : arrayAlmacen)
        {
            if(ae.getId_seccion() == _IdAula)
            {                
                arrayIdAlmacen.add(ae.getAlmacen_id());
                dcm.addElement(ae.getAlmacen_nombre());
                count++;
            }            
        }
        _Combo.setModel(dcm);
        
        return arrayIdAlmacen;
    }
    
    public void MostrarTablaAlmacenAdministracionBotones(JTable _Tabla,int idPeriodo)
    {        
        String titulos[] = {"ID ALMACEN","ID AULA","N°","ALMACEN","AULA","",""};
        ArrayList<AlmacenEntity> arrayAlmacen;
        Object fila[] = new Object[7];
        AlmacenDAL almacenDAL = new AlmacenDAL();        
        
        DefaultTableModel dtm = new DefaultTableModel(null,titulos)
        {
            @Override
            public boolean isCellEditable(int rowIndex,int columnIndex)
            {return false;}
        };  
        int count = 1;
        JButton btnEditar = new JButton("");
        JButton btnEliminar = new JButton("");
        
        btnEditar.setName("btnEditar");        
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/edit_black.png")));        
                 
        btnEliminar.setName("btnEliminar");
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/delete_black.png")));     
        
        arrayAlmacen = almacenDAL.ListarAlmacenesPorPeriodo(idPeriodo);
        for(AlmacenEntity ae : arrayAlmacen)
        {
            fila[0] = ae.getAlmacen_id();
            fila[1] = ae.getId_seccion();
            fila[2] = count;
            fila[3] = " "+ae.getAlmacen_nombre();
            fila[4] = ae.getNombre_seccion();
            fila[5] = btnEditar;
            fila[6] = btnEliminar;
            dtm.addRow(fila);
            count++;
        }
        _Tabla.setModel(dtm);
        _Tabla.setRowHeight(20);        
        
        _Tabla.removeColumn(_Tabla.getColumnModel().getColumn(0));
        _Tabla.removeColumn(_Tabla.getColumnModel().getColumn(0));
        
        TableColumn colOrden = _Tabla.getColumnModel().getColumn(0);colOrden.setMaxWidth(35);
        
        TableCellRenderer tcr =  _Tabla.getTableHeader().getDefaultRenderer();
        GeneralRender.MiRender miRender = new GeneralRender.MiRender(tcr);
        _Tabla.setDefaultRenderer(Object.class, new Render());
        
        miRender.getTableCellRendererComponent(_Tabla, fila, true, true, WIDTH, 0);
        miRender.getTableCellRendererComponent(_Tabla, fila, true, true, WIDTH, 2);
       
        
        
    }    
    
    public void MostrarTablaAlmacenAdministracionPeriodo(JTable _Tabla, int _IdPeriodo)
    {        
        String titulos[] = {"ID ALMACEN","ID AULA","ALMACEN","AULA"};
        ArrayList<AlmacenEntity> arrayAlmacen;
        Object fila[] = new Object[6];
        AlmacenDAL almacenDAL = new AlmacenDAL();        
        
        DefaultTableModel dtm = new DefaultTableModel(null,titulos)
        {
            @Override
            public boolean isCellEditable(int rowIndex,int columnIndex)
            {return false;}
        };  
        
        JButton btnEditar = new JButton("");
        JButton btnEliminar = new JButton("");
        
        btnEditar.setName("btnEditar");        
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/edit_black.png")));        
                 
        btnEliminar.setName("btnEliminar");
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/delete_black.png")));     
        
        arrayAlmacen = almacenDAL.ListarAlmacenesPeriodo(_IdPeriodo);
        for(AlmacenEntity ae : arrayAlmacen)
        {
            fila[0] = ae.getAlmacen_id();
            fila[1] = ae.getId_seccion();
            fila[2] = ae.getAlmacen_nombre();
            fila[3] = ae.getNombre_seccion();
            fila[4] = ae.getId_periodo();
            
            dtm.addRow(fila);
        }
        _Tabla.setModel(dtm);
        _Tabla.setRowHeight(28);        
        
        TableCellRenderer tcr =  _Tabla.getTableHeader().getDefaultRenderer();
        MiRender miRender = new MiRender(tcr);
        _Tabla.setDefaultRenderer(Object.class, miRender);
        miRender.getTableCellRendererComponent(_Tabla, fila, true, true, WIDTH, 0);
        _Tabla.removeColumn(_Tabla.getColumnModel().getColumn(0));
        _Tabla.removeColumn(_Tabla.getColumnModel().getColumn(0));
        TableColumn BotonEditar = _Tabla.getColumnModel().getColumn(0);
        BotonEditar.setPreferredWidth(200);
        
    }
    
    public void MostrarTablaAlmacenAdministracionItemsBotones(JTable _Tabla, boolean _Consumo)
    {   
        //Primeros 4 items se ocultan
        String titulos[] = {"ID ITEM","ID ALMACEN","DESCRIPCION","N°","NOMBRE","U. MEDIDA","U. ASIGNADAS","U. UTILIZADAS","U. RESTANTES","ALMACEN","",""};
        ArrayList<ItemEntity> arrayItems;
        Object fila[] = new Object[13];
        ItemDAL itemDAL = new ItemDAL();        
        int count = 1;
        int itemId=0;
        int unidadesAsignadas=0;
        int unidadesUtilizadas=0;
        int unidadesRestantes=0;
        
        DefaultTableModel dtm = new DefaultTableModel(null,titulos)
        {
            @Override
            public boolean isCellEditable(int rowIndex,int columnIndex)
            {return false;}
        };  
        
        JButton btnEditar = new JButton("");
        JButton btnEliminar = new JButton("");
        
        btnEditar.setName("btnEditar");        
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/edit_black.png")));        
                 
        btnEliminar.setName("btnEliminar");
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/delete_black.png")));     
        
        arrayItems = itemDAL.ListarItemsSinAsignar(_Consumo);
        
        for(ItemEntity ie : arrayItems)
        {
            itemId = ie.getItem_id();
            unidadesAsignadas = ie.getItem_stock();
            unidadesUtilizadas = ObtenerUnidadesUtilizadas(itemId);
            unidadesRestantes = unidadesAsignadas - unidadesUtilizadas;
            
            fila[0] = itemId;
            fila[1] = ie.getAlmacen_id();
            fila[2] = ie.getItem_descripcion();
            fila[3] = count;
            fila[4] = " "+ie.getItem_nombre();
            fila[5] = ie.getItem_medida();
            fila[6] = unidadesAsignadas;
            fila[7] = unidadesUtilizadas;
            fila[8] = unidadesRestantes;
            fila[9] = "SIN ASIGNAR";
            fila[10] = btnEditar;
            fila[11] = btnEliminar;
            dtm.addRow(fila);
            count++;
        }
        
        _Tabla.setModel(dtm);
        _Tabla.setRowHeight(20);        
       
        _Tabla.removeColumn(_Tabla.getColumnModel().getColumn(0));
        _Tabla.removeColumn(_Tabla.getColumnModel().getColumn(0));
        _Tabla.removeColumn(_Tabla.getColumnModel().getColumn(0));
        
        TableColumn colOrden = _Tabla.getColumnModel().getColumn(0);colOrden.setPreferredWidth(35);
        TableColumn colItem = _Tabla.getColumnModel().getColumn(1);colItem.setPreferredWidth(230);
        TableColumn colMedida = _Tabla.getColumnModel().getColumn(2);colMedida.setPreferredWidth(90);
        TableColumn colAsignadas = _Tabla.getColumnModel().getColumn(3);colAsignadas.setPreferredWidth(100);
        TableColumn colUtilizadas = _Tabla.getColumnModel().getColumn(4);colUtilizadas.setPreferredWidth(100);
        TableColumn colRestantes = _Tabla.getColumnModel().getColumn(5);colRestantes.setPreferredWidth(100);
        TableColumn cAlmacen = _Tabla.getColumnModel().getColumn(6);cAlmacen.setPreferredWidth(200);
        TableColumn cBotonEditar =_Tabla.getColumnModel().getColumn(7);cBotonEditar.setPreferredWidth(100);
        TableColumn cBotonEliminar =_Tabla.getColumnModel().getColumn(8);cBotonEliminar.setPreferredWidth(100);
        
        TableCellRenderer tcr =  _Tabla.getTableHeader().getDefaultRenderer();
        GeneralRender.MiRender miRender = new GeneralRender.MiRender(tcr);            
        _Tabla.setDefaultRenderer(Object.class, new Render());
        miRender.getTableCellRendererComponent(_Tabla, fila, true, true, WIDTH, 0);
        miRender.getTableCellRendererComponent(_Tabla, fila, true, true, WIDTH, 2);
        miRender.getTableCellRendererComponent(_Tabla, fila, true, true, WIDTH, 3);
        miRender.getTableCellRendererComponent(_Tabla, fila, true, true, WIDTH, 4);
        miRender.getTableCellRendererComponent(_Tabla, fila, true, true, WIDTH, 5);
        
        _Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
    }
    
    private int ObtenerUnidadesUtilizadas(int xIdItem)
    {
        Connection con = ClsConexion.getConection();
        ResultSet rsItem;
        PreparedStatement st;
        int cantidad=0;
        try 
        {
            st = con.prepareStatement("select ai.item_nombre, ai.item_id, sum(ri.ritem_cantidad) as 'cantidad_suma'\n" +
                            "from mps_requerimiento_item as ri \n" +
                            "inner join mps_almacen_item as ai on ri.item_id = ai.item_id\n" +
                            "where ri.item_id = ?");
            st.setInt(1, xIdItem);
            rsItem = st.executeQuery();
            
            while(rsItem.next())
            {
                cantidad = rsItem.getInt("cantidad_suma");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AlmacenModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        ClsConexion.closeConnection(con);
        return cantidad;
    }
    
    public void MostrarTablaAlmacenItemsBusqueda(JTable Table, int IdAlmacen, boolean EsConsumo, String Busqueda) {
        
        String titulos[] = {"ID ITEM","N°","ITEM","STOCK","U. MEDIDA","DESCRIPCION"};
        ArrayList<ItemEntity> arrayItems;
        Object fila[] = new Object[8];
        ItemDAL itemDAL = new ItemDAL();        
        int count = 1;
        
        DefaultTableModel dtm = new DefaultTableModel(null,titulos)
        {
            @Override
            public boolean isCellEditable(int rowIndex,int columnIndex)
            {return false;}
        };
        
        arrayItems = itemDAL.ListarItemsBusqueda(EsConsumo, Busqueda);
        for(ItemEntity ie : arrayItems)
        {
            if(ie.getAlmacen_id() == IdAlmacen)
            {
                fila[0] = ie.getItem_id();
                fila[1] = count;
                fila[2] = ie.getItem_nombre();
                fila[3] = ie.getItem_stock();
                fila[4] = ie.getItem_medida();
                fila[5] = ie.getItem_descripcion();           

                dtm.addRow(fila);
                count++;
            }            
        }
        Table.setModel(dtm);
        Table.setRowHeight(20); 
        
        Table.removeColumn(Table.getColumnModel().getColumn(0));        
        TableColumn colOrden = Table.getColumnModel().getColumn(0);
        colOrden.setPreferredWidth(30);
        TableColumn colNombre = Table.getColumnModel().getColumn(1);
        colNombre.setPreferredWidth(150);
        TableColumn colStock = Table.getColumnModel().getColumn(2);
        colStock.setPreferredWidth(60);
        TableColumn colMedida = Table.getColumnModel().getColumn(3);
        colMedida.setPreferredWidth(90);
        TableColumn colDescripcion = Table.getColumnModel().getColumn(4);
        colDescripcion.setPreferredWidth(200);
        
        TableCellRenderer tcr =  Table.getTableHeader().getDefaultRenderer();
        ClsMisc.miRender mr = new ClsMisc.miRender(tcr);
        mr.getTableCellRendererComponent(Table, fila, true, true, WIDTH, 0);
        mr.getTableCellRendererComponent(Table, fila, true, true, WIDTH, 2);
        mr.getTableCellRendererComponent(Table, fila, true, true, WIDTH, 3);
    }
    
    public void MostrarTablaAlmacenItems(JTable _Tabla, int _IdAlmacen, boolean _Consumo)
    {   
        //Primeros 4 items se ocultan
        String titulos[] = {"ID ITEM","N°","ITEM","STOCK","U. MEDIDA","DESCRIPCION"};
        ArrayList<ItemEntity> arrayItems;
        Object fila[] = new Object[8];
        ItemDAL itemDAL = new ItemDAL();        
        int count = 1;
        
        DefaultTableModel dtm = new DefaultTableModel(null,titulos)
        {
            @Override
            public boolean isCellEditable(int rowIndex,int columnIndex)
            {return false;}
        };  
        
        arrayItems = itemDAL.ListarItems(_Consumo);
        for(ItemEntity ie : arrayItems)
        {
            if(ie.getAlmacen_id() == _IdAlmacen)
            {
                fila[0] = ie.getItem_id();
                fila[1] = count;
                fila[2] = ie.getItem_nombre();
                fila[3] = ie.getItem_stock();
                fila[4] = ie.getItem_medida();
                fila[5] = ie.getItem_descripcion();           

                dtm.addRow(fila);
                count++;
            }            
        }
        _Tabla.setModel(dtm);
        _Tabla.setRowHeight(20); 
        
        _Tabla.removeColumn(_Tabla.getColumnModel().getColumn(0));        
        TableColumn colOrden = _Tabla.getColumnModel().getColumn(0);
        colOrden.setPreferredWidth(30);
        TableColumn colNombre = _Tabla.getColumnModel().getColumn(1);
        colNombre.setPreferredWidth(150);
        TableColumn colStock = _Tabla.getColumnModel().getColumn(2);
        colStock.setPreferredWidth(60);
        TableColumn colMedida = _Tabla.getColumnModel().getColumn(3);
        colMedida.setPreferredWidth(90);
        TableColumn colDescripcion = _Tabla.getColumnModel().getColumn(4);
        colDescripcion.setPreferredWidth(200);
        
        TableCellRenderer tcr =  _Tabla.getTableHeader().getDefaultRenderer();
        ClsMisc.miRender mr = new ClsMisc.miRender(tcr);
        mr.getTableCellRendererComponent(_Tabla, fila, true, true, WIDTH, 0);
        mr.getTableCellRendererComponent(_Tabla, fila, true, true, WIDTH, 2);
        mr.getTableCellRendererComponent(_Tabla, fila, true, true, WIDTH, 3);
        
    }
    
    public void MostrarTablaAlmacenRequerimientoBotones(JTable _Tabla, int _IdAula, boolean evaluar)
    {        
        String titulos[] = {"ID REQUERIMIENTO","ID AULA","N° REQUERIMIENTO","TEMA","FECHA","ESTADO",""};
        ArrayList<RequerimientoEntity> arrayRequerimiento;
        Object fila[] = new Object[10];
        RequerimientoDAL requerimientoDAL = new RequerimientoDAL();        
        
        DefaultTableModel dtm = new DefaultTableModel(null,titulos)
        {
            @Override
            public boolean isCellEditable(int rowIndex,int columnIndex)
            {return false;}
        };  
        
        JButton btnEditar = new JButton("");
        JButton btnEliminar = new JButton("");
        
        btnEditar.setName("btnEditar");
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/edit_black.png"))); 
        
//        btnEliminar.setName("btnEliminar");
//        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/delete_black.png")));  
        
        arrayRequerimiento = requerimientoDAL.ListarRequerimientos();
        for(RequerimientoEntity re : arrayRequerimiento)
        {
            if(re.getId_seccion() == _IdAula)
            {
                fila[0] = re.getRequerimiento_id();
                fila[1] = re.getId_seccion();
                fila[2] = re.getRequerimiento_numero();
                fila[3] = " "+re.getRequerimiento_tema();
                fila[4] = re.getRequerimiento_fecha();
                fila[5] = re.getRequerimiento_estado();
                fila[6] = btnEditar;            
                dtm.addRow(fila);
            }            
        }
        _Tabla.setModel(dtm);
        _Tabla.setRowHeight(20);        
        
        _Tabla.removeColumn(_Tabla.getColumnModel().getColumn(0));
        _Tabla.removeColumn(_Tabla.getColumnModel().getColumn(0));
        
        TableColumn colNumero = _Tabla.getColumnModel().getColumn(0);colNumero.setMaxWidth(150);colNumero.setPreferredWidth(130);
        TableColumn colTema = _Tabla.getColumnModel().getColumn(1);colTema.setPreferredWidth(350);
        TableColumn colFecha = _Tabla.getColumnModel().getColumn(2);colTema.setPreferredWidth(60);
        
        TableCellRenderer tcr =  _Tabla.getTableHeader().getDefaultRenderer();
        GeneralRender.MiRender miRender = new GeneralRender.MiRender(tcr);  
        _Tabla.setDefaultRenderer(Object.class, new Render());
        
        miRender.getTableCellRendererComponent(_Tabla, fila, true, true, WIDTH, 0);
        miRender.getTableCellRendererComponent(_Tabla, fila, true, true, WIDTH, 2);
        miRender.getTableCellRendererComponent(_Tabla, fila, true, true, WIDTH, 3);        
        
        
    }
    
    public void MostrarTablaAlmacenAdministracionRequerimientoBotones(JTable _Tabla, int _IdAula, boolean evaluar)
    {        
        String titulos[] = {"ID REQUERIMIENTO","ID AULA","N° REQUERIMIENTO","TEMA","FECHA","ESTADO","",""};
        ArrayList<RequerimientoEntity> arrayRequerimiento;
        Object fila[] = new Object[10];
        RequerimientoDAL requerimientoDAL = new RequerimientoDAL();        
        
        DefaultTableModel dtm = new DefaultTableModel(null,titulos)
        {
            @Override
            public boolean isCellEditable(int rowIndex,int columnIndex)
            {return false;}
        };  
        
        JButton btnEditar = new JButton("");
        JButton btnEliminar = new JButton("");
        
        btnEditar.setName("btnEditar");
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/edit_black.png"))); 
        
        btnEliminar.setName("btnEliminar");
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/delete_black.png")));  
        
        arrayRequerimiento = requerimientoDAL.ListarRequerimientos();
        for(RequerimientoEntity re : arrayRequerimiento)
        {
            if(re.getId_seccion() == _IdAula)
            {
                fila[0] = re.getRequerimiento_id();
                fila[1] = re.getId_seccion();
                fila[2] = re.getRequerimiento_numero();
                fila[3] = " "+re.getRequerimiento_tema();
                fila[4] = re.getRequerimiento_fecha();
                fila[5] = re.getRequerimiento_estado();
                fila[6] = btnEditar;   
                fila[7] = btnEliminar;   
                dtm.addRow(fila);
            }            
        }
        _Tabla.setModel(dtm);
        _Tabla.setRowHeight(20);        
        
        _Tabla.removeColumn(_Tabla.getColumnModel().getColumn(0));
        _Tabla.removeColumn(_Tabla.getColumnModel().getColumn(0));
        
        TableColumn colNumero = _Tabla.getColumnModel().getColumn(0);colNumero.setMaxWidth(150);colNumero.setPreferredWidth(130);
        TableColumn colTema = _Tabla.getColumnModel().getColumn(1);colTema.setPreferredWidth(350);
        TableColumn colFecha = _Tabla.getColumnModel().getColumn(2);colTema.setPreferredWidth(60);
        
        TableCellRenderer tcr =  _Tabla.getTableHeader().getDefaultRenderer();
        GeneralRender.MiRender miRender = new GeneralRender.MiRender(tcr);  
        _Tabla.setDefaultRenderer(Object.class, new Render());
        
        miRender.getTableCellRendererComponent(_Tabla, fila, true, true, WIDTH, 0);
        miRender.getTableCellRendererComponent(_Tabla, fila, true, true, WIDTH, 2);
        miRender.getTableCellRendererComponent(_Tabla, fila, true, true, WIDTH, 3);        
        
        
    }
}
