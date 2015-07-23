package com.horaceb.asosfashionbrowser.model;

import com.google.gson.annotations.SerializedName;

public class Listing {

    @SerializedName("CategoryId")
    private String categoryId;

    @SerializedName("Name")
    private String name;

    @SerializedName("ProductCount")
    private int productCount;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }
}
