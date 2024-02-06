package com.example.jetpackcomposedemo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("_type")
    String Type;

    @SerializedName("passwordFieldType")
    String passwordFieldType;

    @SerializedName("userNameFieldType")
    String userNameFieldType;
    @SerializedName("_createdAt")
    private String createdAt;
    @SerializedName("titleText")
    private String titleText;
    @SerializedName("_rev")
    private String rev;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("settingIcon")
    private SettingIcon settingIcon;
    @SerializedName("_updatedAt")
    private String updatedAt;


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

    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    public String getTitleText() {
        return titleText;
    }
    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }
    public String getRev() {
        return rev;
    }
    public void setRev(String rev) {
        this.rev = rev;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public SettingIcon getSettingIcon() {
        return settingIcon;
    }
    public void setSettingIcon(SettingIcon settingIcon) {
        this.settingIcon = settingIcon;
    }
    public String getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
