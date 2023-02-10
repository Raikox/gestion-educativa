/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PckRenderTabla;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email jkevin.mv@gmail.com
 */
public class Render extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
    {
        if(value instanceof JButton)
        {
            JButton btn = (JButton)value;
            btn.setBorderPainted(false);                
            btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));            
            return btn;
        }
        else if (value instanceof Boolean) {
            JCheckBox cbx = (JCheckBox) value;
            cbx.setSelected(((Boolean) value));
        }
        else if(value instanceof JCheckBox)
        {
            JCheckBox cbx = (JCheckBox) value;       
            cbx.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            return cbx;
        }
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(table.getBackground());
        }
        
//        DefaultTableCellRenderer m = new DefaultTableCellRenderer();
//        m.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);            
//        table.getColumnModel().getColumn(column).setCellRenderer(m);
        
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
    }

    
}
