/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Interface;

import com.ridwan.Model.PenyebabModel;
import com.ridwan.Model.PerangkatVMModel;
import java.util.List;

/**
 *
 * @author ciwong
 */
public interface PenyebabInterface {
    boolean insertPenyebab(PenyebabModel pm);
    boolean updatePenyebab (PenyebabModel pm);
    boolean deletePenyebab (PenyebabModel pm);
    List<PenyebabModel> getPenyebabModels();
    PenyebabModel getPenyebabByid(int id);
    List<PenyebabModel> getByPerangkatVM(PerangkatVMModel mModel);
    List<PenyebabModel> getBygatepos();
    List<PenyebabModel> getByNOTgatepos();
    
     List<PenyebabModel> getByPart(String NamaPart);
}
