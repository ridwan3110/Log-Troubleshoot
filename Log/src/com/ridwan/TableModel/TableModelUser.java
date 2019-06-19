/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.TableModel;

import com.ridwan.Model.UserModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ciwong
 */
public class TableModelUser extends AbstractTableModel{
    
    
    List<UserModel> list= new ArrayList();

    public TableModelUser() {
    
    }
    
    

    @Override
    public int getRowCount() {
    return list.size();
    }

    @Override
    public int getColumnCount() {
    return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0 : return list.get(rowIndex).getNo();
            case 1 : return list.get(rowIndex).getNama();
            case 2 : return list.get(rowIndex).getUsr();
            //case 3 : return list.get(rowIndex).getPwd();
            case 3 : return list.get(rowIndex).getAlamat();
            case 4 : return list.get(rowIndex).getTlp();
            case 5 : return list.get(rowIndex).getStatus();
            case 6 : return list.get(rowIndex).isCek();
            default : return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        switch (column){
            case 0 : return "No";
            case 1 : return "Nama";
            case 2 : return "Username";
            case 3 : return "Alamat";
            case 4 : return "Telpon";
            case 5 : return "Status";
            case 6 : return "";
            default : return null;
        }
    }
    
    public void inserUser(UserModel um){
        this.list.add(um);
        fireTableDataChanged();
    }
    
    public void updateUser(int index, UserModel um){
        this.list.set(index, um);
        fireTableDataChanged();
    }
    
    public void deleteUser(int index){
        this.list.remove(index);
        fireTableDataChanged();
    }
    
    public void setData(List<UserModel> list){
        this.list = list;
        fireTableDataChanged();
    }
    
    public UserModel getUser(int index){
        return list.get(index);
    }
    
    public void clear (){
        list.clear();
    }
    
    public List<UserModel> getUsercek(){
        List ls = new ArrayList();
        for (UserModel u : list){
            if (u.isCek()){
                ls.add(u);
            }
        }
        return ls;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return true;
    }
    
     public boolean Usercek(boolean cek){
        return list.equals(cek);
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex){
        if (columnIndex==6){
            return Boolean.class;
        }else {
            return super.getColumnClass(columnIndex);
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex){
        if (aValue!=null && aValue instanceof Boolean && columnIndex==6){
            Boolean cek = (Boolean) aValue;
            list.get(rowIndex).setCek(cek);
        }
    }
    
    
}
