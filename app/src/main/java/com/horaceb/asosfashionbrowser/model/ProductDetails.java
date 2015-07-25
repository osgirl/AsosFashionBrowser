package com.horaceb.asosfashionbrowser.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.List;

public class ProductDetails {

    @SerializedName("BasePrice")
    private BigDecimal basePrice;

    @SerializedName("Brand")
    private String brand;

    @SerializedName("Colour")
    private Object colour;

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
    private Object size;

    @SerializedName("Sku")
    private String sku;

    @SerializedName("Title")
    private String title;

    @SerializedName("AdditionalInfo")
    private String additionalInfo;

    @SerializedName("AssociatedProducts")
    private List<AssociatedProduct> associatedProducts;

    @SerializedName("CareInfo")
    private String careInfo;

    @SerializedName("Description")
    private String description;

    @SerializedName("Variants")
    private List<Variant> variants;

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String Brand) {
        this.brand = Brand;
    }

    public Object getColour() {
        return colour;
    }

    public void setColour(Object Colour) {
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

    public Object getSize() {
        return size;
    }

    public void setSize(Object Size) {
        this.size = Size;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String Sku) {
        this.sku = Sku;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String Title) {
        this.title = Title;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String AdditionalInfo) {
        this.additionalInfo = AdditionalInfo;
    }

    public List<AssociatedProduct> getAssociatedProducts() {
        return associatedProducts;
    }

    public void setAssociatedProducts(List<AssociatedProduct> AssociatedProducts) {
        this.associatedProducts = AssociatedProducts;
    }

    public String getCareInfo() {
        return careInfo;
    }

    public void setCareInfo(String CareInfo) {
        this.careInfo = CareInfo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String Description) {
        this.description = Description;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> Variants) {
        this.variants = Variants;
    }

}