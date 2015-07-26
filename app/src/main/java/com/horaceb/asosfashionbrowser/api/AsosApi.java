package com.horaceb.asosfashionbrowser.api;

import com.horaceb.asosfashionbrowser.api.json.CategoryListing;
import com.horaceb.asosfashionbrowser.api.json.ProductCategoryListings;
import com.horaceb.asosfashionbrowser.api.json.ProductDetails;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Calls to the API
 * Created by HoraceBG on 25/07/15.
 */
public interface AsosApi {

    @GET("/cats_women.json")
    void getFemaleCategories(Callback<CategoryListing> callback);

    @GET("/cats_men.json")
    void getMaleCategories(Callback<CategoryListing> callback);

    @GET("/cats_women.json")
    CategoryListing getFemaleCategories();

    @GET("/cats_men.json")
    CategoryListing getMaleCategories();

    @GET("/anyproduct_details.json")
    void getProductDetails(@Query("catid") final String catId, Callback<ProductDetails> callback);

    @GET("/anycat_products.json")
    void getProductsByCategory(@Query("catid") final String catId, Callback<ProductCategoryListings> callback);

}
