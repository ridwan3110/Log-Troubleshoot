/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Interface;

import com.ridwan.Model.PenyebabModel;
import com.ridwan.Model.ProjectModel;
import com.ridwan.Model.TroubleEtModel;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ciwong
 */
public interface TroubleETInterface {
    boolean insertTroubleETPermasalahan (TroubleEtModel tem);
     boolean insertTroubleETPekerjaan (TroubleEtModel tem);
     boolean insertTroubleETAduan (TroubleEtModel tem);
    boolean updateTroubleET (TroubleEtModel tem);
    boolean deleteTroubleET (TroubleEtModel tem);
    
    //query count downtime
   List<TroubleEtModel> getDowntime(String Akhirdowntime, String Awaldowntime, String Akhirdowntime1, String Awaldowntime1, String Akhirdowntime2, String Awaldowntime2);
    List<TroubleEtModel> getDowntimeparah(String haridone, String harimasalah); 
    //by 1 filter
    
    List<TroubleEtModel> getEtModels();
    List<TroubleEtModel> getEtModelsByTanggal(Date tglnya);
    List<TroubleEtModel> getEtModelsByStatusandDate(String status, Date tglnya);
    List<TroubleEtModel> getEtModelsByNoticket(String Notiket);
    List<TroubleEtModel> getEtModelsBypenyebab(PenyebabModel penyebab);
     List<TroubleEtModel> getEtModelsbystatus(String status);
       
     
     //for export
     
     List<TroubleEtModel> getExport(ProjectModel projek,Date tglMulai, Date tglAkhir);
      List<TroubleEtModel> getEtExportStatusCC(Date tglMulai, Date tglAkhir);
       List<TroubleEtModel> getEtExportStatusIT(Date tglMulai, Date tglAkhir);
      List<TroubleEtModel> getEtExportStatussifmalam(String Jenislaporan,Date tglMulai,String Timemalamawal, Date tglAkhir, String Timemalamakhir);
      
      List<TroubleEtModel> getexportgrafikPerPerangkat (String Projek,Date tglMulai, Date tglAkhir);
      List<TroubleEtModel> getexportgrafikperpenyebab (String Projek,Date tglMulai, Date tglAkhir);
      
      
      List<TroubleEtModel> getcountandalldata (String Status,String jenislaporan,Date tglMulai, Date tglAkhir);
      
      
      //for view 2 filter atau lebih 
      
      
      List<TroubleEtModel> getEtModelsBytgl2andstatus(Date tglMulai, Date tglAkhir, String Status);
       List<TroubleEtModel> getEtModelsBypenyebabAndPojek(String Projek,Date tglMulai, Date tglAkhir);
       List<TroubleEtModel> getEtModelsByDate2(Date tglawal, Date tglakhir);
        List<TroubleEtModel> forexportandview( String status, ProjectModel projek,Date tglawal, Date tglakhir);
      
        
        
     
     String setautonumber(ProjectModel projek);
     
     
     
     //report
     List<TroubleEtModel> getEtModelsReport(Date tglawal, Date tglakhir, String Status);
    
     
     //getmaxidtroubleetfrmuntuk kirim email
    
    
     
     
}
