/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.TableModel;

import com.ridwan.Model.TroubleVMModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ciwong
 */
public class TableModelTroubleVM extends AbstractTableModel{

    List<TroubleVMModel> list = new ArrayList();
    
    
    @Override
    public int getRowCount() {
    return list.size();
    }

    @Override
    public int getColumnCount() {
    return 17;    
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    switch(columnIndex){
        case 0 : return list.get(rowIndex).getNoVM();
        case 1 : return list.get(rowIndex).getTglMasalah();
        case 2 : return list.get(rowIndex).getJamDone();
        case 3 : return list.get(rowIndex).getTglDone();
        case 4 : return list.get(rowIndex).getJamDone();
        case 5 : return list.get(rowIndex).getSm().getNamastasiun();
        case 6 : return list.get(rowIndex).getEtm().getId();
        case 7 : return list.get(rowIndex).getEtm().getType();
        case 8 : return list.get(rowIndex).getEtm().getNamaPerangkat();
        case 9 : return list.get(rowIndex).getProblem();
        case 10 : return list.get(rowIndex).getSolusi();
        case 11: return list.get(rowIndex).getPvmm().getNamaPerangkat();
        case 12 : return list.get(rowIndex).getPm().getNamaPenyebab();
        case 13 : return list.get(rowIndex).getStatus();
        case 14 : return list.get(rowIndex).getAnalisa();
        case 15 : return list.get(rowIndex).getPromvm().getNamaprojek();
        case 16 : return list.get(rowIndex).isCek();
        default : return null;
    }    
    }
    

    @Override
    public String getColumnName(int column){
    switch (column){
        case 0 : return "No";
        case 1 : return "Tanggal Masalah";
        case 2 : return "Jam Masalah";
        case 3 : return "Tanggal Done";
        case 4 : return "Jam Done";
        case 5 : return "Lokasi";
        case 6 : return "ID";
        case 7 : return "Type";
        case 8 : return "Nama Perangkat";
        case 9 : return "Problem";
        case 10 : return "Solusi";
        case 11 : return "Perangkat VM";
        case 12 : return "Penyebab";
        case 13 : return "Status";
        case 14 : return "Analisa";
        case 15 : return "Projek";
        case 16 : return "";
        default : return null;
    }
}
    
    
    public void insertTroubleVM(TroubleVMModel tvmm){
        this.list.add(tvmm);
        fireTableDataChanged();
    }
    
    public void updateTroubleVM(int index,TroubleVMModel tvmm){
        this.list.set(index, tvmm);
        fireTableDataChanged();
    }
    
    
    public void deleteTroubleVM (TroubleVMModel tvmm){
        this.list.remove(tvmm);
        fireTableDataChanged();
    }
    
    public void setData(List<TroubleVMModel> list){
        this.list = list;
        fireTableDataChanged();
    }
    
    public TroubleVMModel getTroubleVM (int index){
        return list.get(index);
    }
    
    public void clear (){
        list.clear();
    }
    
    
    public List<TroubleVMModel> getTroubleVMCek(){
        List ls = new ArrayList();
        for (TroubleVMModel tv : list){
            if (tv.isCek()){
                ls.add(tv);
            }
        }
        return ls;
    }
    
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return true;
    }
    
    public boolean TroubleVMCek(boolean cek){
        return list.equals(cek);
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex){
        if (columnIndex==16){
            return Boolean.class;
        }else {
            return super.getColumnClass(columnIndex);
        }
    }
    
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex){
        if (aValue!=null && aValue instanceof Boolean && columnIndex==16){
            Boolean cek =(Boolean) aValue;
            list.get(rowIndex).setCek(cek);
        }
    }
    
    

}

