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
public class UserModel {
    private int no;
    private String Nama;
    private String usr;
    private String pwd;
    private String alamat;
    private String tlp;
    private String status;
    private boolean cek;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    

    public boolean isCek() {
        return cek;
    }

    public void setCek(boolean cek) {
        this.cek = cek;
    }
    
    

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String Nama) {
        this.Nama = Nama;
    }

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTlp() {
        return tlp;
    }

    public void setTlp(String tlp) {
        this.tlp = tlp;
    }

    @Override
    public String toString() {
        return  Nama;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.no;
        hash = 59 * hash + Objects.hashCode(this.Nama);
        hash = 59 * hash + Objects.hashCode(this.usr);
        hash = 59 * hash + Objects.hashCode(this.pwd);
        hash = 59 * hash + Objects.hashCode(this.alamat);
        hash = 59 * hash + Objects.hashCode(this.tlp);
        hash = 59 * hash + Objects.hashCode(this.status);
        hash = 59 * hash + (this.cek ? 1 : 0);
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
        final UserModel other = (UserModel) obj;
        if (this.no != other.no) {
            return false;
        }
        if (!Objects.equals(this.Nama, other.Nama)) {
            return false;
        }
        if (!Objects.equals(this.usr, other.usr)) {
            return false;
        }
        if (!Objects.equals(this.pwd, other.pwd)) {
            return false;
        }
        if (!Objects.equals(this.alamat, other.alamat)) {
            return false;
        }
        if (!Objects.equals(this.tlp, other.tlp)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (this.cek != other.cek) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    
}
