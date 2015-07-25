package com.horaceb.asosfashionbrowser.api.json;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.List;


public class ProductListing {

    @SerializedName("BasePrice")
    private BigDecimal basePrice;

    @SerializedName("Brand")
    private String brand;

    @SerializedName("CurrentPrice")
    private String currentPrice;

    @SerializedName("HasMoreColours")
    private boolean hasMoreColours;

    @SerializedName("IsInSet")
    private boolean isInSet;

    @SerializedName("PreviousPrice")
    private String previousPrice;

    @SerializedName("ProductId")
    private int productId;

    @SerializedName("ProductImageUrl")
    private List<String> productImageUrl;

    @SerializedName("RRP")
    private String rrp;

    @SerializedName("Title")
    private String title;

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal bigDecimal) {
        this.basePrice = bigDecimal;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String Brand) {
        this.brand = Brand;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public boolean getHasMoreColours() {
        return hasMoreColours;
    }

    public void setHasMoreColours(boolean hasMoreColours) {
        this.hasMoreColours = hasMoreColours;
    }

    public boolean getIsInSet() {
        return isInSet;
    }

    public void setIsInSet(boolean isInSet) {
        this.isInSet = isInSet;
    }

    public String getPreviousPrice() {
        return previousPrice;
    }

    public void setPreviousPrice(String previousPrice) {
        this.previousPrice = previousPrice;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public List<String> getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(List<String> productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public String getRRP() {
        return rrp;
    }

    public void setRRP(String rrp) {
        this.rrp = rrp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
