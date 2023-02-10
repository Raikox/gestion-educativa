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
import javax.swing.table.TableCellRenderer;
/**
 *
 * @author John Kevin Montes De Oca Vizcarra
 * @email jkevin.mv@gmail.com
 */
public class GeneralRender extends DefaultTableCellRenderer{
    
    //Render con botones
    public static class MiRender extends DefaultTableCellRenderer {
        
        TableCellRenderer tcr;
        public MiRender(TableCellRenderer tcr)
        {
            this.tcr = tcr;
        }
        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
        {
            if(value instanceof JButton)
            {
                JButton btn = (JButton)value;                
                btn.setBorderPainted(false);                
                btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                if(btn.getName().equals("btnEditar"))
                {
                    btn.setText("");
                    btn.setToolTipText("Editar");
                    //btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/edit_black.png")));
                }
                else if(btn.getName().equals("btnVer"))
                {
                    btn.setText("");
                    btn.setToolTipText("Ver");
                    //btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PckIcoMenu/delete_black.png")));
                }
                
                return btn;
            }
            else if (value instanceof JCheckBox)
            {
                JCheckBox cbx = (JCheckBox) value;
                
                return cbx;
            }
            
            javax.swing.JComponent componente = (javax.swing.JComponent) 
            tcr.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            javax.swing.JLabel label = (javax.swing.JLabel)componente;
            label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
            DefaultTableCellRenderer m = new DefaultTableCellRenderer();
            m.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);            
            table.getColumnModel().getColumn(column).setCellRenderer(m);
            
            return componente;
        }
    }
}
