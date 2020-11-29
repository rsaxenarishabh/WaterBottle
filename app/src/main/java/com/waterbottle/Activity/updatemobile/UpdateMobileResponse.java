package com.waterbottle.Activity.updatemobile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateMobileResponse {

@SerializedName("message")
@Expose
private String message;
@SerializedName("status_code")
@Expose
private Integer statusCode;
@SerializedName("otp")
@Expose
private Integer otp;

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

public Integer getStatusCode() {
return statusCode;
}

public void setStatusCode(Integer statusCode) {
this.statusCode = statusCode;
}

public Integer getOtp() {
return otp;
}

public void setOtp(Integer otp) {
this.otp = otp;
}

}