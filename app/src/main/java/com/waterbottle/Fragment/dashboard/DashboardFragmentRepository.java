package com.waterbottle.Fragment.dashboard;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.waterbottle.Retrofit.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragmentRepository {
    MutableLiveData<HomeResponse> responseMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> booleanMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> stringMutableLiveData = new MutableLiveData<>();
    private static final String TAG = "DashboardFragmentReposi";

    public void setData(String userId, String fcmToken) {
        HomeRequest homeRequest = new HomeRequest();
        homeRequest.setUserId(userId);
        homeRequest.setFcmToken(fcmToken);
        booleanMutableLiveData.setValue(true);
        RestClient.homeData(homeRequest, new Callback<HomeResponse>() {
            @Override
            public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                if (response != null) {
                    booleanMutableLiveData.setValue(false);
                    Log.d(TAG, "onResponse:" + response.toString());
                    stringMutableLiveData.setValue("Success");
                    responseMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<HomeResponse> call, Throwable t) {

            }
        });
    }

    public LiveData<HomeResponse> getData() {
        return responseMutableLiveData;
    }

    public LiveData<String> getStatus() {
        return stringMutableLiveData;
    }

    public LiveData<Boolean> getProgress() {
        return booleanMutableLiveData;
    }
}
