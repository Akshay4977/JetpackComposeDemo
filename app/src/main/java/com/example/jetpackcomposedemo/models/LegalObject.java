package com.example.jetpackcomposedemo.models;

import com.google.gson.annotations.SerializedName;

public class LegalObject {

    @SerializedName("_key")
    private String key;
    @SerializedName("nextIcon")
    private SettingIcon nextIcon;
    @SerializedName("legalIcon")
    private SettingIcon legalIcon;
    @SerializedName("title")
    private String title;

    public LegalObject(String key, SettingIcon nextIcon, SettingIcon legalIcon, String title) {
        this.key = key;
        this.nextIcon = nextIcon;
        this.legalIcon = legalIcon;
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public SettingIcon getNextIcon() {
        return nextIcon;
    }

    public void setNextIcon(SettingIcon nextIcon) {
        this.nextIcon = nextIcon;
    }

    public SettingIcon getLegalIcon() {
        return legalIcon;
    }

    public void setLegalIcon(SettingIcon legalIcon) {
        this.legalIcon = legalIcon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
