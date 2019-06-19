/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Interface;

import com.ridwan.Model.JobdeskModel;
import java.util.List;

/**
 *
 * @author ciwong
 */
public interface JobdeskInterface {
    boolean insertJD (JobdeskModel jm);
    boolean updateJD (JobdeskModel jm);
    boolean deleteJD (JobdeskModel jm);
    List<JobdeskModel> getJobdeskModels();
}
