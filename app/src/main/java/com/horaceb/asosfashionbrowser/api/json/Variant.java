package com.horaceb.asosfashionbrowser.api.json;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Variant {

    @SerializedName("BasePrice")
    private float basePrice;

    @SerializedName("Brand")
    private Object brand;

    @SerializedName("Colour")
    private String colour;

    @SerializedName("CurrentPrice")
    private String currentPrice;

    @SerializedName("InStock")
    private boolean inStock;

    @SerializedName("IsInSet")
    private boolean isInSet;

    @SerializedName("PreviousPrice")
    private String previousPrice;

    @SerializedName("PriceType")
    private String priceType;

    @SerializedName("ProductId")
    private int productId;

    @SerializedName("ProductImageUrls")
    private List<String> productImageUrls;

    @SerializedName("RRP")
    private String rrp;

    @SerializedName("Size")
    private String size;

    @SerializedName("Sku")
    private Object sku;

    @SerializedName("Title")
    private Object title;

    public float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(float BasePrice) {
        this.basePrice = BasePrice;
    }

    public Object getBrand() {
        return brand;
    }

    public void setBrand(Object Brand) {
        this.brand = Brand;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String Colour) {
        this.colour = Colour;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String CurrentPrice) {
        this.currentPrice = CurrentPrice;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean InStock) {
        this.inStock = InStock;
    }

    public boolean isIsInSet() {
        return isInSet;
    }

    public void setIsInSet(boolean IsInSet) {
        this.isInSet = IsInSet;
    }

    public String getPreviousPrice() {
        return previousPrice;
    }

    public void setPreviousPrice(String PreviousPrice) {
        this.previousPrice = PreviousPrice;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String PriceType) {
        this.priceType = PriceType;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int ProductId) {
        this.productId = ProductId;
    }

    public List<String> getProductImageUrls() {
        return productImageUrls;
    }

    public void setProductImageUrls(List<String> ProductImageUrls) {
        this.productImageUrls = ProductImageUrls;
    }

    public String getRRP() {
        return rrp;
    }

    public void setRRP(String RRP) {
        this.rrp = RRP;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String Size) {
        this.size = Size;
    }

    public Object getSku() {
        return sku;
    }

    public void setSku(Object Sku) {
        this.sku = Sku;
    }

    public Object getTitle() {
        return title;
    }

    public void setTitle(Object Title) {
        this.title = Title;
    }

}