package com.horaceb.asosfashionbrowser.api.json;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductCategoryListings {

    @SerializedName("AlsoSearched")
    private List<String> alsoSearched;

    @SerializedName("Description")
    private String description;

    @SerializedName("Facets")
    private List<Facet> facets;

    @SerializedName("ItemCount")
    private int itemCount;

    @SerializedName("Listings")
    private List<ProductListing> listings;

    @SerializedName("RedirectUrl")
    private String redirectUrl;

    @SerializedName("SortType")
    private String sortType;

    public List<String> getAlsoSearched() {
        return alsoSearched;
    }

    public void setAlsoSearched(List<String> alsoSearched) {
        this.alsoSearched = alsoSearched;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Facet> getFacets() {
        return facets;
    }

    public void setFacets(List<Facet> facets) {
        this.facets = facets;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public List<ProductListing> getListings() {
        return listings;
    }

    public void setListings(List<ProductListing> listings) {
        this.listings = listings;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

}