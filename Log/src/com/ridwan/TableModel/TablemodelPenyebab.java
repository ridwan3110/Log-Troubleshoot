/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.TableModel;

import com.ridwan.Model.PenyebabModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ciwong
 */
public class TablemodelPenyebab extends AbstractTableModel{

    List<PenyebabModel> list = new ArrayList();
    
    @Override
    public int getRowCount() {
    return list.size();
    }

    @Override
    public int getColumnCount() {
    return 5;    
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0 : return list.get(rowIndex).getNoPenyebab();
            case 1 : return list.get(rowIndex).getNamaPenyebab();
            case 2 : return list.get(rowIndex).getKategori();
            case 3 : return list.get(rowIndex).getPvmmodel();
            case 4 : return list.get(rowIndex).isCek();
            default : return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0 : return "No";
            case 1 : return "Nama Penyebab";
            case 2 : return "Kategori";
            case 3 : return "Perangkat";
            case 4 : return "";
            default : return null;
        }
    }
    
    
    public void insertPenyebab(PenyebabModel pm){
        this.list.add(pm);
        fireTableDataChanged();
    }
    
    
    public void updatePenyebab (int index, PenyebabModel pm){
        this.list.set(index, pm);
        fireTableDataChanged();
    }
    
    
    public void deletePenyebab (PenyebabModel pm){
        this.list.remove(pm);
       fireTableDataChanged();
    }
    
    
    public void setData(List<PenyebabModel> list){
        this.list = list;
        fireTableDataChanged();
    }
    
    
    public PenyebabModel getPenyebab (int index){
        return list.get(index);
    }
    
    public void clear (){
        list.clear();
    }
    
    
    
    public List<PenyebabModel> getPenyebabCek(){
        List ls = new ArrayList();
        for (PenyebabModel pp : list){
            if (pp.isCek()){
                ls.add(pp);
            }
        }
        return ls;
    }
    
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return true;
    }
    
   
    public boolean PenyebabCek(boolean cek){
        return list.equals(cek);
    }
   
    @Override
    public Class<?> getColumnClass(int columnIndex){
        if (columnIndex==4){
            return Boolean.class;
        }else {
            return super.getColumnClass(columnIndex);
        }
    }
    
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex){
        if (aValue!=null&& aValue instanceof Boolean && columnIndex==4){
            Boolean cek = (Boolean) aValue;
            list.get(rowIndex).setCek(cek);
        }
    }
    
    
    
}
