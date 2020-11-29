package com.waterbottle.Activity.login;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.waterbottle.Retrofit.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    private static final String TAG = "LoginRepository";
    MutableLiveData<LoginResponse> loginMutableLiveData = new MutableLiveData<>();
    MutableLiveData<LoginResponseOtp> loginResponseOtpMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> booleanMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> stringMutableLiveData = new MutableLiveData<>();

    public void sendData(String mobile) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setMobile(mobile);
        booleanMutableLiveData.setValue(true);
        RestClient.mobileVerify(loginRequest, new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response != null) {
                    booleanMutableLiveData.setValue(false);
                    Log.d(TAG, "onResponse:" + response.toString());
                    stringMutableLiveData.setValue("Success");
                    loginMutableLiveData.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                booleanMutableLiveData.setValue(false);
                stringMutableLiveData.setValue("Failed");
            }
        });
    }


    public LiveData<String> getStatus() {
        return stringMutableLiveData;
    }

    public LiveData<Boolean> getProgress() {
        return booleanMutableLiveData;
    }


    public LiveData<LoginResponse> getData() {
        return loginMutableLiveData;
    }

    public void sendOtp(String otp, String mobile) {
        LoginRequestOtp loginRequestOtp = new LoginRequestOtp();
        loginRequestOtp.setMobile(mobile);
        loginRequestOtp.setOtp(otp);
        booleanMutableLiveData.setValue(true);
        RestClient.mobileVerifyUsingOtp(loginRequestOtp, new Callback<LoginResponseOtp>() {
            @Override
            public void onResponse(Call<LoginResponseOtp> call, Response<LoginResponseOtp> response) {
                if (response.body() != null) {
                    booleanMutableLiveData.setValue(false);
                    loginResponseOtpMutableLiveData.setValue(response.body());
                    stringMutableLiveData.setValue("Success");
                }
            }

            @Override
            public void onFailure(Call<LoginResponseOtp> call, Throwable t) {
                booleanMutableLiveData.setValue(false);
                stringMutableLiveData.setValue("Failed");
            }
        });


    }

    public LiveData<LoginResponseOtp> getOtpResponse() {
        return loginResponseOtpMutableLiveData;
    }
}
