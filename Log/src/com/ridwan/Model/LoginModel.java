/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Model;

import java.sql.Timestamp;

/**
 *
 * @author Muhammad Ridwan
 */
public class LoginModel {
    private int nomer;
    private UserModel user;
    private Timestamp datelogin;
    private Timestamp datelogout;
    private String statuslogin;
      private String statuslogin2;

    public String getStatuslogin2() {
        return statuslogin2;
    }

    public void setStatuslogin2(String statuslogin2) {
        this.statuslogin2 = statuslogin2;
    }
      
      

    public int getNomer() {
        return nomer;
    }

    public void setNomer(int nomer) {
        this.nomer = nomer;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Timestamp getDatelogin() {
        return datelogin;
    }

    public void setDatelogin(Timestamp datelogin) {
        this.datelogin = datelogin;
    }

    public Timestamp getDatelogout() {
        return datelogout;
    }

    public void setDatelogout(Timestamp datelogout) {
        this.datelogout = datelogout;
    }

    public String getStatuslogin() {
        return statuslogin;
    }

    public void setStatuslogin(String statuslogin) {
        this.statuslogin = statuslogin;
    }

    
    
    
    
}
