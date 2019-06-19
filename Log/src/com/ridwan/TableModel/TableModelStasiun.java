/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.TableModel;

import com.ridwan.Model.StasiunModel;
//import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ciwong
 */
public class TableModelStasiun extends AbstractTableModel{

    List<StasiunModel> list = new ArrayList();
    
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
        case 0 : return list.get(rowIndex).getIp();
        case 1 : return list.get(rowIndex).getNamastasiun();
       // case 2 : return list.get(rowIndex).getZm().getNamaZona();
        case 2 : return list.get(rowIndex).getPm().getNamaprojek();
        case 3 : return list.get(rowIndex).isCek();
        default : return null;
    }    
    
    }
    
    
    @Override
    public String getColumnName (int column){
        switch (column){
            case 0 : return "No";
            case 1 : return "Nama Stasiun";
           // case 2 : return "Zona";
            case 2 : return "Project";
            case 3 : return "";
            default : return null;
        }
    }
    
    
    public void insertStasiun (StasiunModel sm){
        this.list.add(sm);
        fireTableDataChanged();
    }
    
    public void updateStasiun (int index, StasiunModel sm){
        this.list.set(index, sm);
        fireTableDataChanged();
    }
    
    public void deleteStasiun (int index){
        this.list.remove(index);
        fireTableDataChanged();
    }
    public void setData (List<StasiunModel> list){
        this.list=list;
        fireTableDataChanged();
    }
    
    
    public StasiunModel getStasiun (int index){
        return list.get(index);
    }
    
    
    public void clear (){
        list.clear();
    }
    
    
    public List<StasiunModel> getStasiunCek(){
        List ls = new ArrayList();
        for (StasiunModel s : list){
            if (s.isCek()){
                ls.add(s);
            }
        }
        return ls;
    }
    
    
    @Override
    public boolean isCellEditable (int rowIndex, int columnIndex){
        return true;
    }
    
    public boolean StasiunCek(boolean cek){
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
