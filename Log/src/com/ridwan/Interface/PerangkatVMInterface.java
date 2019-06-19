/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Interface;

import com.ridwan.Model.PerangkatVMModel;
import java.util.List;

/**
 *
 * @author ciwong
 */
public interface PerangkatVMInterface {
    boolean insertPVM (PerangkatVMModel pvmm);
    boolean updatePVM (PerangkatVMModel pvmm);
    boolean deletePVM (PerangkatVMModel pvmm);
    List<PerangkatVMModel> getPerangkatVMModels();
    PerangkatVMModel perangkatVMModelbyid(int id);
    List<PerangkatVMModel> getPartbyPerangkatnotVM();
    List<PerangkatVMModel> getPartbyPerangkat();
}
