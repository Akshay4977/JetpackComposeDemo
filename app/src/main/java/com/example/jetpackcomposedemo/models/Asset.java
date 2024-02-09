package com.example.jetpackcomposedemo.models;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

public class Asset {

    @SerializedName("_ref")
    private String ref;
    @SerializedName("_type")
    private String type;

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFinalUrl() {
        String[] logoStringArray = ref.split("-");
        String url = "https://cdn.sanity.io/images/kq4riuh0/production/" + logoStringArray[1].toString() + "-" + logoStringArray[2] + "." + logoStringArray[3];
        return url;
    }
}
