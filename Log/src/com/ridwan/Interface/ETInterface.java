/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Interface;

import com.ridwan.Model.ETModel;
import com.ridwan.Model.ProjectModel;
import com.ridwan.Model.StasiunModel;
import java.util.List;

/**
 *
 * @author ciwong
 */
public interface ETInterface {
    boolean insertET (ETModel etm);
    boolean updateET (ETModel etm);
    boolean deleteET (ETModel etm);
    List<ETModel> getETModels();
    ETModel getEtmodelByid(int id);
    List<ETModel> getEtModelbyIDPerangkatANDstasiun(String id, String lokasi);
    List<ETModel> getEtModelbyStasiun(String lokasi);
    List<ETModel> getrangelist(int a, int b);
    ETModel Maxdata();
    ETModel Mindata();
    List<ETModel> getidbyperangkat(String Lokasi, String Perangkat);
    
}
