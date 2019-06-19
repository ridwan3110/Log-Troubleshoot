/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.DBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;





public class DBC {
    
    
    
    private static Connection connection;
    public static Connection getConnection(){
        try {
           // String url = "jdbc:mysql://localhost/log";
            String url = "jdbc:mysql://100.100.200.229/log";
            String usr = "root";
            String pwd = "backend123456";
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(url, usr, pwd);
        } catch (SQLException ex) {
            Logger.getLogger(DBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
    
}
