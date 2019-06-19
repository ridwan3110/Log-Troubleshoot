/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.DAO;

import com.ridwan.DBC.DBC;
import com.ridwan.Interface.JobdeskInterface;
import com.ridwan.Model.ETModel;
import com.ridwan.Model.JobdeskModel;
import com.ridwan.Model.PenyebabModel;
import com.ridwan.Model.StasiunModel;
import com.ridwan.Model.UserModel;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ciwong
 */
public class AddjobdeskDao implements  JobdeskInterface{
    
    private Connection  connection;
    private StasiunDao stasiunDao = new StasiunDao();
    private PenyebabDao penyebabDao = new PenyebabDao();
    private ETDao eTDao = new ETDao();
    private UserDAo userDAo = new UserDAo();
            
    public AddjobdeskDao() {
    connection = DBC.getConnection();
    }
    
    
    

    @Override
    public boolean insertJD(JobdeskModel jm) {
        boolean valid = false;
        PreparedStatement  statement = null;
        String sql = "insert into jobdesk (No,Tanggal_Masalah,Jam_Masalah, Tanggal_Done, Jam_Done, IP, problem, No_Penyebab, solusi, No_Perangkat, Saldo_Sebelum, Saldo_Sesudah, status, No_User)"
                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, jm.getNoJd());
            statement.setDate(2, (Date) jm.getTglMasalah());
            statement.setTime(3, jm.getJamMasalah());
            statement.setDate(4, (Date) jm.getTglDone());
            statement.setTime(5, jm.getJamDone());
            statement.setInt(6, jm.getSm().getIp());
            statement.setString(7, jm.getProblem());
            statement.setInt(8, jm.getPm().getNoPenyebab());
            statement.setString(9, jm.getSolusi());
            statement.setInt(10, jm.getEtm().getNoPerangkat());
            statement.setString(11, jm.getSaldosblm());
            statement.setString(12, jm.getSaldossdh());
            statement.setString(13, jm.getStatus());
            statement.setInt(14, jm.getUm().getNo());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(AddjobdeskDao.class.getName()).log(Level.SEVERE, null, ex);
        valid = false;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AddjobdeskDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
        
    }

    @Override
    public boolean updateJD(JobdeskModel jm) {
    boolean valid = false;
    PreparedStatement statement = null;
    String sql = "insert into jobdesk set Tanggal_Masalah=?, Jam_Masalah=?, Tanggal_Done=?, Jam_Done=?, IP=?, problem=?, No_Penyebab=?, solusi=?, No_Perangkat=?, Saldo_Sebelum=?, Saldo_Sesudah=?, No_User=? where No=?";
    
        try {
            statement = connection.prepareStatement(sql);
            statement.setDate(1, (Date) jm.getTglMasalah());
            statement.setTime(2, jm.getJamMasalah());
            statement.setDate(3, (Date) jm.getTglDone());
            statement.setTime(4, jm.getJamDone());
            statement.setInt(5, jm.getSm().getIp());
            statement.setString(6, jm.getProblem());
            statement.setInt(7,jm.getPm().getNoPenyebab());
            statement.setString(8,jm.getSolusi());
            statement.setInt(9, jm.getEtm().getNoPerangkat());
            statement.setString(10, jm.getSaldosblm());
            statement.setString(11, jm.getSaldossdh());
            statement.setString(12, jm.getStatus());
            statement.setInt(13, jm.getUm().getNo());
            statement.setInt(14, jm.getNoJd());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(AddjobdeskDao.class.getName()).log(Level.SEVERE, null, ex);
        valid = false;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AddjobdeskDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean deleteJD(JobdeskModel jm) {
    boolean valid = false;
    PreparedStatement statement = null;
    String sql = "delete from jobdesk where No=?";
    
        try {
            statement= connection.prepareStatement(sql);
            statement.setInt(1, jm.getNoJd());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(AddjobdeskDao.class.getName()).log(Level.SEVERE, null, ex);
        valid = false;
        }finally {
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AddjobdeskDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public List<JobdeskModel> getJobdeskModels() {
    PreparedStatement statement = null;
    List list = new VirtualFlow.ArrayLinkedList();
        ResultSet rs =null;
    String sql = "select * from jobdesk";
    
        try {
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()){
                JobdeskModel jobdeskModel = new JobdeskModel();
                jobdeskModel.setNoJd(rs.getInt("No"));
                jobdeskModel.setTglMasalah(rs.getDate("Tanggal_Masalah"));
                jobdeskModel.setJamMasalah(rs.getTime("Jam_Masalah"));
                jobdeskModel.setTglDone(rs.getDate("Tanggal_Done"));
                jobdeskModel.setJamDone(rs.getTime("Jam_Done"));
                int stasiunid = rs.getInt("IP");
                StasiunModel stasiunModel = stasiunDao.getStasiunModel(stasiunid);
                jobdeskModel.setSm(stasiunModel);
                jobdeskModel.setProblem(rs.getString("problem"));
                int penyebabid = rs.getInt("No_Penyebab");
                PenyebabModel penyebabByid = penyebabDao.getPenyebabByid(stasiunid);
                jobdeskModel.setPm(penyebabByid);
                jobdeskModel.setSolusi(rs.getString("solusi"));
                int perangkatid = rs.getInt("No_Perangkat");
                ETModel etmodelByid = eTDao.getEtmodelByid(stasiunid);
                jobdeskModel.setEtm(etmodelByid);
                jobdeskModel.setSaldosblm(rs.getString("Saldo_Sebelum"));
                jobdeskModel.setSaldossdh(rs.getString("Saldo_Sesudah"));
                int userid = rs.getInt("No_User");
                UserModel usermodelById = userDAo.getusermodelById(userid);
                jobdeskModel.setUm(usermodelById);
                list.add(jobdeskModel);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(AddjobdeskDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AddjobdeskDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AddjobdeskDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    
    }
    
}
