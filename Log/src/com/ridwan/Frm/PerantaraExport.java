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
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.beans.PropertyVetoException;
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
import javafx.scene.control.Cell;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.table.TableModel;
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
public class PerantaraExport extends javax.swing.JInternalFrame {

    private TroubleETDao troubleETDao = new TroubleETDao();
    private ProjekDao projekDao = new ProjekDao();
    private TableModelExportExcel tableModelExportExcel = new TableModelExportExcel();
    JTable table = new JTable();
    MAIN mm = new MAIN();
    
    /**
     * Creates new form ExportFrm
     */
    public PerantaraExport() {
        initComponents();
         setIconifiable(false);
        setMaximizable(false);
        setClosable(false);
        setResizable(false);
        //txt_terimakasih.setVisible(false);
      // loading.setVisible(false);
       ProgressBar.setVisible(false);
       
        table.setModel(tableModelExportExcel);
    }
    
   /*private void loadData(){
        List<TroubleEtModel> export = troubleETDao.getExport((ProjectModel) cbo_projek.getSelectedItem(), txt_tglawal.getDate(), txt_akhir.getDate());
       tableModelExportExcel.setData(export);
   }*/
   
    
    
    
   /* public void exportTable(JTable table, File file) throws IOException {
            TableModel model = table.getModel();
            FileWriter out = new FileWriter(file);
            for(int i=0; i < model.getColumnCount(); i++) {
        out.write(model.getColumnName(i) + "\t");
            }
            out.write("\n");

            for(int i=0; i< model.getRowCount(); i++) {
        for(int j=0; j < model.getColumnCount(); j++) {
            out.write(model.getValueAt(i,j).toString()+"\t");
            }
            out.write("\n");
        }

        out.close();
        System.out.println("write out to: " + file);
}
    */
    
    
     /* class tampilWorker extends SwingWorker{

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
    }*/
    public void setSkrin(JInternalFrame frame){
         mm.getDesktopPane().setSelectedFrame(frame);
        Dimension scrin = this.getSize();
        Dimension dim = frame.getSize();
        frame.setLocation((scrin.width-dim.width)/2, ((scrin.height-dim.height)/2));
        frame.setVisible(true);
    }
    
   
    
    
      public JTextField getTxtPengguna (){
        return txt_petugas;
    }
    
    public void setTxtPengguna (JTextField txtpetugas){
        
    this.txt_petugas = txtpetugas;
    
    }
    
    
    
    
    
     /*private void loadProjek(){
        List<ProjectModel> projekModels = projekDao.getProjekModels();
        for (ProjectModel pp : projekModels){
            cbo_projek.addItem(pp);
           // cbo_projekcvm.addItem(pp);
        }
    }
    */
     
     
     
     
    /* private boolean validasiInput2(){
         boolean valid = false;
         if (txt_tglawal.getDate()==null){
             JOptionPane.showMessageDialog(null, "Tanggal Awal Belum Dipilih");
         }else if (txt_akhir.getDate()==null){
             JOptionPane.showMessageDialog(null, "Tanggal Akhir Belum Dipilih");
         }else {
             valid = true;
         }
         return valid;
     }
*/
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txt_petugas = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        ProgressBar = new javax.swing.JProgressBar();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setOpaque(true);

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_petugas, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txt_petugas, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(0, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ridwan/ICON/calculator.png"))); // NOI18N
        jLabel7.setText("Count Data");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(0, 0, 153));

        jSeparator3.setForeground(new java.awt.Color(0, 255, 255));

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Export Data Untuk Report Harian..");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jSeparator3)
                        .addContainerGap())))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel7.setBackground(new java.awt.Color(0, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel7MouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ridwan/ICON/share.png"))); // NOI18N
        jLabel9.setText("Export Data");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(0, 0, 153));

        jSeparator4.setForeground(new java.awt.Color(0, 255, 255));

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Export Data To Excel..");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jSeparator4)
                        .addContainerGap())))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel10.setBackground(new java.awt.Color(0, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel10MouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ridwan/ICON/share.png"))); // NOI18N
        jLabel11.setText("Dokumentasi");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        jPanel11.setBackground(new java.awt.Color(0, 0, 153));

        jSeparator5.setForeground(new java.awt.Color(0, 255, 255));

        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Export Dokumentasi Pemasangan Barang..");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jSeparator5)
                        .addContainerGap())))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(609, 609, 609)
                        .addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(145, 145, 145)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(159, 159, 159)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(202, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 306, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(301, 301, 301)
                .addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_petugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_petugasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_petugasActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
dispose();               // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

   /* private void ExportCCForm(){
        List<TroubleEtModel> etModelsbystatus = troubleETDao.getEtExportStatusCC(txt_tglawal.getDate(), txt_akhir.getDate());
    
        
         try {
             //tampilWorker w = new tampilWorker();
             w.execute();
     
        //Get the excel file.
        FileInputStream file = new FileInputStream(new File("E:/FORM HARIAN IT & CALL CENTER/Form Call Center & IT Support Unit.xlsx"));

        //Get workbook for XLS file.
       XSSFWorkbook yourworkbook = new XSSFWorkbook(file);

        //Get first sheet from the workbook.
        //If there have >1 sheet in your workbook, you can change it here IF you want to edit other sheets.
        XSSFSheet sheet1 = yourworkbook.getSheetAt(0);

        for (int r = 34; r<etModelsbystatus.size()+34; r++ ){
             Row row = sheet1.getRow(r);
             for (int c = 10; c<=12; c++){
             if (c==10){
            org.apache.poi.ss.usermodel.Cell column = row.getCell(c);
            String updatename = column.getStringCellValue();
               updatename=String.valueOf(etModelsbystatus.get(r-34).getNamaProjek());
             column.setCellValue(updatename);
           
        }else if (c==11){
              org.apache.poi.ss.usermodel.Cell column1 = row.getCell(c);
        String updatename1 = column1.getStringCellValue();
            updatename1=String.valueOf(etModelsbystatus.get(r-34).getStatus2());
             column1.setCellValue(updatename1);
        }else if (c==12){
              org.apache.poi.ss.usermodel.Cell column2 = row.getCell(c);
        int updatename2 = (int) column2.getNumericCellValue();
            updatename2=Integer.valueOf(etModelsbystatus.get(r-34).getCount());
             column2.setCellValue(updatename2);
        }
       
             }
        
        }
        
        // Get the row of your desired cell.
        // Let's say that your desired cell is at row 2.
       
        //System.out.println(updatename);
         
        //Close the excel file.
        file.close();
        //Where you want to save the updated sheet.
        FileOutputStream out = 
            new FileOutputStream(new File("E:/FORM HARIAN IT & CALL CENTER/Form Call Center & IT Support Unit.xlsx"));
        yourworkbook.write(out);
        out.close();
          JOptionPane.showMessageDialog(null, "Data Berhasil DiExport ke E:/FORM HARIAN IT & CALL CENTER/Form Call Center & IT Support Unit.xlsx");

    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
        
    }
    
    
    
    
    private void ExportITForm(){
        List<TroubleEtModel> etModelsbystatus = troubleETDao.getEtExportStatusIT(txt_tglawal.getDate(), txt_akhir.getDate());
    
        
         try {
             tampilWorker w = new tampilWorker();
             w.execute();
     
        //Get the excel file.
        FileInputStream file = new FileInputStream(new File("E:/FORM HARIAN IT & CALL CENTER/Form Call Center & IT Support Unit.xlsx"));

        //Get workbook for XLS file.
        XSSFWorkbook yourworkbook = new XSSFWorkbook(file);

        //Get first sheet from the workbook.
        //If there have >1 sheet in your workbook, you can change it here IF you want to edit other sheets.
        XSSFSheet sheet1 = yourworkbook.getSheetAt(1);

        for (int r = 34; r<etModelsbystatus.size()+34; r++ ){
             Row row = sheet1.getRow(r);
             for (int c = 10; c<=12; c++){
             if (c==10){
            org.apache.poi.ss.usermodel.Cell column = row.getCell(c);
            String updatename = column.getStringCellValue();
               updatename=String.valueOf(etModelsbystatus.get(r-34).getNamaProjek());
             column.setCellValue(updatename);
           
        }else if (c==11){
              org.apache.poi.ss.usermodel.Cell column1 = row.getCell(c);
        String updatename1 = column1.getStringCellValue();
            updatename1=String.valueOf(etModelsbystatus.get(r-34).getStatus2());
             column1.setCellValue(updatename1);
        }else if (c==12){
              org.apache.poi.ss.usermodel.Cell column2 = row.getCell(c);
        int updatename2 = (int) column2.getNumericCellValue();
            updatename2=Integer.valueOf(etModelsbystatus.get(r-34).getCount());
             column2.setCellValue(updatename2);
        }
       
             }
        
        }
        
        // Get the row of your desired cell.
        // Let's say that your desired cell is at row 2.
       
        //System.out.println(updatename);
         
        //Close the excel file.
        file.close();
        //Where you want to save the updated sheet.
        FileOutputStream out = 
            new FileOutputStream(new File("E:/FORM HARIAN IT & CALL CENTER/Form Call Center & IT Support Unit.xlsx"));
        yourworkbook.write(out);
        out.close();
          JOptionPane.showMessageDialog(null, "Data Berhasil DiExport ke E:/FORM HARIAN IT & CALL CENTER/Form Call Center & IT Support Unit.xlsx");

    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
        
    }*/
    
    
    
    
    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
   /*String a = "Open";
String b = "Done";
        if (validasiInput2()==true){
            
                ExportCCForm();
                ExportITForm();
                
            
        }*/
       ExportbyStatus  exportbyStatus = new ExportbyStatus();
       JDesktopPane desktopPane = getDesktopPane();
desktopPane.add(exportbyStatus);//add f1 to desktop pane
exportbyStatus.setVisible(true);
exportbyStatus.getTxtPengguna().setText(txt_petugas.getText());
try {
            exportbyStatus.setMaximum(true);
            exportbyStatus.setClosable(false);
            exportbyStatus.setIconifiable(false);
            javax.swing.plaf.InternalFrameUI ui = exportbyStatus.getUI();
((javax.swing.plaf.basic.BasicInternalFrameUI)ui).setNorthPane(null);
// TODO add your handling code here:
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MAIN.class.getName()).log(Level.SEVERE, null, ex);
        }   
       
       //.add(exportbyStatus);
        //exportbyStatus.setVisible(true);
        //clearand
        
        
       
        
//excel.getTxtPengguna().setText(txt_petugas.getText());
         //mm.getDesktopPane().setSelectedFrame(excel);
        //mm.repaint();
         //dispose();
       /* try {
            excel.setMaximum(true);
            excel.setClosable(false);
            excel.setIconifiable(false);
            javax.swing.plaf.InternalFrameUI ui = excel.getUI();
((javax.swing.plaf.basic.BasicInternalFrameUI)ui).setNorthPane(null);
// TODO add your handling code here:
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MAIN.class.getName()).log(Level.SEVERE, null, ex);
        }   
        */
       
                // TODO add your handling code here:
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel6MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
 ExportAllData  exportAllData = new ExportAllData();
       JDesktopPane desktopPane = getDesktopPane();
desktopPane.add(exportAllData);//add f1 to desktop pane
exportAllData.setVisible(true);
exportAllData.getTxtPengguna().setText(txt_petugas.getText());
try {
            exportAllData.setMaximum(true);
            exportAllData.setClosable(false);
            exportAllData.setIconifiable(false);
            javax.swing.plaf.InternalFrameUI ui = exportAllData.getUI();
((javax.swing.plaf.basic.BasicInternalFrameUI)ui).setNorthPane(null);
// TODO add your handling code here:
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MAIN.class.getName()).log(Level.SEVERE, null, ex);
        }           // TODO add your handling code here:
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel7MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
ExportDokumentasi exportDokumentasi = new ExportDokumentasi();
       JDesktopPane desktopPane = getDesktopPane();
desktopPane.add(exportDokumentasi);//add f1 to desktop pane
exportDokumentasi.setVisible(true);
exportDokumentasi.getTxtPengguna().setText(txt_petugas.getText());
try {
            exportDokumentasi.setMaximum(true);
            exportDokumentasi.setClosable(false);
            exportDokumentasi.setIconifiable(false);
            javax.swing.plaf.InternalFrameUI ui = exportDokumentasi.getUI();
((javax.swing.plaf.basic.BasicInternalFrameUI)ui).setNorthPane(null);
// TODO add your handling code here:
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MAIN.class.getName()).log(Level.SEVERE, null, ex);
        }            // TODO add your handling code here:
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jPanel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel10MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar ProgressBar;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTextField txt_petugas;
    // End of variables declaration//GEN-END:variables
}
