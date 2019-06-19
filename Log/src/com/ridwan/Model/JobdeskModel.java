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
public class JobdeskModel {
     private int NoJd;
    private Date TglMasalah;
    private Time jamMasalah;
    private Date TglDone;
    private Time jamDone;
    private StasiunModel sm;
    private String problem;
    private PenyebabModel pm;
    private String solusi;
    private ETModel etm;
    private String Saldosblm;
    private String Saldossdh;
   private String status;
  private UserModel um;
  private boolean cek;

    public boolean isCek() {
        return cek;
    }

    public void setCek(boolean cek) {
        this.cek = cek;
    }
  
  

    public int getNoJd() {
        return NoJd;
    }

    public void setNoJd(int NoJd) {
        this.NoJd = NoJd;
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

    public String getSaldosblm() {
        return Saldosblm;
    }

    public void setSaldosblm(String Saldosblm) {
        this.Saldosblm = Saldosblm;
    }

    public String getSaldossdh() {
        return Saldossdh;
    }

    public void setSaldossdh(String Saldossdh) {
        this.Saldossdh = Saldossdh;
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
