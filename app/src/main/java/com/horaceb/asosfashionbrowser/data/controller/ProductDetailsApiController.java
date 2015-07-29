package com.horaceb.asosfashionbrowser.data.controller;

import android.support.annotation.NonNull;

import com.horaceb.asosfashionbrowser.api.AsosApiManager;
import com.horaceb.asosfashionbrowser.api.OnResultListener;
import com.horaceb.asosfashionbrowser.api.json.ProductDetails;
import com.horaceb.asosfashionbrowser.data.model.ItemDetail;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ProductDetailsApiController implements Callback<ProductDetails> {

    private OnResultListener<ItemDetail> listener;

    public void getProductDetails(@NonNull final long productId, @NonNull final OnResultListener<ItemDetail> listener) {
        this.listener = listener;
        AsosApiManager.getApi().getProductDetails(String.valueOf(productId), this);
    }

    @Override
    public void success(ProductDetails productDetails, Response response) {
        ProductTransformer transformer = new ProductTransformer();
        listener.onSuccess(transformer.transform(productDetails));
    }

    @Override
    public void failure(RetrofitError error) {
        listener.onError(error.getMessage());
    }
}
