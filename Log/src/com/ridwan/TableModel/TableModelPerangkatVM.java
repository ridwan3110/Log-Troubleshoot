/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.TableModel;

import com.ridwan.Model.PerangkatVMModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ciwong
 */
public class TableModelPerangkatVM extends AbstractTableModel{

    List<PerangkatVMModel> list = new ArrayList();
    
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
    switch (columnIndex){
        case 0 : return list.get(rowIndex).getNoPVM();
        case 1 : return list.get(rowIndex).getNamaPerangkat();
        case 2 : return list.get(rowIndex).getJenis();
        case 3 : return list.get(rowIndex).isCek();
        default : return null;
    }
    }
    
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0 : return "No";
            case 1 : return "Nama Perangkat VM";
            case 2 : return "Jenis";
            case 3 : return "";
            default : return null;
        }
    }
    
    public void insertPVM(PerangkatVMModel pvmm){
        this.list.add(pvmm);
        fireTableDataChanged();
    }
    
    public void updatePVM(int index,PerangkatVMModel pvmm){
        this.list.set(index, pvmm);
        fireTableDataChanged();
    }
    
    public void deletePVM (int index){
        this.list.remove(index);
        fireTableDataChanged();
    }
    
    public void setData (List <PerangkatVMModel> list){
        this.list = list;
        fireTableDataChanged();
    }
    
    public PerangkatVMModel getPVM(int index){
        return list.get(index);
    }
    
    public void clear(){
        list.clear();
    }
    
    
    public List<PerangkatVMModel> getPVMcek(){
        List ls = new ArrayList();
        for (PerangkatVMModel p : list){
            if (p.isCek()){
                ls.add(p);
            }
        }
        return ls;
    }
    
    
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return true;
    }
    
    public boolean PVMcek(boolean cek){
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
    public void setValueAt (Object aValue, int rowIndex, int columnIndex){
        if (aValue!=null && aValue instanceof Boolean &&columnIndex == 3){
            Boolean cek = (Boolean) aValue;
            list.get(rowIndex).setCek(cek);
        }
    }
    
    
    
    
    
}
