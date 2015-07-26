package com.horaceb.asosfashionbrowser.data.controller;

import com.horaceb.asosfashionbrowser.api.json.CategoryListing;
import com.horaceb.asosfashionbrowser.api.json.Listing;
import com.horaceb.asosfashionbrowser.data.model.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts the backend response into a list of
 * objects we'll use throughout the app.
 */
public class CategoryListingTransformer {

    public List<Category> transform(List<CategoryListing> listings) {
        List<Category> categories = new ArrayList<>();

        for (CategoryListing categorylisting : listings) {
            for (Listing listing : categorylisting.getListing()) {
                Category category = new Category();
                category.setCategoryId(listing.getCategoryId());
                category.setName(listing.getName());
                category.setGender(categorylisting.getDescription().name());
                category.setProductCount(listing.getProductCount());
                categories.add(category);
            }
        }
        return categories;
    }
}
