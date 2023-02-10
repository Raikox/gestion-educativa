/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PckInterfaces;
import PckConexion.ClsConexion;
import PckEntidad.ClsEntidadAlumnoPago;
import PckEntidad.ClsEntidadBoleta;
import PckEntidad.ClsEntidadBoletaMasDetalle;
import PckEntidad.ClsEntidadDetalleBoleta;
import PckEntidad.ClsEntidadDeudas;
import PckEntidad.ClsEntidadDeudasAlumno;
import PckEntidad.ClsEntidadDeudasBean;
import PckEntidad.ClsEntidadImpresionBoleta;
import PckEntidad.ClsEntidadNotificacion;
import PckEntidad.ClsEntidadPagoHistorial;
import PckEntidad.ClsEntidadPeriodo;
import PckEntidad.ClsEntidadPgoObservacion;
import PckEntidad.ClsEntidadSeccion;
import static PckInterfaces.FrmPrincipal.dskpPrincipal;
import PckNegocio.ClsAcceso;
import PckNegocio.ClsAlumno;
import PckNegocio.ClsBoleta;
import PckNegocio.ClsDetalleBoleta;
import PckNegocio.ClsDeudaProd;
import PckNegocio.ClsDeudaServ;
import PckNegocio.ClsDeudas;
import PckNegocio.ClsEntidadDeudasTiny;
import PckNegocio.ClsMatMen;
import PckNegocio.ClsMatricula;
import PckNegocio.ClsPagoHistorial;
import PckNegocio.ClsPendienteBoleta;
import PckNegocio.ClsPeriodo;
import PckNegocio.ClsPgoObservacion;
import PckNegocio.ClsSeccion;
import java.awt.Font;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.PrinterName;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import mpsDAL.PagoDAL;
import mpsModel.PagoModelo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.commons.codec.digest.DigestUtils;
import prymatricula.ClsMisc;
import static prymatricula.ClsMisc.ConvertirEUtoEUAstring;
import static prymatricula.ClsMisc.fechaActual;
import prymatricula.MyTableCellEditor;

/**
 *
 * @author Sistemas
 */
public class FrmPagoTodo extends javax.swing.JInternalFrame {
    
    //ArrayList para almacenar codigos de Periodo y Sección
    ArrayList<String> codigoPeriodo = new ArrayList();
    ArrayList<String> codigoSeccion = new ArrayList();
    //Variable para ver cuantas filas tiene la tabla al actualizarce    
    static int limitePendiente = 0;
    //Variable para ver que fila esta seleccionada
    static DefaultTableModel modelo;
    private static final String LOGOTIPO= System.getProperty("user.dir")+"/Img/solr.png";
    String codMat="";
    int mes=0;
    String alumno="";
    int auxObs=0;
    String tipoDeuda;
    String idDeuda;
    Double total;
    Double pendiente;
    String mesDeuda;
    String idBoleta,NumeroBoleta,tip;
    String salon;
    DecimalFormat df = new DecimalFormat("0.00");
    Connection con = null;
    
    
    prymatricula.MyTableCellEditor ce = new prymatricula.MyTableCellEditor();   
    /**
     * Creates new form FrmPagoTodos
     */
    public FrmPagoTodo() {
        initComponents();
        jScrollPane4.getVerticalScrollBar().setUnitIncrement(16);
        txaObservacion.setLineWrap(true);
        cargaComboPeriodo();
        cargaComboSeccion();
        if(!codigoSeccion.isEmpty())
        {
           actualizarTablaAlumno();  
        }
           
        
        activaBotonesBoleta(false,false,false,false);
        activaBotonesAccion(false,false,true,false);
        activaBotonesOpcion(false,false,false,false);
        btnGuardar.setEnabled(false);
    }
    
        private void habilitarCheckBox(boolean matricula, boolean mensualidad,
                boolean alimentacion, boolean producto, boolean servicio,
                boolean todo, boolean sinvencer)
        {
            cbxMatricula.setEnabled(matricula);
            cbxMensualidad.setEnabled(mensualidad);
            cbxAlimentacion.setEnabled(alimentacion);
            cbxProducto.setEnabled(producto);
            cbxServicio.setEnabled(servicio);
            cbxTodo.setEnabled(todo);
            cbxSinVencimiento.setEnabled(sinvencer);
        }
        
        private void cargaComboPeriodo()
        {
            ClsPeriodo periodos = new ClsPeriodo();
            ArrayList<ClsEntidadPeriodo> periodo =  periodos.ListarPeriodo();
            Iterator iterator = periodo.iterator();
            DefaultComboBoxModel DefaultComboBoxModel = new DefaultComboBoxModel();
            DefaultComboBoxModel.removeAllElements();
            cmbPeriodo.removeAllItems();
            String fila[] = new String[4];

            while(iterator.hasNext())
            {
            ClsEntidadPeriodo Periodo;
            Periodo = (ClsEntidadPeriodo) iterator.next();               
            fila[0] = Periodo.getNombre_Periodo();
            fila[1] = Periodo.getEstado_Periodo();        
                if(fila[1].equals("Activo"))
                {   
                codigoPeriodo.add(Periodo.getId_Periodo());     
                DefaultComboBoxModel.addElement(fila[0]);  
                }        
            }
            cmbPeriodo.setModel(DefaultComboBoxModel);
            //cambia estado del combo a activado
           
        }
        
        private void activaBotonesBoleta(boolean imprimir, boolean eliminar, boolean modificar, boolean anular)
        {
            btnImprimirBoleta.setEnabled(imprimir);
            btnEliminarBoleta.setEnabled(eliminar);
            btnModificarBoleta.setEnabled(modificar);
            btnAnularBoleta.setEnabled(anular);
        }
        
        private void activaBotonesAccion(boolean pago,boolean notificacion, boolean notificacionPeriodo,
                boolean cambio)
        {
            btnGenerarPago.setEnabled(pago);
            btnNotificacion.setEnabled(notificacion);
            btnNotificacionPeriodo.setEnabled(notificacionPeriodo);
            btnHistorialCambio.setEnabled(cambio);
        }
        
        public final void activaBotonesOpcion(boolean mas, boolean menos, boolean editar, boolean quitar)
        {
            btnMas.setEnabled(mas);
            btnMenos.setEnabled(menos);
            btnEditar.setEnabled(editar);
            btnQuitar.setEnabled(quitar);
        }
        
        private void llamaCambio()
        {
            PckInterfaces.FrmAccesoPago matri= new PckInterfaces.FrmAccesoPago();        
            int x = (dskpPrincipal.getWidth() / 2) - matri.getWidth() / 2;
            int y = (dskpPrincipal.getHeight() / 2) - matri.getHeight() / 2; 
            if (matri.isShowing()) {
                matri.setLocation(x, y);
            } else {                    
            dskpPrincipal.add(matri);        
            matri.setLocation(x, y);
            matri.masmenosTodo(this);
            matri.show();         
            }   
        }
        
        private void cargaComboSeccion()
        {    
            codigoSeccion.clear();
            ClsSeccion secciones = new ClsSeccion();
            ArrayList<ClsEntidadSeccion> seccion =  secciones.ListarSeccion();
            Iterator it = seccion.iterator();
            DefaultComboBoxModel DefaultComboBoxModel = new DefaultComboBoxModel();
            DefaultComboBoxModel.removeAllElements();
            cmbSeccion.removeAllItems();
            String fila2[] = new String[5];        
            while(it.hasNext())
            {
            ClsEntidadSeccion Seccion = (ClsEntidadSeccion) it.next();             
            fila2[0] = Seccion.getNombre_Seccion();
            fila2[1] = Seccion.getEstado_Periodo();       
            fila2[3] = Seccion.getNombre_Periodo();

                if (codigoPeriodo.get(cmbPeriodo.getSelectedIndex()).equals(Seccion.getId_Periodo())) 
                {
                    codigoSeccion.add(Seccion.getId_Seccion());
                    DefaultComboBoxModel.addElement(fila2[0]);
                }       
            }
            cmbSeccion.setModel(DefaultComboBoxModel);         
        }
        
        private void actualizarTablaAlumno()
        {
            int p=1;
            String titulos[] = {"ID","N°","Apellidos y nombres"};
            con = ClsConexion.getConection();
            ClsAlumno alumnos = new ClsAlumno();
            ArrayList<ClsEntidadAlumnoPago> alumnoo = alumnos.ListarAlumnoPago(con);
            Iterator iterator = alumnoo.iterator();
            DefaultTableModel defaultTableModel =new DefaultTableModel(null,titulos){ 
            @Override
            public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
            }; 
            String fila[] = new String[12];                
            while(iterator.hasNext())
            {
                ClsEntidadAlumnoPago Alumno;
                Alumno = (ClsEntidadAlumnoPago) iterator.next();
                fila[0] = Alumno.getId_Matricula();
                fila[1] = String.valueOf(p);
                fila[2] = Alumno.getApellidos_Nombres();
                fila[3] = Alumno.getId_Seccion();                       
                if(fila[3].equals(codigoSeccion.get(cmbSeccion.getSelectedIndex())))
                {
                    p++;
                    defaultTableModel.addRow(fila);                
                }
            } 
            ClsConexion.closeConnection(con);
            tblAlumno.setModel(defaultTableModel);
            tblAlumno.removeColumn(tblAlumno.getColumnModel().getColumn(0));
            TableColumn  columnaN =tblAlumno.getColumnModel().getColumn(0);
            columnaN.setPreferredWidth(15);
            TableColumn  columnaPer =tblAlumno.getColumnModel().getColumn(1);
            columnaPer.setPreferredWidth(250);
        }
        
        private void actualizarTablaAlumnoBusqueda(String texto)
        {
            int p=1;
            String titulos[] = {"ID","N°","Apellidos y nombres","Sección","Periodo"};
            con = ClsConexion.getConection();
            ClsAlumno alumnos = new ClsAlumno();
            ArrayList<ClsEntidadAlumnoPago> alumnoo = alumnos.ListarAlumnoBusqueda(texto,con);
            Iterator iterator = alumnoo.iterator();
            DefaultTableModel defaultTableModel =new DefaultTableModel(null,titulos);               
            String fila[] = new String[12];                
            while(iterator.hasNext())
            {
                ClsEntidadAlumnoPago Alumno;
                Alumno = (ClsEntidadAlumnoPago) iterator.next();
                fila[0] = Alumno.getId_Matricula();
                fila[1] = String.valueOf(p);
                fila[2] = Alumno.getApellidos_Nombres();
                fila[3] = Alumno.getNombre_Seccion();
                fila[4] = Alumno.getNombre_Periodo();
                
                    p++;
                    defaultTableModel.addRow(fila);                
                
            }         
            ClsConexion.closeConnection(con);
            tblAlumno.setModel(defaultTableModel);
            tblAlumno.removeColumn(tblAlumno.getColumnModel().getColumn(0));
            TableColumn  columnaN =tblAlumno.getColumnModel().getColumn(0);
            columnaN.setPreferredWidth(12);
            TableColumn  columnaPer =tblAlumno.getColumnModel().getColumn(1);
            columnaPer.setPreferredWidth(150);
        }
        
        public void actualiza()
        {
            cbxTodo.setSelected(false);
            cbxTodo.setSelected(true);
            actualizarBoleta(codMat);
        }
        
        private int actualizarDatosGenerales(String codMatricula)
        {
            int mmes;
            String inicioClases = "";
            ClsMatricula matricula = new ClsMatricula();
            ResultSet rsMatricula;      
            Connection conx = ClsConexion.getConection();
            try 
            {
                rsMatricula = matricula.SeleccionarMatriculaSolo(codMatricula,conx);
                while(rsMatricula.next())
                {
                    txtAlimentacion.setText(rsMatricula.getString("comida_alumno"));
                    inicioClases = rsMatricula.getString("inicio_clases");
                    txtInicioClases.setText(inicioClases);
                    txtHorario.setText(rsMatricula.getString("horario"));
                }                
                
            } catch (Exception ex) {
                Logger.getLogger(FrmPagoTodo.class.getName()).log(Level.SEVERE, null, ex);
            }
            ClsConexion.closeConnection(conx);
            mmes = Integer.parseInt(inicioClases.substring(3, 5))-1;
            return mmes;
        }
        
        private int obtenerMesInicioClases(String codMatricula,Connection conx)
        {
            int mmes;
            String inicioClases = "";
            ClsMatricula matricula = new ClsMatricula();
            ResultSet rsMatricula;    
            
            try 
            {
                rsMatricula = matricula.SeleccionarMatriculaSolo(codMatricula,conx);
                while(rsMatricula.next())
                {
                   
                    inicioClases = rsMatricula.getString("inicio_clases");                    
                }                
                
            } catch (Exception ex) {
                Logger.getLogger(FrmPagoTodo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            mmes = Integer.parseInt(inicioClases.substring(3, 5))-1;
            return mmes;
        }
        
        private void actualizarBoletaCondicional(String codMatricula,
            boolean matricula,boolean mensualidad,boolean alimentacion,boolean producto,
            boolean servicio)
        {
            String titulos[] = {"ID MATRICULA","ID BOLETA","N° BOLETA","DESCRIPCIÓN","IMPORTE","FECHA"};
            ClsDeudas boletas = new ClsDeudas();
            con = ClsConexion.getConection();
            ArrayList<ClsEntidadBoletaMasDetalle> boleta = boletas.ListarBoletasTodas(codMatricula,con);
            Iterator it = boleta.iterator();
            ClsConexion.closeConnection(con); 
            DefaultTableModel dtm = new DefaultTableModel(null,titulos){
            @Override
            public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
            }; 
            String fila[] = new String[10];
            
                while(it.hasNext())
                {
                    ClsEntidadBoletaMasDetalle Boleta;
                    Boleta = (ClsEntidadBoletaMasDetalle) it.next();
                    fila[0] = Boleta.getId_Matricula();
                    fila[1] = Boleta.getId_Boleta();
                    fila[2] = Boleta.getNumero_Boleta();
                    fila[3] = Boleta.getDescripcion_Boleta();
                    fila[4] = Boleta.getImporte_Boleta();
                    fila[5] = Boleta.getFecha_Boleta();
                    fila[6] = Boleta.getTipo_Detalle_Boleta();
                    System.out.println("_________________");
                    System.out.println("Tipo: "+fila[6]);
                    
                    if(fila[6].equals("matricula"))
                    {
                        if(matricula)
                        {
                            dtm.addRow(fila);
                        }
                    }
                        
                    if(fila[6].equals("mensualidad"))
                    {
                        if(mensualidad)
                        {
                            dtm.addRow(fila);
                        }
                    }
                                
                    if(fila[6].equals("comida"))
                    {
                        if(alimentacion)
                        {
                            dtm.addRow(fila);
                        }
                    }
                            
                    if(fila[6].equals("producto"))
                    {
                        if(producto)
                        {
                            dtm.addRow(fila);
                        }
                    }
                            
                    if(fila[6].equals("servicio"))
                    {
                        if(servicio)
                        {
                            dtm.addRow(fila);
                        }
                    } 
                }
                
            tblBoleta.setModel(dtm);
            tblBoleta.removeColumn(tblBoleta.getColumnModel().getColumn(0));
            tblBoleta.removeColumn(tblBoleta.getColumnModel().getColumn(0));
            
            TableColumn  columnaNumero =tblBoleta.getColumnModel().getColumn(0);
            columnaNumero.setPreferredWidth(35); 
            TableColumn  columnaDesc =tblBoleta.getColumnModel().getColumn(1);
            columnaDesc.setPreferredWidth(200); 
            TableColumn  columnaImporte =tblBoleta.getColumnModel().getColumn(2);
            columnaImporte.setPreferredWidth(25); 
            TableColumn  columnaFecha =tblBoleta.getColumnModel().getColumn(3);
            columnaFecha.setPreferredWidth(25);
            TableCellRenderer tcr =  tblBoleta.getTableHeader().getDefaultRenderer();                       
            ClsMisc.miRender mr = new ClsMisc.miRender(tcr);
            
            mr.getTableCellRendererComponent(tblBoleta, fila, isSelected, isIcon, WIDTH, 0);            
            mr.getTableCellRendererComponent(tblBoleta, fila, isSelected, isIcon, WIDTH, 2);
            mr.getTableCellRendererComponent(tblBoleta, fila, isSelected, isIcon, WIDTH, 3);
        }
        
        private void actualizarBoleta(String codMatricula)
        {
            String titulos[] = {"ID MATRICULA","ID BOLETA","N° BOLETA","DESCRIPCIÓN","IMPORTE","FECHA","TIPO"};
            ClsDeudas boletas = new ClsDeudas();
            con = ClsConexion.getConection();
            ArrayList<ClsEntidadBoletaMasDetalle> boleta = boletas.ListarBoletasTodas(codMatricula,con);
            Iterator it = boleta.iterator();
            ClsConexion.closeConnection(con);
            DefaultTableModel dtm = new DefaultTableModel(null,titulos){
            @Override
            public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
            }; 
            String fila[] = new String[10];
            
                while(it.hasNext())
                {
                    ClsEntidadBoletaMasDetalle Boleta;
                    Boleta = (ClsEntidadBoletaMasDetalle) it.next();
                    fila[0] = Boleta.getId_Matricula();
                    fila[1] = Boleta.getId_Boleta();
                    fila[2] = Boleta.getNumero_Boleta();
                    fila[3] = Boleta.getDescripcion_Boleta();
                    fila[4] = Boleta.getImporte_Boleta();
                    fila[5] = Boleta.getFecha_Boleta();
                    fila[6] = Boleta.getTipo_Detalle_Boleta();                    
                    dtm.addRow(fila);                    
                }
            tblBoleta.setModel(dtm);
            tblBoleta.removeColumn(tblBoleta.getColumnModel().getColumn(0));
            tblBoleta.removeColumn(tblBoleta.getColumnModel().getColumn(0));
            tblBoleta.removeColumn(tblBoleta.getColumnModel().getColumn(4));
            TableColumn  columnaNumero =tblBoleta.getColumnModel().getColumn(0);
            columnaNumero.setPreferredWidth(35); 
            TableColumn  columnaDesc =tblBoleta.getColumnModel().getColumn(1);
            columnaDesc.setPreferredWidth(200); 
            TableColumn  columnaImporte =tblBoleta.getColumnModel().getColumn(2);
            columnaImporte.setPreferredWidth(25); 
            TableColumn  columnaFecha =tblBoleta.getColumnModel().getColumn(3);
            columnaFecha.setPreferredWidth(25);
            TableCellRenderer tcr =  tblBoleta.getTableHeader().getDefaultRenderer();                       
            ClsMisc.miRender mr = new ClsMisc.miRender(tcr);
            mr.getTableCellRendererComponent(tblBoleta, fila, isSelected, isIcon, WIDTH, 0);            
            mr.getTableCellRendererComponent(tblBoleta, fila, isSelected, isIcon, WIDTH, 2);
            mr.getTableCellRendererComponent(tblBoleta, fila, isSelected, isIcon, WIDTH, 3);
        }
        
        private void actualizarPendiente(int mes, String codMatricula,boolean sinVencer)
        {
            double aux=0.0;
            String titulos[] = {"CONCEPTO","TOTAL","PENDIENTE","TOT. A PAGAR","FEC. VENCIMIENTO","TIPO","ID_DEUDA","MES"};
            ClsDeudas deudas = new ClsDeudas();
            ArrayList<ClsEntidadDeudas> deuda;
            if(sinVencer)
            {
                con = ClsConexion.getConection();
                deuda = deudas.ListarDeudasSinVencer(codMatricula,con);
                ClsConexion.closeConnection(con);
            }
            else
            {
                con = ClsConexion.getConection();
                deuda = deudas.ListarDeudas(codMatricula,con);
                ClsConexion.closeConnection(con);
            }
            Iterator iterator = deuda.iterator();
            modelo = new DefaultTableModel(null,titulos);
            DefaultTableModel defaultTableModel =new DefaultTableModel(null,titulos);             
            String fila[] = new String[12];    
            int aux2=0;
            int aux3=0;
            while(iterator.hasNext())
            {
            ClsEntidadDeudas Deuda; 
            Deuda = (ClsEntidadDeudas) iterator.next();
            fila[0] = Deuda.getConcepto();
            fila[1] = Deuda.getUnitario();
            fila[2] = Deuda.getImporte();
            fila[3] = fila[2];
            fila[4] = Deuda.getVencimiento();
            fila[5] = Deuda.getTipo();
            fila[6] = Deuda.getId_Deuda();
            fila[7] = Deuda.getMes();
            
            //////Filtra registros para que aparezcan a partir de inicio de clases/////
            /////////INICIO/////////
            //verifica que la palabra sea mayor a 11 caracteres (ALIMENTACION) (MENSUALIDAD)
            if(fila[0].length()>11)
            {
                //en este caso esta registrado "ALIMENTACION " (con espacio) 
                //se verifica que a continuacion de ALIMENTACION no exista ningun otro caracter                
                if(fila[0].substring(12).equals(" "))
                {
                    defaultTableModel.addRow(fila);
                    modelo.addRow(fila);
                    aux = Double.parseDouble(fila[3])+aux;                    
                }
                //si no se cumple quiere decir que a continuacion eixsten mas caracteres
                //comprobamos que la primera palabra es ALIMENTACION
                else if(fila[0].substring(0,12).equals("ALIMENTACION"))
                { 
                //cumple, por lo tanto la siguiente palabra es un mes                    
                    switch (fila[0].substring(13)) 
                    {
                        case "ENERO":aux2 = 0;break;
                        case "FEBRERO":aux2=1;break;
                        case "MARZO":aux2=2;break;
                        case "ABRIL":aux2=3;break;
                        case "MAYO":aux2=4;break;
                        case "JUNIO":aux2=5;break;
                        case "JULIO":aux2=6;break;
                        case "AGOSTO":aux2=7;break;
                        case "SEPTIEMBRE":aux2=8;break;
                        case "OCTUBRE":aux2=9;break;
                        case "NOVIEMBRE":aux2=10;break;
                        case "DICIEMBRE":aux2=11;break;
                    }
                    //si el mes es mayor que la fecha de inicio de clases se mostrara en la tabla
                    if(aux2>=mes)
                    {
                        defaultTableModel.addRow(fila);
                        modelo.addRow(fila);
                        aux = Double.parseDouble(fila[3])+aux;
                    }
                    //si nada de esto ocurre comparamos que despues de ALIMENTACION haya un espacio en blanco
                    //si es asi, existen caracteres despues de ese espacio y deben mostrarse en la tabla
                    //caso "ALIMENTACION MES DE .... "
                    else if(fila[0].substring(12,13).equals(" "))
                    {
                        defaultTableModel.addRow(fila);
                        modelo.addRow(fila);
                        aux = Double.parseDouble(fila[3])+aux;
                    }
                } 
                //si no es eso es deuda de mensualidad segun el mes 
                //la deuda es mayor al inicio de clases
                else if(fila[0].substring(0,11).equals("MENSUALIDAD"))
                { 
                        switch (fila[0].substring(12)) 
                        {
                            case "ENERO":aux3 = 0;break;
                            case "FEBRERO":aux3=1;break;
                            case "MARZO":aux3=2;break;
                            case "ABRIL":aux3=3;break;
                            case "MAYO":aux3=4;break;
                            case "JUNIO":aux3=5;break;
                            case "JULIO":aux3=6;break;
                            case "AGOSTO":aux3=7;break;
                            case "SEPTIEMBRE":aux3=8;break;
                            case "OCTUBRE":aux3=9;break;
                            case "NOVIEMBRE":aux3=10;break;
                            case "DICIEMBRE":aux3=11;break;
                        }
                        if(aux3>=mes)
                        {
                        defaultTableModel.addRow(fila);
                        modelo.addRow(fila);
                        aux = Double.parseDouble(fila[3])+aux;
                        } 
                }
                else 
                {
                    //producto o servicio (mayor a 11 caracteres)
                    defaultTableModel.addRow(fila);
                    modelo.addRow(fila);
                    aux = Double.parseDouble(fila[3])+aux;
                } 
            }
            else
            {
                //producto o servicio (menor a 11 caracteres)
                defaultTableModel.addRow(fila);
                modelo.addRow(fila);
                aux = Double.parseDouble(fila[3])+aux;
            }
        ////////END////////
            }
            tblPendiente.setModel(defaultTableModel);
            txtTotal.setText(String.valueOf(df.format(aux)));
            TableColumn  columnaPer =tblPendiente.getColumnModel().getColumn(0);
                columnaPer.setPreferredWidth(200);
                columnaPer.setMinWidth(170);

            //Ocultando dos ultimas columnas
                //es tres veces 5 porque al remover la 1ra la siguiente queda como la 4ta
            tblPendiente.removeColumn(tblPendiente.getColumnModel().getColumn(5));
            tblPendiente.removeColumn(tblPendiente.getColumnModel().getColumn(5));
            tblPendiente.removeColumn(tblPendiente.getColumnModel().getColumn(5));
            TableCellRenderer tcr =  tblPendiente.getTableHeader().getDefaultRenderer();                       
            ClsMisc.miRenderPintar mr = new ClsMisc.miRenderPintar(tcr);
            mr.getTableCellRendererComponent(tblPendiente, fila, isSelected, isIcon, WIDTH, 3);

            limitePendiente = defaultTableModel.getRowCount();
        }
        
        private void actualizarPendienteCondicional(int mess, String codMatricula,
            boolean matricula,boolean mensualidad,boolean alimentacion,boolean producto,
            boolean servicio,boolean sinVencer)
        {
            double aux=0.0;
            String titulos[] = {"CONCEPTO","TOTAL","PENDIENTE","TOT. A PAGAR","FEC. VENCIMIENTO","TIPO","ID_DEUDA","MES"};
            ClsDeudas deudas = new ClsDeudas();
            ArrayList<ClsEntidadDeudas> deuda;
            if(sinVencer)
            {
                con = ClsConexion.getConection();
                deuda = deudas.ListarDeudasSinVencer(codMatricula,con);
                ClsConexion.closeConnection(con);
            }
            else
            {
                con = ClsConexion.getConection();
                deuda = deudas.ListarDeudas(codMatricula,con);
                ClsConexion.closeConnection(con);
            }
            Iterator iterator = deuda.iterator();
            modelo = new DefaultTableModel(null,titulos);
            DefaultTableModel defaultTableModel =new DefaultTableModel(null,titulos);             
            String fila[] = new String[12];    
            int aux2=0;
            int aux3=0;
            while(iterator.hasNext())
            {
            ClsEntidadDeudas Deuda ; 
            Deuda = (ClsEntidadDeudas) iterator.next();
            fila[0] = Deuda.getConcepto();
            fila[1] = Deuda.getUnitario();
            fila[2] = Deuda.getImporte();
            fila[3] = fila[2];
            fila[4] = Deuda.getVencimiento();
            fila[5] = Deuda.getTipo();
            fila[6] = Deuda.getId_Deuda();
            fila[7] = Deuda.getMes();
            
            //////Filtra registros para que aparezcan a partir de inicio de clases/////
            /////////INICIO/////////
            //verifica que la palabra sea mayor a 11 caracteres (ALIMENTACION) (MENSUALIDAD)
            if(fila[0].length()>11)
            {
                //en este caso esta registrado "ALIMENTACION " (con espacio) 
                //se verifica que a continuacion de ALIMENTACION no exista ningun otro caracter
                //no existe "ALIMENTACION " solo siempre sigue alguna palabra,
                //por eso el programa ejecuta a partir del ELSE                
                if(fila[0].substring(12).equals(" "))
                {
                    defaultTableModel.addRow(fila);
                    modelo.addRow(fila);
                    aux = Double.parseDouble(fila[3])+aux;                    
                }
                //si no se cumple quiere decir que a continuacion eixsten mas caracteres
                //comprobamos que la primera palabra es ALIMENTACION
                else if(fila[0].substring(0,12).equals("ALIMENTACION"))
                { 
                //cumple, por lo tanto la siguiente palabra es un mes                    
                    switch (fila[0].substring(13)) 
                    {
                        case "ENERO":aux2 = 0;break;
                        case "FEBRERO":aux2=1;break;
                        case "MARZO":aux2=2;break;
                        case "ABRIL":aux2=3;break;
                        case "MAYO":aux2=4;break;
                        case "JUNIO":aux2=5;break;
                        case "JULIO":aux2=6;break;
                        case "AGOSTO":aux2=7;break;
                        case "SEPTIEMBRE":aux2=8;break;
                        case "OCTUBRE":aux2=9;break;
                        case "NOVIEMBRE":aux2=10;break;
                        case "DICIEMBRE":aux2=11;break;
                    }
                    //si el mes es mayor que la fecha de inicio de clases se mostrara en la tabla
                    if(aux2>=mess)
                    {   
                        if(alimentacion)
                        {              
                            defaultTableModel.addRow(fila);
                            modelo.addRow(fila);
                            aux = Double.parseDouble(fila[3])+aux;
                        }
                    }
                    //si nada de esto ocurre comparamos que despues de ALIMENTACION haya un espacio en blanco
                    //si es asi, existen caracteres despues de ese espacio y deben mostrarse en la tabla
                    //caso "ALIMENTACION MES DE .... "
                    else if(fila[0].substring(12,13).equals(" "))
                    {
                        if(alimentacion)
                        {
                            defaultTableModel.addRow(fila);
                            modelo.addRow(fila);
                            aux = Double.parseDouble(fila[3])+aux;
                        }
                    }
                } 
                //si no es eso es deuda de mensualidad segun el mes 
                //la deuda es mayor al inicio de clases
                else if(fila[0].substring(0,11).equals("MENSUALIDAD"))
                { 
                        switch (fila[0].substring(12)) 
                        {
                            case "ENERO":aux3 = 0;break;
                            case "FEBRERO":aux3=1;break;
                            case "MARZO":aux3=2;break;
                            case "ABRIL":aux3=3;break;
                            case "MAYO":aux3=4;break;
                            case "JUNIO":aux3=5;break;
                            case "JULIO":aux3=6;break;
                            case "AGOSTO":aux3=7;break;
                            case "SEPTIEMBRE":aux3=8;break;
                            case "OCTUBRE":aux3=9;break;
                            case "NOVIEMBRE":aux3=10;break;
                            case "DICIEMBRE":aux3=11;break;
                        }
                        if(aux3>=mess)
                        {
                            if(mensualidad)
                            {
                                defaultTableModel.addRow(fila);
                                modelo.addRow(fila);
                                aux = Double.parseDouble(fila[3])+aux;
                            }
                        } 
                }
                else 
                {
                    //producto o servicio (mayor a 11 caracteres)
                    switch (fila[5]) 
                    {
                        case "servicio":
                            if(servicio)
                            {
                                defaultTableModel.addRow(fila);
                                modelo.addRow(fila);
                                aux = Double.parseDouble(fila[3])+aux;
                            }   break;
                        case "producto":
                            if(producto)
                            {
                                defaultTableModel.addRow(fila);
                                modelo.addRow(fila);
                                aux = Double.parseDouble(fila[3])+aux;
                            }   break;
                    }
                } 
            }
            else
            {
                //producto o servicio (menor a 11 caracteres)
                switch (fila[5]) 
                {
                    case "servicio":
                        if(servicio)
                        {
                            defaultTableModel.addRow(fila);
                            modelo.addRow(fila);
                            aux = Double.parseDouble(fila[3])+aux;
                        }   break;
                    case "producto":
                        if(producto)
                        {
                            defaultTableModel.addRow(fila);
                            modelo.addRow(fila);
                            aux = Double.parseDouble(fila[3])+aux;
                        }   break;
                    case "matricula":
                        if(matricula)
                        {
                            defaultTableModel.addRow(fila);
                            modelo.addRow(fila);
                            aux = Double.parseDouble(fila[3])+aux;
                        }   break;    
                }
            }
            ////////END////////
            }
            tblPendiente.setModel(defaultTableModel);
            System.out.println("----------------------");
            System.out.println("total: "+aux);
            txtTotal.setText(String.valueOf(aux));
            TableColumn  columnaPer =tblPendiente.getColumnModel().getColumn(0);
                columnaPer.setPreferredWidth(200);
                columnaPer.setMinWidth(170);

            //Ocultando dos ultimas columnas
                //es tres veces 5 porque al remover la 1ra la siguiente queda como la 4ta
            tblPendiente.removeColumn(tblPendiente.getColumnModel().getColumn(5));
            tblPendiente.removeColumn(tblPendiente.getColumnModel().getColumn(5));
            tblPendiente.removeColumn(tblPendiente.getColumnModel().getColumn(5));
            TableCellRenderer tcr =  tblPendiente.getTableHeader().getDefaultRenderer();                       
            ClsMisc.miRenderPintar mr = new ClsMisc.miRenderPintar(tcr);
            mr.getTableCellRendererComponent(tblPendiente, fila, isSelected, isIcon, WIDTH, 3);
            limitePendiente = defaultTableModel.getRowCount();
        }
        
        public boolean updateText(String valor,int Fila)
        {
            //Fila = tblPendiente.getSelectedRow();
            boolean res = false;        
            double monto=0.0;
            double totall;
            double aux;
            int i;
            int Columna=0;
            String valor2;
            //int Row=0;
             try 
             {                                      
                for (i=0; i < limitePendiente;i++)
                {
                    if(i!=Fila)
                    {
                        valor2= (String) modelo.getValueAt(i, 3);
                        System.out.println("valor "+i+": "+valor2);
                        aux = Double.parseDouble(valor2);
                        monto= aux + monto;
                    }
                    else
                    {
                        Columna=i;
                    }
                }
                System.out.println("modificado: "+valor);
                totall = monto + Double.parseDouble(valor);
                txtTotal.setText(String.valueOf(totall));
                System.out.println("total: "+totall);
                jPanel6.repaint();
                res=true;
                modelo.setValueAt(valor, Fila, 3);
             }catch(NumberFormatException e){            
                System.out.println(e);
            }
            return res;       

        }   
        
        private String ingresarClave()
        {
            String texto = null;
            //Crear un JOptionPane.ShowInputDialog para Password
            JPanel panel = new JPanel();
            JLabel label = new JLabel("Ingrese clave de autorización:");
            JPasswordField pass = new JPasswordField(10);
            panel.add(label);
            panel.add(pass);
            String[] options = new String[]{"ACEPTAR", "CANCELAR"};
            int option = JOptionPane.showOptionDialog(null, panel, "Seguridad",
            JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
            null, options, options[0]);
            if(option == 0) // pressing OK button
            {
                char[] password = pass.getPassword();
                texto= new String(password);
            }
            
            return texto;
        }
        
        private String[] ingresarClave2()
        {
            
            String usupass = null;
            String usu = null;
            //Crear un JOptionPane.ShowInputDialog para Password
            JPanel panel = new JPanel();
            JLabel label1 = new JLabel("Ingrese usuario:");
            JLabel label = new JLabel("Ingrese clave de autorización:");
            JTextField pass1 = new JTextField(10);
            JPasswordField pass = new JPasswordField(10);
            panel.add(label1);
            panel.add(pass1);
            panel.add(label);
            panel.add(pass);
            String[] options = new String[]{"ACEPTAR", "CANCELAR"};
            int option = JOptionPane.showOptionDialog(null, panel, "Seguridad",
            JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
            null, options, options[0]);
            if(option == 0) // pressing OK button
            {
                char[] password = pass.getPassword();
                usupass= new String(password);
                
                usu = pass1.getText();
                
            }
            String datos[]={usu,usupass};
            return datos;
        }
        
        private String obtenerClave()
        {
            String texto=null;
            //leemos archivo de texto para extraer clave
            String url ="/Data/p001.txt";
            //contenido del fichero
            String content;  
            //ruta completa al fichero que deseamos leer
            File file = new File(System.getProperty("user.dir")+url); 
            try 
            {
                try ( //leyendo el archivo completo
                        FileReader reader = new FileReader(file)) {
                    char[] chars = new char[(int) file.length()];
                    reader.read(chars);
                    content = new String(chars);
                    //cerramos el reader
                }
                //eliminamos espacios en blanco (opcional)
                //content = content.replaceAll("\\s","");
                //asignamos cada valor al nuevo vector (usamos como separador la coma)
                String[] vector = content.split(",");
                //mostramos el vector
                //for (String vector1 : vector) {
                //System.out.println(vector1);                
                //}
                texto=vector[0];
            } catch (IOException e) {
                    System.out.println(e);
            }
            return texto;
        }
        private void listarDeudasPagadas(int mes, String codMatricula)
        {
            double aux=0.0;
            String titulos[] = {"CONCEPTO","TOTAL","PENDIENTE","TOT. A PAGAR","FEC. VENCIMIENTO","TIPO","ID_DEUDA","MES"};
            ClsDeudas deudas = new ClsDeudas();
            con = ClsConexion.getConection();
                ArrayList<ClsEntidadDeudas> deuda;
                deuda = deudas.ListarPagos(codMatricula,con);
                Iterator iterator = deuda.iterator();
            ClsConexion.closeConnection(con);
            modelo = new DefaultTableModel(null,titulos);
            DefaultTableModel defaultTableModel =new DefaultTableModel(null,titulos);             
            String fila[] = new String[12];    
            int aux2=0;
            int aux3=0;
            while(iterator.hasNext())
            {
            ClsEntidadDeudas Deuda ; 
            Deuda = (ClsEntidadDeudas) iterator.next();
            fila[0] = Deuda.getConcepto();
            fila[1] = Deuda.getUnitario();
            fila[2] = Deuda.getImporte();
            fila[3] = fila[2];
            fila[4] = Deuda.getVencimiento();
            fila[5] = Deuda.getTipo();
            fila[6] = Deuda.getId_Deuda();
            fila[7] = Deuda.getMes();
            
            //////Filtra registros para que aparezcan a partir de inicio de clases/////
            /////////INICIO/////////
            //verifica que la palabra sea mayor a 11 caracteres (ALIMENTACION) (MENSUALIDAD)
            if(fila[0].length()>11)
            {
                //en este caso esta registrado "ALIMENTACION " (con espacio) 
                //se verifica que a continuacion de ALIMENTACION no exista ningun otro caracter                
                if(fila[0].substring(12).equals(" "))
                {
                    defaultTableModel.addRow(fila);
                    modelo.addRow(fila);
                    aux = Double.parseDouble(fila[3])+aux;                    
                }
                //si no se cumple quiere decir que a continuacion eixsten mas caracteres
                //comprobamos que la primera palabra es ALIMENTACION
                else if(fila[0].substring(0,12).equals("ALIMENTACION"))
                { 
                //cumple, por lo tanto la siguiente palabra es un mes                    
                    switch (fila[0].substring(13)) 
                    {
                        case "ENERO":aux2 = 0;break;
                        case "FEBRERO":aux2=1;break;
                        case "MARZO":aux2=2;break;
                        case "ABRIL":aux2=3;break;
                        case "MAYO":aux2=4;break;
                        case "JUNIO":aux2=5;break;
                        case "JULIO":aux2=6;break;
                        case "AGOSTO":aux2=7;break;
                        case "SEPTIEMBRE":aux2=8;break;
                        case "OCTUBRE":aux2=9;break;
                        case "NOVIEMBRE":aux2=10;break;
                        case "DICIEMBRE":aux2=11;break;
                    }
                    //si el mes es mayor que la fecha de inicio de clases se mostrara en la tabla
                    if(aux2>=mes)
                    {
                        defaultTableModel.addRow(fila);
                        modelo.addRow(fila);
                        aux = Double.parseDouble(fila[3])+aux;
                    }
                    //si nada de esto ocurre comparamos que despues de ALIMENTACION haya un espacio en blanco
                    //si es asi, existen caracteres despues de ese espacio y deben mostrarse en la tabla
                    //caso "ALIMENTACION MES DE .... "
                    else if(fila[0].substring(12,13).equals(" "))
                    {
                        defaultTableModel.addRow(fila);
                        modelo.addRow(fila);
                        aux = Double.parseDouble(fila[3])+aux;
                    }
                } 
                //si no es eso es deuda de mensualidad segun el mes 
                //la deuda es mayor al inicio de clases
                else if(fila[0].substring(0,11).equals("MENSUALIDAD"))
                { 
                        switch (fila[0].substring(12)) 
                        {
                            case "ENERO":aux3 = 0;break;
                            case "FEBRERO":aux3=1;break;
                            case "MARZO":aux3=2;break;
                            case "ABRIL":aux3=3;break;
                            case "MAYO":aux3=4;break;
                            case "JUNIO":aux3=5;break;
                            case "JULIO":aux3=6;break;
                            case "AGOSTO":aux3=7;break;
                            case "SEPTIEMBRE":aux3=8;break;
                            case "OCTUBRE":aux3=9;break;
                            case "NOVIEMBRE":aux3=10;break;
                            case "DICIEMBRE":aux3=11;break;
                        }
                        if(aux3>=mes)
                        {
                        defaultTableModel.addRow(fila);
                        modelo.addRow(fila);
                        aux = Double.parseDouble(fila[3])+aux;
                        } 
                }
                else 
                {
                    //producto o servicio (mayor a 11 caracteres)
                    defaultTableModel.addRow(fila);
                    modelo.addRow(fila);
                    aux = Double.parseDouble(fila[3])+aux;
                } 
            }
            else
            {
                //producto o servicio (menor a 11 caracteres)
                defaultTableModel.addRow(fila);
                modelo.addRow(fila);
                aux = Double.parseDouble(fila[3])+aux;
            }
        ////////END////////
            }
            tblPendiente.setModel(defaultTableModel);
            txtTotal.setText(String.valueOf(aux));
            TableColumn  columnaPer =tblPendiente.getColumnModel().getColumn(0);
                columnaPer.setPreferredWidth(200);
                columnaPer.setMinWidth(170);

            //Ocultando dos ultimas columnas
                //es dos veces 5 porque al remover la 1ra la siguiente queda como la 4ta
            tblPendiente.removeColumn(tblPendiente.getColumnModel().getColumn(5));
            tblPendiente.removeColumn(tblPendiente.getColumnModel().getColumn(5));
            tblPendiente.removeColumn(tblPendiente.getColumnModel().getColumn(5));
            TableCellRenderer tcr =  tblPendiente.getTableHeader().getDefaultRenderer();                       
            ClsMisc.miRenderPintar mr = new ClsMisc.miRenderPintar(tcr);
            mr.getTableCellRendererComponent(tblPendiente, fila, isSelected, isIcon, WIDTH, 3);

            limitePendiente = defaultTableModel.getRowCount();
        }
        private void condicionalesActualizar(boolean sinVencer)
        {
            //COMBINACIONES DE 4
            //MATRICULA-MENSUALIDAD-ALIMENTACION-PRODUCTO-SERVICIO
            if((cbxMatricula.isSelected() && cbxMensualidad.isSelected() && 
                    cbxAlimentacion.isSelected() && cbxProducto.isSelected() &&
                    cbxServicio.isSelected()) || cbxTodo.isSelected() )
            {
                actualizarPendienteCondicional(mes,codMat,true,true,true,true,true,sinVencer);
                actualizarBoletaCondicional(codMat,true,true,true,true,true);
                ce.ActualizaTodo(this); 
                tblPendiente.getColumnModel().getColumn( 3 ).setCellEditor(new MyTableCellEditor());
            }
            //MATRICULA-MENSUALIDAD-ALIMENTACION-PRODUCTO
            else if(cbxMatricula.isSelected() && cbxMensualidad.isSelected() && 
                    cbxAlimentacion.isSelected() && cbxProducto.isSelected())
            {
                actualizarPendienteCondicional(mes,codMat,true,true,true,true,false,sinVencer);
                actualizarBoletaCondicional(codMat,true,true,true,true,false);
                ce.ActualizaTodo(this); 
                tblPendiente.getColumnModel().getColumn( 3 ).setCellEditor(new MyTableCellEditor());
            }
            //MATRICULA-ALIMENTACION-PRODUCTO-SERVICIO
            else if(cbxMatricula.isSelected() && cbxMensualidad.isSelected() && 
                    cbxProducto.isSelected() && cbxServicio.isSelected())
            {
                actualizarPendienteCondicional(mes,codMat,true,false,true,true,true,sinVencer);
                actualizarBoletaCondicional(codMat,true,false,true,true,true);
                ce.ActualizaTodo(this); 
                tblPendiente.getColumnModel().getColumn( 3 ).setCellEditor(new MyTableCellEditor());
            }
            //MATRICULA-MENSUALIDAD-PRODUCTO-SERVICIO
            else if(cbxMatricula.isSelected() && cbxMensualidad.isSelected() && 
                    cbxProducto.isSelected() && cbxServicio.isSelected())
            {
                actualizarPendienteCondicional(mes,codMat,true,true,false,true,true,sinVencer);
                actualizarBoletaCondicional(codMat,true,true,false,true,true);
                ce.ActualizaTodo(this); 
                tblPendiente.getColumnModel().getColumn( 3 ).setCellEditor(new MyTableCellEditor());
            }
            //MATRICULA-MENSUALIDAD-ALIMENTACION-SERVICIO
            else if(cbxMatricula.isSelected() && cbxMensualidad.isSelected() && 
                    cbxAlimentacion.isSelected() && cbxServicio.isSelected())
            {
                actualizarPendienteCondicional(mes,codMat,true,true,true,false,true,sinVencer);
                actualizarBoletaCondicional(codMat,true,true,true,false,true);
                ce.ActualizaTodo(this); 
                tblPendiente.getColumnModel().getColumn( 3 ).setCellEditor(new MyTableCellEditor());
            }
            //MENSUALIDAD-ALIMENTACION-PRODUCTO-SERVICIO
            else if(cbxMensualidad.isSelected() && cbxAlimentacion.isSelected() && 
                    cbxProducto.isSelected() && cbxServicio.isSelected())
            {
                actualizarPendienteCondicional(mes,codMat,false,true,true,true,true,sinVencer);
                actualizarBoletaCondicional(codMat,false,true,true,true,true);
                ce.ActualizaTodo(this); 
                tblPendiente.getColumnModel().getColumn( 3 ).setCellEditor(new MyTableCellEditor());
            }

            //COMBINACIONES DE 3
            //MATRICULA-MENSUALIDAD-ALIMENTACION
            else if(cbxMatricula.isSelected() && cbxMensualidad.isSelected() && 
                    cbxAlimentacion.isSelected())
            {
                actualizarPendienteCondicional(mes,codMat,true,true,true,false,false,sinVencer);
                actualizarBoletaCondicional(codMat,true,true,true,false,false);
                ce.ActualizaTodo(this); 
                tblPendiente.getColumnModel().getColumn( 3 ).setCellEditor(new MyTableCellEditor());
            }
            //MATRICULA-ALIMENTACION-PRODUCTO
            else if(cbxMatricula.isSelected() && cbxAlimentacion.isSelected() && 
                    cbxProducto.isSelected())
            {
                actualizarPendienteCondicional(mes,codMat,true,false,true,true,false,sinVencer);
                actualizarBoletaCondicional(codMat,true,false,true,true,false);
                ce.ActualizaTodo(this); 
                tblPendiente.getColumnModel().getColumn( 3 ).setCellEditor(new MyTableCellEditor());
            }
            //MATRICULA-MENSUALIDAD-PRODUCTO
            else if(cbxMatricula.isSelected() && cbxMensualidad.isSelected() && 
                    cbxProducto.isSelected())
            {
                actualizarPendienteCondicional(mes,codMat,true,true,false,true,false,sinVencer);
                actualizarBoletaCondicional(codMat,true,true,false,true,false);
                ce.ActualizaTodo(this); 
                tblPendiente.getColumnModel().getColumn( 3 ).setCellEditor(new MyTableCellEditor());
            }
            //MATRICULA-MENSUALIDAD-SERVICIO
            else if(cbxMatricula.isSelected() && cbxMensualidad.isSelected() && 
                    cbxServicio.isSelected())
            {
                actualizarPendienteCondicional(mes,codMat,true,true,false,false,true,sinVencer);
                actualizarBoletaCondicional(codMat,true,true,false,false,true);
                ce.ActualizaTodo(this); 
                tblPendiente.getColumnModel().getColumn( 3 ).setCellEditor(new MyTableCellEditor());
            }
            //MENSUALIDAD-ALIMENTACION-PRODUCTO
            else if(cbxMensualidad.isSelected() && cbxAlimentacion.isSelected() && 
                    cbxProducto.isSelected())
            {
                actualizarPendienteCondicional(mes,codMat,false,true,true,true,false,sinVencer);
                actualizarBoletaCondicional(codMat,false,true,true,true,false);
                ce.ActualizaTodo(this); 
                tblPendiente.getColumnModel().getColumn( 3 ).setCellEditor(new MyTableCellEditor());
            }
            //ALIMENTACION-PRODUCTO-SERVICIO
            else if(cbxAlimentacion.isSelected() && cbxProducto.isSelected() &&
                    cbxServicio.isSelected())
            {
                actualizarPendienteCondicional(mes,codMat,false,false,true,true,true,sinVencer);
                actualizarBoletaCondicional(codMat,false,false,true,true,true);
                ce.ActualizaTodo(this); 
                tblPendiente.getColumnModel().getColumn( 3 ).setCellEditor(new MyTableCellEditor());
            }
            //MENSUALIDAD-PRODUCTO-SERVICIO
            else if(cbxMensualidad.isSelected() && cbxProducto.isSelected() &&
                    cbxServicio.isSelected())
            {
                actualizarPendienteCondicional(mes,codMat,false,true,false,true,true,sinVencer);
                actualizarBoletaCondicional(codMat,false,true,false,true,true);
                ce.ActualizaTodo(this); 
                tblPendiente.getColumnModel().getColumn( 3 ).setCellEditor(new MyTableCellEditor());
            }
            //MATRICULA-PRODUCTO-SERVICIO
            else if(cbxMatricula.isSelected() && cbxProducto.isSelected() &&
                    cbxServicio.isSelected())
            {
                actualizarPendienteCondicional(mes,codMat,true,false,false,true,true,sinVencer);
                actualizarBoletaCondicional(codMat,true,false,false,true,true);
                ce.ActualizaTodo(this); 
                tblPendiente.getColumnModel().getColumn( 3 ).setCellEditor(new MyTableCellEditor());
            }
            //MATRICULA-ALIMENTACION-SERVICIO
            else if(cbxMatricula.isSelected() && cbxAlimentacion.isSelected()  &&
                    cbxServicio.isSelected())
            {
                actualizarPendienteCondicional(mes,codMat,true,false,true,false,true,sinVencer);
                actualizarBoletaCondicional(codMat,true,false,true,false,true);
                ce.ActualizaTodo(this); 
                tblPendiente.getColumnModel().getColumn( 3 ).setCellEditor(new MyTableCellEditor());
            }
            //MENSUALIDAD-ALIMENTACION-SERVICIO
            else if(cbxMensualidad.isSelected() && cbxAlimentacion.isSelected() &&
                    cbxServicio.isSelected())
            {
                actualizarPendienteCondicional(mes,codMat,false,true,true,false,true,sinVencer);
                actualizarBoletaCondicional(codMat,false,true,true,false,true);
                ce.ActualizaTodo(this); 
                tblPendiente.getColumnModel().getColumn( 3 ).setCellEditor(new MyTableCellEditor());
            }

            //COMBINACIÓNes DE 2
            //MATRICULA-MENSUALIDAD
            else if(cbxMatricula.isSelected() && cbxMensualidad.isSelected())
            {
                actualizarPendienteCondicional(mes,codMat,true,true,false,false,false,sinVencer);
                actualizarBoletaCondicional(codMat,true,true,false,false,false);
                ce.ActualizaTodo(this); 
                tblPendiente.getColumnModel().getColumn( 3 ).setCellEditor(new MyTableCellEditor());
            }
            //MATRICULA-ALIMENTACION
            else if(cbxMatricula.isSelected() && cbxAlimentacion.isSelected() )
            {
                actualizarPendienteCondicional(mes,codMat,true,false,true,false,false,sinVencer);
                actualizarBoletaCondicional(codMat,true,false,true,false,false);
                ce.ActualizaTodo(this); 
                tblPendiente.getColumnModel().getColumn( 3 ).setCellEditor(new MyTableCellEditor());
            }
            //MATRICULA-PRODUCTO
            else if(cbxMatricula.isSelected() && cbxProducto.isSelected())
            {
                actualizarPendienteCondicional(mes,codMat,true,false,false,true,false,false);
                actualizarBoletaCondicional(codMat,true,false,false,true,false);
                ce.ActualizaTodo(this); 
                tblPendiente.getColumnModel().getColumn( 3 ).setCellEditor(new MyTableCellEditor());
            }
            //MATRICULA-SERVICIO
            else if(cbxMatricula.isSelected() && cbxServicio.isSelected())
            {
                actualizarPendienteCondicional(mes,codMat,true,false,false,false,true,false);
                actualizarBoletaCondicional(codMat,true,false,false,false,true);
                ce.ActualizaTodo(this); 
                tblPendiente.getColumnModel().getColumn( 3 ).setCellEditor(new MyTableCellEditor());
            }
            //MENSUALIDAD-ALIMENTACION
            else if(cbxMensualidad.isSelected() && cbxAlimentacion.isSelected())
            {
                actualizarPendienteCondicional(mes,codMat,false,true,true,false,false,sinVencer);
                actualizarBoletaCondicional(codMat,false,true,true,false,false);
                ce.ActualizaTodo(this); 
                tblPendiente.getColumnModel().getColumn( 3 ).setCellEditor(new MyTableCellEditor());
            }
            //MENSUALIDAD-PRODUCTO
            else if(cbxMensualidad.isSelected() && cbxProducto.isSelected())
            {
                actualizarPendienteCondicional(mes,codMat,false,true,false,true,false,sinVencer);
                actualizarBoletaCondicional(codMat,false,true,false,true,false);
                ce.ActualizaTodo(this); 
                tblPendiente.getColumnModel().getColumn( 3 ).setCellEditor(new MyTableCellEditor());
            }
            //MENSUALIDAD-SERVICIO
            else if(cbxMensualidad.isSelected() && cbxServicio.isSelected())
            {
                actualizarPendienteCondicional(mes,codMat,false,true,false,false,true,sinVencer);
                actualizarBoletaCondicional(codMat,false,true,false,false,true);
                ce.ActualizaTodo(this); 
                tblPendiente.getColumnModel().getColumn( 3 ).setCellEditor(new MyTableCellEditor());
            }
            //ALIMENTACION-PRODUCTO
            else if(cbxAlimentacion.isSelected() && cbxProducto.isSelected()) 
            {
                actualizarPendienteCondicional(mes,codMat,false,false,true,true,false,sinVencer);
                actualizarBoletaCondicional(codMat,false,false,true,true,false);
                ce.ActualizaTodo(this); 
                tblPendiente.getColumnModel().getColumn( 3 ).setCellEditor(new MyTableCellEditor());
            }
            //ALIMENTACION-SERVICIO
            else if(cbxAlimentacion.isSelected() && cbxServicio.isSelected())
            {
                actualizarPendienteCondicional(mes,codMat,false,false,true,false,true,sinVencer);
                actualizarBoletaCondicional(codMat,false,false,true,false,true);
                ce.ActualizaTodo(this); 
                tblPendiente.getColumnModel().getColumn( 3 ).setCellEditor(new MyTableCellEditor());
            }
            //PRODUCTO-SERVICIO
            else if(cbxProducto.isSelected() && cbxServicio.isSelected())
            {
                actualizarPendienteCondicional(mes,codMat,false,false,false,true,true,sinVencer);
                actualizarBoletaCondicional(codMat,false,false,false,true,true);
                ce.ActualizaTodo(this); 
                tblPendiente.getColumnModel().getColumn( 3 ).setCellEditor(new MyTableCellEditor());
            }

            //SIN COMBINACION        
            else if(cbxMatricula.isSelected())
            {
                actualizarPendienteCondicional(mes,codMat,true,false,false,false,false,sinVencer);
                actualizarBoletaCondicional(codMat,true,false,false,false,false);
                ce.ActualizaTodo(this); 
                tblPendiente.getColumnModel().getColumn( 3 ).setCellEditor(new MyTableCellEditor());
            }
            else if(cbxMensualidad.isSelected())
            {
                actualizarPendienteCondicional(mes,codMat,false,true,false,false,false,sinVencer);
                actualizarBoletaCondicional(codMat,false,true,false,false,false);
                ce.ActualizaTodo(this); 
                tblPendiente.getColumnModel().getColumn( 3 ).setCellEditor(new MyTableCellEditor());
            }
            else if(cbxAlimentacion.isSelected())
            {
                actualizarPendienteCondicional(mes,codMat,false,false,true,false,false,sinVencer);                
                actualizarBoletaCondicional(codMat,false,false,true,false,false);
                ce.ActualizaTodo(this); 
                tblPendiente.getColumnModel().getColumn( 3 ).setCellEditor(new MyTableCellEditor());
            }
            else if(cbxProducto.isSelected())
            {
                actualizarPendienteCondicional(mes,codMat,false,false,false,true,false,sinVencer);
                actualizarBoletaCondicional(codMat,false,false,false,true,false);
                ce.ActualizaTodo(this); 
                tblPendiente.getColumnModel().getColumn( 3 ).setCellEditor(new MyTableCellEditor());
            }
            else if(cbxServicio.isSelected())
            {
                actualizarPendienteCondicional(mes,codMat,false,false,false,false,true,sinVencer);
                actualizarBoletaCondicional(codMat,false,false,false,false,true);
                ce.ActualizaTodo(this); 
                tblPendiente.getColumnModel().getColumn( 3 ).setCellEditor(new MyTableCellEditor());
            }
            else
            {
                cbxTodo.setSelected(true);
            }
        }
        
        private String codigoBoleta()
        {
            con = ClsConexion.getConection();
            ClsMatricula matricula = new ClsMatricula();
            String correlativo;
            correlativo = matricula.obtenSerie(con);
            ClsConexion.closeConnection(con);
            return correlativo;
        }
        private double calculaSeleccionadoTotal()
        {
            int seleccionado[] = tblPendiente.getSelectedRows();
            int i;            
            double aux=0;
            int limite;
            limite = seleccionado.length;      
            //almacenamos las filas seleccionadas en un ArrayList
            for (i=0; i < limite;i++)
            {                
                //variable aux va sumando el pendiente o total que es la misma cantidad
                aux = aux +  Double.parseDouble((String) modelo.getValueAt(seleccionado[i], 3));       
            }
            
            return aux;
        }
        
        private void generarPagos(String fecha) {   
            
            String optionsBanco [] = {"SI","NO"};
            int chosenOption = JOptionPane.showOptionDialog(null, "¿El pago se realizó a través del banco?", "Tipo de pago", 
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,null,optionsBanco,optionsBanco[0]);
            
            //pickea SI
            if(chosenOption == 0) {
                
                //PROCESO DE PAGO DE BANCO
                
                String numeroOperacion = JOptionPane.showInputDialog(null, "Por favor, ingrese el número de operación", "Verificación de operación", JOptionPane.INFORMATION_MESSAGE);
                
                if((numeroOperacion != null) && (!numeroOperacion.equals(""))) {
                    
                    if(!NumeroOperacionRepetido(numeroOperacion) ) {
                    
                        //PAGA LA DEUDA NORMALMENTE
                        //REGISTRA EL NUMERO DE OPERACION Y EL TIPO DE PAGO                    
                        pagarDeudaBanco(fecha,numeroOperacion);
                    
                    }
                    else {

                        JOptionPane.showMessageDialog(null, "El número de operación ya ha sido registrado","Operación repetida",JOptionPane.ERROR_MESSAGE);
                    }
                }
                System.out.println("hola");
                
            }
            //pickea NO
            else if(chosenOption == 1) {
                
                //PROCESO NORMAL
                pagarDeuda(fecha);
                
            }
                       
        }
        
        private void pagarDeudaBanco(String fecha,String numeroOperacion) {
            
            int seleccionado[] = tblPendiente.getSelectedRows();
            int i,j;
            ArrayList<ClsEntidadDeudas> DeudaAlumno = new ArrayList<>();
            ArrayList<ClsEntidadDeudasTiny> arrayVencimiento = new ArrayList<>();
            ArrayList<String> arrayConcepto = new ArrayList<>();
            double aux=0;
            
            int limite;
            limite = seleccionado.length;            
            ClsDeudas cd = new ClsDeudas(); 
            
            //Pagos seleccionados            
            for (i=0; i < limite;i++) {                
                
                DeudaAlumno.add(cd.produceBoleta(
                    (String) modelo.getValueAt(seleccionado[i], 0),     //concepto
                    (String) modelo.getValueAt(seleccionado[i], 2),     //total
                    (String) modelo.getValueAt(seleccionado[i], 3),     //Tot. a pagar o importe
                    (String) modelo.getValueAt(seleccionado[i], 4),     //vencimiento
                    (String) modelo.getValueAt(seleccionado[i], 5),     //Tipo
                    (String) modelo.getValueAt(seleccionado[i], 6)));   //Id Deuda
                //variable aux va sumando el pendiente o total que es la misma cantidad
                aux = aux +  Double.parseDouble((String) modelo.getValueAt(seleccionado[i], 3));                

            }
            
            String fechaEUA = "";
            try {            
                fechaEUA = ConvertirEUtoEUAstring(fecha);
            } catch (ParseException ex) {
                Logger.getLogger(FrmPagoTodo.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Agregar boleta con codigo de operacion   
            PagoDAL pagoDAL = new PagoDAL();
            String codigoBoleta = codigoBoleta();
            con = ClsConexion.getConection();
            pagoDAL.GuardarComprobante(codigoBoleta, fechaEUA, aux, "banco", numeroOperacion, codMat, con);
                        
            //Obtener boleta ingresada
            ClsMisc misc = new ClsMisc();
            String idBoletaP = "";
            try 
            {                
                idBoletaP = misc.UltimoIdInsertado2(con);
            } catch (Exception ex) 
            {
                Logger.getLogger(FrmPagoTodo.class.getName()).log(Level.SEVERE, null, ex);
            }
                        
            //Agregar detalle boleta
            ClsEntidadDetalleBoleta EntDetalleBoleta = new ClsEntidadDetalleBoleta();
            ClsDetalleBoleta DetalleBoleta = new ClsDetalleBoleta();
            DecimalFormat formato = new DecimalFormat("0.00");
            
            String anioPeriodo = cd.ObtenerAnioPeriodoDeBoleta(idBoletaP, con);
            ClsConexion.closeConnection(con);
            for(ClsEntidadDeudas k: DeudaAlumno) {
                
                int cantidadd = 1;
                String descripciond;
                if(k.Tipo.equals("mensualidad")) {
                    descripciond=k.Concepto+" "+anioPeriodo;
                }
                else {
                    descripciond=k.Concepto;
                }
                
                double unitariod = Double.parseDouble(k.Unitario);
                double totald = unitariod * cantidadd;
                double imported = Double.parseDouble(k.Importe);
                System.out.println("----------------------");
                System.out.println(imported+" importe"); 
                double pendiented = Double.parseDouble(formato.format(totald - imported).replaceAll(",", "."));
                String tipod = k.Tipo;
                String idDeudad = k.Id_Deuda;
               
                // Cantidad / Descripcion / Unitario / Importe / Id Boleta
                EntDetalleBoleta.setCantidad_Detalle(cantidadd);
                EntDetalleBoleta.setDescripcion_Detalle(descripciond);
                //unitario se obtiene de Total porque cantidad = 1
                EntDetalleBoleta.setUnitario_Detalle(totald);
                //el importe que se abonará es la deuda (pendiente)
                EntDetalleBoleta.setImporte_Detalle(imported);  
                //tipo deuda
                EntDetalleBoleta.setTipo_Detalle(tipod);
                //se agrega el id Boleta obtenido en el resultset
                EntDetalleBoleta.setId_Boleta(idBoletaP);
                //id deuda
                EntDetalleBoleta.setId_Deuda(idDeudad);          
                con = ClsConexion.getConection();
                DetalleBoleta.AgregarDetalleBoleta(EntDetalleBoleta,con);   
                
                ClsConexion.closeConnection(con); 
                
                //Editar pendientes
                switch (tipod) 
                {
                    case "matricula":
                        //declaraciones matricula                        
                        ClsMatMen matricula = new ClsMatMen();     
                        con = ClsConexion.getConection();
                        matricula.ModificarPendienteMatricula(idDeudad, pendiented,con);
                        ClsConexion.closeConnection(con); 
                        break;
                    case "mensualidad":
                        //declaraciones mensualidad                    
                        ClsMatMen mensualidad = new ClsMatMen(); 
                        con = ClsConexion.getConection();
                        mensualidad.ModificarPendienteMensualidad(idDeudad,pendiented,con);
                        ClsConexion.closeConnection(con); 
                        break;
                    case "comida": 
                        //declaraciones alimentacion                        
                        ClsMatMen comida = new ClsMatMen();     
                        con = ClsConexion.getConection();
                        comida.ModificarPendienteComida(idDeudad,pendiented,con);
                        ClsConexion.closeConnection(con); 
                        break;
                    case "producto":
                        //declaraciones producto                         
                        ClsDeudaProd Producto = new ClsDeudaProd(); 
                        con = ClsConexion.getConection();
                        Producto.ModificarPendienteProducto(idDeudad, pendiented,con);
                        ClsConexion.closeConnection(con);
                        break;
                    case "servicio":
                        //declariociones servicio                        
                        ClsDeudaServ Servicio = new ClsDeudaServ();
                        con = ClsConexion.getConection();
                        Servicio.ModificarPendienteServicio(idDeudad, pendiented,con);
                        ClsConexion.closeConnection(con);
                        break;
                }
            }
            
            JOptionPane.showMessageDialog(this, "Pagos Registrados Exitosamente");
            
            //Aumentar correlativo
            
            ClsMatricula mat = new ClsMatricula();
            con = ClsConexion.getConection();
            int corr = Integer.parseInt(codigoBoleta.substring(6));
            mat.ModificaCorrelativo(String.valueOf(corr),con);
            ClsConexion.closeConnection(con);
            this.actualiza();
            
            //Generar reporte
            Map p = new HashMap();
            //parametros de boleta
            p.put("nombre", alumno);
            p.put("fecha", fecha);            
            p.put("codigo", codigoBoleta);
            p.put("monto", String.valueOf(aux));
            p.put("operacion",numeroOperacion);
            
            String  urlI ="/Reportes/RptImpresionBoletaDetalle.jrxml";            
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(DeudaAlumno);
            try
            {    
                JasperDesign jasperDesign = JRXmlLoader.load(System.getProperty("user.dir")+urlI); 
                JasperReport ReportMain = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint print = JasperFillManager.fillReport(ReportMain, p, ds);                
                print.setPageHeight(390);
                
                PrinterJob printerJob = PrinterJob.getPrinterJob();
                PageFormat pageFormat = PrinterJob.getPrinterJob().defaultPage();
                printerJob.defaultPage(pageFormat);

                int selectedService = 0;

                AttributeSet attributeSet = new HashPrintServiceAttributeSet(new PrinterName("WebPrintService", null));

                PrintService[] printService = PrintServiceLookup.lookupPrintServices(null, attributeSet);

                 try {
                    printerJob.setPrintService(printService[selectedService]);

                } catch (PrinterException e) {

                    System.out.println(e);
                }
                JRPrintServiceExporter exporter;
                PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
                printRequestAttributeSet.add(MediaSizeName.ISO_A4);
                printRequestAttributeSet.add(new Copies(1));

                // these are deprecated
                exporter = new JRPrintServiceExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, printService[selectedService]);
                exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printService[selectedService].getAttributes());
                exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
                exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
                exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
                exporter.exportReport();
                //JasperPrintManager.printReport(print, true);                                  
            }
            catch(JRException ex)
            {
                JOptionPane.showMessageDialog(this, ex);
                Logger.getLogger(FrmEntregablesDocente.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        private boolean NumeroOperacionRepetido(String numeroOperacion){
            con = ClsConexion.getConection();
            PagoModelo pagoModelo = new PagoModelo();
            boolean numeroOperacionRepetido;
            
            //llena un array con los codigos de operacion por periodo
            String idPeriodo = codigoPeriodo.get(cmbPeriodo.getSelectedIndex());
            
            numeroOperacionRepetido = pagoModelo.ComprobarCodigoOperacionOperacion(numeroOperacion, idPeriodo, con);
            ClsConexion.closeConnection(con);
            return numeroOperacionRepetido;
        }
        
        private void pagarDeuda(String fecha) {
            
            int seleccionado[] = tblPendiente.getSelectedRows();
            int i,j;
            ArrayList<ClsEntidadDeudas> DeudaAlumno = new ArrayList<>();
            ArrayList<ClsEntidadDeudasTiny> arrayVencimiento = new ArrayList<>();
            ArrayList<String> arrayConcepto = new ArrayList<>();
            double aux=0;
            boolean pagoPermitido=true;
            int limite;
            limite = seleccionado.length;            
            ClsDeudas cd = new ClsDeudas(); 
            
            //Comprobar las fechas de vencimiento
            //agrego vencimiento y tipo al array.
            for(i=0; i < limite;i++)
            {
                arrayVencimiento.add(new ClsEntidadDeudasTiny(
                        (String) modelo.getValueAt(seleccionado[i], 4), 
                        (String) modelo.getValueAt(seleccionado[i], 5)
                ));
            }
            
            try 
            {
                //Comparamos fechas
                SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                Date fecha1;
                Date fecha2;
                //arrayVencimiento tiene los seleccionados
                int auxCount=0;
                for(ClsEntidadDeudasTiny e : arrayVencimiento)
                {

                    fecha1 = formateador.parse(e.getVencimiento());
                    
                    for(i=0; i < modelo.getRowCount(); i++)
                    {
                        fecha2 = formateador.parse((String) modelo.getValueAt(i, 4));
                        
                        //el pago seleccionado aun no vence
                        if(fecha1.after(fecha2))
                        {
                            //agregar pagos primordiales a pagar
                            arrayConcepto.add((String) modelo.getValueAt(i, 0));                            
                        }                        
                        
                    }
                    auxCount++;
                } 
                
                pagoPermitido = arrayConcepto.isEmpty();
            } 
            catch (ParseException ex) 
            {
                Logger.getLogger(FrmPagoTodo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //si el pago seleccionado a vencido, permitir pagar
            if(pagoPermitido)
            {
                //almacenamos las filas seleccionadas en un ArrayList
                for (i=0; i < limite;i++)
                {// {"CONCEPTO","TOTAL","PENDIENTE","TOT. A PAGAR","FEC. VENCIMIENTO","TIPO","ID_DEUDA","MES"};
                    DeudaAlumno.add(cd.produceBoleta(
                        (String) modelo.getValueAt(seleccionado[i], 0),     //concepto
                        (String) modelo.getValueAt(seleccionado[i], 2),     //total
                        (String) modelo.getValueAt(seleccionado[i], 3),     //Tot. a pagar o importe
                        (String) modelo.getValueAt(seleccionado[i], 4),     //vencimiento
                        (String) modelo.getValueAt(seleccionado[i], 5),     //Tipo
                        (String) modelo.getValueAt(seleccionado[i], 6)));   //Id Deuda
                    //variable aux va sumando el pendiente o total que es la misma cantidad
                    aux = aux +  Double.parseDouble((String) modelo.getValueAt(seleccionado[i], 3));                

                }

                //AGREGAR BOLETA 
                // Numero Boleta / Cliente / Fecha / Total
                ClsEntidadBoleta EntBoleta = new ClsEntidadBoleta();
                ClsBoleta Boleta = new ClsBoleta();
                //---------------------------------//
                String codigoBoleta = codigoBoleta();
                EntBoleta.setNumero_Boleta(codigoBoleta);            
                EntBoleta.setFecha_Boleta(fecha);
                System.out.println("fecha boleta: "+fecha);
                EntBoleta.setTotal_Boleta(aux);
                EntBoleta.setId_Matricula(codMat);
                con = ClsConexion.getConection();
                Boleta.AgregarBoleta(EntBoleta,con);
                //--------------------------------//  
                ClsMisc misc = new ClsMisc();
                String idBoletaP = "";
                try 
                {                
                    idBoletaP = misc.UltimoIdInsertado2(con);
                } catch (Exception ex) 
                {
                    Logger.getLogger(FrmPagoTodo.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //-----------------------------------------------------------------------//
                ClsEntidadDetalleBoleta EntDetalleBoleta = new ClsEntidadDetalleBoleta();
                ClsDetalleBoleta DetalleBoleta = new ClsDetalleBoleta();
                DecimalFormat formato = new DecimalFormat("0.00");
                //-----------------------------------------------------------------------//
                String anioPeriodo = cd.ObtenerAnioPeriodoDeBoleta(idBoletaP, con);
                ClsConexion.closeConnection(con);
                for(ClsEntidadDeudas k: DeudaAlumno) // las filas seleccionadas
                {

                    int cantidadd = 1;
                    String descripciond;
                    if(k.Tipo.equals("mensualidad")) {
                        descripciond=k.Concepto+" "+anioPeriodo;
                    }
                    else {
                        descripciond=k.Concepto;
                    }
                    double unitariod = Double.parseDouble(k.Unitario);
                    double totald = unitariod * cantidadd;
                    double imported = Double.parseDouble(k.Importe);
                    System.out.println("----------------------");
                    System.out.println(imported+" importe"); 
                    System.out.println(descripciond+" descripcion"); 
                    double pendiented = Double.parseDouble(formato.format(totald - imported).replaceAll(",", "."));
                    String tipod = k.Tipo;
                    String idDeudad = k.Id_Deuda;
                    //AGREGAR DETALLE BOLETA
                    // Cantidad / Descripcion / Unitario / Importe / Id Boleta
                    EntDetalleBoleta.setCantidad_Detalle(cantidadd);
                    EntDetalleBoleta.setDescripcion_Detalle(descripciond);
                    //unitario se obtiene de Total porque cantidad = 1
                    EntDetalleBoleta.setUnitario_Detalle(totald);
                    //el importe que se abonará es la deuda (pendiente)
                    EntDetalleBoleta.setImporte_Detalle(imported);  
                    //tipo deuda
                    EntDetalleBoleta.setTipo_Detalle(tipod);
                    //se agrega el id Boleta obtenido en el resultset
                    EntDetalleBoleta.setId_Boleta(idBoletaP);
                    //id deuda
                    EntDetalleBoleta.setId_Deuda(idDeudad);
                    con = ClsConexion.getConection();
                    DetalleBoleta.AgregarDetalleBoleta(EntDetalleBoleta,con);   
                    ClsConexion.closeConnection(con);

                    //MODIFICAR PENDIENTES

                    switch (tipod) 
                    {
                        case "matricula":
                            //declaraciones matricula                        
                            ClsMatMen matricula = new ClsMatMen();     
                            con = ClsConexion.getConection();
                            matricula.ModificarPendienteMatricula(idDeudad, pendiented,con);
                            ClsConexion.closeConnection(con); 
                            break;
                        case "mensualidad":
                            //declaraciones mensualidad                    
                            ClsMatMen mensualidad = new ClsMatMen(); 
                            con = ClsConexion.getConection();
                            mensualidad.ModificarPendienteMensualidad(idDeudad,pendiented,con);
                            ClsConexion.closeConnection(con); 
                            break;
                        case "comida": 
                            //declaraciones alimentacion                        
                            ClsMatMen comida = new ClsMatMen();     
                            con = ClsConexion.getConection();
                            comida.ModificarPendienteComida(idDeudad,pendiented,con);
                            ClsConexion.closeConnection(con); 
                            break;
                        case "producto":
                            //declaraciones producto                         
                            ClsDeudaProd Producto = new ClsDeudaProd(); 
                            con = ClsConexion.getConection();
                            Producto.ModificarPendienteProducto(idDeudad, pendiented,con);
                            ClsConexion.closeConnection(con);
                            break;
                        case "servicio":
                            //declariociones servicio                        
                            ClsDeudaServ Servicio = new ClsDeudaServ();
                            con = ClsConexion.getConection();
                            Servicio.ModificarPendienteServicio(idDeudad, pendiented,con);
                            ClsConexion.closeConnection(con);
                            break;
                    }
                }

                JOptionPane.showMessageDialog(this, "Pagos Registrados Exitosamente");

                //AUMENTAR CORRELATIVO DE LA BOLETA
                ClsMatricula mat = new ClsMatricula();
                con = ClsConexion.getConection();
                int corr = Integer.parseInt(codigoBoleta.substring(6));
                mat.ModificaCorrelativo(String.valueOf(corr),con);
                ClsConexion.closeConnection(con);
                this.actualiza();
                //GENERAR REPORTE E IMPRIMIR
                //REPORTE
                Map p = new HashMap();
                //parametros de boleta
                p.put("nombre", alumno);
                p.put("fecha", fecha);            
                p.put("codigo", codigoBoleta);
                p.put("monto", String.valueOf(aux));

                String  urlI ="/Reportes/RptImpresionBoletaCaja.jrxml";            
                JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(DeudaAlumno);
                try
                {    
                    JasperDesign jasperDesign = JRXmlLoader.load(System.getProperty("user.dir")+urlI); 
                    JasperReport ReportMain = JasperCompileManager.compileReport(jasperDesign);
                    JasperPrint print = JasperFillManager.fillReport(ReportMain, p, ds);                
                    print.setPageHeight(390);
                    
                    PrinterJob printerJob = PrinterJob.getPrinterJob();
                    PageFormat pageFormat = PrinterJob.getPrinterJob().defaultPage();
                    printerJob.defaultPage(pageFormat);
                    
                    int selectedService = 0;

                    AttributeSet attributeSet = new HashPrintServiceAttributeSet(new PrinterName("WebPrintService", null));

                    PrintService[] printService = PrintServiceLookup.lookupPrintServices(null, attributeSet);
                    
                     try {
                        printerJob.setPrintService(printService[selectedService]);

                    } catch (PrinterException e) {

                        System.out.println(e);
                    }
                    JRPrintServiceExporter exporter;
                    PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
                    printRequestAttributeSet.add(MediaSizeName.ISO_A4);
                    printRequestAttributeSet.add(new Copies(1));

                    // these are deprecated
                    exporter = new JRPrintServiceExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, printService[selectedService]);
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printService[selectedService].getAttributes());
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
                    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
                    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
                    exporter.exportReport();

                    
                    
                    //JasperPrintManager.printReport(print, true);                                  
                }
                catch(JRException ex)
                {
                    JOptionPane.showMessageDialog(this, ex);
                    Logger.getLogger(FrmEntregablesDocente.class.getName()).log(Level.SEVERE, null, ex);
                }  
            }
            else if(pagoPermitido == false)
            {
                String mensaje="";
                String mensajef="</html>";
                //se mencionan los pagos en un mensaje
                for(String s : arrayConcepto)
                {
                    mensaje = mensaje + s + "<br/>";
                }
                mensaje = "<html>Debe realizar primero los siguientes pagos: " +"<br/>"+mensaje;
                
                boolean autorizado=false;
                //Crear un JOptionPane.ShowInputDialog
                JPanel panel = new JPanel();
                JLabel label = new JLabel(mensaje+mensajef);                
                panel.add(label);
                
                String[] options = new String[]{"ACEPTAR", "AUTORIZAR PAGO"};
                int option = JOptionPane.showOptionDialog(null, panel, "Advertencia",
                JOptionPane.NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);
                
                //////////////////////
                if(option == 1) // pressing Autorizar Pago button
                {
                    String pId="";
                    
                    String pFecha;                    
                    pFecha = fechaActual();
                    try 
                    {
                        //Autorizando pago
                        String[] datos = ingresarClave2();
                        
                        //Comparar usuario y clave con BD
                        
                        ClsAcceso acceso = new ClsAcceso();
                        con = ClsConexion.getConection();
                        ResultSet rsAutenticacion;
                        
                        rsAutenticacion = acceso.SeleccionarUsuario(datos[0], con);
                        if(rsAutenticacion != null)
                        {
                            while(rsAutenticacion.next())
                            {
                                if(rsAutenticacion.getString("personal_estado").equals("A"))
                                {
                                    //Pagar
                                    autorizado = DigestUtils.shaHex(datos[1]).equals(rsAutenticacion.getString("personal_password"));                                    
                                    pId = rsAutenticacion.getString("personal_id");
                                }
                            }
                        }
                        ClsConexion.closeConnection(con);
                        
                    } catch (Exception ex) {
                        Logger.getLogger(FrmPagoTodo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    //EL usuario si se identificó, entonces pagar.
                    if(autorizado)
                    {
                        
                        
                        //almacenamos las filas seleccionadas en un ArrayList
                        for (i=0; i < limite;i++)
                        {// {"CONCEPTO","TOTAL","PENDIENTE","TOT. A PAGAR","FEC. VENCIMIENTO","TIPO","ID_DEUDA","MES"};
                            DeudaAlumno.add(cd.produceBoleta(
                                (String) modelo.getValueAt(seleccionado[i], 0),     //concepto
                                (String) modelo.getValueAt(seleccionado[i], 2),     //total
                                (String) modelo.getValueAt(seleccionado[i], 3),     //Tot. a pagar o importe
                                (String) modelo.getValueAt(seleccionado[i], 4),     //vencimiento
                                (String) modelo.getValueAt(seleccionado[i], 5),     //Tipo
                                (String) modelo.getValueAt(seleccionado[i], 6)));   //Id Deuda
                            //variable aux va sumando el pendiente o total que es la misma cantidad
                            aux = aux +  Double.parseDouble((String) modelo.getValueAt(seleccionado[i], 3));                

                            
                            /////////////////////////////////////////////
                            //Colocar en el historial de autorizaciones//
                            con = ClsConexion.getConection();                        
                            ClsPagoHistorial pgoHistorial = new ClsPagoHistorial();
                            ClsEntidadPagoHistorial epgoHistorial = new ClsEntidadPagoHistorial(
                                    (String) modelo.getValueAt(seleccionado[i], 0),
                                    (String) modelo.getValueAt(seleccionado[i], 3),
                                    pFecha, 
                                    pId
                            );
                            pgoHistorial.CrearPagoHistorial(epgoHistorial, con);
                            ClsConexion.closeConnection(con);
                            /////////////////////////////////////////////
                        }

                        //AGREGAR BOLETA 
                        // Numero Boleta / Cliente / Fecha / Total
                        ClsEntidadBoleta EntBoleta = new ClsEntidadBoleta();
                        ClsBoleta Boleta = new ClsBoleta();
                        //---------------------------------//
                        String codigoBoleta = codigoBoleta();
                        EntBoleta.setNumero_Boleta(codigoBoleta);            
                        EntBoleta.setFecha_Boleta(fecha);
                        System.out.println("fecha boleta: "+fecha);
                        EntBoleta.setTotal_Boleta(aux);
                        EntBoleta.setId_Matricula(codMat);
                        con = ClsConexion.getConection();
                        Boleta.AgregarBoleta(EntBoleta,con);
                        //--------------------------------//  
                        ClsMisc misc = new ClsMisc();
                        String idBoletaP = "";
                        try 
                        {                
                            idBoletaP = misc.UltimoIdInsertado2(con);
                        } catch (Exception ex) 
                        {
                            Logger.getLogger(FrmPagoTodo.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        //-----------------------------------------------------------------------//
                        ClsEntidadDetalleBoleta EntDetalleBoleta = new ClsEntidadDetalleBoleta();
                        ClsDetalleBoleta DetalleBoleta = new ClsDetalleBoleta();
                        DecimalFormat formato = new DecimalFormat("0.00");
                        //-----------------------------------------------------------------------//
                        String anioPeriodo = cd.ObtenerAnioPeriodoDeBoleta(idBoletaP, con);
                        ClsConexion.closeConnection(con);
                        for(ClsEntidadDeudas k: DeudaAlumno) // las filas seleccionadas
                        {

                            int cantidadd = 1;
                            String descripciond;
                            if(k.Tipo.equals("mensualidad")) {
                                descripciond=k.Concepto+" "+anioPeriodo;
                            }
                            else {
                                descripciond=k.Concepto;
                            }
                            double unitariod = Double.parseDouble(k.Unitario);
                            double totald = unitariod * cantidadd;
                            double imported = Double.parseDouble(k.Importe);
                            System.out.println("----------------------");
                            System.out.println(imported+" importe"); 
                            double pendiented = Double.parseDouble(formato.format(totald - imported).replaceAll(",", "."));
                            String tipod = k.Tipo;
                            String idDeudad = k.Id_Deuda;
                            //AGREGAR DETALLE BOLETA
                            // Cantidad / Descripcion / Unitario / Importe / Id Boleta
                            EntDetalleBoleta.setCantidad_Detalle(cantidadd);
                            EntDetalleBoleta.setDescripcion_Detalle(descripciond);
                            //unitario se obtiene de Total porque cantidad = 1
                            EntDetalleBoleta.setUnitario_Detalle(totald);
                            //el importe que se abonará es la deuda (pendiente)
                            EntDetalleBoleta.setImporte_Detalle(imported);  
                            //tipo deuda
                            EntDetalleBoleta.setTipo_Detalle(tipod);
                            //se agrega el id Boleta obtenido en el resultset
                            EntDetalleBoleta.setId_Boleta(idBoletaP);
                            //id deuda
                            EntDetalleBoleta.setId_Deuda(idDeudad);
                            con = ClsConexion.getConection();
                            DetalleBoleta.AgregarDetalleBoleta(EntDetalleBoleta,con);   
                            ClsConexion.closeConnection(con);

                            //MODIFICAR PENDIENTES

                            switch (tipod) 
                            {
                                case "matricula":
                                    //declaraciones matricula                        
                                    ClsMatMen matricula = new ClsMatMen();     
                                    con = ClsConexion.getConection();
                                    matricula.ModificarPendienteMatricula(idDeudad, pendiented,con);
                                    ClsConexion.closeConnection(con); 
                                    break;
                                case "mensualidad":
                                    //declaraciones mensualidad                    
                                    ClsMatMen mensualidad = new ClsMatMen(); 
                                    con = ClsConexion.getConection();
                                    mensualidad.ModificarPendienteMensualidad(idDeudad,pendiented,con);
                                    ClsConexion.closeConnection(con); 
                                    break;
                                case "comida": 
                                    //declaraciones alimentacion                        
                                    ClsMatMen comida = new ClsMatMen();     
                                    con = ClsConexion.getConection();
                                    comida.ModificarPendienteComida(idDeudad,pendiented,con);
                                    ClsConexion.closeConnection(con); 
                                    break;
                                case "producto":
                                    //declaraciones producto                         
                                    ClsDeudaProd Producto = new ClsDeudaProd(); 
                                    con = ClsConexion.getConection();
                                    Producto.ModificarPendienteProducto(idDeudad, pendiented,con);
                                    ClsConexion.closeConnection(con);
                                    break;
                                case "servicio":
                                    //declariociones servicio                        
                                    ClsDeudaServ Servicio = new ClsDeudaServ();
                                    con = ClsConexion.getConection();
                                    Servicio.ModificarPendienteServicio(idDeudad, pendiented,con);
                                    ClsConexion.closeConnection(con);
                                    break;
                            }
                        }

                        JOptionPane.showMessageDialog(this, "Pagos Registrados Exitosamente");

                        //AUMENTAR CORRELATIVO DE LA BOLETA
                        ClsMatricula mat = new ClsMatricula();
                        con = ClsConexion.getConection();
                        int corr = Integer.parseInt(codigoBoleta.substring(6));
                        mat.ModificaCorrelativo(String.valueOf(corr),con);
                        ClsConexion.closeConnection(con);
                        this.actualiza();
                        //GENERAR REPORTE E IMPRIMIR
                        //REPORTE
                        Map p = new HashMap();
                        //parametros de boleta
                        p.put("nombre", alumno);
                        p.put("fecha", fecha);            
                        p.put("codigo", codigoBoleta);
                        p.put("monto", String.valueOf(aux));

                        String  urlI ="/Reportes/RptImpresionBoletaCaja.jrxml";            
                        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(DeudaAlumno);
                        try
                        {    
                            JasperDesign jasperDesign = JRXmlLoader.load(System.getProperty("user.dir")+urlI); 
                            JasperReport ReportMain = JasperCompileManager.compileReport(jasperDesign);
                            JasperPrint print = JasperFillManager.fillReport(ReportMain, p, ds);                
                            print.setPageHeight(390);
                            
                            PrinterJob printerJob = PrinterJob.getPrinterJob();
                            PageFormat pageFormat = PrinterJob.getPrinterJob().defaultPage();
                            printerJob.defaultPage(pageFormat);

                            int selectedService = 0;

                            AttributeSet attributeSet = new HashPrintServiceAttributeSet(new PrinterName("WebPrintService", null));

                            PrintService[] printService = PrintServiceLookup.lookupPrintServices(null, attributeSet);

                             try {
                                printerJob.setPrintService(printService[selectedService]);

                            } catch (PrinterException e) {

                                System.out.println(e);
                            }
                            JRPrintServiceExporter exporter;
                            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
                            printRequestAttributeSet.add(MediaSizeName.NA_LETTER);
                            printRequestAttributeSet.add(new Copies(1));

                            // these are deprecated
                            exporter = new JRPrintServiceExporter();
                            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, printService[selectedService]);
                            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printService[selectedService].getAttributes());
                            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
                            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
                            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
                            exporter.exportReport();
                            
                        }
                        catch(JRException ex)
                        {
                            JOptionPane.showMessageDialog(this, ex);
                            Logger.getLogger(FrmEntregablesDocente.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                       
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Credenciales incorrectas","Error de autenticación",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        
        private String obtenerFechaActual() {
            
            Date date = new Date();
            String fechaActual;
            String format = "dd/MM/yyyy";
            SimpleDateFormat formato2 = new SimpleDateFormat(format);            
            fechaActual=formato2.format(date);
            
            return fechaActual;
        }
        
     /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAlumno = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        cmbSeccion = new javax.swing.JComboBox();
        cmbPeriodo = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtBusqueda = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtAlimentacion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtInicioClases = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtHorario = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtFechaPedido = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        lblLinea = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        cbxMatricula = new javax.swing.JCheckBox();
        cbxMensualidad = new javax.swing.JCheckBox();
        cbxAlimentacion = new javax.swing.JCheckBox();
        cbxProducto = new javax.swing.JCheckBox();
        cbxSinVencimiento = new javax.swing.JCheckBox();
        cbxServicio = new javax.swing.JCheckBox();
        cbxTodo = new javax.swing.JCheckBox();
        cbxPagado = new javax.swing.JCheckBox();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPendiente = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txaObservacion = new javax.swing.JTextArea();
        btnGuardar = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnMas = new javax.swing.JButton();
        btnMenos = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        btnQuitar = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        btnNotificacion = new javax.swing.JButton();
        btnNotificacionPeriodo = new javax.swing.JButton();
        btnHistorialCambio = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnGenerarPago = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblBoleta = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        btnImprimirBoleta = new javax.swing.JButton();
        btnModificarBoleta = new javax.swing.JButton();
        btnAnularBoleta = new javax.swing.JButton();
        btnEliminarBoleta = new javax.swing.JButton();

        setTitle("Pagos Generales");

        jScrollPane4.setBorder(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Alumnos"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblAlumno.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        tblAlumno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        tblAlumno.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblAlumno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAlumnoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAlumno);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 320, 450));

        jLabel9.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel9.setText("Seleccione Sección:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, -1, 20));

        cmbSeccion.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbSeccion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSeccionItemStateChanged(evt);
            }
        });
        jPanel2.add(cmbSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 250, -1));

        cmbPeriodo.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cmbPeriodo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbPeriodo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPeriodoItemStateChanged(evt);
            }
        });
        jPanel2.add(cmbPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 250, -1));

        jLabel8.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel8.setText("Seleccione Periodo:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 120, 20));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Búsqueda"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtBusqueda.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtBusqueda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBusquedaMouseClicked(evt);
            }
        });
        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyTyped(evt);
            }
        });
        jPanel4.add(txtBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 240, -1));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 300, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 340, 670));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel1.setText("S. Alimentación:");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 20));

        txtAlimentacion.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtAlimentacion.setEnabled(false);
        jPanel3.add(txtAlimentacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 30, -1));

        jLabel2.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel2.setText("Inicio clases:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 20, -1, 20));

        txtInicioClases.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtInicioClases.setEnabled(false);
        jPanel3.add(txtInicioClases, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 75, -1));

        jLabel3.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel3.setText("Horario:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 20, -1, 20));

        txtHorario.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtHorario.setEnabled(false);
        jPanel3.add(txtHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 65, -1));

        jLabel4.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel4.setText("F. Pedido:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, -1, 20));

        txtFechaPedido.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtFechaPedido.setEnabled(false);
        jPanel3.add(txtFechaPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, 70, -1));

        jLabel5.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel5.setText("Stock:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, -1, 20));

        txtStock.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txtStock.setEnabled(false);
        jPanel3.add(txtStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(573, 20, 30, -1));

        lblLinea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckImages/line.jpg"))); // NOI18N
        jPanel3.add(lblLinea, new org.netbeans.lib.awtextra.AbsoluteConstraints(395, 10, -1, 35));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 620, 50));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Vista"));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbxMatricula.setBackground(new java.awt.Color(255, 255, 255));
        cbxMatricula.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cbxMatricula.setText("Matrícula");
        cbxMatricula.setEnabled(false);
        cbxMatricula.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxMatriculaItemStateChanged(evt);
            }
        });
        jPanel5.add(cbxMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 13, -1, 30));

        cbxMensualidad.setBackground(new java.awt.Color(255, 255, 255));
        cbxMensualidad.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cbxMensualidad.setText("Mensualidad");
        cbxMensualidad.setEnabled(false);
        cbxMensualidad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxMensualidadItemStateChanged(evt);
            }
        });
        jPanel5.add(cbxMensualidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 13, -1, 30));

        cbxAlimentacion.setBackground(new java.awt.Color(255, 255, 255));
        cbxAlimentacion.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cbxAlimentacion.setText("Alimentación");
        cbxAlimentacion.setEnabled(false);
        cbxAlimentacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxAlimentacionItemStateChanged(evt);
            }
        });
        jPanel5.add(cbxAlimentacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 13, -1, 30));

        cbxProducto.setBackground(new java.awt.Color(255, 255, 255));
        cbxProducto.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cbxProducto.setText("Producto");
        cbxProducto.setEnabled(false);
        cbxProducto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxProductoItemStateChanged(evt);
            }
        });
        jPanel5.add(cbxProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 13, -1, 30));

        cbxSinVencimiento.setBackground(new java.awt.Color(255, 255, 255));
        cbxSinVencimiento.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cbxSinVencimiento.setText("Sin vencer");
        cbxSinVencimiento.setEnabled(false);
        cbxSinVencimiento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSinVencimientoItemStateChanged(evt);
            }
        });
        jPanel5.add(cbxSinVencimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 13, -1, 30));

        cbxServicio.setBackground(new java.awt.Color(255, 255, 255));
        cbxServicio.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cbxServicio.setText("Servicio");
        cbxServicio.setEnabled(false);
        cbxServicio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxServicioItemStateChanged(evt);
            }
        });
        jPanel5.add(cbxServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 13, -1, 30));

        cbxTodo.setBackground(new java.awt.Color(255, 255, 255));
        cbxTodo.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cbxTodo.setText("Todos");
        cbxTodo.setEnabled(false);
        cbxTodo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTodoItemStateChanged(evt);
            }
        });
        jPanel5.add(cbxTodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 13, -1, 30));

        cbxPagado.setBackground(new java.awt.Color(255, 255, 255));
        cbxPagado.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        cbxPagado.setText("Pagados");
        cbxPagado.setEnabled(false);
        cbxPagado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxPagadoItemStateChanged(evt);
            }
        });
        jPanel5.add(cbxPagado, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 13, 70, 30));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 620, 50));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Pagos Pendientes"));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblPendiente.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        tblPendiente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "CONCEPTO", "TOTAL", "PENDIENTE", "TOT. A PAGAR", "FEC. VENCIMIENTO"
            }
        ));
        tblPendiente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPendienteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblPendiente);

        jPanel6.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 580, 220));

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel11.setText("TOTAL:");
        jPanel6.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 250, 50, 20));

        txtTotal.setFont(new java.awt.Font("Droid Sans", 1, 11)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(255, 255, 255));
        txtTotal.setCaretColor(new java.awt.Color(255, 255, 255));
        txtTotal.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTotal.setEnabled(false);
        jPanel6.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 250, 120, -1));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 110, 620, 280));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Observación"));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txaObservacion.setColumns(20);
        txaObservacion.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        txaObservacion.setRows(5);
        txaObservacion.setAutoscrolls(false);
        jScrollPane3.setViewportView(txaObservacion);

        jPanel7.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 130, 130));

        btnGuardar.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel7.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 140, 150, 200));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones"));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel6.setText("Total:");
        jPanel8.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, 20));

        jLabel7.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel7.setText("Pendiente:");
        jPanel8.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, 20));

        btnMas.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnMas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/add.png"))); // NOI18N
        btnMas.setToolTipText("Aumentar Monto");
        btnMas.setEnabled(false);
        btnMas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasActionPerformed(evt);
            }
        });
        jPanel8.add(btnMas, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 30, 20));

        btnMenos.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnMenos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/remove.png"))); // NOI18N
        btnMenos.setToolTipText("Quitar Monto");
        btnMenos.setEnabled(false);
        btnMenos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenosActionPerformed(evt);
            }
        });
        jPanel8.add(btnMenos, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 30, 20));

        btnEditar.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/pen.png"))); // NOI18N
        btnEditar.setEnabled(false);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel8.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 30, 20));

        jLabel10.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jLabel10.setText("Quitar serv. o prod.:");
        jPanel8.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, 20));

        btnQuitar.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/erase.png"))); // NOI18N
        btnQuitar.setEnabled(false);
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });
        jPanel8.add(btnQuitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 30, 20));

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 10, 150, 130));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Acciones"));
        jPanel10.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnNotificacion.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnNotificacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/map_warning.png"))); // NOI18N
        btnNotificacion.setText("<html> <p align=center>Generar</p> <p align=center>Notificación</p> </html>");
        btnNotificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNotificacionActionPerformed(evt);
            }
        });
        jPanel10.add(btnNotificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 110, -1));

        btnNotificacionPeriodo.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnNotificacionPeriodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/book_warning.png"))); // NOI18N
        btnNotificacionPeriodo.setText("<html> <p align=center>Notificación</p> <p align=center>por Periodo</p> </html>");
        btnNotificacionPeriodo.setActionCommand("<html> <p align=center>Generar Notificación</p> <p align=center>por Periodo</p> </html>");
        btnNotificacionPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNotificacionPeriodoActionPerformed(evt);
            }
        });
        jPanel10.add(btnNotificacionPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 110, 40));

        btnHistorialCambio.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnHistorialCambio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/edit_page.png"))); // NOI18N
        btnHistorialCambio.setText("<html> <p align=center>Historial</p> <p align=center>Autorizacion</p> </html>");
        btnHistorialCambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistorialCambioActionPerformed(evt);
            }
        });
        jPanel10.add(btnHistorialCambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 110, -1));

        btnSalir.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/eqsl_exit.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel10.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 110, 40));

        btnGenerarPago.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnGenerarPago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/dollar_coin.png"))); // NOI18N
        btnGenerarPago.setText("<html> <p align=center>Generar</p> <p align=center> Pagos</p> </html>");
        btnGenerarPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarPagoActionPerformed(evt);
            }
        });
        jPanel10.add(btnGenerarPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 110, 40));

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 340, 150, 290));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Historial Boletas"));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblBoleta.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        tblBoleta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "N° BOLETA", "DESCRIPCIÓN", "IMPORTE", "FECHA"
            }
        ));
        tblBoleta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBoletaMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblBoleta);

        jPanel11.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 580, 200));

        jPanel1.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 390, 620, 240));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Acciones Boleta"));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnImprimirBoleta.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnImprimirBoleta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/agt_print.png"))); // NOI18N
        btnImprimirBoleta.setText("<html> <p align=center>Imprimir</p> <p align=center>Boleta</p> </html>");
        btnImprimirBoleta.setMaximumSize(new java.awt.Dimension(133, 25));
        btnImprimirBoleta.setMinimumSize(new java.awt.Dimension(133, 25));
        btnImprimirBoleta.setPreferredSize(new java.awt.Dimension(133, 25));
        btnImprimirBoleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirBoletaActionPerformed(evt);
            }
        });
        jPanel9.add(btnImprimirBoleta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 130, 40));

        btnModificarBoleta.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnModificarBoleta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/edit_page.png"))); // NOI18N
        btnModificarBoleta.setText("<html> <p align=center>Modificar </p> <p align=center> Boleta </p> </html>");
        btnModificarBoleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarBoletaActionPerformed(evt);
            }
        });
        jPanel9.add(btnModificarBoleta, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 130, 40));

        btnAnularBoleta.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnAnularBoleta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/map_warning.png"))); // NOI18N
        btnAnularBoleta.setText("<html> <p align=center>Anular</p> <p align=center>Boleta</p> </html>");
        btnAnularBoleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularBoletaActionPerformed(evt);
            }
        });
        jPanel9.add(btnAnularBoleta, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, 130, 40));

        btnEliminarBoleta.setFont(new java.awt.Font("Droid Sans", 0, 11)); // NOI18N
        btnEliminarBoleta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/delete_page.png"))); // NOI18N
        btnEliminarBoleta.setText("<html> <p align=center>Eliminar</p> <p align=center> Boleta</p> </html>");
        btnEliminarBoleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarBoletaActionPerformed(evt);
            }
        });
        jPanel9.add(btnEliminarBoleta, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 130, 40));

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 630, 620, 70));

        jScrollPane4.setViewportView(jPanel1);

        getContentPane().add(jScrollPane4, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblAlumnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAlumnoMouseClicked
        int reg;       
        DefaultTableModel defaultTableModel;
        reg = tblAlumno.getSelectedRow();      
        if(reg == -1)
        {JOptionPane.showMessageDialog(null, "Debe Seleccionar una opción");}
        else
        {
            
            defaultTableModel = (DefaultTableModel)tblAlumno.getModel();
            codMat = ((String)defaultTableModel.getValueAt(reg, 0));            
            System.out.println("idMatricula: "+codMat);
            alumno = (String) defaultTableModel.getValueAt(reg, 2);
            salon = cmbSeccion.getSelectedItem().toString();
            //actualiza campos Servicio Alimentacion - Inicio Clases - Horario
            mes = actualizarDatosGenerales(codMat);
            habilitarCheckBox(true,true,true,true,true,true,true);
            cbxPagado.setEnabled(true);
            cbxTodo.setSelected(true);
            if(cbxTodo.isSelected())
            {
                //actualiza deudas del alumno
                if(cbxSinVencimiento.isSelected())
                {
                    actualizarPendiente(mes,codMat,true);
                }
                else
                {
                    actualizarPendiente(mes,codMat,false);
                }
                
            }
            //agrego un CellEditor            
            ce.ActualizaTodo(this); 
            
            tblPendiente.getColumnModel().getColumn( 3 ).setCellEditor(new MyTableCellEditor());            
            //actualiza boletas del alumno
            actualizarBoleta(codMat);     
            
            //habilita los botones
            activaBotonesAccion(false,false,true,true);
            
            //Llena observación
            
            //LLENA OBSERVACION
            txaObservacion.setText("");
            auxObs=0;
            ResultSet rsObs;
                ClsPgoObservacion obs = new ClsPgoObservacion();
                try {
                    rsObs = obs.SeleccionarObservacion(codMat);
                    while(rsObs.next())
                    {
                        txaObservacion.setText(rsObs.getString("obs_obs"));
                        auxObs++;
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            btnGuardar.setEnabled(true);
        }
    }//GEN-LAST:event_tblAlumnoMouseClicked

    private void cmbSeccionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSeccionItemStateChanged
        if(cmbSeccion.getSelectedIndex() != -1)
        {            
            actualizarTablaAlumno();                 
        }
    }//GEN-LAST:event_cmbSeccionItemStateChanged

    private void cmbPeriodoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPeriodoItemStateChanged
        if(cmbPeriodo.getSelectedIndex() != -1)
        {            
            cargaComboSeccion();
            actualizarTablaAlumno();
        }

    }//GEN-LAST:event_cmbPeriodoItemStateChanged

    private void btnMasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasActionPerformed
        String n;
        String p;
        
        n= ingresarClave();
        p= obtenerClave();       
        
        if(n.equals(p))
        {
            FrmAccesoPago.tipo = tipoDeuda;
            FrmAccesoPago.opcion = "+";
            FrmAccesoPago.tot = total;
            FrmAccesoPago.pend = pendiente;
            FrmAccesoPago.codAlu = codMat;   
            FrmAccesoPago.mesD = mesDeuda;
            FrmAccesoPago.codDeuda = idDeuda;
            llamaCambio();
        }            
            
    }//GEN-LAST:event_btnMasActionPerformed

    private void btnMenosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenosActionPerformed
        String n;
        String p;
        
        n= ingresarClave();
        p= obtenerClave();       
        
        if(n.equals(p))
        {
            FrmAccesoPago.tipo = tipoDeuda;
            FrmAccesoPago.opcion = "-";
            FrmAccesoPago.tot = total;
            FrmAccesoPago.pend = pendiente;
            FrmAccesoPago.codAlu = codMat;   
            FrmAccesoPago.mesD = mesDeuda;
            FrmAccesoPago.codDeuda = idDeuda;
            llamaCambio();
        } 
    }//GEN-LAST:event_btnMenosActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        String n;
        String p;
        
        n= ingresarClave();
        p= obtenerClave();       
        
        if(n.equals(p))
        {
      
            PckInterfaces.FrmEditaPendiente matri= new PckInterfaces.FrmEditaPendiente();
            int x = (dskpPrincipal.getWidth() / 2) - matri.getWidth() / 2;
            int y = (dskpPrincipal.getHeight() / 2) - matri.getHeight() / 2;

            if (matri.isShowing()) {
                matri.setLocation(x, y);
            } else {
                FrmEditaPendiente.codigoDeuda = idDeuda;
                FrmEditaPendiente.tipo = tipoDeuda;
                dskpPrincipal.add(matri);
                matri.editarTodo(this);
                matri.setLocation(x, y);
                matri.show();
            }
        } 

    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        
        String options[] = {"Aceptar","Cancelar"};
        int option = JOptionPane.showOptionDialog(this, "¿Seguro que desea retirar deuda para "+alumno+"?", 
        "Confirmación", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if(option == 0) // pressing OK button
        {        
            switch (tipoDeuda) 
           {
               case "producto":                    
                   
                    //quitar deuda_preoducto (deuda)
                    ClsDeudaProd cdp = new ClsDeudaProd();
                    con = ClsConexion.getConection();
                    cdp.EliminarRelacionProducto(idDeuda, con);
                    cdp.EliminarDeudaProducto(idDeuda,con); 
                    ClsConexion.closeConnection(con);
                    JOptionPane.showMessageDialog(this, "Deuda retirada correctamente");                    
                    if(cbxTodo.isSelected())
                    {
                        //actualiza deudas del alumno
                        if(cbxSinVencimiento.isSelected())
                        {
                            actualizarPendiente(mes,codMat,true);
                        }
                        else
                        {
                            actualizarPendiente(mes,codMat,false);
                        }

                    }
                   break;
               case "servicio":                    
                    
                    //quitar deuda_preoducto (deuda)
                    ClsDeudaServ cds = new ClsDeudaServ();
                    con = ClsConexion.getConection();
                    cds.EliminarRelacionServicio(idDeuda, con);
                    cds.EliminarDeudaServicio(idDeuda,con);                    
                    ClsConexion.closeConnection(con); 
                    JOptionPane.showMessageDialog(this, "Deuda retirada correctamente");
                    
                    if(cbxTodo.isSelected())
                    {
                        //actualiza deudas del alumno
                        if(cbxSinVencimiento.isSelected())
                        {
                            actualizarPendiente(mes,codMat,true);
                        }
                        else
                        {
                            actualizarPendiente(mes,codMat,false);
                        }

                    }
                   break;
           }
        }
    }//GEN-LAST:event_btnQuitarActionPerformed

    private void btnImprimirBoletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirBoletaActionPerformed
       
        ArrayList<ClsEntidadImpresionBoleta> DeudaAlumno;
        ClsBoleta boleta = new ClsBoleta();
        //ClsEntidadImpresionBoleta ib = new ClsEntidadImpresionBoleta();
        String alumnox = null,fechax = null,codigoBoletax = null,auxx = null;   
        String tipopagox=""; String numeroopx=null;
        con = ClsConexion.getConection();
        {
            DeudaAlumno = boleta.SeleccionarBoletaDetalle(idBoleta,con);
        }
        ClsConexion.closeConnection(con);
        con = ClsConexion.getConection();
        {
            ResultSet datos;
            try
            {
                datos = boleta.SeleccionarBoletaUnica(idBoleta,con);
                while(datos.next())
                {
                    alumnox = datos.getString("nombre");
                    fechax = datos.getString("fecha_boleta");
                    codigoBoletax = datos.getString("num_boleta");
                    auxx = datos.getString("total_boleta");
                    tipopagox = datos.getString("tipo_pago");
                    numeroopx = datos.getString("numero_operacion");
                }
            }
            catch(Exception ex)
            {
                System.out.println(ex);
            }
        }
        ClsConexion.closeConnection(con);
        String  urlI="";
        Map p = new HashMap();
        //parametros de boleta
        p.put("nombre", alumnox);
        p.put("fecha", fechax);            
        p.put("codigo", codigoBoletax);
        p.put("monto", auxx);
        if(tipopagox.equals("banco")) {
            p.put("operacion",numeroopx);
            urlI="/Reportes/RptImpresionBoletaDetalle.jrxml";     
        }
        else {
            urlI="/Reportes/RptImpresionBoletaCaja.jrxml";     
        }        
        
                
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(DeudaAlumno);
        JasperReport report;
        JasperPrint print;
        try
        {
            JasperDesign jasperDesign = JRXmlLoader.load(System.getProperty("user.dir")+urlI);
            JasperReport ReportMain = JasperCompileManager.compileReport(jasperDesign);
            print = JasperFillManager.fillReport(ReportMain, p,ds);
            print.setPageHeight(390);
            
            PrinterJob printerJob = PrinterJob.getPrinterJob();
            PageFormat pageFormat = PrinterJob.getPrinterJob().defaultPage();
            printerJob.defaultPage(pageFormat);

            int selectedService = 0;

            AttributeSet attributeSet = new HashPrintServiceAttributeSet(new PrinterName("WebPrintService", null));

            PrintService[] printService = PrintServiceLookup.lookupPrintServices(null, attributeSet);

             try {
                printerJob.setPrintService(printService[selectedService]);

            } catch (PrinterException e) {

                System.out.println(e);
            }
            JRPrintServiceExporter exporter;
            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
            printRequestAttributeSet.add(MediaSizeName.ISO_A4);
            printRequestAttributeSet.add(new Copies(1));

            // these are deprecated
            exporter = new JRPrintServiceExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, printService[selectedService]);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printService[selectedService].getAttributes());
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
            exporter.exportReport();
            
            
            //JasperPrintManager.printReport(print, true);                                     
        }
        catch(JRException ex)
        {
            JOptionPane.showMessageDialog(this, ex);
            System.out.println(ex);
        }             
        
    }//GEN-LAST:event_btnImprimirBoletaActionPerformed

    private void btnGenerarPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarPagoActionPerformed
        Font fuente=new Font("Dialog", Font.BOLD, 13);
        String codigoBoleta = codigoBoleta();
        
        //asdas
            int seleccionado[] = tblPendiente.getSelectedRows();
            int i;
           
            double aux=0;
            int limite;
            limite = seleccionado.length;            
            ClsDeudas cd = new ClsDeudas(); 
            
            //Se obtiene el total de las filas seleccionadas
            for (i=0; i < limite;i++)
            {// {"CONCEPTO","TOTAL","PENDIENTE","TOT. A PAGAR","FEC. VENCIMIENTO","TIPO","ID_DEUDA","MES"};
                
                //variable aux va sumando el pendiente o total que es la misma cantidad
                aux = aux +  Double.parseDouble((String) modelo.getValueAt(seleccionado[i], 3));       
            }
        
        int n = JOptionPane.showConfirmDialog(this, "Desea pagar deudas "
                + "seleccionadas para "+alumno+"\n----------------------------------------------------------\n"
                        + "Correlativo boleta: "+codigoBoleta+"\nMonto a pagar: "+String.valueOf(aux),"Confirmar pago",JOptionPane.OK_CANCEL_OPTION );
        if(n==0)
        {
            String fecha = obtenerFechaActual();            
            generarPagos(fecha);
            
        }
    }//GEN-LAST:event_btnGenerarPagoActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtBusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyTyped
        
        String a = txtBusqueda.getText();
        if(a.equals(""))
        {
            actualizarTablaAlumno();    
        }
        else
        {
            actualizarTablaAlumnoBusqueda(a);    
        }
        
    }//GEN-LAST:event_txtBusquedaKeyTyped

    private void txtBusquedaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBusquedaMouseClicked
        int fin = txtBusqueda.getHeight();
        txtBusqueda.setSelectionStart(0);
        txtBusqueda.setSelectionEnd(fin);
    }//GEN-LAST:event_txtBusquedaMouseClicked

    private void cbxTodoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTodoItemStateChanged
        if(cbxTodo.isSelected())
        {
            cbxMatricula.setSelected(false);
            cbxMensualidad.setSelected(false);
            cbxAlimentacion.setSelected(false);
            cbxProducto.setSelected(false);
            cbxServicio.setSelected(false);
            cbxPagado.setSelected(false);
           //actualiza deudas del alumno
            if(cbxSinVencimiento.isSelected())
            {
                actualizarPendiente(mes,codMat,true);  
                actualizarBoleta(codMat); 
                activaBotonesOpcion(false,false,false,false);
            }
            else
            {
                actualizarPendiente(mes,codMat,false);  
                actualizarBoleta(codMat); 
            }
            ce.ActualizaTodo(this); 
            tblPendiente.getColumnModel().getColumn( 3 ).setCellEditor(new MyTableCellEditor());
        }
    }//GEN-LAST:event_cbxTodoItemStateChanged

    private void cbxMatriculaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxMatriculaItemStateChanged
        cbxTodo.setSelected(false);
        cbxPagado.setSelected(false);
        if(cbxSinVencimiento.isSelected())
        {
            condicionalesActualizar(true); 
            activaBotonesOpcion(false,false,false,false);
        }
        else
        {
            condicionalesActualizar(false);
           
            activaBotonesOpcion(false,false,false,false);
        }
        
    }//GEN-LAST:event_cbxMatriculaItemStateChanged

    private void cbxMensualidadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxMensualidadItemStateChanged
        cbxTodo.setSelected(false);
        cbxPagado.setSelected(false);
        if(cbxSinVencimiento.isSelected())
        {
            condicionalesActualizar(true);   
            activaBotonesOpcion(false,false,false,false);
        }
        else
        {
            condicionalesActualizar(false);   
            activaBotonesOpcion(false,false,false,false);
        }
    }//GEN-LAST:event_cbxMensualidadItemStateChanged

    private void cbxAlimentacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxAlimentacionItemStateChanged
        cbxTodo.setSelected(false);
        cbxPagado.setSelected(false);
        if(cbxSinVencimiento.isSelected())
        {
            condicionalesActualizar(true); 
            activaBotonesOpcion(false,false,false,false);
        }
        else
        {
            activaBotonesOpcion(false,false,false,false);
            condicionalesActualizar(false);         
        }
    }//GEN-LAST:event_cbxAlimentacionItemStateChanged

    private void cbxProductoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxProductoItemStateChanged
       cbxTodo.setSelected(false);
       cbxPagado.setSelected(false);
       if(cbxSinVencimiento.isSelected())
        {
            condicionalesActualizar(true); 
            activaBotonesOpcion(false,false,false,false);
        }
        else
        {
            condicionalesActualizar(false); 
            activaBotonesOpcion(false,false,false,false);
        }
    }//GEN-LAST:event_cbxProductoItemStateChanged

    private void cbxServicioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxServicioItemStateChanged
        cbxTodo.setSelected(false);
        cbxPagado.setSelected(false);        
        if(cbxSinVencimiento.isSelected())
        {
            condicionalesActualizar(true);  
            activaBotonesOpcion(false,false,false,false);
        }
        else
        {
            condicionalesActualizar(false);  
            activaBotonesOpcion(false,false,false,false);
        }
    }//GEN-LAST:event_cbxServicioItemStateChanged

    private void cbxSinVencimientoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSinVencimientoItemStateChanged
        if(cbxSinVencimiento.isSelected())
        {
            cbxPagado.setSelected(false);
            condicionalesActualizar(true);  
            activaBotonesOpcion(false,false,false,false);
            activaBotonesAccion(false,false,false,false);
        }
        else
        {
            cbxPagado.setSelected(false);
            condicionalesActualizar(false);
            activaBotonesOpcion(false,false,false,false);
        }
    }//GEN-LAST:event_cbxSinVencimientoItemStateChanged

    private void tblPendienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPendienteMouseClicked
       if(FrmPrincipal.editarPago)
       {
            activaBotonesOpcion(true,true,true,false);
       }
        
       
       //identificar si es producto o servicio, para llenar campos
       int reg = tblPendiente.getSelectedRow();
       ResultSet rsDeuda;
       tipoDeuda = (String) modelo.getValueAt(reg, 5);       
       idDeuda = (String) modelo.getValueAt(reg, 6);
       mesDeuda = (String) modelo.getValueAt(reg, 7);
       total = Double.parseDouble((String) modelo.getValueAt(reg, 1));
       pendiente = Double.parseDouble((String) modelo.getValueAt(reg, 2));
       System.out.println("----------------------");
       System.out.println(idDeuda+" "+tipoDeuda);      
       System.out.println("----------------------");
       System.out.println(total+" total"); 
       System.out.println("----------------------");
       System.out.println(pendiente+" pendiente"); 
       
       ClsDeudaProd dp = new ClsDeudaProd();
       ClsDeudaServ ds = new ClsDeudaServ();
        try {
            
           switch (tipoDeuda) 
           {
               case "producto":
                   con = ClsConexion.getConection();
                   rsDeuda = dp.SeleccionarDeudaProducto(idDeuda,con);
                   while(rsDeuda.next())
                   {
                       txtFechaPedido.setText(rsDeuda.getString("fecha_deudap"));
                       txtStock.setText(rsDeuda.getString("stock_producto"));
                       if(FrmPrincipal.editarPago)
                       {
                       activaBotonesOpcion(true,true,true,true);           
                       }
                   }
                   ClsConexion.closeConnection(con);
                   break;
               case "servicio":
                   con = ClsConexion.getConection();
                   rsDeuda = ds.SeleccionarDeudaServicio(idDeuda,con);
                   while(rsDeuda.next())
                   {
                       txtFechaPedido.setText(rsDeuda.getString("fecha_deudas"));  
                       if(FrmPrincipal.editarPago)
                       {
                       activaBotonesOpcion(true,true,true,true); 
                       }
                   }
                   ClsConexion.closeConnection(con);
                   break;
           }
        
        } catch (Exception ex) {
        Logger.getLogger(FrmPagoTodo.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(cbxSinVencimiento.isSelected())
        {   
            activaBotonesAccion(true,true,true,true);
        }
        else
        {
            activaBotonesAccion(true,true,true,true);
        }
        
        txtTotal.setText(String.valueOf(calculaSeleccionadoTotal()));
       
    }//GEN-LAST:event_tblPendienteMouseClicked

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(auxObs !=0) //caso modificar
        {
            ClsEntidadPgoObservacion epo = new ClsEntidadPgoObservacion();
            ClsPgoObservacion po = new ClsPgoObservacion();
            epo.setObservacion_Obs(txaObservacion.getText());
            po.ModificarObservacion(epo, codMat);           
        }
        else //caso guardar
        {
            ClsEntidadPgoObservacion epo = new ClsEntidadPgoObservacion();
            ClsPgoObservacion po = new ClsPgoObservacion();
            epo.setObservacion_Obs(txaObservacion.getText());            
            epo.setId_Alumno(codMat);
            po.AgregarObservacion(epo);            
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void cbxPagadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxPagadoItemStateChanged
        if(cbxPagado.isSelected())
        {
            cbxMatricula.setSelected(false);
            cbxMensualidad.setSelected(false);
            cbxAlimentacion.setSelected(false);
            cbxProducto.setSelected(false);
            cbxServicio.setSelected(false);
            cbxTodo.setSelected(false);
            cbxSinVencimiento.setSelected(false);
            
            listarDeudasPagadas(mes,codMat);
            
            activaBotonesOpcion(false,false,false,false);
            activaBotonesAccion(false,false,true,true);
        }
        else
        {
            cbxTodo.setSelected(true);
        }
    }//GEN-LAST:event_cbxPagadoItemStateChanged

    private void btnModificarBoletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarBoletaActionPerformed
        FrmBoleta.idBoleta=idBoleta;  
        FrmBoleta.tipo= tip;
        PckInterfaces.FrmBoleta comida= new PckInterfaces.FrmBoleta();
        int x = (dskpPrincipal.getWidth() / 2) - comida.getWidth() / 2;
        int y = (dskpPrincipal.getHeight() / 2) - comida.getHeight() / 2; 
        if (comida.isShowing()) 
        {
            comida.setLocation(x, y);
        } else 
        {
            dskpPrincipal.add(comida);               
            comida.ActualizaTodoBoleta(this);  
            comida.setLocation(x, y);
            comida.show();                    
        }
    }//GEN-LAST:event_btnModificarBoletaActionPerformed

    private void btnNotificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNotificacionActionPerformed
        
        try {
        
            TableModel tm = tblPendiente.getModel();
            int filas[] = tblPendiente.getSelectedRows();

            int i; 
            ClsDeudas cd = new ClsDeudas();
            ArrayList<ClsEntidadNotificacion> dataBeanList = new ArrayList<>();

            double aux=0;
            for (i=0; i < filas.length;i++)
            {
                dataBeanList.add(cd.produceNotificacion(
                        (String) tm.getValueAt(filas[i], 0),     //concepto
                        (String) tm.getValueAt(filas[i], 1),     //total
                        (String) tm.getValueAt(filas[i], 2),     //pendiente
                        (String) tm.getValueAt(filas[i], 4),     //vencimiento
                        (String) tm.getValueAt(filas[i], 5),     //Tipo
                        (String) tm.getValueAt(filas[i], 6)));   //Id Deuda

                aux = aux +  Double.parseDouble((String) tm.getValueAt(filas[i], 2));       
            }
            System.out.println("Total:"+ String.valueOf(aux));

            if(txtBusqueda.getText().length()>0)
            {

                TableModel tma = tblAlumno.getModel();
                int reg;               
                reg = tblAlumno.getSelectedRow();      
                if(reg == -1)
                {JOptionPane.showMessageDialog(null, "Debe Seleccionar una opción");}
                else
                {
                    salon = (String) tma.getValueAt(reg, 3);
                }

            }

            //Inicia reporte

            Map mapa = new HashMap();
            JasperPrint jpReporte;
            JRBeanCollectionDataSource dsReporte;
            JasperViewer jvReporte;
            String pathJRXML = "/Reportes/RptNotificacion.jrxml";
            String pathJASPER = "/Reportes/RptNotificacion.jasper";

            mapa.put("nino", alumno);
            mapa.put("aula", salon);
            mapa.put("logo", LOGOTIPO);
            mapa.put("tot", String.valueOf(aux));

            dsReporte = new JRBeanCollectionDataSource(dataBeanList);
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
            Logger.getLogger(FrmPagoTodo.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        
    }//GEN-LAST:event_btnNotificacionActionPerformed

    private void tblBoletaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBoletaMouseClicked
        int reg = tblBoleta.getSelectedRow();
        DefaultTableModel dtm = (DefaultTableModel)tblBoleta.getModel();
        idBoleta = (String) dtm.getValueAt(reg, 1);               
        NumeroBoleta = (String) dtm.getValueAt(reg, 2); 
        tip = (String) dtm.getValueAt(reg, 6);         
        System.out.println("----------------------");
        System.out.println("Boleta N°: "+idBoleta);
        System.out.println("Tipo: "+tip);
        if(tip.equals("anulada"))
        {
            activaBotonesBoleta(false,false,true,false);
        }
        else
        {
            activaBotonesBoleta(true,true,true,true);
        }
    }//GEN-LAST:event_tblBoletaMouseClicked

    private void btnEliminarBoletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarBoletaActionPerformed
        
        int n = JOptionPane.showConfirmDialog(this, "¿Desea eliminar la "
                + "boleta "+NumeroBoleta+" y todos sus pagos?");
        if(n==0)
        {
            //OBTENER DETALLE BOLETA
            String idDetalle,cantidad,descripcion,punitario,tipo,idDeudal;
            Double importe;

            ClsDetalleBoleta db = new ClsDetalleBoleta();             
            ClsPendienteBoleta pboleta = new ClsPendienteBoleta();
            con = ClsConexion.getConection();
            ArrayList<ClsEntidadDetalleBoleta> boleta = db.SeleccionarDetalles(idBoleta,con);            
            try 
            {
                con.close();
            } catch (SQLException ex) 
            {
                Logger.getLogger(FrmPagoTodo.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (ClsEntidadDetalleBoleta Detalle : boleta) 
            {            
                idDetalle = Detalle.getId_Detalle();
                cantidad = String.valueOf(Detalle.getCantidad_Detalle());
                descripcion = Detalle.getDescripcion_Detalle();
                punitario = String.valueOf(Detalle.getUnitario_Detalle());
                importe = Double.parseDouble(Detalle.getImporte_Detalle().toString());
                tipo = String.valueOf(Detalle.getTipo_Detalle());
                idDeudal = String.valueOf(Detalle.getId_Deuda());

                //MODIFICAR PENDIENTES
                switch(tipo)
                {
                    case "matricula":

                        //a la deuda se le suma el importe que se pago                                        
                        pboleta.ModificarPendienteMatricula(idDeudal,importe, "+");                    

                        break;
                    case "mensualidad":

                        //si importe antiguo es mayor que importe nuevo se debe sumar al pendiente                        
                        pboleta.ModificarPendienteMensualidad(idDeudal,importe, "+");

                        break;  
                    case "comida":

                        //si importe antiguo es mayor que importe nuevo se debe sumar al pendiente                        
                        pboleta.ModificarPendienteComida(idDeudal,importe, "+");

                        break;    
                    case "producto":                    

                        //si importe antiguo es mayor que importe nuevo se debe sumar al pendiente                        
                        pboleta.ModificarPendienteProducto(idDeudal,importe, "+");                        

                        break;    
                    case "servicio":

                        //si importe antiguo es mayor que importe nuevo se debe sumar al pendiente                        
                        pboleta.ModificarPendienteServicio(idDeudal,importe, "+");

                        break;    
                }

               
            }

            //ELIMINAR BOLETA
            
            ClsBoleta bol = new ClsBoleta();
            con = ClsConexion.getConection();
            bol.EliminarBoleta(idBoleta,con);
            ClsConexion.closeConnection(con);    
            JOptionPane.showMessageDialog(null, "Boleta borrada correctamente, ajuste el código boleta \nsi es necesario");
            this.actualiza();
            activaBotonesBoleta(false,false,false,false);
        }
        
    }//GEN-LAST:event_btnEliminarBoletaActionPerformed

    private void btnAnularBoletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularBoletaActionPerformed
              
        int n = JOptionPane.showConfirmDialog(this, "¿Desea anular pagos "
                + "de la boleta "+NumeroBoleta+"?");
        if(n==0)
        {
            //OBTENER DETALLE BOLETA
            String idDetalle,cantidad,descripcion,punitario,tipo,idDeudal;
            Double importe;

            ClsDetalleBoleta db = new ClsDetalleBoleta();             
            ClsPendienteBoleta pboleta = new ClsPendienteBoleta();
            con = ClsConexion.getConection();
            ArrayList<ClsEntidadDetalleBoleta> boleta = db.SeleccionarDetalles(idBoleta,con);
            try 
            {
                con.close();
            } catch (SQLException ex) 
            {
                Logger.getLogger(FrmPagoTodo.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (ClsEntidadDetalleBoleta Detalle : boleta) {            
                idDetalle = Detalle.getId_Detalle();
                cantidad = String.valueOf(Detalle.getCantidad_Detalle());
                descripcion = Detalle.getDescripcion_Detalle();
                punitario = String.valueOf(Detalle.getUnitario_Detalle());
                importe = Double.parseDouble(Detalle.getImporte_Detalle().toString());
                tipo = String.valueOf(Detalle.getTipo_Detalle());
                idDeudal = String.valueOf(Detalle.getId_Deuda());

                //MODIFICAR PENDIENTES
                switch(tipo)
                {
                    case "matricula":

                        //a la deuda se le suma el importe que se pago                                        
                        pboleta.ModificarPendienteMatricula(idDeudal,importe, "+");                    

                        break;
                    case "mensualidad":

                        //si importe antiguo es mayor que importe nuevo se debe sumar al pendiente                        
                        pboleta.ModificarPendienteMensualidad(idDeudal,importe, "+");

                        break;  
                    case "comida":

                        //si importe antiguo es mayor que importe nuevo se debe sumar al pendiente                        
                        pboleta.ModificarPendienteComida(idDeudal,importe, "+");

                        break;    
                    case "producto":                    

                        //si importe antiguo es mayor que importe nuevo se debe sumar al pendiente                        
                        pboleta.ModificarPendienteProducto(idDeudal,importe, "+");                        

                        break;    
                    case "servicio":

                        //si importe antiguo es mayor que importe nuevo se debe sumar al pendiente                        
                        pboleta.ModificarPendienteServicio(idDeudal,importe, "+");

                        break;    
                }

                //ANULAR DETALLE BOLETA       
                con = ClsConexion.getConection();
                db.ModificarDetalleBoletaAnulada("ANULADA "+descripcion,0.0,idDetalle,con);
                try 
                {
                    con.close();
                } catch (SQLException ex) 
                {
                    Logger.getLogger(FrmPagoTodo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            //ANULAR BOLETA
                //modificar total boleta
                ClsBoleta b = new ClsBoleta();       
                con = ClsConexion.getConection();
                b.ModificaTotalBoleta(0.0,idBoleta,con);
                ClsConexion.closeConnection(con);
                
            JOptionPane.showMessageDialog(null, "Boleta anulada correctamente");
            this.actualiza();
            activaBotonesBoleta(false,false,false,false);
        }
        
        
    }//GEN-LAST:event_btnAnularBoletaActionPerformed

    private void btnNotificacionPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNotificacionPeriodoActionPerformed
        ClsAlumno alumnos = new ClsAlumno();
        ClsDeudas deudas = new ClsDeudas();
        
        //lista de deudas que pasaran por filtro (declaracion)
        ArrayList<ClsEntidadDeudas> deudasSinFiltro; 
        //lista de alumnos (declaracion)
        ArrayList<ClsEntidadDeudasAlumno> beanAlumno = new ArrayList<>(); 
        //parametro total deuda
        Double total2 = 0.0;
        String idPeriodo = codigoPeriodo.get(cmbPeriodo.getSelectedIndex());
        //System.out.println("periodo: "+idPeriodo);
        ResultSet rsAlu=null;
        //String iniClases;
        String idMatricula;
        String salonAlu;
        String nombreAlu;
        //Relacionar lista de alumnos con deudas
        try
        {
            con = ClsConexion.getConection();
            rsAlu = alumnos.SeleccionarAlumnoPeriodo(idPeriodo,con);
            
            while(rsAlu.next())
            {
                //iniClases=rsAlu.getString("inicio_clases");
                idMatricula = rsAlu.getString("id_matricula");
                salonAlu = rsAlu.getString("nombre_seccion");
                nombreAlu = rsAlu.getString("apellidos_nombres");                
                
                deudasSinFiltro = deudas.ListarDeudas(idMatricula,con); 
                
                ClsEntidadDeudasAlumno alumnoo = new ClsEntidadDeudasAlumno(nombreAlu,salonAlu);
                beanAlumno.add(alumnoo);                
                total2=0.0;
                
                for(ClsEntidadDeudas i: deudasSinFiltro)
                {
                    int aux2=0;
                    int aux3=0;
                    int mes2=0;
                    //System.out.println(i.Mes);
                    mes2 = obtenerMesInicioClases(idMatricula,con);
                    //verifica que la palabra sea mayor a 11 caracteres (ALIMENTACION)
                    if(i.Concepto.length()>11)
                    {
                        //en este caso esta registrado "ALIMENTACION " (con espacio) 
                        //se verifica que a continuacion de ALIMENTACION no exista ningun otro caracter                
                        if(i.Concepto.substring(12).equals(" "))
                        {
                            //aca editar el total de deuda
                            total2 = total2 + Double.parseDouble(i.Pendiente);
                            alumnoo.addDeuda(new ClsEntidadDeudasBean(i.Concepto,i.Unitario,i.Pendiente,i.Vencimiento,i.Mes,String.valueOf(total2)));                      
                        }
                        //si no se cumple quiere decir que a continuacion eixsten mas caracteres
                        //comprobamos que la primera palabra es ALIMENTACION
                        else if(i.Concepto.substring(0,12).equals("ALIMENTACION"))
                        { 
                        //cumple, por lo tanto la siguiente palabra es un mes, y la deuda es matricula                    
                            switch (i.Concepto.substring(13)) 
                            {
                                case "ENERO":aux2 = 0;break;
                                case "FEBRERO":aux2=1;break;
                                case "MARZO":aux2=2;break;
                                case "ABRIL":aux2=3;break;
                                case "MAYO":aux2=4;break;
                                case "JUNIO":aux2=5;break;
                                case "JULIO":aux2=6;break;
                                case "AGOSTO":aux2=7;break;
                                case "SEPTIEMBRE":aux2=8;break;
                                case "OCTUBRE":aux2=9;break;
                                case "NOVIEMBRE":aux2=10;break;
                                case "DICIEMBRE":aux2=11;break;
                            }
                            //si el mes es mayor que la fecha de inicio de clases se mostrara en la tabla
                            if(aux2>=mes2)
                            {
                            //aca editar el total de deuda
                            total2 = total2 + Double.parseDouble(i.Pendiente);
                            alumnoo.addDeuda(new ClsEntidadDeudasBean(i.Concepto,i.Unitario,i.Pendiente,i.Vencimiento,i.Mes,String.valueOf(total2)));
   
                            }
                        //si nada de esto ocurre comparamos que despues de ALIMENTACION haya un espacio en blanco
                        //si es asi, existen caracteres despues de ese espacio y deben mostrarse en la tabla
                            else if(i.Concepto.substring(12,13).equals(" "))
                            {
                            //aca editar el total de deuda
                            total2 = total2 + Double.parseDouble(i.Pendiente);
                            alumnoo.addDeuda(new ClsEntidadDeudasBean(i.Concepto,i.Unitario,i.Pendiente,i.Vencimiento,i.Mes,String.valueOf(total2)));
                            }
                        } 
                        //si no es eso es deuda de mensualidad segun el mes 
                        //la deuda es mayor al inicio de clases
                        else if(i.Concepto.substring(0,11).equals("MENSUALIDAD"))
                        { 
                            switch (i.Concepto.substring(12)) 
                            {
                                case "ENERO":aux3 = 0;break;
                                case "FEBRERO":aux3=1;break;
                                case "MARZO":aux3=2;break;
                                case "ABRIL":aux3=3;break;
                                case "MAYO":aux3=4;break;
                                case "JUNIO":aux3=5;break;
                                case "JULIO":aux3=6;break;
                                case "AGOSTO":aux3=7;break;
                                case "SEPTIEMBRE":aux3=8;break;
                                case "OCTUBRE":aux3=9;break;
                                case "NOVIEMBRE":aux3=10;break;
                                case "DICIEMBRE":aux3=11;break;
                            }
                            if(aux3>=mes2)
                            {
                            //aca editar el total de deuda
                            total2 = total2 + Double.parseDouble(i.Pendiente);    
                            alumnoo.addDeuda(new ClsEntidadDeudasBean(i.Concepto,i.Unitario,i.Pendiente,i.Vencimiento,i.Mes,String.valueOf(total2)));
                            //dBean.add(deudas.produce3(nombreAlu, salonAlu));
                            } 
                        }
                        else 
                        {
                            //aca editar el total de deuda
                            //System.out.println("Pendiente: "+i.Pendiente);
                            total2 = total2 + Double.parseDouble(i.Pendiente);
                            alumnoo.addDeuda(new ClsEntidadDeudasBean(i.Concepto,i.Unitario,i.Pendiente,i.Vencimiento,i.Mes,String.valueOf(total2)));
                            //dBean.add(deudas.produce3(nombreAlu, salonAlu));
                        }

                    }
                    else
                    {   
                        //aca editar el total de deuda
                        total2 = total2 + Double.parseDouble(i.Pendiente);
                        alumnoo.addDeuda(new ClsEntidadDeudasBean(i.Concepto,i.Unitario,i.Pendiente,i.Vencimiento,i.Mes,String.valueOf(total2)));
                        //dBean.add(deudas.produce3(nombreAlu, salonAlu));
                    }
                }
                //una vez terminado el ciclo de llenado de deudas por cada alumno
                //creamos un indice que indicará la posicion del arraylist principal (beanAlumno)
                //obtenemos las deudas del alumno y si esta vacio se removera al alumno 
                //con el indice obtenido :D
                int indice=beanAlumno.size();

                if(alumnoo.getDeudas().isEmpty())
                {
                    beanAlumno.remove(indice-1);
                }
            }    
            ClsConexion.closeConnection(con);
        }
        catch (Exception ex) 
        {
            Logger.getLogger(FrmImpresionBoleta.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex);
        }
        
        try {
            //principal
            String  url7 ="/Reportes/RptPagoPendienteTodos.jrxml";
            JasperDesign jasperDesign = JRXmlLoader.load(System.getProperty("user.dir") + url7);
            JasperReport ReportMain = JasperCompileManager.compileReport(jasperDesign);
            System.out.print(System.getProperty("user.dir"));
            Map<String, Object> params = new HashMap();
            //params.put("nino", nombreAlu);
            //params.put("aula", salonAlu);
            //params.put("total",String.valueOf(total));           
            
            params.put("logo", LOGOTIPO);
            params.put("SUBREPORT_DIR", System.getProperty("user.dir") + "\\Reportes\\");
            JasperPrint print = JasperFillManager.fillReport(ReportMain, params, new JRBeanCollectionDataSource(beanAlumno));
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Pagos Pendientes");
            view.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(FrmIngresosEgresos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex);
        } 
        
        
    }//GEN-LAST:event_btnNotificacionPeriodoActionPerformed

    private void btnHistorialCambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialCambioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHistorialCambioActionPerformed

   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnularBoleta;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminarBoleta;
    private javax.swing.JButton btnGenerarPago;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnHistorialCambio;
    private javax.swing.JButton btnImprimirBoleta;
    private javax.swing.JButton btnMas;
    private javax.swing.JButton btnMenos;
    private javax.swing.JButton btnModificarBoleta;
    private javax.swing.JButton btnNotificacion;
    private javax.swing.JButton btnNotificacionPeriodo;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JCheckBox cbxAlimentacion;
    private javax.swing.JCheckBox cbxMatricula;
    private javax.swing.JCheckBox cbxMensualidad;
    private javax.swing.JCheckBox cbxPagado;
    private javax.swing.JCheckBox cbxProducto;
    private javax.swing.JCheckBox cbxServicio;
    private javax.swing.JCheckBox cbxSinVencimiento;
    private javax.swing.JCheckBox cbxTodo;
    private javax.swing.JComboBox cmbPeriodo;
    private javax.swing.JComboBox cmbSeccion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblLinea;
    private javax.swing.JTable tblAlumno;
    private javax.swing.JTable tblBoleta;
    private javax.swing.JTable tblPendiente;
    private javax.swing.JTextArea txaObservacion;
    private javax.swing.JTextField txtAlimentacion;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtFechaPedido;
    private javax.swing.JTextField txtHorario;
    private javax.swing.JTextField txtInicioClases;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
