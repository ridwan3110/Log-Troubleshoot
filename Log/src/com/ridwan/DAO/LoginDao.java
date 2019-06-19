/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.DAO;

import com.ridwan.DBC.DBC;
import com.ridwan.Interface.LoginInterface;
import com.ridwan.Model.LoginModel;
import com.ridwan.Model.UserModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Muhammad Ridwan
 */
public class LoginDao implements LoginInterface{

    private Connection connection;
    private LoginModel k;
    private UserDAo userDAo = new UserDAo();

    public LoginDao() {
    connection=DBC.getConnection();
    }
    
    
    
    
    @Override
    public boolean insertUser(LoginModel lm) {
    boolean valid = false;
        PreparedStatement statement = null;
        String sql = "insert into login (Nomer, No_User,d_login,d_logout,login_status) values (?,?,?,?,?)";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, lm.getNomer());
            statement.setInt(2, lm.getUser().getNo());
            statement.setTimestamp(3, lm.getDatelogin());
            statement.setTimestamp(4, lm.getDatelogout());
            statement.setString(5, lm.getStatuslogin());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        valid = false;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean updateUser(LoginModel lm) {
    boolean valid = false;
        PreparedStatement statement = null;
    String sql = "update login set d_logout=now(), login_status=? where No_User=? and login_status=?";
    
        try {
            statement = connection.prepareStatement(sql);
            //statement.setTimestamp(1, lm.getDatelogout());
            statement.setString(1, lm.getStatuslogin());
            statement.setInt(2, lm.getUser().getNo());
            statement.setString(3, lm.getStatuslogin2());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        valid = false;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    return valid; 
    }

    @Override
    public LoginModel getstatuslogin(String status) {
     PreparedStatement statement = null;
        ResultSet rs = null;
        LoginModel k = null;
       String sql = "select * from login where login_status=?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, status);
            rs = statement.executeQuery();
            while (rs.next()){
                k = new LoginModel();
                k.setNomer(rs.getInt("Nomer"));
                int byid = rs.getInt("No_User");
                UserModel userModel = userDAo.getusermodelById(byid);
                k.setUser(userModel);
                k.setDatelogin(rs.getTimestamp("d_login"));
                k.setDatelogout(rs.getTimestamp("d_logout"));
                k.setStatuslogin(rs.getString("login_status"));
                
            }return k ;
        } catch (SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            } if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }       
    }

   
    
}
