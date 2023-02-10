/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mpsModel;

import static java.awt.image.ImageObserver.WIDTH;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
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
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PrinterName;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import mpsDAL.PagoDAL;
import mpsEntity.BoletaEntity;
import mpsEntity.EgresoEntity;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.view.JasperViewer;

import prymatricula.ClsMisc;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email jkevin.mv@gmail.com
 */
public class PagoModelo {
    
    PagoDAL pagoDAL = new PagoDAL();
    final int ROW_HEIGHT = 20;
    
    public boolean ComprobarCodigoOperacionOperacion(String numeroOperacion,String IdPeriodo,Connection con) {
        
        boolean numeroOperacionRepetido = false;
        ArrayList<String> listNumeroOperacion = pagoDAL.ListarNumeroOperacionPeriodo(IdPeriodo, con);
        
        for(String x : listNumeroOperacion) {
            
            if( x.equals(numeroOperacion) ) {
                
                numeroOperacionRepetido = true;
                break;
            }
        }
                
        return numeroOperacionRepetido;
        
    }
    
    public void CargarCierreCaja(JTextField TxtCierreCaja, Double Ingresos, Double Egresos) {
        
        TxtCierreCaja.setText(String.valueOf(Ingresos - Egresos));
        
    }
    
    
    public Double CargarTablaIngresosCaja(JTable Tabla, Date Fecha, Connection con) {
        
        String columnTitle[] = { "IDBOLETA","N°" ,"AULA","ALUMNO","CONCEPTO","MONTO","BOLETA"  };
        List<BoletaEntity> listDetalle;
        Object rows[] = new Object[7];
        int countOrderNumber = 1;                
        Double totalIngresos = 0.0;
        
        DefaultTableModel dtm = new DefaultTableModel(null,columnTitle)
        {
            @Override
            public boolean isCellEditable(int rowIndex,int columnIndex)
            {return false;}
        }; 
        
        listDetalle = pagoDAL.ListarIngresosCaja(Fecha, con);
        
        for(BoletaEntity be : listDetalle) {
            
            rows[0] = be.getId_boleta();
            rows[1] = countOrderNumber;
            rows[2] = be.getNombre_seccion();
            rows[3] = be.getNombresApellidosAlumno();
            rows[4] = be.getDesc_detalle();
            rows[5] = be.getImporte_detalle();
            rows[6] = be.getNum_boleta();
            dtm.addRow(rows);
            countOrderNumber++;   
            totalIngresos = totalIngresos + be.getImporte_detalle();
        }
        
        Tabla.setModel(dtm); 
        Tabla.setRowHeight(ROW_HEIGHT);        
        Tabla.removeColumn(Tabla.getColumnModel().getColumn(0));
        TableColumn colOrden = Tabla.getColumnModel().getColumn(0); colOrden.setPreferredWidth(30);colOrden.setMaxWidth(35);
        TableColumn colAula = Tabla.getColumnModel().getColumn(1); colAula.setPreferredWidth(70);
        TableColumn colAlumno = Tabla.getColumnModel().getColumn(2); colAlumno.setPreferredWidth(230);
        TableColumn colConcepto = Tabla.getColumnModel().getColumn(3); colConcepto.setPreferredWidth(230);
        TableColumn colMonto = Tabla.getColumnModel().getColumn(4); colMonto.setPreferredWidth(40);
        TableColumn colBoleta = Tabla.getColumnModel().getColumn(5); colBoleta.setPreferredWidth(60);
        
        TableCellRenderer tcr =  Tabla.getTableHeader().getDefaultRenderer();
        ClsMisc.miRender mr = new ClsMisc.miRender(tcr);
        mr.getTableCellRendererComponent(Tabla, rows, true, true, WIDTH, 0);
        mr.getTableCellRendererComponent(Tabla, rows, true, true, WIDTH, 4);
        mr.getTableCellRendererComponent(Tabla, rows, true, true, WIDTH, 5);
        
        return totalIngresos;
    }
    
    public Double CargarTablaEgresosCaja(JTable Tabla, Date Fecha, Connection con) {
        
        String columnTitle[] = { "IDEGRESO", "N°", "PERSONA", "CONCEPTO", "DESCRIPCION", "MONTO"  };
        List<EgresoEntity> listEgreso;
        Object rows[] = new Object[6];
        int countOrderNumber = 1;                
        Double totalEgresos = 0.0;
        
        DefaultTableModel dtm = new DefaultTableModel(null,columnTitle)
        {
            @Override
            public boolean isCellEditable(int rowIndex,int columnIndex)
            {return false;}
        };
        
        listEgreso = pagoDAL.ListarEgresosCaja(Fecha, con);
        
        for(EgresoEntity le : listEgreso) {
            
            rows[0] = le.getId_egreso();
            rows[1] = countOrderNumber;
            rows[2] = le.getNombresApellidosPersona();
            rows[3] = le.getConcepto_egreso();
            rows[4] = le.getDesc_egreso();
            rows[5] = le.getMonto_egreso();            
            dtm.addRow(rows);
            countOrderNumber++;   
            totalEgresos = totalEgresos + le.getMonto_egreso();
        }
        
        Tabla.setModel(dtm); 
        Tabla.setRowHeight(ROW_HEIGHT);        
        Tabla.removeColumn(Tabla.getColumnModel().getColumn(0));
        TableColumn colOrden = Tabla.getColumnModel().getColumn(0); colOrden.setPreferredWidth(30);colOrden.setMaxWidth(35);    
        TableColumn colPersona = Tabla.getColumnModel().getColumn(1); colPersona.setPreferredWidth(200);
        TableColumn colConcepto = Tabla.getColumnModel().getColumn(2); colConcepto.setPreferredWidth(200);
        TableColumn colDescripcion = Tabla.getColumnModel().getColumn(3); colDescripcion.setPreferredWidth(200);
        TableColumn colMonto = Tabla.getColumnModel().getColumn(4); colMonto.setPreferredWidth(40);
        
        
        TableCellRenderer tcr =  Tabla.getTableHeader().getDefaultRenderer();
        ClsMisc.miRender mr = new ClsMisc.miRender(tcr);
        mr.getTableCellRendererComponent(Tabla, rows, true, true, WIDTH, 0);
        mr.getTableCellRendererComponent(Tabla, rows, true, true, WIDTH, 4);
        
        
        return totalEgresos;
    }
    
    public Double CargarTablaIngresosBanco(JTable Tabla, Date Fecha, Connection con) {
        
        String columnTitle[] = { "IDBOLETA","N°" ,"AULA","ALUMNO","CONCEPTO","MONTO","N° OPERACION","BOLETA"  };
        List<BoletaEntity> listDetalle;
        Object rows[] = new Object[8];
        int countOrderNumber = 1;                
        Double totalIngresosCaja = 0.0;
        
        DefaultTableModel dtm = new DefaultTableModel(null,columnTitle)
        {
            @Override
            public boolean isCellEditable(int rowIndex,int columnIndex)
            {return false;}
        }; 
        
        listDetalle = pagoDAL.ListarIngresosBanco(Fecha, con);
        
        for(BoletaEntity be : listDetalle) {
            
            rows[0] = be.getId_boleta();
            rows[1] = countOrderNumber;
            rows[2] = be.getNombre_seccion();
            rows[3] = be.getNombresApellidosAlumno();
            rows[4] = be.getDesc_detalle();
            rows[5] = be.getImporte_detalle();
            rows[6] = be.getNumero_operacion();
            rows[7] = be.getNum_boleta();
            dtm.addRow(rows);
            countOrderNumber++;   
            totalIngresosCaja = totalIngresosCaja + be.getImporte_detalle();
        }
        
        Tabla.setModel(dtm); 
        Tabla.setRowHeight(ROW_HEIGHT);        
        Tabla.removeColumn(Tabla.getColumnModel().getColumn(0));
        TableColumn colOrden = Tabla.getColumnModel().getColumn(0); colOrden.setPreferredWidth(30);colOrden.setMaxWidth(35);
        TableColumn colAula = Tabla.getColumnModel().getColumn(1); colAula.setPreferredWidth(70);
        TableColumn colAlumno = Tabla.getColumnModel().getColumn(2); colAlumno.setPreferredWidth(230);
        TableColumn colConcepto = Tabla.getColumnModel().getColumn(3); colConcepto.setPreferredWidth(230);
        TableColumn colMonto = Tabla.getColumnModel().getColumn(4); colMonto.setPreferredWidth(40);
        TableColumn colOperacion = Tabla.getColumnModel().getColumn(5); colOperacion.setPreferredWidth(60);
        TableColumn colBoleta = Tabla.getColumnModel().getColumn(6); colBoleta.setPreferredWidth(60);
        
        TableCellRenderer tcr =  Tabla.getTableHeader().getDefaultRenderer();
        ClsMisc.miRender mr = new ClsMisc.miRender(tcr);
        mr.getTableCellRendererComponent(Tabla, rows, true, true, WIDTH, 0);
        mr.getTableCellRendererComponent(Tabla, rows, true, true, WIDTH, 4);
        
        return totalIngresosCaja;
    }
    
    public void GenerarReporteIngresosBanco(Date Fecha, Connection con) {
        
        try {
            
            List<BoletaEntity> listBoletaBanco;
            
            Double totalIngresosCaja = 0.0;
            listBoletaBanco = pagoDAL.ListarIngresosBanco(Fecha, con);
            
            for(BoletaEntity be : listBoletaBanco) {
                
                   totalIngresosCaja = totalIngresosCaja + be.getImporte_detalle();
                
            }
            
            Map mapa = new HashMap();
            JasperPrint jpReporte;
            JRBeanCollectionDataSource dsReporte;
            JasperViewer jvReporte;
            String pathJRXML = "/Reportes/rptIngresosBanco.jrxml";
            String pathJASPER = "/Reportes/rptIngresosBanco.jasper";
            
            mapa.put("total", String.valueOf(totalIngresosCaja));
            
            dsReporte = new JRBeanCollectionDataSource(listBoletaBanco);
            JasperCompileManager.compileReportToFile
                (
                        System.getProperty("user.dir")+pathJRXML,
                        System.getProperty("user.dir")+pathJASPER
                );
            jpReporte = JasperFillManager.fillReport(System.getProperty("user.dir")+pathJASPER, mapa,dsReporte);
            jvReporte = new JasperViewer(jpReporte, false);
            
            
            PrinterJob printerJob = PrinterJob.getPrinterJob();
            PageFormat pageFormat = PrinterJob.getPrinterJob().defaultPage();
            //pageFormat.setOrientation(PageFormat.LANDSCAPE);
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
            printRequestAttributeSet.add(OrientationRequested.LANDSCAPE);
            printRequestAttributeSet.add(new Copies(1));

            // these are deprecated
            exporter = new JRPrintServiceExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jpReporte);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, printService[selectedService]);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printService[selectedService].getAttributes());
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
            exporter.exportReport();
            
            
            
//            jvReporte.setTitle("Reporte Personal");
//            jvReporte.setVisible(true);
            
        } catch (JRException ex) {
            Logger.getLogger(PagoModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
