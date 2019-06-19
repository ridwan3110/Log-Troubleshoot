/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Interface;

import com.ridwan.Model.UserModel;
import java.util.List;

/**
 *
 * @author ciwong
 */
public interface UserInterface {
    boolean insertUser (UserModel um);
    boolean updateUser (UserModel um);
    boolean deleteUser (UserModel um);
    List<UserModel> getUserModels();
    UserModel getusermodelById(int id);
    UserModel login (String username, String password);
    List<UserModel> getuserbyname(String Name);
    
     UserModel logout (String nama);
}
