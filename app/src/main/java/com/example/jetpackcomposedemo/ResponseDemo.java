package com.example.jetpackcomposedemo;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseDemo {

    @SerializedName("query")
    String query;

    @SerializedName("result")
    List<Result> result;

    @SerializedName("ms")
    int ms;

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setMs(int ms) {
        this.ms = ms;
    }

    public int getMs() {
        return ms;
    }
}
