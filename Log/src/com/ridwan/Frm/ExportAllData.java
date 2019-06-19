/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Frm;

import com.ridwan.DAO.ProjekDao;
import com.ridwan.DAO.TroubleETDao;
import com.ridwan.Model.ProjectModel;
import com.ridwan.Model.TroubleEtModel;
import com.ridwan.TableModel.TableModelExportExcel;
import com.ridwan.TableModel.TableModelTroubleET;
import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.scene.control.Cell;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author Muhammad Ridwan
 */
public class ExportAllData extends javax.swing.JInternalFrame implements DocumentListener{

    private TroubleETDao troubleETDao = new TroubleETDao();
    private ProjekDao projekDao = new ProjekDao();
    private TableModelExportExcel tableModelExportExcel = new TableModelExportExcel();
    private TableModelTroubleET tableModelTroubleET = new TableModelTroubleET();
    private TableRowSorter rowSorter = new TableRowSorter();
    
    
    /**
     * Creates new form ExportFrm
     */
    public ExportAllData() {
        initComponents();
         setIconifiable(false);
        setMaximizable(false);
        setClosable(false);
        setResizable(false);
        txt_terimakasih.setVisible(false);
       loading.setVisible(false);
       ProgressBar.setVisible(false);
       tabelexport.setModel(tableModelExportExcel);
       loadProjek();
       
    // tabelexport.setDefaultRenderer(Object.class, new RenderWarnaWarni(Color.red, Color.yellow,Color.white));
     
      rowSorter = new TableRowSorter(tabelexport.getModel());
        tabelexport.setRowSorter(rowSorter);
        txt_cari.getDocument().addDocumentListener(this);
    
    }
    
 
    
    private void saring(){
        String text = txt_cari.getText();
        if (text.length()==0){
            rowSorter.setRowFilter(null);
        }else {
           rowSorter.setRowFilter(RowFilter.regexFilter
        (Pattern.compile("(?i).*" + text + ".*", Pattern.CASE_INSENSITIVE|Pattern.DOTALL ).toString()));
        }
     }

    
    
    public void exportTable(JTable table, File file) throws IOException {
            TableModel model = table.getModel();
            FileWriter out = new FileWriter(file);
            for(int i=0; i < model.getColumnCount(); i++) {
        out.write(model.getColumnName(i) + "\t");
            }
            out.write("\n");

            for(int i=0; i< model.getRowCount(); i++) {
        for(int j=0; j < model.getColumnCount(); j++) {
            out.write(model.getValueAt(i,j)+"\t");
            }
            out.write("\n");
        }

        out.close();
        System.out.println("write out to: " + file);
}

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
    
    
    
      class tampilWorker extends SwingWorker{

        @Override
        protected Object doInBackground() throws Exception {
        for (int i=0; i<=120 ; i++){
            Thread.sleep(12);
            ProgressBar.setValue(i);
            //ProgressBar.setVisible(true);
            loading.setVisible(true);
            txt_terimakasih.setVisible(true);
           ProgressBar.setIndeterminate(true);
           
        }
            return null;
        }
        
        @Override
        protected void done (){
            ProgressBar.setVisible(false);
            loading.setVisible(false);
            txt_terimakasih.setVisible(false);
        }
    }
    
   
    
    
      public JTextField getTxtPengguna (){
        return txt_petugas;
    }
    
    public void setTxtPengguna (JTextField txtpetugas){
        
    this.txt_petugas = txtpetugas;
    
    }
    
    
    
     private void loadProjek(){
        List<ProjectModel> projekModels = projekDao.getProjekModels();
        for (ProjectModel pp : projekModels){
            cbo_projek.addItem(pp);
           // cbo_projekcvm.addItem(pp);
        }
    }
    
     
     
     
     
     private boolean validasiInput(){
         boolean valid = false;
         if (txt_tglawal.getDate()==null){
             JOptionPane.showMessageDialog(null, "Tanggal Awal Belum Dipilih");
         }else if (txt_akhir.getDate()==null){
             JOptionPane.showMessageDialog(null, "Tanggal Akhir Belum Dipilih");
         }
         
         else {
             valid = true;
         }
         return valid;
     }
     
     
      public class RenderWarnaWarni extends DefaultTableCellRenderer {
 
    private static final long serialVersionUID = 47612794125L;
 
    // warna background untuk baris ganjil
    private Color tabel;
    private Color tabel2;
    private Color tabel3;
    
    public RenderWarnaWarni(Color tabel, Color tabel2, Color tabel3) {
       this.tabel = tabel;
       this.tabel2 = tabel2;
       this.tabel3 = tabel3;
    }
 
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // mendapatkan component superclass
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        int a = column=12;
        TroubleEtModel t = new TroubleEtModel();
  value = tabelexport.getModel().getValueAt(row, 16);
  if (value.equals("Pending")) {
                component.setBackground(tabel);
            } else if (value.equals("Open")) {
                component.setBackground(tabel2);
            }else {
                component.setBackground(tabel3);
            }
        
    
   
        // mengembalikan komponen
        return component;
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

        txt_tglawal = new com.toedter.calendar.JDateChooser();
        txt_akhir = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txt_petugas = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        BTNEXPORT = new javax.swing.JButton();
        CBO_DRIVEREXPORT = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        loading = new javax.swing.JLabel();
        txt_terimakasih = new javax.swing.JLabel();
        ProgressBar = new javax.swing.JProgressBar();
        jLabel6 = new javax.swing.JLabel();
        cbo_projek = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        cbo_status = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelexport = new javax.swing.JTable();
        btnHapus1 = new javax.swing.JButton();
        BTNCARI = new javax.swing.JButton();
        txt_cari = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setOpaque(true);

        txt_tglawal.setFocusable(false);
        txt_tglawal.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N

        txt_akhir.setFocusable(false);
        txt_akhir.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(0, 51, 153));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ridwan/ICON/susu.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2)
        );

        jPanel3.setBackground(new java.awt.Color(0, 255, 255));

        txt_petugas.setBackground(new java.awt.Color(0, 255, 255));
        txt_petugas.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        txt_petugas.setForeground(new java.awt.Color(0, 0, 153));
        txt_petugas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_petugas.setBorder(null);
        txt_petugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_petugasActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(0, 51, 153));
        jSeparator1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ridwan/ICON/left-arrow (1).png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        BTNEXPORT.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        BTNEXPORT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ridwan/ICON/share(1).png"))); // NOI18N
        BTNEXPORT.setText("EXPORT TO EXCEL");
        BTNEXPORT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNEXPORTActionPerformed(evt);
            }
        });

        CBO_DRIVEREXPORT.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        CBO_DRIVEREXPORT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- PILIH -", "D", "E", "F", "G", "H" }));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setText("Drive Export :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 604, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(BTNEXPORT)
                        .addGap(46, 46, 46)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(CBO_DRIVEREXPORT, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(461, 461, 461)
                        .addComponent(txt_petugas, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_petugas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addComponent(BTNEXPORT, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(CBO_DRIVEREXPORT, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel4.setText("Dari Tanggal :");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel5.setText("Sampai Tanggal :");

        loading.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ridwan/ICON/Loading Jos.gif"))); // NOI18N

        txt_terimakasih.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_terimakasih.setForeground(new java.awt.Color(0, 51, 153));
        txt_terimakasih.setText("Berhasil DI Proses");

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel6.setText("Projek :");

        cbo_projek.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        cbo_projek.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- ALL -" }));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel7.setText("Status :");

        cbo_status.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        cbo_status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- ALL -", "Done", "Open" }));

        tabelexport.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabelexport);

        btnHapus1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnHapus1.setText("EXPORT TO EXCEL");
        btnHapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapus1ActionPerformed(evt);
            }
        });

        BTNCARI.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        BTNCARI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ridwan/ICON/Search_32x32.png"))); // NOI18N
        BTNCARI.setText("CARI DATA");
        BTNCARI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNCARIActionPerformed(evt);
            }
        });

        txt_cari.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel3.setText("Cari :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(35, 35, 35)
                .addComponent(cbo_projek, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel4)
                .addGap(36, 36, 36)
                .addComponent(txt_tglawal, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(loading)
                        .addGap(20, 20, 20))
                    .addComponent(txt_terimakasih, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(34, 34, 34)
                .addComponent(txt_akhir, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(27, 27, 27)
                .addComponent(cbo_status, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(874, 874, 874)
                        .addComponent(BTNCARI)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(28, 28, 28)
                        .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(919, 919, 919)
                        .addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(880, 880, 880)
                    .addComponent(btnHapus1)
                    .addContainerGap(880, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(cbo_projek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txt_tglawal, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addComponent(txt_akhir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cbo_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(loading)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addComponent(txt_terimakasih)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BTNCARI, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(493, 493, 493)
                    .addComponent(btnHapus1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(493, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_petugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_petugasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_petugasActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private boolean validasiexport(){
        boolean valid = false;
        if (CBO_DRIVEREXPORT.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "Silahkan Pilih Drive Export");
        }else {
            valid = true;
        }
        return valid;
    }
    
    
    private void BTNEXPORTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNEXPORTActionPerformed
       String a = (String) CBO_DRIVEREXPORT.getSelectedItem();
        if (validasiexport()==true){
            try {
            exportTable(tabelexport, new File(a+":/HasilExport.xls"));
            // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(ExportAllData.class.getName()).log(Level.SEVERE, null, ex);
        }
tampilWorker w = new tampilWorker();
w.execute();
        JOptionPane.showMessageDialog(null, "Data Berhasil Diexport ke"+a);    
       
       }
        
         
       
        // TODO add your handling code here:
    }//GEN-LAST:event_BTNEXPORTActionPerformed

    private void btnHapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapus1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHapus1ActionPerformed

    private void BTNCARIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNCARIActionPerformed
if (validasiInput()==true){
    if (cbo_projek.getSelectedIndex()==0&cbo_status.getSelectedIndex()==0){
        List<TroubleEtModel> etModelsByDate2 = troubleETDao.getEtModelsByDate2(txt_tglawal.getDate(), txt_akhir.getDate());
        tableModelExportExcel.setData(etModelsByDate2);
         
}else if (cbo_projek.getSelectedIndex()==0&cbo_status.getSelectedIndex()!=0){
        List<TroubleEtModel> etModelsBytgl2andstatus = troubleETDao.getEtModelsBytgl2andstatus(txt_tglawal.getDate(), txt_akhir.getDate(), (String) cbo_status.getSelectedItem());
    tableModelExportExcel.setData(etModelsBytgl2andstatus);
}else if (cbo_projek.getSelectedIndex()!=0&cbo_status.getSelectedIndex()==0){
        List<TroubleEtModel> export = troubleETDao.getExport((ProjectModel) cbo_projek.getSelectedItem(), txt_tglawal.getDate(), txt_akhir.getDate());
        tableModelExportExcel.setData(export);
}
    
    else {
        String a = (String) cbo_status.getSelectedItem();
        ProjectModel b = (ProjectModel) cbo_projek.getSelectedItem();
        Date c = txt_tglawal.getDate();
        Date e = txt_akhir.getDate();
        List<TroubleEtModel> forexportandview = troubleETDao.forexportandview(a,b, c, e);
        tableModelExportExcel.setData(forexportandview);
         
    }
}        // TODO add your handling code here:
    }//GEN-LAST:event_BTNCARIActionPerformed

   
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNCARI;
    private javax.swing.JButton BTNEXPORT;
    private javax.swing.JComboBox CBO_DRIVEREXPORT;
    private javax.swing.JProgressBar ProgressBar;
    private javax.swing.JButton btnHapus1;
    private javax.swing.JComboBox cbo_projek;
    private javax.swing.JComboBox cbo_status;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel loading;
    private javax.swing.JTable tabelexport;
    private com.toedter.calendar.JDateChooser txt_akhir;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_petugas;
    private javax.swing.JLabel txt_terimakasih;
    private com.toedter.calendar.JDateChooser txt_tglawal;
    // End of variables declaration//GEN-END:variables
}
