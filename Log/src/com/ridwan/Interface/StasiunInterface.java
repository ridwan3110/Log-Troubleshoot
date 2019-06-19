/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Interface;

import com.ridwan.Model.ProjectModel;
import com.ridwan.Model.StasiunModel;
import java.util.List;

/**
 *
 * @author ciwong
 */
public interface StasiunInterface {
    boolean insertStasiun(StasiunModel sm);
    boolean updateStasiun (StasiunModel sm);
    boolean deleteStasiun (StasiunModel sm);
    List<StasiunModel> getStasiunModels();
    StasiunModel getStasiunModel(int id);
     List<StasiunModel> getModelsByprojek(ProjectModel projek);
    
}
