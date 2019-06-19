/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Frm;
import TableModelHeadercheckBox.TableHeaderCheckBox;
import com.ridwan.DAO.ETDao;
import com.ridwan.DAO.PenyebabDao;
import com.ridwan.DAO.PerangkatVMDao;
import com.ridwan.DAO.ProjekDao;


import com.ridwan.DAO.StasiunDao;
import com.ridwan.DAO.TroubleETDao;
import com.ridwan.DAO.TroubleVmDao;
import com.ridwan.DAO.UserDAo;
import com.ridwan.DBC.DBC;
import com.ridwan.Dialog.DIalogUbahTroubleCVM;
import com.ridwan.Dialog.DIalogUbahTroubleET;
import com.ridwan.Model.ETModel;
import com.ridwan.Model.PenyebabModel;
import com.ridwan.Model.PerangkatVMModel;
import com.ridwan.Model.ProjectModel;
import com.ridwan.Model.StasiunModel;
import com.ridwan.Model.TroubleEtModel;
import com.ridwan.Model.TroubleVMModel;
import com.ridwan.Model.UserModel;
import com.ridwan.TableModel.TableModelTroubleET;
import com.ridwan.TableModel.TableModelTroubleVM;
import com.sun.mail.util.MailSSLSocketFactory;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.SpinnerDateModel;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author ciwong
 */
public class TroubleETFrmGabung extends javax.swing.JInternalFrame implements DocumentListener{
    
    private TableModelTroubleET tableModelTroubleET = new TableModelTroubleET();
    private TroubleETDao troubleETDao = new TroubleETDao();
    
   // private TableModelTroubleVM tableModelTroubleVM = new TableModelTroubleVM();
    private TroubleVmDao troubleVmDao = new TroubleVmDao();
    
    private PerangkatVMDao perangkatVMDao = new PerangkatVMDao();
    
    private ETDao eTDao = new ETDao();
    
    private ProjekDao  projekDao = new ProjekDao();
    
    
    private StasiunDao stasiunDao = new StasiunDao();
    private ETDao  perangkatDao = new ETDao();
    private PenyebabDao penyebabDao = new PenyebabDao();
    private UserDAo userDAo = new UserDAo();
    javax.swing.Timer t;
    javax.swing.Timer tD;
    private Connection c;
    
    private TableRowSorter rowSorter = new TableRowSorter();
      private TableRowSorter rowSorter1 = new TableRowSorter();
      private TableRowSorter rowSorter2 = new TableRowSorter();
      
      
      
       private Image image;
       private Image image1;
       private TroubleEtModel tm;
    
      
      
      private UserModel petugas;
      
      private TroubleEtModel tromod = new TroubleEtModel();
      
    /**
     * 
     * Creates new form TroubleETFrm
     */
    public TroubleETFrmGabung() {
        initComponents();
        txt_Jam.setVisible(false);
        setIconifiable(false);
        setMaximizable(false);
        setClosable(false);
        setResizable(false);
        loading.setVisible(false);
        ProgressBar.setVisible(false);
        txt_terimakasih.setVisible(false);
        txt_gambarsebelum.setVisible(false);
        txt_gambarsesudah.setVisible(false);
        panelgambarbefore.setVisible(false);
        panelgambarafterr.setVisible(false);
        cekemail.setVisible(false);
       // cekeditno.setVisible(true);
        txt_Notroubletiket.setEnabled(false);
      // if (jTabbedPane1.getSelectedIndex()==0){
          
            tabel_troubleET.setModel(tableModelTroubleET);
        // tabel_troubleET.getColumnModel().getColumn(13).setHeaderRenderer(new TableHeaderCheckBox(tabel_troubleET.getTableHeader(), 13));
         
         
         
        Date dd = new Date();
        txt_TanggalM.setDate(dd);
        txt_TanggalD.setDate(dd);
        

        
       // txt_TanggalMCvim.setDate(dd);
        //txt_TanggalDCvm.setDate(dd);
       
        rowSorter = new TableRowSorter(tabel_troubleET.getModel());
        tabel_troubleET.setRowSorter(rowSorter);
        txt_carikata.getDocument().addDocumentListener(this);


          
        
        
        
        
       //} else if (jTabbedPane1.getSelectedIndex()==1){
            
         //   tabel_troubleCVM.setModel(tableModelTroubleVM);
       //  tabel_troubleCVM.getColumnModel().getColumn(16).setHeaderRenderer(new TableHeaderCheckBox(tabel_troubleCVM.getTableHeader(), 16));
       // loadDataCVM();
        loadDataET();
        //loadstasiun();
        //loadperangkat();
       // loadPenyebabCVM();
        //loadUser();
      //  loadPerangkatVM();
        loadProjek();
        loadPartVM();
        loadPenyebabET();
        //rowSorter1 = new TableRowSorter(tabel_troubleCVM.getModel());
      //  tabel_troubleCVM.setRowSorter(rowSorter1);
       // txt_carikatacvm.getDocument().addDocumentListener(this);
        
     //  }
       
       
        

         t = new javax.swing.Timer(1000, new ClockListener());
        t.start();    
         tD = new javax.swing.Timer(1000, new ClockListener1());
        tD.start();
       
        setClosable(true);
        setIconifiable(true);    
      
         tabel_troubleET.setDefaultRenderer(Object.class, new RenderWarnaWarni(Color.red, Color.yellow,Color.white));
        
        c = DBC.getConnection();
        
        // buat next nanti kalo datanya ribuan
        /*
         final int rows = 26;
        JButton previous = new JButton("<<");
        previous.addActionListener( new ActionListener(){
                    public void actionPerformed(ActionEvent ae) {
                        int height = tabel_troubleET.getRowHeight()*(rows-1);
                        JScrollBar bar = jScrollPane1.getVerticalScrollBar();
                        bar.setValue( bar.getValue()-height );
                    }
                } );
        
        
                JButton next = new JButton(">>");
                next.addActionListener( new ActionListener(){
                    public void actionPerformed(ActionEvent ae) {
                        int height = tabel_troubleET.getRowHeight()*(rows-1);
                        JScrollBar bar = jScrollPane1.getVerticalScrollBar();
                        bar.setValue( bar.getValue()+height );
                    }
                } );  
      
        panelnext.add(next);
        panelprev.add(previous);*/
        
        
        
        
        //getimage if klik tabel
           tabel_troubleET.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                int row = tabel_troubleET.getSelectedRow();
                if (row !=-1){
                     tromod = tableModelTroubleET.getTroubleET(tabel_troubleET.convertRowIndexToModel(row));
                    
                            //tromod = tableModelTroubleET.getTroubleET(row);
                    if (tromod.getPicbefore()!=null&&tromod.getPicafter()==null){
                        try {
                            ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(tableModelTroubleET.getTroubleET(tabel_troubleET.convertRowIndexToModel(row)).getPicbefore()));
                            ImageIcon icon  =  (ImageIcon) inputStream.readObject();
                            image = icon.getImage();
                            panelgambarbefore.setVisible(true);
                            txt_gambarsebelum.setVisible(true);
                            panelgambarafterr.setVisible(true);
                            txt_gambarsesudah.setVisible(true);
                            panelgambarbefore.setImage(image);
                            panelgambarafterr.setImage(null);
                            inputStream.close();
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(TroubleETFrmGabung.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(TroubleETFrmGabung.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }else if(tromod.getPicafter()!=null && tromod.getPicbefore()==null) {
                       try {
                        ObjectInputStream  inputStream1 = new ObjectInputStream(new ByteArrayInputStream(tableModelTroubleET.getTroubleET(tabel_troubleET.convertRowIndexToModel(row)).getPicafter()));
                            ImageIcon icon1  =  (ImageIcon) inputStream1.readObject();
                            image1 = icon1.getImage();
                            panelgambarbefore.setVisible(true);
                            txt_gambarsebelum.setVisible(true);
                            panelgambarafterr.setVisible(true);
                            txt_gambarsesudah.setVisible(true);
                            panelgambarafterr.setImage(image1);
                            panelgambarbefore.setImage(null);
                            inputStream1.close();
                      } catch (ClassNotFoundException ex) {
                            Logger.getLogger(TroubleETFrmGabung.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(TroubleETFrmGabung.class.getName()).log(Level.SEVERE, null, ex);
                        }    
                            
                        
                    }else if(tromod.getPicafter()!=null && tromod.getPicbefore()!=null) {
                       try {
                        ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(tableModelTroubleET.getTroubleET(tabel_troubleET.convertRowIndexToModel(row)).getPicbefore()));
                            ImageIcon icon  =  (ImageIcon) inputStream.readObject();
                           ObjectInputStream  inputStream1 = new ObjectInputStream(new ByteArrayInputStream(tableModelTroubleET.getTroubleET(tabel_troubleET.convertRowIndexToModel(row)).getPicafter()));
                            ImageIcon icon1  =  (ImageIcon) inputStream1.readObject();
                            image1 = icon1.getImage();
                            panelgambarbefore.setVisible(true);
                            txt_gambarsebelum.setVisible(true);
                            panelgambarafterr.setVisible(true);
                            txt_gambarsesudah.setVisible(true);
                            panelgambarafterr.setImage(image1);
                            inputStream1.close();
                            
                            
                            
                             image = icon.getImage();
                            panelgambarbefore.setVisible(true);
                            txt_gambarsebelum.setVisible(true);
                            panelgambarafterr.setVisible(true);
                            txt_gambarsesudah.setVisible(true);
                            panelgambarbefore.setImage(image);
                            inputStream.close();
                      } catch (ClassNotFoundException ex) {
                            Logger.getLogger(TroubleETFrmGabung.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(TroubleETFrmGabung.class.getName()).log(Level.SEVERE, null, ex);
                        }    
                            
                        
                    }
                    else {
                        panelgambarafterr.setImage(null);
                        panelgambarbefore.setImage(null);
                        panelgambarafterr.setVisible(false);
                        panelgambarbefore.setVisible(false);
                        txt_gambarsebelum.setVisible(false);
                        txt_gambarsesudah.setVisible(false);
                    }
                }
            }
             });
    }

    
    
    
    //fungsi getdansettextuser
     public JTextField getTxtPengguna (){
        return txt_petugas;
    }
    
    public void setTxtPengguna (JTextField txtpetugas){
        
    this.txt_petugas = txtpetugas;
    
    }
    //fungsi getdansettextuser
    
    
    
    //fungsi sort data
     private void saring(){
        
           String text = txt_carikata.getText();
        if (text.length()==0){
            rowSorter.setRowFilter(null);
        }else {
           rowSorter.setRowFilter(RowFilter.regexFilter
        (Pattern.compile("(?i).*" + text + ".*", Pattern.CASE_INSENSITIVE|Pattern.DOTALL ).toString()));
        }
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
    //fungsi sort data
    
    

   
   //fungsi jam 
    public class ClockListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
 
            Calendar now = Calendar.getInstance();
            int h = now.get(Calendar.HOUR_OF_DAY);
            int m = now.get(Calendar.MINUTE);
            int s = now.get(Calendar.SECOND);
             txt_JamMa.setText("" + h + ":" + m + ":" + s);
    	}
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
    //fungsi jam
    
    
    
    
    
    
    
    
    //fungsi warna tabel
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
  value = tabel_troubleET.getModel().getValueAt(row, 11);
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
     //fungsi warna tabel
    
     
     
     
     
     //fungsi loading
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
    //fungsi loading
    
      
      
    
    /*private void loadPerangkatVM(){
        List<PerangkatVMModel> perangkatVMModels = perangkatVMDao.getPerangkatvmnotgatepos();
        for (PerangkatVMModel pvmm : perangkatVMModels){
            cbo_Prangkatcvm.addItem(pvmm);
        }
    }*/
    
    
  /*  private void loadstasiun(){
            List<StasiunModel> stasiun = stasiunDao.getModelsByprojek((ProjectModel) cbo_projek.getSelectedItem());
        for (StasiunModel s: stasiun){
                cbo_stasiun.addItem(s);
                cbo_stasiuncvm.addItem(s);
    }
        }*/

    
    /* private void loadperangkat(){
        List<ETModel> perangkat = perangkatDao.getETModels();
        for (ETModel p: perangkat){
            cbo_Perangkat.addItem(p);
        }
    }*/
    
      
      
      
      
      
      private void loadPenyebabET(){
        List<PenyebabModel> penyebabModels = penyebabDao.getPenyebabModels();
        for (PenyebabModel pms: penyebabModels){
           // cbo_Penyebab.addItem(pms);
            cbo_caripenyebab.addItem(pms);
           // cbo_Penyebabcvm.addItem(pms);
           
        }
    }
      
      
      private void loadPartVM(){
          cbo_PartVM.removeAllItems();
          cbo_PartVM.addItem("- PILIH -");
          cbo_PartVM.setSelectedIndex(0);
        List<PerangkatVMModel> perangkatVMModels = perangkatVMDao.getPerangkatVMModels();
        for (PerangkatVMModel mModel: perangkatVMModels){
            cbo_PartVM.addItem(mModel);
        }
      }
      
      
      
     /* private void loadPenyebabCVM(){
        List<PenyebabModel> penyebabs = penyebabDao.getByNOTgatepos();
        for (PenyebabModel pms: penyebabs){
            //cbo_Penyebab.addItem(pms);
            cbo_caripenyebabcvm.addItem(pms);
           // cbo_Penyebabcvm.addItem(pms);
        }
    }*/
      
     

      
      
       
      
    /*   private void loadUser(){
        List<UserModel> user = userDAo.getUserModels();
        for (UserModel u: user){
                cbo_user.addItem(u);
                cbo_usercvm.addItem(u);
               
        }
    }*/
    
    
    
    private void loadDataET(){
        List<TroubleEtModel> etModels = troubleETDao.getEtModels();
        tableModelTroubleET.setData(etModels);
    }
    
   /* private void loadDataCVM(){
        List<TroubleVMModel> troubleVMModels = troubleVmDao.getTroubleVMModels();
        tableModelTroubleVM.setData(troubleVMModels);
    }*/
    
      
    private void loadProjek(){
        List<ProjectModel> projekModels = projekDao.getProjekModels();
        for (ProjectModel pp : projekModels){
            cbo_projek.addItem(pp);
           // cbo_projekcvm.addItem(pp);
        }
    }
    
    
    //fungsi resset
    private void resetFrm(){
        Date ddd = new Date();
        txt_TanggalM.setDate(ddd);
        txt_TanggalD.setDate(ddd);
        txt_TanggalD.setEnabled(true);
        txt_TanggalM.setEnabled(true);
        txt_JamMa.setEnabled(true);
        txt_jamD.setEnabled(true);
        t.start();
        tD.start();
        btn_Set.setText("SET");
        btnDone.setText("DONE");
        txt_Notroubletiket.setText("");
        txt_TanggalD.setDate(ddd);
        txt_Jam.setEnabled(false);
        cbo_stasiun.setSelectedIndex(0);
        cbo_Perangkat.setSelectedIndex(0);
        txt_problem.setText("");
        cbo_Penyebab.setSelectedIndex(0);
        txt_solusi.setText("Startup Aplikasi By Backend");
        cbo_status.setSelectedIndex(0);
       // txt_carikata.setText("");
        cbo_caripenyebab.setSelectedIndex(0);
        txt_caritanggal.setDate(null);
       // cbo_projek.setSelectedIndex(0);
        txt_Notroubletiket.setText("");
        txt_sumberaduan.setText("Teknisi On Site");
        loadPartVM();
        cbo_PartVM.setSelectedIndex(0);
        cbo_jenislaporan.setSelectedIndex(0);
        txt_ref.setText("");
        txt_teknisi.setText("");
        cekteknisi.setSelected(false);
        chkref.setSelected(false);
        txt_ref.setEnabled(false);
        txt_teknisi.setEnabled(false);
        //txt_carigambar.setText("");
        panelgambarbefore.setImage(null);
        cbo_arah.setSelectedIndex(0);
        txt_gambarsebelum.setVisible(false);
        txt_gambarsesudah.setVisible(false);
        panelgambarbefore.setVisible(false);
        panelgambarafterr.setVisible(false);
        cekemail.setVisible(false);
       txt_Notroubletiket.setEnabled(false);
            loadDataET();
        t.start();
         tD.start();
         // cbo_arah.setSelectedIndex(0);
         
    }
    //fungsi resset
    
    
   /* private void resetFrmCvm(){
        Date ddcvm = new Date();
        txt_TanggalMCvim.setDate(ddcvm);
        txt_TanggalDCvm.setDate(ddcvm);
        txt_TanggalDCvm.setEnabled(true);
        txt_TanggalMCvim.setEnabled(true);
        txt_JamMaCvm.setEnabled(true);
        txt_jamDcvm.setEnabled(true);
        t.start();
        tD.start();
        btn_Setcvm.setText("SET");
        cbo_stasiuncvm.setSelectedIndex(0);
        cbo_Prangkatcvm.setSelectedIndex(0);
        txt_idcvim.setText("");
        txt_problemcvm.setText("");
        txt_solusicvm.setText("");
        cbo_Penyebabcvm.setSelectedIndex(0);
        cbo_statuscvm.setSelectedIndex(0);
        txt_carikatacvm.setText("");
        txt_caritanggalcvm.setDate(null);
        cbo_caripenyebabcvm.setSelectedIndex(0);
        txt_analisa.setText("");
        cbo_projekcvm.setSelectedIndex(0);
        txt_Notroubletiketcvm.setText("");
        t.start();
          tD.start();
    }
    */
    
    
    
    
    //fungsi validasi
    private boolean validasiInput(){
        boolean valid = false;
        if (txt_TanggalM.getDate()==null){
            JOptionPane.showMessageDialog(null, "Tanggal Masalah Belum Diisi");
        }else if (txt_JamMa.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Jam Masalah Belum Diisi");
        }else if (txt_TanggalD.getDate()==null){
            JOptionPane.showMessageDialog(null, "Tanggal Done Belum Diisi");
        }else if (txt_jamD.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Jam Done Belum Diisi");
        }else if (cbo_stasiun.getSelectedItem()=="- PILIH -"){
            JOptionPane.showMessageDialog(null, "Stasiun Belum Dipilih");
        }else if (cbo_Perangkat.getSelectedItem()=="- PILIH -"){
            JOptionPane.showMessageDialog(null, "Perangkat Belum Dipilih");
        }else if (txt_problem.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Permasalahan Belum Diisi");
        }else if (cbo_Penyebab.getSelectedItem()=="- PILIH -"){
            JOptionPane.showMessageDialog(null, "Penyebab Belum Dipilih");
        }else if (txt_solusi.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Solusi Belum Diisi");
        }else if (cbo_status.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "Status Belum Dipilih");
        }else if (cbo_projek.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "Projek Belum Dipilih");
        }else if (txt_Notroubletiket.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "No Trouble ticket Belum Diisi");
        }else if (btn_Set.getText()=="SET"){
            JOptionPane.showMessageDialog(null, "Waktu Kejadian Belom Di SET");
        }else if (btnDone.getText()=="DONE"){
            JOptionPane.showMessageDialog(null, "Waktu Done Belom Di DONE");
        }
        else {
            valid = true;
        }
        return valid;
    }
    //fungsi validasi

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txt_petugas = new javax.swing.JTextField();
        btnHapus = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        panelprev = new javax.swing.JPanel();
        panelnext = new javax.swing.JPanel();
        btnUbah = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_troubleET = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        cbo_caripenyebab = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_caritanggal = new com.toedter.calendar.JDateChooser();
        btn_Go = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        cbo_searchstatus = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_TanggalM = new com.toedter.calendar.JDateChooser();
        txt_TanggalD = new com.toedter.calendar.JDateChooser();
        txt_jamD = new javax.swing.JTextField();
        txt_JamMa = new javax.swing.JTextField();
        btn_Set = new javax.swing.JButton();
        btnDone = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        cbo_jenislaporan = new javax.swing.JComboBox();
        jLabel28 = new javax.swing.JLabel();
        cbo_projek = new javax.swing.JComboBox();
        txt_Notroubletiket = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cbo_PartVM = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cbo_Penyebab = new javax.swing.JComboBox();
        cbo_Perangkat = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cbo_stasiun = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbo_status = new javax.swing.JComboBox();
        txt_sumberaduan = new javax.swing.JTextField();
        txt_solusi = new javax.swing.JTextField();
        Date date = new Date();
        SpinnerDateModel sm =
        new SpinnerDateModel(date, null,null, Calendar.HOUR_OF_DAY);
        txt_Jam = new javax.swing.JSpinner(sm);
        jLabel4 = new javax.swing.JLabel();
        txt_problem = new javax.swing.JTextField();
        chk_cvim = new javax.swing.JCheckBox();
        ProgressBar = new javax.swing.JProgressBar();
        jLabel16 = new javax.swing.JLabel();
        txt_ref = new javax.swing.JTextField();
        chkref = new javax.swing.JCheckBox();
        jLabel17 = new javax.swing.JLabel();
        txt_teknisi = new javax.swing.JTextField();
        cekteknisi = new javax.swing.JCheckBox();
        jLabel20 = new javax.swing.JLabel();
        cbo_arah = new javax.swing.JComboBox();
        checkarah = new javax.swing.JCheckBox();
        cekemail = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txt_carikata = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        panelgambarbefore = new com.ridwan.Dialog.panelgambar();
        panelgambarafterr = new com.ridwan.Dialog.panelgambar();
        txt_gambarsebelum = new javax.swing.JLabel();
        txt_gambarsesudah = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        loading = new javax.swing.JLabel();
        txt_terimakasih = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(0, 255, 255));

        txt_petugas.setBackground(new java.awt.Color(0, 255, 255));
        txt_petugas.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        txt_petugas.setForeground(new java.awt.Color(0, 51, 153));
        txt_petugas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_petugas.setBorder(null);

        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ridwan/ICON/Remove_24x24.png"))); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ridwan/ICON/Refresh_24x24.png"))); // NOI18N
        jButton1.setText("Reload");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel31.setText("Petugas :");

        jSeparator1.setBackground(new java.awt.Color(0, 0, 153));
        jSeparator1.setForeground(new java.awt.Color(0, 102, 153));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ridwan/ICON/left-arrow (1).png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        panelprev.setBackground(new java.awt.Color(0, 255, 255));
        panelprev.setForeground(new java.awt.Color(0, 255, 255));

        panelnext.setBackground(new java.awt.Color(0, 255, 255));
        panelnext.setForeground(new java.awt.Color(0, 255, 255));

        btnUbah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ridwan/ICON/Edit_24x24.png"))); // NOI18N
        btnUbah.setText("Ubah");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ridwan/ICON/Add_24x24.png"))); // NOI18N
        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 556, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(panelprev, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(btnSimpan)
                        .addGap(18, 18, 18)
                        .addComponent(btnUbah)
                        .addGap(18, 18, 18)
                        .addComponent(btnHapus)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelnext, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(398, 398, 398)
                        .addComponent(jLabel31)
                        .addGap(28, 28, 28)
                        .addComponent(txt_petugas, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_petugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(panelprev, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelnext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 23, Short.MAX_VALUE))))
        );

        tabel_troubleET.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabel_troubleET.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_troubleETMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_troubleET);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cbo_caripenyebab.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- PILIH -" }));
        cbo_caripenyebab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_caripenyebabActionPerformed(evt);
            }
        });

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ridwan/ICON/Search_16x16.png"))); // NOI18N
        jLabel12.setText("Cari Penyebab :");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ridwan/ICON/Calendar_16x16.png"))); // NOI18N
        jLabel11.setText("Tanggal :");

        btn_Go.setText("Go");
        btn_Go.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GoActionPerformed(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ridwan/ICON/Search_16x16.png"))); // NOI18N
        jLabel10.setText("Status   :");

        cbo_searchstatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- PILIH -", "Open" }));
        cbo_searchstatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_searchstatusActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ridwan/ICON/Search_16x16.png"))); // NOI18N
        jButton3.setText("By No Ticket");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ridwan/ICON/Search_16x16.png"))); // NOI18N
        jButton4.setText("By Status & Date");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(cbo_caripenyebab, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(185, 185, 185)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 204, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(txt_caritanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Go)
                .addGap(182, 182, 182)
                .addComponent(jButton4)
                .addGap(196, 196, 196)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbo_searchstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(txt_caritanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cbo_caripenyebab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jButton3)))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btn_Go)
                .addComponent(jLabel10)
                .addComponent(cbo_searchstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton4))
        );

        jLabel1.setText("Waktu Kejadian :");

        jLabel2.setText("Waktu Selesai :");

        btn_Set.setText("SET");
        btn_Set.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SetActionPerformed(evt);
            }
        });

        btnDone.setText("DONE");
        btnDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoneActionPerformed(evt);
            }
        });

        jLabel13.setText("Jenis Laporan :");

        cbo_jenislaporan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- PILIH -", "PERMASALAHAN", "PEKERJAAN", "ADUAN" }));
        cbo_jenislaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_jenislaporanActionPerformed(evt);
            }
        });

        jLabel28.setText("Projek :");

        cbo_projek.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- PILIH -" }));
        cbo_projek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_projekActionPerformed(evt);
            }
        });

        txt_Notroubletiket.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_NotroubletiketMouseClicked(evt);
            }
        });

        jLabel8.setText("No Trouble :");

        cbo_PartVM.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- PILIH -" }));
        cbo_PartVM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_PartVMActionPerformed(evt);
            }
        });

        jLabel7.setText("Perangkat :");

        jLabel5.setText("Penyebab :");

        jLabel14.setText("Part  :");

        cbo_Penyebab.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- PILIH -" }));

        cbo_Perangkat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- PILIH -" }));
        cbo_Perangkat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_PerangkatActionPerformed(evt);
            }
        });

        jLabel3.setText("Lokasi :");

        cbo_stasiun.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- PILIH -" }));
        cbo_stasiun.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbo_stasiunMouseClicked(evt);
            }
        });
        cbo_stasiun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_stasiunActionPerformed(evt);
            }
        });

        jLabel6.setText("Solusi :");

        jLabel15.setText("Sumber Aduan :");

        jLabel9.setText("Status :");

        cbo_status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- PILIH -", "Done", "Pending", "Open" }));
        cbo_status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_statusActionPerformed(evt);
            }
        });

        txt_sumberaduan.setText("Teknisi On Site");
        txt_sumberaduan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_sumberaduanMouseClicked(evt);
            }
        });

        txt_solusi.setText("Starup Aplikasi By Backend");
        txt_solusi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_solusiMouseClicked(evt);
            }
        });
        txt_solusi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_solusiActionPerformed(evt);
            }
        });

        JSpinner.DateEditor de = new JSpinner.DateEditor(txt_Jam, "HH:mm:ss");
        txt_Jam.setEditor(de);

        jLabel4.setText("Permasalahan :");

        txt_problem.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        chk_cvim.setText("CVIM");
        chk_cvim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_cvimActionPerformed(evt);
            }
        });

        jLabel16.setText("Ref No Aduan :");

        txt_ref.setEnabled(false);

        chkref.setText("use");
        chkref.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkrefActionPerformed(evt);
            }
        });

        jLabel17.setText("Tambahan :");

        txt_teknisi.setEnabled(false);

        cekteknisi.setText("use");
        cekteknisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekteknisiActionPerformed(evt);
            }
        });

        jLabel20.setText("Arah Gate :");

        cbo_arah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- PILIH -", "IN", "OUT", "IN OUT", " " }));
        cbo_arah.setEnabled(false);

        checkarah.setText("use");
        checkarah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkarahActionPerformed(evt);
            }
        });

        cekemail.setText("Email");
        cekemail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekemailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_TanggalD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_TanggalM, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_JamMa)
                                .addComponent(txt_jamD, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addGap(9, 9, 9)
                            .addComponent(cbo_jenislaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbo_projek, 0, 234, Short.MAX_VALUE)
                            .addComponent(txt_Notroubletiket))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnDone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_Set, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 3, Short.MAX_VALUE)
                        .addComponent(cekemail, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(200, 200, 200)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbo_stasiun, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbo_Penyebab, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbo_Perangkat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cbo_PartVM, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chk_cvim)
                        .addGap(0, 3, Short.MAX_VALUE))
                    .addComponent(txt_problem))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(38, 38, 38)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cbo_status, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbo_arah, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_teknisi)
                    .addComponent(txt_solusi)
                    .addComponent(txt_ref, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                    .addComponent(txt_sumberaduan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(checkarah, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(chkref, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cekteknisi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_Jam, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(124, 124, 124))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbo_status, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20)
                            .addComponent(cbo_arah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkarah)
                            .addComponent(jLabel9))
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_Jam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txt_ref, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkref))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_teknisi)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(cekteknisi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txt_TanggalM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txt_TanggalD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Set)
                            .addComponent(txt_JamMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbo_stasiun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txt_solusi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(2, 2, 2)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_sumberaduan, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_jamD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnDone)
                                .addComponent(jLabel7)
                                .addComponent(cbo_Perangkat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbo_jenislaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(cekemail))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbo_projek, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txt_Notroubletiket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbo_PartVM, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)
                        .addComponent(chk_cvim)))
                .addGap(8, 8, 8))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_problem, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(2, 2, 2))
                    .addComponent(cbo_Penyebab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        cbo_jenislaporan.getAccessibleContext().setAccessibleName("");
        cbo_Penyebab.getAccessibleContext().setAccessibleDescription("");
        txt_problem.getAccessibleContext().setAccessibleName("");

        jPanel7.setBackground(new java.awt.Color(0, 51, 153));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ridwan/ICON/susu.png"))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(1594, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel18)
        );

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ridwan/ICON/Search_16x16.png"))); // NOI18N
        jLabel19.setText(" Cari Kata           :");

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelgambarbefore.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelgambarbeforeLayout = new javax.swing.GroupLayout(panelgambarbefore);
        panelgambarbefore.setLayout(panelgambarbeforeLayout);
        panelgambarbeforeLayout.setHorizontalGroup(
            panelgambarbeforeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );
        panelgambarbeforeLayout.setVerticalGroup(
            panelgambarbeforeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel5.add(panelgambarbefore, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 0, -1, 58));

        panelgambarafterr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelgambarafterrLayout = new javax.swing.GroupLayout(panelgambarafterr);
        panelgambarafterr.setLayout(panelgambarafterrLayout);
        panelgambarafterrLayout.setHorizontalGroup(
            panelgambarafterrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );
        panelgambarafterrLayout.setVerticalGroup(
            panelgambarafterrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 56, Short.MAX_VALUE)
        );

        jPanel5.add(panelgambarafterr, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 0, -1, -1));

        txt_gambarsebelum.setText("Gambar Sebelum");
        jPanel5.add(txt_gambarsebelum, new org.netbeans.lib.awtextra.AbsoluteConstraints(304, 64, -1, -1));

        txt_gambarsesudah.setText("Gambar Sesudah");
        jPanel5.add(txt_gambarsesudah, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 64, -1, -1));

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loading.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ridwan/ICON/Loading Jos.gif"))); // NOI18N
        jPanel6.add(loading, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 0, -1, -1));

        txt_terimakasih.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_terimakasih.setForeground(new java.awt.Color(0, 51, 153));
        txt_terimakasih.setText("Berhasil DI Proses Terima Kasih......");
        jPanel6.add(txt_terimakasih, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 72, -1, -1));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 759, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(txt_carikata, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 1572, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_carikata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
tampilWorker w = new tampilWorker();
        List<TroubleEtModel> cektroubleet = tableModelTroubleET.getTECek();
if (!cektroubleet.isEmpty()){
if (JOptionPane.showConfirmDialog(null,"Konfirmasi", "Apakah Anda Ingin Menghapus Data Ini ?", JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
    boolean valid = true;
    for (TroubleEtModel tem : cektroubleet){
        if (valid){
            troubleETDao.deleteTroubleET(tem);
        }
        loadDataET();
        resetFrm();
        
         w.execute();
         // loadDataCVM();
       // resetFrmCvm();
    }
}
    
}else {
    JOptionPane.showMessageDialog(null,"Seleksi Salah Satu Baris");
}



    
// TODO add your handling code here:
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        List<UserModel> userbyname = userDAo.getuserbyname(txt_petugas.getText());
    if (userbyname.isEmpty()){
        JOptionPane.showMessageDialog(null, "User Tidak Ada");
    }else {
    if (validasiInput()==true){
    Date TglM = txt_TanggalM.getDate();
    Date TglD = txt_TanggalD.getDate();
    
    String jenislaporan = (String) cbo_jenislaporan.getSelectedItem();
     ProjectModel projek = (ProjectModel) cbo_projek.getSelectedItem();
       String noTroubleTicket = txt_Notroubletiket.getText();

    StasiunModel sts = (StasiunModel) cbo_stasiun.getSelectedItem();
    ETModel perangkat = (ETModel) cbo_Perangkat.getSelectedItem();
    PerangkatVMModel part = (PerangkatVMModel) cbo_PartVM.getSelectedItem();
    //String arahgate = (String) cbo_arah.getSelectedItem();
    String permasalahan = txt_problem.getText();
    PenyebabModel penyebab =  (PenyebabModel) cbo_Penyebab.getSelectedItem();
    String solusi = txt_solusi.getText();
    String Sumber = txt_sumberaduan.getText();
    String status = (String) cbo_status.getSelectedItem();
    String ref = txt_ref.getText();
    //UserModel user = (UserModel) cbo_user.getSelectedItem();
   String Teknisi = txt_teknisi.getText();
  
    
    
    TroubleEtModel temm = new TroubleEtModel();
    tampilWorker worker = new tampilWorker();
    
    
  
  
    
    if (cbo_jenislaporan.getSelectedItem()=="ADUAN"){
        temm.setTglMasalah(TglM);
    temm.setTglDone(TglD);
    
    
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    String timeM = txt_JamMa.getText();
    try {
        java.sql.Time timeValue = new java.sql.Time(dateFormat.parse(timeM).getTime());
        temm.setJamMasalah(timeValue);
    } catch (ParseException ex) {
        Logger.getLogger(TroubleETFrmGabung.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    
   
    SimpleDateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
    String timeD = txt_jamD.getText();
            try {
        java.sql.Time timeValue1 = new java.sql.Time(dateFormat1.parse(timeD).getTime());
        temm.setJamDone(timeValue1);
    } catch (ParseException ex) {
        Logger.getLogger(TroubleETFrmGabung.class.getName()).log(Level.SEVERE, null, ex);
    }
            
            
            
            
            //getdowntime
         SimpleDateFormat dateFormattime = new SimpleDateFormat("HH:mm:ss");
    String timeDo = txt_jamD.getText();
    String timeMa = txt_JamMa.getText();
    
        try {
            java.sql.Time timeValue1 = new java.sql.Time(dateFormattime.parse(timeDo).getTime());
             java.sql.Time timeValue2 = new java.sql.Time(dateFormattime.parse(timeMa).getTime());
             
              // Date tanggalmas=new SimpleDateFormat("yyyy-MM-dd").parse(tanggalmasalah);
    String pattern = "yyyy-MM-dd";
SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern);

String tgldone = simpleDateFormat.format(txt_TanggalD.getDate());
String tglmslh = simpleDateFormat1.format(txt_TanggalM.getDate()); 
        String jamdone = String.valueOf(timeValue1);
        String jammslh = String.valueOf(timeValue2);
        
        
        
         String awaldown = String.valueOf(tglmslh+" "+jammslh);
                String akhirdown = String.valueOf(tgldone+" "+jamdone);
                
                
            List<TroubleEtModel> downtime = troubleETDao.getDowntime(akhirdown, awaldown, akhirdown, awaldown, akhirdown, awaldown);
                     String a = String.valueOf(downtime.get(0).getTotalDowntime());
                     if (a.equals("34 Hari 22 Jam 59 Menit")){
                        
             List<TroubleEtModel> downtimeparah = troubleETDao.getDowntimeparah(akhirdown, awaldown);
             String parah = String.valueOf(downtimeparah.get(0).getTotalDowntime());
                     temm.setTotalDowntime(parah);
                     }else {
                          temm.setTotalDowntime(a);
                     }
        } catch (ParseException ex) {
            Logger.getLogger(TroubleETFrmGabung.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    
   
            
            //getdowntime    
            
            
            
    temm.setJenisLaporan(jenislaporan);
    temm.setProm(projek);
    temm.setNoEt(noTroubleTicket);
    temm.setSm(sts);
    temm.setEtm(perangkat);
        temm.setPart(null);
        temm.setPm(null);
        temm.setSolusi(solusi);
        temm.setSumber(Sumber);
        temm.setProblem(permasalahan);
    temm.setStatus(status);
    temm.setReftrouble(ref);
    temm.setTeknisi(Teknisi);
    temm.setUm(userbyname.get(0));
    
    
    if (troubleETDao.insertTroubleETAduan(temm)==true){
       worker.execute();
        tableModelTroubleET.insertTroubleET(temm);
        //loadDataET();
       resetFrm();
        //loadDataCVM();
      //  resetFrmCvm();
    }else {
        JOptionPane.showMessageDialog(null, "Data Gagal Disimpan");
    //loadDataET();
    //loadDataCVM();
       // resetFrmCvm();
    }
    
    
    
    }else if (cbo_jenislaporan.getSelectedItem()=="PEKERJAAN"){
        temm.setTglMasalah(TglM);
    temm.setTglDone(TglD);
    
    
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    String timeM = txt_JamMa.getText();
    try {
        java.sql.Time timeValue = new java.sql.Time(dateFormat.parse(timeM).getTime());
        temm.setJamMasalah(timeValue);
    } catch (ParseException ex) {
        Logger.getLogger(TroubleETFrmGabung.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    
   
    SimpleDateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
    String timeD = txt_jamD.getText();
            try {
        java.sql.Time timeValue1 = new java.sql.Time(dateFormat1.parse(timeD).getTime());
        temm.setJamDone(timeValue1);
    } catch (ParseException ex) {
        Logger.getLogger(TroubleETFrmGabung.class.getName()).log(Level.SEVERE, null, ex);
    }
            
            
            
            
              //getdowntime
         SimpleDateFormat dateFormattime = new SimpleDateFormat("HH:mm:ss");
    String timeDo = txt_jamD.getText();
    String timeMa = txt_JamMa.getText();
    
        try {
            java.sql.Time timeValue1 = new java.sql.Time(dateFormattime.parse(timeDo).getTime());
             java.sql.Time timeValue2 = new java.sql.Time(dateFormattime.parse(timeMa).getTime());
             
              // Date tanggalmas=new SimpleDateFormat("yyyy-MM-dd").parse(tanggalmasalah);
    String pattern = "yyyy-MM-dd";
SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern);

String tgldone = simpleDateFormat.format(txt_TanggalD.getDate());
String tglmslh = simpleDateFormat1.format(txt_TanggalM.getDate()); 
        String jamdone = String.valueOf(timeValue1);
        String jammslh = String.valueOf(timeValue2);
        
        
        
         String awaldown = String.valueOf(tglmslh+" "+jammslh);
                String akhirdown = String.valueOf(tgldone+" "+jamdone);
                
                
            List<TroubleEtModel> downtime = troubleETDao.getDowntime(akhirdown, awaldown, akhirdown, awaldown, akhirdown, awaldown);
                     String a = String.valueOf(downtime.get(0).getTotalDowntime());
                     if (a.equals("34 Hari 22 Jam 59 Menit")){
                        
             List<TroubleEtModel> downtimeparah = troubleETDao.getDowntimeparah(akhirdown, awaldown);
             String parah = String.valueOf(downtimeparah.get(0).getTotalDowntime());
                     temm.setTotalDowntime(parah);
                     }else {
                          temm.setTotalDowntime(a);
                     }
        } catch (ParseException ex) {
            Logger.getLogger(TroubleETFrmGabung.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    
   
            
            //getdowntime    
            
            
            
            
            
    temm.setJenisLaporan(jenislaporan);
    temm.setProm(projek);
    temm.setNoEt(noTroubleTicket);
    temm.setSm(sts);
    temm.setEtm(perangkat);
        temm.setPart(null);
        temm.setPm(null);
        temm.setSumber(null);
        temm.setSolusi(solusi);
        temm.setProblem(permasalahan);
    temm.setStatus(status);
    temm.setReftrouble(ref);
    temm.setTeknisi(Teknisi);
    temm.setUm(userbyname.get(0));
    
     if (troubleETDao.insertTroubleETPekerjaan(temm)==true){
         worker.execute();
        tableModelTroubleET.insertTroubleET(temm);
        
        //loadDataET();
        resetFrm();
        //loadDataCVM();
      //  resetFrmCvm();
    }else {
        JOptionPane.showMessageDialog(null, "Data Gagal Disimpan");
    //loadDataET();
    //loadDataCVM();
       // resetFrmCvm();
    }
    
    
    }else if (cbo_jenislaporan.getSelectedItem()=="PERMASALAHAN") {
        temm.setTglMasalah(TglM);
    temm.setTglDone(TglD);
    
    
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    String timeM = txt_JamMa.getText();
    try {
        java.sql.Time timeValue = new java.sql.Time(dateFormat.parse(timeM).getTime());
        temm.setJamMasalah(timeValue);
    } catch (ParseException ex) {
        Logger.getLogger(TroubleETFrmGabung.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    
   
    SimpleDateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
    String timeD = txt_jamD.getText();
            try {
        java.sql.Time timeValue1 = new java.sql.Time(dateFormat1.parse(timeD).getTime());
        temm.setJamDone(timeValue1);
    } catch (ParseException ex) {
        Logger.getLogger(TroubleETFrmGabung.class.getName()).log(Level.SEVERE, null, ex);
    }
            
            
            
            
            //for image
             /*if(!txt_carigambar.getText().equals("")){
            ObjectOutputStream objectOutputStream=null;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            try {
                objectOutputStream = new ObjectOutputStream(outputStream);
                ImageIcon icon = new ImageIcon(image);
                objectOutputStream.writeObject(icon);
                objectOutputStream.flush();
                objectOutputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(TroubleETFrmGabung.class.getName()).log(Level.SEVERE, null, ex);
            }
            temm.setPicbefore(outputStream.toByteArray());
        }else if (checkpergantian.isSelected()==true&&txt_carigambar.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Gambar Sebelum Belum Dipilih");
           
             }else if (txt_carigambar.getText().equals("")){
                  temm.setPicbefore(null);
             }
             //for image*/
             
             
             
             
             //arah gate
             if (cbo_arah.getSelectedIndex()==0){
                 temm.setArahgate(null);
             }else {
                 temm.setArahgate((String) cbo_arah.getSelectedItem());
             }
             
             
             
             
             
             
             
             
         //getdowntime
         SimpleDateFormat dateFormattime = new SimpleDateFormat("HH:mm:ss");
    String timeDo = txt_jamD.getText();
    String timeMa = txt_JamMa.getText();
    
        try {
            java.sql.Time timeValue1 = new java.sql.Time(dateFormattime.parse(timeDo).getTime());
             java.sql.Time timeValue2 = new java.sql.Time(dateFormattime.parse(timeMa).getTime());
             
              // Date tanggalmas=new SimpleDateFormat("yyyy-MM-dd").parse(tanggalmasalah);
    String pattern = "yyyy-MM-dd";
SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern);

String tgldone = simpleDateFormat.format(txt_TanggalD.getDate());
String tglmslh = simpleDateFormat1.format(txt_TanggalM.getDate()); 
        String jamdone = String.valueOf(timeValue1);
        String jammslh = String.valueOf(timeValue2);
        
        
        
         String awaldown = String.valueOf(tglmslh+" "+jammslh);
                String akhirdown = String.valueOf(tgldone+" "+jamdone);
                
                
            List<TroubleEtModel> downtime = troubleETDao.getDowntime(akhirdown, awaldown, akhirdown, awaldown, akhirdown, awaldown);
                     String a = String.valueOf(downtime.get(0).getTotalDowntime());
                     if (a.equals("34 Hari 22 Jam 59 Menit")){
                        
             List<TroubleEtModel> downtimeparah = troubleETDao.getDowntimeparah(akhirdown, awaldown);
             String parah = String.valueOf(downtimeparah.get(0).getTotalDowntime());
                     temm.setTotalDowntime(parah);
                     }else {
                          temm.setTotalDowntime(a);
                     }
        } catch (ParseException ex) {
            Logger.getLogger(TroubleETFrmGabung.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    
   
            
            //getdowntime    
             
             
             
             
             
             
             
             
             
             
             
            
    temm.setJenisLaporan(jenislaporan);
    temm.setProm(projek);
    temm.setNoEt(noTroubleTicket);
    temm.setSm(sts);
    temm.setEtm(perangkat);
         temm.setPart(part);
          temm.setPm(penyebab);
    temm.setSolusi(solusi);
    temm.setSumber(Sumber);
    temm.setProblem(permasalahan);
    temm.setStatus(status);
     temm.setReftrouble(ref);
    temm.setTeknisi(Teknisi);
    temm.setUm(userbyname.get(0));
    
    
    
    if (cekemail.isSelected()==true){
         if (troubleETDao.insertTroubleETPermasalahan(temm)==true){
          worker.execute();
            //tableModelTroubleET.insertTroubleET(temm);
                createfile();
                     String from = USER_NAME;
                  String pass = PASSWORD;
                  String[] to =  RECIPIENT ;
                 String Lokasi = String.valueOf(cbo_stasiun.getSelectedItem());
                     String Part = String.valueOf(cbo_PartVM.getSelectedItem());
                  String urutan = String.valueOf(cbo_Perangkat.getSelectedItem());
                     String subject = "Permintaan / Perbaikan Barang "+Part+" "+urutan+" "+Lokasi;
                  String body = "Berikut Terlampir Detail Permintaan";

                  sendFromGMail(from, pass, to, subject, body);  
            
                  resetFrm();
        //loadDataET();

        //loadDataCVM();
      //  resetFrmCvm();

            
                }else {
                              JOptionPane.showMessageDialog(null, "Data Gagal Disimpan");
    ///loadDataET();
    //loadDataCVM();
       // resetFrmCvm();
                        }   
    }else if (cekemail.isSelected()==false){
       if (troubleETDao.insertTroubleETPermasalahan(temm)==true){
       worker.execute();
       // tableModelTroubleET.insertTroubleET(temm);
           resetFrm();
            
            
        //loadDataET();

        //loadDataCVM();
      //  resetFrmCvm();

            
    }else {
        JOptionPane.showMessageDialog(null, "Data Gagal Disimpan");
    ///loadDataET();
    //loadDataCVM();
       // resetFrmCvm();
    }     
    }
    
    }
  
    
   
    
    
    /*
     cbo_PartVM.setEnabled(false);
    cbo_Penyebab.setEnabled(false);
    txt_sumberaduan.setEnabled(false);
    
    
    */
    
    
    
}

        
        
        
    }
        
        
      
        
        
// TODO add your handling code here:
    }//GEN-LAST:event_btnSimpanActionPerformed



    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
tampilWorker w = new tampilWorker();
        loadDataET();
        resetFrm();
        w.execute();
  
      
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    
    
    


    
    public void addstasiunbyprojek(){
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
    
    
    
    private void addPerangkatbystation(){
        cbo_Perangkat.removeAllItems();
        cbo_Perangkat.addItem("- PILIH -");
            cbo_Perangkat.setSelectedIndex(0);
        List<ETModel> etModelbyStasiun = eTDao.getEtModelbyStasiun(String.valueOf(cbo_stasiun.getSelectedItem()));
        for (ETModel e : etModelbyStasiun){
            cbo_Perangkat.addItem(e);
        }
                     
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btn_SetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SetActionPerformed
        if (btn_Set.getText()=="SET"){
            txt_JamMa.setEnabled(false);
            t.stop();
            //Date d = new Date();
            //txt_TanggalM.setDate(d);
            txt_TanggalM.setEnabled(false);
            btn_Set.setText("EDIT");
        }else if (btn_Set.getText()=="EDIT"){
            txt_JamMa.setEnabled(true);
            txt_TanggalM.setEnabled(true);
            t.start();
            btn_Set.setText("SET");
            t.stop();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btn_SetActionPerformed

    private void btnDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoneActionPerformed
        if (btnDone.getText()=="DONE"){
            txt_TanggalD.setEnabled(false);
            txt_jamD.setEnabled(false);
            tD.stop();
            btnDone.setText(" EDIT  ");
            btnDone.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        }else if(btnDone.getText()==" EDIT  "){
            txt_TanggalD.setEnabled(true);
            txt_jamD.setEnabled(true);
            tD.start();
            btnDone.setText("DONE");
            tD.stop();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnDoneActionPerformed

    private void cbo_stasiunMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbo_stasiunMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_stasiunMouseClicked

    private void cbo_stasiunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_stasiunActionPerformed
        addPerangkatbystation();       // TODO add your handling code here:
    }//GEN-LAST:event_cbo_stasiunActionPerformed

    
    private void cbo_PerangkatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_PerangkatActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_PerangkatActionPerformed

    
   
    private void btn_GoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GoActionPerformed
             if (txt_caritanggal.getDate()!=null){
            Date tanggal = txt_caritanggal.getDate();

            List<TroubleEtModel> etModelsByTanggal = troubleETDao.getEtModelsByTanggal(tanggal);
            tableModelTroubleET.setData(etModelsByTanggal);
        }else {
            JOptionPane.showMessageDialog(null, "Tanggal Belum Dipilih");
        } 
           
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_GoActionPerformed

    private void tabel_troubleETMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_troubleETMouseClicked
       
            int row = tabel_troubleET.getSelectedRow();
            //String table_click = (String) (tabel_troubleET.getModel().getValueAt(row, 0));
            TroubleEtModel etModel = tableModelTroubleET.getTroubleET(tabel_troubleET.convertRowIndexToModel(row));
            PreparedStatement ps = null;
            ResultSet rs = null;
            List list = new ArrayList();
            String sql = "select * from trouble_et where No='"+etModel.getNoEt()+"'";
             try {
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()){
                
                String Jenis = rs.getString("JenisLaporan");
                cbo_jenislaporan.setSelectedItem(Jenis);
                String Sumber = rs.getString("Sumber");
               txt_sumberaduan.setText(Sumber);
               int idpart = rs.getInt("No_PVM");
               PerangkatVMModel part = perangkatVMDao.perangkatVMModelbyid(idpart);
               PerangkatVMModel pvm = part;
                cbo_PartVM.removeAllItems();
                cbo_PartVM.addItem(pvm);
                cbo_PartVM.setSelectedIndex(0);
                String problem = rs.getString("Problem");
                txt_problem.setText(problem);
                int idpnybb = rs.getInt("No_Penyebab");
                PenyebabModel penyebabByid = penyebabDao.getPenyebabByid(idpnybb);
                PenyebabModel pp = penyebabByid;
                cbo_Penyebab.removeAllItems();
                cbo_Penyebab.addItem(pp);
                cbo_Penyebab.setSelectedIndex(0);
                String solusi = rs.getString("Solusi");
                txt_solusi.setText(solusi);
                String status = rs.getString("status");
                cbo_status.setSelectedItem(status);
                

            }

            // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(TroubleETFrmGabung.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                 if (ps!=null){
                     try {
                         ps.close();
                     } catch (SQLException ex) {
                         Logger.getLogger(TroubleETFrmGabung.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }if (rs!=null){
                     try {
                         rs.close();
                     } catch (SQLException ex) {
                         Logger.getLogger(TroubleETFrmGabung.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
             }
    }//GEN-LAST:event_tabel_troubleETMouseClicked

    private void cbo_projekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_projekActionPerformed
if (cbo_projek.getSelectedIndex()==0){
    JOptionPane.showMessageDialog(null, "SIlahkan Pilih Projek");
}else {
      
        ProjectModel p = (ProjectModel) cbo_projek.getSelectedItem();
        addstasiunbyprojek();
        String setautonumber = troubleETDao.setautonumber(p);
        txt_Notroubletiket.setText(setautonumber);
}
      
        /*        String a ="KCI";
        String b = "DAMRI";
        String c = "RAILINK JKT";
        String d = "CCTV REST AREA";
        String e = "RAILINK MEDAN";
        String f = "TRANSJAKARTA";
        addstasiunbyprojek();
        String aa = String.valueOf(cbo_projek.getSelectedItem());
        if (aa.equals(a)){
        String setAutoNumberkci = troubleETDao.setAutoNumberKCI();
        txt_Notroubletiket.setText(setAutoNumberkci);
        }else if (aa.equals(b)){
        String setAutoNumberdamri = troubleETDao.setAutoNumberdamri();
        txt_Notroubletiket.setText(setAutoNumberdamri);
        } else if (aa.equals(c)){
        String setAutoNumberrlj = troubleETDao.setAutoNumberRLJ();
        txt_Notroubletiket.setText(setAutoNumberrlj);
        } else if (aa.equals(d)){
        String setAutoNumberram = troubleETDao.setAutoNumberRAM();
        txt_Notroubletiket.setText(setAutoNumberram);
        } else if (aa.equals(e)){
        String setAutoNumberrlm = troubleETDao.setAutoNumberRLM();
        txt_Notroubletiket.setText(setAutoNumberrlm);
        } else if (aa.equals(f)){
        String setAutoNumberTJ = troubleETDao.setAutoNumberTJ();
        txt_Notroubletiket.setText(setAutoNumberTJ);
        }
         */
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_projekActionPerformed

    private void txt_solusiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_solusiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_solusiActionPerformed

    private void chk_cvimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_cvimActionPerformed
if (chk_cvim.isSelected()){
    cbo_PartVM.removeAllItems();
    cbo_PartVM.addItem("- PILIH PERANGKAT VM -");
    cbo_PartVM.setSelectedIndex(0);
    List<PerangkatVMModel> partbyPerangkat = perangkatVMDao.getPartbyPerangkat();
    for (PerangkatVMModel p : partbyPerangkat){
        cbo_PartVM.addItem(p);
    }
}else if (!chk_cvim.isSelected()){
    cbo_PartVM.removeAllItems();
    cbo_PartVM.addItem("- PILIH PERANGKAT ET -");
    cbo_PartVM.setSelectedIndex(0);
    List<PerangkatVMModel> partbyPerangkatnotVM = perangkatVMDao.getPartbyPerangkatnotVM();
    for (PerangkatVMModel pp : partbyPerangkatnotVM){
        cbo_PartVM.addItem(pp);
    }
}
// TODO add your handling code here:
    }//GEN-LAST:event_chk_cvimActionPerformed

    
     public void addPenyebabByPart(){
     cbo_Penyebab.removeAllItems();
     cbo_Penyebab.addItem("- PILIH -");
            cbo_Penyebab.setSelectedIndex(0);
            
            if (cbo_PartVM.getSelectedIndex()==0){
            //JOptionPane.showMessageDialog(null, "Perangkat VM Belum Dipilih");
        }else {
         List<PenyebabModel> byPart = penyebabDao.getByPart(String.valueOf(cbo_PartVM.getSelectedItem()));
          for (PenyebabModel pm : byPart){
              cbo_Penyebab.addItem(pm);
          }
            
            }           
}
    
    private void cbo_PartVMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_PartVMActionPerformed
       addPenyebabByPart(); // TODO add your handling code here:
    }//GEN-LAST:event_cbo_PartVMActionPerformed

    private void cbo_jenislaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_jenislaporanActionPerformed
if (cbo_jenislaporan.getSelectedItem()=="ADUAN"){
    txt_sumberaduan.setText("");
    txt_solusi.setText("");
    cbo_PartVM.setEnabled(true);
    chk_cvim.setEnabled(true);
    cbo_Penyebab.setEnabled(true);
    txt_solusi.setEnabled(true);
    txt_sumberaduan.setEnabled(true);
    cbo_PartVM.setEnabled(false);
    chk_cvim.setEnabled(false);
    cbo_Penyebab.setEnabled(false);
   // txt_solusi.setEnabled(false);
    cbo_PartVM.removeAllItems();
    cbo_Penyebab.removeAllItems();
    //txt_solusi.setText("null");
     cekemail.setVisible(false);
     cekemail.setSelected(false);
}else  if (cbo_jenislaporan.getSelectedItem()=="PERMASALAHAN"){
    cbo_PartVM.setEnabled(true);
    chk_cvim.setEnabled(true);
    cbo_Penyebab.setEnabled(true);
    txt_solusi.setText("");
    txt_sumberaduan.setText("");
    txt_solusi.setEnabled(true);
    txt_sumberaduan.setEnabled(true);
    cekemail.setVisible(true);
    cekemail.setSelected(false);
    loadPartVM();
}else  if (cbo_jenislaporan.getSelectedItem()=="PEKERJAAN"){
   txt_sumberaduan.setText("");
    txt_solusi.setText("");
    cbo_PartVM.setEnabled(true);
    chk_cvim.setEnabled(true);
    cbo_Penyebab.setEnabled(true);
    txt_solusi.setEnabled(true);
    txt_sumberaduan.setEnabled(true);
    cbo_PartVM.setEnabled(false);
     chk_cvim.setEnabled(false);
    cbo_Penyebab.setEnabled(false);
    //txt_sumberaduan.setEnabled(false);
     cbo_PartVM.removeAllItems();
    cbo_Penyebab.removeAllItems();
   // txt_sumberaduan.setText("null");
     cekemail.setVisible(false);
     cekemail.setSelected(false);
}

    }//GEN-LAST:event_cbo_jenislaporanActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
tampilWorker w = new tampilWorker();
           int index = tabel_troubleET.getSelectedRow();
            if (index!=-1){
    try {
        TroubleEtModel model = tableModelTroubleET.getTroubleET(tabel_troubleET.convertRowIndexToModel(index));
        DIalogUbahTroubleET d = new DIalogUbahTroubleET();
        d.getTxtPengguna().setText(txt_petugas.getText());
        TroubleEtModel tm = d.UpdateTroubleEt(model);
        if (tm!=null){
            troubleETDao.updateTroubleET(model);
            tableModelTroubleET.updateTroubleET(index, tm);
            w.execute();
            String stat =  String.valueOf(cbo_searchstatus.getSelectedItem());
            List<TroubleEtModel> etModelsbystatus = troubleETDao.getEtModelsbystatus(stat);
        }else {
            JOptionPane.showMessageDialog(null, "Data Gagal Diupdate");
        }
    } catch (IOException ex) {
        Logger.getLogger(TroubleETFrmGabung.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(TroubleETFrmGabung.class.getName()).log(Level.SEVERE, null, ex);
    }
            }else {
                JOptionPane.showMessageDialog(null, "Seleksi Salah Satu Baris");
                //loadDataET();
            }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUbahActionPerformed

    private void txt_solusiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_solusiMouseClicked
       // TODO add your handling code here:
    }//GEN-LAST:event_txt_solusiMouseClicked

    private void txt_sumberaduanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_sumberaduanMouseClicked
txt_sumberaduan.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sumberaduanMouseClicked

    private void chkrefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkrefActionPerformed
if (chkref.isSelected()){
    txt_ref.setEnabled(true);
}else {
    txt_ref.setEnabled(false);
}
// TODO add your handling code here:
    }//GEN-LAST:event_chkrefActionPerformed

    private void cbo_statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_statusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_statusActionPerformed

    private void cbo_searchstatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_searchstatusActionPerformed
String stat =  String.valueOf(cbo_searchstatus.getSelectedItem());
        
        if (cbo_searchstatus.getSelectedIndex()==0){
loadDataET();
}else{
            List<TroubleEtModel> etModelsbystatus = troubleETDao.getEtModelsbystatus(stat);
    
            if (!etModelsbystatus.isEmpty()){
                  tableModelTroubleET.setData(etModelsbystatus);
            }else {
                JOptionPane.showMessageDialog(null, "Data Not Found");
            }
            
  
}
        

// TODO add your handling code here:
    }//GEN-LAST:event_cbo_searchstatusActionPerformed
private boolean validasisearchbyno(){
    boolean valid = false;
    if (txt_Notroubletiket.getText().trim().isEmpty()){
        JOptionPane.showMessageDialog(null, "NO Ticket masih kosong");
    }else {
        valid = true;
    }
    return valid;
}
    
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (validasisearchbyno()==true){
            String No = txt_Notroubletiket.getText();

            List<TroubleEtModel> etModelsByNoticket = troubleETDao.getEtModelsByNoticket(No);
            tableModelTroubleET.setData(etModelsByNoticket);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    
    private boolean validasisearchbydateandstatus(){
        boolean valid = false;
        if (txt_caritanggal.getDate()==null){
            JOptionPane.showMessageDialog(null, "Tanggal belum Dipilih");
        }else if (cbo_searchstatus.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "Status Belum Dipilih");
        }else {
            valid = true;
        }
        return valid;
    }
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (validasisearchbydateandstatus()==true){
            Date tanggal = txt_caritanggal.getDate();
            String status = (String) cbo_searchstatus.getSelectedItem();
            List<TroubleEtModel> etModelsByStatusandDate = troubleETDao.getEtModelsByStatusandDate(status, tanggal);
            tableModelTroubleET.setData(etModelsByStatusandDate);
        }      // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cekteknisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekteknisiActionPerformed
if (cekteknisi.isSelected()){
    txt_teknisi.setEnabled(true);
}else {
    txt_teknisi.setEnabled(false);
}        // TODO add your handling code here:
    }//GEN-LAST:event_cekteknisiActionPerformed

    private void cbo_caripenyebabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_caripenyebabActionPerformed
        if(cbo_caripenyebab.getSelectedIndex()==0){
            loadDataET();
        }else{

            PenyebabModel p = (PenyebabModel) cbo_caripenyebab.getSelectedItem();
            List<TroubleEtModel> penyebab = troubleETDao.getEtModelsBypenyebab(p);

            if(!penyebab.isEmpty()){
                tableModelTroubleET.setData(penyebab);
            }else{
                JOptionPane.showMessageDialog(null, "Data Penyebab not found");
            }
        }
    }//GEN-LAST:event_cbo_caripenyebabActionPerformed

    
    
    private void checkarahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkarahActionPerformed
if (checkarah.isSelected()){
    cbo_arah.setEnabled(true);
}else {
    cbo_arah.setEnabled(false);
}        // TODO add your handling code here:
    }//GEN-LAST:event_checkarahActionPerformed
 
    
    
    private static String USER_NAME = "callcenternutech@gmail.com";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "gataugwsob"; // GMail password
    
    private static String[] RECIPIENT = {"Marjanimakbulloh@gmail.com","Andreannovaldo5@gmail.com","fhandayani525@gmail.com","unitlogistik123@gmail.com","Repair.nutech@gmail.com","ucok@nutech-integrasi.com"};
   //  private static String[] RECIPIENT = {"yusufrahmatullah1749@gmail.com","anakgunung7@gmail.com","muhammadr170@gmail.com"};
    
     private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
        try {
            Properties props = System.getProperties();
            String host = "smtp.gmail.com";
            String filename = "E:/Form Permintaan Barang.xlsx";
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            props.put("mail.smtp.ssl.socketFactory", sf);
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.user", from);
            
            props.put("mail.smtp.password", pass);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            
            Session session = Session.getDefaultInstance(props);
            MimeMessage message = new MimeMessage(session);
            
            try {
                message.setFrom(new InternetAddress(from));
                InternetAddress[] toAddress = new InternetAddress[to.length];
                
                // To get the array of addresses
                for( int i = 0; i < to.length; i++ ) {
                    toAddress[i] = new InternetAddress(to[i]);
                }
                
                for( int i = 0; i < toAddress.length; i++) {
                    message.addRecipient(Message.RecipientType.TO, toAddress[i]);
                }
                
                message.setSubject(subject);
                BodyPart messageBodyPart = new MimeBodyPart();
                
                messageBodyPart.setText(body);
                
                Multipart multipart = new MimeMultipart();
                
                multipart.addBodyPart(messageBodyPart);
                
                messageBodyPart = new MimeBodyPart();
                
                DataSource source = new FileDataSource(filename);
                
                messageBodyPart.setDataHandler(new DataHandler(source));
                
                messageBodyPart.setFileName(filename);
                
                multipart.addBodyPart(messageBodyPart);
                
                message.setContent(multipart);
                
                Transport transport = session.getTransport("smtp");
                transport.connect(host, from, pass);
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
            }
            catch (AddressException ae) {
                ae.printStackTrace();
            }
            catch (MessagingException me) {
                me.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Email Berhasil Dikirim");
        }
        catch (GeneralSecurityException ex) {
            Logger.getLogger(TroubleETFrmGabung.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    
     
      
        // Get system properties
      


        
    
    
    
    
    
    
    
    
    
    
    
    
    private void createfile() {
    
     
    
        InputStream inputStreamimage = null;
        try {
            //List<TroubleEtModel> etModelsReport = troubleETDao.getEtModelsReport(txt_tglawal.getDate(), txt_akhir.getDate(), a);
            
            
            Workbook wb = new XSSFWorkbook();
            Sheet sheet = wb.createSheet("Form Aduan Permintaan dan Perbaikan Barang");
            XSSFFont font = (XSSFFont) wb.createFont();
            font.setBold(true);
            //header
            CellRangeAddress cellRangeAddress = new CellRangeAddress(4, 4, 1, 8);
            sheet.addMergedRegion(cellRangeAddress);
            CellStyle Style = wb.createCellStyle();
            CellStyle headerStyle1 = wb.createCellStyle();
            CellStyle headerStyle2 = wb.createCellStyle();
            CellStyle headerStyle3 = wb.createCellStyle();
            org.apache.poi.ss.usermodel.Cell cellmerge = sheet.createRow(4).createCell(1);
            //cellmerge.setCellValue("Form Aduan Permintaan dan Perbaikan Barang");
            headerStyle1.setBorderBottom(BorderStyle.MEDIUM);
            headerStyle1.setBorderTop(BorderStyle.MEDIUM);
            headerStyle1.setBorderLeft(BorderStyle.MEDIUM);
            cellmerge.setCellStyle(headerStyle1);
            Row rowheaderhead = sheet.getRow(4);
            org.apache.poi.ss.usermodel.Cell cellmerge2 = rowheaderhead.getCell(2);
            if (cellmerge2 == null) {
                cellmerge2 = rowheaderhead.createCell(2);
            }       cellmerge2.setCellType(cellmerge2.CELL_TYPE_STRING);
            headerStyle2.setBorderBottom(BorderStyle.MEDIUM);
            headerStyle2.setBorderTop(BorderStyle.MEDIUM);
            cellmerge2.setCellStyle(headerStyle2);
            Row rowheaderhead1 = sheet.getRow(4);
            org.apache.poi.ss.usermodel.Cell cellmerge3 = rowheaderhead1.getCell(3);
            if (cellmerge3 == null) {
                cellmerge3 = rowheaderhead1.createCell(3);
            }       cellmerge3.setCellType(cellmerge3.CELL_TYPE_STRING);
            headerStyle1.setBorderBottom(BorderStyle.MEDIUM);
            headerStyle1.setBorderTop(BorderStyle.MEDIUM);
            cellmerge3.setCellStyle(headerStyle1);
            Row rowheaderhead2 = sheet.getRow(4);
            org.apache.poi.ss.usermodel.Cell cellmerge4 = rowheaderhead1.getCell(4);
            if (cellmerge4 == null) {
        cellmerge4 = rowheaderhead2.createCell(4);
            }       cellmerge4.setCellType(cellmerge4.CELL_TYPE_STRING);
            headerStyle1.setBorderBottom(BorderStyle.MEDIUM);
            headerStyle1.setBorderTop(BorderStyle.MEDIUM);
            cellmerge4.setCellStyle(headerStyle1);
            Row rowheaderhead3 = sheet.getRow(4);
            org.apache.poi.ss.usermodel.Cell cellmerge5 = rowheaderhead3.getCell(5);
            if (cellmerge5 == null) {
        cellmerge5 = rowheaderhead2.createCell(5);
            }       cellmerge5.setCellType(cellmerge5.CELL_TYPE_STRING);
            headerStyle1.setBorderBottom(BorderStyle.MEDIUM);
            headerStyle1.setBorderTop(BorderStyle.MEDIUM);
            cellmerge5.setCellStyle(headerStyle1);
            Row rowheaderhead4 = sheet.getRow(4);
            org.apache.poi.ss.usermodel.Cell cellmerge6 = rowheaderhead4.getCell(6);
            if (cellmerge6 == null) {
                cellmerge6 = rowheaderhead4.createCell(6);
            }       cellmerge6.setCellType(cellmerge6.CELL_TYPE_STRING);
            headerStyle1.setBorderBottom(BorderStyle.MEDIUM);
            headerStyle1.setBorderTop(BorderStyle.MEDIUM);
            cellmerge6.setCellStyle(headerStyle1);
            Row rowheaderhead5 = sheet.getRow(4);
            org.apache.poi.ss.usermodel.Cell cellmerge7 = rowheaderhead5.getCell(7);
            if (cellmerge7 == null) {
                cellmerge7 = rowheaderhead5.createCell(7);
            }       cellmerge7.setCellType(cellmerge7.CELL_TYPE_STRING);
            headerStyle1.setBorderBottom(BorderStyle.MEDIUM);
            headerStyle1.setBorderTop(BorderStyle.MEDIUM);
            cellmerge7.setCellStyle(headerStyle1);
            Row rowheaderhead6 = sheet.getRow(4);
            org.apache.poi.ss.usermodel.Cell cellmerge8 = rowheaderhead6.getCell(8);
            if (cellmerge8 == null) {
                cellmerge8 = rowheaderhead6.createCell(8);
            }       cellmerge8.setCellType(cellmerge8.CELL_TYPE_STRING);
            headerStyle3.setBorderBottom(BorderStyle.MEDIUM);
            headerStyle3.setBorderTop(BorderStyle.MEDIUM);
            headerStyle3.setBorderRight(BorderStyle.MEDIUM);
            cellmerge8.setCellStyle(headerStyle3);
            Row rowcenter = sheet.getRow(4);
            org.apache.poi.ss.usermodel.Cell cellcenter = CellUtil.createCell(rowcenter, 1, "Form Aduan Permintaan dan Perbaikan Barang");
            CellUtil.setAlignment(cellcenter, HorizontalAlignment.CENTER);
            CellUtil.setFont(cellcenter, font);
            
            //header
            //Judul
            
            
            
            org.apache.poi.ss.usermodel.Cell cell6 = sheet.createRow(6).createCell(1);
            cell6.setCellValue("Hari/Tanggal "); //nama
            Row rowheader = sheet.getRow(6);
            org.apache.poi.ss.usermodel.Cell cell62 = rowheader.getCell(2);
            if (cell62 == null) {
                cell62 = rowheader.createCell(2);
            }       cell62.setCellType(cell62.CELL_TYPE_STRING);
            cell62.setCellValue(":");
            int widthUnits1 = 20*256;
            sheet.setColumnWidth(1, widthUnits1);
            int widthUnits2 = 20*20;
            sheet.setColumnWidth(2, widthUnits2);
            org.apache.poi.ss.usermodel.Cell cell7 = sheet.createRow(7).createCell(1);
            cell7.setCellValue("Petugas Call Center "); //nama
            Row rowheader7 = sheet.getRow(7);
            org.apache.poi.ss.usermodel.Cell cell72 = rowheader7.getCell(2);
            if (cell72 == null) {
                cell72 = rowheader7.createCell(2);
            }       cell72.setCellType(cell72.CELL_TYPE_STRING);
            cell72.setCellValue(":");
            org.apache.poi.ss.usermodel.Cell cell8 = sheet.createRow(8).createCell(1);
            cell8.setCellValue("Ref No "); //nama
            Row rowheader8 = sheet.getRow(8);
            org.apache.poi.ss.usermodel.Cell cell82 = rowheader8.getCell(2);
            if (cell82 == null) {
                cell82 = rowheader8.createCell(2);
            }       cell82.setCellType(cell82.CELL_TYPE_STRING);
            cell82.setCellValue(":");
            org.apache.poi.ss.usermodel.Cell cell9 = sheet.createRow(9).createCell(1);
            cell9.setCellValue("Trouble Ticket "); //nama
            Row rowheader9 = sheet.getRow(9);
            org.apache.poi.ss.usermodel.Cell cell92 = rowheader9.getCell(2);
            if (cell92 == null) {
                cell92 = rowheader9.createCell(2);
            }       cell92.setCellType(cell92.CELL_TYPE_STRING);
            cell92.setCellValue(":");
            org.apache.poi.ss.usermodel.Cell cell10 = sheet.createRow(10).createCell(1);
            cell10.setCellValue("Nama Stasiun "); //nama
            Row rowheader10 = sheet.getRow(10);
            org.apache.poi.ss.usermodel.Cell cell102 = rowheader10.getCell(2);
            if (cell102 == null) {
                cell102 = rowheader10.createCell(2);
            }       cell102.setCellType(cell102.CELL_TYPE_STRING);
            cell102.setCellValue(":");
            org.apache.poi.ss.usermodel.Cell cell11 = sheet.createRow(11).createCell(1);
            cell11.setCellValue("Nama Barang "); //nama
            Row rowheader11 = sheet.getRow(11);
            org.apache.poi.ss.usermodel.Cell cell112 = rowheader11.getCell(2);
            if (cell112 == null) {
                cell112 = rowheader11.createCell(2);
            }       cell112.setCellType(cell112.CELL_TYPE_STRING);
            cell112.setCellValue(":");
            org.apache.poi.ss.usermodel.Cell cell12 = sheet.createRow(12).createCell(1);
            cell12.setCellValue("Serial Number "); //nama
            Row rowheader12 = sheet.getRow(12);
            org.apache.poi.ss.usermodel.Cell cell122 = rowheader12.getCell(2);
            if (cell122 == null) {
                cell122 = rowheader12.createCell(2);
            }       cell122.setCellType(cell122.CELL_TYPE_STRING);
            cell122.setCellValue(":");
            org.apache.poi.ss.usermodel.Cell cell13 = sheet.createRow(13).createCell(1);
            cell13.setCellValue("Id Perangkat "); //nama
            Row rowheader13 = sheet.getRow(13);
            org.apache.poi.ss.usermodel.Cell cell132 = rowheader13.getCell(2);
            if (cell132 == null) {
                cell132 = rowheader13.createCell(2);
            }       cell132.setCellType(cell132.CELL_TYPE_STRING);
            cell132.setCellValue(":");
            org.apache.poi.ss.usermodel.Cell cell14 = sheet.createRow(14).createCell(1);
            cell14.setCellValue("Permasalahan "); //nama
            Row rowheader14 = sheet.getRow(14);
            org.apache.poi.ss.usermodel.Cell cell142 = rowheader14.getCell(2);
            if (cell142 == null) {
                cell142 = rowheader14.createCell(2);
            }       cell142.setCellType(cell142.CELL_TYPE_STRING);
            cell142.setCellValue(":");
            CellStyle Style1 = wb.createCellStyle();
            Style1.setBorderLeft(BorderStyle.MEDIUM);
            Style1.setBorderTop(BorderStyle.MEDIUM);
            CellStyle Style2 = wb.createCellStyle();
            Style2.setBorderLeft(BorderStyle.MEDIUM);
            Style2.setBorderBottom(BorderStyle.MEDIUM);
            Style.setFont(font);
            Style1.setFont(font);
            Style2.setFont(font);
            Style.setBorderLeft(BorderStyle.MEDIUM);
            cell6.setCellStyle(Style1);
            cell7.setCellStyle(Style);
            cell8.setCellStyle(Style);
            cell9.setCellStyle(Style);
            cell10.setCellStyle(Style);
            cell11.setCellStyle(Style);
            cell12.setCellStyle(Style);
            cell13.setCellStyle(Style);
            cell14.setCellStyle(Style2);
            //row 6
            CellStyle Style3 = wb.createCellStyle();
            Style3.setBorderTop(BorderStyle.MEDIUM);
            Row row6 = sheet.getRow(6);
            org.apache.poi.ss.usermodel.Cell cellD = row6.getCell(2);
            if (cellD == null) {
                cellD = row6.createCell(2);
            }       Row row6E = sheet.getRow(6);
            org.apache.poi.ss.usermodel.Cell cellE = row6E.getCell(3);
            if (cellE == null) {
                cellE = row6E.createCell(3);
            }       Row row6f = sheet.getRow(6);
            org.apache.poi.ss.usermodel.Cell cellf = row6f.getCell(4);
            if (cellf == null) {
                cellf = row6f.createCell(4);
            }       Row row6g = sheet.getRow(6);
            org.apache.poi.ss.usermodel.Cell cellg = row6f.getCell(5);
            if (cellg == null) {
                cellg = row6g.createCell(5);
            }       Row row6h = sheet.getRow(6);
            org.apache.poi.ss.usermodel.Cell cellh = row6f.getCell(6);
            if (cellh == null) {
                cellh = row6h.createCell(6);
            }       Row row6i = sheet.getRow(6);
            org.apache.poi.ss.usermodel.Cell celli = row6f.getCell(7);
            if (celli == null) {
                celli = row6i.createCell(7);
            }       Row row6j = sheet.getRow(6);
            org.apache.poi.ss.usermodel.Cell cellj = row6j.getCell(8);
            if (cellj == null) {
                cellj = row6j.createCell(8);
            }       CellStyle Style68 = wb.createCellStyle();
            Style68.setBorderTop(BorderStyle.MEDIUM);
            Style68.setBorderRight(BorderStyle.MEDIUM);
            cellD.setCellStyle(Style3);
            cellE.setCellStyle(Style3);
            cellf.setCellStyle(Style3);
            cellg.setCellStyle(Style3);
            cellh.setCellStyle(Style3);
            celli.setCellStyle(Style3);
            cellj.setCellStyle(Style68);
            //row 14
            
            CellStyle Style4 = wb.createCellStyle();
            Style4.setBorderBottom(BorderStyle.MEDIUM);
            Row row14 = sheet.getRow(14);
            org.apache.poi.ss.usermodel.Cell cellD14 = row14.getCell(2);
            if (cellD14 == null) {
                cellD14 = row14.createCell(2);
            }       Row row14E = sheet.getRow(14);
            org.apache.poi.ss.usermodel.Cell cell14E = row14E.getCell(3);
            if (cell14E == null) {
                cell14E = row14E.createCell(3);
            }       Row row14f = sheet.getRow(14);
            org.apache.poi.ss.usermodel.Cell cell14f = row14f.getCell(4);
            if (cell14f == null) {
                cell14f = row14f.createCell(4);
            }       Row row14g = sheet.getRow(14);
            org.apache.poi.ss.usermodel.Cell cell14g = row14f.getCell(5);
            if (cell14g == null) {
                cell14g = row14g.createCell(5);
            }       Row row14h = sheet.getRow(14);
            org.apache.poi.ss.usermodel.Cell cell14h = row14f.getCell(6);
            if (cell14h == null) {
                cell14h = row14h.createCell(6);
            }       Row row14i = sheet.getRow(14);
            org.apache.poi.ss.usermodel.Cell cell14i = row14f.getCell(7);
            if (cell14i == null) {
                cell14i = row14i.createCell(7);
            }       Row row14j = sheet.getRow(14);
            org.apache.poi.ss.usermodel.Cell cell14j = row14j.getCell(8);
            if (cell14j == null) {
                cell14j = row14j.createCell(8);
            }       CellStyle Style148 = wb.createCellStyle();
            Style148.setBorderBottom(BorderStyle.MEDIUM);
            Style148.setBorderRight(BorderStyle.MEDIUM);
            cellD14.setCellStyle(Style4);
            cell14E.setCellStyle(Style4);
            cell14f.setCellStyle(Style4);
            cell14g.setCellStyle(Style4);
            cell14h.setCellStyle(Style4);
            cell14i.setCellStyle(Style4);
            cell14j.setCellStyle(Style148);
            //column 8
            
            
            CellStyle Stylecol8 = wb.createCellStyle();
            Stylecol8.setBorderRight(BorderStyle.MEDIUM);
            Row row78 = sheet.getRow(7);
            org.apache.poi.ss.usermodel.Cell celli7 = row78.getCell(8);
            if (celli7 == null) {
                celli7 = row78.createCell(8);
            }       Row row88 = sheet.getRow(8);
            org.apache.poi.ss.usermodel.Cell celli8 = row88.getCell(8);
            if (celli8 == null) {
        celli8 = row88.createCell(8);
            }       Row row98 = sheet.getRow(9);
            org.apache.poi.ss.usermodel.Cell celli9 = row98.getCell(8);
            if (celli9 == null) {
        celli9 = row98.createCell(8);
            }       Row row108 = sheet.getRow(10);
            org.apache.poi.ss.usermodel.Cell celli10 = row108.getCell(8);
            if (celli10 == null) {
                celli10 = row108.createCell(8);
            }       Row row118 = sheet.getRow(11);
            org.apache.poi.ss.usermodel.Cell celli11 = row118.getCell(8);
            if (celli11 == null) {
                celli11 = row118.createCell(8);
            }       Row row128 = sheet.getRow(12);
            org.apache.poi.ss.usermodel.Cell celli12 = row128.getCell(8);
            if (celli12 == null) {
                celli12 = row128.createCell(8);
            }       Row row138 = sheet.getRow(13);
            org.apache.poi.ss.usermodel.Cell celli13 = row138.getCell(8);
            if (celli13 == null) {
                celli13 = row138.createCell(8);
            }       celli7.setCellStyle(Stylecol8);
            celli8.setCellStyle(Stylecol8);
            celli9.setCellStyle(Stylecol8);
            celli10.setCellStyle(Stylecol8);
            celli11.setCellStyle(Stylecol8);
            celli12.setCellStyle(Stylecol8);
            celli13.setCellStyle(Stylecol8);
            //value on database
    
     Date d = new Date();
     DateFormat dayFormat = new SimpleDateFormat("EEEE");
     String day = dayFormat.format(d);
     DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
     String tanggal = dateFormat.format(d);
     ProjectModel p = (ProjectModel) cbo_projek.getSelectedItem();
     List<TroubleEtModel> etModelsByNoticket = troubleETDao.getEtModelsByNoticket(txt_Notroubletiket.getText());
     Row row63 = sheet.getRow(6);
     org.apache.poi.ss.usermodel.Cell celltanggal = row63.getCell(3);
     if (celltanggal == null) {
         celltanggal = row63.createCell(3);
     }       celltanggal.setCellType(celltanggal.CELL_TYPE_STRING);
     celltanggal.setCellValue(String.valueOf(day+", "+tanggal));
     Row row73 = sheet.getRow(7);
     org.apache.poi.ss.usermodel.Cell cellpetugas = row73.getCell(3);
     if (cellpetugas == null) {
         cellpetugas = row73.createCell(3);
     }       cellpetugas.setCellType(cellpetugas.CELL_TYPE_STRING);
     cellpetugas.setCellValue(String.valueOf(txt_petugas.getText()));
     Row row83 = sheet.getRow(8);
     org.apache.poi.ss.usermodel.Cell cellrefno = row83.getCell(3);
     if (cellrefno == null) {
         cellrefno = row83.createCell(3);
     }       cellrefno.setCellType(cellrefno.CELL_TYPE_STRING);
     cellrefno.setCellValue(String.valueOf(etModelsByNoticket.get(0).getReftrouble()));
            Row row93 = sheet.getRow(9);
            org.apache.poi.ss.usermodel.Cell cellnotrouble = row93.getCell(3);
            if (cellnotrouble == null) {
                cellnotrouble = row93.createCell(3);
            }       cellnotrouble.setCellType(cellnotrouble.CELL_TYPE_STRING);
            cellnotrouble.setCellValue(String.valueOf(etModelsByNoticket.get(0).getNoEt()));
            Row row103 = sheet.getRow(10);
            org.apache.poi.ss.usermodel.Cell cellstasiun = row103.getCell(3);
            if (cellstasiun == null) {
                cellstasiun = row103.createCell(3);
            }       cellstasiun.setCellType(cellstasiun.CELL_TYPE_STRING);
            cellstasiun.setCellValue(String.valueOf(etModelsByNoticket.get(0).getSm().getNamastasiun()));
            Row row113 = sheet.getRow(11);
            org.apache.poi.ss.usermodel.Cell cellbarang = row113.getCell(3);
            if (cellbarang == null) {
                cellbarang = row113.createCell(3);
            }       cellbarang.setCellType(cellbarang.CELL_TYPE_STRING);
            cellbarang.setCellValue(String.valueOf(etModelsByNoticket.get(0).getPart().getNamaPerangkat()));
            Row row123 = sheet.getRow(12);
            org.apache.poi.ss.usermodel.Cell cellsn = row123.getCell(3);
            if (cellsn == null) {
                cellsn = row123.createCell(3);
            }       cellsn.setCellType(cellsn.CELL_TYPE_STRING);
            cellsn.setCellValue(String.valueOf(etModelsByNoticket.get(0).getTeknisi()));
            Row row133 = sheet.getRow(13);
            org.apache.poi.ss.usermodel.Cell cellid = row133.getCell(3);
            if (cellid == null) {
                cellid = row133.createCell(3);
            }       cellid.setCellType(cellid.CELL_TYPE_STRING);
            cellid.setCellValue(String.valueOf(etModelsByNoticket.get(0).getEtm().getId()));
            Row row143 = sheet.getRow(14);
            org.apache.poi.ss.usermodel.Cell cellmasalah = row143.getCell(3);
            if (cellmasalah == null) {
                cellmasalah = row143.createCell(3);
            }       cellmasalah.setCellType(cellmasalah.CELL_TYPE_STRING);
            cellmasalah.setCellValue(String.valueOf(etModelsByNoticket.get(0).getProblem()));
            //image on excel
            //ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
           // URL resource = classLoader.getResource("com/ridwan/ICON/logonutech.png");
            File file = new File("E:/FORM HARIAN IT & CALL CENTER/logonutech.png");
            inputStreamimage = new FileInputStream(file);
            //Get the contents of an InputStream as a byte[].
            byte[] bytes;
            try {
                bytes = IOUtils.toByteArray(inputStreamimage);
                int pictureIdxbefore = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            //close the input stream
            inputStreamimage.close();
            //Returns an object that handles instantiating concrete classes
            CreationHelper helper = wb.getCreationHelper();
            //Creates the top-level drawing patriarch.
            Drawing drawing = sheet.createDrawingPatriarch();
            //Create an anchor that is attached to the worksheet
            ClientAnchor anchor = helper.createClientAnchor();
            //create an anchor with upper left cell _and_ bottom right cell
            anchor.setCol1(1); //Column B
            anchor.setRow1(2); //Row 3
            anchor.setCol2(2); //Column C
            anchor.setRow2(3); //Row 4
            //Creates a picture
            Picture pict = drawing.createPicture(anchor, pictureIdxbefore);
            //Reset the image to the original size
            //pict.resize(); //don't do that. Let the anchor resize the image!

   //Create the Cell picture value
          org.apache.poi.ss.usermodel.Cell cellimage = sheet.createRow(2).createCell(1);
            //set width to n character widths = count characters * 256
   int widthUnits = 20*256;
            sheet.setColumnWidth(1, widthUnits);
            //set height to n points in twips = n * 20
   short heightUnits = 60*15;
            cellimage.getRow().setHeight(heightUnits);
            //Write the Excel file
   FileOutputStream fileOut = null;
            fileOut = new FileOutputStream("E:/Form Permintaan Barang.xlsx");
            //JOptionPane.showMessageDialog(null, "Ok");
   wb.write(fileOut);
            fileOut.close();
            JOptionPane.showMessageDialog(null, "File Berhasil Dibuat");
            } catch (IOException ex) {
                Logger.getLogger(TroubleETFrmGabung.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Adds a picture to the workbook
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TroubleETFrmGabung.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inputStreamimage.close();
            } catch (IOException ex) {
                Logger.getLogger(TroubleETFrmGabung.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      }
    
    
    
    
    
    
    private void cekemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekemailActionPerformed
        // TODO add your handling code here:'
       
        
    }//GEN-LAST:event_cekemailActionPerformed

    private void txt_NotroubletiketMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_NotroubletiketMouseClicked
txt_Notroubletiket.setEnabled(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NotroubletiketMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar ProgressBar;
    private javax.swing.JButton btnDone;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUbah;
    private javax.swing.JButton btn_Go;
    private javax.swing.JButton btn_Set;
    private javax.swing.JComboBox cbo_PartVM;
    private javax.swing.JComboBox cbo_Penyebab;
    private javax.swing.JComboBox cbo_Perangkat;
    private javax.swing.JComboBox cbo_arah;
    private javax.swing.JComboBox cbo_caripenyebab;
    private javax.swing.JComboBox cbo_jenislaporan;
    private javax.swing.JComboBox cbo_projek;
    private javax.swing.JComboBox cbo_searchstatus;
    private javax.swing.JComboBox cbo_stasiun;
    private javax.swing.JComboBox cbo_status;
    private javax.swing.JCheckBox cekemail;
    private javax.swing.JCheckBox cekteknisi;
    private javax.swing.JCheckBox checkarah;
    private javax.swing.JCheckBox chk_cvim;
    private javax.swing.JCheckBox chkref;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel loading;
    private com.ridwan.Dialog.panelgambar panelgambarafterr;
    private com.ridwan.Dialog.panelgambar panelgambarbefore;
    private javax.swing.JPanel panelnext;
    private javax.swing.JPanel panelprev;
    private javax.swing.JTable tabel_troubleET;
    private javax.swing.JSpinner txt_Jam;
    private javax.swing.JTextField txt_JamMa;
    private javax.swing.JTextField txt_Notroubletiket;
    private com.toedter.calendar.JDateChooser txt_TanggalD;
    private com.toedter.calendar.JDateChooser txt_TanggalM;
    private javax.swing.JTextField txt_carikata;
    private com.toedter.calendar.JDateChooser txt_caritanggal;
    private javax.swing.JLabel txt_gambarsebelum;
    private javax.swing.JLabel txt_gambarsesudah;
    private javax.swing.JTextField txt_jamD;
    private javax.swing.JTextField txt_petugas;
    private javax.swing.JTextField txt_problem;
    private javax.swing.JTextField txt_ref;
    private javax.swing.JTextField txt_solusi;
    private javax.swing.JTextField txt_sumberaduan;
    private javax.swing.JTextField txt_teknisi;
    private javax.swing.JLabel txt_terimakasih;
    // End of variables declaration//GEN-END:variables
}
