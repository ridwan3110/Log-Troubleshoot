/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Model;

import java.util.Objects;

/**
 *
 * @author ciwong
 */
public class StasiunModel {
    private int ip;
    private String namastasiun;
    private ProjectModel pm;
    private String projek;
    private boolean cek;

    public String getProjek() {
        return projek;
    }

    public void setProjek(String projek) {
        this.projek = projek;
    }
    
    

    public boolean isCek() {
        return cek;
    }

    public void setCek(boolean cek) {
        this.cek = cek;
    }
    
    

    public int getIp() {
        return ip;
    }

    public void setIp(int ip) {
        this.ip = ip;
    }

    public String getNamastasiun() {
        return namastasiun;
    }

    public void setNamastasiun(String namastasiun) {
        this.namastasiun = namastasiun;
    }

    public ProjectModel getPm() {
        return pm;
    }

    public void setPm(ProjectModel pm) {
        this.pm = pm;
    }

    
    
  

    @Override
    public String toString() {
        return namastasiun ;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.ip;
        hash = 97 * hash + Objects.hashCode(this.namastasiun);
       hash = 97 * hash + Objects.hashCode(this.pm);
        hash = 97 * hash + (this.cek ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StasiunModel other = (StasiunModel) obj;
        if (this.ip != other.ip) {
            return false;
        }
        if (!Objects.equals(this.namastasiun, other.namastasiun)) {
            return false;
        }
       if (!Objects.equals(this.pm, other.pm)) {
        return false;
        }
        if (this.cek != other.cek) {
            return false;
        }
        return true;
    }
    
    
    
    
}
