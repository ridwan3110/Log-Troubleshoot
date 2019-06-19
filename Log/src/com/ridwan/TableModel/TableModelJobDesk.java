/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.TableModel;

import com.ridwan.Model.JobdeskModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ciwong
 */
public class TableModelJobDesk extends AbstractTableModel{
    
    List<JobdeskModel> list = new ArrayList();

    @Override
    public int getRowCount() {
    return list.size();
    }

    @Override
    public int getColumnCount() {
    return 14;    
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    switch(columnIndex){
        case 0 : return list.get(rowIndex).getNoJd();
        case 1 : return list.get(rowIndex).getTglMasalah();
        case 2 : return list.get(rowIndex).getJamMasalah();
        case 3 : return list.get(rowIndex).getTglDone();
        case 4 : return list.get(rowIndex).getJamDone();
        case 5 : return list.get(rowIndex).getSm().getNamastasiun();
        case 6 : return list.get(rowIndex).getProblem();
        case 7 : return list.get(rowIndex).getPm().getNamaPenyebab();
        case 8 : return list.get(rowIndex).getSolusi();
        case 9 : return list.get(rowIndex).getEtm().getNamaPerangkat();
        case 10 : return list.get(rowIndex).getSaldosblm();
        case 11 : return list.get(rowIndex).getSaldossdh();
        case 12 : return list.get(rowIndex).getStatus();
        case 13 : return list.get(rowIndex).isCek();
        default : return null;
    }    
    }
    
    public String getColumnName (int column){
        switch (column){
            case 0 : return "No";
            case 1 : return "Tanggal Masalah";
            case 2 : return "Jam Masalah";
            case 3 : return "Tanggal Done";
            case 4 : return "Jam Done";
            case 5 : return "Stasiun";
            case 6 : return "Problem";
            case 7 : return "Penyebab";
            case 8 : return "Solusi";
            case 9 : return "Nama Perangkat";
            case 10 : return "Saldo Sebelum";
            case 11 : return "Saldo Sesudah";
            case 12 : return "Status";
            case 13 : return "";
            default : return null;
        }
    }
    
    public void insertJobdesk(JobdeskModel jm){
        this.list.add(jm);
        fireTableDataChanged();
    }
    
    public void updateJobdesk(int index,JobdeskModel jm){
        this.list.set(index, jm);
        fireTableDataChanged();
    }
    
    public void deleteJobdesk(int index){
        this.list.remove(index);
        fireTableDataChanged();
    }
    
    public void setData(List<JobdeskModel> list){
        this.list=list;
        fireTableDataChanged();
    }
    
    public JobdeskModel getJobdesk(int index){
        return list.get(index);
    }
    
    public void clear(){
        list.clear();
    }
    
    public List<JobdeskModel> getjobdeskcek(){
        List ls = new ArrayList();
        for (JobdeskModel j : list ){
            if (j.isCek()){
                ls.add(j);
            }
        }
        return ls;
    }
    
    
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return true;
    }
    
    public boolean jobdeskcek(boolean cek){
        return list.equals(cek);
    }
    
    
    public Class<?> getColumnClass (int columnIndex){
        if (columnIndex==13){
            return Boolean.class;
        }else {
            return super.getColumnClass(columnIndex);
        }
    }
    
    public void setValueAt (Object aValue, int rowIndex, int columnIndex){
        if (aValue!=null && aValue instanceof Boolean && columnIndex==13){
            Boolean cek = (Boolean) aValue;
            list.get(rowIndex).setCek(cek);
        }
    }
}
