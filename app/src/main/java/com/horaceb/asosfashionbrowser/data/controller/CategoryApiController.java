package com.horaceb.asosfashionbrowser.data.controller;

import com.horaceb.asosfashionbrowser.api.AsosApiManager;
import com.horaceb.asosfashionbrowser.api.json.CategoryListing;
import com.horaceb.asosfashionbrowser.api.json.Description;
import com.horaceb.asosfashionbrowser.data.model.Category;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import retrofit.RetrofitError;

/**
 * Wraps the request to the API
 * Created by HoraceBG on 23/07/15.
 */
public class CategoryApiController {

    public void getCategories(final Description description) {
        switch (description) {
            case WOMEN:
                AsosApiManager.getApi().getFemaleCategories();
                break;
            case MEN:
                AsosApiManager.getApi().getMaleCategories();
                break;
        }
    }

    /**
     * Makes two synchronous calls to the API and combines
     * the result.
     *
     * @return A list of both male and female categories.
     * @throws RetrofitError
     */
    public List<Category> retrieveAllCategories() throws RetrofitError {
        CategoryListing femaleCategories = AsosApiManager.getApi().getFemaleCategories();
        CategoryListing maleCategories = AsosApiManager.getApi().getMaleCategories();

        // Transform the backend responses into something we'll actually want to use.
        CategoryListingTransformer transformer = new CategoryListingTransformer();
        return transformer.transform(Arrays.asList(femaleCategories, maleCategories));
    }
}
