/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Interface;

import com.ridwan.Model.ProjectModel;
import java.util.List;

/**
 *
 * @author ciwong
 */
public interface ProjekInterface {
    boolean insertProjek(ProjectModel zm);
    boolean updateProjek (ProjectModel zm);
    boolean deleteProjek (ProjectModel zm);
    List<ProjectModel> getProjekModels();
    ProjectModel getbyid(int id);
}
