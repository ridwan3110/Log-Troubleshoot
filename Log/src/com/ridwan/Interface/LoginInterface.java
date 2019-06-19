/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Interface;

import com.ridwan.Model.LoginModel;

/**
 *
 * @author Muhammad Ridwan
 */
public interface LoginInterface {
     boolean insertUser (LoginModel lm);
    boolean updateUser (LoginModel lm);
    LoginModel getstatuslogin (String status);
    
}
