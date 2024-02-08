package com.example.jetpackcomposedemo.models;

import com.google.gson.annotations.SerializedName;

public class CardObject {

    @SerializedName("_key")
    private String key;
    @SerializedName("cardIcon")
    private SettingIcon cardIcon;
    @SerializedName("title")
    private String title;

    public CardObject(String key, String cardUrl, SettingIcon cardIcon, String title) {
        this.key = key;
        this.cardUrl = cardUrl;
        this.cardIcon = cardIcon;
        this.title = title;
    }

    public SettingIcon getCardIcon() {
        return cardIcon;
    }

    public void setCardIcon(SettingIcon cardIcon) {
        this.cardIcon = cardIcon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCardUrl() {
        return cardUrl;
    }

    public void setCardUrl(String cardUrl) {
        this.cardUrl = cardUrl;
    }
    @SerializedName("cardUrl")
    private String cardUrl;

}
