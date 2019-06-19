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
 * @author Muhammad Ridwan
 */
public class TableModelExportExcel extends AbstractTableModel{
    
    List<TroubleEtModel> list = new ArrayList();
    
    @Override
    public int getRowCount() {
    return list.size();
    }

    @Override
    public int getColumnCount() {
    return 20;    
    }
    
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    switch (columnIndex){
        case 0 : return list.get(rowIndex).getNoEt();
        case 1 : return list.get(rowIndex).getTglMasalah();
        case 2 : return list.get(rowIndex).getJamMasalah();
        case 3 : return list.get(rowIndex).getTglDone();
        case 4 : return list.get(rowIndex).getJamDone();
        case 5 : return list.get(rowIndex).getTotalDowntime();
        case 6 : return list.get(rowIndex).getJenisLaporan();
        case 7 : return list.get(rowIndex).getProm().getNamaprojek();
        case 8 : return list.get(rowIndex).getSm().getNamastasiun();
        case 9 : return list.get(rowIndex).getEtm();
        case 10 : return list.get(rowIndex).getEtm().getId();
        case 11 : return list.get(rowIndex).getEtm().getType();
        case 12 : return list.get(rowIndex).getPart();
        case 13: return list.get(rowIndex).getProblem();
        case 14 : return list.get(rowIndex).getPm();
        case 15 : return list.get(rowIndex).getSolusi();
        case 16: return list.get(rowIndex).getSumber();
        case 17 : return list.get(rowIndex).getStatus();
        case 18 : return list.get(rowIndex).getRef();
        case 19 : return list.get(rowIndex).getUm().getNama();
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
            case 5 : return "Downtime";
            case 6 : return "Jenis Laporan";
            case 7 : return "Nama Projek";
            case 8 : return "Nama Stasiun";
            case 9 : return "Nama Perangkat";
            case 10 : return "ID";
            case 11 : return "Type";
            case 12 : return "Part";
            case 13 : return "Problem";
            case 14 : return "Penyebab";
            case 15 : return "Solusi";
            case 16 : return "Sumber";
            case 17 : return "Status";
            case 18 : return "ref Logistik";
            case 19 : return "User";
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
    
   /*
    public List<TroubleEtModel> getTECek(){
        List ls = new ArrayList();
        for (TroubleEtModel t : list){
            if (t.isCek()){
                ls.add(t);
            }
        }
        return ls;
    }*/
    
    
    @Override
    public boolean isCellEditable (int rowIndex, int columnIndex){
        return true;
    }
    /*
    public boolean TECek(boolean cek){
        return list.equals(cek);
    }
    /*
    @Override
    public Class<?> getColumnClass(int columnIndex){
        if (columnIndex==18){
            return Boolean.class;
        }else {
            return super.getColumnClass(columnIndex);
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex){
        if (aValue!=null && aValue instanceof Boolean && columnIndex==18){
            Boolean cek = (Boolean) aValue;
            list.get(rowIndex).setCek(cek);
        }
    }
    
    
   */ 
    
}
