package com.waterbottle.Activity.updatemobile;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateMobileUsingOtpRequest {

@SerializedName("otp")
@Expose
private String otp;
@SerializedName("new_mobile")
@Expose
private String newMobile;

public String getOtp() {
return otp;
}

public void setOtp(String otp) {
this.otp = otp;
}

public String getNewMobile() {
return newMobile;
}

public void setNewMobile(String newMobile) {
this.newMobile = newMobile;
}

}