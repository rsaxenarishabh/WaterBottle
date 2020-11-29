package com.waterbottle.Fragment.dashboard;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class DashboardFragmentViewModel extends AndroidViewModel {
    DashboardFragmentRepository fragmentRepository;

    public DashboardFragmentViewModel(@NonNull Application application) {
        super(application);
        fragmentRepository = new DashboardFragmentRepository();
    }

    public void setAction(String userId, String fcmToken) {
        fragmentRepository.setData(userId, fcmToken);
    }

    public LiveData<HomeResponse> homeResponseLiveData() {
        return fragmentRepository.getData();
    }

    public LiveData<String> getStatus() {
        return fragmentRepository.getStatus();
    }

    public LiveData<Boolean> getProgress() {
        return fragmentRepository.getProgress();
    }
}
