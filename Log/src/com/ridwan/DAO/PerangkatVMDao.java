/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.DAO;

import com.ridwan.DBC.DBC;
import com.ridwan.Interface.PerangkatVMInterface;
import com.ridwan.Model.PerangkatVMModel;
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
public class PerangkatVMDao implements PerangkatVMInterface {
    
    Connection connection;

    public PerangkatVMDao() {
    connection = DBC.getConnection();
    }
    
    

    @Override
    public boolean insertPVM(PerangkatVMModel pvmm) {
    boolean valid = false;
    PreparedStatement statement = null;
        
       String sql = "insert into perangkat_vm (No_PVM, Nama_Perangkat, Jenis) values (?,?,?)";
        try {
            statement= connection.prepareStatement(sql);
            statement.setInt(1, pvmm.getNoPVM());
            statement.setString(2, pvmm.getNamaPerangkat());
            statement.setString(3, pvmm.getJenis());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(PerangkatVMDao.class.getName()).log(Level.SEVERE, null, ex);
        valid = false;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PerangkatVMDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    return valid;
    }

    @Override
    public boolean updatePVM(PerangkatVMModel pvmm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deletePVM(PerangkatVMModel pvmm) {
    boolean valid = false;
    PreparedStatement statement = null;
    String sql = "delete from perangkat_vm where No_PVM=?";
    
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, pvmm.getNoPVM());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(PerangkatVMDao.class.getName()).log(Level.SEVERE, null, ex);
        valid = false;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PerangkatVMDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public List<PerangkatVMModel> getPerangkatVMModels() {
    List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement statement = null;
        String sql = "select * from perangkat_vm";
        
        try {
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()){
                PerangkatVMModel model = new PerangkatVMModel();
                model.setNoPVM(rs.getInt("No_PVM"));
                model.setNamaPerangkat(rs.getString("Nama_Perangkat"));
                  model.setJenis(rs.getString("Jenis"));
                list.add(model);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(PerangkatVMDao.class.getName()).log(Level.SEVERE, null, ex);
       return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PerangkatVMDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PerangkatVMDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public PerangkatVMModel perangkatVMModelbyid(int id) {
    //List list = new ArrayList();
        ResultSet rs = null;
        PerangkatVMModel model = null;
        PreparedStatement statement = null;
        String sql = "select * from perangkat_vm where No_PVM=?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()){
                 model = new PerangkatVMModel();
                model.setNoPVM(rs.getInt("No_PVM"));
                model.setNamaPerangkat(rs.getString("Nama_Perangkat"));
                model.setJenis(rs.getString("Jenis"));
            }return model;
        } catch (SQLException ex) {
            Logger.getLogger(PerangkatVMDao.class.getName()).log(Level.SEVERE, null, ex);
       return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PerangkatVMDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PerangkatVMDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }   
    
    }

    @Override
    public List<PerangkatVMModel> getPartbyPerangkatnotVM() {
      List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement statement = null;
        String sql = "select * from perangkat_vm where Jenis not in ('Vending Machine')";
        
        try {
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()){
                PerangkatVMModel model = new PerangkatVMModel();
                model.setNoPVM(rs.getInt("No_PVM"));
                model.setNamaPerangkat(rs.getString("Nama_Perangkat"));
                model.setJenis(rs.getString("Jenis"));
                list.add(model);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(PerangkatVMDao.class.getName()).log(Level.SEVERE, null, ex);
       return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PerangkatVMDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PerangkatVMDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }  
    }

    @Override
    public List<PerangkatVMModel> getPartbyPerangkat() {
      List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement statement = null;
        String sql = "select * from perangkat_vm where Jenis not in ('E-Ticketing')";
        
        try {
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()){
                PerangkatVMModel model = new PerangkatVMModel();
                model.setNoPVM(rs.getInt("No_PVM"));
                model.setNamaPerangkat(rs.getString("Nama_Perangkat"));
                model.setJenis(rs.getString("Jenis"));
                list.add(model);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(PerangkatVMDao.class.getName()).log(Level.SEVERE, null, ex);
       return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PerangkatVMDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PerangkatVMDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }  
    
    }
    
}
