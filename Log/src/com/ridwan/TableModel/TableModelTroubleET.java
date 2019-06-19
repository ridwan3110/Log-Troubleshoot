/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.TableModel;

import com.ridwan.Model.TroubleEtModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ciwong
 */
public class TableModelTroubleET extends AbstractTableModel{

    List<TroubleEtModel> list = new ArrayList();
    
    @Override
    public int getRowCount() {
    return list.size();
    }

    @Override
    public int getColumnCount() {
    return 15;    
    }
    
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    switch (columnIndex){
        case 0 : return list.get(rowIndex).getNoEt();
        case 1 : return list.get(rowIndex).getTglDone();
        case 2 : return list.get(rowIndex).getJamDone();
        case 3 : return list.get(rowIndex).getJenisLaporan();
        case 4 : return list.get(rowIndex).getProm().getNamaprojek();
        case 5 : return list.get(rowIndex).getSm().getNamastasiun();
        case 6 : return list.get(rowIndex).getEtm().getNamaPerangkat();
        case 7 : return list.get(rowIndex).getPart();
        case 8 : return list.get(rowIndex).getProblem();
        case 9 : return list.get(rowIndex).getPm();
        case 10 : return list.get(rowIndex).getSolusi();
        case 11 : return list.get(rowIndex).getStatus();
        case 12 : return list.get(rowIndex).getTotalDowntime();
        case 13 : return list.get(rowIndex).getRef();
        case 14 : return list.get(rowIndex).isCek();
        default : return null;
    }
    }
    
    @Override
    public String getColumnName(int column){
        switch (column){
            case 0 : return "No";
            case 1 : return "Tanggal Done";
            case 2 : return "Jam Done";
            case 3 : return "Jenis Laporan";
            case 4 : return "Nama Projek";
            case 5 : return "Nama Stasiun";
            case 6 : return "Nama Perangkat";
            case 7 : return "Part";
            case 8 : return "Problem";
            case 9 : return "Penyebab";
            case 10 : return "Solusi";
            case 11 : return "Status";
            case 12 : return "Durasi";
            case 13 : return "RefLog";
            case 14 : return "";
            default : return null;
    }
    }
    
    public void insertTroubleET(TroubleEtModel tem){
        this.list.add(tem);
        fireTableDataChanged();
    }
    
    public void updateTroubleET(int index,TroubleEtModel tem){
        this.list.set(index, tem);
        fireTableDataChanged();
    }
    
    
    public void deleteTroubleET (int index){
        this.list.remove(index);
        fireTableDataChanged();
    }
    
    public void setData(List<TroubleEtModel> list){
        this.list=list;
        fireTableDataChanged();
    }
    
    
    public TroubleEtModel getTroubleET(int index){
        return list.get(index);
    }
    
    public void clear(){
        list.clear();
    }
    
    
    public List<TroubleEtModel> getTECek(){
        List ls = new ArrayList();
        for (TroubleEtModel t : list){
            if (t.isCek()){
                ls.add(t);
            }
        }
        return ls;
    }
    
    
    @Override
    public boolean isCellEditable (int rowIndex, int columnIndex){
        return true;
    }
    
    public boolean TECek(boolean cek){
        return list.equals(cek);
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex){
        if (columnIndex==14){
            return Boolean.class;
        }else {
            return super.getColumnClass(columnIndex);
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex){
        if (aValue!=null && aValue instanceof Boolean && columnIndex==14){
            Boolean cek = (Boolean) aValue;
            list.get(rowIndex).setCek(cek);
        }
    }
    
}
