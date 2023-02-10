/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prymatricula;

import java.awt.Color;
import java.awt.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.DocumentFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 *
 * @author J Kevin Montes De Oca Vizcarra
 */
public class ClsMisc {
     
    static SimpleDateFormat formatoEUslash = new SimpleDateFormat("dd/MM/yyyy");
    static SimpleDateFormat formatoEUguion = new SimpleDateFormat("dd-MMMM-yyyy");
    static SimpleDateFormat formatoEUAguion = new SimpleDateFormat("yyyy-MM-dd");
    static SimpleDateFormat formatoTexto = new SimpleDateFormat("dd MMMMM yyyy"); 
    
    /*
    * Valida Mayusculas
    */
    public static class UppercaseDocumentFilter extends DocumentFilter 
    {
        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset,
                String text, AttributeSet attr) throws BadLocationException {

            fb.insertString(offset, text.toUpperCase(), attr);
        }

        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length,
                String text, AttributeSet attrs) throws BadLocationException {

            fb.replace(offset, length, text.toUpperCase(), attrs);
        }
    }
    
    /*
    * Valida Numeros
    */
    private static boolean test(String text) {
      try {
         Integer.parseInt(text);
         return true;
      } catch (NumberFormatException e) {
         return false;
      }
   }
    public static class IntegerDocumentFilter extends DocumentFilter 
    {
        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset,
                String text, AttributeSet attr) throws BadLocationException {

            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.insert(offset, text);

            if (test(sb.toString())) 
            {
                super.insertString(fb, offset, text, attr);
            }
           
        }

        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length,
                String text, AttributeSet attrs) throws BadLocationException {

            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.replace(offset, offset + length, text);
            
            if (test(sb.toString())) 
            {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }
    
    //CENTRAR TABLAS
    public static class miRender implements javax.swing.table.TableCellRenderer {
        TableCellRenderer tcr;

        public miRender(TableCellRenderer tcr)
        {
            this.tcr = tcr;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
        {
            //centrar header de columna
            javax.swing.JComponent wth = (javax.swing.JComponent) tcr.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            javax.swing.JLabel label = (javax.swing.JLabel)wth;
            label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

            //centrar columna completa
            DefaultTableCellRenderer m = new DefaultTableCellRenderer();
            m.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            table.getColumnModel().getColumn(column).setCellRenderer(m);             
            
            return wth;
        }      
    }
    
    public static class miRenderPintar implements javax.swing.table.TableCellRenderer
    {
        TableCellRenderer tcr;

        public miRenderPintar(TableCellRenderer tcr)
        {
            this.tcr = tcr;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
        {
            //centrar header de columna
            javax.swing.JComponent wth = (javax.swing.JComponent) tcr.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            javax.swing.JLabel label = (javax.swing.JLabel)wth;
            label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

            //centrar columna completa
            DefaultTableCellRenderer m = new DefaultTableCellRenderer();
            m.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            table.getColumnModel().getColumn(column).setCellRenderer(m); 
            
            m.setBackground(new Color(204,229,255));
            
            return wth;
        }      
    }
    
    public static class miRenderPintarRojo extends DefaultTableCellRenderer
    {
        

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
        {
            //centrar header de columna
            
            javax.swing.JLabel celda = (javax.swing.JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
            celda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);            
            if(value instanceof String) {
                celda.setBackground(new Color(249,137,137));
            }
            
                      
            
            //centrar columna completa
            
//            m.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//            table.getColumnModel().getColumn(column).setCellRenderer(m); 
            
              
            return celda;
        }      
    }
    
    //COLOCAR CAMPOS EN MAYUSCULAS
    public static String convertirMayu(String texto)
    {
        String text;
        text =texto.toUpperCase();
        return text;
    }
    
    public static String UltimoIdInsertado(Connection con)  throws Exception{
        ResultSet rs;
        String cod="";
        try{
            Statement statement = con.createStatement();
            rs = statement.executeQuery("select last_insert_id() as 'id_insertado';"); 
            while(rs.next()){
                cod = rs.getString("id_insertado");
            }
            return cod;            
        }catch(SQLException ex){
            throw ex;
        }
    }

    public String UltimoIdInsertado2(Connection con)  throws Exception{
        ResultSet rs;
        String cod="";
        try{
            Statement statement = con.createStatement();
            rs = statement.executeQuery("select last_insert_id() as 'id_insertado';"); 
            while(rs.next()){
                cod = rs.getString("id_insertado");
            }
            return cod;            
        }catch(SQLException ex){
            throw ex;
        }
    }
    public static int obtenerDiasDelMes(int mes)
    {
          switch(mes){
          
          case 1:  // Febrero
          return 28;   
          case 2:  // Marzo
          case 0:  // Enero
          case 4:  // Mayo          
          case 6:  // Julio
          case 7:  // Agosto
          case 9:  // Octubre
          case 11: // Diciembre
          return 31;              
          case 3:  // Abril    
          case 5:  // Junio
          case 8:  // Septiembre
          case 10: // Noviembre
          return 30;          
          default:
          throw new java.lang.IllegalArgumentException(
                          "El mes debe estar entre 0 y 11");
          }      
    }
    
    public static int obtenerMeses(String messi, String messf)
    {
      int a = Integer.parseInt(messi);
      int b = Integer.parseInt(messf);
      
      int meses = b-a;      
      return meses;
    }
    
    public static String calculaEdad(Date nacimiento)
    {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String naci = "";
        String fechaNacimiento = formatoEUslash.format(nacimiento);
        
        LocalDate fechaNac = LocalDate.parse(fechaNacimiento, fmt);
        LocalDate ahora = LocalDate.now();
        
        Period periodo = Period.between(fechaNac, ahora);
        
        naci = periodo.getYears()+" año(s) y "+periodo.getMonths()+" mes(es)";
        //System.out.println(fechaNacimiento + " - "+naci);
        
        return naci;
    }
    
    public static String formatoFecha(Date xfecha)
    {
     String fec="";        
     fec = formatoEUslash.format(xfecha);          
     return fec;
    }
    
    public static String formatoFechaOriginal(Date xfecha)
    {
     String fec="";        
     fec = formatoEUAguion.format(xfecha);          
     return fec;
    }
    
    public static String formatoFechaEUA(Date FechaEU) throws ParseException {
        
        String fecha = formatoEUAguion.format(FechaEU);
        return fecha;
    }
    
    public static String formatoFechaPalabra(String FechaEUA) throws ParseException {
        String fecha;
        Date date = formatoEUAguion.parse(FechaEUA);
        fecha = formatoTexto.format(date);
        
        return fecha;
    }
    
    public static String formatoFechaPalabraAGuion(String xfecha) throws ParseException
    {
        String fec="";        
        Date date = formatoEUAguion.parse(xfecha);
        fec = formatoEUguion.format(date);
        return fec;
    }
    
    public static String FechaISOtoFechaEuropea(String fecha) throws ParseException
    {
        
        Date date = formatoEUslash.parse(fecha);
        return formatoEUslash.format(date);
        
    }    
    
    public static String fechaActual()
    {
        Date hoy = new Date();
        String fecha = formatoEUslash.format(hoy);        
        return fecha;
    }
    
    public static String obtenerAnioActual()
    {
        String anio,fecha;
        Date d = new Date();
        fecha = formatoEUslash.format(d);
        anio = fecha.substring(6);
        
        return anio;
    }
    
    public static Date ObtenerFechaActual() {
        
        Date date = new Date();
        
        return date;
    }
    
    public static Date ConvertirEUAtoEUdate(String FechaEUA) throws ParseException {
        
        Date dateEUA = formatoEUAguion.parse(FechaEUA);
        String dateEU = formatoEUslash.format(dateEUA);
        Date dateEUslash = formatoEUslash.parse(dateEU);
        
        return dateEUslash;
    }
    
    public static String ConvertirEUAtoEUstring(String FechaEUA) throws ParseException {
        
        Date dateEUA = formatoEUAguion.parse(FechaEUA);
        String dateEU = formatoEUslash.format(dateEUA);        
        
        return dateEU;
    }
    
    public static String ConvertirEUtoEUAstring(String FechaEU) throws ParseException {
        
        Date dateEU = formatoEUslash.parse(FechaEU);
        String dateEUA = formatoEUAguion.format(dateEU);
        
        return dateEUA;
    }
    
    public static Date formatoFechaDate(String fecha)
    {
     Date fec=null;        
         try 
         {          
            fec = formatoEUslash.parse(fecha);
         } 
         catch (ParseException ex) 
         {
            Logger.getLogger(ClsMisc.class.getName()).log(Level.SEVERE, null, ex);
         }
     return fec;
    }    
    public static int obtenerMesNumero(String mes)
    {
        int aux3 = 0;
        switch (mes) 
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
        
        return aux3;
    }
    
    public static String obtenerMesNombre(int mes)
    {
        String aux3 = "";
        switch (mes) 
        {
            case 0:aux3 ="ENERO";break;
            case 1:aux3="FEBRERO";break;
            case 2:aux3="MARZO";break;
            case 3:aux3="ABRIL";break;
            case 4:aux3="MAYO";break;
            case 5:aux3="JUNIO";break;
            case 6:aux3="JULIO";break;
            case 7:aux3="AGOSTO";break;
            case 8:aux3="SEPTIEMBRE";break;
            case 9:aux3="OCTUBRE";break;
            case 10:aux3="NOVIEMBRE";break;
            case 11:aux3="DICIEMBRE";break;
        }
        
        return aux3;
    }
}
