/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.DAO;

import com.ridwan.DBC.DBC;
import com.ridwan.Interface.TroubleETInterface;
import com.ridwan.Model.ETModel;
import com.ridwan.Model.PenyebabModel;
import com.ridwan.Model.PerangkatVMModel;
import com.ridwan.Model.ProjectModel;
import com.ridwan.Model.StasiunModel;
import com.ridwan.Model.TroubleEtModel;
import com.ridwan.Model.UserModel;
import java.awt.Image;
//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ciwong
 */
public class TroubleETDao implements TroubleETInterface {
    
     Date d = new Date();  
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");  
       String tanggal = dateFormat.format(d);
            
       
    
    private Connection connection;
    
    private StasiunDao stasiunDao = new StasiunDao();
    private PenyebabDao penyebabDao = new PenyebabDao();
    private ETDao eTDao = new ETDao();
    private UserDAo userDAo = new UserDAo();
    private ProjekDao projekDao = new ProjekDao();
    private PerangkatVMDao partdao = new PerangkatVMDao();
    
    
    
   
    
    public TroubleETDao() {
    connection = DBC.getConnection();
    }
    
    

    @Override
    public boolean insertTroubleETPermasalahan(TroubleEtModel tem) {
    boolean valid = false;
        PreparedStatement statement = null;
        String sql = "insert into trouble_et (No,Tanggal_Masalah,Jam_Masalah,Tanggal_Done, Jam_Done, IP, Problem, No_Penyebab, Solusi, No_Perangkat, status, No_User, No_Projek, JenisLaporan, No_PVM, Sumber, RefnoTrouble, Teknisi, totaldowntime, Arah_gate)"
                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, tem.getNoEt());
            statement.setDate(2,  new java.sql.Date(tem.getTglMasalah().getTime()));
            statement.setTime(3, tem.getJamMasalah());
            statement.setDate(4, new java.sql.Date(tem.getTglDone().getTime()));
            statement.setTime(5, tem.getJamDone());
            statement.setInt(6,tem.getSm().getIp());
            statement.setString(7, tem.getProblem());
            statement.setInt(8, tem.getPm().getNoPenyebab());
            statement.setString(9, tem.getSolusi());
            statement.setInt(10, tem.getEtm().getNoPerangkat());
         //   statement.setString(11,tem.getArah());
            statement.setString(11, tem.getStatus());
            statement.setInt(12, tem.getUm().getNo());
            statement.setInt(13, tem.getProm().getNoprojek());
            statement.setString(14, tem.getJenisLaporan());
            statement.setInt(15, tem.getPart().getNoPVM());
            statement.setString(16, tem.getSumber());
            statement.setString(17, tem.getReftrouble());
            statement.setString(18, tem.getTeknisi());
            //statement.setBytes(19, tem.getPicbefore());
            statement.setString(19, tem.getTotalDowntime());
            statement.setString(20, tem.getArahgate());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
        valid = false;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean updateTroubleET(TroubleEtModel tem) {
    boolean valid = false;
        PreparedStatement statement = null;
    String sql = "update trouble_et set  Tanggal_Done=?, Jam_Done=?, problem=?, Sumber=? , solusi=?, Status=?, RefNumber=?, Teknisi=?, No_User=?, pic_before=?, pic_after=?, totaldowntime=? where No=?";
   
        try {
            statement = connection.prepareStatement(sql);
            statement.setDate(1,new java.sql.Date(tem.getTglDone().getTime()));
            statement.setTime(2, tem.getJamDone());
            statement.setString(3, tem.getProblem());
            statement.setString(4,tem.getSumber());
            statement.setString(5, tem.getSolusi());
             statement.setString(6, tem.getStatus());
             statement.setString(7, tem.getRef());
             statement.setString(8, tem.getTeknisi());
             statement.setInt(9, tem.getUm().getNo());
             statement.setBytes(10, tem.getPicbefore());
             statement.setBytes(11, tem.getPicafter());
             statement.setString(12, tem.getTotalDowntime());
          statement.setString(13, tem.getNoEt());
          
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
        valid = false;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean deleteTroubleET(TroubleEtModel tem) {
    boolean valid = false;
    PreparedStatement statement = null;
    String sql = "delete from trouble_et where No=?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, tem.getNoEt());
            statement.executeUpdate();
            valid = true;
            } catch (SQLException ex) {
            Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
            valid = false;
            }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public List<TroubleEtModel> getEtModels() {
    List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement  statement = null;
        String sql = "select No,Tanggal_Done,Jam_Done,JenisLaporan,No_Projek,IP,No_Perangkat,No_PVM,Problem,No_Penyebab, Solusi,Status, totaldowntime,RefNumber, count(*) from trouble_et where Tanggal_Done > CURRENT_DATE - interval 3 month group by No_Penyebab order by COUNT(*) desc limit 200";
        
        try {
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()){
                TroubleEtModel model = new TroubleEtModel();
                model.setNoEt(rs.getString("No"));
               // model.setTglMasalah(rs.getDate("Tanggal_Masalah"));
               // model.setJamMasalah(rs.getTime("Jam_Masalah"));
                model.setTglDone(rs.getDate("Tanggal_Done"));
                model.setJamDone(rs.getTime("Jam_Done"));
                int stasiunid = rs.getInt("IP");
                StasiunModel stasiunModel = stasiunDao.getStasiunModel(stasiunid);
                model.setSm(stasiunModel);
                model.setProblem(rs.getString("problem"));
                int penyebabid = rs.getInt("No_Penyebab");
                PenyebabModel penyebabByid = penyebabDao.getPenyebabByid(penyebabid);
                model.setPm(penyebabByid);
                model.setSolusi(rs.getString("Solusi"));
                int etid = rs.getInt("No_Perangkat");
                ETModel etmodelByid = eTDao.getEtmodelByid(etid);
                model.setEtm(etmodelByid);
               // model.setArah(rs.getString("Arah_Gate"));
                model.setStatus(rs.getString("Status"));
               // int userid = rs.getInt("No_User");
               // UserModel usermodelById = userDAo.getusermodelById(userid);
                //model.setUm(usermodelById);
                 int projekid = rs.getInt("No_Projek");
                ProjectModel projekmodelbyid = projekDao.getbyid(projekid);
                model.setProm(projekmodelbyid);
                model.setJenisLaporan(rs.getString("JenisLaporan"));
                 int partid = rs.getInt("No_PVM");
                PerangkatVMModel perangkatVMModelbyid = partdao.perangkatVMModelbyid(partid);
                model.setPart(perangkatVMModelbyid);
               // model.setSumber(rs.getString("Sumber"));
                model.setRef(rs.getString("RefNumber"));
               // model.setReftrouble(rs.getString("RefnoTrouble"));
                //model.setTeknisi(rs.getString("Teknisi"));
                //model.setPicbefore(rs.getBytes("pic_before"));
                //model.setPicafter(rs.getBytes("pic_after"));
                model.setTotalDowntime((rs.getString("totaldowntime")));
               // model.setArahgate(rs.getString("Arah_gate"));
                list.add(model);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<TroubleEtModel> getEtModelsByTanggal(java.util.Date tglnya) {
        List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement  statement = null;
        String sql = "select * from trouble_et where Tanggal_Done=?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setDate(1, new java.sql.Date(tglnya.getTime()));
            rs = statement.executeQuery();
            while (rs.next()){
                TroubleEtModel model = new TroubleEtModel();
                model.setNoEt(rs.getString("No"));
                model.setTglMasalah(rs.getDate("Tanggal_Masalah"));
                model.setJamMasalah(rs.getTime("Jam_Masalah"));
                model.setTglDone(rs.getDate("Tanggal_Done"));
                model.setJamDone(rs.getTime("Jam_Done"));
                int stasiunid = rs.getInt("IP");
                StasiunModel stasiunModel = stasiunDao.getStasiunModel(stasiunid);
                model.setSm(stasiunModel);
                model.setProblem(rs.getString("problem"));
                int penyebabid = rs.getInt("No_Penyebab");
                PenyebabModel penyebabByid = penyebabDao.getPenyebabByid(penyebabid);
                model.setPm(penyebabByid);
                model.setSolusi(rs.getString("Solusi"));
                int etid = rs.getInt("No_Perangkat");
                ETModel etmodelByid = eTDao.getEtmodelByid(etid);
                model.setEtm(etmodelByid);
                //model.setArah(rs.getString("Arah_Gate"));
                model.setStatus(rs.getString("Status"));
                int userid = rs.getInt("No_User");
                UserModel usermodelById = userDAo.getusermodelById(userid);
                model.setUm(usermodelById);
                int projekid = rs.getInt("No_Projek");
                ProjectModel projekmodelbyid = projekDao.getbyid(projekid);
                model.setProm(projekmodelbyid);
                  model.setJenisLaporan(rs.getString("JenisLaporan"));
                 int partid = rs.getInt("No_PVM");
                PerangkatVMModel perangkatVMModelbyid = partdao.perangkatVMModelbyid(partid);
                model.setPart(perangkatVMModelbyid);
                model.setSumber(rs.getString("Sumber"));
                   model.setRef(rs.getString("RefNumber"));
                    model.setReftrouble(rs.getString("RefnoTrouble"));
                model.setTeknisi(rs.getString("Teknisi"));
                 model.setPicbefore(rs.getBytes("pic_before"));
                model.setPicafter(rs.getBytes("pic_after"));
                model.setTotalDowntime((rs.getString("totaldowntime")));
                model.setArahgate(rs.getString("Arah_gate"));
                list.add(model);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }

    @Override
    public List<TroubleEtModel> getEtModelsBypenyebab(PenyebabModel penyebab) {
    List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement  statement = null;
        String sql = "select * from trouble_et where No_Penyebab=? limit 100";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, penyebab.getNoPenyebab());
            rs = statement.executeQuery();
            while (rs.next()){
                TroubleEtModel model = new TroubleEtModel();
                model.setNoEt(rs.getString("No"));
                model.setTglMasalah(rs.getDate("Tanggal_Masalah"));
                model.setJamMasalah(rs.getTime("Jam_Masalah"));
                model.setTglDone(rs.getDate("Tanggal_Done"));
                model.setJamDone(rs.getTime("Jam_Done"));
                int stasiunid = rs.getInt("IP");
                StasiunModel stasiunModel = stasiunDao.getStasiunModel(stasiunid);
                model.setSm(stasiunModel);
                model.setProblem(rs.getString("problem"));
                int penyebabid = rs.getInt("No_Penyebab");
                PenyebabModel penyebabByid = penyebabDao.getPenyebabByid(penyebabid);
                model.setPm(penyebabByid);
                model.setSolusi(rs.getString("Solusi"));
                int etid = rs.getInt("No_Perangkat");
                ETModel etmodelByid = eTDao.getEtmodelByid(etid);
                model.setEtm(etmodelByid);
               // model.setArah(rs.getString("Arah_Gate"));
                model.setStatus(rs.getString("Status"));
                int userid = rs.getInt("No_User");
                UserModel usermodelById = userDAo.getusermodelById(userid);
                model.setUm(usermodelById);
                int projekid = rs.getInt("No_Projek");
                ProjectModel projekmodelbyid = projekDao.getbyid(projekid);
                model.setProm(projekmodelbyid);
                  model.setJenisLaporan(rs.getString("JenisLaporan"));
                 int partid = rs.getInt("No_PVM");
                PerangkatVMModel perangkatVMModelbyid = partdao.perangkatVMModelbyid(partid);
                model.setPart(perangkatVMModelbyid);
                model.setSumber(rs.getString("Sumber"));
                   model.setRef(rs.getString("RefNumber"));
                    model.setReftrouble(rs.getString("RefnoTrouble"));
                model.setTeknisi(rs.getString("Teknisi"));
                  model.setPicbefore(rs.getBytes("pic_before"));
                model.setPicafter(rs.getBytes("pic_after"));
                model.setTotalDowntime((rs.getString("totaldowntime")));
                model.setArahgate(rs.getString("Arah_gate"));
                list.add(model);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }

   

    @Override
    public boolean insertTroubleETPekerjaan(TroubleEtModel tem) {
    boolean valid = false;
        PreparedStatement statement = null;
        String sql = "insert into trouble_et (No,Tanggal_Masalah,Jam_Masalah,Tanggal_Done, Jam_Done, IP, Problem, No_Penyebab, Solusi, No_Perangkat, status, No_User, No_Projek, JenisLaporan, No_PVM, Sumber, RefnoTrouble, Teknisi, totaldowntime)"
                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, tem.getNoEt());
            statement.setDate(2,  new java.sql.Date(tem.getTglMasalah().getTime()));
            statement.setTime(3, tem.getJamMasalah());
            statement.setDate(4, new java.sql.Date(tem.getTglDone().getTime()));
            statement.setTime(5, tem.getJamDone());
            statement.setInt(6,tem.getSm().getIp());
            statement.setString(7, tem.getProblem());
            statement.setObject(8, tem.getPm());
            statement.setString(9, tem.getSolusi());
            statement.setInt(10, tem.getEtm().getNoPerangkat());
         //   statement.setString(11,tem.getArah());
            statement.setString(11, tem.getStatus());
            statement.setInt(12, tem.getUm().getNo());
            statement.setInt(13, tem.getProm().getNoprojek());
            statement.setString(14, tem.getJenisLaporan());
            statement.setObject(15, tem.getPart());
            statement.setString(16, tem.getSumber());
            statement.setString(17, tem.getReftrouble());
            statement.setString(18, tem.getTeknisi());
            statement.setString(19, tem.getTotalDowntime());
            
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
        valid = false;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid; 
    
    }

    @Override
    public boolean insertTroubleETAduan(TroubleEtModel tem) {
    boolean valid = false;
        PreparedStatement statement = null;
        String sql = "insert into trouble_et (No,Tanggal_Masalah,Jam_Masalah,Tanggal_Done, Jam_Done, IP, Problem, No_Penyebab, Solusi, No_Perangkat, status, No_User, No_Projek, JenisLaporan, No_PVM, Sumber, RefnoTrouble, Teknisi, totaldowntime)"
                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, tem.getNoEt());
            statement.setDate(2,  new java.sql.Date(tem.getTglMasalah().getTime()));
            statement.setTime(3, tem.getJamMasalah());
            statement.setDate(4, new java.sql.Date(tem.getTglDone().getTime()));
            statement.setTime(5, tem.getJamDone());
            statement.setInt(6,tem.getSm().getIp());
            statement.setString(7, tem.getProblem());
            statement.setObject(8, tem.getPm());
            statement.setString(9, tem.getSolusi());
            statement.setInt(10, tem.getEtm().getNoPerangkat());
         //   statement.setString(11,tem.getArah());
            statement.setString(11, tem.getStatus());
            statement.setInt(12, tem.getUm().getNo());
            statement.setInt(13, tem.getProm().getNoprojek());
            statement.setString(14, tem.getJenisLaporan());
            statement.setObject(15, tem.getPart());
            statement.setString(16, tem.getSumber());
            statement.setString(17, tem.getReftrouble());
            statement.setString(18, tem.getTeknisi());
            statement.setString(19, tem.getTotalDowntime());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
        valid = false;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid; 
    
        
    }

    @Override
    public List<TroubleEtModel> getEtModelsBypenyebabAndPojek(String Projek,Date tglMulai, Date tglAkhir) {
    List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement  statement = null;
        String sql = "select perangkat_vm.Nama_Perangkat, count(*) from trouble_et inner JOIN perangkat_vm on (trouble_et.No_PVM=perangkat_vm.No_PVM) join projek on (trouble_et.No_Projek=projek.No_Projek) where projek.Nama_Projek=? and trouble_et.Tanggal_Done>=? and trouble_et.Tanggal_Done<=? group by perangkat_vm.Nama_Perangkat";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, Projek);
            statement.setDate(2, new java.sql.Date(tglMulai.getTime()));
            statement.setDate(3, new java.sql.Date(tglAkhir.getTime()));
            rs = statement.executeQuery();
            while (rs.next()){
                TroubleEtModel model = new TroubleEtModel();
                /*model.setNoEt(rs.getString("No"));
                model.setTglMasalah(rs.getDate("Tanggal_Masalah"));
                model.setJamMasalah(rs.getTime("Jam_Masalah"));
                model.setTglDone(rs.getDate("Tanggal_Done"));
                model.setJamDone(rs.getTime("Jam_Done"));
                int stasiunid = rs.getInt("IP");
                StasiunModel stasiunModel = stasiunDao.getStasiunModel(stasiunid);
                model.setSm(stasiunModel);
                model.setProblem(rs.getString("problem"));
                int penyebabid = rs.getInt("No_Penyebab");
                PenyebabModel penyebabByid = penyebabDao.getPenyebabByid(penyebabid);
                model.setPm(penyebabByid);
                model.setSolusi(rs.getString("Solusi"));
                int etid = rs.getInt("No_Perangkat");
                ETModel etmodelByid = eTDao.getEtmodelByid(etid);
                model.setEtm(etmodelByid);
               // model.setArah(rs.getString("Arah_Gate"));
                model.setStatus(rs.getString("Status"));
                int userid = rs.getInt("No_User");
                UserModel usermodelById = userDAo.getusermodelById(userid);
                model.setUm(usermodelById);
                int projekid = rs.getInt("No_Projek");
                ProjectModel projekmodelbyid = projekDao.getbyid(projekid);
                model.setProm(projekmodelbyid);
                  model.setJenisLaporan(rs.getString("JenisLaporan"));
                 int partid = rs.getInt("No_PVM");
                PerangkatVMModel perangkatVMModelbyid = partdao.perangkatVMModelbyid(etid);
                model.setPart(perangkatVMModelbyid);
                model.setSumber(rs.getString("Sumber"));
                   model.setRef(rs.getString("RefNumber"));*/
                model.setNamaPerangkat(rs.getString("Nama_Perangkat"));
                   model.setCount(rs.getInt("count(*)"));
                list.add(model);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }  
    }

    @Override
    public List<TroubleEtModel> getEtModelsbystatus(String status) {
        List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement  statement = null;
        String sql = "select * from trouble_et where status=?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, status);
            rs = statement.executeQuery();
            while (rs.next()){
                TroubleEtModel model = new TroubleEtModel();
                model.setNoEt(rs.getString("No"));
                model.setTglMasalah(rs.getDate("Tanggal_Masalah"));
                model.setJamMasalah(rs.getTime("Jam_Masalah"));
                model.setTglDone(rs.getDate("Tanggal_Done"));
                model.setJamDone(rs.getTime("Jam_Done"));
                int stasiunid = rs.getInt("IP");
                StasiunModel stasiunModel = stasiunDao.getStasiunModel(stasiunid);
                model.setSm(stasiunModel);
                model.setProblem(rs.getString("problem"));
                int penyebabid = rs.getInt("No_Penyebab");
                PenyebabModel penyebabByid = penyebabDao.getPenyebabByid(penyebabid);
                model.setPm(penyebabByid);
                model.setSolusi(rs.getString("Solusi"));
                int etid = rs.getInt("No_Perangkat");
                ETModel etmodelByid = eTDao.getEtmodelByid(etid);
                model.setEtm(etmodelByid);
               // model.setArah(rs.getString("Arah_Gate"));
                model.setStatus(rs.getString("Status"));
                int userid = rs.getInt("No_User");
                UserModel usermodelById = userDAo.getusermodelById(userid);
                model.setUm(usermodelById);
                int projekid = rs.getInt("No_Projek");
                ProjectModel projekmodelbyid = projekDao.getbyid(projekid);
                model.setProm(projekmodelbyid);
                  model.setJenisLaporan(rs.getString("JenisLaporan"));
                 int partid = rs.getInt("No_PVM");
                PerangkatVMModel perangkatVMModelbyid = partdao.perangkatVMModelbyid(partid);
                model.setPart(perangkatVMModelbyid);
                model.setSumber(rs.getString("Sumber"));
                   model.setRef(rs.getString("RefNumber"));
                    model.setReftrouble(rs.getString("RefnoTrouble"));
                model.setTeknisi(rs.getString("Teknisi"));
                 model.setPicbefore(rs.getBytes("pic_before"));
                model.setPicafter(rs.getBytes("pic_after"));
                model.setTotalDowntime((rs.getString("totaldowntime")));
                model.setArahgate(rs.getString("Arah_gate"));
                list.add(model);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    
    }

    @Override
    public List<TroubleEtModel> getExport(ProjectModel projek,Date tglMulai, Date tglAkhir) {
      List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement  statement = null;
        String sql = "select no, Tanggal_Masalah, Jam_Masalah,Tanggal_Done, Jam_Done,totaldowntime, JenisLaporan,\n" +
"No_Projek, IP, No_Perangkat, No_PVM, Problem, No_Penyebab, Solusi, Sumber, `Status`, RefNumber,No_User\n" +
"   from trouble_et where No_Projek=? and Tanggal_Done>=? and Tanggal_Done<=?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, projek.getNoprojek());
            statement.setDate(2, new java.sql.Date(tglMulai.getTime()));
            statement.setDate(3, new java.sql.Date(tglAkhir.getTime()));
            rs = statement.executeQuery();
            while (rs.next()){
                TroubleEtModel model = new TroubleEtModel();
                model.setNoEt(rs.getString("No"));
                model.setTglMasalah(rs.getDate("Tanggal_Masalah"));
                model.setJamMasalah(rs.getTime("Jam_Masalah"));
                model.setTglDone(rs.getDate("Tanggal_Done"));
                model.setJamDone(rs.getTime("Jam_Done"));
                int stasiunid = rs.getInt("IP");
                StasiunModel stasiunModel = stasiunDao.getStasiunModel(stasiunid);
                model.setSm(stasiunModel);
                model.setProblem(rs.getString("problem"));
                int penyebabid = rs.getInt("No_Penyebab");
                PenyebabModel penyebabByid = penyebabDao.getPenyebabByid(penyebabid);
                model.setPm(penyebabByid);
                model.setSolusi(rs.getString("Solusi"));
                int etid = rs.getInt("No_Perangkat");
                ETModel etmodelByid = eTDao.getEtmodelByid(etid);
                model.setEtm(etmodelByid);
               // model.setArah(rs.getString("Arah_Gate"));
                model.setStatus(rs.getString("Status"));
                int userid = rs.getInt("No_User");
                UserModel usermodelById = userDAo.getusermodelById(userid);
                model.setUm(usermodelById);
                 int projekid = rs.getInt("No_Projek");
                ProjectModel projekmodelbyid = projekDao.getbyid(projekid);
                model.setProm(projekmodelbyid);
                model.setJenisLaporan(rs.getString("JenisLaporan"));
                 int partid = rs.getInt("No_PVM");
                PerangkatVMModel perangkatVMModelbyid = partdao.perangkatVMModelbyid(partid);
                model.setPart(perangkatVMModelbyid);
                model.setSumber(rs.getString("Sumber"));
                model.setRef(rs.getString("RefNumber"));
                // model.setReftrouble(rs.getString("RefnoTrouble"));
               // model.setTeknisi(rs.getString("Teknisi"));
                 // model.setPicbefore(rs.getBytes("pic_before"));
                //model.setPicafter(rs.getBytes("pic_after"));
                model.setTotalDowntime((rs.getString("totaldowntime")));
              // model.setArahgate(rs.getString("Arah_gate"));
                list.add(model);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        
    }

    

   

    @Override
    public List<TroubleEtModel> getEtExportStatusCC(Date tglMulai, Date tglAkhir) {
     List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement  statement = null;
        String sql = "select projek.Nama_Projek AS PROJEK, status, count(*) from trouble_et INNER JOIN projek ON (trouble_et.No_Projek=projek.No_Projek)  where JenisLaporan='ADUAN' and Tanggal_Done>=? and Tanggal_Done <=? and status in ('Open', 'Done') GROUP BY PROJEK,status order by PROJEK asc,status desc";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setDate(1, new java.sql.Date(tglMulai.getTime()));
            statement.setDate(2, new java.sql.Date(tglAkhir.getTime()));
            rs = statement.executeQuery();
            while (rs.next()){
                TroubleEtModel model = new TroubleEtModel();
                /*model.setNoEt(rs.getString("No"));
                model.setTglMasalah(rs.getDate("Tanggal_Masalah"));
                model.setJamMasalah(rs.getTime("Jam_Masalah"));
                model.setTglDone(rs.getDate("Tanggal_Done"));
                model.setJamDone(rs.getTime("Jam_Done"));
                int stasiunid = rs.getInt("IP");
                StasiunModel stasiunModel = stasiunDao.getStasiunModel(stasiunid);
                model.setSm(stasiunModel);
                model.setProblem(rs.getString("problem"));
                int penyebabid = rs.getInt("No_Penyebab");
                PenyebabModel penyebabByid = penyebabDao.getPenyebabByid(penyebabid);
                model.setPm(penyebabByid);
                model.setSolusi(rs.getString("Solusi"));
                int etid = rs.getInt("No_Perangkat");
                ETModel etmodelByid = eTDao.getEtmodelByid(etid);
                model.setEtm(etmodelByid);
               // model.setArah(rs.getString("Arah_Gate"));
                model.setStatus(rs.getString("Status"));
                int userid = rs.getInt("No_User");
                UserModel usermodelById = userDAo.getusermodelById(userid);
                model.setUm(usermodelById);
                int projekid = rs.getInt("No_Projek");
                ProjectModel projekmodelbyid = projekDao.getbyid(projekid);
                model.setProm(projekmodelbyid);
                  model.setJenisLaporan(rs.getString("JenisLaporan"));
                 int partid = rs.getInt("No_PVM");
                PerangkatVMModel perangkatVMModelbyid = partdao.perangkatVMModelbyid(etid);
                model.setPart(perangkatVMModelbyid);
                model.setSumber(rs.getString("Sumber"));
                   model.setRef(rs.getString("RefNumber"));*/
                model.setNamaProjek(rs.getString("PROJEK"));
                model.setStatus2(rs.getString("status"));
                   model.setCount(rs.getInt("count(*)"));
                list.add(model);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }      
    
    
    }

    @Override
    public List<TroubleEtModel> getEtModelsByStatusandDate(String status, Date tglnya) {
      List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement  statement = null;
        String sql = "select * from trouble_et where status=? and Tanggal_Done=?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, status);
            statement.setDate(2, new java.sql.Date(tglnya.getTime()));
            rs = statement.executeQuery();
            while (rs.next()){
                TroubleEtModel model = new TroubleEtModel();
                model.setNoEt(rs.getString("No"));
                model.setTglMasalah(rs.getDate("Tanggal_Masalah"));
                model.setJamMasalah(rs.getTime("Jam_Masalah"));
                model.setTglDone(rs.getDate("Tanggal_Done"));
                model.setJamDone(rs.getTime("Jam_Done"));
                int stasiunid = rs.getInt("IP");
                StasiunModel stasiunModel = stasiunDao.getStasiunModel(stasiunid);
                model.setSm(stasiunModel);
                model.setProblem(rs.getString("problem"));
                int penyebabid = rs.getInt("No_Penyebab");
                PenyebabModel penyebabByid = penyebabDao.getPenyebabByid(penyebabid);
                model.setPm(penyebabByid);
                model.setSolusi(rs.getString("Solusi"));
                int etid = rs.getInt("No_Perangkat");
                ETModel etmodelByid = eTDao.getEtmodelByid(etid);
                model.setEtm(etmodelByid);
               // model.setArah(rs.getString("Arah_Gate"));
                model.setStatus(rs.getString("Status"));
                int userid = rs.getInt("No_User");
                UserModel usermodelById = userDAo.getusermodelById(userid);
                model.setUm(usermodelById);
                int projekid = rs.getInt("No_Projek");
                ProjectModel projekmodelbyid = projekDao.getbyid(projekid);
                model.setProm(projekmodelbyid);
                  model.setJenisLaporan(rs.getString("JenisLaporan"));
                 int partid = rs.getInt("No_PVM");
                PerangkatVMModel perangkatVMModelbyid = partdao.perangkatVMModelbyid(partid);
                model.setPart(perangkatVMModelbyid);
                model.setSumber(rs.getString("Sumber"));
                   model.setRef(rs.getString("RefNumber"));
                    model.setReftrouble(rs.getString("RefnoTrouble"));
                model.setTeknisi(rs.getString("Teknisi"));
                  model.setPicbefore(rs.getBytes("pic_before"));
                model.setPicafter(rs.getBytes("pic_after"));
                model.setTotalDowntime((rs.getString("totaldowntime")));
                model.setArahgate(rs.getString("Arah_gate"));
                list.add(model);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
           
    
    }

    @Override
    public List<TroubleEtModel> getEtModelsByNoticket(String Notiket) {
      List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement  statement = null;
        String sql = "select * from trouble_et where No=?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, Notiket);
            rs = statement.executeQuery();
            while (rs.next()){
                TroubleEtModel model = new TroubleEtModel();
                model.setNoEt(rs.getString("No"));
                model.setTglMasalah(rs.getDate("Tanggal_Masalah"));
                model.setJamMasalah(rs.getTime("Jam_Masalah"));
                model.setTglDone(rs.getDate("Tanggal_Done"));
                model.setJamDone(rs.getTime("Jam_Done"));
                int stasiunid = rs.getInt("IP");
                StasiunModel stasiunModel = stasiunDao.getStasiunModel(stasiunid);
                model.setSm(stasiunModel);
                model.setProblem(rs.getString("problem"));
                int penyebabid = rs.getInt("No_Penyebab");
                PenyebabModel penyebabByid = penyebabDao.getPenyebabByid(penyebabid);
                model.setPm(penyebabByid);
                model.setSolusi(rs.getString("Solusi"));
                int etid = rs.getInt("No_Perangkat");
                ETModel etmodelByid = eTDao.getEtmodelByid(etid);
                model.setEtm(etmodelByid);
               // model.setArah(rs.getString("Arah_Gate"));
                model.setStatus(rs.getString("Status"));
                int userid = rs.getInt("No_User");
                UserModel usermodelById = userDAo.getusermodelById(userid);
                model.setUm(usermodelById);
                int projekid = rs.getInt("No_Projek");
                ProjectModel projekmodelbyid = projekDao.getbyid(projekid);
                model.setProm(projekmodelbyid);
                  model.setJenisLaporan(rs.getString("JenisLaporan"));
                 int partid = rs.getInt("No_PVM");
                PerangkatVMModel perangkatVMModelbyid = partdao.perangkatVMModelbyid(partid);
                model.setPart(perangkatVMModelbyid);
                model.setSumber(rs.getString("Sumber"));
                   model.setRef(rs.getString("RefNumber"));
                    model.setReftrouble(rs.getString("RefnoTrouble"));
                model.setTeknisi(rs.getString("Teknisi"));
                  model.setPicbefore(rs.getBytes("pic_before"));
                model.setPicafter(rs.getBytes("pic_after"));
                model.setTotalDowntime((rs.getString("totaldowntime")));
                model.setArahgate(rs.getString("Arah_gate"));
                list.add(model);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
         
    }

    @Override
    public List<TroubleEtModel> getEtExportStatussifmalam(String Jenislaporan, Date tglMulai, String Timemalamawal, Date tglAkhir, String Timemalamakhir) {
    List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement  statement = null;
        String sql = "select projek.Nama_Projek AS PROJEK, status, count(*) from trouble_et INNER JOIN projek ON (trouble_et.No_Projek=projek.No_Projek)  where Tanggal_Done>=? and Tanggal_Done <=? and status in ('Open', 'Done') GROUP BY PROJEK,status order by status desc";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setDate(1, new java.sql.Date(tglMulai.getTime()));
            statement.setDate(2, new java.sql.Date(tglAkhir.getTime()));
            rs = statement.executeQuery();
            while (rs.next()){
                TroubleEtModel model = new TroubleEtModel();
                /*model.setNoEt(rs.getString("No"));
                model.setTglMasalah(rs.getDate("Tanggal_Masalah"));
                model.setJamMasalah(rs.getTime("Jam_Masalah"));
                model.setTglDone(rs.getDate("Tanggal_Done"));
                model.setJamDone(rs.getTime("Jam_Done"));
                int stasiunid = rs.getInt("IP");
                StasiunModel stasiunModel = stasiunDao.getStasiunModel(stasiunid);
                model.setSm(stasiunModel);
                model.setProblem(rs.getString("problem"));
                int penyebabid = rs.getInt("No_Penyebab");
                PenyebabModel penyebabByid = penyebabDao.getPenyebabByid(penyebabid);
                model.setPm(penyebabByid);
                model.setSolusi(rs.getString("Solusi"));
                int etid = rs.getInt("No_Perangkat");
                ETModel etmodelByid = eTDao.getEtmodelByid(etid);
                model.setEtm(etmodelByid);
               // model.setArah(rs.getString("Arah_Gate"));
                model.setStatus(rs.getString("Status"));
                int userid = rs.getInt("No_User");
                UserModel usermodelById = userDAo.getusermodelById(userid);
                model.setUm(usermodelById);
                int projekid = rs.getInt("No_Projek");
                ProjectModel projekmodelbyid = projekDao.getbyid(projekid);
                model.setProm(projekmodelbyid);
                  model.setJenisLaporan(rs.getString("JenisLaporan"));
                 int partid = rs.getInt("No_PVM");
                PerangkatVMModel perangkatVMModelbyid = partdao.perangkatVMModelbyid(etid);
                model.setPart(perangkatVMModelbyid);
                model.setSumber(rs.getString("Sumber"));
                   model.setRef(rs.getString("RefNumber"));*/
                model.setNamaProjek(rs.getString("PROJEK"));
                model.setStatus2(rs.getString("status"));
                   model.setCount(rs.getInt("count(*)"));
                list.add(model);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }      
    
        
        
    }

    @Override
    public List<TroubleEtModel> getEtExportStatusIT(Date tglMulai, Date tglAkhir) {
    List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement  statement = null;
        String sql = "select projek.Nama_Projek AS PROJEK, status, count(*) from trouble_et INNER JOIN projek ON (trouble_et.No_Projek=projek.No_Projek)  where JenisLaporan='PERMASALAHAN' and Tanggal_Done>=? and Tanggal_Done <=? and status in ('Open', 'Done') GROUP BY PROJEK,status order by PROJEK asc,status desc";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setDate(1, new java.sql.Date(tglMulai.getTime()));
            statement.setDate(2, new java.sql.Date(tglAkhir.getTime()));
            rs = statement.executeQuery();
            while (rs.next()){
                TroubleEtModel model = new TroubleEtModel();
                /*model.setNoEt(rs.getString("No"));
                model.setTglMasalah(rs.getDate("Tanggal_Masalah"));
                model.setJamMasalah(rs.getTime("Jam_Masalah"));
                model.setTglDone(rs.getDate("Tanggal_Done"));
                model.setJamDone(rs.getTime("Jam_Done"));
                int stasiunid = rs.getInt("IP");
                StasiunModel stasiunModel = stasiunDao.getStasiunModel(stasiunid);
                model.setSm(stasiunModel);
                model.setProblem(rs.getString("problem"));
                int penyebabid = rs.getInt("No_Penyebab");
                PenyebabModel penyebabByid = penyebabDao.getPenyebabByid(penyebabid);
                model.setPm(penyebabByid);
                model.setSolusi(rs.getString("Solusi"));
                int etid = rs.getInt("No_Perangkat");
                ETModel etmodelByid = eTDao.getEtmodelByid(etid);
                model.setEtm(etmodelByid);
               // model.setArah(rs.getString("Arah_Gate"));
                model.setStatus(rs.getString("Status"));
                int userid = rs.getInt("No_User");
                UserModel usermodelById = userDAo.getusermodelById(userid);
                model.setUm(usermodelById);
                int projekid = rs.getInt("No_Projek");
                ProjectModel projekmodelbyid = projekDao.getbyid(projekid);
                model.setProm(projekmodelbyid);
                  model.setJenisLaporan(rs.getString("JenisLaporan"));
                 int partid = rs.getInt("No_PVM");
                PerangkatVMModel perangkatVMModelbyid = partdao.perangkatVMModelbyid(etid);
                model.setPart(perangkatVMModelbyid);
                model.setSumber(rs.getString("Sumber"));
                   model.setRef(rs.getString("RefNumber"));*/
                model.setNamaProjek(rs.getString("PROJEK"));
                model.setStatus2(rs.getString("status"));
                   model.setCount(rs.getInt("count(*)"));
                list.add(model);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }       
    
    }

   

    @Override
    public List<TroubleEtModel> getEtModelsByDate2(Date tglawal, Date tglakhir) {
     List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement  statement = null;
        String sql = "select no, Tanggal_Masalah, Jam_Masalah,Tanggal_Done, Jam_Done,totaldowntime, JenisLaporan,\n" +
"No_Projek, IP, No_Perangkat, No_PVM, Problem, No_Penyebab, Solusi, Sumber, `Status`, RefNumber,No_User\n" +
"   from trouble_et where Tanggal_Done>=? and Tanggal_Done<=?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setDate(1, new java.sql.Date(tglawal.getTime()));
            statement.setDate(2, new java.sql.Date(tglakhir.getTime()));
           
            rs = statement.executeQuery();
            while (rs.next()){
                TroubleEtModel model = new TroubleEtModel();
                model.setNoEt(rs.getString("No"));
                model.setTglMasalah(rs.getDate("Tanggal_Masalah"));
                model.setJamMasalah(rs.getTime("Jam_Masalah"));
                model.setTglDone(rs.getDate("Tanggal_Done"));
                model.setJamDone(rs.getTime("Jam_Done"));
                int stasiunid = rs.getInt("IP");
                StasiunModel stasiunModel = stasiunDao.getStasiunModel(stasiunid);
                model.setSm(stasiunModel);
                model.setProblem(rs.getString("problem"));
                int penyebabid = rs.getInt("No_Penyebab");
                PenyebabModel penyebabByid = penyebabDao.getPenyebabByid(penyebabid);
                model.setPm(penyebabByid);
                model.setSolusi(rs.getString("Solusi"));
                int etid = rs.getInt("No_Perangkat");
                ETModel etmodelByid = eTDao.getEtmodelByid(etid);
                model.setEtm(etmodelByid);
                //model.setArah(rs.getString("Arah_Gate"));
                model.setStatus(rs.getString("Status"));
                int userid = rs.getInt("No_User");
                UserModel usermodelById = userDAo.getusermodelById(userid);
                model.setUm(usermodelById);
                int projekid = rs.getInt("No_Projek");
                ProjectModel projekmodelbyid = projekDao.getbyid(projekid);
                model.setProm(projekmodelbyid);
                  model.setJenisLaporan(rs.getString("JenisLaporan"));
                 int partid = rs.getInt("No_PVM");
                PerangkatVMModel perangkatVMModelbyid = partdao.perangkatVMModelbyid(partid);
                model.setPart(perangkatVMModelbyid);
                model.setSumber(rs.getString("Sumber"));
                   model.setRef(rs.getString("RefNumber"));
                 //   model.setReftrouble(rs.getString("RefnoTrouble"));
               // model.setTeknisi(rs.getString("Teknisi"));
               // model.setPicbefore(rs.getBytes("pic_before"));
               // model.setPicafter(rs.getBytes("pic_after"));
                model.setTotalDowntime((rs.getString("totaldowntime")));
               // model.setArahgate(rs.getString("Arah_gate"));
                list.add(model);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<TroubleEtModel> forexportandview(String status, ProjectModel projek,Date tglawal, Date tglakhir) {
     List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement  statement = null;
        String sql = "select no, Tanggal_Masalah, Jam_Masalah,Tanggal_Done, Jam_Done,totaldowntime, JenisLaporan,\n" +
"No_Projek, IP, No_Perangkat, No_PVM, Problem, No_Penyebab, Solusi, Sumber, `Status`, RefNumber,No_User\n" +
"   from trouble_et where status=? and No_Projek=? and Tanggal_Done>=? and Tanggal_Done<=?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, status);
            statement.setInt(2, projek.getNoprojek());
            statement.setDate(3, new java.sql.Date(tglawal.getTime()));
            statement.setDate(4, new java.sql.Date(tglakhir.getTime()));
           
            rs = statement.executeQuery();
            while (rs.next()){
                TroubleEtModel model = new TroubleEtModel();
                model.setNoEt(rs.getString("No"));
                model.setTglMasalah(rs.getDate("Tanggal_Masalah"));
                model.setJamMasalah(rs.getTime("Jam_Masalah"));
                model.setTglDone(rs.getDate("Tanggal_Done"));
                model.setJamDone(rs.getTime("Jam_Done"));
                int stasiunid = rs.getInt("IP");
                StasiunModel stasiunModel = stasiunDao.getStasiunModel(stasiunid);
                model.setSm(stasiunModel);
                model.setProblem(rs.getString("problem"));
                int penyebabid = rs.getInt("No_Penyebab");
                PenyebabModel penyebabByid = penyebabDao.getPenyebabByid(penyebabid);
                model.setPm(penyebabByid);
                model.setSolusi(rs.getString("Solusi"));
                int etid = rs.getInt("No_Perangkat");
                ETModel etmodelByid = eTDao.getEtmodelByid(etid);
                model.setEtm(etmodelByid);
                //model.setArah(rs.getString("Arah_Gate"));
                model.setStatus(rs.getString("Status"));
                int userid = rs.getInt("No_User");
                UserModel usermodelById = userDAo.getusermodelById(userid);
                model.setUm(usermodelById);
                int projekid = rs.getInt("No_Projek");
                ProjectModel projekmodelbyid = projekDao.getbyid(projekid);
                model.setProm(projekmodelbyid);
                  model.setJenisLaporan(rs.getString("JenisLaporan"));
                 int partid = rs.getInt("No_PVM");
                PerangkatVMModel perangkatVMModelbyid = partdao.perangkatVMModelbyid(partid);
                model.setPart(perangkatVMModelbyid);
                model.setSumber(rs.getString("Sumber"));
                   model.setRef(rs.getString("RefNumber"));
                  //  model.setReftrouble(rs.getString("RefnoTrouble"));
               // model.setTeknisi(rs.getString("Teknisi"));
                 // model.setPicbefore(rs.getBytes("pic_before"));
               // model.setPicafter(rs.getBytes("pic_after"));
                model.setTotalDowntime((rs.getString("totaldowntime")));
               // model.setArahgate(rs.getString("Arah_gate"));
                list.add(model);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }   
    }

    @Override
    public List<TroubleEtModel> getEtModelsBytgl2andstatus(Date tglMulai, Date tglAkhir, String Status) {
    List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement  statement = null;
        String sql = "select no, Tanggal_Masalah, Jam_Masalah,Tanggal_Done, Jam_Done,totaldowntime, JenisLaporan,\n" +
"No_Projek, IP, No_Perangkat, No_PVM, Problem, No_Penyebab, Solusi, Sumber, `Status`, RefNumber,No_User\n" +
"   from trouble_et where Tanggal_Done>=? and Tanggal_Done<=? and status=?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setDate(1, new java.sql.Date(tglMulai.getTime()));
            statement.setDate(2, new java.sql.Date(tglAkhir.getTime()));
            statement.setString(3, Status);
           
            rs = statement.executeQuery();
            while (rs.next()){
                TroubleEtModel model = new TroubleEtModel();
                model.setNoEt(rs.getString("No"));
                model.setTglMasalah(rs.getDate("Tanggal_Masalah"));
                model.setJamMasalah(rs.getTime("Jam_Masalah"));
                model.setTglDone(rs.getDate("Tanggal_Done"));
                model.setJamDone(rs.getTime("Jam_Done"));
                int stasiunid = rs.getInt("IP");
                StasiunModel stasiunModel = stasiunDao.getStasiunModel(stasiunid);
                model.setSm(stasiunModel);
                model.setProblem(rs.getString("problem"));
                int penyebabid = rs.getInt("No_Penyebab");
                PenyebabModel penyebabByid = penyebabDao.getPenyebabByid(penyebabid);
                model.setPm(penyebabByid);
                model.setSolusi(rs.getString("Solusi"));
                int etid = rs.getInt("No_Perangkat");
                ETModel etmodelByid = eTDao.getEtmodelByid(etid);
                model.setEtm(etmodelByid);
                //model.setArah(rs.getString("Arah_Gate"));
                model.setStatus(rs.getString("Status"));
                int userid = rs.getInt("No_User");
                UserModel usermodelById = userDAo.getusermodelById(userid);
                model.setUm(usermodelById);
                int projekid = rs.getInt("No_Projek");
                ProjectModel projekmodelbyid = projekDao.getbyid(projekid);
                model.setProm(projekmodelbyid);
                  model.setJenisLaporan(rs.getString("JenisLaporan"));
                 int partid = rs.getInt("No_PVM");
                PerangkatVMModel perangkatVMModelbyid = partdao.perangkatVMModelbyid(partid);
                model.setPart(perangkatVMModelbyid);
                model.setSumber(rs.getString("Sumber"));
                   model.setRef(rs.getString("RefNumber"));
                  //  model.setReftrouble(rs.getString("RefnoTrouble"));
               // model.setTeknisi(rs.getString("Teknisi"));
                // model.setPicbefore(rs.getBytes("pic_before"));
               // model.setPicafter(rs.getBytes("pic_after"));
                model.setTotalDowntime((rs.getString("totaldowntime")));
                //model.setArahgate(rs.getString("Arah_gate"));
                list.add(model);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }     
    
    }

    @Override
    public List<TroubleEtModel> getexportgrafikPerPerangkat(String Projek, Date tglMulai, Date tglAkhir) {
    List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement  statement = null;
        String sql = "SELECT perangkat_vm.Jenis, perangkat_vm.Nama_Perangkat, penyebab.Penyebab, count(*) from trouble_et INNER JOIN penyebab on (trouble_et.No_Penyebab=penyebab.No_Penyebab) join perangkat_vm on (trouble_et.No_PVM=perangkat_vm.No_PVM) join projek on (trouble_et.No_Projek=projek.No_Projek) where projek.Nama_Projek=? and trouble_et.Tanggal_Done>=? and trouble_et.Tanggal_Done<=? GROUP BY perangkat_vm.Nama_Perangkat, penyebab.Penyebab ORDER BY jenis, Nama_Perangkat ASC";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, Projek);
            statement.setDate(2, new java.sql.Date(tglMulai.getTime()));
            statement.setDate(3, new java.sql.Date(tglAkhir.getTime()));
            rs = statement.executeQuery();
            while (rs.next()){
                TroubleEtModel model = new TroubleEtModel();
                /*model.setNoEt(rs.getString("No"));
                model.setTglMasalah(rs.getDate("Tanggal_Masalah"));
                model.setJamMasalah(rs.getTime("Jam_Masalah"));
                model.setTglDone(rs.getDate("Tanggal_Done"));
                model.setJamDone(rs.getTime("Jam_Done"));
                int stasiunid = rs.getInt("IP");
                StasiunModel stasiunModel = stasiunDao.getStasiunModel(stasiunid);
                model.setSm(stasiunModel);
                model.setProblem(rs.getString("problem"));
                int penyebabid = rs.getInt("No_Penyebab");
                PenyebabModel penyebabByid = penyebabDao.getPenyebabByid(penyebabid);
                model.setPm(penyebabByid);
                model.setSolusi(rs.getString("Solusi"));
                int etid = rs.getInt("No_Perangkat");
                ETModel etmodelByid = eTDao.getEtmodelByid(etid);
                model.setEtm(etmodelByid);
               // model.setArah(rs.getString("Arah_Gate"));
                model.setStatus(rs.getString("Status"));
                int userid = rs.getInt("No_User");
                UserModel usermodelById = userDAo.getusermodelById(userid);
                model.setUm(usermodelById);
                int projekid = rs.getInt("No_Projek");
                ProjectModel projekmodelbyid = projekDao.getbyid(projekid);
                model.setProm(projekmodelbyid);
                  model.setJenisLaporan(rs.getString("JenisLaporan"));
                 int partid = rs.getInt("No_PVM");
                PerangkatVMModel perangkatVMModelbyid = partdao.perangkatVMModelbyid(etid);
                model.setPart(perangkatVMModelbyid);
                model.setSumber(rs.getString("Sumber"));
                   model.setRef(rs.getString("RefNumber"));*/
                model.setJenis(rs.getString("Jenis"));
                model.setNamapenyebab(rs.getString("Penyebab"));
                model.setNamaPerangkat(rs.getString("Nama_Perangkat"));
                   model.setCount(rs.getInt("count(*)"));
                list.add(model);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }     
    
    
    }

    @Override
    public List<TroubleEtModel> getexportgrafikperpenyebab(String Projek, Date tglMulai, Date tglAkhir) {
    List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement  statement = null;
        String sql = "SELECT  penyebab.Penyebab, count(*) from trouble_et INNER JOIN penyebab on (trouble_et.No_Penyebab=penyebab.No_Penyebab) join projek on (trouble_et.No_Projek=projek.No_Projek) where projek.Nama_Projek=? and trouble_et.Tanggal_Done>=? and trouble_et.Tanggal_Done<=? GROUP BY penyebab.Penyebab";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, Projek);
            statement.setDate(2, new java.sql.Date(tglMulai.getTime()));
            statement.setDate(3, new java.sql.Date(tglAkhir.getTime()));
            rs = statement.executeQuery();
            while (rs.next()){
                TroubleEtModel model = new TroubleEtModel();
                /*model.setNoEt(rs.getString("No"));
                model.setTglMasalah(rs.getDate("Tanggal_Masalah"));
                model.setJamMasalah(rs.getTime("Jam_Masalah"));
                model.setTglDone(rs.getDate("Tanggal_Done"));
                model.setJamDone(rs.getTime("Jam_Done"));
                int stasiunid = rs.getInt("IP");
                StasiunModel stasiunModel = stasiunDao.getStasiunModel(stasiunid);
                model.setSm(stasiunModel);
                model.setProblem(rs.getString("problem"));
                int penyebabid = rs.getInt("No_Penyebab");
                PenyebabModel penyebabByid = penyebabDao.getPenyebabByid(penyebabid);
                model.setPm(penyebabByid);
                model.setSolusi(rs.getString("Solusi"));
                int etid = rs.getInt("No_Perangkat");
                ETModel etmodelByid = eTDao.getEtmodelByid(etid);
                model.setEtm(etmodelByid);
               // model.setArah(rs.getString("Arah_Gate"));
                model.setStatus(rs.getString("Status"));
                int userid = rs.getInt("No_User");
                UserModel usermodelById = userDAo.getusermodelById(userid);
                model.setUm(usermodelById);
                int projekid = rs.getInt("No_Projek");
                ProjectModel projekmodelbyid = projekDao.getbyid(projekid);
                model.setProm(projekmodelbyid);
                  model.setJenisLaporan(rs.getString("JenisLaporan"));
                 int partid = rs.getInt("No_PVM");
                PerangkatVMModel perangkatVMModelbyid = partdao.perangkatVMModelbyid(etid);
                model.setPart(perangkatVMModelbyid);
                model.setSumber(rs.getString("Sumber"));
                   model.setRef(rs.getString("RefNumber"));*/
                model.setNamapenyebab(rs.getString("Penyebab"));
                   model.setCount(rs.getInt("count(*)"));
                list.add(model);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }     
    
    }

    @Override
    public String setautonumber(ProjectModel projek) {
       PreparedStatement statement=null;
      ResultSet rs=null;
      String kode=null;
      String s, s1;
      Integer j;
      Integer panjang = 9;
      String sql="select max(right(No, 9)) from trouble_et where No_Projek=?";
        try {
            statement=connection.prepareStatement(sql);
            statement.setInt(1, projek.getNoprojek());
            rs=statement.executeQuery();
            if(rs.first()==false){
                kode=tanggal+projek.getInit()+"000000001";
            }else{
                rs.last();
                s=Integer.toString(rs.getInt(1)+1);
                j=s.length();
                s1="";
                for(int i=0; i<=panjang-j; i++){
                    s1=s1+"0";
                }
                return kode=tanggal+projek.getInit()+s1+s;
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

    @Override
    public List<TroubleEtModel> getcountandalldata(String Status,String jenislaporan, Date tglMulai, Date tglAkhir) {
        List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement  statement = null;
        String sql = "select * from trouble_et where Status=? and JenisLaporan=? and Tanggal_Done BETWEEN ? and ? ";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, Status);
            statement.setString(2, jenislaporan);
            statement.setDate(3, new java.sql.Date(tglMulai.getTime()));
            statement.setDate(4, new java.sql.Date(tglAkhir.getTime()));
            rs = statement.executeQuery();
            while (rs.next()){
                TroubleEtModel model = new TroubleEtModel();
                model.setNoEt(rs.getString("No"));
                model.setTglMasalah(rs.getDate("Tanggal_Masalah"));
                model.setJamMasalah(rs.getTime("Jam_Masalah"));
                model.setTglDone(rs.getDate("Tanggal_Done"));
                model.setJamDone(rs.getTime("Jam_Done"));
                int stasiunid = rs.getInt("IP");
                StasiunModel stasiunModel = stasiunDao.getStasiunModel(stasiunid);
                model.setSm(stasiunModel);
                model.setProblem(rs.getString("problem"));
                int penyebabid = rs.getInt("No_Penyebab");
                PenyebabModel penyebabByid = penyebabDao.getPenyebabByid(penyebabid);
                model.setPm(penyebabByid);
                model.setSolusi(rs.getString("Solusi"));
                int etid = rs.getInt("No_Perangkat");
                ETModel etmodelByid = eTDao.getEtmodelByid(etid);
                model.setEtm(etmodelByid);
               // model.setArah(rs.getString("Arah_Gate"));
                model.setStatus(rs.getString("Status"));
                int userid = rs.getInt("No_User");
                UserModel usermodelById = userDAo.getusermodelById(userid);
                model.setUm(usermodelById);
                int projekid = rs.getInt("No_Projek");
                ProjectModel projekmodelbyid = projekDao.getbyid(projekid);
                model.setProm(projekmodelbyid);
                  model.setJenisLaporan(rs.getString("JenisLaporan"));
                 int partid = rs.getInt("No_PVM");
                PerangkatVMModel perangkatVMModelbyid = partdao.perangkatVMModelbyid(partid);
                model.setPart(perangkatVMModelbyid);
                model.setSumber(rs.getString("Sumber"));
                   model.setRef(rs.getString("RefNumber"));
                     model.setPicbefore(rs.getBytes("pic_before"));
                model.setPicafter(rs.getBytes("pic_after"));
                model.setTotalDowntime((rs.getString("totaldowntime")));
                model.setArahgate(rs.getString("Arah_gate"));
                list.add(model);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }     
    
    }

    @Override
    public List<TroubleEtModel> getDowntime(String Akhirdowntime, String Awaldowntime, String Akhirdowntime1, String Awaldowntime1, String Akhirdowntime2, String Awaldowntime2 ) {
     List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement  statement = null;
        String sql = "SELECT CONCAT(FLOOR(HOUR(TIMEDIFF(?, ?)) / 24), ' Hari ',MOD(HOUR(TIMEDIFF(?, ?)), 24), ' Jam ',MINUTE(TIMEDIFF(?, ?)), ' Menit') as Durasi";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, Akhirdowntime);
            statement.setString(2, Awaldowntime);
            statement.setString(3, Akhirdowntime1);
            statement.setString(4, Awaldowntime1);
            statement.setString(5,Akhirdowntime2);
            statement.setString(6, Awaldowntime2);
            rs = statement.executeQuery();
            while (rs.next()){
                TroubleEtModel model = new TroubleEtModel();
                model.setTotalDowntime(rs.getString("Durasi"));
                list.add(model);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }    
    }

    @Override
    public List<TroubleEtModel> getDowntimeparah(String haridone, String harimasalah) {
      
     List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement  statement = null;
        String sql = "SELECT CONCAT(FLOOR((DATEDIFF(?, ?))), ' Hari ') as Durasi";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, haridone);
            statement.setString(2, harimasalah);
            rs = statement.executeQuery();
            while (rs.next()){
                TroubleEtModel model = new TroubleEtModel();
                model.setTotalDowntime(rs.getString("Durasi"));
                list.add(model);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }   
    
    }

    @Override
    public List<TroubleEtModel> getEtModelsReport(Date tglawal, Date tglakhir, String Status) {
     List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement  statement = null;
        String sql = "SELECT * FROM trouble_et WHERE LENGTH(pic_before)>0 and Tanggal_Done>=? and Tanggal_Done<=? and status=?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setDate(1, new java.sql.Date(tglawal.getTime()));
            statement.setDate(2, new java.sql.Date(tglakhir.getTime()));
            statement.setString(3, Status);
           
            rs = statement.executeQuery();
            while (rs.next()){
                TroubleEtModel model = new TroubleEtModel();
                model.setNoEt(rs.getString("No"));
                model.setTglMasalah(rs.getDate("Tanggal_Masalah"));
                model.setJamMasalah(rs.getTime("Jam_Masalah"));
                model.setTglDone(rs.getDate("Tanggal_Done"));
                model.setJamDone(rs.getTime("Jam_Done"));
                int stasiunid = rs.getInt("IP");
                StasiunModel stasiunModel = stasiunDao.getStasiunModel(stasiunid);
                model.setSm(stasiunModel);
                model.setProblem(rs.getString("problem"));
                int penyebabid = rs.getInt("No_Penyebab");
                PenyebabModel penyebabByid = penyebabDao.getPenyebabByid(penyebabid);
                model.setPm(penyebabByid);
                model.setSolusi(rs.getString("Solusi"));
                int etid = rs.getInt("No_Perangkat");
                ETModel etmodelByid = eTDao.getEtmodelByid(etid);
                model.setEtm(etmodelByid);
                //model.setArah(rs.getString("Arah_Gate"));
                model.setStatus(rs.getString("Status"));
                int userid = rs.getInt("No_User");
                UserModel usermodelById = userDAo.getusermodelById(userid);
                model.setUm(usermodelById);
                int projekid = rs.getInt("No_Projek");
                ProjectModel projekmodelbyid = projekDao.getbyid(projekid);
                model.setProm(projekmodelbyid);
                  model.setJenisLaporan(rs.getString("JenisLaporan"));
                 int partid = rs.getInt("No_PVM");
                PerangkatVMModel perangkatVMModelbyid = partdao.perangkatVMModelbyid(partid);
                model.setPart(perangkatVMModelbyid);
                model.setSumber(rs.getString("Sumber"));
                   model.setRef(rs.getString("RefNumber"));
                    model.setReftrouble(rs.getString("RefnoTrouble"));
                model.setTeknisi(rs.getString("Teknisi"));
                model.setPicbefore(rs.getBytes("pic_before"));
                model.setPicafter(rs.getBytes("pic_after"));
                model.setTotalDowntime((rs.getString("totaldowntime")));
                model.setArahgate(rs.getString("Arah_gate"));
                list.add(model);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TroubleETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }   
    
    }

    

   
     
}
