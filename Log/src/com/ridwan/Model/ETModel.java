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
public class ETModel {
    private int NoPerangkat;
    private String NamaPerangkat;
    private String id;
    private String type;
    private StasiunModel stasiun;
    private boolean cek;

    public StasiunModel getStasiun() {
        return stasiun;
    }

    public void setStasiun(StasiunModel stasiun) {
        this.stasiun = stasiun;
    }

    
    
    
    public boolean isCek() {
        return cek;
    }

    public void setCek(boolean cek) {
        this.cek = cek;
    }
    
    

    public int getNoPerangkat() {
        return NoPerangkat;
    }

    public void setNoPerangkat(int NoPerangkat) {
        this.NoPerangkat = NoPerangkat;
    }

    public String getNamaPerangkat() {
        return NamaPerangkat;
    }

    public void setNamaPerangkat(String NamaPerangkat) {
        this.NamaPerangkat = NamaPerangkat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return NamaPerangkat ;
    }
   
    
    
}
