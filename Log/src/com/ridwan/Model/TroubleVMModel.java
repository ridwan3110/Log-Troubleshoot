/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Model;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author ciwong
 */
public class TroubleVMModel {
   private String NoVM;
    private Date TglMasalah;
    private Time jamMasalah;
    private Date TglDone;
    private Time jamDone;
    private StasiunModel sm;
    private ETModel etm;
    private String problem;
    private String solusi;
    private PerangkatVMModel pvmm;
    private PenyebabModel pm;
   private String status;
   private String analisa;
  private UserModel um;
  private ProjectModel promvm;
  private boolean cek;

    public ProjectModel getPromvm() {
        return promvm;
    }

    public void setPromvm(ProjectModel promvm) {
        this.promvm = promvm;
    }
  
  

    public boolean isCek() {
        return cek;
    }

    public void setCek(boolean cek) {
        this.cek = cek;
    }

    public String getNoVM() {
        return NoVM;
    }

    public void setNoVM(String NoVM) {
        this.NoVM = NoVM;
    }
  
  

  

    public PerangkatVMModel getPvmm() {
        return pvmm;
    }

    public void setPvmm(PerangkatVMModel pvmm) {
        this.pvmm = pvmm;
    }

    public String getAnalisa() {
        return analisa;
    }

    public void setAnalisa(String analisa) {
        this.analisa = analisa;
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
