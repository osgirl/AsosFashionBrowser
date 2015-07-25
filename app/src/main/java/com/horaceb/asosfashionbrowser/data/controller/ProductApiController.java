package com.horaceb.asosfashionbrowser.data.controller;

import com.horaceb.asosfashionbrowser.api.AsosApiManager;
import com.horaceb.asosfashionbrowser.api.json.ProductDetails;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ProductApiController implements Callback<ProductDetails> {

    public void getProductDetails(final String catId) {
        AsosApiManager.getApi().getProductDetails(catId, this);
    }

    @Override
    public void success(ProductDetails productDetails, Response response) {
        String s = "";
        String x = "";
    }

    @Override
    public void failure(RetrofitError error) {
        String s = "";
        String x = "";
    }
}
