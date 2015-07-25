package com.horaceb.asosfashionbrowser.api.json;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryListing {

    @SerializedName("Description")
    private Description description;

    @SerializedName("Listing")
    private List<Listing> listing;

    @SerializedName("SortType")
    private String sortType;

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public List<Listing> getListing() {
        return listing;
    }

    public void setListing(List<Listing> listing) {
        this.listing = listing;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
}
