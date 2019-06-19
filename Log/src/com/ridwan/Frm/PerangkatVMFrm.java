/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Frm;

import TableModelHeadercheckBox.TableHeaderCheckBox;
import com.ridwan.DAO.PerangkatVMDao;
import com.ridwan.Dialog.DialogAddPerangkatVM;
import com.ridwan.Model.PerangkatVMModel;
import com.ridwan.TableModel.TableModelPerangkatVM;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ciwong
 */
public class PerangkatVMFrm extends javax.swing.JInternalFrame implements DocumentListener{

    private TableModelPerangkatVM tableModelPerangkatVM = new TableModelPerangkatVM();
    private PerangkatVMDao  perangkatVMDao = new PerangkatVMDao();
    private TableRowSorter rowSorter = new TableRowSorter();
    /**
     * Creates new form PerangkatVMFrm
     */
    public PerangkatVMFrm() {
        initComponents();
        tabel_Perangkatvm.setModel(tableModelPerangkatVM);
        setTitle("Data Part");
        setClosable(true);
        setIconifiable(true);
                loadData();
                 tabel_Perangkatvm.setModel(tableModelPerangkatVM);
       //  tabel_Perangkatvm.getColumnModel().getColumn(2).setHeaderRenderer(new TableHeaderCheckBox(tabel_Perangkatvm.getTableHeader(), 2));
        loadData();
        
          rowSorter = new TableRowSorter(tabel_Perangkatvm.getModel());
        tabel_Perangkatvm.setRowSorter(rowSorter);
        txt_Cari.getDocument().addDocumentListener(this);
    }
    
    private void loadData(){
        List<PerangkatVMModel> perangkatVMModels = perangkatVMDao.getPerangkatVMModels();
        tableModelPerangkatVM.setData(perangkatVMModels);
    }
    
    
      private void saring(){
        String text = txt_Cari.getText();
        if (text.length()==0){
            rowSorter.setRowFilter(null);
        }else {
           rowSorter.setRowFilter(RowFilter.regexFilter
        (Pattern.compile("(?i).*" + text + ".*", Pattern.CASE_INSENSITIVE|Pattern.DOTALL ).toString()));
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_Perangkatvm = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txt_Cari = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnTambah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();

        tabel_Perangkatvm.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabel_Perangkatvm);

        jLabel1.setText("Cari :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txt_Cari, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_Cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ridwan/ICON/Add_24x24.png"))); // NOI18N
        btnTambah.setText("Tambah Data");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });
        jPanel2.add(btnTambah);

        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ridwan/ICON/Remove_24x24.png"))); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        jPanel2.add(btnHapus);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
List<PerangkatVMModel> cekPerangkatVm = tableModelPerangkatVM.getPVMcek();
if (!cekPerangkatVm.isEmpty()){
    if (JOptionPane.showConfirmDialog(null, "Konfirmasi", "Apakah Anda Ingin Menghapus Data Ini ?", JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
        boolean valid = true;
        for (PerangkatVMModel model : cekPerangkatVm){
            if (valid){
                perangkatVMDao.deletePVM(model);
            }
            loadData();
        }
        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
    }
    
}else {
    JOptionPane.showMessageDialog(null, "Seleksi Salah Satu Baris");
}

// TODO add your handling code here:
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
DialogAddPerangkatVM  dialogAddPerangkatVM = new DialogAddPerangkatVM();
PerangkatVMModel model = dialogAddPerangkatVM.insertPVM();
if (perangkatVMDao.insertPVM(model)==true){
    JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
    loadData();
}else {
    JOptionPane.showMessageDialog(null, "Data Gagal Disimpan");
    loadData();
}

// TODO add your handling code here:
    }//GEN-LAST:event_btnTambahActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnTambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabel_Perangkatvm;
    private javax.swing.JTextField txt_Cari;
    // End of variables declaration//GEN-END:variables

    @Override
    public void insertUpdate(DocumentEvent e) {
        saring();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
    saring();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    saring();
    }
}