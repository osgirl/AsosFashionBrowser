package com.horaceb.asosfashionbrowser.data.controller;

import com.horaceb.asosfashionbrowser.api.AsosApiManager;
import com.horaceb.asosfashionbrowser.api.json.ProductCategoryListings;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ProductByCategoryApiController implements Callback<ProductCategoryListings> {

    public void getProductByCategory(final String catId) {
        AsosApiManager.getApi().getProductsByCategory(catId, this);
    }

    @Override
    public void success(ProductCategoryListings productCategoryListings, Response response) {
        String s = "";
        String se = "";
    }

    @Override
    public void failure(RetrofitError error) {
        String s = "";
        String se = "";
    }
}
