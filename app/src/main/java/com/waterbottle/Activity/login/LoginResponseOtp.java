package com.waterbottle.Activity.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponseOtp {

    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }



    public class User {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("mobile")
        @Expose
        private String mobile;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

    }

}
