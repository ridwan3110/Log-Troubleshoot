/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Model;

import java.awt.Image;
import java.sql.Blob;
import java.sql.Time;
import java.util.Date;

/**
 *
 * @author ciwong
 */
public class TroubleEtModel {
    private String NoEt;
    private Date TglMasalah;
    private Time jamMasalah;
    private Date TglDone;
    private Time jamDone;
    private String JenisLaporan;
    private ProjectModel prom;
    private StasiunModel sm;
     private ETModel etm;
    private PerangkatVMModel part;
    private String problem;
    private PenyebabModel pm;
    private String solusi;
    // String Arah;
   private String status;
  private UserModel um;
  private String Sumber;
  private String Ref;
  private String Reftrouble;
  private String teknisi;
  
  
  
  private String Jenis;
  private String NamaPerangkat;
  private String NamaProjek;
  private String Status2;
  private int count;
  private String Namapenyebab;
  
  private boolean cek;
  
  
  private byte [] picbefore;
  private byte [] picafter;
  private String totalDowntime;
  private String arahgate;

    public String getArahgate() {
        return arahgate;
    }

    public void setArahgate(String arahgate) {
        this.arahgate = arahgate;
    }
  
  
  
  
  

    public String getJenis() {
        return Jenis;
    }

    public void setJenis(String Jenis) {
        this.Jenis = Jenis;
    }
  
 
  
  
  
  

  
  
  
    public String getTotalDowntime() {
        return totalDowntime;
    }

    public void setTotalDowntime(String totalDowntime) {
        this.totalDowntime = totalDowntime;
    }

   
  
  

    

   

   
  
  

    public byte[] getPicbefore() {
        return picbefore;
    }

    public void setPicbefore(byte[] picbefore) {
        this.picbefore = picbefore;
    }

    public byte[] getPicafter() {
        return picafter;
    }

    public void setPicafter(byte[] picafter) {
        this.picafter = picafter;
    }
  
  
  

    public String getNamapenyebab() {
        return Namapenyebab;
    }

    public void setNamapenyebab(String Namapenyebab) {
        this.Namapenyebab = Namapenyebab;
    }
  
  
  

    public String getNamaProjek() {
        return NamaProjek;
    }

    public void setNamaProjek(String NamaProjek) {
        this.NamaProjek = NamaProjek;
    }

    public String getStatus2() {
        return Status2;
    }

    public void setStatus2(String Status2) {
        this.Status2 = Status2;
    }
  

    public String getNamaPerangkat() {
        return NamaPerangkat;
    }

    public void setNamaPerangkat(String NamaPerangkat) {
        this.NamaPerangkat = NamaPerangkat;
    }

  
  
  
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getReftrouble() {
        return Reftrouble;
    }

    public void setReftrouble(String Reftrouble) {
        this.Reftrouble = Reftrouble;
    }

    public String getTeknisi() {
        return teknisi;
    }

    public void setTeknisi(String teknisi) {
        this.teknisi = teknisi;
    }
  
    
    
    
    
    
    
    
    
  

    public String getRef() {
        return Ref;
    }

    public void setRef(String Ref) {
        this.Ref = Ref;
    }
  
  
  

    public String getJenisLaporan() {
        return JenisLaporan;
    }

    public void setJenisLaporan(String JenisLaporan) {
        this.JenisLaporan = JenisLaporan;
    }

    public PerangkatVMModel getPart() {
        return part;
    }

    public void setPart(PerangkatVMModel part) {
        this.part = part;
    }

    public String getSumber() {
        return Sumber;
    }

    public void setSumber(String Sumber) {
        this.Sumber = Sumber;
    }
  
  
  

    public String getNoEt() {
        return NoEt;
    }

    public void setNoEt(String NoEt) {
        this.NoEt = NoEt;
    }
  
  
  

    public ProjectModel getProm() {
        return prom;
    }

    public void setProm(ProjectModel prom) {
        this.prom = prom;
    }
  
  

    public boolean isCek() {
        return cek;
    }

    public void setCek(boolean cek) {
        this.cek = cek;
    }
  

   

    public Date getTglMasalah() {
        return TglMasalah;
    }

    public void setTglMasalah(Date TglMasalah) {
        this.TglMasalah = TglMasalah;
    }

    public Time getJamMasalah() {
        return jamMasalah;
    }

    public void setJamMasalah(Time jamMasalah) {
        this.jamMasalah = jamMasalah;
    }

    public Date getTglDone() {
        return TglDone;
    }

    public void setTglDone(Date TglDone) {
        this.TglDone = TglDone;
    }

    public Time getJamDone() {
        return jamDone;
    }

    public void setJamDone(Time jamDone) {
        this.jamDone = jamDone;
    }

    public StasiunModel getSm() {
        return sm;
    }

    public void setSm(StasiunModel sm) {
        this.sm = sm;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public PenyebabModel getPm() {
        return pm;
    }

    public void setPm(PenyebabModel pm) {
        this.pm = pm;
    }

    public String getSolusi() {
        return solusi;
    }

    public void setSolusi(String solusi) {
        this.solusi = solusi;
    }

    public ETModel getEtm() {
        return etm;
    }

    public void setEtm(ETModel etm) {
        this.etm = etm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserModel getUm() {
        return um;
    }

    public void setUm(UserModel um) {
        this.um = um;
    }

 
    
    
  
}
