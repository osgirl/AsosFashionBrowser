package com.horaceb.asosfashionbrowser.api.json;

import com.google.gson.annotations.SerializedName;

public class FacetValue {

    @SerializedName("Count")
    private int count;

    @SerializedName("Id")
    private String id;

    @SerializedName("Name")
    private String name;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}