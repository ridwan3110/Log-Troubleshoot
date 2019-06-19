/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Main;

import com.ridwan.DBC.DBC;
import javax.swing.JOptionPane;

/**
 *
 * @author ciwong
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        if (DBC.getConnection()!=null){
            JOptionPane.showMessageDialog(null, "Koneksi Berhasil");
        }else {
            JOptionPane.showMessageDialog(null,"Koneksi Gagal");
        }
        // TODO code application logic here
    }
    
}
