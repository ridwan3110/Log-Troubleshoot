/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.TableModel;

import com.ridwan.Model.PerangkatVMModel;
import com.ridwan.Model.TroubleEtModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ciwong
 */
public class TableModelgrafik extends AbstractTableModel{

    List<TroubleEtModel> list = new ArrayList();
    
    @Override
    public int getRowCount() {
    return list.size();
    }

    @Override
    public int getColumnCount() {
    return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    switch (columnIndex){
        case 0 : return list.get(rowIndex).getNamaPerangkat();
        case 1 : return list.get(rowIndex).getCount();
        case 2 : return list.get(rowIndex).isCek();
        default : return null;
    }
    }
    
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0 : return "Nama Perangkat VM";
            case 1 : return "Count";
            case 2 : return "";
            default : return null;
        }
    }
    
    public void insertPVM(TroubleEtModel tem){
        this.list.add(tem);
        fireTableDataChanged();
    }
    
    public void updatePVM(int index,TroubleEtModel tem){
        this.list.set(index, tem);
        fireTableDataChanged();
    }
    
    public void deletePVM (int index){
        this.list.remove(index);
        fireTableDataChanged();
    }
    
    public void setData (List <TroubleEtModel> list){
        this.list = list;
        fireTableDataChanged();
    }
    
    public TroubleEtModel getTEM(int index){
        return list.get(index);
    }
    
    public void clear(){
        list.clear();
    }
    
    
    public List<TroubleEtModel> getTEMcek(){
        List ls = new ArrayList();
        for (TroubleEtModel t : list){
            if (t.isCek()){
                ls.add(t);
            }
        }
        return ls;
    }
    
    
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return true;
    }
    
    public boolean TEMcek(boolean cek){
        return list.equals(cek);
    }
    
    
    @Override
    public Class<?> getColumnClass(int columnIndex){
        if (columnIndex==2){
            return Boolean.class;
        }else {
            return super.getColumnClass(columnIndex);
        }
    }
    
    
    @Override
    public void setValueAt (Object aValue, int rowIndex, int columnIndex){
        if (aValue!=null && aValue instanceof Boolean &&columnIndex == 2){
            Boolean cek = (Boolean) aValue;
            list.get(rowIndex).setCek(cek);
        }
    }
    
    
    
    
    
}
