package com.horaceb.asosfashionbrowser.api.json;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.List;

public class AssociatedProduct {

    @SerializedName("BasePrice")
    private BigDecimal basePrice;

    @SerializedName("Brand")
    private String brand;

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
    private String sku;

    @SerializedName("Title")
    private String title;

    @SerializedName("ProductType")
    private String productType;

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public boolean isIsInSet() {
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

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public List<String> getProductImageUrls() {
        return productImageUrls;
    }

    public void setProductImageUrls(List<String> productImageUrls) {
        this.productImageUrls = productImageUrls;
    }

    public String getRRP() {
        return rrp;
    }

    public void setRRP(String rrp) {
        this.rrp = rrp;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

}