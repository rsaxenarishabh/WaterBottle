package com.waterbottle.Activity.updatemobile;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class UpdateMobileViewModel extends AndroidViewModel {
    UpdateActivityRepository updateActivityRepository;
    public UpdateMobileViewModel(@NonNull Application application) {
        super(application);
        updateActivityRepository=new UpdateActivityRepository();
    }

    public LiveData<String> getStatus() {
        return updateActivityRepository.getStatus();
    }

    public LiveData<Boolean> getProgress() {
        return updateActivityRepository.getProgress();
    }


    public LiveData<UpdateMobileResponse> getUpdateData(){
        return updateActivityRepository.getData();
    }
    public LiveData<UpdateMobileUsingOtpResponse> getUpdateOtpData(){
        return updateActivityRepository.getOtpData();
    }
    public void setAction(String userId, String userMobile, String newMobile) {
         updateActivityRepository.sendData(userId,userMobile,newMobile);

    }


    public void setOtpAction(String newMobile, String otp) {
        updateActivityRepository.sendOtpAction(newMobile,otp);
    }
}
