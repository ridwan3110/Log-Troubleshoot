/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Model;

/**
 *
 * @author ciwong
 */
public class PenyebabModel {
    private int NoPenyebab;
    private String NamaPenyebab;
    private String kategori;
    private PerangkatVMModel pvmmodel;    
    private boolean cek;

    public PerangkatVMModel getPvmmodel() {
        return pvmmodel;
    }

    public void setPvmmodel(PerangkatVMModel pvmmodel) {
        this.pvmmodel = pvmmodel;
    }
    
    

    public boolean isCek() {
        return cek;
    }

    public void setCek(boolean cek) {
        this.cek = cek;
    }
    
    

    public int getNoPenyebab() {
        return NoPenyebab;
    }

    public void setNoPenyebab(int NoPenyebab) {
        this.NoPenyebab = NoPenyebab;
    }

    public String getNamaPenyebab() {
        return NamaPenyebab;
    }

    public void setNamaPenyebab(String NamaPenyebab) {
        this.NamaPenyebab = NamaPenyebab;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    @Override
    public String toString() {
        return NamaPenyebab  ;
        
    }
    
    
}
