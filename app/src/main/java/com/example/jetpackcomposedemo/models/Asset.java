package com.example.jetpackcomposedemo.models;

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
}
