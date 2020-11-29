package com.waterbottle.Activity.updatemobile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.waterbottle.Retrofit.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateActivityRepository {
    MutableLiveData<UpdateMobileResponse> mutableLiveData = new MutableLiveData<>();
    MutableLiveData<UpdateMobileUsingOtpResponse> mutableLiveDataotp = new MutableLiveData<>();
    MutableLiveData<Boolean> booleanMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> stringMutableLiveData = new MutableLiveData<>();


    public void sendData(String userId, String userMobile, String newMobile) {
        UpdateMobileRequest updateMobileRequest = new UpdateMobileRequest();
        updateMobileRequest.setUserId(userId);
        updateMobileRequest.setOldMobile(userMobile);
        updateMobileRequest.setNewMobile(newMobile);
        booleanMutableLiveData.setValue(true);
        RestClient.updateMobile(updateMobileRequest, new Callback<UpdateMobileResponse>() {
            @Override
            public void onResponse(Call<UpdateMobileResponse> call, Response<UpdateMobileResponse> response) {
                if (response.body() != null) {
                    booleanMutableLiveData.setValue(false);
                    mutableLiveData.setValue(response.body());
                    stringMutableLiveData.setValue("Success");
                }
            }

            @Override
            public void onFailure(Call<UpdateMobileResponse> call, Throwable t) {
                booleanMutableLiveData.setValue(false);
                stringMutableLiveData.setValue("Failed");
            }
        });

    }

    public void sendOtpAction(String newMobile, String otp) {
        UpdateMobileUsingOtpRequest updateMobileRequest=new UpdateMobileUsingOtpRequest();
            updateMobileRequest.setOtp(otp);
            updateMobileRequest.setNewMobile(newMobile);
            RestClient.verifyMobileUsingOtp(updateMobileRequest, new Callback<UpdateMobileUsingOtpResponse>() {
                @Override
                public void onResponse(Call<UpdateMobileUsingOtpResponse> call, Response<UpdateMobileUsingOtpResponse> response) {
                    if (response.body() != null) {
                        booleanMutableLiveData.setValue(false);
                        mutableLiveDataotp.setValue(response.body());
                        stringMutableLiveData.setValue("Success");
                    }
                }

                @Override
                public void onFailure(Call<UpdateMobileUsingOtpResponse> call, Throwable t) {
                    booleanMutableLiveData.setValue(false);
                    stringMutableLiveData.setValue("Failed");
                }
            });
    }


    public LiveData<UpdateMobileResponse> getData(){
        return mutableLiveData;
    }

    public LiveData<UpdateMobileUsingOtpResponse> getOtpData(){
        return mutableLiveDataotp;
    }



    public LiveData<String> getStatus() {
        return stringMutableLiveData;
    }

    public LiveData<Boolean> getProgress() {
        return booleanMutableLiveData;
    }



}
