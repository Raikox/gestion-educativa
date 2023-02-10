package prymatricula;

import PckInterfaces.FrmPagoTodo;
import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
/**
 * @author J Kevin Montes De Oca Vizcarra
 * @author Mouse
 */
public class MyTableCellEditor extends AbstractCellEditor implements TableCellEditor{

    public static FrmPagoTodo PObjTodo;
    
    //private database db;
    //FrmPagoTodo db = new FrmPagoTodo();
    private String OldValue=""; //Valor antiguo de la celda
    private String NewValue=""; //valor nuevo de la celda    
    //private String NameColum="";//nombre de la columna
    //private String ID="";// Llave del registro
    private JComponent component = new JTextField();
    private static int Fila=0;
   
    @Override
    public Object getCellEditorValue() {
        return ((JTextField)component).getText();
    }

    public void ActualizaTodo(FrmPagoTodo obj) {
            MyTableCellEditor.PObjTodo = obj;
    }        
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        OldValue = value.toString();//Toma valor de celda antes de cualquier modificación               
        Fila = row;
        System.out.println("fila: "+row);
        ((JTextField)component).setText(value.toString());//coloca valor de la celda al JTextField
        return component;
    }

    @Override
    public boolean stopCellEditing() {
        NewValue = (String)getCellEditorValue();//Captura nuevo valor de la celda
        //Compara valores, si no son iguales, debe actualizar registro
        if( !NewValue.equals(OldValue))
        {   //Realiza la actualizacion  
            
            if( !PObjTodo.updateText(NewValue,Fila))
            {   //Si existe algun error al actualizar, escribe viejo valor en la celda
                JOptionPane.showMessageDialog(null,"Error: Ingrese un valor correcto");
                ((JTextField)component).setText(OldValue);
            }
           
        }
        return super.stopCellEditing();
    }
}
