/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.DAO;

import com.ridwan.DBC.DBC;
import com.ridwan.Interface.PenyebabInterface;
import com.ridwan.Model.PenyebabModel;
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
public class PenyebabDao implements PenyebabInterface {

    private Connection connection;
    PerangkatVMDao perangkatVMDao = new PerangkatVMDao();
    
    public PenyebabDao() {
    connection = DBC.getConnection();
    }
    
    
    
    @Override
    public boolean insertPenyebab(PenyebabModel pm) {
    boolean valid = false;
        PreparedStatement statement = null;
        
        String sql = "insert into penyebab (No_Penyebab, Penyebab, Kategori, No_PVM) values (?,?,?,?)";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, pm.getNoPenyebab());
            statement.setString(2, pm.getNamaPenyebab());
            statement.setString(3, pm.getKategori());
            statement.setInt(4, pm.getPvmmodel().getNoPVM());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(PenyebabDao.class.getName()).log(Level.SEVERE, null, ex);
        valid = false;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenyebabDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean updatePenyebab(PenyebabModel pm) {
    boolean valid = false;
    PreparedStatement statement = null;
    String sql = "update penyebab set Penyebab=?, Kategori=?, No_PVM=? where No_Penyebab=?";
    
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, pm.getNamaPenyebab());
            statement.setString(2, pm.getKategori());
            statement.setInt(3,pm.getPvmmodel().getNoPVM());
            statement.setInt(4, pm.getNoPenyebab());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(PenyebabDao.class.getName()).log(Level.SEVERE, null, ex);
        valid = false;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenyebabDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    return valid;
    }

    @Override
    public boolean deletePenyebab(PenyebabModel pm) {
    boolean valid = false;
    PreparedStatement statement = null;
    
    String sql = "delete from penyebab where No_Penyebab=?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, pm.getNoPenyebab());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(PenyebabDao.class.getName()).log(Level.SEVERE, null, ex);
        valid = false;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenyebabDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public List<PenyebabModel> getPenyebabModels() {
    List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement statement = null;
        
        String sql = "select * from penyebab";
        
        try {
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()){
                PenyebabModel pmm = new PenyebabModel();
                pmm.setNoPenyebab(rs.getInt("No_Penyebab"));
                pmm.setNamaPenyebab(rs.getString("Penyebab"));
                pmm.setKategori(rs.getString("Kategori"));
                int idpvm = rs.getInt("No_PVM");
                PerangkatVMModel perangkatVMModelbyid = perangkatVMDao.perangkatVMModelbyid(idpvm);
                pmm.setPvmmodel(perangkatVMModelbyid);
                list.add(pmm);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(PenyebabDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenyebabDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenyebabDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public PenyebabModel getPenyebabByid(int id) {
    //List list = new ArrayList();
        ResultSet rs = null;
        PenyebabModel pmm = null;
        PreparedStatement statement = null;
        
        String sql = "select * from penyebab where No_Penyebab=?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()){
                pmm = new PenyebabModel();
                pmm.setNoPenyebab(rs.getInt("No_Penyebab"));
                pmm.setNamaPenyebab(rs.getString("Penyebab"));
                pmm.setKategori(rs.getString("Kategori"));
                int idpvm = rs.getInt("No_PVM");
                PerangkatVMModel perangkatVMModelbyid = perangkatVMDao.perangkatVMModelbyid(idpvm);
                pmm.setPvmmodel(perangkatVMModelbyid);
            }return pmm;
        } catch (SQLException ex) {
            Logger.getLogger(PenyebabDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenyebabDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenyebabDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }   
    }

    @Override
    public List<PenyebabModel> getByPerangkatVM(PerangkatVMModel mModel) {
       List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement statement = null;
        
        String sql = "select * from penyebab where No_PVM=?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, mModel.getNoPVM());
            rs = statement.executeQuery();
            while (rs.next()){
                PenyebabModel pmm = new PenyebabModel();
                pmm.setNoPenyebab(rs.getInt("No_Penyebab"));
                pmm.setNamaPenyebab(rs.getString("Penyebab"));
                pmm.setKategori(rs.getString("Kategori"));
                int idpvm = rs.getInt("No_PVM");
                PerangkatVMModel perangkatVMModelbyid = perangkatVMDao.perangkatVMModelbyid(idpvm);
                pmm.setPvmmodel(perangkatVMModelbyid);
                list.add(pmm);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(PenyebabDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenyebabDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenyebabDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }  
    
    }

    @Override
    public List<PenyebabModel> getBygatepos() {
        List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement statement = null;
        
        String sql = "select * from penyebab inner join perangkat_vm on (penyebab.No_PVM= perangkat_vm.No_PVM) where perangkat_vm.Nama_Perangkat in ('GATE','POS')";
        
        try {
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()){
                PenyebabModel pmm = new PenyebabModel();
                pmm.setNoPenyebab(rs.getInt("No_Penyebab"));
                pmm.setNamaPenyebab(rs.getString("Penyebab"));
                pmm.setKategori(rs.getString("Kategori"));
                int idpvm = rs.getInt("No_PVM");
                PerangkatVMModel perangkatVMModelbyid = perangkatVMDao.perangkatVMModelbyid(idpvm);
                pmm.setPvmmodel(perangkatVMModelbyid);
                list.add(pmm);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(PenyebabDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenyebabDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenyebabDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }  
    }

    

    @Override
    public List<PenyebabModel> getByNOTgatepos() {
    List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement statement = null;
        
        String sql = "select * from penyebab inner join perangkat_vm on (penyebab.No_PVM= perangkat_vm.No_PVM) where perangkat_vm.Nama_Perangkat not in ('GATE','POS')";
        
        try {
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()){
                PenyebabModel pmm = new PenyebabModel();
                pmm.setNoPenyebab(rs.getInt("No_Penyebab"));
                pmm.setNamaPenyebab(rs.getString("Penyebab"));
                pmm.setKategori(rs.getString("Kategori"));
                int idpvm = rs.getInt("No_PVM");
                PerangkatVMModel perangkatVMModelbyid = perangkatVMDao.perangkatVMModelbyid(idpvm);
                pmm.setPvmmodel(perangkatVMModelbyid);
                list.add(pmm);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(PenyebabDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenyebabDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenyebabDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }     
    
    }

    @Override
    public List<PenyebabModel> getByPart(String NamaPart) {
      List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement statement = null;
        
        String sql = "select * from penyebab inner join perangkat_vm on (penyebab.No_PVM= perangkat_vm.No_PVM) where perangkat_vm.Nama_Perangkat=?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, NamaPart);
            rs = statement.executeQuery();
            while (rs.next()){
                PenyebabModel pmm = new PenyebabModel();
                pmm.setNoPenyebab(rs.getInt("No_Penyebab"));
                pmm.setNamaPenyebab(rs.getString("Penyebab"));
                pmm.setKategori(rs.getString("Kategori"));
                int idpvm = rs.getInt("No_PVM");
                PerangkatVMModel perangkatVMModelbyid = perangkatVMDao.perangkatVMModelbyid(idpvm);
                pmm.setPvmmodel(perangkatVMModelbyid);
                list.add(pmm);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(PenyebabDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenyebabDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenyebabDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }  
     
    
    
    }
    
}
