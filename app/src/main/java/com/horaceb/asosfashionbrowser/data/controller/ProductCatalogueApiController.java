package com.horaceb.asosfashionbrowser.data.controller;

import com.horaceb.asosfashionbrowser.api.AsosApiManager;
import com.horaceb.asosfashionbrowser.api.OnResultListener;
import com.horaceb.asosfashionbrowser.api.json.ProductCategoryListings;
import com.horaceb.asosfashionbrowser.data.model.Catalogue;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Wraps calls to the backend to retrieve a product 'catalogue',
 * that is a list of items and the name of the category those items belong to
 */
public class ProductCatalogueApiController implements Callback<ProductCategoryListings> {

    private OnResultListener<Catalogue> listener;

    public void getProductByCategory(final String catId, final OnResultListener<Catalogue> listener) {
        this.listener = listener;
        AsosApiManager.getApi().getProductsByCategory(catId, this);
    }

    @Override
    public void success(ProductCategoryListings productCategoryListings, Response response) {
        // Transform
        ProductListingTransformer transformer = new ProductListingTransformer();
        listener.onSuccess(transformer.transform(productCategoryListings));
    }

    @Override
    public void failure(RetrofitError error) {
        listener.onError(error.getMessage());
    }
}
