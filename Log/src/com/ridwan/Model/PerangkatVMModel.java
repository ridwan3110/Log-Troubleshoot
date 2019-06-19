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
public class PerangkatVMModel {
    
    private int NoPVM;
    private String NamaPerangkat;
    private String jenis;
    private boolean cek;

    public int getNoPVM() {
        return NoPVM;
    }

    public void setNoPVM(int NoPVM) {
        this.NoPVM = NoPVM;
    }

    public String getNamaPerangkat() {
        return NamaPerangkat;
    }

    public void setNamaPerangkat(String NamaPerangkat) {
        this.NamaPerangkat = NamaPerangkat;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public boolean isCek() {
        return cek;
    }

    public void setCek(boolean cek) {
        this.cek = cek;
    }

    @Override
    public String toString() {
        return  NamaPerangkat;
    }

    
    
    
    
}
