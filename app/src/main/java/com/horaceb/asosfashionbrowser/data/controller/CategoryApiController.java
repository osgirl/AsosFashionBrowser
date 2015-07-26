package com.horaceb.asosfashionbrowser.data.controller;

import android.support.annotation.Nullable;
import android.util.Log;

import com.horaceb.asosfashionbrowser.api.AsosApiManager;
import com.horaceb.asosfashionbrowser.api.json.CategoryListing;
import com.horaceb.asosfashionbrowser.data.model.Category;

import java.util.Arrays;
import java.util.List;

import retrofit.RetrofitError;

/**
 * Wraps the request to the API
 * Created by HoraceBG on 23/07/15.
 */
public class CategoryApiController {

    /**
     * Makes two synchronous calls to the API and combines
     * the result.
     *
     * @return A list of both male and female categories.
     * @throws RetrofitError
     */
    @Nullable
    public List<Category> retrieveAllCategories() {
        try {
            // If either of the categories fail to download for any reason, we consider this a failure
            CategoryListing femaleCategories = AsosApiManager.getApi().getFemaleCategories();
            CategoryListing maleCategories = AsosApiManager.getApi().getMaleCategories();
            // Transform the backend responses into something we'll actually want to use.
            CategoryListingTransformer transformer = new CategoryListingTransformer();
            return transformer.transform(Arrays.asList(femaleCategories, maleCategories));
        } catch (RetrofitError error) {
            Log.d(this.getClass().getSimpleName(), error.getCause().getMessage());
            return null;
        }
    }
}
