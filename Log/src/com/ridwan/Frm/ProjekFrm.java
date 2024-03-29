/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Frm;

import TableModelHeadercheckBox.TableHeaderCheckBox;
import com.ridwan.DAO.ProjekDao;
import com.ridwan.Dialog.DialogAddProjek;
import com.ridwan.Model.ProjectModel;
import com.ridwan.TableModel.TableModelProjek;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ciwong
 */
public class ProjekFrm extends javax.swing.JInternalFrame {
    
    private TableModelProjek tableModelProjek = new TableModelProjek();
    private ProjekDao projekDao = new ProjekDao();
    

    /**
     * Creates new form ZonaFrm
     */
    public ProjekFrm() {
        initComponents();
        tabel_projek.setModel(tableModelProjek);
        setTitle("Data Projek");
        setClosable(true);
        setIconifiable(true);
        loadData();
         tabel_projek.setModel(tableModelProjek);
       //  tabel_projek.getColumnModel().getColumn(2).setHeaderRenderer(new TableHeaderCheckBox(tabel_projek.getTableHeader(), 2));
        loadData();
    }
    
    
    private void loadData(){
        List<ProjectModel> projekModels = projekDao.getProjekModels();
        tableModelProjek.setData(projekModels);
    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_projek = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnTambah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();

        tabel_projek.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabel_projek);

        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ridwan/ICON/Add_24x24.png"))); // NOI18N
        btnTambah.setText("Tambah Data");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });
        jPanel1.add(btnTambah);

        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ridwan/ICON/Remove_24x24.png"))); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        jPanel1.add(btnHapus);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
DialogAddProjek dialogAddZona = new DialogAddProjek();
ProjectModel model = dialogAddZona.insertZona();
if (projekDao.insertProjek(model)==true){
    JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
    loadData();
}else {
    JOptionPane.showMessageDialog(null, "Data Gagal Disimpan");
    loadData();
}
        

// TODO add your handling code here:
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
List<ProjectModel> cekzona = tableModelProjek.getZonacek();
if (!cekzona.isEmpty()){
    
    if (JOptionPane.showConfirmDialog(null, "Konfirmasi", "Apakah Anda Ingin Menghapus Data Ini ?", JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
        boolean valid = true;
        for (ProjectModel pm : cekzona){
            if (valid){
                projekDao.deleteProjek(pm);       
            }
            loadData();
        }
        JOptionPane.showMessageDialog(null, "Data Berhasil DIhapus");
    }
}else {
    JOptionPane.showMessageDialog(null, "Seleksi Salah Satu Baris");
}
// TODO add your handling code here:
    }//GEN-LAST:event_btnHapusActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnTambah;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabel_projek;
    // End of variables declaration//GEN-END:variables
}
