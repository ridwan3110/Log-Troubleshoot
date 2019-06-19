/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.DAO;

import com.ridwan.DBC.DBC;
import com.ridwan.Interface.UserInterface;
import com.ridwan.Model.UserModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ciwong
 */
public class UserDAo implements UserInterface {

    private Connection connection;

    public UserDAo() {
    connection = DBC.getConnection();
    }
    
    
    
    @Override
    public boolean insertUser(UserModel um) {
        boolean valid = false;
        PreparedStatement statement = null;
        String sql = "insert into user (No_User, Nama_User,Username, Password, Alamat, Telepon, Status) values (?,?,?,?,?,?,?)";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, um.getNo());
            statement.setString(2, um.getNama());
            statement.setString(3, um.getUsr());
            statement.setString(4, um.getPwd());
            statement.setString(5, um.getAlamat());
            statement.setString(6, um.getTlp());
            statement.setString(7, um.getStatus());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAo.class.getName()).log(Level.SEVERE, null, ex);
        valid = false;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    
    
    @Override
    public boolean updateUser(UserModel um) {
    boolean valid = false;
        PreparedStatement statement = null;
    String sql = "update user set Nama_User=?, Username=?, Password=?, Alamat=?, Telepon=?, Status=? where No_User=?";
    
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, um.getNama());
            statement.setString(2, um.getUsr());
            statement.setString(3, um.getPwd());
            statement.setString(4, um.getAlamat());
            statement.setString(5, um.getTlp());
            statement.setString(6, um.getStatus());
            statement.setInt(7, um.getNo());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAo.class.getName()).log(Level.SEVERE, null, ex);
        valid = false;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    return valid;
    }

    @Override
    public boolean deleteUser(UserModel um) {
    boolean valid = false;
        PreparedStatement statement = null;
    String sql = "delete from user where No_User=?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, um.getNo());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAo.class.getName()).log(Level.SEVERE, null, ex);
        valid = false;
        }finally {
             if (statement!=null){
                 try {
                     statement.close();
                 } catch (SQLException ex) {
                     Logger.getLogger(UserDAo.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
        }
        return valid;
    }

    @Override
    public List<UserModel> getUserModels() {
    List list =  new ArrayList();
    ResultSet rs  = null;
    PreparedStatement statement = null;
    String sql = "select * from user";
    
        try {
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()){
                UserModel um = new UserModel();
                um.setNo(rs.getInt("No_User"));
                um.setNama(rs.getString("Nama_User"));
                um.setUsr(rs.getString("Username"));
                um.setPwd(rs.getString("Password"));
                um.setAlamat(rs.getString("Alamat"));
                um.setTlp(rs.getString("Telepon"));
                um.setStatus(rs.getString("Status"));
                list.add(um);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAo.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    
    }

    @Override
    public UserModel getusermodelById(int id) {
    //List list =  new ArrayList();
    ResultSet rs  = null;
    PreparedStatement statement = null;
    UserModel um = null;
    String sql = "select * from user where No_User=?";
    
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()){
                um = new UserModel();
                um.setNo(rs.getInt("No_User"));
                um.setNama(rs.getString("Nama_User"));
                um.setUsr(rs.getString("Username"));
                um.setPwd(rs.getString("Password"));
                um.setAlamat(rs.getString("Alamat"));
                um.setTlp(rs.getString("Telepon"));
                um.setStatus(rs.getString("Status"));
                
            }return um;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAo.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }  
    }

    @Override
    public UserModel login(String username, String password) {
     PreparedStatement statement = null;
        ResultSet rs = null;
        UserModel k = null;
       String sql = "select * from user where Username=? and Password=?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            rs = statement.executeQuery();
            while (rs.next()){
                k = new UserModel();
                k.setNo(rs.getInt("No_User"));
                k.setNama(rs.getString("Nama_User"));
                k.setUsr(rs.getString("Username"));
                k.setPwd(rs.getString("Password"));
                k.setAlamat(rs.getString("Alamat"));
                k.setTlp(rs.getString("Telepon"));
                k.setStatus(rs.getString("Status"));
                
            }return k ;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAo.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAo.class.getName()).log(Level.SEVERE, null, ex);
                }
            } if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }  
    }

    @Override
    public List<UserModel> getuserbyname(String Name) {
    List list =  new ArrayList();
    ResultSet rs  = null;
    PreparedStatement statement = null;
    String sql = "select * from user where Nama_User=?";
    
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, Name);
            rs = statement.executeQuery();
            while (rs.next()){
                UserModel um = new UserModel();
                um.setNo(rs.getInt("No_User"));
                um.setNama(rs.getString("Nama_User"));
                um.setUsr(rs.getString("Username"));
                um.setPwd(rs.getString("Password"));
                um.setAlamat(rs.getString("Alamat"));
                um.setTlp(rs.getString("Telepon"));
                um.setStatus(rs.getString("Status"));
                list.add(um);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAo.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    
    }

    @Override
    public UserModel logout(String nama) {
     PreparedStatement statement = null;
        ResultSet rs = null;
        UserModel k = null;
       String sql = "select * from user where Nama_User=?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, nama);
            rs = statement.executeQuery();
            while (rs.next()){
                k = new UserModel();
                k.setNo(rs.getInt("No_User"));
                k.setNama(rs.getString("Nama_User"));
                k.setUsr(rs.getString("Username"));
                k.setPwd(rs.getString("Password"));
                k.setAlamat(rs.getString("Alamat"));
                k.setTlp(rs.getString("Telepon"));
                k.setStatus(rs.getString("Status"));
                
            }return k ;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAo.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAo.class.getName()).log(Level.SEVERE, null, ex);
                }
            } if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }    
    }
    
}
