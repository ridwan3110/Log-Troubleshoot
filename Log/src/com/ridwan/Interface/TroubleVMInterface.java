/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Interface;

import com.ridwan.Model.PenyebabModel;
import com.ridwan.Model.TroubleVMModel;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ciwong
 */
public interface TroubleVMInterface {
    boolean insertTroubleVM (TroubleVMModel tvmm);
    boolean updateTroubleVM (TroubleVMModel tvmm);
    boolean deleteTroubleVM (TroubleVMModel tvmm);
    List<TroubleVMModel> getTroubleVMModels();
     List<TroubleVMModel> getVMModelsByTanggal(Date tglnya);
    List<TroubleVMModel> getVMModelsBypenyebab(PenyebabModel penyebab);
     String setAutoNumberVM();
}
