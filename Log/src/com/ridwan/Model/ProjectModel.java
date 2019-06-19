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
public class ProjectModel {
    
    
    private int Noprojek;
    private String Namaprojek;
    private String init;
    private boolean cek;

    public String getInit() {
        return init;
    }

    public void setInit(String init) {
        this.init = init;
    }
    
    
    

    public int getNoprojek() {
        return Noprojek;
    }

    public void setNoprojek(int Noprojek) {
        this.Noprojek = Noprojek;
    }

    public String getNamaprojek() {
        return Namaprojek;
    }

    public void setNamaprojek(String Namaprojek) {
        this.Namaprojek = Namaprojek;
    }

    public boolean isCek() {
        return cek;
    }

    public void setCek(boolean cek) {
        this.cek = cek;
    }

    @Override
    public String toString() {
        return  Namaprojek ;
    }

   
    
    
    
    
}
