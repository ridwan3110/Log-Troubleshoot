/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Frm;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Muhammad Ridwan
 */

//LOADING CLASS
public class TestOptionPane11 extends JInternalFrame{
    public static void main(String[] args) {
        new TestOptionPane11();
    }

    public TestOptionPane11() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
              try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(MAIN.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(MAIN.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(MAIN.class.getName()).log(Level.SEVERE, null, ex);
    } catch (UnsupportedLookAndFeelException ex) {
        Logger.getLogger(MAIN.class.getName()).log(Level.SEVERE, null, ex);
    }

                final JDialog dialog = new JDialog((Frame)null, "Boo");
                JLabel label = new javax.swing.JLabel();
label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ridwan/ICON/Loading Jos.gif")));
                JPanel op  = new JPanel();
                op.add(label);
                op.setOpaque (true);
               op.setBackground (new Color (0, 0, 0, 0));
               op.setBackground (new Color (0, 0, 0, 0));
                op.setPreferredSize(new Dimension(64, 68));
                op.addPropertyChangeListener(new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        String name = evt.getPropertyName();
                        if ("value".equals(name)) {

                            dialog.dispose();;
                        }
                    }
                });

                dialog.setUndecorated(true);
                        
                 dialog.getRootPane ().setOpaque (false);
                dialog.getContentPane ().setBackground (new Color (0, 0, 0, 0));
                dialog.setBackground (new Color (0, 0, 0, 0));
                dialog.setLayout(new BorderLayout());
                dialog.add(op);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
                
            }
        });
    }

}
