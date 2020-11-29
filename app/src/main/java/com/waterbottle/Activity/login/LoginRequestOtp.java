package com.waterbottle.Activity.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequestOtp {

    @SerializedName("otp")
    @Expose
    private String otp;
    @SerializedName("mobile")
    @Expose
    private String mobile;

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
