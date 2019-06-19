/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.DAO;

import com.ridwan.DBC.DBC;
import com.ridwan.Interface.StasiunInterface;
import com.ridwan.Model.StasiunModel;
import com.ridwan.Model.ProjectModel;
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
public class StasiunDao implements StasiunInterface{
    ProjekDao projekDao = new ProjekDao();
    
    Connection connection;

    public StasiunDao() {
    connection = DBC.getConnection();
    }
    
    

    @Override
    public boolean insertStasiun(StasiunModel sm) {
    boolean valid = false;
        PreparedStatement statement =  null;
        String sql = "insert into stasiun (IP,Nama_Stasiun, No_Projek) values (?,?,?)";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, sm.getIp());
            statement.setString(2, sm.getNamastasiun());
           statement.setInt(3, sm.getPm().getNoprojek());
            //statement.setString(3, sm.getProjek());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(StasiunDao.class.getName()).log(Level.SEVERE, null, ex);
        valid = false;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StasiunDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean updateStasiun(StasiunModel sm) {
        boolean valid = false;
        PreparedStatement statement = null;
        String sql = "update stasiun set Nama_Stasiun=?,  No_Projek=? where IP=? ";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, sm.getNamastasiun());
           statement.setInt(2, sm.getPm().getNoprojek());
           statement.setInt(3, sm.getIp());
           // statement.setString(2,sm.getProjek());
            statement.executeUpdate();
            valid = false;
        } catch (SQLException ex) {
            Logger.getLogger(StasiunDao.class.getName()).log(Level.SEVERE, null, ex);
        valid = false;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StasiunDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean deleteStasiun(StasiunModel sm) {
    boolean valid = false;
    PreparedStatement statement = null;
    String sql = "delete from stasiun where IP=?";
    
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,sm.getIp());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(StasiunDao.class.getName()).log(Level.SEVERE, null, ex);
        valid = false;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StasiunDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    return valid;
    }

    @Override
    public List<StasiunModel> getStasiunModels() {
    List list = new ArrayList();
        ResultSet rs = null;
        
        PreparedStatement statement = null;
        
        String sql = "select * from stasiun";
        
        try {
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()){
                StasiunModel sm = new StasiunModel();
                sm.setIp(rs.getInt("IP"));
                sm.setNamastasiun(rs.getString("Nama_Stasiun"));
                int byid = rs.getInt("No_Projek");
               ProjectModel byid1 = projekDao.getbyid(byid);
                sm.setPm(byid1);
                //sm.setProjek(rs.getString("Project"));
                list.add(sm);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(StasiunDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StasiunDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StasiunDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public StasiunModel getStasiunModel(int id) {
     List list = new ArrayList();
        ResultSet rs = null;
        StasiunModel sm = null;
        
        PreparedStatement statement = null;
        
        String sql = "select * from stasiun where IP=?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()){
                sm = new StasiunModel();
                sm.setIp(rs.getInt("IP"));
               sm.setNamastasiun(rs.getString("Nama_Stasiun"));
               int byid = rs.getInt("No_Projek");
               ProjectModel byid1 = projekDao.getbyid(byid);
                sm.setPm(byid1);
            }return sm;
        } catch (SQLException ex) {
            Logger.getLogger(StasiunDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StasiunDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StasiunDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }  
    }

    @Override
    public List<StasiunModel> getModelsByprojek(ProjectModel projek) {
    List list = new ArrayList();
    ResultSet rs =null;
    PreparedStatement statement = null;
     String sql = "select * from Stasiun where No_Projek=? order by Nama_Stasiun asc";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, projek.getNoprojek());
            rs = statement.executeQuery();
            while (rs.next()){
                StasiunModel sm = new StasiunModel();
                sm.setIp(rs.getInt("IP"));
               sm.setNamastasiun(rs.getString("Nama_Stasiun"));
               int byid = rs.getInt("No_Projek");
               ProjectModel byid1 = projekDao.getbyid(byid);
                sm.setPm(byid1);
                list.add(sm);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(StasiunDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StasiunDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StasiunDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
