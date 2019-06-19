/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Dialog;

import com.ridwan.DAO.ETDao;
import com.ridwan.DAO.PenyebabDao;
import com.ridwan.DAO.PerangkatVMDao;
import com.ridwan.DAO.ProjekDao;
import com.ridwan.DAO.StasiunDao;
import com.ridwan.Interface.StasiunInterface;
import com.ridwan.Model.ETModel;
import com.ridwan.Model.PenyebabModel;
import com.ridwan.Model.PerangkatVMModel;
import com.ridwan.Model.ProjectModel;
import com.ridwan.Model.StasiunModel;
import com.ridwan.Model.TroubleVMModel;
import java.util.List;
import javax.swing.JOptionPane;

;

/**
 *
 * @author ciwong
 */
public class DIalogUbahTroubleCVM extends javax.swing.JDialog {

    private TroubleVMModel tvm;
    private boolean modeEdit;
    
    private StasiunInterface stasiunDao = new StasiunDao();
    private PenyebabDao penyebabDao = new PenyebabDao();
    private ETDao eTDao = new ETDao();
    private ProjekDao projekDao = new ProjekDao();
    private PerangkatVMDao mDao = new PerangkatVMDao();
    
    /**
     * Creates new form DIalogUbahTroubleET
     */
    public DIalogUbahTroubleCVM() {
        setModal(true);
        initComponents();
        setLocationRelativeTo(null);
    }
    
    private void loadStasiun(){
        List<StasiunModel> stasiunModels = stasiunDao.getStasiunModels();
        for (StasiunModel s : stasiunModels){
            cbo_stasiun.addItem(s);
        }
    }
    
    private void loadPerangkat(){
        List<PerangkatVMModel> perangkatVMModels = mDao.getPerangkatVMModels();
        for (PerangkatVMModel model : perangkatVMModels){
            cbo_perangkatcvm.addItem(model);
        }
    }
    
     private void loadPenyebab(){
        List<PenyebabModel> penyebabModels = penyebabDao.getPenyebabModels();
        for (PenyebabModel pm: penyebabModels){
            cbo_penyebabcvm.addItem(pm);
        }
        
    }
     
   private void loadProjek(){
        List<ProjectModel> projekModels = projekDao.getProjekModels();
        for (ProjectModel ppp : projekModels){
            cbo_projek.addItem(ppp);
        }
     }
     
    
    
    public TroubleVMModel UpdateTroubleVM(TroubleVMModel t){
        this.modeEdit=true;
        this.tvm=t;
        loadStasiun();
        loadPenyebab();
        loadPerangkat();
        loadProjek();
        txt_idcvim.setText(t.getEtm().getId());
        cbo_stasiun.setSelectedItem((t.getSm()));
        txt_permasalahan.setText(t.getProblem());
        cbo_penyebabcvm.setSelectedItem(t.getPm());
        txt_solusi.setText(t.getSolusi());
        txt_analisa.setText(t.getAnalisa());
        cbo_perangkatcvm.setSelectedItem(t.getPvmm());
        cbo_status.setSelectedItem(t.getStatus());
        cbo_projek.setSelectedItem(t.getPromvm());
        setVisible(true);
        setLocationRelativeTo(null);
        return tvm;
    }
    
    private boolean validasiInput(){
        boolean valid = false;
        if (txt_idcvim.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Id Cvim Belum Diisi");
        }else if(txt_permasalahan.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Permasalahan Belum Diisi");
        }else if (txt_solusi.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Solusi Belum Diisi");
        }else if (txt_analisa.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Analisa Belum diisi");
        }else if (cbo_projek.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "Projek Belum diisi");
        }else if (cbo_stasiun.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "Stasiun Belum diisi");
        
        }else {
            valid = true;
        }
        return valid;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        cbo_stasiun = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        txt_idcvim = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbo_perangkatcvm = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        txt_permasalahan = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_solusi = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbo_penyebabcvm = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        txt_analisa = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cbo_status = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        cbo_projek = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        btnsimpan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel9.setText("Stasiun :");

        jLabel1.setText("ID CVIM :");

        jLabel2.setText("Part VM :");

        jLabel3.setText("Permasalahan :");

        jLabel4.setText("Solusi :");

        jLabel5.setText("Detail :");

        jLabel6.setText("Analisa :");

        jLabel7.setText("Status :");

        cbo_status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- PILIH -", "Done", "Pending" }));

        jLabel8.setText("Projek :");

        cbo_projek.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- PILIH -" }));
        cbo_projek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_projekActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbo_stasiun, 0, 194, Short.MAX_VALUE)
                            .addComponent(cbo_perangkatcvm, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_idcvim)
                            .addComponent(cbo_projek, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_permasalahan)
                            .addComponent(txt_solusi)
                            .addComponent(cbo_penyebabcvm, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_analisa)
                            .addComponent(cbo_status, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(44, 44, 44))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(cbo_projek, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cbo_stasiun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_idcvim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbo_perangkatcvm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_permasalahan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_solusi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbo_penyebabcvm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_analisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbo_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        btnsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ridwan/ICON/Add_24x24.png"))); // NOI18N
        btnsimpan.setText("Simpan");
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });
        jPanel1.add(btnsimpan);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
     
         List<ETModel> etModelbyIDPerangkat = eTDao.getEtModelbyIDPerangkatANDstasiun(txt_idcvim.getText(), String.valueOf(cbo_stasiun.getSelectedItem()));
        if (etModelbyIDPerangkat.isEmpty()){
            JOptionPane.showMessageDialog(null, "id cvim tidak terdaftar");
        }   
        else {
                if (validasiInput()==true){


   
    
    StasiunModel stasiun = (StasiunModel) cbo_stasiun.getSelectedItem();
    String permasalahan = txt_permasalahan.getText();
    PenyebabModel penyebab = (PenyebabModel) cbo_penyebabcvm.getSelectedItem();
    String solusi = txt_solusi.getText();
    PerangkatVMModel perangkat = (PerangkatVMModel) cbo_perangkatcvm.getSelectedItem();
    String analisa = txt_analisa.getText();
    String status1 = (String) cbo_status.getSelectedItem();
    ProjectModel p = (ProjectModel) cbo_projek.getSelectedItem();
    
   tvm.setEtm(etModelbyIDPerangkat.get(0));
   tvm.setSm(stasiun);
   tvm.setProblem(permasalahan);
   tvm.setPm(penyebab);
   tvm.setSolusi(solusi);
   tvm.setPvmm(perangkat);
   tvm.setAnalisa(analisa);
   tvm.setStatus(status1);
   tvm.getPromvm();
    JOptionPane.showMessageDialog(null, "Data Berhssil diupdate");
    dispose();
    
    
}

 }
// TODO add your handling code here:
    }//GEN-LAST:event_btnsimpanActionPerformed

    
    public void addstasiunbyprojekcvm(){
     cbo_stasiun.removeAllItems();
        cbo_stasiun.addItem("- PILIH -");
            cbo_stasiun.setSelectedIndex(0);
            
            if (cbo_projek.getSelectedIndex()==0){
            //JOptionPane.showMessageDialog(null, "Perangkat VM Belum Dipilih");
        }else {
         List<StasiunModel> modelsByprojek = stasiunDao.getModelsByprojek((ProjectModel) cbo_projek.getSelectedItem());
        for (StasiunModel smm : modelsByprojek){       
            cbo_stasiun.addItem(smm);
        }         
            }
            
    }
    
    private void cbo_projekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_projekActionPerformed
      addstasiunbyprojekcvm();  // TODO add your handling code here:
    }//GEN-LAST:event_cbo_projekActionPerformed

    /**
     * @param args the command line arguments
     */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnsimpan;
    private javax.swing.JComboBox cbo_penyebabcvm;
    private javax.swing.JComboBox cbo_perangkatcvm;
    private javax.swing.JComboBox cbo_projek;
    private javax.swing.JComboBox cbo_stasiun;
    private javax.swing.JComboBox cbo_status;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txt_analisa;
    private javax.swing.JTextField txt_idcvim;
    private javax.swing.JTextField txt_permasalahan;
    private javax.swing.JTextField txt_solusi;
    // End of variables declaration//GEN-END:variables
}