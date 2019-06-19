/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Dialog;

import com.ridwan.DAO.UserDAo;
import com.ridwan.Frm.MAIN;
import com.ridwan.Interface.UserInterface;
import com.ridwan.Interface.UserInterface;
import com.ridwan.Model.UserModel;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author ciwong
 */
public class LoginScreen extends javax.swing.JFrame {

  
    private UserInterface karyawanDao;
    private UserModel k;
    private MAIN m;
    /**
     * Creates new form Login
     */
    public LoginScreen() {
        //setModal(true);
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        labelvalidasi.setVisible(false);
         m = new MAIN();
    }
    
    public UserModel loginPengguna(){
        k = new UserModel();
        setLocationRelativeTo(getParent());
       
        setVisible(true);
         return k;
    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        labelvalidasi = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        labelusername = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_password = new javax.swing.JPasswordField();
        panellogin = new javax.swing.JPanel();
        labellogin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(0, 51, 255));
        setForeground(new java.awt.Color(0, 0, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowIconified(java.awt.event.WindowEvent evt) {
                formWindowIconified(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 51, 153));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Selamat Datang");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ridwan/ICON/susu kiri.png"))); // NOI18N

        labelvalidasi.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        labelvalidasi.setForeground(new java.awt.Color(255, 0, 0));
        labelvalidasi.setText("Username Dan Password Tidak Valid");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(115, 115, 115))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(labelvalidasi)
                        .addGap(105, 105, 105))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(jLabel2)
                .addContainerGap(553, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(78, 78, 78))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addGap(97, 97, 97)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(labelvalidasi)
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(162, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 153));
        jLabel4.setText("Sign In");

        jSeparator1.setBackground(new java.awt.Color(0, 51, 153));

        labelusername.setBackground(new java.awt.Color(0, 255, 255));
        labelusername.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        labelusername.setForeground(new java.awt.Color(0, 51, 153));
        labelusername.setText("Entry Your Username Here");
        labelusername.setBorder(null);
        labelusername.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelusernameMouseClicked(evt);
            }
        });
        labelusername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                labelusernameActionPerformed(evt);
            }
        });

        jSeparator2.setBackground(new java.awt.Color(0, 51, 153));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 153));
        jLabel5.setText("PASSWORD");

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 153));
        jLabel6.setText("USERNAME");

        txt_password.setBackground(new java.awt.Color(0, 255, 255));
        txt_password.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txt_password.setForeground(new java.awt.Color(0, 51, 153));
        txt_password.setText("tetettetettetete");
        txt_password.setBorder(null);
        txt_password.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_passwordMouseClicked(evt);
            }
        });
        txt_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_passwordActionPerformed(evt);
            }
        });

        panellogin.setBackground(new java.awt.Color(0, 51, 153));
        panellogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelloginMouseClicked(evt);
            }
        });
        panellogin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labellogin.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        labellogin.setForeground(new java.awt.Color(255, 255, 255));
        labellogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ridwan/ICON/Key_48x48.png"))); // NOI18N
        labellogin.setText("Sign In");
        panellogin.add(labellogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(143, 143, 143)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSeparator2)
                                .addComponent(jLabel5)
                                .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelusername)
                                .addComponent(jLabel6)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 527, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panellogin, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel4)
                .addGap(112, 112, 112)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(labelusername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panellogin, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(134, 134, 134))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    k = null;
    System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    private void labelusernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_labelusernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_labelusernameActionPerformed

    private void txt_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_passwordActionPerformed

        
        
    }//GEN-LAST:event_txt_passwordActionPerformed

    private void labelusernameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelusernameMouseClicked
labelusername.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_labelusernameMouseClicked

    private void txt_passwordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_passwordMouseClicked
txt_password.setText(null);        // TODO add your handling code here:
    }//GEN-LAST:event_txt_passwordMouseClicked

    private void panelloginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelloginMouseClicked
  karyawanDao = new UserDAo();
String username = labelusername.getText();
char [] pwd = txt_password.getPassword();
String password = new String(pwd);
k = karyawanDao.login(username, password);
if (k!=null){
     m.setVisible(true);
             m.getTxtPengguna().setText(k.getNama());
             dispose();
}

else {
   labelvalidasi.setVisible(true);
}       // TODO add your handling code here:
    }//GEN-LAST:event_panelloginMouseClicked

    private void formWindowIconified(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowIconified
LoginScreen login = new LoginScreen();
login.setExtendedState(MAXIMIZED_BOTH);
login.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_formWindowIconified

    
     public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MAIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MAIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MAIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MAIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginScreen().setVisible(true);
            }
        });
    }
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel labellogin;
    private javax.swing.JTextField labelusername;
    private javax.swing.JLabel labelvalidasi;
    private javax.swing.JPanel panellogin;
    private javax.swing.JPasswordField txt_password;
    // End of variables declaration//GEN-END:variables
}
