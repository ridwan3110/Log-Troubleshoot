/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Frm;

import com.ridwan.DAO.ProjekDao;
import com.ridwan.DAO.TroubleETDao;
import com.ridwan.DBC.DBC;
import com.ridwan.Model.ProjectModel;
import com.ridwan.Model.TroubleEtModel;
import com.ridwan.ReportDialog.TampilReport;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Cell;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFFont;


/**
 *
 * @author Muhammad Ridwan
 */
public class ExportDokumentasi extends javax.swing.JInternalFrame {
private ProjekDao projekDao = new ProjekDao();
private TroubleETDao troubleETDao = new TroubleETDao();
    private Connection connection;
    private Image image;
    
    
    /**
     * Creates new form ExportFrm
     */
    public ExportDokumentasi() {
        initComponents();
         setIconifiable(false);
        setMaximizable(false);
        setClosable(false);
        setResizable(false);
       connection =DBC.getConnection();
    }
    
    
    
    
    
    
    private boolean validasiinput(){
        boolean valid = false;
        if (txt_tglawal.getDate()==null){
            JOptionPane.showMessageDialog(null, "Tanggal Awal Belum Dipilih");
        }else if (txt_akhir.getDate()==null){
            JOptionPane.showMessageDialog(null, "Tanggal Akhir Belum DIpilih");
        }else {
            valid = true;
        }
        return valid;
    }
    
    
      public JTextField getTxtPengguna (){
        return txt_petugas;
    }
    
    public void setTxtPengguna (JTextField txtpetugas){
        
    this.txt_petugas = txtpetugas;
    
    }
    
    
    
    
    
    
    
    
    private void saveimagetofile() throws SQLException, ClassNotFoundException{
    try {
         PreparedStatement ps = null;
            ResultSet rs = null;
            List list = new ArrayList();
            String sql = "select JenisLaporan, pic_before from trouble_et where No = '20181120KCI0000000537'";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()){
                byte [] b = rs.getBytes("pic_before");
                String a = rs.getString("JenisLaporan");
                ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(b));
                ImageIcon icon =  (ImageIcon) inputStream.readObject();
               

// cast it to bufferedimage
BufferedImage bi = new BufferedImage(
    icon.getIconWidth(),
    icon.getIconHeight(),
    BufferedImage.TYPE_INT_RGB);
Graphics g = bi.createGraphics();
// paint the Icon to the BufferedImage.
icon.paintIcon(null, g, 0,0);
g.dispose();

try {
    // save to file
    File outputfile = new File("saved.png");
    ImageIO.write(bi, "png", outputfile);
} catch (IOException e) {
    e.printStackTrace();
}
       // BufferedImage final_buffered_image = ImageIO.read(inputStream);
       // ImageIO.write(final_buffered_image , "jpg", new File("D:/final_file.jpg") );
       // System.out.println("Converted Successfully!");
            }
    } catch (IOException ex) {
        Logger.getLogger(ExportDokumentasi.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    
    
    
    

    
    
    
    
    

    
 private void addgambartoexcel() throws ClassNotFoundException, IOException{
     String a = "Done";
    List<TroubleEtModel> etModelsReport = troubleETDao.getEtModelsReport(txt_tglawal.getDate(), txt_akhir.getDate(), a);
     
    
    Workbook wb = new XSSFWorkbook();
   Sheet sheet = wb.createSheet("Dokumentasi Pemasangan Barang ");
   XSSFFont font = (XSSFFont) wb.createFont();
            font.setBold(true);
   
   //header

   CellStyle Style = wb.createCellStyle();
      Style.setBorderTop(BorderStyle.MEDIUM);
            Style.setBorderRight(BorderStyle.MEDIUM);
            Style.setBorderBottom(BorderStyle.MEDIUM);
            Style.setBorderLeft(BorderStyle.MEDIUM);
            Style.setAlignment(HorizontalAlignment.CENTER);
            Style.setFont(font);
            org.apache.poi.ss.usermodel.Cell cellmerge = sheet.createRow(1).createCell(0);
          cellmerge.setCellValue("Dokumentasi Pemasangan Barang");
         cellmerge.setCellStyle(Style); 
     CellRangeAddress cellRangeAddress = new CellRangeAddress(1, 1, 0, 10);
     sheet.addMergedRegion(cellRangeAddress);
     
      
         
          
           
         // Row rowcenter = sheet.getRow(1);
        //org.apache.poi.ss.usermodel.Cell cellcenter = CellUtil.createCell(rowcenter, 0, "Dokumentasi Pemasangan Barang");
        //CellUtil.setAlignment(cellcenter, HorizontalAlignment.CENTER);
        
        
    org.apache.poi.ss.usermodel.Cell cellnol = sheet.createRow(2).createCell(0);
          cellnol.setCellValue("No Trouble");
          
           Row rowheader = sheet.getRow(2);
           cellnol.setCellStyle(Style);
           
           
    org.apache.poi.ss.usermodel.Cell cellsatu = rowheader.getCell(1);
    if (cellsatu == null) {
        cellsatu = rowheader.createCell(1);
    }
    cellsatu.setCellType(cellsatu.CELL_TYPE_STRING);
    cellsatu.setCellValue("Tanggal Masalah");
     cellsatu.setCellStyle(Style);
    
     org.apache.poi.ss.usermodel.Cell celldua = rowheader.getCell(2);
    if (celldua == null) {
        celldua = rowheader.createCell(2);
    }
    celldua.setCellType(celldua.CELL_TYPE_STRING);
    celldua.setCellValue("Tanggal Done");
     celldua.setCellStyle(Style);
    
    org.apache.poi.ss.usermodel.Cell celltiga = rowheader.getCell(3);
    if (celltiga == null) {
        celltiga = rowheader.createCell(3);
    }
    celltiga.setCellType(celltiga.CELL_TYPE_STRING);
    celltiga.setCellValue("Lokasi");
     celltiga.setCellStyle(Style);
    
    org.apache.poi.ss.usermodel.Cell cellempat = rowheader.getCell(4);
    if (cellempat == null) {
        cellempat = rowheader.createCell(4);
    }
    cellempat.setCellType(cellempat.CELL_TYPE_STRING);
    cellempat.setCellValue("Perangkat");
     cellempat.setCellStyle(Style);
    
    org.apache.poi.ss.usermodel.Cell celllima = rowheader.getCell(5);
    if (celllima == null) {
        celllima = rowheader.createCell(5);
    }
    celllima.setCellType(celllima.CELL_TYPE_STRING);
    celllima.setCellValue("Id");
     celllima.setCellStyle(Style);
    
    
    org.apache.poi.ss.usermodel.Cell cellenam = rowheader.getCell(6);
    if (cellenam == null) {
        cellenam = rowheader.createCell(6);
    }
    cellenam.setCellType(cellenam.CELL_TYPE_STRING);
    cellenam.setCellValue("Problem");
     cellenam.setCellStyle(Style);
    
    
    org.apache.poi.ss.usermodel.Cell celllapan = rowheader.getCell(8);
    if (celllapan == null) {
        celllapan = rowheader.createCell(8);
    }
    celllapan.setCellType(celllapan.CELL_TYPE_STRING);
    celllapan.setCellValue("Solusi");
    celllapan.setCellStyle(Style);
    
    org.apache.poi.ss.usermodel.Cell celltujuh = rowheader.getCell(7);
    if (celltujuh == null) {
        celltujuh = rowheader.createCell(7);
    }
    celltujuh.setCellType(celltujuh.CELL_TYPE_STRING);
    celltujuh.setCellValue("Pic Before");
     celltujuh.setCellStyle(Style);
    
      org.apache.poi.ss.usermodel.Cell cellsembilan = rowheader.getCell(9);
    if (cellsembilan == null) {
        cellsembilan = rowheader.createCell(9);
    }
    cellsembilan.setCellType(cellsembilan.CELL_TYPE_STRING);
    cellsembilan.setCellValue("Pic After");
    cellsembilan.setCellStyle(Style);
    
     org.apache.poi.ss.usermodel.Cell cellsepuluh = rowheader.getCell(10);
    if (cellsepuluh == null) {
        cellsepuluh = rowheader.createCell(10);
    }
    cellsepuluh.setCellType(cellsepuluh.CELL_TYPE_STRING);
    cellsepuluh.setCellValue("Jenis Pergantian");
     cellsepuluh.setCellStyle(Style);
    
    
    
     int widthUnits0 = 20*400;
   sheet.setColumnWidth(0, widthUnits0);
   
   int widthUnits1 = 20*256;
   sheet.setColumnWidth(1, widthUnits1);
           
           int widthUnits2 = 20*256;
   sheet.setColumnWidth(2, widthUnits2);
     int widthUnits3 = 20*256;
   sheet.setColumnWidth(3, widthUnits3);
   
     int widthUnits4 = 20*256;
   sheet.setColumnWidth(4, widthUnits4);
   
     int widthUnits5 = 20*256;
   sheet.setColumnWidth(5, widthUnits5);
   
     int widthUnits6 = 20*700;
   sheet.setColumnWidth(6, widthUnits6);
   
     int widthUnits8 = 20*700;
   sheet.setColumnWidth(8, widthUnits8);
   
   int widthUnits9 = 20*256;
   sheet.setColumnWidth(10, widthUnits5);
  
  
    for (int i = 2 ; i<etModelsReport.size()+2;i++){
    
                ObjectInputStream inputStreambefore = new ObjectInputStream(new ByteArrayInputStream(etModelsReport.get(i-2).getPicbefore()));
                 ObjectInputStream inputStreamafter = new ObjectInputStream(new ByteArrayInputStream(etModelsReport.get(i-2).getPicafter()));
                ImageIcon iconbefore =  (ImageIcon) inputStreambefore.readObject();
                 ImageIcon iconafter =  (ImageIcon) inputStreamafter.readObject();
      
                BufferedImage bibefore = new BufferedImage(
    iconbefore.getIconWidth(),
    iconbefore.getIconHeight(),
    BufferedImage.TYPE_INT_RGB);
                
                
                
                BufferedImage biafter = new BufferedImage(
    iconafter.getIconWidth(),
    iconafter.getIconHeight(),
    BufferedImage.TYPE_INT_RGB);
                
                
                
Graphics gbefore = bibefore.createGraphics();
Graphics gafter = biafter.createGraphics();
// paint the Icon to the BufferedImage.
iconbefore.paintIcon(null, gbefore, 0,0);
gbefore.dispose();
iconafter.paintIcon(null, gafter, 0,0);
gafter.dispose();


    // save to file
    File outputfilebefore = new File("saved.png");
    ImageIO.write(bibefore, "png", outputfilebefore);
    
    File outputfileafter = new File("saved1.png");
    ImageIO.write(biafter, "png", outputfileafter);
         
                
                
                
   //FileInputStream obtains input bytes from the image file
   InputStream inputStream1before = new FileInputStream(outputfilebefore);
   InputStream inputStream2after = new FileInputStream(outputfileafter);
   //Get the contents of an InputStream as a byte[].
   byte[] bytesbefore = IOUtils.toByteArray(inputStream1before);
   byte[] bytesafter = IOUtils.toByteArray(inputStream2after);
   //Adds a picture to the workbook
   int pictureIdxbefore = wb.addPicture(bytesbefore, Workbook.PICTURE_TYPE_PNG);
   int pictureIdxafter = wb.addPicture(bytesafter, Workbook.PICTURE_TYPE_PNG);
   //close the input stream
   inputStream1before.close();
   inputStream2after.close();
   //Returns an object that handles instantiating concrete classes
   CreationHelper helperbefore = wb.getCreationHelper();
   CreationHelper helperafter = wb.getCreationHelper();
   //Creates the top-level drawing patriarch.
   Drawing drawingbefore = sheet.createDrawingPatriarch();
    Drawing drawingafter = sheet.createDrawingPatriarch();

   //Create an anchor that is attached to the worksheet
   ClientAnchor anchorbefore = helperbefore.createClientAnchor();
   ClientAnchor anchorafter = helperafter.createClientAnchor();

   //create an anchor with upper left cell _and_ bottom right cell
   anchorbefore.setCol1(7); //Column B
   anchorbefore.setRow1(i+1); //Row 3
anchorbefore.setCol2(8); //Column C
anchorbefore.setRow2(i+2); //Row 4


 anchorafter.setCol1(9); //Column B
   anchorafter.setRow1(i+1); //Row 3
anchorafter.setCol2(10); //Column C
anchorafter.setRow2(i+2); 

   //Creates a picture
   Picture pictbefore = drawingbefore.createPicture(anchorbefore, pictureIdxbefore);
   Picture pictafter = drawingafter.createPicture(anchorafter, pictureIdxafter);

   //Reset the image to the original size
   //pict.resize(); //don't do that. Let the anchor resize the image!

   //Create the Cell picture value
          org.apache.poi.ss.usermodel.Cell cellbefore = sheet.createRow(i+1).createCell(7);
          org.apache.poi.ss.usermodel.Cell cellafter = sheet.createRow(i+1).createCell(9);
          
          /*//cell header
          org.apache.poi.ss.usermodel.Cell cellnol = sheet.createRow(2).createCell(0);
          cellnol.setCellValue("No Trouble");
          org.apache.poi.ss.usermodel.Cell cellsatu = sheet.createRow(2).createCell(1);
          cellsatu.setCellValue("Tanggal Masalah");
          org.apache.poi.ss.usermodel.Cell celldua = sheet.createRow(2).createCell(2);
          celldua.setCellValue("Tanggal Done");
          org.apache.poi.ss.usermodel.Cell celltiga = sheet.createRow(2).createCell(3);
          celltiga.setCellValue("Lokasi");
          org.apache.poi.ss.usermodel.Cell cellempat = sheet.createRow(2).createCell(4);
          cellempat.setCellValue("Perangkat");
          org.apache.poi.ss.usermodel.Cell celllima = sheet.createRow(2).createCell(5);
          celllima.setCellValue("ID");
          org.apache.poi.ss.usermodel.Cell cellenam = sheet.createRow(2).createCell(6);
          cellenam.setCellValue("Problem");
          org.apache.poi.ss.usermodel.Cell celllapan = sheet.createRow(2).createCell(8);
          celllapan.setCellValue("Solusi");
          */
          
          
          //cell text value database
          org.apache.poi.ss.usermodel.Cell cell0 = sheet.createRow(i+1).createCell(0);
          cell0.setCellValue(etModelsReport.get(i-2).getNoEt());
          cell0.setCellStyle(Style);
          Row row = sheet.getRow(i+1);
   org.apache.poi.ss.usermodel.Cell cell1 = row.getCell(1);
    if (cell1 == null) {
        cell1 = row.createCell(1);
    }
    cell1.setCellType(cell1.CELL_TYPE_STRING);
    cell1.setCellValue(String.valueOf(etModelsReport.get(i-2).getTglMasalah()));
    cell1.setCellStyle(Style);
    
    org.apache.poi.ss.usermodel.Cell cell2 = row.getCell(2);
    if (cell2 == null) {
        cell2 = row.createCell(2);
    }
    cell2.setCellType(cell2.CELL_TYPE_STRING);
    cell2.setCellValue(String.valueOf(etModelsReport.get(i-2).getTglDone()));
    cell2.setCellStyle(Style);
    
    org.apache.poi.ss.usermodel.Cell cell3 = row.getCell(3);
    if (cell3 == null) {
        cell3 = row.createCell(3);
    }
    cell3.setCellType(cell3.CELL_TYPE_STRING);
    cell3.setCellValue(String.valueOf(etModelsReport.get(i-2).getSm().getNamastasiun()));
         cell3.setCellStyle(Style); 
    
    org.apache.poi.ss.usermodel.Cell cell4 = row.getCell(4);
    if (cell4 == null) {
        cell4 = row.createCell(4);
    }
    cell4.setCellType(cell4.CELL_TYPE_STRING);
    cell4.setCellValue(String.valueOf(etModelsReport.get(i-2).getEtm().getNamaPerangkat()));    
    cell4.setCellStyle(Style);
    
      org.apache.poi.ss.usermodel.Cell cell5 = row.getCell(5);
    if (cell5 == null) {
        cell5 = row.createCell(5);
    }
    cell5.setCellType(cell5.CELL_TYPE_STRING);
    cell5.setCellValue(String.valueOf(etModelsReport.get(i-2).getEtm().getId())); 
    cell5.setCellStyle(Style);
    
     org.apache.poi.ss.usermodel.Cell cell6 = row.getCell(6);
    if (cell6 == null) {
        cell6 = row.createCell(6);
    }
    cell6.setCellType(cell6.CELL_TYPE_STRING);
    cell6.setCellValue(etModelsReport.get(i-2).getProblem()); 
    cell6.setCellStyle(Style); 
    
    org.apache.poi.ss.usermodel.Cell cell8 = row.getCell(8);
    if (cell8 == null) {
        cell8 = row.createCell(8);
    }
    cell8.setCellType(cell8.CELL_TYPE_STRING);
    cell8.setCellValue(etModelsReport.get(i-2).getSolusi()); 
    cell8.setCellStyle(Style);
    
     org.apache.poi.ss.usermodel.Cell cell10 = row.getCell(10);
    if (cell10 == null) {
        cell10 = row.createCell(10);
    }
    cell10.setCellType(cell10.CELL_TYPE_STRING);
    cell10.setCellValue(etModelsReport.get(i-2).getPart().getNamaPerangkat()); 
  cell10.setCellStyle(Style);
          
         //ukuran cell
          
   /*
    int widthUnits1 = 20*256;
   sheet.setColumnWidth(1, widthUnits1);
   short heightUnits1 = 60*20;
   cell1.getRow().setHeight(heightUnits1);
   
   int widthUnits2 = 20*256;
   sheet.setColumnWidth(2, widthUnits2);
   short heightUnits2 = 60*20;
   cell2.getRow().setHeight(heightUnits2);
   
   
   int widthUnits3 = 20*256;
   sheet.setColumnWidth(3, widthUnits3);
   short heightUnits3 = 60*20;
   cell3.getRow().setHeight(heightUnits3);
   
    int widthUnits4 = 20*256;
   sheet.setColumnWidth(4, widthUnits4);
   short heightUnits4 = 60*20;
   cell4.getRow().setHeight(heightUnits4);
   
   int widthUnits5 = 20*256;
   sheet.setColumnWidth(5, widthUnits5);
   short heightUnits5 = 60*20;
   cell5.getRow().setHeight(heightUnits5);
   
   
   int widthUnits6 = 20*1000;
   sheet.setColumnWidth(6, widthUnits6);
   short heightUnits6 = 60*20;
   cell6.getRow().setHeight(heightUnits6);
   
   int widthUnits8 = 20*1000;
   sheet.setColumnWidth(8, widthUnits8);
   short heightUnits8 = 60*20;
   cell8.getRow().setHeight(heightUnits8);
   
   */
          

   //set width to n character widths = count characters * 256
   int widthUnitsbefore = 20*256;
   sheet.setColumnWidth(7, widthUnitsbefore);
   
   int widthUnitsafter = 20*256;
   sheet.setColumnWidth(9, widthUnitsafter);

   //set height to n points in twips = n * 20
   short heightUnitsbefore = 60*20;
   cellbefore.getRow().setHeight(heightUnitsbefore);
   
   short heightUnitsafter = 60*20;
   cellafter.getRow().setHeight(heightUnitsafter);
    }
    
    
    //image on excel
  //  ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//URL resource = classLoader.getResource("com/ridwan/ICON/logonutech.png");
    String file ="E:/FORM HARIAN IT & CALL CENTER/logonutech.png";
//File file = new File(resource.getPath());
   // File file = new File(file);
    InputStream inputStreamimage = new FileInputStream(file);
   //Get the contents of an InputStream as a byte[].
   byte[] bytes = IOUtils.toByteArray(inputStreamimage);
   //Adds a picture to the workbook
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
   anchor.setCol1(0); //Column B
   anchor.setRow1(0); //Row 3
anchor.setCol2(1); //Column C
anchor.setRow2(1); //Row 4

   //Creates a picture
   Picture pict = drawing.createPicture(anchor, pictureIdxbefore);

   //Reset the image to the original size
   //pict.resize(); //don't do that. Let the anchor resize the image!

   //Create the Cell picture value
          org.apache.poi.ss.usermodel.Cell cellimage = sheet.createRow(0).createCell(0);
           

   //set width to n character widths = count characters * 256
   int widthUnits = 20*400;
   sheet.setColumnWidth(0, widthUnits);
   //set height to n points in twips = n * 20
   short heightUnits = 60*15;
   cellimage.getRow().setHeight(heightUnits);
    

   //Write the Excel file
   FileOutputStream fileOut = null;
   fileOut = new FileOutputStream("E:/Dokumentasi Pemasangan Barang.xlsx");
   //JOptionPane.showMessageDialog(null, "Ok");
   wb.write(fileOut);
   fileOut.close();
   JOptionPane.showMessageDialog(null, "Dokumentasi Pemasangan Barang Berhasil DiExport");
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
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

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

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel4.setText("Dari Tanggal :");

        jPanel6.setBackground(new java.awt.Color(0, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ridwan/ICON/dokpoto.png"))); // NOI18N
        jLabel7.setText("Cetak Dokumentasi");
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
        jLabel8.setText("Export Dokumentasi Pemasangan Barang..");

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
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel6.setText("Sampai Tanggal :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(jLabel4)
                .addGap(28, 28, 28)
                .addComponent(txt_tglawal, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 834, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(42, 42, 42)
                .addComponent(txt_akhir, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109))
            .addGroup(layout.createSequentialGroup()
                .addGap(702, 702, 702)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel4)
                        .addComponent(txt_tglawal, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                        .addComponent(txt_akhir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 223, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(281, 281, 281)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_petugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_petugasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_petugasActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
    try {
        addgambartoexcel();
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(ExportDokumentasi.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(ExportDokumentasi.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel6MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private com.toedter.calendar.JDateChooser txt_akhir;
    private javax.swing.JTextField txt_petugas;
    private com.toedter.calendar.JDateChooser txt_tglawal;
    // End of variables declaration//GEN-END:variables
}
