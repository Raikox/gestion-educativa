/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckRenderTabla;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email jkevin.mv@gmail.com
 */
public class RenderPintarRojo extends DefaultTableCellRenderer{
     
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
    {
        //centrar header de columna

        javax.swing.JLabel celda = (javax.swing.JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        
        
        if(table.getValueAt(row, column).equals("F")){
            celda.setBackground(new Color(252,191,191));
            celda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        }
        else if(table.getValueAt(row, column).equals("A")){
            celda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            celda.setBackground(Color.WHITE);
            celda.setForeground(Color.BLACK);
        }
        else if(table.getValueAt(row, column).equals("FJ")){
            celda.setBackground(new Color(210,212,216));            
            celda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        }
        else {
            celda.setBackground(Color.WHITE);
            celda.setForeground(Color.BLACK);
            celda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        }
            

        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    } 
        
}
