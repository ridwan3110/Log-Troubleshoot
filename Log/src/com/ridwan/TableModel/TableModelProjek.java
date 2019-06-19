/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.TableModel;

import com.ridwan.Model.ProjectModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ciwong
 */
public class TableModelProjek extends AbstractTableModel{
    
    List<ProjectModel> list = new ArrayList();

    @Override
    public int getRowCount() {
    return list.size();
    }

    @Override
    public int getColumnCount() {
    return 4;    
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0 : return list.get(rowIndex).getNoprojek();
            case 1 : return list.get(rowIndex).getNamaprojek();
            case 2 : return list.get(rowIndex).getInit();
            case 3 : return list.get(rowIndex).isCek();
            default : return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        switch (column){
            case 0 : return "No";
            case 1 : return "Nama Projek";
            case 2 : return "Initial";
            case 3 : return "";
            default : return null;
        }
    }
    
    public void insertZona(ProjectModel zm){
        this.list.add(zm);
        fireTableDataChanged();
    }
    
    public void updateUser(int index, ProjectModel zm){
        this.list.set(index, zm);
        fireTableDataChanged();
    }
    
    public void deleteUser(int index){
        this.list.remove(index);
        fireTableDataChanged();
    }
    
    public void setData (List<ProjectModel> list){
        this.list = list;
        fireTableDataChanged();
    }
    
    public ProjectModel getZona(int index){
        return list.get(index);
    }
    
    public void clear(){
        list.clear();
    } 
    
    
    public List<ProjectModel> getZonacek(){
        List ls = new ArrayList();
        for (ProjectModel zm : list){
            if (zm.isCek()){
                ls.add(zm);
            }
        }
        return ls;
    }
    
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return true;
    }
    
    public boolean Zonacek(boolean cek){
        return list.equals(cek);
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex){
        if (columnIndex==3){
            return Boolean.class;
        }else {
            return super.getColumnClass(columnIndex);
        }
    }
    
    
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex){
        if (aValue!=null && aValue instanceof Boolean && columnIndex==3){
            Boolean cek = (Boolean) aValue;
            list.get(rowIndex).setCek(cek);
        }
    }
}






