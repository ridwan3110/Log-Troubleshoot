/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Dialog;

import com.ridwan.DAO.ProjekDao;
import com.ridwan.DAO.StasiunDao;
import com.ridwan.Model.ETModel;
import com.ridwan.Model.ProjectModel;
import com.ridwan.Model.StasiunModel;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ciwong
 */
public class DialogAddPerangkat extends javax.swing.JDialog {
private ETModel etm;
private boolean modeEdit;
private StasiunDao stasiunDao = new StasiunDao();
private ProjekDao projekDao = new ProjekDao();
    /**
     * Creates new form DialogAddPerangkat
     */
    public DialogAddPerangkat() {
        setModal(true);
        initComponents();
        setLocationRelativeTo(null);
        loadProjek();
        
    }
    
    public ETModel insertUser(){
        setTitle("Tambah Data Perangkat");
        this.modeEdit=false;
        setVisible(true);
        setLocationRelativeTo(null);
        return etm;
        
    }
    
    
  /*  private void loadLokasi(){
    List<StasiunModel> stasiunModels = stasiunDao.getStasiunModels();
    for (StasiunModel s : stasiunModels){
        cbo_lokasi.addItem(s);
    }
    }*/
    
    private void loadProjek(){
    List<ProjectModel> projekModels = projekDao.getProjekModels();
    for (ProjectModel pm : projekModels){
        cbo_projek.addItem(pm);
    }
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
        jLabel1 = new javax.swing.JLabel();
        txt_namaperangkat = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_idperangkat = new javax.swing.JTextField();
        txt_type = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbo_lokasi = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        cbo_projek = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        btnSimpan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Nama Perangkat :");

        jLabel2.setText("ID Perangkat :");

        jLabel3.setText("Type :");

        jLabel4.setText("Lokasi :");

        cbo_lokasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- PILIH -" }));
        cbo_lokasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_lokasiActionPerformed(evt);
            }
        });

        jLabel5.setText("Projek :");

        cbo_projek.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- PILIH -" }));
        cbo_projek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_projekActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_type, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addComponent(txt_idperangkat, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addComponent(txt_namaperangkat, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addComponent(cbo_lokasi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbo_projek, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_namaperangkat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_idperangkat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbo_projek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbo_lokasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ridwan/ICON/Add_24x24.png"))); // NOI18N
        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        jPanel2.add(btnSimpan);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
if (modeEdit==false){
etm = new ETModel();
}

if (txt_namaperangkat.getText().trim().isEmpty()){
    JOptionPane.showMessageDialog(null, "Nama Belum Diisi");
}else if (txt_namaperangkat.getText().trim().isEmpty()){
    JOptionPane.showMessageDialog(null,"Id Belum Diisi");
}else if (txt_type.getText().trim().isEmpty()){
    JOptionPane.showMessageDialog(null,"Type Belum Diisi");
}else if (cbo_lokasi.getSelectedIndex()==0){
    JOptionPane.showMessageDialog(null,"Lokasi Belum Dipilih");
}else if (cbo_projek.getSelectedIndex()==0){
    JOptionPane.showMessageDialog(null,"Projek Belum Dipilih");
}
else {
    
    String nama = txt_namaperangkat.getText();
    String id = txt_idperangkat.getText();
    String type = txt_type.getText();
    StasiunModel lokasi = (StasiunModel) cbo_lokasi.getSelectedItem();
    
    
    etm = new ETModel();
    etm.setNamaPerangkat(nama);
    etm.setId(id);
    etm.setType(type);
    etm.setStasiun(lokasi);
    dispose();
    
}

// TODO add your handling code here:
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void cbo_projekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_projekActionPerformed
cbo_lokasi.removeAllItems();
        cbo_lokasi.addItem("- PILIH -");
        //cbo_lokasi.addItem("aduh");
            cbo_lokasi.setSelectedIndex(0);
         List<StasiunModel> modelsByprojek1 = stasiunDao.getModelsByprojek((ProjectModel) cbo_projek.getSelectedItem());
        for (StasiunModel smm1 : modelsByprojek1){       
            cbo_lokasi.addItem(smm1);
             
            }      // TODO add your handling code here:
    }//GEN-LAST:event_cbo_projekActionPerformed

    private void cbo_lokasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_lokasiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_lokasiActionPerformed

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox cbo_lokasi;
    private javax.swing.JComboBox cbo_projek;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txt_idperangkat;
    private javax.swing.JTextField txt_namaperangkat;
    private javax.swing.JTextField txt_type;
    // End of variables declaration//GEN-END:variables
}
