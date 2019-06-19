/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.DAO;

import com.ridwan.DBC.DBC;
import com.ridwan.Interface.TroubleVMInterface;
import com.ridwan.Model.ETModel;
import com.ridwan.Model.PenyebabModel;
import com.ridwan.Model.PerangkatVMModel;
import com.ridwan.Model.ProjectModel;
import com.ridwan.Model.StasiunModel;
import com.ridwan.Model.TroubleVMModel;
import com.ridwan.Model.UserModel;
import java.sql.Connection;
import java.sql.Date;
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
public class TroubleVmDao implements TroubleVMInterface{
    
    private Connection connection;
    private StasiunDao stasiunDao = new StasiunDao();
    private ETDao eTDao = new ETDao();
    private PerangkatVMDao perangkatVMDao = new PerangkatVMDao();
    private PenyebabDao penyebabDao = new PenyebabDao();
    private UserDAo userDAo = new UserDAo();
    private ProjekDao projekDao = new ProjekDao();
            
    public TroubleVmDao() {
    connection = DBC.getConnection();
    }
    
    

    @Override
    public boolean insertTroubleVM(TroubleVMModel tvmm) {
    boolean valid = false;
        PreparedStatement statement = null;
        String sql = "insert into trouble_vm (No, Tanggal_Masalah, Jam_Masalah, Tanggal_Done, Jam_Done, IP, No_Perangkat, problem, solusi, No_PVM, No_Penyebab, Status, Analisa, No_User, No_Projek)"
                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, tvmm.getNoVM());
            statement.setDate(2,  new java.sql.Date(tvmm.getTglMasalah().getTime()));
            statement.setTime(3, tvmm.getJamMasalah());
            statement.setDate(4, new java.sql.Date(tvmm.getTglDone().getTime()));
            statement.setTime(5, tvmm.getJamDone());
            statement.setInt(6, tvmm.getSm().getIp());
            statement.setInt(7, tvmm.getEtm().getNoPerangkat());
            statement.setString(8, tvmm.getProblem());
            statement.setString(9, tvmm.getSolusi());
            statement.setInt(10, tvmm.getPvmm().getNoPVM());
            statement.setInt(11,tvmm.getPm().getNoPenyebab());
            statement.setString(12, tvmm.getStatus());
            statement.setString(13, tvmm.getAnalisa());
            statement.setInt(14, tvmm.getUm().getNo());
            statement.setInt(15, tvmm.getPromvm().getNoprojek());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(TroubleVmDao.class.getName()).log(Level.SEVERE, null, ex);
        valid = false;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleVmDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean updateTroubleVM(TroubleVMModel tvmm) {
    boolean valid = false;
    PreparedStatement statement = null;
    String sql = "update trouble_vm set Tanggal_Masalah=?, Jam_Masalah=?, Tanggal_Done=?, Jam_Done=? , IP=?, No_Perangkat=?, problem=?, solusi=?, No_PVM=?, No_Penyebab=?, Status=?, analisa=?, No_User=?, No_Projek=? where No=?";
    
        try {
            statement=connection.prepareStatement(sql);
            statement.setDate(1, (Date) tvmm.getTglMasalah());
            statement.setTime(2, tvmm.getJamMasalah());
            statement.setDate(3, (Date) tvmm.getTglDone());
            statement.setTime(4, tvmm.getJamDone());
            statement.setInt(5,tvmm.getSm().getIp());
            statement.setInt(6, tvmm.getEtm().getNoPerangkat());
            statement.setString(7, tvmm.getProblem());
            statement.setString(8, tvmm.getSolusi());
            statement.setInt(9, tvmm.getPvmm().getNoPVM());
            statement.setInt(10,tvmm.getPm().getNoPenyebab());
            statement.setString(11, tvmm.getStatus());
            statement.setString(12, tvmm.getAnalisa());
            statement.setInt(13, tvmm.getUm().getNo());
            statement.setInt(14, tvmm.getPromvm().getNoprojek());
            statement.setString(15, tvmm.getNoVM());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(TroubleVmDao.class.getName()).log(Level.SEVERE, null, ex);
        valid = false;
        }finally {
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleVmDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean deleteTroubleVM(TroubleVMModel tvmm) {
    boolean valid = false;
    PreparedStatement  statement = null;
    
    String sql = "delete from trouble_vm where No=?";
    
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, tvmm.getNoVM());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(TroubleVmDao.class.getName()).log(Level.SEVERE, null, ex);
        valid = false;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleVmDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public List<TroubleVMModel> getTroubleVMModels() {
    PreparedStatement statement = null;
    List list = new ArrayList();
        ResultSet rs = null;
        String sql = "select * from trouble_vm limit 100";
        
        try {
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()){
                TroubleVMModel tvm = new TroubleVMModel();
                tvm.setNoVM(rs.getString("No"));
                tvm.setTglMasalah(rs.getDate("Tanggal_Masalah"));
                tvm.setJamMasalah(rs.getTime("Jam_Masalah"));
                tvm.setTglDone(rs.getDate("Tanggal_Done"));
                tvm.setJamDone(rs.getTime("Jam_Done"));
                int stasiunid = rs.getInt("IP");
                StasiunModel stasiunModel = stasiunDao.getStasiunModel(stasiunid);
                tvm.setSm(stasiunModel);
                int etid = rs.getInt("No_Perangkat");
                ETModel etmodelByid = eTDao.getEtmodelByid(etid);
                tvm.setEtm(etmodelByid);
                tvm.setProblem(rs.getString("problem"));
                tvm.setSolusi(rs.getString("solusi"));
                int pvmid= rs.getInt("No_PVM");
                PerangkatVMModel perangkatVMModelbyid = perangkatVMDao.perangkatVMModelbyid(pvmid);
                tvm.setPvmm(perangkatVMModelbyid);
                int penyebabid = rs.getInt("No_Penyebab");
                PenyebabModel penyebabByid = penyebabDao.getPenyebabByid(penyebabid);
                tvm.setPm(penyebabByid);
                tvm.setStatus(rs.getString("status"));
                tvm.setAnalisa(rs.getString("analisa"));
                int userid = rs.getInt("No_User");
                UserModel usermodelById = userDAo.getusermodelById(userid);
                tvm.setUm(usermodelById);
                int projekid = rs.getInt("No_Projek");
                ProjectModel projekbyid = projekDao.getbyid(projekid);
                tvm.setPromvm(projekbyid);
                list.add(tvm);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(TroubleVmDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleVmDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleVmDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<TroubleVMModel> getVMModelsByTanggal(java.util.Date tglnya) {
     PreparedStatement statement = null;
    List list = new ArrayList();
        ResultSet rs = null;
        String sql = "select * from trouble_vm where Tanggal_Done=? limit 100";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setDate(1, new java.sql.Date(tglnya.getTime()));
            rs = statement.executeQuery();
            while (rs.next()){
                TroubleVMModel tvm = new TroubleVMModel();
                tvm.setNoVM(rs.getString("No"));
                tvm.setTglMasalah(rs.getDate("Tanggal_Masalah"));
                tvm.setJamMasalah(rs.getTime("Jam_Masalah"));
                tvm.setTglDone(rs.getDate("Tanggal_Done"));
                tvm.setJamDone(rs.getTime("Jam_Done"));
                int stasiunid = rs.getInt("IP");
                StasiunModel stasiunModel = stasiunDao.getStasiunModel(stasiunid);
                tvm.setSm(stasiunModel);
                int etid = rs.getInt("No_Perangkat");
                ETModel etmodelByid = eTDao.getEtmodelByid(etid);
                tvm.setEtm(etmodelByid);
                tvm.setProblem(rs.getString("problem"));
                tvm.setSolusi(rs.getString("solusi"));
                int pvmid= rs.getInt("No_PVM");
                PerangkatVMModel perangkatVMModelbyid = perangkatVMDao.perangkatVMModelbyid(pvmid);
                tvm.setPvmm(perangkatVMModelbyid);
                int penyebabid = rs.getInt("No_Penyebab");
                PenyebabModel penyebabByid = penyebabDao.getPenyebabByid(penyebabid);
                tvm.setPm(penyebabByid);
                tvm.setStatus(rs.getString("status"));
                tvm.setAnalisa(rs.getString("analisa"));
                int userid = rs.getInt("No_User");
                UserModel usermodelById = userDAo.getusermodelById(userid);
                tvm.setUm(usermodelById);
                int projekid = rs.getInt("No_Projek");
                ProjectModel projekbyid = projekDao.getbyid(projekid);
                tvm.setPromvm(projekbyid);
                list.add(tvm);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(TroubleVmDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleVmDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleVmDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }   
    
    }

    @Override
    public List<TroubleVMModel> getVMModelsBypenyebab(PenyebabModel penyebab) {
     PreparedStatement statement = null;
    List list = new ArrayList();
        ResultSet rs = null;
        String sql = "select * from trouble_vm where No_Penyebab=? limit 100";
        
        try {
            statement = connection.prepareStatement(sql);
             statement.setInt(1, penyebab.getNoPenyebab());
            rs = statement.executeQuery();
            while (rs.next()){
                TroubleVMModel tvm = new TroubleVMModel();
                tvm.setNoVM(rs.getString("No"));
                tvm.setTglMasalah(rs.getDate("Tanggal_Masalah"));
                tvm.setJamMasalah(rs.getTime("Jam_Masalah"));
                tvm.setTglDone(rs.getDate("Tanggal_Done"));
                tvm.setJamDone(rs.getTime("Jam_Done"));
                int stasiunid = rs.getInt("IP");
                StasiunModel stasiunModel = stasiunDao.getStasiunModel(stasiunid);
                tvm.setSm(stasiunModel);
                int etid = rs.getInt("No_Perangkat");
                ETModel etmodelByid = eTDao.getEtmodelByid(etid);
                tvm.setEtm(etmodelByid);
                tvm.setProblem(rs.getString("problem"));
                tvm.setSolusi(rs.getString("solusi"));
                int pvmid= rs.getInt("No_PVM");
                PerangkatVMModel perangkatVMModelbyid = perangkatVMDao.perangkatVMModelbyid(pvmid);
                tvm.setPvmm(perangkatVMModelbyid);
                int penyebabid = rs.getInt("No_Penyebab");
                PenyebabModel penyebabByid = penyebabDao.getPenyebabByid(penyebabid);
                tvm.setPm(penyebabByid);
                tvm.setStatus(rs.getString("status"));
                tvm.setAnalisa(rs.getString("analisa"));
                int userid = rs.getInt("No_User");
                UserModel usermodelById = userDAo.getusermodelById(userid);
                tvm.setUm(usermodelById);
                int projekid = rs.getInt("No_Projek");
                ProjectModel projekbyid = projekDao.getbyid(projekid);
                tvm.setPromvm(projekbyid);
                list.add(tvm);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(TroubleVmDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleVmDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleVmDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }     
    }

    @Override
    public String setAutoNumberVM() {
     PreparedStatement statement=null;
      ResultSet rs=null;
      String kode=null;
      String s, s1;
      Integer j;
      Integer panjang = 1;
      String sql="select max(right(No, 1)) from trouble_vm";
        try {
            statement=connection.prepareStatement(sql);
            rs=statement.executeQuery();
            if(rs.first()==false){
                kode="1";
            }else{
                rs.last();
                s=Integer.toString(rs.getInt(1)+1);
                j=s.length();
                s1="";
                for(int i=0; i<=panjang-j; i++){
                    s1=s1;
                }
                return kode=s1+s;
            }
            return kode;
        } catch (SQLException ex) {
            Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }  
    }  
    
    }

