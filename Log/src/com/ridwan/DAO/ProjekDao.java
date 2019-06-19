/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.DAO;

import com.ridwan.DBC.DBC;
import com.ridwan.Interface.ProjekInterface;
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
public class ProjekDao implements ProjekInterface{
    
    Connection connection;

    public ProjekDao() {
    connection = DBC.getConnection();
    }
    
    

    @Override
    public boolean insertProjek(ProjectModel zm) {
   boolean valid = false;
        PreparedStatement statement = null;     
        String sql = "insert into Projek (No_Projek, Nama_Projek, initial) values (?,?,?)";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,zm.getNoprojek());
            statement.setString(2, zm.getNamaprojek());
            statement.setString(3, zm.getInit());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(ProjekDao.class.getName()).log(Level.SEVERE, null, ex);
        valid = false;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProjekDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
        
   }

    @Override
    public boolean updateProjek(ProjectModel zm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteProjek(ProjectModel zm) {
    boolean valid = false;
    PreparedStatement statement =null;
    String sql = "delete from projek where No_Projek=?";
    
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, zm.getNoprojek());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(ProjekDao.class.getName()).log(Level.SEVERE, null, ex);
        valid = false;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProjekDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    
    }

    @Override
    public List<ProjectModel> getProjekModels() {
    List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement statement = null;
        
        String sql = "select * from Projek where b_active='t'";
        try {
            statement = connection.prepareStatement(sql);
        rs = statement.executeQuery();
        while (rs.next()){
            ProjectModel model = new ProjectModel();
            model.setNoprojek(rs.getInt("No_Projek"));
            model.setNamaprojek(rs.getString("Nama_Projek"));
            model.setInit(rs.getString("initial"));
            list.add(model);
        }return list;
        } catch (SQLException ex) {
            Logger.getLogger(ProjekDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProjekDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProjekDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public ProjectModel getbyid(int id) {
    PreparedStatement statement = null;
    ResultSet rs = null;
    ProjectModel zm = null;
    String sql = "select * from Projek where No_Projek=?";
    
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()){
                zm = new ProjectModel();
                zm.setNoprojek(rs.getInt("No_Projek"));
                zm.setNamaprojek(rs.getString("Nama_Projek"));
                zm.setInit(rs.getString("initial"));
            }return zm;
        } catch (SQLException ex) {
            Logger.getLogger(ProjekDao.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProjekDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProjekDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    
}
