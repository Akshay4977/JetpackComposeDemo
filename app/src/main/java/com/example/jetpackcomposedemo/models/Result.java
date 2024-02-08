package com.example.jetpackcomposedemo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("_type")
    String Type;

    @SerializedName("passwordFieldType")
    String passwordFieldType;

    @SerializedName("userNameFieldType")
    String userNameFieldType;
    @SerializedName("loginButtonType")
    String loginButtonType;

    @SerializedName("loginButtonTitle")
    String loginButtonTitle;
    @SerializedName("_createdAt")
    private String createdAt;
    @SerializedName("titleText")
    private String titleText;

    @SerializedName("couponUnitText")
    private String couponUnitText;
    @SerializedName("_rev")
    private String rev;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("settingIcon")
    private SettingIcon settingIcon;
    @SerializedName("userIcon")
    private SettingIcon userIcon;
    @SerializedName("_updatedAt")
    private String updatedAt;

    @SerializedName("bannerImage")
    private SettingIcon bannerImage;

    @SerializedName("legalObject")
    private List<LegalObject> legalObject;

    @SerializedName("cardObject")
    private List<CardObject> cardObject;

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

    public String getCouponUnitText() {
        return couponUnitText;
    }

    public void setCouponUnitText(String couponUnitText) {
        this.couponUnitText = couponUnitText;
    }

    public SettingIcon getUserIcon() {
        return userIcon;
    }



    public void setUserIcon(SettingIcon userIcon) {
        this.userIcon = userIcon;
    }

    public String getLoginButtonType() {
        return loginButtonType;
    }

    public void setLoginButtonType(String loginButtonType) {
        this.loginButtonType = loginButtonType;
    }
    public String getLoginButtonTitle() {
        return loginButtonTitle;
    }

    public void setLoginButtonTitle(String loginButtonTitle) {
        this.loginButtonTitle = loginButtonTitle;
    }

    public SettingIcon getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(SettingIcon bannerImage) {
        this.bannerImage = bannerImage;
    }

    public List<LegalObject> getLegalObject() {
        return legalObject;
    }

    public void setLegalObject(List<LegalObject> legalObject) {
        this.legalObject = legalObject;
    }

    public List<CardObject> getCardObject() {
        return cardObject;
    }

    public void setCardObject(List<CardObject> cardObject) {
        this.cardObject = cardObject;
    }
}
