/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Dialog;

import com.ridwan.DAO.ETDao;
import com.ridwan.DAO.PenyebabDao;
import com.ridwan.DAO.ProjekDao;
import com.ridwan.DAO.StasiunDao;
import com.ridwan.DAO.TroubleETDao;
import com.ridwan.DAO.UserDAo;
import com.ridwan.Frm.TroubleETFrmGabung;
import com.ridwan.Interface.StasiunInterface;
import com.ridwan.Model.ETModel;
import com.ridwan.Model.PenyebabModel;
import com.ridwan.Model.ProjectModel;
import com.ridwan.Model.StasiunModel;
import com.ridwan.Model.TroubleEtModel;
import com.ridwan.Model.UserModel;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author ciwong
 */
public class DIalogUbahTroubleET extends javax.swing.JDialog {

    private TroubleEtModel tm;
    private boolean modeEdit;
   
    
    private StasiunInterface stasiunDao = new StasiunDao();
    private PenyebabDao penyebabDao = new PenyebabDao();
    private ETDao eTDao = new ETDao();
    private ProjekDao projekDao = new ProjekDao();
    javax.swing.Timer tD;
    
    private UserDAo userDAo = new UserDAo();
   private Image image;
   private Image image1;
   
   private TroubleETDao troubleETDao = new TroubleETDao();
    
    /**
     * Creates new form DIalogUbahTroubleET
     */
    public DIalogUbahTroubleET() {
        setModal(true);
        initComponents();
        setLocationRelativeTo(null);
 txt_petugas.setVisible(false);
 txt_tanggalmasalah.setVisible(false);
 txt_jammas.setVisible(false);
        
    }
    
    
    public JTextField getTxtPengguna (){
        return txt_petugas;
    }
    
    public void setTxtPengguna (JTextField txtpetugas){
        
    this.txt_petugas = txtpetugas;
    }
    
    
    public class ClockListener1 implements ActionListener{
    public void actionPerformed(ActionEvent e) {
 
            Calendar now = Calendar.getInstance();
            int h = now.get(Calendar.HOUR_OF_DAY);
            int m = now.get(Calendar.MINUTE);
            int s = now.get(Calendar.SECOND);
            txt_jamD.setText("" + h + ":" + m + ":" + s);
    	}
}
    
    
    
  
    
    public TroubleEtModel UpdateTroubleEt(TroubleEtModel t) throws IOException, ClassNotFoundException{
        this.modeEdit=true;
        this.tm=t;
       CBO_JENIS.setSelectedItem(t.getJenisLaporan());
       CBO_JENIS.setEnabled(false);
       if (CBO_JENIS.getSelectedItem()=="PEKERJAAN"){
           Date dd = new Date();
        txt_TanggalD.setDate(dd);
         tD = new javax.swing.Timer(1000, new ClockListener1());
        tD.start();
        txt_permasalahan.setText(t.getProblem());
        txt_solusi.setText(t.getSolusi());
       // txt_sumber.setEnabled(false);
        //txt_sumber.setText("null");
        txt_sumber.setText(t.getSumber());
        cbo_status.setSelectedItem(t.getStatus());
         CBO_JENIS.setSelectedItem(t.getJenisLaporan());
         txt_ref.setText(t.getRef());
         txt_teknisi.setText(t.getTeknisi());
         txt_tanggalmasalah.setText(String.valueOf(t.getTglMasalah()));
         txt_jammas.setText(String.valueOf(t.getJamMasalah()));
         if (t.getPicbefore()!=null&&t.getPicafter()==null){
              ObjectInputStream  inputStream = new ObjectInputStream(new ByteArrayInputStream(t.getPicbefore()));
                            ImageIcon icon  =  (ImageIcon) inputStream.readObject();
                            image = icon.getImage();
                            panelgambarsebelum.setImage(image);  
                            panelafter.setImage(null);
                            inputStream.close();
         } else if (t.getPicafter()!=null&&t.getPicbefore()==null){
             ObjectInputStream  inputStream1 = new ObjectInputStream(new ByteArrayInputStream(t.getPicafter()));
                            ImageIcon icon1  =  (ImageIcon) inputStream1.readObject();
                            image1 = icon1.getImage();
                            panelafter.setImage(image1); 
                            panelgambarsebelum.setImage(null);
                            inputStream1.close();
         }else if (t.getPicafter()!=null&&t.getPicbefore()!=null){
             ObjectInputStream  inputStream1 = new ObjectInputStream(new ByteArrayInputStream(t.getPicafter()));
             ObjectInputStream  inputStream = new ObjectInputStream(new ByteArrayInputStream(t.getPicbefore()));
             ImageIcon icon  =  (ImageIcon) inputStream.readObject();
                            image = icon.getImage();
                            panelgambarsebelum.setImage(image);
                            inputStream.close();
                            
                            ImageIcon icon1  =  (ImageIcon) inputStream1.readObject();
                            image1 = icon1.getImage();
                            panelafter.setImage(image1);
                            inputStream1.close();
         
         
         } else {
             panelgambarsebelum.setImage(null);
             panelafter.setImage(null);
         }
        
            
       }else {
            Date dd = new Date();
        txt_TanggalD.setDate(dd);
         tD = new javax.swing.Timer(1000, new ClockListener1());
        tD.start();
        txt_permasalahan.setText(t.getProblem());
        txt_solusi.setText(t.getSolusi());
        txt_sumber.setText(t.getSumber());
        cbo_status.setSelectedItem(t.getStatus());
         CBO_JENIS.setSelectedItem(t.getJenisLaporan());
         txt_ref.setText(t.getRef());
          txt_teknisi.setText(t.getTeknisi());
          txt_tanggalmasalah.setText(String.valueOf(t.getTglMasalah()));
         txt_jammas.setText(String.valueOf(t.getJamMasalah()));
          if (t.getPicbefore()!=null&&t.getPicafter()==null){
              ObjectInputStream  inputStream = new ObjectInputStream(new ByteArrayInputStream(t.getPicbefore()));
                            ImageIcon icon  =  (ImageIcon) inputStream.readObject();
                            image = icon.getImage();
                            panelgambarsebelum.setImage(image);  
                            panelafter.setImage(null);
                            inputStream.close();
         } else if (t.getPicafter()!=null&&t.getPicbefore()==null){
             ObjectInputStream  inputStream1 = new ObjectInputStream(new ByteArrayInputStream(t.getPicafter()));
                            ImageIcon icon1  =  (ImageIcon) inputStream1.readObject();
                            image1 = icon1.getImage();
                            panelafter.setImage(image1); 
                            panelgambarsebelum.setImage(null);
                            inputStream1.close();
         }else if (t.getPicafter()!=null&&t.getPicbefore()!=null){
             ObjectInputStream  inputStream1 = new ObjectInputStream(new ByteArrayInputStream(t.getPicafter()));
             ObjectInputStream  inputStream = new ObjectInputStream(new ByteArrayInputStream(t.getPicbefore()));
             ImageIcon icon  =  (ImageIcon) inputStream.readObject();
                            image = icon.getImage();
                            panelgambarsebelum.setImage(image);
                            inputStream.close();
                            
                            ImageIcon icon1  =  (ImageIcon) inputStream1.readObject();
                            image1 = icon1.getImage();
                            panelafter.setImage(image1);
                            inputStream1.close();
         
         
         } else {
             panelgambarsebelum.setImage(null);
             panelafter.setImage(null);
         }
       }
       
          
         
        
        setVisible(true);
        setLocationRelativeTo(null);
        return tm;
    }
    
    private boolean validasiInput(){
        boolean valid = false;
        if (btnDone.getText()=="EDIT"){
            JOptionPane.showMessageDialog(null, "Jam Done Belom Diset");
        }else if (txt_permasalahan.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Permasalahan Belum diisi");
        }else if (txt_solusi.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Solusi Belum Diisi");
        }else if (txt_sumber.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Sumber Belum Diisi");
        }else if (cbo_status.getSelectedIndex()==0){
        JOptionPane.showMessageDialog(null, "Status Belum Dipilih");
    }   else if (txt_petugas.getText()==null){
        JOptionPane.showMessageDialog(null, "User Tidak Ada");
    } 
        else {
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
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_permasalahan = new javax.swing.JTextField();
        txt_solusi = new javax.swing.JTextField();
        cbo_status = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        txt_sumber = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txt_jamD = new javax.swing.JTextField();
        btnDone = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        CBO_JENIS = new javax.swing.JComboBox();
        txt_TanggalD = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        txt_ref = new javax.swing.JTextField();
        txt_petugas = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_teknisi = new javax.swing.JTextField();
        panelafter = new com.ridwan.Dialog.panelgambar();
        txt_carigambar = new javax.swing.JTextField();
        btnambilgambar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txt_tanggalmasalah = new javax.swing.JTextField();
        txt_jammas = new javax.swing.JTextField();
        panelgambarsebelum = new com.ridwan.Dialog.panelgambar();
        ceksesudah = new javax.swing.JCheckBox();
        ceksebelum = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        btnsimpan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Permasalahan :");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 93, -1, -1));

        jLabel4.setText("Solusi :");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 124, -1, -1));

        jLabel7.setText("Status :");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 187, -1, -1));
        jPanel2.add(txt_permasalahan, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 90, 278, -1));
        jPanel2.add(txt_solusi, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 121, 278, -1));

        cbo_status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- PILIH -", "Done", "Pending", "Open" }));
        cbo_status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_statusActionPerformed(evt);
            }
        });
        jPanel2.add(cbo_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 184, 278, -1));

        jLabel5.setText("Sumber Aduan :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 152, -1, -1));
        jPanel2.add(txt_sumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 152, 278, -1));

        jLabel1.setText("Waktu Selesai :");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 13, -1, -1));
        jPanel2.add(txt_jamD, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 12, 76, -1));

        btnDone.setText("DONE");
        btnDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoneActionPerformed(evt);
            }
        });
        jPanel2.add(btnDone, new org.netbeans.lib.awtextra.AbsoluteConstraints(352, 11, -1, -1));

        jLabel3.setText("Jenis Laporan :");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 58, -1, -1));

        CBO_JENIS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- PILIH -", "PERMASALAHAN", "PEKERJAAN", "ADUAN" }));
        jPanel2.add(CBO_JENIS, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 52, 278, -1));
        jPanel2.add(txt_TanggalD, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 13, 121, -1));

        jLabel6.setText("Ref BA Logistik :");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 218, -1, -1));
        jPanel2.add(txt_ref, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 215, 278, -1));
        jPanel2.add(txt_petugas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 30, -1));

        jLabel8.setText("Tambahan :");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, -1, -1));
        jPanel2.add(txt_teknisi, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 253, 278, -1));

        panelafter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelafterLayout = new javax.swing.GroupLayout(panelafter);
        panelafter.setLayout(panelafterLayout);
        panelafterLayout.setHorizontalGroup(
            panelafterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 132, Short.MAX_VALUE)
        );
        panelafterLayout.setVerticalGroup(
            panelafterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jPanel2.add(panelafter, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 350, -1, -1));
        jPanel2.add(txt_carigambar, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 285, 177, -1));

        btnambilgambar.setText("Pilih Gambar");
        btnambilgambar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnambilgambarActionPerformed(evt);
            }
        });
        jPanel2.add(btnambilgambar, new org.netbeans.lib.awtextra.AbsoluteConstraints(322, 284, -1, -1));

        jLabel9.setText("Gambar  :");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, -1, -1));
        jPanel2.add(txt_tanggalmasalah, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 30, -1));
        jPanel2.add(txt_jammas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 30, -1));

        panelgambarsebelum.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelgambarsebelumLayout = new javax.swing.GroupLayout(panelgambarsebelum);
        panelgambarsebelum.setLayout(panelgambarsebelumLayout);
        panelgambarsebelumLayout.setHorizontalGroup(
            panelgambarsebelumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        panelgambarsebelumLayout.setVerticalGroup(
            panelgambarsebelumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 58, Short.MAX_VALUE)
        );

        jPanel2.add(panelgambarsebelum, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 350, -1, -1));

        ceksesudah.setText("Sesudah");
        jPanel2.add(ceksesudah, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 320, -1, -1));

        ceksebelum.setText("Sebelum");
        jPanel2.add(ceksebelum, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, -1, -1));

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
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
if (validasiInput()==true){

    Date tanggal = txt_TanggalD.getDate();
    String permasalahan = txt_permasalahan.getText();
    String solusi = txt_solusi.getText();
    String Sumber = txt_sumber.getText();
    String status1 = (String) cbo_status.getSelectedItem();
    String ref =  txt_ref.getText();
    String user = txt_petugas.getText();
    String teknisi = txt_teknisi.getText();
    String tanggalmasalah = txt_tanggalmasalah.getText();
    String jammasalah = txt_jammas.getText();
    
    
    
    //getdate();
    try {
        Date tanggalmas=new SimpleDateFormat("yyyy-mm-dd").parse(tanggalmasalah);
       
        //setdate
        tm.setTglMasalah(tanggalmas);
    } catch (ParseException ex) {
        Logger.getLogger(DIalogUbahTroubleET.class.getName()).log(Level.SEVERE, null, ex);
    }
    List<UserModel> userbyname = userDAo.getuserbyname(txt_petugas.getText());
    
    
    
      SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");
    String timeD = txt_jamD.getText();
    String timeM = txt_jammas.getText();
            try {
        java.sql.Time timeValue1 = new java.sql.Time(dateFormat2.parse(timeD).getTime());
        java.sql.Time timeValue2 = new java.sql.Time(dateFormat2.parse(timeM).getTime());
        
        //settime
        tm.setJamDone(timeValue1);
        tm.setJamMasalah(timeValue2);
        
    } catch (ParseException ex) {
        Logger.getLogger(DIalogUbahTroubleET.class.getName()).log(Level.SEVERE, null, ex);
    }
            //getdate();
            
            
            
            
            
            //getimage();
            if(status1=="Done"&&tm.getPicbefore()!=null&&tm.getPicafter()!=null){
            ObjectOutputStream objectOutputStream=null;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream1=null;
            ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
            try {
                objectOutputStream = new ObjectOutputStream(outputStream);
                ImageIcon icon = new ImageIcon(image);
                objectOutputStream.writeObject(icon);
                objectOutputStream.flush();
                objectOutputStream.close();
                
                
                  objectOutputStream1 = new ObjectOutputStream(outputStream1);
                ImageIcon icon1 = new ImageIcon(image1);
                objectOutputStream1.writeObject(icon1);
                objectOutputStream1.flush();
                objectOutputStream1.close();
            } catch (IOException ex) {
                Logger.getLogger(DIalogUbahTroubleET.class.getName()).log(Level.SEVERE, null, ex);
            }
            tm.setPicafter(outputStream1.toByteArray());
            tm.setPicbefore(outputStream.toByteArray());
            
        }else if(status1=="Done"&&tm.getPicbefore()==null&&tm.getPicafter()==null&&!txt_carigambar.getText().equals("")){
            ObjectOutputStream objectOutputStream=null;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream1=null;
            ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
            try {
                objectOutputStream = new ObjectOutputStream(outputStream);
                ImageIcon icon = new ImageIcon(image);
                objectOutputStream.writeObject(icon);
                objectOutputStream.flush();
                objectOutputStream.close();
                
                
                  objectOutputStream1 = new ObjectOutputStream(outputStream1);
                ImageIcon icon1 = new ImageIcon(image1);
                objectOutputStream1.writeObject(icon1);
                objectOutputStream1.flush();
                objectOutputStream1.close();
            } catch (IOException ex) {
                Logger.getLogger(DIalogUbahTroubleET.class.getName()).log(Level.SEVERE, null, ex);
            }
            tm.setPicafter(outputStream1.toByteArray());
            tm.setPicbefore(outputStream.toByteArray());
            
        }
            
            else if (status1=="Open"&&txt_carigambar.getText().equals("")){
                
            //setimage
            tm.setPicafter(null);
            tm.setPicbefore(null);
            
            }
            //getimage();
     
            
            
            
            
            
            
            //get downtime
             try {
         SimpleDateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
    String timeDo = txt_jamD.getText();
    String timeMa = txt_jammas.getText();
    
    //java.sql.Time timeValue1 = new java.sql.Time(dateFormat1.parse(timeD).getTime());
       // java.sql.Time timeValue2 = new java.sql.Time(dateFormat1.parse(timeM).getTime());
    
     Date tanggalmas=new SimpleDateFormat("yyyy-MM-dd").parse(tanggalmasalah);
    String pattern = "yyyy-MM-dd";
SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern);

String tgldone = simpleDateFormat.format(txt_TanggalD.getDate());
String tglmslh = simpleDateFormat1.format(tanggalmas); 
        String jamdone = String.valueOf(timeDo);
        String jammslh = String.valueOf(timeMa);
        
        
        
         String awaldown = String.valueOf(tglmslh+" "+jammslh);
                String akhirdown = String.valueOf(tgldone+" "+jamdone);
                
                
            List<TroubleEtModel> downtime = troubleETDao.getDowntime(akhirdown, awaldown, akhirdown, awaldown, akhirdown, awaldown);
                     String a = String.valueOf(downtime.get(0).getTotalDowntime());
                     if (a.equals("34 Hari 22 Jam 59 Menit")){
                        
             List<TroubleEtModel> downtimeparah = troubleETDao.getDowntimeparah(akhirdown, awaldown);
             String parah = String.valueOf(downtimeparah.get(0).getTotalDowntime());
                     tm.setTotalDowntime(parah);
                     }else {
                          tm.setTotalDowntime(a);
                     }
   
     
    } catch (ParseException ex) {
        Logger.getLogger(DIalogUbahTroubleET.class.getName()).log(Level.SEVERE, null, ex);
    }
             
             //get downtime
            
             
             
             
             
             
            
            
          
            tm.setTeknisi(teknisi);
            tm.setRef(ref);
    tm.setTglDone(tanggal);
    tm.setProblem(permasalahan);
    tm.setSolusi(solusi);
    tm.setSumber(Sumber);
    tm.setStatus(status1);
    tm.setUm(userbyname.get(0));
    
    dispose();
    
    
}
// TODO add your handling code here:
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btnDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoneActionPerformed
        if (btnDone.getText()=="DONE"){
            txt_TanggalD.setEnabled(false);
            txt_jamD.setEnabled(false);
            tD.stop();
            btnDone.setText("EDIT  ");
        }else if(btnDone.getText()=="EDIT  "){
            txt_TanggalD.setEnabled(true);
            txt_jamD.setEnabled(true);
            tD.start();
            btnDone.setText("DONE");
            tD.stop();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnDoneActionPerformed

    private void btnambilgambarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnambilgambarActionPerformed
JFileChooser chooser = new JFileChooser(System.getProperty("user.home"));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setFileFilter(new FileNameExtensionFilter("jpg|png|bmp", "jpg","png","bmp"));
 
        if(chooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){
            TroubleEtModel m = new TroubleEtModel();
            if (ceksebelum.isSelected()==true){
                 File file=chooser.getSelectedFile();
            try {
                image = ImageIO.read(file);
                panelgambarsebelum.setImage(image);
            } catch (IOException ex) {
                Logger.getLogger(TroubleETFrmGabung.class.getName()).log(Level.SEVERE, null, ex);
            }
            txt_carigambar.setText(file.getAbsolutePath());
            }else if (ceksesudah.isSelected()==true){
              File file=chooser.getSelectedFile();
            try {
                image1 = ImageIO.read(file);
                panelafter.setImage(image1);
            } catch (IOException ex) {
                Logger.getLogger(TroubleETFrmGabung.class.getName()).log(Level.SEVERE, null, ex);
            }
            txt_carigambar.setText(file.getAbsolutePath());   
            }
           
        }          // TODO add your handling code here:
    }//GEN-LAST:event_btnambilgambarActionPerformed

    private void cbo_statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_statusActionPerformed
                // TODO add your handling code here:
    }//GEN-LAST:event_cbo_statusActionPerformed

    
   
    
    /**
     * @param args the command line arguments
     */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox CBO_JENIS;
    private javax.swing.JButton btnDone;
    private javax.swing.JButton btnambilgambar;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JComboBox cbo_status;
    private javax.swing.JCheckBox ceksebelum;
    private javax.swing.JCheckBox ceksesudah;
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
    private com.ridwan.Dialog.panelgambar panelafter;
    private com.ridwan.Dialog.panelgambar panelgambarsebelum;
    private com.toedter.calendar.JDateChooser txt_TanggalD;
    private javax.swing.JTextField txt_carigambar;
    private javax.swing.JTextField txt_jamD;
    private javax.swing.JTextField txt_jammas;
    private javax.swing.JTextField txt_permasalahan;
    private javax.swing.JTextField txt_petugas;
    private javax.swing.JTextField txt_ref;
    private javax.swing.JTextField txt_solusi;
    private javax.swing.JTextField txt_sumber;
    private javax.swing.JTextField txt_tanggalmasalah;
    private javax.swing.JTextField txt_teknisi;
    // End of variables declaration//GEN-END:variables
}
