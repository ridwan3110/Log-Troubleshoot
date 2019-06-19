/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.DAO;

import com.ridwan.DBC.DBC;
import com.ridwan.Interface.ETInterface;
import com.ridwan.Model.ETModel;
import com.ridwan.Model.StasiunModel;
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
public class ETDao  implements ETInterface {
    
    private Connection connection;
    private StasiunDao stasiunDao = new StasiunDao();
    
    public ETDao() {
    connection = DBC.getConnection();
    }
    
    
    

    @Override
    public boolean insertET(ETModel etm) {
    boolean valid = false;
        PreparedStatement statement = null;
        String sql = "insert into et (No_Perangkat, Nama_Perangkat, ID, Type, IP) values (?,?,?,?,?)";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, etm.getNoPerangkat());
            statement.setString(2, etm.getNamaPerangkat());
            statement.setString(3, etm.getId());
            statement.setString(4, etm.getType());
            statement.setInt(5, etm.getStasiun().getIp());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(ETDao.class.getName()).log(Level.SEVERE, null, ex);
        valid = false;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean updateET(ETModel etm) {
    boolean valid = false;
    PreparedStatement statement = null;
    String sql = "update et set Nama_Perangkat=?, ID=?, Type=?, IP=? where No_Perangkat=?";
    
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, etm.getNamaPerangkat());
            statement.setString(2, etm.getId());
            statement.setString(3, etm.getType());
            statement.setInt(4, etm.getStasiun().getIp());
            statement.setInt(5, etm.getNoPerangkat());
            statement.executeUpdate();
            valid =true;
        } catch (SQLException ex) {
            Logger.getLogger(ETDao.class.getName()).log(Level.SEVERE, null, ex);
        valid = false;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean deleteET(ETModel etm) {
    boolean valid = false;
    PreparedStatement statement = null;
    String sql = "delete from et where No_Perangkat=?";
    
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, etm.getNoPerangkat());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(ETDao.class.getName()).log(Level.SEVERE, null, ex);
        valid = false;
        }finally {
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        }
        return valid;
    }

    @Override
    public List<ETModel> getETModels() {
    List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement statement = null;
        String sql = "select * from et";
        
        try {
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while(rs.next()){
                ETModel et = new ETModel();
                et.setNoPerangkat(rs.getInt("No_Perangkat"));
                et.setNamaPerangkat(rs.getString("Nama_Perangkat"));
                et.setId(rs.getString("ID"));
                et.setType(rs.getString("Type"));
                int byid = rs.getInt("IP");
                StasiunModel stasiunModel = stasiunDao.getStasiunModel(byid);
                et.setStasiun(stasiunModel);
                list.add(et);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(ETDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
                        
    }

    @Override
    public ETModel getEtmodelByid(int id) {
   // List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement statement = null;
        ETModel et = null;
        
        String sql = "select * from et where No_Perangkat=?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while(rs.next()){
                et = new ETModel();
                et.setNoPerangkat(rs.getInt("No_Perangkat"));
                et.setNamaPerangkat(rs.getString("Nama_Perangkat"));
                et.setId(rs.getString("ID"));
                et.setType(rs.getString("Type"));
                 int byid = rs.getInt("IP");
                StasiunModel stasiunModel = stasiunDao.getStasiunModel(byid);
                et.setStasiun(stasiunModel);
            }return et;
        } catch (SQLException ex) {
            Logger.getLogger(ETDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
              
    
    }

    @Override
    public List<ETModel> getEtModelbyIDPerangkatANDstasiun(String id, String lokasi) {
    List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement statement = null;
        String sql =  "select * from et inner join stasiun on (et.IP= stasiun.IP) where et.ID=? and stasiun.Nama_Stasiun =?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.setString(2, lokasi);
            rs = statement.executeQuery();
            while(rs.next()){
                ETModel et = new ETModel();
                et.setNoPerangkat(rs.getInt("No_Perangkat"));
                et.setNamaPerangkat(rs.getString("Nama_Perangkat"));
                et.setId(rs.getString("ID"));
                et.setType(rs.getString("Type"));
                 int byid = rs.getInt("IP");
                StasiunModel stasiunModel = stasiunDao.getStasiunModel(byid);
                et.setStasiun(stasiunModel);
                list.add(et);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(ETDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }  
    }

    @Override
    public List<ETModel> getEtModelbyStasiun(String lokasi) {
     List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement statement = null;
        String sql = "select * from et inner join stasiun on (et.IP= stasiun.IP) where stasiun.Nama_Stasiun =?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, lokasi);
            rs = statement.executeQuery();
            while(rs.next()){
                ETModel et = new ETModel();
                et.setNoPerangkat(rs.getInt("No_Perangkat"));
                et.setNamaPerangkat(rs.getString("Nama_Perangkat"));
                et.setId(rs.getString("ID"));
                et.setType(rs.getString("Type"));
                 int byid = rs.getInt("IP");
                StasiunModel stasiunModel = stasiunDao.getStasiunModel(byid);
                et.setStasiun(stasiunModel);
                list.add(et);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(ETDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }  
    }

    @Override
    public List<ETModel> getrangelist(int a, int b) {
    List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement statement = null;
        String sql = "select * from et where No_Perangkat between ? and ?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, a);
            statement.setInt(2, b);
            rs = statement.executeQuery();
            while(rs.next()){
                ETModel et = new ETModel();
                et.setNoPerangkat(rs.getInt("No_Perangkat"));
                et.setNamaPerangkat(rs.getString("Nama_Perangkat"));
                et.setId(rs.getString("ID"));
                et.setType(rs.getString("Type"));
                int byid = rs.getInt("IP");
                StasiunModel stasiunModel = stasiunDao.getStasiunModel(byid);
                et.setStasiun(stasiunModel);
                list.add(et);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(ETDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } 
    
    }

    @Override
    public ETModel Maxdata() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ETModel Mindata() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ETModel> getidbyperangkat(String Lokasi, String Perangkat) {
     List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement statement = null;
        String sql = "select * from et INNER JOIN stasiun on (et.IP=stasiun.IP) where stasiun.Nama_Stasiun=? AND Nama_Perangkat=?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, Lokasi);
            statement.setString(2, Perangkat);
            rs = statement.executeQuery();
            while(rs.next()){
                ETModel et = new ETModel();
                et.setNoPerangkat(rs.getInt("No_Perangkat"));
                et.setNamaPerangkat(rs.getString("Nama_Perangkat"));
                et.setId(rs.getString("ID"));
                et.setType(rs.getString("Type"));
                 int byid = rs.getInt("IP");
                StasiunModel stasiunModel = stasiunDao.getStasiunModel(byid);
                et.setStasiun(stasiunModel);
                list.add(et);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(ETDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ETDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }     
    
    }

   
    
}
