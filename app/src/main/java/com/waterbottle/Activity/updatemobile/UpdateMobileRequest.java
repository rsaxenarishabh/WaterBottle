package com.waterbottle.Activity.updatemobile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateMobileRequest {

@SerializedName("user_id")
@Expose
private String userId;
@SerializedName("old_mobile")
@Expose
private String oldMobile;
@SerializedName("new_mobile")
@Expose
private String newMobile;

public String getUserId() {
return userId;
}

public void setUserId(String userId) {
this.userId = userId;
}

public String getOldMobile() {
return oldMobile;
}

public void setOldMobile(String oldMobile) {
this.oldMobile = oldMobile;
}

public String getNewMobile() {
return newMobile;
}

public void setNewMobile(String newMobile) {
this.newMobile = newMobile;
}

}