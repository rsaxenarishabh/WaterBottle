package com.waterbottle.Activity.login;

import android.app.Application;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class LoginViewModel extends AndroidViewModel {
    LoginRepository loginRepository;


    void LoginRepository() {
    }

    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginRepository = new LoginRepository();
    }

    public LiveData<String> getStatus() {
        return loginRepository.getStatus();
    }

    public LiveData<Boolean> getProgress() {
        return loginRepository.getProgress();
    }

    public LiveData<LoginResponse> getDataValid() {
        return loginRepository.getData();
    }

    public LiveData<LoginResponseOtp> getOtpResponse(){
        return loginRepository.getOtpResponse();
    }

    public void setAction(String mobile) {
        loginRepository.sendData(mobile);
    }

    public void setActionOtp(String otp, String mobile) {
        loginRepository.sendOtp(otp, mobile);
    }
}
