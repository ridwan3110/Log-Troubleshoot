/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModelHeadercheckBox;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 *
 * @author DICKA
 */
public class TableHeaderCheckBox implements TableCellRenderer{
    
    private final JCheckBox check=new JCheckBox();
    
    
    public TableHeaderCheckBox(JTableHeader header, final int index){
        check.setOpaque(false);
        check.setFont(header.getFont());
        header.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
              JTable table=((JTableHeader)e.getSource()).getTable();
                TableColumnModel columnModel=table.getColumnModel();
                int viewColumn=columnModel.getColumnIndexAtX(e.getX());
                int modelColumn=table.convertColumnIndexToModel(viewColumn);
                if(modelColumn==index){
                    check.setSelected(!check.isSelected());
                    TableModel m=table.getModel();
                    Boolean f=check.isSelected();
                    for(int i=0; i < m.getRowCount(); i++){
                        m.setValueAt(f, i, index);
                    }
                    ((JTableHeader)e.getSource()).repaint();
                }else return;
            }
            
            
});
    }
    

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        TableCellRenderer r=table.getTableHeader().getDefaultRenderer();
        JLabel l=(JLabel) r.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        l.setIcon(new CheckBoxIcon(check));
        return l;
    }
    
   private static class CheckBoxIcon implements Icon{
       
       private final JCheckBox check;
       
       public CheckBoxIcon(JCheckBox check){
           this.check=check;
       }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
         SwingUtilities.paintComponent(g, check, (Container) c, x, y, getIconWidth(), getIconHeight());
        }

        @Override
        public int getIconWidth() {
         return check.getPreferredSize().width;
        }

        @Override
        public int getIconHeight() {
          return check.getPreferredSize().height;
        }
       
       
       
       
       
   }
    
}
