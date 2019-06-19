/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.TableModel;

import com.ridwan.Model.ETModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ciwong
 */
public class TableModelET extends AbstractTableModel{
    List<ETModel> list = new ArrayList();
    
    
    
    private int position = 0;

    

    
    @Override
    public int getRowCount() {
        return list.size();
    }
      
    @Override
    public int getColumnCount() {
    return 6;    
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    switch (columnIndex){
        case 0 : return list.get(rowIndex).getNoPerangkat();
        case 1 : return list.get(rowIndex).getNamaPerangkat();
        case 2 : return list.get(rowIndex).getId();
        case 3 : return list.get(rowIndex).getType();
        case 4 : return list.get(rowIndex).getStasiun().getNamastasiun();
        case 5 : return list.get(rowIndex).isCek();
        default : return null;
    }
        
    }
    
    
    
    
    
    
    
    
    
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0 : return "No";
            case 1 : return "Nama Perangkat";
            case 2 : return "ID";
            case 3 : return "Type";
            case 4 : return "Lokasi";
            case 5 : return "";
            default : return null;
        }
    }
    
    
    public void insertET(ETModel etm){
        this.list.add(etm);
        fireTableDataChanged();
    }
    
    
    public void updateET(int index, ETModel etm){
        this.list.set(index, etm);
        fireTableDataChanged();
    }
    
    public void deleteET(int index){
        this.list.remove(index);
        fireTableDataChanged();
    }
    
    public void setData (List<ETModel> list){
        this.list = list;
        fireTableDataChanged();
    }
    
    public ETModel getET(int index){
        return list.get(index);
    }
    
    public void clear (){
        list.clear();
    }
    
    
    public List<ETModel> getETcek(){
        List ls =new ArrayList();
        for (ETModel e : list){
            if (e.isCek()){
                ls.add(e);
            }
        }
        return ls;
    }
    
    
    @Override
    public boolean isCellEditable (int rowIndex, int columnIndex){
        return true;
    }
    
    public boolean ETcek(boolean cek){
        return list.equals(cek);
    }
    
    
    @Override
    public Class<?> getColumnClass (int columnIndex){
        if (columnIndex==5){
            return Boolean.class;
        }else {
            return super.getColumnClass(columnIndex);
        }
    }
    
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex){
        if (aValue!=null && aValue instanceof Boolean && columnIndex==5){
            Boolean cek = (Boolean) aValue;
        list.get(rowIndex).setCek(cek);
        }
    }
    
    
    
    
    
}
