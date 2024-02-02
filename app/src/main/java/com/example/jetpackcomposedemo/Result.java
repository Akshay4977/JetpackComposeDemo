package com.example.jetpackcomposedemo;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("_type")
    String Type;

    @SerializedName("passwordFieldType")
    String passwordFieldType;

    @SerializedName("userNameFieldType")
    String userNameFieldType;


    public void setType(String Type) {
        this.Type = Type;
    }

    public String getType() {
        return Type;
    }

    public void setPasswordFieldType(String passwordFieldType) {
        this.passwordFieldType = passwordFieldType;
    }

    public String getPasswordFieldType() {
        return passwordFieldType;
    }

    public void setUserNameFieldType(String userNameFieldType) {
        this.userNameFieldType = userNameFieldType;
    }

    public String getUserNameFieldType() {
        return userNameFieldType;
    }
}

