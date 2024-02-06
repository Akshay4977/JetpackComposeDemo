package com.example.jetpackcomposedemo.models;

import com.example.jetpackcomposedemo.models.Asset;
import com.google.gson.annotations.SerializedName;

public class SettingIcon {

    @SerializedName("_type")
    private String type;
    @SerializedName("asset")
    private Asset asset;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

}