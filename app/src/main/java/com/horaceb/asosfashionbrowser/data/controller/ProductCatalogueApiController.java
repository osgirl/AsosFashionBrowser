package com.horaceb.asosfashionbrowser.data.controller;

import com.horaceb.asosfashionbrowser.api.AsosApiManager;
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

    private OnResponseListener listener;

    public interface OnResponseListener {
        void onSuccess(final Catalogue catalogue);

        void onError(final String errorMessage);
    }

    public void getProductByCategory(final String catId, final OnResponseListener listener) {
        this.listener = listener;
        AsosApiManager.getApi().getProductsByCategory(catId, this);
    }

    @Override
    public void success(ProductCategoryListings productCategoryListings, Response response) {
        // Transform
        ProductListingTransformer transformer = new ProductListingTransformer();
        Catalogue catalogue = transformer.transform(productCategoryListings);
        listener.onSuccess(catalogue);
    }

    @Override
    public void failure(RetrofitError error) {
        listener.onError(error.getMessage());
    }
}
